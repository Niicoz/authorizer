(ns authorizer.db)

(def client {:name "Marcos Wilson"
             :cpf "12345678912"
             :email "Marcos@teste.com"})

(def card {:client-cpf "12345678912"
           :number "123456789"
           :cvv "123"
           :validate "2021/01/23"
           :limit 5000})

(def purchase1 {:client-cpf "12345678912"
                :date "2021/01/23 11:33"
                :value 100
                :establishment "Burguer king"
                :category "Fast Food"})

(def purchase2 {:client-cpf "12345678912"
                :date "2021/07/23 11:33"
                :value 100
                :establishment "Burguer king"
                :category "Fast Food"})

(def purchase3 {:client-cpf "12345678912"
                :date "2021/06/23 11:33"
                :value 250
                :establishment "Girafas"
                :category "Fast Food"})

(def purchase4 {:client-cpf "12345678912"
                :date "2021/10/23 11:33"
                :value 300
                :establishment "Riot"
                :category "Games"})

(def purchase5 {:client-cpf "12345678912"
                :date "2021/08/23 11:33"
                :value 133
                :establishment "Piticas"
                :category "Roupas"})

(defn all-purchases []
  [purchase1, purchase2, purchase3, purchase4, purchase5])

(defn all-clients []
  [client])

(defn all-cards []
  [card])