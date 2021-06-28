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
// import {saveOotdPost} from "@/api/img";

let FileElement = document.querySelector('#File');
export default {
    name   : "PostImgForm",
    data   : () => ({
        postnum: "",
        imgsrc : "",
    }),
    methods: {
        // 이미지 저장만 하는 것
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
          await console.log(data);
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
        // async saveOotd() {
        //     const BoardEl = document.querySelector('#Boardname');
        //     const TitleEl = document.querySelector('#Title');
        //     const ContentEl = document.querySelector('#Content');
        //     const ootdData = new FormData();
        //     ootdData.append("boardname", BoardEl.value);
        //     ootdData.append("title", TitleEl.value);
        //     ootdData.append("content", ContentEl.value);
        //     ootdData.append("uid", this.$store.state.id);
        //     for (let i = 0; i < FileElement.files.length; i++) {
        //         ootdData.append("images", FileElement.files[i]);
        //     }
        //     const {data} = await saveOotdPost(ootdData);
        //     console.log(data);
        //     this.postnum = data.postnum;
        //     console.log(this.postnum);
        //     // Todo: 포스트 번호가 이상함. db 의 가장 앞 전체 게시물 번호로 되어 있음. 수정필요.
        //     // await this.$router.push(`/post/${ootdData.get("boardname")}/${data.postnum}`);
        // },
        // onFileSelected: function (event) {
        //     FileElement = event.target;
        //     if (FileElement.files[0]) {
        //         let img = this.$refs.uploadImg;
        //         img.src = URL.createObjectURL(FileElement.files[0]);
        //         this.imgsrc = img.src;
        //         img.width = 200;
        //         img.onload = () => {
        //             URL.revokeObjectURL(this.src)
        //         }
        //     }
        // },
    },
};
</script>

<style scoped>

</style>