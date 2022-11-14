<template>
<div>
   <div>
          <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/main' }">HOME</el-breadcrumb-item>
            <el-breadcrumb-item>Brands</el-breadcrumb-item>
          </el-breadcrumb>
      </div>
      <div style="margin-top:10px;">
        <span>
          Search：
          <el-input
            style="width:200px"
            placeholder="brand title"
            @input="search"
            v-model="search_brandsstitle"
          ></el-input>
        </span>
        <el-button type="danger" icon="el-icon-search"></el-button>
        <el-button  plain icon="el-icon-plus" @click="brand_drawer = true">Add</el-button>
    </div>

    <el-drawer v-model="brand_drawer" size="40%">

   <el-card class="box-card" >
    <h2 class="title">Add brand</h2>
  <div class="text item">
     <div style="padding-left:10px;">
      
       <div style="width:100%;">
            <span style="width:30%;">English Name:</span>
            <el-input  v-model="input1" placeholder="Input English name" style="padding-left:10px;width:65%;" clearable> </el-input>
       </div>

        <div style="width:100%; margin-top:10px;">
            <span style="width:30%;">Chinese Name:</span>
            <el-input  v-model="input2" placeholder="Input Chinese name" style="padding-left:10px;width:65%;" clearable> </el-input>
       </div>
         <div style="width:100%; margin-top:10px;">
            <span style="width:30%;">State:</span>
             <el-select
              style="width:65%; margin-left:12%;"
              placeholder
              v-model="BrandForm.stsCd"
              autocomplete="off"
            >
              <el-option
                v-for="item in state_options"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              ></el-option>
            </el-select>
       </div>
         <div style="width:100%;padding-top:10px;">
            <el-button @click="updateUserInfo()"  type="primary">confirm</el-button>
            <el-button  @click="cancelDrawer()"  type="danger">cancel</el-button>
          </div>
       </div>
  </div>
</el-card>
  

</el-drawer>

  </div>

</template>

<script>
  export default {
    data() {
      return {
        brand_drawer: false,
        input1:'',
        input2:'',
         state_options: [
            { value:"0",  label: "Pending warehousing" },
            { value: "1", label: "already warehousing" },
            { value: "2", label: "in shelf	" }       
        ],
        BrandForm:[],
      };
    },
  };
</script>

<style>
  .text {
    font-size: 14px;
  }

  .item {
    padding: 18px 0;
  }

  .box-card {
    margin-left:3%;
    width: 94%;
  }
</style>