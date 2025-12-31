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
import com.ruoyi.system.domain.Reports;
import com.ruoyi.system.service.IReportsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 举报Controller
 * 
 * @author ruoyi
 * @date 2025-12-30
 */
@RestController
@RequestMapping("/system/reports")
public class ReportsController extends BaseController
{
    @Autowired
    private IReportsService reportsService;

    /**
     * 查询举报列表
     */
    @PreAuthorize("@ss.hasPermi('system:reports:list')")
    @GetMapping("/list")
    public TableDataInfo list(Reports reports)
    {
        startPage();
        List<Reports> list = reportsService.selectReportsList(reports);
        return getDataTable(list);
    }

    /**
     * 导出举报列表
     */
    @PreAuthorize("@ss.hasPermi('system:reports:export')")
    @Log(title = "举报", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Reports reports)
    {
        List<Reports> list = reportsService.selectReportsList(reports);
        ExcelUtil<Reports> util = new ExcelUtil<Reports>(Reports.class);
        util.exportExcel(response, list, "举报数据");
    }

    /**
     * 获取举报详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:reports:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(reportsService.selectReportsById(id));
    }

    /**
     * 新增举报
     */
    @PreAuthorize("@ss.hasPermi('system:reports:add')")
    @Log(title = "举报", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Reports reports)
    {
        return toAjax(reportsService.insertReports(reports));
    }

    /**
     * 修改举报
     */
    @PreAuthorize("@ss.hasPermi('system:reports:edit')")
    @Log(title = "举报", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Reports reports)
    {
        return toAjax(reportsService.updateReports(reports));
    }

    /**
     * 删除举报
     */
    @PreAuthorize("@ss.hasPermi('system:reports:remove')")
    @Log(title = "举报", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(reportsService.deleteReportsByIds(ids));
    }
}
