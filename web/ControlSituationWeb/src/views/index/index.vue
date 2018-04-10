<template>
  <el-container>
    <!--头部-->
    <el-header>
      <el-row>
        <el-col :span="2" class="logo">
          <span>DEMON</span>
        </el-col>
        <el-col :span="19">
          <el-menu
            :default-active="activeIndex"
            class="el-menu-demo"
            mode="horizontal"
            @select="handleSelect"
            text-color="#000"
            active-text-color="#ffd04b">
            <!--<el-menu-item v-for="(menu,key) in menus" :index="menu.id" class="first_menu">{{menu.name}}</el-menu-item>-->
            <template v-for="menu in menus">
              <el-menu-item :index="menu.id + ''" class="first_menu">
                <router-link :to="menu.url">{{menu.name}}</router-link>
              </el-menu-item>
            </template>
            <!--<el-menu-item index="1" class="first_menu">首页</el-menu-item>-->
            <!--<el-menu-item index="2" class="first_menu">订单管理</el-menu-item>-->
          </el-menu>
        </el-col>
        <el-col :span="1">
          <i class="icon iconfont ei-fullscreen" @click="fullScreen"></i>
        </el-col>
        <el-col :span="2">
          <el-menu
            class="el-menu-user"
            mode="horizontal"
            @select="handleSelect"
            text-color="#000"
            active-text-color="#ffd04b">
            <el-submenu index="1" class="first_menu">
              <template slot="title">Demon-Coffee</template>
              <el-menu-item index="1-1">退出</el-menu-item>
            </el-submenu>
          </el-menu>
        </el-col>
      </el-row>
    </el-header>
    <!--头部-->

    <el-container>
      <!--侧边栏-->
      <el-aside width="159px">
        <span style="color: red;">侧边栏</span>
      </el-aside>
      <!--侧边栏-->

      <!--内容-->
      <el-main>
        <span style="color: red;">正文内容</span>
      </el-main>
      <!--内容-->
    </el-container>
  </el-container>
</template>

<script>
  export default {
    name: 'index',
    data() {
      return {
        activeIndex: '1', // 侧边栏当前激活的菜单
        isFull: false,  // 是否全屏展示
        // 一级菜单
        menus: [{
          id: 1,
          pid: 0,
          name: '首页',
          url: '/index',
          sort: 0,
          level: 1,
          is_children: 0,
          menu: 1,
          enable: 1,
          open: 1,
          create_time: '2018-3-31 18:31:25',
          update_time: '2018-3-31 18:31:25',
          childrens: []
        }, {
          id: 2,
          pid: 0,
          name: '系统管理',
          url: '/system',
          sort: 0,
          level: 1,
          is_children: 1,
          menu: 1,
          enable: 1,
          open: 1,
          create_time: '2018-3-31 18:31:25',
          update_time: '2018-3-31 18:31:25',
          childrens: [{
            id: 3,
            pid: 2,
            name: '菜单管理',
            url: '/menu',
            sort: 1,
            level: 2,
            is_children: 1,
            menu: 1,
            enable: 1,
            open: 0,
            create_time: '2018-3-31 18:31:25',
            update_time: '2018-3-31 18:31:25',
            childrens: []
          }, {
            id: 4,
            pid: 2,
            name: '用户管理',
            url: '/user',
            sort: 1,
            level: 2,
            is_children: 1,
            menu: 1,
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
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      },
      // 全屏缩放
      fullScreen: () => {
        // 退出全屏
        if (this.isFull) {
          //FireFox
          if (document.mozCancelFullScreen) document.mozCancelFullScreen();
          //Chrome等
          else if (document.webkitCancelFullScreen) document.webkitCancelFullScreen();
          //IE11
          else if (document.msExitFullscreen) document.msExitFullscreen();

          this.isFull = false;
        }
        // 全屏
        else {
          let docElm = document.documentElement;
          //FireFox
          if (docElm.mozRequestFullScreen) docElm.mozRequestFullScreen();
          //Chrome等
          else if (docElm.webkitRequestFullScreen) docElm.webkitRequestFullScreen();
          //IE11
          else if (elem.msRequestFullscreen) elem.msRequestFullscreen();

          this.isFull = true;
        }
      }
    }
  }
</script>

<style>
  @import "index.css";
</style>


