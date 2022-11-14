<template>
<div>
  <table style="width:100%;">
   <td style="width:40%; vertical-align:top;">
      <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/main' }">Home</el-breadcrumb-item>
            <el-breadcrumb-item>BVO</el-breadcrumb-item>
            <el-breadcrumb-item>Wallet</el-breadcrumb-item>
          </el-breadcrumb>
   </td>
   <td style="width:60%; vertical-align:top;">
    <el-button  icon="el-icon-refresh"  @click="refFund()"  style="float:right;margin-left:5px;"></el-button>
       <el-button  type="danger" icon="el-icon-setting"  @click="saveAccount()"  style="float:right;margin-left:5px;">
       </el-button>
       <el-button id="btndeposit" icon="el-icon-bottom-left"  type="primary"  @click="checkPassword(1)" style="visibility:hidden;float:right;margin-left:5px;">Deposit</el-button>
         <el-button id="btnwithdraw" icon="el-icon-top-right" type="primary"  @click="checkPassword(2)"  style="visibility:hidden;float:right; margin-left:5px;">Withdraw</el-button>

   </td>
  </table>
   
    <div style="margin-top:2px;">
     <table style="width:100%; ">
     <tr>
      <td style="width:20%;"> 

      </td>

      <td style="float:right;width:80%;">
             
      </td>
     </tr>
     </table>

    </div>

    <el-tabs type="border-card" style="margin-top:0px;">
  <el-tab-pane label="Account Information">
   <table style="width:90%; margin-left:5%; margin-right:5%">
     <tr>
       <td style="width:30%;float:top;vertical-align:top;">
         <p style="width:25rem">
            <i class="el-icon-link"></i>
            Account No.：{{acData.buyer_ID}}
          </p>
          <br>
         <p style="width:25rem">
            <i class="el-icon-s-custom"></i>
            Account Name：{{acData.account_NAME}}
          </p>
       </td>
       <td style="width:70%;vertical-align:top;">
        <div class="bank-card">
          <p style="width:20rem; padding-left:40px;">
            <i class="el-icon-finished"></i>
            <span style="color:blue;">Total Balanace：{{acData.available_MONEY}} </span>
          </p>

          <p style="width:25rem; padding-left:40px;">
            <i class="el-icon-link"></i>
            Total Depositing:  {{acData.depositing_MONEY}}
          </p>

          <p style="width:25rem; padding-left:40px;">
            <i class="el-icon-link"></i>
            Total Withdrawing:  {{acData.withdrawing_MONEY}}
          </p>
          </div>
       </td>
     </tr>
   </table>
  
  </el-tab-pane>
  <el-tab-pane label="Transactions">
  
      <el-table  header-cell-style=" background-color: 	#d9edf7; margin-top:50px;" 
       style="width: 100%" :data="transTable.slice((transFormcurrentPage-1)*transpagesize,transFormcurrentPage*transpagesize)"
        class="table-check" @selection-change="handleSelectionChange"  >
            <el-table-column prop="transaction_AUDIT_ID" label="Trans. ID" style="width: 50px; float:left;"></el-table-column>
            <el-table-column prop="withdrawing_MONEY_ACTUAL" label="Withdraw amount" style="width: 50px;float:left;"></el-table-column>
            <el-table-column prop="depositing_MONEY_ACTUAL" label="Deposit amount" style="width: 50px;float:left;"></el-table-column>
             <el-table-column prop="commission_MONEY_ACTUAL" label="Commission" style="width: 50px;float:left;"></el-table-column>
            <el-table-column prop="commissionType.transaction_type_name" label="Type" style=" font-color:blue; width: 50px;float:left;">           
              
            </el-table-column>

            <el-table-column prop="create_TIME" label="Creation time" width="150" ></el-table-column>
         
        </el-table>
        <br>
            <el-pagination
            @size-change="handletransFormSizeChange"
            @current-change="handletransFormCurrentChange"
            :current-page="transFormcurrentPage"
            :page-sizes="[3,6,9]"
            :page-size="transpagesize"
            layout="total,sizes,prev,pager,next,jumper"
            :total="transTable.length" >
            </el-pagination>

  </el-tab-pane>
  <el-tab-pane label="Canceled requests">

    
      <el-table  header-cell-style=" background-color: 	#d9edf7; margin-top:50px;" 
       style="width: 100%" :data="cancelTable.slice((cancelFormcurrentPage-1)*cancelpagesize,cancelFormcurrentPage*cancelpagesize)"
        class="table-check" @selection-change="handleSelectionChange"  >
            <el-table-column prop="transaction_AUDIT_ID" label="Trans. ID" style="width: 50px; float:left;"></el-table-column>
            <el-table-column prop="withdrawing_MONEY_ACTUAL" label="Withdraw amount" style="width: 50px;float:left;"></el-table-column>
            <el-table-column prop="depositing_MONEY_ACTUAL" label="Deposit amount" style="width: 50px;float:left;"></el-table-column>
            <el-table-column prop="commissionType.transaction_type_name" label="Type" style=" font-color:blue; width: 50px;float:left;"></el-table-column>
            <el-table-column prop="reason_CANCEL" label="Reason" width="300" style=" font-color:blue; width: 50px;float:left;"></el-table-column>
            <el-table-column prop="create_TIME" label="Creation time" width="150" ></el-table-column>
         
        </el-table>
        <br>
            <el-pagination
            @size-change="handlecancelFormSizeChange"
            @current-change="handlecancelFormCurrentChange"
            :current-page="cancelFormcurrentPage"
            :page-sizes="[3,6,9]"
            :page-size="cancelpagesize"
            layout="total,sizes,prev,pager,next,jumper"
            :total="cancelTable.length" >
            </el-pagination>
  </el-tab-pane>

