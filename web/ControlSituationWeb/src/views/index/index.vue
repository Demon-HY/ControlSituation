<template>
  <el-container>
    <!--头部-->
    <el-header>
      <el-row>
        <el-col :span="2" class="logo">
          <span>DEMON</span>
        </el-col>
        <el-col :span="19" class="header-menu">
          <el-menu
            :default-active="activeIndex"
            class="el-menu-header"
            mode="horizontal"
            @select="handleSelect"
            text-color="#000"
            active-text-color="#ffd04b">
            <template v-for="menu in headerMenus">
              <el-menu-item :index="menu.id + ''" class="first_menu">
                <router-link :to="menu.url">{{menu.name}}</router-link>
              </el-menu-item>
            </template>
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
              <el-menu-item index="logout">退出</el-menu-item>
            </el-submenu>
          </el-menu>
        </el-col>
      </el-row>
    </el-header>
    <!--头部-->

    <el-container>
      <!--侧边栏-->
      <aside-tree></aside-tree>
      <!--侧边栏-->

      <!--内容-->
      <div class="main-container" style="width: 100%;height: auto%;">
        <router-view/>
      </div>
      <!--内容-->
    </el-container>
  </el-container>
</template>

<script>
  import {logout} from '@/api/user/auth_api'
  import AsideTree from './aside/AsideTree'

  export default {
    name: 'index',
    components: {AsideTree},
    data() {
      return {
        activeIndex: '1', // 侧边栏当前激活的菜单
        isFull: false,  // 是否全屏展示
        // 头部菜单
        headerMenus: []
      }
    },
    methods: {
      handleSelect(key, keyPath) {
        console.log(key);
        console.log(keyPath);
        if (key === 'logout') {
          logout();
        }
      },

      addTab(targetName) {
        let newTabName = ++this.tabIndex + '';
        this.editableTabs2.push({
          title: 'New Tab',
          name: newTabName,
          content: 'New Tab content'
        });
        this.editableTabsValue2 = newTabName;
      },
      removeTab(targetName) {
        let tabs = this.editableTabs2;
        let activeName = this.editableTabsValue2;
        if (activeName === targetName) {
          tabs.forEach((tab, index) => {
            if (tab.name === targetName) {
              let nextTab = tabs[index + 1] || tabs[index - 1];
              if (nextTab) {
                activeName = nextTab.name;
              }
            }
          });
        }

        this.editableTabsValue2 = activeName;
        this.editableTabs2 = tabs.filter(tab => tab.name !== targetName);
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


