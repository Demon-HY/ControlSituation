<template>
  <div class="wrapper">
    <h1>Control Situation - 掌控全局</h1>
    <h2>用于实现一些系统级的服务</h2>
    <div class="content">
      <div id="form_login" class="form_login">
        <form class="login active">
          <h3>Login</h3>
          <div>
            <label>User</label>
            <input v-model="account" type="text" name="username"/>
            <span class="error">ERROR</span>
          </div>
          <div>
            <label>Password</label>
            <input v-model="password" type="password" name="password"/>
            <span class="error">ERROR</span>
          </div>
          <div class="bottom">
            <div class="remember"><input type="checkbox"/><span>Keep me logged in</span></div>
            <input type="submit" value="Login" class="submit-login" @click="toLogin(account, password)"/>
            <div class="clear"></div>
          </div>
        </form>
      </div>
      <div class="clear"></div>
    </div>
  </div>
</template>

<script>

  import {login, checkLogin} from '@/api/user/login_api'

  export default {
    name: 'login',
    data() {
      return {
        account: '',
        password: ''
      }
    },
    methods: {
      toLogin(account, password) {
        let result = login(account, password);
      }
    }
  }
</script>

<style scoped>
  body {
    font-family: "Trebuchet MS", "Myriad Pro", Arial, sans-serif;
    font-size: 14px;
    background: #f4f4f4 repeat top left;
    color: #333;
    text-shadow: 1px 1px 1px #fff;
    overflow-y: scroll;
  }

  h1 {
    font-size: 56px;
  }

  h2 {
    font-size: 20px;
    padding: 0 0 40px 0;
    color: #aaa;
  }

  h2 span {
    color: #ffa800;
  }

  a {
    color: #777;
  }

  a:hover {
    color: #222;
  }

  p {
    padding: 5px 0;
  }

  .wrapper {
    width: 960px;
    margin: 20px auto;
    min-height: 550px;
  }

  .clear {
    clear: both;
  }

  /* Form Style */
  .form_login {
    background: #fff;
    border: 1px solid #ddd;
    margin: 0 auto;
    width: 350px;
    font-size: 16px;
    -moz-box-shadow: 1px 1px 7px #ccc;
    -webkit-box-shadow: 1px 1px 7px #ccc;
    box-shadow: 1px 1px 7px #ccc;
  }

  .form_login h3 {
    padding: 20px 30px 20px 30px;
    background-color: #444;
    color: #fff;
    font-size: 25px;
    border-bottom: 1px solid #ddd;
  }

  .form_login form {
    display: none;
    background: #fff;
  }

  form.active {
    display: block;
  }

  form.login {
    width: 350px;
  }

  .form_login a {
    text-decoration: none;
    color: #777;
    font-size: 12px;
  }

  .form_login a:hover {
    color: #000;
  }

  .form_login label {
    display: block;
    padding: 10px 30px 0 30px;
    margin: 10px 0 0 0;
  }

  .form_login input[type="text"],
  .form_login input[type="password"] {
    border: solid 1px #E5E5E5;
    background: #FFFFFF;
    margin: 5px 30px 0 30px;
    padding: 9px;
    display: block;
    font-size: 16px;
    width: 76%;
    background: -webkit-gradient(
      linear,
      left top,
      left 25,
      from(#FFFFFF),
      color-stop(4%, #EEEEEE),
      to(#FFFFFF)
    );
    background: -moz-linear-gradient(
      top,
      #FFFFFF,
      #EEEEEE 1px,
      #FFFFFF 25px
    );
    -moz-box-shadow: 0 0 8px #f0f0f0;
    -webkit-box-shadow: 0 0 8px #f0f0f0;
    box-shadow: 0 0 8px #f0f0f0;
  }

  .form_login input[type="text"]:focus,
  .form_login input[type="password"]:focus {
    background: #feffef;
  }

  .form_login .bottom {
    background-color: #444;
    border-top: 1px solid #ddd;
    margin-top: 20px;
    clear: both;
    color: #fff;
    text-shadow: 1px 1px 1px #000;
  }

  .form_login .bottom a {
    display: block;
    clear: both;
    padding: 10px 30px;
    text-align: right;
    color: #ffa800;
    text-shadow: 1px 1px 1px #000;
  }

  .form_login div.remember {
    float: left;
    width: 140px;
    margin: 20px 0 20px 30px;
    font-size: 11px;
  }

  .form_login div.remember input {
    float: left;
    margin: 2px 5px 0 0;
  }

  .form_login span.error {
    visibility: hidden;
    color: red;
    font-size: 11px;
    font-style: italic;
    display: block;
    margin: 4px 30px;
  }

  .form_login input[type="submit"] {
    background: #e3e3e3;
    border: 1px solid #ccc;
    color: #333;
    font-family: "Trebuchet MS", "Myriad Pro", sans-serif;
    font-size: 14px;
    font-weight: bold;
    padding: 8px 0 9px;
    text-align: center;
    width: 150px;
    cursor: pointer;
    float: right;
    margin: 15px 20px 10px 10px;
    text-shadow: 0 1px 0 #fff;
    -moz-border-radius: 4px;
    -webkit-border-radius: 4px;
    border-radius: 4px;
    -moz-box-shadow: 0 0 2px #fff inset;
    -webkit-box-shadow: 0 0 2px #fff inset;
    box-shadow: 0 0 2px #fff inset;
  }

  .form_login input[type="submit"]:hover {
    background: #d9d9d9;
    -moz-box-shadow: 0 0 2px #eaeaea inset;
    -webkit-box-shadow: 0 0 2px #eaeaea inset;
    box-shadow: 0 0 2px #eaeaea inset;
    color: #222;
  }
</style>
