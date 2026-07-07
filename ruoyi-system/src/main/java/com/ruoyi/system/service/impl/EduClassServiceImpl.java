package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.EduClassMapper;
import com.ruoyi.system.domain.EduClass;
import com.ruoyi.system.service.IEduClassService;

/**
 * 班级信息Service业务层处理
 * 
 * @author 李坤
 * @date 2026-06-23
 */
@Service
public class EduClassServiceImpl implements IEduClassService 
{
    @Autowired
    private EduClassMapper eduClassMapper;

    /**
     * 查询班级信息
     * 
     * @param id 班级信息主键
     * @return 班级信息
     */
    @Override
    public EduClass selectEduClassById(Long id)
    {
        return eduClassMapper.selectEduClassById(id);
    }

    /**
     * 查询班级信息列表
     * 
     * @param eduClass 班级信息
     * @return 班级信息
     */
    @Override
    public List<EduClass> selectEduClassList(EduClass eduClass)
    {
        return eduClassMapper.selectEduClassList(eduClass);
    }

    /**
     * 新增班级信息
     * 
     * @param eduClass 班级信息
     * @return 结果
     */
    @Override
    public int insertEduClass(EduClass eduClass)
    {
        eduClass.setCreateTime(DateUtils.getNowDate());
        return eduClassMapper.insertEduClass(eduClass);
    }

    /**
     * 修改班级信息
     * 
     * @param eduClass 班级信息
     * @return 结果
     */
    @Override
    public int updateEduClass(EduClass eduClass)
    {
        eduClass.setUpdateTime(DateUtils.getNowDate());
        return eduClassMapper.updateEduClass(eduClass);
    }

    /**
     * 批量删除班级信息
     * 
     * @param ids 需要删除的班级信息主键
     * @return 结果
     */
    @Override
    public int deleteEduClassByIds(Long[] ids)
    {
        return eduClassMapper.deleteEduClassByIds(ids);
    }

    /**
     * 删除班级信息信息
     * 
     * @param id 班级信息主键
     * @return 结果
     */
    @Override
    public int deleteEduClassById(Long id)
    {
        return eduClassMapper.deleteEduClassById(id);
    }
}
