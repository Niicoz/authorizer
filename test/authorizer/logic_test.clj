(ns authorizer.logic-test
  (:use clojure.pprint)
  (:require [clojure.test :refer :all]
            [authorizer.logic :refer :all]
            [schema.core :as s]))

(deftest add-purchase-test
  (testing "Add purchase to the empty list"
    (let [purchases [], purchase {:id 2, :client-cpf "123456" :date "14/07/2020", :value 500, :establishment "Piticas" :category "Roupas"}]
      (is (= [{:id 2, :client-cpf "123456" :date "14/07/2020", :value 500, :establishment "Piticas" :category "Roupas"}]
             (add-purchase purchases purchase)))))

  (testing "Add purchase to the list"
    (let [purchases (s/as-queue [{:id 1, :client-cpf "123456" :date "14/07/2020", :value 500, :establishment "Piticas" :category "Roupas"}]),
          purchase {:id 2, :client-cpf "123456" :date "14/07/2020", :value 500, :establishment "Piticas" :category "Roupas"}]
      (is (= [{:id 1, :client-cpf "123456" :date "14/07/2020", :value 500, :establishment "Piticas" :category "Roupas"},{:id 2, :client-cpf "123456" :date "14/07/2020", :value 500, :establishment "Piticas" :category "Roupas"}]
             (add-purchase purchases purchase)))))

  (testing "Schema Error"
    (let [purchases (s/as-queue []), purchase {:client-cpf "123456" :date "14/07/2020", :value 500, :establishment "Piticas" :category "Roupas"}]
      (is (thrown? clojure.lang.ExceptionInfo
                   (add-purchase purchases purchase)))))){:id 2, :client-cpf "123456" :date "14/07/2020", :value 500, :establishment "Piticas" :category "Roupas"}

(deftest purchases-by-category-test
  (testing "testing purchases by category"
    (let [purchases [{:id 1, :client-cpf "123456" :date "14/07/2020", :value 500, :establishment "Piticas" :category "Roupas"},
                     {:id 2, :client-cpf "123456" :date "14/07/2020", :value 100, :establishment "Piticas" :category "Roupas"},
                     {:id 3, :client-cpf "123456" :date "14/07/2020", :value 100, :establishment "Riot Games" :category "Games"}]]
      (is (= [{:category "Roupas", :total-value 600}, {:category "Games", :total-value 100}]
             (purchases-by-category purchases))))))