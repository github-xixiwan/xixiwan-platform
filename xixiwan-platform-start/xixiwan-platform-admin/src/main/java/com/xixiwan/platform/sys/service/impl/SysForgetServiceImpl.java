package com.xixiwan.platform.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xixiwan.platform.module.common.rest.RestResponse;
import com.xixiwan.platform.module.common.util.DateUtils;
import com.xixiwan.platform.module.common.util.EmailUtils;
import com.xixiwan.platform.module.common.util.RegexpUtils;
import com.xixiwan.platform.constant.CommonConsts;
import com.xixiwan.platform.exception.WebException;
import com.xixiwan.platform.exception.enums.WebEnum;
import com.xixiwan.platform.sys.entity.SysForget;
import com.xixiwan.platform.sys.entity.SysUser;
import com.xixiwan.platform.sys.form.SysForgetForm;
import com.xixiwan.platform.sys.mapper.SysForgetMapper;
import com.xixiwan.platform.sys.mapper.SysUserMapper;
import com.xixiwan.platform.sys.service.ISysForgetService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * <p>
 * 忘记密码表 服务实现类
 * </p>
 *
 * @author Sente
 * @since 2018-10-03
 */
@Service
@Transactional
public class SysForgetServiceImpl extends ServiceImpl<SysForgetMapper, SysForget> implements ISysForgetService {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private SysForgetMapper sysForgetMapper;

    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public RestResponse<String> sendCaptcha(SysForgetForm forgetForm) {
        if (forgetForm == null) {
            throw new WebException(WebEnum.ERROR_0002);
        }
        Integer rtype = forgetForm.getRtype();
        if (rtype == null || rtype.equals(0)) {
            throw new WebException(WebEnum.ERROR_0022);
        }
        String rvalue = forgetForm.getRvalue();
        if (StringUtils.isBlank(rvalue)) {
            throw new WebException(WebEnum.ERROR_0023);
        }
        SysUser sysUser = new SysUser();
        String captcha = "";
        if (CommonConsts.RTYPE_1.equals(rtype)) {
            if (!RegexpUtils.isEmail(rvalue)) {
                throw new WebException(WebEnum.ERROR_0024);
            }
            sysUser.setEmail(rvalue);
            QueryWrapper<SysUser> userQueryWrapper = new QueryWrapper<>(sysUser);
            sysUser = sysUserMapper.selectOne(userQueryWrapper);
            if (sysUser == null) {
                throw new WebException(WebEnum.ERROR_0025);
            }
            // 发送邮件（10位字母+数字的随机数）
            captcha = RandomStringUtils.random(10, true, true);
            try {
                EmailUtils.simpleEmail("重置密码",
                        "您正在重置密码，验证码为：【" + captcha + "】，有效期2小时，区分大小写。请不要把验证码泄露给其他人,如非本人操作，可不用理会！【XIXIWAN platform】",
                        rvalue);
            } catch (Exception e) {
                throw new WebException(WebEnum.ERROR_0028);
            }
        } else if (CommonConsts.RTYPE_2.equals(rtype)) {
            if (!RegexpUtils.isPhone(rvalue)) {
                throw new WebException(WebEnum.ERROR_0026);
            }
            sysUser.setPhone(rvalue);
            QueryWrapper<SysUser> userQueryWrapper = new QueryWrapper<>(sysUser);
            sysUser = sysUserMapper.selectOne(userQueryWrapper);
            if (sysUser == null) {
                throw new WebException(WebEnum.ERROR_0027);
            }
            // 发送短信（6位数字的随机数）
            captcha = RandomStringUtils.random(6, false, true);
        }
        SysForget sysForget = new SysForget();
        sysForget.setUserid(sysUser.getId());
        QueryWrapper<SysForget> forgetQueryWrapper = new QueryWrapper<>(sysForget);
        SysForget forget = sysForgetMapper.selectOne(forgetQueryWrapper);
        sysForget.setRtype(rtype);
        sysForget.setRvalue(rvalue);
        sysForget.setCaptcha(captcha);
        sysForget.setUsed(CommonConsts.COMMON_0);
        // 验证码有效期2小时
        sysForget.setExpiredtime(DateUtils.addTime(LocalDateTime.now(), ChronoUnit.HOURS, 2));
        int rusult = 0;
        if (forget != null) {
            sysForget.setId(forget.getId());
            rusult = sysForgetMapper.updateById(sysForget);
        } else {
            rusult = sysForgetMapper.insert(sysForget);
        }
        if (rusult > 0) {
            return RestResponse.success("发送成功");
        }
        return RestResponse.failure("发送失败");
    }

