<template>
    <div>
        <div>
        <span>
        <router-link class="toolbar" to="/main">Main</router-link>
        </span>
            |
            <span v-if="isAdmin"><router-link class="toolbar" to="/admin">관리자 페이지</router-link> |</span>
            <router-link class="toolbar" to="/list/freeBoard">자유게시판 조회</router-link>
            |
            <router-link class="toolbar" to="/list/OOTD">OOTD 조회</router-link>
            |
            <router-link class="toolbar" to="/list/qna">Q&A 조회</router-link>
            |
            <router-link class="toolbar" to="/post/freeBoard">자유게시판 작성</router-link>
            |
            <router-link class="toolbar" to="/post/OOTD">OOTD 작성</router-link>
            |
            <router-link class="toolbar" to="/post/qna">Q&A 작성</router-link>
            |
            <a class="logout-button" href="javascript:;" @click="logoutUser">Logout</a>
        </div>
        <div>
            <span v-if="isUserLogin">{{ this.$store.state.id }}님 환영합니다.</span>
        </div>
    </div>
</template>

<script>

export default {
    computed: {
        isUserLogin() {
            return this.$store.getters.isLogin;
        },
        isAdmin() {
            return this.$store.getters.isAdmin;
        }
    },
    methods : {
        logoutUser() {
            this.$store.commit('clearUserData');
            this.$router.push('/login');
        },
    }

}
</script>

<style scoped>

</style>