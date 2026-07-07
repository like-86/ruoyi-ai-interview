package com.ruoyi.interview.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 维度评分对象 interview_dimension_score
 * 
 * @author 李坤
 * @date 2026-06-24
 */
public class InterviewDimensionScore extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 关联报告ID */
    @Excel(name = "关联报告ID")
    private Long reportId;

    /** 关联维度ID */
    @Excel(name = "关联维度ID")
    private Long dimensionId;

    /** 维度得分 */
    @Excel(name = "维度得分")
    private BigDecimal score;

    /** 维度评价 */
    @Excel(name = "维度评价")
    private String evaluation;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setReportId(Long reportId) 
    {
        this.reportId = reportId;
    }

    public Long getReportId() 
    {
        return reportId;
    }

    public void setDimensionId(Long dimensionId) 
    {
        this.dimensionId = dimensionId;
    }

    public Long getDimensionId() 
    {
        return dimensionId;
    }

    public void setScore(BigDecimal score) 
    {
        this.score = score;
    }

    public BigDecimal getScore() 
    {
        return score;
    }

    public void setEvaluation(String evaluation) 
    {
        this.evaluation = evaluation;
    }

    public String getEvaluation() 
    {
        return evaluation;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("reportId", getReportId())
            .append("dimensionId", getDimensionId())
            .append("score", getScore())
            .append("evaluation", getEvaluation())
            .append("createTime", getCreateTime())
            .toString();
    }
}
