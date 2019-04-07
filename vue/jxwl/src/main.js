import Vue from 'vue'
import './plugins/axios'
import App from './main.vue'
import './plugins/iview.js'
import router from './router'

Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
