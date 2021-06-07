<template>
    <div id="app">
        <!--URL확인하여 AppHeader 숨김-->
        <div v-if="isUrlCheck">
            <AppHeader></AppHeader>
        </div>
        <router-view></router-view>
    </div>
</template>

<script>
import AppHeader from "@/components/common/AppHeader";

export default {
    name      : 'App',
    components: {AppHeader},
    computed  : {
        isUrlCheck() {
            if (this.$route.path !== '/' && this.$route.path !== '/login' && this.$route.path !== '/signup') {
                return true;
            } else {
                return false;
            }
        },
    }
}
</script>

<style>
#app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 60px;
}
</style>
