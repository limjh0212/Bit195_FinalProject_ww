<template>
    <div>
        <form @submit.prevent="submitForm"></form>
        <div>
            <label for="email">Email:</label>
            <input id="email" v-model="email" type="text"/>
        </div>
        <div>
            <label for="pw">Password:</label>
            <input id="pw" v-model="pw" type="password"/>
        </div>
        <div>
            <label for="nickname">NickName:</label>
            <input id="nickname" v-model="nickname" type="text"/>
        </div>
        <button type="submit">회원가입</button>
    </div>
</template>

<script>
import {signupMember} from "@/api";

export default {
    data() {
        return {
            email   : '',
            pw      : '',
            nickname: '',
        }
    },
    methods: {
        async submitForm() {
            const response = await signupMember({
                email   : this.email,
                pw      : this.pw,
                nickname: this.nickname,
            });
            await this.$router.push('/login');
        }
    },
}
</script>

<style scoped>

</style>