<template>
    <form @submit.prevent="submitForm">
        <div>
            <label for="id">ID :</label>
            <input id="id" v-model="id" type="text"/>
        </div>
        <div>
            <label for="pw">Password:</label>
            <input id="pw" v-model="pw" type="password"/>
        </div>
        <div>
            <label for="nickname">NickName:</label>
            <input id="nickname" v-model="nickname" type="text"/>
        </div>
        <div>
            <label for="email">Email:</label>
            <input id="email" v-model="email" type="text"/>
        </div>
        <button type="submit">회원가입</button>
    </form>
</template>

<script>
import {checkId, registerUser} from "@/api/auth";

export default {
    data() {
        return {
            id      : '',
            pw      : '',
            nickname: '',
            email   : '',
            role    : 'ROLE_USER',

            logMessage: '',
        }
    },
    methods: {

        async submitForm() {
            try {
                await console.log(checkId(this.id));
                const userData = {
                    id      : this.id,
                    pw      : this.pw,
                    nickname: this.nickname,
                    email   : this.email,
                    role    : this.role,
                };
                const {data} = await registerUser(userData);
                await this.$router.push('/login');
            } catch (error) {
                this.logMessage = error;
            }
        }
    }
}
</script>

<style scoped>

</style>