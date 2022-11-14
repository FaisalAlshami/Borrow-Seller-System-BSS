<template>
  <div>
      <div>
          <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/main' }">HOME</el-breadcrumb-item>
            <el-breadcrumb-item>Menu</el-breadcrumb-item>
          </el-breadcrumb>

      </div>
<br />
    <br />
  <div>
    <span>
          Search：
          <el-input
            style="width:200px"
            placeholder="search keywords"
            @input="search"
            v-model="search_keywords"
          ></el-input>
        </span>
        <el-button type="danger" icon="el-icon-search">Search</el-button>
         <el-button type="danger" icon="el-icon-plus" @click="dialogVisible = true">Add</el-button>
          <!-- <el-button type="danger" plain icon="el-icon-plus" @click="add">Add</el-button> -->
    
  </div>
    <el-divider></el-divider>
 
   <el-table
    :data="tableData"
    style="width: 100%"
     @selection-change="handleSelectionChange"
     :row-class-name="tableRowClassName">
           <el-table-column type="selection" width="50"></el-table-column>

     <!-- <el-table-column  prop="menuId" label="ID"  width="150" hidden="true"></el-table-column> -->
     <el-table-column prop="menuOrder" label="Menu Order"></el-table-column>
      <el-table-column prop="menuName" label="MenuTitle"></el-table-column>
      <el-table-column prop="menuUrl" label="Menu Url"></el-table-column>
       <el-table-column prop="parentId" label="Menu Type"></el-table-column>
      <el-table-column prop ="menuIcon" label="Icon"> 
        <template  #default="scope">
            <i :class="scope.row.menuIcon"  ></i>
        </template>
        </el-table-column> 
     
      <!-- <p class="p-menu">
            <i :class="menuIcon"></i>
            {{menuIcon}}
          </p> -->
        <!-- <p class="p-menu"> <i class="menuIcon"></i>  </p> -->
          
        
         
      <el-table-column label="Operation">
        <template #default="scope">
          <!-- <el-button @click="routertest">routertest</el-button> -->
          <el-button type="success" size="mini" icon="el-icon-edit" @click="edit(scope.row)">Edit</el-button>
          <el-button
            type="danger"
            size="mini"
            icon="el-icon-delete"
            @click="deleteMenu(scope.row)"
          >Delete</el-button>
        </template>
      </el-table-column>
    </el-table>
      <br />
 <el-button size="medium" type="danger" icon="el-icon-delete" @click="removeMore">Delete ALL</el-button>
 <br/>
 <br/>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :currentPage="currentPage"
      :page-sizes="[5, 10, 20, tableData.length]"
      :page-size="pagesize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total">
    </el-pagination>


  <el-dialog
  title="Menu Information"
  v-model="dialogVisible"
  width="50%"
  >
      <el-form :model="menuForm" ref="menuForm" label-width="130px">
        <el-form-item
          label="Menu title"
          prop="menuName"
          :rules="[{ required: true, message: 'Please enter the Menu title'}]"
        >
          <el-input style="width:20rem" v-model="menuForm.menuName" autocomplete="off"></el-input>
        </el-form-item>
        <br />
        <el-form-item
          label="Menu Url"
          prop="menuUrl"
          :rules="[{ required: true, message: 'Please enter the index'}]"
        >
          <el-input style="width:20rem" v-model="menuForm.menuUrl" autocomplete="off" 	placeholder="Please enter /something."
				prefix-icon="el-icon-edit-outline"></el-input>
        </el-form-item>
        <br />
        <el-form-item
          label="Menu Order"
          prop="menuOrder"
          :rules="[{ required: true, message: 'Please enter the Order'}]"
        >
          <el-input style="width:20rem" v-model="menuForm.menuOrder" autocomplete="off" 	placeholder="Please enter Number."
				prefix-icon="el-icon-notebook-2"></el-input>
        </el-form-item>
        <br />
         <el-form-item
            label="Menu Type"
            prop="parentId"
            :rules="[{ required: true, message: 'Please choose Menu type', trigger: 'blur'}]"
          >
           <el-select
              style="width:20rem"
              placeholder
              v-model="menuForm.parentId"
              autocomplete="off"
            >
              <el-option
                v-for="item in type_options"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              ></el-option>
            </el-select>
          </el-form-item>
        <el-form-item
            label="Menu Icon"
            prop="menuIcon"
            :rules="[{ required: true, message: 'Please choose Menu icon', trigger: 'blur'}]"
          >
           <el-select
              style="width:20rem"
              placeholder
              v-model="menuForm.menuIcon"
              autocomplete="off"
            >
        <el-option
                v-for="item in icon_options"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              ></el-option>
            </el-select>
          </el-form-item>
            <el-form-item
          label="Component"
          prop="component"
          :rules="[{ required: true, message: 'Please enter the component'}]"
        >
          <el-input style="width:20rem" v-model="menuForm.component" autocomplete="off" ></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="cancel('menuForm')">cancel</el-button>
        <el-button type="danger" @click="submitForm()">Submit</el-button>
      </span>
  </el-dialog>

 


  </div>
