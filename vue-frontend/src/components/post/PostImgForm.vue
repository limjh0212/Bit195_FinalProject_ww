<template>
  <div>
    <form @submit.prevent="saveimg" method="post" enctype="multipart/form-data">
      <input type="file" id="File"  name="files" multiple="multiple"></input>
      <button type="submit" id="Button">저장</button>
    </form>
  </div>
</template>

<script>
import {saveImg} from "@/api/img";

export default {
  name: "PostImgForm",
  data   : () => ({

  }),
  methods : {
    async saveimg() {
      const FileElement = document.querySelector('#File');
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
    }
  },
};
</script>

<style scoped>

</style>