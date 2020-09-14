package com.xixiwan.platform.module.web.handler;

import com.xixiwan.platform.module.common.exception.BaseException;
import com.xixiwan.platform.module.common.rest.RestResponse;
import com.xixiwan.platform.module.web.constant.ErrorCodeConsts;
import com.xixiwan.platform.module.web.util.LogUtils;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestControllerAdvice
@Order(10)
public class DefaultExceptionHandler {

    private final ErrorAttributes errorAttributes;

    public DefaultExceptionHandler(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object handelException(HttpServletRequest request, Exception ex) {
        LogUtils.logError(request, ex);
        return handleExceptionInternal(request, ex);
    }

    private Object handleExceptionInternal(HttpServletRequest request, Exception ex) {
        if (ex instanceof BaseException) {
            return handelException((BaseException) ex);
        } else if (ex instanceof NoHandlerFoundException) {
            return handelException((NoHandlerFoundException) ex);
        } else if (ex instanceof MissingServletRequestParameterException) {
            return handelException((MissingServletRequestParameterException) ex);
        } else if (ex instanceof RestClientException) {
            return handelException((RestClientException) ex);
        } else if (ex instanceof HttpMediaTypeNotSupportedException) {
            return failure(ErrorCodeConsts.INVALID_PARAMETER_ERROR, "http mediaType not supported");
        } else if (ex instanceof HttpMessageNotReadableException) {
            return failure(ErrorCodeConsts.INVALID_PARAMETER_ERROR, "http message not readable");
        }
        return failure(request);
    }

    private RestResponse<Void> failure(String code, String message) {
        return RestResponse.failure(code, message);
    }

    private RestResponse<Void> handelException(BaseException ex) {
        return failure(ex.getCode(), ex.getMessage());
    }

    private RestResponse<Void> handelException(MissingServletRequestParameterException ex) {
        return failure(ErrorCodeConsts.INVALID_PARAMETER_ERROR, String.format("[%s]字段不能为空", ex.getParameterName()));
    }

    private RestResponse<Void> handelException(NoHandlerFoundException ex) {
        return failure(ErrorCodeConsts.NOT_FOUND_ERROR, "404NotFound");
    }

    private RestResponse<Void> handelException(RestClientException ex) {
        return failure(ErrorCodeConsts.HTTP_EXCEPTION, "业务接口异常");
    }

    private Object failure(HttpServletRequest request) {
        if (request.getHeader("Accept").startsWith(MediaType.TEXT_HTML_VALUE)) {
            return buildFailedPage(request);
        }
        return buildFailedMessage(request);
    }

    private RestResponse<Void> buildFailedMessage(HttpServletRequest request) {
        Map<String, Object> model = getErrorAttributes(request, true);
        return failure(String.valueOf(model.get("status")), String.valueOf(model.get("message")));
    }

    private ModelAndView buildFailedPage(HttpServletRequest request) {
        Map<String, Object> model = getErrorAttributes(request, true);
        return new ModelAndView("error/500", model);
    }

    private Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
        WebRequest webRequest = new ServletWebRequest(request);
        return errorAttributes.getErrorAttributes(webRequest, includeStackTrace);
    }

}
