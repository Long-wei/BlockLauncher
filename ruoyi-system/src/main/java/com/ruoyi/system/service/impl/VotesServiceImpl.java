package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.VotesMapper;
import com.ruoyi.system.domain.Votes;
import com.ruoyi.system.service.IVotesService;

/**
 * 投票Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-30
 */
@Service
public class VotesServiceImpl implements IVotesService 
{
    @Autowired
    private VotesMapper votesMapper;

    /**
     * 查询投票
     * 
     * @param id 投票主键
     * @return 投票
     */
    @Override
    public Votes selectVotesById(Long id)
    {
        return votesMapper.selectVotesById(id);
    }

    /**
     * 查询投票列表
     * 
     * @param votes 投票
     * @return 投票
     */
    @Override
    public List<Votes> selectVotesList(Votes votes)
    {
        return votesMapper.selectVotesList(votes);
    }

    /**
     * 新增投票
     * 
     * @param votes 投票
     * @return 结果
     */
    @Override
    public int insertVotes(Votes votes)
    {
        return votesMapper.insertVotes(votes);
    }

    /**
     * 修改投票
     * 
     * @param votes 投票
     * @return 结果
     */
    @Override
    public int updateVotes(Votes votes)
    {
        return votesMapper.updateVotes(votes);
    }

    /**
     * 批量删除投票
     * 
     * @param ids 需要删除的投票主键
     * @return 结果
     */
    @Override
    public int deleteVotesByIds(Long[] ids)
    {
        return votesMapper.deleteVotesByIds(ids);
    }

    /**
     * 删除投票信息
     * 
     * @param id 投票主键
     * @return 结果
     */
    @Override
    public int deleteVotesById(Long id)
    {
        return votesMapper.deleteVotesById(id);
    }
}
