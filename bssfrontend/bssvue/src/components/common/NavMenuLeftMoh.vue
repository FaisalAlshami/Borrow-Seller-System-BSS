<template>

                       
                <el-menu
                    default-active="2"
                    :default-active="$route.path"
                    class="el-menu-vertical-demo navDiv"
                    router
                    @open="handleOpen"
                    @close="handleClose"
                    background-color=white
                    text-color="#000"
                    active-text-color="#CD853F" 
                >

                <!-- got submenu -->
                <template v-for="item in items" >
                    <template v-if="item.subMenuList!=undefined && item.subMenuList.length>0" >
                        <el-submenu :index="item.menuUrl" :key="item.menuUrl"  >
                        <template #title >
                   
                            <i :class="item.menuIcon"  ></i>
                        <span slot="title">{{item.menuName}}</span>
                   
                        </template>
                        
                        <el-menu-item  
                            v-for="sub in item.subMenuList"
                            :index="sub.menuUrl"
                            :key="sub.menuName"
                            >
                             <i :class="sub.menuIcon"></i>
                        <span slot="title">{{sub.menuName}}</span>
                           
                            </el-menu-item>
                        </el-submenu>
                    </template>
                    <!--  not got submenu -->
                    <template v-else>
                    <el-menu-item :index="item.menuUrl" :key="item.menuUrl" >
                        <i :class="item.menuIcon"></i>
                        <span>{{ item.menuName }}</span>
                    </el-menu-item>
                    </template>
                </template>
                </el-menu>





</template>

<script>
import { computed } from 'vue';
export default {
    name:"NavMenuLeft",
    data(){
        return{
            username:"",
            isCollapse: true,
            items: [
                {
                menuIcon: "el-icon-s-home",
                menuUrl: "/main",
                menuName: "Home"
                },
                {
                menuIcon: "el-icon-s-tools",
                menuName: "Management",
                menuUrl: "1",
                subMenuList: [
                    {
                    menuIcon: "el-icon-notebook-2",
                    menuUrl: "/menu",
                    menuName: "Menu manage"
                    },
                    {
                    menuIcon: "el-icon-user",
                    menuUrl: "/user",
                    menuName: "User manage"
                    },
                    {
                    menuIcon: "el-icon-notebook-1",
                    menuUrl: "/param",
                    menuName: "Parameter manage"
                    },
                    {
                    menuIcon: "el-icon-collection",
                    menuUrl: "/data",
                    menuName: "Data dictionary"
                    },
                    {
                    menuIcon: "el-icon-document-checked",
                    menuUrl: "/check",
                    menuName: "Fund check"
                    }
                ]
                },
                {
                menuIcon: "el-icon-office-building",
                menuName: "MVO",
                menuUrl: "2",
                subMenuList: [
                    {
                    menuIcon: "el-icon-goods",
                    menuUrl: "/goods",
                    menuName: "Goods manage"
                    },
                    {
                    menuIcon: "el-icon-s-order",
                    menuUrl: "/MVO/order",
                    menuName: "Order manage"
                    },
                    
                ]
                },
                {
                menuIcon: "el-icon-s-shop",
                menuName: "BVO",
                menuUrl: "3",
                subMenuList: [
                
                    {
                    menuIcon: "el-icon-goods",
                    menuUrl: "/goodslist",
                    menuName: "Goods list"
                    },
                    {
                    menuIcon: "el-icon-goods",
                    menuUrl: "/Cart",
                    menuName: "Cart"
                    },
                    {
                    menuIcon: "el-icon-star-off",
                    menuUrl: "/wishlist",
                    menuName: "Wish list"
                    },
                    {
                    menuIcon: "el-icon-s-order",
                    menuUrl: "/BVO/order",
                    menuName: "Order manage"
                    },
                    
                ]
                }
            ]
        }
    },
    methods:{
        handleOpen(key, keyPath) {
            console.log(key, keyPath);
        },
        handleClose(key, keyPath) {
            console.log(key, keyPath);
        },
        changeCollapse(msg){
            console.log("msg:"+msg)
            this.isCollapse=msg
            this.$store.commit('changeisCollapse', msg);
        }
   

    },created(){

          // use axios to send request  to back-end
    //   this.axios.get("/getspringbootproxy/user/sys-menu/getAllMenuList")
    //                         .then((res)=>{
    //                             console.log(res)
    //             this.items=res.data.obj
    //    })
        let objstr= window.sessionStorage.getItem("userinfo")
       let userinfo= JSON.parse(objstr)
        console.log(userinfo);
        console.log(userinfo.username);
        this.username=userinfo.username;
        console.log( this.username)
        this.axios.post("/getspringbootproxy/user/sys-menu/getMenuListByUserName",{username:this.username})
                            .then((res)=>{
                                console.log(res)
                this.items=res.data.obj
       })
    }


}
</script>

<style scoped>
 .el-menu-vertical-demo {
    float:left;
    text-align:left;
    }
 .navDiv{
        min-height: 1700px;
        width: 100%;
        position: relative;
        z-index: 1;
        top: 0;
        left: 10;
        overflow-x: hidden;
        transition: 0.5s;
        display: block;  
        background-color: #D3DCE6;  
         float:right;
     
     
    }
.submenudiv{

 float:left;
  position: relative;
   display: block;  
   width:100%;
 
}

</style>