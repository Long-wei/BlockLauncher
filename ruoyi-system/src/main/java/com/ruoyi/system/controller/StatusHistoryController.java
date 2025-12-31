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
import com.ruoyi.system.domain.StatusHistory;
import com.ruoyi.system.service.IStatusHistoryService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 状态变更历史Controller
 * 
 * @author ruoyi
 * @date 2025-12-30
 */
@RestController
@RequestMapping("/system/history")
public class StatusHistoryController extends BaseController
{
    @Autowired
    private IStatusHistoryService statusHistoryService;

    /**
     * 查询状态变更历史列表
     */
    @PreAuthorize("@ss.hasPermi('system:history:list')")
    @GetMapping("/list")
    public TableDataInfo list(StatusHistory statusHistory)
    {
        startPage();
        List<StatusHistory> list = statusHistoryService.selectStatusHistoryList(statusHistory);
        return getDataTable(list);
    }

    /**
     * 导出状态变更历史列表
     */
    @PreAuthorize("@ss.hasPermi('system:history:export')")
    @Log(title = "状态变更历史", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StatusHistory statusHistory)
    {
        List<StatusHistory> list = statusHistoryService.selectStatusHistoryList(statusHistory);
        ExcelUtil<StatusHistory> util = new ExcelUtil<StatusHistory>(StatusHistory.class);
        util.exportExcel(response, list, "状态变更历史数据");
    }

    /**
     * 获取状态变更历史详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:history:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(statusHistoryService.selectStatusHistoryById(id));
    }

    /**
     * 新增状态变更历史
     */
    @PreAuthorize("@ss.hasPermi('system:history:add')")
    @Log(title = "状态变更历史", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StatusHistory statusHistory)
    {
        return toAjax(statusHistoryService.insertStatusHistory(statusHistory));
    }

    /**
     * 修改状态变更历史
     */
    @PreAuthorize("@ss.hasPermi('system:history:edit')")
    @Log(title = "状态变更历史", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StatusHistory statusHistory)
    {
        return toAjax(statusHistoryService.updateStatusHistory(statusHistory));
    }

    /**
     * 删除状态变更历史
     */
    @PreAuthorize("@ss.hasPermi('system:history:remove')")
    @Log(title = "状态变更历史", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(statusHistoryService.deleteStatusHistoryByIds(ids));
    }
}
