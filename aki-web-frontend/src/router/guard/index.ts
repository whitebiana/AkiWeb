import type { LocationQueryRaw, Router } from 'vue-router';
import NProgress from 'nprogress';

// 全局权限校验
function setupPageGuard(router: Router) {
    router.beforeEach(async (to, from, next) => {
        NProgress.start();
        NProgress.done();
        next();
    })
}

export default function createRouteGuard(router: Router) {
    setupPageGuard(router);
}