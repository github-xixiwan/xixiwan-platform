package com.xixiwan.platform.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xixiwan.platform.module.common.rest.RestResponse;
import com.xixiwan.platform.sys.entity.SysForget;
import com.xixiwan.platform.sys.form.SysForgetForm;

/**
 * <p>
 * 忘记密码表 服务类
 * </p>
 *
 * @author Sente
 * @since 2018-10-03
 */
public interface ISysForgetService extends IService<SysForget> {

    RestResponse<String> sendCaptcha(SysForgetForm forgetForm);

    RestResponse<String> reset(SysForget forget);

}
