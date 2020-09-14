package com.xixiwan.platform.sys.entity;

import com.xixiwan.platform.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 登录记录
 * </p>
 *
 * @author Sente
 * @since 2018-11-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysLogLogin extends BaseEntity<SysLogLogin> {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户id
	 */
	private Integer userid;

	/**
	 * 账号
	 */
	private String username;

	/**
	 * 名字
	 */
	private String name;

	/**
	 * 登录ip
	 */
	private String ip;

	/**
	 * 创建时间
	 */
	private LocalDateTime createtime;

}
