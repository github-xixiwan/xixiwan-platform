package com.xixiwan.platform.face.feign.order.controller;

import com.xixiwan.platform.face.feign.basic.api.BasicApi;
import com.xixiwan.platform.face.feign.order.base.BaseMvcController;
import com.xixiwan.platform.module.common.rest.RestResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * <p>
 * 订单明细表 前端控制器
 * </p>
 *
 * @author Sente
 * @since 2018-11-19
 */
@Controller
@RequestMapping("/order/product")
public class OrderProductController extends BaseMvcController {

    @Resource
    private BasicApi basicApi;

    @GetMapping("/hello")
    public RestResponse<String> hello(String username) {
        return basicApi.hello(username);
    }

}
