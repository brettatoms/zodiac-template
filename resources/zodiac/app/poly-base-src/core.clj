(ns {{top/ns}}.{{main/ns}}.core
  (:gen-class)
  (:require [integrant.core :as ig]
            [{{top/ns}}.{{main/ns}}.config :as config]))

(defn start
  ([] (start :default))
  ([profile]
   (let [system-config (config/read-config "{{main}}/system.edn" {:profile profile})]

     (ig/load-namespaces system-config)
     (ig/init system-config))))

(defn -main [& _]
  (start)
  (.join (Thread/currentThread))
  (System/exit 0))
