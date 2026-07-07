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
import com.ruoyi.interview.domain.InterviewDimension;
import com.ruoyi.interview.service.IInterviewDimensionService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 面试维度Controller
 * 
 * @author 李坤
 * @date 2026-06-24
 */
@RestController
@RequestMapping("/interview/dimension")
public class InterviewDimensionController extends BaseController
{
    @Autowired
    private IInterviewDimensionService interviewDimensionService;

    /**
     * 查询面试维度列表
     */
    @PreAuthorize("@ss.hasPermi('interview:dimension:list')")
    @GetMapping("/list")
    public TableDataInfo list(InterviewDimension interviewDimension)
    {
        startPage();
        List<InterviewDimension> list = interviewDimensionService.selectInterviewDimensionList(interviewDimension);
        return getDataTable(list);
    }

    /**
     * 导出面试维度列表
     */
    @PreAuthorize("@ss.hasPermi('interview:dimension:export')")
    @Log(title = "面试维度", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, InterviewDimension interviewDimension)
    {
        List<InterviewDimension> list = interviewDimensionService.selectInterviewDimensionList(interviewDimension);
        ExcelUtil<InterviewDimension> util = new ExcelUtil<InterviewDimension>(InterviewDimension.class);
        util.exportExcel(response, list, "面试维度数据");
    }

    /**
     * 获取面试维度详细信息
     */
    @PreAuthorize("@ss.hasPermi('interview:dimension:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(interviewDimensionService.selectInterviewDimensionById(id));
    }

    /**
     * 新增面试维度
     */
    @PreAuthorize("@ss.hasPermi('interview:dimension:add')")
    @Log(title = "面试维度", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody InterviewDimension interviewDimension)
    {
        return toAjax(interviewDimensionService.insertInterviewDimension(interviewDimension));
    }

    /**
     * 修改面试维度
     */
    @PreAuthorize("@ss.hasPermi('interview:dimension:edit')")
    @Log(title = "面试维度", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody InterviewDimension interviewDimension)
    {
        return toAjax(interviewDimensionService.updateInterviewDimension(interviewDimension));
    }

    /**
     * 删除面试维度
     */
    @PreAuthorize("@ss.hasPermi('interview:dimension:remove')")
    @Log(title = "面试维度", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(interviewDimensionService.deleteInterviewDimensionByIds(ids));
    }
}
