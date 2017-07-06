(ns reactor.handlers.rent
  (:require [blueprints.models
             [member-license :as member-license]
             [rent-payment :as rent-payment]]
            [datomic.api :as d]
            [reactor.dispatch :as dispatch]
            [reactor.handlers.common :refer :all]
            [reactor.models.event :as event]
            [toolbelt.date :as date]
            [mailer.core :as mailer]
            [blueprints.models.account :as account]
            [mailer.message :as mm]))


;; =============================================================================
;; Create All Payments
;; =============================================================================


;; The `:rent-payments/create-all` should be triggered by a scheduler on the
;; first of the month. This event then spawns a new event for each member that
;; needs to have a rent payment generated for him/her.


(defn active-licenses
  "Query all active licenses that are not on autopay that have not yet commenced."
  [db period]
  (d/q '[:find ?l ?p
         :in $ ?period
         :where
         ;; active licenses
         [?l :member-license/status :member-license.status/active]
         [?l :member-license/unit ?u]
         [?l :member-license/price ?p]
         [?l :member-license/commencement ?c]
         ;; not on autopay
         [(missing? $ ?l :member-license/subscription-id)]
         ;; license has commenced
         [(.before ^java.util.Date ?c ?period)]] ; in other words, now is after commencement
       db period))


(defn create-payment-events
  [db event period]
  (let [actives (active-licenses db period)]
    (mapv
     (fn [[member-license-id amount]]
       (let [ml    (d/entity db member-license-id)
             tz    (member-license/time-zone ml)
             start (date/beginning-of-day period tz)
             end   (date/end-of-month start tz)]
         (event/job :rent-payment/create {:params       {:start             start
                                                         :end               end
                                                         :amount            amount
                                                         :member-license-id member-license-id}
                                          :triggered-by event})))
     actives)))


(defmethod dispatch/job :rent-payments/create-all [deps event params]
  (assert (:period params) "The time period to create payments for must be supplied!")
  (create-payment-events (->db deps) event (:period params)))


;; =============================================================================
;; Create Payment
;; =============================================================================


(defn rent-reminder-body [account amount hostname]
  (mm/msg
   (mm/greet (account/first-name account))
   (mm/p (format "It's that time again! Your rent payment of $%.2f is <b>due by the 5th</b>." amount))
   (mm/p "Please log into your member dashboard " [:a {:href (str hostname "/me/account/rent")} "here"]
         " to pay your rent with ACH. <b>If you'd like to stop getting these reminders, sign up for autopay while you're there!</b>")
   (mm/sig)))


(defmethod dispatch/notify :rent-payment/create
  [deps event {:keys [member-license-id amount]}]
  (let [license (d/entity (->db deps) member-license-id)
        account (member-license/account license)]
    (mailer/send
     (->mailer deps)
     (account/email account)
     "Starcity: Your Rent is Due"
     (rent-reminder-body account amount (->public-hostname deps))
     {:uuid (event/uuid event)})))


(defmethod dispatch/job :rent-payment/create
  [deps event {:keys [start end amount member-license-id] :as params}]
  (let [payment (rent-payment/create amount start end :rent-payment.status/due)]
    [(event/notify :rent-payment/create
                   {:params       {:member-license-id member-license-id
                                   :amount            amount}
                    :triggered-by event})
     payment]))