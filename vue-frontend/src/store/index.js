import Vuex from 'vuex'
import Vue from "vue";
import createPersistedState from 'vuex-persistedstate';
import Cookies from 'js-cookie';
import {loginUser} from "@/api/auth";

Vue.use(Vuex);

export const store = new Vuex.Store({
    plugins  : [createPersistedState({
        storage : {
            getItem: key => Cookies.get(key),
            setItem: (key, value) => Cookies.set(key, value, { expires: 1, secure: true }),
            removeItem: key => Cookies.remove(key)
        }
    })],
    state    : {
        id      : '',
        token   : '',
        nickname: '',
        role    : '',
    },
    getters  : {
        isLogin(state) {
            return state.id !== '';
        },
        isAdmin(state) {
            return state.role === 'ROLE_ADMIN';
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
        //로그인 정보 저장
        async LOGIN({commit}, userData) {
            const {data} = await loginUser(userData);
            commit('setUserData', data);
            return data;

        },


    },

});
