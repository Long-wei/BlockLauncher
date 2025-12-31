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
import com.ruoyi.system.domain.ActivityLogs;
import com.ruoyi.system.service.IActivityLogsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 活动日志Controller
 * 
 * @author ruoyi
 * @date 2025-12-30
 */
@RestController
@RequestMapping("/system/logs")
public class ActivityLogsController extends BaseController
{
    @Autowired
    private IActivityLogsService activityLogsService;

    /**
     * 查询活动日志列表
     */
    @PreAuthorize("@ss.hasPermi('system:logs:list')")
    @GetMapping("/list")
    public TableDataInfo list(ActivityLogs activityLogs)
    {
        startPage();
        List<ActivityLogs> list = activityLogsService.selectActivityLogsList(activityLogs);
        return getDataTable(list);
    }

    /**
     * 导出活动日志列表
     */
    @PreAuthorize("@ss.hasPermi('system:logs:export')")
    @Log(title = "活动日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ActivityLogs activityLogs)
    {
        List<ActivityLogs> list = activityLogsService.selectActivityLogsList(activityLogs);
        ExcelUtil<ActivityLogs> util = new ExcelUtil<ActivityLogs>(ActivityLogs.class);
        util.exportExcel(response, list, "活动日志数据");
    }

    /**
     * 获取活动日志详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:logs:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(activityLogsService.selectActivityLogsById(id));
    }

    /**
     * 新增活动日志
     */
    @PreAuthorize("@ss.hasPermi('system:logs:add')")
    @Log(title = "活动日志", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ActivityLogs activityLogs)
    {
        return toAjax(activityLogsService.insertActivityLogs(activityLogs));
    }

    /**
     * 修改活动日志
     */
    @PreAuthorize("@ss.hasPermi('system:logs:edit')")
    @Log(title = "活动日志", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ActivityLogs activityLogs)
    {
        return toAjax(activityLogsService.updateActivityLogs(activityLogs));
    }

    /**
     * 删除活动日志
     */
    @PreAuthorize("@ss.hasPermi('system:logs:remove')")
    @Log(title = "活动日志", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(activityLogsService.deleteActivityLogsByIds(ids));
    }
}
