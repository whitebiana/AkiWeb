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
          path: '/edit/:cid(\\d+)/',
          name: '编辑错题',
          component: () => import('@/views/edit/index.vue')
        },
        {
          path: '/',
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
