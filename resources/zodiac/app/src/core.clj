(ns {{top/ns}}.{{main/ns}}.core
  (:gen-class)
  (:require [clojure.tools.logging :as log]
            [integrant.core :as ig]
            [zodiac.core :as z]
            [zodiac.ext.assets :as z.assets]
            [zodiac.ext.headers :as z.headers]
            [zodiac.ext.hot-reload :as z.hot-reload]
            [zodiac.ext.sql :as z.sql]
            [{{top/ns}}.{{main/ns}}.config :as config]
            [{{top/ns}}.{{main/ns}}.routes :as routes]))

;; --- System ---

(defmethod ig/init-key ::migrate [_ {:keys [zodiac]}]
  (let [db (::z.sql/db zodiac)]
    (z.sql/execute! db ["{{create-table-sql}}"])))

(defmethod ig/init-key ::zodiac-sql [_ options]
  (z.sql/init options))

(defmethod ig/init-key ::zodiac-assets [_ options]
  (z.assets/init options))

;; NOTE: z.headers/web includes a Content-Security-Policy of "default-src 'self'"
;; which blocks inline scripts, eval (used by Alpine.js), and external resources.
;; Uncomment and customize the CSP below when you're ready to lock things down.
;; See: https://developer.mozilla.org/en-US/docs/Web/HTTP/CSP
(defmethod ig/init-key ::zodiac-headers [_ _options]
  (z.headers/init {:headers (dissoc z.headers/web
                                    :content-security-policy)
                   ;; Example CSP for Alpine.js + htmx:
                   ;; :headers (assoc z.headers/web
                   ;;                 :content-security-policy
                   ;;                 (str "default-src 'self'; "
                   ;;                      "script-src 'self' 'unsafe-inline' 'unsafe-eval'; "
                   ;;                      "style-src 'self' 'unsafe-inline'"))
                   }))

(defmethod ig/init-key ::zodiac-hot-reload [_ options]
  (when options
    (z.hot-reload/init options)))

(defmethod ig/init-key ::zodiac [_ {:keys [extensions] :as options}]
  (let [extensions (->> extensions (filterv some?))]
    (log/info "Starting zodiac...")
    (z/start (assoc options
                    :routes #'routes/routes
                    :extensions extensions))))

(defmethod ig/halt-key! ::zodiac [_ zodiac]
  (log/info "Stopping zodiac...")
  (z/stop zodiac))

(defn start
  ([] (start :default))
  ([profile]
   (let [system-config (config/read-config "system.edn" {:profile profile})]
     (ig/load-namespaces system-config)
     (ig/init system-config))))

(defn -main [& _]
  (start)
  (.join (Thread/currentThread))
  (System/exit 0))
