<template>
    <div>

    </div>
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

<style lang="scss" scoped>
.md-progress-bar {
    position: absolute;
    top: 0;
    right: 0;
    left: 0;
}
</style>