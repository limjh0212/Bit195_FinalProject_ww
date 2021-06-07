import Vue from 'vue';
import ElementUI from 'element-ui';
import { ElementTiptapPlugin } from 'element-tiptap';
// import ElementUI's styles
import 'element-ui/lib/theme-chalk/index.css';
// import this package's styles
import 'element-tiptap/lib/index.css';

// use ElementUI's plugin
Vue.use(ElementUI);
// use this package's plugin
Vue.use(ElementTiptapPlugin, { /* plugin options */ });
// Now you register `'el-tiptap'` component globally.