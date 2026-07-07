package com.ruoyi.interview.service;

import java.util.List;
import com.ruoyi.interview.domain.InterviewReport;

/**
 * 面试报告Service接口
 * 
 * @author 李坤
 * @date 2026-06-25
 */
public interface IInterviewReportService 
{
    /**
     * 查询面试报告
     * 
     * @param id 面试报告主键
     * @return 面试报告
     */
    public InterviewReport selectInterviewReportById(Long id);

    /**
     * 查询面试报告列表
     * 
     * @param interviewReport 面试报告
     * @return 面试报告集合
     */
    public List<InterviewReport> selectInterviewReportList(InterviewReport interviewReport);

    /**
     * 新增面试报告
     * 
     * @param interviewReport 面试报告
     * @return 结果
     */
    public int insertInterviewReport(InterviewReport interviewReport);

    /**
     * 修改面试报告
     * 
     * @param interviewReport 面试报告
     * @return 结果
     */
    public int updateInterviewReport(InterviewReport interviewReport);

    /**
     * 批量删除面试报告
     * 
     * @param ids 需要删除的面试报告主键集合
     * @return 结果
     */
    public int deleteInterviewReportByIds(Long[] ids);

    /**
     * 删除面试报告信息
     * 
     * @param id 面试报告主键
     * @return 结果
     */
    public int deleteInterviewReportById(Long id);
}
