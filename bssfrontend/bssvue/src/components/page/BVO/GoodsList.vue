<template>
  <div> 
    <table style="width:100%;">
   <td style="width:40%; vertical-align:top;">
         <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/main' }">Home</el-breadcrumb-item>
       <el-breadcrumb-item>BVO</el-breadcrumb-item>
      <el-breadcrumb-item>Goods List</el-breadcrumb-item>
    </el-breadcrumb>
   </td>
   <td style="width:60%; vertical-align:top;">
    <el-button  icon="el-icon-refresh"  @click="refresh()"  style="float:right;margin-left:5px;"></el-button>
    
   </td>
  </table>

     
      

        
        <el-row>
              <el-col :span="4" v-for="item in goods" :key="item.title">
                <div style="padding-right:20px;">
                  <el-card class="goods-card" :body-style="{ padding: '0px' }" >
                    <img :src="item.remark" class="small-img" @click="detail(item)" />
                    <div class="goods-info-div">
                      <div class="goods-row">
                        <span>{{item.title}}</span>
                        <div class="small-icon" @click="star(item)">
                          <i :class="item.star == 1 ? 'el-icon-star-on' :'el-icon-star-off'"></i>
                        </div>
                      </div>
                      <div class="goods-row">
                        <el-link :underline="false" type="danger">¥ {{item.retailPrice}}</el-link>
                        <el-button type="text" class="btn" @click="detail(item)">Details</el-button>
                      </div>
                    </div>
                  </el-card>
                </div>
              </el-col>
            </el-row>

    <el-drawer
        size="35%"
        :title="chooseItem.title"
        v-model="drawer"
        :direction="direction"
        :before-close="handleClose">
          <div class="goods-div">
            <h2>{{chooseItem.title}}</h2>
            <div class="icon" @click="star(chooseItem)">
              <i :class="chooseItem.star == 1 ? 'el-icon-star-on' :'el-icon-star-off'"></i>
            </div>
            <div class="base-info">
              <img :src="chooseItem.remark" class="img"  style="width:150px; height:200px; margin-right:10px;"/>
              <div style="width:25rem">
                <p>{{chooseItem.title}}</p>
                <el-link :underline="false" type="danger">{{chooseItem.retailPrice}} ¥</el-link>
                <p>Brand: {{chooseItem.name}}</p>
                <p>Stock: {{chooseItem.replenishmentPeriod}}</p>
                <p>Quantity : <br><el-input-number v-model="num" :step="1" max="chooseItem.replenishmentPeriod" ></el-input-number></p>
                <el-popover placement="bottom" v-model="dialogVisible">
                  <div class="check">
                    <el-checkbox
                      :indeterminate="isIndeterminate"
                      v-model="checkAll"
                      @change="handleCheckAllChange"
                    >Amazon</el-checkbox>

                    <el-checkbox-group v-model="checkedAStores" @change="handleCheckedAStoresChange">
                      <el-checkbox
                        class="store"
                        v-for="item in Astores"
                        :label="item"
                        :key="item"
                      >{{item}}</el-checkbox>
                    </el-checkbox-group>

                    <el-checkbox
                      class="title"
                      :indeterminate="EisIndeterminate"
                      v-model="EcheckAll"
                      @change="EhandleCheckAllChange"
                    >Ebay</el-checkbox>

                    <el-checkbox-group v-model="checkedEStores" @change="handleCheckedEStoresChange">
                      <el-checkbox
                        class="store"
                        v-for="item in Estores"
                        :label="item"
                        :key="item"
                      >{{item}}</el-checkbox>
                    </el-checkbox-group>
                  </div>
                  <div class="minibtn">
                    <el-button size="mini" @click="dialogVisible = false">Cancel</el-button>
                    <el-button size="mini" type="danger" plain @click="pushShip">Push</el-button>
                  </div>
                  <el-button type="danger" slot="reference">Dropship Now</el-button>
                </el-popover>
               <p> <el-button type="primary"  @click="addToCart(chooseItem)" >Add to cart</el-button></p>

                <el-button       type="danger"          @click="addToWish(chooseItem)"
                >{{chooseItem.star == 1 ? 'Remove from Wish List' : 'Add to Wish List'}}</el-button>
              </div>
            </div>
            <br />
            <el-tabs v-model="activeName" class="tab">
              <el-tab-pane label="Amazon description" name="first">
                <!-- <article v-html="compileMarkDown(chooseItem.amazon_description)" ></article>  -->
              </el-tab-pane>
              <el-tab-pane label="ebay description" name="second">
                <!-- <article v-html="compileMarkDown(chooseItem.ebay_description)" ></article> -->
              </el-tab-pane>
            </el-tabs>
          </div>
      </el-drawer>




  </div>
</template>

