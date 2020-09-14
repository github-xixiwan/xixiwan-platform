package com.xixiwan.platform.listener;

import com.xixiwan.platform.sys.entity.SysUser;
import com.xixiwan.platform.sys.service.ISysUserService;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class AuthenticationSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {

    @Resource
    private ISysUserService sysUserService;

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        SysUser sysUser = sysUserService.selectUserByUsername(event.getAuthentication().getName());
        SysUser sysUsers = new SysUser();
        sysUsers.setId(sysUser.getId());
        sysUsers.setFailtimes(0);
        sysUserService.updateById(sysUsers);
    }

}
