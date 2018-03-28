<template>
  <el-container>
    <!--头部-->
    <el-header>
      <el-row>
        <el-col :span="5"><div class="grid-content bg-purple">
          <span class="logo">DEMON</span>
        </div></el-col>
        <el-col :span="14"><div class="grid-content bg-purple-light"></div></el-col>
        <el-col :span="5"><div class="grid-content bg-purple"></div></el-col>
      </el-row>
    </el-header>
    <!--头部-->

    <el-container>
      <!--侧边栏-->
      <el-aside width="200px">Aside</el-aside>
      <!--侧边栏-->

      <!--内容-->
      <el-main>Main</el-main>
      <!--内容-->
    </el-container>
  </el-container>


  <!--<div class="index el-container">-->
    <!--&lt;!&ndash;头部&ndash;&gt;-->
    <!--<header-nav></header-nav>-->
    <!--&lt;!&ndash;头部&ndash;&gt;-->

    <!--&lt;!&ndash;侧边栏&ndash;&gt;-->
    <!--&lt;!&ndash;侧边栏&ndash;&gt;-->

    <!--&lt;!&ndash;内容&ndash;&gt;-->
    <!--&lt;!&ndash;内容&ndash;&gt;-->
  <!--</div>-->
</template>

<script>
  export default {
    name: 'index',
    data() {
      return {
      }
    }
  }
</script>

<style scoped>
  .el-header {
    background-color: #1c2b36;
    color: #333;
    /*text-align: center;*/
    line-height: 60px;
    height: 60px;
  }
  .el-aside {
    background-color: #1c2b36;
    color: #7793a7;
    text-align: center;
    height: 100vh;
  }
  .el-main {
    background-color: #29406C;
    color: #333;
    text-align: center;
    height: 100vh;
  }
  .logo {
    width: 60px;
    /*font-size: 40px;*/
    font-size: 20px;
    font-weight: 700;
    line-height: 50px;
    color: #c4d0d9 !important;
  }
</style>
