package com.ruoyi.web.controller.interview;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.interview.domain.*;
import com.ruoyi.interview.domain.po.GenReport;
import com.ruoyi.interview.service.*;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ai")
public class DSAIController {

    private ChatClient chatClient;

    @Autowired
    private IInterviewPositionService positionService;

    @Autowired
    private IInterviewDimensionService dimensionService;
    @Autowired
    private IInterviewRecordService recordService;
    @Autowired
    private IInterviewQuestionService questionService;
    @Autowired
    private IInterviewAnswerService answerService;
    @Autowired
        private IInterviewReportService reportService;

    public DSAIController(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    //提供一个功能 用户传入一个岗位 我们要生产对应的面试题
    @GetMapping("/genInterview")
    public AjaxResult genInterview(@RequestParam("positionId") Long positionId) {
        //1.接受岗位id positionId
        InterviewPosition interviewPosition = positionService.selectInterviewPositionById(positionId);
        //2.根据岗位id 查询出对应的 维度信息
        List<InterviewDimension> dimensionList = dimensionService.selectInterviewDimensionByPositionId(positionId);

        Collections.shuffle(dimensionList);
        List<InterviewDimension> newDimensionList = dimensionList.subList(0, Math.min(5, dimensionList.size()));

        //3.拼接维度信息
        String dimensionString = newDimensionList.stream().map( dimension -> {
            return dimension.getName() + ":" + dimension.getDescription() + " 权重(" + dimension.getWeight() + ")";
        }).collect(Collectors.joining(","));

        System.out.println(dimensionString);
        //4.将维度信息传给AI进行对应的面试题生成
        SystemPromptTemplate systemPromptTemplate = new SystemPromptTemplate(STR);
        Message message = systemPromptTemplate.createMessage(Map.of("positionName", interviewPosition.getName(), "number", 5, "dimensions", dimensionString));
        String content = chatClient.prompt().messages(message).call().content();
        BeanOutputConverter<List<AIResultData>> converter =
                new BeanOutputConverter<>(new ParameterizedTypeReference<List<AIResultData>>() {});
        List<AIResultData> convert = converter.convert(content);

        //5.保存面试记录
        InterviewRecord interviewRecord = buildRecord(positionId);
        //6.保存问题记录
        saveQuestion(convert, interviewRecord);
        return AjaxResult.success(interviewRecord.getId());
    }

    @GetMapping("/genReport/{recordId}")
    public AjaxResult genReport(@PathVariable("recordId") Long recordId){
        List<InterviewQuestion> list = questionService.selectInterviewQuestionReportId(recordId);
        //问题记录的id
        List<Long> questionIds = list.stream().map(InterviewQuestion::getId).toList();
        //用户回答的记录
        List<InterviewAnswer> answerList =answerService.selectInterviewAnswerByQuestionIds(questionIds);

        //构建问题和答案
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            InterviewQuestion interviewQuestion = list.get(i);
            sb.append("问题"+(i+1)+"："+interviewQuestion.getContent());
            sb.append("/n");
            sb.append("回答"+(i+1)+"："+answerList.get(i).getContent());
            sb.append("/n");
        }
        String string = sb.toString();
        //完整的提示词
        SystemPromptTemplate systemPromptTemplate = new SystemPromptTemplate(REPORT_STR);
        Message message  = systemPromptTemplate.createMessage(Map.of("problem",string));
        System.out.println(message.getText());


        //调用AI生成对应的报告
        GenReport entity = chatClient.prompt().messages(message).call().entity(GenReport.class);
        InterviewReport interviewReport = new InterviewReport();
        interviewReport.setRecordId(recordId);
        interviewReport.setGenerateTime(new Date());
        interviewReport.setStrengths(entity.getStrengths());
        interviewReport.setWeaknesses(entity.getWeaknesses());
        interviewReport.setSuggestions(entity.getSuggestions());
        interviewReport.setOverallEvaluation(entity.getOverallEvaluation());
        interviewReport.setOverallScore(entity.getOverallScore());
        reportService.insertInterviewReport(interviewReport);


        return  AjaxResult.success();

    }

    private void saveQuestion(List<AIResultData> convert, InterviewRecord interviewRecord) {
        for (AIResultData aiResultData : convert) {
            InterviewQuestion interviewQuestion = new InterviewQuestion();
            interviewQuestion.setRecordId(interviewRecord.getId());
            interviewQuestion.setQuestionNo(aiResultData.getQuestionNo());
            interviewQuestion.setContent(aiResultData.getText());
            interviewQuestion.setQuestionPrompt(aiResultData.getSampleAnswer());
            interviewQuestion.setCreateTime(new Date());
            questionService.insertInterviewQuestion(interviewQuestion);
        }
    }

    private InterviewRecord buildRecord(Long positionId) {
        InterviewRecord interviewRecord = new InterviewRecord();

        interviewRecord.setStatus("0");
        interviewRecord.setPositionId(positionId);
        //userId当前登陆用户  ruoyi有个工具类 SecurityUtils
        interviewRecord.setUserId(SecurityUtils.getUserId());
        recordService.insertInterviewRecord(interviewRecord);
        return interviewRecord;
    }

    public static final String REPORT_STR = """
    你是一位专业的IT技术面试评估专家，需要根据候选人的面试问题和回答内容，从不同维度进行评估并生成详细的面试报告。请按照以下要求进行分析：
    
    ### 评估维度
    - 技术能力(权重40%)：评估Java专业知识、框架使用、系统设计等能力
    - 编码实践(权重30%)：评估代码质量、设计模式、性能优化等实践能力
    - 问题解决(权重20%)：评估分析问题和解决问题的能力
    - 沟通表达(权重10%)：评估技术表达清晰度和沟通能力
    
    {problem}
    
    ### 评估要求
    1. 对每个维度进行评分(0-100分)，并给出具体评价
    2. 生成综合评分和总体评价
    3. 分析候选人的优势和不足
    4. 提供针对性的改进建议
    5. 保持评价专业、客观且有建设性
    
    ### 输出格式
    请严格按照以下JSON格式输出评估结果：
    
    {{
      "overall_score": 综合评分,
      "overall_evaluation": "总体评价",
      "dimension_scores": [
        {{
          "dimension_id": 维度ID,
          "score": 维度得分,
          "evaluation": "维度评价"
        }}
      ],
      "strengths": "优势分析",
      "weaknesses": "不足分析",
      "suggestions": "改进建议"
    }}
    """;
    public static final String STR = """
                        根据职位名称{positionName}，生成{number}个面试问题，考核维度包括{dimensions}
                        请根据以下参数生成模拟面试问题，要求严格遵循指定的JSON格式输出：
                          [
                            {{
                              "questionNo":"问题的顺序 从1开始",
                              "dimension": "所属维度",
                              "type": "问题类型(行为/技术/情景等)",
                              "language": "中文",
                              "text": "问题文本",
                              "difficulty": "难度等级(1-5)",
                              "sampleAnswer": "参考答案要点"
                            }}
                          ]
                        生成要求：
                        1. 按指定维度及权重分布问题数量
                        2. 问题类型应包含行为面试题、技术/专业题、情景模拟题等
                        3. 难度应呈正态分布
                        4. 结果为JSON数组格式的字符串，不需要以markdown的方式生成，直接生成内容即可,去掉最外层的{{}}
                        5. 参考答案只需提供要点
                        6. 需要注意text和sampleAnswer的值如果有双引号需要转义
                        """;
}