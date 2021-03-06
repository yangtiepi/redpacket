package com.liuhe.redpacket.utils.result;


public class AppResult {
	/**
	 * 响应是否成功
	 * 1  成功
	 * 0 失败
	 */
	private Integer success = 0;
	
	/**
	 * 响应结果说明
	 */
	private String message;
	
	/**
	 * 失败返回
	 * 错误码（前台可以根据错误码，进行特殊处理）
	 */
	private Integer errorCode ;
	/**
	 * 成功返回
	 * 封装好的数据
	 */
	private Object data ;

	public Integer getSuccess() {
		return success;
	}

	public void setSuccess(Integer success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
	

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	/**
	 * 请求失败
	 */
	public AppResult() {
	}

	/**
	 * 请求成功
	 * @param success  请求状态
	 * @param message  成功后的提示消息
	 * @param data  封装好的数据
	 */
	public AppResult(Object data) {
		this.success = 1;
		this.data = data;
	}

	/**
	 *  请求错误
	 * @param message  错误消息
	 * @param errorCode 错误码
	 */
	public AppResult(String message, Integer errorCode) {
		this.success = 0;
		this.message = message;
		this.errorCode = errorCode;
	}

	
}
