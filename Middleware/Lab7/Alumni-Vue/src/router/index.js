import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import login from '@/components/login'
import index from '@/components/index'
import alumni from '@/components/alumni'
import alumniAdd from '@/components/alumniAdd'
import alumniInfo from '@/components/alumniInfo'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/h',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/login',
      name: 'login',
      components: {
        login: login
      }
    },
    {
      path: '/',
      name: 'index',
      component: index
    },
    {
      path: '/alumni',
      name: 'alumni',
      component: alumni
    },
    {
      path: '/alumni/add',
      name: 'alumniAdd',
      component: alumniAdd
    },
    {
      path: '/alumni/info',
      name: 'alumniInfo',
      component: alumniInfo
    }
  ]
})
