package com.xixiwan.platform.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xixiwan.platform.base.BaseMvcController;
import com.xixiwan.platform.module.common.rest.RestResponse;
import com.xixiwan.platform.constant.CommonConsts;
import com.xixiwan.platform.sys.entity.SysLogOperation;
import com.xixiwan.platform.sys.entity.SysMenu;
import com.xixiwan.platform.sys.entity.SysUser;
import com.xixiwan.platform.sys.form.SysLogOperationForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 操作日志 前端控制器
 * </p>
 *
 * @author Sente
 * @since 2018-11-13
 */
@Controller
@RequestMapping("/sys/logOperation")
public class SysLogOperationController extends BaseMvcController {

    @GetMapping("/list")
    public ModelAndView list(Map<String, Object> model, @RequestParam("code") String code) {
        SysUser sysUser = getSysUser();
        SysMenu sysMenu = selectMenuMoreByCode(code);
        model.put("sysMenu", sysMenu);
        List<SysMenu> menuList = getMenuList(sysUser.getId(), code, CommonConsts.MTYPE_3, null);
        model.put("menuList", menuList);
        return new ModelAndView("sys/logOperationList", model);
    }

    @GetMapping("/selectPage")
    @ResponseBody
    public RestResponse<IPage<SysLogOperation>> selectPage(SysLogOperationForm logOperationForm) {
        return RestResponse.success(sysLogOperationService.selectPage(logOperationForm));
    }

}
