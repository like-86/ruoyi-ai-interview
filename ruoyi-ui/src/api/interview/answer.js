import request from '@/utils/request'

// 查询面试回答列表
export function listAnswer(query) {
  return request({
    url: '/interview/answer/list',
    method: 'get',
    params: query
  })
}

// 查询面试回答详细
export function getAnswer(id) {
  return request({
    url: '/interview/answer/' + id,
    method: 'get'
  })
}

// 新增面试回答
export function addAnswer(data) {
  return request({
    url: '/interview/answer',
    method: 'post',
    data: data
  })
}

// 修改面试回答
export function updateAnswer(data) {
  return request({
    url: '/interview/answer',
    method: 'put',
    data: data
  })
}

// 删除面试回答
export function delAnswer(id) {
  return request({
    url: '/interview/answer/' + id,
    method: 'delete'
  })
}
