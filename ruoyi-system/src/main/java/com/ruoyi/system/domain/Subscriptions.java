package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 关注订阅对象 subscriptions
 * 
 * @author ruoyi
 * @date 2025-12-30
 */
public class Subscriptions extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 关注ID */
    private Long id;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 关注的反馈ID */
    @Excel(name = "关注的反馈ID")
    private Long feedbackId;

    /** 关注的分类ID */
    @Excel(name = "关注的分类ID")
    private Long categoryId;

    /** 关注类型 */
    @Excel(name = "关注类型")
    private String subscriptionType;

    /** 是否邮件通知 */
    @Excel(name = "是否邮件通知")
    private Integer notifyEmail;

    /** 是否推送通知 */
    @Excel(name = "是否推送通知")
    private Integer notifyPush;

    /** 关注时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "关注时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdAt;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    public void setFeedbackId(Long feedbackId) 
    {
        this.feedbackId = feedbackId;
    }

    public Long getFeedbackId() 
    {
        return feedbackId;
    }

    public void setCategoryId(Long categoryId) 
    {
        this.categoryId = categoryId;
    }

    public Long getCategoryId() 
    {
        return categoryId;
    }

    public void setSubscriptionType(String subscriptionType) 
    {
        this.subscriptionType = subscriptionType;
    }

    public String getSubscriptionType() 
    {
        return subscriptionType;
    }

    public void setNotifyEmail(Integer notifyEmail) 
    {
        this.notifyEmail = notifyEmail;
    }

    public Integer getNotifyEmail() 
    {
        return notifyEmail;
    }

    public void setNotifyPush(Integer notifyPush) 
    {
        this.notifyPush = notifyPush;
    }

    public Integer getNotifyPush() 
    {
        return notifyPush;
    }

    public void setCreatedAt(Date createdAt) 
    {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt() 
    {
        return createdAt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("feedbackId", getFeedbackId())
            .append("categoryId", getCategoryId())
            .append("subscriptionType", getSubscriptionType())
            .append("notifyEmail", getNotifyEmail())
            .append("notifyPush", getNotifyPush())
            .append("createdAt", getCreatedAt())
            .toString();
    }
}
