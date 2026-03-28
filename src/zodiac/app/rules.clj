(ns zodiac.app.rules)

(defn flat
  "Transform rules for flat (standard src/) project structure."
  [_data]
  [["root" ""]
   ["flat-root" ""]
   ["src" "src/{{top/file}}"
    {"main.clj" "{{main/file}}.clj"
     "main.css" "{{main/file}}.css"
     "main.ts" "{{main/file}}.ts"
     "routes.clj" "{{main/file}}/routes.clj"
     "config.clj" "{{main/file}}/config.clj"}]
   ["src-routes" "src/{{top/file}}/{{main/file}}/routes"
    {"todos.clj" "todos.clj"}]
   ["dev" "dev"]
   ["test" "test/{{top/file}}"
    {"main_test.clj" "{{main/file}}_test.clj"}]])

(defn polylith
  "Transform rules for polylith project structure."
  [_data]
  [["root" ""]
   ["poly-root" ""]
   ["poly-base-src" "bases/app/src/{{top/file}}/app"
    {"core.clj" "core.clj"
     "server.clj" "server.clj"
     "routes.clj" "routes.clj"
     "config.clj" "config.clj"}]
   ["poly-base-resources" "bases/app/resources/app"]
   ["poly-base-root" "bases/app"]
   ["poly-component-db-src" "components/database/src/{{top/file}}/database"
    {"interface.clj" "interface.clj"}]
   ["poly-routes" "bases/app/src/{{top/file}}/app/routes"
    {"todos.clj" "todos.clj"}]
   ["poly-component-db-root" "components/database"]
   ["poly-project" "projects/app"]
   ["poly-dev" "development/src"]
   ["src" "bases/app/src/{{top/file}}/app"
    {"main.css" "{{main/file}}.css"
     "main.ts" "{{main/file}}.ts"}
    :only]
   ["poly-test" "bases/app/test/{{top/file}}/app"
    {"server_test.clj" "server_test.clj"}]])
