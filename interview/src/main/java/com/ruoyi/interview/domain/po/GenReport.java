package com.ruoyi.interview.domain.po;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GenReport {
    /** 综合评分 */
    private BigDecimal overallScore;

    /** 综合评价 */
    private String overallEvaluation;

    /** 优势分析 */
    private String strengths;

    /** 不足分析 */
    private String weaknesses;

    /** 改进建议 */
    private String suggestions;
}