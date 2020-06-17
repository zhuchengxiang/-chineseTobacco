import { asyncRoutes, constantRoutes } from '@/router'

/**
 * 使用 meta.role 确定当前用户是否具有权限
 * @param roles
 * @param route
 */
function hasPermission(roles, route) {
  if (route.meta && route.meta.roles) {
    console.debug("-----------hasPermission:true")
    console.debug(route.meta.roles)
    return roles.some(role => route.meta.roles.includes(role))
  } else {
    ("-----------hasPermission:false")
    return true
  }
}

/**
 * Filter asynchronous routing tables by recursion
 * @param routes asyncRoutes
 * @param roles
 */
export function filterAsyncRoutes(routes, roles) {
  const res = []

  routes.forEach(route => {
    const tmp = { ...route }
    if (hasPermission(roles, tmp)) {
      if (tmp.children) {
        tmp.children = filterAsyncRoutes(tmp.children, roles)
      }
      res.push(tmp)
    }
  })

  return res
}

const state = {
  routes: [],
  addRoutes: []
}

const mutations = {
  SET_ROUTES: (state, routes) => {
    state.addRoutes = routes
    state.routes = constantRoutes.concat(routes)
  }
}


const actions = {
  generateRoutes({ commit }, roles) {
    return new Promise(resolve => {
      let accessedRoutes
      // 这是原来的代码
      // if (roles.includes('admin')) {
      //   accessedRoutes = asyncRoutes || []
      // } else {
      //
      // }
      console.debug('generateRoutes--------')
      console.debug(asyncRoutes)
      // 将else中的代码拿出来，改为验证所有角色权限，根据角色的名字来动态生成要加载的菜单
      accessedRoutes = filterAsyncRoutes(asyncRoutes, roles)
      console.debug(accessedRoutes)
      commit('SET_ROUTES', accessedRoutes)
      resolve(accessedRoutes)
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
