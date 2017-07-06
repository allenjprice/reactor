(ns mock.stripe.event)


;; =============================================================================
;; Charge
;; =============================================================================


(def successful-charge
  {:request          "req_Au7bbGcclGWoA1",
   :type             "charge.succeeded",
   :created          1498330309,
   :pending_webhooks 0,
   :id               "evt_1AYJMHIvRccmW9nO5FPxwRwc",
   :api_version      "2016-07-06",
   :livemode         false,
   :object           "event",
   :data
   {:object
    {:description          "test bank charge from Ribbon lib",
     :amount               1000,
     :application_fee      nil,
     :source_transfer      nil,
     :application          nil,
     :failure_message      nil,
     :transfer             "tr_1AYJMHIvRccmW9nOkFMaqd5Z",
     :captured             true,
     :dispute              nil,
     :receipt_email        "josh@joinstarcity.com",
     :on_behalf_of         "acct_191838JDow24Tc1a",
     :created              1498330308,
     :outcome
     {:network_status "approved_by_network",
      :reason         nil,
      :risk_level     "not_assessed",
      :seller_message "Payment complete.",
      :type           "authorized"},
     :receipt_number       nil,
     :source
     {:bank_name           "STRIPE TEST BANK",
      :account_holder_type nil,
      :last4               "6789",
      :customer            "cus_A7rRh3Ro3c2dmx",
      :currency            "usd",
      :routing_number      "110000000",
      :status              "verified",
      :id                  "ba_19nbiQIvRccmW9nOqgXy74mB",
      :account_holder_name nil,
      :country             "US",
      :metadata            {},
      :object              "bank_account",
      :fingerprint         "rfpYTWA1pcwqao0x"},
     :customer             "cus_A7rRh3Ro3c2dmx",
     :balance_transaction  "txn_1AYJMGIvRccmW9nOkhhPnd4q",
     :transfer_group       "group_py_1AYJMGIvRccmW9nO1fG6pIj5",
     :invoice              nil,
     :currency             "usd",
     :refunded             false,
     :review               nil,
     :status               "succeeded",
     :id                   "py_1AYJMGIvRccmW9nO1fG6pIj5",
     :paid                 true,
     :failure_code         nil,
     :order                nil,
     :livemode             false,
     :shipping             nil,
     :fraud_details        {},
     :metadata             {},
     :destination          "acct_191838JDow24Tc1a",
     :object               "charge",
     :statement_descriptor nil,
     :refunds
     {:object      "list",
      :data        [],
      :has_more    false,
      :total_count 0,
      :url         "/v1/charges/py_1AYJMGIvRccmW9nO1fG6pIj5/refunds"},
     :amount_refunded      0}}})



(def failed-charge
  (assoc successful-charge :type "charge.failed"))


;; =============================================================================
;; Customer
;; =============================================================================


(defn source-updated
  ([]
   (source-updated "verified"))
  ([status]
   {:request          "req_AqoWnkSXWlRGKh",
    :type             "customer.source.updated",
    :created          1497567432,
    :pending_webhooks 0,
    :id               "evt_1AV6toIvRccmW9nO2pu0TOYc",
    :api_version      "2016-07-06",
    :livemode         false,
    :object           "event",
    :data
    {:object
     {:bank_name           "STRIPE TEST BANK",
      :account_holder_type "individual",
      :last4               "6789",
      :customer            "cus_AqoW7OTNg0Ld7t",
      :currency            "usd",
      :routing_number      "110000000",
      :status              status,
      :id                  "ba_1AV6tfIvRccmW9nOfjsLP6DZ",
      :account_holder_name "Josh Lehman",
      :country             "US",
      :metadata            {},
      :object              "bank_account",
      :fingerprint         "rfpYTWA1pcwqao0x"},
     :previous_attributes {:status "new"}}}))


;; =============================================================================
;; Invoice
;; =============================================================================

