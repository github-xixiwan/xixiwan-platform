package com.xixiwan.platform.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.xixiwan.platform.module.common.rest.RestResponse;
import com.xixiwan.platform.module.web.constant.WebConsts;
import com.xixiwan.platform.constant.CommonConsts;
import com.xixiwan.platform.exception.WebException;
import com.xixiwan.platform.exception.enums.WebEnum;
import com.xixiwan.platform.sys.entity.SysDict;
import com.xixiwan.platform.sys.form.SysDictForm;
import com.xixiwan.platform.sys.mapper.SysDictMapper;
import com.xixiwan.platform.sys.service.CommonService;
import com.xixiwan.platform.sys.service.ISysDictService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 字典表 服务实现类
 * </p>
 *
 * @author Sente
 * @since 2018-11-12
 */
@Service
@Transactional
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements ISysDictService {

    @Resource
    private CommonService commonService;

    @Resource
    private SysDictMapper sysDictMapper;

    @Override
    public IPage<SysDict> selectPage(SysDictForm dictForm) {
        dictForm.setSortNames(Lists.newArrayList("code", "num"));
        dictForm.setSortOrders(WebConsts.SORTORDER_ASC);
        Page<SysDict> page = commonService.getPage(dictForm);
        QueryWrapper<SysDict> queryWrapper = new QueryWrapper<>();
        String pcode = dictForm.getPcode();
        if (StringUtils.isNotBlank(pcode)) {
            queryWrapper.eq("pcode", pcode);
        }
        String name = dictForm.getName();
        if (StringUtils.isNotBlank(name)) {
            queryWrapper.like("name", name);
        }
        IPage<SysDict> iPage = sysDictMapper.selectPage(page, queryWrapper);
        if (iPage != null && iPage.getTotal() > 0) {
            List<SysDict> list = iPage.getRecords();
            for (SysDict sysDict : list) {
                sysDict.setPname(commonService.getDictNameByCode(sysDict.getPcode(), CommonConsts.TOP_LEVEL));
            }
        }
        return iPage;
    }

    @Override
    public List<SysDict> selectList(SysDictForm dictForm) {
        QueryWrapper<SysDict> queryWrapper = new QueryWrapper<>();
        String pcode = dictForm.getPcode();
        if (StringUtils.isNotBlank(pcode)) {
            queryWrapper.eq("pcode", pcode);
        }
        return sysDictMapper.selectList(queryWrapper);
    }

    @Override
    public RestResponse<String> addDict(SysDict dict) {
        if (dict == null) {
            throw new WebException(WebEnum.ERROR_0002);
        }
        String code = dict.getCode();
        if (StringUtils.isBlank(code)) {
            throw new WebException(WebEnum.ERROR_0009);
        }
        String pcode = dict.getPcode();
        if (StringUtils.isBlank(pcode)) {
            throw new WebException(WebEnum.ERROR_0038);
        }
        if (CommonConsts.TOP_LEVEL.equals(pcode)
                && commonService.selectDictByCode(code, CommonConsts.TOP_LEVEL) != null) {
            throw new WebException(WebEnum.ERROR_0010);
        }
        if (sysDictMapper.insert(dict) > 0) {
            return RestResponse.success("保存成功");
        }
        return RestResponse.failure("保存失败");
    }

    @Override
    public RestResponse<String> editDict(SysDict dict) {
        if (dict == null) {
            throw new WebException(WebEnum.ERROR_0002);
        }
        Integer id = dict.getId();
        if (id == null) {
            throw new WebException(WebEnum.ERROR_0011);
        }
        String code = dict.getCode();
        if (StringUtils.isBlank(code)) {
            throw new WebException(WebEnum.ERROR_0009);
        }
        String pcode = dict.getPcode();
        if (StringUtils.isBlank(pcode)) {
            throw new WebException(WebEnum.ERROR_0038);
        }
        SysDict sysDict = sysDictMapper.selectById(id);
        if (sysDict == null) {
            throw new WebException(WebEnum.ERROR_0012);
        }
        String originalPcode = sysDict.getCode();
        if (CommonConsts.TOP_LEVEL.equals(pcode)) {
            sysDict = commonService.selectDictByCode(code, CommonConsts.TOP_LEVEL);
            if (sysDict != null && !sysDict.getId().equals(id)) {
                throw new WebException(WebEnum.ERROR_0010);
            }
        }
        if (sysDictMapper.updateById(dict) > 0) {
            // 更新子级条件
            SysDict childrenConditionDict = new SysDict();
            childrenConditionDict.setPcode(originalPcode);
            Wrapper<SysDict> sysDictUpdateWrapper = new QueryWrapper<>(childrenConditionDict);
            // 更新子级内容
            SysDict childrenContentDict = new SysDict();
            childrenContentDict.setPcode(code);
            sysDictMapper.update(childrenContentDict, sysDictUpdateWrapper);
            return RestResponse.success("修改成功");
        }
        return RestResponse.failure("修改失败");
    }

    @Override
    public RestResponse<String> deleteDict(SysDictForm dictForm) {
        if (dictForm == null) {
            throw new WebException(WebEnum.ERROR_0002);
        }
        Integer[] ids = dictForm.getIds();
        if (ids == null || ids.length == 0) {
            throw new WebException(WebEnum.ERROR_0011);
        }
        int num = 0;
        for (Integer id : ids) {
            SysDict sysDict = sysDictMapper.selectById(id);
            if (sysDict == null) {
                continue;
            }
            if (sysDictMapper.deleteById(id) > 0) {
                num++;
                // 删除子级
                SysDict sysDictQuery = new SysDict();
                sysDictQuery.setPcode(sysDict.getCode());
                Wrapper<SysDict> sysDictQueryWrapper = new QueryWrapper<>(sysDictQuery);
                sysDictMapper.delete(sysDictQueryWrapper);
            }
        }
        if (num > 0) {
            return RestResponse.success("删除成功");
        }
        return RestResponse.failure("删除失败");
    }

}
