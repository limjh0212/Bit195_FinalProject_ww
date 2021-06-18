<template>
  <div>
     <p><v-img :src="this.src"></v-img></p>
  </div>
</template>

<script>
import {getImg} from "@/api/img";

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
        var bytes, blob;
        bytes = new Uint8Array(getImg(num));
        console.log(bytes);
        blob = new Blob([bytes],{type:'image/bmp'});
        const imgsrc = URL.createObjectURL(blob);
        console.log(imgsrc);
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