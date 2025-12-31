import request from '@/utils/request'

// 查询反馈意见列表
export function listFeedbacks(query) {
  return request({
    url: '/system/feedbacks/list',
    method: 'get',
    params: query
  })
}

// 查询反馈意见详细
export function getFeedbacks(id) {
  return request({
    url: '/system/feedbacks/' + id,
    method: 'get'
  })
}

// 新增反馈意见
export function addFeedbacks(data) {
  return request({
    url: '/system/feedbacks',
    method: 'post',
    data: data
  })
}

// 修改反馈意见
export function updateFeedbacks(data) {
  return request({
    url: '/system/feedbacks',
    method: 'put',
    data: data
  })
}

// 删除反馈意见
export function delFeedbacks(id) {
  return request({
    url: '/system/feedbacks/' + id,
    method: 'delete'
  })
}
