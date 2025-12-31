package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ReportsMapper;
import com.ruoyi.system.domain.Reports;
import com.ruoyi.system.service.IReportsService;

/**
 * 举报Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-30
 */
@Service
public class ReportsServiceImpl implements IReportsService 
{
    @Autowired
    private ReportsMapper reportsMapper;

    /**
     * 查询举报
     * 
     * @param id 举报主键
     * @return 举报
     */
    @Override
    public Reports selectReportsById(Long id)
    {
        return reportsMapper.selectReportsById(id);
    }

    /**
     * 查询举报列表
     * 
     * @param reports 举报
     * @return 举报
     */
    @Override
    public List<Reports> selectReportsList(Reports reports)
    {
        return reportsMapper.selectReportsList(reports);
    }

    /**
     * 新增举报
     * 
     * @param reports 举报
     * @return 结果
     */
    @Override
    public int insertReports(Reports reports)
    {
        return reportsMapper.insertReports(reports);
    }

    /**
     * 修改举报
     * 
     * @param reports 举报
     * @return 结果
     */
    @Override
    public int updateReports(Reports reports)
    {
        return reportsMapper.updateReports(reports);
    }

    /**
     * 批量删除举报
     * 
     * @param ids 需要删除的举报主键
     * @return 结果
     */
    @Override
    public int deleteReportsByIds(Long[] ids)
    {
        return reportsMapper.deleteReportsByIds(ids);
    }

    /**
     * 删除举报信息
     * 
     * @param id 举报主键
     * @return 结果
     */
    @Override
    public int deleteReportsById(Long id)
    {
        return reportsMapper.deleteReportsById(id);
    }
}
