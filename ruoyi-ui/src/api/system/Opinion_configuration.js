import request from '@/utils/request'

// 查询系统配置列表
export function listOpinion_configuration(query) {
  return request({
    url: '/system/Opinion_configuration/list',
    method: 'get',
    params: query
  })
}

// 查询系统配置详细
export function getOpinion_configuration(id) {
  return request({
    url: '/system/Opinion_configuration/' + id,
    method: 'get'
  })
}

// 新增系统配置
export function addOpinion_configuration(data) {
  return request({
    url: '/system/Opinion_configuration',
    method: 'post',
    data: data
  })
}

// 修改系统配置
export function updateOpinion_configuration(data) {
  return request({
    url: '/system/Opinion_configuration',
    method: 'put',
    data: data
  })
}

// 删除系统配置
export function delOpinion_configuration(id) {
  return request({
    url: '/system/Opinion_configuration/' + id,
    method: 'delete'
  })
}
