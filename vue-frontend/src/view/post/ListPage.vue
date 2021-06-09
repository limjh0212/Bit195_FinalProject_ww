<template>
    <div>
        <div v-if="this.$route.params.boardname === 'freeboard'">
            <ListForm></ListForm>
        </div>
        <div v-if="this.$route.params.boardname === 'OOTD'">
            <CardForm></CardForm>
        </div>
        <div v-if="this.$route.params.boardname === 'qna'">
            <QnaForm></QnaForm>
        </div>
    </div>
</template>

<script>
import ListForm from "@/components/post/ListForm";
import CardForm from "@/components/post/CardForm";
import QnaForm from "@/components/post/QnaForm";

export default {
    data() {
        return {}
    },
    components: {QnaForm, CardForm, ListForm},
    methods   : {},
    created() {
    }
}
</script>

<style scoped>

</style>