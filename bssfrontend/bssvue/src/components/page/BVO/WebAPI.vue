<template>
  <div class="main-container">
      <div>
          <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/main' }">HOME</el-breadcrumb-item>
            <el-breadcrumb-item>WebAPI</el-breadcrumb-item>
          </el-breadcrumb>

      </div>
    <div class="searchfilter">
    <el-card class="box-card">
     <el-form :inline="true" :model="formFilter" class="demo-form-inline">
    <el-form-item label="API ID" class="formitem">
      <el-input v-model="formFilter.apiID" placeholder="API ID"></el-input>
    </el-form-item>
    <el-form-item label="API Name" class="formitem">
      <el-input v-model="formFilter.apiName" placeholder="API Name"></el-input>
    </el-form-item>
    <el-form-item label="SSL Support" class="formitem">
      <el-select v-model="formFilter.sslSupport" placeholder="SSL Support">
        <el-option label="All" value=""></el-option>
        <el-option label="Yes" value="Yes"></el-option>
        <el-option label="No" value="No"></el-option>
        <el-option label="None" value="None"></el-option>        
      </el-select>
    </el-form-item>
    <el-form-item label="Architectural Style"  class="formitem">
      <el-select v-model="formFilter.archStyle" placeholder="Architectural Style">
        <el-option label="All" value=""></el-option>
        <el-option label="REST" value="REST"></el-option>
        <el-option label="RPC" value="RPC"></el-option>
        <el-option label="Push/Streaming" value="Push/Streaming"></el-option> 
        <el-option label="GraphQL" value="GraphQL"></el-option> 
        <el-option label="Native/Browser" value="Push/Streaming"></el-option>        
        <el-option label="Indirect" value="Unspecified"></el-option>
        <el-option label="None" value="None"></el-option> 
        <el-option label="Null" value="Null"></el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="Authentication Model" class="formitem">
      <el-input v-model="formFilter.authModel" placeholder="Authentication Model"></el-input>
    </el-form-item>
    <el-form-item label="API Source" class="formitem">
      <el-input v-model="formFilter.apiSource" placeholder="API Source"></el-input>
    </el-form-item>
    <el-form-item label="Category/Tags" class="formitem2">
      <el-input v-model="formFilter.apiCategory" placeholder="Category/Tags"></el-input>
    </el-form-item>
    <el-form-item label="Description" class="formitem4">
      <el-input v-model="formFilter.apiDescription" placeholder="Description"></el-input>
    </el-form-item>
    <el-form-item  class="formitem4">
      <el-button type="primary"  @click="getAll()" > Search   </el-button>
    </el-form-item>


  </el-form>
  </el-card>
  </div>

   

      <el-table :data="tableData" style="width: 100%; margin-top:10px;" 
                 header-cell-style=" background-color: 		#d9edf7; ">
     
        
        <el-table-column  prop="apiID" label="ID"  width="80"></el-table-column>
        <el-table-column  prop="apiName" label="Name"  width="150"> </el-table-column>
        <el-table-column  prop="apiVersion" label="Ver No."  width="80">  </el-table-column> 
        <el-table-column  prop="sslSupport" label="SSL Support"  width="100">  </el-table-column>     
        <el-table-column  prop="authenticationModel" label="Auth Model"  width="100">  </el-table-column>
        <el-table-column  prop="architecturalStyle" label="Architectural Style"  width="150">  </el-table-column>  
        <el-table-column  prop="sslSupport" label="SSL Support"  width="150">  </el-table-column>   
        <el-table-column  prop="primaryCategory"  label="Primary Category"  width="150"> </el-table-column>        
        <el-table-column  prop="secondaryCategory"  label="Seconadry Category"  width="150"> </el-table-column>        
        <el-table-column  prop="tags" label="Tags"  width="350">  </el-table-column>
       <el-table-column type="expand" style="float:left;">
            <template #default="props" >
            <table class="insidetable" >
            <tr>
              <td class="tdleft"> Source :</td>
              <td class="tdright"> {{props.row.apiSource}} </td> 
            </tr>
            <tr>
              <td class="tdleft"> Source Url :</td>
              <td class="tdright"> <el-link type="primary">{{props.row.apiAddress}}</el-link> </td> 
            </tr>
            <tr>
              <td class="tdleft"> Request Formats :</td>
              <td class="tdright"> {{props.row.supportedRequestFormats}} </td> 
            </tr>
            <tr>
              <td class="tdleft"> Response Formats :</td>
              <td class="tdright"> {{props.row.supportedResponseFormat}} </td> 
            </tr>
            <tr>
              <td class="tdleft"> Developers No.:</td>
              <td class="tdright"> {{props.row.numDevelopers}} </td> 
            </tr>
            <tr>
              <td class="tdleft"> Followers No.:</td>
              <td class="tdright"> {{props.row.numFollowers}} </td> 
            </tr>
            <tr>
              <td class="tdleft"> Description:</td>
              <td class="tdright"> {{props.row.description}} </td> 
            </tr>     
            <tr>
              <td colspan="2" style=" padding-left:20px;">
                 <el-button type="danger"  @click="deleteCart(scope.row)" > Add to Wish List
                </el-button>
              </td>
            </tr>         

            </table>
            
            </template>
        </el-table-column>
    
      </el-table>

    <el-pagination 
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :currentPage="currentPage"
      :page-sizes="[10, 20, 50,100,200, tableData.length]"
      :page-size="pagesize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total">
    </el-pagination>



  </div>
