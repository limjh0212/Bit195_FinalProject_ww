<template>
  <v-container>
    <div>
        <table>
            <tr class="row">
                <td class="card-box card" v-for="item in ootdList">
                  <img :src="item" class="imgCard">
                </td>
                <td class="card-box" v-for="(item, idx) in postList" :key="idx">
                  <div class="card-info">
                    <div class="card-box-default"><a :href="`/post/OOTD/${item.postnum}`">{{ item.title }}</a></div>
                    <div class="card-box-default">{{ item.postnum }}</div>
                  </div>
                  <div class="card-info">
                    <div class="card-box-default">{{ item.writer }}</div>
                    <div class="card-box-default" v-if="$moment(item.regdate).format('YYYY-MM-DD')===$moment().format('YYYY-MM-DD')">{{ $moment(item.regdate).format('HH:mm:ss') }}</div>
                    <div class="card-box-default" v-else>{{ $moment(item.regdate).format('YYYY-MM-DD') }}</div>
                  </div>
                  <div class="card-info">
                    <!--                      <div class="card-box-default">{{ item.existLike }}</div>-->
                    <div class="card-box-default">{{ item.likecount }}</div>
                  </div>
                </td>
            </tr>
        </table>
        <LoadingSpinner v-if="isLoading"></LoadingSpinner>
    </div>
  </v-container>
</template>

<script>
import LoadingSpinner from "@/components/common/LoadingSpinner";
import {getOotdList} from "@/api/img";
import {OOTDList} from "@/api/post";

export default {
    components: {LoadingSpinner},
    data() {
        return {
            isLoading: false,
            ootdList : [],
            postList:[],
            pageList : [],
        }
    },
    methods: {
        async fetchData() {
            this.isLoading = true;
            const {data} = await OOTDList();
            console.log(data);
            this.isLoading = false;
            this.postList = data.posts; // 포스트 정보 리스트
            this.pageList = data.pageList;

        },
        async fetchOotd(){
              this.isLoading = true;
              let imgSrc = '';
              const {data} = await getOotdList();
              this.isLoading = false;
              for (var i = 0; i< data.length; i++){
                imgSrc = "data:image/png;base64," + data[i];
                this.ootdList.push(imgSrc);
              } // 이미지 정보 리스트
        },
    },
    created() {
      this.fetchOotd();
      this.fetchData();
    }
}
</script>

<style scoped>
.text-left {
    width: 10rem;
}
.row{
  margin: 30px;
}
.card{
  width: 20%;
  height: 300px;
  border-radius: 5px;
  overflow: hidden;
  margin-left: 15px;
  margin-right: 15px;
}
.imgCard{
  width: 100%;
  height: 100%;
  object-fit: revert;
}
</style>