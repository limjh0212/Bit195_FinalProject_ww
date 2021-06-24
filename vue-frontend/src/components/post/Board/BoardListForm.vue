<template>
  <v-container class="content-view">
    <div id="board-list-form">
      <v-simple-table>
        <template v-slot:default>
            <thead>
                <tr>
                    <th class="th">No.</th>
                    <th class="th">Title</th>
                    <th class="th">작성자</th>
                    <th class="th">작성일</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="(item, idx) in items.posts" :key="idx">
                    <td>{{ item.postnum }}</td>
                    <td><a :href="`/post/freeBoard/${item.num}`">{{ item.title }}</a></td>
                    <td>{{ item.writer }}</td>
                    <td v-if="$moment().format('YYYY-MM-DD') === $moment(item.regdate).format('YYYY-MM-DD')">
                        {{ $moment(item.regdate).format('HH:mm:ss') }}
                    </td>
                    <td v-else>{{ $moment(item.regdate).format('YYYY-MM-DD') }}</td>
                </tr>
            </tbody>
            <tfoot>
                <div>
                <button><router-link to="/post/freeBoard">글 작성</router-link></button>
                |
                <button><router-link to="#">내글 보기</router-link></button>
                </div>
                <div>
                  <div class="page-list">
                    <div class="page-box" v-for="page in items.pageList" @click="fetchData(page)">
                      <div class="page-box-default">{{ page }}</div>
                    </div>
                  </div>
                </div>
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
                    <button @click.prevent="SearchData">검색</button>
                  </div>
                </div>
            </tfoot>
            <span v-if="isLoading"><LoadingSpinner></LoadingSpinner></span>
        </template>
      </v-simple-table>
    </div>
  </v-container>
</template>

<script>
import LoadingSpinner from "@/components/common/LoadingSpinner";
import {freeBoardAll, freeboardList, freeBoardTitle} from "@/api/post";

export default {
    components: {LoadingSpinner},
    data() {
        return {
            items    : [],
            options  : ['제목', '제목+내용'],
            isLoading: false,
            keyword  : '',
            search   : true,
        }
    },
    methods: {
        async fetchData(num) {
            this.isLoading = true;
            const {data} = await freeboardList(num);
            this.isLoading = false;
            this.items = data;
        },

        //기본 게시글 조회
        async SearchData() {
            if (this.search === true) {
                console.log(this.search);
                // Title 검색
                this.isLoading = true;
                const {data} = await freeBoardTitle(this.keyword, 1);
                this.isLoading = false;
                this.items = data;
            } else {
                console.log(this.search);
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
@import "../../../css/cmnty/boardList.css";
</style>