package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SubscriptionsMapper;
import com.ruoyi.system.domain.Subscriptions;
import com.ruoyi.system.service.ISubscriptionsService;

/**
 * 关注订阅Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-30
 */
@Service
public class SubscriptionsServiceImpl implements ISubscriptionsService 
{
    @Autowired
    private SubscriptionsMapper subscriptionsMapper;

    /**
     * 查询关注订阅
     * 
     * @param id 关注订阅主键
     * @return 关注订阅
     */
    @Override
    public Subscriptions selectSubscriptionsById(Long id)
    {
        return subscriptionsMapper.selectSubscriptionsById(id);
    }

    /**
     * 查询关注订阅列表
     * 
     * @param subscriptions 关注订阅
     * @return 关注订阅
     */
    @Override
    public List<Subscriptions> selectSubscriptionsList(Subscriptions subscriptions)
    {
        return subscriptionsMapper.selectSubscriptionsList(subscriptions);
    }

    /**
     * 新增关注订阅
     * 
     * @param subscriptions 关注订阅
     * @return 结果
     */
    @Override
    public int insertSubscriptions(Subscriptions subscriptions)
    {
        return subscriptionsMapper.insertSubscriptions(subscriptions);
    }

    /**
     * 修改关注订阅
     * 
     * @param subscriptions 关注订阅
     * @return 结果
     */
    @Override
    public int updateSubscriptions(Subscriptions subscriptions)
    {
        return subscriptionsMapper.updateSubscriptions(subscriptions);
    }

    /**
     * 批量删除关注订阅
     * 
     * @param ids 需要删除的关注订阅主键
     * @return 结果
     */
    @Override
    public int deleteSubscriptionsByIds(Long[] ids)
    {
        return subscriptionsMapper.deleteSubscriptionsByIds(ids);
    }

    /**
     * 删除关注订阅信息
     * 
     * @param id 关注订阅主键
     * @return 结果
     */
    @Override
    public int deleteSubscriptionsById(Long id)
    {
        return subscriptionsMapper.deleteSubscriptionsById(id);
    }
}
