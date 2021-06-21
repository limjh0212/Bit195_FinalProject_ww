<template>
    <div>
        <router-link to="/list/freeBoard">자유게시판</router-link>
        |
        <router-link to="/list/OOTD">OOTD</router-link>
        |
        <router-link to="/post/qna">문의하기</router-link>
        |
        <router-link v-if="isAdmin" to="/list/qna">Q&A</router-link>
    </div>
</template>

<script>
export default {
    computed: {
        isAdmin() {
            return this.$store.getters.isAdmin;
        }
    }
}
</script>

<style scoped>

</style>