(ns authorizer.logic
  (:require [schema.core :as s]
            [authorizer.model :as a.model]))
(use 'java-time)

(defn card-from-client?
  [client-cpf card]
  (= (:client-cpf card) client-cpf))

(defn filter-cards-of-client
  [cards client-cpf]
  (->> cards
       (filter (partial card-from-client? client-cpf))))

(defn total-purchases
  [purchases]
  (->> purchases
       (map :value)
       (reduce +)))

(defn total-by-category
  [[category purchases]]
  {:category   category
   :total-value (total-purchases purchases)})

(defn purchases-from-establishment
  [establishment purchase]
  (= (:establishment purchase) establishment))

(defn filter-purchases-of-establishment
  [purchases establishment]
  (->> purchases
       (filter (partial purchases-from-establishment establishment))))

(defn same-month?
  [month purchase]
  (= (as (local-date-time "yyyy/MM/dd HH:mm" (:date purchase)) :month-of-year) month))

(defn total-invoice
  [purchases month]
  (->> purchases
       (filter (partial same-month? month))
       (map :value)
       (reduce +)))

(s/defn purchases-by-category :- a.model/Total-by-category
  [purchases :- a.model/Purchases]
  (->> purchases
       (group-by :category)
       (map total-by-category)))

(s/defn add-purchase :- a.model/Purchases
        [purchases :- a.model/Purchases, purchase :- a.model/Purchase]
        (conj purchases purchase))