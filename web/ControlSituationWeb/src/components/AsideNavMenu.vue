<template>
  <div class="navMenu">
    <template v-for="menu in this.navMenus">
      <el-submenu index="value.id" v-if="value.node">
        <template slot="title">
          <i class="el-icon-message"></i>
          <span slot="title">{{value.menu_name}}</span>
        </template>
        <MenuTree :menuData="value.node"></MenuTree>
      </el-submenu>
      <el-menu-item index="value.id" v-else>
        <i class="el-icon-message"></i>
        <span slot="title">{{value.menu_name}}</span>
      </el-menu-item>
    </template>

    作者：oryxtc
    链接：https://www.jianshu.com/p/35dd41d8173d
    來源：简书
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
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
  default-active="0"
  class="el-menu-vertical-demo"
  @select="menuSelected"
  background-color="#F0F6F6"
  text-color="#3C3F41"
  active-text-color="black">
  <NavMenu :navMenus="leftMenus"></NavMenu>
</el-menu>
-->

