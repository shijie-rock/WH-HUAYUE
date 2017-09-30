/**
 * ��Ŀ����:AGENT_CENTER
 * ��         ��:com.youbus.framework.comm
 * ��   ��  ��:YoubusCacheService.java
 * �� ������:2015��4��2��-����3:18:15
 * Copyright @ 2015-YouBus.com.cn
 */
package com.youbus.framework.comm;

import java.util.Map;

import org.apache.log4j.Logger;

import com.infoservice.framework.Service;
import com.infoservice.framework.exceptions.FrameException;
import com.youbus.framework.comm.cache.YouBusCacheFactory;

/**
 * ������:YoubusCacheService
 * ������:Ӧ���ⲿ���棬������������洢ҵ���װ���ݣ�
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2015��4��2�� ����3:18:15
 * �޸ı�ע:
 * @version 1.0.0
 */
public class YoubusCacheService implements Service {
	private static Logger log=Logger.getLogger(YoubusCacheService.class);
	private static boolean IS_OCS_SUPPORT=false;
	private YouBusCacheFactory cacheFactory=null;
	/**
	 * ����һ���µ�ʵ�� YoubusCacheService.
	 *
	 */
	private YoubusCacheService() {
		// TODO Auto-generated constructor stub
//		cachedClient=new MemCachedClient();  
//		System.out.println("YoubusCacheService init begin :"+cachedClient);
	}
	

	private static YoubusCacheService instance;
	
	public static YoubusCacheService getInstance(){
		if(instance==null)instance=new YoubusCacheService();
				return instance;
	}

	/* (non-Javadoc)
	 * @see com.infoservice.framework.Service#destroyService()
	 */
	@Override
	public void destroyService() throws FrameException {
		// TODO Auto-generated method stub
		cacheFactory=null;
	}
	
	/* (non-Javadoc)
	 * @see com.infoservice.framework.Service#getName()
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return YoubusCacheService.class.getName();
	}

	/* (non-Javadoc)
	 * @see com.infoservice.framework.Service#initialize(java.util.Map)
	 */
	@Override
	public void initialize(Map params) throws FrameException {
		if(params.get("cacheClass")==null){
			log.debug(this.getClass()+":[cacheClass=null],�޷���ɳ�ʼ����");
			System.out.println(this.getClass()+":[cacheClass=null],�޷���ɳ�ʼ����");
			return;
		}
		System.out.println("YoubusCacheService.cacheClass:="+params.get("cacheClass"));
		IS_OCS_SUPPORT=true;//����
		// TODO Auto-generated method stub
		String className=(String)params.get("cacheClass");
		try {
			cacheFactory=(YouBusCacheFactory)Class.forName(className).newInstance();
			cacheFactory.init();
			log.debug(this.getClass()+":cacheFactory=["+cacheFactory+"],��ɳ�ʼ����");
			set("AAAA", "1111");
			System.out.println(get("AAAA"));
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
	public Object get(Object key) {
		// TODO Auto-generated method stub
		if(!IS_OCS_SUPPORT)return null;
		System.out.println(this.getClass()+"-- get mothod");
		return cacheFactory.get((String)key);
	}

	/* (non-Javadoc)
	 * @see com.youbus.framework.comm.cache.YouBusCacheFactory#set(java.lang.Object, java.lang.Object)
	 */
	public void set(Object key, Object value) {
		// TODO Auto-generated method stub
		if(!IS_OCS_SUPPORT)return;
		cacheFactory.set((String)key, value);
		System.out.println(this.getClass()+"-- set mothod");

	}
  
}
