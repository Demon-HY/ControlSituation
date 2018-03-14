// HTTP Axios 请求封装
import Axios from 'axios'

let http = Axios.create({
  baseURL: "http://localhost:8080/",
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

// 请求时的拦截
http.interceptors.request.use(
  (config) => {
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

// 响应时的拦截
http.interceptors.response.use(
  (response) => {
    return response
  },
  (error) => {
    return Promise.reject(error);
  }
);

function apiAxios(method, url) {
  let result = {};
  alert(url);
  http({
    method: method,
    url: url,
    data: method === 'POST' || method === 'PUT' ? params : null,
    params: method === 'GET' || method === 'DELETE' ? params : null,
  }).then(function (res) {
    result = res.data;
    result.status = res.status;
  }).catch(function (err) {
    result.err = err.message;
  });

  return result;
}

export default {
  get: function (url, params) {
    return apiAxios('GET', url, params)
  },
  get: function (url) {
    return apiAxios('GET', url)
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
