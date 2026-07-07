package com.ruoyi.interview.service;

import java.util.List;
import com.ruoyi.interview.domain.InterviewAnswer;
import com.ruoyi.interview.domain.InterviewQuestion;

/**
 * 面试回答Service接口
 * 
 * @author 李坤
 * @date 2026-06-25
 */
public interface IInterviewAnswerService 
{
    /**
     * 查询面试回答
     * 
     * @param id 面试回答主键
     * @return 面试回答
     */
    public InterviewAnswer selectInterviewAnswerById(Long id);

    /**
     * 查询面试回答列表
     * 
     * @param interviewAnswer 面试回答
     * @return 面试回答集合
     */
    public List<InterviewAnswer> selectInterviewAnswerList(InterviewAnswer interviewAnswer);

    /**
     * 新增面试回答
     * 
     * @param interviewAnswer 面试回答
     * @return 结果
     */
    public int insertInterviewAnswer(InterviewAnswer interviewAnswer);

    /**
     * 修改面试回答
     * 
     * @param interviewAnswer 面试回答
     * @return 结果
     */
    public int updateInterviewAnswer(InterviewAnswer interviewAnswer);

    /**
     * 批量删除面试回答
     * 
     * @param ids 需要删除的面试回答主键集合
     * @return 结果
     */
    public int deleteInterviewAnswerByIds(Long[] ids);

    /**
     * 删除面试回答信息
     * 
     * @param id 面试回答主键
     * @return 结果
     */
    public int deleteInterviewAnswerById(Long id);


    List<InterviewAnswer> selectInterviewAnswerByQuestionIds(List<Long> questionIds);
}
