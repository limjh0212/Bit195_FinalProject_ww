<template>
  <div>
  <div class="test">
    <img class= "profile" :src="src">
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
    // Todo: 상단 바에 메소드 적용 필요.
    async getImg(){
      var base = this;
      const num = 1; //로그인한 회원 정보에서 img 값 가져오는 걸로 변경 $store.
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
.test{
  width: 150px;
  height: 150px;
  border-radius: 70%;
  overflow: hidden;
  margin-left: 20px;
  margin-top : 20px;
  margin-bottom: 20px;
  margin-right: 20px;
}
.profile {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
</style>