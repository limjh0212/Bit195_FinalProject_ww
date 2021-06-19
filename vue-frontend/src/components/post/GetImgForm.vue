<template>
  <div>
    <img :src="src">
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
    async getImg(){
      var base = this;
      await getImg(1)
          .then(function (response) {
            let base64String = btoa(String.fromCharCode.apply(null, new Uint8Array(response.data)));
            base.src = "data:image/png;base64,"+base64String;
          });
    },

  },
  created() {
      this.getImg();
  }
};
</script>

<style scoped>

</style>