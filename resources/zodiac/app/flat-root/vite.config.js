import { defineConfig } from "vite"
import tailwindcss from "@tailwindcss/vite"

export default defineConfig({
  root: "src/{{ns-prefix-path}}",
  resolve: {
    alias: {
      "~": __dirname.concat("/src/{{ns-prefix-path}}"),
    },
  },
  plugins: [
    tailwindcss(),
  ],
  build: {
    outDir: "resources/{{app-name}}/build",
    manifest: true,
    rollupOptions: {
      input: [
        "main.ts",
        "main.css",
      ],
    },
  },
})
