package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.FeedbackTags;

/**
 * 反馈标签关联Service接口
 * 
 * @author ruoyi
 * @date 2025-12-30
 */
public interface IFeedbackTagsService 
{
    /**
     * 查询反馈标签关联
     * 
     * @param feedbackId 反馈标签关联主键
     * @return 反馈标签关联
     */
    public FeedbackTags selectFeedbackTagsByFeedbackId(Long feedbackId);

    /**
     * 查询反馈标签关联列表
     * 
     * @param feedbackTags 反馈标签关联
     * @return 反馈标签关联集合
     */
    public List<FeedbackTags> selectFeedbackTagsList(FeedbackTags feedbackTags);

    /**
     * 新增反馈标签关联
     * 
     * @param feedbackTags 反馈标签关联
     * @return 结果
     */
    public int insertFeedbackTags(FeedbackTags feedbackTags);

    /**
     * 修改反馈标签关联
     * 
     * @param feedbackTags 反馈标签关联
     * @return 结果
     */
    public int updateFeedbackTags(FeedbackTags feedbackTags);

    /**
     * 批量删除反馈标签关联
     * 
     * @param feedbackIds 需要删除的反馈标签关联主键集合
     * @return 结果
     */
    public int deleteFeedbackTagsByFeedbackIds(Long[] feedbackIds);

    /**
     * 删除反馈标签关联信息
     * 
     * @param feedbackId 反馈标签关联主键
     * @return 结果
     */
    public int deleteFeedbackTagsByFeedbackId(Long feedbackId);
}
