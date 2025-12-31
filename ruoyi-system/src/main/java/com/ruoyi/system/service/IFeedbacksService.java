package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Feedbacks;

/**
 * 反馈意见Service接口
 * 
 * @author ruoyi
 * @date 2025-12-30
 */
public interface IFeedbacksService 
{
    /**
     * 查询反馈意见
     * 
     * @param id 反馈意见主键
     * @return 反馈意见
     */
    public Feedbacks selectFeedbacksById(Long id);

    /**
     * 查询反馈意见列表
     * 
     * @param feedbacks 反馈意见
     * @return 反馈意见集合
     */
    public List<Feedbacks> selectFeedbacksList(Feedbacks feedbacks);

    /**
     * 新增反馈意见
     * 
     * @param feedbacks 反馈意见
     * @return 结果
     */
    public int insertFeedbacks(Feedbacks feedbacks);

    /**
     * 修改反馈意见
     * 
     * @param feedbacks 反馈意见
     * @return 结果
     */
    public int updateFeedbacks(Feedbacks feedbacks);

    /**
     * 批量删除反馈意见
     * 
     * @param ids 需要删除的反馈意见主键集合
     * @return 结果
     */
    public int deleteFeedbacksByIds(Long[] ids);

    /**
     * 删除反馈意见信息
     * 
     * @param id 反馈意见主键
     * @return 结果
     */
    public int deleteFeedbacksById(Long id);
}
