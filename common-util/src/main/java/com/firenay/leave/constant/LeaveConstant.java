package com.firenay.leave.constant;

/**
 * <p>Title: LeaveConstant</p>
 * Description：
 * date：2020/5/8 19:11
 */
public class LeaveConstant {

	public static final String MESSAGE_LOGIN_FAILED = "账户或密码输入错误! 请重新输入";

	public static final String MESSAGE_LOGIN_ACCT_ALREADY_IN_USE = "抱歉,这个账号已经被使用了!";

	public static final String MESSAGE_ACCESS_FORBIDEN = "请登录以后再访问!";

	public static final String MESSAGE_STRING_INVALIDATE = "字符串不合法,请不要传入空字符串";

	public static final String MESSAGE_SYSTEM_ERROR_LOGIN_NOT_UNIQUE = "系统错误：登录账号不唯一！";

	public static final String MESSAGE_NOT_LOGGED = "对不起, 此操作之前必须登录！";

	public static final String MESSAGE_FIRENAY = "来自FIRENAY";

	public static final String MESSAGE_ACCESS_DENIED = "抱歉！您不能访问这个资源！";

	public static final String MESSAGE_CODE_NOT_EXISTS = "验证码已过期, 请检查手机号或重新发送!";

	public static final String MESSAGE_CODE_INVALID = "验证码不正确!";

	public static final String ATTR_PHONE_INVALID = "手机号或邮箱输入有误！";

	public static final String ATTR_USER_NAME_INVALID = "用户名不能为空！";

	public static final String MESSAGE_HEADER_PIC_UPLOAD_FAILED = "头图上传失败";

	public static final String MESSAGE_HEADER_PIC_EMPTY = "头图不可为空!";

	public static final String MESSAGE_DETAIL_PIC_EMPTY = "详情图片不可为空不可为空!";

	public static final String MESSAGE_DETAIL_PIC_UPLOAD_FAILED = "详情图片上传失败!";

	public static final String MESSAGE_TEMPLE_PROJECT_MISSING = "临时存储的ProjectVO对象丢失";

	/**
	 * 页面中从这个值获取异常信息NotloggedException
	 */
	public static final String ATTR_NAME_EXCEPTION = "exception";

	public static final String ATTR_NAME_LOGIN_ADMIN = "loginAdmin";

	/**
	 * session 共享的用户对象
	 */
	public static final String ATTR_NAME_LOGIN_MEMBER = "loginMember";

	public static final String ATTR_ADMIN_EDIT = "admin";

	public static final String ATTR_ADMIN_ID = "AdminID";

	public static final String REDIS_CODE_PREFIX = "REDIS_CODE_PREFIX_";

	/**
	 * 用于管理员查询分页
	 */
	public static final String ATTR_NAME_PAGE_INFO = "pageInfo";

	public static final String ATTR_NAME_MESSAGE = "message";

	public static final String ATTR_NAME_TEMPLE_PROJECT = "tempProject";

	public static final String ATTR_NAME_PORTAL_DATA = "portal_data";
}
