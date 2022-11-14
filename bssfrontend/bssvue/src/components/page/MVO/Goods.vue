<template>
  <div>
      <table style="width:100%;">
   <td style="width:40%; vertical-align:top;">
      <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/main' }">Home</el-breadcrumb-item>
            <el-breadcrumb-item>MVO</el-breadcrumb-item>
            <el-breadcrumb-item>Goods</el-breadcrumb-item>
          </el-breadcrumb>
   </td>
   <td style="width:60%; vertical-align:top;">
    <el-button  icon="el-icon-refresh"  @click="refFund()"  style="float:right;margin-left:5px;"></el-button>
   </td>
  </table>

 <br />
    <br />
      <div>
        <span>
          Search：
          <el-input
            style="width:200px"
            placeholder="Goods title"
            @input="search"
            v-model="search_goodstitle"
          ></el-input>
        </span>
        <el-button type="danger" icon="el-icon-search"></el-button>
        <el-button  plain icon="el-icon-plus" @click="add">Add</el-button>
    </div>
  <el-divider></el-divider>
      <el-table
      :data="tableData"
      style="width: 100%"
      class="table-check"
      @selection-change="handleSelectionChange"
      min-height="850"
    >
    <el-table-column type="selection" width="50"></el-table-column>
      <el-table-column prop="proId" label="Goods id"></el-table-column>
      <el-table-column prop="title" label="Goods title"></el-table-column>
      <el-table-column
        prop="keyWords"
        label="Goods type"
        :filter-method="filterHandler"
        :filters="[{ text: 'Phone', value: 'Phone' },{ text: 'Computer', value: 'Computer' }, { text: 'Instrument', value: 'Instrument'},
         { text: 'Appliances', value: 'Appliances'}]"
      ></el-table-column>
      <el-table-column prop="remark" label="Goods picture">
        <template  #default="scope">
          <img :src="scope.row.remark" width="100" />
        </template>
      </el-table-column>
      <el-table-column prop="retailPrice" label="Goods price"></el-table-column>
      <el-table-column prop="replenishmentPeriod" label="Stock"></el-table-column>
      <el-table-column
        prop="stsCd"
        label="State"
        :filter-method="filterHandler"
        :filters="[{ text: 'Not in warehouse', value: 'Not in warehouse' },{ text: 'In warehouse', value: 'In warehouse' }, { text: 'On shelf', value: 'On shelf'}]"
      >
        <template #default="scope">
          <el-tag :type="tag(scope.row.stsCd)" disable-transitions>{{scope.row.stsCd}}</el-tag>
        </template>
      </el-table-column>

      <el-table-column label="operation" width="250">
        <template  #default="scope">
          <el-button type="success" size="small" icon="el-icon-edit" @click="edit(scope.row)"></el-button>
       
          <el-button
            type="danger"
            size="small"
            icon="el-icon-delete"
             @click="deleteProduct(scope.row)"
          ></el-button>
          <el-button
            :type="btntag(scope.row.stsCd)"
            size="small"
            @click="operate(scope.row)"
          >{{btn(scope.row.stsCd)}}</el-button>
        </template>
      </el-table-column>
    </el-table>

      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[5,10,20,total]"
          :page-size="pagesize"
          layout="total,sizes,prev,pager,next,jumper"
          :total="total"
        ></el-pagination>   

