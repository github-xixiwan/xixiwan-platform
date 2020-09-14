package com.xixiwan.platform.module.common.exception;

public abstract class BaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final String code;

	public BaseException(String code, String mesage) {
		super(mesage);
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
