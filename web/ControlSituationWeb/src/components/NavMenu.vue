<template>
  <div class="navMenu">
    <template v-for="menu in this.navMenus">
      <el-submenu :index="menu.url" v-if="menu.is_children">
        <template slot="title">
          <!--<i class="el-icon-message"></i>-->
          <span slot="title">{{menu.name}}</span>
        </template>
        <nav-menu :navMenus="menu.children"></nav-menu>
      </el-submenu>
      <el-menu-item :index="menu.url" v-else>
        <!--<i class="el-icon-message"></i>-->
        <span slot="title">{{menu.name}}</span>
      </el-menu-item>
    </template>
  </div>
</template>

<script>
  export default {
    name: 'NavMenu',
    props: ['navMenus'],
    data() {
      return {}
    },
    methods: {}
  }
</script>

<style scoped>

</style>

<!-- example -->
<!--左侧菜单组件-->
<!--
<el-menu
  :default-active="$route.path"
  class="el-menu-vertical-demo"
  @select="menuSelected"
  background-color="#F0F6F6"
  text-color="#3C3F41"
  active-text-color="black">
  <nav-menu :navMenus="leftMenus"></nav-menu>
</el-menu>
-->

