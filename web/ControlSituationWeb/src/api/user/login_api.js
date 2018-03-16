import http from '../../utils/axios-config'

// const loginApi = {
//   // 验证登录
//   checkLogin: () => {
//     return http.post('/api/auth/checkLogin', {})
//   },
//
//   // 登录
//   login: (account, password) => {
//     alert('login');
//     return http.post( '/api/auth/login', {
//       account: account,
//       password: password
//     });
//   }
// };
//
// export default {
//   loginApi: loginApi
// }


export function checkLogin() {
  return http.post('/api/auth/checkLogin', {})
}

export function login(account, password) {
  return http.post( '/api/auth/login', {
    account: account,
    password: password
  });
}


