package com.xixiwan.platform.module.web.util;

import com.xixiwan.platform.module.common.exception.BaseException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class LogUtils {
    public static final String REQUEST_START_TIME = "request-start-time";

    private static final Logger gateLog = LoggerFactory.getLogger("gatelog");
    private static final Logger exLog = LoggerFactory.getLogger("ex");

    public static void setStartTime(HttpServletRequest request) {
        request.setAttribute(REQUEST_START_TIME, System.currentTimeMillis());
    }

    public static Long getStartTime(HttpServletRequest request) {
        Object startTimeValue = request.getAttribute(REQUEST_START_TIME);
        return startTimeValue instanceof Long ? (Long) startTimeValue : null;
    }

    public static Long getDuration(HttpServletRequest request) {
        Long startTime = getStartTime(request);
        return startTime == null ? null : System.currentTimeMillis() - startTime;
    }

    public static void logSuccess(HttpServletRequest request, HttpServletResponse response) {
        log(request, response, null);
    }

    public static void logError(HttpServletRequest request, Exception ex) {
        log(request, null, ex);
    }

    private static void log(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        String method = request.getMethod();
        String uri = request.getServletPath();
        String ip = IpUtils.getClientIpAddress(request);
        Long durantion = getDuration(request);
        StringBuilder sb = new StringBuilder(512);
        sb.append(" ").append(method);
        sb.append(" ").append(uri);
        sb.append(" ").append(ip);
        sb.append(" ").append(durantion);
        if (response != null) {
            sb.append(" ").append(response.getStatus());
        }
        sb.append(" ").append(request.getQueryString());
        sb.append(" ").append(PayloadUtils.getPayload(request));
        if (response != null) {
            sb.append(" ").append(PayloadUtils.getPayload(response));
        }
        if (ex != null) {
            sb.append(" ").append(ex instanceof BaseException ? ex.getMessage() : ExceptionUtils.getStackTrace(ex));
        }
        String logContent = sb.toString();
        // write out
        if (ex != null) {
            exLog.error(logContent);
        } else {
            gateLog.info(logContent);
        }
    }
}
