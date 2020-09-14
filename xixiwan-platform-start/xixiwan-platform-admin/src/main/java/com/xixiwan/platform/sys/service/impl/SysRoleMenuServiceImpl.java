package com.xixiwan.platform.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xixiwan.platform.module.common.rest.RestResponse;
import com.xixiwan.platform.exception.WebException;
import com.xixiwan.platform.exception.enums.WebEnum;
import com.xixiwan.platform.sys.entity.SysRoleMenu;
import com.xixiwan.platform.sys.form.SysRoleMenuForm;
import com.xixiwan.platform.sys.mapper.SysRoleMenuMapper;
import com.xixiwan.platform.sys.service.ISysRoleMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 * 角色和菜单关联表 服务实现类
 * </p>
 *
 * @author Sente
 * @since 2018-09-14
 */
@Service
@Transactional
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements ISysRoleMenuService {

    @Resource
    private SysRoleMenuMapper sysRoleMenuMapper;

    public void deleteRoleMenu(Integer roleid) {
        SysRoleMenu sysRoleMenu = new SysRoleMenu();
        sysRoleMenu.setRoleid(roleid);
        Wrapper<SysRoleMenu> roleMenuQueryWrapper = new QueryWrapper<>(sysRoleMenu);
        sysRoleMenuMapper.delete(roleMenuQueryWrapper);
    }

    @Override
    public RestResponse<String> authority(SysRoleMenuForm roleMenuForm) {
        if (roleMenuForm == null) {
            throw new WebException(WebEnum.ERROR_0002);
        }
        Integer roleid = roleMenuForm.getRoleid();
        if (roleid == null || roleid.equals(0)) {
            throw new WebException(WebEnum.ERROR_0017);
        }
        Integer[] menuids = roleMenuForm.getMenuids();
        if (menuids == null || menuids.length == 0) {
            throw new WebException(WebEnum.ERROR_0018);
        }
        // 删除角色对应的菜单
        deleteRoleMenu(roleid);
        // 新增
        for (Integer menuid : menuids) {
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setRoleid(roleid);
            sysRoleMenu.setMenuid(menuid);
            sysRoleMenuMapper.insert(sysRoleMenu);
        }
        return RestResponse.success("保存成功");
    }

}
