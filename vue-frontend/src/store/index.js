import Vuex from 'vuex'
import Vue from "vue";
import {fetchMemberList, fetchUser, fetchBoardList} from "@/api";

Vue.use(Vuex);

export const store = new Vuex.Store({
    state    : {
        memberList: [],
        userList  : [],
        boardList : []
    },
    mutations: {
        SET_MEMBER(state, memberList) {
            state.memberList = memberList;
        },
        SET_USER(state, userList) {
            state.userList = userList;
        },
        SET_BOARD(state, boardList) {
            state.boardList = boardList;
        },
    },
    actions  : {
        //회원목록 조회
        FETCH_MEMBER({commit}) {
            fetchMemberList()
                .then(({data}) => commit('SET_MEMBER', data))
                .catch(function (error) {
                    console.log(error)
                });
        },
        FETCH_USER({commit}, userId) {
            fetchUser(userId)
                .then(({data}) => commit('SET_USER', data))
                .then(({data}) => console.log(data))
                .catch(function (error) {
                    console.log(error)
                });
        },
        //게시글 조회
        FETCH_BOARD({commit}) {
            fetchBoardList()
                .then(({data}) => commit('SET_BOARD', data))
                .catch(function (error) {
                    console.log(error)
                });
        },
    }
});