import request from '@/utils/request'

export function listAll() {
  return request({
    url: '/system/class/listAll',
    method: 'get'
  })
}
// 查询班级信息列表
export function listClass(query) {
  return request({
    url: '/system/class/list',
    method: 'get',
    params: query
  })
}

// 查询班级信息详细
export function getClass(id) {
  return request({
    url: '/system/class/' + id,
    method: 'get'
  })
}

// 新增班级信息
export function addClass(data) {
  return request({
    url: '/system/class',
    method: 'post',
    data: data
  })
}

// 修改班级信息
export function updateClass(data) {
  return request({
    url: '/system/class',
    method: 'put',
    data: data
  })
}

// 删除班级信息
export function delClass(id) {
  return request({
    url: '/system/class/' + id,
    method: 'delete'
  })
}
