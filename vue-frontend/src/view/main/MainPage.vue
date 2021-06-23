<template>
    <div id="main-form">
    <div v-if="this.$store.state.role === 'ROLE_USER'" class="content-view">
          <WeatherForm></WeatherForm>
    </div>
    <div v-if="isAdmin" class="content-view">
      <div class="side-nav">
          <AdminSideNav></AdminSideNav>
      </div>
      <div class="side-content-view">
          <router-view></router-view>
      </div>
    </div>
    </div>
</template>

<script>
import WeatherForm from "@/components/weather/WeatherForm";
import AdminSideNav from "@/components/member/AdminSideNav";
export default {
  components: {AdminSideNav,WeatherForm},
  computed: {
    isAdmin() {
      return this.$store.getters.isAdmin;
    }
  },
}
</script>

<style scoped>
@import '../../css/common/mainP.css';
</style>