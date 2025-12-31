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
import com.ruoyi.system.domain.FeedbackTags;
import com.ruoyi.system.service.IFeedbackTagsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 反馈标签关联Controller
 * 
 * @author ruoyi
 * @date 2025-12-30
 */
@RestController
@RequestMapping("/system/feedback-tags")
public class FeedbackTagsController extends BaseController
{
    @Autowired
    private IFeedbackTagsService feedbackTagsService;

    /**
     * 查询反馈标签关联列表
     */
    @PreAuthorize("@ss.hasPermi('system:tags:list')")
    @GetMapping("/list")
    public TableDataInfo list(FeedbackTags feedbackTags)
    {
        startPage();
        List<FeedbackTags> list = feedbackTagsService.selectFeedbackTagsList(feedbackTags);
        return getDataTable(list);
    }

    /**
     * 导出反馈标签关联列表
     */
    @PreAuthorize("@ss.hasPermi('system:tags:export')")
    @Log(title = "反馈标签关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FeedbackTags feedbackTags)
    {
        List<FeedbackTags> list = feedbackTagsService.selectFeedbackTagsList(feedbackTags);
        ExcelUtil<FeedbackTags> util = new ExcelUtil<FeedbackTags>(FeedbackTags.class);
        util.exportExcel(response, list, "反馈标签关联数据");
    }

    /**
     * 获取反馈标签关联详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:tags:query')")
    @GetMapping(value = "/{feedbackId}")
    public AjaxResult getInfo(@PathVariable("feedbackId") Long feedbackId)
    {
        return success(feedbackTagsService.selectFeedbackTagsByFeedbackId(feedbackId));
    }

    /**
     * 新增反馈标签关联
     */
    @PreAuthorize("@ss.hasPermi('system:tags:add')")
    @Log(title = "反馈标签关联", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FeedbackTags feedbackTags)
    {
        return toAjax(feedbackTagsService.insertFeedbackTags(feedbackTags));
    }

    /**
     * 修改反馈标签关联
     */
    @PreAuthorize("@ss.hasPermi('system:tags:edit')")
    @Log(title = "反馈标签关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FeedbackTags feedbackTags)
    {
        return toAjax(feedbackTagsService.updateFeedbackTags(feedbackTags));
    }

    /**
     * 删除反馈标签关联
     */
    @PreAuthorize("@ss.hasPermi('system:tags:remove')")
    @Log(title = "反馈标签关联", businessType = BusinessType.DELETE)
	@DeleteMapping("/{feedbackIds}")
    public AjaxResult remove(@PathVariable Long[] feedbackIds)
    {
        return toAjax(feedbackTagsService.deleteFeedbackTagsByFeedbackIds(feedbackIds));
    }
}
