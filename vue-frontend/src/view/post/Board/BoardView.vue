<template>
    <div>
<!--        <BoardListForm></BoardListForm>-->
        <div v-if="this.$route.params.boardname === 'freeBoard'">
            <BoardListForm></BoardListForm>
        </div>
        <div v-if="this.$route.params.boardname === 'OOTD'">
            <CardForm></CardForm>
        </div>
        <!--<div v-if="this.$route.params.boardname === 'qna'">-->
        <!--    <QnaForm></QnaForm>-->
        <!--</div>-->
    </div>
</template>

<script>
import BoardListForm from "@/components/post/Board/BoardListForm";
import CardForm from "@/components/post/CardForm";
export default {
    data() {
        return {}
    },
    components: {BoardListForm, CardForm},
    methods   : {},
    created() {
    }
}
</script>

<style scoped>

</style>