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
                            <td class="text_center"
                                v-if="$moment().format('YYYY-MM-DD') === $moment(item.regdate).format('YYYY-MM-DD')">
                                {{ $moment(item.regdate).format('HH:mm:ss') }}
                            </td>
                            <td class="text_center" v-else>{{ $moment(item.regdate).format('YYYY-MM-DD') }}</td>
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
                                    <v-btn elevation="1" rounded small v-if="myList"><span
                                        @click="fetchData(1)">전체 보기</span>
                                    </v-btn>
                                    <v-btn elevation="1" rounded small v-else><span @click="fetchMyList(1)">내글 보기</span>
                                    </v-btn>
                                </div>
                                <div>
                                    <div style="display: flex" class="page-list">
                                        <div style="display: flex" v-if="myList" v-for="page in items.pageList">
                                            <div class="page-box-default" @click="fetchMyList(page)">{{ page }}</div>&nbsp;&nbsp;&nbsp;
                                        </div>
                                        <div style="display: flex" v-if="myList===false" v-for="page in items.pageList">
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
import LoadingSpinner from "@/components/common/LoadingSpinner";
import {boardUserList, freeBoardAll, freeboardList, freeBoardTitle} from "@/api/post";

export default {
    components: {LoadingSpinner},
    data() {
        return {
            items    : [],
            pageList : [],
            options  : ['제목', '제목+내용'],
            isLoading: false,
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
            console.log(this.items)
        },

        async fetchData(num) {
            this.isLoading = true;
            const {data} = await freeboardList(num);
            this.isLoading = false;
            this.items = data;
            this.pageList = data.pageList;
            this.myList = false;
            console.log(this.items)
        },

        //기본 게시글 조회
        async SearchData() {
            if (this.search === true) {
                // Title 검색
                this.isLoading = true;
                const {data} = await freeBoardTitle(this.keyword, 1);
                this.isLoading = false;
                this.items = data;
            } else {
                // Title+Content 검색
                this.isLoading = true;
                const {data} = await freeBoardAll(this.keyword, 1);
                this.isLoading = false;
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