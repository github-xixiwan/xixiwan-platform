package com.xixiwan.platform.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.xixiwan.platform.module.common.rest.RestResponse;
import com.xixiwan.platform.module.web.constant.WebConsts;
import com.xixiwan.platform.exception.WebException;
import com.xixiwan.platform.exception.enums.WebEnum;
import com.xixiwan.platform.sys.dto.Node;
import com.xixiwan.platform.sys.dto.NodeState;
import com.xixiwan.platform.sys.entity.SysRole;
import com.xixiwan.platform.sys.entity.SysRoleMenu;
import com.xixiwan.platform.sys.entity.SysUserRole;
import com.xixiwan.platform.sys.form.SysRoleForm;
import com.xixiwan.platform.sys.mapper.SysRoleMapper;
import com.xixiwan.platform.sys.mapper.SysRoleMenuMapper;
import com.xixiwan.platform.sys.mapper.SysUserRoleMapper;
import com.xixiwan.platform.sys.service.CommonService;
import com.xixiwan.platform.sys.service.ISysRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author Sente
 * @since 2018-09-28
 */
@Service
@Transactional
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Resource
    private CommonService commonService;

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    @Resource
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public SysRole selectRoleByAuthorities(String authorities) {
        SysRole sysRole = new SysRole();
        sysRole.setAuthorities(authorities);
        Wrapper<SysRole> queryWrapper = new QueryWrapper<>(sysRole);
        return sysRoleMapper.selectOne(queryWrapper);
    }

    @Override
    public IPage<SysRole> selectPage(SysRoleForm roleForm) {
        roleForm.setSortNames(Lists.newArrayList("num"));
        roleForm.setSortOrders(WebConsts.SORTORDER_ASC);
        Page<SysRole> page = commonService.getPage(roleForm);
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        String authorities = roleForm.getAuthorities();
        if (StringUtils.isNotBlank(authorities)) {
            queryWrapper.like("authorities", authorities);
        }
        String name = roleForm.getName();
        if (StringUtils.isNotBlank(name)) {
            queryWrapper.like("name", name);
        }
        return sysRoleMapper.selectPage(page, queryWrapper);
    }

    public List<SysUserRole> selectUserRoleList(SysUserRole userRole) {
        Wrapper<SysUserRole> queryWrapper = new QueryWrapper<>(userRole);
        return sysUserRoleMapper.selectList(queryWrapper);
    }

    public Boolean isExists(List<SysUserRole> userRoleList, Integer roleid) {
        for (SysUserRole sysUserRole : userRoleList) {
            if (roleid.equals(sysUserRole.getRoleid())) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    @Override
    public List<Node> selectNodeList(SysRoleForm roleForm) {
        List<Node> rootList = Lists.newArrayList();
        List<SysUserRole> userRoleList = Lists.newArrayList();
        Integer userid = roleForm.getUserid();
        if (userid != null && userid > 0) {
            SysUserRole userRole = new SysUserRole();
            userRole.setUserid(userid);
            userRoleList = selectUserRoleList(userRole);
        }
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        Integer status = roleForm.getStatus();
        if (status != null) {
            queryWrapper.eq("status", status);
        }
        queryWrapper.orderByAsc("num");
        List<SysRole> roleList = sysRoleMapper.selectList(queryWrapper);
        if (roleList != null && !roleList.isEmpty()) {
            for (SysRole sysRole : roleList) {
                Node node = new Node();
                node.setId(sysRole.getId());
                node.setText(sysRole.getName());
                // 选中
                if (userRoleList != null && !userRoleList.isEmpty()) {
                    NodeState state = new NodeState();
                    state.setChecked(isExists(userRoleList, sysRole.getId()));
                    node.setState(state);
                }
                rootList.add(node);
            }
        }
        return rootList;
    }

    @Override
    public RestResponse<String> addRole(SysRole role) {
        if (role == null) {
            throw new WebException(WebEnum.ERROR_0002);
        }
        String authorities = role.getAuthorities();
        if (StringUtils.isBlank(authorities)) {
            throw new WebException(WebEnum.ERROR_0014);
        }
        if (selectRoleByAuthorities(authorities) != null) {
            throw new WebException(WebEnum.ERROR_0015);
        }
        if (sysRoleMapper.insert(role) > 0) {
            return RestResponse.success("保存成功");
        }
        return RestResponse.failure("保存失败");
    }

    @Override
    public RestResponse<String> editRole(SysRole role) {
        if (role == null) {
            throw new WebException(WebEnum.ERROR_0002);
        }
        Integer id = role.getId();
        if (id == null) {
            throw new WebException(WebEnum.ERROR_0011);
        }
        SysRole sysRole = sysRoleMapper.selectById(id);
        if (sysRole == null) {
            throw new WebException(WebEnum.ERROR_0012);
        }
        if (sysRoleMapper.updateById(role) > 0) {
            return RestResponse.success("修改成功");
        }
        return RestResponse.failure("修改失败");
    }

    @Override
    public RestResponse<String> deleteRole(SysRole role) {
        if (role == null) {
            throw new WebException(WebEnum.ERROR_0002);
        }
        Integer id = role.getId();
        if (id == null || id.equals(0)) {
            throw new WebException(WebEnum.ERROR_0011);
        }
        role = sysRoleMapper.selectById(id);
        if (role == null) {
            throw new WebException(WebEnum.ERROR_0012);
        }
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setRoleid(id);
        Wrapper<SysUserRole> userRoleQueryWrapper = new QueryWrapper<>(sysUserRole);
        List<SysUserRole> userRoleList = sysUserRoleMapper.selectList(userRoleQueryWrapper);
        if (userRoleList != null && !userRoleList.isEmpty()) {
            throw new WebException(WebEnum.ERROR_0016);
        }
        if (sysRoleMapper.deleteById(id) > 0) {
            // 删除角色对应的菜单
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setRoleid(id);
            Wrapper<SysRoleMenu> roleMenuQueryWrapper = new QueryWrapper<>(sysRoleMenu);
            sysRoleMenuMapper.delete(roleMenuQueryWrapper);
            return RestResponse.success("删除成功");
        }
        return RestResponse.failure("删除失败");
    }

}
