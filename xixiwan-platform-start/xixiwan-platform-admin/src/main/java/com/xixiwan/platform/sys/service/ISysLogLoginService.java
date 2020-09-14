package com.xixiwan.platform.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xixiwan.platform.sys.entity.SysLogLogin;
import com.xixiwan.platform.sys.entity.SysUser;
import com.xixiwan.platform.sys.form.SysLogLoginForm;

/**
 * <p>
 * 登录记录 服务类
 * </p>
 *
 * @author Sente
 * @since 2018-11-13
 */
public interface ISysLogLoginService extends IService<SysLogLogin> {

	IPage<SysLogLogin> selectPage(SysLogLoginForm logLoginForm);

	void addLogLogin(SysUser user, String ip);

}
