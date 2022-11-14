<template>
  <el-container  class="dvbody">
    
    <el-container>
    <el-header class="el-header"><Topbar/> </el-header>
      
      <el-container>
       <el-aside class="leftbackground" style="width:16%;"><NavMenuLeft /></el-aside>
        <el-main class="main-contain">
       
        <router-view class="main-router"></router-view>
        </el-main>
      
      </el-container>
     
    </el-container>

  </el-container>


 
</template>

<script>
    import NavMenuLeft from './common/NavMenuLeft'
    import { useStore } from "vuex";
    import Topbar from './common/Topbar'
export default {
    name:"Main",
    components:{
        NavMenuLeft,Topbar
    },
    data(){
        return{
            username:"",
            isCollapse: true,
            leftMenuWidth:4, 
            rigthContentWidth:20
        }
    },
    methods: {
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      },
      changeLeftRightWidth(){
          this.leftMenuWidth=2,
        this.rigthContentWidth=22;
      },

    },
    created(){    
      
       let objstr= window.sessionStorage.getItem("userinfo")
       let userinfo= JSON.parse(objstr)
        console.log(userinfo);
        console.log(userinfo.username);
        this.username=userinfo.username;
         const store = useStore();
        this.isCollapse = store.state.isCollapse
        console.log("Main.vue  created  vuex store.state.isCollapse  this.isCollapse:"+this.isCollapse)
          if( this.isCollapse ){ //click expend  false
               this.leftMenuWidth=2,
                this.rigthContentWidth=22;
            }else{
                this.leftMenuWidth=4,
                this.rigthContentWidth=20;
            }

    




    } ,computed:{
        changeisCollapse(){
                // monitor vuex state isCollapse changed
            return this.$store.state.isCollapse;
        }
    },watch:{
        changeisCollapse(newval){
            // put newval to vuex  we use mutations ->changeisCollapse->
             console.log("main.vue watch changeisCollapse newval:"+newval)

            if(newval){ //click expend  false
               this.leftMenuWidth=2,
                this.rigthContentWidth=22;
            }else{
                this.leftMenuWidth=4,
                this.rigthContentWidth=20;
            }


        }

    }


}
</script>

<style scoped>
html {
  min-height: 100%;
  
}

body {
  min-height: 100vh;
}
.dvbody{
box-sizing: border-box;
box-sizing: content-box;

/* Global values */
box-sizing: inherit;
box-sizing: initial;
box-sizing: revert;
box-sizing: unset;
background-color:black;

}
.el-header{
        /* background-color: #B3C0D1; */
        /* color: #333;
        text-align: center;
        line-height: 60px; */
        background-color:#4863A0; 
    }
    .el-footer {
        /* background-color: #B3C0D1; */
        /* color: #333;
        text-align: center;
        line-height: 60px; */
    }

    .el-aside {
        color: #333;
        text-align: center;
        line-height: 200px;
        min-height:400px;
    }

    .el-main {
        /* background-color: #E9EEF3;
        color: #333;
        text-align: center;
        line-height: 160px; */
    }
   

    .el-row {
    margin-bottom: 20px;
    &:last-child {
      margin-bottom: 0;
    }
  }
  .el-col {
    border-radius: 4px;
  }
  .bg-purple-dark {
    background: #99a9bf;
  }
  .bg-purple {
    background: #d3dce6;
  }
  .bg-purple-light {
    background: #e5e9f2;
  }
  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }
  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  }
  .main-contain{
   background-color:#DCDCDC;
   margin:0px 0px 0px 0px;
   
  }
   .main-contain .main-router{
    background-color:white;
    padding: 20px 20px 20px 20px;
    padding-bottom:50px;
   }
   .main-contain .main-router template{
    background-color:white;
   }
    .leftbackground1{
    background-image: url('~@/assets/leftmenubkcolor.jpg');  
    background-repeat: repeat;
   }
   .leftbackground{
  background-color: #abcdef;
}
</style>