package com.ruoyi.interview.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.interview.mapper.InterviewQuestionMapper;
import com.ruoyi.interview.domain.InterviewQuestion;
import com.ruoyi.interview.service.IInterviewQuestionService;

/**
 * 面试问题Service业务层处理
 * 
 * @author 李坤
 * @date 2026-06-25
 */
@Service
public class InterviewQuestionServiceImpl implements IInterviewQuestionService 
{
    @Autowired
    private InterviewQuestionMapper interviewQuestionMapper;

    /**
     * 查询面试问题
     * 
     * @param id 面试问题主键
     * @return 面试问题
     */
    @Override
    public InterviewQuestion selectInterviewQuestionById(Long id)
    {
        return interviewQuestionMapper.selectInterviewQuestionById(id);
    }

    /**
     * 查询面试问题列表
     * 
     * @param interviewQuestion 面试问题
     * @return 面试问题
     */
    @Override
    public List<InterviewQuestion> selectInterviewQuestionList(InterviewQuestion interviewQuestion)
    {
        return interviewQuestionMapper.selectInterviewQuestionList(interviewQuestion);
    }

    /**
     * 新增面试问题
     * 
     * @param interviewQuestion 面试问题
     * @return 结果
     */
    @Override
    public int insertInterviewQuestion(InterviewQuestion interviewQuestion)
    {
        interviewQuestion.setCreateTime(DateUtils.getNowDate());
        return interviewQuestionMapper.insertInterviewQuestion(interviewQuestion);
    }

    /**
     * 修改面试问题
     * 
     * @param interviewQuestion 面试问题
     * @return 结果
     */
    @Override
    public int updateInterviewQuestion(InterviewQuestion interviewQuestion)
    {
        return interviewQuestionMapper.updateInterviewQuestion(interviewQuestion);
    }

    /**
     * 批量删除面试问题
     * 
     * @param ids 需要删除的面试问题主键
     * @return 结果
     */
    @Override
    public int deleteInterviewQuestionByIds(Long[] ids)
    {
        return interviewQuestionMapper.deleteInterviewQuestionByIds(ids);
    }

    /**
     * 删除面试问题信息
     * 
     * @param id 面试问题主键
     * @return 结果
     */
    @Override
    public int deleteInterviewQuestionById(Long id)
    {
        return interviewQuestionMapper.deleteInterviewQuestionById(id);
    }

    @Override
    public List<InterviewQuestion> selectInterviewQuestionReportId(long reportId) {
        return interviewQuestionMapper.selectInterviewQuestionReportId(reportId);

    }
}
