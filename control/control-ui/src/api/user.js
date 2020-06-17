export function login(data) {
//request是以封装axios 直接进行ajax操作
  //注意ajax请求baseURL： proce.env.VUE_APP_BASE_API
  return request({
    url: 'users/login',
    method: 'post',
    data
  })
}
