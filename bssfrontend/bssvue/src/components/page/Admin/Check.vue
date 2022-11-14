<template>
  <div> 
  <table style="width:100%;">
   <td style="width:60%; vertical-align:top;">
       <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/main' }">HOME</el-breadcrumb-item>
            <el-breadcrumb-item>Management</el-breadcrumb-item>
            <el-breadcrumb-item>Fund check</el-breadcrumb-item>
            
          </el-breadcrumb>
   </td>
   <td style="width:20%; vertical-align:top;">
   <el-button  icon="el-icon-refresh"  @click="refresh()" 
           style="float:right;margin-left:5px;display:relative;position:block; margin-top:-10px;"></el-button>
   </td>
  </table>
 
<el-tabs type="border-card">
  <el-tab-pane label="Accounts">
  
    <el-table  header-cell-style=" background-color: 	#FAEBD7; margin-top:50px;" 
       style="width: 100%" :data="accountTable"
        class="table-check" @selection-change="handleSelectionChange"  >
            <el-table-column prop="buyer_ID" label="No." style="width: 50px; float:left;"></el-table-column>
            <el-table-column prop="account_NAME" label="Name" style="width: 50px;float:left;"></el-table-column>
            <el-table-column prop="walletAccountFund.available_MONEY" label="Total Balance" style="width: 50px;float:left;"></el-table-column>
            <el-table-column prop="walletAccountFund.depositing_MONEY" label="Total Deposit." style=" font-color:blue; width: 50px;float:left;"></el-table-column>
            <el-table-column prop="walletAccountFund.withdrawing_MONEY" label="Total Withdraw." width="150" style="width: 100px;float:left;"></el-table-column>
            <el-table-column prop="walletAccountFund.commission" label="Total Com." style="width: 50px;float:left;"></el-table-column>
             <el-table-column prop="walletAccountFund.create_TIME" label="Creation Time" style="width: 100px;float:left;"></el-table-column>
            
            <el-table-column label="operation" width="300">
            <template  #default="scope">
                <el-button type="success" icon="el-icon-edit" small @click="showPermDrawer(scope.row)"  >Permissions</el-button>
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
            :total="accountTable.length" >
            </el-pagination>
  
  </el-tab-pane>
  <el-tab-pane label="Fund requests">
   <el-table  header-cell-style=" background-color: 	#FAEBD7; margin-top:50px;" 
       style="width: 100%" :data="fundTable"
        class="table-check" @selection-change="handleSelectionChange"  >
            <el-table-column prop="buyer_Id" label="Buyer Id" style="width: 50px; float:left;"></el-table-column>
            <el-table-column prop="depositing_money" label="Deposit money" style="width: 50px;float:left;"></el-table-column>
            <el-table-column prop="withdrawing_money"  label="Withdraw money" style="width: 100px;float:left;"></el-table-column>
            <el-table-column prop="commission" label="Commission" style="width: 50px;float:left;"></el-table-column>
            <el-table-column prop="commissionType.transaction_type_name" label="Commission Type"  style="width: 50px;float:left;"></el-table-column>
            
            <el-table-column label="operation" width="300"> 
            <template  #default="scope">
                <el-button type="success" icon="el-icon-edit" small  @click="passFund(scope.row)">pass</el-button>
                <el-button type="danger"  @click="canceledFund(scope.row)" icon="el-icon-delete"  > cancel 
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
            :total="fundTable.length" >
            </el-pagination>
  </el-tab-pane>

</el-tabs>
       
  <el-drawer
  title="Wallet Account Permissions"
  v-model="drawerperm"
  :direction="direction"
  :before-close="handleClose">
   <div style="width:90%; margin-left:5%;">

     <el-checkbox  v-model="permForm.is_direct_deposit" style="checkperm">
       Depositing directly without send requests to administration</el-checkbox>
    <br>     <br>
       <el-checkbox  v-model="permForm.is_direct_withdraw" style="checkperm">
       Withdrawing directly without send the requests to administration</el-checkbox>
  <br>     <br>
        <el-checkbox  v-model="permForm.is_direct_transaction" style="checkperm">
       Transactions directly without send the requests to administration</el-checkbox>
  <br>     <br>
       <el-checkbox  v-model="permForm.add_deposit" style="checkperm">
       Enable depositing</el-checkbox>
         <br>  <br>
        <el-checkbox  v-model="permForm.add_withdraw" style="checkperm">
       Enable withdrawing</el-checkbox>
       <br><br>

       <el-button type="primary" @click="savePerms" >Save</el-button>

    

  </div>
</el-drawer>

  </div>
</template>

