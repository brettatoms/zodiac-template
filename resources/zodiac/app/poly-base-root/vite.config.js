import { defineConfig } from "vite"
import tailwindcss from "@tailwindcss/vite"

export default defineConfig({
  root: "src/{{top/file}}/app",
  resolve: {
    alias: {
      "~": __dirname.concat("/src/{{top/file}}/app"),
    },
  },
  plugins: [
    tailwindcss(),
  ],
  build: {
    outDir: "resources/app/build",
    manifest: true,
    rollupOptions: {
      input: [
        "{{main/file}}.ts",
        "{{main/file}}.css",
      ],
    },
  },
})
