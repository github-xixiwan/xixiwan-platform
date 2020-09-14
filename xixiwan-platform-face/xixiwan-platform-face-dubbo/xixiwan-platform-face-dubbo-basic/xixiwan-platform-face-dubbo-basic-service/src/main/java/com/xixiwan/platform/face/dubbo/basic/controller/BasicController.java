package com.xixiwan.platform.face.dubbo.basic.controller;

import com.xixiwan.platform.module.common.rest.RestResponse;
import com.xixiwan.platform.face.dubbo.basic.base.BaseMvcController;
import com.xixiwan.platform.face.dubbo.basic.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class BasicController extends BaseMvcController {

    @GetMapping("/hello")
    public RestResponse<String> hello(@RequestParam("username") String username) {
        SysUser sysUser = sysUserService.selectUserByUsername(username);
        return RestResponse.success("hello dubbo basic " + sysUser.getUsername() + " " + sysUser.getName());
    }

}
