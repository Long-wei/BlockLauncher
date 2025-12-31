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
import com.ruoyi.system.domain.Categories;
import com.ruoyi.system.service.ICategoriesService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 反馈分类Controller
 * 
 * @author ruoyi
 * @date 2025-12-30
 */
@RestController
@RequestMapping("/system/categories")
public class CategoriesController extends BaseController
{
    @Autowired
    private ICategoriesService categoriesService;

    /**
     * 查询反馈分类列表
     */
    @PreAuthorize("@ss.hasPermi('system:categories:list')")
    @GetMapping("/list")
    public TableDataInfo list(Categories categories)
    {
        startPage();
        List<Categories> list = categoriesService.selectCategoriesList(categories);
        return getDataTable(list);
    }

    /**
     * 导出反馈分类列表
     */
    @PreAuthorize("@ss.hasPermi('system:categories:export')")
    @Log(title = "反馈分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Categories categories)
    {
        List<Categories> list = categoriesService.selectCategoriesList(categories);
        ExcelUtil<Categories> util = new ExcelUtil<Categories>(Categories.class);
        util.exportExcel(response, list, "反馈分类数据");
    }

    /**
     * 获取反馈分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:categories:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(categoriesService.selectCategoriesById(id));
    }

    /**
     * 新增反馈分类
     */
    @PreAuthorize("@ss.hasPermi('system:categories:add')")
    @Log(title = "反馈分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Categories categories)
    {
        return toAjax(categoriesService.insertCategories(categories));
    }

    /**
     * 修改反馈分类
     */
    @PreAuthorize("@ss.hasPermi('system:categories:edit')")
    @Log(title = "反馈分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Categories categories)
    {
        return toAjax(categoriesService.updateCategories(categories));
    }

    /**
     * 删除反馈分类
     */
    @PreAuthorize("@ss.hasPermi('system:categories:remove')")
    @Log(title = "反馈分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(categoriesService.deleteCategoriesByIds(ids));
    }
}
