import Vue from 'vue'
import App from './App.vue'
import {router} from "@/router";
import {store} from "@/store";
import {formatDate} from '@/utils/filters';

//element-tiptap
import ElementUI from 'element-ui';
import {ElementTiptapPlugin} from 'element-tiptap';
import 'element-tiptap/lib/index.css';

Vue.use(ElementUI);
Vue.use(ElementTiptapPlugin, {});

//Vuetify
import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.min.css'

Vue.use(Vuetify);

Vue.filter('formatDate', formatDate);
Vue.config.productionTip = false

new Vue({
    render: h => h(App),
    router,
    store,
}).$mount('#app')