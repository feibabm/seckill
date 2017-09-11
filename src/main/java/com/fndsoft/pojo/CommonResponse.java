package com.fndsoft.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fndsoft.Constants;
import net.sf.json.JSONObject;


@JsonIgnoreProperties(ignoreUnknown = true)
public class CommonResponse<T> {
	private T data;
	private boolean success;
	private String code; // "000000"成功
	private String message;

	/**
	 * @return the data
	 */
	public T getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(T data) {
		this.data = data;
	}

	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * @param success
	 *            the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	public static CommonResponse buildRespose4Success(Object resp, String msg) {
		CommonResponse<Object> commonResponse = new CommonResponse<Object>();
		commonResponse.setData(resp);
		commonResponse.setCode(Constants.SUCCESS_CODE);
		commonResponse.setMessage(msg);
		commonResponse.setSuccess(true);
		return commonResponse;
	}

	public static CommonResponse buildRespose4Fail(String errorCode, String msg) {
		CommonResponse<Object> commonResponse = new CommonResponse<Object>();
		commonResponse.setCode(errorCode);
		commonResponse.setMessage(msg);
		commonResponse.setSuccess(false);
		return commonResponse;
	}

	public static void main(String[] args) {
		CommonResponse res = CommonResponse.buildRespose4Success(new Boolean(true), "test");
		System.out.println(JSONObject.fromObject(res));
	}
}
