import request from '@/utils/request'

// 查询反馈分类列表
export function listCategories(query) {
  return request({
    url: '/system/categories/list',
    method: 'get',
    params: query
  })
}

// 查询反馈分类详细
export function getCategories(id) {
  return request({
    url: '/system/categories/' + id,
    method: 'get'
  })
}

// 新增反馈分类
export function addCategories(data) {
  return request({
    url: '/system/categories',
    method: 'post',
    data: data
  })
}

// 修改反馈分类
export function updateCategories(data) {
  return request({
    url: '/system/categories',
    method: 'put',
    data: data
  })
}

// 删除反馈分类
export function delCategories(id) {
  return request({
    url: '/system/categories/' + id,
    method: 'delete'
  })
}
