// 菜单模块接口
import http from '../../utils/axios-config'

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
  });
}
