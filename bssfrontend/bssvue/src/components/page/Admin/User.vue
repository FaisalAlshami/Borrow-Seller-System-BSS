<template>
  <div>
      <div>
          <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/main' }">HOME</el-breadcrumb-item>
            <el-breadcrumb-item>User</el-breadcrumb-item>
          </el-breadcrumb>

      </div>
 <br />

    <span>
      Search：
      <el-input style="width:15rem" placeholder="User name" @input="search" v-model="search_name"></el-input>
    </span>
    <el-button type="danger" icon="el-icon-plus" @click="add">Add</el-button>

    <el-divider></el-divider>

      <el-table
    :data="tableData"
    style="width: 100%">
     <el-table-column type="selection" width="50"></el-table-column>
        <el-table-column fixed prop="userId" label="USERID"  width="100"></el-table-column>
        <el-table-column fixed prop="username" label="USERNAME"  width="120"> </el-table-column>
        <el-table-column prop="name" label="NICKNAME"  width="120">  </el-table-column>
         <el-table-column prop="roleName" label="ROLE"  width="120">  </el-table-column>
    
        <el-table-column
          prop="email"
          label="Eamil"
          width="150">
        </el-table-column>
          <el-table-column
          prop="phone"
          label="PHONE"
          width="120">
        </el-table-column>
        <el-table-column
        prop="status"
        label="Status"
        :filter-method="filterHandler"
        :filters="[{ text: ' active ', value: '1' },{ text: 'Not Active', value: '0' }]">
           <template #default="scope">
            <el-tag :type="tag(scope.row.status)" disable-transitions>{{tag(scope.row.status)}}</el-tag>
      
        </template>
        </el-table-column>
        <br/>
        <el-table-column
          fixed="right"
          label="Operations"
          width="300">
          <template #default="scope">
            <!-- <el-tag :type="tag(scope.row.status)" disable-transitions>{{tag(scope.row.status)}}</el-tag> -->
              <!-- <el-button type="warning" size="mini" icon="el-icon-user" @click="permissions(scope.row)"></el-button> -->
            <el-button type="success" size="mini" icon="el-icon-edit"  @click="showUserInfo(scope.row)" >Edit</el-button>
            <el-button type="danger" size="mini" icon="el-icon-delete" @click="deleteUser(scope.row)" >Delete</el-button>
            <el-button
            :type="btntag(scope.row.status)"
            size="small"
            @click="operate(scope.row)"
          >{{btn(scope.row.status)}}</el-button>
            <!-- <el-button @click="handleClick" type="text" size="small">Detail</el-button>
            <el-button type="text" size="small">Edit</el-button> -->
          </template>
        </el-table-column>
  </el-table>

    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :currentPage="currentPage"
      :page-sizes="[5, 10, 20, tableData.length]"
      :page-size="pagesize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total">
    </el-pagination>
<br/>
 <el-button size="medium" type="danger" icon="el-icon-delete" @click="removeMore">Delete All</el-button>


      <el-drawer
        title="I am the title"
        v-model="drawer"
        :with-header="false"
        >
        
       <h2 class="title">UserInfo</h2>
				<el-input
				style="width:24rem"
				class="input"
				v-model="userForm.username"
				placeholder="Please enter the user name."
				prefix-icon="el-icon-user-solid"
				></el-input>
				<el-input
				style="width:24rem"
				class="input"
				v-model="userForm.name"
				placeholder="Please enter the nick name."
				prefix-icon="el-icon-user"
				></el-input>
		
				<el-input
				style="width:24rem"
				class="input"
				v-model="userForm.phone"
				placeholder="Please enter your phone."
				prefix-icon="el-icon-phone"
				></el-input>
				<el-input
				style="width:24rem"
				class="input"
				v-model="userForm.email"
				placeholder="Please enter your e-mail."
				prefix-icon="el-icon-message"
				></el-input>
        	<el-input
				style="width:24rem"
				class="input"
	    v-model="userForm.roleName"

				placeholder="Please enter your e-mail."
				prefix-icon="el-icon-user"
				></el-input>
			 <el-form-item
            class="radio"
            label="Role"
            prop="roleName"
            :rules="[{ required: true, message: 'Please choose the Role'}]"
          >
            <el-radio v-model="userForm.roleName" label="MVO">Brand-seller</el-radio>
            <el-radio v-model="userForm.roleName" label="BVO">Borrow-seller</el-radio>
          </el-form-item>
				<el-button @click="updateUserInfo()"  >confirm</el-button>
        <el-button @click="cancel()"  >cancel</el-button>
       


      </el-drawer>
 


  </div>
