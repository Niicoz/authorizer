(ns authorizer.challenge
  (:require [authorizer.db :as c.db]
            [authorizer.logic :as c.logic]))

(let [purchases (c.db/all-purchases)
      clients (c.db/all-clients)
      cards (c.db/all-cards)]
  (println "Clients:" clients)
  (println "Cards:" cards)
  (println "Cards from client 12345678912:" (c.logic/filter-cards-of-client cards "12345678912"))
  (println "Total value by category:" (c.logic/purchases-by-category purchases))
  (println "Purchases of specific establishment"
           (c.logic/filter-purchases-of-establishment purchases "Piticas"))
  (println "Invoice:" (c.logic/total-invoice purchases 8)))