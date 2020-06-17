import request from '@/utils/request'
// 从后台读取分好组的部门信息
export function groupDept() {
  return request({
    url: '/dept/groupDept',
    method: 'get'
  })
}

export function add(data) {
  return request({
    url: '/dept/add',
    method: 'post',
    data
  })
}

export function deleteUser(id) {
  return request({
    url: '/dept/delete',
    method: 'get',
    params: { id }
  })
}

export function update(data) {
  return request({
    url: '/dept/update',
    method: 'post',
    data
  })
}

export function list(data) {
  return request({
    url: '/dept/list',
    method: 'post',
    data
  })
}
