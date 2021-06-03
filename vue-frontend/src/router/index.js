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
        // Member 조회
        {
            path     : '/member',
            component: () => import('@/view/member/MemberPage'),
        },
        // FreeBoard 게시글 조회
        {
            path     : '/board',
            component: () => import('@/view/post/FreeBoardPage'),
        },
        // FreeBoard 게시글 작성
        {
            path     : '/post',
            component: () => import('@/view/post/FreeBoardPost'),
        },
        // Member 로그인
        {
            path     : '/login',
            component: () => import('@/view/member/LoginPage'),
        },
        // Member 회원가입
        {
            path     : '/signup',
            component: () => import('@/view/member/SignupPage'),
        },

    ]
});