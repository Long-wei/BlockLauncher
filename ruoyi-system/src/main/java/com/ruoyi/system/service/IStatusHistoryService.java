package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.StatusHistory;

/**
 * 状态变更历史Service接口
 * 
 * @author ruoyi
 * @date 2025-12-30
 */
public interface IStatusHistoryService 
{
    /**
     * 查询状态变更历史
     * 
     * @param id 状态变更历史主键
     * @return 状态变更历史
     */
    public StatusHistory selectStatusHistoryById(Long id);

    /**
     * 查询状态变更历史列表
     * 
     * @param statusHistory 状态变更历史
     * @return 状态变更历史集合
     */
    public List<StatusHistory> selectStatusHistoryList(StatusHistory statusHistory);

    /**
     * 新增状态变更历史
     * 
     * @param statusHistory 状态变更历史
     * @return 结果
     */
    public int insertStatusHistory(StatusHistory statusHistory);

    /**
     * 修改状态变更历史
     * 
     * @param statusHistory 状态变更历史
     * @return 结果
     */
    public int updateStatusHistory(StatusHistory statusHistory);

    /**
     * 批量删除状态变更历史
     * 
     * @param ids 需要删除的状态变更历史主键集合
     * @return 结果
     */
    public int deleteStatusHistoryByIds(Long[] ids);

    /**
     * 删除状态变更历史信息
     * 
     * @param id 状态变更历史主键
     * @return 结果
     */
    public int deleteStatusHistoryById(Long id);
}
