package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户对象 users
 * 
 * @author ruoyi
 * @date 2025-12-30
 */
public class Users extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    private Long id;

    /** 用户名 */
    @Excel(name = "用户名")
    private String username;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 密码哈希 */
    @Excel(name = "密码哈希")
    private String passwordHash;

    /** 显示名称 */
    @Excel(name = "显示名称")
    private String displayName;

    /** 头像URL */
    @Excel(name = "头像URL")
    private String avatarUrl;

    /** 个人简介 */
    @Excel(name = "个人简介")
    private String bio;

    /** 角色 */
    @Excel(name = "角色")
    private String role;

    /** 声望值 */
    @Excel(name = "声望值")
    private Long reputation;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 最后登录时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最后登录时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastLoginAt;

    /** 邮箱是否验证 */
    @Excel(name = "邮箱是否验证")
    private Integer emailVerified;

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

    public void setUsername(String username) 
    {
        this.username = username;
    }

    public String getUsername() 
    {
        return username;
    }

    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }

    public void setPasswordHash(String passwordHash) 
    {
        this.passwordHash = passwordHash;
    }

    public String getPasswordHash() 
    {
        return passwordHash;
    }

    public void setDisplayName(String displayName) 
    {
        this.displayName = displayName;
    }

    public String getDisplayName() 
    {
        return displayName;
    }

    public void setAvatarUrl(String avatarUrl) 
    {
        this.avatarUrl = avatarUrl;
    }

    public String getAvatarUrl() 
    {
        return avatarUrl;
    }

    public void setBio(String bio) 
    {
        this.bio = bio;
    }

    public String getBio() 
    {
        return bio;
    }

    public void setRole(String role) 
    {
        this.role = role;
    }

    public String getRole() 
    {
        return role;
    }

    public void setReputation(Long reputation) 
    {
        this.reputation = reputation;
    }

    public Long getReputation() 
    {
        return reputation;
    }

    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    public void setLastLoginAt(Date lastLoginAt) 
    {
        this.lastLoginAt = lastLoginAt;
    }

    public Date getLastLoginAt() 
    {
        return lastLoginAt;
    }

    public void setEmailVerified(Integer emailVerified) 
    {
        this.emailVerified = emailVerified;
    }

    public Integer getEmailVerified() 
    {
        return emailVerified;
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
            .append("username", getUsername())
            .append("email", getEmail())
            .append("passwordHash", getPasswordHash())
            .append("displayName", getDisplayName())
            .append("avatarUrl", getAvatarUrl())
            .append("bio", getBio())
            .append("role", getRole())
            .append("reputation", getReputation())
            .append("status", getStatus())
            .append("lastLoginAt", getLastLoginAt())
            .append("emailVerified", getEmailVerified())
            .append("createdAt", getCreatedAt())
            .append("updatedAt", getUpdatedAt())
            .toString();
    }
}
