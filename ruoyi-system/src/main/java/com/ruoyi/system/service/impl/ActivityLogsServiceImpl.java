package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ActivityLogsMapper;
import com.ruoyi.system.domain.ActivityLogs;
import com.ruoyi.system.service.IActivityLogsService;

/**
 * 活动日志Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-30
 */
@Service
public class ActivityLogsServiceImpl implements IActivityLogsService 
{
    @Autowired
    private ActivityLogsMapper activityLogsMapper;

    /**
     * 查询活动日志
     * 
     * @param id 活动日志主键
     * @return 活动日志
     */
    @Override
    public ActivityLogs selectActivityLogsById(Long id)
    {
        return activityLogsMapper.selectActivityLogsById(id);
    }

    /**
     * 查询活动日志列表
     * 
     * @param activityLogs 活动日志
     * @return 活动日志
     */
    @Override
    public List<ActivityLogs> selectActivityLogsList(ActivityLogs activityLogs)
    {
        return activityLogsMapper.selectActivityLogsList(activityLogs);
    }

    /**
     * 新增活动日志
     * 
     * @param activityLogs 活动日志
     * @return 结果
     */
    @Override
    public int insertActivityLogs(ActivityLogs activityLogs)
    {
        return activityLogsMapper.insertActivityLogs(activityLogs);
    }

    /**
     * 修改活动日志
     * 
     * @param activityLogs 活动日志
     * @return 结果
     */
    @Override
    public int updateActivityLogs(ActivityLogs activityLogs)
    {
        return activityLogsMapper.updateActivityLogs(activityLogs);
    }

    /**
     * 批量删除活动日志
     * 
     * @param ids 需要删除的活动日志主键
     * @return 结果
     */
    @Override
    public int deleteActivityLogsByIds(Long[] ids)
    {
        return activityLogsMapper.deleteActivityLogsByIds(ids);
    }

    /**
     * 删除活动日志信息
     * 
     * @param id 活动日志主键
     * @return 结果
     */
    @Override
    public int deleteActivityLogsById(Long id)
    {
        return activityLogsMapper.deleteActivityLogsById(id);
    }
}
