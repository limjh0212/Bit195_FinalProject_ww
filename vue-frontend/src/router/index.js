import VueRouter from "vue-router";
import Vue from "vue";

Vue.use(VueRouter);

export const router = new VueRouter({
    mode  : "history",
    routes: [
        {
            path     : '/',
            component: () => import('@/view/MainPage'),
        },
        {
            path     : '/member',
            component: () => import('@/view/member/MemberPage'),
        },
        {
            path     : '/board',
            component: () => import('@/view/post/FreeBoardPage'),
        },
        {
            path     : '/post',
            component: () => import('@/view/post/FreeBoardPost'),
        },
        {
            path     : '/login',
            component: () => import('@/view/member/LoginPage'),
        },

    ]
});