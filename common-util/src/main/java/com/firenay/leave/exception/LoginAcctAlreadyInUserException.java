package com.firenay.leave.exception;

/**
 * <p>Title: LoginAcctAlreadyInUserException</p>
 * Description：保存admin时 如果检测到用户名字段重复时抛这个异常
 * date：2020/5/9 23:21
 */
public class LoginAcctAlreadyInUserException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public LoginAcctAlreadyInUserException() {
		super();
	}

	public LoginAcctAlreadyInUserException(String message) {
		super(message);
	}

	public LoginAcctAlreadyInUserException(String message, Throwable cause) {
		super(message, cause);
	}

	public LoginAcctAlreadyInUserException(Throwable cause) {
		super(cause);
	}

	protected LoginAcctAlreadyInUserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
