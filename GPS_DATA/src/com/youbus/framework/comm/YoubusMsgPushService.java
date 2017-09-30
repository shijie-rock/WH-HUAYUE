/**
 * 项目名称:AGENT_CENTER
 * 包         名:com.youbus.framework.comm
 * 文   件  名:YoubusMsgPushService.java
 * 创 建日期:2015年11月24日-下午4:06:55
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
 * 类名称:YoubusMsgPushService
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2015年11月24日 下午4:06:55
 * 修改备注:
 * @version 1.0.0
 */
public class YoubusMsgPushService implements Service {
	private static Logger log=Logger.getLogger(YoubusMsgPushService.class);
	public static boolean IS_PUSH_SUPPORT=false;
	private static YouBusMsgPushFactory pushFactory=null;
	private String className=null;
	/**
	 * 创建一个新的实例 YoubusMsgPushService.
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
			log.debug(this.getClass()+":[pushClass=null],无法完成初始化！");
			System.out.println(this.getClass()+":[pushClass=null],无法完成初始化！");
			return;
		}
		System.out.println("YoubusMsgPushService.pushClass:="+params.get("pushClass"));

		// TODO Auto-generated method stub
		IS_PUSH_SUPPORT=true;
		className=(String)params.get("pushClass");
		try {
			pushFactory=(YouBusMsgPushFactory)Class.forName(className).newInstance();
			pushFactory.init();
			log.debug(this.getClass()+":pushFactory=["+pushFactory+"],完成初始化！");
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

}
