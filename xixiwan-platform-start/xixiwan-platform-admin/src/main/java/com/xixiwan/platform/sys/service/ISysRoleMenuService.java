package com.xixiwan.platform.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xixiwan.platform.module.common.rest.RestResponse;
import com.xixiwan.platform.sys.entity.SysRoleMenu;
import com.xixiwan.platform.sys.form.SysRoleMenuForm;

/**
 * <p>
 * 角色和菜单关联表 服务类
 * </p>
 *
 * @author Sente
 * @since 2018-09-14
 */
public interface ISysRoleMenuService extends IService<SysRoleMenu> {

    RestResponse<String> authority(SysRoleMenuForm roleMenuForm);

}
