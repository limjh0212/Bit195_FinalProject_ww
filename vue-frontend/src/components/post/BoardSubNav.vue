<template>
    <div>
        <v-container>
            <div id="sub-nav">
                <div class="sub-nav-box">
                    <router-link to="/list/freeBoard">
                        <div class="sub-nav-box-default">자유게시판</div>
                    </router-link>
                </div>
                <div class="sub-nav-box">
                    <router-link to="/list/OOTD">
                        <div class="sub-nav-box-default">OOTD</div>
                    </router-link>
                </div>
                <div class="sub-nav-box">
                    <router-link to="/list/qna">
                        <div class="sub-nav-box-default">Q&A</div>
                    </router-link>
                </div>
            </div>
        </v-container>
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