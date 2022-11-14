<template>
    <div>
   
            <el-button   @click="method1()" >method1</el-button>

            <el-button   @click="method2()" >method2</el-button>
         
            <el-button   @click="method3()" >method3</el-button>

            <el-button   @click="method4()" >method4</el-button>

            <el-button   @click="method5()" >method5</el-button>
    </div>
</template>

<script>
export default {
    name:"PromiseTest",
    methods:{
        method1(){

            this.axios.post("/getspringbootproxy/ajax1").then(res => {
                console.log("ajax1")
                console.log(res)
                
            
              });
            this.axios.post("/getspringbootproxy/ajax2").then(res => {
                console.log("ajax2")
                console.log(res)
                
            
              });
            this.axios.post("/getspringbootproxy/ajax3").then(res => {
                            console.log("ajax3")
                            console.log(res)
                            
                        
                        });

        },
        method2(){

            this.axios.post("/getspringbootproxy/ajax1").then(res => {
                console.log("ajax1")
                console.log(res)
                 this.axios.post("/getspringbootproxy/ajax2").then(res => {
                console.log("ajax2")
                console.log(res)
                    this.axios.post("/getspringbootproxy/ajax3").then(res => {
                    console.log("ajax3")
                    console.log(res)
                    
                });
              });
            
              });
           

        },
        method3(){
            
             this.axios.all([this.method3axios1(), this.method3axios2(),this.method3axios3()])
            .then(this.axios.spread(function (res1, res2,res3) {
                // 两个请求现在都执行完成
                 console.log("method3axios1 method3axios2")
                 console.log(res1)
                 console.log(res2)
                 console.log(res3)
            }));
        },
        method3axios1(){
            
            //  this.axios.post("/getspringbootproxy/ajax1").then(res => {
            //     console.log("ajax1")
            //     console.log(res)
            //   });
            return this.axios.post("/getspringbootproxy/ajax1");
        },
        method3axios2(){
            
            //  this.axios.post("/getspringbootproxy/ajax2").then(res => {
            //     console.log("ajax2")
            //     console.log(res)
            //   });
              return this.axios.post("/getspringbootproxy/ajax2");
        },
        method3axios3(){
            
            //  this.axios.post("/getspringbootproxy/ajax2").then(res => {
            //     console.log("ajax2")
            //     console.log(res)
            //   });
              return this.axios.post("/getspringbootproxy/ajax3");
        },
         method4(){
            
                // const p1 = new Promise(function (resolve, reject) {
                // setTimeout(() => reject(new Error('fail')), 3000)
                // })

                // const p2 = new Promise(function (resolve, reject) {
                // setTimeout(() => resolve(p1), 1000)
                // })

                // p2
                // .then(result => console.log(result))
                // .catch(error => console.log(error))
                // Error: fail
                new Promise((resolve, reject) => {
                 return resolve(this.axios.post("/getspringbootproxy/ajax1"));
               
                }).then(r => {
                     console.log(r)
                    //  new Promise((resolve, reject) => {
                    //  return resolve(this.axios.post("/getspringbootproxy/ajax2"));
                    
                    // }).then(r => {
                    //      console.log(r)
                    //      new Promise((resolve, reject) => {
                    //         return resolve(this.axios.post("/getspringbootproxy/ajax3"));
                            
                    //         }).then(r => {
                    //             console.log(r)
                    //         });
                    // });
                });
         },
         method5(){
            //  因为resolve跟reject这两个参数是可以省略的，所以可以把new Promise(...)写成Promise.resolve或者Promise.reject的形式.
            new Promise((resolve, reject) => {
                        return resolve(this.axios.post("/getspringbootproxy/ajax1"));
                }).then(r => {
                        console.log(r)
                        return Promise.resolve(this.axios.post("/getspringbootproxy/ajax2"));
                    })
                    .then(r => {
                        console.log(r)
                        return Promise.resolve(this.axios.post("/getspringbootproxy/ajax3"));
                            
                    })
                    .then(r => {
                                console.log(r)
                            });
                    
            
         }
        
        
     }

}
</script>

<style>

</style>