<template>
  <div>
  <div>
    <img :src="src">
  </div>
  <div>
    <table>
      <thead>
        <tr>
          <th>No.</th>
          <th>제목</th>
          <th>작성자</th>
          <th>내용</th>
        </tr>
      </thead>
      <tbody>
<!--        <tr>-->
<!--          <td>{{ postInfo.postnum }}</td>-->
<!--          <td>{{ postInfo.title }}</td>-->
<!--          <td>{{ postInfo.writer }}</td>-->
<!--          <td>{{ postInfo.content }}</td>-->
<!--        </tr>-->
        <tr  v-for="(item, idx) in imgSrcList" :key="idx">
          <img :src="item">
        </tr>
      </tbody>
    </table>
  </div>
  </div>
</template>

<script>
// import {getImg} from "@/api/img";
import {getImg, getImgList} from "@/api/img";

export default {
  name: "GetImgForm",
  data() {
    return{
        imgSrcList : [],
        src : "",
    }
  },
  methods : {
    async getImg(){
      var base = this;
      const num = 6;
      await getImg(num)
          .then(function (response) {
            let base64String = btoa(String.fromCharCode.apply(null, new Uint8Array(response.data)));
            base.src = "data:image/png;base64,"+base64String;
          });
    },
    async getImgList(){
        var base = this;
        let imgSrc = '';
        const num = 118; // postDB num
        const {data} = await getImgList(num);
        console.log(data.length);
        for (var i = 0; i < data.length; i++) {
          imgSrc = "data:image/png;base64," + data[i];
          base.imgSrcList.push(imgSrc);
        }
        console.log(this.imgSrcList);
    },
  },
  created() {
    this.getImg();
    this.getImgList();
  }
};
</script>

<style scoped>

</style>