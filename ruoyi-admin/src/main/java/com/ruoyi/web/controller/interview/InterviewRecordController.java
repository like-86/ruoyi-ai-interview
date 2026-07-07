package com.ruoyi.web.controller.interview;

import java.util.Date;
import java.util.List;

import com.ruoyi.common.utils.SecurityUtils;
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
import com.ruoyi.interview.domain.InterviewRecord;
import com.ruoyi.interview.service.IInterviewRecordService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 模拟面试记录Controller
 * 
 * @author 李坤
 * @date 2026-06-25
 */
@RestController
@RequestMapping("/interview/record")
public class InterviewRecordController extends BaseController
{
    @Autowired
    private IInterviewRecordService interviewRecordService;
    //开始时间接口
    @GetMapping("/start/{recordId}")
    public AjaxResult updateStartTime(@PathVariable("recordId")long recordId )
    {
        InterviewRecord interviewRecord = interviewRecordService.selectInterviewRecordById(recordId);
        interviewRecord.setStartTime(new Date());
        interviewRecordService.updateInterviewRecord(interviewRecord);
        return AjaxResult.success();

    }
    //结束时间接口
    @GetMapping("/end/{recordId}")
    public AjaxResult updateEndTime(@PathVariable("recordId")long recordId )
    {
        InterviewRecord interviewRecord = interviewRecordService.selectInterviewRecordById(recordId);
        interviewRecord.setEndTime(new Date());
        interviewRecord.setStatus("1");
        interviewRecordService.updateInterviewRecord(interviewRecord);
        return AjaxResult.success();

    }

    @GetMapping("/listAll")
    public AjaxResult listAll()
    {

        Long userId = SecurityUtils.getUserId();
        List<InterviewRecord> list = interviewRecordService.selectInterviewRecordByUserId(userId);
        return AjaxResult.success(list);
    }

    /**
     * 查询模拟面试记录列表
     */
    @PreAuthorize("@ss.hasPermi('interview:record:list')")
    @GetMapping("/list")
    public TableDataInfo list(InterviewRecord interviewRecord)
    {
        startPage();
        List<InterviewRecord> list = interviewRecordService.selectInterviewRecordList(interviewRecord);
        return getDataTable(list);
    }

    /**
     * 导出模拟面试记录列表
     */
    @PreAuthorize("@ss.hasPermi('interview:record:export')")
    @Log(title = "模拟面试记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, InterviewRecord interviewRecord)
    {
        List<InterviewRecord> list = interviewRecordService.selectInterviewRecordList(interviewRecord);
        ExcelUtil<InterviewRecord> util = new ExcelUtil<InterviewRecord>(InterviewRecord.class);
        util.exportExcel(response, list, "模拟面试记录数据");
    }

    /**
     * 获取模拟面试记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('interview:record:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(interviewRecordService.selectInterviewRecordById(id));
    }

    /**
     * 新增模拟面试记录
     */
    @PreAuthorize("@ss.hasPermi('interview:record:add')")
    @Log(title = "模拟面试记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody InterviewRecord interviewRecord)
    {
        return toAjax(interviewRecordService.insertInterviewRecord(interviewRecord));
    }

    /**
     * 修改模拟面试记录
     */
    @PreAuthorize("@ss.hasPermi('interview:record:edit')")
    @Log(title = "模拟面试记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody InterviewRecord interviewRecord)
    {
        return toAjax(interviewRecordService.updateInterviewRecord(interviewRecord));
    }

    /**
     * 删除模拟面试记录
     */
    @PreAuthorize("@ss.hasPermi('interview:record:remove')")
    @Log(title = "模拟面试记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(interviewRecordService.deleteInterviewRecordByIds(ids));
    }
}
