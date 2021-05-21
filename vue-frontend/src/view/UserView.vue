<template>
    <div>
        userview
        {{ item }}
    </div>
</template>

<script>
export default {
    name    : "UserView",
    computed: {
        item() {
            return this.$store.state.user
        }
    },
    created() {
        const userId = this.$route.params.id;
        console.log(userId);
        this.$store.dispatch('FETCH_USER', userId)
    }
}
</script>

<style scoped>

</style>