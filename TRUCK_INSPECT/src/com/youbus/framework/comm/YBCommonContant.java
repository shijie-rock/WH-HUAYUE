/**
 * 项目名称:AGENT_CENTER
 * 包         名:com.youbus.framework.comm
 * 文   件  名:YBCommonContant.java
 * 创 建日期:2015年4月13日-下午4:16:23
 * Copyright @ 2015-YouBus.com.cn
 */
package com.youbus.framework.comm;


/**
 * 类名称:YBCommonContant
 * 类描述:应用常量表
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2015年4月13日 下午4:16:23
 * 修改备注:
 * @version 1.0.0
 */
public class YBCommonContant {

	/**
	 * 创建一个新的实例 YBCommonContant.
	 *
	 */
	public YBCommonContant() {
		// TODO Auto-generated constructor stub
	}
	
	public static String AGENT_SERVER_VERSION="V1.8.1-alpha";/*应从数据库中动态读取*/
	
	public static final int SYS_DEFAULT_MEMBER_ID=0;
	
	public static final String BUS_TRIP_NO_TYPE="DT";//车队调度行程单-TM_BUS_TRIP_MAIN
	public static final String AGENT_TRIP_NO_TYPE="AT";//车队业务行程单-TM_AGENT_TRIP_TASK
	
	public static final String SINGLE_TRIP_LINE_SN_TOUR="L";//行程单班次号，班次类型代号，旅游
	public static final String SINGLE_TRIP_LINE_SN_SHUTTLE="J";//行程单班次号，班次类型代号，接送
	public static final String SINGLE_TRIP_LINE_SN_BUS="B";//行程单班次号，班次类型代号， 班车
	
	public static final String SESSION_USER_ID = "session_userid"; //member_id 全局唯一
	public static final String SESSION_EMP_ID = "session_empid"; //agent_member id 每个应用库 唯一
	public static final String SESSION_USER_NAME = "session_username"; //
	public static final String SESSION_AGENT_INFO = "session_agentinfo"; //车队基本信息
	public static final String SESSION_ID = "session_id";
	public static final String SESSION_COMP_ID = "session_compid";
	public static final String SESSION_USER_CODE = "session_usercode";
	public static final String SESSION_AGENT_ID = "session_agentid";
	public static final String SESSION_AUTH_SESSION_ID = "session_auth_sessionid";//安全中心登录时会话id
	public static final String SESSION_AGENT_NAME = "session_agent_name";
	public static final String SESSION_SYSCODE = "session_syscode";
	public static final String SESSION_LOGIN_USER = "loginuser";
	public static final String SESSION_CLENT_IP = "clentIP";
	public static final String SESSION_FAO_LIST = "session_FAOList";//权限功能菜单
	public static final String SESSION_FUN_LIST = "session_FUNList";//权限功能菜单
	public static final String SESSION_FAO_MAP = "session_FAOMAP";//权限功能菜单
	public static final String SESSION_ACTION_SHORTCUT = "session_ACTION_SHORTCUT";//个人首页快捷菜单
	public static final String SESSION_ACTION_SHORTCUT_ALL = "session_ACTION_SHORTCUT_ALL";//当前用户所有可以操作的快捷菜单（即可被选择的)
	public static final String SESSION_ACTION_URL_LIST = "session_ACTION_URL_LIST";//功能Action url list
	
	public static final String APP_TYPE_AUTH_CENTER="AUTH_CENTER";
	
	
	public static class TripAssignPanel{
		public static final double divisor  = 360.0; //panel单元格间隔，360分钟
		public static final int popBeginTime = 360; //tooltip弹出框开始时间(6:00 对应360分钟)
		public static final String Trip_Panel_Title = "trip-panel-title"; //panel第一行第一列标题
		public static final String Trip_Panel_Title_Default_Date = "00000000"; //0列默认日期
		public static final String Trip_Panel_License_Date_Separator = "@"; //拼接分隔符
		public static final String Trip_Panel_Title_Text = "车辆"; //panel第一行第一列标题
		public static final String Trip_Panel_License_Key = "license_key_xxxxxxxxxx"; //车牌key
		
		public static final int conflict_percent = 250;  //大于100即可
		
	}
	
	
	public static final String WORK_DEF_BEGIN_TIME = "07:00:00"; //系统默认行程：开始与结束时间 //应从数据字典读取
	public static final String WORK_DEF_END_TIME = "18:00:00";  //

	public static final String TRAVEL_EXCEL_PATH = "D:/YoubusTravelExcel";//
	public static final String UPLOAD_TEMP_PATH = "D:/YOUBUSFILEDATA/upload/";//
	
	
	public static final String FILE_SPACE_THUMB_DIR="youbusthumb" ;//缩略图子目录 
	                                                               //普通目录：AGENT_0001/20150514/1/333a9f02xxx.pdf
	                                                               //缩略图子目录 AGENT_0001/20150514/1/youbusthumb/333a9f02xxx_thumb.png..
	public static final String FILE_SPACE_THUMB_SUFFIX="_thumb";
	public static final String FILE_SAPCE_THUMB_TYPE="jpg";

	public static final String REMOTE_SERVER_CHANNEL="remote";//远程服务器的接收远程服务通道名
	public static final String MOBILE_REQ_CHANNEL="mobile";//远程服务接收手机发送请求通道名
	
	public static final String MOBILE_CHECK_CODE_BIND="MOBILE_CHECK_CODE_BIND";//手机验证码业务类型：绑定手机。
	public static final String MOBILE_CHECK_CODE_MODIFY="MOBILE_CHECK_CODE_MODIFY";//手机验证码业务类型：修改账号手机。
	
