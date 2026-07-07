package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.EduStudentMapper;
import com.ruoyi.system.domain.EduStudent;
import com.ruoyi.system.service.IEduStudentService;

/**
 * 学生信息Service业务层处理
 * 
 * @author 李坤
 * @date 2026-06-24
 */
@Service
public class EduStudentServiceImpl implements IEduStudentService 
{
    @Autowired
    private EduStudentMapper eduStudentMapper;

    /**
     * 查询学生信息
     * 
     * @param id 学生信息主键
     * @return 学生信息
     */
    @Override
    public EduStudent selectEduStudentById(Long id)
    {
        return eduStudentMapper.selectEduStudentById(id);
    }

    /**
     * 查询学生信息列表
     * 
     * @param eduStudent 学生信息
     * @return 学生信息
     */
    @Override
    public List<EduStudent> selectEduStudentList(EduStudent eduStudent)
    {
        return eduStudentMapper.selectEduStudentList(eduStudent);
    }

    /**
     * 新增学生信息
     * 
     * @param eduStudent 学生信息
     * @return 结果
     */
    @Override
    public int insertEduStudent(EduStudent eduStudent)
    {
        eduStudent.setCreateTime(DateUtils.getNowDate());
        return eduStudentMapper.insertEduStudent(eduStudent);
    }

    /**
     * 修改学生信息
     * 
     * @param eduStudent 学生信息
     * @return 结果
     */
    @Override
    public int updateEduStudent(EduStudent eduStudent)
    {
        eduStudent.setUpdateTime(DateUtils.getNowDate());
        return eduStudentMapper.updateEduStudent(eduStudent);
    }

    /**
     * 批量删除学生信息
     * 
     * @param ids 需要删除的学生信息主键
     * @return 结果
     */
    @Override
    public int deleteEduStudentByIds(Long[] ids)
    {
        return eduStudentMapper.deleteEduStudentByIds(ids);
    }

    /**
     * 删除学生信息信息
     * 
     * @param id 学生信息主键
     * @return 结果
     */
    @Override
    public int deleteEduStudentById(Long id)
    {
        return eduStudentMapper.deleteEduStudentById(id);
    }
}
