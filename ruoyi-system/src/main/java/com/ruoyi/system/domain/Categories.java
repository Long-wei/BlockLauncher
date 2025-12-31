package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 反馈分类对象 categories
 * 
 * @author ruoyi
 * @date 2025-12-30
 */
public class Categories extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 分类ID */
    private Long id;

    /** 分类名称 */
    @Excel(name = "分类名称")
    private String name;

    /** 分类URL标识 */
    @Excel(name = "分类URL标识")
    private String slug;

    /** 分类描述 */
    @Excel(name = "分类描述")
    private String description;

    /** 父分类ID */
    @Excel(name = "父分类ID")
    private Long parentId;

    /** 排序顺序 */
    @Excel(name = "排序顺序")
    private Long sortOrder;

    /** 是否激活 */
    @Excel(name = "是否激活")
    private Integer isActive;

    /** 分类图标 */
    @Excel(name = "分类图标")
    private String icon;

    /** 分类颜色 */
    @Excel(name = "分类颜色")
    private String color;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
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

    public void setParentId(Long parentId) 
    {
        this.parentId = parentId;
    }

    public Long getParentId() 
    {
        return parentId;
    }

    public void setSortOrder(Long sortOrder) 
    {
        this.sortOrder = sortOrder;
    }

    public Long getSortOrder() 
    {
        return sortOrder;
    }

    public void setIsActive(Integer isActive) 
    {
        this.isActive = isActive;
    }

    public Integer getIsActive() 
    {
        return isActive;
    }

    public void setIcon(String icon) 
    {
        this.icon = icon;
    }

    public String getIcon() 
    {
        return icon;
    }

    public void setColor(String color) 
    {
        this.color = color;
    }

    public String getColor() 
    {
        return color;
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
            .append("name", getName())
            .append("slug", getSlug())
            .append("description", getDescription())
            .append("parentId", getParentId())
            .append("sortOrder", getSortOrder())
            .append("isActive", getIsActive())
            .append("icon", getIcon())
            .append("color", getColor())
            .append("createdAt", getCreatedAt())
            .append("updatedAt", getUpdatedAt())
            .toString();
    }
}
