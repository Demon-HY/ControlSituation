// HTTP Axios 请求封装
import Axios from 'axios'
import Vue from 'vue';
// import store from '../store'
const http = Axios.create({
  // baseURL: process.env.BASE_URL,
  baseURL: 'http://localhost:8080/',
  responseType: 'json',
  withCredentials: true, // 请求会带上 Cookies
  headers: {
    'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
  },
  transformRequest: [function (data) {
    // 请求前处理数据
    let newData = '';
    for (let k in data) {
      if (data.hasOwnProperty(k) === true) {
        newData += encodeURIComponent(k) + '=' + encodeURIComponent(data[k]) + '&';
      }
    }
    return newData;
  }],
  transformResponse: [(data) => {
    // 这里提前处理返回的数据
    return data;
  }],
  timeout: 3000 // 超时时间
});

// http.baseURL = process.env.BABEL_ENV;

// 请求时的拦截
http.interceptors.request.use(config => {
    return config;
  }, error => {
    console.log(error);
    return Promise.reject(error);
  }
);

// 响应时的拦截
http.interceptors.response.use(response => {
    return response
  },
  error => {
    console.log(error);// for debug
    // Vue.$message.error(error.message);
    return Promise.reject(error);
  }
);

function apiAxios(method, url, params) {
  return http({
    method: method,
    url: url,
    data: method === 'POST' || method === 'PUT' ? params : null,
    params: method === 'GET' || method === 'DELETE' ? params : null,
  });
}

export default {
  get: function (url, params) {
    return apiAxios('GET', url, params)
  },
  post: function (url, params) {
    return apiAxios('POST', url, params)
  },
  put: function (url, params) {
    return apiAxios('PUT', url, params)
  },
  delete: function (url, params) {
    return apiAxios('DELETE', url, params)
  }
}

// export default http;