</template>

<script>
export default {
name:"Menu",
created(){
     this.getMenuList();
    
 },
 data() {
return {
        title: "",
      currentPage:1,
      pagesize: 10,
      dialog: false,
      menuForm:[],
      tableData: [],
        multipleSelection: [],
         table: [],
       total:0,
       drawer:false,
       isAdd:false,
        dialogVisible: false,
         type_options: [
        { value: "1", label: "Admin"},
        { value: "2", label: "BVO" },
        { value: "3", label: "MVO" }
       
    ],
      icon_options: [
        { value: "el-icon-notebook-2", label: "el-icon-notebook-2"},
        { value: "el-icon-user", label: "el-icon-user" },
        { value: "el-icon-notebook-1", label: "el-icon-notebook-1" },
         { value: "el-icon-collection", label: "el-icon-collection" },
          { value: "el-icon-school", label: "el-icon-school" },
           { value: "el-icon-s-tools", label: "el-icon-s-tools" },
            { value: "el-icon-s-shop", label: "el-icon-s-shop" },
             { value: "el-icon-house", label: "el-icon-house" }
       
    ],
    }
  },
   
  methods: {
    
    add(){
      this.dialogVisible  =true;
  },
   cancelDrawer(){
   
                this.fileList = [];
                this.drawer=false;

                  this.$router.go(0);

   },
    getMenuList(){
     this.axios.post("/getspringbootproxy/user/sys-menu/getAllMenu",{
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
   add(){
      this.drawer =true;
   },
   submitForm(){
  this.axios.post("/getspringbootproxy/user/sys-menu/Save",{
             menuId:this.menuForm.menuId,
            menuName:this.menuForm.menuName,
            menuUrl:this.menuForm.menuUrl,
            parentId:this.menuForm.parentId,
            component:this.menuForm.component,
            menuIcon:this.menuForm.menuIcon,
            name:this.menuForm.menuName,
            menuOrder:this.menuForm.menuOrder
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

 this.axios.post("/getspringbootproxy/user/sys-menu-role-relationship/addMenuByUser",{
   menuId:this.menuId,
   roleId:this.parentId

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
   cancel(formName) {
      this.menuForm = {
      
        menuName: "",
        menuUrl: "",
        menuIcon: ""
      };
      this.dialogVisible = false;
    },
    edit(row) {
       console.log(row)
            this.menuForm=row
           
            this.dialogVisible = true;

    },
   deleteMenu(row) {
      this.menuForm=row
        this.$confirm('This will permanently delete the file. Continue?', 'Warning', {
          confirmButtonText: 'OK',
          cancelButtonText: 'Cancel',
          type: 'warning'
        }).then(() => {
           this.axios.post("/getspringbootproxy/user/sys-menu/deleteById",{
               menuId:this.menuForm.menuId,
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
          this.$message({
            type: 'success',
            message: 'Delete completed'
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: 'Delete canceled'
          });
        });
   },
    
  
 handleClick() {
        console.log('click');
      },
       handleSizeChange(val) {
        // console.log(`${val} items per page`);
        this.pagesize=val
        this.getMenuList();

      },
      handleCurrentChange(val) {
        // console.log(`current page: ${val}`);
        this.currentPage=val;
        this.getMenuList();
      },
       search(val){
     this.tableData = this.table.filter(e => e.menuName.match(this.title));
  },





     routertest(){


    console.log("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")



  let routeObj = {
      path: '/aaa', // 这里要把父路由的路径也带上
      name: 'aaaa',
      // meta: { title: '测试路由test', noCache: true },
      component: () =>
          import('@/components/page/Admin/Menu.vue'),
  }



  this.$router.addRoute("Main",routeObj)
  this.$router.push('/aaa')


    // this.$router.addRoutes();
    // this.$router.addRoute();

  }

  }
}
</script>

<style scoped>
.form-div {
  margin-left: 3rem;
  height: 50rem;
}
.add-user-form {
  margin-top: 5rem;
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