package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.NotificationsMapper;
import com.ruoyi.system.domain.Notifications;
import com.ruoyi.system.service.INotificationsService;

/**
 * 通知Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-30
 */
@Service
public class NotificationsServiceImpl implements INotificationsService 
{
    @Autowired
    private NotificationsMapper notificationsMapper;

    /**
     * 查询通知
     * 
     * @param id 通知主键
     * @return 通知
     */
    @Override
    public Notifications selectNotificationsById(Long id)
    {
        return notificationsMapper.selectNotificationsById(id);
    }

    /**
     * 查询通知列表
     * 
     * @param notifications 通知
     * @return 通知
     */
    @Override
    public List<Notifications> selectNotificationsList(Notifications notifications)
    {
        return notificationsMapper.selectNotificationsList(notifications);
    }

    /**
     * 新增通知
     * 
     * @param notifications 通知
     * @return 结果
     */
    @Override
    public int insertNotifications(Notifications notifications)
    {
        return notificationsMapper.insertNotifications(notifications);
    }

    /**
     * 修改通知
     * 
     * @param notifications 通知
     * @return 结果
     */
    @Override
    public int updateNotifications(Notifications notifications)
    {
        return notificationsMapper.updateNotifications(notifications);
    }

    /**
     * 批量删除通知
     * 
     * @param ids 需要删除的通知主键
     * @return 结果
     */
    @Override
    public int deleteNotificationsByIds(Long[] ids)
    {
        return notificationsMapper.deleteNotificationsByIds(ids);
    }

    /**
     * 删除通知信息
     * 
     * @param id 通知主键
     * @return 结果
     */
    @Override
    public int deleteNotificationsById(Long id)
    {
        return notificationsMapper.deleteNotificationsById(id);
    }
}
