package com.xixiwan.platform.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.xixiwan.platform.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 忘记密码表
 * </p>
 *
 * @author Sente
 * @since 2018-10-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysForget extends BaseEntity<SysForget> {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户id
	 */
	private Integer userid;

	/**
	 * 重置类型(1：邮箱 2：手机）
	 */
	private Integer rtype;

	/**
	 * 重置值
	 */
	private String rvalue;

	/**
	 * 验证码
	 */
	private String captcha;

	/**
	 * 是否使用(1：使用 0：未用）
	 */
	private Integer used;

	/**
	 * 验证码过期时间
	 */
	private LocalDateTime expiredtime;

	/**
	 * 创建时间
	 */
	private LocalDateTime createtime;

	/**
	 * 密码
	 */
	@TableField(exist = false)
	private String password;

}
