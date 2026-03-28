(ns {{top/ns}}.app.routes
  (:require [{{top/ns}}.app.routes.todos :as todos]))

(defn routes []
  ["" {}
   ["/" {:name :todos
         :get #'todos/index-handler
         :post #'todos/create-handler
         :delete #'todos/delete-handler}]])
