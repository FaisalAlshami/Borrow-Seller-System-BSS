<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/main' }">Home</el-breadcrumb-item>
      <el-breadcrumb-item>Company</el-breadcrumb-item>
    </el-breadcrumb>
    <div v-show="emptyShow" class="empty-box">
      <img style="width:25rem" :src="emptyImage" alt />
      <span>There is no company information.</span>
      <el-button type="danger" @click="drawer=true">Fill Company information</el-button>
    </div>

    <div v-show="mainShow" class="main-box-top">
      <div>
        <el-link style="font-size:18px" :underline="false" type="primary">Company information</el-link>
        <el-divider></el-divider>
        <div class="info-flex">
          <p style="width:25rem">
            <i class="el-icon-office-building"></i>
            Company Name：{{name_en}}
          </p>
          <p style="width:25rem">
            <i class="el-icon-document"></i>
            Brief Introduction：{{description}}
          </p>
        </div>
        <div class="info-flex">
          <p style="width:25rem">
            <i class="el-icon-collection-tag"></i>
            GMC Report Type：{{gmc_report_type}}
          </p>
          <p style="width:25rem">
            <i class="el-icon-link"></i>
            GMC Report Url：{{gmc_report_url}}
          </p>
        </div>
        <br />
        <el-button type="primary" @click="changeCompanyInfo">Change</el-button>
        <el-button type="danger" @click="deleteCompany">Delete</el-button>
      </div>
      <div class="main-box-top">
        <el-link style="font-size:18px" :underline="false" type="primary">Brand information</el-link>
        <el-divider></el-divider>
        <span>
          Search：
          <el-input
            style="width:12rem"
            placeholder="Brand Name"
            @input="search"
            v-model="search_name"
          ></el-input>
        </span>
        <el-button type="primary" plain icon="el-icon-search" circle style="margin-left:5px;"></el-button>
        <el-button type="primary" plain icon="el-icon-plus" @click="add">Add</el-button>
        <br />
        <br />
        <el-table size="medium" :data="tableData.slice((currentPage-1)*pagesize,currentPage*pagesize)" style="width: 100%" height="301">
          <el-table-column  prop="brd_ID" label="Brand Id"></el-table-column>
          <el-table-column  prop="name_EN" label="Brand Name"></el-table-column>
          
          <el-table-column  prop="description" label="Brand description"></el-table-column>
          <el-table-column label="operation">
            <template #default="scope">
            <el-tooltip class="item" effect="dark" content="Edit brand" placement="top-start">
              <el-button type="success" circle size="mini" icon="el-icon-edit" @click="changeBrand(scope.$index, scope.row)"></el-button>
               </el-tooltip>   
            <el-tooltip class="item" effect="dark" content="Delete brand" placement="top-start">
              <el-button type="danger"  circle icon="el-icon-delete" size="mini" @click="DeleteBrandForm(scope.row.brd_ID)"></el-button>
            </el-tooltip>
            </template>
          </el-table-column>
        </el-table>
        <br>
         <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="currentPage"
      :page-sizes="[3,6,9]"
      :page-size="pagesize"
      layout="total,sizes,prev,pager,next,jumper"
      :total="tableData.length"
    ></el-pagination>
      </div>
    </div>




    <el-drawer
      title="drawer"
      v-model="drawer"
      size="50%"
      :wrapperClosable="false"
      :with-header="false"
    >
      <div class="form-div">
        <h3>Company information</h3>
        <el-form :model="addComForm" ref="addComForm" label-width="150px" class="add-com-form">
          <el-form-item label="Company Name"
            prop="name_en"
            :rules="[{ required: true, message: 'Please enter the Company Name'}]"
          >
            <el-input style="width:80%" v-model="addComForm.name_en" autocomplete="off"></el-input>
          </el-form-item>
          <br />
          <el-form-item
            label="Brief Introduction"
            prop="description"
            :rules="[{ required: true, message: 'Please enter the Brief Introduction'}]"
          >
            <el-input
              style="width:80%"
              type="textarea"
              v-model="addComForm.description"
              autocomplete="off"
            ></el-input>
          </el-form-item>
          <br />
          <el-form-item
            label="GMC Report Type"
            prop="gmc_report_type"
            :rules="[{ required: true, message: 'Please enter the GMC Report Type'}]"
          >
     
              <el-select
              style="width:65%; "
              placeholder
              v-model="addComForm.gmc_report_type"
              autocomplete="off"
            >
              <el-option
                v-for="item in state_options"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <br />
          <el-form-item
            label="GMC Report Url"
            prop="gmc_report_url"
            :rules="[{ required: true, message: 'Please enter the GMC Report Url'}]"
          >
            <el-input style="width:80%" v-model="addComForm.gmc_report_url" autocomplete="off"></el-input>
          </el-form-item>
          <br />
          <el-form-item class="com-form-button">
            <el-button type="primary" @click="submitForm('addComForm')">Save</el-button>
            <el-button type="danger" @click="drawer=false">Cancel</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-drawer>
    <el-drawer
      title="branddrawer"
      v-model="branddrawer"
      size="50%"
      :wrapperClosable="false"
      :with-header="false"
    >
      <div class="form-div">
        <h3>Add Brand information</h3>
        <el-form :model="brandForm" ref="brandForm" label-width="150px" class="add-com-form">
       
          <el-form-item label="Brand Name"
            prop="name_en"
            :rules="[{ required: true, message: 'Please enter the Company Name'}]"
          >
            <el-input style="width:80%" v-model="brandForm.name_en" autocomplete="off"></el-input>
          </el-form-item>
          <br />
          <el-form-item
            label="Brief Introduction"
            prop="description"
            :rules="[{ required: true, message: 'Please enter the Brief Introduction'}]"
          >
            <el-input
              style="width:80%"
              type="textarea"
              v-model="brandForm.description"
              autocomplete="off"
            ></el-input>
          </el-form-item>
         
          <br />
          <el-form-item class="com-form-button">
            <el-button type="primary" @click="submitAddBrandForm('brandForm')">Save</el-button>
            <el-button type="danger" @click="branddrawer=false">Cancel</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-drawer>
    <el-drawer
      title="updatebranddrawer"
      v-model="updatebranddrawer"
      size="50%"
      :wrapperClosable="false"
      :with-header="false"
    >
      <div class="form-div">
        <h3>Update Brand information</h3>
        <el-form :model="brandForm" ref="brandForm" label-width="150px" class="add-com-form">
       
          <el-form-item label="Brand Name"
            prop="name_en"
            :rules="[{ required: true, message: 'Please enter the Company Name'}]"
          >
            <el-input style="width:80%" v-model="brandForm.name_en" autocomplete="off"></el-input>
          </el-form-item>
          <br />
          <el-form-item
            label="Brief Introduction"
            prop="description"
            :rules="[{ required: true, message: 'Please enter the Brief Introduction'}]"
          >
            <el-input
              style="width:80%"
              type="textarea"
              v-model="brandForm.description"
              autocomplete="off"
            ></el-input>
          </el-form-item>
         
          <br />
          <el-form-item class="com-form-button">
            <el-button type="primary" @click="submitUpdateBrandForm('brandForm')">Save</el-button>
            <el-button type="danger" @click="updatebranddrawer=false">Cancel</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-drawer>
  </div>
