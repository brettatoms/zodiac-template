(ns {{top/ns}}.database.interface
  (:require [zodiac.ext.sql :as z.sql]))

(defn create-todo! [db title]
  (z.sql/execute-one! db {:insert-into :todo
                          :values [{:title title}]{{insert-returning}}}))

(defn delete-todo! [db id]
  (z.sql/execute! db {:delete-from :todo
                      :where [:= :id id]}))

(defn list-todos [db]
  (z.sql/execute! db {:select :* :from :todo :order-by [[:created_at :desc]]}))
