(ns authorizer.challenge
  (:use clojure.pprint)
  (:require [authorizer.db :as c.db]
            [authorizer.logic :as c.logic]
            [schema.core :as s]))

(let [purchases (c.db/all-purchases)
      clients (c.db/all-clients)
      cards (c.db/all-cards)]
  (println "Clients:" clients)
  (println "Cards:" cards)
  (println "Cards from client 12345678912:" (c.logic/filter-cards-of-client cards "12345678912"))
  (println "Total value by category:" (c.logic/purchases-by-category purchases))
  (println "Purchases of specific establishment"
           (c.logic/filter-purchases-of-establishment purchases "Piticas"))
  (println "Invoice:" (c.logic/total-invoice purchases 8))
  (pprint  (c.logic/add-purchase (s/as-queue purchases) {:id 2, :client-cpf "123456" :date "14/07/2020", :value 500, :establishment "Piticas" :category "Roupas"})))