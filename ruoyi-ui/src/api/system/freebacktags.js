import request from '@/utils/request'

// 查询反馈标签关联列表
export function listTags(query) {
  return request({
    url: '/system/tags/list',
    method: 'get',
    params: query
  })
}

// 查询反馈标签关联详细
export function getTags(feedbackId) {
  return request({
    url: '/system/tags/' + feedbackId,
    method: 'get'
  })
}

// 新增反馈标签关联
export function addTags(data) {
  return request({
    url: '/system/tags',
    method: 'post',
    data: data
  })
}

// 修改反馈标签关联
export function updateTags(data) {
  return request({
    url: '/system/tags',
    method: 'put',
    data: data
  })
}

// 删除反馈标签关联
export function delTags(feedbackId) {
  return request({
    url: '/system/tags/' + feedbackId,
    method: 'delete'
  })
}
