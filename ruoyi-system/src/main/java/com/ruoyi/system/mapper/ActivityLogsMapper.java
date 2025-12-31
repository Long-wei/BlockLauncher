package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.ActivityLogs;

/**
 * 活动日志Mapper接口
 * 
 * @author ruoyi
 * @date 2025-12-30
 */
public interface ActivityLogsMapper 
{
    /**
     * 查询活动日志
     * 
     * @param id 活动日志主键
     * @return 活动日志
     */
    public ActivityLogs selectActivityLogsById(Long id);

    /**
     * 查询活动日志列表
     * 
     * @param activityLogs 活动日志
     * @return 活动日志集合
     */
    public List<ActivityLogs> selectActivityLogsList(ActivityLogs activityLogs);

    /**
     * 新增活动日志
     * 
     * @param activityLogs 活动日志
     * @return 结果
     */
    public int insertActivityLogs(ActivityLogs activityLogs);

    /**
     * 修改活动日志
     * 
     * @param activityLogs 活动日志
     * @return 结果
     */
    public int updateActivityLogs(ActivityLogs activityLogs);

    /**
     * 删除活动日志
     * 
     * @param id 活动日志主键
     * @return 结果
     */
    public int deleteActivityLogsById(Long id);

    /**
     * 批量删除活动日志
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteActivityLogsByIds(Long[] ids);
}
