package com.xixiwan.platform.face.feign.order.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.xixiwan.platform.face.feign.order.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author Sente
 * @since 2018-11-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Order extends BaseEntity<Order> {

	private static final long serialVersionUID = 1L;

	/**
	 * 订单编号
	 */
	private Long orderNumber;

	/**
	 * 用户ID
	 */
	private Long userId;

	/**
	 * 支付方式 0=线下支付，1=在线支付
	 */
	private Integer payType;

	/**
	 * 配送时间 1=不限送货时间，2=工作日送货，3=双休日、假日送货
	 */
	private Integer shipmentTime;

	/**
	 * 配送方式 0=快递配送（免运费），1=快递配送（运费）
	 */
	private Integer shipmentType;

	/**
	 * 快递费
	 */
	private BigDecimal shipmentAmount;

	/**
	 * 支付方式 1=不开发票，2=电子发票，3=普通发票
	 */
	private Integer invoiceType;

	/**
	 * 发票抬头
	 */
	private String invoiceTitle;

	/**
	 * 订单状态
	 */
	private Integer orderStatus;

	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;

	/**
	 * 更新时间
	 */
	private LocalDateTime updateTime;

	/**
	 * 订单金额
	 */
	private BigDecimal orderAmount;

	/**
	 * 订单积分
	 */
	private Integer orderScore;

	/**
	 * 支付金额 = 订单金额 + 快递费
	 */
	private BigDecimal payAmount;

	/**
	 * 商品总数量
	 */
	private Integer buyNumber;

}
