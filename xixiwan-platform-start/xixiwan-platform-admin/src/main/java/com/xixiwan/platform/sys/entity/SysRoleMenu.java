package com.xixiwan.platform.sys.entity;

import com.xixiwan.platform.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色和菜单关联表
 * </p>
 *
 * @author Sente
 * @since 2018-09-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysRoleMenu extends BaseEntity<SysRoleMenu> {

	private static final long serialVersionUID = 1L;

	/**
	 * 角色id
	 */
	private Integer roleid;

	/**
	 * 菜单id
	 */
	private Integer menuid;

}
