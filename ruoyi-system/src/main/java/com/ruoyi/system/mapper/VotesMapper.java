package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Votes;

/**
 * 投票Mapper接口
 * 
 * @author ruoyi
 * @date 2025-12-30
 */
public interface VotesMapper 
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
     * 删除投票
     * 
     * @param id 投票主键
     * @return 结果
     */
    public int deleteVotesById(Long id);

    /**
     * 批量删除投票
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteVotesByIds(Long[] ids);
}
