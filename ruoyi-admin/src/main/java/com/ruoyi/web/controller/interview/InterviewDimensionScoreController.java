package com.ruoyi.web.controller.interview;

import java.util.List;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.interview.domain.InterviewDimensionScore;
import com.ruoyi.interview.service.IInterviewDimensionScoreService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 维度评分Controller
 * 
 * @author 李坤
 * @date 2026-06-24
 */
@RestController
@RequestMapping("/interview/score")
public class InterviewDimensionScoreController extends BaseController
{
    @Autowired
    private IInterviewDimensionScoreService interviewDimensionScoreService;

    /**
     * 查询维度评分列表
     */
    @PreAuthorize("@ss.hasPermi('interview:score:list')")
    @GetMapping("/list")
    public TableDataInfo list(InterviewDimensionScore interviewDimensionScore)
    {
        startPage();
        List<InterviewDimensionScore> list = interviewDimensionScoreService.selectInterviewDimensionScoreList(interviewDimensionScore);
        return getDataTable(list);
    }

    /**
     * 导出维度评分列表
     */
    @PreAuthorize("@ss.hasPermi('interview:score:export')")
    @Log(title = "维度评分", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, InterviewDimensionScore interviewDimensionScore)
    {
        List<InterviewDimensionScore> list = interviewDimensionScoreService.selectInterviewDimensionScoreList(interviewDimensionScore);
        ExcelUtil<InterviewDimensionScore> util = new ExcelUtil<InterviewDimensionScore>(InterviewDimensionScore.class);
        util.exportExcel(response, list, "维度评分数据");
    }

    /**
     * 获取维度评分详细信息
     */
    @PreAuthorize("@ss.hasPermi('interview:score:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(interviewDimensionScoreService.selectInterviewDimensionScoreById(id));
    }

    /**
     * 新增维度评分
     */
    @PreAuthorize("@ss.hasPermi('interview:score:add')")
    @Log(title = "维度评分", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody InterviewDimensionScore interviewDimensionScore)
    {
        return toAjax(interviewDimensionScoreService.insertInterviewDimensionScore(interviewDimensionScore));
    }

    /**
     * 修改维度评分
     */
    @PreAuthorize("@ss.hasPermi('interview:score:edit')")
    @Log(title = "维度评分", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody InterviewDimensionScore interviewDimensionScore)
    {
        return toAjax(interviewDimensionScoreService.updateInterviewDimensionScore(interviewDimensionScore));
    }

    /**
     * 删除维度评分
     */
    @PreAuthorize("@ss.hasPermi('interview:score:remove')")
    @Log(title = "维度评分", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(interviewDimensionScoreService.deleteInterviewDimensionScoreByIds(ids));
    }
}
