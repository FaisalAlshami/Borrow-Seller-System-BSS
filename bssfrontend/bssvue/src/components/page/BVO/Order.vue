<template>
<div>
  <table style="width:100%;">
   <td style="width:40%; vertical-align:top;">
         <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/main' }">Home</el-breadcrumb-item>
       <el-breadcrumb-item>BVO</el-breadcrumb-item>
      <el-breadcrumb-item>Order Management</el-breadcrumb-item>
    </el-breadcrumb>
   </td>
   <td style="width:60%; vertical-align:top;">
    <el-button  icon="el-icon-refresh"  @click="refresh()"  style="float:right;margin-left:5px;"></el-button>
    
   </td>
  </table>
 <div>

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

    <div style="margin-top:20px;">
      <el-tabs  type="border-card">
      <el-tab-pane label="In Cart">
      

        <el-table  style="width: 100%; margin-top:10px;" :data="cartForm.slice((currentPage-1)*pagesize,currentPage*pagesize)"
        class="table-check" @selection-change="handleSelectionChange"  
         header-cell-style=" background-color: 		#d9edf7; ">
            <el-table-column prop="cartId" label="Cart id" style="width: 20px;" :class="eltd"></el-table-column>
            <el-table-column prop="remark" label="Product picture">
            <template  #default="scope">
            <img :src="scope.row.proProduct.remark" width="60" height="60" />
            </template>
            </el-table-column>
            <el-table-column prop="proProduct.title" label="Product Title"></el-table-column>
            <el-table-column prop="quantity" label="Qty"></el-table-column>
            <el-table-column prop="proStatus.statusName" label="Status"></el-table-column>
            <el-table-column prop="createDate" label="Creation Date"></el-table-column>
            <el-table-column label="operation" width="250">
            <template  #default="scope">
           
                <el-button type="danger"  @click="deleteCart(scope.row)" > Delete
                </el-button>
             <el-button type="primary"  @click="sendOrder(scope.row)" > Send</el-button>
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
            :total="cartForm.length" >
            </el-pagination>
      </el-tab-pane>
      <el-tab-pane label="Sent Orders">

    
       <el-table  header-cell-style=" background-color: 	#d9edf7;" 
       style="width: 100%" :data="sentForm.slice((sentFormcurrentPage-1)*Sentpagesize,sentFormcurrentPage*Sentpagesize)"
        class="table-check" @selection-change="handleSelectionChange"  >
            <el-table-column prop="or_id" label="Order id" style="width: 20px;"></el-table-column>
            <el-table-column prop="remark" label="Picture">
            <template  #default="scope">
            <img :src="scope.row.proProduct.remark" width="60" height="60" />
            </template>
            </el-table-column>
            <el-table-column prop="proProduct.title" label="Product Title" width="250"></el-table-column>
            <el-table-column prop="order_ID" label="Order No." width="150"></el-table-column>
            <el-table-column prop="qty" label="Qty"></el-table-column>
            
            <el-table-column prop="proStatus.statusName" label="Status" width="100"></el-table-column>
            <el-table-column prop="order_CREATED_TIME" label="Create time" width="200"></el-table-column>
            <el-table-column label="operation" width="200">
            <template  #default="scope">
           
                <el-button type="danger"  @click="deleteOrder(scope.row)" > Delete
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
            :total="sentForm.length" >
            </el-pagination>
      </el-tab-pane>
       <el-tab-pane label="Awaiting payment">
       
       
       <el-table header-cell-style=" background-color: 		#d9edf7; "
        style="width:100%" :data="paymentForm.slice((paymentFormcurrentPage-1)*paymentpagesize,paymentFormcurrentPage*paymentpagesize)" width="100%"
        class="table-check" @selection-change="handleSelectionChange"  >
            <el-table-column prop="or_id" label="ID" width="80"></el-table-column>
            <el-table-column prop="remark" label="Picture" width="80">
            <template  #default="scope">
            <img :src="scope.row.proProduct.remark" width="60" height="60" />
            </template>
            </el-table-column>
            <el-table-column prop="proProduct.title" label="Product Title" width="300"></el-table-column>
            <el-table-column prop="order_ID" label="Order No." width="100"></el-table-column>
            <el-table-column prop="qty" label="Qty" width="50"></el-table-column>
            
            <el-table-column prop="proStatus.statusName" label="Status" width="150" style="color:red;"></el-table-column>
            <el-table-column prop="order_CREATED_TIME" label="Create time" width="200"></el-table-column>
            <el-table-column label="operation" width="200">
            <template  #default="scope">
           
                <el-button type="primary"  @click="paumentInfoDrawer(scope.row)"> Payment
                </el-button>
            </template>
            </el-table-column>
        </el-table>
        <br>
            <el-pagination
            @size-change="handlepaymentFormSizeChange"
            @current-change="handlepaymentFormCurrentChange"
            :current-page="paymnetFormcurrentPage"
            :page-sizes="[3,6,9]"
            :page-size="paymentpagesize"
            layout="total,sizes,prev,pager,next,jumper"
            :total="paymentForm.length" >
            </el-pagination>
       
       </el-tab-pane>
       <el-tab-pane label="Shipped">
       
       
       <el-table header-cell-style=" background-color: 		#d9edf7; "
         style="width: 100%" :data="shipForm.slice((shipFormcurrentPage-1)*shippagesize,shipFormcurrentPage*shippagesize)"
        class="table-check" @selection-change="handleSelectionChange"  >
            <el-table-column prop="or_id" label="Order id" width="80"></el-table-column>
            <el-table-column prop="remark" label="Picture" width="70">
            <template  #default="scope">
            <img :src="scope.row.proProduct.remark" width="50" height="50" />
            </template>
            </el-table-column>
            <el-table-column prop="proProduct.title" label="Product Title" width="250"></el-table-column>
            <el-table-column prop="order_ID" label="Order No." width="100"></el-table-column>
            <el-table-column prop="ship_time" label="Shipment time" width="150"></el-table-column>
            <el-table-column prop="proStatus.statusName" label="Status" width="100"></el-table-column>
            <el-table-column prop="tracking_company" label="Tracking Company" width="140"></el-table-column>
            <el-table-column prop="tracking_number" label="Tracking No." width="120"></el-table-column>
            <el-table-column label="operation" width="150">
            <template  #default="scope">
           
                <el-button type="primary"  @click="deliveredOrder(scope.row)"> Delivered
                </el-button>
            </template>
            </el-table-column>
            
        </el-table>
        <br>
            <el-pagination
            @size-change="handleshipFormSizeChange"
            @current-change="handleshipFormCurrentChange"
            :current-page="shipFormcurrentPage"
            :page-sizes="[3,6,9]"
            :page-size="shippagesize"
            layout="total,sizes,prev,pager,next,jumper"
            :total="shipForm.length" >
            </el-pagination>
       
       </el-tab-pane>

       <el-tab-pane label="Delivered">
       
       <el-table  header-cell-style=" background-color: 		#d9edf7; " 
       style="width: 100%" :data="deliveredForm.slice((deliveredFormcurrentPage-1)*deliveredpagesize,deliveredFormcurrentPage*deliveredpagesize)"
        class="table-check" @selection-change="handleSelectionChange"  >
            <el-table-column prop="or_id" label="Order id" width="80"></el-table-column>
            <el-table-column prop="remark" label="Picture" width="70">
            <template  #default="scope">
            <img :src="scope.row.proProduct.remark" width="50" height="50" />
            </template>
            </el-table-column>
            <el-table-column prop="proProduct.title" label="Product Title" width="300"></el-table-column>
            <el-table-column prop="order_ID" label="Order No." width="150"></el-table-column>
            <el-table-column prop="ship_time" label="Shipment time" width="200"></el-table-column>
            <el-table-column prop="proStatus.statusName" label="Status" width="150"></el-table-column>
            <el-table-column prop="completed_time" label="Delivered time" width="200"></el-table-column>
         
            
        </el-table>
        <br>
            <el-pagination
            @size-change="handledeliveredFormSizeChange"
            @current-change="handledeliveredFormCurrentChange"
            :current-page="deliveredFormcurrentPage"
            :page-sizes="[3,6,9]"
            :page-size="deliveredpagesize"
            layout="total,sizes,prev,pager,next,jumper"
            :total="deliveredForm.length" >
            </el-pagination>
       
       </el-tab-pane>
       <el-tab-pane label="Canceled">
       
       <el-table  header-cell-style=" background-color: 		#d9edf7; "
       style="width: 100%" :data="canceledForm.slice((canceledFormcurrentPage-1)*Canceledpagesize,canceledFormcurrentPage*Canceledpagesize)"
        class="table-check" @selection-change="handleSelectionChange"  >
            <el-table-column prop="or_id" label="Order id" style="width: 20px;"></el-table-column>
            <el-table-column prop="remark" label="Picture">
            <template  #default="scope">
            <img :src="scope.row.proProduct.remark" width="60" height="60" />
            </template>
            </el-table-column>
            <el-table-column prop="proProduct.title" label="Product Title" width="200"></el-table-column>
            <el-table-column prop="order_ID" label="Order No." width="150"></el-table-column>
            <el-table-column prop="qty" label="Qty"></el-table-column>
            
            <el-table-column prop="proStatus.statusName" label="Status" width="100"></el-table-column>
            <el-table-column prop="cancelled_time" label="Canceled time"  width="150"></el-table-column>
            <el-table-column prop="canceled_reason" label="Reason" width="200"></el-table-column>
            
            
        </el-table>
        <br>
            <el-pagination
            @size-change="handlecanceledFormSizeChange"
            @current-change="handlecanceledFormCurrentChange"
            :current-page="canceledFormcurrentPage"
            :page-sizes="[3,6,9]"
            :page-size="Canceledpagesize"
            layout="total,sizes,prev,pager,next,jumper"
            :total="canceledForm.length" >
            </el-pagination>
       </el-tab-pane>
      

