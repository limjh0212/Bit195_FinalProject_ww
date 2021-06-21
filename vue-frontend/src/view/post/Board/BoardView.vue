<template>
    <div>
        <BoardListForm></BoardListForm>
        <!--<div v-if="this.$route.params.boardname === 'freeBoard'">-->
        <!--    <ListForm></ListForm>-->
        <!--</div>-->
        <!--<div v-if="this.$route.params.boardname === 'OOTD'">-->
        <!--    <CardForm></CardForm>-->
        <!--</div>-->
        <!--<div v-if="this.$route.params.boardname === 'qna'">-->
        <!--    <QnaForm></QnaForm>-->
        <!--</div>-->
    </div>
</template>

<script>
import BoardListForm from "@/components/post/Board/BoardListForm";

export default {
    data() {
        return {}
    },
    components: {BoardListForm},
    methods   : {},
    created() {
    }
}
</script>

<style scoped>

</style>