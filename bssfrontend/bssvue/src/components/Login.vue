<template>
    <div id="page" class="login">

 <video  no-controls autoplay loop class="bannerImg">
 
    </video>
        <div v-show="showLogin"  >
           <el-form
        :rules="rules"
        class="login-container"
        label-position="center"
        :model="loginForm"
        label-width="0px"
        v-loading="loading"
      >
     
         
            <!-- <div> -->
               <h2>System Login </h2>     
            
              <el-form-item prop="username">
                <el-input 
                placeholder="Please input your username" 
                v-model="username"
                
                prefix-icon="el-icon-user-solid"
                ></el-input>
              </el-form-item>
           
          <el-form-item prop="password">
                <el-input placeholder="Please input your  password" 
                v-model="password"
                
                 show-password
                 prefix-icon="el-icon-lock"
                 >
                </el-input>
          </el-form-item>
      <div>
          <el-input
            style="width:18rem"
            class="input"
            v-model="checkCode"
            placeholder="Please enter the code."
            prefix-icon="el-icon-edit"
          ></el-input>
          <el-button
            style="width:5.5rem;height: 50px"
            type="danger"
            plain
            @click="createCode"
          >{{Code}}</el-button>
        </div>
        
<br/>
       
                  
                <el-button class="btn" type="danger" @click="login">Login</el-button>
               
              

                	<el-link
			    href="javascript:void(0)"
				class="register"
				type="danger"
				:underline="false"
				@click="change"
				icon="el-icon-right"
				>register</el-link>
        </el-form>
        </div>
        <!-- </div> -->
 
            <div v-show="showRegister"  >
              <el-form
        :rules="rules"
        class="login-container"
        label-position="left"
        :model="loginForm"
        label-width="0px"
        v-loading="loading"
      >
          	<h2 class="title"> Register </h2>
        
        <el-form-item prop="username">
              	<el-input
			
				class="input"
				v-model="username"
				placeholder="Please enter the user name."
				prefix-icon="el-icon-user-solid"
				></el-input>
          </el-form-item>
         
            <el-form-item prop="nickname">
				<el-input
			
				class="input"
				v-model="nickname"
				placeholder="Please enter the nick name."
				prefix-icon="el-icon-user"
				></el-input>
            </el-form-item>
        <el-form-item prop="password">
				<el-input
				
				class="input"
				v-model="password"
				placeholder="Please enter your password."
				prefix-icon="el-icon-lock"
				show-password
				></el-input>
        </el-form-item>
        <el-form-item prop="passwordSure">
				<el-input
			
				class="input"
				v-model="passwordSure"
				placeholder="Please enter password again."
				prefix-icon="el-icon-lock"
				show-password
				></el-input>
        </el-form-item>

        <el-form-item prop="phone">
				<el-input
				
				class="input"
				v-model="phone"
				placeholder="Please enter your phone."
				prefix-icon="el-icon-phone"
				></el-input>
        </el-form-item>
         <el-form-item prop="email">
				<el-input
				
				class="input"
				v-model="email"
				placeholder="Please enter your e-mail."
				prefix-icon="el-icon-message"
				></el-input>
         </el-form-item>
      <el-form-item prop="role">
				<div class="radio">
				<el-radio v-model="roleId" label="2" >Brand-seller</el-radio>
				<el-radio v-model="roleId" label="3">Borrow-seller</el-radio>
				</div>
      </el-form-item>
				<el-button class="btn" type="danger" @click="register">Register</el-button>
				<el-link
				href="javascript:void(0)"
				class="register"
				type="danger"
				:underline="false"
				@click="change"
				icon="el-icon-back"
				>login</el-link>
              </el-form>
        </div>
    
    <myFooter id="loginFoot" />
    </div>   
</template>

<script>

