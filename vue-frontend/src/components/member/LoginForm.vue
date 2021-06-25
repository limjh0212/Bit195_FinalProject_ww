<template>
    <div class="row" id="login-page" style="width: 400px">
        <div class="col s12 z-depth-4 card-panel">
            <!--Form-->
            <form class="login-form">
                <div class="input-field col s12 center">
                    <h5>Login</h5>
                    <p class="center">Login our community now!</p>
                </div>
                <div style="width:100%; margin: auto">
                    <!--ID-->
                    <div class="row margin">
                        <div class="input-field col s12">
                            <i class="material-icons prefix">account_circle</i>
                            <input id="id" name="id" v-model="id" type="text"/>
                            <label for="id">ID</label>
                        </div>
                    </div>

                    <!--PW-->
                    <div class="row margin">
                        <div class="input-field col s12">
                            <i class="material-icons prefix">vpn_key</i>
                            <input id="pw" name="pw" v-model="pw" type="password"/>
                            <label for="pw">Password</label>
                        </div>
                    </div>
                </div>
                <p style="height: 24px; font-size:11px; color: red">
                    {{ logMessage }}
                </p>

                <div class="row" style="margin-top: 0px;margin-bottom: 0px; ">
                    <!--Submit 버튼-->
                    <div class="input-field col s12">
                        <button class="btn waves-effect waves-light col s12" @click.prevent="submitForm">Submit</button>
                    </div>

                    <!--Cancel 버튼-->
                    <div class="input-field col s12">
                        <button class="btn waves-effect waves-light col s12" @click.prevent="goBack">Cancel
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
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
    methods: {
        //Cancel 버튼
        goBack() {
            return this.$router.push('/')
        },
        //Submit 버튼
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
                this.pw = '';
            }
        },
    },
}
</script>
