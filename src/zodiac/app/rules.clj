(ns zodiac.app.rules)

(defn flat
  "Transform rules for flat (standard src/) project structure."
  [_data]
  [["root" ""]
   ["flat-root" ""]
   ["src" "src/{{top/file}}/{{main/file}}"
    {"core.clj" "core.clj"
     "main.css" "main.css"
     "main.ts" "main.ts"
     "routes.clj" "routes.clj"
     "config.clj" "config.clj"}]
   ["src-routes" "src/{{top/file}}/{{main/file}}/routes"
    {"todos.clj" "todos.clj"}]
   ["dev" "dev"]
   ["test" "test/{{top/file}}/{{main/file}}"
    {"core_test.clj" "core_test.clj"}]])

(defn polylith
  "Transform rules for polylith project structure."
  [_data]
  [["root" ""]
   ["poly-root" ""]
   ["poly-base-src" "bases/{{main}}/src/{{top/file}}/{{main/file}}"
    {"core.clj" "core.clj"
     "server.clj" "server.clj"
     "routes.clj" "routes.clj"
     "config.clj" "config.clj"}]
   ["poly-base-resources" "bases/{{main}}/resources/{{main}}"]
   ["poly-base-root" "bases/{{main}}"]
   ["poly-component-db-src" "components/database/src/{{top/file}}/database"
    {"interface.clj" "interface.clj"}]
   ["poly-routes" "bases/{{main}}/src/{{top/file}}/{{main/file}}/routes"
    {"todos.clj" "todos.clj"}]
   ["poly-component-db-root" "components/database"]
   ["poly-project" "projects/{{main}}"]
   ["poly-dev" "development/src"]
   ["src" "bases/{{main}}/src/{{top/file}}/{{main/file}}"
    {"main.css" "main.css"
     "main.ts" "main.ts"}
    :only]
   ["poly-test" "bases/{{main}}/test/{{top/file}}/{{main/file}}"
    {"server_test.clj" "server_test.clj"}]])
