<template>
  <div id="main-form" class="content-view">
    <div class="side-nav">
      <AdminSideNav></AdminSideNav>
    </div>
    <div class="side-content-view">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
import AdminSideNav from "@/components/member/AdminSideNav";

export default {
  components: {AdminSideNav},

}
</script>

<style scoped>
@import '../../css/common/mainP.css';
</style>