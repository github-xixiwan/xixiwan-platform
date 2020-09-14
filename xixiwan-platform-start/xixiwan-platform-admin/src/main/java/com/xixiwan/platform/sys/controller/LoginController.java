package com.xixiwan.platform.sys.controller;

import com.google.common.collect.Lists;
import com.xixiwan.platform.base.BaseMvcController;
import com.xixiwan.platform.constant.CommonConsts;
import com.xixiwan.platform.module.common.rest.RestResponse;
import com.xixiwan.platform.module.web.util.IpUtils;
import com.xixiwan.platform.sys.entity.SysForget;
import com.xixiwan.platform.sys.entity.SysMenu;
import com.xixiwan.platform.sys.entity.SysUser;
import com.xixiwan.platform.sys.form.SysForgetForm;
import com.xixiwan.platform.sys.form.SysUserForm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: LoginController
 * @Description:登录权限控制
 * @Author: Sente
 * @Date: 2018年9月13日 下午1:56:14
 * @Copyright: 2018 www.sto.cn Inc. All rights reserved.
 */
@Controller
public class LoginController extends BaseMvcController {

    @Value("${test:test}")
    private String test;

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @GetMapping("/register")
    public ModelAndView register() {
        return new ModelAndView("register");
    }

    @GetMapping("/login/error")
    public void loginError() {
        throw (AuthenticationException) request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
    }

    @PostMapping("/forward")
    @ResponseBody
    public RestResponse<String> forward() {
        sysLogLoginService.addLogLogin(getSysUser(), IpUtils.getClientIpAddress(request));
        return RestResponse.success("登录成功");
    }

    @GetMapping("/")
    public ModelAndView index(Map<String, Object> model) {
        SysUser sysUser = getSysUser();
        model.put("sysUser", sysUser);
        List<Integer> mtypeList = Lists.newArrayList();
        mtypeList.add(CommonConsts.MTYPE_1);
        mtypeList.add(CommonConsts.MTYPE_2);
        List<SysMenu> menuList = getMenuList(sysUser.getId(), null, null, mtypeList);
        model.put("menuList", menuList);
        return new ModelAndView("index", model);
    }

    @GetMapping("/dashboard")
    public ModelAndView dashboard() {
        return new ModelAndView("dashboard");
    }

    @PostMapping("/register")
    @ResponseBody
    public RestResponse<String> register(SysUser user) {
        return sysUserService.register(user);
    }

    @GetMapping("/reset")
    public ModelAndView reset() {
        return new ModelAndView("reset");
    }

    @PostMapping("/sendCaptcha")
    @ResponseBody
    public RestResponse<String> sendCaptcha(SysForgetForm forgetForm) {
        return sysForgetService.sendCaptcha(forgetForm);
    }

    @PostMapping("/reset")
    @ResponseBody
    public RestResponse<String> reset(SysForget forget) {
        return sysForgetService.reset(forget);
    }

    @GetMapping("/unlockScreen")
    public ModelAndView unlockScreen(Map<String, Object> model, @RequestParam("refresh") String refresh) {
        model.put("refresh", refresh);
        SysUser sysUser = getSysUser();
        model.put("sysUser", sysUser);
        sysUser.setStatus(CommonConsts.STATUS_5);
        sysUserService.updateById(sysUser);
        return new ModelAndView("unlockScreen");
    }

    @PostMapping("/unlockScreen")
    @ResponseBody
    public RestResponse<String> unlockScreen(@RequestParam("password") String password) {
        return sysUserService.unlockScreen(getSysUser(), password);
    }

    @GetMapping("/expire")
    public ModelAndView changePassword() {
        return new ModelAndView("expire");
    }

    @PostMapping("/expire")
    @ResponseBody
    public RestResponse<String> expire(SysUserForm userForm) {
        return sysUserService.expire(userForm);
    }

    @GetMapping("/test")
    @ResponseBody
    public RestResponse<String> test() {
        return RestResponse.success(test);
    }

}
