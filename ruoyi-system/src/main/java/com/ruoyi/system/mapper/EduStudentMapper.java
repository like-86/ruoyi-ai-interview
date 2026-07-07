package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.EduStudent;

/**
 * 学生信息Mapper接口
 * 
 * @author 李坤
 * @date 2026-06-24
 */
public interface EduStudentMapper 
{
    /**
     * 查询学生信息
     * 
     * @param id 学生信息主键
     * @return 学生信息
     */
    public EduStudent selectEduStudentById(Long id);

    /**
     * 查询学生信息列表
     * 
     * @param eduStudent 学生信息
     * @return 学生信息集合
     */
    public List<EduStudent> selectEduStudentList(EduStudent eduStudent);

    /**
     * 新增学生信息
     * 
     * @param eduStudent 学生信息
     * @return 结果
     */
    public int insertEduStudent(EduStudent eduStudent);

    /**
     * 修改学生信息
     * 
     * @param eduStudent 学生信息
     * @return 结果
     */
    public int updateEduStudent(EduStudent eduStudent);

    /**
     * 删除学生信息
     * 
     * @param id 学生信息主键
     * @return 结果
     */
    public int deleteEduStudentById(Long id);

    /**
     * 批量删除学生信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEduStudentByIds(Long[] ids);
}
