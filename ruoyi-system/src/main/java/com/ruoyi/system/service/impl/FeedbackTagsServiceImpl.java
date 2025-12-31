package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.FeedbackTagsMapper;
import com.ruoyi.system.domain.FeedbackTags;
import com.ruoyi.system.service.IFeedbackTagsService;

/**
 * 反馈标签关联Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-30
 */
@Service
public class FeedbackTagsServiceImpl implements IFeedbackTagsService 
{
    @Autowired
    private FeedbackTagsMapper feedbackTagsMapper;

    /**
     * 查询反馈标签关联
     * 
     * @param feedbackId 反馈标签关联主键
     * @return 反馈标签关联
     */
    @Override
    public FeedbackTags selectFeedbackTagsByFeedbackId(Long feedbackId)
    {
        return feedbackTagsMapper.selectFeedbackTagsByFeedbackId(feedbackId);
    }

    /**
     * 查询反馈标签关联列表
     * 
     * @param feedbackTags 反馈标签关联
     * @return 反馈标签关联
     */
    @Override
    public List<FeedbackTags> selectFeedbackTagsList(FeedbackTags feedbackTags)
    {
        return feedbackTagsMapper.selectFeedbackTagsList(feedbackTags);
    }

    /**
     * 新增反馈标签关联
     * 
     * @param feedbackTags 反馈标签关联
     * @return 结果
     */
    @Override
    public int insertFeedbackTags(FeedbackTags feedbackTags)
    {
        return feedbackTagsMapper.insertFeedbackTags(feedbackTags);
    }

    /**
     * 修改反馈标签关联
     * 
     * @param feedbackTags 反馈标签关联
     * @return 结果
     */
    @Override
    public int updateFeedbackTags(FeedbackTags feedbackTags)
    {
        return feedbackTagsMapper.updateFeedbackTags(feedbackTags);
    }

    /**
     * 批量删除反馈标签关联
     * 
     * @param feedbackIds 需要删除的反馈标签关联主键
     * @return 结果
     */
    @Override
    public int deleteFeedbackTagsByFeedbackIds(Long[] feedbackIds)
    {
        return feedbackTagsMapper.deleteFeedbackTagsByFeedbackIds(feedbackIds);
    }

    /**
     * 删除反馈标签关联信息
     * 
     * @param feedbackId 反馈标签关联主键
     * @return 结果
     */
    @Override
    public int deleteFeedbackTagsByFeedbackId(Long feedbackId)
    {
        return feedbackTagsMapper.deleteFeedbackTagsByFeedbackId(feedbackId);
    }
}
