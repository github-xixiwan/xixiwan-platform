package com.xixiwan.platform.module.web.util;

import org.apache.commons.lang3.exception.ExceptionUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

public final class IpUtils {

    // ip headers
    private static final String[] IP_HEADERS_TO_TRY = {
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR"};

    /**
     * 获取请求来源IP
     *
     * @param request
     * @return IP，找不到时为null
     */
    public static String getClientIpAddress(HttpServletRequest request) {
        boolean found = false;
        String ip = null;
        for (int index = 0; index < IP_HEADERS_TO_TRY.length && !found; index++) {
            ip = request.getHeader(IP_HEADERS_TO_TRY[index]);
            if (ip != null && !ip.isEmpty() && !"unknown".equalsIgnoreCase(ip)) {
                if (index == 0) { // forwarded
                    int spIndex = ip.indexOf(",");
                    if (spIndex > 0) {
                        ip = ip.substring(0, spIndex);
                    }
                }
                found = true;
            }
        }
        if (!found) {
            ip = request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "localhost" : ip;
    }

    /**
     * 获得MAC地址
     *
     * @param ip
     * @return
     */
    public static String getMACAddress(String ip) {
        String str = "";
        String macAddress = "";
        try {
            Process p = Runtime.getRuntime().exec("nbtstat -A " + ip);
            InputStreamReader ir = new InputStreamReader(p.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            for (int i = 1; i < 100; i++) {
                str = input.readLine();
                if (str != null) {
                    if (str.indexOf("MAC Address") > 1) {
                        macAddress = str.substring(str.indexOf("MAC Address") + 14);
                        break;
                    }
                }
            }
        } catch (IOException e) {
            return ExceptionUtils.rethrow(e);
        }
        return macAddress;
    }
}
