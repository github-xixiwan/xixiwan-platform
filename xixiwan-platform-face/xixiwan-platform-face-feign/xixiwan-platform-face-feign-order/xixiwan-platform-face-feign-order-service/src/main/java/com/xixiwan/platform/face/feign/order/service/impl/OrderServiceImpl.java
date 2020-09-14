package com.xixiwan.platform.face.feign.order.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xixiwan.platform.face.feign.order.dto.OrderDto;
import com.xixiwan.platform.face.feign.order.entity.Order;
import com.xixiwan.platform.face.feign.order.entity.OrderProduct;
import com.xixiwan.platform.face.feign.order.form.OrderForm;
import com.xixiwan.platform.face.feign.order.mapper.OrderMapper;
import com.xixiwan.platform.face.feign.order.mapper.OrderProductMapper;
import com.xixiwan.platform.face.feign.order.service.CommonService;
import com.xixiwan.platform.face.feign.order.service.IOrderService;
import com.xixiwan.platform.module.common.rest.RestResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author Sente
 * @since 2018-11-19
 */
@Service
@Transactional
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Resource
    private CommonService commonService;

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private OrderProductMapper orderProductMapper;

    @Override
    public RestResponse<String> addOrder(Order order) {
        Long id = commonService.generateKey();
        order.setId(id);
        order.setOrderNumber(commonService.generateOrderKey());
        order.setCreateTime(LocalDateTime.now());
        if (orderMapper.insert(order) > 0) {
            OrderProduct orderProduct1 = new OrderProduct();
            orderProduct1.setId(commonService.generateKey());
            orderProduct1.setOrderId(id);
            orderProduct1.setUserId(order.getUserId());
            orderProduct1.setName("测试商品1");
            orderProduct1.setPicImg("/a.jpg");
            orderProductMapper.insert(orderProduct1);

            OrderProduct orderProduct2 = new OrderProduct();
            orderProduct2.setId(commonService.generateKey());
            orderProduct2.setOrderId(id);
            orderProduct2.setUserId(order.getUserId());
            orderProduct2.setName("测试商品2");
            orderProduct2.setPicImg("/b.jpg");
            orderProductMapper.insert(orderProduct2);
            return RestResponse.success("新增成功");
        }
        return RestResponse.failure("新增失败");
    }

    @Override
    public IPage<OrderDto> selectPageByForm(OrderForm orderForm) {
        Page<Order> page = commonService.getPage(orderForm);
        return orderMapper.selectPageByForm(page, orderForm);
    }

}