</template>

<script>
export default {
name:"User",
 created(){

     this.getUsers();


 },
  methods: {
    deleteUser(row){
         this.userForm=row
      // let userid=row.userId;
       if (this.userForm.userId == 3) {
        this.$message.warning("Can't remove the admin account");
        return;
      }
    
       this.axios.post("/getspringbootproxy/user/sys-user/deleteById",{
            userId:this.userForm.userId,
          }).then(res => {
                console.log(res)
              let resdata=res.data;
                if (resdata.code == 504) {
                  this.$notify.error(resdata.code+resdata.msg);
                }
                if (resdata.code == 200) {
                
                  this.$notify.success(resdata.msg);
                  this.$router.go(0)
                }
              });

    },
     btntag(msg) {
      if (msg == "1") return "warning";
      if (msg == "0") return "success";
     
    },
    btn(msg) {
      if (msg == "1") return "Disactive";
      if (msg == "0") return "Active";
     
    },
    tag(msg) {
      if (msg == "1") return "active";
      if (msg == "0") return "Not active ";
      
    },
   removeMore() {
      if(this.multipleSelection.length == 0){
        this.$message.warning('Please select item');
        return
      }
      this.multipleSelection.forEach(element => {
        this.axios.post("/getspringbootproxy/user/sys-user/deleteById",{
              userId:element.userId
        }).then(res => {
          if (res.code == 504) {
            this.$message.warning(res.message);
            return;
          }
          if (res.code == 200) {
             this.$router.go(0)
          }
        });
      });
      this.$message.success("Delete success");
    },
   

        updateUserInfo(){
           
           let tmp;
           
            if(this.userForm.roleName=="MVO"){
              tmp = "2";
            }
            if(this.userForm.roleName=="BVO"){
              tmp = "3";
            }
            // this.userForm 
            // use axios send data back
             this.axios.post("/getspringbootproxy/user/sys-user/UpdateUserInfo",{
            userId:this.userForm.userId,
            username:this.userForm.username,
            name:this.userForm.name,
            email:this.userForm.email,
            phone:this.userForm.phone,
            roleId:tmp,
            roleName:this.userForm.roleName,
          }).then(res => {
                console.log(res)
              let resdata=res.data;


                if (resdata.code == 504) {
                
                  this.$notify.error(resdata.code+resdata.msg);
                }
                if (resdata.code == 200) {
                
                  this.$notify.success(resdata.msg);
                  this.$router.go(0)
                }
              });

        },
        showUserInfo(row){
            
            console.log(row)
            this.userForm=row
            console.log( this.userForm.username)
            this.drawer = true;



        },
        filterHandler(value, row, column) {
      const property = column["property"];
      return row[property] === value;
    },
         cancel() {
      this.userForm={};
      this.drawer = false;
    },

        getUsers(){
          this.axios.post("/getspringbootproxy/user/sys-user/getAllUsers2",{
            pageSize:this.pagesize,
            currentPage:this.currentPage
          }).then(res => {
                console.log(res)
              let resdata=res.data;


                if (resdata.code == 504) {
                
                  // this.$notify.error(resdata.code+resdata.msg);
                }
                if (resdata.code == 200) {
                  this.tableData=resdata.obj.records;
                  this.total=resdata.obj.total

                  // this.$notify.success(resdata.msg);
                }
              });
 
        },

        add()
        {
          this.drawer=true;
        },
      handleClick() {
        console.log('click');
      },
       handleSizeChange(val) {
        // console.log(`${val} items per page`);
        this.pagesize=val
        this.getUsers();

      },
      handleCurrentChange(val) {
        // console.log(`current page: ${val}`);
        this.currentPage=val;
        this.getUsers();
      },

operate(row){
  if(row.userId == 3){
        this.$message.warning("Can't edit the admin account");
        return
      }
 let nextStage = "";

if (row.status=="1"){
   nextStage="0"
   
  this.axios.post("/getspringbootproxy/user/sys-user-role-relationship/deleteById",{
userId:row.userId

  }).then(res => {
                console.log(res)
              let resdata=res.data;
                if (resdata.code == 504) {
                  this.$notify.error(resdata.code+resdata.msg);
                }
                if (resdata.code == 200) {
                
                  this.$notify.success(resdata.msg);
                  this.$router.go(0)
                }
              });

   this.axios.post("/getspringbootproxy/user/sys-user/UpdateUserInfo",{
            userId:row.userId,
            roleId:row.roleId,
              status:nextStage 
}).then(res => {

                console.log(res)
              let resdata=res.data;


                if (resdata.code == 504) {
                
                  this.$notify.error(resdata.code+resdata.msg);
                }
                if (resdata.code == 200) {
                
                  this.$notify.success(resdata.msg);
                  this.$router.go(0)
                }
              });
  }

 else if (row.status=="0"){
 
   nextStage="1"
this.axios.post("/getspringbootproxy/user/sys-user-role-relationship/addRoleByUser",{
userId:row.userId,
roleId:row.roleId

  }).then(res => {
                console.log(res)
              let resdata=res.data;
                if (resdata.code == 504) {
                  this.$notify.error(resdata.code+resdata.msg);
                }
                if (resdata.code == 200) {
                
                  this.$notify.success(resdata.msg);
                  this.$router.go(0)
                }
              });

   this.axios.post("/getspringbootproxy/user/sys-user/UpdateUserInfo",{
            userId:row.userId,
           roleId:row.roleId,
              status:nextStage 
}).then(res => {

                console.log(res)
              let resdata=res.data;


                if (resdata.code == 504) {
                
                  this.$notify.error(resdata.code+resdata.msg);
                }
                if (resdata.code == 200) {
                
                  this.$notify.success(resdata.msg);
                  this.$router.go(0)
                }
              });






 }

  },
       permissions(row) {    
      if(row.user_id == 3){
        this.$message.warning("Can't edit the admin account");
        return
      }
      this.drawerPr=true;

     this.axios.post("/getspringbootproxy/user/sys-menu-role-relationship", {
        parentId: row.roleId
      }).then(res => {
        let resdata=res.data;
        if (resdata.code == 504) {
          this.$message.warning(res.message);
          return;
        }
        if (resdata.code == 200) {
          this.menu0 = resdata.obj.filter(e => e.parentId == 0);
          this.menu1 = resdata.obj.filter(e => e.parentId == 1);
          this.menu2 = resdata.obj.filter(e => e.parentId == 2);
          this.beingChangedUserID = row.user_id;
        }
      });

      this.drawerPr = true;
    
    
  }
  },
    data() {
      return {
      userForm:[],
      drawer: false,
       total:0,
       pagesize:10,
       currentPage: 1,
     tableData: [],
     multipleSelection: [],
      menu0: [],
      menu1: [],
      menu2: [],
      beingChangedUserID:0,
      search_name: "",
      drawerPr: false,
     
      }
    }
}
</script>

<style>
.form-div {
  margin-left: 3rem;
  height: 50rem;
}

.user-form-button {
  margin-top: 3rem;
  margin-left: 10rem;
}
.table >>> .el-checkbox__input.is-indeterminate .el-checkbox__inner {
  background-color: #f56c6c;
  border-color: #f56c6c;
}
.table >>> .el-checkbox__input.is-checked .el-checkbox__inner {
  background-color: #f56c6c;
  border-color: #f56c6c;
}
.table >>> .el-checkbox__input.is-checked + .el-checkbox__label {
  color: #f56c6c;
}
.radio >>> .el-radio__input.is-checked .el-radio__inner {
  border-color: #f46774;
  background: #f46774;
}
.radio >>> .el-radio__input.is-checked + .el-radio__label {
  color: #f46774;
}
</style>