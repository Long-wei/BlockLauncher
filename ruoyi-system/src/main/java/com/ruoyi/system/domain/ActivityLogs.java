package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 活动日志对象 activity_logs
 * 
 * @author ruoyi
 * @date 2025-12-30
 */
public class ActivityLogs extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 日志ID */
    private Long id;

    /** 用户ID（可为空，表示系统操作） */
    @Excel(name = "用户ID", readConverterExp = "可=为空，表示系统操作")
    private Long userId;

    /** 操作类型 */
    @Excel(name = "操作类型")
    private String actionType;

    /** 目标类型 */
    @Excel(name = "目标类型")
    private String targetType;

    /** 目标ID */
    @Excel(name = "目标ID")
    private Long targetId;

    /** 操作描述 */
    @Excel(name = "操作描述")
    private String description;

    /** IP地址 */
    @Excel(name = "IP地址")
    private String ipAddress;

    /** 用户代理 */
    @Excel(name = "用户代理")
    private String userAgent;

    /** 附加数据 */
    @Excel(name = "附加数据")
    private String metadata;

    /** 操作时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "操作时间", width = 30, dateFormat = "yyyy-MM-dd")
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

    public void setActionType(String actionType) 
    {
        this.actionType = actionType;
    }

    public String getActionType() 
    {
        return actionType;
    }

    public void setTargetType(String targetType) 
    {
        this.targetType = targetType;
    }

    public String getTargetType() 
    {
        return targetType;
    }

    public void setTargetId(Long targetId) 
    {
        this.targetId = targetId;
    }

    public Long getTargetId() 
    {
        return targetId;
    }

    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }

    public void setIpAddress(String ipAddress) 
    {
        this.ipAddress = ipAddress;
    }

    public String getIpAddress() 
    {
        return ipAddress;
    }

    public void setUserAgent(String userAgent) 
    {
        this.userAgent = userAgent;
    }

    public String getUserAgent() 
    {
        return userAgent;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("actionType", getActionType())
            .append("targetType", getTargetType())
            .append("targetId", getTargetId())
            .append("description", getDescription())
            .append("ipAddress", getIpAddress())
            .append("userAgent", getUserAgent())
            .append("metadata", getMetadata())
            .append("createdAt", getCreatedAt())
            .toString();
    }
}
