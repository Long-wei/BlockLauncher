package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 通知对象 notifications
 * 
 * @author ruoyi
 * @date 2025-12-30
 */
public class Notifications extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 通知ID */
    private Long id;

    /** 接收用户ID */
    @Excel(name = "接收用户ID")
    private Long userId;

    /** 通知类型 */
    @Excel(name = "通知类型")
    private String type;

    /** 通知标题 */
    @Excel(name = "通知标题")
    private String title;

    /** 通知内容 */
    @Excel(name = "通知内容")
    private String content;

    /** 关联类型 */
    @Excel(name = "关联类型")
    private String referenceType;

    /** 关联ID */
    @Excel(name = "关联ID")
    private Long referenceId;

    /** 是否已读 */
    @Excel(name = "是否已读")
    private Integer isRead;

    /** 邮件是否已发送 */
    @Excel(name = "邮件是否已发送")
    private Integer isEmailSent;

    /** 附加数据 */
    @Excel(name = "附加数据")
    private String metadata;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdAt;

    /** 阅读时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "阅读时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date readAt;

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

    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }

    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }

    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }

    public void setReferenceType(String referenceType) 
    {
        this.referenceType = referenceType;
    }

    public String getReferenceType() 
    {
        return referenceType;
    }

    public void setReferenceId(Long referenceId) 
    {
        this.referenceId = referenceId;
    }

    public Long getReferenceId() 
    {
        return referenceId;
    }

    public void setIsRead(Integer isRead) 
    {
        this.isRead = isRead;
    }

    public Integer getIsRead() 
    {
        return isRead;
    }

    public void setIsEmailSent(Integer isEmailSent) 
    {
        this.isEmailSent = isEmailSent;
    }

    public Integer getIsEmailSent() 
    {
        return isEmailSent;
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

    public void setReadAt(Date readAt) 
    {
        this.readAt = readAt;
    }

    public Date getReadAt() 
    {
        return readAt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("type", getType())
            .append("title", getTitle())
            .append("content", getContent())
            .append("referenceType", getReferenceType())
            .append("referenceId", getReferenceId())
            .append("isRead", getIsRead())
            .append("isEmailSent", getIsEmailSent())
            .append("metadata", getMetadata())
            .append("createdAt", getCreatedAt())
            .append("readAt", getReadAt())
            .toString();
    }
}
