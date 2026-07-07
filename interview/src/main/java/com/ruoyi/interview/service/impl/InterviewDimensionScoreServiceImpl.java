package com.ruoyi.interview.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.interview.mapper.InterviewDimensionScoreMapper;
import com.ruoyi.interview.domain.InterviewDimensionScore;
import com.ruoyi.interview.service.IInterviewDimensionScoreService;

/**
 * 维度评分Service业务层处理
 * 
 * @author 李坤
 * @date 2026-06-24
 */
@Service
public class InterviewDimensionScoreServiceImpl implements IInterviewDimensionScoreService 
{
    @Autowired
    private InterviewDimensionScoreMapper interviewDimensionScoreMapper;

    /**
     * 查询维度评分
     * 
     * @param id 维度评分主键
     * @return 维度评分
     */
    @Override
    public InterviewDimensionScore selectInterviewDimensionScoreById(Long id)
    {
        return interviewDimensionScoreMapper.selectInterviewDimensionScoreById(id);
    }

    /**
     * 查询维度评分列表
     * 
     * @param interviewDimensionScore 维度评分
     * @return 维度评分
     */
    @Override
    public List<InterviewDimensionScore> selectInterviewDimensionScoreList(InterviewDimensionScore interviewDimensionScore)
    {
        return interviewDimensionScoreMapper.selectInterviewDimensionScoreList(interviewDimensionScore);
    }

    /**
     * 新增维度评分
     * 
     * @param interviewDimensionScore 维度评分
     * @return 结果
     */
    @Override
    public int insertInterviewDimensionScore(InterviewDimensionScore interviewDimensionScore)
    {
        interviewDimensionScore.setCreateTime(DateUtils.getNowDate());
        return interviewDimensionScoreMapper.insertInterviewDimensionScore(interviewDimensionScore);
    }

    /**
     * 修改维度评分
     * 
     * @param interviewDimensionScore 维度评分
     * @return 结果
     */
    @Override
    public int updateInterviewDimensionScore(InterviewDimensionScore interviewDimensionScore)
    {
        return interviewDimensionScoreMapper.updateInterviewDimensionScore(interviewDimensionScore);
    }

    /**
     * 批量删除维度评分
     * 
     * @param ids 需要删除的维度评分主键
     * @return 结果
     */
    @Override
    public int deleteInterviewDimensionScoreByIds(Long[] ids)
    {
        return interviewDimensionScoreMapper.deleteInterviewDimensionScoreByIds(ids);
    }

    /**
     * 删除维度评分信息
     * 
     * @param id 维度评分主键
     * @return 结果
     */
    @Override
    public int deleteInterviewDimensionScoreById(Long id)
    {
        return interviewDimensionScoreMapper.deleteInterviewDimensionScoreById(id);
    }
}
