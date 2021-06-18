import Vue from 'vue'
import App from './App.vue'
import {router} from "@/router";
import {store} from "@/store";
import {Editor} from '@/utils/editor'
import {moment} from "@/utils/moment";
import vuetify from "@/utils/vuetify";

Vue.config.productionTip = false

new Vue({
    render: h => h(App),
    router,
    store,
    Editor,
    moment,
    vuetify,

}).$mount('#app')