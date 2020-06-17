import axios from './axios'
/*
 * 将所有接口统一起来便于维护
 * 如果项目很大可以将 url 独立成文件，接口分成不同的模块
 */
// 单独导出
export const login = data => {
  return axios({
    url: '/users/login',
    method: 'post',
    data
  })
}
export const getUser = () => {
  return axios({
    url: '/user',
    method: 'get'
  })
}
export const getMenu = data =>{
  return axios({
    url: '/menu',
    method: 'get'
  })
}
export default {
  login,
  getUser,
  getMenu
}
