package com.firenay.leave.exception;

/**
 * <p>Title: NotlogedException</p>
 * Description：用户未登录
 * date：2020/5/8 21:50
 */
public class NotloggedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NotloggedException() {
		super();
	}

	/**
	 * 用户未登录
	 */
	public NotloggedException(String message) {
		super(message);
	}

	public NotloggedException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotloggedException(Throwable cause) {
		super(cause);
	}

	protected NotloggedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
