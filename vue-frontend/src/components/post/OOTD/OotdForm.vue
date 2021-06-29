<template>
  <v-container id="board-list-form">
      <div class="content-view">
        <div v-if="myList" class="mt-3 mb-3 d-fixed">
          <router-link to="/post/OOTD" class="board-btn btn-outline-primary mr-2">
            <span class="btn-text">글 작성</span>
          </router-link>
          <button class="board-btn btn-success" id="ootd-btn" @click="fetchOotd">
                <span
                    class="spinner-border spinner-border-sm"
                    role="status"
                    aria-hidden="true"
                ></span>
            <span class="btn-text" @click="fetchData">전체 보기</span>
          </button>
        </div>
        <div v-else class="mt-3 mb-3 d-fixed">
            <router-link to="/post/OOTD" class="board-btn btn-outline-primary mr-2">
              <span class="btn-text">글 작성</span>
            </router-link>
            <button class="board-btn btn-success" id="fusionexport-btn" @click="fetchMyImgList()">
              <span
                  class="spinner-border spinner-border-sm"
                  role="status"
                  aria-hidden="true"
              ></span>
              <span class="btn-text" @click="fetchMyPostList()">내 글 보기</span>
            </button>
        </div>
        <div>
          <div class="main">
            <table>
                <tr class="ootd-line">
                    <td class="card" v-for="item in list()">
                      <div id="card-form" class="card-size">
                          <div class="card-img card-box">
                            <div><img :src="item[0]" class="imgCard"></div>
                          </div>
                          <div class="card-info card-box">
                            <div class="card-box-default card-info1">
                              <div class="card-info1-left">{{ item[1].postnum }}</div>
                              <div class="card-info1-right"><router-link :to="`/post/OOTD/${item[1].num}`"><div>{{ item[1].title }}</div></router-link></div>
                            </div>
                            <div id="card-post" class="card-box-default card-info2">
                              <div class="card-info2-left">{{ item[1].writer }}</div>
                              <div class="card-info2-right" v-if="$moment(item[1].regdate).format('YYYY-MM-DD')===$moment().format('YYYY-MM-DD')">{{ $moment(item[1].regdate).format('HH:mm:ss') }}</div>
                              <div class="card-info2-right" v-else>{{ $moment(item[1].regdate).format('YYYY-MM-DD') }}</div>
                            </div>
                            <div class="card-box-default card-info3">
                              <!--                      <div class="card-info3-default">{{ item.existLike }}</div>-->
                              <div class="card-info3-default">좋아요 {{ item[1].likecount }}</div>
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
import {getMyOotdList, getOotdList} from "@/api/img";
import {boardUserListNoPage, OOTDList} from "@/api/post";

export default {
    components: {LoadingSpinner},
    data() {
        return {
            isLoading: false,
            postList:[],
            cardList: [],
            myList   : false,
        }
    },
    methods: {
        async fetchMyPostList() {
          const {data} = await boardUserListNoPage(this.$store.state.id, this.$route.params.boardname);
          this.postList = data.posts;
          this.myList = true;
        },
        async fetchMyImgList() {
          this.isLoading = true;
          const {data} = await getMyOotdList(this.$store.state.id);
          this.isLoading = false;
          this.myList = true;
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

          await this.fetchMyPostList()
        },
        async fetchData() {
            this.isLoading = true;
            const {data} = await OOTDList();
            this.isLoading = false;
            this.myList = false;
            this.postList = data.posts; // 포스트 정보 리스트
        },
        async fetchOotd(){
            this.isLoading = true;
            const {data} = await getOotdList();
            this.isLoading = false;
            this.myList = false;
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

          await this.fetchData();
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

    },
    created() {
      this.fetchOotd();

    }
}
</script>

<style scoped>
@import '../../../css/cmnty/ootdList.css';
</style>