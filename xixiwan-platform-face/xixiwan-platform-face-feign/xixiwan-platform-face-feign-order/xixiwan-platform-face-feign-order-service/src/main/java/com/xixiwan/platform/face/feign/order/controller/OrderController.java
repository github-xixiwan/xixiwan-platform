package com.xixiwan.platform.face.feign.order.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xixiwan.platform.face.feign.basic.api.BasicApi;
import com.xixiwan.platform.face.feign.order.base.BaseMvcController;
import com.xixiwan.platform.face.feign.order.dto.OrderDto;
import com.xixiwan.platform.face.feign.order.entity.Order;
import com.xixiwan.platform.face.feign.order.form.OrderForm;
import com.xixiwan.platform.face.feign.order.service.IOrderService;
import com.xixiwan.platform.module.common.rest.RestResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author Sente
 * @since 2018-11-19
 */
@RestController
@RequestMapping("/order")
public class OrderController extends BaseMvcController {

    @Resource
    private BasicApi basicApi;

    @Resource
    private IOrderService orderService;

    @PostMapping("/addOrder")
    public RestResponse<String> addOrder(Order order) {
        return orderService.addOrder(order);
    }

    @GetMapping("/selectPageByForm")
    public RestResponse<IPage<OrderDto>> selectPageByForm(OrderForm orderForm) {
        return RestResponse.success(orderService.selectPageByForm(orderForm));
    }

    @GetMapping("/hello")
    public RestResponse<String> hello(String username) {
        return basicApi.hello(username);
    }

}
