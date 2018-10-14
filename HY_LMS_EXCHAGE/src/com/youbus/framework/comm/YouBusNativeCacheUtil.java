/**
 * 项目名称:AGENT_CENTER
 * 包         名:com.youbus.framework.comm
 * 文   件  名:YouBusNativeCacheUtil.java
 * 创 建日期:2015年7月15日-下午1:53:31
 * Copyright @ 2015-YouBus.com.cn
 */
package com.youbus.framework.comm;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.apache.log4j.Logger;

import com.infoservice.po.DynaBean;
import com.infoservice.po.POFactory;
import com.youbus.agentcenter.businessData.po.TmAgentGroupPO;
import com.youbus.agentcenter.businessData.pofactory.GroupInfoFactory;
import com.youbus.custcenter.agentbase.po.TmAgentMemberPO;
import com.youbus.custcenter.agentbase.po.TmAgentShortcutActionPO;
import com.youbus.custcenter.agentbase.pofactory.TmAgentMemberPOFactory;
import com.youbus.custcenter.agentbase.pofactory.TmAgentShortcutActionPOFactory;
import com.youbus.framework.util.DBConUtil;

/**
 * 类名称:YouBusNativeCacheUtil
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2015年7月15日 下午1:53:31
 * 修改备注:
 * @version 1.0.0
 */
public class YouBusNativeCacheUtil {

	/**
	 * 创建一个新的实例 YouBusNativeCacheUtil.
	 *
	 */
	static Logger logger=Logger.getLogger(YouBusNativeCacheUtil.class);
	
	public YouBusNativeCacheUtil() {
		// TODO Auto-generated constructor stub
	}
	
	public static void refreshDriverCache(Connection conn){
		logger.debug("refreshDriverCache begin");
		   List<DynaBean> driverList=TmAgentMemberPOFactory.queryAllAgentDriver(conn);
		   Cache AGENT_DRIVER_LIST_CACHE=YoubusNativeCacheService.getCache("AGENT_DRIVER_LIST_CACHE");
		   AGENT_DRIVER_LIST_CACHE.removeAll();
			Element e=null;
			//init driver
			for(DynaBean bean:driverList){
				Integer agentId=bean.getInt("AGENT_ID"); 
				if(AGENT_DRIVER_LIST_CACHE.isKeyInCache(agentId)){
				 e=AGENT_DRIVER_LIST_CACHE.get(agentId);
				 List<DynaBean> cacheList=(List<DynaBean>)e.getValue();
				 cacheList.add(bean);
				}else {
					List<DynaBean> cacheList=new ArrayList<DynaBean>();
					cacheList.add(bean);
					e=new Element(agentId, cacheList);
				}
				AGENT_DRIVER_LIST_CACHE.put(e);
		        System.out.println("AGENT_DRIVER_LIST_CACHE:="+AGENT_DRIVER_LIST_CACHE.getSize());  
			} 
			logger.debug("refreshDriverCache end");  
	}
	
	public static void refreshBusCache(Connection conn){
		logger.debug("refreshBusCache begin");
		   List<DynaBean> vehicleList=TmAgentMemberPOFactory.queryAllAgentVehicle(conn);
		   Cache AGENT_VEHICLE_LIST_CACHE=YoubusNativeCacheService.getCache("AGENT_VEHICLE_LIST_CACHE");
			Element e=null;
			AGENT_VEHICLE_LIST_CACHE.removeAll();
			//init driver
			for(DynaBean bean:vehicleList){
				Integer agentId=bean.getInt("AGENT_ID"); 
				if(AGENT_VEHICLE_LIST_CACHE.isKeyInCache(agentId)){
				 e=AGENT_VEHICLE_LIST_CACHE.get(agentId);
				 List<DynaBean> cacheList=(List<DynaBean>)e.getValue();
				 cacheList.add(bean);
				}else {
					List<DynaBean> cacheList=new ArrayList<DynaBean>();
					cacheList.add(bean);
					e=new Element(agentId, cacheList);
				}
				AGENT_VEHICLE_LIST_CACHE.put(e);
		        System.out.println("AGENT_VEHICLE_LIST_CACHE:="+AGENT_VEHICLE_LIST_CACHE.getSize());  
			} 
			logger.debug("refreshBusCache end");  
	}
	
