import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'basicLayout',
      component: () => import('@/layout/BasicLayout.vue'),
      children: [
        {
          path: '/',
          name: '首页',
          component: () => import('@/views/home/index.vue')
        },
        {
          path: '/search/',
          name: '搜索',
          component: () => import('@/views/search/index.vue')
        },
        {
          path: '/add/',
          name: '添加错题',
          component: () => import('@/views/add/index.vue')
        },
        {
          path: '/decks/',
          name: '错题本',
          component: () => import('@/views/deck/index.vue')
        },
        {
          path: '/study/:deckname/',
          name: '复习页面',
          component: () => import('@/views/study/index.vue')
        }
      ]
    }
  ]
})

export default router
