import request from '@/utils/request'

// 查询面试问题列表
export function listQuestion(query) {
  return request({
    url: '/interview/question/list',
    method: 'get',
    params: query
  })
}

// 查询面试问题详细
export function getQuestion(id) {
  return request({
    url: '/interview/question/' + id,
    method: 'get'
  })
}

// 新增面试问题
export function addQuestion(data) {
  return request({
    url: '/interview/question',
    method: 'post',
    data: data
  })
}

// 修改面试问题
export function updateQuestion(data) {
  return request({
    url: '/interview/question',
    method: 'put',
    data: data
  })
}

// 删除面试问题
export function delQuestion(id) {
  return request({
    url: '/interview/question/' + id,
    method: 'delete'
  })
}