</el-tabs>
    </div>

  </div>

      <el-drawer
      title="drawer"
      v-model="paymentdrawer"
      size="50%"
      :wrapperClosable="false"
      :with-header="false"
    >
      <div class="form-div">
        <h3>Payment information</h3>
        <el-form :model="payInfoForm" ref="payInfoForm" label-width="150px" class="add-com-form">
          <el-form-item label="Receive Name"
            prop="receive_name"
            :rules="[{ required: true, message: 'Please enter a receive name '}]"
          >
            <el-input style="width:80%" v-model="payInfoForm.receive_name" autocomplete="off"></el-input>
          </el-form-item>
          <br />
          <el-form-item
            label="Receive Address"
            prop="receive_address"
            :rules="[{ required: true, message: 'Please enter a receive address'}]"
          >
            <el-input
              style="width:80%"
              v-model="payInfoForm.receive_address"
              autocomplete="off"
            ></el-input>
          </el-form-item>
          <br />
          <el-form-item
           label="Receive Phone"
            prop="receive_phone"
            :rules="[{ required: true, message: 'Please enter a receive phone'}]"
          >
            <el-input style="width:80%" v-model="payInfoForm.receive_phone" autocomplete="off"></el-input>
          </el-form-item>
           <br />
          <el-form-item
           label="Price"
            prop="price"
            :rules="[{ required: true, message: 'Please enter a receive phone'}]"
          >
            <el-input style="width:80%" v-model="payInfoForm.price" disabled autocomplete="off"></el-input>
          </el-form-item>
          <br />
          <el-form-item class="com-form-button">
            <el-button type="primary" @click="paymentInfo">Save</el-button>
            <el-button type="danger" @click="paymentdrawer=false">Cancel</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-drawer>
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
      sentFormcurrentPage:1,
      canceledFormcurrentPage:1,
      paymentFormcurrentPage:1,
      pagesize: 5, 
      Sentpagesize: 5, 
      Canceledpagesize: 5,
      paymentpagesize: 5, 

      shippagesize: 5, 
      shipFormcurrentPage:1,
      shipForm:[],

      deliveredpagesize: 5, 
      deliveredFormcurrentPage:1,
      deliveredForm:[],


      cartForm:[],
      sentForm:[],
      canceledForm:[],
      paymentForm:[],
      paymentdrawer:false,
      payInfoForm: {
        or_id:'',
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
     this.getAllCartByUser();
     this.getAllBVOOrders(0);
     this.getAllBVOOrders(1);
     this.getAllBVOOrders(2);
     this.getAllBVOOrders(3);
     this.getAllBVOOrders(4);
   },
   handleSizeChange(size) {
      this.pagesize = size;
    },
    handleCurrentChange(currentPage) {
      this.currentPage = currentPage;
    },
