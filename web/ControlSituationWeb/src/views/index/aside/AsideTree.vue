<template>
  <el-aside width="159px">
    <!--unique-opened 是为了点击一个菜单的时候，其他菜单关闭-->
    <el-menu
      router
      :default-active="$route.path"
      class="el-menu-vertical-demo"
      @select="menuSelected"
      background-color="#F0F6F6"
      text-color="#3C3F41"
      active-text-color="black">
      <nav-menu :navMenus="leftMenus"></nav-menu>
    </el-menu>
  </el-aside>
</template>

<script>
  import NavMenu from '@/components/NavMenu'

  export default {
    name: 'aside-tree',
    components: {NavMenu},
    data() {
      return {
        leftMenus: [{
          id: 1,
          pid: 0,
          name: '系统管理',
          url: '',
          sort: 1,
          level: 1,
          is_children: 1,
          menu: 1,
          enable: 1,
          open: 1,
          create_time: '2018-3-31 18:31:25',
          update_time: '2018-3-31 18:31:25',
          children: [{
            id: 2,
            pid: 1,
            name: '菜单管理',
            url: 'menu',
            sort: 2,
            level: 2,
            is_children: 0,
            menu: 0,
            enable: 1,
            open: 0,
            create_time: '2018-3-31 18:31:25',
            update_time: '2018-3-31 18:31:25',
            childrens: []
          }, {
            id: 3,
            pid: 1,
            name: '用户管理',
            url: 'user',
            sort: 3,
            level: 2,
            is_children: 0,
            menu: 0,
            enable: 1,
            open: 0,
            create_time: '2018-3-31 18:31:25',
            update_time: '2018-3-31 18:31:25',
            childrens: []
          }]
        }]
      }
    },
    methods: {
      // 选中左侧菜单
      menuSelected: (key, keyPath) => {
        console.log(key);
        console.log(keyPath);
      }
    }
  }
</script>

<style scoped>
  /*侧边栏*/
  .el-aside {
    background-color: #1c2b36;
    color: #7793a7;
    text-align: center;
    height: 100vh;
  }
</style>
