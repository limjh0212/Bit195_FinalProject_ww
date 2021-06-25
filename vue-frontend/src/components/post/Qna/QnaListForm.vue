<template>
  <v-container class="content-view">
    <div id="board-list-form">
      <v-simple-table>
        <template v-slot:default>
            <thead>
                <tr>
                    <th class="text-left">요청번호</th>
                    <th class="text-left">Title</th>
                    <th class="text-left">작성자</th>
                    <th class="text-left">작성일</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="(item, idx) in items.posts" :key="idx">
                    <td>{{ item.postnum }}</td>
                    <td><a :href="`/post/qna/${item.num}`">{{ item.title }}</a></td>
                    <td>{{ item.writer }}</td>
                    <td v-if="$moment().format('YYYY-MM-DD') === $moment(item.regdate).format('YYYY-MM-DD')">
                        {{ $moment(item.regdate).format('HH:mm:ss') }}
                    </td>
                    <td v-else>{{ $moment(item.regdate).format('YYYY-MM-DD') }}</td>
                </tr>
            </tbody>
            <tfoot>
                <button v-if="isAdmin===false">
                    <router-link to="/post/qna">글 작성</router-link>
                </button>

                <div>
                    <ul>
                        <li v-for="page in items.pageList" @click="fetchData(page)">{{ page }}</li>
                    </ul>
                </div>
                <div>
                    <select v-model="search" name="search">
                        <option value="">Select</option>
                        <option value="true">제목</option>
                        <option value="false">제목+내용</option>
                    </select>
                    <input v-model="keyword" placeholder="검색어를 입력하세요" type="text"></input>
                    <button @click.prevent="SearchData">검색</button>
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
import {boardUserList, freeBoardAll, freeBoardTitle, qnaAdminList} from "@/api/post";

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
    computed: {
        isAdmin() {
            return this.$store.getters.isAdmin;
        }
    },
    methods : {
        //게시글 조회
        async fetchData(num) {
            if (this.isAdmin) {
                this.isLoading = true;
                const {data} = await qnaAdminList(num);
                console.log(data);
                this.isLoading = false;
                this.items = data;
            } else {
                this.isLoading = true;
                const {data} = await boardUserList(this.$store.state.id,num,this.$route.params.boardname);
                console.log(data);
                console.log(this.$store.state.id,num);
                this.isLoading = false;
                this.items = data;
            }
        },

        //게시글 검색
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
.text-left {
    width: 10rem;
}


</style>