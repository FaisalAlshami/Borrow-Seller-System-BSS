<template>
  <div>
     
          <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/main' }">HOME</el-breadcrumb-item>
            <el-breadcrumb-item>Data Dictionary</el-breadcrumb-item>
          </el-breadcrumb>

     <br />
    <br />
      <div>
        <span>
          Search：
          <el-input
            style="width:200px"
            placeholder="Dictionary type"
             @input="search"
            v-model="search_keywords"
          ></el-input>
        </span>
       <!-- <el-button type="danger" icon="el-icon-search" @click="search">Search</el-button> -->
        <el-button type="danger" plain icon="el-icon-plus" @click="add">Add</el-button>
    </div>
       <el-divider></el-divider>

      <el-table
      :data="tableData"
      style="width: 100%"
      class="table"
      @selection-change="handleSelectionChange"
     :row-class-name="tableRowClassName">
      <el-table-column type="selection" width="50"></el-table-column>
        <el-table-column  prop="id" label="ID"  width="100" style=hidden></el-table-column>
        <el-table-column prop="codeType" label="Dictionary type"  width="150"></el-table-column>
         <el-table-column prop="description" label="Description"  width="120">  </el-table-column>
        <el-table-column prop="typeCd" label="Code"  width="120"> </el-table-column>
        <el-table-column prop="codeValue" label="Code value"  width="120">  </el-table-column>
       
        <el-table-column
          fixed="right"
          label="Operations"
          width="300">
          <template #default="scope">

            
            <el-button type="success" icon="el-icon-edit" size="mini"  @click="showDataInfo(scope.row)" >Edit</el-button>
            <el-button  type="danger" icon="el-icon-delete" size="mini" @click="deleteData(scope.row)" >Delete</el-button>
           
         
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

<el-drawer
        title="I am the title"
        v-model="drawer"
        :with-header="false"
        >
        
       <h2 class="title">DataInfo</h2>
				<el-input
				style="width:24rem"
				class="input"
				v-model="dataForm.codeType"
				placeholder="Please enter the field name."
				prefix-icon="el-icon-edit-outline"
				></el-input>
				<el-input
				style="width:24rem"
				class="input"
				v-model="dataForm.description"
				placeholder="Please enter the field size."
				prefix-icon="el-icon-notebook-2"
				></el-input>
		
				<el-input
				style="width:24rem"
				class="input"
				v-model="dataForm.typeCd"
				placeholder="Please enter data type."
				prefix-icon="el-icon-notebook-1"
				></el-input>
				<el-input
				style="width:24rem"
				class="input"
				v-model="dataForm.codeValue"
				placeholder="Please enter description."
				prefix-icon="el-icon-notebook-2"
				></el-input>
			
				<el-button @click="updateDataInfo()"  >confirm</el-button>
        <el-button  @click="cancelDrawer()"  >cancel</el-button>
       


      </el-drawer>





     </div>
</template>
<script>
export default {
name:"Data",
 created(){

     this.getData();


 },
  methods: {
      getData(){

 this.axios.post("/getspringbootproxy/user/sys-data/getAllData",{
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
   cancelDrawer(){
   
                this.dataForm = {
                  
                };
                this.drawer=false;

                 

   },
   deleteData(row){
     
       this.dataForm=row
        this.$confirm('This will permanently delete the file. Continue?', 'Warning', {
          confirmButtonText: 'OK',
          cancelButtonText: 'Cancel',
          type: 'warning'
        }).then(() => {
           this.axios.post("/getspringbootproxy/user/sys-data/deleteById",{
              id:this.dataForm.id,
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
        updateDataInfo(){
          
             this.axios.post("/getspringbootproxy/user/sys-data/Save",{
             id:this.dataForm.id,
            codeType:this.dataForm.codeType,
            description:this.dataForm.description,
            typeCd:this.dataForm.typeCd,
            codeValue:this.dataForm.codeValue
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
        showDataInfo(row){
            
            console.log(row)
            this.dataForm=row
           
            this.drawer = true;



        

  },
      handleClick() {
        console.log('click');
      },
       handleSizeChange(val) {
        // console.log(`${val} items per page`);
        this.pagesize=val
        this.getData();

      },
      handleCurrentChange(val) {
        // console.log(`current page: ${val}`);
        this.currentPage=val;
        this.getData();
      },
       search() {
      this.tableData = this.table.filter(e =>
        e.codeType.match(this.search_keywords)
      );
    },
      handleSelectionChange(val) {
      this.multipleSelection = val;
      },
  
 removeMore() {
      if(this.multipleSelection.length == 0){
        this.$message.warning('Please select item');
        return
      }
      this.multipleSelection.forEach(element => {
         this.axios.post("/getspringbootproxy/user/sys-data/deleteById",{
              id:element.id
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
        tableRowClassName({row, rowIndex}) {
        if (rowIndex === 1) {
          return 'warning-row';
        } else if (rowIndex === 3) {
          return 'success-row';
        }
        return '';
        }
    },
    data() {
      return {
        dataForm:[],
        drawer: false,
        total:0,
        pagesize:5,
        currentPage: 1,
        tableData: [],
         table: [],
        search_keywords:"",
         multipleSelection: [],
          dataForm: {
        codeType: "",
        description: "",
        typeCd: "",
        codeValue: ""
      }
         }
    }
}
</script>

<style>
 .el-table .warning-row {
    background: oldlace;
  }

  .el-table .success-row {
    background: #f0f9eb;
  }
</style>