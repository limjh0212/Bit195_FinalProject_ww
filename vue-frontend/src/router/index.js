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
            //게시글 작성
            path     : '/post/:boardname',
            component: () => import('@/view/post/WritePage'),
        },
        {
            //게시글 조회
            path     : '/board/:boardname',
            component: () => import('@/view/post/ListPage'),

        },

        // },
        // // Member 조회
        // {
        //     path     : '/member',
        //     component: () => import('@/view/member/MemberPage'),
        // },
        // // FreeBoard 게시글 조회
        // {
        //     path     : '/board',
        //     component: () => import('@/view/post/FreeBoardPage'),
        // },
        // // FreeBoard 게시글 작성
        // {
        //     path     : '/post',
        //     component: () => import('@/view/post/FreeBoardPost'),
        // },
        // // Member 로그인
        // {
        //     path     : '/login',
        //     component: () => import('@/view/member/LoginPage'),
        // },
        // // Member 회원가입
        // {
        //     path     : '/signup',
        //     component: () => import('@/view/member/SignupPage'),
    ]
});