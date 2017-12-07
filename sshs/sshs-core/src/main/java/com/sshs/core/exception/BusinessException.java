package com.sshs.core.exception;

/**
 * 
 * @author Suny
 *
 */
public class BusinessException extends RuntimeException {

	/**
	 */
	private static final long serialVersionUID = -7638041501183925225L;

	private Integer code;

	public BusinessException(Integer code, String msg) {
		super(msg);
		this.code = code;
	}

	public BusinessException(Integer code, String msg, Throwable cause) {
		super(msg, cause);
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

}