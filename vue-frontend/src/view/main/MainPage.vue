<template>
  <div>
  <div v-if="this.$store.state.role === 'ROLE_USER'">
    <v-container>
      <WeatherForm></WeatherForm>
    </v-container>
  </div>
  <div v-if="isAdmin">
    <div>
    <AdminSideNav></AdminSideNav>
    </div>
    <div>
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

</style>