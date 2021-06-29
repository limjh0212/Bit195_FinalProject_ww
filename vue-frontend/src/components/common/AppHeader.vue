<template>
    <header>
        <div id="nav">
            <div class="nav-logo">
                <h1 style="text-decoration: none;"><a class="logo" href="/main">Weather</a></h1>
            </div>
            <div class="nav-menu">
                <div class="nav-box">
                    <router-link to="/login">
                        <div ref="javascript:;" class="nav-box-default" @click="logoutUser">로그아웃</div>
                    </router-link>
                </div>
                <div v-if="isUserLogin" class="nav-box">
                    <router-link to="/mypage/aboutme">
                        <div class="nav-box-default">
                            <div class="headerProfile"><img :src="src" class="profile"></div>
                            <div><p class="nickname">{{ this.$store.state.id }}님</p></div>
                        </div>
                    </router-link>
                </div>
                <div class="nav-box">
                    <router-link to="/board">
                        <div class="nav-box-default">커뮤니티</div>
                    </router-link>
                </div>
            </div>
        </div>
    </header>
</template>

<script>
import {getimg} from "@/api/member";

export default {
    data() {
        return {
            src: "",
        };
    },
    computed: {
        isUserLogin() {
            return this.$store.getters.isLogin;
        },
    },
    methods : {
        logoutUser() {
            this.$store.commit('clearUserData');
        },
        async fetchImg() {
            let base = this;
            await getimg(this.$store.state.img).then(function (response) {
                let base64String = btoa(String.fromCharCode.apply(null, new Uint8Array(response.data)));
                base.src = "data:image/png;base64," + base64String;
            });
        },
    },
    created() {
        this.fetchImg();
    }
}
</script>

<style scoped>
@import '../../css/cmnty/main.css';
</style>