(ns {{ns-prefix}}.core
  (:gen-class)
  (:require [integrant.core :as ig]
            [{{ns-prefix}}.config :as config]))

(defn start
  ([] (start :default))
  ([profile]
   (let [system-config (config/read-config "{{app-name}}/system.edn" {:profile profile})]
     (ig/load-namespaces system-config)
     (ig/init system-config))))

(defn -main [& _]
  (start)
  (.join (Thread/currentThread))
  (System/exit 0))
