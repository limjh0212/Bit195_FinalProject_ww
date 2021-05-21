import VueRouter from "vue-router";
import Vue from "vue";
import MemberView from "@/view/MemberView";
import UserView from "@/view/UserView";

Vue.use(VueRouter);

export const router = new VueRouter({
    mode  : "history",
    routes: [
        // {
        //     path    : '/',
        //     redirect: '/main',
        // },
        {
            path     : '/user/:id',
            name     : 'user',
            component: UserView,
        },
        {
            path     : '/member',
            name     : 'member',
            component: MemberView,
        },
    ]
});