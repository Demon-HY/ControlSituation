import http from '../../utils/axios-config'

/**
 * 验证登录
 * @returns {*}
 */
export function checkLogin() {
  return http.post('/api/auth/checkLogin.do', {})
}

/**
 * 登录
 * @param account 账号
 * @param password 密码
 * @returns {Promise<T> | *}
 */
export function login(account, password) {
  return http.post( '/api/auth/login.do', {
    account: account,
    password: password
  }).then(data => {
    console.log(data);
  }).catch(e => {
    console.log(e);
  });
}