	//消息发起业务点
	public static final String BUSI_TYPE_ASSIGN_CONFIRM="BU_0010"; //派车确认
	public static final String BUSI_TYPE_TRIP_MODIFY="BU_0020";//行程单变更
	public static final String BUSI_TYPE_DRIVER_MODIFY="BU_0030";//司机更换
	public static final String BUSI_TYPE_LICENSE_MODIFY="BU_0040";//车辆变更
	public static final String BUSI_TYPE_TRIP_CANCEL="BU_0050";//行程单取消
	public static final String BUSI_TYPE_TRIP_CANCEL_SUB="BU_0051";//行程单部分取消派车
	public static final String BUSI_TYPE_TRIP_CASH_CHANGE="BU_0060";//现结变更
	
	//消息接收人类型
	public static final String MSG_RECEIVE_TYPE_AGENT_EMP="MR_0010"; //普通车队平台用户--暂不开放
	public static final String MSG_RECEIVE_TYPE_AGENT_DISPATCHER="MR_0020"; //车队调度--调度
	public static final String MSG_RECEIVE_TYPE_AGENT_DRIVER="MR_0030"; //车队司机（派单当前司机）--司机
	public static final String MSG_RECEIVE_TYPE_AGENT_DRIVER_PRE="MR_0031"; //派单前任司机
	public static final String MSG_RECEIVE_TYPE_AGENT_CUSTOMER="MR_0040"; //订单联系人//客户联系人、订车人--客户联系人
	public static final String MSG_RECEIVE_TYPE_AGENT_CUST_CONTACT="MR_0050"; //客户联系人//未用
	public static final String MSG_RECEIVE_TYPE_AGENT_CUST_PASSENGER="MR_0060"; //订单乘客//行程联系人--乘车联系人
	public static final String MSG_RECEIVE_TYPE_AGENT_CUST_MANAGER="MR_0070"; //客户负责人//客户所属业务员--业务员
	public static final String MSG_RECEIVE_TYPE_AGENT_TRADE_MANAGER="MR_0080"; //车辆所属协作车队负责人-借调车队联系人
	public static final String MSG_RECEIVE_TYPE_AGENT_TRADE_MANAGER_PRE="MR_0081"; //车辆所属协作车队负责人-前任借调车队联系人
	
	//消息发送渠道类型
	public static final String MSG_MSG_SEND_CHANNEL_SITE="SC_0010"; //系统内，站内信
	public static final String MSG_MSG_SEND_CHANNEL_SMS="SC_0020"; //短信
	public static final String MSG_MSG_SEND_CHANNEL_WEIXIN="SC_0030"; //微信
	public static final String MSG_MSG_SEND_CHANNEL_MAIL="SC_0040"; //邮件
	public static final String MSG_MSG_SEND_CHANNEL_APP="SC_0050"; //APP
	//消息发送状态
	public static final String MSG_SEND_STATUS_SEND_UNREADY="SS_0000"; //未准备好发送消息，消息暂存状态
	public static final String MSG_SEND_STATUS_SEND_READY="SS_0010"; //准备发送消息
	public static final String MSG_SEND_STATUS_SENDING="SS_0020"; //发送中
	public static final String MSG_SEND_STATUS_SEND_END="SS_0030"; //已发
	public static final String MSG_SEND_STATUS_SEND_REJECT="SS_0090"; //拒发（如用户需要不收短信，司机设置不收短信等）
	//消息默认过期时间长度
	public static final  int MSG_EXPIRE_TIME_LENGTH=60; //分钟
	
	//消息类型
	public static final  String MSG_MSG_TYPE_NOTICE="MY_0010"; //通知-不需处理
	public static final  String MSG_MSG_TYPE_INFO="MY_0020"; //消息-需处理
	//消息处理类型
	public static final  String MSG_MSG_PROCESS_TYPE_RECEIVE="MP_0010"; //接收人为直接目标
	public static final  String MSG_MSG_PROCESS_TYPE_CC="MP_0020"; //接收人为间接目标
	//消息读取类型
	public static final  String MSG_READ_STATUS_READY="RS_0010"; //未读
	public static final  String MSG_READ_STATUS_DONE="RS_0020"; //已读
	//消息处理状态
	public static final  String MSG_PROCESS_STATUS_READY="PS_0010"; //未处理（含TARGET_MSG_PROCESS_STATUS）
	public static final  String MSG_PROCESS_STATUS_DOING="PS_0020"; //处理中（相关主次ITEM消息锁定，同消息其他人无法处理，不含TARGET_MSG_PROCESS_STATUS）
	public static final  String MSG_PROCESS_STATUS_RECEIVE_DONE="PS_0030"; //当前消息接受者本人已处理（含TARGET_MSG_PROCESS_STATUS）-主动处理
	public static final  String MSG_PROCESS_STATUS_CC_DONE="PS_0040"; //当前消息其他接受者已处理（含TARGET_MSG_PROCESS_STATUS）-被动处理
	
