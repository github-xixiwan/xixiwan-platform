package com.xixiwan.platform.sys.controller;

import com.xixiwan.platform.annotation.Operation;
import com.xixiwan.platform.base.BaseMvcController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * <p>
 * 通知和用户关联表 前端控制器
 * </p>
 *
 * @author Sente
 * @since 2018-11-15
 */
@Controller
@RequestMapping("/sys/noticeUser")
public class SysNoticeUserController extends BaseMvcController {

    @GetMapping("/readNotice")
    @ResponseBody
    @Operation(describe = "阅读通知数据")
    public ModelAndView readNotice(Map<String, Object> model, @RequestParam("id") Integer id) {
        model.put("sysNotice", sysNoticeUserService.readNotice(getSysUser(), id));
        return new ModelAndView("index/readNotice", model);
    }

}
