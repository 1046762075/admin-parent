package com.firenay.leave.util;

/**
 * 统一整个项目中Ajax请求返回的结果（未来也可以用于分布式架构各个模块间调用时返回统一类型）
 */
public class ResultEntity<T> {
	
	public static final String SUCCESS = "SUCCESS";
	public static final String FAILED = "FAILED";
	
	// 用来封装当前请求处理的结果是成功还是失败
	private String result;
	
	// 请求处理失败时返回的错误消息
	private String message;
	
	// 要返回的数据
	private T data;
	
	public String getResult() {
		return result;
	}

	@Override
	public String toString() {
		return "ResultEntity{" +
				"result='" + result + '\'' +
				", message='" + message + '\'' +
				", data=" + data +
				'}';
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public ResultEntity(String result, String message, T data) {
		this.result = result;
		this.message = message;
		this.data = data;
	}

	public ResultEntity() {}

	/**
	 * 请求成功 不需要返回数据
	 * @return
	 */
	public static <E> ResultEntity<E> successWithoutData(String ...message) {
		return new ResultEntity<E>(SUCCESS, getMsg(message), null);
	}
	
	/**
	 * 请求成功将数据返回
	 */
	public static <E> ResultEntity<E> successWithData(E data, String ...message) {
		return new ResultEntity<E>(SUCCESS, getMsg(message), data);
	}
	
	/**
	 * 请求处理失败后使用的工具方法
	 * @param message 失败的错误消息
	 * @return
	 */
	public static <E> ResultEntity<E> failed(String ...message) {
		return new ResultEntity<E>(FAILED, getMsg(message), null);
	}

	private static String getMsg(String[] message){
		String Msg = null;
		if(null != message && message.length > 0){
			Msg = "";
			for (String temp : message) {
				if(temp != null){
					Msg += temp;
					if(!message[message.length-1].equals(temp)){
						Msg += "\t";
					}
				}
			}
		}
		return Msg;
	}
}
