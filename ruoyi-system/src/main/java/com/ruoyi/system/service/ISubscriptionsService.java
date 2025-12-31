package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Subscriptions;

/**
 * 关注订阅Service接口
 * 
 * @author ruoyi
 * @date 2025-12-30
 */
public interface ISubscriptionsService 
{
    /**
     * 查询关注订阅
     * 
     * @param id 关注订阅主键
     * @return 关注订阅
     */
    public Subscriptions selectSubscriptionsById(Long id);

    /**
     * 查询关注订阅列表
     * 
     * @param subscriptions 关注订阅
     * @return 关注订阅集合
     */
    public List<Subscriptions> selectSubscriptionsList(Subscriptions subscriptions);

    /**
     * 新增关注订阅
     * 
     * @param subscriptions 关注订阅
     * @return 结果
     */
    public int insertSubscriptions(Subscriptions subscriptions);

    /**
     * 修改关注订阅
     * 
     * @param subscriptions 关注订阅
     * @return 结果
     */
    public int updateSubscriptions(Subscriptions subscriptions);

    /**
     * 批量删除关注订阅
     * 
     * @param ids 需要删除的关注订阅主键集合
     * @return 结果
     */
    public int deleteSubscriptionsByIds(Long[] ids);

    /**
     * 删除关注订阅信息
     * 
     * @param id 关注订阅主键
     * @return 结果
     */
    public int deleteSubscriptionsById(Long id);
}
