<template>
    <div class="header">
        <router-link class="toolbar" to="/">Home&nbsp;</router-link>
        <span class="tab">&#9;</span>
        <router-link class="toolbar" to="/member">회원 조회&nbsp;</router-link>
        <span class="tab">&#9;</span>
        <router-link class="toolbar" to="/member">회원 수정</router-link>

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