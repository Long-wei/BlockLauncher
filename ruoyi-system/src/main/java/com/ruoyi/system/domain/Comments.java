package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 评论对象 comments
 * 
 * @author ruoyi
 * @date 2025-12-30
 */
public class Comments extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 评论ID */
    private Long id;

    /** 反馈ID */
    @Excel(name = "反馈ID")
    private Long feedbackId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 父评论ID */
    @Excel(name = "父评论ID")
    private Long parentId;

    /** 评论内容 */
    @Excel(name = "评论内容")
    private String content;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 是否为反馈作者 */
    @Excel(name = "是否为反馈作者")
    private Integer isAuthor;

    /** 是否为官方回复 */
    @Excel(name = "是否为官方回复")
    private Integer isOfficial;

    /** 赞同数 */
    @Excel(name = "赞同数")
    private Long voteCount;

    /** 被举报次数 */
    @Excel(name = "被举报次数")
    private Long reportCount;

    /** 附加数据 */
    @Excel(name = "附加数据")
    private String metadata;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdAt;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedAt;

    /** 删除时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "删除时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deletedAt;

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

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    public void setParentId(Long parentId) 
    {
        this.parentId = parentId;
    }

    public Long getParentId() 
    {
        return parentId;
    }

    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }

    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    public void setIsAuthor(Integer isAuthor) 
    {
        this.isAuthor = isAuthor;
    }

    public Integer getIsAuthor() 
    {
        return isAuthor;
    }

    public void setIsOfficial(Integer isOfficial) 
    {
        this.isOfficial = isOfficial;
    }

    public Integer getIsOfficial() 
    {
        return isOfficial;
    }

    public void setVoteCount(Long voteCount) 
    {
        this.voteCount = voteCount;
    }

    public Long getVoteCount() 
    {
        return voteCount;
    }

    public void setReportCount(Long reportCount) 
    {
        this.reportCount = reportCount;
    }

    public Long getReportCount() 
    {
        return reportCount;
    }

    public void setMetadata(String metadata) 
    {
        this.metadata = metadata;
    }

    public String getMetadata() 
    {
        return metadata;
    }

    public void setCreatedAt(Date createdAt) 
    {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt() 
    {
        return createdAt;
    }

    public void setUpdatedAt(Date updatedAt) 
    {
        this.updatedAt = updatedAt;
    }

    public Date getUpdatedAt() 
    {
        return updatedAt;
    }

    public void setDeletedAt(Date deletedAt) 
    {
        this.deletedAt = deletedAt;
    }

    public Date getDeletedAt() 
    {
        return deletedAt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("feedbackId", getFeedbackId())
            .append("userId", getUserId())
            .append("parentId", getParentId())
            .append("content", getContent())
            .append("status", getStatus())
            .append("isAuthor", getIsAuthor())
            .append("isOfficial", getIsOfficial())
            .append("voteCount", getVoteCount())
            .append("reportCount", getReportCount())
            .append("metadata", getMetadata())
            .append("createdAt", getCreatedAt())
            .append("updatedAt", getUpdatedAt())
            .append("deletedAt", getDeletedAt())
            .toString();
    }
}
