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
import com.ruoyi.interview.domain.InterviewAnswer;
import com.ruoyi.interview.service.IInterviewAnswerService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 面试回答Controller
 * 
 * @author 李坤
 * @date 2026-06-25
 */
@RestController
@RequestMapping("/interview/answer")
public class InterviewAnswerController extends BaseController
{
    @Autowired
    private IInterviewAnswerService interviewAnswerService;

    /**
     * 查询面试回答列表
     */
    @PreAuthorize("@ss.hasPermi('interview:answer:list')")
    @GetMapping("/list")
    public TableDataInfo list(InterviewAnswer interviewAnswer)
    {
        startPage();
        List<InterviewAnswer> list = interviewAnswerService.selectInterviewAnswerList(interviewAnswer);
        return getDataTable(list);
    }

    /**
     * 导出面试回答列表
     */
    @PreAuthorize("@ss.hasPermi('interview:answer:export')")
    @Log(title = "面试回答", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, InterviewAnswer interviewAnswer)
    {
        List<InterviewAnswer> list = interviewAnswerService.selectInterviewAnswerList(interviewAnswer);
        ExcelUtil<InterviewAnswer> util = new ExcelUtil<InterviewAnswer>(InterviewAnswer.class);
        util.exportExcel(response, list, "面试回答数据");
    }

    /**
     * 获取面试回答详细信息
     */
    @PreAuthorize("@ss.hasPermi('interview:answer:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(interviewAnswerService.selectInterviewAnswerById(id));
    }

    /**
     * 新增面试回答
    */
//    @PreAuthorize("@ss.hasPermi('interview:answer:add')")
    @Log(title = "面试回答", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody InterviewAnswer interviewAnswer)
    {
        return toAjax(interviewAnswerService.insertInterviewAnswer(interviewAnswer));
    }

    /**
     * 修改面试回答
     */
    @PreAuthorize("@ss.hasPermi('interview:answer:edit')")
    @Log(title = "面试回答", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody InterviewAnswer interviewAnswer)
    {
        return toAjax(interviewAnswerService.updateInterviewAnswer(interviewAnswer));
    }

    /**
     * 删除面试回答
     */
    @PreAuthorize("@ss.hasPermi('interview:answer:remove')")
    @Log(title = "面试回答", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(interviewAnswerService.deleteInterviewAnswerByIds(ids));
    }
}
