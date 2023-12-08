import { fileURLToPath, URL } from 'node:url'

import { defineConfig, loadEnv } from 'vite'
import vue from '@vitejs/plugin-vue'
import vueJsx from '@vitejs/plugin-vue-jsx'
import nightwatchPlugin from 'vite-plugin-nightwatch'

// https://vitejs.dev/config/
// export default defineConfig({
//   plugins: [
//     vue(),
//     vueJsx(),
//     nightwatchPlugin(),
//   ],
//   resolve: {
//     alias: {
//       '@': fileURLToPath(new URL('./src', import.meta.url))
//     }
//   }
// })

export default defineConfig(({command}) => {
  if (command === 'serve') {
    const {VITE_APP_TITLE, VITE_BASE_URL, VITE_OUTPUT_DIR} = loadEnv('development', process.cwd())
    return {
      plugins: [
        vue(),
        vueJsx(),
        nightwatchPlugin(),
      ],
      resolve: {
        alias: {
          '@': fileURLToPath(new URL('./src', import.meta.url))
        }
      },
      base: VITE_BASE_URL,
      root: process.cwd(),
    }
  } else {
    return {
      plugins: [
        vue(),
        vueJsx(),
        nightwatchPlugin(),
      ],
      resolve: {
        alias: {
          '@': fileURLToPath(new URL('./src', import.meta.url))
        }
      },
      build: {
        sourcemap: true,
        rollupOptions: {
          input: {
            main: fileURLToPath(new URL('./index.html', import.meta.url)),
            about: fileURLToPath(new URL('./about.html', import.meta.url)),
          }
        }
      }
    }
  }
})
