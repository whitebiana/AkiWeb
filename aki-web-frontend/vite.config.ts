import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'

import path from 'path'

// https://vitejs.dev/config/
export default defineConfig({
  base: './',
  server: {	
    host: '0.0.0.0'
  },
  plugins: [
    
    vue(),
    AutoImport({
      // 自动导入 Vue 相关的Api，如：ref, reactive, toRef 等
      imports: [
        'vue',
        'vue-router',
      ],
      dts: true, // 生成auto-imports.d.ts声明文件
    }),
    Components({
      // dirs 指定组件所在位置，默认为 src/components
      // 可以让我们使用自己定义组件的时候免去 import 的麻烦
      dirs: ['src/components/'],
      dts: true, // 生成components.d.ts声明文件
    }),
  ],
  resolve: {
    alias: {
      '@': path.resolve(__dirname, './src'),
    }
  },
  css: {
    preprocessorOptions: {
      less: {
        modifyVars: {
          // 'arcoblue-6': '#f85959',
        },
        javascriptEnabled: true,
      }
    }
  },
})
