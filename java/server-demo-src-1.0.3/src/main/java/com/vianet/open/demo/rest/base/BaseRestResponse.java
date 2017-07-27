package com.vianet.open.demo.rest.base;

import java.io.Serializable;

import com.vianet.open.demo.constants.Constants;

/**
 * 基础返回
 * @author wei.yong
 */
public class BaseRestResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
     * 响应码
     */
    private String retCode;

    /**
     * 响应信息
     */
    private String retInfo;

    /**
     * 响应数据对象
     */
    private Object result;

	public BaseRestResponse() {
		this.retCode = Constants.OK;
		this.retInfo = Constants.OK_MSG;
		this.result = "";
	}

	public BaseRestResponse(String code,String info) {
		this.retCode = code;
		this.retInfo = info;
		this.result = "";
	}

	public BaseRestResponse(String code,String info, Object result) {
        this.retCode = code;
        this.retInfo = info;
        this.result = result;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetInfo() {
        return retInfo;
    }

    public void setRetInfo(String retInfo) {
        this.retInfo = retInfo;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "BaseRestResponse [retCode=" + retCode + ", retInfo=" + retInfo + ", result=" + result + "]";
    }

}