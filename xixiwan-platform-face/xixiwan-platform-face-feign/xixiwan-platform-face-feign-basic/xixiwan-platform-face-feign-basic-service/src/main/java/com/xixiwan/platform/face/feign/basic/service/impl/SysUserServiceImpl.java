package com.xixiwan.platform.face.feign.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xixiwan.platform.face.feign.basic.entity.SysUser;
import com.xixiwan.platform.face.feign.basic.mapper.SysUserMapper;
import com.xixiwan.platform.face.feign.basic.service.ISysUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author Sente
 * @since 2018-09-18
 */
@Service
@Transactional
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser selectUserByUsername(String username) {
        SysUser sysUser = new SysUser();
        sysUser.setUsername(username);
        Wrapper<SysUser> queryWrapper = new QueryWrapper<>(sysUser);
        return sysUserMapper.selectOne(queryWrapper);
    }

}