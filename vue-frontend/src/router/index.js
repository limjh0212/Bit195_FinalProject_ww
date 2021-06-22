import VueRouter from "vue-router";
import Vue from "vue";
import {store} from '@/store';
import WeatherPage from "@/view/weather/WeatherPage";

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
                //MainPage
                path     : '/weather',
                component: WeatherPage
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
                component: () => import('@/view/admin/MemberList'),
                meta     : {auth: true}
            },
            {
                //회원 상세조회
                path     : '/member/:id',
                component: () => import('@/view/admin/MemberList'),
                meta     : {auth: true}
            },

            {
                //이미지 게시글 작성
                path     : '/postimg',
                component: () => import('@/view/post/ImgPage'),
                meta     : {auth: true}

            },
            {
                //이미지 게시글 출력
                path     : '/getimg',
                component: () => import('@/view/post/GetImgPage'),
                meta     : {auth: true}

            },
            {
                //관리자 페이지
                path     : '/admin/main',
                component: () => import('@/view/admin/AdminPage'),
                meta     : {admin: true},
                children : [
                    {
                        path     : '/admin/main',
                        component: () => import('@/view/admin/AdminMain')
                    },
                    {
                        path     : '/admin/cmnty',
                        component: () => import('@/view/admin/CmntyPage')
                    },
                    {
                        path     : '/admin/member',
                        component: () => import('@/view/admin/MemberPage')
                    },
                    {
                        path     : '/admin/qna',
                        component: () => import('@/view/admin/QnaPage')
                    },
                    {
                        path     : '/admin/memberlist',
                        component: () => import('@/view/admin/MemberList')
                    }
                ]
            },
            {
                //커뮤니티
                path     : '/board',
                component: () => import('@/view/post/BoardMain'),
                meta     : {auth: true},
                children : [
                    {
                        //Main
                        path     : '/board',
                        component: () => import('@/view/post/PostMain'),

                    },
                    {
                        //게시글 작성
                        path     : '/post/:boardname',
                        component: () => import('@/view/post/WritePage'),

                    },
                    {
                        //게시글 목록조회
                        path     : '/list/:boardname',
                        component: () => import('@/view/post/Board/BoardView'),
                    },
                    {
                        //게시글 상세조회
                        path     : '/post/:boardname/:postnum',
                        component: () => import('@/view/post/Board/PostPage'),
                    },
                ]
            },
            {
                //My Page
                path     : '/mypage',
                component: () => import('@/view/mypage/MyPage'),
                meta     : {auth: true},
                children : [
                    {
                        //Main
                        path     : '/mypage/main',
                        component: () => import('@/view/mypage/MyPageMain')
                    },
                    {
                        //내 정보 조회
                        path     : '/mypage/aboutme',
                        component: () => import('@/view/mypage/AboutMePage'),
                    },
                    {
                        //위치 등록
                        path     : '/mypage/location',
                        component: () => import('@/view/mypage/UserLocation'),
                    },

                ]
            },

        ]
    })
;

//로그인 검증
router.beforeEach((to, form, next) => {
    if (to.meta.auth && !store.getters.isLogin) {
        console.log('인증이 필요합니다')
        next('/login');
        return;
    }
    next();
})

//관리자 권한 검증
router.beforeEach((to, form, next) => {
    if (to.meta.admin && !store.getters.isAdmin) {
        console.log('관리자 권한이 필요합니다')
        next('/');
        return;
    }
    next();
})
export default router;