package com.xixiwan.platform.face.dubbo.basic.base;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseMvcController {

    @Resource
    protected HttpServletRequest request;

    @Resource
    protected HttpServletResponse response;

}
