package com.xixiwan.platform.listener;

import com.xixiwan.platform.constant.CommonConsts;
import com.xixiwan.platform.sys.entity.SysUser;
import com.xixiwan.platform.sys.service.ISysUserService;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class AuthenticationFailureBadCredentialsListener
        implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    @Resource
    private ISysUserService sysUserService;

    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
        SysUser sysUser = sysUserService.selectUserByUsername(event.getAuthentication().getName());
        SysUser sysUsers = new SysUser();
        Integer failtimes = sysUser.getFailtimes() + 1;
        if (failtimes >= 5) {
            sysUsers.setStatus(CommonConsts.STATUS_3);
        }
        sysUsers.setId(sysUser.getId());
        sysUsers.setFailtimes(failtimes);
        sysUserService.updateById(sysUsers);
    }

}
