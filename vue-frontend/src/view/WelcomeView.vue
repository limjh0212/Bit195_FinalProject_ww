<template>
    <div>
        <h1>Welcome!!!</h1>
        <router-link to="/login">Log in</router-link>
        |
        <router-link to="/Signup">Sign up</router-link>
      <MainForm></MainForm>
    </div>
</template>

<script>
import MainForm from "@/components/MainForm";

export default {
    name: "WelcomeView",
    components: {MainForm},
    methods:{
    },
}
</script>

<style scoped>

</style>