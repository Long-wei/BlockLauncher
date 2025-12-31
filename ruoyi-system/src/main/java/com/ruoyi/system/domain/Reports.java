package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 举报对象 reports
 * 
 * @author ruoyi
 * @date 2025-12-30
 */
public class Reports extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 举报ID */
    private Long id;

    /** 举报人ID */
    @Excel(name = "举报人ID")
    private Long reporterId;

    /** 举报目标类型 */
    @Excel(name = "举报目标类型")
    private String targetType;

    /** 目标ID */
    @Excel(name = "目标ID")
    private Long targetId;

    /** 举报原因 */
    @Excel(name = "举报原因")
    private String reason;

    /** 详细描述 */
    @Excel(name = "详细描述")
    private String description;

    /** 处理状态 */
    @Excel(name = "处理状态")
    private String status;

    /** 处理人ID */
    @Excel(name = "处理人ID")
    private Long resolvedBy;

    /** 处理结果 */
    @Excel(name = "处理结果")
    private String resolution;

    /** 处理时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "处理时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date resolvedAt;

    /** 举报时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "举报时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdAt;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedAt;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setReporterId(Long reporterId) 
    {
        this.reporterId = reporterId;
    }

    public Long getReporterId() 
    {
        return reporterId;
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

    public void setReason(String reason) 
    {
        this.reason = reason;
    }

    public String getReason() 
    {
        return reason;
    }

    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }

    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    public void setResolvedBy(Long resolvedBy) 
    {
        this.resolvedBy = resolvedBy;
    }

    public Long getResolvedBy() 
    {
        return resolvedBy;
    }

    public void setResolution(String resolution) 
    {
        this.resolution = resolution;
    }

    public String getResolution() 
    {
        return resolution;
    }

    public void setResolvedAt(Date resolvedAt) 
    {
        this.resolvedAt = resolvedAt;
    }

    public Date getResolvedAt() 
    {
        return resolvedAt;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("reporterId", getReporterId())
            .append("targetType", getTargetType())
            .append("targetId", getTargetId())
            .append("reason", getReason())
            .append("description", getDescription())
            .append("status", getStatus())
            .append("resolvedBy", getResolvedBy())
            .append("resolution", getResolution())
            .append("resolvedAt", getResolvedAt())
            .append("createdAt", getCreatedAt())
            .append("updatedAt", getUpdatedAt())
            .toString();
    }
}
