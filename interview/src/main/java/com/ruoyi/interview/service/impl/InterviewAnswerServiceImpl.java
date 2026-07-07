package com.ruoyi.interview.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.interview.mapper.InterviewAnswerMapper;
import com.ruoyi.interview.domain.InterviewAnswer;
import com.ruoyi.interview.service.IInterviewAnswerService;

/**
 * 面试回答Service业务层处理
 * 
 * @author 李坤
 * @date 2026-06-25
 */
@Service
public class InterviewAnswerServiceImpl implements IInterviewAnswerService 
{
    @Autowired
    private InterviewAnswerMapper interviewAnswerMapper;

    /**
     * 查询面试回答
     * 
     * @param id 面试回答主键
     * @return 面试回答
     */
    @Override
    public InterviewAnswer selectInterviewAnswerById(Long id)
    {
        return interviewAnswerMapper.selectInterviewAnswerById(id);
    }

    /**
     * 查询面试回答列表
     * 
     * @param interviewAnswer 面试回答
     * @return 面试回答
     */
    @Override
    public List<InterviewAnswer> selectInterviewAnswerList(InterviewAnswer interviewAnswer)
    {
        return interviewAnswerMapper.selectInterviewAnswerList(interviewAnswer);
    }

    /**
     * 新增面试回答
     * 
     * @param interviewAnswer 面试回答
     * @return 结果
     */
    @Override
    public int insertInterviewAnswer(InterviewAnswer interviewAnswer)
    {
        interviewAnswer.setCreateTime(DateUtils.getNowDate());
        return interviewAnswerMapper.insertInterviewAnswer(interviewAnswer);
    }

    /**
     * 修改面试回答
     * 
     * @param interviewAnswer 面试回答
     * @return 结果
     */
    @Override
    public int updateInterviewAnswer(InterviewAnswer interviewAnswer)
    {
        interviewAnswer.setUpdateTime(DateUtils.getNowDate());
        return interviewAnswerMapper.updateInterviewAnswer(interviewAnswer);
    }

    /**
     * 批量删除面试回答
     * 
     * @param ids 需要删除的面试回答主键
     * @return 结果
     */
    @Override
    public int deleteInterviewAnswerByIds(Long[] ids)
    {
        return interviewAnswerMapper.deleteInterviewAnswerByIds(ids);
    }

    /**
     * 删除面试回答信息
     * 
     * @param id 面试回答主键
     * @return 结果
     */
    @Override
    public int deleteInterviewAnswerById(Long id)
    {
        return interviewAnswerMapper.deleteInterviewAnswerById(id);
    }

    @Override
    public List<InterviewAnswer> selectInterviewAnswerByQuestionIds(List<Long> questionIds)
    {
        return interviewAnswerMapper.selectInterviewAnswerByQuestionId(questionIds);
    }
}
