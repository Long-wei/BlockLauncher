package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 系统配置对象 configuration
 * 
 * @author ruoyi
 * @date 2025-12-30
 */
public class SystemSettings extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 配置ID */
    private Long id;

    /** 配置键 */
    @Excel(name = "配置键")
    private String settingKey;

    /** 配置值 */
    @Excel(name = "配置值")
    private String settingValue;

    /** 值类型 */
    @Excel(name = "值类型")
    private String settingType;

    /** 配置分类 */
    @Excel(name = "配置分类")
    private String category;

    /** 配置描述 */
    @Excel(name = "配置描述")
    private String description;

    /** 是否公开 */
    @Excel(name = "是否公开")
    private Integer isPublic;

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

    public void setSettingKey(String settingKey) 
    {
        this.settingKey = settingKey;
    }

    public String getSettingKey() 
    {
        return settingKey;
    }

    public void setSettingValue(String settingValue) 
    {
        this.settingValue = settingValue;
    }

    public String getSettingValue() 
    {
        return settingValue;
    }

    public void setSettingType(String settingType) 
    {
        this.settingType = settingType;
    }

    public String getSettingType() 
    {
        return settingType;
    }

    public void setCategory(String category) 
    {
        this.category = category;
    }

    public String getCategory() 
    {
        return category;
    }

    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }

    public void setIsPublic(Integer isPublic) 
    {
        this.isPublic = isPublic;
    }

    public Integer getIsPublic() 
    {
        return isPublic;
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
            .append("settingKey", getSettingKey())
            .append("settingValue", getSettingValue())
            .append("settingType", getSettingType())
            .append("category", getCategory())
            .append("description", getDescription())
            .append("isPublic", getIsPublic())
            .append("createdAt", getCreatedAt())
            .append("updatedAt", getUpdatedAt())
            .toString();
    }
}
