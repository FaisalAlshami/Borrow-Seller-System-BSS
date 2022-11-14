
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
import Brand from '../components/page/MVO/Brand.vue'
import Order from '../components/page/MVO/Order.vue'
import BVOOrder from '../components/page/BVO/Order.vue'
import Wallet from '../components/page/MVO/Wallet.vue'
import Store from '../components/page/BVO/Store.vue'
import GoodsList from '../components/page/BVO/GoodsList.vue'
import Cart from '../components/page/BVO/Cart.vue'
import WishList from '../components/page/BVO/WishList.vue'
import Menu from '../components/page/Admin/Menu.vue'
import User from '../components/page/Admin/User.vue'
import Param from '../components/page/Admin/Param.vue'
import Data from '../components/page/Admin/Data.vue'
import Check from '../components/page/Admin/Check.vue'
import My from '../components/My.vue'
import Promise from '../components/PromiseTest.vue'
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
        children:[
          {
            path: '/my',
            component: My
          },
          {
            path: '/main',
            component: Home
          },
          {
            path: '/company',
            component:Company
          },
          {
            path: '/goods',
            component:Goods
          },
          
          {
            path: '/MVO/order',
            component: Order
          },
          {
            path: '/BVO/order',
            component: BVOOrder
          },
          {
            path: '/MVO/wallet',
            component:Wallet
          },
          {
            path: '/MVO/Brand',
            component:Brand
          },
          {
            path: '/BVO/order',
            component: Order
          },
          {
            path: '/BVO/wallet',
            component: Wallet
          },
          {
            path: '/store',
            component: Store
          },
          {
            path: '/goodslist',
            component: GoodsList
          },
          {
            path: '/cart',
            component: Cart
          },
          {
            path: '/wishlist',
            component: WishList
          },
          {
            path: '/menu',
            component: Menu
          },
          {
            path: '/user',
            component: User
          },
          {
            path: '/param',
            component: Param
          },
          {
            path: '/data',
            component: Data
          },
          {
            path: '/check',
            component: Check
          },
          {
            path: 'PromiseTest',
            component: Promise
          }
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