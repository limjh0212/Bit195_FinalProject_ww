<template>
    <header>
        <h1><a href="/weather" class="logo">Weather</a></h1>
        <div class="menuWrap">
            <ul class="menu">
                <!--<span v-if="isUserLogin">{{ this.$store.state.id }}님 환영합니다.</span>-->
                <li v-if="isAdmin">
                    <router-link class="toolbar" to="/admin/main">관리자 페이지</router-link>
                </li>
                <li>
                    <router-link class="toolbar" to="/board">커뮤니티</router-link>
                </li>
                <li>
                    <router-link class="toolbar" to="/post/OOTD">OOTD 작성</router-link>
                </li>
                <li>
                    <router-link class="toolbar" to="/mypage">My Page</router-link>
                </li>
                <li><a class="logout-button" href="javascript:;" @click="logoutUser">Logout</a></li>
            </ul>
        </div>

    </header>
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
header {
    width: 100%;
    text-align: center;
    position: relative;
    height: 100px;
    border-bottom: 1px solid #35495e
}

header h1 {
    position: absolute;
    top: 0;
    left: 100px;
}

header ul.menu:after {
    display: block;
    clear: both;
    content: '';
}

header ul.menu {
    position: absolute;
    top: 20px;
    right: 50px;
}

header ul.menu li {
    float: left;
    padding: 10px 20px;
    list-style: none;
}

a {
    text-decoration: none;
    color: #333;
}
</style>