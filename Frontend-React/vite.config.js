import path from "path";
import react from "@vitejs/plugin-react";
import { defineConfig } from "vite";

export default defineConfig({
  server: {
    proxy: {
      '/api': {
        target: 'https://treading-platform.onrender.com',
        changeOrigin: true,  // Needed for cross-origin requests
        secure: false,       // Allow requests to an HTTPS target without HTTPS on the dev server
        rewrite: (path) => path.replace(/^\/api/, ''),  // Rewrite the path if necessary
      }
    }
  },
  plugins: [react()],
  resolve: {
    alias: {
      "@": path.resolve(__dirname, "./src"),
    },
  },
});
