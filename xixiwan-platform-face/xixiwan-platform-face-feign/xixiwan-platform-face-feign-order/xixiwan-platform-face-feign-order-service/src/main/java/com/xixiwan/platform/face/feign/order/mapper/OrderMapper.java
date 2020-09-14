package com.xixiwan.platform.face.feign.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xixiwan.platform.face.feign.order.dto.OrderDto;
import com.xixiwan.platform.face.feign.order.entity.Order;
import com.xixiwan.platform.face.feign.order.form.OrderForm;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author Sente
 * @since 2018-11-19
 */
public interface OrderMapper extends BaseMapper<Order> {

    IPage<OrderDto> selectPageByForm(Page<Order> page, @Param("orderForm") OrderForm orderForm);

}
