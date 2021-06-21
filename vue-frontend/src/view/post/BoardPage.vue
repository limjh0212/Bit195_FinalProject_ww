<template>
    <div>
        <BoardSubNav></BoardSubNav>
        <router-view></router-view>
    </div>
</template>

<script>

import BoardSubNav from "@/components/post/BoardSubNav";

export default {
    components: {BoardSubNav}
}
</script>

<style scoped>

</style>