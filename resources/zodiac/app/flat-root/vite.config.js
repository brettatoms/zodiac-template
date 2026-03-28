import { defineConfig } from "vite"
import tailwindcss from "@tailwindcss/vite"

export default defineConfig({
  root: "src/{{top/file}}/{{main/file}}",
  resolve: {
    alias: {
      "~": __dirname.concat("/src/{{top/file}}/{{main/file}}"),
    },
  },
  plugins: [
    tailwindcss(),
  ],
  build: {
    outDir: "resources/{{main}}/build",
    manifest: true,
    rollupOptions: {
      input: [
        "{{main/file}}.ts",
        "{{main/file}}.css",
      ],
    },
  },
})
