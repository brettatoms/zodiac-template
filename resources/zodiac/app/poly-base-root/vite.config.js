import { defineConfig } from "vite"
import tailwindcss from "@tailwindcss/vite"

export default defineConfig({
  root: __dirname.concat("/src/{{ns-prefix-path}}"),
  resolve: {
    alias: {
      "~": __dirname.concat("/src/{{ns-prefix-path}}"),
    },
  },
  plugins: [tailwindcss()],
  build: {
    manifest: true,
    outDir: __dirname.concat("/resources/{{app-name}}/build"),
    emptyOutDir: true,
    rollupOptions: {
      input: [
        "~/main.ts",
        "~/main.css",
      ],
    },
  },
})
