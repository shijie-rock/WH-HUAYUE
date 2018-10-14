/**
 * 项目名称:AGENT_CENTER
 * 包         名:com.youbus.framework.comm
 * 文   件  名:YoubusCacheUtil.java
 * 创 建日期:2015年9月9日-下午8:23:44
 * Copyright @ 2015-YouBus.com.cn
 */
package com.youbus.framework.comm;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import com.youbus.customer.common.CommonConstant;

/**
 * 类名称:YoubusCacheUtil
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2015年9月9日 下午8:23:44
 * 修改备注:
 * @version 1.0.0
 */
public class YoubusCacheUtil {

	/**
	 * 创建一个新的实例 YoubusCacheUtil.
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
//	public static String getCacheMobilePositionCurInfoKey(String mobile){ //存储key的list
//		return YBCommonContant.SYS_NAME+"-MOBILE_INFO-POSITION_CUR_INFO-"+mobile;
//	}
	public static String getCacheAgentIdAndBusIdViaPositionCurInfoKey(String agentId, String busId){ //存储key的list
		return YBCommonContant.SYS_NAME+"-TASK_VIA_INFO-PASS_INFO-"+agentId+"-"+busId;
	}
	public static String getCacheBusPositionCurInfoKeysKey(){ //
		return YBCommonContant.SYS_NAME+"-BUS_INFO-POSITION_CUR_INFO-KEY";
	}
	
	public static String getCacheBusPositionHisInfoKeysKey(){
		return YBCommonContant.SYS_NAME+"-BUS_INFO-POSITION_HIS_INFO-KEY";
	}
	
	
	//add 9-28
	public static String getCacheMobilePositionCurInfoKeysKey(){ //存储key的map
		return YBCommonContant.SYS_NAME+"-MOBILE_INFO-POSITION_CUR_INFO-KEY";
	}
	
	public static String getCacheMobilePositionHisInfoKeysKey(){//存储key的map
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
	public static String getCacheTaskViaPassInfoKeysKey(){//存储key的map
		return YBCommonContant.SYS_NAME+"-TASK_VIA_INFO-PASS_INFO-KEY";
	}
	
	public static String getCachePushDriverMsgUnReadCountKey(){//存储司机手机端未读消息总数Map 的Cache
		return YBCommonContant.SYS_NAME+"-MOBILE_INFO-PUSH_DRIVER_MSG_UNREAD_COUNT";
	}
	
	public static String getCacheMsgPushTokenKey(){//存储司机手机端车队id 用户 id与push token的对应关系
		return YBCommonContant.SYS_NAME+"-MOBILE_INFO-MSG_PUSH_TOKEN_INFO";
	}
	
	/**
	 * 获取行程缓存key值（车次号+日期）
	 * 
	 * @param tripDesc
	 * @param date
	 * @return
	 */
	public static String getChacheTripInfoByTripDescKey(String tripDesc, String date) {
		return "YOUBUS-"+tripDesc+"-"+date;
	}
	
	/**
	 * 获取行程缓存key值（客户ID）
	 * 
	 * @param tripDesc
	 * @param date
	 * @return
	 */
	public static String getChacheTripInfoByCustomerIdKey(String customerId) {
		return "YOUBUS-"+customerId+"-"+CommonConstant.EP_WX_ID;
	}


	/**
	 * 获取行程途径点缓存key值（派工单ID+AgetnId）
	 * 
	 * @param agentId
	 * @param busId
	 * @return
	 */
	public static String getCacheTaskViaPassInfoByBusIdKey(String agentId, String dailyId){
		return "YOUBUS-"+agentId+"-"+dailyId;
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

}
