import { createRouter, createWebHistory } from 'vue-router'
import createRouteGuard from './guard';
import NProgress from 'nprogress'; // progress bar
import 'nprogress/nprogress.css';

NProgress.configure({ showSpinner: false }); // NProgress Configuration

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'basicLayout',
      component: () => import('@/layout/BasicLayout.vue'),
      children: [
        {
          path: '/search',
          name: '搜索',
          component: () => import('@/views/search/index.vue')
        },
        {
          path: '/add',
          name: '添加错题',
          component: () => import('@/views/add/index.vue')
        },
        {
          path: '/edit/:cid(\\d+)',
          name: '编辑错题',
          component: () => import('@/views/edit/index.vue')
        },
        {
          path: '/',
          name: '错题本',
          component: () => import('@/views/deck/index.vue')
        },
        {
          path: '/study',
          name: '复习页面',
          component: () => import('@/views/study/index.vue')
        },
        {
          path: '/statistic',
          name: '数据统计',
          component: () => import('@/views/statistic/index.vue')
        },
        {
          path: '/:pathMatch(.*)*',
          alias: '/404',
          name: '404',
          component: () => import('@/views/exception/404/index.vue'),
      },
      ]
    }
  ]
})


createRouteGuard(router);

export default router
