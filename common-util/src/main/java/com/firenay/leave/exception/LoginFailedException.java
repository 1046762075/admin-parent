package com.firenay.leave.exception;

/**
 * <p>Title: LoginFailedException</p>
 * Description：自定义异常 [登录失败之后]
 * date：2020/5/8 21:36
 */
public class LoginFailedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public LoginFailedException() {
		super();
	}

	public LoginFailedException(String message) {
		super(message);
	}

	public LoginFailedException(String message, Throwable cause) {
		super(message, cause);
	}

	public LoginFailedException(Throwable cause) {
		super(cause);
	}

	protected LoginFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
