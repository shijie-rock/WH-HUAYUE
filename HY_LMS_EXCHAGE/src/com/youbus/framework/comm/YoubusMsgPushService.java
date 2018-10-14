/**
 * ��Ŀ����:AGENT_CENTER
 * ��         ��:com.youbus.framework.comm
 * ��   ��  ��:YoubusMsgPushService.java
 * �� ������:2015��11��24��-����4:06:55
 * Copyright @ 2015-YouBus.com.cn
 */
package com.youbus.framework.comm;

import java.util.Map;

import org.apache.log4j.Logger;

import com.infoservice.framework.Service;
import com.infoservice.framework.exceptions.FrameException;
import com.youbus.framework.comm.cache.YouBusCacheFactory;
import com.youbus.framework.comm.push.YouBusMsgPushFactory;

/**
 * ������:YoubusMsgPushService
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2015��11��24�� ����4:06:55
 * �޸ı�ע:
 * @version 1.0.0
 */
public class YoubusMsgPushService implements Service {
	private static Logger log=Logger.getLogger(YoubusMsgPushService.class);
	public static boolean IS_PUSH_SUPPORT=false;
	private static YouBusMsgPushFactory pushFactory=null;
	private String className=null;
	/**
	 * ����һ���µ�ʵ�� YoubusMsgPushService.
	 *
	 */
	public YoubusMsgPushService() {
		// TODO Auto-generated constructor stub

	}

	/* (non-Javadoc)
	 * @see com.infoservice.framework.Service#destroyService()
	 */
	@Override
	public void destroyService() throws FrameException {
		// TODO Auto-generated method stub
		pushFactory=null;
//		IS_PUSH_SUPPORT=false;
	}
	private static YoubusMsgPushService instance;
	
	public static YouBusMsgPushFactory getPushFactoryInstance(){
		return pushFactory;
	}

	/* (non-Javadoc)
	 * @see com.infoservice.framework.Service#getName()
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.getName();
	}

	/* (non-Javadoc)
	 * @see com.infoservice.framework.Service#initialize(java.util.Map)
	 */
	@Override
	public void initialize(Map params) throws FrameException {
		// TODO Auto-generated method stub
		if(params.get("pushClass")==null){
			log.debug(this.getClass()+":[pushClass=null],�޷���ɳ�ʼ����");
			System.out.println(this.getClass()+":[pushClass=null],�޷���ɳ�ʼ����");
			return;
		}
		System.out.println("YoubusMsgPushService.pushClass:="+params.get("pushClass"));

		// TODO Auto-generated method stub
		IS_PUSH_SUPPORT=true;
		className=(String)params.get("pushClass");
		try {
			pushFactory=(YouBusMsgPushFactory)Class.forName(className).newInstance();
			pushFactory.init();
			log.debug(this.getClass()+":pushFactory=["+pushFactory+"],��ɳ�ʼ����");
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
//	public static String pushMsg(String title,String content,String msgType,String receiverToken,int showTag){
//		if(!IS_PUSH_SUPPORT)return null;
//		System.out.println(this.getClass()+"-- pushMsg mothod"+":pushFactory:="+pushFactory);
//		return pushFactory.pushMsg(title, content, msgType, receiverToken,showTag);
//	}
	
	

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

}
