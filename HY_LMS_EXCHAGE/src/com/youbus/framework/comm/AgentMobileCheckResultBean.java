/**
 * 项目名称:AGENT_CENTER
 * 包         名:com.youbus.framework.comm
 * 文   件  名:AgentMobileCheckResultBean.java
 * 创 建日期:2015年6月5日-下午3:35:23
 * Copyright @ 2015-YouBus.com.cn
 */
package com.youbus.framework.comm;

import com.infoservice.po.DataBean;
import com.infoservice.po.POFactoryUtil;

/**
 * 类名称:AgentMobileCheckResultBean
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2015年6月5日 下午3:35:23
 * 修改备注:
 * @version 1.0.0
 */
public class AgentMobileCheckResultBean implements DataBean {

	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since 1.0.0
	 */
	
	private static final long serialVersionUID = 66135871499378928L;

	private int returnCode;//验证 验证码返回结果代码
	private String returnMsg;//验证 验证码返回结果说明
	
	/**
	 * 创建一个新的实例 AgentMobileCheckResultBean.
	 *
	 */
	public AgentMobileCheckResultBean() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * returnCode
	 *
	 * @return  the returnCode
	 * @since   1.0.0
	 */
	
	public int getReturnCode() {
		return returnCode;
	}

	
	/**
	 * 创建一个新的实例 AgentMobileCheckResultBean.
	 *
	 * @param returnCode
	 * @param returnMsg
	 */
	public AgentMobileCheckResultBean(int returnCode, String returnMsg) {
		super();
		this.returnCode = returnCode;
		this.returnMsg = returnMsg;
	}

	/**
	 * @param returnCode the returnCode to set
	 */
	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}

	/**
	 * returnMsg
	 *
	 * @return  the returnMsg
	 * @since   1.0.0
	 */
	
	public String getReturnMsg() {
		return returnMsg;
	}

	/**
	 * @param returnMsg the returnMsg to set
	 */
	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
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
