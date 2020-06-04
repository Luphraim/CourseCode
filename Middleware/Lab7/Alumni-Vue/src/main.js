// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import VueRouter from 'vue-router'
import axios from 'axios'
import swal from 'sweetalert2'
import qs from 'qs'
import VCharts from 'v-charts'

import store from './store/index'

Vue.prototype.$axios = axios
Vue.prototype.$swal = swal
Vue.prototype.$qs = qs

Vue.use(VueRouter)
Vue.use(VCharts)
// Vue.component(VeLine.name, VeLine)
// Vue.component(VePie.name, VePie)

Vue.config.productionTip = false

axios.defaults.headers.common['authorization'] = store.state.token

/* eslint-disable no-new */
new Vue({
  el: '#app',
  render: h => h(App),
  store,
  router,
  components: { App },
  template: '<App/>',
  computed: {
    getToken () {
      return store.state.token
    }
  },
  watch: {
    getToken (cur, old) {
      axios.defaults.headers.common['authorization'] = cur
    }
  }
})
