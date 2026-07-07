package com.ruoyi.interview.mapper;

import java.util.List;
import com.ruoyi.interview.domain.InterviewDimensionScore;

/**
 * 维度评分Mapper接口
 * 
 * @author 李坤
 * @date 2026-06-24
 */
public interface InterviewDimensionScoreMapper 
{
    /**
     * 查询维度评分
     * 
     * @param id 维度评分主键
     * @return 维度评分
     */
    public InterviewDimensionScore selectInterviewDimensionScoreById(Long id);

    /**
     * 查询维度评分列表
     * 
     * @param interviewDimensionScore 维度评分
     * @return 维度评分集合
     */
    public List<InterviewDimensionScore> selectInterviewDimensionScoreList(InterviewDimensionScore interviewDimensionScore);

    /**
     * 新增维度评分
     * 
     * @param interviewDimensionScore 维度评分
     * @return 结果
     */
    public int insertInterviewDimensionScore(InterviewDimensionScore interviewDimensionScore);

    /**
     * 修改维度评分
     * 
     * @param interviewDimensionScore 维度评分
     * @return 结果
     */
    public int updateInterviewDimensionScore(InterviewDimensionScore interviewDimensionScore);

    /**
     * 删除维度评分
     * 
     * @param id 维度评分主键
     * @return 结果
     */
    public int deleteInterviewDimensionScoreById(Long id);

    /**
     * 批量删除维度评分
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteInterviewDimensionScoreByIds(Long[] ids);
}
