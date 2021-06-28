<template>
    <v-container>
        <div id="sub-nav">
            <div v-if="isAdmin">
                <v-btn elevation="2">
                    <router-link to="/admin">
                        <div class="sub-nav-box-default">관리자 페이지</div>
                    </router-link>
                </v-btn>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <v-btn elevation="2">
                    <router-link to="/mypage/aboutme">
                        <div class="sub-nav-box-default">내 정보</div>
                    </router-link>
                </v-btn>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </div>
            <div v-else>
                <v-btn elevation="2">
                    <router-link to="/mypage/mypost">
                        <div class="sub-nav-box-default">내가 작성한 글</div>
                    </router-link>
                </v-btn>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <v-btn elevation="2">
                    <router-link to="/mypage/mycmnt">
                        <div class="sub-nav-box-default">내가 작성한 댓글</div>
                    </router-link>
                </v-btn>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <v-btn elevation="2">
                    <router-link to="/mypage/location">
                        <div class="sub-nav-box-default">위치 등록</div>
                    </router-link>
                </v-btn>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <v-btn elevation="2">
                    <router-link to="/mypage/aboutme">
                        <div class="sub-nav-box-default">내 정보</div>
                    </router-link>
                </v-btn>
            </div>
        </div>
        <div class="router-view">
            <router-view></router-view>
        </div>
    </v-container>
</template>

<script>
import MyPageMain from "@/view/mypage/MyPageMain";

export default {
    name      : "MyPage",
    components: {MyPageMain},
    computed  : {
        isAdmin() {
            return this.$store.getters.isAdmin;
        }
    },
}
</script>

<style scoped>

</style>