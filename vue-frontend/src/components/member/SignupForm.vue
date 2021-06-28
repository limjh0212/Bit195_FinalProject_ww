<template>
    <div class="content-view" id="login-page" style="width: 400px">
        <div class="col s12 z-depth-4 card-panel">
            <!--Form-->
            <form class="login-form">
                <div class="input-field col s12 center">
                    <h5>SignUp</h5>
                    <p class="center">Join to our community now!</p>
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

                    <!--Nickname-->
                    <div class="row margin">
                        <div class="input-field col s12">
                            <i class="material-icons prefix">face</i>
                            <input id="nickname" name="nickname" v-model="nickname" type="text"/>
                            <label for="pw">Nickname</label>
                        </div>
                    </div>

                    <!--email-->
                    <div class="row margin">
                        <div class="input-field col s12">
                            <i class="material-icons prefix">mail</i>
                            <input id="email" name="email" v-model="email" type="text"/>
                            <label for="email">Email</label>
                        </div>
                    </div>
                </div>
                <p style="height: 24px; font-size:11px; color: red">
                    {{ logMessage }}
                </p>

                <div class="row" style="margin-top: 0;margin-bottom: 0; ">
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
import {api_checkId, api_registerUser} from "@/api/auth";

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
        //Cancel 버튼
        goBack() {
            return this.$router.push('/')
        },

        //Submit 버튼
        async submitForm() {
            try {
                const {data} = await (api_checkId(this.id));
                console.log(data);
                const userData = {
                    id      : this.id,
                    pw      : this.pw,
                    nickname: this.nickname,
                    email   : this.email,
                    role    : this.role,
                };
                await api_registerUser(userData);
                await this.$router.push('/login');
            } catch (error) {
                this.logMessage = "입력하신 내용을 다시 확인바랍니다.";
                this.pw = ''
            }
        }
    }
}
</script>