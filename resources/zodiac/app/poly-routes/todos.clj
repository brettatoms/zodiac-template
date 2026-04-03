(ns {{ns-prefix}}.routes.todos
  (:require [ring.middleware.anti-forgery :refer [*anti-forgery-token*]]
            [charred.api :as json]
            [zodiac.core :as z]
            [zodiac.ext.assets :as z.assets]
            [zodiac.ext.sql :as z.sql]
            [{{top/ns}}.database.interface :as db]))

;; --- Views ---

(defn layout [{:keys [::z.assets/assets]} & body]
  [:html
   [:head
    [:meta {:charset "utf-8"}]
    [:meta {:name "viewport"
            :content "width=device-width, initial-scale=1"}]
    [:title "{{display-name}}"]
    [:style "[x-cloak] {display: none !important;}"]
    [:link {:rel "stylesheet"
            :href (assets "main.css")}]]
   [:body {:hx-ext "alpine-morph"
           :hx-swap "morph"
           :x-data ""
           :x-cloak true}
    [:div {:class "max-w-2xl mx-auto mt-20 px-4"}
     body]
    [:script {:src (assets "main.ts")
              :type "module"}]]])

(defn todo-form []
  [:form {:hx-post (z/url-for :todos)
          :hx-target "#todo-list"
          :x-on:htmx:after-request.camel "$event.detail.elt.reset()"
          :class "flex flex-row mb-6"}
   [:input {:type "hidden"
            :name "__anti-forgery-token"
            :value (force *anti-forgery-token*)}]
   [:input {:type "text"
            :name "title"
            :placeholder "What needs to be done?"
            :required true
            :class "flex-1 mr-4"}]
   [:button {:type "submit"
             :class "hover:text-gray-700 border border-black hover:border-gray-600 px-4 py-2"}
    "Add"]])

(defn todo-list [ctx]
  (let [{:keys [::z.sql/db]} ctx]
    [:ul {:id "todo-list"
          :class "divide-y divide-gray-200"}
     (for [item (db/list-todos db)]
       [:li {:class "flex flex-row items-center py-3"}
        [:span {:class "mr-8 flex-1"}
         (:todo/title item)]
        [:button {:hx-delete (z/url-for :todos)
                  :hx-headers (json/write-json-str {:X-CSRF-Token (force *anti-forgery-token*)})
                  :hx-target "#todo-list"
                  :hx-confirm "Are you sure?"
                  :class "text-red-500 hover:text-red-700"
                  :name "id"
                  :value (:todo/id item)}
         "Delete"]])]))

;; --- Handlers ---

(defn index-handler [{:keys [::z/context]}]
  (let [{:keys [::z.assets/assets]} context]
    (layout {:assets assets}
            [:h1 {:class "text-2xl font-bold mb-6"} "{{display-name}}"]
            (todo-form)
            (todo-list context))))

(defn create-handler [{:keys [::z/context form-params]}]
  (let [{:keys [::z.sql/db]} context
        title (get form-params "title")]
    (db/create-todo! db title)
    (todo-list context)))

(defn delete-handler [{:keys [::z/context form-params]}]
  (let [{:keys [::z.sql/db]} context
        id (-> form-params (get "id") parse-long)]
    (db/delete-todo! db id)
    (todo-list context)))
