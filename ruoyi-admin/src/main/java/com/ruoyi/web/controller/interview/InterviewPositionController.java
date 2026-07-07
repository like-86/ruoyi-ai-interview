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
import com.ruoyi.interview.domain.InterviewPosition;
import com.ruoyi.interview.service.IInterviewPositionService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 岗位Controller
 * 
 * @author 李坤
 * @date 2026-06-24
 */
@RestController
@RequestMapping("/interview/position")
public class InterviewPositionController extends BaseController
{
    @Autowired
    private IInterviewPositionService interviewPositionService;

    //    @PreAuthorize("@ss.hasPermi('interview:position:list')")
        @GetMapping("/listAll")
        public AjaxResult listAll()
        {
            List<InterviewPosition> list = interviewPositionService.selectInterviewPositionList(null);
            return AjaxResult.success(list);
        }

    /**
     * 查询岗位列表
     */
    @PreAuthorize("@ss.hasPermi('interview:position:list')")
    @GetMapping("/list")
    public TableDataInfo list(InterviewPosition interviewPosition)
    {
        startPage();
        List<InterviewPosition> list = interviewPositionService.selectInterviewPositionList(interviewPosition);
        return getDataTable(list);
    }

    /**
     * 导出岗位列表
     */
    @PreAuthorize("@ss.hasPermi('interview:position:export')")
    @Log(title = "岗位", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, InterviewPosition interviewPosition)
    {
        List<InterviewPosition> list = interviewPositionService.selectInterviewPositionList(interviewPosition);
        ExcelUtil<InterviewPosition> util = new ExcelUtil<InterviewPosition>(InterviewPosition.class);
        util.exportExcel(response, list, "岗位数据");
    }

    /**
     * 获取岗位详细信息
     */
    @PreAuthorize("@ss.hasPermi('interview:position:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(interviewPositionService.selectInterviewPositionById(id));
    }

    /**
     * 新增岗位
     */
    @PreAuthorize("@ss.hasPermi('interview:position:add')")
    @Log(title = "岗位", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody InterviewPosition interviewPosition)
    {
        return toAjax(interviewPositionService.insertInterviewPosition(interviewPosition));
    }

    /**
     * 修改岗位
     */
    @PreAuthorize("@ss.hasPermi('interview:position:edit')")
    @Log(title = "岗位", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody InterviewPosition interviewPosition)
    {
        return toAjax(interviewPositionService.updateInterviewPosition(interviewPosition));
    }

    /**
     * 删除岗位
     */
    @PreAuthorize("@ss.hasPermi('interview:position:remove')")
    @Log(title = "岗位", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(interviewPositionService.deleteInterviewPositionByIds(ids));
    }
}
