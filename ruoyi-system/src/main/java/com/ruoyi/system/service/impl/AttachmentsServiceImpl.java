package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.AttachmentsMapper;
import com.ruoyi.system.domain.Attachments;
import com.ruoyi.system.service.IAttachmentsService;

/**
 * 附件Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-30
 */
@Service
public class AttachmentsServiceImpl implements IAttachmentsService 
{
    @Autowired
    private AttachmentsMapper attachmentsMapper;

    /**
     * 查询附件
     * 
     * @param id 附件主键
     * @return 附件
     */
    @Override
    public Attachments selectAttachmentsById(Long id)
    {
        return attachmentsMapper.selectAttachmentsById(id);
    }

    /**
     * 查询附件列表
     * 
     * @param attachments 附件
     * @return 附件
     */
    @Override
    public List<Attachments> selectAttachmentsList(Attachments attachments)
    {
        return attachmentsMapper.selectAttachmentsList(attachments);
    }

    /**
     * 新增附件
     * 
     * @param attachments 附件
     * @return 结果
     */
    @Override
    public int insertAttachments(Attachments attachments)
    {
        return attachmentsMapper.insertAttachments(attachments);
    }

    /**
     * 修改附件
     * 
     * @param attachments 附件
     * @return 结果
     */
    @Override
    public int updateAttachments(Attachments attachments)
    {
        return attachmentsMapper.updateAttachments(attachments);
    }

    /**
     * 批量删除附件
     * 
     * @param ids 需要删除的附件主键
     * @return 结果
     */
    @Override
    public int deleteAttachmentsByIds(Long[] ids)
    {
        return attachmentsMapper.deleteAttachmentsByIds(ids);
    }

    /**
     * 删除附件信息
     * 
     * @param id 附件主键
     * @return 结果
     */
    @Override
    public int deleteAttachmentsById(Long id)
    {
        return attachmentsMapper.deleteAttachmentsById(id);
    }
}
