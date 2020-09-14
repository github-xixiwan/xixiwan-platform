package com.xixiwan.platform.module.web.util;

import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/***
 * @see org.springframework.web.filter.AbstractRequestLoggingFilter#getMessagePayload(HttpServletRequest)
 */
public final class PayloadUtils {
    public static String getPayload(HttpServletRequest request) {
        ContentCachingRequestWrapper wrapper =
                WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
        if (wrapper != null) {
            return bytesToString(wrapper.getContentAsByteArray(), wrapper.getCharacterEncoding());
        }
        return null;
    }

    public static String getPayload(HttpServletResponse response) {
        ContentCachingResponseWrapper wrapper =
                WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
        if (wrapper != null) {
            return bytesToString(wrapper.getContentAsByteArray(), wrapper.getCharacterEncoding());
        }
        return null;
    }

    private static String bytesToString(byte[] buf, String encoding) {
        if (buf != null && buf.length > 0) {
            try {
                return new String(buf, 0, buf.length, encoding);
            } catch (UnsupportedEncodingException ex) {
                return "[unknown]";
            }
        } else {
            return "";
        }
    }
}