(def invoice-created
  {:request          "req_Aqp42ywGsN0Yfb",
   :type             "invoice.created",
   :created          1497569472,
   :pending_webhooks 0,
   :id               "evt_1AV7QiIvRccmW9nOYBqnkTcE",
   :api_version      "2016-07-06",
   :livemode         false,
   :object           "event",
   :data
   {:object
    {:description           nil,
     :closed                false,
     :date                  1497569471,
     :next_payment_attempt  nil,
     :application_fee       nil,
     :webhooks_delivered_at nil,
     :lines
     {:object      "list",
      :data
      [{:description       nil,
        :proration         false,
        :amount            10000,
        :subscription_item "si_1AV7QhIvRccmW9nOpfT399Ur",
        :type              "subscription",
        :currency          "usd",
        :id                "sub_Aqp4vYf86GyvQV",
        :discountable      true,
        :livemode          false,
        :period            {:start 1496346874, :end 1500161471},
        :quantity          1,
        :plan
        {:amount               10000,
         :name                 "cleaning,weekly",
         :created              1497569471,
         :currency             "usd",
         :interval_count       1,
         :id                   "cleaning,weekly",
         :trial_period_days    nil,
         :interval             "month",
         :livemode             false,
         :metadata             {},
         :object               "plan",
         :statement_descriptor nil},
        :metadata          {},
        :object            "line_item",
        :subscription      nil}],
      :has_more    false,
      :total_count 1,
      :url         "/v1/invoices/in_1AV7QhIvRccmW9nOGsTAZinx/lines"},
     :subtotal              10000,
     :receipt_number        nil,
     :period_start          1497567529,
     :forgiven              false,
     :tax                   nil,
     :customer              "cus_AqoW7OTNg0Ld7t",
     :charge                "ch_1AV7QiIvRccmW9nOh9uXoIYA",
     :currency              "usd",
     :total                 10000,
     :id                    "in_1AV7QhIvRccmW9nOGsTAZinx",
     :paid                  true,
     :attempt_count         0,
     :period_end            1497569471,
     :starting_balance      0,
     :livemode              false,
     :attempted             true,
     :ending_balance        0,
     :discount              nil,
     :metadata              {},
     :object                "invoice",
     :statement_descriptor  nil,
     :amount_due            10000,
     :subscription          "sub_Aqp4vYf86GyvQV",
     :tax_percent           nil}}})


(def invoice-updated
  {:request          nil,
   :type             "invoice.updated",
   :created          1488408525,
   :pending_webhooks 0,
   :id               "evt_19sgFVJDow24Tc1a3Azk8ppH",
   :api_version      "2016-07-06",
   :livemode         false,
   :object           "event",
   :data
   {:object
    {:description           nil,
     :closed                false,
     :date                  1488404659,
     :next_payment_attempt  nil,
     :application_fee       50000,
     :webhooks_delivered_at 1488404914,
     :lines
     {:object      "list",
      :data
      [{:description       nil,
        :proration         false,
        :amount            200000,
        :subscription_item "si_19fc4AJDow24Tc1aqFzDc0QC",
        :type              "subscription",
        :currency          "usd",
        :id                "sub_9zbG4ycfe4VA1u",
        :discountable      true,
        :livemode          false,
        :period            {:start 1488404581, :end 1491082981},
        :quantity          1,
        :plan
        {:amount               200000,
         :name                 "Member User's rent at West SoMa",
         :created              1485294181,
         :currency             "usd",
         :interval_count       1,
         :id                   "285873023222909",
         :trial_period_days    nil,
         :interval             "month",
         :livemode             false,
         :metadata             {},
         :object               "plan",
         :statement_descriptor "Starcity Rent"},
        :metadata          {},
        :object            "line_item",
        :subscription      nil}],
      :has_more    false,
      :total_count 1,
      :url         "/v1/invoices/in_19sfF9JDow24Tc1a9cMWLVfb/lines"},
     :subtotal              200000,
     :receipt_number        nil,
     :period_start          1485985381,
     :forgiven              false,
     :tax                   nil,
     :customer              "cus_9zbGYwH44Ht3vF",
     :charge                "py_19sgFVJDow24Tc1adQeXuztY",
     :currency              "usd",
     :total                 200000,
     :id                    "in_19sfF9JDow24Tc1a9cMWLVfb",
     :paid                  false,
     :attempt_count         0,
     :period_end            1488404581,
     :starting_balance      0,
     :livemode              false,
     :attempted             true,
     :ending_balance        0,
     :discount              nil,
     :metadata              {},
     :object                "invoice",
     :statement_descriptor  nil,
     :amount_due            200000,
     :subscription          "sub_9zbG4ycfe4VA1u",
     :tax_percent           nil},
    :previous_attributes {:application_fee nil, :attempted false, :charge nil, :ending_balance nil, :next_payment_attempt 1488408259}}})


