/**
 * 项目名称:HY_LMS_EXCHAGE
 * 包         名:com.test2.common
 * 文   件  名:ClientHelper.java
 * 创 建日期:2018年10月17日-下午4:00:27
 * Copyright @ 2018-YouBus.com.cn
 */
package com.test2.common;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

/**保存connection 对象
 * 类名称:ClientHelper
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2018年10月17日 下午4:00:27
 * 修改备注:
 * @version 1.0.0
 */
public class ClientHelper {

	/**
	 * 方   法  名:main
	 * 方法描述:
	 * 参         数:@param args
	 * 返   回  值:void
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	private NioSocketConnector  CONNECTOR = null;
	private IoSession SESSION_CHANNAL = null;
	
	private static int hearBeatRetryTimes=0;
	
	private static ClientHelper instance;
	
	public static ClientHelper getInstance(){
		if(instance==null)
			instance=new ClientHelper();
		return instance;
	}

	/**
	 * sESSION_CHANNAL
	 *
	 * @return  the sESSION_CHANNAL
	 * @since   1.0.0
	*/
	
	public IoSession getSESSION_CHANNAL() {
		return SESSION_CHANNAL;
	}

	/**
	 * @param sESSION_CHANNAL the sESSION_CHANNAL to set
	 */
	public void setSESSION_CHANNAL(IoSession sESSION_CHANNAL) {
		SESSION_CHANNAL = sESSION_CHANNAL;
	}

	/**
	 * cONNECTOR
	 *
	 * @return  the cONNECTOR
	 * @since   1.0.0
	*/
	
	public NioSocketConnector getCONNECTOR() {
		return CONNECTOR;
	}

	/**
	 * @param cONNECTOR the cONNECTOR to set
	 */
	public void setCONNECTOR(NioSocketConnector cONNECTOR) {
		CONNECTOR = cONNECTOR;
	}
	
	
	/**
	 * 心跳超时时，调用
	 * 方   法  名:beatTimeOut
	 * 方法描述:
	 * 参         数:@return
	 * 返   回  值:int
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public int beatTimeOut(){
		hearBeatRetryTimes++;
		return hearBeatRetryTimes;
	}
	
	
	/**
	 * 心跳正常是，调用
	 * 方   法  名:beatIntime
	 * 方法描述:
	 * 参         数:@return
	 * 返   回  值:int
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public int beatIntime(){
		hearBeatRetryTimes=0;
		return hearBeatRetryTimes;
	}
}
