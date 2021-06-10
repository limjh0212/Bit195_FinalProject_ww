<template>
    <div>
        <p>자유게시글 : {{ cntFreeBoard }}</p>
        <p>OOTD 글 : {{ cntOOTD }}</p>
        <p>최근 자유게시글 : {{ cntRecentFB }}</p>
        <p>최근 OOTD : {{ cntRecentOOTD }}</p>
        <p>최근 작성글 : {{ cntRecentPosts }}</p>
        <p>전체 게시글 : {{ cntTotalPosts }}</p>
    </div>
</template>

<script>
import {fetchAdminMain} from "@/api/admin";

export default {
    data() {
        return {
            cntFreeBoard  : '',
            cntOOTD       : '',
            cntRecentFB   : '',
            cntRecentOOTD : '',
            cntRecentPosts: '',
            cntTotalPosts : '',
        }
    },
    methods: {
        async fetchData() {
            const {data} = await fetchAdminMain();
            this.cntFreeBoard = data.cntFreeBoard;
            this.cntOOTD = data.cntOOTD;
            this.cntRecentFB = data.cntRecentFB;
            this.cntRecentOOTD = data.cntRecentOOTD;
            this.cntRecentPosts = data.cntRecentPosts;
            this.cntTotalPosts = data.cntTotalPosts;
        },
    },
    created() {
        this.fetchData()
    }

}
</script>

<style scoped>

</style>