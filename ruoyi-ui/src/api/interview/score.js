import request from '@/utils/request'

// 查询维度评分列表
export function listScore(query) {
  return request({
    url: '/interview/score/list',
    method: 'get',
    params: query
  })
}

// 查询维度评分详细
export function getScore(id) {
  return request({
    url: '/interview/score/' + id,
    method: 'get'
  })
}

// 新增维度评分
export function addScore(data) {
  return request({
    url: '/interview/score',
    method: 'post',
    data: data
  })
}

// 修改维度评分
export function updateScore(data) {
  return request({
    url: '/interview/score',
    method: 'put',
    data: data
  })
}

// 删除维度评分
export function delScore(id) {
  return request({
    url: '/interview/score/' + id,
    method: 'delete'
  })
}
