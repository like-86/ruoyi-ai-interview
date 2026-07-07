package com.ruoyi.interview.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 面试问题对象 interview_question
 * 
 * @author 李坤
 * @date 2026-06-25
 */
public class InterviewQuestion extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 问题ID */
    private Long id;

    /** 关联记录ID */
    @Excel(name = "关联记录ID")
    private Long recordId;

    /** 问题序号 */
    @Excel(name = "问题序号")
    private Long questionNo;

    /** 问题内容 */
    @Excel(name = "问题内容")
    private String content;

    /** 问题提示信息 */
    @Excel(name = "问题提示信息")
    private String questionPrompt;

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

    public void setQuestionNo(Long questionNo) 
    {
        this.questionNo = questionNo;
    }

    public Long getQuestionNo() 
    {
        return questionNo;
    }

    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }

    public void setQuestionPrompt(String questionPrompt) 
    {
        this.questionPrompt = questionPrompt;
    }

    public String getQuestionPrompt() 
    {
        return questionPrompt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("recordId", getRecordId())
            .append("questionNo", getQuestionNo())
            .append("content", getContent())
            .append("questionPrompt", getQuestionPrompt())
            .append("createTime", getCreateTime())
            .toString();
    }
}
