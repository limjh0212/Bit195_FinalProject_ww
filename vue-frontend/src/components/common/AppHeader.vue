<template>
  <header>
    <div id="nav">
      <div class="nav-logo">
        <h1><a href="/weather" class="logo">Weather</a></h1>
      </div>
      <div class="nav-menu">
      <div class ="nav-box">
        <div class ="nav-box-default">
          <a class="logout-button" href="javascript:;" @click="logoutUser">로그아웃</a>
        </div>
      </div>
      <div v-if="isUserLogin" class ="nav-box">
        <router-link class="toolbar" to="/mypage">
          <div class ="nav-box-default">
            <div class="headerProfile"><img :src="src" class="profile"></div>
            <div><p class="nickname">{{ this.$store.state.id }}님</p></div>
          </div>
        </router-link>
      </div>
      <div class ="nav-box">
        <router-link class="toolbar" to="/post/OOTD"><div class ="nav-box-default">OOTD 작성</div></router-link>
      </div>
      <div class ="nav-box">
        <router-link class="toolbar" to="/board"><div class ="nav-box-default">커뮤니티</div></router-link>
      </div>
      <div v-if="isAdmin" class ="nav-box">
        <router-link class="toolbar" to="/admin/main"><div class ="nav-box-default">관리자 페이지</div></router-link>
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
        src : "",
      };
    },
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
        async fetchImg() {
            var base = this;
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
/*header {*/
/*    width: 100%;*/
/*    text-align: center;*/
/*    position: relative;*/
/*    height: 100px;*/
/*    border-bottom: 1px solid #35495e*/
/*}*/

/*header h1 {*/
/*    position: absolute;*/
/*    top: 0;*/
/*    left: 100px;*/
/*}*/

/*header ul.menu:after {*/
/*    display: block;*/
/*    clear: both;*/
/*    content: '';*/
/*}*/

/*header ul.menu {*/
/*    position: absolute;*/
/*    top: 20px;*/
/*    right: 50px;*/
/*}*/

/*header ul.menu li {*/
/*    float: left;*/
/*    padding: 10px 20px;*/
/*    list-style: none;*/
/*}*/

a {
    text-decoration: none;
    color: #333;
}
#nav{
  width: 100%;
  text-align: center;
  position: relative;
  height: 100px;
  border-bottom: 1px solid #35495e
}
.nav-logo{
  position: absolute;
  top: 0;
  float:left;
  width: 20%;
}
.nav-menu{
  float:right;
  width: 60%;
  height: 60px;
}
.nav-box{
  width:25%;
  float:right;
}
.nav-box-default{
  /*width: 100%;*/
  /*height: 100%;*/
  margin: 10px;
}
.nav-box div{
  display: inline-block;
}

.headerProfile {
  float:left;
  width: 40px;
  height: 40px;
  border-radius: 70%;
  overflow: hidden;
}
.profile {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
.nickname{
  float:right;
  width: auto;
  height: 40px;
  margin: 5px;
  color: #333;
}
@media(max-width: 1000px){
  #nav{
    width:100%;
  }
}

</style>