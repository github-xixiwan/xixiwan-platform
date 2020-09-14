package com.xixiwan.platform.face.feign.order.entity;

import java.math.BigDecimal;

import com.xixiwan.platform.face.feign.order.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单明细表
 * </p>
 *
 * @author Sente
 * @since 2018-11-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class OrderProduct extends BaseEntity<OrderProduct> {

	private static final long serialVersionUID = 1L;

	/**
	 * 订单ID
	 */
	private Long orderId;

	/**
	 * 商品ID
	 */
	private Long productId;

	/**
	 * 商品编号
	 */
	private Long productNumber;

	/**
	 * 用户ID
	 */
	private Long userId;

	/**
	 * 商品名称
	 */
	private String name;

	/**
	 * 展示图片
	 */
	private String picImg;

	/**
	 * 商品规格编号
	 */
	private Long productSpecNumber;

	/**
	 * 商品规格名称
	 */
	private String productSpecName;

	/**
	 * 价格
	 */
	private BigDecimal price;

	/**
	 * 积分
	 */
	private Integer score;

	/**
	 * 商品总数量
	 */
	private Integer buyNumber;

	/**
	 * 商品总积分
	 */
	private Integer productScore;

	/**
	 * 商品总金额
	 */
	private BigDecimal productAmount;

	/**
	 * 评论状态 0=未评论，1=已评论
	 */
	private Integer commentStatus;

}
