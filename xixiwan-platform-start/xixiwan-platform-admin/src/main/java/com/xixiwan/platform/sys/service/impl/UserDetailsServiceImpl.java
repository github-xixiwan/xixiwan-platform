package com.xixiwan.platform.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.xixiwan.platform.module.common.util.DateUtils;
import com.xixiwan.platform.constant.CommonConsts;
import com.xixiwan.platform.exception.WebException;
import com.xixiwan.platform.exception.enums.WebEnum;
import com.xixiwan.platform.sys.entity.SysRole;
import com.xixiwan.platform.sys.entity.SysUser;
import com.xixiwan.platform.sys.mapper.SysRoleMapper;
import com.xixiwan.platform.sys.mapper.SysUserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) {
        if (StringUtils.isBlank(username)) {
            throw new WebException(WebEnum.ERROR_0003);
        }
        List<GrantedAuthority> authorities = Lists.newArrayList();

        // 从数据库中取出用户信息
        SysUser sysUser = new SysUser();
        sysUser.setUsername(username);
        Wrapper<SysUser> queryWrapper = new QueryWrapper<>(sysUser);
        sysUser = sysUserMapper.selectOne(queryWrapper);
        // 判断用户是否存在
        if (sysUser == null) {
            throw new WebException(WebEnum.ERROR_0004);
        }
        // 添加权限
        List<SysRole> list = sysRoleMapper.selectRoleByUserid(sysUser.getId());
        if (list != null && !list.isEmpty()) {
            for (SysRole sysRole : list) {
                authorities.add(new SimpleGrantedAuthority(sysRole.getAuthorities()));
            }
        }
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        Integer status = sysUser.getStatus();
        if (CommonConsts.STATUS_2.equals(status)) {
            enabled = false;
        } else if (CommonConsts.STATUS_3.equals(status)) {
            accountNonLocked = false;
        } else if (CommonConsts.STATUS_4.equals(status)) {
            accountNonExpired = false;
        } else if (CommonConsts.STATUS_5.equals(status)) {
            sysUser.setStatus(CommonConsts.STATUS_1);
            sysUserMapper.updateById(sysUser);
        }
        LocalDateTime expiredtime = sysUser.getExpiredtime();
        if (DateUtils.compareTwoTime(LocalDateTime.now(), expiredtime) > 0) {
            credentialsNonExpired = false;
            sysUser.setStatus(CommonConsts.STATUS_6);
            sysUserMapper.updateById(sysUser);
        }
        // 返回UserDetails实现类
        return new User(sysUser.getUsername(), sysUser.getPassword(), enabled, accountNonExpired, credentialsNonExpired,
                accountNonLocked, authorities);
    }

}
