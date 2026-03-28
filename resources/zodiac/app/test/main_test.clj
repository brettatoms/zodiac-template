(ns {{top/ns}}.{{main/ns}}-test
  (:require [clojure.test :refer [deftest is testing use-fixtures]]
            [integrant.core :as ig]
            [zodiac.core :as z]
            [zodiac.ext.sql :as z.sql]
            [{{top/ns}}.{{main/ns}}.routes :as routes]
            [{{top/ns}}.{{main/ns}}.routes.todos :as todo]))

;; --- Test System ---

(def ^:dynamic *db* nil)
(def ^:dynamic *app* nil)
{{test-jdbc-url-def}}

(defn test-system-config [jdbc-url]
  {:{{top/ns}}.{{main/ns}}/zodiac-sql
   {:spec {:jdbcUrl jdbc-url}
    :context-key :db}

   :{{top/ns}}.{{main/ns}}/zodiac
   {:extensions [(ig/ref :{{top/ns}}.{{main/ns}}/zodiac-sql)]
    :routes #'routes/routes
    :start-server? false}

   :{{top/ns}}.{{main/ns}}/migrate
   {:zodiac (ig/ref :{{top/ns}}.{{main/ns}}/zodiac)}})

(defn system-refs [system]
  [(-> system :{{top/ns}}.{{main/ns}}/zodiac ::z.sql/db)
   (-> system :{{top/ns}}.{{main/ns}}/zodiac ::z/app)])

{{test-fixture}}

(use-fixtures :once system-fixture)

{{test-body}}
