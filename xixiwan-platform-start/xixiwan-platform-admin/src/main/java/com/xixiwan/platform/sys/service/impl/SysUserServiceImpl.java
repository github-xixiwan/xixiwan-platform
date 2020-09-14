package com.xixiwan.platform.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.xixiwan.platform.constant.CommonConsts;
import com.xixiwan.platform.exception.WebException;
import com.xixiwan.platform.exception.enums.WebEnum;
import com.xixiwan.platform.module.common.rest.RestResponse;
import com.xixiwan.platform.module.common.util.DateUtils;
import com.xixiwan.platform.module.minio.MinioComponent;
import com.xixiwan.platform.module.web.constant.WebConsts;
import com.xixiwan.platform.module.web.form.BaseForm;
import com.xixiwan.platform.sys.entity.SysUser;
import com.xixiwan.platform.sys.entity.SysUserRole;
import com.xixiwan.platform.sys.form.SysUserForm;
import com.xixiwan.platform.sys.mapper.SysUserMapper;
import com.xixiwan.platform.sys.mapper.SysUserRoleMapper;
import com.xixiwan.platform.sys.service.CommonService;
import com.xixiwan.platform.sys.service.ISysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author Sente
 * @since 2018-09-18
 */
@Service
@RefreshScope
@Transactional
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Resource
    private CommonService commonService;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    @Resource
    private MinioComponent minioComponent;

    @Override
    public IPage<SysUser> selectPage(SysUserForm userForm) {
        userForm.setSortNames(Lists.newArrayList("createtime"));
        userForm.setSortOrders(WebConsts.SORTORDER_DESC);
        Page<SysUser> page = commonService.getPage(userForm);
        BaseForm form = commonService.getBaseForm(userForm);
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("createtime", form.getStartDateTime(), form.getEndDateTime());
        String username = userForm.getUsername();
        if (StringUtils.isNotBlank(username)) {
            queryWrapper.eq("username", username);
        }
        String name = userForm.getName();
        if (StringUtils.isNotBlank(name)) {
            queryWrapper.like("name", name);
        }
        return sysUserMapper.selectPage(page, queryWrapper);
    }

    @Override
    public SysUser selectUserByUsername(String username) {
        SysUser sysUser = new SysUser();
        sysUser.setUsername(username);
        Wrapper<SysUser> queryWrapper = new QueryWrapper<>(sysUser);
        sysUser = sysUserMapper.selectOne(queryWrapper);
        sysUser.setAvatar(minioComponent.getUrl(sysUser.getAvatar()));
        return sysUser;
    }

    @Override
    public RestResponse<String> register(SysUser user) {
        if (user == null) {
            throw new WebException(WebEnum.ERROR_0002);
        }
        String username = user.getUsername();
        if (StringUtils.isBlank(username)) {
            throw new WebException(WebEnum.ERROR_0003);
        }
        String password = user.getPassword();
        if (StringUtils.isBlank(password)) {
            throw new WebException(WebEnum.ERROR_0005);
        }
        String email = user.getEmail();
        if (StringUtils.isBlank(email)) {
            throw new WebException(WebEnum.ERROR_0006);
        }
        SysUser querySysUser = new SysUser();
        querySysUser.setUsername(username);
        Wrapper<SysUser> queryWrapper = new QueryWrapper<>(querySysUser);
        querySysUser = sysUserMapper.selectOne(queryWrapper);
        if (querySysUser != null) {
            throw new WebException(WebEnum.ERROR_0007);
        }
        querySysUser = new SysUser();
        querySysUser.setEmail(email);
        queryWrapper = new QueryWrapper<>(querySysUser);
        querySysUser = sysUserMapper.selectOne(queryWrapper);
        if (querySysUser != null) {
            throw new WebException(WebEnum.ERROR_0008);
        }
        user.setPassword(passwordEncoder.encode(password));
        // 密码有效期100天
        user.setExpiredtime(DateUtils.addTime(LocalDateTime.now(), ChronoUnit.DAYS, 100));
        if (sysUserMapper.insert(user) > 0) {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserid(user.getId());
            sysUserRole.setRoleid(2);
            sysUserRoleMapper.insert(sysUserRole);
            return RestResponse.success("注册成功");
        }
        return RestResponse.failure("注册失败");
    }

    @Override
    public RestResponse<String> editUser(SysUser user) {
        if (user == null) {
            throw new WebException(WebEnum.ERROR_0002);
        }
        Integer id = user.getId();
        if (id == null) {
            throw new WebException(WebEnum.ERROR_0011);
        }
        SysUser sysUser = sysUserMapper.selectById(id);
        if (sysUser == null) {
            throw new WebException(WebEnum.ERROR_0012);
        }
        Integer status = user.getStatus();
        if (CommonConsts.STATUS_1.equals(status)) {
            user.setFailtimes(0);
        }
        if (sysUserMapper.updateById(user) > 0) {
            return RestResponse.success("修改成功");
        }
        return RestResponse.failure("修改失败");
    }

    @Override
    public RestResponse<String> editAvatar(String avatar, String fileName, SysUser user) {
        if (StringUtils.isBlank(avatar)) {
            throw new WebException(WebEnum.ERROR_0019);
        }
        if (StringUtils.isBlank(fileName)) {
            throw new WebException(WebEnum.ERROR_0020);
        }
        minioComponent.uploadBase64(fileName, avatar);
        user.setAvatar(fileName);
        return editUser(user);
    }

    @Override
    public RestResponse<String> unlockScreen(SysUser user, String password) {
        if (user == null) {
            throw new WebException(WebEnum.ERROR_0002);
        }
        if (StringUtils.isBlank(password)) {
            throw new WebException(WebEnum.ERROR_0005);
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new WebException(WebEnum.ERROR_0034);
        }
        user.setStatus(CommonConsts.STATUS_1);
        if (sysUserMapper.updateById(user) > 0) {
            return RestResponse.success("解锁成功");
        }
        return RestResponse.failure("解锁失败");
    }

    @Override
    public RestResponse<String> expire(SysUserForm userForm) {
        if (userForm == null) {
            throw new WebException(WebEnum.ERROR_0002);
        }
        String username = userForm.getUsername();
        if (StringUtils.isBlank(username)) {
            throw new WebException(WebEnum.ERROR_0003);
        }
        String originalPassword = userForm.getOriginalPassword();
        if (StringUtils.isBlank(originalPassword)) {
            throw new WebException(WebEnum.ERROR_0036);
        }
        String password = userForm.getPassword();
        if (StringUtils.isBlank(password)) {
            throw new WebException(WebEnum.ERROR_0005);
        }
        if (originalPassword.equals(password)) {
            throw new WebException(WebEnum.ERROR_0037);
        }
        SysUser sysUser = new SysUser();
        sysUser.setUsername(username);
        Wrapper<SysUser> queryWrapper = new QueryWrapper<>(sysUser);
        sysUser = sysUserMapper.selectOne(queryWrapper);
        if (sysUser == null) {
            throw new WebException(WebEnum.ERROR_0004);
        }
        if (!CommonConsts.STATUS_6.equals(sysUser.getStatus())) {
            throw new WebException(WebEnum.ERROR_0035);
        }
        if (!passwordEncoder.matches(originalPassword, sysUser.getPassword())) {
            throw new WebException(WebEnum.ERROR_0034);
        }
        sysUser.setStatus(CommonConsts.STATUS_1);
        sysUser.setPassword(passwordEncoder.encode(password));
        // 密码有效期100天
        sysUser.setExpiredtime(DateUtils.addTime(LocalDateTime.now(), ChronoUnit.DAYS, 100));
        if (sysUserMapper.updateById(sysUser) > 0) {
            return RestResponse.success("修改成功");
        }
        return RestResponse.failure("修改失败");
    }

    @Override
    public RestResponse<String> changePassword(SysUser user, SysUserForm userForm) {
        if (userForm == null) {
            throw new WebException(WebEnum.ERROR_0002);
        }
        String originalPassword = userForm.getOriginalPassword();
        if (StringUtils.isBlank(originalPassword)) {
            throw new WebException(WebEnum.ERROR_0036);
        }
        String password = userForm.getPassword();
        if (StringUtils.isBlank(password)) {
            throw new WebException(WebEnum.ERROR_0005);
        }
        if (user == null) {
            throw new WebException(WebEnum.ERROR_0001);
        }
        if (!passwordEncoder.matches(originalPassword, user.getPassword())) {
            throw new WebException(WebEnum.ERROR_0034);
        }
        user.setPassword(passwordEncoder.encode(password));
        // 密码有效期100天
        user.setExpiredtime(DateUtils.addTime(LocalDateTime.now(), ChronoUnit.DAYS, 100));
        if (sysUserMapper.updateById(user) > 0) {
            return RestResponse.success("修改成功");
        }
        return RestResponse.failure("修改失败");
    }

}