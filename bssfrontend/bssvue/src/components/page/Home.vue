<template>
  <div class="card-body">
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/main' }">Home</el-breadcrumb-item>
    </el-breadcrumb>
    <div class="top-box">
       <div class="card-flex" >
          <img class="img-class2" src="http://127.0.0.1:8091/img/user.png" alt />
          <div class="subitem">
            <h2 class="name">Roles</h2>
            <h1 class="num">{{userInfo.roleName}}</h1>
          </div>
        </div>
          <div class="card-flex" @click="card">
          <img class="img-class2" src="http://127.0.0.1:8091/img/goods.png" alt />
          <div class="subitem">
            <h2 class="name">Goods</h2>
            <h1 class="num">{{userInfo.totalgoods}}</h1>
          </div>
        </div>
          <div class="card-flex" @click="card">
          <img class="img-class2" src="http://127.0.0.1:8091/img/order.png" alt />
          <div class="subitem">
            <h2 class="name">Orders</h2>
            <h1 class="num">{{userInfo.totalorders}}</h1>
          </div>
        </div>
         <div class="card-flex" @click="card">
          <img class="img-class2" src="http://127.0.0.1:8091/img/wallet.png" alt />
          <div >
            <h2 class="name">Wallet</h2>
            <span style="font-size:12pt; font-color:red;">¥ {{userInfo.totalwallet}}</span>
          </div>
        </div>
    </div>

<div style=" margin-top:50px;">

   <el-carousel :interval="5000" arrow="always" height="400px"  class="slideshow">
    <!-- <el-carousel height="500px" > -->
      <el-carousel-item v-for="item in goods_b" :key="item.title" >
        <img :src="item.remark" class="image" style="width:100%;"/>
                             <!-- <img src="http://127.0.0.1:8091/img/banner-08.jpg" class="image"> -->
 
      <!-- <div class="goods-info-div">
                      <div class="goods-row">
                        <span>{{item.title}}</span>
                       
                      </div>
                    </div> -->
                   
       
      </el-carousel-item>
    </el-carousel>


   
</div>


  </div>
</template>

<script>
export default {
  data() {
    return {

      cardInfo: [],
      userInfo:{
       roleName:'',
       totalgoods:'',
       totalorders:'',
       totalwallet:''
      },
        goods_b:[
         {title:"huaweip30",
          remark:require('@/assets/slide4.png'),
          retailPrice:1000
          },
          {title:"huaweip30",
          remark:require('@/assets/slide5.png'),
          retailPrice:1000
          },
          {title:"huaweip30",
          remark:require('@/assets/slide3.jpg'),
          retailPrice:1000
          },
          {title:"banner-ecommerce",
          remark:require('@/assets/banner-ecommerce.jpg'),
          retailPrice:500
          },
         
          {title:"huaweip30",
          remark:require('@/assets/banner-1.png'),
          retailPrice:1000
          },
          {title:"banner",
          remark:require('@/assets/banner-2.jpg'),
          retailPrice:1000
          },
          {title:"banner2",
          remark:require('@/assets/banner-3.jpg'),
          retailPrice:1000
          },
      ],
    
    };
  },
  mounted() {
    
    
    
  },
  created(){
  this.getInfo();
  },
  methods: {
    card() {},
    getInfo(){
      let objstr= window.sessionStorage.getItem("userinfo")
      let userinfo= JSON.parse(objstr)
      this.userid=userinfo.userId;
      this.username=userinfo.username;
      this.axios.post("/getspringbootproxy/user/main/getUserInfo",
              {
               userId:this.userid,
               username:this.username
               }).then(res => {
                let resdata=res.data;
                if(resdata.code==200){
                    console.log(resdata)
                    this.userInfo.roleName=resdata.obj.roleName;
                    this.userInfo.totalgoods=resdata.obj.totalGoods;
                    this.userInfo.totalorders=resdata.obj.totalOrder;
                    this.userInfo.totalwallet=resdata.obj.totalWallet;
                  
                }else{  
                    this.$notify.warning(res.message);
                }
    });   
    },
  }
};
</script>

<style scoped>
.card-body >>>.el-card__body {
  padding: 0px;
}
.top-box {
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  align-items: center;
  margin-top: 3rem;
}
.card-flex {
  height: 9rem;
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  align-items: center;

   width:280px;
  box-shadow:
       inset 0 -3em 3em rgba(0,0,0,0.1),
             0 0  0 2px rgb(255,255,255),
             0.3em 0.3em 1em rgba(0,0,0,0.3);
}
.card {
  width: 20rem;
  height: 9rem;
}
.img-class1 {
  width: 3.5rem;
  height: 4.5rem;
}
.img-class2 {
  width: 60px;
  height: 80px;
  margin-right:10px;
  vertical-align:top;
}
.name {
  font-size: 18px;
  color: grey;
}
.num {
  font-size: 25px;
  color: rgb(199, 121, 121);
}
.chart-box {
  margin-top: 6rem;
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  align-content: center;
}
.subitem{
 margin-left:20px;
}
.slideshow{
margin-top:60px;
 box-shadow:
       inset 0 -3em 3em rgba(0,0,0,0.1),
             0 0  0 2px rgb(255,255,255),
             0.3em 0.3em 1em rgba(0,0,0,0.3);
}
</style>