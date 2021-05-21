import VueRouter from "vue-router";
import Vue from "vue";
import MemberView from "@/view/MemberView";
import UserView from "@/view/UserView";
import UserEdit from "@/view/UserEdit";

Vue.use(VueRouter);

export const router = new VueRouter({
    mode  : "history",
    routes: [
        // {
        //     path    : '/',
        //     redirect: '/main',
        // },
        {
            path     : '/member/:id',
            name     : 'userView',
            component: UserView,
        },

        {
            path     : '/useredit/:id',
            name     : 'userEdit',
            component: UserEdit,
        },

        {
            path     : '/member',
            name     : 'member',
            component: MemberView,
        },
    ]
});