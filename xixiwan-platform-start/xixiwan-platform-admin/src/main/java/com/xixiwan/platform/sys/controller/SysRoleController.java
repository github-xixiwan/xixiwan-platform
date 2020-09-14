package com.xixiwan.platform.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xixiwan.platform.annotation.Operation;
import com.xixiwan.platform.base.BaseMvcController;
import com.xixiwan.platform.module.common.rest.RestResponse;
import com.xixiwan.platform.constant.CommonConsts;
import com.xixiwan.platform.sys.dto.Node;
import com.xixiwan.platform.sys.entity.SysMenu;
import com.xixiwan.platform.sys.entity.SysRole;
import com.xixiwan.platform.sys.entity.SysUser;
import com.xixiwan.platform.sys.form.SysRoleForm;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author Sente
 * @since 2018-09-28
 */
@Controller
@RequestMapping("/sys/role")
public class SysRoleController extends BaseMvcController {

    @GetMapping("/list")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView list(Map<String, Object> model, @RequestParam("code") String code) {
        SysUser sysUser = getSysUser();
        SysMenu sysMenu = selectMenuMoreByCode(code);
        model.put("sysMenu", sysMenu);
        List<SysMenu> menuList = getMenuList(sysUser.getId(), code, CommonConsts.MTYPE_3, null);
        model.put("menuList", menuList);
        return new ModelAndView("sys/roleList", model);
    }

    @GetMapping("/selectPage")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public RestResponse<IPage<SysRole>> selectPage(SysRoleForm roleForm) {
        return RestResponse.success(sysRoleService.selectPage(roleForm));
    }

    @GetMapping("/selectNodeList")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public RestResponse<List<Node>> selectNodeList(SysRoleForm roleForm) {
        return RestResponse.success(sysRoleService.selectNodeList(roleForm));
    }

    @PostMapping("/addRole")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(describe = "新增角色数据")
    public RestResponse<String> addRole(SysRole role) {
        return sysRoleService.addRole(role);
    }

    @PostMapping("/editRole")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(describe = "修改角色数据")
    public RestResponse<String> editRole(SysRole role) {
        return sysRoleService.editRole(role);
    }

    @PostMapping("/deleteRole")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(describe = "删除角色数据")
    public RestResponse<String> deleteRole(SysRole role) {
        return sysRoleService.deleteRole(role);
    }

}