</template>

<script>

export default {
name:"User",
 created(){

     this.getAll();


 },
  methods: {

   
        getAll(){
          this.tableData=0
          this.axios.post("/getspringbootproxy/user/webapi-user/getAll",{
            pageSize:this.pagesize,
            currentPage:this.currentPage,
            apiFilter:this.formFilter,
          }).then(res => {
                
              let resdata=res.data;
              

                if (resdata.code == 504) {
                
                  // this.$notify.error(resdata.code+resdata.msg);
                }
                if (resdata.code == 200) {
                  this.tableData=resdata.obj.records;
                  this.total=resdata.obj.total;
                  console.log(`total webapis are ${resdata.obj.total}`);

                  //this.$notify.success(resdata.msg);
                  console.log(`seccuss load all webapis`);
                }
              });
 
        },

      handleClick() {
        console.log('click');
      },
       handleSizeChange(val) {
        // console.log(`${val} items per page`);
        this.pagesize=val
        this.getAll();

      },
      handleCurrentChange(val) {
        // console.log(`current page: ${val}`);
        this.currentPage=val;
        this.getAll();
      },
      onSubmit() {
        console.log('submit!')
      },

  },
    data() {
      return {
      userForm:[],
      drawer: false,
       total:0,
      pagesize:20,
      currentPage: 1,
      tableData: [],
      multipleSelection: [],
      menu0: [],
      menu1: [],
      menu2: [],
      beingChangedUserID:0,
      search_name: "",
      drawerPr: false,
      formFilter: {
        apiID: '',
        apiName: '',
        sslSupport:'',
        authModel:'',
        archStyle:'',
        apiSource:'',
        apiCategory:'',
        apiDescription:'',
       },
     
      }
    }
}
</script>


<style scope>

@import url("//unpkg.com/element-plus/dist/index.css");

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

.insidetable tr{
 width:100%;

 height:10px;
 background-color:#f8f8ff;
}
.insidetable tr td{
 padding:10px 20px 10px 20px;
}
.tdleft{
 width:20%;
 padding:5px 5px 5px 5px;
 padding-left:20px;
 align-text:top;
 line-weight:10pt;
 height:10px;
}
.tdright{
 width:80%;
 padding:5px 5px 5px 5px;
 align-text:top;
 line-weight:10pt;
 height:10px;
 padding-left:20px;
}



.main-container{
 
  padding-right:10px;
}
.searchfilter{
padding:15px 0px 15px 0px;
margin-top:20px;
margin-bottom:10px;

min-height:40px;
vertical-align:top;
float:left;
}
.formitem{
 width:350px;
}
.formitem2{
 width:700px;
}
.formitem4{
 width:1400px;
}
</style>