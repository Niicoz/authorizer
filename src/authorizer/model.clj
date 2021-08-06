(ns authorizer.model
  (:use clojure.pprint)
  (:require [schema.core :as s]))
(s/set-fn-validation! true)


(s/def Purchase
  {:id s/Int
   :client-cpf s/Str
   :date     s/Str
   :value    (s/pred pos-int? 'inteiro-positivo)
   :establishment    s/Str
   :category s/Str})

(s/def Purchases [Purchase])

(s/def Total
  {:category  s/Str
   :total-value s/Int})

(s/def Total-by-category [Total])
