import VueRouter from "vue-router";
import Vue from "vue";

Vue.use(VueRouter);

export const router = new VueRouter({
    mode  : "history",
    routes: [
        {
            //WelcomePage
            path     : '/',
            component: () => import('@/view/WelcomeView'),
        },
        {
            //로그인
            path     : '/login',
            component: () => import('@/view/member/LoginPage'),
        },
        {
            //회원 가입
            path     : '/signup',
            component: () => import('@/view/member/SignupPage'),
        },

        {
            //회원 조회
            path     : '/member',
            component: () => import('@/view/member/MemberList'),
        },
        {
            //회원 상세조회
            path     : '/member/:id',
            component: () => import('@/view/member/MemberList'),
        },


        {
            //게시글 작성
            path     : '/post/:boardname',
            component: () => import('@/view/post/WritePage'),
        },
        {
            //게시글 목록조회
            path     : '/list/:boardname',
            component: () => import('@/view/post/ListPage'),
        },
        {
            //게시글 상세조회
            path     : '/post/:boardname/:postnum',
            component: () => import('@/view/post/PostPage'),
        },
        {
            //MainPage
            path     : '/main',
            component: () => import('@/view/MainPage'),
        },
    ]
});