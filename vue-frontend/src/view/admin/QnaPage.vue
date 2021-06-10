<template>
    <div>
        <p>답변 없음 : {{cntNotAnswered}}</p>
        <p>최근 문의 : {{cntRecentQuestion}}</p>

        <li v-for="item in NotAnsweredQuestion">{{item.title}}</li>
    </div>
</template>

<script>
import {fetchQna} from "@/api/admin";

export default {
    data() {
        return {
            cntNotAnswered     : '',
            cntRecentQuestion  : '',
            NotAnsweredQuestion: [],

        }
    },
    methods: {
        async fetchData() {
            const {data} = await fetchQna();
            this.cntNotAnswered = data.cntNotAnswered;
            this.cntRecentQuestion = data.cntRecentQuestion;
            this.NotAnsweredQuestion = data.NotAnsweredQuestion;
        },
    },
    created() {
        this.fetchData()
    }
}
</script>

<style scoped>

</style>