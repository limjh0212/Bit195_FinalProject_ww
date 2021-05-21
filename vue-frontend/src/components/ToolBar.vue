<template>
    <div class="header">
        <router-link class="toolbar" to="/"> Home   </router-link>
        <router-link class="toolbar" to="/member"> 회원 조회   </router-link>

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
    background-color: #42b883;
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
.toolbar{
    text-decoration: none;
}
</style>