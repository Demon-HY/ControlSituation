<template>
  <!--<div class="el-header">-->
    <!--<div class="logo el-col el-col-10"></div>-->
  <!--</div>-->
  <el-header>
    <el-row>
      <el-col :span="5"><div class="grid-content bg-purple">
        <img class="logo" :src="logo"/>
      </div></el-col>
      <el-col :span="14"><div class="grid-content bg-purple-light"></div></el-col>
      <el-col :span="5"><div class="grid-content bg-purple"></div></el-col>
    </el-row>
  </el-header>
</template>

<script>
  export default {
    name: 'header-nav',
    data() {
      return {
        logo: '/static/logo.png'
      }
    }
  }
</script>

<style>
  el-header {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 60px;
    width: 100%;
  }

  .logo {
    width: 60px;
  }
</style>

