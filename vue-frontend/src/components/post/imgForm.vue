<template>
  <div>
<!--    <table>-->
<!--    <form @submit.prevent="getData" method="post" enctype="multipart/form-data">-->
<!--      <tr><td>제목</td><td><input type="text" id="Title" ></input></td></tr>-->
<!--      <tr><td>내용</td><td><input type="text" id="Content" ></input></td></tr>-->
<!--      <tr><td>파일</td><td><input type="file" id="File"  name="files" multiple="multiple"></input></td></tr>-->
<!--      <tr><td colspan="2"><button type="submit" id="Button">저장</button></td></tr>-->
<!--    </form>-->
<!--    </table>-->
    <form @submit.prevent="saveimg" method="post" enctype="multipart/form-data">
      <input type="file" id="File"  name="files" multiple="multiple"></input>
      <button type="submit" id="Button">저장</button>
    </form>
  </div>
</template>

<script>
import {saveImg} from "@/api/post";
// const ButtonElement = document.querySelector('#Button');
// const FileElement = document.querySelector('#File');
export default {
  name: "imgForm",
  data : () => ({
    title : "",
    content : "",
  }),
  methods : {
    async saveimg() {
      const FileElement = document.querySelector('#File');
      let temp = [];
      for(let i = 0; i< FileElement.files.length;i++){
        temp.push(FileElement.files[i]);
      }
      const imgData = {
        boardname: "imgtest",
        uid: this.$store.state.id,
        title: this.title,
        content: this.content,
        files : temp
      }
      const {data} = await saveImg(imgData);
      console.log(data);
      // await this.$router.push(`/list/${imgData.boardname}`);
    }
    // async getData(){
    //   const imgData = new FormData();
    //   imgData.append("uid", this.$store.state.id);
    //   imgData.append("title", this.title);
    //   imgData.append("content", this.content);
    //   for(let i = 0; i< FileElement.files.length;i++){
    //     imgData.append("files", FileElement.files[i]);
    //   }
    //   const response = await saveImg(imgData);
    //   console.log(response);
    // }
  }
};
</script>

<style scoped>

</style>