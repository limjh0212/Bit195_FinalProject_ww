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
            <!--<div id="sub-nav">-->
            <!--    <div class="sub-nav-box">-->
            <!--        <router-link to="/list/freeBoard">-->
            <!--            <div class="sub-nav-box-default">자유게시판</div>-->
            <!--        </router-link>-->
            <!--    </div>-->
            <!--    <div class="sub-nav-box">-->
            <!--        <router-link to="/list/OOTD">-->
            <!--            <div class="sub-nav-box-default">OOTD</div>-->
            <!--        </router-link>-->
            <!--    </div>-->
            <!--    <div class="sub-nav-box">-->
            <!--        <router-link to="/list/qna">-->
            <!--            <div class="sub-nav-box-default">Q&A</div>-->
            <!--        </router-link>-->
            <!--    </div>-->
            <!--</div>-->
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