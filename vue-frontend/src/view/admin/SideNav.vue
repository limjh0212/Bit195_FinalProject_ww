<template>
    <div>
        <router-link to="/admin/main">관리페이지</router-link> |
        <router-link to="/admin/cmnty">커뮤니티관리</router-link> |
        <router-link to="/admin/member">회원 관리</router-link> |
        <router-link to="/admin/qna">Q&A 관리</router-link> |
        <router-link to="/admin/memberlist">회원 조회</router-link>
    </div>

</template>

<script>
export default {
    name: "SideNav"
}
</script>

<style scoped>

</style>