<br />
 <br />
    <span> </span>
    <el-button @click="batch(msg[0])" type="danger">Push</el-button>
    <el-button @click="batch(msg[1])" type="success">Shelve</el-button>
    <el-button @click="batch(msg[2])" type="warning">Unshelve</el-button>
  

      <el-drawer title="drawer" v-model="drawer" size="60%" :with-header="false"  style="overflow-y: scroll">
      <div class="form-div">
        <h3>Goods information</h3>
        <el-form
          :model="addGoodsForm"
          ref="addGoodsForm"
          label-width="150px"
          class="add-goods-form"
           
        >
     
          <el-form-item
            label="Goods title"
            prop="title"
            :rules="[{ required: true, message: 'Please enter the goods title'}]"
          >
            <el-input style="width:35rem" v-model="addGoodsForm.title" autocomplete="off"></el-input>
          </el-form-item>
          <br />
      
         
         
          <el-form-item
            label="Goods type"
            prop="keyWords"
            :rules="[{ required: true, message: 'Please choose the Goods type', trigger: 'blur'}]"
          >
            <el-select
              style="width:35rem"
              placeholder
              v-model="addGoodsForm.keyWords"
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
          <br />


          <br />
          <el-form-item label="Goods brand" prop="brdName"> 
            <!-- :rules="[{ required: true, message: 'Please choose the Goods brand', trigger: 'blur'}]" -->

            <el-select
              style="width:35rem"
              placeholder
              v-model="addGoodsForm.brdName"
               
              autocomplete="off"
            >
              <el-option
                v-for="item in brand_options"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <br />
          <el-form-item
            label="Goods State"
            prop="stsCd"
            :rules="[{ required: true, message: 'Please choose the Goods State', trigger: 'blur'}]"
          >
            <el-select
              style="width:35rem"
              placeholder
              v-model="addGoodsForm.stsCd"
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
            label="Goods model"
            prop="model"
            :rules="[{ required: true, message: 'Please enter the Goods model'}]"
          >
            <el-input style="width:35rem" v-model="addGoodsForm.model" autocomplete="off"></el-input>
          </el-form-item>
          <br />
          <el-form-item
            label="Goods price"
            prop="retailPrice"
            :rules="[{ required: true, message: 'Please enter the Goods price'}]"
          >
            <el-input style="width:35rem" v-model="addGoodsForm.retailPrice" autocomplete="off"></el-input>
          </el-form-item>
        
        

          <el-form-item
            label="Goods stocks"
            prop="replenishmentPeriod"
            :rules="[{ required: true, message: 'Please enter the Goods stocks'}]"
          >
            <el-input
              style="width:35rem"
              v-model="addGoodsForm.replenishmentPeriod"
              autocomplete="off"
            ></el-input>
          </el-form-item>
          <br />
          <el-form-item
            label="Warranty period"
            prop="warrantyDay"
            :rules="[{ required: true, message: 'Please enter the Warranty period'}]"
          >
            <el-input style="width:35rem" v-model="addGoodsForm.warrantyDay" autocomplete="off"></el-input>
          </el-form-item>
          <br />
          <div class="flex">
            <div>
              <span>Goods picture:</span>
              <br />
              <br />
              <el-upload
                style="width:10rem"
                ref="upload"
                drag
                action="getspringbootproxy/user/pro-product/uploadPicture"
                :name="fileName"
                :file-list="fileList"
                :on-success="handleSuccess"
                :on-change="fileChange"
                :before-upload="uploadPicture"
                :auto-upload="false"
                list-type="picture"
                :limit="1"
              >
                <i class="el-icon-upload"></i>
                <div class="el-upload__text">
                  Drag the file here, or
                  <em>Click to upload</em>
                </div>
                <div
                  class="el-upload__tip"
                  slot="tip"
                >It is recommended to upload JPG/PNG files and not exceed 500kb</div>
              </el-upload>
            </div>
            <div>
              <span>Goods parameters:</span>
              <br />
              <br />
              <el-form-item
                label="Length(cm)"
                prop="length"
                :rules="[{ required: true, message: 'Please enter the length'}]"
              >
                <el-input style="width:12rem" v-model="addGoodsForm.length" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item
                label="Width(cm)"
                prop="width"
                :rules="[{ required: true, message: 'Please enter the width'}]"
              >
                <el-input style="width:12rem" v-model="addGoodsForm.width" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item
                label="Height(cm)"
                prop="height"
                :rules="[{ required: true, message: 'Please enter the height'}]"
              >
                <el-input style="width:12rem" v-model="addGoodsForm.height" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item
                label="Weight(kg)"
                prop="weight"
                :rules="[{ required: true, message: 'Please enter the weight'}]"
              >
                <el-input style="width:12rem" v-model="addGoodsForm.weight" autocomplete="off"></el-input>
              </el-form-item>
            </div>
          </div>
          <br />
          <span>eBay description：</span>
        
          <el-input
            type="textarea"
            :rows="2"
            placeholder="input eBay description "
            v-model="addGoodsForm.ebay_description">
          </el-input>
          <br />
          <span>Amazon description：</span>
      
          <el-input
            type="textarea"
            :rows="2"
            placeholder="input amazon description "
            v-model="addGoodsForm.amazon_description">
          </el-input>
          <el-form-item class="goods-form-button">
            
            <el-button type="danger" @click="submitForm('addGoodsForm')">Save </el-button>
           
             <el-button  @click="cancelDrawer()" >Cancel</el-button>
           
      	
          </el-form-item>
        
        </el-form>
      </div>
      
    </el-drawer>
    
  </div>
      
  
</template>

