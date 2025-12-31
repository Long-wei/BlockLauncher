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
import com.ruoyi.system.domain.Notifications;
import com.ruoyi.system.service.INotificationsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 通知Controller
 * 
 * @author ruoyi
 * @date 2025-12-30
 */
@RestController
@RequestMapping("/system/notifications")
public class NotificationsController extends BaseController
{
    @Autowired
    private INotificationsService notificationsService;

    /**
     * 查询通知列表
     */
    @PreAuthorize("@ss.hasPermi('system:notifications:list')")
    @GetMapping("/list")
    public TableDataInfo list(Notifications notifications)
    {
        startPage();
        List<Notifications> list = notificationsService.selectNotificationsList(notifications);
        return getDataTable(list);
    }

    /**
     * 导出通知列表
     */
    @PreAuthorize("@ss.hasPermi('system:notifications:export')")
    @Log(title = "通知", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Notifications notifications)
    {
        List<Notifications> list = notificationsService.selectNotificationsList(notifications);
        ExcelUtil<Notifications> util = new ExcelUtil<Notifications>(Notifications.class);
        util.exportExcel(response, list, "通知数据");
    }

    /**
     * 获取通知详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:notifications:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(notificationsService.selectNotificationsById(id));
    }

    /**
     * 新增通知
     */
    @PreAuthorize("@ss.hasPermi('system:notifications:add')")
    @Log(title = "通知", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Notifications notifications)
    {
        return toAjax(notificationsService.insertNotifications(notifications));
    }

    /**
     * 修改通知
     */
    @PreAuthorize("@ss.hasPermi('system:notifications:edit')")
    @Log(title = "通知", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Notifications notifications)
    {
        return toAjax(notificationsService.updateNotifications(notifications));
    }

    /**
     * 删除通知
     */
    @PreAuthorize("@ss.hasPermi('system:notifications:remove')")
    @Log(title = "通知", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(notificationsService.deleteNotificationsByIds(ids));
    }
}
