package com.xixiwan.platform.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.xixiwan.platform.module.web.constant.WebConsts;
import com.xixiwan.platform.module.web.form.BaseForm;
import com.xixiwan.platform.sys.entity.SysLogOperation;
import com.xixiwan.platform.sys.form.SysLogOperationForm;
import com.xixiwan.platform.sys.mapper.SysLogOperationMapper;
import com.xixiwan.platform.sys.service.CommonService;
import com.xixiwan.platform.sys.service.ISysLogOperationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 * 操作日志 服务实现类
 * </p>
 *
 * @author Sente
 * @since 2018-11-13
 */
@Service
@Transactional
public class SysLogOperationServiceImpl extends ServiceImpl<SysLogOperationMapper, SysLogOperation>
        implements ISysLogOperationService {

    @Resource
    private CommonService commonService;

    @Resource
    private SysLogOperationMapper sysLogOperationMapper;

    @Override
    public IPage<SysLogOperation> selectPage(SysLogOperationForm logOperationForm) {
        logOperationForm.setSortNames(Lists.newArrayList("createtime"));
        logOperationForm.setSortOrders(WebConsts.SORTORDER_DESC);
        Page<SysLogOperation> page = commonService.getPage(logOperationForm);
        BaseForm form = commonService.getBaseForm(logOperationForm);
        QueryWrapper<SysLogOperation> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("createtime", form.getStartDateTime(), form.getEndDateTime());
        String username = logOperationForm.getUsername();
        if (StringUtils.isNotBlank(username)) {
            queryWrapper.eq("username", username);
        }
        String name = logOperationForm.getName();
        if (StringUtils.isNotBlank(name)) {
            queryWrapper.like("name", name);
        }
        String ip = logOperationForm.getIp();
        if (StringUtils.isNotBlank(ip)) {
            queryWrapper.like("ip", ip);
        }
        String requestpath = logOperationForm.getRequestpath();
        if (StringUtils.isNotBlank(requestpath)) {
            queryWrapper.like("requestpath", requestpath);
        }
        String packagename = logOperationForm.getPackagename();
        if (StringUtils.isNotBlank(packagename)) {
            queryWrapper.like("packagename", packagename);
        }
        String methodname = logOperationForm.getMethodname();
        if (StringUtils.isNotBlank(methodname)) {
            queryWrapper.like("methodname", methodname);
        }
        String methoddescribe = logOperationForm.getMethoddescribe();
        if (StringUtils.isNotBlank(methoddescribe)) {
            queryWrapper.like("methoddescribe", methoddescribe);
        }
        String parameters = logOperationForm.getParameters();
        if (StringUtils.isNotBlank(parameters)) {
            queryWrapper.like("parameters", parameters);
        }
        return sysLogOperationMapper.selectPage(page, queryWrapper);
    }

}
