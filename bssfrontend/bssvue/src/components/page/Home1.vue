<template>
  <div class="block">
  
    <!-- <span class="demonstration">Trending Products</span> -->
   <el-carousel :interval="5000" arrow="always" height="400px" >
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
     <div class="center">
      
				<h3 class="ltext-105 cl5 txt-center respon1" style="width:800px; margin:0 auto; font-size: 50px;
  font-weight: bold; justify-content: space-between;">
				 Store Overview
				</h3>
		
				<!-- <h3 class="ltext-105 cl5 txt-center respon1" style="width:800px; margin:0 auto;">
					  
				</h3> -->
			
      <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
  <el-menu-item index="1">Best Seller</el-menu-item>
  <el-menu-item index="2" >Featured</el-menu-item>
  <el-menu-item index="2" >Sale</el-menu-item>
  <el-menu-item index="3"><a href="https://www.ele.me" target="_blank">Orders</a></el-menu-item>
                    </el-menu>
                    </div>
  <!-- <el-row>
         
      <el-col :span="6" v-for="(o, index) in 3" :key="o" :offset="index > 0 ? 2 : 0">
        <el-card :body-style="{ padding: '0px' }">
          <img src="http://127.0.0.1:8091/img/honor.jpg" class="image" >
          
          <div style="padding: 14px;">
            <span>Yummy hamburger</span>
            <div class="bottom clearfix">
              <time class="time">{{ currentDate }}</time>
              <el-button type="text" class="button">  Shop Now</el-button>
            </div>
          </div>
        </el-card>
      </el-col>
      
    </el-row>  -->



     <el-row>
              <el-col :span="6" v-for="item in goods_a" :key="item.title" :offset="index > 0 ? 2 : 0">
           
                  <el-card  :body-style="{ padding: '0px' }">
                    <img :src="item.remark" class="image" @click="detail(item)" 
                    style=" width:300px; heigh:500px;"/>
                     <div style="padding: 14px;">
            <span>Yummy hamburger</span>
            <div class="bottom clearfix">
              <!-- <time class="time">{{ currentDate }}</time> -->
              <el-button type="text" class="button">  Shop Now</el-button>
            </div>
          </div>
        </el-card>
      </el-col>
      
    </el-row> 

       <!-- <footer class="bg3 p-t-75 p-b-32">
		<div class="container">
			<div class="row">
				<div class="col-sm-6 col-lg-3 p-b-50">
					<h4 class="stext-301 cl0 p-b-30">
						Categories
					</h4>

					<ul>
						<li class="p-b-10">
							<a href="#" class="stext-107 cl7 hov-cl1 trans-04">
								Women
							</a>
						</li>

						<li class="p-b-10">
							<a href="#" class="stext-107 cl7 hov-cl1 trans-04">
								Men
							</a>
						</li>

						<li class="p-b-10">
							<a href="#" class="stext-107 cl7 hov-cl1 trans-04">
								Shoes
							</a>
						</li>

						<li class="p-b-10">
							<a href="#" class="stext-107 cl7 hov-cl1 trans-04">
								Watches
							</a>
						</li>
					</ul>
				</div>  
      </div>
      
    </div>
    
       </footer> -->
  </div>
</template>

 




<script>
const dayjs = require('dayjs')
export default {
name:"Home",
data() {
    return {
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
    goods_a:[
         {title:"banner-ecommerce",
           remark:require('@/assets/product-03.jpg'),
          retailPrice:500
          },
         
           {title:"huaweip30",
          remark:require('@/assets/product-03.jpg'),
          retailPrice:1000
          },
          {title:"banner",
          remark:require('@/assets/product-06.jpg'),
          retailPrice:1000
          },
          {title:"banner",
          remark:require('@/assets/product-06.jpg'),
          retailPrice:1000}
          
      ],
   }
},
created(){
      this.axios.post("/getspringbootproxy/user/pro-product/getAllProduct").then(res => {
                console.log(res)

              let resdata=res.data;
                if (resdata.code == 504) {
                  this.$notify.error(resdata.code+resdata.msg);
                }
                if (resdata.code == 200) {
                  // this.$notify.success(resdata.msg);
                 console.log(resdata)
                 let j=0;
                 let arraylength=resdata.obj.length;
                 console.log("arraylength:"+arraylength)
                  for(j=0;j<arraylength;j++){
                    console.log("slice："+resdata.obj[j].remark.slice(0,4))
                      if(resdata.obj[j].remark.slice(0,4)=="http"){ //pic server

                      }else{
                        resdata.obj[j].remark=require("@/assets/"+resdata.obj[j].remark)
                      }
                  }
                  this.goods=resdata.obj

                  // if()

                  // this.$router.go(0)
                }
              });




  }
}

</script>

<style>

  .el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 150px;
    margin: 0;
  }

  .el-carousel__item:nth-child(2n) {
    background-color: #ffffff;
  }

  .el-carousel__item:nth-child(2n+1) {
    background-color:#ffffff;
  }
.btn-primary-pos {
   background-color: #007bff;
   border-color: #007bff #007bff #007bff;
   color: #FFF;
 }

 .btn-primary-pos:hover,
 .btn-primary-pos.hover {
   background-color: #0069d9;
   border-color: #0069d9;
   color: #FFF;
 }


 .card-footer {
   padding: 0px 0.75rem !important;
 }
 .center {
  margin: auto;
  width: 50%;
  
  padding: 10px;
}
</style>