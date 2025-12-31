package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CategoriesMapper;
import com.ruoyi.system.domain.Categories;
import com.ruoyi.system.service.ICategoriesService;

/**
 * 反馈分类Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-30
 */
@Service
public class CategoriesServiceImpl implements ICategoriesService 
{
    @Autowired
    private CategoriesMapper categoriesMapper;

    /**
     * 查询反馈分类
     * 
     * @param id 反馈分类主键
     * @return 反馈分类
     */
    @Override
    public Categories selectCategoriesById(Long id)
    {
        return categoriesMapper.selectCategoriesById(id);
    }

    /**
     * 查询反馈分类列表
     * 
     * @param categories 反馈分类
     * @return 反馈分类
     */
    @Override
    public List<Categories> selectCategoriesList(Categories categories)
    {
        return categoriesMapper.selectCategoriesList(categories);
    }

    /**
     * 新增反馈分类
     * 
     * @param categories 反馈分类
     * @return 结果
     */
    @Override
    public int insertCategories(Categories categories)
    {
        return categoriesMapper.insertCategories(categories);
    }

    /**
     * 修改反馈分类
     * 
     * @param categories 反馈分类
     * @return 结果
     */
    @Override
    public int updateCategories(Categories categories)
    {
        return categoriesMapper.updateCategories(categories);
    }

    /**
     * 批量删除反馈分类
     * 
     * @param ids 需要删除的反馈分类主键
     * @return 结果
     */
    @Override
    public int deleteCategoriesByIds(Long[] ids)
    {
        return categoriesMapper.deleteCategoriesByIds(ids);
    }

    /**
     * 删除反馈分类信息
     * 
     * @param id 反馈分类主键
     * @return 结果
     */
    @Override
    public int deleteCategoriesById(Long id)
    {
        return categoriesMapper.deleteCategoriesById(id);
    }
}
