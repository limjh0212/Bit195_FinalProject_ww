<template>
  <div id="welcome" class="welcome">
    <div class="welcome-logo">
      <div class="welcome-logo-box" v-if="i<2" v-for="(item,i) in welcomeImgList">
        <img class="welcome-logo-img" :src="item">
      </div>
    </div>
    <div class="welcome-service">
      <div class="welcome-service-box" v-if="i>1" v-for="(item,i) in welcomeImgList">
        <img class="welcome-service-img" :src="item">
      </div>
    </div>
    <div class="welcome-btn">
      <div class="welcome-btn-box">
        <router-link to="/login"><div class="welcome-btn-login">LOGIN</div></router-link>
      </div>
      <div class="welcome-btn-box">
        <router-link to="/signup"><div class="welcome-btn-signup">JOIN</div></router-link>
      </div>
    </div>
  </div>
</template>

<script>
import {getWelcomeImg} from "@/api/img";

export default {
  name: "WelcomeForm",
  data(){
    return {
      welcomeImgList : [],
    }
  },
  methods: {
    async fetchImgList() {
      const {data} = await getWelcomeImg();
      console.log(data);
      let imgsrc = '';
      let imgList = [];
      for (var i = 0; i < data.length; i++) {
        imgsrc = "data:image/png;base64," + data[i];
        imgList.push(imgsrc);
      }
      this.welcomeImgList = imgList;
    },
  },
  created() {
    this.fetchImgList()
  }
}
</script>

<style scoped>
@import "../../css/common/welcome.css";
</style>