export default{
 

    name:"Login",
    data(){
        return{
            username:"",
            password:"",
            Code:"",
            checkCode: "",
            showLogin:true,
            showRegister:false,
            nickname: "",
            passwordSure: "",
			phone: "",
			email: "",
      roleId:""
		
        }
    },methods:{
        createCode(){
               let code="";
               let codeLength=4;
               let random=new Array(
                    0,1,2,3,4,5,6,7,8,9,    "A",
                                            "B",
                                            "C",
                                            "D",
                                            "E",
                                            "F",
                                            "G",
                                            "H",
                                            "I",
                                            "J",
                                            "K",
                                            "L",
                                            "M",
                                            "N",
                                            "O",
                                            "P",
                                            "Q",
                                            "R",
                                            "S",
                                            "T",
                                            "U",
                                            "V",
                                            "W",
                                            "X",
                                            "Y",
                                            "Z"
               )
            for(let i=0;i<codeLength;i++){
                // [0,1)*36---->[0,36)  1.5--->1
                 let index= Math.floor(Math.random()*36);
                 code=code+random[index];
            }
            this.Code=code
                
        },
        change(){
            if(this.showLogin== true){
                this.showLogin=false;
                this.showRegister=true;
            }else{
                 this.showLogin=true;
                this.showRegister=false;
            }



        },
        login(){
            // check  user input username and password 
            if(this.username== ""){
                    this.$notify.error("please enter your username")
                    return ;
            }
            if(this.password==""){
                    this.$notify.error("please enter your password")
                    return ;
            }
            if(this.checkCode==""){
                 this.$notify.error("please enter your verification code")
                 return ;
            }
            if(this.checkCode != this.Code){
                 this.$notify.error("please check your verification code,your code is wrong")
                return ;
            }

            //next we can send data to back-end  we need axios
            // this.axios.post("/getspringbootproxy/login2",
             this.axios.post("/getspringbootproxy/user/sys-user/login",
                            {username:this.username,password:this.password})
                            .then((res)=>{
                                console.log(res)

                                if(res.data.code==504){
                                     this.$notify.error(res.data.msg)
                                         return ;
                                }else if(res.data.code==200){
                                    console.log("aaaaaaa")
                                    console.log("res.data.obj.username:"+res.data.obj.username)
                                    let objstr=  JSON.stringify(res.data.obj)

                                       this.$message.success(res.data.msg);
                                        window.sessionStorage.setItem("userinfo",objstr);
                                        this.$router.push("/main"); 

                                }

                                // console.log(res.data.username)
                                
                                // save username to sessionStorage
                                // window.sessionStorage.setItem("username",res.data.username)
                                // this.$router.push("/Main");     
                                // change url to another url we use  this.$router.push("/Main")
                                    // if(res.data=="ok"){
                                    //                 this.$router.push("/Main");  //javascript: location.href=""

                                    // }

                            })


        },register(){
if (this.username == "") {
        this.$notify.error("Please enter the username");
        return;
      }
      if (this.nickname == "") {
        this.$notify.error("Please enter the nickname");
        return;
      }
      if (this.password == "") {
        this.$notify.error("Please enter the password");
        return;
      }
      if (this.passwordSure == "") {
        this.$notify.error("Please enter the password again");
        return;
      }
      if (this.phone == "") {
        this.$notify.error("Please enter the phone");
        return;
      }
      if (this.email == "") {
        this.$notify.error("Please enter the email");
        return;
      }
      if (this.password != this.passwordSure) {
        this.$notify.error("Two passwords are not the same，please re-enter");
        return;
      }
         let tmp;
           
            if(this.roleId=="2"){
              tmp = "MVO";
            }
            if(this.roleId=="3"){
              tmp = "BVO";
            }
      this.axios.post("/getspringbootproxy/user/sys-user/register", {
        username: this.username,
        password:this.password,
        name:this.nickname,
        phone:this.phone,
        email:this.email,
        roleId:this.roleId,
        roleName:tmp
      }).then(res => {

			let resdata=res.data;
        if (resdata.code == 504) {
				
          this.$notify.error(resdata.code+resdata.msg);
        }
        if (resdata.code == 200) {
          this.change();
          this.$notify.success(resdata.msg);
        }
      });



        }


    },created(){
            this.createCode();

    }


}
</script>

<style scoped> 
.login{

background-image: url('~@/assets/background1.jpg');
 background-repeat: no-repeat;
 background-size: 100% ;
height:800px;

}

#robot {
  margin: -5%;
   background-color: rgba(0, 0, 0, 0.301);
}
.bannerImg {
  width: 100%;
  height: 100%;
  opacity: 0.5;
}
.login-container {
  border-radius: 15px;
  background-clip: padding-box;
   margin-left:40px;
  margin-top: -41%;
  width: 350px;

  padding: 35px 35px 15px 35px;
background-color: rgba(0, 0, 0, 0.301);
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
  z-index: 9;
  transition: background-color 89s linear;

}
.login_title {
  margin: 0px auto 40px auto;
  text-align: center;
  color: rgb(240, 231, 231);
}
.login_remember {
  margin: 0px 0px 35px 0px;
  text-align: left;

}
.el-radio__label {
    padding-left: 10px;
     color: #308b30;
     font-size: 18px;
}
.el-radio__input.is-checked+.el-radio__label {
    color: #308b30;
}
.el-avatar, .el-cascader-panel, .el-radio, .el-radio--medium.is-bordered .el-radio__label, .el-radio__label {
    font-size: 18px;
}
.spaceBottom{
margin-bottom:5px;
}
.btn {
  width: 15rem;
  height: 3.2rem;
  font-size: 20px;
}

</style>