<template>
<div class="block">
  <table style="width:100%;">
   <td style="width:40%; vertical-align:top;">
     
          <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/main' }">HOME</el-breadcrumb-item>
             <el-breadcrumb-item>MVO</el-breadcrumb-item>
            <el-breadcrumb-item>Orders Management</el-breadcrumb-item>
          </el-breadcrumb>
   </td>
   <td style="width:60%; vertical-align:top;">
    <el-button  icon="el-icon-refresh"  @click="refresh()"  style="float:right;margin-left:5px;"></el-button>
    
   </td>
  </table>
  <div>
  </div>
      <div style="margin-top:20px;">
        <span>
          Search：
          <el-input
            style="width:200px"
            placeholder="product title"
            @input="search"
            v-model="search_goodstitle"
          ></el-input>
        </span>
        <el-button type="danger" icon="el-icon-search" @click="viewAll" style="margin-left:10px;"></el-button>
    </div>

  <br>
  <div>   
     <el-tabs type="border-card">
     <el-tab-pane label="Received Orders">
 
       <el-table 
       header-cell-style=" background-color: 		#d9edf7;" 
        style="width: 100%" :data="ReceivedForm.slice((ReceivedFormcurrentPage-1)*Receivedpagesize,ReceivedFormcurrentPage*Receivedpagesize)"
        class="table-check" @selection-change="handleSelectionChange"  >
            <el-table-column prop="or_id" label="Order id" style="width: 20px;"></el-table-column>
            <el-table-column prop="remark" label="Picture">
            <template  #default="scope">
            <img :src="scope.row.proProduct.remark" width="60" height="60" />
            </template>
            </el-table-column>
            <el-table-column prop="proProduct.title" label="Product Title" width="200"></el-table-column>
            <el-table-column prop="order_ID" label="Order No."></el-table-column>
            <el-table-column prop="qty" label="Qty"></el-table-column>
            
            <el-table-column prop="proStatus.statusName" label="Status"></el-table-column>
            <el-table-column prop="order_CREATED_TIME" label="Create time"></el-table-column>
            <el-table-column label="operation" width="250">
            <template  #default="scope">
                 <el-button type="success"  @click="AllowedOrder(scope.row)" > Allowed
                </el-button>
                <el-button type="danger"  @click="cancelOrder(scope.row)" > Cancel
                </el-button>
            </template>
            </el-table-column>
        </el-table>
        <br>
            <el-pagination
            @size-change="handleReceivedFormSizeChange"
            @current-change="handleReceivedFormCurrentChange"
            :current-page="ReceivedFormcurrentPage"
            :page-sizes="[3,6,9]"
            :page-size="Receivedpagesize"
            layout="total,sizes,prev,pager,next,jumper"
            :total="ReceivedForm.length" >
            </el-pagination>
      </el-tab-pane>

      <el-tab-pane label="Awaiting Payment">

     
       <el-table  header-cell-style=" background-color: 		#d9edf7; " 
       style="width: 100%" :data="PaymentForm.slice((PaymentFormcurrentPage-1)*Paymentpagesize,PaymentFormcurrentPage*Paymentpagesize)"
        class="table-check" @selection-change="handleSelectionChange"  >
            <el-table-column prop="or_id" label="Order id" style="width: 20px;"></el-table-column>
            <el-table-column prop="remark" label="Picture">
            <template  #default="scope">
            <img :src="scope.row.proProduct.remark" width="60" height="60" />
            </template>
            </el-table-column>
            <el-table-column prop="proProduct.title" label="Product Title" width="200"></el-table-column>
            <el-table-column prop="order_ID" label="Order No."></el-table-column>
            <el-table-column prop="qty" label="Qty"></el-table-column>
            
            <el-table-column prop="proStatus.statusName" label="Status"></el-table-column>
            <el-table-column prop="order_CREATED_TIME" label="Creation time"></el-table-column>
           
        </el-table>
        <br>
            <el-pagination
            @size-change="handlePaymentFormSizeChange"
            @current-change="handlePaymentFormCurrentChange"
            :current-page="PaymentFormcurrentPage"
            :page-sizes="[3,6,9]"
            :page-size="Paymentpagesize"
            layout="total,sizes,prev,pager,next,jumper"
            :total="PaymentForm.length" >
            </el-pagination>
      </el-tab-pane>

      <el-tab-pane label="Awaiting Shipmemt">

     
       <el-table  header-cell-style=" background-color: 		#d9edf7; " 
       style="width: 100%" :data="PaidForm.slice((PaidFormcurrentPage-1)*PaidFormpagesize,PaidFormcurrentPage*Paidpagesize)"
        class="table-check" @selection-change="handleSelectionChange"  >
            <el-table-column prop="or_id" label="Order id" style="width: 20px;"></el-table-column>
            <el-table-column prop="remark" label="Picture">
            <template  #default="scope">
            <img :src="scope.row.proProduct.remark" width="60" height="60" />
            </template>
            </el-table-column>
            <el-table-column prop="proProduct.title" label="Product Title" width="200"></el-table-column>
            <el-table-column prop="order_ID" label="Order No."></el-table-column>
            <el-table-column prop="qty" label="Qty"></el-table-column>
            
            <el-table-column prop="proStatus.statusName" label="Status"></el-table-column>
            <el-table-column prop="order_CREATED_TIME" label="Create time"></el-table-column>
            <el-table-column label="operation" width="250">
            <template  #default="scope">
                 <el-button type="success"  @click="refund(scope.row)" > Refund
                </el-button>
                <el-button type="danger"  @click="viewShipment(scope.row)" > Shipment
                </el-button>
            </template>
            </el-table-column>
        </el-table>
        <br>
            <el-pagination
            @size-change="handlePaidFormSizeChange"
            @current-change="handlePaidFormCurrentChange"
            :current-page="PaidFormcurrentPage"
            :page-sizes="[3,6,9]"
            :page-size="Paidpagesize"
            layout="total,sizes,prev,pager,next,jumper"
            :total="PaidForm.length" >
            </el-pagination>
      </el-tab-pane>
      
      
      <el-tab-pane label="Completed Orders">

       <el-table  header-cell-style=" background-color: 		#d9edf7; " 
       style="width: 100%" :data="completedForm.slice((completedFormcurrentPage-1)*completedFormpagesize,completedFormcurrentPage*completedpagesize)"
        class="table-check" @selection-change="handleSelectionChange"  >
            <el-table-column prop="or_id" label="Order id" style="width: 20px;"></el-table-column>
            <el-table-column prop="remark" label="Picture">
            <template  #default="scope">
            <img :src="scope.row.proProduct.remark" width="60" height="60" />
            </template>
            </el-table-column>
            <el-table-column prop="proProduct.title" label="Product Title" width="200"></el-table-column>
            <el-table-column prop="order_ID" label="Order No."></el-table-column>
            <el-table-column prop="qty" label="Qty"></el-table-column>
            
            <el-table-column prop="proStatus.statusName" label="Status"></el-table-column>
            <el-table-column prop="order_CREATED_TIME" label="Creation time"></el-table-column>
           
        </el-table>
        <br>
            <el-pagination
            @size-change="handlecompletedFormSizeChange"
            @current-change="handlecompletedFormCurrentChange"
            :current-page="completedFormcurrentPage"
            :page-sizes="[3,6,9]"
            :page-size="completedpagesize"
            layout="total,sizes,prev,pager,next,jumper"
            :total="completedForm.length" >
            </el-pagination>
      </el-tab-pane>
