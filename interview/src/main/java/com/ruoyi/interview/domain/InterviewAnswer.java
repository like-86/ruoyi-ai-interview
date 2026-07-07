package com.ruoyi.interview.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 面试回答对象 interview_answer
 * 
 * @author 李坤
 * @date 2026-06-25
 */
public class InterviewAnswer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 回答ID */
    private Long id;

    /** 关联问题ID */
    @Excel(name = "关联问题ID")
    private Long questionId;

    /** 回答内容 */
    @Excel(name = "回答内容")
    private String content;

    /** 语音回答URL */
    @Excel(name = "语音回答URL")
    private String audioUrl;

    /** 回答时长(秒) */
    @Excel(name = "回答时长(秒)")
    private Long duration;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setQuestionId(Long questionId) 
    {
        this.questionId = questionId;
    }

    public Long getQuestionId() 
    {
        return questionId;
    }

    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }

    public void setAudioUrl(String audioUrl) 
    {
        this.audioUrl = audioUrl;
    }

    public String getAudioUrl() 
    {
        return audioUrl;
    }

    public void setDuration(Long duration) 
    {
        this.duration = duration;
    }

    public Long getDuration() 
    {
        return duration;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("questionId", getQuestionId())
            .append("content", getContent())
            .append("audioUrl", getAudioUrl())
            .append("duration", getDuration())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
