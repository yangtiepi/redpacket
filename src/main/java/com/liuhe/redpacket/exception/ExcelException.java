package com.liuhe.redpacket.exception;

/**
 * Excel导入导出异常
 * @author ozil
 *
 */
public class ExcelException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2708894791581030751L;
	private Integer errorCode=-99; // 默认值

	public Integer getErrorCode() {
		return errorCode;
	}
	
	public ExcelException() {
		super();
	}

	public ExcelException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ExcelException(String message, Throwable cause) {
		super(message, cause);
	}

	public ExcelException(String message) {
		super(message);
	}
	
	public ExcelException(String message,Integer errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public ExcelException(Throwable cause) {
		super(cause);
	}

	
}
