/**
 * ��Ŀ����:AGENT_CENTER
 * ��         ��:com.youbus.framework.comm
 * ��   ��  ��:YoubusCacheUtil.java
 * �� ������:2015��9��9��-����8:23:44
 * Copyright @ 2015-YouBus.com.cn
 */
package com.youbus.framework.comm;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import com.youbus.customer.common.CommonConstant;

/**
 * ������:YoubusCacheUtil
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2015��9��9�� ����8:23:44
 * �޸ı�ע:
 * @version 1.0.0
 */
public class YoubusCacheUtil {

	/**
	 * ����һ���µ�ʵ�� YoubusCacheUtil.
	 *
	 */
	public YoubusCacheUtil() {
		// TODO Auto-generated constructor stub
	}
	
	public static String getEmpMsgCountKeyUnRead(int agentId,int empId){
		return YBCommonContant.SYS_NAME+"-EMP_MSG_COUNT-UNREAD-"+agentId+"-"+empId;
	}
	public static String getEmpMsgCountKeyUnParse(int agentId,int empId){
		return YBCommonContant.SYS_NAME+"-EMP_MSG_COUNT-UNPARSE-"+agentId+"-"+empId;
	}
	
	public static String getCacheBusInfoKey(int agentId,int busId){
		return YBCommonContant.SYS_NAME+"-BUS_INFO-BASE_INFO-"+agentId+"-"+busId;
	}
	
//	public static String getCacheBusPositionHisInfoKey(int agentId,int busId){
//		return YBCommonContant.SYS_NAME+"-BUS_INFO-POSITION_HIS_INFO-"+agentId+"-"+busId;
//	}
//	
//	public static String getCacheBusPositionCurInfoKey(int agentId,int busId){
//		return YBCommonContant.SYS_NAME+"-BUS_INFO-POSITION_CUR_INFO-"+agentId+"-"+busId;
//	}
	public static String getCacheBusPositionHisInfoKey(int agentId,int memberId){
		return YBCommonContant.SYS_NAME+"-BUS_INFO-POSITION_HIS_INFO-"+agentId+"-"+memberId;
	}
	
	public static String getCacheBusPositionCurInfoKey(int agentId,int memberId){
		return YBCommonContant.SYS_NAME+"-BUS_INFO-POSITION_CUR_INFO-"+agentId+"-"+memberId;
	}
	
	public static String getCacheLongitudeLatitudeBusGridKey(double longitudeValue,double latitudeValue){
		DecimalFormat formater=new DecimalFormat(".##");
		formater.setRoundingMode(RoundingMode.DOWN);
		return YBCommonContant.SYS_NAME+"-LONGITUDE_LATITUDE-BUS_GRID-"+formater.format(longitudeValue)+"-"+formater.format(latitudeValue);
	}
	public static String getCacheMobileLoginTokensKey(String token){ //YOUBUS-MOBILE_LOGIN_INFO-TOKENS
		return YBCommonContant.SYS_NAME+"-MOBILE_LOGIN_INFO-TOKENS-"+token;
	}
	public static String getCacheMobileLoginMobilesKey(String mobile){ //YOUBUS-MOBILE_LOGIN_INFO-MOBILES
		return YBCommonContant.SYS_NAME+"-MOBILE_LOGIN_INFO-MOBILES-"+mobile;
	}
//	public static String getCacheMobileLoginTokensKey(){ //YOUBUS-MOBILE_LOGIN_INFO-TOKENS
//		return YBCommonContant.SYS_NAME+"-MOBILE_LOGIN_INFO-TOKENS";
//	}
//	public static String getCacheMobileLoginMobilesKey(){ //YOUBUS-MOBILE_LOGIN_INFO-MOBILES
//		return YBCommonContant.SYS_NAME+"-MOBILE_LOGIN_INFO-MOBILES";
//	}
//	public static String getCacheMobilePositionCurInfoKey(String mobile){ //�洢key��list
//		return YBCommonContant.SYS_NAME+"-MOBILE_INFO-POSITION_CUR_INFO-"+mobile;
//	}
	public static String getCacheAgentIdAndBusIdViaPositionCurInfoKey(String agentId, String busId){ //�洢key��list
		return YBCommonContant.SYS_NAME+"-TASK_VIA_INFO-PASS_INFO-"+agentId+"-"+busId;
	}
	public static String getCacheBusPositionCurInfoKeysKey(){ //
		return YBCommonContant.SYS_NAME+"-BUS_INFO-POSITION_CUR_INFO-KEY";
	}
	
	public static String getCacheBusPositionHisInfoKeysKey(){
		return YBCommonContant.SYS_NAME+"-BUS_INFO-POSITION_HIS_INFO-KEY";
	}
	
	
	//add 9-28
	public static String getCacheMobilePositionCurInfoKeysKey(){ //�洢key��map
		return YBCommonContant.SYS_NAME+"-MOBILE_INFO-POSITION_CUR_INFO-KEY";
	}
	
	public static String getCacheMobilePositionHisInfoKeysKey(){//�洢key��map
		return YBCommonContant.SYS_NAME+"-MOBILE_INFO-POSITION_HIS_INFO-KEY";
	}
	
	public static String getCacheMobilePositionCurInfoKey(String mobile){ //
		return YBCommonContant.SYS_NAME+"-MOBILE_INFO-POSITION_CUR_INFO-"+mobile;
	}
	
	public static String getCacheMobilePositionHisInfoKey(String mobile){//
		return YBCommonContant.SYS_NAME+"-MOBILE_INFO-POSITION_HIS_INFO-"+mobile;
	}
	
	public static String getCacheTaskViaPassInfoKey(int agentId,int busId){//
		return YBCommonContant.SYS_NAME+"-TASK_VIA_INFO-PASS_INFO-"+agentId+"-"+busId;
	}
	public static String getCacheTaskViaPassInfoKeysKey(){//�洢key��map
		return YBCommonContant.SYS_NAME+"-TASK_VIA_INFO-PASS_INFO-KEY";
	}
	
	public static String getCachePushDriverMsgUnReadCountKey(){//�洢˾���ֻ���δ����Ϣ����Map ��Cache
		return YBCommonContant.SYS_NAME+"-MOBILE_INFO-PUSH_DRIVER_MSG_UNREAD_COUNT";
	}
	
	public static String getCacheMsgPushTokenKey(){//�洢˾���ֻ��˳���id �û� id��push token�Ķ�Ӧ��ϵ
		return YBCommonContant.SYS_NAME+"-MOBILE_INFO-MSG_PUSH_TOKEN_INFO";
	}
	
	/**
	 * ��ȡ�г̻���keyֵ�����κ�+���ڣ�
	 * 
	 * @param tripDesc
	 * @param date
	 * @return
	 */
	public static String getChacheTripInfoByTripDescKey(String tripDesc, String date) {
		return "YOUBUS-"+tripDesc+"-"+date;
	}
	
	/**
	 * ��ȡ�г̻���keyֵ���ͻ�ID��
	 * 
	 * @param tripDesc
	 * @param date
	 * @return
	 */
	public static String getChacheTripInfoByCustomerIdKey(String customerId) {
		return "YOUBUS-"+customerId+"-"+CommonConstant.EP_WX_ID;
	}


	/**
	 * ��ȡ�г�;���㻺��keyֵ���ɹ���ID+AgetnId��
	 * 
	 * @param agentId
	 * @param busId
	 * @return
	 */
	public static String getCacheTaskViaPassInfoByBusIdKey(String agentId, String dailyId){
		return "YOUBUS-"+agentId+"-"+dailyId;
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

}
