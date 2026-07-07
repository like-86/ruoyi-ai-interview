package com.ruoyi.interview.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.interview.mapper.InterviewPositionMapper;
import com.ruoyi.interview.domain.InterviewPosition;
import com.ruoyi.interview.service.IInterviewPositionService;

/**
 * 岗位Service业务层处理
 * 
 * @author 李坤
 * @date 2026-06-24
 */
@Service
public class InterviewPositionServiceImpl implements IInterviewPositionService 
{
    @Autowired
    private InterviewPositionMapper interviewPositionMapper;

    /**
     * 查询岗位
     * 
     * @param id 岗位主键
     * @return 岗位
     */
    @Override
    public InterviewPosition selectInterviewPositionById(Long id)
    {
        return interviewPositionMapper.selectInterviewPositionById(id);
    }

    /**
     * 查询岗位列表
     * 
     * @param interviewPosition 岗位
     * @return 岗位
     */
    @Override
    public List<InterviewPosition> selectInterviewPositionList(InterviewPosition interviewPosition)
    {
        return interviewPositionMapper.selectInterviewPositionList(interviewPosition);
    }

    /**
     * 新增岗位
     * 
     * @param interviewPosition 岗位
     * @return 结果
     */
    @Override
    public int insertInterviewPosition(InterviewPosition interviewPosition)
    {
        interviewPosition.setCreateTime(DateUtils.getNowDate());
        return interviewPositionMapper.insertInterviewPosition(interviewPosition);
    }

    /**
     * 修改岗位
     * 
     * @param interviewPosition 岗位
     * @return 结果
     */
    @Override
    public int updateInterviewPosition(InterviewPosition interviewPosition)
    {
        interviewPosition.setUpdateTime(DateUtils.getNowDate());
        return interviewPositionMapper.updateInterviewPosition(interviewPosition);
    }

    /**
     * 批量删除岗位
     * 
     * @param ids 需要删除的岗位主键
     * @return 结果
     */
    @Override
    public int deleteInterviewPositionByIds(Long[] ids)
    {
        return interviewPositionMapper.deleteInterviewPositionByIds(ids);
    }

    /**
     * 删除岗位信息
     * 
     * @param id 岗位主键
     * @return 结果
     */
    @Override
    public int deleteInterviewPositionById(Long id)
    {
        return interviewPositionMapper.deleteInterviewPositionById(id);
    }
}