(def invoice-payment-succeeded
  {:request          nil,
   :type             "invoice.payment_succeeded",
   :created          1496357035,
   :pending_webhooks 0,
   :id               "evt_1AQ21HJDow24Tc1auKJnmQFA",
   :api_version      "2016-07-06",
   :livemode         false,
   :object           "event",
   :data
   {:object
    {:description           nil,
     :closed                true,
     :date                  1496353425,
     :next_payment_attempt  nil,
     :application_fee       50000,
     :webhooks_delivered_at 1496353431,
     :lines
     {:object      "list",
      :data
      [{:description       nil,
        :proration         false,
        :amount            200000,
        :subscription_item "si_19fc4AJDow24Tc1aqFzDc0QC",
        :type              "subscription",
        :currency          "usd",
        :id                "sub_9zbG4ycfe4VA1u",
        :discountable      true,
        :livemode          false,
        :period            {:start 1496353381, :end 1498945381},
        :quantity          1,
        :plan
        {:amount               200000,
         :name                 "Member User's rent at West SoMa",
         :created              1485294181,
         :currency             "usd",
         :interval_count       1,
         :id                   "285873023222909",
         :trial_period_days    nil,
         :interval             "month",
         :livemode             false,
         :metadata             {},
         :object               "plan",
         :statement_descriptor "Starcity Rent"},
        :metadata          {},
        :object            "line_item",
        :subscription      nil}],
      :has_more    false,
      :total_count 1,
      :url         "/v1/invoices/in_1AQ153JDow24Tc1aIrWkAyK8/lines"},
     :subtotal              200000,
     :receipt_number        nil,
     :period_start          1493674981,
     :forgiven              false,
     :tax                   nil,
     :customer              "cus_9zbGYwH44Ht3vF",
     :charge                "py_1AQ21FJDow24Tc1aModsVYE1",
     :currency              "usd",
     :total                 200000,
     :id                    "in_1AQ153JDow24Tc1aIrWkAyK8",
     :paid                  true,
     :attempt_count         0,
     :period_end            1496353381,
     :starting_balance      0,
     :livemode              false,
     :attempted             true,
     :ending_balance        0,
     :discount              nil,
     :metadata              {},
     :object                "invoice",
     :statement_descriptor  nil,
     :amount_due            200000,
     :subscription          "sub_9zbG4ycfe4VA1u",
     :tax_percent           nil}}})


(def invoice-payment-failed
  {:request          nil,
   :type             "invoice.payment_failed",
   :created          1496357035,
   :pending_webhooks 0,
   :id               "evt_1AQ21HJDow24Tc1auKJnmQFA",
   :api_version      "2016-07-06",
   :livemode         false,
   :object           "event",
   :data
   {:object
    {:description           nil,
     :closed                true,
     :date                  1496353425,
     :next_payment_attempt  nil,
     :application_fee       50000,
     :webhooks_delivered_at 1496353431,
     :lines
     {:object      "list",
      :data
      [{:description       nil,
        :proration         false,
        :amount            200000,
        :subscription_item "si_19fc4AJDow24Tc1aqFzDc0QC",
        :type              "subscription",
        :currency          "usd",
        :id                "sub_9zbG4ycfe4VA1u",
        :discountable      true,
        :livemode          false,
        :period            {:start 1496353381, :end 1498945381},
        :quantity          1,
        :plan
        {:amount               200000,
         :name                 "Member User's rent at West SoMa",
         :created              1485294181,
         :currency             "usd",
         :interval_count       1,
         :id                   "285873023222909",
         :trial_period_days    nil,
         :interval             "month",
         :livemode             false,
         :metadata             {},
         :object               "plan",
         :statement_descriptor "Starcity Rent"},
        :metadata          {},
        :object            "line_item",
        :subscription      nil}],
      :has_more    false,
      :total_count 1,
      :url         "/v1/invoices/in_1AQ153JDow24Tc1aIrWkAyK8/lines"},
     :subtotal              200000,
     :receipt_number        nil,
     :period_start          1493674981,
     :forgiven              false,
     :tax                   nil,
     :customer              "cus_9zbGYwH44Ht3vF",
     :charge                "py_1AQ21FJDow24Tc1aModsVYE1",
     :currency              "usd",
     :total                 200000,
     :id                    "in_1AQ153JDow24Tc1aIrWkAyK8",
     :paid                  true,
     :attempt_count         0,
     :period_end            1496353381,
     :starting_balance      0,
     :livemode              false,
     :attempted             true,
     :ending_balance        0,
     :discount              nil,
     :metadata              {},
     :object                "invoice",
     :statement_descriptor  nil,
     :amount_due            200000,
     :subscription          "sub_9zbG4ycfe4VA1u",
     :tax_percent           nil}}})


