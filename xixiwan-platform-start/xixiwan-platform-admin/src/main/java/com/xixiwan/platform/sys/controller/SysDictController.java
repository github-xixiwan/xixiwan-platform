package com.xixiwan.platform.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xixiwan.platform.annotation.Operation;
import com.xixiwan.platform.base.BaseMvcController;
import com.xixiwan.platform.module.common.rest.RestResponse;
import com.xixiwan.platform.constant.CommonConsts;
import com.xixiwan.platform.sys.entity.SysDict;
import com.xixiwan.platform.sys.entity.SysMenu;
import com.xixiwan.platform.sys.entity.SysUser;
import com.xixiwan.platform.sys.form.SysDictForm;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 字典表 前端控制器
 * </p>
 *
 * @author Sente
 * @since 2018-11-12
 */
@Controller
@RequestMapping("/sys/dict")
public class SysDictController extends BaseMvcController {

    @GetMapping("/list")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView list(Map<String, Object> model, @RequestParam("code") String code) {
        SysUser sysUser = getSysUser();
        SysMenu sysMenu = selectMenuMoreByCode(code);
        model.put("sysMenu", sysMenu);
        List<SysMenu> menuList = getMenuList(sysUser.getId(), code, CommonConsts.MTYPE_3, null);
        model.put("menuList", menuList);
        return new ModelAndView("sys/dictList", model);
    }

    @GetMapping("/selectPage")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public RestResponse<IPage<SysDict>> selectPage(SysDictForm dictForm) {
        return RestResponse.success(sysDictService.selectPage(dictForm));
    }

    @GetMapping("/selectList")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public RestResponse<List<SysDict>> selectList(SysDictForm dictForm) {
        return RestResponse.success(sysDictService.selectList(dictForm));
    }

    @PostMapping("/addDict")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(describe = "新增字典数据")
    public RestResponse<String> addDict(SysDict dict) {
        return sysDictService.addDict(dict);
    }

    @PostMapping("/editDict")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(describe = "修改字典数据")
    public RestResponse<String> editDict(SysDict dict) {
        return sysDictService.editDict(dict);
    }

    @PostMapping("/deleteDict")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(describe = "删除字典数据")
    public RestResponse<String> deleteDict(SysDictForm dictForm) {
        return sysDictService.deleteDict(dictForm);
    }

}