	public static void refreshGroupList(Connection conn){
		logger.debug("refreshGroupList begin");
		List<TmAgentGroupPO> groupPOList = GroupInfoFactory.queryAllGroup(conn,0);
		Cache AGENT_GROUP_LIST_CACHE = YoubusNativeCacheService.getCache("AGENT_GROUP_LIST_CACHE");
		Element e = null;
		// init driver
		String agentId = null;
		AGENT_GROUP_LIST_CACHE.removeAll();
		for (TmAgentGroupPO bean : groupPOList) {
			agentId = String.valueOf(bean.getAgentId());
			if (AGENT_GROUP_LIST_CACHE.isKeyInCache(agentId)) {
				e = AGENT_GROUP_LIST_CACHE.get(agentId);
				List<TmAgentGroupPO> cacheList = (List<TmAgentGroupPO>) e.getValue();
				cacheList.add(bean);
			} else {
				List<TmAgentGroupPO> cacheList = new ArrayList<TmAgentGroupPO>();
				cacheList.add(bean);
				e = new Element(agentId, cacheList);
			}
			AGENT_GROUP_LIST_CACHE.put(e);
			System.out.println("AGENT_GROUP_LIST_CACHE:="+ AGENT_GROUP_LIST_CACHE.getSize());
		}
		logger.debug("refreshGroupList end");  
	}
	
	public static void refreshAgentMemberDataCache(Connection conn,int agId,int memId,int userId){
		logger.debug("refreshAgentMemberDataCache begin");
		Cache AGENT_MEMBER_DATA_CACHE=YoubusNativeCacheService.getCache("AGENT_MEMBER_DATA_CACHE");
		TmAgentMemberPO mPOCon= new TmAgentMemberPO();
		if(agId!=0){
			mPOCon.setAgentId(agId);
		}
		if(memId!=0){
			mPOCon.setMemberId(memId);
		}
		if(userId!=0){
			mPOCon.setId(userId);
		}
		List<TmAgentMemberPO> list=POFactory.select(conn,mPOCon);
		if(list!=null){
			int id=0;
			int memberId=0;
			int agentId=0;
			String key=null;
			Element e=null;
			for(TmAgentMemberPO temp:list){
				id=temp.getId()==0?0:temp.getId();
				memberId=temp.getMemberId()==null?0:temp.getMemberId();
				agentId=temp.getAgentId()==null?0:temp.getAgentId();
				key=agentId+"-"+memberId+"-"+id;
				e=new Element(key, temp);
				AGENT_MEMBER_DATA_CACHE.put(e);
			}
		}
		logger.debug("refreshAgentMemberDataCache end");  
	}
	
	
/*	public static void initGroupList(Connection conn){
		   List<TmAgentGroupPO> groupPOList=GroupInfoFactory.queryAllGroup(conn,0);
		   Cache groupCache=YoubusNativeCacheService.getCache("AGENT_GROUP_LIST_CACHE");
			Element e=null;
			//init driver
			String agentId=null;
			for(TmAgentGroupPO bean:groupPOList){
				agentId=String.valueOf(bean.getAgentId()); 
				if(groupCache.isKeyInCache(agentId)){
				 e=groupCache.get(agentId);
				 List<TmAgentGroupPO> cacheList=(List<TmAgentGroupPO>)e.getValue();
				 cacheList.add(bean);
				}else {
					List<TmAgentGroupPO> cacheList=new ArrayList<TmAgentGroupPO>();
					cacheList.add(bean);
					e=new Element(agentId, cacheList);
				}
				groupCache.put(e);
		        System.out.println("AGENT_GROUP_LIST_CACHE:="+groupCache.getSize());  
			} 
	}*/
	
//	public static void refreshMemberAgentShortcutAction(CacheManager manager,Connection conn,int agentId,int empId){
//		Cache AGENT_SHORTCUT_ACTION_CACHE=YoubusNativeCacheService.getCache("AGENT_SHORTCUT_ACTION_CACHE");
//		//TM_AGENT_SHORTCUT_ACTION
//		List<TmAgentShortcutActionPO> list=TmAgentShortcutActionPOFactory.queryAgentShrotcutAction(conn,agentId,empId);
//		if(DBConUtil.listNotNULL(list)){
//			for(TmAgentShortcutActionPO temp:list){
//				String code=temp.getShortcutCode();
//				Element e=new Element(code, temp);
//				AGENT_SHORTCUT_ACTION_CACHE.put(e);
//			}
//		}
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
