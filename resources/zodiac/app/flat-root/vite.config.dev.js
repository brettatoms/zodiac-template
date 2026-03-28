import config from "./vite.config.js"

config.mode = "development"
config.build.watch = true
config.build.minify = false
config.build.cssMinify = false

export default config
