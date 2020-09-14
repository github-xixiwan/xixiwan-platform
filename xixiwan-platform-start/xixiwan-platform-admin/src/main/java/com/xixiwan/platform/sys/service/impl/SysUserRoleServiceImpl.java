package com.xixiwan.platform.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xixiwan.platform.module.common.rest.RestResponse;
import com.xixiwan.platform.exception.WebException;
import com.xixiwan.platform.exception.enums.WebEnum;
import com.xixiwan.platform.sys.entity.SysUserRole;
import com.xixiwan.platform.sys.form.SysUserRoleForm;
import com.xixiwan.platform.sys.mapper.SysUserRoleMapper;
import com.xixiwan.platform.sys.service.ISysUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 * 用户和角色关联表 服务实现类
 * </p>
 *
 * @author Sente
 * @since 2018-09-14
 */
@Service
@Transactional
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {

    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    public void deleteUserRole(Integer userid) {
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setUserid(userid);
        Wrapper<SysUserRole> roleMenuQueryWrapper = new QueryWrapper<>(sysUserRole);
        sysUserRoleMapper.delete(roleMenuQueryWrapper);
    }

    @Override
    public RestResponse<String> authority(SysUserRoleForm userRoleForm) {
        if (userRoleForm == null) {
            throw new WebException(WebEnum.ERROR_0002);
        }
        Integer userid = userRoleForm.getUserid();
        if (userid == null || userid.equals(0)) {
            throw new WebException(WebEnum.ERROR_0021);
        }
        Integer[] roleids = userRoleForm.getRoleids();
        if (roleids == null || roleids.length == 0) {
            throw new WebException(WebEnum.ERROR_0017);
        }
        // 删除用户对应的角色
        deleteUserRole(userid);
        // 新增
        for (Integer roleid : roleids) {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserid(userid);
            sysUserRole.setRoleid(roleid);
            sysUserRoleMapper.insert(sysUserRole);
        }
        return RestResponse.success("保存成功");
    }

}
