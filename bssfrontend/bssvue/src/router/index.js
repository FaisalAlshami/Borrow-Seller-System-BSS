
// npm install vue-router@next
import  {createRouter, createWebHistory} from 'vue-router'
const routerHistory = createWebHistory()


import Hello1 from '../components/Hello1.vue'
import HelloWorld from '../components/HelloWorld.vue'
import Login from '../components/Login.vue'
import Main from '../components/Main.vue'

import Home from '../components/page/Home.vue'
import Company from '../components/page/MVO/Company.vue'
import Goods from '../components/page/MVO/Goods.vue'
import Order from '../components/page/MVO/Order.vue'
import Wallet from '../components/page/MVO/Wallet.vue'
import Store from '../components/page/BVO/Store.vue'
import GoodsList from '../components/page/BVO/GoodsList.vue'
import WishList from '../components/page/BVO/WishList.vue'
import Menu from '../components/page/Admin/Menu.vue'
import User from '../components/page/Admin/User.vue'
import Param from '../components/page/Admin/Param.vue'
import Data from '../components/page/Admin/Data.vue'
import Check from '../components/page/Admin/Check.vue'
import My from '../components/My.vue'
const routes = [
    {
        path: '/',
        redirect: '/Login'
      },
      // {
      //   path: '/Main',
      //   name: 'Main',
      //   component: Main,
      //   // hidden : true
      // }

      {
        path: '/main',
        component:Main,
        name: 'Main',
        children:[
          {
            path: '/main',
            component: Home
          },
          
        ]
      },


,
    {
        path: '/Login',
        name: 'Login',
        component: Login,
        // hidden : true
      }
,
    {
      path: '/Hello1',
      name: 'Hello1',
      component: Hello1,
      // hidden : true
    },
    {
      path: '/HelloWorld',
      name: 'HelloWorld',
      component: HelloWorld,
      // hidden : true
    }
  ];

const router = createRouter({
    history: routerHistory,
    routes
  })


  export default router;