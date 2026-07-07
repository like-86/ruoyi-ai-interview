import request from '@/utils/request'

export function listPositionAll() {
  return request({
    url: '/interview/position/listAll',
    method: 'get'
  })
}
// 查询岗位列表
export function listPosition(query) {
  return request({
    url: '/interview/position/list',
    method: 'get',
    params: query
  })
}

// 查询岗位详细
export function getPosition(id) {
  return request({
    url: '/interview/position/' + id,
    method: 'get'
  })
}

// 新增岗位
export function addPosition(data) {
  return request({
    url: '/interview/position',
    method: 'post',
    data: data
  })
}

// 修改岗位
export function updatePosition(data) {
  return request({
    url: '/interview/position',
    method: 'put',
    data: data
  })
}

// 删除岗位
export function delPosition(id) {
  return request({
    url: '/interview/position/' + id,
    method: 'delete'
  })
}
