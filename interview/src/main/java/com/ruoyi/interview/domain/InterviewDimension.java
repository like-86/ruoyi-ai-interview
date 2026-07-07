package com.ruoyi.interview.domain;

import java.math.BigDecimal;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 面试维度对象 interview_dimension
 * 
 * @author 李坤
 * @date 2026-06-24
 */
@Data
public class InterviewDimension extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 维度ID */
    private Long id;

    /** 关联岗位ID */
    @Excel(name = "关联岗位ID")
    private Long positionId;
    /** 岗位名称 */
    @Excel(name = "岗位名称")
    private String positionName;

    /** 维度名称 */
    @Excel(name = "维度名称")
    private String name;

    /** 维度描述 */
    @Excel(name = "维度描述")
    private String description;

    /** 权重(0-1) */
    @Excel(name = "权重(0-1)")
    private BigDecimal weight;

    /** 显示顺序 */
    @Excel(name = "显示顺序")
    private Long sort;

    /** 状态(0:正常,1:停用) */
    @Excel(name = "状态(0:正常,1:停用)")
    private String status;


}
