<template>
  <div class="container">
    <div>
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/main' }">HOME</el-breadcrumb-item>
        <el-breadcrumb-item>Management</el-breadcrumb-item>
        <el-breadcrumb-item>Parameters</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    

    <!-- Add new Parameter button -->
    <div class="add-button">
      <el-button type="success" plain icon="el-icon-plus" @click="addParameter" >New Parameter</el-button>
    </div>
    <!-- Title -->

    <br/>

    <!-- Parameter Table -->
    <div class="table-block">
       <el-input v-model="search" size="mini" placeholder="Type parameter ID" class="serch-input"/>
        <el-table ref="multipleTable" 
          :data="tableData.filter(data => !search || data.parId == search)" class="table-row"
          stripe
          style="width: 100%">
          <el-table-column prop="parId" label="ID" ></el-table-column>
          <el-table-column prop="paramCd" label="Parameter Key"></el-table-column>
          <el-table-column prop="paramValue" label="Parameter Value"></el-table-column>
          <el-table-column prop="description" label="Description"></el-table-column>
          <el-table-column fixed="right" label="Operations">
            <template #default="scope">
              <el-button @click="editParameter(scope.row)" type="primary" icon="el-icon-edit" circle></el-button>
              <el-button @click="deleteParameter(scope.row)" type="danger" icon="el-icon-delete" circle></el-button>
            </template>
          </el-table-column>
        </el-table>
    </div>

    <!-- Pagination -->
    <div>  
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :currentPage="currentPage"
      :page-sizes="[5, 10, 20, tableData.length]"
      :page-size="pagesize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total">
    </el-pagination>
    </div>
    <!-- Drawer -->
      <el-drawer
        v-model="drawer"
        :with-header="false"
        size="40%"
      >

        <div class="form-block">
          <h2>Parameters</h2>
          <el-form :model="saveParameters" ref="saveParameters" label-width="150px" class="saveParameters-form">
            <el-form-item label="Parameter Key" prop="paramCd"
              :rules="[{required: true, message: 'Param_CD is required', trigger: 'blur'}]">
              <el-input style="width:20rem" v-model="saveParameters.paramCd"></el-input>
            </el-form-item>
            <br />
            <el-form-item label="Parameter Value" prop="paramValue"
              :rules="[{required: true, message: 'Value is required', trigger: 'blur'}]">
            <el-input style="width:20rem" v-model="saveParameters.paramValue"></el-input>
            </el-form-item>
            <br />
    
            <el-form-item label="Description" prop="description"
              :rules="[{required: true, message: 'Description is required', trigger: 'blur'}]">
            <el-input style="width:20rem" v-model="saveParameters.description"></el-input>
            </el-form-item>

            <br />
              <el-form-item class="goods-form-button">
                <el-button type="primary" @click="updateParameter()">Save</el-button>
                <el-button  @click="cancelDrawer()">Cancel</el-button>
              </el-form-item>
          </el-form>
        </div>
      </el-drawer>
    
  </div>
</template>

<script>
export default {
  name:'Param',
  data() {
    return {
      pageTitle: 'Parameter Manage',
      total: 0,
      pagesize: 10,
      currentPage: 1,
      tableData: [
        {
          parId: '',
          paramCd: '',
          paramValue: '',
          creationDate: '',
          lastUpdateDate: '',
          description: '',
          createdBy: '',
          
        }, 
      ],
      drawer: false,
      search: '',
      saveParameters: [],
    };
  },

  //Lifcycle method 
  created() {
   this.getAllParameters();
  },

  methods: {
    // sending a request to the back-end
    getAllParameters() {
      this.axios.post('/getspringbootproxy/user/par-parameter/getAllParameters',
        {
          pageSize: this.pagesize,
          currentPage: this.currentPage
        }
      ).then(res => {
          console.log(res);
          let resdata = res.data; 

          if(resdata.code == 200){
            this.tableData = resdata.obj.records;// putting records in the table
            this.total = resdata.obj.total; // getting pagination data
          }
          if(resdata == 504) {
            this.$message.error(res.msg); //showing error message
          }
      });
    },
    // Add a new parameter
    saveData() {
        let objstr= window.sessionStorage.getItem("userinfo")
        let userinfo= JSON.parse(objstr)
        this.userid=userinfo.userId;
        this.username=userinfo.username;
        this.axios.post('/getspringbootproxy/user/par-parameter/addParameterById',
          {
            userId:this.userId,
            parId: this.saveParameters.parId,
            paramCd: this.saveParameters.paramCd,
            paramValue: this.saveParameters.paramValue,
            description: this.saveParameters.description,
          }
        ).then(res => {
          console.log(res);
          let resdata = res.obj;
          if(resdata.code == 200) {
            this.$message.success(resdata.msg);
            this.$router.go(0);
          }
          if(resdata.code == 504) {
            this.$message.error(resdata.code + resdata.msg);
          }
        });
    },
    addParameter(){
      this.drawer = true;
    },
    // send a request for editting
    updateParameter() {
      this.axios.post('/getspringbootproxy/user/par-parameter/updateParameterId',
        {
          parId: this.saveParameters.parId,
          paramCd: this.saveParameters.paramCd,
          paramValue: this.saveParameters.paramValue,
          creationDate: this.saveParameters.creationDate,
          lastUpdateDate: this.saveParameters.lastUpdateDate,
          description: this.saveParameters.description,
          createdBy: this.saveParameters.createdBy,
        }
      ).then(res => {
        console.log(res);
        let resdata = res.data;

        if(resdata.code == 200) {
          this.$message.success(resdata.msg);
          this.$router.go(0)
        }
        if(resdata.code == 504) {
          this.$message.error(resdata.code + resdata.msg);
        }
      });
    },
    // edit a parameter
    editParameter(row) {
      console.log(row);
      this.saveParameters=row
      console.log( this.saveParameters)
      this.drawer = true;
    },
    // Delete parameter by ID
    deleteParameter(row) {
      console.log(row);
      this.saveParameters = row;
      this.$confirm('This will permanently delete the file. Continue?', 'Warning', {
          confirmButtonText: 'OK',
          cancelButtonText: 'Cancel',
          type: 'warning'
        }).then(() => {
            this.axios.post('/getspringbootproxy/user/par-parameter/deleteParameterById',
              {
                parId: this.saveParameters.parId
              }
            ).then(res => {
              console.log(res);
              let resdata = res.data;

              if(resdata.code == 200) {
                this.$message.success(resdata.msg);
                this.$router.go(0)
              }
              if(resdata.code == 504) {
                this.$message.error(resdata.msg);
              }
            });
            
          }).catch(() => {
          this.$message({
            type: 'info',
            message: 'Delete canceled'
          });
        });
    },
    cancelDrawer(){
     this.drawer = false;
    },

    // Paginations
    handleSizeChange(val) {
      this.pagesize = val;
      this.getAllParameters();
    },
    handleCurrentChange(val) {
      this.currentPage = val;
      this.getAllParameters();
    },
  },
};
</script>

<style scoped>

.title-block {
  font-size: 20px;
  margin-top: 0px;
  text-align: center;
}
.add-button {
  margin-left:85%;
  margin-top: 0%;
  display: inline-block;

}

.form-block {
  margin-left: 1rem;
}
.saveParameters-form {
  margin-top: 3rem;
}
.goods-form-button {
  margin-top: 2rem;
  margin-left: 0rem;
}
.serch-input {
  width: 250px;
}
</style>