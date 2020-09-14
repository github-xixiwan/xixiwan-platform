package com.xixiwan.platform.sys.controller;

import com.xixiwan.platform.annotation.Operation;
import com.xixiwan.platform.base.BaseMvcController;
import com.xixiwan.platform.module.common.rest.RestResponse;
import com.xixiwan.platform.sys.form.SysUserRoleForm;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 用户和角色关联表 前端控制器
 * </p>
 *
 * @author Sente
 * @since 2018-09-14
 */
@Controller
@RequestMapping("/sys/userRole")
public class SysUserRoleController extends BaseMvcController {

    @PostMapping("/authority")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(describe = "给用户赋权")
    public RestResponse<String> authority(SysUserRoleForm userRoleForm) {
        return sysUserRoleService.authority(userRoleForm);
    }
}
