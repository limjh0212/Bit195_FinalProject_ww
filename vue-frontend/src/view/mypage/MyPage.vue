<template>
  <v-container>
    <div id="sub-nav">
      <div class="sub-nav-box">
        <router-link to="#"><div class="sub-nav-box-default">코디 좋아요</div></router-link>
      </div>
      <div class="sub-nav-box">
        <router-link to=" "><div class="sub-nav-box-default">내가 작성한 글</div></router-link>
      </div>
      <div class="sub-nav-box">
        <router-link to="#"><div class="sub-nav-box-default">내가 작성한 댓글</div></router-link>
      </div>
      <div class="sub-nav-box">
        <router-link to="/mypage/location"><div class="sub-nav-box-default">위치 등록</div></router-link>
      </div>
      <div class="sub-nav-box">
        <router-link to="/mypage/aboutme"><div class="sub-nav-box-default">내 정보</div></router-link>
      </div>
    </div>
    <div>
      <router-view></router-view>
    </div>
  </v-container>
</template>

<script>
import MyPageMain from "@/view/mypage/MyPageMain";

export default {
    name      : "MyPage",
    components: {MyPageMain}
}
</script>

<style scoped>

</style>