	public static final String SMS_PROCESS_STATUS_READY="SP_0010";//短信处理状态：未处理//只针对短信接收并处理计划任务，不涉及系统发送短信
	public static final String SMS_PROCESS_STATUS_DONE="SP_0020";//短信处理状态：未处理
	public static final String SMS_PROCESS_STATUS_FAILED="SP_0030";//短信处理状态：处理失败
	
//	DRIVER_NAME	{司机姓名}
	public static final String MSG_SYS_PARAM_DRIVER_NAME="DRIVER_NAME";//替换符 为	{司机姓名} ${司机姓名}
//	DRIVER_PHONE	{司机电话}
	public static final String MSG_SYS_PARAM_DRIVER_PHONE="DRIVER_PHONE";//替换符 为	{司机电话}
//	TRIP_TASK_NO	{订单号}
	public static final String MSG_SYS_PARAM_TRIP_TASK_NO="TRIP_TASK_NO";//替换符 为	{订单号}
//	BUS_TRIP_NO	{行程单号}
	public static final String MSG_SYS_PARAM_BUS_TRIP_NO="BUS_TRIP_NO";//替换符 为	{行程单号}
//	TRIP_BEGIN_DATE	{日期}
	public static final String MSG_SYS_PARAM_TRIP_BEGIN_DATE="TRIP_BEGIN_DATE";//替换符 为	{日期} //月/日
//	TRIP_ADDRESS	{行程目的}
	public static final String MSG_SYS_PARAM_TRIP_ADDRESS="TRIP_ADDRESS";//替换符 为	{行程目的}  //TRIP_DESC
//	GET_ON_TIME	{上车时间}
	public static final String MSG_SYS_PARAM_GET_ON_TIME="GET_ON_TIME";//替换符 为	{上车时间}   //8:00
//	GET_ON_ADDRESS	{上车地点}
	public static final String MSG_SYS_PARAM_GET_ON_ADDRESS="GET_ON_ADDRESS";//替换符 为	{上车地点}
//	GET_OFF_ADDRESS	{下车地点}
	public static final String MSG_SYS_PARAM_GET_OFF_ADDRESS="GET_OFF_ADDRESS";//替换符 为	{下车地点}
//	PASSENGER_NAME	{乘车人姓名}
	public static final String MSG_SYS_PARAM_PASSENGER_NAME="PASSENGER_NAME";//替换符 为	{乘车人姓名}
//	PASSENGER_SEX	{乘车人性别}
	public static final String MSG_SYS_PARAM_PASSENGER_SEX="PASSENGER_SEX";//替换符 为	{乘车人性别}
//	PASSENGER_PHONE	{乘车人电话}
	public static final String MSG_SYS_PARAM_PASSENGER_PHONE="PASSENGER_PHONE";//替换符 为	{乘车人电话}
//	TRIP_ACCOUNT_MSG	{结算信息}
	public static final String MSG_SYS_PARAM_TRIP_ACCOUNT_MSG="TRIP_ACCOUNT_MSG";//替换符 为	{结算信息}
//	TRIP_REMARK	{备注}
	public static final String MSG_SYS_PARAM_TRIP_REMARK="TRIP_REMARK";//替换符 为 	{备注}
//	SAME_MSG_COUNT	{当日指定对象信息发送次数}
	public static final String MSG_SYS_PARAM_SAME_MSG_COUNT="SAME_MSG_COUNT";//替换符 为	{当日指定对象信息发送次数}
//	AGENT_SHORT_NAME	{车队}
	public static final String MSG_SYS_PARAM_AGENT_SHORT_NAME="AGENT_SHORT_NAME";//替换符 为	{车队}
//	TRIP_CUST_NAME	{订车人姓名}
	public static final String MSG_SYS_PARAM_TRIP_CUST_NAME="TRIP_CUST_NAME";//替换符 为	{订车人姓名}
//	TRIP_CUST_SEX	{订车人性别}
	public static final String MSG_SYS_PARAM_TRIP_CUST_SEX="TRIP_CUST_SEX";//替换符 为	{订车人性别}
//	BUS_LICENSE	{车牌号}
	public static final String MSG_SYS_PARAM_BUS_LICENSE="BUS_LICENSE";//替换符 为	{车牌号}
	
	public static final String MSG_SYS_PARAM_DRIVER_SURNAME="DRIVER_SURNAME";//替换符 为	{司机姓}   	${司机姓}
	public static final String MSG_SYS_PARAM_TRIP_CUST_SURNAME="TRIP_CUST_SURNAME";//替换符 为	{订车人姓}
	public static final String MSG_SYS_PARAM_PASSENGER_SURNANE="PASSENGER_SURNANE";//替换符 为	{乘车人姓}
	
	public static final String MSG_SYS_PARAM_PRE_DRIVER_SURNAME="PRE_DRIVER_SURNAME";//替换符 为	{前司机姓}
	public static final String MSG_SYS_PARAM_PRE_DRIVER_NAME="PRE_DRIVER_NAME";//替换符 为	{前司机姓名}
	
	public static final String MSG_SYS_PARAM_TRIP_PASS_POINT="TRIP_PASS_POINT";//替换符 为{停靠点} 、、15字
	public static final String MSG_SYS_PARAM_AGENT_TRADE_MANAGER_SURNAME="AGENT_TRADE_MANAGER_SURNAME";//替换符 为{协作车队负责人姓}
	public static final String MSG_SYS_PARAM_BUS_TYPE="BUS_TYPE";//替换符 为{车型}
	
	/* 4-18 ADD NEW*/
	public static final String MSG_SYS_PARAM_CASH_PRICE="CASH_PRICE";//替换符 为{现结车价金额}
	public static final String MSG_SYS_PARAM_CASH_DRIVER_SUBSIDY="CASH_DRIVER_SUBSIDY";//替换符 为{现结司贴金额}
	public static final String MSG_SYS_PARAM_CASH_ADD_AMOUNT="CASH_ADD_AMOUNT";//替换符 为{现结补差金额}
	
	public static final String MSG_SYS_PARAM_PRICE_INCLUDE_COST="PRICE_INCLUDE_COST";//替换符 为{车价包含费用} //借调的任务单，则为借调车价包含
	public static final String MSG_SYS_PARAM_CUST_CASH_COST="CUST_CASH_COST";//替换符 为{客户现结包含费用}
	
	public static final String MSG_SYS_PARAM_TRADE_AGENT_NAME="TRADE_AGENT_NAME";//替换符 为{借调车队名称}
	public static final String MSG_SYS_PARAM_TRIP_DESC_SN="TRIP_DESC_SN";//替换符 为{行程车号}
	public static final String MSG_SYS_PARAM_TRIP_LINE_SN="TRIP_LINE_SN";//替换符 为{行程班次号}
	
	public static final String MSG_SYS_PARAM_GET_ON_ADDRESS_DESC="GET_ON_ADDRESS_DESC";//替换符 为{上车地点位置信息} 、、上海市黄浦区人民大道120号
	public static final String MSG_SYS_PARAM_CUST_SHORT_NAME="CUST_SHORT_NAME";//替换符 为{客户简称}
	public static final String MSG_SYS_PARAM_TRIP_DESC="TRIP_DESC";//替换符 为{行程概要}
	public static final String MSG_SYS_PARAM_GET_OFF_TIME="GET_OFF_TIME";//替换符 为{下车时间}2015-01-01 18:00
	
