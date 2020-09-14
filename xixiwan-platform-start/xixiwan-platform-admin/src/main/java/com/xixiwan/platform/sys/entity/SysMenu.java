package com.xixiwan.platform.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.xixiwan.platform.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author Sente
 * @since 2018-09-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysMenu extends BaseEntity<SysMenu> {

	private static final long serialVersionUID = 1L;

	/**
	 * 菜单编码
	 */
	private String code;

	/**
	 * 菜单名称
	 */
	private String name;

	/**
	 * 菜单父编码
	 */
	private String pcode;

	/**
	 * 菜单图标
	 */
	private String icon;

	/**
	 * url地址
	 */
	private String url;

	/**
	 * 序号
	 */
	private Integer num;

	/**
	 * 菜单层级
	 */
	private Integer levels;

	/**
	 * 菜单类型（1：目录 2：菜单 3：按钮）
	 */
	private Integer mtype;

	/**
	 * 按钮颜色
	 */
	private String color;

	/**
	 * 小贴士
	 */
	private String tips;

	/**
	 * 菜单状态（1：启用 0：禁用）
	 */
	private Integer status;

	/**
	 * 是否打开（1：是 0：否）
	 */
	private Integer isopen;

	/**
	 * 菜单父名称
	 */
	@TableField(exist = false)
	private String pname;

}
