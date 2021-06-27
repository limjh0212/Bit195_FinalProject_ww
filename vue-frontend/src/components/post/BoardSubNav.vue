<template>
    <div>
        <v-container>
            <div style="display: flex">
                <v-btn elevation="2">
                    <router-link to="/list/freeBoard">자유게시판</router-link>
                </v-btn>&nbsp;&nbsp;&nbsp;
                <v-btn elevation="2">
                    <router-link to="/list/OOTD">OOTD</router-link>
                </v-btn>&nbsp;&nbsp;&nbsp;
                <v-btn elevation="2">
                    <router-link to="/list/qna">Q&A</router-link>
                </v-btn>
            </div>
        </v-container>
    </div>
</template>

<script>
export default {
    methods : {},
    computed: {
        isAdmin() {
            return this.$store.getters.isAdmin;
        }
    }
}
</script>

<style scoped>

</style>