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
import com.ruoyi.system.domain.Feedbacks;
import com.ruoyi.system.service.IFeedbacksService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 反馈意见Controller
 * 
 * @author ruoyi
 * @date 2025-12-30
 */
@RestController
@RequestMapping("/system/feedbacks")
public class FeedbacksController extends BaseController
{
    @Autowired
    private IFeedbacksService feedbacksService;

    /**
     * 查询反馈意见列表
     */
    @PreAuthorize("@ss.hasPermi('system:feedbacks:list')")
    @GetMapping("/list")
    public TableDataInfo list(Feedbacks feedbacks)
    {
        startPage();
        List<Feedbacks> list = feedbacksService.selectFeedbacksList(feedbacks);
        return getDataTable(list);
    }

    /**
     * 导出反馈意见列表
     */
    @PreAuthorize("@ss.hasPermi('system:feedbacks:export')")
    @Log(title = "反馈意见", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Feedbacks feedbacks)
    {
        List<Feedbacks> list = feedbacksService.selectFeedbacksList(feedbacks);
        ExcelUtil<Feedbacks> util = new ExcelUtil<Feedbacks>(Feedbacks.class);
        util.exportExcel(response, list, "反馈意见数据");
    }

    /**
     * 获取反馈意见详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:feedbacks:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(feedbacksService.selectFeedbacksById(id));
    }

    /**
     * 新增反馈意见
     */
    @PreAuthorize("@ss.hasPermi('system:feedbacks:add')")
    @Log(title = "反馈意见", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Feedbacks feedbacks)
    {
        return toAjax(feedbacksService.insertFeedbacks(feedbacks));
    }

    /**
     * 修改反馈意见
     */
    @PreAuthorize("@ss.hasPermi('system:feedbacks:edit')")
    @Log(title = "反馈意见", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Feedbacks feedbacks)
    {
        return toAjax(feedbacksService.updateFeedbacks(feedbacks));
    }

    /**
     * 删除反馈意见
     */
    @PreAuthorize("@ss.hasPermi('system:feedbacks:remove')")
    @Log(title = "反馈意见", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(feedbacksService.deleteFeedbacksByIds(ids));
    }
}
