package com.xixiwan.platform.interceptor;

import com.xixiwan.platform.annotation.Operation;
import com.xixiwan.platform.base.BaseMvcController;
import com.xixiwan.platform.module.common.util.JacksonUtils;
import com.xixiwan.platform.module.web.util.IpUtils;
import com.xixiwan.platform.sys.entity.SysLogOperation;
import com.xixiwan.platform.sys.entity.SysUser;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class LogOperationInterceptor extends BaseMvcController implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            SysUser sysUser = getSysUser();
            if (sysUser != null) {
                SysLogOperation sysLogOperation = new SysLogOperation();
                sysLogOperation.setUserid(sysUser.getId());
                sysLogOperation.setUsername(sysUser.getUsername());
                sysLogOperation.setName(sysUser.getName());
                sysLogOperation.setIp(IpUtils.getClientIpAddress(request));
                sysLogOperation.setRequestpath(request.getRequestURI());
                String packagename = method.getBean().getClass().getName();
                if (packagename.contains("$$")) {
                    packagename = packagename.substring(0, packagename.indexOf("$$"));
                }
                sysLogOperation.setPackagename(packagename);
                sysLogOperation.setMethod(request.getMethod());
                sysLogOperation.setMethodname(method.getMethod().getName());
                Operation operation = method.getMethodAnnotation(Operation.class);
                sysLogOperation.setMethoddescribe(operation != null ? operation.describe() : "");
                Map<String, String[]> pramMap = request.getParameterMap();
                sysLogOperation
                        .setParameters(pramMap != null && pramMap.size() > 0 ? JacksonUtils.mapToJson(pramMap) : "");
                sysLogOperationService.save(sysLogOperation);
            }
        }
        return true;
    }

}
