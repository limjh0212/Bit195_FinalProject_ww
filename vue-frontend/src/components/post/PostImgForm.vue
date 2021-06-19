<template>
  <div>
      <div>
        <form @submit.prevent="saveimg" method="post" enctype="multipart/form-data">
          <input type="file" id="File"  name="files" multiple="multiple" @change="onFileSelected"></input>
          <button type="submit" id="Button">저장</button>
        </form>
      </div>
      <div>
        <img ref="uploadImg" :src="this.imgsrc">
      </div>
  </div>
</template>

<script>
import {saveImg} from "@/api/img";
let FileElement = document.querySelector('#File');
export default {
  name: "PostImgForm",
  data   : () => ({
      imgsrc : "",
  }),
  methods : {
    async saveimg() {
      const formData = new FormData();
      // formData.append("boardname", "imgtest")
      // formData.append("uid", this.$store.state.id);
      // formData.append("title", "title");
      // formData.append("content", "content");
      for (let i = 0; i < FileElement.files.length; i++) {
        formData.append("images", FileElement.files[i]);
      }
      const {data} = await saveImg(formData);
      console.log(data)
    },
    onFileSelected: function (event) {
      FileElement = event.target;
      if(FileElement.files[0]) {
        let img = this.$refs.uploadImg;
        img.src = URL.createObjectURL(FileElement.files[0]);
        this.imgsrc = img.src;
        img.onload = () => {
          URL.revokeObjectURL(this.src)
        }
      }
    },
  },
};
</script>

<style scoped>

</style>