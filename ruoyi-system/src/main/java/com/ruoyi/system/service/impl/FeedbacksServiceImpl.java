package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.FeedbacksMapper;
import com.ruoyi.system.domain.Feedbacks;
import com.ruoyi.system.service.IFeedbacksService;

/**
 * 反馈意见Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-30
 */
@Service
public class FeedbacksServiceImpl implements IFeedbacksService 
{
    @Autowired
    private FeedbacksMapper feedbacksMapper;

    /**
     * 查询反馈意见
     * 
     * @param id 反馈意见主键
     * @return 反馈意见
     */
    @Override
    public Feedbacks selectFeedbacksById(Long id)
    {
        return feedbacksMapper.selectFeedbacksById(id);
    }

    /**
     * 查询反馈意见列表
     * 
     * @param feedbacks 反馈意见
     * @return 反馈意见
     */
    @Override
    public List<Feedbacks> selectFeedbacksList(Feedbacks feedbacks)
    {
        return feedbacksMapper.selectFeedbacksList(feedbacks);
    }

    /**
     * 新增反馈意见
     * 
     * @param feedbacks 反馈意见
     * @return 结果
     */
    @Override
    public int insertFeedbacks(Feedbacks feedbacks)
    {
        return feedbacksMapper.insertFeedbacks(feedbacks);
    }

    /**
     * 修改反馈意见
     * 
     * @param feedbacks 反馈意见
     * @return 结果
     */
    @Override
    public int updateFeedbacks(Feedbacks feedbacks)
    {
        return feedbacksMapper.updateFeedbacks(feedbacks);
    }

    /**
     * 批量删除反馈意见
     * 
     * @param ids 需要删除的反馈意见主键
     * @return 结果
     */
    @Override
    public int deleteFeedbacksByIds(Long[] ids)
    {
        return feedbacksMapper.deleteFeedbacksByIds(ids);
    }

    /**
     * 删除反馈意见信息
     * 
     * @param id 反馈意见主键
     * @return 结果
     */
    @Override
    public int deleteFeedbacksById(Long id)
    {
        return feedbacksMapper.deleteFeedbacksById(id);
    }
}
