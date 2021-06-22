<template>
    <div>
        <div v-if="this.$route.params.boardname === 'freeBoard'">
            <BoardListForm></BoardListForm>
        </div>
        <div v-if="this.$route.params.boardname === 'OOTD'">
            <CardForm></CardForm>
        </div>
        <div v-if="this.$route.params.boardname === 'qna'">
            <QnaListForm></QnaListForm>
        </div>
    </div>
</template>

<script>
import BoardListForm from "@/components/post/Board/BoardListForm";
import CardForm from "@/components/post/OOTD/OotdForm";
import QnaListForm from "@/components/post/Qna/QnaListForm";

export default {
    data() {
        return {}
    },
    components: {QnaListForm, BoardListForm, CardForm},
    methods   : {},
    created() {
    }
}
</script>

<style scoped>

</style>