	public static final String MSG_SYS_PARAM_TASK_LIST_NAME="TASK_LIST_NAME";//替换符 为	{司机刷新列表名称}（新任务/运行中）
	public static final String MSG_SYS_PARAM_CANCLE_DATE_LIST="CANCLE_DATE_LIST";//替换符 为{取消派车日期列表}（03-23,03-24，03-25...）
	
	/* 4-18 ADD END */
	
	public static final String DATA_DIC_SEX_MALE ="SE_0010";  //男
	public static final String DATA_DIC_SEX_FMALE ="SE_0020"; //女
	
	public static int AGENT_MAINPAGE_MSG_MAX_COUNT=10;//首页消息，通知，最大显示条数。
	
	public static final String ASSIGN_FLOW_OP_TYPE_ASSIGN ="AF_0010"; //调度派车（司机）操作类型:正常派
	public static final String ASSIGN_FLOW_OP_TYPE_CANNEL_ASSIGN ="AF_0020"; //调度派车（司机）操作类型：取消派
	public static final String ASSIGN_FLOW_OP_TYPE_CHANGE_ASSIGN ="AF_0030"; //调度派车（司机）操作类型：更换派
	
	public static boolean OSS_ISREADY;
	public static String OSS_ACCESS_KEY_ID;
	public static String OSS_ACCESS_KEY_SECRET;
	public static String OSS_BUCKET;
	public static String OSS_ENDPOINT_OUT;
	public static String OSS_ENDPOINT_INNER;
	public static String OSS_IN_OUT_TAG;
	
	public static boolean IS_OCS_SUPPORT=false;//是否支持外部缓存。
	
	
	public static boolean OTS_ISREADY;
	public static String OTS_ACCESS_KEY_ID;
	public static String OTS_ACCESS_KEY_SECRET;
	public static String OTS_ENDPOINT_OUT;
	public static String OTS_ENDPOINT_INNER;
	public static String OTS_IN_OUT_TAG;	
	public static String OTS_INSTANCE_NAME;	
//	public static String OTS_TABLE_LIST;
	
	
	public static final String SYS_NAME="YOUBUS";//用于生成cachekey的前缀
	public static final String APP_NAME="AGENT_CENTER";//
	
	//司机移动端任务消息属性
	public static final String TASK_MSG_PROPERTY_ORDER_TRIP_TYPE="ORDER_TRIP_TYPE";//任务属性字典-订单类型
	public static final String TASK_MSG_PROPERTY_ORDER_STATUS="ORDER_STATUS";//任务属性字典-订单状态
	public static final String TASK_MSG_PROPERTY_ORDER_STATUS_UNBEGIN="ORDER_STATUS_0001";//任务属性字典-未开始
	public static final String TASK_MSG_PROPERTY_ORDER_STATUS_RUN="ORDER_STATUS_0002";//任务属性字典-执行中
	public static final String TASK_MSG_PROPERTY_ORDER_STATUS_SUB_FINISH="ORDER_STATUS_0003";//任务属性字典-部分完成
	public static final String TASK_MSG_PROPERTY_ORDER_STATUS_ALL_FINISH="ORDER_STATUS_0004";//任务属性字典-已完成
	
	public static final String TASK_MSG_PROPERTY_ORDER_CHANGE="ORDER_CHANGE";//任务属性字典-变更状态
	public static final String TASK_MSG_PROPERTY_ORDER_CHANGE_NORMAL="ORDER_CHANGE_0000";//任务属性字典-正常
	public static final String TASK_MSG_PROPERTY_ORDER_CHANGE_CHANGE="ORDER_CHANGE_0001";//任务属性字典-变更
	public static final String TASK_MSG_PROPERTY_ORDER_CHANGE_CANCLE="ORDER_CHANGE_0002";//任务属性字典-取消
	
	
	public static final String TASK_MSG_PROPERTY_ORDER_CUST_NAME="ORDER_CUST_NAME";//任务属性字典-客户简称
	public static final String TASK_MSG_PROPERTY_ORDER_TRIP_SUMMRY="ORDER_TRIP_SUMMRY";//任务属性字典-行程简述
	public static final String TASK_MSG_PROPERTY_ORDER_P_BEGIN_TIME="ORDER_P_BEGIN_TIME";//任务属性字典-行程计划开始时间
	public static final String TASK_MSG_PROPERTY_ORDER_P_END_TIME="ORDER_P_END_TIME";//任务属性字典-行程计划结束时间
	public static final String TASK_MSG_PROPERTY_ORDER_R_BEGIN_TIME="ORDER_R_BEGIN_TIME";//任务属性字典-行程实际开始时间
	public static final String TASK_MSG_PROPERTY_ORDER_R_END_TIME="ORDER_R_END_TIME";//任务属性字典-行程实际结束时间
	public static final String TASK_MSG_PROPERTY_ORDER_TRIP_DISTANCE="ORDER_TRIP_DISTANCE";//任务属性字典-行程距离公里
	public static final String TASK_MSG_PROPERTY_ORDER_TRIP_DURATION="ORDER_TRIP_DURATION";//任务属性字典-行程时长分钟
	public static final String TASK_MSG_PROPERTY_ORDER_PAUSE="ORDER_PAUSE";//任务属性字典-行程暂停状态
	public static final String TASK_MSG_PROPERTY_ORDER_PAUSE_UN_PAUSE="ORDER_PAUSE_0000";//任务属性字典-行程暂停状态
	public static final String TASK_MSG_PROPERTY_ORDER_PAUSE_PAUSEING="ORDER_PAUSE_0001";//任务属性字典-行程暂停状态
	
	
	public static final String TASK_MSG_PROPERTY_ORDER_PAUSE_TIME="ORDER_PAUSE_TIME";//任务属性字典-行程最近一次暂停时间
	
