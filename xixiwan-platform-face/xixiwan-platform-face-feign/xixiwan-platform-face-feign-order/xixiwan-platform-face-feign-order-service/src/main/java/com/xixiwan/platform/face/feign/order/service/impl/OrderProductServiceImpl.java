package com.xixiwan.platform.face.feign.order.service.impl;

import com.xixiwan.platform.face.feign.order.entity.OrderProduct;
import com.xixiwan.platform.face.feign.order.mapper.OrderProductMapper;
import com.xixiwan.platform.face.feign.order.service.IOrderProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单明细表 服务实现类
 * </p>
 *
 * @author Sente
 * @since 2018-11-19
 */
@Service
public class OrderProductServiceImpl extends ServiceImpl<OrderProductMapper, OrderProduct> implements IOrderProductService {

}
