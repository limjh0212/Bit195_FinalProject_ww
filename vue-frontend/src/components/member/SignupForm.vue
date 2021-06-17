<template>
    <div>
        <form novalidate class="md-layout" @submit.prevent="validateUser">
            <md-card class="md-layout-item md-size-50 md-small-size-100">
                <md-card-header>
                    <div class="md-title">Users</div>
                </md-card-header>

                <md-card-content>
                    <div class="md-layout md-gutter">
                        <div class="md-layout-item md-small-size-100">
                            <md-field :class="getValidationClass('firstName')">
                                <label for="first-name">First Name</label>
                                <md-input name="first-name" id="first-name" autocomplete="given-name" v-model="form.firstName" :disabled="sending" />
                                <span class="md-error" v-if="!$v.form.firstName.required">The first name is required</span>
                                <span class="md-error" v-else-if="!$v.form.firstName.minlength">Invalid first name</span>
                            </md-field>
                        </div>

                        <div class="md-layout-item md-small-size-100">
                            <md-field :class="getValidationClass('lastName')">
                                <label for="last-name">Last Name</label>
                                <md-input name="last-name" id="last-name" autocomplete="family-name" v-model="form.lastName" :disabled="sending" />
                                <span class="md-error" v-if="!$v.form.lastName.required">The last name is required</span>
                                <span class="md-error" v-else-if="!$v.form.lastName.minlength">Invalid last name</span>
                            </md-field>
                        </div>
                    </div>

                    <div class="md-layout md-gutter">
                        <div class="md-layout-item md-small-size-100">
                            <md-field :class="getValidationClass('gender')">
                                <label for="gender">Gender</label>
                                <md-select name="gender" id="gender" v-model="form.gender" md-dense :disabled="sending">
                                    <md-option></md-option>
                                    <md-option value="M">M</md-option>
                                    <md-option value="F">F</md-option>
                                </md-select>
                                <span class="md-error">The gender is required</span>
                            </md-field>
                        </div>

                        <div class="md-layout-item md-small-size-100">
                            <md-field :class="getValidationClass('age')">
                                <label for="age">Age</label>
                                <md-input type="number" id="age" name="age" autocomplete="age" v-model="form.age" :disabled="sending" />
                                <span class="md-error" v-if="!$v.form.age.required">The age is required</span>
                                <span class="md-error" v-else-if="!$v.form.age.maxlength">Invalid age</span>
                            </md-field>
                        </div>
                    </div>

                    <md-field :class="getValidationClass('email')">
                        <label for="email">Email</label>
                        <md-input type="email" name="email" id="email" autocomplete="email" v-model="form.email" :disabled="sending" />
                        <span class="md-error" v-if="!$v.form.email.required">The email is required</span>
                        <span class="md-error" v-else-if="!$v.form.email.email">Invalid email</span>
                    </md-field>
                </md-card-content>

                <md-progress-bar md-mode="indeterminate" v-if="sending" />

                <md-card-actions>
                    <md-button type="submit" class="md-primary" :disabled="sending">Create user</md-button>
                </md-card-actions>
            </md-card>

            <md-snackbar :md-active.sync="userSaved">The user {{ lastUser }} was saved with success!</md-snackbar>
        </form>
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