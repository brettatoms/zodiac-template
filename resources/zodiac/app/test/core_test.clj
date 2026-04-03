(ns {{ns-prefix}}.core-test
  (:require [clojure.test :refer [deftest is testing use-fixtures]]
            [integrant.core :as ig]
            [zodiac.core :as z]
            [zodiac.ext.sql :as z.sql]
            [{{ns-prefix}}.routes :as routes]
            [{{ns-prefix}}.routes.todos :as todo]))

;; --- Test System ---

(def ^:dynamic *db* nil)
(def ^:dynamic *app* nil)
{{test-jdbc-url-def}}

(defn test-system-config [jdbc-url]
  {:{{ns-prefix}}.core/zodiac-sql
   {:spec {:jdbcUrl jdbc-url}
    :context-key :db}

   :{{ns-prefix}}.core/zodiac
   {:extensions [(ig/ref :{{ns-prefix}}.core/zodiac-sql)]
    :routes #'routes/routes
    :start-server? false}

   :{{ns-prefix}}.core/migrate
   {:zodiac (ig/ref :{{ns-prefix}}.core/zodiac)}})

(defn system-refs [system]
  [(-> system :{{ns-prefix}}.core/zodiac ::z.sql/db)
   (-> system :{{ns-prefix}}.core/zodiac ::z/app)])

{{test-fixture}}

(use-fixtures :once system-fixture)

{{test-body}}
