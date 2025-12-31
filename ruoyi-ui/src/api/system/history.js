import request from '@/utils/request'

// 查询状态变更历史列表
export function listHistory(query) {
  return request({
    url: '/system/history/list',
    method: 'get',
    params: query
  })
}

// 查询状态变更历史详细
export function getHistory(id) {
  return request({
    url: '/system/history/' + id,
    method: 'get'
  })
}

// 新增状态变更历史
export function addHistory(data) {
  return request({
    url: '/system/history',
    method: 'post',
    data: data
  })
}

// 修改状态变更历史
export function updateHistory(data) {
  return request({
    url: '/system/history',
    method: 'put',
    data: data
  })
}

// 删除状态变更历史
export function delHistory(id) {
  return request({
    url: '/system/history/' + id,
    method: 'delete'
  })
}
