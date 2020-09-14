package com.xixiwan.platform.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xixiwan.platform.module.common.rest.RestResponse;
import com.xixiwan.platform.sys.dto.Node;
import com.xixiwan.platform.sys.entity.SysMenu;
import com.xixiwan.platform.sys.form.SysMenuForm;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author Sente
 * @since 2018-09-13
 */
public interface ISysMenuService extends IService<SysMenu> {

    IPage<SysMenu> selectPage(SysMenuForm menuForm);

    SysMenu selectMenuByCode(String code);

    SysMenu selectMenuMoreByCode(String code);

    List<SysMenu> selectUserMenuByForm(SysMenuForm menuForm);

    List<Node> selectNodeList(SysMenuForm menuForm);

    RestResponse<String> addMenu(SysMenu menu);

    RestResponse<String> editMenu(SysMenu menu);

    RestResponse<String> deleteMenu(SysMenu menu);

}
