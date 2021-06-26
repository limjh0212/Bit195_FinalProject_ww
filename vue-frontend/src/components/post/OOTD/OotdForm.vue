<template>
  <v-container id="board-list-form">
      <div class="content-view">
        <div class="mt-3 mb-3 d-fixed">
            <router-link to="/post/OOTD" class="board-btn btn-outline-primary mr-2">
              <span class="btn-text">글 작성</span>
            </router-link>
            <button class="board-btn btn-success" id="fusionexport-btn">
<!--              @click="fetchMyList(1)"-->
              <span
                  class="spinner-border spinner-border-sm"
                  role="status"
                  aria-hidden="true"
              ></span>
              <span class="btn-text">내 글 보기</span>
            </button>
        </div>
        <div>
          <div class="main">
            <table>
                <tr class="row">
                    <td id="card-form" class="card" v-for="item in list()">
                      <div class="card-content">
                          <div id="card-img" class="card-box">
                            <img :src="item[0]" class="imgCard">
                          </div>
                          <div class="card-box">
                            <div class="card-info">
                              <div class="card-box-default"><router-link :to="`/post/OOTD/${item[1].num}`"><div>{{ item[1].title }}</div></router-link></div>
                              <div class="card-box-default">{{ item[1].postnum }}</div>
                            </div>
                            <div id="card-post" class="card-info">
                              <div class="card-box-default">{{ item[1].writer }}</div>
                              <div class="card-box-default" v-if="$moment(item[1].regdate).format('YYYY-MM-DD')===$moment().format('YYYY-MM-DD')">{{ $moment(item[1].regdate).format('HH:mm:ss') }}</div>
                              <div class="card-box-default" v-else>{{ $moment(item[1].regdate).format('YYYY-MM-DD') }}</div>
                            </div>
                            <div class="card-info">
                              <!--                      <div class="card-box-default">{{ item.existLike }}</div>-->
                              <div class="card-box-default">{{ item[1].likecount }}</div>
                            </div>
                          </div>
                      </div>
                    </td>
                </tr>
            </table>
          </div>
        </div>
            <LoadingSpinner v-if="isLoading"></LoadingSpinner>
      </div>
  </v-container>
</template>

<script>
import LoadingSpinner from "@/components/common/LoadingSpinner";
import {getOotdList} from "@/api/img";
import {boardUserList, OOTDList} from "@/api/post";

export default {
    components: {LoadingSpinner},
    data() {
        return {
            isLoading: false,
            postList:[],
            pageList : [],
            cardList: [],
            myList   : false,
        }
    },
    methods: {
        async fetchData() {
            this.isLoading = true;
            const {data} = await OOTDList();
            this.isLoading = false;
            this.postList = data.posts; // 포스트 정보 리스트
            this.pageList = data.pageList;
        },
        async fetchOotd(){
            this.isLoading = true;
            const {data} = await getOotdList();
            this.isLoading = false;
            let img='';
            let post=[];
            let temp = [];
            for (var i=0;i<data.length;i++){
              img = "data:image/png;base64," + data[i];
              temp.push(img);
              post = this.postList[i];
              temp.push(post);
            }
            this.cardList=temp;
        },
        list : function (){
          var card = [];
          var cards =[];
          for (var i=0;i<this.cardList.length;i+=2){
            card.push(this.cardList[i]);
            card.push(this.cardList[i+1]);
            cards.push(card.slice(i,i+2));
          }
          return cards;
        },
        // async fetchMyList(num) {
        //   const {data} = await boardUserList(this.$store.state.id, num, this.$route.params.boardname);
        //   this.postList = data;
        //   this.pageList = data.pageList;
        //   this.myList = true;
        //   console.log(this.postList)
        // },
    },
    created() {
      this.fetchOotd();
      this.fetchData();
    }
}
</script>

<style scoped>
@media(max-width: 1200px){
  #card-form{
    width:23%;
    height: 20%;
  }
}
.row{
  margin: 0px;
}
.card-content{
  -ms-flex: 1 1 auto;
  flex: 1 1 auto;
  padding: 0.2rem;
}
.card{
  width: 23%;
  height: 350px;
  border-radius: 5px;
  overflow: hidden;
  padding: 5px 5px;
  margin-left: 5px;
  margin-right: 5px;
}
.imgCard{
  width: 100%;
  height: 100%;
  object-fit: revert;
}
.card-box {
  width: 100%;
}
</style>