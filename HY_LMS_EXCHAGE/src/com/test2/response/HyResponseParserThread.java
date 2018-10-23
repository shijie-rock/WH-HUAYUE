/**
 * 项目名称:HY_LMS_EXCHAGE
 * 包         名:com.test2.response
 * 文   件  名:HyResponseParserThread.java
 * 创 建日期:2018年10月22日-下午10:45:13
 * Copyright @ 2018-YouBus.com.cn
 */
package com.test2.response;

/**
 * 类名称:HyResponseParserThread
 * 类描述:处理接收应答报文线程
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2018年10月22日 下午10:45:13
 * 修改备注:
 * @version 1.0.0
 */
public class HyResponseParserThread implements Runnable {

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	private String responseJson;
	/**
	 * 创建一个新的实例 HyResponseParserThread.
	 *
	 * @param responseJson
	 */
	public HyResponseParserThread(String responseJson) {
		super();
		this.responseJson = responseJson;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
//		Thread.sleep(1000);
		HyResponseUtil.parseReceiveMsg(responseJson);
	}

	/**
	 * responseJson
	 *
	 * @return  the responseJson
	 * @since   1.0.0
	*/
	
	public String getResponseJson() {
		return responseJson;
	}

	/**
	 * @param responseJson the responseJson to set
	 */
	public void setResponseJson(String responseJson) {
		this.responseJson = responseJson;
	}

}
