<template>
    <div>
        <router-link to="#">코디 좋아요</router-link>|
        <router-link to="#">내가 작성한 글</router-link>|
        <router-link to="#">내가 작성한 댓글</router-link>|
        <router-link to="/mypage/location">위치 등록</router-link>|
        <router-link to="/mypage/aboutme">내 정보</router-link>
        <router-view></router-view>
    </div>
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