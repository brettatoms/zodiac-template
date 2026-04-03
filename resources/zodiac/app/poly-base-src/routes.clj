(ns {{top/ns}}.{{main/ns}}.routes
  (:require [{{top/ns}}.{{main/ns}}.routes.todos :as todos]))

(defn routes []
  ["" {}
   ["/" {:name :todos
         :get #'todos/index-handler
         :post #'todos/create-handler
         :delete #'todos/delete-handler}]])
