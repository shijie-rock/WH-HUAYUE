/**
 * ��Ŀ����:HY_LMS_EXCHAGE
 * ��         ��:com.test2.response
 * ��   ��  ��:HyResponseParserThread.java
 * �� ������:2018��10��22��-����10:45:13
 * Copyright @ 2018-YouBus.com.cn
 */
package com.test2.response;

/**
 * ������:HyResponseParserThread
 * ������:�������Ӧ�����߳�
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2018��10��22�� ����10:45:13
 * �޸ı�ע:
 * @version 1.0.0
 */
public class HyResponseParserThread implements Runnable {

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	private String responseJson;
	/**
	 * ����һ���µ�ʵ�� HyResponseParserThread.
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