<script>
export default {
name:"GoodList",
data() {
    return {
      num:1,
      chooseItem:[],
      drawer:false,
      goods:[
          // {title:"honor",
          // remark:require('@/assets/honor.jpg'),
          // retailPrice:500
          // },
          //  {title:"huaweip30",
          // remark:require('@/assets/huaweip30.jpg'),
          // retailPrice:1000
          // },
          //  {title:"huaweinova",
          // remark:require('@/assets/huaweinova.jpg'),
          // retailPrice:700
          // },
          //  {title:"honormagic",
          // remark:require('@/assets/huaweimac.jpg'),
          // retailPrice:600
          // }

      ],
      currentDate: new Date()
      
    };
  },
  methods:{
      refresh(){
      this.getAllProducts();
      },
      getAllProducts(){
          this.axios.post("/getspringbootproxy/user/pro-product/getAllProduct").then(res => {
                console.log(res)

              let resdata=res.data;
                if (resdata.code == 504) {
                  this.$notify.error(resdata.code+resdata.msg);
                }
                if (resdata.code == 200) {
                  this.$notify.success(resdata.msg);
                 console.log(resdata)
                 let j=0;
                 let arraylength=resdata.obj.length;
                 console.log("arraylength:"+arraylength)
                 console.log(resdata.obj);
                //  for(j=0;j<arraylength;j++){
                   // console.log("slice："+resdata.obj[j].remark.slice(0,4))
                  //    if(resdata.obj[j].remark.slice(0,4)=="http"){ //pic server

                    //  }else{
                     //   resdata.obj[j].remark=require("@/assets/"+resdata.obj[j].remark)
                     // }
                  //}
                  this.goods=resdata.obj

                  // if()

                  // this.$router.go(0)
                }
              });
      },
      detail(item){
        this.drawer=true;
        this.chooseItem=item;
      },
      star(chooseItem){ 
      console.log("Star  chooseItem");
      console.log(chooseItem);
      console.log("proId ==>"+chooseItem.proId);
      console.log("num ==>"+this.num);
      },

      addToCart(chooseItem){
          let objstr= window.sessionStorage.getItem("userinfo")
          let userinfo= JSON.parse(objstr)
          this.userid=userinfo.userId;
          this.username=userinfo.username;
          console.log("userId="+this.userid);
          this.axios.post("/getspringbootproxy/user/pro-cart/addCartByUser",{
                proId:this.chooseItem.proId,
                borrowUserId:this.userid,
                quantity:this.num
              }).then(res => {
              let resdata=res.data;
              this.$message.success(resdata.msg);
              console.log(resdata);
              });
             this.num=1; 
      
      },
      addToWish(chooseItem){
          let objstr= window.sessionStorage.getItem("userinfo")
          let userinfo= JSON.parse(objstr)
          this.userid=userinfo.userId;
          this.username=userinfo.username;
          console.log("userId="+this.userid);
          this.axios.post("/getspringbootproxy/user/pro-wish/addWishByUser",{
                proId:this.chooseItem.proId,
                userId:this.userid
              }).then(res => {
              let resdata=res.data;
              console.log(resdata);
              this.$message.success(resdata.msg);
              });
             this.num=1; 
      
      }
  },
  created(){
      this.refresh();
  }
}
</script>

<style scoped>
.base-info {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
.goods-div {
  margin-left: 3rem;
  height: 60rem;
}
.small-icon {
  font-size: 20px;
  color: #f3c93f;
}
.icon {
  position: absolute;
  top: 1rem;
  left: 11rem;
  font-size: 30px;
  color: #f3c93f;
}

.goods-row {
  width: 94%;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
}
.goods-info-div {
  margin-top: 0.5rem;
  margin-left: 1rem;
}
.small-img {
  width: 100%;
  height: 15rem;
}
.img {
  width: 20rem;
  height: 20rem;
}
.goods-card {
  margin-top: 0.8rem;
  
  width: 12rem;
  height: 20rem;

   box-shadow:
       inset 0 -3em 3em rgba(0,0,0,0.1),
             0 0  0 2px rgb(255,255,255),
             0.3em 0.3em 1em rgba(0,0,0,0.3);
      
}
 .time {
    font-size: 13px;
    color: #999;
  }
  
  .bottom {
    margin-top: 13px;
    line-height: 12px;
  }

  .button {
    padding: 0;
    float: right;
  }

  .image {
    width: 100%;
    display: block;
  }

  .clearfix:before,
  .clearfix:after {
      display: table;
      content: "";
  }
  
  .clearfix:after {
      clear: both
  }

 .itemcontainer{
   width:100%;
   padding :10px 10px 10px 10px;
 }
  .itemcontainer .itemPro{
     width:210px;
     height:320px;
      border-radius:2%;
     background-color:#F8F8FF;
     margin:10px 10px 10px 10px;
     padding-top:5px;
     border: 1px solid #D9CCCC;
     display:inline-block;
     position:relative;
     

  }
  .itemcontainer .itemPro .imgpro{
    width:140px;
    margin-left:10px;
    margin-right:10px;
    height:180px;
    float:center;
    display:inline-block;
  }
    .itemcontainer .itemPro .titlepro{
    width:100%;
    padding-left:10px;
    color:blue;
    font-size:12pt;
  } 
   .itemcontainer .itemPro  .probrand{
    float:left;
    width:80%;
    margin-left:10px;
   }
   .itemcontainer .itemPro  .small-icon{
    float:right;
    margin-right:10px;
   }
</style>