// user login
import {getToken} from "../../../../vue-element-admin-master/src/api/qiniu";

login({ commit }, userInfo){
  const { username, password } = userInfo
  return new Promise((resolve, reject) => {
    // 调用src/api/user.js下的login方法登录
    login({ username: username.trim(), password: password }).then(response => {
      // 从响应中取出返回的数据
      const { data } = response
      // 更新store里的全局token值，一般是后台返回的，标识用户已登录的唯一标识
      commit('SET_TOKEN', data.token)
      // 设置token到cookies里，是src/utils/auth.js里的方法
      setToken(data.token)
      resolve()
    }).catch(error => {
      reject(error)
    })
  })
}
getInfo({commit, state}){
  return new Promise((resolve ,reject) => {
    console.debug('store中user.js的getinfo方法')
    console.debug('getinfo'+getToken())
    getInfo(getToken()).then(response => {
      const {data} =response
      console.debug(response)
      if(!data){
        reject('验证失败，请重新登录')
      }
      const {roles,name,avatar,introduction}=data
      //roles必须是非空数组
      if(!roles || roles.length<=0){
        reject('getinfo: roles must be a non-null array ')
      }
      //更新roles中角色 用户 图片 个人介绍
      commit('SET_ROLES',roles)
      commit('SET_NAME', name)
      commit('SET_AVATAR', avatar)
      commit('SET_INTRODUCTION', introduction)
      resolve(data)
    }).catch(error => {
      reject(error)
    })
  })
}
