<template>
    <div>
        <p>userview</p>
        <p>{{ item }}</p>

    </div>
</template>

<script>
import axios from "axios";

export default {
    name    : "UserView",
    computed: {
        item() {
            return this.$store.state.user
        }
    },
    created() {
        axios
        const userId = this.$route.params.id;
        console.log(userId)
        this.$store.dispatch('FETCH_USER', userId)
    }
}
</script>

<style scoped>

</style>