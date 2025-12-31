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
import com.ruoyi.system.domain.Votes;
import com.ruoyi.system.service.IVotesService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 投票Controller
 * 
 * @author ruoyi
 * @date 2025-12-30
 */
@RestController
@RequestMapping("/system/votes")
public class VotesController extends BaseController
{
    @Autowired
    private IVotesService votesService;

    /**
     * 查询投票列表
     */
    @PreAuthorize("@ss.hasPermi('system:votes:list')")
    @GetMapping("/list")
    public TableDataInfo list(Votes votes)
    {
        startPage();
        List<Votes> list = votesService.selectVotesList(votes);
        return getDataTable(list);
    }

    /**
     * 导出投票列表
     */
    @PreAuthorize("@ss.hasPermi('system:votes:export')")
    @Log(title = "投票", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Votes votes)
    {
        List<Votes> list = votesService.selectVotesList(votes);
        ExcelUtil<Votes> util = new ExcelUtil<Votes>(Votes.class);
        util.exportExcel(response, list, "投票数据");
    }

    /**
     * 获取投票详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:votes:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(votesService.selectVotesById(id));
    }

    /**
     * 新增投票
     */
    @PreAuthorize("@ss.hasPermi('system:votes:add')")
    @Log(title = "投票", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Votes votes)
    {
        return toAjax(votesService.insertVotes(votes));
    }

    /**
     * 修改投票
     */
    @PreAuthorize("@ss.hasPermi('system:votes:edit')")
    @Log(title = "投票", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Votes votes)
    {
        return toAjax(votesService.updateVotes(votes));
    }

    /**
     * 删除投票
     */
    @PreAuthorize("@ss.hasPermi('system:votes:remove')")
    @Log(title = "投票", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(votesService.deleteVotesByIds(ids));
    }
}
