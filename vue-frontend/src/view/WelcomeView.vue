<template>
    <div>
        <h1>Welcome!!!</h1>
        <router-link to="/login">Log in</router-link>
        |
        <router-link to="/Signup">Sign up</router-link>
    </div>

</template>

<script>


import SearchLocation from "@/components/weather/SearchLocation";

export default {
    components: {SearchLocation},
    methods   : {},
}
</script>

<style scoped>

</style>