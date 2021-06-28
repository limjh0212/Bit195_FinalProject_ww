<template>
    <div id="app">
        <!--URL확인하여 AppHeader 숨김-->
        <div v-if="isUrlCheck">
            <AppHeader></AppHeader>
        </div>
        <div class="router-view">
            <router-view></router-view>
        </div>
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
@import '../src/css/common/common.css';
/* 반응형 최대크기 */
#app {
  margin:0 auto;
  width:1200px;
  min-height:100%;
  padding-top:71px;
  border-left:1px #D8D8D8 solid;
  border-right:1px #D8D8D8 solid;
  positon:relative;
  text-align:center;
}
/* 여백제거 + 드래그방지 */
body {
  padding:0;
  margin:0;
  -ms-user-select: none;
  -moz-user-select: none;
  -khtml-user-select: none;
  -webkit-user-select: none;
  user-select: none;
}
/* 반응형 시작 */
@media(max-width:1200px){
  #app{
    width:100%;
    border-left:0;
    border-right:0;
  }
}
</style>