</el-tabs>

  </div>

</template>

<script>
export default {
name:"Wallet",
data() {
    return {
      username:'',
      is_add_deposit:false,
      is_add_withdraw:false,
      cancelTable:[],
      transTable:[],
      acData:{
      buyer_ID:'',
      account_NAME:'',
      available_MONEY:'',
      depositing_MONEY:'',
      withdrawing_MONEY:''
      },
      transFormcurrentPage:1,
      transpagesize: 5, 
      cancelFormcurrentPage:1,
      cancelpagesize: 5, 
      }
    },
 created(){
       this.refFund();
     },
  methods:{
  /***********************/
    handletransFormSizeChange(size) {
      this.transpagesize = size;
    },
    handletransFormCurrentChange(cPage) {
      this.transFormcurrentPage = cPage;
    },
/***********************/
    handlecancelFormSizeChange(size) {
      this.cancelpagesize = size;
    },
    handlecancelFormCurrentChange(cPage) {
      this.cancelFormcurrentPage = cPage;
    },
/***********************/
  refFund(){
    this.getAccount();
    this.getTransactions();
    this.getTransCancel();
    
  },
        applyVisible(elem,value){
            if(elem){
            elem.style.visibility=value;
            }
        },
        applyPerm(row){    
          var btndeposit = document.getElementById('btndeposit');
          var btnwithdraw=document.getElementById('btnwithdraw');
          console.log("Row-===>");
          console.log(row);
          if(row.walletAccountPerm.add_deposit==1){
            this.is_add_deposit=true;
            this.applyVisible(btndeposit,"visible");
          }else {
            this.is_add_deposit=false;
            this.applyVisible(btndeposit,"hidden");
          }
          if(row.walletAccountPerm.add_withdraw==1){
            this.is_add_withdraw=true;
             this.applyVisible(btnwithdraw,"visible");
          }else {
            this.is_add_withdraw=false;
            this.applyVisible(btnwithdraw,"hidden");
          }
    },
      saveAccount(){
      this.$prompt('Please input account password', 'Set password', {
          confirmButtonText: 'OK',
          cancelButtonText: 'Cancel'
        }).then(({ value }) => {
         
          let objstr= window.sessionStorage.getItem("userinfo")
          let userinfo= JSON.parse(objstr)
          this.userid=userinfo.userId;
         this.axios.post("/getspringbootproxy/user/wa-user/saveAccount",
           {
              buyer_ID:this.userid,
              password:value
           })
           .then(res => {
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
    checkPassword(opno){
    this.$prompt('Please enter the password', 'Login account', {
          confirmButtonText: 'OK',
          cancelButtonText: 'Cancel'
        }).then(({ value }) => {         
          let objstr= window.sessionStorage.getItem("userinfo")
          let userinfo= JSON.parse(objstr)
          this.userid=userinfo.userId;
         this.axios.post("/getspringbootproxy/user/wa-user/checkPassword",
           {
              buyer_ID:this.userid,
              password:value
           })
           .then(res => {
           console.log(res)
           let resdata=res.data;
              if(resdata.code==200){
                if(opno==1){
                this.depositBVO(value);
                }
                else if(opno==2){
                this.withdrawBVO(value);
                }                     
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
    depositBVO(pwd){
    this.$prompt('Please enter the deposit amount', 'Deposit', {
          confirmButtonText: 'OK',
          cancelButtonText: 'Cancel'
        }).then(({ value }) => {         
          let objstr= window.sessionStorage.getItem("userinfo")
          let userinfo= JSON.parse(objstr)
          this.userid=userinfo.userId;
         this.axios.post("/getspringbootproxy/user/wa-user/deposit",
           {
              user_id:this.userid,
              password:pwd,
              depositing_money:value

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
     withdrawBVO(pwd){
    this.$prompt('Please enter the withdraw amount', 'Deposit', {
          confirmButtonText: 'OK',
          cancelButtonText: 'Cancel'
        }).then(({ value }) => {         
          let objstr= window.sessionStorage.getItem("userinfo")
          let userinfo= JSON.parse(objstr)
          this.userid=userinfo.userId;
         this.axios.post("/getspringbootproxy/user/wa-user/withdraw",
           {
              user_id:this.userid,
              password:pwd,
              withdraw_money:value

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
    getAccount(){
     
          let objstr= window.sessionStorage.getItem("userinfo")
          let userinfo= JSON.parse(objstr)
          this.userid=userinfo.userId;
          this.username=userinfo.username;
          this.axios.post("/getspringbootproxy/user/wa-user/getAccount",
            {
              userId:this.userid
            })
            .then(res => {
                 console.log("Accounts");

                let resdata=res.data;
                if(resdata.code==200){
                      this.acData=resdata.obj;
                      console.log(this.acData);
                        this.acData.buyer_ID=resdata.obj.buyer_ID;
                        this.acData.account_NAME=resdata.obj.account_NAME;
                        this.acData.available_MONEY=resdata.obj.walletAccountFund.available_MONEY;
                        this.acData.depositing_MONEY=resdata.obj.walletAccountFund.depositing_MONEY;
                        this.acData.withdrawing_MONEY=resdata.obj.walletAccountFund.withdrawing_MONEY;

                    this.applyPerm(this.acData);                  
                }
                else{
                    this.$message.error(resdata.msg);
                }
            }
          );
     },

      getTransactions(){
     
          let objstr= window.sessionStorage.getItem("userinfo")
          let userinfo= JSON.parse(objstr)
          this.userid=userinfo.userId;
          this.username=userinfo.username;
          this.axios.post("/getspringbootproxy/user/wa-user/getTransactions",
            {
              userId:this.userid
            })
            .then(res => {
                 console.log("getTransactions");
                console.log(res);
                let resdata=res.data;
                if(resdata.code==200){
                  this.transTable=resdata.obj;            
                }
                else{
                    this.$message.error(resdata.msg);
                }
            }
          );
     },
      getTransCancel(){
     
          let objstr= window.sessionStorage.getItem("userinfo")
          let userinfo= JSON.parse(objstr)
          this.userid=userinfo.userId;
          this.username=userinfo.username;
          this.axios.post("/getspringbootproxy/user/wa-user/getTransCancel",
            {
              userId:this.userid
            })
            .then(res => {
                 console.log("getTransactions");
                console.log(res);
                let resdata=res.data;
                if(resdata.code==200){
                  this.cancelTable=resdata.obj;            
                }
                else{
                    this.$message.error(resdata.msg);
                }
            }
          );
     },

  },

  
}
</script>

<style scoped>

.bank-card {
   width: 32.3rem;
  height: 20rem;

  background-image: url('~@/assets/card.png');
  background-size: contain;
  display: flex;
  flex-direction: column;
  justify-content: space-around;
}
</style>