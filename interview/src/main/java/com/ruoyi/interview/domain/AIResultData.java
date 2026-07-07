package com.ruoyi.interview.domain;

import lombok.Data;

@Data
public class AIResultData {
    private Long questionNo;
    private String dimension;
    private String type;
    private String language;
    private String text;
    private Integer difficulty;
    private String sampleAnswer;
}