	public static final String TASK_MSG_PROPERTY_TASK_MSG_TYPE="TASK_MSG_TYPE";//任务属性字典-消息类型
	public static final String TASK_MSG_PROPERTY_TASK_MSG_TYPE_NEW="TASK_MSG_TYPE_0001";//任务属性字典-消息类型-新任务
	public static final String TASK_MSG_PROPERTY_TASK_MSG_TYPE_RUNNING="TASK_MSG_TYPE_0002";//任务属性字典-消息类型-执行中
	public static final String TASK_MSG_PROPERTY_TASK_MSG_TYPE_FINISH="TASK_MSG_TYPE_0003";//任务属性字典-消息类型-已完成
	
	public static final String TASK_MSG_PROPERTY_TASK_MSG_READ="TASK_MSG_READ";//任务属性字典-读取状态
	public static final String TASK_MSG_PROPERTY_TASK_MSG_READ_UNREAD="TASK_MSG_READ_0000";//任务属性字典-读取状态-未读
	public static final String TASK_MSG_PROPERTY_TASK_MSG_READ_READ_DONE="TASK_MSG_READ_0001";//任务属性字典-读取状态-已读
	
	public static final String TASK_MSG_PROPERTY_TASK_MSG_CFM="TASK_MSG_CFM";//任务属性字典-消息确认状态
	public static final String TASK_MSG_PROPERTY_TASK_MSG_CFM_UNCFM="TASK_MSG_CFM_0000";//任务属性字典-消息未确认状态
	public static final String TASK_MSG_PROPERTY_TASK_MSG_CFM_CFM_DONE="TASK_MSG_CFM_0001";//任务属性字典-消息已确认状态
	
	public static final String TRIP_BUS_WORKING_STATUS_UN_BEGIN="BW_0000";//daily当前运行状态 未开始;
	public static final String TRIP_BUS_WORKING_STATUS_BEGINING="BW_0005";//daily当前运行状态接客户中;
	public static final String TRIP_BUS_WORKING_STATUS_RUNNING="BW_0010";//daily当前运行状态 运行中;
	public static final String TRIP_BUS_WORKING_STATUS_PAUSE="BW_0020";//daily当前运行状态 已暂停;
	public static final String TRIP_BUS_WORKING_STATUS_FINISH="BW_0030";//daily当前运行状态 已完成;
	
	public static final String TRIP_VIA_MOBILE_RECORD_TYPE_AT="RT_0010";//行程单途经点打卡方式：自动;
	public static final String TRIP_VIA_MOBILE_RECORD_TYPE_MT="RT_0020";//行程单途经点打卡方式：手工;
	
	public static final String PUSH_MSG_TYPE_MESSAGE="PMT_0010";//消息推送平台消息类型：穿透消息，屏幕不显示；TYPE_MESSAGE:透传消息。必填 注意：TYPE_MESSAGE类型消息 默认在终端是不展示的
	public static final String PUSH_MSG_TYPE_NOTIFICATION="PMT_0020";//消息推送平台消息类型:普通消息,屏幕显示，TYPE_NOTIFICATION:通知；
	
	public static final String DRIVER_TASK_MSG_TYPE_NEW_TASK="DTM_0010";//新任务-新派
	public static final String DRIVER_TASK_MSG_TYPE_MOD_TASK="DTM_0020";//任务变更-同一行程单下，司机daily数变化，并>=1，(针对单天取消，单天新派等)
	public static final String DRIVER_TASK_MSG_TYPE_MOD_TRIP="DTM_0030";//行程变更-行程变更（针对客户行程变化）
	public static final String DRIVER_TASK_MSG_TYPE_CAL_TASK="DTM_0040";//任务取消-司机完全取消派，同一行程单下，司机daily数为0（针对全部取消，或整单换司机）
	public static final String DRIVER_TASK_MSG_TYPE_CAL_TRIP="DTM_0050";//行程取消-行程取消

	public static final String TRIP_FINANCIAL_CHECK_STATUS_UN_DRIVER_SUBMIT="AM_0000";//司机未提交  --待司机执行完成
	public static final String TRIP_FINANCIAL_CHECK_STATUS_DRIVER_SUBMIT="AM_0010";//司机已提交 --待调度审核
	public static final String TRIP_FINANCIAL_CHECK_STATUS_DISPATCH_SAVE="AM_0019";//调度已初审
	public static final String TRIP_FINANCIAL_CHECK_STATUS_DISPATCH_SUBMIT="AM_0020";//调度已审核--待出纳审核
	public static final String TRIP_FINANCIAL_CHECK_STATUS_CASHIER_SAVE="AM_0029";//出纳已初审核
	public static final String TRIP_FINANCIAL_CHECK_STATUS_CASHIER_SUBMIT="AM_0030";//出纳已审核
	public static final String TRIP_FINANCIAL_CHECK_STATUS_FINANCE_SUBMIT="AM_0040";//财务已审核--已完成
	
//	司机端途经点运行状态
	public static final String DRIVE_VIA_RUN_STATUS_NORMAL="DVS_0010";//正常
	public static final String DRIVE_VIA_RUN_STATUS_NORMAL_DESC="正常";//正常
	public static final String DRIVE_VIA_RUN_STATUS_SKIP="DVS_0020";//跳过
	public static final String DRIVE_VIA_RUN_STATUS_SKIP_DESC="跳过";//跳过
	public static final String DRIVE_VIA_RUN_STATUS_DONE="DVS_0030";//已完成
	public static final String DRIVE_VIA_RUN_STATUS_DONE_DESC="已完成";//已完成
	public static final String DRIVE_VIA_RUN_STATUS_LOCK="DVS_0050";//锁定//针对班车订单
	public static final String DRIVE_VIA_RUN_STATUS_LOCK_DESC="锁定";//锁定
	
//  司机任务状态：DRIVER_TASK_STATUS
	public static final String DRIVER_TASK_STATUS_UN_CONFIRM="DTS_0010";//待确认--
	public static final String DRIVER_TASK_STATUS_UN_BEGIN="DTS_0020";//未开始  --废弃
	public static final String DRIVER_TASK_STATUS_BEGINING="DTS_0030";//接客户中  --废弃
	public static final String DRIVER_TASK_STATUS_RUNNING="DTS_0040";//执行中  --废弃
	public static final String DRIVER_TASK_STATUS_PAUSE="DTS_0050";//暂停  --废弃
	public static final String DRIVER_TASK_STATUS_CONTINUE="DTS_0060";//继续  --废弃
	public static final String DRIVER_TASK_STATUS_SUB_FINISH="DTS_0070";//部分完成--派工单未全部到达已结束状态  --废弃
	public static final String DRIVER_TASK_STATUS_TASK_FINISH="DTS_0080";//已完成//待司机结算--派工单全部到达已结束状态
	public static final String DRIVER_TASK_STATUS_FINACE_SUBMIT="DTS_0090";//已结算//待调度审核--
	public static final String DRIVER_TASK_STATUS_DIS_CHECK_END="DTS_0100";//已审核//待出纳审核--已完成
	public static final String DRIVER_TASK_STATUS_FINACE_END="DTS_0110";//已报销//出纳已审核--已完成
	public static final String DRIVER_TASK_STATUS_ALL_END="DTS_0120";//已结束--已完成
	
