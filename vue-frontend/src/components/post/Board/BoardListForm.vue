<template xmlns="http://www.w3.org/1999/html">
    <v-simple-table style="width: 70%">
        <template v-slot:default>
            <thead>
                <tr>
                    <th class="text-left">No.</th>
                    <th class="text-left">Title</th>
                    <th class="text-left">작성자</th>
                    <th class="text-left">작성일</th>
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
                <button>
                    <router-link to="/post/freeboard">글 작성</router-link>
                </button>
                |
                <button>
                    <router-link to="#">내글 보기</router-link>
                </button>
                <div>
                    <ul>
                        <li v-for="page in items.pageList" @click="fetchData(page)">{{ page }}</li>
                    </ul>
                </div>
                <div>
                    <select v-model="search" data-title="선택" name="search">
                        <option value="0">선택</option>
                        <option value="1">제목</option>
                        <option value="2">제목+내용</option>
                    </select>
                    <input v-model="keyword" placeholder="검색어를 입력하세요" type="text"></input>
                    <button @click.prevent="SearchDataTitle">검색</button>
                </div>
            </tfoot>
        </template>
    </v-simple-table>
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
            search   : ''
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
        async SearchDataTitle() {
            this.isLoading = true;
            const {data} = await freeBoardTitle(this.keyword, 1);
            this.isLoading = false;
            this.items = data;
        },

        //기본 게시글 조회
        async SearchDataAll() {
            this.isLoading = true;
            const {data} = await freeBoardAll(this.keyword, 1);
            this.isLoading = false;
            this.items = data;
        },
        //검색 결과 조회
        isTitleSearch() {
            return this.search === 1;
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