</template>

<script>
export default {
  data() {
    return {
      username:'',
      userid:'',
      count: 0,
      brandname: "",
      fileList: [],
      search_name: "",
      dialogVisible: false,
      dialogVisible1: false,
      branddrawer:false,
      updatebranddrawer:false,
      emptyShow: true,
      mainShow: false,
      drawer: false,
      emptyImage: '',
      brd_id: "",
      man_id: "",
      name_en: "",
      description: "",
      remake: "",
      logo:"",
      fileName: "fileName",
      gmc_report_type: "",
      gmc_report_url: "",
      tableData: [],
      table:[],
      currentPage: 1, 
      pagesize: 5, 
      isAdd: false,
      addComForm: {
        name_en: "",
        description: "",
        gmc_report_type: "",
        gmc_report_url: ""
      },
      brandForm: {
        brd_id: "",
        name_en:"",
        remark:"",
        description: ""
      },
       state_options: [
            { value:"1",  label: "TÜV" },
            { value: "2", label: "UL" }     
        ],
    };
  },
  mounted() {
    this.checkCompany();
  },
  methods: {
    handleSizeChange(size) {
      this.pagesize = size;
    },
    handleCurrentChange(currentPage) {
      this.currentPage = currentPage;
    },
    changeCompanyInfo(){
      console.log("in changeCompanyInfo");
      this.addComForm.name_en = this.name_en;
      this.addComForm.description = this.description;
      this.addComForm.gmc_report_type = this.gmc_report_type;
      this.addComForm.gmc_report_url = this.gmc_report_url;
    

      this.drawer = true;
    },
    checkCompany() {
      let objstr= window.sessionStorage.getItem("userinfo")
      let userinfo= JSON.parse(objstr)
      this.userid=userinfo.userId;
      this.username=userinfo.username;
      console.log(" userid :"+this.userid);
      this.axios.post("/getspringbootproxy/user/man-manufacturer/getManufacturerByUserID", {
        userId: this.userid,
        username:this.username
      }).then(res => {
        console.log(res);
        if (res.data.code == 504) {
          this.$message.warning(res.data.msg);
          this.emptyShow = true;
          this.mainShow = false;
          return;
        }
        if (res.data.code == 200) {
          this.$message.success("Successfully get company info!");
        
          this.man_id = res.data.man_ID;
          this.name_en = res.data.obj.name_EN;
          this.description = res.data.obj.description;
          this.gmc_report_type = res.data.obj.gmc_REPORT_TYPE;
          this.gmc_report_url = res.data.obj.gmc_REPORT_URL;
          this.emptyShow = false;
          this.mainShow = true;
          this.getBrand();
        }
      });
    },
    getBrand() {
      let objstr= window.sessionStorage.getItem("userinfo")
      let userinfo= JSON.parse(objstr)
      this.userid=userinfo.userId;
      this.username=userinfo.username;
      this.tableData = [];
      this.table = [];
      this.axios.post("/getspringbootproxy/user/pro-brand/getbrandbyuserid", {
        userId: this.userid,
        username:this.username
      }).then(res => {
      console.log(res);
        for (var i = 0; i < res.data.obj.length; i++) {
          console.log("Obj "+i+"==>"+res.data.obj[i]);
          this.tableData=res.data.obj;
         
          this.table.push({
            brdid: res.data.obj[i].brd_ID,
            name: res.data.obj[i].name_EN,
            logo: res.data.obj[i].remark
          });
        }
      });
    },

    clearbrandForm(){
        this.brandForm.brd_id="";
        this.brandForm.name_en="";
        this.brandForm.remark="";
        this.brandForm.description= "";
    },
    diaCancel() {
      this.dialogVisible = false;
      this.dialogVisible1 = false;
      this.branddrawer=false;
      updatebranddrawer=false;
      this.brandname = "";
      this.count = 0;
      this.fileList = [];
    },
    add() {
      this.isAdd = true;
      this.brandname = "";
      this.count = 0;
      this.fileList = [];
      this.clearbrandForm();
      this.branddrawer = true;
    },

    changeBrand(index, row) {
      console.log(row.brd_ID);
      this.brandForm.brd_id= row.brd_ID;
      this.brandForm.name_en= row.name_EN;
      this.brandForm.description= row.description;
      this.brandForm.remark= row.remark;
      this.isAdd = false;
      this.brd_id = row.brd_ID;
      this.brandname = row.name_En;
      this.fileList = [{name: 'logo', url: row.remark}]
      this.updatebranddrawer=true;
  
    },
    fileChange() {
      this.count++;
    },
    submitUpload() {
      if (this.brandname == "") {
        this.$message.warning("The brand name cannot be empty");
        return;
      }
      if (this.count == 0) {
        this.$message.warning("Please upload the logo");
        return;
      }
      this.$refs.upload.submit();
    },
    submitUpdate() {
      if (this.brandname == "") {
        this.$message.warning("The brand name cannot be empty");
        return;
      }
      this.$refs.upload.submit();
    },
    search() {
      this.tableData = this.table.filter(e =>
        e.name.match(this.search_name)
      );
    },
    submitForm(formName) {
      alert("Are sure you want save company information");
      let objstr= window.sessionStorage.getItem("userinfo")
      let userinfo= JSON.parse(objstr)
      this.userid=userinfo.userId;
      this.username=userinfo.username;
      this.$refs[formName].validate(valid => {
        if (valid) {
         
            this.axios.post("/getspringbootproxy/user/man-manufacturer/saveManufacturer", {
              user_id: this.userid,
              name_en: this.addComForm.name_en,
              gmc_report_type: this.addComForm.gmc_report_type,
              gmc_report_url: this.addComForm.gmc_report_url,
              description: this.addComForm.description
            }).then(res => {
              if (res.data.code == 504) {
                this.$message.warning(res.data.msg);
                return;
              }
              if (res.data.code == 200) {
                this.$message.success("Successfully added new company!");
                this.checkCompany();
              }
              this.checkCompany();
            });
          
          this.drawer = false;
        } else {
          return false;
        }
      });
    },
     deleteCompany() {
      alert("Are sure you want delete  company information and all its brands");
      let objstr= window.sessionStorage.getItem("userinfo")
      let userinfo= JSON.parse(objstr)
      this.userid=userinfo.userId;
      this.username=userinfo.username;

       
            this.axios.post("/getspringbootproxy/user/man-manufacturer/removeManufacturer", {
              user_id: this.userid,
              man_ID: this.userid
            }).then(res => {
              if (res.data.code == 504) {
                this.$message.warning(res.data.msg);
                return;
              }
              if (res.data.code == 200) {
                this.$message.success("Successfully added new company!");
                
              }
              this.checkCompany();
                this.getBrand();
            });
          

    },
    submitAddBrandForm(formName) {
      alert("Are sure you want save new brand information  "+this.brandForm.name_en);
      let objstr= window.sessionStorage.getItem("userinfo")
      let userinfo= JSON.parse(objstr)
      this.userid=userinfo.userId;
      this.username=userinfo.username;
      this.$refs[formName].validate(valid => {

            this.axios.post("/getspringbootproxy/user/pro-brand/addBrand", {
              man_ID: this.userid,
              brd_ID: this.brandForm.brd_id,
              name_EN: this.brandForm.name_en,
              description:this.brandForm.description
            }).then(res => {
               
              if (res.data.code == 504) {
                this.$message.warning(res.data.msg);
                return;
              }
              if (res.data.code == 200) {
                this.$message.success("Successfully save brand information!");
                this.branddrawer = false;
                this.getBrand();
                this.clearbrandForm();
              }
             
            });
          
      
      });
    },
     submitUpdateBrandForm(formName) {
      alert("Are sure you want save change brand information  "+this.brandForm.brd_id);
      let objstr= window.sessionStorage.getItem("userinfo")
      let userinfo= JSON.parse(objstr)
      this.userid=userinfo.userId;
      this.username=userinfo.username;
      this.$refs[formName].validate(valid => {

            this.axios.post("/getspringbootproxy/user/pro-brand/updateBrand", {
              man_ID: this.userid,
              brd_ID: this.brandForm.brd_id,
              name_EN: this.brandForm.name_en,
              description:this.brandForm.description
            }).then(res => {
               
              if (res.data.code == 504) {
                this.$message.warning(res.data.msg);
                return;
              }
              if (res.data.code == 200) {
                this.$message.success("Successfully save change brand information!");
                this.updatebranddrawer = false;
                this.getBrand();
                this.clearbrandForm();
              }
             
            });
          
      
      });
    },
    DeleteBrandForm(brdId) {
      alert("Are sure you want delete brand information  "+brdId);
      let objstr= window.sessionStorage.getItem("userinfo")
      let userinfo= JSON.parse(objstr)
      this.userid=userinfo.userId;
      this.username=userinfo.username;

            this.axios.post("/getspringbootproxy/user/pro-brand/deleteBrand", {
              man_ID: this.userid,
              brd_ID: brdId
            }).then(res => {
               
              if (res.data.code == 504) {
                this.$message.warning(res.data.msg);
                return;
              }
              if (res.data.code == 200) {
                this.$message.success("Successfully delete brand information!");
                this.getBrand();
                this.clearbrandForm();
              }
             
            });
          
    }
    
  }
};
</script>

<style scoped>
.form-div {
  float:left;
  width:80%;
  margin-left: 20px;
  height: 50rem;
}
.add-com-form {
  margin-top: 5rem;
}
.com-form-button {
  margin-left: 10px;
}
.empty-box {
  height: 20rem;
  margin-bottom:20rem;
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  align-items: center;
}
.main-box-top {
  margin-top: 2rem;
}
.info-flex {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  width: 60%;
}
</style>