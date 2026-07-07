import request from '@/utils/request'

// 查询模拟面试记录列表
export function listRecord(query) {
  return request({
    url: '/interview/record/list',
    method: 'get',
    params: query
  })
}

// 查询模拟面试记录详细
export function getRecord(id) {
  return request({
    url: '/interview/record/' + id,
    method: 'get'
  })
}

// 新增模拟面试记录
export function addRecord(data) {
  return request({
    url: '/interview/record',
    method: 'post',
    data: data
  })
}

// 修改模拟面试记录
export function updateRecord(data) {
  return request({
    url: '/interview/record',
    method: 'put',
    data: data
  })
}

// 删除模拟面试记录
export function delRecord(id) {
  return request({
    url: '/interview/record/' + id,
    method: 'delete'
  })
}