/***********************/
    handleSentFormSizeChange(size) {
      this.Sentpagesize = size;
    },
    handleSentFormCurrentChange(cPage) {
      this.sentFormcurrentPage = cPage;
    },
/***********************/
        handleCanceledFormSizeChange(size) {
      this.pagesize = size;
    },
    handleCanceledFormCurrentChange(currentPage) {
      this.CanceledFormcurrentPage = currentPage;
    },
/***********************/
    handlepaymentFormSizeChange(size) {
      this.paymentpagesize = size;
    },
    handlepaymnetFormCurrentChange(currentPage) {
      this.paymentFormcurrentPage = currentPage;
    },
/****************************/
 handleshipFormSizeChange(size) {
      this.shippagesize = size;
    },
    handleshipFormCurrentChange(cPage) {
      this.shipFormcurrentPage = cPage;
    },

/***********************/
 handledeliveredFormSizeChange(size) {
      this.deliveredpagesize = size;
    },
    handledeliveredFormCurrentChange(cPage) {
      this.deliveredFormcurrentPage = cPage;
    },

/***********************/
    paumentInfoDrawer(row){
    
      this.payInfoForm.or_id=row.or_id;
      this.payInfoForm.price=(row.sales_PRICE * row.qty);
      this.paymentdrawer = true;
    },