	public static final String DRIVER_TASK_STATUS_CONFIRM_END="DTS_0011";//已确认//任务单状态--
	public static final String DRIVER_TASK_STATUS_CANCEL="DTS_0190";//任务已取消--已取消--
	
	
	
	public static final String AGENT_REC_ACC_TYPE_CASH="RAT_0000";//现金//收付款渠道，账户类型
	public static final String AGENT_REC_ACC_TYPE_BANK="RAT_0010";//银行//收付款渠道，账户类型
	public static final String AGENT_ACC_REC_PAY_TAG_ALL="ARP_0030";//可收可付//车队账户可收付款类型
	
//	TRIP_STATUS
	public static final String BUS_TRIP_MAIN_TRIP_STATUS_UNASSIGN="TS_0010";//未配车
	public static final String BUS_TRIP_MAIN_TRIP_STATUS_ASSIGN_END="TS_0011";//已配车//含部分派车
	public static final String BUS_TRIP_MAIN_TRIP_STATUS_RUNNING="TS_0020";//出行中//含部分开始，部分完成
	public static final String BUS_TRIP_MAIN_TRIP_STATUS_TRIP_FINISH="TS_0030";//行程结束
	
//	TM_AGENT_TRIP_TASK TRIP_STATUS
	public static final String TRIP_TASK_TRIP_STATUS_TEMP="TOS_0000";//草稿
	public static final String TRIP_TASK_TRIP_STATUS_UNASSIGN="TOS_0020";//等待派车
	public static final String TRIP_TASK_TRIP_STATUS_ASSIGNING="TOS_0030";//派车中
	public static final String TRIP_TASK_TRIP_STATUS_ASSIGNED="TOS_0040";//已派车
	public static final String TRIP_TASK_TRIP_STATUS_ASSIGN_FINISH="TOS_0050";//派车完成
	public static final String TRIP_TASK_TRIP_STATUS_RUNNING="TOS_0060";//执行中
	public static final String TRIP_TASK_TRIP_STATUS_UNDISPATCH_CHECK="TOS_0070";//待调度审核
	public static final String TRIP_TASK_TRIP_STATUS_UNCASHER_CHECK="TOS_0080";//待出纳审核
	public static final String TRIP_TASK_TRIP_STATUS_UNORDER_CHECK="TOS_0090";//待财务审核
	public static final String TRIP_TASK_TRIP_STATUS_ORDER_CHECK_END="TOS_0100";//已结算
	public static final String TRIP_TASK_TRIP_STATUS_ORDER_CANCEL="TOS_0110";//已取消
	
//	TM_SINGLE_BUS_TRIP SBT_TRIP_STATUS,TM_BUS_TRIP_MAIN TRIP_STATUS
	public static final String TRIP_TASK_SBT_TRIP_STATUS_TEMP="SBT_0000";//草稿
	public static final String TRIP_TASK_SBT_TRIP_STATUS_UNASSIGN="SBT_0010";//待派车
	public static final String TRIP_TASK_SBT_TRIP_STATUS_ASSIGNING="SBT_0020";//部分派车
	public static final String TRIP_TASK_SBT_TRIP_STATUS_ASSIGNED="SBT_0030";//已派车
	public static final String TRIP_TASK_SBT_TRIP_STATUS_UNDRIVER_CFM="SBT_0040";//待司机确认
	public static final String TRIP_TASK_SBT_TRIP_STATUS_ENDRIVER_CFM="SBT_0050";//已确认未开始
	public static final String TRIP_TASK_SBT_TRIP_STATUS_RUNNING="SBT_0060";//执行中
	public static final String TRIP_TASK_SBT_TRIP_STATUS_SUB_FINISH="SBT_0070";//部分已完成
	public static final String TRIP_TASK_SBT_TRIP_STATUS_UNDRIVER_CHECK="SBT_0080";//待司机结算
	public static final String TRIP_TASK_SBT_TRIP_STATUS_UNDISPATCH_CHECK="SBT_0090";//待调度审核
	public static final String TRIP_TASK_SBT_TRIP_STATUS_UNCASHER_CHECK="SBT_0100";//待出纳审核
	public static final String TRIP_TASK_SBT_TRIP_STATUS_CASHER_CHECK_END="SBT_0110";//出纳已审核
	public static final String TRIP_TASK_SBT_TRIP_STATUS_SINGLE_CANCEL="SBT_0120";//已取消
	
//	TM_BUS_TRIP_DAILY  BTD_TRIP_STATUS 
	public static final String TRIP_TASK_BTD_TRIP_STATUS_TEMP="BTD_0000";//未确认--草稿
	public static final String TRIP_TASK_BTD_TRIP_STATUS_UNASSIGN="BTD_0010";//待派单 //待派
	public static final String TRIP_TASK_BTD_TRIP_STATUS_ASSIGNED="BTD_0020";//已（预）派车 //预派
	public static final String TRIP_TASK_BTD_TRIP_STATUS_UNDRIVER_CFM="BTD_0030";//待司机确认 //待确认
	public static final String TRIP_TASK_BTD_TRIP_STATUS_ENDRIVER_CFM="BTD_0040";//已确认未开始 //未开始
	public static final String TRIP_TASK_BTD_TRIP_STATUS_MEET_CUST="BTD_0050";//接客户中
	public static final String TRIP_TASK_BTD_TRIP_STATUS_RUNNING="BTD_0060";//执行中
	public static final String TRIP_TASK_BTD_TRIP_STATUS_PAUSE="BTD_0070";//暂停
	public static final String TRIP_TASK_BTD_TRIP_STATUS_FINISH="BTD_0080";//已结束（原已完成）
	public static final String TRIP_TASK_BTD_TRIP_STATUS_DRIVER_CHECK_END="BTD_0090";//--已完成（原结算已提交）
	
