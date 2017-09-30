/**
 * 项目名称:AGENT_CENTER
 * 包         名:com.youbus.framework.comm
 * 文   件  名:YoubusNativeCacheService.java
 * 创 建日期:2015年4月4日-下午5:10:42
 * Copyright @ 2015-YouBus.com.cn
 */
package com.youbus.framework.comm;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.apache.log4j.Logger;

import com.info.base.po.TsDataDicPO;
import com.infoservice.framework.FrameworkConstant;
import com.infoservice.framework.Service;
import com.infoservice.framework.exceptions.FrameException;
import com.infoservice.framework.services.DBService;
import com.infoservice.po.DynaBean;
import com.infoservice.po.POFactory;
import com.truckinspect.busi.organize.pofactory.TbSysRolePOFactory;


/**
 * 类名称:YoubusNativeCacheService
 * 类描述:应用本地缓存，存储基础数据(省市县，数据字典)
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2015年4月4日 下午5:10:42
 * 修改备注:
 * @version 1.0.0
 */
public class TruckInsNativeCacheService implements Service {
	
	private static Logger log=Logger.getLogger(TruckInsNativeCacheService.class);

//	public static Cache CITY_CACHE; //<市, bean>
	public static Cache DATA_DIC_CACHE; //<数据字典表><typecode:bean>
	public static Cache DATA_DIC_MAP_CACHE; //<数据字典表><code:bean>
	public static Cache FUN_ACT_OPT_CACHE; //FUNTION ACTION OPTION LIST
	
//	public static Cache REMOTE_REG_INFO_CACHE ; //安全中心远程登录信息; 
//	public static Cache AGENT_VEHICLE_LIST_CACHE ; //agent下辖车辆信息; 
//	public static Cache AGENT_DRIVER_LIST_CACHE ; //agent下辖司机信息; 
//	
//	public static Cache AGENT_AUTH_SESSION_CACHE ; //agentcenter存储安全中心会话与本地会话的对应关系 
//	public static Cache AGENT_SESSION_CACHE ; //agentcenter存储安本地会话的状态
//	
//	public static Cache BUS_DRIVER_CALENDAR_CACHE ; //车辆司机日历缓存; 
//	public static Cache AGENT_MSG_PARAM_CACHE ; //系统默认消息参数
//	public static Cache AGENT_UNPROCESS_COUNT_CACHE ; //按empId区分每人的未处理消息数目
//	public static Cache AGENT_UNREAD_COUNT_CACHE ; //按empId区分每人的未读取消息数目
//	
//	public static Cache AGENT_PROCESS_INFO_CACHE ; //按empId区分每人的最新一周内消息（需要处理与不需要处理）
//	public static Cache AGENT_READ_NOTICE_CACHE ; //按empId区分每人的最新一周内通知（已读未读）
//	
//	public static Cache FUN_ACT_OPT_HTML_CACHE ; //系统权限功能菜单列表
//	public static Cache AGENT_SHORTCUT_ACTION_CACHE ; //首页快捷菜单列表 //TM_AGENT_SHORTCUT_ACTION
//	public static Cache AGENT_SHORTCUT_ACTION_HTML_CACHE ; //首页快捷菜单CHECK BOX HTML//TM_AGENT_SHORTCUT_ACTION 
//	
//	
//	public static Cache TS_APP_ALL_INFO_CACHE ; //TS_APP_ALL_INFO
//	
//	public static Cache AGENT_MOBILE_CHECK_CODE_CACHE;//车队平台手机验证码缓存
//	
//	public static Cache AGENT_INFO_LIST_CACHE;//车队信息
//	
//	public static Cache AGENT_MEMBER_DATA_CACHE;//本应用上车队成员信息key：AGENT_ID-MEMBER_ID-ID VALUE:BEAN(TM_AGENT_MEMBER)
//	
//	public static Cache MOBILE_TOKEN_CACHE;//手机登录用户token缓存 key:token--value(DynaBean<MEMBER_ID:STRING.MEMBER_ID;GROUP_ID:STRING.GROUP_ID>)
	
	private final String cacheUrl=FrameworkConstant.APP_CONTEXT_PATH+"WEB-INF\\AppCache.xml";
	
	public static String APP_CODE; //对应 TB_APP_INFO
	public static String APP_NO_PREFIX;
	public static String APP_NAME;
	public static String APP_TYPE;
	
	/* (non-Javadoc)
	 * @see com.infoservice.framework.Service#destroyService()
	 */
	@Override
	public void destroyService() throws FrameException {
		// TODO Auto-generated method stub
		//cache clear
	}

