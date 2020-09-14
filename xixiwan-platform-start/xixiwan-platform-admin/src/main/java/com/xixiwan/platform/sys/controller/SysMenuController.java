package com.xixiwan.platform.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xixiwan.platform.annotation.Operation;
import com.xixiwan.platform.base.BaseMvcController;
import com.xixiwan.platform.module.common.rest.RestResponse;
import com.xixiwan.platform.constant.CommonConsts;
import com.xixiwan.platform.sys.dto.Node;
import com.xixiwan.platform.sys.entity.SysMenu;
import com.xixiwan.platform.sys.entity.SysUser;
import com.xixiwan.platform.sys.form.SysMenuForm;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author Sente
 * @since 2018-09-13
 */
@Controller
@RequestMapping("/sys/menu")
public class SysMenuController extends BaseMvcController {

    @GetMapping("/list")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView list(Map<String, Object> model, @RequestParam("code") String code) {
        SysUser sysUser = getSysUser();
        SysMenu sysMenu = selectMenuMoreByCode(code);
        model.put("sysMenu", sysMenu);
        List<SysMenu> menuList = getMenuList(sysUser.getId(), code, CommonConsts.MTYPE_3, null);
        model.put("menuList", menuList);
        return new ModelAndView("sys/menuList", model);
    }

    @GetMapping("/selectPage")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public RestResponse<IPage<SysMenu>> selectPage(SysMenuForm menuForm) {
        return RestResponse.success(sysMenuService.selectPage(menuForm));
    }

    @GetMapping("/selectNodeList")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public RestResponse<List<Node>> selectNodeList(SysMenuForm menuForm) {
        return RestResponse.success(sysMenuService.selectNodeList(menuForm));
    }

    @PostMapping("/addMenu")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(describe = "新增菜单数据")
    public RestResponse<String> addMenu(SysMenu menu) {
        return sysMenuService.addMenu(menu);
    }

    @PostMapping("/editMenu")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(describe = "修改菜单数据")
    public RestResponse<String> editMenu(SysMenu menu) {
        return sysMenuService.editMenu(menu);
    }

    @PostMapping("/deleteMenu")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(describe = "删除菜单数据")
    public RestResponse<String> deleteMenu(SysMenu menu) {
        return sysMenuService.deleteMenu(menu);
    }

}