/***********************/
    deleteCart(row){
      var result = confirm("Want to delete this product from Cart?");
      if (result) {
        console.log(row);
         console.log("row.cartId: "+row.cartId);
        console.log("confirm deleted cart")
         this.axios.post("/getspringbootproxy/user/pro-cart/deleteCartByUser",
           {
              cartId:row.cartId
           })
           .then(res => {
            let resdata=res.data;
            if(resdata.code==200){
                this.$message.success(resdata.msg);
                this.getAllCartByUser();
                //this.$router.go(0);
                //return;
            }
            else{
                this.$message.error(resdata.msg);
            }
           }
          );
      }
    },
    getAllCartByUser(){
          console.log("getAllCartByUser");
          let objstr= window.sessionStorage.getItem("userinfo")
          let userinfo= JSON.parse(objstr)
          this.userid=userinfo.userId;
          this.username=userinfo.username;
           this.axios.post("/getspringbootproxy/user/pro-cart/getAllCartByBorrowUserId",
           {
              userId:this.userid,
              username:this.username
           })
           .then(res => {
              let resdata=res.data;
              console.log(resdata);
              console.log("Record length: "+resdata.obj.length);
              let len=resdata.obj.length;
              if(resdata.code==200){              
                this.cartForm =resdata.obj;
               
      
              }
              else{
               this.$message.error(resdata.msg);
              }
               this.getAllBVOOrders(0);
            }
          );          
    },
    sendOrder(row){

      this.$prompt('Please input drop store (Amazon / eBay)', 'Send Order', {
          confirmButtonText: 'OK',
          cancelButtonText: 'Cancel',
          inputErrorMessage: 'Please input drop store (Amazon / eBay)'
        }).then(({ value }) => {
         
         if(value==='amazon' || value==='ebay'){
         console.log(row);
          let objstr= window.sessionStorage.getItem("userinfo")
          let userinfo= JSON.parse(objstr)
          this.userid=userinfo.userId;
         this.axios.post("/getspringbootproxy/user/bvo-order/sendOrder",
           {
              cart_id:row.cartId,
              user_id:this.userid,
              dropshop:value,
              pro_id:row.proId
           })
           .then(res => {
           let resdata=res.data;
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
         }else{
          this.$message({
            type: 'error',
            message: 'please input amazon or ebay'
          });
         }
        }).catch(() => {
          this.$message({
            type: 'info',
            message: 'Input canceled'
          });
        });
    },
    deleteOrder(row){
      var result = confirm("Are want delete this order from the sent orders?");
      if (result) {
        console.log("getAllSentOrders");
          let objstr= window.sessionStorage.getItem("userinfo")
          let userinfo= JSON.parse(objstr)
          this.userid=userinfo.userId;
          this.username=userinfo.username;
           this.axios.post("/getspringbootproxy/user/bvo-order/deleteOrder",
           {
              user_id:this.userid,
              o_id:row.or_id
           })
           .then(res => {
              let resdata=res.data;
              console.log(resdata);
              if(resdata.code==200){
               this.getAllBVOOrders(0);      
              }
              else{
               this.$message.error(resdata.msg);
              }
            }
          );  
      }        
    },
    getAllBVOOrders(sts_id){
          let objstr= window.sessionStorage.getItem("userinfo")
          let userinfo= JSON.parse(objstr)
          this.userid=userinfo.userId;
          this.username=userinfo.username;
           this.axios.post("/getspringbootproxy/user/bvo-order/getAllBVOOrders",
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
                this.sentForm =resdata.obj;
                }
                else if(sts_id==1){
                this.canceledForm=resdata.obj;
                }
                else if(sts_id==2){
                  this.paymentForm=resdata.obj;
                   console.log("Get the awaiting payment order");
                   console.log(res);
                }
                else if(sts_id==3){
                 this.shipForm=resdata.obj;
                }
                else if(sts_id==4){
                 this.deliveredForm=resdata.obj;
                }
      
              }
              else{
               this.$message.error(resdata.msg);
              }
            }
          );          
    },
   
     
     
    paymentInfo(){
      var result = confirm("Are want payment to this order?");
      if (result) {
         let objstr= window.sessionStorage.getItem("userinfo")
         let userinfo= JSON.parse(objstr)
         this.userid=userinfo.userId;
         this.username=userinfo.username;
         console.log("row:o_id="+this.payInfoForm.or_id);
         this.axios.post("/getspringbootproxy/user/bvo-order/paymentInfoByBVO",
           {
              o_id:this.payInfoForm.or_id,
              user_id:this.userid,
              receive_name:this.payInfoForm.receive_name,
              receive_address:this.payInfoForm.receive_address,
              receive_phone:this.payInfoForm.receive_phone,
              price:this.payInfoForm.price

           })
           .then(res => {
            let resdata=res.data;
            if(resdata.code==200){
                this.$message.success(resdata.msg);
                this.getAllCartByUser();              
            }else if(resdata.code==502){
              alert(resdata.msg);
             // this.$message.error(resdata.msg);
            }
            else{
                this.$message.error(resdata.msg);
            }
           }
          );
      }
    },
    deliveredOrder(row){
      var result = confirm("Are want confirm delivered this order?");
      if (result) {
         let objstr= window.sessionStorage.getItem("userinfo")
         let userinfo= JSON.parse(objstr)
         this.userid=userinfo.userId;
         this.username=userinfo.username;
         console.log(row);
         console.log("row:o_id="+row.or_id);
         this.axios.post("/getspringbootproxy/user/bvo-order/deliveredOrder",
           {
              o_id:row.or_id,
              user_id:this.userid,
           })
           .then(res => {
            let resdata=res.data;
            if(resdata.code==200){
                this.$message.success(resdata.msg);
                this.getAllCartByUser();              
            }
            else{
                this.$message.error(resdata.msg);
            }
           }
          );
      }
    },
  }
}
</script>

<style scope>

table{
  border-collapse: collapse;
}
.tbview ,tr ,td {
 
}
.tbview tr th{
 text-align:left;
 padding:5px 5px 5px 5px;
 background-color: 	#FAEBD7;
 
}
.tableheader{
 background-color: 	#FAEBD7;
}
</style>