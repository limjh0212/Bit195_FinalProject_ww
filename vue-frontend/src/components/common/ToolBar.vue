<template>
    <div class="header">
        <router-link class="toolbar" to="/">Main</router-link>
        |
        <router-link class="toolbar" to="/member">회원 조회&nbsp;</router-link>
        |
        <router-link class="toolbar" to="/board">게시글 조회</router-link>
        |
        <router-link class="toolbar" to="/post">게시글 작성</router-link>
        |
        <router-link class="toolbar" to="/login">Log in</router-link>
    </div>
</template>

<script>
export default {
    name: "ToolBar"
}
</script>

<style scoped>
.header {
    color: white;
    background-color: #b6a3a3;
    /*display: flex;*/
    padding: 8px;
    text-decoration: none;
}

.header .router-link-exact-active {
    color: #35495e;
}

.header a {
    color: white;
}

.toolbar {
    text-decoration: none;
}

.tab {
    white-space: pre;
}
</style>