package com.ruoyi.interview.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.interview.mapper.InterviewReportMapper;
import com.ruoyi.interview.domain.InterviewReport;
import com.ruoyi.interview.service.IInterviewReportService;

/**
 * 面试报告Service业务层处理
 * 
 * @author 李坤
 * @date 2026-06-25
 */
@Service
public class InterviewReportServiceImpl implements IInterviewReportService 
{
    @Autowired
    private InterviewReportMapper interviewReportMapper;

    /**
     * 查询面试报告
     * 
     * @param id 面试报告主键
     * @return 面试报告
     */
    @Override
    public InterviewReport selectInterviewReportById(Long id)
    {
        return interviewReportMapper.selectInterviewReportById(id);
    }

    /**
     * 查询面试报告列表
     * 
     * @param interviewReport 面试报告
     * @return 面试报告
     */
    @Override
    public List<InterviewReport> selectInterviewReportList(InterviewReport interviewReport)
    {
        return interviewReportMapper.selectInterviewReportList(interviewReport);
    }

    /**
     * 新增面试报告
     * 
     * @param interviewReport 面试报告
     * @return 结果
     */
    @Override
    public int insertInterviewReport(InterviewReport interviewReport)
    {
        interviewReport.setCreateTime(DateUtils.getNowDate());
        return interviewReportMapper.insertInterviewReport(interviewReport);
    }

    /**
     * 修改面试报告
     * 
     * @param interviewReport 面试报告
     * @return 结果
     */
    @Override
    public int updateInterviewReport(InterviewReport interviewReport)
    {
        interviewReport.setUpdateTime(DateUtils.getNowDate());
        return interviewReportMapper.updateInterviewReport(interviewReport);
    }

    /**
     * 批量删除面试报告
     * 
     * @param ids 需要删除的面试报告主键
     * @return 结果
     */
    @Override
    public int deleteInterviewReportByIds(Long[] ids)
    {
        return interviewReportMapper.deleteInterviewReportByIds(ids);
    }

    /**
     * 删除面试报告信息
     * 
     * @param id 面试报告主键
     * @return 结果
     */
    @Override
    public int deleteInterviewReportById(Long id)
    {
        return interviewReportMapper.deleteInterviewReportById(id);
    }
}
