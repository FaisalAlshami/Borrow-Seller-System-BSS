<template>
  <div>
    <table style="width:100%;">
   <td style="width:40%; vertical-align:top;">
          <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/main' }">HOME</el-breadcrumb-item>
             <el-breadcrumb-item>Management</el-breadcrumb-item>
            <el-breadcrumb-item>Commissions Management</el-breadcrumb-item>
            
          </el-breadcrumb>
   </td>
   <td style="width:60%; vertical-align:top;">
      <el-button  icon="el-icon-refresh"  @click="refresh()"  style="float:right;margin-left:5px;"></el-button>
     <el-button type="primary" icon="el-icon-circle-plus-outline" 
     class="btnadd" @click="dialogFormVisible = true" style="float:right;margin-left:5px;"></el-button>
  
   </td>
  </table>

    
  
  <el-dialog title="Add Role Commission" v-model="dialogFormVisible">
  <el-form :model="form">
   
    <el-form-item label="Commission Type  " :label-width="formLabelWidth">
      <el-select v-model="commform.commission_type" placeholder="Commission Type">
        <el-option label="1- Deposit" value="1"></el-option>
        <el-option label="2- Withdraw" value="2"></el-option>
         <el-option label="3- Transaction" value="3"></el-option>
      </el-select>
    </el-form-item>
     <el-form-item
           label="Percent Commission"
            prop="amount">
            <el-input style="width:30%" v-model="commform.amount" autocomplete="off"></el-input>
          </el-form-item>

  </el-form>
  <template #footer>
    <span class="dialog-footer">
      <el-button @click="dialogFormVisible = false" >Cancel</el-button>
      <el-button type="primary" @click="addComm">Confirm</el-button>
    </span>
  </template>
</el-dialog>

<br>
     <el-table  header-cell-style=" background-color: 	#FAEBD7;" 
       style="width: 100%" :data="commTable"
        class="table-check" @selection-change="handleSelectionChange"  >
            <el-table-column prop="commission_id" label="Comm No." style="width: 50px; float:left;"></el-table-column>
            <el-table-column prop="percent" label="Comm Percent %" style="width: 50px;float:left;"></el-table-column>
            <el-table-column prop="commissionType.transaction_type_name" label="Commission Type" 
            style="width: 50px;float:left;"></el-table-column>
            <el-table-column label="operation" width="200">
            <template  #default="scope">
                <el-button type="primary" icon="el-icon-edit" circle @click="editcomm(scope.row)"></el-button>
                <el-button type="danger"  @click="deleteComm(scope.row)" icon="el-icon-delete" circle > 
                </el-button>
            </template>
            </el-table-column>
        </el-table>
        <br>
            <el-pagination
            @size-change="handleSentFormSizeChange"
            @current-change="handleSentFormCurrentChange"
            :current-page="sentFormcurrentPage"
            :page-sizes="[3,6,9]"
            :page-size="Sentpagesize"
            layout="total,sizes,prev,pager,next,jumper"
            :total="commTable.length" >
            </el-pagination>
  </div>
</template>

<script>
export default {
name:"Check",
    data() {
      return {
      
        dialogTableVisible: false,
        dialogFormVisible: false,
        commTable:[],
        commform: {
          commission_id: '',
          commission_type: '',
          amount: ''
        },
        formLabelWidth: '120px'
      };
    },
     created(){
         this.refresh();
     },
  methods:{
  refresh(){
  this.getallcomm();
  },
  addcomm(){
    console.log(row);
     this.commform.commission_id=0;
      this.dialogFormVisible=true;

   },
   editcomm(row){
    console.log(row);
     this.commform.commission_id=row.commission_id;
     this.commform.commission_type=row.transaction_type_id;
     this.commform.amount=row.percent;
      this.dialogFormVisible=true;

   },
   addComm(){
      var result = confirm("Do you Want add this role commission?");
      if (result) {
          let objstr= window.sessionStorage.getItem("userinfo")
          let userinfo= JSON.parse(objstr)
          this.userid=userinfo.userId;
          this.username=userinfo.username;
          this.axios.post("/getspringbootproxy/user/wa-comm/saveComm",
            {
              user_id:this.userid,
              commission_id:this.commform.commission_id,
              commission_type:this.commform.commission_type,
              amount:this.commform.amount
            })
            .then(res => {
                let resdata=res.data;
                if(resdata.code==200){
                    this.$message.success(resdata.msg);
                   this.getallcomm();
                  
                }
                else{
                    this.$message.error(resdata.msg);
                }
            }
          );
      }
      this.dialogFormVisible = false;
   },
   
      deleteComm(row){
      var result = confirm("Do you Want delete this role commission?");
      if (result) {
          let objstr= window.sessionStorage.getItem("userinfo")
          let userinfo= JSON.parse(objstr)
          this.userid=userinfo.userId;
          this.username=userinfo.username;
          this.axios.post("/getspringbootproxy/user/wa-comm/deleteComm",
            {
              user_id:this.userid,
              commission_id:row.commission_id
            })
            .then(res => {
                let resdata=res.data;
                if(resdata.code==200){
                    this.$message.success(resdata.msg);
                   this.getallcomm();                  
                }
                else{
                    this.$message.error(resdata.msg);
                }
            }
          );
      }
      this.dialogFormVisible = false;
   },
   getallcomm(){
     
          let objstr= window.sessionStorage.getItem("userinfo")
          let userinfo= JSON.parse(objstr)
          this.userid=userinfo.userId;
          this.username=userinfo.username;
          this.axios.post("/getspringbootproxy/user/wa-comm/getAll",
            {
              userId:this.userid
            })
            .then(res => {
                 console.log(res);
                let resdata=res.data;
                if(resdata.code==200){
                    this.commTable=resdata.obj;                  
                }
                else{
                    this.$message.error(resdata.msg);
                }
            }
          );
      
      this.dialogFormVisible = false;
   },
  }
}
</script>

<style scoped>

.topcontainer{
height:40px;
margin-top:20px;
width:100%; 
border-color: #409EFF;
border-bottom: 2px solid #409EFF;


}
.topcontainer .amount{
 height:40px;
 width:70px;
 float:left;
 background-color: #409EFF; 
 margin-bottom:10px;
}
.topcontainer .btnadd{
 float:right; 
 margin-bottom:-10px;
 position:block;
}
</style>