package com.xixiwan.platform.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.xixiwan.platform.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 通知表
 * </p>
 *
 * @author Sente
 * @since 2018-11-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysNotice extends BaseEntity<SysNotice> {

	private static final long serialVersionUID = 1L;

	/**
	 * 通知类型
	 */
	private String type;

	/**
	 * 对象类型（1：单个用户 2：多个用户 3：所有用户）
	 */
	private Integer objecttype;

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 内容
	 */
	private String content;

	/**
	 * 修改时间
	 */
	private LocalDateTime modifytime;

	/**
	 * 创建时间
	 */
	private LocalDateTime createtime;

	/**
	 * 通知状态（1：未发布 2：已发布 3：已撤销）
	 */
	private Integer status;

	/**
	 * 类型名称
	 */
	@TableField(exist = false)
	private String typename;

	/**
	 * 图标
	 */
	@TableField(exist = false)
	private String icon;

	/**
	 * 通知用户id
	 */
	@TableField(exist = false)
	private Integer nuid;

	/**
	 * 是否阅读（1：是 0：否）
	 */
	@TableField(exist = false)
	private Integer isread;

	/**
	 * 阅读时间
	 */
	@TableField(exist = false)
	private LocalDateTime readtime;

}
