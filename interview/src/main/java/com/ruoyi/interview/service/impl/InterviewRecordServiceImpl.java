package com.ruoyi.interview.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.interview.mapper.InterviewRecordMapper;
import com.ruoyi.interview.domain.InterviewRecord;
import com.ruoyi.interview.service.IInterviewRecordService;

/**
 * 模拟面试记录Service业务层处理
 * 
 * @author 李坤
 * @date 2026-06-25
 */
@Service
public class InterviewRecordServiceImpl implements IInterviewRecordService 
{
    @Autowired
    private InterviewRecordMapper interviewRecordMapper;

    /**
     * 查询模拟面试记录
     * 
     * @param id 模拟面试记录主键
     * @return 模拟面试记录
     */
    @Override
    public InterviewRecord selectInterviewRecordById(Long id)
    {
        return interviewRecordMapper.selectInterviewRecordById(id);
    }

    /**
     * 查询模拟面试记录列表
     * 
     * @param interviewRecord 模拟面试记录
     * @return 模拟面试记录
     */
    @Override
    public List<InterviewRecord> selectInterviewRecordList(InterviewRecord interviewRecord)
    {
        return interviewRecordMapper.selectInterviewRecordList(interviewRecord);
    }

    /**
     * 新增模拟面试记录
     * 
     * @param interviewRecord 模拟面试记录
     * @return 结果
     */
    @Override
    public int insertInterviewRecord(InterviewRecord interviewRecord)
    {
        interviewRecord.setCreateTime(DateUtils.getNowDate());
        return interviewRecordMapper.insertInterviewRecord(interviewRecord);
    }

    /**
     * 修改模拟面试记录
     * 
     * @param interviewRecord 模拟面试记录
     * @return 结果
     */
    @Override
    public int updateInterviewRecord(InterviewRecord interviewRecord)
    {
        interviewRecord.setUpdateTime(DateUtils.getNowDate());
        return interviewRecordMapper.updateInterviewRecord(interviewRecord);
    }

    /**
     * 批量删除模拟面试记录
     * 
     * @param ids 需要删除的模拟面试记录主键
     * @return 结果
     */
    @Override
    public int deleteInterviewRecordByIds(Long[] ids)
    {
        return interviewRecordMapper.deleteInterviewRecordByIds(ids);
    }

    /**
     * 删除模拟面试记录信息
     * 
     * @param id 模拟面试记录主键
     * @return 结果
     */
    @Override
    public int deleteInterviewRecordById(Long id)
    {
        return interviewRecordMapper.deleteInterviewRecordById(id);
    }

    @Override
    public List<InterviewRecord> selectInterviewRecordByUserId(Long userId)
    {
        return interviewRecordMapper.selectInterviewRecordByUserId(userId);
    }
}
