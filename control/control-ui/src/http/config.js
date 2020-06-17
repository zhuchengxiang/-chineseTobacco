export default {
  method: 'get',
  // 基础url前缀 修改为后台的访问地址
  baseURL: 'http://127.0.0.1:8080/control/',
  // 请求头信息
  headers: {
    'Content-Type': 'application/json;charset=UTF-8'
  },
  // 参数
  data: {},
  // 设置超时
  timeout: 10000,
  // 携带凭证
  withCredentials: true,
  // 返回数据
  responseType: 'json'

}
