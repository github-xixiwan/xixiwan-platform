package com.xixiwan.platform.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.xixiwan.platform.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 字典表
 * </p>
 *
 * @author Sente
 * @since 2018-11-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysDict extends BaseEntity<SysDict> {

	private static final long serialVersionUID = 1L;

	/**
	 * 字典编码
	 */
	private String code;

	/**
	 * 字典名称
	 */
	private String name;

	/**
	 * 字典父编码
	 */
	private String pcode;

	/**
	 * 排序
	 */
	private Integer num;

	/**
	 * 图标
	 */
	private String icon;

	/**
	 * 提示
	 */
	private String tips;

	/**
	 * 字典父名称
	 */
	@TableField(exist = false)
	private String pname;

}
