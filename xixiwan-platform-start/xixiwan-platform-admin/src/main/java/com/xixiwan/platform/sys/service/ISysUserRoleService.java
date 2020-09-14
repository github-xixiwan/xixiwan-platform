package com.xixiwan.platform.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xixiwan.platform.module.common.rest.RestResponse;
import com.xixiwan.platform.sys.entity.SysUserRole;
import com.xixiwan.platform.sys.form.SysUserRoleForm;

/**
 * <p>
 * 用户和角色关联表 服务类
 * </p>
 *
 * @author Sente
 * @since 2018-09-14
 */
public interface ISysUserRoleService extends IService<SysUserRole> {

    RestResponse<String> authority(SysUserRoleForm userRoleForm);

}