<script>
export default {
name:"Goods",
data(){
  return{
    type_options: [
        { value: "Phone", label: "Phone"},
        { value: "Instrument", label: "Instrument" },
        { value: "Laptops", label: "Laptops" }
    ],
        brand_options: [
        { value: "xiaomi", label: "xiaomi" },
         
        { value: "huawei", label: "huawei" },
        { value: "Lenovo", label: "Lenovo" },
        { value: "Apple", label: "Apple" },
        { value: "HP", label: "HP" },
        { value: "Dell", label: "Dell" },
        { value: "Samsung", label: "Samsung" },
    ],

    state_options: [
        { value:"On shelf",  label: "On shelf" },
        { value: "In warehouse", label: "In warehouse" },
        { value: "Not in warehouse", label: "Not in warehouse" }
        
    ],
    search_goodstitle:"",
    pagesize:10,
    currentPage:1,
    table:[],
    tableData:[],
    fileName: "fileName",
    drawer: false,
    isAdd:false,
    addGoodsForm:[],
     multipleSelection: [],
      msg: ["Not in warehouse", "In warehouse", "On shelf"]
   
  }
},
created(){
  this.getPaginationProductInfo();

},
methods:{
refresh(){
  this.getPaginationProductInfo();
},
   handleSizeChange(val) {
        console.log(`${val} items per page`);
         this.pagesize = val;
         this.getPaginationProductInfo();
      },
      handleCurrentChange(val) {
        console.log(`current page: ${val}`);
         this.currentPage = val;
         this.getPaginationProductInfo();
      },
  filterHandler(value, row, column) {
      const property = column["property"];
      return row[property] === value;
    },
  search(val){
    console.log(val)
    this.search_goodstitle=val;
    this.tableData = this.table.filter(e =>
        e.title.match(this.search_goodstitle)
      );
  },
   btntag(msg) {
      if (msg == "Not in warehouse") return "danger";
      if (msg == "In warehouse") return "success";
      if (msg == "On shelf") return "warning";
    },
    btn(msg) {
      if (msg == "Not in warehouse") return "Push";
      if (msg == "In warehouse") return "Shelve";
      if (msg == "On shelf") return "Unshelve";
    },
    tag(msg) {
      if (msg == "Not in warehouse") return "danger";
      if (msg == "In warehouse") return "warning";
      if (msg == "On shelf") return "success";
    },
  getPaginationProductInfo(){
      let objstr= window.sessionStorage.getItem("userinfo")
      let userinfo= JSON.parse(objstr)
      this.userid=userinfo.userId;
      this.username=userinfo.username;
      this.axios.post("/getspringbootproxy/user/pro-product/getAllProduct2",
              {
               currentPage: this.currentPage,
							 pageSize: this.pagesize,
               userId:this.userid,
               username:this.username
               }).then(res => {
                let resdata=res.data;
                if(resdata.code==200){
                    console.log(resdata)
                  //handle pic url
                  if(resdata.obj.records.length>0){
                    let j=0;
                    let len=resdata.obj.records.length;
                   
                  }
                  // this.goods = resdata.obj;
                  console.log(resdata)
                  // this.tableData=resdata.obj;
              this.tableData =resdata.obj.records
              this.table = resdata.obj.records;
               this.total=resdata.obj.total   

      }else{  
        this.$notify.warning(res.message);
      }
    });         
    },

   handleSuccess(res){
    

   console.log(res)
      this.addGoodsForm.remark=res.obj
      let objstr= window.sessionStorage.getItem("userinfo")
      let userinfo= JSON.parse(objstr)
      this.userid=userinfo.userId;
      this.username=userinfo.username;

      this.axios.post("getspringbootproxy/user/pro-product/saveProduct", {
              brdName: this.addGoodsForm.brdName,
              retailPrice: this.addGoodsForm.retailPrice,
              skuCd: this.addGoodsForm.sku_cd,
              title: this.addGoodsForm.title,
              stsCd: this.addGoodsForm.stsCd,
              createdBy: this.addGoodsForm.created_by,
              creationDate: this.addGoodsForm.creation_date,
              ean: this.addGoodsForm.ean,
              nameEn: this.addGoodsForm.name_en,
              ebayDescription: this.addGoodsForm.ebay_description,
              amazonDescription: this.addGoodsForm.amazon_description,
              keyWords: this.addGoodsForm.keyWords,
              width: this.addGoodsForm.width,
              height: this.addGoodsForm.height,
              weight: this.addGoodsForm.weight,
              length: this.addGoodsForm.length,
              model: this.addGoodsForm.model,
              replenishmentPeriod: this.addGoodsForm.replenishmentPeriod,
              warrantyDay: this.addGoodsForm.warrantyDay,
              remark: this.addGoodsForm.remark,
              userId: this.userid,
              proId: this.addGoodsForm.proId

            }).then(res => {

                let resdata=res.data;

              if (resdata.code == 504) {
                this.$message.warning(resdata.msg);
                this.$refs["addGoodsForm"].resetFields();
                this.addGoodsForm.ebay_description = "";
                this.addGoodsForm.amazon_description = "";
                this.count = 0;
                this.fileList = [];
                return;
              }
              if (resdata.code == 200) {
                this.$message.success(resdata.msg);
                // this.refresh();
                this.$refs["addGoodsForm"].resetFields();
                this.addGoodsForm.ebay_description = "";
                this.addGoodsForm.amazon_description = "";
                this.count = 0;
                this.fileList = [];
                this.drawer=false;

                 this.$router.go(0);
                return;
              }
            });
  },
  
  
 
 edit(row){

     console.log(row)
     this.addGoodsForm=row
            console.log( this.addGoodsForm.proId)
            this.drawer = true;
            // this.productForm=row
            // console.log( this.productForm.proId)
            // this.drawer1 = true;




  },
  handleSelectionChange(val) {
      this.multipleSelection = val;
    },
operate(row){

 let nextStage = "";
      if (row.stsCd == "Not in warehouse") {
        nextStage = "In warehouse";
      }
      if (row.stsCd == "In warehouse") {
        nextStage = "On shelf";
      }
      if (row.stsCd == "On shelf") {
        nextStage = "Not in warehouse";
      }
  this.axios.post("getspringbootproxy/user/pro-product/saveProduct", {
           proId: row.proId,
          userId: this.userid,
              stsCd:nextStage,

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
   submitForm(formName) {
      this.$refs[formName].validate(valid => {
          this.$refs.upload.submit();
       
      });
   },
  add(){
      this.drawer =true;
  },
    deleteProduct(row){
       this.addGoodsForm=row

     this.$confirm('This will permanently delete the file. Continue?', 'Warning', {
          confirmButtonText: 'OK',
          cancelButtonText: 'Cancel',
          type: 'warning'
        }).then(() => {
           this.axios.post("/getspringbootproxy/user/pro-product/deleteById",{
            proId:this.addGoodsForm.proId,
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

batch(msg) {
      if (this.multipleSelection == 0) {
        this.$message.warning("Please select item");
        return;
      }
      if (
        this.multipleSelection.filter(e => e.stsCd == msg).length !=
        this.multipleSelection.length
      ) {
        this.$message.warning(`Please select items that ${msg}`);
        return;
      }
     let nextStage = "";
      if (msg == "Not in warehouse") {
        nextStage = "In warehouse";
      }
      if (msg == "In warehouse") {
        nextStage = "On shelf";
      }
      if (msg == "On shelf") {
        nextStage = "Not in warehouse";
      }
      this.multipleSelection.forEach(element => { this.axios.post("getspringbootproxy/user/pro-product/saveProduct", {
           proId: element.proId,
          userId: this.userid,
              stsCd:nextStage,

          }).then(res => {
                console.log(res)
              let resdata=res.data;


                if (resdata.code == 504) {
                
                  this.$notify.error(resdata.code+resdata.msg);
                }
                if (resdata.code == 200) {
                
                 
                  this.$router.go(0)
                }
              });
 });
      this.$message.success("Success");

},

cancelDrawer(){
   this.$refs["addGoodsForm"].resetFields();
               
                this.count = 0;
                this.fileList = [];
                this.drawer=false;

                  this.$router.go(0);
}
}
}
</script>

<style>
.goods-form-button {
  margin-top: 2rem;
  margin-left: 30rem;
}

.flex {
  display: flex;
  flex-direction: row;
  justify-content: space-around;
}

 .el-drawer__body{
  overflow-y: auto  !important
}  
  /* #eldrawer{
    overflow-y: auto
  } */


.form-div {
  margin-left: 3rem;
  
}
.add-goods-form {
  margin-top: 2rem;
}
.top {
  display: flex;
  flex-direction: row;
  width: 20%;
  font-size: 15px;
  font-weight: bold;
  justify-content: space-between;
}
.table-check >>> .el-checkbox__input.is-checked .el-checkbox__inner,
.el-checkbox__input.is-indeterminate .el-checkbox__inner {
  background-color: #f56c6c;
  border-color: #f56c6c;
}
</style>