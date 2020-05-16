package com.firenay.leave.exception;

/**
 * <p>Title: AccessForbiddenException</p>
 * Description：用户未登录抛出来的异常
 * date：2020/5/9 10:09
 */
public class AccessForbiddenException extends RuntimeException  {

	private static final long serialVersionUID = 1L;

	public AccessForbiddenException() {
		super();
	}

	public AccessForbiddenException(String message) {
		super(message);
	}

	public AccessForbiddenException(String message, Throwable cause) {
		super(message, cause);
	}

	public AccessForbiddenException(Throwable cause) {
		super(cause);
	}

	protected AccessForbiddenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