</el-tabs>

 

  </div>
    <el-dialog title="Tracking Company" v-model="dialogFormVisible" width="30%">
      <el-form :model="trackTable" ref="trackTable" label-width="130px">
        <el-form-item
          label="Company"
          prop="trackingCompany"
          :rules="[{ required: true, message: 'Please select tracking company'}]"
        >
          <el-radio
            v-model="trackTable.trackingCompany"
            label="SF Express"
          >SF Express</el-radio>
          <br />
          <el-radio v-model="trackTable.trackingCompany" label="UPS">UPS</el-radio>
        </el-form-item>
        <br />
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button type="danger" @click="shipmentOrder(trackTable)">Ship</el-button>
        <el-button @click="dialogFormVisible = false">Cancel</el-button>
      </span>
    </el-dialog>
</div>

  
</template>

<script>
export default {
name:"Order",
 data() {
    return {
      username:'',
      userid:'',
      count: 0,
      currentPage: 1,
      ReceivedFormcurrentPage:1,
      canceledFormcurrentPage:1,      
      pagesize: 5, 
      Receivedpagesize: 5, 
      Canceledpagesize: 5,

      shippagesize: 5, 
      shipFormcurrentPage:1,
      shipForm:[],

      completedpagesize: 5, 
      completedFormcurrentPage:1,
      completedForm:[],

      Paymentpagesize: 5, 
      PaymentFormcurrentPage:1,
      PaymentForm:[],
      paymentdrawer:false,

      Paidpagesize: 5, 
      PaidFormcurrentPage:1,
      PaidForm:[],
      Paiddrawer:false,

      dialogFormVisible: false,
      trackTable: {
        or_id:'',
        trackingCompany: ""
      },

      ReceivedForm:[],
      canceledForm:[],
      
      
      payInfoForm: {
        receive_name: "",
        receive_address: "",
        receive_phone: "",
        price:0
      },
      }
    },
  created(){
     this.viewAll();
     },
  methods:{
  refresh(){
   this.viewAll();
  },
  viewAll(){
     this.getAllMVOOrders(0);
     this.getAllMVOOrders(2);
     this.getAllMVOOrders(4);
     this.getAllMVOOrders(6);
  },
    /***********************/
    handleReceivedFormSizeChange(size) {
      this.Receivedpagesize = size;
    },
    handleReceivedFormCurrentChange(cPage) {
      this.ReceivedFormcurrentPage = cPage;
    },
/***********************/
 handlePaymentFormSizeChange(size) {
      this.Paymentpagesize = size;
    },
    handlePaymentFormCurrentChange(cPage) {
      this.PaymentFormcurrentPage = cPage;
    },
/****************************/
 handlePaidFormSizeChange(size) {
      this.Paidpagesize = size;
    },
    handlePaidFormCurrentChange(cPage) {
      this.PaidFormcurrentPage = cPage;
    },
/****************************/
 handleshipFormSizeChange(size) {
      this.shippagesize = size;
    },
    handleshipFormCurrentChange(cPage) {
      this.shipFormcurrentPage = cPage;
    },
/****************************/
 handlecompletedFormSizeChange(size) {
      this.completedpagesize = size;
    },
    handlecompletedFormCurrentChange(cPage) {
      this.completedFormcurrentPage = cPage;
    },
/****************************/
 AllowedOrder(row){      
        var result = confirm("Are want Allowed payment to this order?");
        if (result) { 
          let objstr= window.sessionStorage.getItem("userinfo")
          let userinfo= JSON.parse(objstr)
          this.userid=userinfo.userId;
          this.username=userinfo.username;
           this.axios.post("/getspringbootproxy/user/mvo-order/allowedOrder",
           {
              user_id:this.userid,
              o_id:row.or_id
           })
           .then(res => {
              let resdata=res.data;
              console.log(resdata);
              if(resdata.code==200){
               this.getAllMVOOrders(2);      
              }
              else{
               this.$message.error(resdata.msg);
              }
              this.getAllMVOOrders(2);      
            }
          );   
        }       
    },
getAllMVOOrders(sts_id){
          console.log("getAllSentOrders");
          let objstr= window.sessionStorage.getItem("userinfo")
          let userinfo= JSON.parse(objstr)
          this.userid=userinfo.userId;
          this.username=userinfo.username;
           this.axios.post("/getspringbootproxy/user/mvo-order/getAllMVOOrders",
           {
              user_id:this.userid,
              status_id:sts_id
           })
           .then(res => {
              let resdata=res.data;
              console.log(resdata);
              console.log("Record length: "+resdata.obj.length);
              let len=resdata.obj.length;
              if(resdata.code==200){
               if(sts_id==0){
                this.ReceivedForm =resdata.obj;
                }
                else if(sts_id==2){
                this.PaymentForm=resdata.obj;
                }
                else if(sts_id==6){
                  this.PaidForm=resdata.obj;
                }
                 else if(sts_id==4){
                  this.completedForm=resdata.obj;
                }
                
      
              }
              else{
               this.$message.error(resdata.msg);
              }
            }
          );          
    },
    cancelOrder(row){

      this.$prompt('Please input the cancel reason', 'Cancel Order', {
          confirmButtonText: 'OK',
          cancelButtonText: 'Cancel',
          inputErrorMessage: 'Please input the cancel reason'
        }).then(({ value }) => {
         
        
         console.log(row);
          let objstr= window.sessionStorage.getItem("userinfo")
          let userinfo= JSON.parse(objstr)
          this.userid=userinfo.userId;
         this.axios.post("/getspringbootproxy/user/mvo-order/cancelOrder",
           {
              o_id:row.or_id,
              user_id:this.userid,
              cancelreason:value,
              pro_id:row.proId
           })
           .then(res => {
             let resdata=res.data;
              console.log(resdata);
              console.log("Record length: "+resdata.obj.length);
              let len=resdata.obj.length;
              if(resdata.code==200){
                this.$message.success(resdata.msg);
               
              }
              else{
               this.$message.error(resdata.msg);
              }
              this.getAllCartByUser();
              this.getAllBVOOrders(0);
            }
          );          
         
        }).catch(() => {
          this.$message({
            type: 'info',
            message: 'Input canceled'
          });
        });
    },
    refund(row){
      var result = confirm("Are want refund to this order?");
      if (result) {
         let objstr= window.sessionStorage.getItem("userinfo")
         let userinfo= JSON.parse(objstr)
         this.userid=userinfo.userId;
         this.username=userinfo.username;
         console.log("refund");
         console.log(row);
          this.axios.post("/getspringbootproxy/user/mvo-order/refundOrder",
           {
              o_id:row.or_id,
              user_id:this.userid
           })
           .then(res => {
            let resdata=res.data;
            if(resdata.code==200){
                this.$message.success(resdata.msg);          
            }else if(resdata.code==502){
              alert(resdata.msg);
            }
            else{
                this.$message.error(resdata.msg);
            }
           }
          );
        
      }
    },

    viewShipment(row){
    this.dialogFormVisible=true;
    this.trackTable.or_id=row.or_id;
    },

   shipmentOrder(trackTable){

     
          console.log(trackTable);
          let objstr= window.sessionStorage.getItem("userinfo")
          let userinfo= JSON.parse(objstr)
          this.userid=userinfo.userId;
         this.axios.post("/getspringbootproxy/user/mvo-order/shipmentOrder",
           {
              o_id:this.trackTable.or_id,
              user_id:this.userid,
              trackingCompany:this.trackTable.trackingCompany
           })
           .then(res => {
             let resdata=res.data;
              console.log(resdata);
              console.log("Record length: "+resdata.obj.length);
              let len=resdata.obj.length;
              if(resdata.code==200){
                this.$message.success(resdata.msg);               
              }
              else{
               this.$message.error(resdata.msg);
              }
              this.getAllCartByUser();
              this.getAllBVOOrders(0);
            }
          );          
         
    },
  }
}
</script>

<style>

</style>