<template>

  <div>
        <div>
          <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/main' }">HOME</el-breadcrumb-item>
            <el-breadcrumb-item>MyInfo</el-breadcrumb-item>
          </el-breadcrumb>
      </div>

   <div style="width:80%; margin-top:40px; margin-right:10%; min-height:100px;">
   <el-form  ref="form" :model="form" label-width="180px">
    <table>
     <tr>
      <td style="width:30%;">
      <div class="demo-image__preview" style="padding-top:10px;">
       <img v-if="imageUrl" :src="imageUrl" class="avatar" />
        
        <div style="width:100%;height:40px; text-align:center; padding-top:10px;">
        </div>
      </div>
      </td>
      <td style="width:100%; padding-left:50px;">
      <table style="display: table-row;">
      <tr style="padding-bottom:50px;">
       <td>lastLogin</td>
       <td> {{userForm.lastLogin}} </td>
      </tr>
      <tr>
       <td>User name</td>
       <td> <el-input v-model="userForm.username" enable="false" style="width: 250px;"></el-input></td>
      </tr>
      <tr>
       <td>Nickname</td>
       <td> <el-input v-model="userForm.nickname" enable="false" style="width: 250px;"></el-input></td>
      </tr>
       <tr>
       <td>Email</td>
       <td> <el-input v-model="userForm.email" enable="false" style="width: 250px;"></el-input></td>
      </tr>
       <tr>
       <td>Phone</td>
       <td> <el-input v-model="userForm.phone" enable="false" style="width: 250px;"></el-input></td>
      </tr>
      </table>
        
      </td>
     </tr>
     <tr>
     <td colspan="2" style="display:relative; padding-left:50px;">
           <el-form-item>
            <el-button type="primary" @click="updateUserInfo">Save</el-button>
            <el-button @click="drawer = true" type="primary" style="margin-left: 16px;">
            Change password
          </el-button>
           </el-form-item>
     </td>
     </tr>
    </table>
    </el-form>
   </div>
  <el-drawer
  title="I am the title"
  v-model="drawer"
  :with-header="false">
  <div style="width:80%;  margin-left:5%; margin-top:10px;">
  <span>Change password</span>

  <el-form :label-position="labelPosition" label-width="100px" :model="formLabelAlign">
      <el-form-item label="Password">
        <el-input v-model="passwordForm.firstpassword" type="password"></el-input>
      </el-form-item>
      <el-form-item label="Retry password">
        <el-input v-model="passwordForm.retrypassword" type="password"></el-input>
      </el-form-item>
      <el-form-item type="danger">
         <div style="color:red;"> {{warringmsg}}</div>
      </el-form-item >
       <el-form-item >
        <el-button type="primary" @click="updatePassword()">Save</el-button>
        <el-button @click="drawer = false" type="warning" style="margin-left: 16px;">
           Cancel
        </el-button>
      </el-form-item>
  </el-form>

  </div>
</el-drawer>



  </div>
  
</template>

<script>
export default {
    name:"My",
    data() {
      return {
      labelPosition: 'top',
        formLabelAlign: {
          name: '',
          region: '',
          type: ''
        },
        photoForm:[],
        passwordForm: {
        firstpassword:'',
        retrypassword:''
        },
        warringmsg:'',
        imageUrl: '',
        drawer:false,
        drawerPhoto:false,
        imgSrc:'',
        fileList: [
        ],
        userForm: {
          username: '',
          nickname: '',
          lastLogin:'',
          email:'',
          phone:'',
          imgUrl:''
        },
        username:'',
        userid:''
        

      }
    },
    created(){
        let objstr= window.sessionStorage.getItem("userinfo")
        let userinfo= JSON.parse(objstr)
        console.log(userinfo)
        this.userForm.username=userinfo.username;
        this.userForm.nickname=userinfo.name;
        this.userForm.lastLogin=userinfo.lastLogin;
        this.userForm.email=userinfo.email;
        this.userForm.phone=userinfo.phone;
        this.imageUrl=userinfo.imgUrl;
    },
     methods: {
        submitForm(formName) {
              this.$refs[formName].validate(valid => {
                  this.$refs.upload.submit();
                  console.log("fileName:"+this.fileName);
              
              });
          },
          add(){
              this.drawerPhoto =true;
          },
       handleAvatarSuccess(res, file) {
       console.log("file  :"+file);
       console.log(res);
        this.imageUrl = URL.createObjectURL(file.raw)
      },
       beforeAvatarUpload(file) {
       console.log("File :"+file);
        console.log("imageUrl :"+this.imageUrl);
        const isJPG = file.type === 'image/jpeg';
        const isLt2M = file.size / 1024 / 1024 < 2;
        if (!isJPG) {
          this.$message.error('Avatar picture must be JPG format!')
        }
        if (!isLt2M) {
          this.$message.error('Avatar picture size can not exceed 2MB!')
        }
        return isJPG && isLt2M
       },

       updateUserInfo(){
          console.log(" invoke updateCompanyInfo");
          let objstr= window.sessionStorage.getItem("userinfo")
          let userinfo= JSON.parse(objstr)
          this.userid=userinfo.userId;
          this.axios.post("/getspringbootproxy/user/sys-user/UpdateUserInfo",{
            userId:this.userid,
            email:this.userForm.email,
            name:this.userForm.nickname,
            username:this.userForm.username,
            phone:this.userForm.phone
          }).then(res => {
                console.log(res)
                let resdata=res.data;
                if (resdata.code == 504) {                
                  this.$notify.error(resdata.code+resdata.msg);
                }
                if (resdata.code == 500) {
                
                  this.$notify.error(resdata.code+resdata.msg);
                }
                if (resdata.code == 200) {
                // this code after update successful user info will update userinfo in seesion don't need relogin
                  let objstr=  JSON.stringify(res.data.obj)
                  this.$message.success(res.data.msg);
                  window.sessionStorage.setItem("userinfo",objstr);
                  this.$notify.success(resdata.msg);
                  //this.$router.go(0)
                }
              });

        },
        updatePassword(){
          this.warringmsg="";
          if(this.passwordForm.firstpassword!=this.passwordForm.retrypassword){
          this.warringmsg="the password isn't corresponding";
          }
          else if(this.passwordForm.firstpassword.length<3)
          {
           this.warringmsg="the password should more 3 characters";
          }
          else{
            this.warringmsg="";
            let objstr= window.sessionStorage.getItem("userinfo")
            let userinfo= JSON.parse(objstr)
            this.userid=userinfo.userId;
              this.axios.post("/getspringbootproxy/user/sys-user/UpdateUserPassword",{
                userId:this.userid,
                password:this.passwordForm.firstpassword,
                retryPassword:this.passwordForm.retrypassword
              }).then(res => {

              });
          }
        },
      
    },

}
</script>

<style scope>
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409eff;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>