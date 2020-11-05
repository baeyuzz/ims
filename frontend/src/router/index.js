// Imports
import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

const router = new Router({
  mode: "history",
  base: process.env.BASE_URL,
  scrollBehavior: (to, from, savedPosition) => {
    if (to.hash) return { selector: to.hash };
    if (savedPosition) return savedPosition;

    return { x: 0, y: 0 };
  },
  routes: [
    {
      path: "/",
      component: () => import("@/layouts/home/Index.vue"),
      children: [
        {
          path: "",
          name: "Home",
          component: () => import("@/views/home/Index.vue")
        },
        {
          path: 'ai-analysis',
          name: 'AI-Analysis',
          component: () => import('@/views/ai-analysis/input.vue'),

        },
        {
          path: 'ai-result',
          name: 'AI-Result',
          component: () => import('@/views/ai-analysis/output.vue'),
          props: true
        },
        {
          path: "mypage",
          name: "Mypage",
          component: () => import("@/views/Mypage.vue")
        },
        {
          path: "myEssay",
          name: "MyEssay",
          component: () => import("@/views/MyEssay.vue")
        },
        {
          path: "*",
          name: "FourOhFour",
          component: () => import("@/views/404/Index.vue")
        },
      ]
    }
  ]
});

export default router;
