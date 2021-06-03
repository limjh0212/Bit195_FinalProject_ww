<template>
    <div>
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
    </div>
</template>

<script>
import {signupMember} from "@/api";

export default {
    data() {
        return {
            id      : '',
            email   : '',
            pw      : '',
            nickname: '',
        }
    },
    methods: {
        async submitForm() {
            const response = await signupMember({
                id      : this.id,
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