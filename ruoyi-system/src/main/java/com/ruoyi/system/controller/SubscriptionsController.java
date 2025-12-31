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
import com.ruoyi.system.domain.Subscriptions;
import com.ruoyi.system.service.ISubscriptionsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 关注订阅Controller
 * 
 * @author ruoyi
 * @date 2025-12-30
 */
@RestController
@RequestMapping("/system/subscriptions")
public class SubscriptionsController extends BaseController
{
    @Autowired
    private ISubscriptionsService subscriptionsService;

    /**
     * 查询关注订阅列表
     */
    @PreAuthorize("@ss.hasPermi('system:subscriptions:list')")
    @GetMapping("/list")
    public TableDataInfo list(Subscriptions subscriptions)
    {
        startPage();
        List<Subscriptions> list = subscriptionsService.selectSubscriptionsList(subscriptions);
        return getDataTable(list);
    }

    /**
     * 导出关注订阅列表
     */
    @PreAuthorize("@ss.hasPermi('system:subscriptions:export')")
    @Log(title = "关注订阅", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Subscriptions subscriptions)
    {
        List<Subscriptions> list = subscriptionsService.selectSubscriptionsList(subscriptions);
        ExcelUtil<Subscriptions> util = new ExcelUtil<Subscriptions>(Subscriptions.class);
        util.exportExcel(response, list, "关注订阅数据");
    }

    /**
     * 获取关注订阅详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:subscriptions:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(subscriptionsService.selectSubscriptionsById(id));
    }

    /**
     * 新增关注订阅
     */
    @PreAuthorize("@ss.hasPermi('system:subscriptions:add')")
    @Log(title = "关注订阅", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Subscriptions subscriptions)
    {
        return toAjax(subscriptionsService.insertSubscriptions(subscriptions));
    }

    /**
     * 修改关注订阅
     */
    @PreAuthorize("@ss.hasPermi('system:subscriptions:edit')")
    @Log(title = "关注订阅", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Subscriptions subscriptions)
    {
        return toAjax(subscriptionsService.updateSubscriptions(subscriptions));
    }

    /**
     * 删除关注订阅
     */
    @PreAuthorize("@ss.hasPermi('system:subscriptions:remove')")
    @Log(title = "关注订阅", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(subscriptionsService.deleteSubscriptionsByIds(ids));
    }
}
