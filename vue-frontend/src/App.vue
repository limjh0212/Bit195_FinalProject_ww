<template>
    <div>
        <ToolBar></ToolBar>
        <router-view></router-view>
    </div>
</template>

<script>
import ToolBar from "@/components/common/ToolBar";
export default {
    name: 'App',
    components: {ToolBar},
}
</script>

<style>

</style>
