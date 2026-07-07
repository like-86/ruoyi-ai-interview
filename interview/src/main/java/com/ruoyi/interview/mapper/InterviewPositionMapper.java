package com.ruoyi.interview.mapper;

import java.util.List;
import com.ruoyi.interview.domain.InterviewPosition;

/**
 * 岗位Mapper接口
 * 
 * @author 李坤
 * @date 2026-06-24
 */
public interface InterviewPositionMapper 
{
    /**
     * 查询岗位
     * 
     * @param id 岗位主键
     * @return 岗位
     */
    public InterviewPosition selectInterviewPositionById(Long id);

    /**
     * 查询岗位列表
     * 
     * @param interviewPosition 岗位
     * @return 岗位集合
     */
    public List<InterviewPosition> selectInterviewPositionList(InterviewPosition interviewPosition);

    /**
     * 新增岗位
     * 
     * @param interviewPosition 岗位
     * @return 结果
     */
    public int insertInterviewPosition(InterviewPosition interviewPosition);

    /**
     * 修改岗位
     * 
     * @param interviewPosition 岗位
     * @return 结果
     */
    public int updateInterviewPosition(InterviewPosition interviewPosition);

    /**
     * 删除岗位
     * 
     * @param id 岗位主键
     * @return 结果
     */
    public int deleteInterviewPositionById(Long id);

    /**
     * 批量删除岗位
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteInterviewPositionByIds(Long[] ids);
}