	public static final String TRIP_TASK_BTD_TRIP_STATUS_UNDRIVER_CHECK="BTD_0081";//待司机结算--
	
	
	
//	COR_0010订单创建
//	COR_0011订单调价
//	COR_0050行程单创建
//	COR_0051行程单调价
//	TM_AGENT_ORDER_CHANGE CHANGE_REASON
	public static final String ORDER_CHANGE_REASON_TYPE_ORDER_CREATE="COR_0010";//订单创建
	public static final String ORDER_CHANGE_REASON_TYPE_ORDER_CHANGE_PRICE="COR_0011";//订单调价
	public static final String ORDER_CHANGE_REASON_TYPE_ORDER_CHANGE_MAIN="COR_0012";//订单主信息变更
	public static final String ORDER_CHANGE_REASON_TYPE_ORDER_CHANGE_TRIP="COR_0013";//订单行程信息变更
	public static final String ORDER_CHANGE_REASON_TYPE_ORDER_CHANGE_CANCEL="COR_0014";//订单取消变更
	public static final String ORDER_CHANGE_REASON_TYPE_ORDER_CHANGE_MEMBER="COR_0015";//订单业务员变更
	public static final String ORDER_CHANGE_REASON_TYPE_ORDER_CHANGE_OTHER="COR_0019";//订单其他信息变更
	public static final String ORDER_CHANGE_REASON_TYPE_ORDER_AMOUNT_SUBMIT="COR_0016";//订单价格入账
	public static final String ORDER_CHANGE_REASON_TYPE_ORDER_AMOUNT_SUBMIT_CANCLE="COR_0017";//订单价格取消入账，负入账
	
	
	public static final String ORDER_CHANGE_REASON_TYPE_SINGLE_CREATE="COR_0050";//行程单创建
	public static final String ORDER_CHANGE_REASON_TYPE_SINGLE_CHANGE_PRICE="COR_0051";//行程单调价
	public static final String ORDER_CHANGE_REASON_TYPE_SINGLE_CHANGE_MAIN="COR_0052";//行程单主信息变更
	public static final String ORDER_CHANGE_REASON_TYPE_SINGLE_CHANGE_TRIP="COR_0053";//行程单行程信息变更
	public static final String ORDER_CHANGE_REASON_TYPE_SINGLE_CHANGE_ASSIGN="COR_0054";//行程单确认待派变更
	public static final String ORDER_CHANGE_REASON_TYPE_SINGLE_CHANGE_CANCEL="COR_0055";//行程单取消行程变更
	
	public static final String ORDER_CHANGE_REASON_TYPE_SINGLE_CHANGE_OTHER="COR_0059";//行程单其他信息变更
	
	
	public static final String TRADE_CHANGE_REASON_TYPE_TASK_CREATE="COR_0060";//借调创建
	public static final String TRADE_CHANGE_REASON_TYPE_PRICE_CHANGE="COR_0061";//借调车价调整
	
	
	
//	TM_AGENT_ORDER_CHANGE CHANGE_ORDER_STATUS 单据价格变更确认状态	
	public static final String ORDER_CHANGE_CHECK_STATUS_UNCHECK="COS_0010";//未确认
	public static final String ORDER_CHANGE_CHECK_STATUS_CHECKED="COS_0020";//已确认
//	 TM_AGENT_ORDER_CHANGE CHANGE_ORDER_TYPE 单据价格变更流水业务单据类型
	public static final String ORDER_CHANGE_ORDER_TYPE_ORDER="COT_0010";//订单号
	public static final String ORDER_CHANGE_ORDER_TYPE_SINGLE="COT_0020";//行程单号
	public static final String ORDER_CHANGE_ORDER_TYPE_TASK="COT_0030";//司机任务单ID
//	TM_AGENT_ORDER_AMOUNT  FINAL_PAY_WAY
	public static final String TRIP_TASK_FINAL_PAY_WAY_CASH="FP_0010";//现结//订单余款，补差金额等付款方式
	public static final String TRIP_TASK_FINAL_PAY_WAY_BACK="FP_0020";//回结

//	TT_BUS_TRIP_COST_LIST TT_AGENT_ORDER_COST_LIST  CUST_PAY_WAY
	public static final String TRIP_TASK_CUST_PAY_WAY_CASH="CP_0010";//现结//客户支付费用，现结回结等方式
	public static final String TRIP_TASK_CUST_PAY_WAY_BACK="CP_0020";//回结
	
