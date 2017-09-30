/**
 * 项目名称:AGENT_CENTER
 * 包         名:com.youbus.framework.comm.remoteclient
 * 文   件  名:ParamBean.java
 * 创 建日期:2015年6月3日-上午11:17:02
 * Copyright @ 2015-YouBus.com.cn
 */
package com.cn.gpslms.httpclient;

import com.infoservice.po.DataBean;
import com.infoservice.po.POFactoryUtil;

/**
 * 类名称:ParamBean
 * 类描述:用于包装请求参数bean
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2015年6月3日 上午11:17:02
 * 修改备注:
 * @version 1.0.0
 */
public class ParamBean implements DataBean {
	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since 1.0.0
	 */
	
	private static final long serialVersionUID = 5585723020016605325L;
	private String paramName;
	private String paramValue;
	/**
	 * 创建一个新的实例 ParamBean.
	 *
	 */
	public ParamBean() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 创建一个新的实例 ParamBean.
	 *
	 * @param paramName
	 * @param paramValue
	 */
	public ParamBean(String paramName, String paramValue) {
		super();
		this.paramName = paramName;
		this.paramValue = paramValue;
	}
	
	/**
	 * paramName
	 *
	 * @return  the paramName
	 * @since   1.0.0
	 */
	
	public String getParamName() {
		return paramName;
	}

	/**
	 * @param paramName the paramName to set
	 */
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	/**
	 * paramValue
	 *
	 * @return  the paramValue
	 * @since   1.0.0
	 */
	
	public String getParamValue() {
		return paramValue;
	}

	/**
	 * @param paramValue the paramValue to set
	 */
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	/**
	 * 方   法  名:main
	 * 方法描述:
	 * 参         数:@param args
	 * 返   回  值:void
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.infoservice.po.DataBean#toXMLString()
	 */
	@Override
	public String toXMLString() {
		// TODO Auto-generated method stub
		return POFactoryUtil.beanToXmlString(this);
	}

}
