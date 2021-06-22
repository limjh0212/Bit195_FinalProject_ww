<template>
    <div>
      <div>
        <BoardSubNav></BoardSubNav>
      </div>
        <div class="router-view">
          <router-view></router-view>
        </div>
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