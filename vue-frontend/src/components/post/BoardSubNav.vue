<template>
  <div>
    <v-container>
      <div id="sub-nav">
            <div class="sub-nav-box">
              <router-link to="/list/freeBoard"><div class="sub-nav-box-default">자유게시판</div></router-link>
            </div>
            <div class="sub-nav-box">
              <router-link to="/list/OOTD"><div class="sub-nav-box-default">OOTD</div></router-link>
            </div>
            <div v-if="isUserLogin" class="sub-nav-box">
              <router-link to="/post/qna"><div class="sub-nav-box-default">문의하기</div></router-link>
            </div>
            <div v-if="isAdmin" class="sub-nav-box">
              <router-link to="/list/qna"><div class="sub-nav-box-default">Q&A</div></router-link>
            </div>
      </div>
    </v-container>
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
    }
}
</script>

<style scoped>

</style>