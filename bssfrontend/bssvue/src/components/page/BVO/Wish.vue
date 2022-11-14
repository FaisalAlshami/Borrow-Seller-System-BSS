<template>
  <div>
          <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/main' }">HOME</el-breadcrumb-item>
            <el-breadcrumb-item>Cart</el-breadcrumb-item>
          </el-breadcrumb>
  </div>
  <br>
  <div>
     

      <div>
        <span>
          Search：
          <el-input
            style="width:200px"
            placeholder="product title"
            @input="search"
            v-model="search_goodstitle"
          ></el-input>
        </span>
        <el-button type="danger" icon="el-icon-search" @click="getAllCartByUser"></el-button>
    </div>

      <el-table
      :data="tableData"
      style="width: 100%"
      class="table-check"
      @selection-change="handleSelectionChange"
      height="850"
    >
      <el-table-column prop="wishId" label="Wish id" style="width: 40px;"></el-table-column>
      <el-table-column prop="remark" label="Product picture">
        <template  #default="scope">
          <img :src="scope.row.proProduct.remark" width="100" />
        </template>
      </el-table-column>
      <el-table-column prop="proProduct.title" label="Product Title"></el-table-column>
      <el-table-column prop="createDate" label="Req. Date"></el-table-column>
       <el-table-column label="operation" width="250">
        <template  #default="scope">
          <el-button type="danger" round size="small" icon="el-icon-delete" @click="deleteCart(scope.row)" ></el-button>
        </template>
      </el-table-column>



    </el-table>

 

  </div>
</template>

<script>
export default {
  name:"Wallet",
  data(){
      return{
        brand_options: [
            { value: "1", label: "xiaomi" },
            { value: "2", label: "huawei" },
        ],
        type_options: [
            { value: "Phone", label: "Phone" },
            { value: "Computer", label: "Computer" },
            { value: "Instrument", label: "Instrument" },
            { value: "Appliances", label: "Appliances" }
        ],
        search_goodstitle:"",
        pagesize:5,
        currentPage:1,
        table:[],
        tableData:[]
      }
  },
  created(){
  this.getAllWishByUser();

  },
  methods:{
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
           }
          );
      }
    },
    getAllWishByUser(){
          console.log("getAllCartByUser");
          let objstr= window.sessionStorage.getItem("userinfo")
          let userinfo= JSON.parse(objstr)
          this.userid=userinfo.userId;
          this.username=userinfo.username;
           this.axios.post("/getspringbootproxy/user/pro-cart/getAllWishByBorrowUserId",
           {
              currentPage: this.currentPage,
							pageSize: this.pagesize,
              userId:this.userid,
              username:this.username
           })
           .then(res => {
              let resdata=res.data;
              console.log(resdata);
              console.log("Record length: "+resdata.obj.length);
              let len=resdata.obj.length;
              if(resdata.code==200){
                this.$message.success(resdata.msg);
                this.tableData =resdata.obj;
                this.table = resdata.obj;
                this.total=resdata.obj.total;
              }
              else{
               this.$message.error(resdata.msg);
              }
            }
          );
          
    }
  }
}
</script>

<style>

</style>