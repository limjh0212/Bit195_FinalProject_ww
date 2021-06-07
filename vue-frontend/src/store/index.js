import Vuex from 'vuex'
import Vue from "vue";
import createPersistedState from 'vuex-persistedstate';
import {loginUser} from "@/api/auth";

Vue.use(Vuex);

export const store = new Vuex.Store({
    plugins  : [createPersistedState()],
    state    : {
        id      : '',
        token   : '',
        nickname: '',
        role    : '',
    },
    getters  : {
        isLogin(state) {
            return state.username !== '';
        },
    },
    mutations: {
        setUserData(state, userData) {
            state.id = userData.id;
            state.token = userData.token;
            state.nickname = userData.nickname;
            state.role = userData.role;
        },
        clearUserData(state) {
            state.id = '';
            state.token = '';
            state.nickname = '';
            state.role = '';
        },
    },
    actions  : {
        //로그인
        async LOGIN({commit}, userData) {
            const {data} = await loginUser(userData);
            commit('setUserData', data);
            return data;

        },


    },

});