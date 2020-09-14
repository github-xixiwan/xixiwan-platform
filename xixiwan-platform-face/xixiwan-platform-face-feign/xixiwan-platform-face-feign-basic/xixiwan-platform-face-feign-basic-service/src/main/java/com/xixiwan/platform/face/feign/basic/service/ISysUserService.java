package com.xixiwan.platform.face.feign.basic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xixiwan.platform.face.feign.basic.entity.SysUser;

/**
 * <p>
 * 管理员表 服务类
 * </p>
 *
 * @author Sente
 * @since 2018-09-18
 */
public interface ISysUserService extends IService<SysUser> {

    SysUser selectUserByUsername(String username);

}