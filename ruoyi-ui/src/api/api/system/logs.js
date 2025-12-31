import request from '@/utils/request'

// 查询活动日志列表
export function listLogs(query) {
  return request({
    url: '/system/logs/list',
    method: 'get',
    params: query
  })
}

// 查询活动日志详细
export function getLogs(id) {
  return request({
    url: '/system/logs/' + id,
    method: 'get'
  })
}

// 新增活动日志
export function addLogs(data) {
  return request({
    url: '/system/logs',
    method: 'post',
    data: data
  })
}

// 修改活动日志
export function updateLogs(data) {
  return request({
    url: '/system/logs',
    method: 'put',
    data: data
  })
}

// 删除活动日志
export function delLogs(id) {
  return request({
    url: '/system/logs/' + id,
    method: 'delete'
  })
}
