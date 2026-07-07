package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.EduClass;

/**
 * 班级信息Mapper接口
 * 
 * @author 李坤
 * @date 2026-06-23
 */
public interface EduClassMapper 
{
    /**
     * 查询班级信息
     * 
     * @param id 班级信息主键
     * @return 班级信息
     */
    public EduClass selectEduClassById(Long id);

    /**
     * 查询班级信息列表
     * 
     * @param eduClass 班级信息
     * @return 班级信息集合
     */
    public List<EduClass> selectEduClassList(EduClass eduClass);

    /**
     * 新增班级信息
     * 
     * @param eduClass 班级信息
     * @return 结果
     */
    public int insertEduClass(EduClass eduClass);

    /**
     * 修改班级信息
     * 
     * @param eduClass 班级信息
     * @return 结果
     */
    public int updateEduClass(EduClass eduClass);

    /**
     * 删除班级信息
     * 
     * @param id 班级信息主键
     * @return 结果
     */
    public int deleteEduClassById(Long id);

    /**
     * 批量删除班级信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEduClassByIds(Long[] ids);
}
