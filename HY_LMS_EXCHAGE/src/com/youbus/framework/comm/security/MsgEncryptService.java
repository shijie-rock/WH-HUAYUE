/**
 * 项目名称:AGENT_CENTER
 * 包         名:com.youbus.framework.comm.security
 * 文   件  名:MsgEncryptService.java
 * 创 建日期:2015年6月3日-上午10:42:34
 * Copyright @ 2015-YouBus.com.cn
 */
package com.youbus.framework.comm.security;

import java.util.Map;

import com.infoservice.framework.Service;
import com.infoservice.framework.exceptions.FrameException;

/**请求报文、应答报文加密解密
 * 类名称:MsgEncryptService
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2015年6月3日 上午10:42:34
 * 修改备注:
 * @version 1.0.0
 */
public abstract class MsgEncryptService implements Service {

	/**
	 * 创建一个新的实例 MsgEncryptService.
	 *
	 */
	public MsgEncryptService() {
		// TODO Auto-generated constructor stub
	}
	
	/* (non-Javadoc)
	 * @see com.infoservice.framework.Service#destroyService()
	 */
	@Override
	public void destroyService() throws FrameException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.infoservice.framework.Service#getName()
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.getClass().getName();
	}

	/* (non-Javadoc)
	 * @see com.infoservice.framework.Service#initialize(java.util.Map)
	 */
	@Override
	public void initialize(Map arg0) throws FrameException {
		// TODO Auto-generated method stub
		System.out.println(this.getClass().getName());
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
	
	/**
	 * 加密
	 * 方   法  名:encrypt
	 * 方法描述:
	 * 参         数:@param src
	 * 参         数:@return
	 * 返   回  值:String
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public abstract String encrypt(String src);
	
	/**
	 * 解密
	 * 方   法  名:decrypt
	 * 方法描述:
	 * 参         数:@param src
	 * 参         数:@return
	 * 返   回  值:String
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public abstract String decrypt(String src);
	

}
