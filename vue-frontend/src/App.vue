<template>
    <div id="app">
        <!--URL확인하여 AppHeader 숨김-->
        <div v-if="isUrlCheck">
            <AppHeader></AppHeader>
        </div>
            <router-view></router-view>
        <div v-if="isUrlCheck">
            <AppFooter></AppFooter>
        </div>
    </div>
</template>

<script>
import AppHeader from "@/components/common/AppHeader";
import AppFooter from "@/components/common/AppFooter";

export default {
    name      : 'App',
    components: {AppFooter, AppHeader},
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

</style>
