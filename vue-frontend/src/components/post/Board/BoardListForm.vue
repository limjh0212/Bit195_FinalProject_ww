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
                            <th class="th" style="width: 60px;">좋아요</th>
                            <th class="th" style="width: 50px;">조회</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(item, idx) in items.posts" :key="idx">
                            <td class="text_center">{{ item.postnum }}</td>
                            <td class="text_center">{{ item.writer }}</td>
                            <td><a :href="`/post/freeBoard/${item.num}`">{{ item.title }}</a></td>
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
                                    </v-btn>&nbsp;&nbsp;&nbsp;
                                    <v-btn v-if="myList" elevation="1" rounded small><span
                                        @click="fetchData(1)">전체 보기</span>
                                    </v-btn>
                                    <v-btn v-else elevation="1" rounded small><span @click="fetchMyList(1)">내글 보기</span>
                                    </v-btn>
                                </div>
                                <div>
                                    <div class="page-list" style="display: flex">
                                        <div v-for="page in items.pageList" v-if="myList" style="display: flex">
                                            <div class="page-box-default" @click="fetchMyList(page)">{{ page }}</div>&nbsp;&nbsp;&nbsp;
                                        </div>
                                        <div v-for="page in items.pageList" v-if="myList===false" style="display: flex">
                                            <div class="page-box-default" @click="fetchData(page)">{{ page }}</div>&nbsp;&nbsp;&nbsp;
                                        </div>
                                    </div>
                                </div>
                                <div>
                                    <div>
                                        <div>
                                            <select v-model="search" name="search">
                                                <option value="">Select</option>
                                                <option value="true">제목</option>
                                                <option value="false">제목+내용</option>
                                            </select>
                                        </div>
                                        <div style="height: auto">
                                            <input v-model="keyword" placeholder="검색어" type="text"></input>
                                        </div>
                                        <div>
                                            <button @click.prevent="SearchData">
                                                <i class="material-icons" style="font-size: 25px">search</i></button>
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
import {boardUserList, freeBoardAll, freeboardList, freeBoardTitle} from "@/api/post";

export default {
    data() {
        return {
            items    : [],
            pageList : [],
            options  : ['제목', '제목+내용'],
            keyword  : '',
            search   : true,
            myList   : false,
        }
    },
    methods: {
        //내글 목록
        async fetchMyList(num) {
            const {data} = await boardUserList(this.$store.state.id, num, this.$route.params.boardname);
            this.items = data;
            this.pageList = data.pageList;
            this.myList = true;
        },

        async fetchData(num) {
            const {data} = await freeboardList(num);
            this.items = data;
            this.pageList = data.pageList;
            this.myList = false;
        },

        //기본 게시글 조회
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
/*table 상단*/
.th {
    width: 10rem;
    text-align: center;
}

/*table 하단*/
.page-list {
    width: 50%;
    text-align: center;
    position: relative;
    height: auto;
    font-size: 15px;
    margin: auto;
}

.page-box-default {
    margin: 3px;
}

.text_center {
    text-align: center;
}

</style>