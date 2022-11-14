<template>
<div class="topbar-container">
    <div class="user">
        
            <el-dropdown trigger="click"  @command="handleCommand">
                <el-avatar :src="imgSrc" style="width:50px; height:50px; margin-top:3px;"></el-avatar>
          
                <template #dropdown>
                    <el-dropdown-menu>
                        <el-dropdown-item disabled icon="el-icon-s-custom">{{nickname}}</el-dropdown-item>
                        <el-dropdown-item command="my">My Info</el-dropdown-item>
                        <el-dropdown-item command="Login">Log out</el-dropdown-item>
                    </el-dropdown-menu>
                  
                </template>
               
            </el-dropdown>
            
    </div>

    <div class="logo">
       <!--  <el-avatar :src="logoimg" class="logo1"></el-avatar>   
        -->
         <span style="color:white; font-size:14pt;">Borrow<span style="color:red;">-</span>Seller 
         <span style="color:white;">System</span> <span style="color:#F5DEB3;">BSS</span></span>
     
    </div>
 
  


</div>    

</template>

<script>

export default {
    name:"Topbar",

    data(){
        return{
             nickname:"" ,
             imgSrc:""  ,
             logoimg:require('@/assets/logo.png'),
        }
    },
    methods:{
        handleCommand(command) {
            console.log("****************handleCommand")
            this.$router.push(command)
            if(command=="Login"){
                window.sessionStorage.clear();
            }
       }
    },
     handleSelect(key, keyPath) {
        console.log(key, keyPath);
      },


    created(){
        let objstr= window.sessionStorage.getItem("userinfo")
        let userinfo= JSON.parse(objstr)
        console.log(userinfo)
        this.nickname=userinfo.name;
        this.imgSrc=userinfo.imgUrl;



    }

}
</script>

<style scoped>
.topbar-container {
  height: 56px;
  top:0;
  left:0;
  
}
.btn {
  display: inline-block;
  padding: 10px 20px;
  color: white;
}
.user {
  position: absolute;
  right: 50px;
  top: 10px;
  display: inline-block;
}
.logo{
  width:300px;
  heigh:30px;
  margin-top:10px;
  left:0px;
  font-size:18pt;

  
}

.logo1{
 right: 0; 
 width: 300px; 
 height: 30px;
 opacity: 0.5;
 float:left; 
}
</style>