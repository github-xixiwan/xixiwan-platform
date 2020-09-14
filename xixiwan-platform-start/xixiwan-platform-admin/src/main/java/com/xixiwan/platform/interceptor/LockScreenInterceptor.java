package com.xixiwan.platform.interceptor;

import com.xixiwan.platform.base.BaseMvcController;
import com.xixiwan.platform.constant.CommonConsts;
import com.xixiwan.platform.sys.entity.SysUser;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LockScreenInterceptor extends BaseMvcController implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		SysUser sysUser = getSysUser();
		if (sysUser != null && CommonConsts.STATUS_5.equals(sysUser.getStatus())) {
			modelAndView.setViewName("/lockScreen");
		}
	}
}
