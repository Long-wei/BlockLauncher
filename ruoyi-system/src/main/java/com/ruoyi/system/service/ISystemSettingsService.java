package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SystemSettings;

/**
 * 系统配置Service接口
 * 
 * @author ruoyi
 * @date 2025-12-30
 */
public interface ISystemSettingsService 
{
    /**
     * 查询系统配置
     * 
     * @param id 系统配置主键
     * @return 系统配置
     */
    public SystemSettings selectSystemSettingsById(Long id);

    /**
     * 查询系统配置列表
     * 
     * @param systemSettings 系统配置
     * @return 系统配置集合
     */
    public List<SystemSettings> selectSystemSettingsList(SystemSettings systemSettings);

    /**
     * 新增系统配置
     * 
     * @param systemSettings 系统配置
     * @return 结果
     */
    public int insertSystemSettings(SystemSettings systemSettings);

    /**
     * 修改系统配置
     * 
     * @param systemSettings 系统配置
     * @return 结果
     */
    public int updateSystemSettings(SystemSettings systemSettings);

    /**
     * 批量删除系统配置
     * 
     * @param ids 需要删除的系统配置主键集合
     * @return 结果
     */
    public int deleteSystemSettingsByIds(Long[] ids);

    /**
     * 删除系统配置信息
     * 
     * @param id 系统配置主键
     * @return 结果
     */
    public int deleteSystemSettingsById(Long id);
}
