<template>
    <div id="main-form" class="content-view">
        <div class="side-nav">
            <AdminSideNav></AdminSideNav>
        </div>
        <div class="side-content-view">
            <div id="wrapper">
                <div class="content-area">
                    <div class="container-fluid">
                        <div class="main">
                            <router-view></router-view>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import AdminSideNav from "@/components/member/AdminSideNav";

export default {
    components: {AdminSideNav},
    methods   : {},
}
</script>

<style scoped>
@import '../../css/common/mainP.css';

</style>