import Vue from 'vue'
import App from './App.vue'
import {router} from "@/router";
import {store} from "@/store";
import {formatDate} from '@/utils/filters';
import {Editor} from '@/utils/editor'

Vue.filter('formatDate', formatDate);
Vue.config.productionTip = false

new Vue({
    render: h => h(App),
    router,
    store,
    Editor,
}).$mount('#app')