	//TT_AGENT_MEMBER_FILE_TASK_LIST TASK_STATUS
	public static final String MEMBER_FILE_TASK_STATUS_UNBEGIN="MFT_0010";//未开始
	public static final String MEMBER_FILE_TASK_STATUS_RUNNING="MFT_0020";//生成中
	public static final String MEMBER_FILE_TASK_STATUS_SUCCESS="MFT_0030";//生成成功
	public static final String MEMBER_FILE_TASK_STATUS_FAILD="MFT_0040";//生成失败
	//TT_AGENT_MEMBER_FILE_TASK_LIST TASK_BUSI_TYPE
	public static final String MEMBER_FILE_TASK_BUSTYPE_FINACE="MFB_0010";//财务报表导出
	public static final String MEMBER_FILE_TASK_BUSTYPE_ASSIGN="MFB_0020";//车辆派车计划
	
	public static final double SMS_AMOUNT_RATE=0.1d;//短息费率  SMS_AMOUNT=SMS_COUNT x 费率（SMS_AMOUNT_RATE）//应从配置表取
	public static final String SMS_PRIFIX_WORD="【有巴】";//每条短信的前缀
	public static final int SMS_UNIT_WORD_LENGTH=67;//每条短信的字数长度	
	
	
//	TM_YOUBUS_PAY_ORDER
	//public static final String YB_PAY_CHANNEL_TYPE_WX="YPCT_0010";//支付网关支付渠道-微信
	public static final String YB_PAY_CHANNEL_TYPE_ALI="YPCT_0020";//支付网关支付渠道-支付宝
	
	public static final String YB_PAY_ORDER_TYPE_TICKET="CT";//支付网关业务类型 - 客户购票
	public static final String YB_PAY_ORDER_TYPE_CPAY="CP";//支付网关业务类型 - 客户支付
	
	
	public static final String YB_PAY_ORDER_STATUS_READY="YPS_0010";//支付网关订单(支付)状态 -草稿(已下单)
	public static final String YB_PAY_ORDER_STATUS_SCAN_CODE="MOS_0020";//支付网关订单(支付)状态 -已扫码
	public static final String YB_PAY_ORDER_STATUS_PAYED="MOS_0030";//支付网关订单(支付)状态 -已支付
	public static final String YB_PAY_ORDER_STATUS_PAY_SUCCESS="MOS_0040";//支付网关订单(支付)状态 -支付成功
	public static final String YB_PAY_ORDER_STATUS_PAY_FAILD="MOS_0050";//支付网关订单(支付)状态 -支付失败
	
	public static final String YB_PAY_CALL_STATUS_UNCALL="YPCS_0010";//支付网关订单支付结果通知状态 未通知
	public static final String YB_PAY_CALL_STATUS_CALL_SUCCESS="YPCS_0020";//支付网关订单支付结果通知状态 已通知
	public static final String YB_PAY_CALL_STATUS_CALL_FAILD="YPCS_0030";//支付网关订单支付结果通知状态 通知失败
	
//	TT_OUT_PAYMENT_MSG_LOG  --REQ_RSP_TYPE
	public static final String PAY_REQ_RSP_TYPE_WX_REQ_PAY="RW_RP_0010";//微信发起支付验证请求
	public static final String PAY_REQ_RSP_TYPE_WX_RSP_COMMON="RW_PC_0010";//微信返回统一下单应答
	public static final String PAY_REQ_RSP_TYPE_WX_REQ_PAY_RESULT="RW_RR_0010";//微信发起支付结果请求
	
	// 支付网关支付渠道
	public final static String YB_PAY_CHANNEL_TYPE_WX = "YPCT_0010";//微信
	public final static String YB_PAY_CHANNEL_TYPE_ZFB = "YPCT_0020";//支付宝
	
	// 支付网关业务类型
	public final static String YB_PAY_ORDER_TYPE_CT = "CT";//客户购票
	public final static String YB_PAY_ORDER_TYPE_CP = "CP";//客户支付
	
	// 支付网关订单(支付)状态
	public final static String YB_PAY_ORDER_STATUS_ORDER = "YPS_0010";//草稿(已下单)
	public final static String YB_PAY_ORDER_STATUS_CODE = "YPS_0020";//已扫码
	public final static String YB_PAY_ORDER_STATUS_PAY = "YPS_0030";//已支付
	public final static String YB_PAY_ORDER_STATUS_SUCCESS = "YPS_0040";//支付成功
	public final static String YB_PAY_ORDER_STATUS_FAIL = "YPS_0050";//支付失败
	public final static String YB_PAY_ORDER_STATUS_CANCLE = "YPS_0060";//已退款
	
	// 支付网关订单支付结果通知状态
	public final static String YB_PAY_CALL_STATUS_NOTIFY_NO = "YPCS_0010";//未通知
	public final static String YB_PAY_CALL_STATUS_NOTIFY_YES = "YPCS_0020";//已通知
	public final static String YB_PAY_CALL_STATUS_NOTIFY_FAIL = "YPCS_0030";//通知失败
	
	// 微信对账单校验类型
	public final static String OUT_PAY_TYPE_JSAPI_CODE = "JSAPI";//公众号支付
	public final static String OUT_PAY_TYPE_JSAPI_NAME = "公众号支付";//公众号支付
	public final static String OUT_PAY_TYPE_MICROPAY_CODE = "MICROPAY";//刷卡支付
	public final static String OUT_PAY_TYPE_MICROPAY_NAME = "刷卡支付";//刷卡支付
	public final static String OUT_PAY_TYPE_NATIVE_CODE = "NATIVE";//扫码支付
	public final static String OUT_PAY_TYPE_NATIVE_NAME = "扫码支付";//扫码支付
	public final static String OUT_PAY_TYPE_APP_CODE = "APP";//APP支付
	public final static String OUT_PAY_TYPE_APP_NAME = "APP支付";//APP支付
	
}
