package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 标签对象 tags
 * 
 * @author ruoyi
 * @date 2025-12-30
 */
public class Tags extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 标签ID */
    private Long id;

    /** 标签名称 */
    @Excel(name = "标签名称")
    private String name;

    /** 标签URL标识 */
    @Excel(name = "标签URL标识")
    private String slug;

    /** 标签描述 */
    @Excel(name = "标签描述")
    private String description;

    /** 标签颜色 */
    @Excel(name = "标签颜色")
    private String color;

    /** 使用次数 */
    @Excel(name = "使用次数")
    private Long usageCount;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdAt;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }

    public void setSlug(String slug) 
    {
        this.slug = slug;
    }

    public String getSlug() 
    {
        return slug;
    }

    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }

    public void setColor(String color) 
    {
        this.color = color;
    }

    public String getColor() 
    {
        return color;
    }

    public void setUsageCount(Long usageCount) 
    {
        this.usageCount = usageCount;
    }

    public Long getUsageCount() 
    {
        return usageCount;
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
            .append("name", getName())
            .append("slug", getSlug())
            .append("description", getDescription())
            .append("color", getColor())
            .append("usageCount", getUsageCount())
            .append("createdAt", getCreatedAt())
            .toString();
    }
}
