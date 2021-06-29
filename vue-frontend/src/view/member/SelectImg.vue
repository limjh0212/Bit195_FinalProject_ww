<template>
<div id="signup-form" class="card-select">
  <div class="card-select1" >
      <div class="card-select-box" v-if="i<6" v-for="(item,i) in recoImgList">
        <div class="card-select-box-default"><img class="card-select-img" :src="item"></div>
      </div>
  </div>
  <div class="card-select2">
    <div class="card-select-box" v-if="i>5" v-for="(item,i) in recoImgList">
      <div class="card-select-box-default"><img class="card-select-img" :src="item"></div>
    </div>
  </div>
</div>
</template>

<script>
import {getRecoImgList,getFileNameList} from "@/api/img";

export default {
  name: "SelectImg",
  data(){
    return {
      recoImgList : [],
    }
  },
  methods:{
    async fetchRecoImgList(){
      const {data} = await getRecoImgList();
      console.log(data);
      let imgsrc='';
      let imgList = [];
      for (var i=0;i<data.length;i++){
        imgsrc="data:image/png;base64," + data[i];
        imgList.push(imgsrc);
      }
      this.recoImgList = imgList;
    },
    async fetchFileNameList(){
      const {data} = await getFileNameList();
      console.log(data);
    }
  },
  created() {
    this.fetchRecoImgList();
    this.fetchFileNameList();
  }
}
</script>

<style scoped>
@import '../../css/common/signup.css';
</style>