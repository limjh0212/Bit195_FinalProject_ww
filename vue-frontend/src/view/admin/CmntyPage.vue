<template>
    <div>
        <p>최근 작성글 : {{cntRecentPosts}}</p>
        <p>최근 자유게시글 : {{cntRecentFB}}</p>
        <p>자유게시글 : {{cntFreeBoard}}</p>
        <p>최근 OOTD : {{cntRecentOOTD}}</p>
        <p>OOTD : {{cntOOTD}}</p>
        <p>전체게시글 : {{cntTotalPosts}}</p>
    </div>
</template>

<script>
import {fetchCmnty} from "@/api/admin";

export default {
    data() {
        return {
            cntFreeBoard  : '',
            cntTotalPosts : '',
            cntRecentFB   : '',
            cntRecentOOTD : '',
            cntRecentPosts: '',
            cntOOTD       : '',
        }
    },
    methods: {
        async fetchData() {
            const {data} = await fetchCmnty();
            this.cntFreeBoard = data.cntFreeBoard;
            this.cntTotalPosts = data.cntTotalPosts;
            this.cntRecentFB = data.cntRecentFB;
            this.cntRecentOOTD = data.cntRecentOOTD;
            this.cntRecentHD = data.cntRecentHD;
            this.cntRecentPosts = data.cntRecentPosts
            this.cntOOTD = data.cntOOTD;
        },
    },
    created() {
        this.fetchData()
    }

}
</script>

<style scoped>

</style>