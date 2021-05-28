<template>
<!--    <div class="contents">-->
<!--        <div class="form-wrapper form-wrapper-sm">-->
<!--            <form class="form" @submit.prevent="submitForm">-->
<!--                <div>-->
<!--                    <label for="username">id:</label>-->
<!--                    <input id="username" v-model="username" type="text"/>-->
<!--                    <p class="validation-text">-->
<!--                        &lt;!&ndash;            <span v-if="!isUsernameValid && username" class="warning">&ndash;&gt;-->
<!--                        &lt;!&ndash;              Please enter an email address&ndash;&gt;-->
<!--                        &lt;!&ndash;            </span>&ndash;&gt;-->
<!--                    </p>-->
<!--                </div>-->
<!--                <div>-->
<!--                    <label for="password">pw:</label>-->
<!--                    <input id="password" v-model="password" type="text"/>-->
<!--                </div>-->
<!--                <button class="btn" type="submit">로그인</button>-->
<!--                &lt;!&ndash;  :disabled="!isUsernameValid || !password"&ndash;&gt;-->
<!--            </form>-->
<!--            <p class="log">{{ logMessage }}</p>-->
<!--        </div>-->
<!--    </div>-->

    <div class="wrapper">
        <form class="login">
            <p class="title">Log in</p>
            <input type="text" placeholder="Username" v-model="username" autofocus/>
            <i class="fa fa-user"></i>
            <input type="password" v-model="password" placeholder="Password"/>
            <i class="fa fa-key"></i>
            <a href="#">Forgot your password?</a>
            <button>
                <i class="spinner"></i>
                <span class="state">Log in</span>
            </button>
        </form>
        <!--        <footer><a target="blank" href="http://boudra.me/">boudra.me</a></footer>-->

    </div>
</template>

<script>
import {validateEmail} from '@/utils/validation';

export default {
    data() {
        return {
            // form values
            username: '',
            password: '',
            // log
            logMessage: '',
        };
    },
    computed: {
        isUsernameValid() {
            return validateEmail(this.username);
        },
    },
    methods : {
        async submitForm() {
            try {
                // 비즈니스 로직
                const userData = {
                    username: this.username,
                    password: this.password,
                };
                await this.$store.dispatch('LOGIN', userData);
                this.$router.push('/');
            } catch (error) {
                // 에러 핸들링할 코드
                console.log(error.response.data);
                this.logMessage = error.response.data;
            } finally {
                this.initForm();
            }
        },
        initForm() {
            this.username = '';
            this.password = '';
        },
    },
};
</script>

<style>
.btn {
    color: white;
}
</style>
