package com.xixiwan.platform.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xixiwan.platform.annotation.Operation;
import com.xixiwan.platform.base.BaseMvcController;
import com.xixiwan.platform.module.common.rest.RestResponse;
import com.xixiwan.platform.constant.CommonConsts;
import com.xixiwan.platform.sys.entity.SysMenu;
import com.xixiwan.platform.sys.entity.SysNotice;
import com.xixiwan.platform.sys.entity.SysUser;
import com.xixiwan.platform.sys.form.SysNoticeForm;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 通知表 前端控制器
 * </p>
 *
 * @author Sente
 * @since 2018-11-14
 */
@Controller
@RequestMapping("/sys/notice")
public class SysNoticeController extends BaseMvcController {

    @GetMapping("/list")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView list(Map<String, Object> model, @RequestParam("code") String code) {
        SysUser sysUser = getSysUser();
        SysMenu sysMenu = selectMenuMoreByCode(code);
        model.put("sysMenu", sysMenu);
        List<SysMenu> menuList = getMenuList(sysUser.getId(), code, CommonConsts.MTYPE_3, null);
        model.put("menuList", menuList);
        return new ModelAndView("sys/noticeList", model);
    }

    @GetMapping("/selectPage")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public RestResponse<IPage<SysNotice>> selectPage(SysNoticeForm noticeForm) {
        return RestResponse.success(sysNoticeService.selectPage(noticeForm));
    }

    @PostMapping("/addNotice")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(describe = "新增通知数据")
    public RestResponse<String> addNotice(SysNotice notice) {
        return sysNoticeService.addNotice(notice);
    }

    @PostMapping("/editNotice")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(describe = "修改通知数据")
    public RestResponse<String> editNotice(SysNotice notice) {
        return sysNoticeService.editNotice(notice);
    }

    @PostMapping("/deleteNotice")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(describe = "删除通知数据")
    public RestResponse<String> deleteNotice(SysNoticeForm noticeForm) {
        return sysNoticeService.deleteNotice(noticeForm);
    }

    @PostMapping("/repealNotice")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(describe = "撤销通知数据")
    public RestResponse<String> repealNotice(SysNoticeForm noticeForm) {
        return sysNoticeService.repealNotice(noticeForm);
    }

    @PostMapping("/releaseNotice")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(describe = "发布通知数据")
    public RestResponse<String> releaseNotice(SysNoticeForm noticeForm) {
        return sysNoticeService.releaseNotice(noticeForm);
    }

    @GetMapping("/selectUserNoticeByForm")
    @ResponseBody
    public RestResponse<List<SysNotice>> selectUserNoticeByForm(SysNoticeForm noticeForm) {
        return RestResponse.success(sysNoticeService.selectUserNoticeByForm(getSysUser(), noticeForm));
    }

}
