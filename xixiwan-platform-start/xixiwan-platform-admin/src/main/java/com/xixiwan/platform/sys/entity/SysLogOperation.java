package com.xixiwan.platform.sys.entity;

import com.xixiwan.platform.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 操作日志
 * </p>
 *
 * @author Sente
 * @since 2018-11-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysLogOperation extends BaseEntity<SysLogOperation> {

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
	 * 请求ip
	 */
	private String ip;

	/**
	 * 请求路径
	 */
	private String requestpath;

	/**
	 * 包名
	 */
	private String packagename;

	/**
	 * 请求方式
	 */
	private String method;

	/**
	 * 方法名称
	 */
	private String methodname;

	/**
	 * 方法描述
	 */
	private String methoddescribe;

	/**
	 * 请求参数
	 */
	private String parameters;

	/**
	 * 创建时间
	 */
	private LocalDateTime createtime;

}
