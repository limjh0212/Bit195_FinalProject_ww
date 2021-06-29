<template>
    <v-container id="board-list-form">
        <div class="content-view" style="display:flex; justify-content: left">
            <div style="display: block">
                <v-btn elevation="1" rounded small>
                    <router-link to="/post/OOTD">글 작성<i class="material-icons"
                                                        style="font-size: 15px">create</i>
                    </router-link>
                </v-btn>
                <v-btn v-if="myList===false" elevation="1" rounded small @click="[fetchMyPostList(),fetchMyImgList()]">
                    내글 보기<i class="material-icons" style="font-size: 15px">photo</i>
                </v-btn>
                <v-btn v-if="myList" elevation="1" rounded small @click="[fetchOotd(),fetchData()]">
                    전체 보기<i class="material-icons" style="font-size: 15px">photo</i>
                </v-btn>
            </div>
        </div>
        <div>
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
                                    <div class="card-info1-right">
                                        <router-link :to="`/post/OOTD/${item[1].num}`">
                                            <div>{{ item[1].title }}</div>
                                        </router-link>
                                    </div>
                                </div>
                                <div id="card-post" class="card-box-default card-info2">
                                    <div class="card-info2-left">{{ item[1].writer }}</div>
                                    <div v-if="$moment(item[1].regdate).format('YYYY-MM-DD')===$moment().format('YYYY-MM-DD')"
                                         class="card-info2-right">
                                        {{ $moment(item[1].regdate).format('HH:mm:ss') }}
                                    </div>
                                    <div v-else class="card-info2-right">
                                        {{ $moment(item[1].regdate).format('YYYY-MM-DD') }}
                                    </div>
                                </div>
                                <div class="card-box-default card-info3">
                                    <div class="card-info3-default">좋아요 {{ item[1].likecount }}</div>
                                </div>
                            </div>
                        </div>
                    </td>
                </tr>
            </table>
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
            postList : [],
            cardList : [],
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
            let img = '';
            let post = [];
            let temp = [];
            for (var i = 0; i < data.length; i++) {
                img = "data:image/png;base64," + data[i];
                temp.push(img);
                post = this.postList[i];
                temp.push(post);
            }
            this.cardList = temp;
        },
        async fetchData() {
            const {data} = await OOTDList();
            this.myList = false;
            this.postList = data.posts; // 포스트 정보 리스트
        },
        async fetchOotd() {
            const {data} = await getOotdList();
            this.myList = false;
            let img = '';
            let post = [];
            let temp = [];
            for (var i = 0; i < data.length; i++) {
                img = "data:image/png;base64," + data[i];
                temp.push(img);
                post = this.postList[i];
                temp.push(post);
            }
            this.cardList = temp;
        },
        list: function () {
            var card = [];
            var cards = [];
            for (var i = 0; i < this.cardList.length; i += 2) {
                card.push(this.cardList[i]);
                card.push(this.cardList[i + 1]);
                cards.push(card.slice(i, i + 2));
            }
            return cards;
        },

    },
    created() {
        this.fetchOotd();
        this.fetchData();
    }
}
</script>

<style scoped>
@import '../../../css/cmnty/ootdList.css';
</style>