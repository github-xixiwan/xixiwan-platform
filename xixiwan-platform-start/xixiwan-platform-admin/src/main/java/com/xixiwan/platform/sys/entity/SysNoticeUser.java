package com.xixiwan.platform.sys.entity;

import com.xixiwan.platform.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 通知和用户关联表
 * </p>
 *
 * @author Sente
 * @since 2018-11-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysNoticeUser extends BaseEntity<SysNoticeUser> {

	private static final long serialVersionUID = 1L;

	/**
	 * 通知id
	 */
	private Integer noticeid;

	/**
	 * 用户id
	 */
	private Integer userid;

	/**
	 * 是否阅读（1：是 0：否）
	 */
	private Integer isread;

	/**
	 * 阅读时间
	 */
	private LocalDateTime readtime;

}
