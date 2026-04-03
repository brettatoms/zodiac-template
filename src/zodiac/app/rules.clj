(ns zodiac.app.rules)

(defn flat
  "Transform rules for flat (standard src/) project structure."
  [_data]
  [["root" ""]
   ["flat-root" ""]
   ["src" "src/{{ns-prefix-path}}"
    {"core.clj" "core.clj"
     "main.css" "main.css"
     "main.ts" "main.ts"
     "routes.clj" "routes.clj"
     "config.clj" "config.clj"}]
   ["src-routes" "src/{{ns-prefix-path}}/routes"
    {"todos.clj" "todos.clj"}]
   ["dev" "dev"]
   ["test" "test/{{ns-prefix-path}}"
    {"core_test.clj" "core_test.clj"}]])

(defn polylith
  "Transform rules for polylith project structure."
  [_data]
  [["root" ""]
   ["poly-root" ""]
   ["poly-base-src" "bases/{{app-name}}/src/{{ns-prefix-path}}"
    {"core.clj" "core.clj"
     "server.clj" "server.clj"
     "routes.clj" "routes.clj"
     "config.clj" "config.clj"}]
   ["poly-base-resources" "bases/{{app-name}}/resources/{{app-name}}"]
   ["poly-base-root" "bases/{{app-name}}"]
   ["poly-component-db-src" "components/database/src/{{top/file}}/database"
    {"interface.clj" "interface.clj"}]
   ["poly-routes" "bases/{{app-name}}/src/{{ns-prefix-path}}/routes"
    {"todos.clj" "todos.clj"}]
   ["poly-component-db-root" "components/database"]
   ["poly-project" "projects/{{app-name}}"]
   ["poly-dev" "development/src"]
   ["src" "bases/{{app-name}}/src/{{ns-prefix-path}}"
    {"main.css" "main.css"
     "main.ts" "main.ts"}
    :only]
   ["poly-test" "bases/{{app-name}}/test/{{ns-prefix-path}}"
    {"server_test.clj" "server_test.clj"}]])
