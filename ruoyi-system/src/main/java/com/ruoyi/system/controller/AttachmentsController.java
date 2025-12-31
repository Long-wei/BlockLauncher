package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.Attachments;
import com.ruoyi.system.service.IAttachmentsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 附件Controller
 * 
 * @author ruoyi
 * @date 2025-12-30
 */
@RestController
@RequestMapping("/system/attachments")
public class AttachmentsController extends BaseController
{
    @Autowired
    private IAttachmentsService attachmentsService;

    /**
     * 查询附件列表
     */
    @PreAuthorize("@ss.hasPermi('system:attachments:list')")
    @GetMapping("/list")
    public TableDataInfo list(Attachments attachments)
    {
        startPage();
        List<Attachments> list = attachmentsService.selectAttachmentsList(attachments);
        return getDataTable(list);
    }

    /**
     * 导出附件列表
     */
    @PreAuthorize("@ss.hasPermi('system:attachments:export')")
    @Log(title = "附件", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Attachments attachments)
    {
        List<Attachments> list = attachmentsService.selectAttachmentsList(attachments);
        ExcelUtil<Attachments> util = new ExcelUtil<Attachments>(Attachments.class);
        util.exportExcel(response, list, "附件数据");
    }

    /**
     * 获取附件详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:attachments:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(attachmentsService.selectAttachmentsById(id));
    }

    /**
     * 新增附件
     */
    @PreAuthorize("@ss.hasPermi('system:attachments:add')")
    @Log(title = "附件", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Attachments attachments)
    {
        return toAjax(attachmentsService.insertAttachments(attachments));
    }

    /**
     * 修改附件
     */
    @PreAuthorize("@ss.hasPermi('system:attachments:edit')")
    @Log(title = "附件", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Attachments attachments)
    {
        return toAjax(attachmentsService.updateAttachments(attachments));
    }

    /**
     * 删除附件
     */
    @PreAuthorize("@ss.hasPermi('system:attachments:remove')")
    @Log(title = "附件", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(attachmentsService.deleteAttachmentsByIds(ids));
    }
}
