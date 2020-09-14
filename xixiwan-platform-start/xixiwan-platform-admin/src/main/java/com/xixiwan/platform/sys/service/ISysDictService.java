package com.xixiwan.platform.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xixiwan.platform.module.common.rest.RestResponse;
import com.xixiwan.platform.sys.entity.SysDict;
import com.xixiwan.platform.sys.form.SysDictForm;

import java.util.List;

/**
 * <p>
 * 字典表 服务类
 * </p>
 *
 * @author Sente
 * @since 2018-11-12
 */
public interface ISysDictService extends IService<SysDict> {

    IPage<SysDict> selectPage(SysDictForm dictForm);

    List<SysDict> selectList(SysDictForm dictForm);

    RestResponse<String> addDict(SysDict dict);

    RestResponse<String> editDict(SysDict dict);

    RestResponse<String> deleteDict(SysDictForm dictForm);

}
