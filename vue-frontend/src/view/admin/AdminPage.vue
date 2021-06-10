<template>
    <div>
        <SideNav></SideNav>
        <router-view></router-view>
    </div>
</template>

<script>
import SideNav from "@/view/admin/SideNav";

export default {
    components: {SideNav}
}
</script>

<style scoped>

</style>