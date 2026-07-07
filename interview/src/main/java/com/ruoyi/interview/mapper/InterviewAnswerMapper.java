package com.ruoyi.interview.mapper;

import java.util.List;
import com.ruoyi.interview.domain.InterviewAnswer;

/**
 * 面试回答Mapper接口
 * 
 * @author 李坤
 * @date 2026-06-25
 */
public interface InterviewAnswerMapper 
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
     * 删除面试回答
     * 
     * @param id 面试回答主键
     * @return 结果
     */
    public int deleteInterviewAnswerById(Long id);

    /**
     * 批量删除面试回答
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteInterviewAnswerByIds(Long[] ids);

    List<InterviewAnswer> selectInterviewAnswerByQuestionId(List<Long> questionIds);
}
