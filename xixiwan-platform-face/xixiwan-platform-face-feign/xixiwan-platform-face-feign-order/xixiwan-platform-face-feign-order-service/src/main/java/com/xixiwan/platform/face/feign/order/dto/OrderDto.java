package com.xixiwan.platform.face.feign.order.dto;

import com.xixiwan.platform.face.feign.order.entity.Order;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class OrderDto extends Order {

	private static final long serialVersionUID = 1L;

	/**
	 * 商品名称
	 */
	private String name;

	/**
	 * 展示图片
	 */
	private String picImg;

}
