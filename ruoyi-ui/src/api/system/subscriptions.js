import request from '@/utils/request'

// 查询关注订阅列表
export function listSubscriptions(query) {
  return request({
    url: '/system/subscriptions/list',
    method: 'get',
    params: query
  })
}

// 查询关注订阅详细
export function getSubscriptions(id) {
  return request({
    url: '/system/subscriptions/' + id,
    method: 'get'
  })
}

// 新增关注订阅
export function addSubscriptions(data) {
  return request({
    url: '/system/subscriptions',
    method: 'post',
    data: data
  })
}

// 修改关注订阅
export function updateSubscriptions(data) {
  return request({
    url: '/system/subscriptions',
    method: 'put',
    data: data
  })
}

// 删除关注订阅
export function delSubscriptions(id) {
  return request({
    url: '/system/subscriptions/' + id,
    method: 'delete'
  })
}
