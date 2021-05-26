<template>
    <div class="memberList">
        회원 List
        <p class="post" v-for="(item, idx) in items" v-bind:key="idx"><a v-bind:href="`member/${item.id}`"> {{
                item.id
            }}</a></p>
    </div>
</template>

<script>
export default {
    name    : "MemberList",
    computed: {
        items() {
            return this.$store.state.memberList
        }
    },

    created() {
        this.$store.dispatch('FETCH_MEMBER')
    }
}
</script>

<style scoped>
.memberList {
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