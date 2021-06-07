<template>
    <div>
        <div>
        <span>
        <router-link class="toolbar" to="/main">Main</router-link>
        </span>
            |
            <span v-if="isAdmin">
            <router-link class="toolbar" to="/member">회원 조회</router-link> | </span>
            <router-link class="toolbar" to="/board">게시글 조회</router-link>
            |
            <router-link class="toolbar" to="/post">게시글 작성</router-link>
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
            return this.$store.state.role === "ROLE_ADMIN";
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