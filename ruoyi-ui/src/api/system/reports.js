import request from '@/utils/request'

// 查询举报列表
export function listReports(query) {
  return request({
    url: '/system/reports/list',
    method: 'get',
    params: query
  })
}

// 查询举报详细
export function getReports(id) {
  return request({
    url: '/system/reports/' + id,
    method: 'get'
  })
}

// 新增举报
export function addReports(data) {
  return request({
    url: '/system/reports',
    method: 'post',
    data: data
  })
}

// 修改举报
export function updateReports(data) {
  return request({
    url: '/system/reports',
    method: 'put',
    data: data
  })
}

// 删除举报
export function delReports(id) {
  return request({
    url: '/system/reports/' + id,
    method: 'delete'
  })
}
