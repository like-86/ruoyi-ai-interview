package com.ruoyi.interview.service;

import java.util.List;
import com.ruoyi.interview.domain.InterviewRecord;

/**
 * 模拟面试记录Service接口
 * 
 * @author 李坤
 * @date 2026-06-25
 */
public interface IInterviewRecordService 
{
    /**
     * 查询模拟面试记录
     * 
     * @param id 模拟面试记录主键
     * @return 模拟面试记录
     */
    public InterviewRecord selectInterviewRecordById(Long id);

    /**
     * 查询模拟面试记录列表
     * 
     * @param interviewRecord 模拟面试记录
     * @return 模拟面试记录集合
     */
    public List<InterviewRecord> selectInterviewRecordList(InterviewRecord interviewRecord);

    /**
     * 新增模拟面试记录
     * 
     * @param interviewRecord 模拟面试记录
     * @return 结果
     */
    public int insertInterviewRecord(InterviewRecord interviewRecord);

    /**
     * 修改模拟面试记录
     * 
     * @param interviewRecord 模拟面试记录
     * @return 结果
     */
    public int updateInterviewRecord(InterviewRecord interviewRecord);

    /**
     * 批量删除模拟面试记录
     * 
     * @param ids 需要删除的模拟面试记录主键集合
     * @return 结果
     */
    public int deleteInterviewRecordByIds(Long[] ids);

    /**
     * 删除模拟面试记录信息
     * 
     * @param id 模拟面试记录主键
     * @return 结果
     */
    public int deleteInterviewRecordById(Long id);




    List<InterviewRecord> selectInterviewRecordByUserId(Long userId);

}
