package com.ruoyi.interview.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 模拟面试记录对象 interview_record
 * 
 * @author 李坤
 * @date 2026-06-25
 */
@Data
public class InterviewRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 记录ID */
    private Long id;
    private String name;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 岗位ID(系统预设) */
    @Excel(name = "岗位ID(系统预设)")
    private Long positionId;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 持续时间(秒) */
    @Excel(name = "持续时间(秒)")
    private Long duration;

    /** 状态(0:进行中,1:已完成,2:已取消) */
    @Excel(name = "状态(0:进行中,1:已完成,2:已取消)")
    private String status;



}
