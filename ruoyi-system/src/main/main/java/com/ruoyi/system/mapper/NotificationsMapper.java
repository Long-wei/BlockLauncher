package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Notifications;

/**
 * 通知Mapper接口
 * 
 * @author ruoyi
 * @date 2025-12-30
 */
public interface NotificationsMapper 
{
    /**
     * 查询通知
     * 
     * @param id 通知主键
     * @return 通知
     */
    public Notifications selectNotificationsById(Long id);

    /**
     * 查询通知列表
     * 
     * @param notifications 通知
     * @return 通知集合
     */
    public List<Notifications> selectNotificationsList(Notifications notifications);

    /**
     * 新增通知
     * 
     * @param notifications 通知
     * @return 结果
     */
    public int insertNotifications(Notifications notifications);

    /**
     * 修改通知
     * 
     * @param notifications 通知
     * @return 结果
     */
    public int updateNotifications(Notifications notifications);

    /**
     * 删除通知
     * 
     * @param id 通知主键
     * @return 结果
     */
    public int deleteNotificationsById(Long id);

    /**
     * 批量删除通知
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNotificationsByIds(Long[] ids);
}
