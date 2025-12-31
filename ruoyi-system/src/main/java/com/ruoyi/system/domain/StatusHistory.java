package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 状态变更历史对象 status_history
 * 
 * @author ruoyi
 * @date 2025-12-30
 */
public class StatusHistory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 历史ID */
    private Long id;

    /** 反馈ID */
    @Excel(name = "反馈ID")
    private Long feedbackId;

    /** 旧状态 */
    @Excel(name = "旧状态")
    private String oldStatus;

    /** 新状态 */
    @Excel(name = "新状态")
    private String newStatus;

    /** 变更人ID */
    @Excel(name = "变更人ID")
    private Long changedBy;

    /** 变更原因 */
    @Excel(name = "变更原因")
    private String reason;

    /** 附加说明 */
    @Excel(name = "附加说明")
    private String comment;

    /** 变更时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "变更时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdAt;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setFeedbackId(Long feedbackId) 
    {
        this.feedbackId = feedbackId;
    }

    public Long getFeedbackId() 
    {
        return feedbackId;
    }

    public void setOldStatus(String oldStatus) 
    {
        this.oldStatus = oldStatus;
    }

    public String getOldStatus() 
    {
        return oldStatus;
    }

    public void setNewStatus(String newStatus) 
    {
        this.newStatus = newStatus;
    }

    public String getNewStatus() 
    {
        return newStatus;
    }

    public void setChangedBy(Long changedBy) 
    {
        this.changedBy = changedBy;
    }

    public Long getChangedBy() 
    {
        return changedBy;
    }

    public void setReason(String reason) 
    {
        this.reason = reason;
    }

    public String getReason() 
    {
        return reason;
    }

    public void setComment(String comment) 
    {
        this.comment = comment;
    }

    public String getComment() 
    {
        return comment;
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
            .append("feedbackId", getFeedbackId())
            .append("oldStatus", getOldStatus())
            .append("newStatus", getNewStatus())
            .append("changedBy", getChangedBy())
            .append("reason", getReason())
            .append("comment", getComment())
            .append("createdAt", getCreatedAt())
            .toString();
    }
}
