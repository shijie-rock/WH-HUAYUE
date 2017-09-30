package com.youbus.framework.comm;

import com.infoservice.po.DataBean;
import com.infoservice.po.POFactoryUtil;

public class MessageResultPO implements DataBean{
	private String code;
	private String msg;
	private String smsId;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getSmsId() {
		return smsId;
	}
	public void setSmsId(String smsId) {
		this.smsId = smsId;
	}
	@Override
	public String toXMLString() {
		return POFactoryUtil.beanToXmlString(this);
	}

}
