(ns {{top/ns}}.app.core
  (:gen-class)
  (:require [integrant.core :as ig]
            [{{top/ns}}.app.config :as config]))

(defn start
  ([] (start :default))
  ([profile]
   (let [system-config (config/read-config "app/system.edn" {:profile profile})]
     (ig/load-namespaces system-config)
     (ig/init system-config))))

(defn -main [& _]
  (start)
  (.join (Thread/currentThread))
  (System/exit 0))
