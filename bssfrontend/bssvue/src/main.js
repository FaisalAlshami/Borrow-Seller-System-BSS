import { createApp } from 'vue'
import App from './App.vue'

// npm install axios
import axios from 'axios'
// npm install --save vue-axios
import VueAxios from 'vue-axios'
// import VueAxios from 'vue-axios'


//vuecli2 use prototype to operate axios
// 原型链prototype 修改原型链  实现了全区可用$axios请求数据
// createApp.prototype.$axios=Axios


import router from './router/index.js'


import ElementPlus from 'element-plus'
import 'element-plus/lib/theme-chalk/index.css'

import store from './store/index'


let runOne=0;

router.beforeEach((to, from, next) => {

    console.log("************************router.beforeEach change url **********************")

    if(to.fullPath=="/" || to.fullPath=="/Login"){
        next();
        return;
    }

    let objstr= window.sessionStorage.getItem("userinfo")
    let userinfo= JSON.parse(objstr)


    if(userinfo==null){
            next({
                path: '/Login'
              });
              return ;
    }else{
        let userId=userinfo.userId;
        let targetUrl=to.fullPath;
        console.log(to)
        console.log(from)
        // userId  targetUrl

        axios.post("/getspringbootproxy/user/sys-role/verifyTargetUrl",
        {userId:userId,
        targetUrl:targetUrl})
        .then((res)=>{
            console.log(res)
            if(res.data.code==200){

                // add routes to router here
                if(runOne==0){
                    let routes=[
                    
                    ];
  
                  axios.post("/getspringbootproxy/user/sys-menu/getMenuRouters")
                   .then((re)=>{
                               console.log("****************************** getMenuRouters res:")
                               console.log(re)
                               routes=re.data.obj;
                               console.log("routes:"+routes)
                        // this.items=res.data.obj
                        routes = routes.filter(itemRouter =>  {
                        router.addRoute("Main", {
                          path: `${itemRouter.path}`,
                          name: itemRouter.name,
                          component: () => import(`@/${itemRouter.component}`)
                        });
                       console.log({
                          path: `${itemRouter.path}`,
                          name: itemRouter.name,
                          component: () => import(`@/${itemRouter.component}`)
                        })
                        runOne++;
                        next({...to, replace: true})
                    })
                   })
  
                }else{
                    next();
                }
               
               return ;
            }else{
                next({
                    path: '/main'
                  });
                  return ;
            }
    
        })

      
       

        // next();
    }
})





// const app=
const app = createApp(App)
app.use(VueAxios, axios)
app.use(router)
app.use(ElementPlus)
app.use(store)
app.mount('#app')
// createApp(App).mount('#app')
