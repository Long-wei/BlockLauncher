package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Reports;

/**
 * 举报Mapper接口
 * 
 * @author ruoyi
 * @date 2025-12-30
 */
public interface ReportsMapper 
{
    /**
     * 查询举报
     * 
     * @param id 举报主键
     * @return 举报
     */
    public Reports selectReportsById(Long id);

    /**
     * 查询举报列表
     * 
     * @param reports 举报
     * @return 举报集合
     */
    public List<Reports> selectReportsList(Reports reports);

    /**
     * 新增举报
     * 
     * @param reports 举报
     * @return 结果
     */
    public int insertReports(Reports reports);

    /**
     * 修改举报
     * 
     * @param reports 举报
     * @return 结果
     */
    public int updateReports(Reports reports);

    /**
     * 删除举报
     * 
     * @param id 举报主键
     * @return 结果
     */
    public int deleteReportsById(Long id);

    /**
     * 批量删除举报
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteReportsByIds(Long[] ids);
}
