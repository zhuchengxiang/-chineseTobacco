import request from '@/utils/request'
/*
export function getRoutes() {
  return request({
    url: '/vue-element-admin/routes',
    method: 'get'
  })
}*/

export function list(data) {
  return request({
    url: '/role/list',
    method: 'post',
    data
  })
}

export function add(data) {
  console.debug('进入add')
  return request({
    url: '/role/add',
    method: 'post',
    data
  })
}
export function update(data) {
  console.debug('进入update')
  return request({
    url: '/role/update',
    method: 'post',
    data
  })
}


export function deleteZT(id) {
  console.debug('进入deleteZT')
  return request({
    url: '/role/deleteZT',
    method: 'post',
     params: {id}
  })
}
