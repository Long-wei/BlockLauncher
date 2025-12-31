package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 附件对象 attachments
 * 
 * @author ruoyi
 * @date 2025-12-30
 */
public class Attachments extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 附件ID */
    private Long id;

    /** 反馈ID */
    @Excel(name = "反馈ID")
    private Long feedbackId;

    /** 上传用户ID */
    @Excel(name = "上传用户ID")
    private Long userId;

    /** 文件名 */
    @Excel(name = "文件名")
    private String fileName;

    /** 文件路径 */
    @Excel(name = "文件路径")
    private String filePath;

    /** 文件大小（字节） */
    @Excel(name = "文件大小", readConverterExp = "字=节")
    private Long fileSize;

    /** 文件类型 */
    @Excel(name = "文件类型")
    private String fileType;

    /** MIME类型 */
    @Excel(name = "MIME类型")
    private String mimeType;

    /** 原始文件名 */
    @Excel(name = "原始文件名")
    private String originalName;

    /** 缩略图路径 */
    @Excel(name = "缩略图路径")
    private String thumbnailPath;

    /** 是否为图片 */
    @Excel(name = "是否为图片")
    private Integer isImage;

    /** 图片宽度 */
    @Excel(name = "图片宽度")
    private Long width;

    /** 图片高度 */
    @Excel(name = "图片高度")
    private Long height;

    /** 上传IP */
    @Excel(name = "上传IP")
    private String uploadIp;

    /** 下载次数 */
    @Excel(name = "下载次数")
    private Long downloadCount;

    /** 上传时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "上传时间", width = 30, dateFormat = "yyyy-MM-dd")
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

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    public void setFileName(String fileName) 
    {
        this.fileName = fileName;
    }

    public String getFileName() 
    {
        return fileName;
    }

    public void setFilePath(String filePath) 
    {
        this.filePath = filePath;
    }

    public String getFilePath() 
    {
        return filePath;
    }

    public void setFileSize(Long fileSize) 
    {
        this.fileSize = fileSize;
    }

    public Long getFileSize() 
    {
        return fileSize;
    }

    public void setFileType(String fileType) 
    {
        this.fileType = fileType;
    }

    public String getFileType() 
    {
        return fileType;
    }

    public void setMimeType(String mimeType) 
    {
        this.mimeType = mimeType;
    }

    public String getMimeType() 
    {
        return mimeType;
    }

    public void setOriginalName(String originalName) 
    {
        this.originalName = originalName;
    }

    public String getOriginalName() 
    {
        return originalName;
    }

    public void setThumbnailPath(String thumbnailPath) 
    {
        this.thumbnailPath = thumbnailPath;
    }

    public String getThumbnailPath() 
    {
        return thumbnailPath;
    }

    public void setIsImage(Integer isImage) 
    {
        this.isImage = isImage;
    }

    public Integer getIsImage() 
    {
        return isImage;
    }

    public void setWidth(Long width) 
    {
        this.width = width;
    }

    public Long getWidth() 
    {
        return width;
    }

    public void setHeight(Long height) 
    {
        this.height = height;
    }

    public Long getHeight() 
    {
        return height;
    }

    public void setUploadIp(String uploadIp) 
    {
        this.uploadIp = uploadIp;
    }

    public String getUploadIp() 
    {
        return uploadIp;
    }

    public void setDownloadCount(Long downloadCount) 
    {
        this.downloadCount = downloadCount;
    }

    public Long getDownloadCount() 
    {
        return downloadCount;
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
            .append("userId", getUserId())
            .append("fileName", getFileName())
            .append("filePath", getFilePath())
            .append("fileSize", getFileSize())
            .append("fileType", getFileType())
            .append("mimeType", getMimeType())
            .append("originalName", getOriginalName())
            .append("thumbnailPath", getThumbnailPath())
            .append("isImage", getIsImage())
            .append("width", getWidth())
            .append("height", getHeight())
            .append("uploadIp", getUploadIp())
            .append("downloadCount", getDownloadCount())
            .append("createdAt", getCreatedAt())
            .toString();
    }
}
