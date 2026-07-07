import request from '@/utils/request'

// 查询面试维度列表
export function listDimension(query) {
  return request({
    url: '/interview/dimension/list',
    method: 'get',
    params: query
  })
}

// 查询面试维度详细
export function getDimension(id) {
  return request({
    url: '/interview/dimension/' + id,
    method: 'get'
  })
}

// 新增面试维度
export function addDimension(data) {
  return request({
    url: '/interview/dimension',
    method: 'post',
    data: data
  })
}

// 修改面试维度
export function updateDimension(data) {
  return request({
    url: '/interview/dimension',
    method: 'put',
    data: data
  })
}

// 删除面试维度
export function delDimension(id) {
  return request({
    url: '/interview/dimension/' + id,
    method: 'delete'
  })
}
