package com.xixiwan.platform.sys.entity;

import com.xixiwan.platform.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户和角色关联表
 * </p>
 *
 * @author Sente
 * @since 2018-09-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysUserRole extends BaseEntity<SysUserRole> {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户id
	 */
	private Integer userid;

	/**
	 * 角色id
	 */
	private Integer roleid;

}
