import Vuex from 'vuex'
import Vue from "vue";
import {fetchMemberList, fetchUser} from "@/api";

Vue.use(Vuex);

export const store = new Vuex.Store({
    state    : {
        member: [],
        user  : {}
    },
    mutations: {
        SET_MEMBER(state, member) {
            state.member = member;
        },
        SET_USER(state, user) {
            state.user = user;
        },
    },
    actions  : {
        FETCH_MEMBER({commit}) {
            fetchMemberList()
                .then(({data}) => commit('SET_MEMBER', data))
                .catch(function (error) {
                    console.log(error)
                });
        },
        SET_USER({commit}, id) {
            fetchUser(id)
                .then(({data}) => commit('SET_USER', data))
                .catch(function (error) {
                    console.log(error)
                });
        },
    },
})