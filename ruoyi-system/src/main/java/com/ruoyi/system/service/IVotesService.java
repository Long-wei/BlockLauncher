package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Votes;

/**
 * 投票Service接口
 * 
 * @author ruoyi
 * @date 2025-12-30
 */
public interface IVotesService 
{
    /**
     * 查询投票
     * 
     * @param id 投票主键
     * @return 投票
     */
    public Votes selectVotesById(Long id);

    /**
     * 查询投票列表
     * 
     * @param votes 投票
     * @return 投票集合
     */
    public List<Votes> selectVotesList(Votes votes);

    /**
     * 新增投票
     * 
     * @param votes 投票
     * @return 结果
     */
    public int insertVotes(Votes votes);

    /**
     * 修改投票
     * 
     * @param votes 投票
     * @return 结果
     */
    public int updateVotes(Votes votes);

    /**
     * 批量删除投票
     * 
     * @param ids 需要删除的投票主键集合
     * @return 结果
     */
    public int deleteVotesByIds(Long[] ids);

    /**
     * 删除投票信息
     * 
     * @param id 投票主键
     * @return 结果
     */
    public int deleteVotesById(Long id);
}
