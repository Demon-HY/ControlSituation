import Api from '../util'

const loginApi = {
  // 验证登录
  checkLogin: () => {
    return Api.get('/api/auth/checkLogin');
  },

  // 登录
  login: function(account, password) {
    return Api.post('/api/auth/login', {
      account: account,
      password: password
    });
  }
};

module.exports = loginApi;
