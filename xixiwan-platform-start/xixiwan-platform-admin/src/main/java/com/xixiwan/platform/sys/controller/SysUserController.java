package com.xixiwan.platform.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xixiwan.platform.annotation.Operation;
import com.xixiwan.platform.base.BaseMvcController;
import com.xixiwan.platform.module.common.rest.RestResponse;
import com.xixiwan.platform.constant.CommonConsts;
import com.xixiwan.platform.sys.entity.SysMenu;
import com.xixiwan.platform.sys.entity.SysUser;
import com.xixiwan.platform.sys.form.SysUserForm;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Sente
 * @since 2018-09-28
 */
@Controller
@RequestMapping("/sys/user")
public class SysUserController extends BaseMvcController {

    @GetMapping("/list")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView list(Map<String, Object> model, @RequestParam("code") String code) {
        SysUser sysUser = getSysUser();
        SysMenu sysMenu = selectMenuMoreByCode(code);
        model.put("sysMenu", sysMenu);
        List<SysMenu> menuList = getMenuList(sysUser.getId(), code, CommonConsts.MTYPE_3, null);
        model.put("menuList", menuList);
        return new ModelAndView("sys/userList", model);
    }

    @GetMapping("/selectPage")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public RestResponse<IPage<SysUser>> selectPage(SysUserForm userForm) {
        return RestResponse.success(sysUserService.selectPage(userForm));
    }

    @GetMapping("/selectUserList")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView selectUserList(Map<String, Object> model, SysUserForm userForm) {
        model.put("userForm", userForm);
        return new ModelAndView("sys/selectUserList", model);
    }

    @PostMapping("/editUser")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(describe = "修改用户数据")
    public RestResponse<String> editUser(SysUser user) {
        return sysUserService.editUser(user);
    }

    @PostMapping("/editAvatar")
    @ResponseBody
    @Operation(describe = "修改用户头像")
    public RestResponse<String> editAvatar(String avatar, String fileName) {
        return sysUserService.editAvatar(avatar, fileName, getSysUser());
    }

    @GetMapping("/changePersonal")
    @ResponseBody
    @Operation(describe = "修改个人信息")
    public ModelAndView changePersonal(Map<String, Object> model) {
        model.put("sysUser", getSysUser());
        return new ModelAndView("index/changePersonal", model);
    }

    @GetMapping("/changePassword")
    @Operation(describe = "显示修改密码页面")
    public ModelAndView changePassword() {
        return new ModelAndView("index/changePassword");
    }

    @PostMapping("/changePassword")
    @ResponseBody
    @Operation(describe = "修改密码")
    public RestResponse<String> changePassword(SysUserForm userForm) {
        return sysUserService.changePassword(getSysUser(), userForm);
    }

}
