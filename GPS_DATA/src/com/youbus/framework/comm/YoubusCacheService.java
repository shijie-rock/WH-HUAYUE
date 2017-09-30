/**
 * 项目名称:AGENT_CENTER
 * 包         名:com.youbus.framework.comm
 * 文   件  名:YoubusCacheService.java
 * 创 建日期:2015年4月2日-下午3:18:15
 * Copyright @ 2015-YouBus.com.cn
 */
package com.youbus.framework.comm;

import java.util.Map;

import org.apache.log4j.Logger;

import com.infoservice.framework.Service;
import com.infoservice.framework.exceptions.FrameException;
import com.youbus.framework.comm.cache.YouBusCacheFactory;

/**
 * 类名称:YoubusCacheService
 * 类描述:应用外部缓存，缓存服务器（存储业务包装数据）
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2015年4月2日 下午3:18:15
 * 修改备注:
 * @version 1.0.0
 */
public class YoubusCacheService implements Service {
	private static Logger log=Logger.getLogger(YoubusCacheService.class);
	private static boolean IS_OCS_SUPPORT=false;
	private YouBusCacheFactory cacheFactory=null;
	/**
	 * 创建一个新的实例 YoubusCacheService.
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
			log.debug(this.getClass()+":[cacheClass=null],无法完成初始化！");
			System.out.println(this.getClass()+":[cacheClass=null],无法完成初始化！");
			return;
		}
		System.out.println("YoubusCacheService.cacheClass:="+params.get("cacheClass"));
		IS_OCS_SUPPORT=true;//启用
		// TODO Auto-generated method stub
		String className=(String)params.get("cacheClass");
		try {
			cacheFactory=(YouBusCacheFactory)Class.forName(className).newInstance();
			cacheFactory.init();
			log.debug(this.getClass()+":cacheFactory=["+cacheFactory+"],完成初始化！");
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
