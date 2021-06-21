<template>
    <div>
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