<script>0
export default {
name:"Check",
data() {
      return {
      
        dialogTableVisible: false,
        dialogFormVisible: false,
        fundTable:[],
        accountTable:[],
        drawerperm:false,
        permForm:{
            buyer_id:'',
            is_direct_deposit:true,
            is_direct_withdraw:false,
            is_direct_transaction:false,
            add_deposit:false,
            add_withdraw:false
        },
        permForm1:{
            buyer_id:'',
            is_direct_deposit:0,
            is_direct_withdraw:0,
            is_direct_transaction:0,
            add_deposit:0,
            add_withdraw:0
        },
      };
    },
   created(){
         this.refresh();
     },
  methods:{
     refresh(){
      this.getallaccounts();
         this.getallfunds();
     },
    showPermDrawer(row){   
        console.log("Row===>");
        console.log(row); 
        this.permForm.buyer_id=row.buyer_ID;
        if(row.walletAccountPerm.is_direct_deposit==1){
            this.permForm.is_direct_deposit=true;
          }else {
            this.permForm.is_direct_deposit=false;
          }      
          if(row.walletAccountPerm.is_direct_withdraw==1){
            this.permForm.is_direct_withdraw=true;
          }else {
            this.permForm.is_direct_withdraw=false;
          }
          if(row.walletAccountPerm.is_direct_transaction==1){
            this.permForm.is_direct_transaction=true;
          }else {
            this.permForm.is_direct_transaction=false;
          }
          if(row.walletAccountPerm.add_deposit==1){
            this.permForm.add_deposit=true;
          }else {
            this.permForm.add_deposit=false;
          }
          if(row.walletAccountPerm.add_withdraw==1){
            this.permForm.add_withdraw=true;
          }else {
            this.permForm.add_withdraw=false;
          }
          this.drawerperm=true;
    },
    passFund(row){
      var result = confirm("Do you Want accept this request?");
      if (result) {
          this.axios.post("/getspringbootproxy/user/wa-user/fundPass",
            {
              buyer_id:row.buyer_id
            })
            .then(res => {
                let resdata=res.data;
                if(resdata.code==200){
                    this.$message.success(resdata.msg);
                   this.getallfunds();                
                }
                else{
                    this.$message.error(resdata.msg);
                }
            }
          );
      }
      this.dialogFormVisible = false;
   },
   canceledFund(row){
    this.$prompt('PLease enter the reason canceled', 'Reason Canceled', {
          confirmButtonText: 'OK',
          cancelButtonText: 'Cancel'
        }).then(({ value }) => {         
          let objstr= window.sessionStorage.getItem("userinfo")
          let userinfo= JSON.parse(objstr)
          this.userid=userinfo.userId;
         this.axios.post("/getspringbootproxy/user/wa-user/canceledfund",
           {
              buyer_id:this.userid,
              cancelreason:value
           })
           .then(res => {
           console.log(res)
           let resdata=res.data;
              if(resdata.code==200){
                 this.$message.success(resdata.msg);                              
              }
              else{
               this.$message.error(resdata.msg);
              }
            }
          );       
        }).catch(() => {
          this.$message({
            type: 'info',
            message: 'Input canceled'
          });
        });
    },
    getallaccounts(){
     
          let objstr= window.sessionStorage.getItem("userinfo")
          let userinfo= JSON.parse(objstr)
          this.userid=userinfo.userId;
          this.username=userinfo.username;
          this.axios.post("/getspringbootproxy/user/wa-user/getAll",
            {
              userId:this.userid
            })
            .then(res => {
                 console.log("Accounts");
                 console.log(res);
                let resdata=res.data;
                if(resdata.code==200){
                    this.accountTable=resdata.obj;                  
                }
                else{
                    this.$message.error(resdata.msg);
                }
            }
          );
   },
  getallfunds(){
     
          let objstr= window.sessionStorage.getItem("userinfo")
          let userinfo= JSON.parse(objstr)
          this.userid=userinfo.userId;
          this.username=userinfo.username;
          this.axios.post("/getspringbootproxy/user/wa-user/getAllfund",
            {
              userId:this.userid
            })
            .then(res => {
                 console.log(res);
                let resdata=res.data;
                if(resdata.code==200){
                    this.fundTable=resdata.obj;                  
                }
                else{
                    this.$message.error(resdata.msg);
                }
            }
          );
      
      this.dialogFormVisible = false;
   },
       cvtPermInt(){   
 
        if(this.permForm.is_direct_deposit==true){
            this.permForm1.is_direct_deposit=1;
          }else {
            this.permForm1.is_direct_deposit=0;
          }      
          if(this.permForm.is_direct_withdraw==true){
            this.permForm1.is_direct_withdraw=1;
          }else {
            this.permForm1.is_direct_withdraw=0;
          }
          if(this.permForm.is_direct_transaction==true){
            this.permForm1.is_direct_transaction=1;
          }else {
            this.permForm1.is_direct_transaction=0;
          }
          if(this.permForm.add_deposit==true){
            this.permForm1.add_deposit=1;
          }else {
            this.permForm1.add_deposit=0;
          }
          if(this.permForm.add_withdraw==true){
            this.permForm1.add_withdraw=1;
          }else {
            this.permForm.add_withdraw=0;
          }

    },
  savePerms(){
      var result = confirm("Do you want saved permissions?");
      if (result) {
          this.cvtPermInt();
          console.log("this.permForm.buyer_Id==>"+this.permForm.buyer_id);
          console.log(this.permForm);
          this.axios.post("/getspringbootproxy/user/wa-user/savePerm",
            {
            buyer_id: this.permForm.buyer_id,
            is_direct_deposit:this.permForm1.is_direct_deposit,
            is_direct_withdraw:this.permForm1.is_direct_withdraw,
            is_direct_transaction:this.permForm1.is_direct_transaction,
            add_deposit:this.permForm1.add_deposit,
            add_withdraw:this.permForm1.add_withdraw
            })
            .then(res => {
                let resdata=res.data;
                if(resdata.code==200){
                    this.$message.success(resdata.msg);
                   //this.getallfunds();                
                }
                else{
                    this.$message.error(resdata.msg);
                }
            }
          );
      }
      this.dialogFormVisible = false;
   },
   refFund(){
   alert("Refresh all fund");
   },
  },
}
</script>

<style scoped>

.checkperm{
 margin-bottom:20px;
 padding-top:10px;
 width:100%;
}

</style>