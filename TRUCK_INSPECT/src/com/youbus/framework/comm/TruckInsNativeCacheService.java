/**
 * ��Ŀ����:AGENT_CENTER
 * ��         ��:com.youbus.framework.comm
 * ��   ��  ��:YoubusNativeCacheService.java
 * �� ������:2015��4��4��-����5:10:42
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
 * ������:YoubusNativeCacheService
 * ������:Ӧ�ñ��ػ��棬�洢��������(ʡ���أ������ֵ�)
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2015��4��4�� ����5:10:42
 * �޸ı�ע:
 * @version 1.0.0
 */
public class TruckInsNativeCacheService implements Service {
	
	private static Logger log=Logger.getLogger(TruckInsNativeCacheService.class);

//	public static Cache CITY_CACHE; //<��, bean>
	public static Cache DATA_DIC_CACHE; //<�����ֵ��><typecode:bean>
	public static Cache DATA_DIC_MAP_CACHE; //<�����ֵ��><code:bean>
	public static Cache FUN_ACT_OPT_CACHE; //FUNTION ACTION OPTION LIST
	
//	public static Cache REMOTE_REG_INFO_CACHE ; //��ȫ����Զ�̵�¼��Ϣ; 
//	public static Cache AGENT_VEHICLE_LIST_CACHE ; //agent��Ͻ������Ϣ; 
//	public static Cache AGENT_DRIVER_LIST_CACHE ; //agent��Ͻ˾����Ϣ; 
//	
//	public static Cache AGENT_AUTH_SESSION_CACHE ; //agentcenter�洢��ȫ���ĻỰ�뱾�ػỰ�Ķ�Ӧ��ϵ 
//	public static Cache AGENT_SESSION_CACHE ; //agentcenter�洢�����ػỰ��״̬
//	
//	public static Cache BUS_DRIVER_CALENDAR_CACHE ; //����˾����������; 
//	public static Cache AGENT_MSG_PARAM_CACHE ; //ϵͳĬ����Ϣ����
//	public static Cache AGENT_UNPROCESS_COUNT_CACHE ; //��empId����ÿ�˵�δ������Ϣ��Ŀ
//	public static Cache AGENT_UNREAD_COUNT_CACHE ; //��empId����ÿ�˵�δ��ȡ��Ϣ��Ŀ
//	
//	public static Cache AGENT_PROCESS_INFO_CACHE ; //��empId����ÿ�˵�����һ������Ϣ����Ҫ�����벻��Ҫ����
//	public static Cache AGENT_READ_NOTICE_CACHE ; //��empId����ÿ�˵�����һ����֪ͨ���Ѷ�δ����
//	
//	public static Cache FUN_ACT_OPT_HTML_CACHE ; //ϵͳȨ�޹��ܲ˵��б�
//	public static Cache AGENT_SHORTCUT_ACTION_CACHE ; //��ҳ��ݲ˵��б� //TM_AGENT_SHORTCUT_ACTION
//	public static Cache AGENT_SHORTCUT_ACTION_HTML_CACHE ; //��ҳ��ݲ˵�CHECK BOX HTML//TM_AGENT_SHORTCUT_ACTION 
//	
//	
//	public static Cache TS_APP_ALL_INFO_CACHE ; //TS_APP_ALL_INFO
//	
//	public static Cache AGENT_MOBILE_CHECK_CODE_CACHE;//����ƽ̨�ֻ���֤�뻺��
//	
//	public static Cache AGENT_INFO_LIST_CACHE;//������Ϣ
//	
//	public static Cache AGENT_MEMBER_DATA_CACHE;//��Ӧ���ϳ��ӳ�Ա��Ϣkey��AGENT_ID-MEMBER_ID-ID VALUE:BEAN(TM_AGENT_MEMBER)
//	
//	public static Cache MOBILE_TOKEN_CACHE;//�ֻ���¼�û�token���� key:token--value(DynaBean<MEMBER_ID:STRING.MEMBER_ID;GROUP_ID:STRING.GROUP_ID>)
	
	private final String cacheUrl=FrameworkConstant.APP_CONTEXT_PATH+"WEB-INF\\AppCache.xml";
	
	public static String APP_CODE; //��Ӧ TB_APP_INFO
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
		 * ��ѯ���ݿ⣬װ�����ݴ��롣
		 */
//		Element element=new Element("010","����");CITY_CACHE.put(element);
//		element=new Element("021","�Ϻ�");CITY_CACHE.put(element);
		
		//��ʼ�������ֵ�
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
	 * ��ʼ�������ֵ��ļ�������
	 * ��   ��  ��:initDataDic
	 * ��������:
	 * ��         ��:@param conn
	 * ��   ��  ֵ:void
	 * ��   ��  ��:rock
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
		//����sort����
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
//			APP_CODE=tAPO.getAppCode(); //��Ӧ TB_APP_INFO
//			APP_NO_PREFIX=tAPO.getNoPrefix();
//			APP_NAME=tAPO.getAppName();
//			APP_TYPE=tAPO.getAppType();
//		}
//		
//	}
	
