<template>
    <div class="boardList">
        게시글 List
        <p class="post" v-for="(item, idx) in items" v-bind:key="idx"><a v-bind:href="`board/${item.num}`">
            {{item.title}}</a> | {{item.writer}} | {{item.regdate}}</p>
    </div>
</template>

<script>
export default {
    name    : "FreeBoardList",
    computed: {
        items() {
            return this.$store.state.boardList
        },
    },

    created() {
        this.$store.dispatch('FETCH_BOARD')
    }
}
</script>

<style scoped>
.boardList {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 60px;

}

.post {
    display: flex;
    align-items: center;
    list-style: none;
    border-bottom: 1px solid #eee;
}
</style>