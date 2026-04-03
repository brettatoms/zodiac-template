(ns {{ns-prefix}}.routes
  (:require [{{ns-prefix}}.routes.todos :as todos]))

(defn routes []
  ["" {}
   ["/" {:name :todos
         :get #'todos/index-handler
         :post #'todos/create-handler
         :delete #'todos/delete-handler}]])
