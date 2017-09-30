/**
 * ��Ŀ����:AGENT_CENTER
 * ��         ��:com.youbus.framework.comm.security
 * ��   ��  ��:MsgEncryptService.java
 * �� ������:2015��6��3��-����10:42:34
 * Copyright @ 2015-YouBus.com.cn
 */
package com.youbus.framework.comm.security;

import java.util.Map;

import com.infoservice.framework.Service;
import com.infoservice.framework.exceptions.FrameException;

/**�����ġ�Ӧ���ļ��ܽ���
 * ������:MsgEncryptService
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2015��6��3�� ����10:42:34
 * �޸ı�ע:
 * @version 1.0.0
 */
public abstract class MsgEncryptService implements Service {

	/**
	 * ����һ���µ�ʵ�� MsgEncryptService.
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
	 * ��   ��  ��:main
	 * ��������:
	 * ��         ��:@param args
	 * ��   ��  ֵ:void
	 * ��   ��  ��:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * ����
	 * ��   ��  ��:encrypt
	 * ��������:
	 * ��         ��:@param src
	 * ��         ��:@return
	 * ��   ��  ֵ:String
	 * ��   ��  ��:rock
	 * @exception
	 * @since  1.0.0
	 */
	public abstract String encrypt(String src);
	
	/**
	 * ����
	 * ��   ��  ��:decrypt
	 * ��������:
	 * ��         ��:@param src
	 * ��         ��:@return
	 * ��   ��  ֵ:String
	 * ��   ��  ��:rock
	 * @exception
	 * @since  1.0.0
	 */
	public abstract String decrypt(String src);
	

}
