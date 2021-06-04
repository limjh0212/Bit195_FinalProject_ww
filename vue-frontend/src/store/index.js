import Vuex from 'vuex'
import Vue from "vue";
import {loginUser, registerUser} from "@/api/auth";
import {getAuthFromCookie, getUserFromCookie, saveAuthToCookie, saveUserToCookie,} from '@/utils/cookies';

Vue.use(Vuex);

export const store = new Vuex.Store({
    state    : {
        id      : getUserFromCookie() || '',
        token   : getAuthFromCookie() || '',
        nickname: '',
        role    : '',
    },
    getters  : {
        isLogin(state) {
            return state.username !== '';
        },
    },
    mutations: {
        setId(state, id) {
            state.id = id;
        },
        clearId(state) {
            state.id = '';
        },
        setToken(state, token) {
            state.token = token;
        },
        clearToken(state) {
            state.token = '';
        },
        setNickname(state, nickname) {
            state.nickname = nickname;
        },
        clearNickname(state) {
            state.nickname = '';
        },
        setRole(state, role) {
            state.role = role;
        },
        clearRole(state) {
            state.role = '';
        }
    },
    actions  : {
        //로그인
        async LOGIN({commit}, userData) {
            const {data} = await loginUser(userData);
            console.log(data)
            commit('setToken', data.token);
            commit('setId', data.id);
            commit('setNickname', data.nickname)
            commit('setRole', data.role)
            saveAuthToCookie(data);
            saveUserToCookie(userData.id);
            return data;
        },

    }
});