	/**
	 * ��ʼ��emp δ����δ����Ϣ����
	 * ��   ��  ��:initMsgCountCache
	 * ��������:
	 * ��         ��:@param manager
	 * ��         ��:@param conn
	 * ��   ��  ֵ:void
	 * ��   ��  ��:rock
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
	 * ��   ��  ��:initDriverAndVehicle
	 * ��������:��ʼ��ÿ�������̵�id�������Ӧ�ĳ�����˾�����ݡ�agent_id:element<list>
	 * ��         ��:@param conn
	 * ��   ��  ֵ:void
	 * ��   ��  ��:rock
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
	
	//AGENT_GROUP_LIST_CACHE//��ʼ����������Ϣ
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
//		bdBean.setTitle("��ʦ��");
//		bdBean.setStartTime("2015-4-23T14:30:00-05:00");
//		bdBean.setStartTime("2015-4-23");
//		list.add(bdBean);
//		
//		bdBean=new Bus2DriverBean();
//		bdBean.setId("1001");
//		bdBean.setTitle("��ʦ��");
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
			FUN_ACT_OPT_CACHE.put(e); //Ȩ�޹��ܲ˵� list
			//into cache
		}
	
	/**
	 * 
	 * ��   ��  ��:initAllFAOHtml
	 * ��������:��ʼ��Ȩ�޻���
	 * ��         ��:@param manager
	 * ��         ��:@param conn
	 * ��   ��  ֵ:void
	 * ��   ��  ��:rock
	 * @exception
	 * @since  1.0.0
	 */
//   	private void initAllFAOHtml(CacheManager manager,Connection conn){
//		String faoHtml=YBUtility.getAllFAOHtmlFromFAOBeanList(YBPayframeUtil.getAllFaoList(conn));
//		FUN_ACT_OPT_HTML_CACHE=manager.getCache("FUN_ACT_OPT_HTML_CACHE"); 
//		Element e=new Element("FUN_ACT_OPT_HTML", faoHtml);
//		FUN_ACT_OPT_HTML_CACHE.put(e); //Ȩ�޹��ܲ˵�html
//		//into cache
//	}
	
	/**
	 * ��ʼ���ֻ���֤�뻺��
	 * ��   ��  ��:initMobileCheckCache
	 * ��������:
	 * ��         ��:@param manager
	 * ��   ��  ֵ:void
	 * ��   ��  ��:rock
	 * @exception
	 * @since  1.0.0
	 */
//	private void initMobileCheckCache(CacheManager manager){
//		AGENT_MOBILE_CHECK_CODE_CACHE=manager.getCache("AGENT_MOBILE_CHECK_CODE_CACHE"); 
//		//into cache
//	}
	
	/**
	 * ��   ��  ��:initAgentAssignPanelCache
	 * ��������:[����-����]
	 * ��         ��:@param manager
	 * ��         ��:@param conn
	 * ��   ��  ֵ:void
	 * ��   ��  ��:Ivin
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
	 * ��ʼ��Ĭ����Ϣ������Ӧ
	 * ��   ��  ��:initAgentMsgSysParam
	 * ��������:
	 * ��         ��:@param manager
	 * ��         ��:@param conn
	 * ��   ��  ֵ:void
	 * ��   ��  ��:rock
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
	 * ����
	 * ��   ��  ��:initAgentShortcutAction
	 * ��������:
	 * ��         ��:@param manager
	 * ��         ��:@param conn
	 * ��   ��  ֵ:void
	 * ��   ��  ��:rock
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
	 * ��ʼ����ҳ��ݲ˵������ݣ�������checkbox��html ���뻺��
	 * ��   ��  ��:initAgentShrotcutActionHtml
	 * ��������:
	 * ��         ��:@param manager
	 * ��         ��:@param conn
	 * ��   ��  ֵ:void
	 * ��   ��  ��:rock
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
	 * ��ʼ����Ӧ���ϳ��ӳ�Ա��Ϣkey��AGENT_ID-MEMBER_ID-ID VALUE:BEAN(TM_AGENT_MEMBER)
	 * ��   ��  ��:initAgentMemberData
	 * ��������:
	 * ��         ��:@param manager
	 * ��         ��:@param conn
	 * ��   ��  ֵ:void
	 * ��   ��  ��:rock
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
	
	//AGENT_MEMBER_GROUP_LIST_CACHE//��ʼ�����ȶ�Ӧ��������Ϣ
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
		
	//AGENT_INFO_LIST_CACHE;//������Ϣ
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
	 * ��������ȫ��Ϣ����
	 * ��   ��  ��:getCache
	 * ��������:
	 * ��         ��:@param cacheName
	 * ��         ��:@return
	 * ��   ��  ֵ:Cache
	 * ��   ��  ��:rock
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
	//��ʼ�����ȶ�Ӧ��������Ϣ
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
//	//��ʼ����������ϢϢ
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
//	//������Ϣ
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
