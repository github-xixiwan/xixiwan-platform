package com.xixiwan.platform.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xixiwan.platform.module.common.rest.RestResponse;
import com.xixiwan.platform.sys.entity.SysUser;
import com.xixiwan.platform.sys.form.SysUserForm;

/**
 * <p>
 * 管理员表 服务类
 * </p>
 *
 * @author Sente
 * @since 2018-09-18
 */
public interface ISysUserService extends IService<SysUser> {

    IPage<SysUser> selectPage(SysUserForm userForm);

    SysUser selectUserByUsername(String username);

    RestResponse<String> register(SysUser user);

    RestResponse<String> editUser(SysUser user);

    RestResponse<String> editAvatar(String avatar, String fileName, SysUser user);

    RestResponse<String> unlockScreen(SysUser user, String password);

    RestResponse<String> expire(SysUserForm userForm);

    RestResponse<String> changePassword(SysUser user, SysUserForm userForm);

}