(def trial-will-end
  {:request          nil,
   :type             "customer.subscription.trial_will_end",
   :created          1498692985,
   :pending_webhooks 0,
   :id               "evt_1AZphtH2E3GdRImXIL8r5cbT",
   :api_version      "2016-07-06",
   :livemode         true,
   :object           "event",
   :data
   {:object
    {:canceled_at             nil,
     :application_fee_percent 30.0,
     :start                   1496705719,
     :created                 1496705719,
     :current_period_end      1498952119,
     :trial_end               1498952119,
     :customer                "cus_An4szDhIX2TzRO",
     :ended_at                nil,
     :status                  "trialing",
     :id                      "sub_An4sAMIw1VqLVw",
     :cancel_at_period_end    false,
     :livemode                true,
     :quantity                1,
     :items
     {:object      "list",
      :data
      [{:id       "si_1ARUjDH2E3GdRImXin3xzKtb",
        :object   "subscription_item",
        :created  1496705719,
        :plan
        {:amount               190000,
         :name                 "Somebody's rent at West SoMa",
         :created              1496705719,
         :currency             "usd",
         :interval_count       1,
         :id                   "285873023228606",
         :trial_period_days    nil,
         :interval             "month",
         :livemode             true,
         :metadata             {},
         :object               "plan",
         :statement_descriptor "Starcity Rent"},
        :quantity 1}],
      :has_more    false,
      :total_count 1,
      :url         "/v1/subscription_items?subscription=sub_An4sAMIw1VqLVw"},
     :trial_start             1496705719,
     :discount                nil,
     :plan
     {:amount               190000,
      :name                 "Somebody's rent at West SoMa",
      :created              1496705719,
      :currency             "usd",
      :interval_count       1,
      :id                   "285873023228606",
      :trial_period_days    nil,
      :interval             "month",
      :livemode             true,
      :metadata             {},
      :object               "plan",
      :statement_descriptor "Starcity Rent"},
     :current_period_start    1496705719,
     :metadata                {},
     :object                  "subscription",
     :tax_percent             nil}}})


(def subscription-deleted
  {:request          nil,
   :type             "customer.subscription.deleted",
   :created          1493901683,
   :pending_webhooks 0,
   :id               "evt_1AFjGpH2E3GdRImXTyDRSg04",
   :api_version      "2016-07-06",
   :livemode         true,
   :object           "event",
   :data
   {:object
    {:canceled_at             1493901683,
     :application_fee_percent 30.0,
     :start                   1488857922,
     :created                 1488857922,
     :current_period_end      1496288322,
     :trial_end               1491017922,
     :customer                "cus_AF3Gbhl208ZP9J",
     :ended_at                1493901683,
     :status                  "canceled",
     :id                      "sub_AF3GvxdqLo3Ur6",
     :cancel_at_period_end    false,
     :livemode                true,
     :quantity                1,
     :items
     {:object      "list",
      :data
      [{:id       "si_19uZ9qH2E3GdRImXcRePsbIY",
        :object   "subscription_item",
        :created  1488857922,
        :plan
        {:amount               150000,
         :name                 "Somebody's rent at West SoMa",
         :created              1488857921,
         :currency             "usd",
         :interval_count       1,
         :id                   "285873023226284",
         :trial_period_days    nil,
         :interval             "month",
         :livemode             true,
         :metadata             {},
         :object               "plan",
         :statement_descriptor "Starcity Rent"},
        :quantity 1}],
      :has_more    false,
      :total_count 1,
      :url         "/v1/subscription_items?subscription=sub_AF3GvxdqLo3Ur6"},
     :trial_start             1488857922,
     :discount                nil,
     :plan
     {:amount               150000,
      :name                 "Migerta Ndrepepaj's rent at West SoMa",
      :created              1488857921,
      :currency             "usd",
      :interval_count       1,
      :id                   "285873023226284",
      :trial_period_days    nil,
      :interval             "month",
      :livemode             true,
      :metadata             {},
      :object               "plan",
      :statement_descriptor "Starcity Rent"},
     :current_period_start    1493609922,
     :metadata                {},
     :object                  "subscription",
     :tax_percent             nil}}})