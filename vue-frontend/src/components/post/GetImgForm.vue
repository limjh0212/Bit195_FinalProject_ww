<template>
  <div>
<!--    <p><v-img :src="this.src"></v-img></p>-->
  </div>
</template>

<script>
import {getImg, byteToSrc} from "@/api/img";

export default {
  name: "GetImgForm",
  data() {
    return{
        src : "",
    }
  },
  methods : {
    async fetchData(){
        const num = 1;
        const byteArray = getImg(num);
        const imgsrc = byteToSrc(byteArray);
        this.src = imgsrc;
    }
  },
  created() {
    this.fetchData();
  }
};
</script>

<style scoped>

</style>