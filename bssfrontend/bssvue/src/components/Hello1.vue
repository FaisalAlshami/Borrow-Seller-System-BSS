<template>
  <div id="container1">  
    hello vue template
    <input value="click to ask infomation" type="button" v-on:click="fn"/>
    <ul>
            <li><router-link to="/">hello1</router-link></li>
            <li><router-link to="/HelloWorld">helloworld</router-link></li>
        </ul>

  <h1>{{ msg }}</h1>

      <div>
            <img :src="path1" >
      </div>
    <div>
            <img :src="require('../assets/2.png')" >
      </div>

      <div>
            <img :src="path2" >
      </div>

    <div>
      <el-button type="primary">el-button</el-button>

    </div>
<el-row>
  <el-button>Default</el-button>
  <el-button type="primary">Primary</el-button>
  <el-button type="success">Success</el-button>
  <el-button type="info">Info</el-button>
  <el-button type="warning">Warning</el-button>
  <el-button type="danger">Danger</el-button>
</el-row>
 </div> 
</template>

<script>
export default {
 name: 'Hello1',
//  props: {
//     msg: String
//   }
 data:function(){
            return {
              msg:"",
              path1:"",
              path2:""
            }
          },
  methods:{
        fn(){

            this.axios.get("/getspringbootproxy/getHello3").then((response) => { 
            // this.axios.post("/getspringbootproxy/getHello1").then((response) => { 
              console.log(response)
              console.log(response.data) 
              this.msg=response.data
            })



        }
  },
  created(){
    
        console.log("aaaaaaaaaaaaaaaaaaaaaaaaa")

          this.axios.get("/getspringbootproxy/getPic").then((response) => { 
            // this.axios.post("/getspringbootproxy/getHello1").then((response) => { 
              // console.log(response)
              // console.log(response.data) 
              // this.msg=response.data
              this.path1= require('../assets/'+response.data)
            })

    //  this.path1="../assets/1.png";
  //  this.path1= require('../assets/1.png')
		this.path2= require('@/assets/3.png')
	}

  
}
</script>

<style scoped>
#container1{
  color:red;
}
</style>