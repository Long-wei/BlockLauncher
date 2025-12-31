package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.StatusHistoryMapper;
import com.ruoyi.system.domain.StatusHistory;
import com.ruoyi.system.service.IStatusHistoryService;

/**
 * 状态变更历史Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-30
 */
@Service
public class StatusHistoryServiceImpl implements IStatusHistoryService 
{
    @Autowired
    private StatusHistoryMapper statusHistoryMapper;

    /**
     * 查询状态变更历史
     * 
     * @param id 状态变更历史主键
     * @return 状态变更历史
     */
    @Override
    public StatusHistory selectStatusHistoryById(Long id)
    {
        return statusHistoryMapper.selectStatusHistoryById(id);
    }

    /**
     * 查询状态变更历史列表
     * 
     * @param statusHistory 状态变更历史
     * @return 状态变更历史
     */
    @Override
    public List<StatusHistory> selectStatusHistoryList(StatusHistory statusHistory)
    {
        return statusHistoryMapper.selectStatusHistoryList(statusHistory);
    }

    /**
     * 新增状态变更历史
     * 
     * @param statusHistory 状态变更历史
     * @return 结果
     */
    @Override
    public int insertStatusHistory(StatusHistory statusHistory)
    {
        return statusHistoryMapper.insertStatusHistory(statusHistory);
    }

    /**
     * 修改状态变更历史
     * 
     * @param statusHistory 状态变更历史
     * @return 结果
     */
    @Override
    public int updateStatusHistory(StatusHistory statusHistory)
    {
        return statusHistoryMapper.updateStatusHistory(statusHistory);
    }

    /**
     * 批量删除状态变更历史
     * 
     * @param ids 需要删除的状态变更历史主键
     * @return 结果
     */
    @Override
    public int deleteStatusHistoryByIds(Long[] ids)
    {
        return statusHistoryMapper.deleteStatusHistoryByIds(ids);
    }

    /**
     * 删除状态变更历史信息
     * 
     * @param id 状态变更历史主键
     * @return 结果
     */
    @Override
    public int deleteStatusHistoryById(Long id)
    {
        return statusHistoryMapper.deleteStatusHistoryById(id);
    }
}
