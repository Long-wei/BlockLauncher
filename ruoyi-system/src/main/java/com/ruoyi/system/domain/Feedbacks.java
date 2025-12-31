package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 反馈意见对象 feedbacks
 * 
 * @author ruoyi
 * @date 2025-12-30
 */
public class Feedbacks extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 反馈ID */
    private Long id;

    /** 提交用户ID */
    @Excel(name = "提交用户ID")
    private Long userId;

    /** 分类ID */
    @Excel(name = "分类ID")
    private Long categoryId;

    /** 反馈标题 */
    @Excel(name = "反馈标题")
    private String title;

    /** 反馈URL标识 */
    @Excel(name = "反馈URL标识")
    private String slug;

    /** 反馈内容 */
    @Excel(name = "反馈内容")
    private String content;

    /** 内容摘要 */
    @Excel(name = "内容摘要")
    private String summary;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 优先级 */
    @Excel(name = "优先级")
    private String priority;

    /** 可见性 */
    @Excel(name = "可见性")
    private String visibility;

    /** 投票总数 */
    @Excel(name = "投票总数")
    private Long voteCount;

    /** 评论总数 */
    @Excel(name = "评论总数")
    private Long commentCount;

    /** 查看次数 */
    @Excel(name = "查看次数")
    private Long viewCount;

    /** 是否精选 */
    @Excel(name = "是否精选")
    private Integer featured;

    /** 是否匿名 */
    @Excel(name = "是否匿名")
    private Integer anonymous;

    /** 是否允许评论 */
    @Excel(name = "是否允许评论")
    private Integer allowComments;

    /** 标签列表 */
    @Excel(name = "标签列表")
    private String tags;

    /** 附件数量 */
    @Excel(name = "附件数量")
    private Long attachmentsCount;

    /** 预计工作量（小时） */
    @Excel(name = "预计工作量", readConverterExp = "小=时")
    private Long estimatedEffort;

    /** 预计完成日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "预计完成日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date estimatedCompletionDate;

    /** 实际完成日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "实际完成日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date actualCompletionDate;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdAt;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedAt;

    /** 关闭时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "关闭时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date closedAt;

    /** 关闭人ID */
    @Excel(name = "关闭人ID")
    private Long closedBy;

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

    public void setCategoryId(Long categoryId) 
    {
        this.categoryId = categoryId;
    }

    public Long getCategoryId() 
    {
        return categoryId;
    }

    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }

    public void setSlug(String slug) 
    {
        this.slug = slug;
    }

    public String getSlug() 
    {
        return slug;
    }

    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }

    public void setSummary(String summary) 
    {
        this.summary = summary;
    }

    public String getSummary() 
    {
        return summary;
    }

    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    public void setPriority(String priority) 
    {
        this.priority = priority;
    }

    public String getPriority() 
    {
        return priority;
    }

    public void setVisibility(String visibility) 
    {
        this.visibility = visibility;
    }

    public String getVisibility() 
    {
        return visibility;
    }

    public void setVoteCount(Long voteCount) 
    {
        this.voteCount = voteCount;
    }

    public Long getVoteCount() 
    {
        return voteCount;
    }

    public void setCommentCount(Long commentCount) 
    {
        this.commentCount = commentCount;
    }

    public Long getCommentCount() 
    {
        return commentCount;
    }

    public void setViewCount(Long viewCount) 
    {
        this.viewCount = viewCount;
    }

    public Long getViewCount() 
    {
        return viewCount;
    }

    public void setFeatured(Integer featured) 
    {
        this.featured = featured;
    }

    public Integer getFeatured() 
    {
        return featured;
    }

    public void setAnonymous(Integer anonymous) 
    {
        this.anonymous = anonymous;
    }

    public Integer getAnonymous() 
    {
        return anonymous;
    }

    public void setAllowComments(Integer allowComments) 
    {
        this.allowComments = allowComments;
    }

    public Integer getAllowComments() 
    {
        return allowComments;
    }

    public void setTags(String tags) 
    {
        this.tags = tags;
    }

    public String getTags() 
    {
        return tags;
    }

    public void setAttachmentsCount(Long attachmentsCount) 
    {
        this.attachmentsCount = attachmentsCount;
    }

    public Long getAttachmentsCount() 
    {
        return attachmentsCount;
    }

    public void setEstimatedEffort(Long estimatedEffort) 
    {
        this.estimatedEffort = estimatedEffort;
    }

    public Long getEstimatedEffort() 
    {
        return estimatedEffort;
    }

    public void setEstimatedCompletionDate(Date estimatedCompletionDate) 
    {
        this.estimatedCompletionDate = estimatedCompletionDate;
    }

    public Date getEstimatedCompletionDate() 
    {
        return estimatedCompletionDate;
    }

    public void setActualCompletionDate(Date actualCompletionDate) 
    {
        this.actualCompletionDate = actualCompletionDate;
    }

    public Date getActualCompletionDate() 
    {
        return actualCompletionDate;
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

    public void setClosedAt(Date closedAt) 
    {
        this.closedAt = closedAt;
    }

    public Date getClosedAt() 
    {
        return closedAt;
    }

    public void setClosedBy(Long closedBy) 
    {
        this.closedBy = closedBy;
    }

    public Long getClosedBy() 
    {
        return closedBy;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("categoryId", getCategoryId())
            .append("title", getTitle())
            .append("slug", getSlug())
            .append("content", getContent())
            .append("summary", getSummary())
            .append("status", getStatus())
            .append("priority", getPriority())
            .append("visibility", getVisibility())
            .append("voteCount", getVoteCount())
            .append("commentCount", getCommentCount())
            .append("viewCount", getViewCount())
            .append("featured", getFeatured())
            .append("anonymous", getAnonymous())
            .append("allowComments", getAllowComments())
            .append("tags", getTags())
            .append("attachmentsCount", getAttachmentsCount())
            .append("estimatedEffort", getEstimatedEffort())
            .append("estimatedCompletionDate", getEstimatedCompletionDate())
            .append("actualCompletionDate", getActualCompletionDate())
            .append("createdAt", getCreatedAt())
            .append("updatedAt", getUpdatedAt())
            .append("closedAt", getClosedAt())
            .append("closedBy", getClosedBy())
            .toString();
    }
}
