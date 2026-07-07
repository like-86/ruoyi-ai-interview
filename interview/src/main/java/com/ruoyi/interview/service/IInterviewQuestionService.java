package com.ruoyi.interview.service;

import java.util.List;

import com.ruoyi.interview.domain.InterviewPosition;
import com.ruoyi.interview.domain.InterviewQuestion;

/**
 * 面试问题Service接口
 * 
 * @author 李坤
 * @date 2026-06-25
 */
public interface IInterviewQuestionService 
{
    /**
     * 查询面试问题
     * 
     * @param id 面试问题主键
     * @return 面试问题
     */
    public InterviewQuestion selectInterviewQuestionById(Long id);

    /**
     * 查询面试问题列表
     * 
     * @param interviewQuestion 面试问题
     * @return 面试问题集合
     */
    public List<InterviewQuestion> selectInterviewQuestionList(InterviewQuestion interviewQuestion);

    /**
     * 新增面试问题
     * 
     * @param interviewQuestion 面试问题
     * @return 结果
     */
    public int insertInterviewQuestion(InterviewQuestion interviewQuestion);

    /**
     * 修改面试问题
     * 
     * @param interviewQuestion 面试问题
     * @return 结果
     */
    public int updateInterviewQuestion(InterviewQuestion interviewQuestion);

    /**
     * 批量删除面试问题
     * 
     * @param ids 需要删除的面试问题主键集合
     * @return 结果
     */
    public int deleteInterviewQuestionByIds(Long[] ids);

    /**
     * 删除面试问题信息
     * 
     * @param id 面试问题主键
     * @return 结果
     */
    public int deleteInterviewQuestionById(Long id);

    List<InterviewQuestion> selectInterviewQuestionReportId( long reportId);
}