    @Override
    public RestResponse<String> reset(SysForget forget) {
        if (forget == null) {
            throw new WebException(WebEnum.ERROR_0002);
        }
        Integer rtype = forget.getRtype();
        if (rtype == null || rtype.equals(0)) {
            throw new WebException(WebEnum.ERROR_0022);
        }
        String rvalue = forget.getRvalue();
        if (StringUtils.isBlank(rvalue)) {
            throw new WebException(WebEnum.ERROR_0023);
        }
        String captcha = forget.getCaptcha();
        if (StringUtils.isBlank(captcha)) {
            throw new WebException(WebEnum.ERROR_0029);
        }
        String password = forget.getPassword();
        if (StringUtils.isBlank(password)) {
            throw new WebException(WebEnum.ERROR_0005);
        }
        SysUser sysUser = new SysUser();
        if (CommonConsts.RTYPE_1.equals(rtype)) {
            if (!RegexpUtils.isEmail(rvalue)) {
                throw new WebException(WebEnum.ERROR_0024);
            }
            sysUser.setEmail(rvalue);
            QueryWrapper<SysUser> userQueryWrapper = new QueryWrapper<>(sysUser);
            sysUser = sysUserMapper.selectOne(userQueryWrapper);
            if (sysUser == null) {
                throw new WebException(WebEnum.ERROR_0025);
            }
        } else if (CommonConsts.RTYPE_2.equals(rtype)) {
            if (!RegexpUtils.isPhone(rvalue)) {
                throw new WebException(WebEnum.ERROR_0026);
            }
            sysUser.setPhone(rvalue);
            QueryWrapper<SysUser> userQueryWrapper = new QueryWrapper<>(sysUser);
            sysUser = sysUserMapper.selectOne(userQueryWrapper);
            if (sysUser == null) {
                throw new WebException(WebEnum.ERROR_0027);
            }
        }
        SysForget sysForget = new SysForget();
        sysForget.setUserid(sysUser.getId());
        QueryWrapper<SysForget> forgetQueryWrapper = new QueryWrapper<>(sysForget);
        sysForget = sysForgetMapper.selectOne(forgetQueryWrapper);
        if (sysForget == null) {
            throw new WebException(WebEnum.ERROR_0030);
        }
        if (CommonConsts.COMMON_1.equals(sysForget.getUsed())) {
            throw new WebException(WebEnum.ERROR_0031);
        }
        if (DateUtils.compareTwoTime(LocalDateTime.now(), sysForget.getExpiredtime()) > 0) {
            throw new WebException(WebEnum.ERROR_0032);
        }
        if (!captcha.equals(sysForget.getCaptcha())) {
            throw new WebException(WebEnum.ERROR_0033);
        }
        sysUser.setPassword(passwordEncoder.encode(password));
        // 密码有效期100天
        sysUser.setExpiredtime(DateUtils.addTime(LocalDateTime.now(), ChronoUnit.DAYS, 100));
        if (sysUserMapper.updateById(sysUser) > 0) {
            sysForget.setUsed(CommonConsts.COMMON_1);
            sysForgetMapper.updateById(sysForget);
            return RestResponse.success("重置成功");
        }
        return RestResponse.failure("重置失败");
    }

}
