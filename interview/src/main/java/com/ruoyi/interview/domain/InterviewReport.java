package com.ruoyi.interview.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 面试报告对象 interview_report
 * 
 * @author 李坤
 * @date 2026-06-25
 */
public class InterviewReport extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 报告ID */
    private Long id;

    /** 关联记录ID */
    @Excel(name = "关联记录ID")
    private Long recordId;

    /** 综合评分 */
    @Excel(name = "综合评分")
    private BigDecimal overallScore;

    /** 综合评价 */
    @Excel(name = "综合评价")
    private String overallEvaluation;

    /** 优势分析 */
    @Excel(name = "优势分析")
    private String strengths;

    /** 不足分析 */
    @Excel(name = "不足分析")
    private String weaknesses;

    /** 改进建议 */
    @Excel(name = "改进建议")
    private String suggestions;

    /** 生成时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date generateTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setRecordId(Long recordId) 
    {
        this.recordId = recordId;
    }

    public Long getRecordId() 
    {
        return recordId;
    }

    public void setOverallScore(BigDecimal overallScore) 
    {
        this.overallScore = overallScore;
    }

    public BigDecimal getOverallScore() 
    {
        return overallScore;
    }

    public void setOverallEvaluation(String overallEvaluation) 
    {
        this.overallEvaluation = overallEvaluation;
    }

    public String getOverallEvaluation() 
    {
        return overallEvaluation;
    }

    public void setStrengths(String strengths) 
    {
        this.strengths = strengths;
    }

    public String getStrengths() 
    {
        return strengths;
    }

    public void setWeaknesses(String weaknesses) 
    {
        this.weaknesses = weaknesses;
    }

    public String getWeaknesses() 
    {
        return weaknesses;
    }

    public void setSuggestions(String suggestions) 
    {
        this.suggestions = suggestions;
    }

    public String getSuggestions() 
    {
        return suggestions;
    }

    public void setGenerateTime(Date generateTime) 
    {
        this.generateTime = generateTime;
    }

    public Date getGenerateTime() 
    {
        return generateTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("recordId", getRecordId())
            .append("overallScore", getOverallScore())
            .append("overallEvaluation", getOverallEvaluation())
            .append("strengths", getStrengths())
            .append("weaknesses", getWeaknesses())
            .append("suggestions", getSuggestions())
            .append("generateTime", getGenerateTime())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
