package com.xixiwan.platform.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xixiwan.platform.module.common.rest.RestResponse;
import com.xixiwan.platform.sys.dto.Node;
import com.xixiwan.platform.sys.entity.SysRole;
import com.xixiwan.platform.sys.form.SysRoleForm;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author Sente
 * @since 2018-09-28
 */
public interface ISysRoleService extends IService<SysRole> {

    SysRole selectRoleByAuthorities(String authorities);

    IPage<SysRole> selectPage(SysRoleForm roleForm);

    List<Node> selectNodeList(SysRoleForm roleForm);

    RestResponse<String> addRole(SysRole role);

    RestResponse<String> editRole(SysRole role);

    RestResponse<String> deleteRole(SysRole role);

}
