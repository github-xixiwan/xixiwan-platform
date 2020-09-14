package com.xixiwan.platform.exception;

import com.xixiwan.platform.module.common.exception.BaseException;
import com.xixiwan.platform.exception.enums.WebEnum;

public class WebException extends BaseException {

    private static final long serialVersionUID = 1L;

    public WebException(String code, String mesage) {
        super(code, mesage);
    }

    public WebException(WebEnum webEnum) {
        super(webEnum.getCode(), webEnum.getMessage());
    }

}