	/* (non-Javadoc)
	 * @see com.infoservice.framework.Service#getName()
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return TruckInsNativeCacheService.class.getName();
	}

	/* (non-Javadoc)
	 * @see com.infoservice.framework.Service#initialize(java.util.Map)
	 */
	@Override
	public void initialize(Map arg0) throws FrameException {
		// TODO Auto-generated method stub
		CacheManager manager = new CacheManager(cacheUrl); 	
//		CITY_CACHE = manager.getCache("CityCache");   
//		REMOTE_REG_INFO_CACHE = manager.getCache("REMOTE_REG_INFO_CACHE"); 
//		
//		AGENT_AUTH_SESSION_CACHE = manager.getCache("AGENT_AUTH_SESSION_CACHE"); 
//		AGENT_SESSION_CACHE = manager.getCache("AGENT_SESSION_CACHE"); 
//		
//		MOBILE_TOKEN_CACHE = manager.getCache("MOBILE_TOKEN_CACHE"); 
		
		Connection conn=DBService.getInstance().getConnection();
		/*
		 * 查询数据库，装载数据代码。
		 */
//		Element element=new Element("010","北京");CITY_CACHE.put(element);
//		element=new Element("021","上海");CITY_CACHE.put(element);
		
		//初始化数据字典
		log.debug("initDataDic begin");
		initDataDic(manager,conn);
		log.debug("initDataDic end");
		
		log.debug("initAppInfo begin");
//		initAppInfo(conn);
		log.debug("initAppInfo end :="+TruckInsNativeCacheService.APP_NO_PREFIX);
		
		//
		
		log.debug("initFAOList begin");
		initFAOList(manager, conn);
		log.debug("initFAOList end");
		
//		System.out.println("initBusAndDriverCalendarTemp begin");
//		initBusAndDriverCalendarTemp(manager, conn);
//		System.out.println("initBusAndDriverCalendarTemp end");
		
//		System.out.println("initAllFAOHtml begin");
//		initAllFAOHtml(manager,conn);
//		System.out.println("initAllFAOHtml end");
		
//		System.out.println("initAgentTripAssignPanelMap begin");
//		//initAgentTripAssignPanelMap(manager,conn);
//		System.out.println("initAgentTripAssignPanelMap end");
		
		System.out.println("initAppAllInfoCache begin");
//		initAppAllInfoCache(manager, conn);
		System.out.println("initAppAllInfoCache end");
		
//		System.out.println("initCalendar begin");
//		TsCalendarPOFactory.createCalendarData(conn);
//		System.out.println("initCalendar end");
//		
		System.out.println("initMobileCheckCache begin");
//		initMobileCheckCache(manager);
		System.out.println("initMobileCheckCache end");
		
//		System.out.println("initGroupList begin");
//		initGroupList(manager,conn);
//		System.out.println("initGroupList end");
		
//		System.out.println("initAgentMsgSysParam begin");
//		initAgentMsgSysParam(manager,conn);
//		System.out.println("initAgentMsgSysParam end");
		
//		System.out.println("initAgentAssignPanelCache begin");
//		initAgentAssignPanelCache(manager);
//		System.out.println("initAgentAssignPanelCache end");
//		
//		System.out.println("initAgentShrotcutActionHtml begin");
//		initAgentShrotcutActionHtml(manager,conn);
//		System.out.println("initAgentShrotcutActionHtml end");
//		
//		System.out.println("initMsgCountCache begin");
//		initMsgCountCache(manager,conn);
//		System.out.println("initMsgCountCache end");
//		
//		System.out.println("initAgentMemberData begin");
//		initAgentMemberData(manager,conn);
//		System.out.println("initAgentMemberData end");
//		
//		System.out.println("initMemberGroupList begin");
//		initMemberGroupList(manager,conn);
//		System.out.println("initMemberGroupList end");
//
//		System.out.println("initAgentInfoList begin");
//		initAgentInfoList(manager,conn);
//		System.out.println("initAgentInfoList end");

		
		try {//AGENT_TRIP_ASSIGN_PANEL_MAP_CACHE
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
				try {
					if(conn!=null&&!conn.isClosed())
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	/**
	 * 初始化数据字典文件到缓存
	 * 方   法  名:initDataDic
	 * 方法描述:
	 * 参         数:@param conn
	 * 返   回  值:void
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 * typecode:element(List<TsDataDicPO>)
	 */
	private void initDataDic(CacheManager manager, Connection conn){
		DATA_DIC_CACHE = manager.getCache("SystemDicCache");  
		DATA_DIC_MAP_CACHE = manager.getCache("DATA_DIC_MAP_CACHE");  
		TsDataDicPO ddPO=new TsDataDicPO();
		ddPO.setStatus("1");
		List<TsDataDicPO> list=POFactory.select(conn,ddPO);
		//根据sort排序
		Collections.sort(list,new Comparator<TsDataDicPO>() {
			public int compare(TsDataDicPO dic1, TsDataDicPO dic2) {
					int sort1= null==dic1.getSort() ? 0:dic1.getSort();
					int sort2= null==dic2.getSort() ? 0:dic2.getSort();
				return sort1-sort2;
			}
		});
		Element e=null;
		for(TsDataDicPO bean:list){
			String typeCode=bean.getTypeCode(); 
			DATA_DIC_MAP_CACHE.put(new Element(bean.getCode(), bean));
			
			if(DATA_DIC_CACHE.isKeyInCache(typeCode)){
			 e=DATA_DIC_CACHE.get(typeCode);
			 List<TsDataDicPO> cacheList=(List<TsDataDicPO>)e.getValue();
			 cacheList.add(bean);
			}else {
				List<TsDataDicPO> cacheList=new ArrayList<TsDataDicPO>();
				cacheList.add(bean);
				e=new Element(typeCode, cacheList);
				 
			}
			DATA_DIC_CACHE.put(e);
//	        System.out.println(DATA_DIC_CACHE.getSize());  

		}
//		System.out.println(((List<TsDataDicPO>)DATA_DIC_CACHE.get("2").getValue()).get(0).getCode());  
//      System.out.println(((List<TsDataDicPO>)DATA_DIC_CACHE.get("2").getValue()).get(1).getCode());  
	}

	
//	private void initAppInfo(Connection conn){
//		TbAppInfoPO tAPO=POFactory.getByPriKey(conn, new TbAppInfoPO());
//		if(tAPO!=null){
//			APP_CODE=tAPO.getAppCode(); //对应 TB_APP_INFO
//			APP_NO_PREFIX=tAPO.getNoPrefix();
//			APP_NAME=tAPO.getAppName();
//			APP_TYPE=tAPO.getAppType();
//		}
//		
//	}
	
	/**
	 * 初始化emp 未处理，未读消息数量
	 * 方   法  名:initMsgCountCache
	 * 方法描述:
	 * 参         数:@param manager
	 * 参         数:@param conn
	 * 返   回  值:void
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
//	private void initMsgCountCache(CacheManager manager,Connection conn){
//		AGENT_UNPROCESS_COUNT_CACHE = manager.getCache("AGENT_UNPROCESS_COUNT_CACHE"); 
//		AGENT_UNREAD_COUNT_CACHE = manager.getCache("AGENT_UNREAD_COUNT_CACHE"); 
//		AGENT_PROCESS_INFO_CACHE = manager.getCache("AGENT_PROCESS_INFO_CACHE"); 
//		AGENT_READ_NOTICE_CACHE = manager.getCache("AGENT_READ_NOTICE_CACHE"); 
//		TmAgentReceiveMsgListPOFactory.refreshMsgCountCache(conn);
//		TmAgentReceiveMsgListPOFactory.refreshMsgCache(conn);
//		if(AGENT_PROCESS_INFO_CACHE!=null)
//		System.out.println("AGENT_PROCESS_INFO_CACHE :"+AGENT_PROCESS_INFO_CACHE.getSize());
//		if(AGENT_READ_NOTICE_CACHE!=null)
//		System.out.println("AGENT_READ_NOTICE_CACHE :"+AGENT_READ_NOTICE_CACHE.getSize());
//	}
	
	/**
	 * 
	 * 方   法  名:initDriverAndVehicle
	 * 方法描述:初始化每个经销商的id，及其对应的车辆及司机数据。agent_id:element<list>
	 * 参         数:@param conn
	 * 返   回  值:void
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
//	private void initDriverAndVehicle(CacheManager manager, Connection conn){
//		
//	   List<DynaBean> driverList=TmAgentMemberPOFactory.queryAllAgentDriver(conn);
//	   List<DynaBean> vehicleList=TmAgentMemberPOFactory.queryAllAgentVehicle(conn);
//	   AGENT_DRIVER_LIST_CACHE = manager.getCache("AGENT_DRIVER_LIST_CACHE"); 
//	   AGENT_VEHICLE_LIST_CACHE = manager.getCache("AGENT_VEHICLE_LIST_CACHE"); 
//		Element e=null;
//		//init driver
//		for(DynaBean bean:driverList){
//			Integer agentId=bean.getInt("AGENT_ID"); 
//			if(AGENT_DRIVER_LIST_CACHE.isKeyInCache(agentId)){
//			 e=AGENT_DRIVER_LIST_CACHE.get(agentId);
//			 List<DynaBean> cacheList=(List<DynaBean>)e.getValue();
//			 cacheList.add(bean);
//			}else {
//				List<DynaBean> cacheList=new ArrayList<DynaBean>();
//				cacheList.add(bean);
//				e=new Element(agentId, cacheList);
//			}
//			AGENT_DRIVER_LIST_CACHE.put(e);
//	        System.out.println("AGENT_DRIVER_LIST_CACHE:="+AGENT_DRIVER_LIST_CACHE.getSize());  
//		} 
//		//init vehicle
//		for(DynaBean bean:vehicleList){
//			Integer agentId=bean.getInt("AGENT_ID"); 
//			if(AGENT_VEHICLE_LIST_CACHE.isKeyInCache(agentId)){
//			 e=AGENT_VEHICLE_LIST_CACHE.get(agentId);
//			 List<DynaBean> cacheList=(List<DynaBean>)e.getValue();
//			 cacheList.add(bean);
//			}else {
//				List<DynaBean> cacheList=new ArrayList<DynaBean>();
//				cacheList.add(bean);
//				e=new Element(agentId, cacheList);
//			}
//			AGENT_VEHICLE_LIST_CACHE.put(e);
//	        System.out.println("AGENT_VEHICLE_LIST_CACHE:="+AGENT_VEHICLE_LIST_CACHE.getSize());  
//		} 
//	}
	
	//AGENT_GROUP_LIST_CACHE//初始化调度组信息
//	private void initGroupList(CacheManager manager, Connection conn){
//		   List<TmAgentGroupPO> groupPOList=GroupInfoFactory.queryAllGroup(conn,0);
//		   AGENT_GROUP_LIST_CACHE = manager.getCache("AGENT_GROUP_LIST_CACHE"); 
//			Element e=null;
//			//init driver
//			String agentId=null;
//			for(TmAgentGroupPO bean:groupPOList){
//				agentId=String.valueOf(bean.getAgentId()); 
//				if(AGENT_GROUP_LIST_CACHE.isKeyInCache(agentId)){
//				 e=AGENT_GROUP_LIST_CACHE.get(agentId);
//				 List<TmAgentGroupPO> cacheList=(List<TmAgentGroupPO>)e.getValue();
//				 cacheList.add(bean);
//				}else {
//					List<TmAgentGroupPO> cacheList=new ArrayList<TmAgentGroupPO>();
//					cacheList.add(bean);
//					e=new Element(agentId, cacheList);
//				}
//				AGENT_GROUP_LIST_CACHE.put(e);
//		        System.out.println("AGENT_GROUP_LIST_CACHE:="+AGENT_GROUP_LIST_CACHE.getSize());  
//			} 
//	}
//	
	
	
//	private void initBusAndDriverCalendarTemp(CacheManager manager,Connection conn){
//		BUS_DRIVER_CALENDAR_CACHE=manager.getCache("BUS_DRIVER_CALENDAR_CACHE"); 
//		//test code //need init from db
//		List<Bus2DriverBean> list=new ArrayList<Bus2DriverBean>();
//		Bus2DriverBean bdBean=new Bus2DriverBean();
//		bdBean.setId("1000");
//		bdBean.setTitle("王师傅");
//		bdBean.setStartTime("2015-4-23T14:30:00-05:00");
//		bdBean.setStartTime("2015-4-23");
//		list.add(bdBean);
//		
//		bdBean=new Bus2DriverBean();
//		bdBean.setId("1001");
//		bdBean.setTitle("刘师傅");
//		bdBean.setStartTime("2015-4-25");
//		list.add(bdBean);
//		
//		Integer agentId=1;
//		Element e=new Element(agentId, list);
//		BUS_DRIVER_CALENDAR_CACHE.put(e);
//	}
//	
	
		private void initFAOList(CacheManager manager,Connection conn){
			List<DynaBean> list=TbSysRolePOFactory.queryFunActOptList(conn);
			FUN_ACT_OPT_CACHE=manager.getCache("FUN_ACT_OPT_CACHE"); 
			Element e=new Element("FUN_ACT_OPT_CACHE_KEY", list);
			FUN_ACT_OPT_CACHE.put(e); //权限功能菜单 list
			//into cache
		}
	
	/**
	 * 
	 * 方   法  名:initAllFAOHtml
	 * 方法描述:初始化权限缓存
	 * 参         数:@param manager
	 * 参         数:@param conn
	 * 返   回  值:void
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
//   	private void initAllFAOHtml(CacheManager manager,Connection conn){
//		String faoHtml=YBUtility.getAllFAOHtmlFromFAOBeanList(YBPayframeUtil.getAllFaoList(conn));
//		FUN_ACT_OPT_HTML_CACHE=manager.getCache("FUN_ACT_OPT_HTML_CACHE"); 
//		Element e=new Element("FUN_ACT_OPT_HTML", faoHtml);
//		FUN_ACT_OPT_HTML_CACHE.put(e); //权限功能菜单html
//		//into cache
//	}
	
	/**
	 * 初始化手机验证码缓存
	 * 方   法  名:initMobileCheckCache
	 * 方法描述:
	 * 参         数:@param manager
	 * 返   回  值:void
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
//	private void initMobileCheckCache(CacheManager manager){
//		AGENT_MOBILE_CHECK_CODE_CACHE=manager.getCache("AGENT_MOBILE_CHECK_CODE_CACHE"); 
//		//into cache
//	}
	
	/**
	 * 方   法  名:initAgentAssignPanelCache
	 * 方法描述:[性能-缓存]
	 * 参         数:@param manager
	 * 参         数:@param conn
	 * 返   回  值:void
	 * 创   建  人:Ivin
	 * @exception
	 * @since  1.0.0
	*/
//	private void initAgentAssignPanelCache(CacheManager manager){
//		AGENT_ASSIGN_PANEL_CACHE=manager.getCache("AGENT_ASSIGN_PANEL_CACHE"); 
//		//into cache
//	}
	
//	TS_APP_ALL_INFO_CACHE
//	private void initAppAllInfoCache(CacheManager manager,Connection conn){
//		TsAppAllInfoPO taPO=new TsAppAllInfoPO();
//		List<TsAppAllInfoPO> list=POFactory.select(conn, taPO);
//		TS_APP_ALL_INFO_CACHE=manager.getCache("TS_APP_ALL_INFO_CACHE");
//		if(list!=null&&list.size()>0){
//		 for(TsAppAllInfoPO temp:list){
//			String appCode=temp.getAppCode();
//			Element e=new Element(appCode, temp);
//			TS_APP_ALL_INFO_CACHE.put(e);
//		 }
//		}
//	}
	
	/**
	 * 初始化默认消息参数对应
	 * 方   法  名:initAgentMsgSysParam
	 * 方法描述:
	 * 参         数:@param manager
	 * 参         数:@param conn
	 * 返   回  值:void
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
//	private void initAgentMsgSysParam(CacheManager manager,Connection conn){
//		AGENT_MSG_PARAM_CACHE=manager.getCache("AGENT_MSG_PARAM_CACHE");
//		List<TbAgentMsgSysParameterPO> list=TmAgentMsgParameterPOFactory.queryAgentMsgSysParam(conn);
//		if(DBConUtil.listNotNULL(list)){
//			for(TbAgentMsgSysParameterPO temp:list){
//				String paramKeyCode=temp.getParamKeyCode();
//				Element e=new Element(paramKeyCode, temp);
//				AGENT_MSG_PARAM_CACHE.put(e);
//			}
//		}
//	}
	
	/**
	 * 废弃
	 * 方   法  名:initAgentShortcutAction
	 * 方法描述:
	 * 参         数:@param manager
	 * 参         数:@param conn
	 * 返   回  值:void
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
//	private void initAgentShortcutAction(CacheManager manager,Connection conn){
//		AGENT_SHORTCUT_ACTION_CACHE=manager.getCache("AGENT_SHORTCUT_ACTION_CACHE");
//		//TM_AGENT_SHORTCUT_ACTION
//		List<TmAgentShortcutActionPO> list=TmAgentShortcutActionPOFactory.queryAgentShrotcutAction(conn);
//		if(DBConUtil.listNotNULL(list)){
//			for(TmAgentShortcutActionPO temp:list){
//				String code=temp.getShortcutCode();
//				Element e=new Element(code, temp);
//				AGENT_SHORTCUT_ACTION_CACHE.put(e);
//			}
//		}
//	}
	
	/**
	 * 初始化首页快捷菜单的数据，并生产checkbox的html 放入缓存
	 * 方   法  名:initAgentShrotcutActionHtml
	 * 方法描述:
	 * 参         数:@param manager
	 * 参         数:@param conn
	 * 返   回  值:void
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
//	private void initAgentShrotcutActionHtml(CacheManager manager,Connection conn){
//		AGENT_SHORTCUT_ACTION_CACHE=manager.getCache("AGENT_SHORTCUT_ACTION_CACHE");
//		//TM_AGENT_SHORTCUT_ACTION
//		List<TmAgentShortcutActionPO> list=TmAgentShortcutActionPOFactory.queryAgentShrotcutAction(conn);
//		if(DBConUtil.listNotNULL(list)){
//			for(TmAgentShortcutActionPO temp:list){
//				String code=temp.getShortcutCode();
//				Element e=new Element(code, temp);
//				AGENT_SHORTCUT_ACTION_CACHE.put(e);
//			}
//		}
//		String shortcutHtml=TmAgentShortcutActionPOFactory.packShortcutActionCheckboxHtml(list);
//		AGENT_SHORTCUT_ACTION_HTML_CACHE=manager.getCache("AGENT_SHORTCUT_ACTION_HTML_CACHE");
//		AGENT_SHORTCUT_ACTION_HTML_CACHE.put(new Element("SHORTCUT_ACTION_HTML", shortcutHtml));
//	}
	
	/**
	 * 初始化本应用上车队成员信息key：AGENT_ID-MEMBER_ID-ID VALUE:BEAN(TM_AGENT_MEMBER)
	 * 方   法  名:initAgentMemberData
	 * 方法描述:
	 * 参         数:@param manager
	 * 参         数:@param conn
	 * 返   回  值:void
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
//	private void initAgentMemberData(CacheManager manager,Connection conn){
//		if(AGENT_MEMBER_DATA_CACHE==null)AGENT_MEMBER_DATA_CACHE=manager.getCache("AGENT_MEMBER_DATA_CACHE");
//		List<TmAgentMemberPO> list=POFactory.select(conn, new TmAgentMemberPO());
//		if(list!=null){
//			int id=0;
//			int memberId=0;
//			int agentId=0;
//			String key=null;
//			Element e=null;
//			for(TmAgentMemberPO temp:list){
//				id=temp.getId()==0?0:temp.getId();
//				memberId=temp.getMemberId()==null?0:temp.getMemberId();
//				agentId=temp.getAgentId()==null?0:temp.getAgentId();
//				key=agentId+"-"+memberId+"-"+id;
//				e=new Element(key, temp);
//				AGENT_MEMBER_DATA_CACHE.put(e);
//			}
//		}
//	}
	
	//AGENT_MEMBER_GROUP_LIST_CACHE//初始化调度对应调度组信息
//	@SuppressWarnings("unchecked")
//	private void initMemberGroupList(CacheManager manager, Connection conn) {
//		
//		List<DynaBean> groupPOList = GroupInfoFactory.queryAllMemberGroup(conn,0);
//		AGENT_MEMBER_GROUP_LIST_CACHE = manager.getCache("AGENT_MEMBER_GROUP_LIST_CACHE"); 
//			
//		Element e = null;
//		String agentId=null;
//		Map<String, Map<String, String>> map = null;
//		
//		for(DynaBean bean : groupPOList){
//			
//			agentId = String.valueOf(bean.get("AGENT_ID")); 
//				
//			Map<String, String> item = new HashMap<String, String>();
//			item.put("MEMBER_ID", String.valueOf(bean.get("MEMBER_ID")));
//			item.put("AGENT_ID", String.valueOf(bean.get("AGENT_ID")));
//			item.put("GROUP_NO", String.valueOf(bean.get("GROUP_NO")));
//			item.put("GROUP_ID", String.valueOf(bean.get("GROUP_ID")));
//			
//			if(AGENT_MEMBER_GROUP_LIST_CACHE.isKeyInCache(agentId)){
//				
//				e=AGENT_MEMBER_GROUP_LIST_CACHE.get(agentId);
//				map = (Map<String, Map<String, String>>) e.getValue();				
//				map.put(String.valueOf(bean.get("MEMBER_ID")), item);
//				
//			}else {
//				
//				map = new HashMap<String, Map<String, String>>();
//				
//				map.put(String.valueOf(bean.get("MEMBER_ID")), item);
//				
//				e=new Element(agentId, map);
//				System.out.println("AGENT_MEMBER_GROUP_LIST_CACHE agentId:="+agentId); 
//			}
//			
//			AGENT_MEMBER_GROUP_LIST_CACHE.put(e);
//		    //System.out.println("AGENT_MEMBER_GROUP_LIST_CACHE:="+AGENT_MEMBER_GROUP_LIST_CACHE.getSize());  
//		} 
//	}
		
	//AGENT_INFO_LIST_CACHE;//车队信息
//	private void initAgentInfoList(CacheManager manager, Connection conn){
//		
//		AGENT_INFO_LIST_CACHE=manager.getCache("AGENT_INFO_LIST_CACHE");
//		
//		//TM_AGENT_INFO
//		TmAgentInfoPO cnd = new TmAgentInfoPO();
//		cnd.setStatus("1");
//		List<TmAgentInfoPO> list = POFactory.select(conn, cnd);
//		
//		Element e = null;
//		String agentId=null;
//		
//		if(DBConUtil.listNotNULL(list)){
//			
//			for(TmAgentInfoPO po : list){
//				
//				agentId = String.valueOf(po.getAgentId());				
//				e = new Element(agentId, po);
//				
//				AGENT_INFO_LIST_CACHE.put(e);
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
	
	/**
	 * 不包含安全信息缓存
	 * 方   法  名:getCache
	 * 方法描述:
	 * 参         数:@param cacheName
	 * 参         数:@return
	 * 返   回  值:Cache
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static Cache getCache(String cacheName){
//		if("CITY_CACHE".equalsIgnoreCase(cacheName))return CITY_CACHE;
//		else 
			if("DATA_DIC_CACHE".equalsIgnoreCase(cacheName))return DATA_DIC_CACHE;
		else if("DATA_DIC_MAP_CACHE".equalsIgnoreCase(cacheName))return DATA_DIC_MAP_CACHE;
		else if("FUN_ACT_OPT_CACHE".equalsIgnoreCase(cacheName))return FUN_ACT_OPT_CACHE;
//		else if("AGENT_VEHICLE_LIST_CACHE".equalsIgnoreCase(cacheName))return AGENT_VEHICLE_LIST_CACHE;
//		else if("AGENT_DRIVER_LIST_CACHE".equalsIgnoreCase(cacheName))return AGENT_DRIVER_LIST_CACHE;
//		else if("FUN_ACT_OPT_HTML_CACHE".equalsIgnoreCase(cacheName))return FUN_ACT_OPT_HTML_CACHE;
//		else if("AGENT_ASSIGN_PANEL_CACHE".equalsIgnoreCase(cacheName))return AGENT_ASSIGN_PANEL_CACHE;
//		else if("TS_APP_ALL_INFO_CACHE".equalsIgnoreCase(cacheName))return TS_APP_ALL_INFO_CACHE;
//		else if("AGENT_MOBILE_CHECK_CODE_CACHE".equalsIgnoreCase(cacheName))return AGENT_MOBILE_CHECK_CODE_CACHE;
//		else if("AGENT_GROUP_LIST_CACHE".equalsIgnoreCase(cacheName))return AGENT_GROUP_LIST_CACHE;
//		else if("AGENT_MSG_PARAM_CACHE".equalsIgnoreCase(cacheName))return AGENT_MSG_PARAM_CACHE;
//		else if("AGENT_SHORTCUT_ACTION_CACHE".equalsIgnoreCase(cacheName))return AGENT_SHORTCUT_ACTION_CACHE;
//		else if("AGENT_SHORTCUT_ACTION_HTML_CACHE".equalsIgnoreCase(cacheName))return AGENT_SHORTCUT_ACTION_HTML_CACHE;
//		else if("AGENT_UNPROCESS_COUNT_CACHE".equalsIgnoreCase(cacheName))return AGENT_UNPROCESS_COUNT_CACHE;
//		else if("AGENT_UNREAD_COUNT_CACHE".equalsIgnoreCase(cacheName))return AGENT_UNREAD_COUNT_CACHE;
//		else if("AGENT_PROCESS_INFO_CACHE".equalsIgnoreCase(cacheName))return AGENT_PROCESS_INFO_CACHE;
//		else if("AGENT_READ_NOTICE_CACHE".equalsIgnoreCase(cacheName))return AGENT_READ_NOTICE_CACHE;
//		else if("MOBILE_TOKEN_CACHE".equalsIgnoreCase(cacheName))return MOBILE_TOKEN_CACHE;
//		else if("AGENT_MEMBER_DATA_CACHE".equalsIgnoreCase(cacheName))return AGENT_MEMBER_DATA_CACHE;
//		else if("AGENT_MEMBER_GROUP_LIST_CACHE".equalsIgnoreCase(cacheName))return AGENT_MEMBER_GROUP_LIST_CACHE;
//		else if("AGENT_INFO_LIST_CACHE".equalsIgnoreCase(cacheName))return AGENT_INFO_LIST_CACHE;
		
		return null;
	}

	//AGENT_MEMBER_GROUP_LIST_CACHE
	//初始化调度对应调度组信息
//	public static Element getAgentMemberGroupListCache(String cacheName, String key){
//
//		if("AGENT_MEMBER_GROUP_LIST_CACHE".equalsIgnoreCase(cacheName)) {
//
//			YoubusNativeCacheService service = new YoubusNativeCacheService();
//			CacheManager manager = service.getCacheManager();
//	
//			Element e = null;
//			Connection conn = null;
//			
//			if(AGENT_MEMBER_GROUP_LIST_CACHE.isKeyInCache(key)){
//				
//				e = YoubusNativeCacheService.getCache(cacheName).get(key);
//				System.out.println("AGENT_MEMBER_GROUP_LIST_CACHE");
//			}else {
//				
//				conn = DBService.getInstance().getConnection();
//				
//				service.initMemberGroupList(manager, conn);				
//
//				e = YoubusNativeCacheService.getCache(cacheName).get(key);
//				System.out.println("DB+AGENT_MEMBER_GROUP_LIST_CACHE");
//			}
//			
//			if(e==null) {
//				
//				conn = DBService.getInstance().getConnection();
//				
//				service.initMemberGroupList(manager, conn);				
//
//				e = YoubusNativeCacheService.getCache(cacheName).get(key);
//				System.out.println("DB+AGENT_MEMBER_GROUP_LIST_CACHE");
//			}
//			
//			try {
//				if(conn!=null&&!conn.isClosed())
//					conn.close();
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}finally{
//					try {
//						if(conn!=null&&!conn.isClosed())
//						conn.close();
//					} catch (SQLException e1) {
//						e1.printStackTrace();
//					}
//			}
//			
//			//AGENT_MEMBER_GROUP_LIST_CACHE.put(e);
//			
//			return e;
//		}		
//		
//		return null;
//	
//	}
	
//	//AGENT_GROUP_LIST_CACHE
//	//初始化调度组信息息
//	public static Element getGroupListCache(String cacheName, String key){
//
//		if("AGENT_GROUP_LIST_CACHE".equalsIgnoreCase(cacheName)) {
//			
//			YoubusNativeCacheService service = new YoubusNativeCacheService();
//			CacheManager manager = service.getCacheManager();
//				
//			Element e = null;
//			Connection conn = null;
//			
//			if(AGENT_GROUP_LIST_CACHE.isKeyInCache(key)){
//				
//				e = YoubusNativeCacheService.getCache(cacheName).get(key);	
//				
//			}else {
//				
//				conn = DBService.getInstance().getConnection();
//				
//				service.initGroupList(manager, conn);				
//
//				e = YoubusNativeCacheService.getCache(cacheName).get(key);	
//			}
//			
//			if(e==null) {
//				
//				conn = DBService.getInstance().getConnection();
//				
//				service.initGroupList(manager, conn);				
//
//				e = YoubusNativeCacheService.getCache(cacheName).get(key);
//			}
//			
//			try {
//				if(conn!=null&&!conn.isClosed())
//					conn.close();
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}finally{
//					try {
//						if(conn!=null&&!conn.isClosed())
//						conn.close();
//					} catch (SQLException e1) {
//						e1.printStackTrace();
//					}
//			}
//			
//			//AGENT_GROUP_LIST_CACHE.put(e);
//
//			return e;
//		}		
//		
//		return null;
//	
//	}
//		
//	//AGENT_INFO_LIST_CACHE;
//	//车队信息
//	public static Element getAgentInfoListCache(String cacheName, String key){
//
//		if("AGENT_INFO_LIST_CACHE".equalsIgnoreCase(cacheName)) {
//
//			YoubusNativeCacheService service = new YoubusNativeCacheService();
//			CacheManager manager = service.getCacheManager();
//			
//			Element e = null;
//			Connection conn = null;
//			
//			if(AGENT_INFO_LIST_CACHE.isKeyInCache(key)){
//				
//				e = YoubusNativeCacheService.getCache(cacheName).get(key);	
//				
//			}else {
//				
//				conn = DBService.getInstance().getConnection();
//				
//				service.initAgentInfoList(manager, conn);				
//
//				e = YoubusNativeCacheService.getCache(cacheName).get(key);
//			}
//			
//			if(e==null) {
//				
//				conn = DBService.getInstance().getConnection();
//				
//				service.initAgentInfoList(manager, conn);				
//
//				e = YoubusNativeCacheService.getCache(cacheName).get(key);
//			}
//			
//			try {
//
//				if(conn!=null&&!conn.isClosed())
//					conn.close();
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}finally{
//					try {
//						if(conn!=null&&!conn.isClosed())
//						conn.close();
//					} catch (SQLException e1) {
//						e1.printStackTrace();
//					}
//			}
//			
//			//AGENT_INFO_LIST_CACHE.put(e);
//			
//			return e;
//		}		
//		
//		return null;
//	
//	}
		
	public CacheManager getCacheManager() {
		return new CacheManager(cacheUrl);
	}
}
