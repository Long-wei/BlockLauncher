package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Categories;

/**
 * 反馈分类Service接口
 * 
 * @author ruoyi
 * @date 2025-12-30
 */
public interface ICategoriesService 
{
    /**
     * 查询反馈分类
     * 
     * @param id 反馈分类主键
     * @return 反馈分类
     */
    public Categories selectCategoriesById(Long id);

    /**
     * 查询反馈分类列表
     * 
     * @param categories 反馈分类
     * @return 反馈分类集合
     */
    public List<Categories> selectCategoriesList(Categories categories);

    /**
     * 新增反馈分类
     * 
     * @param categories 反馈分类
     * @return 结果
     */
    public int insertCategories(Categories categories);

    /**
     * 修改反馈分类
     * 
     * @param categories 反馈分类
     * @return 结果
     */
    public int updateCategories(Categories categories);

    /**
     * 批量删除反馈分类
     * 
     * @param ids 需要删除的反馈分类主键集合
     * @return 结果
     */
    public int deleteCategoriesByIds(Long[] ids);

    /**
     * 删除反馈分类信息
     * 
     * @param id 反馈分类主键
     * @return 结果
     */
    public int deleteCategoriesById(Long id);
}
