<template>
    <form @submit.prevent="submitForm">

            <div>
                <label for="id">ID : </label>
                <input type="text" id="id" placeholder="ID" v-model="id" autofocus/>
            </div>

            <div>
                <label for="pw">Password : </label>
                <input type="password" id="pw" placeholder="Password" v-model="pw"/>
            </div>

        <div>
            <a href="#">Forgot your password?</a>
        </div>
        <button type="submit"> Log in</button>
        <p>{{ logMessage }}</p>
    </form>
</template>


<script>
export default {
    data() {
        return {
            id        : '',
            pw        : '',
            logMessage: '',
        };
    },
    computed: {},
    methods : {
        async submitForm() {
            try {
                const userData = {
                    id: this.id,
                    pw: this.pw,
                };
                await this.$store.dispatch('LOGIN', userData);
                await this.$router.push('/main');
            } catch (error) {
                this.logMessage = '로그인에 실패했습니다. 다시 시도해주세요.';
            } finally {
                this.initForm();
            }
        },
        initForm() {
            this.pw = '';
        },
    },
}
</script>

<style scoped>

</style>