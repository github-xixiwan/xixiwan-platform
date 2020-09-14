package com.xixiwan.platform.sys.entity;

import com.xixiwan.platform.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author Sente
 * @since 2018-09-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysRole extends BaseEntity<SysRole> {

	private static final long serialVersionUID = 1L;

	/**
	 * 角色权限
	 */
	private String authorities;

	/**
	 * 角色名称
	 */
	private String name;

	/**
	 * 描述
	 */
	private String description;

	/**
	 * 序号
	 */
	private Integer num;

	/**
	 * 修改时间
	 */
	private LocalDateTime modifytime;

	/**
	 * 创建时间
	 */
	private LocalDateTime createtime;

	/**
	 * 角色状态（1：启用 0：禁用）
	 */
	private Integer status;

}
