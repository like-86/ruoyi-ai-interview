package com.ruoyi.interview.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.interview.mapper.InterviewDimensionMapper;
import com.ruoyi.interview.domain.InterviewDimension;
import com.ruoyi.interview.service.IInterviewDimensionService;

/**
 * 面试维度Service业务层处理
 * 
 * @author 李坤
 * @date 2026-06-24
 */
@Service
public class InterviewDimensionServiceImpl implements IInterviewDimensionService 
{
    @Autowired
    private InterviewDimensionMapper interviewDimensionMapper;

    /**
     * 查询面试维度
     * 
     * @param id 面试维度主键
     * @return 面试维度
     */
    @Override
    public InterviewDimension selectInterviewDimensionById(Long id)
    {
        return interviewDimensionMapper.selectInterviewDimensionById(id);
    }

    /**
     * 查询面试维度列表
     * 
     * @param interviewDimension 面试维度
     * @return 面试维度
     */
    @Override
    public List<InterviewDimension> selectInterviewDimensionList(InterviewDimension interviewDimension)
    {
        return interviewDimensionMapper.selectInterviewDimensionList(interviewDimension);
    }

    /**
     * 新增面试维度
     * 
     * @param interviewDimension 面试维度
     * @return 结果
     */
    @Override
    public int insertInterviewDimension(InterviewDimension interviewDimension)
    {
        interviewDimension.setCreateTime(DateUtils.getNowDate());
        return interviewDimensionMapper.insertInterviewDimension(interviewDimension);
    }

    /**
     * 修改面试维度
     * 
     * @param interviewDimension 面试维度
     * @return 结果
     */
    @Override
    public int updateInterviewDimension(InterviewDimension interviewDimension)
    {
        interviewDimension.setUpdateTime(DateUtils.getNowDate());
        return interviewDimensionMapper.updateInterviewDimension(interviewDimension);
    }

    /**
     * 批量删除面试维度
     * 
     * @param ids 需要删除的面试维度主键
     * @return 结果
     */
    @Override
    public int deleteInterviewDimensionByIds(Long[] ids)
    {
        return interviewDimensionMapper.deleteInterviewDimensionByIds(ids);
    }

    /**
     * 删除面试维度信息
     * 
     * @param id 面试维度主键
     * @return 结果
     */
    @Override
    public int deleteInterviewDimensionById(Long id)
    {
        return interviewDimensionMapper.deleteInterviewDimensionById(id);
    }

    @Override
    public List<InterviewDimension> selectInterviewDimensionByPositionId(long positionId) {
        return interviewDimensionMapper.selectInterviewDimensionByPositionId(positionId);

    }
}
