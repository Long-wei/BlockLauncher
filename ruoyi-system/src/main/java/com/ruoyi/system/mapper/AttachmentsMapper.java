package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Attachments;

/**
 * 附件Mapper接口
 * 
 * @author ruoyi
 * @date 2025-12-30
 */
public interface AttachmentsMapper 
{
    /**
     * 查询附件
     * 
     * @param id 附件主键
     * @return 附件
     */
    public Attachments selectAttachmentsById(Long id);

    /**
     * 查询附件列表
     * 
     * @param attachments 附件
     * @return 附件集合
     */
    public List<Attachments> selectAttachmentsList(Attachments attachments);

    /**
     * 新增附件
     * 
     * @param attachments 附件
     * @return 结果
     */
    public int insertAttachments(Attachments attachments);

    /**
     * 修改附件
     * 
     * @param attachments 附件
     * @return 结果
     */
    public int updateAttachments(Attachments attachments);

    /**
     * 删除附件
     * 
     * @param id 附件主键
     * @return 结果
     */
    public int deleteAttachmentsById(Long id);

    /**
     * 批量删除附件
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAttachmentsByIds(Long[] ids);
}
