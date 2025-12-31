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
import com.ruoyi.system.domain.SystemSettings;
import com.ruoyi.system.service.ISystemSettingsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 系统配置Controller
 * 
 * @author ruoyi
 * @date 2025-12-30
 */
@RestController
@RequestMapping("/system/Opinion_configuration")
public class SystemSettingsController extends BaseController
{
    @Autowired
    private ISystemSettingsService systemSettingsService;

    /**
     * 查询系统配置列表
     */
    @PreAuthorize("@ss.hasPermi('system:Opinion_configuration:list')")
    @GetMapping("/list")
    public TableDataInfo list(SystemSettings systemSettings)
    {
        startPage();
        List<SystemSettings> list = systemSettingsService.selectSystemSettingsList(systemSettings);
        return getDataTable(list);
    }

    /**
     * 导出系统配置列表
     */
    @PreAuthorize("@ss.hasPermi('system:Opinion_configuration:export')")
    @Log(title = "系统配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SystemSettings systemSettings)
    {
        List<SystemSettings> list = systemSettingsService.selectSystemSettingsList(systemSettings);
        ExcelUtil<SystemSettings> util = new ExcelUtil<SystemSettings>(SystemSettings.class);
        util.exportExcel(response, list, "系统配置数据");
    }

    /**
     * 获取系统配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:Opinion_configuration:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(systemSettingsService.selectSystemSettingsById(id));
    }

    /**
     * 新增系统配置
     */
    @PreAuthorize("@ss.hasPermi('system:Opinion_configuration:add')")
    @Log(title = "系统配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SystemSettings systemSettings)
    {
        return toAjax(systemSettingsService.insertSystemSettings(systemSettings));
    }

    /**
     * 修改系统配置
     */
    @PreAuthorize("@ss.hasPermi('system:Opinion_configuration:edit')")
    @Log(title = "系统配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SystemSettings systemSettings)
    {
        return toAjax(systemSettingsService.updateSystemSettings(systemSettings));
    }

    /**
     * 删除系统配置
     */
    @PreAuthorize("@ss.hasPermi('system:Opinion_configuration:remove')")
    @Log(title = "系统配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(systemSettingsService.deleteSystemSettingsByIds(ids));
    }
}
