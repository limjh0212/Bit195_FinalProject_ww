<template>
    <v-container class="content-view">
        <div id="board-list-form">
            <v-simple-table>
                <template v-slot:default>
                    <thead>
                        <tr>
                            <th class="th" style="width: 50px;">No.</th>
                            <th class="th" style="width: 90px;">작성자</th>
                            <th class="th" style="width: 300px;">Title</th>
                            <th class="th" style="width: 120px;">작성일</th>
                            <th class="th" style="width: 50px;">좋아요</th>
                            <th class="th" style="width: 50px;">조회</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(item, idx) in items.posts" :key="idx">
                            <td class="text_center">{{ item.postnum }}</td>
                            <td class="text_center">{{ item.writer }}</td>
                            <td><a :href="`/post/qna/${item.num}`">{{ item.title }}</a></td>
                            <td v-if="$moment().format('YYYY-MM-DD') === $moment(item.regdate).format('YYYY-MM-DD')"
                                class="text_center">
                                {{ $moment(item.regdate).format('HH:mm:ss') }}
                            </td>
                            <td v-else class="text_center">{{ $moment(item.regdate).format('YYYY-MM-DD') }}</td>
                            <td class="text_center">{{ item.likecount }}</td>
                            <td class="text_center">{{ item.readcount }}</td>
                        </tr>
                    </tbody>
                    <tfoot>
                        <th colspan="6">
                            <div style="justify-content: space-between; display: flex">
                                <div>
                                    <v-btn elevation="1" rounded small>
                                        <router-link to="/post/freeBoard">글 작성<i class="material-icons"
                                                                                 style="font-size: 15px">create</i>
                                        </router-link>
                                    </v-btn>
                                </div>
                                <div>
                                    <div class="page-list" style="display: flex">
                                        <div v-for="page in items.pageList" @click="fetchData(page)">&nbsp;&nbsp;&nbsp;{{
                                                page
                                            }}&nbsp;&nbsp;&nbsp;
                                        </div>
                                    </div>
                                </div>
                                <div>
                                    <div class="foot-search">
                                        <div class="foot-search-box">
                                            <select v-model="search" name="search">
                                                <option value="">Select</option>
                                                <option value="true">제목</option>
                                                <option value="false">제목+내용</option>
                                            </select>
                                        </div>
                                        <div class="foot-search-box">
                                            <input v-model="keyword" placeholder="검색어를 입력하세요" type="text"></input>
                                        </div>
                                        <div class="foot-search-box">
                                            <button @click.prevent="SearchData"><i class="material-icons"
                                                                                   style="font-size: 25px">search</i>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </th>
                    </tfoot>
                </template>
            </v-simple-table>
        </div>
    </v-container>
</template>

<script>
import {boardUserList, freeBoardAll, freeBoardTitle, qnaAdminList} from "@/api/post";

export default {
    data() {
        return {
            items    : [],
            options  : ['제목', '제목+내용'],
            keyword  : '',
            search   : true,
        }
    },
    computed: {
        isAdmin() {
            return this.$store.getters.isAdmin;
        }
    },
    methods : {
        //게시글 조회
        async fetchData(num) {
            if (this.isAdmin) {
                const {data} = await qnaAdminList(num);
                this.items = data;
            } else {
                const {data} = await boardUserList(this.$store.state.id, num, this.$route.params.boardname);
                this.items = data;
            }
        },

        //게시글 검색
        async SearchData() {
            if (this.search === true) {
                // Title 검색
                const {data} = await freeBoardTitle(this.keyword, 1);
                this.items = data;
            } else {
                // Title+Content 검색
                const {data} = await freeBoardAll(this.keyword, 1);
                this.items = data;
            }
        },
    },
    created() {
        this.fetchData(1);
    }
}
</script>

<style scoped>
.text_center {
    text-align: center;
}
</style>