package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SystemSettingsMapper;
import com.ruoyi.system.domain.SystemSettings;
import com.ruoyi.system.service.ISystemSettingsService;

/**
 * 系统配置Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-30
 */
@Service
public class SystemSettingsServiceImpl implements ISystemSettingsService 
{
    @Autowired
    private SystemSettingsMapper systemSettingsMapper;

    /**
     * 查询系统配置
     * 
     * @param id 系统配置主键
     * @return 系统配置
     */
    @Override
    public SystemSettings selectSystemSettingsById(Long id)
    {
        return systemSettingsMapper.selectSystemSettingsById(id);
    }

    /**
     * 查询系统配置列表
     * 
     * @param systemSettings 系统配置
     * @return 系统配置
     */
    @Override
    public List<SystemSettings> selectSystemSettingsList(SystemSettings systemSettings)
    {
        return systemSettingsMapper.selectSystemSettingsList(systemSettings);
    }

    /**
     * 新增系统配置
     * 
     * @param systemSettings 系统配置
     * @return 结果
     */
    @Override
    public int insertSystemSettings(SystemSettings systemSettings)
    {
        return systemSettingsMapper.insertSystemSettings(systemSettings);
    }

    /**
     * 修改系统配置
     * 
     * @param systemSettings 系统配置
     * @return 结果
     */
    @Override
    public int updateSystemSettings(SystemSettings systemSettings)
    {
        return systemSettingsMapper.updateSystemSettings(systemSettings);
    }

    /**
     * 批量删除系统配置
     * 
     * @param ids 需要删除的系统配置主键
     * @return 结果
     */
    @Override
    public int deleteSystemSettingsByIds(Long[] ids)
    {
        return systemSettingsMapper.deleteSystemSettingsByIds(ids);
    }

    /**
     * 删除系统配置信息
     * 
     * @param id 系统配置主键
     * @return 结果
     */
    @Override
    public int deleteSystemSettingsById(Long id)
    {
        return systemSettingsMapper.deleteSystemSettingsById(id);
    }
}
