import request from '@/utils/request'

// 查询投票列表
export function listVotes(query) {
  return request({
    url: '/system/votes/list',
    method: 'get',
    params: query
  })
}

// 查询投票详细
export function getVotes(id) {
  return request({
    url: '/system/votes/' + id,
    method: 'get'
  })
}

// 新增投票
export function addVotes(data) {
  return request({
    url: '/system/votes',
    method: 'post',
    data: data
  })
}

// 修改投票
export function updateVotes(data) {
  return request({
    url: '/system/votes',
    method: 'put',
    data: data
  })
}

// 删除投票
export function delVotes(id) {
  return request({
    url: '/system/votes/' + id,
    method: 'delete'
  })
}
