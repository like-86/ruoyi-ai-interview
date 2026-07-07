package com.ruoyi.interview.mapper;

import java.util.List;
import com.ruoyi.interview.domain.InterviewDimension;

/**
 * 面试维度Mapper接口
 * 
 * @author 李坤
 * @date 2026-06-24
 */
public interface InterviewDimensionMapper 
{
    /**
     * 查询面试维度
     * 
     * @param id 面试维度主键
     * @return 面试维度
     */
    public InterviewDimension selectInterviewDimensionById(Long id);

    /**
     * 查询面试维度列表
     * 
     * @param interviewDimension 面试维度
     * @return 面试维度集合
     */
    public List<InterviewDimension> selectInterviewDimensionList(InterviewDimension interviewDimension);

    /**
     * 新增面试维度
     * 
     * @param interviewDimension 面试维度
     * @return 结果
     */
    public int insertInterviewDimension(InterviewDimension interviewDimension);

    /**
     * 修改面试维度
     * 
     * @param interviewDimension 面试维度
     * @return 结果
     */
    public int updateInterviewDimension(InterviewDimension interviewDimension);

    /**
     * 删除面试维度
     * 
     * @param id 面试维度主键
     * @return 结果
     */
    public int deleteInterviewDimensionById(Long id);

    /**
     * 批量删除面试维度
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteInterviewDimensionByIds(Long[] ids);

    List<InterviewDimension> selectInterviewDimensionByPositionId(long positionId);
}
