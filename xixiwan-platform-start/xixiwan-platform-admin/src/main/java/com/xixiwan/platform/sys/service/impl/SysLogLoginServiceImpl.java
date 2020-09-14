package com.xixiwan.platform.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.xixiwan.platform.module.web.constant.WebConsts;
import com.xixiwan.platform.module.web.form.BaseForm;
import com.xixiwan.platform.sys.entity.SysLogLogin;
import com.xixiwan.platform.sys.entity.SysUser;
import com.xixiwan.platform.sys.form.SysLogLoginForm;
import com.xixiwan.platform.sys.mapper.SysLogLoginMapper;
import com.xixiwan.platform.sys.service.CommonService;
import com.xixiwan.platform.sys.service.ISysLogLoginService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 * 登录记录 服务实现类
 * </p>
 *
 * @author Sente
 * @since 2018-11-13
 */
@Service
@Transactional
public class SysLogLoginServiceImpl extends ServiceImpl<SysLogLoginMapper, SysLogLogin> implements ISysLogLoginService {

    @Resource
    private CommonService commonService;

    @Resource
    private SysLogLoginMapper sysLogLoginMapper;

    @Override
    public IPage<SysLogLogin> selectPage(SysLogLoginForm logLoginForm) {
        logLoginForm.setSortNames(Lists.newArrayList("createtime"));
        logLoginForm.setSortOrders(WebConsts.SORTORDER_DESC);
        Page<SysLogLogin> page = commonService.getPage(logLoginForm);
        BaseForm form = commonService.getBaseForm(logLoginForm);
        QueryWrapper<SysLogLogin> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("createtime", form.getStartDateTime(), form.getEndDateTime());
        String username = logLoginForm.getUsername();
        if (StringUtils.isNotBlank(username)) {
            queryWrapper.eq("username", username);
        }
        String name = logLoginForm.getName();
        if (StringUtils.isNotBlank(name)) {
            queryWrapper.like("name", name);
        }
        String ip = logLoginForm.getIp();
        if (StringUtils.isNotBlank(ip)) {
            queryWrapper.like("ip", ip);
        }
        return sysLogLoginMapper.selectPage(page, queryWrapper);
    }

    @Override
    public void addLogLogin(SysUser user, String ip) {
        SysLogLogin sysLogLogin = new SysLogLogin();
        sysLogLogin.setUserid(user.getId());
        sysLogLogin.setUsername(user.getUsername());
        sysLogLogin.setName(user.getName());
        sysLogLogin.setIp(ip);
        sysLogLoginMapper.insert(sysLogLogin);
    }

}
