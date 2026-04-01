import { defineConfig } from "vite"
import tailwindcss from "@tailwindcss/vite"

export default defineConfig({
  root: "src/{{top/file}}",
  resolve: {
    alias: {
      "~": __dirname.concat("/src/{{top/file}}"),
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
        "main.ts",
        "main.css",
      ],
    },
  },
})
