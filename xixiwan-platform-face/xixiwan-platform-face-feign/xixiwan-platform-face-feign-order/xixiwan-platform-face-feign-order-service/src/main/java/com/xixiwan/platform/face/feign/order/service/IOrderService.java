package com.xixiwan.platform.face.feign.order.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xixiwan.platform.module.common.rest.RestResponse;
import com.xixiwan.platform.face.feign.order.dto.OrderDto;
import com.xixiwan.platform.face.feign.order.entity.Order;
import com.xixiwan.platform.face.feign.order.form.OrderForm;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author Sente
 * @since 2018-09-18
 */
public interface IOrderService extends IService<Order> {

    RestResponse<String> addOrder(Order order);

    IPage<OrderDto> selectPageByForm(OrderForm orderForm);

}