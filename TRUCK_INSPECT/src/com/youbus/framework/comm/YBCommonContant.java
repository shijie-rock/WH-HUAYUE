/**
 * ��Ŀ����:AGENT_CENTER
 * ��         ��:com.youbus.framework.comm
 * ��   ��  ��:YBCommonContant.java
 * �� ������:2015��4��13��-����4:16:23
 * Copyright @ 2015-YouBus.com.cn
 */
package com.youbus.framework.comm;


/**
 * ������:YBCommonContant
 * ������:Ӧ�ó�����
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2015��4��13�� ����4:16:23
 * �޸ı�ע:
 * @version 1.0.0
 */
public class YBCommonContant {

	/**
	 * ����һ���µ�ʵ�� YBCommonContant.
	 *
	 */
	public YBCommonContant() {
		// TODO Auto-generated constructor stub
	}
	
	public static String AGENT_SERVER_VERSION="V1.8.1-alpha";/*Ӧ�����ݿ��ж�̬��ȡ*/
	
	public static final int SYS_DEFAULT_MEMBER_ID=0;
	
	public static final String BUS_TRIP_NO_TYPE="DT";//���ӵ����г̵�-TM_BUS_TRIP_MAIN
	public static final String AGENT_TRIP_NO_TYPE="AT";//����ҵ���г̵�-TM_AGENT_TRIP_TASK
	
	public static final String SINGLE_TRIP_LINE_SN_TOUR="L";//�г̵���κţ�������ʹ��ţ�����
	public static final String SINGLE_TRIP_LINE_SN_SHUTTLE="J";//�г̵���κţ�������ʹ��ţ�����
	public static final String SINGLE_TRIP_LINE_SN_BUS="B";//�г̵���κţ�������ʹ��ţ� �೵
	
	public static final String SESSION_USER_ID = "session_userid"; //member_id ȫ��Ψһ
	public static final String SESSION_EMP_ID = "session_empid"; //agent_member id ÿ��Ӧ�ÿ� Ψһ
	public static final String SESSION_USER_NAME = "session_username"; //
	public static final String SESSION_AGENT_INFO = "session_agentinfo"; //���ӻ�����Ϣ
	public static final String SESSION_ID = "session_id";
	public static final String SESSION_COMP_ID = "session_compid";
	public static final String SESSION_USER_CODE = "session_usercode";
	public static final String SESSION_AGENT_ID = "session_agentid";
	public static final String SESSION_AUTH_SESSION_ID = "session_auth_sessionid";//��ȫ���ĵ�¼ʱ�Ựid
	public static final String SESSION_AGENT_NAME = "session_agent_name";
	public static final String SESSION_SYSCODE = "session_syscode";
	public static final String SESSION_LOGIN_USER = "loginuser";
	public static final String SESSION_CLENT_IP = "clentIP";
	public static final String SESSION_FAO_LIST = "session_FAOList";//Ȩ�޹��ܲ˵�
	public static final String SESSION_FUN_LIST = "session_FUNList";//Ȩ�޹��ܲ˵�
	public static final String SESSION_FAO_MAP = "session_FAOMAP";//Ȩ�޹��ܲ˵�
	public static final String SESSION_ACTION_SHORTCUT = "session_ACTION_SHORTCUT";//������ҳ��ݲ˵�
	public static final String SESSION_ACTION_SHORTCUT_ALL = "session_ACTION_SHORTCUT_ALL";//��ǰ�û����п��Բ����Ŀ�ݲ˵������ɱ�ѡ���)
	public static final String SESSION_ACTION_URL_LIST = "session_ACTION_URL_LIST";//����Action url list
	
	public static final String APP_TYPE_AUTH_CENTER="AUTH_CENTER";
	
	
	public static class TripAssignPanel{
		public static final double divisor  = 360.0; //panel��Ԫ������360����
		public static final int popBeginTime = 360; //tooltip������ʼʱ��(6:00 ��Ӧ360����)
		public static final String Trip_Panel_Title = "trip-panel-title"; //panel��һ�е�һ�б���
		public static final String Trip_Panel_Title_Default_Date = "00000000"; //0��Ĭ������
		public static final String Trip_Panel_License_Date_Separator = "@"; //ƴ�ӷָ���
		public static final String Trip_Panel_Title_Text = "����"; //panel��һ�е�һ�б���
		public static final String Trip_Panel_License_Key = "license_key_xxxxxxxxxx"; //����key
		
		public static final int conflict_percent = 250;  //����100����
		
	}
	
	
	public static final String WORK_DEF_BEGIN_TIME = "07:00:00"; //ϵͳĬ���г̣���ʼ�����ʱ�� //Ӧ�������ֵ��ȡ
	public static final String WORK_DEF_END_TIME = "18:00:00";  //

	public static final String TRAVEL_EXCEL_PATH = "D:/YoubusTravelExcel";//
	public static final String UPLOAD_TEMP_PATH = "D:/YOUBUSFILEDATA/upload/";//
	
	
	public static final String FILE_SPACE_THUMB_DIR="youbusthumb" ;//����ͼ��Ŀ¼ 
	                                                               //��ͨĿ¼��AGENT_0001/20150514/1/333a9f02xxx.pdf
	                                                               //����ͼ��Ŀ¼ AGENT_0001/20150514/1/youbusthumb/333a9f02xxx_thumb.png..
	public static final String FILE_SPACE_THUMB_SUFFIX="_thumb";
	public static final String FILE_SAPCE_THUMB_TYPE="jpg";

	public static final String REMOTE_SERVER_CHANNEL="remote";//Զ�̷������Ľ���Զ�̷���ͨ����
	public static final String MOBILE_REQ_CHANNEL="mobile";//Զ�̷�������ֻ���������ͨ����
	
	public static final String MOBILE_CHECK_CODE_BIND="MOBILE_CHECK_CODE_BIND";//�ֻ���֤��ҵ�����ͣ����ֻ���
	public static final String MOBILE_CHECK_CODE_MODIFY="MOBILE_CHECK_CODE_MODIFY";//�ֻ���֤��ҵ�����ͣ��޸��˺��ֻ���
	
	//��Ϣ����ҵ���
	public static final String BUSI_TYPE_ASSIGN_CONFIRM="BU_0010"; //�ɳ�ȷ��
	public static final String BUSI_TYPE_TRIP_MODIFY="BU_0020";//�г̵����
	public static final String BUSI_TYPE_DRIVER_MODIFY="BU_0030";//˾������
	public static final String BUSI_TYPE_LICENSE_MODIFY="BU_0040";//�������
	public static final String BUSI_TYPE_TRIP_CANCEL="BU_0050";//�г̵�ȡ��
	public static final String BUSI_TYPE_TRIP_CANCEL_SUB="BU_0051";//�г̵�����ȡ���ɳ�
	public static final String BUSI_TYPE_TRIP_CASH_CHANGE="BU_0060";//�ֽ���
	
	//��Ϣ����������
	public static final String MSG_RECEIVE_TYPE_AGENT_EMP="MR_0010"; //��ͨ����ƽ̨�û�--�ݲ�����
	public static final String MSG_RECEIVE_TYPE_AGENT_DISPATCHER="MR_0020"; //���ӵ���--����
	public static final String MSG_RECEIVE_TYPE_AGENT_DRIVER="MR_0030"; //����˾�����ɵ���ǰ˾����--˾��
	public static final String MSG_RECEIVE_TYPE_AGENT_DRIVER_PRE="MR_0031"; //�ɵ�ǰ��˾��
	public static final String MSG_RECEIVE_TYPE_AGENT_CUSTOMER="MR_0040"; //������ϵ��//�ͻ���ϵ�ˡ�������--�ͻ���ϵ��
	public static final String MSG_RECEIVE_TYPE_AGENT_CUST_CONTACT="MR_0050"; //�ͻ���ϵ��//δ��
	public static final String MSG_RECEIVE_TYPE_AGENT_CUST_PASSENGER="MR_0060"; //�����˿�//�г���ϵ��--�˳���ϵ��
	public static final String MSG_RECEIVE_TYPE_AGENT_CUST_MANAGER="MR_0070"; //�ͻ�������//�ͻ�����ҵ��Ա--ҵ��Ա
	public static final String MSG_RECEIVE_TYPE_AGENT_TRADE_MANAGER="MR_0080"; //��������Э�����Ӹ�����-���������ϵ��
	public static final String MSG_RECEIVE_TYPE_AGENT_TRADE_MANAGER_PRE="MR_0081"; //��������Э�����Ӹ�����-ǰ�ν��������ϵ��
	
	//��Ϣ������������
	public static final String MSG_MSG_SEND_CHANNEL_SITE="SC_0010"; //ϵͳ�ڣ�վ����
	public static final String MSG_MSG_SEND_CHANNEL_SMS="SC_0020"; //����
	public static final String MSG_MSG_SEND_CHANNEL_WEIXIN="SC_0030"; //΢��
	public static final String MSG_MSG_SEND_CHANNEL_MAIL="SC_0040"; //�ʼ�
	public static final String MSG_MSG_SEND_CHANNEL_APP="SC_0050"; //APP
	//��Ϣ����״̬
	public static final String MSG_SEND_STATUS_SEND_UNREADY="SS_0000"; //δ׼���÷�����Ϣ����Ϣ�ݴ�״̬
	public static final String MSG_SEND_STATUS_SEND_READY="SS_0010"; //׼��������Ϣ
	public static final String MSG_SEND_STATUS_SENDING="SS_0020"; //������
	public static final String MSG_SEND_STATUS_SEND_END="SS_0030"; //�ѷ�
	public static final String MSG_SEND_STATUS_SEND_REJECT="SS_0090"; //�ܷ������û���Ҫ���ն��ţ�˾�����ò��ն��ŵȣ�
	//��ϢĬ�Ϲ���ʱ�䳤��
	public static final  int MSG_EXPIRE_TIME_LENGTH=60; //����
	
	//��Ϣ����
	public static final  String MSG_MSG_TYPE_NOTICE="MY_0010"; //֪ͨ-���账��
	public static final  String MSG_MSG_TYPE_INFO="MY_0020"; //��Ϣ-�账��
	//��Ϣ��������
	public static final  String MSG_MSG_PROCESS_TYPE_RECEIVE="MP_0010"; //������Ϊֱ��Ŀ��
	public static final  String MSG_MSG_PROCESS_TYPE_CC="MP_0020"; //������Ϊ���Ŀ��
	//��Ϣ��ȡ����
	public static final  String MSG_READ_STATUS_READY="RS_0010"; //δ��
	public static final  String MSG_READ_STATUS_DONE="RS_0020"; //�Ѷ�
	//��Ϣ����״̬
	public static final  String MSG_PROCESS_STATUS_READY="PS_0010"; //δ������TARGET_MSG_PROCESS_STATUS��
	public static final  String MSG_PROCESS_STATUS_DOING="PS_0020"; //�����У��������ITEM��Ϣ������ͬ��Ϣ�������޷���������TARGET_MSG_PROCESS_STATUS��
	public static final  String MSG_PROCESS_STATUS_RECEIVE_DONE="PS_0030"; //��ǰ��Ϣ�����߱����Ѵ�����TARGET_MSG_PROCESS_STATUS��-��������
	public static final  String MSG_PROCESS_STATUS_CC_DONE="PS_0040"; //��ǰ��Ϣ�����������Ѵ�����TARGET_MSG_PROCESS_STATUS��-��������
	
	public static final String SMS_PROCESS_STATUS_READY="SP_0010";//���Ŵ���״̬��δ����//ֻ��Զ��Ž��ղ�����ƻ����񣬲��漰ϵͳ���Ͷ���
	public static final String SMS_PROCESS_STATUS_DONE="SP_0020";//���Ŵ���״̬��δ����
	public static final String SMS_PROCESS_STATUS_FAILED="SP_0030";//���Ŵ���״̬������ʧ��
	
//	DRIVER_NAME	{˾������}
	public static final String MSG_SYS_PARAM_DRIVER_NAME="DRIVER_NAME";//�滻�� Ϊ	{˾������} ${˾������}
//	DRIVER_PHONE	{˾���绰}
	public static final String MSG_SYS_PARAM_DRIVER_PHONE="DRIVER_PHONE";//�滻�� Ϊ	{˾���绰}
//	TRIP_TASK_NO	{������}
	public static final String MSG_SYS_PARAM_TRIP_TASK_NO="TRIP_TASK_NO";//�滻�� Ϊ	{������}
//	BUS_TRIP_NO	{�г̵���}
	public static final String MSG_SYS_PARAM_BUS_TRIP_NO="BUS_TRIP_NO";//�滻�� Ϊ	{�г̵���}
//	TRIP_BEGIN_DATE	{����}
	public static final String MSG_SYS_PARAM_TRIP_BEGIN_DATE="TRIP_BEGIN_DATE";//�滻�� Ϊ	{����} //��/��
//	TRIP_ADDRESS	{�г�Ŀ��}
	public static final String MSG_SYS_PARAM_TRIP_ADDRESS="TRIP_ADDRESS";//�滻�� Ϊ	{�г�Ŀ��}  //TRIP_DESC
//	GET_ON_TIME	{�ϳ�ʱ��}
	public static final String MSG_SYS_PARAM_GET_ON_TIME="GET_ON_TIME";//�滻�� Ϊ	{�ϳ�ʱ��}   //8:00
//	GET_ON_ADDRESS	{�ϳ��ص�}
	public static final String MSG_SYS_PARAM_GET_ON_ADDRESS="GET_ON_ADDRESS";//�滻�� Ϊ	{�ϳ��ص�}
//	GET_OFF_ADDRESS	{�³��ص�}
	public static final String MSG_SYS_PARAM_GET_OFF_ADDRESS="GET_OFF_ADDRESS";//�滻�� Ϊ	{�³��ص�}
//	PASSENGER_NAME	{�˳�������}
	public static final String MSG_SYS_PARAM_PASSENGER_NAME="PASSENGER_NAME";//�滻�� Ϊ	{�˳�������}
//	PASSENGER_SEX	{�˳����Ա�}
	public static final String MSG_SYS_PARAM_PASSENGER_SEX="PASSENGER_SEX";//�滻�� Ϊ	{�˳����Ա�}
//	PASSENGER_PHONE	{�˳��˵绰}
	public static final String MSG_SYS_PARAM_PASSENGER_PHONE="PASSENGER_PHONE";//�滻�� Ϊ	{�˳��˵绰}
//	TRIP_ACCOUNT_MSG	{������Ϣ}
	public static final String MSG_SYS_PARAM_TRIP_ACCOUNT_MSG="TRIP_ACCOUNT_MSG";//�滻�� Ϊ	{������Ϣ}
//	TRIP_REMARK	{��ע}
	public static final String MSG_SYS_PARAM_TRIP_REMARK="TRIP_REMARK";//�滻�� Ϊ 	{��ע}
//	SAME_MSG_COUNT	{����ָ��������Ϣ���ʹ���}
	public static final String MSG_SYS_PARAM_SAME_MSG_COUNT="SAME_MSG_COUNT";//�滻�� Ϊ	{����ָ��������Ϣ���ʹ���}
//	AGENT_SHORT_NAME	{����}
	public static final String MSG_SYS_PARAM_AGENT_SHORT_NAME="AGENT_SHORT_NAME";//�滻�� Ϊ	{����}
//	TRIP_CUST_NAME	{����������}
	public static final String MSG_SYS_PARAM_TRIP_CUST_NAME="TRIP_CUST_NAME";//�滻�� Ϊ	{����������}
//	TRIP_CUST_SEX	{�������Ա�}
	public static final String MSG_SYS_PARAM_TRIP_CUST_SEX="TRIP_CUST_SEX";//�滻�� Ϊ	{�������Ա�}
//	BUS_LICENSE	{���ƺ�}
	public static final String MSG_SYS_PARAM_BUS_LICENSE="BUS_LICENSE";//�滻�� Ϊ	{���ƺ�}
	
	public static final String MSG_SYS_PARAM_DRIVER_SURNAME="DRIVER_SURNAME";//�滻�� Ϊ	{˾����}   	${˾����}
	public static final String MSG_SYS_PARAM_TRIP_CUST_SURNAME="TRIP_CUST_SURNAME";//�滻�� Ϊ	{��������}
	public static final String MSG_SYS_PARAM_PASSENGER_SURNANE="PASSENGER_SURNANE";//�滻�� Ϊ	{�˳�����}
	
	public static final String MSG_SYS_PARAM_PRE_DRIVER_SURNAME="PRE_DRIVER_SURNAME";//�滻�� Ϊ	{ǰ˾����}
	public static final String MSG_SYS_PARAM_PRE_DRIVER_NAME="PRE_DRIVER_NAME";//�滻�� Ϊ	{ǰ˾������}
	
	public static final String MSG_SYS_PARAM_TRIP_PASS_POINT="TRIP_PASS_POINT";//�滻�� Ϊ{ͣ����} ����15��
	public static final String MSG_SYS_PARAM_AGENT_TRADE_MANAGER_SURNAME="AGENT_TRADE_MANAGER_SURNAME";//�滻�� Ϊ{Э�����Ӹ�������}
	public static final String MSG_SYS_PARAM_BUS_TYPE="BUS_TYPE";//�滻�� Ϊ{����}
	
	/* 4-18 ADD NEW*/
	public static final String MSG_SYS_PARAM_CASH_PRICE="CASH_PRICE";//�滻�� Ϊ{�ֽᳵ�۽��}
	public static final String MSG_SYS_PARAM_CASH_DRIVER_SUBSIDY="CASH_DRIVER_SUBSIDY";//�滻�� Ϊ{�ֽ�˾�����}
	public static final String MSG_SYS_PARAM_CASH_ADD_AMOUNT="CASH_ADD_AMOUNT";//�滻�� Ϊ{�ֽᲹ����}
	
	public static final String MSG_SYS_PARAM_PRICE_INCLUDE_COST="PRICE_INCLUDE_COST";//�滻�� Ϊ{���۰�������} //��������񵥣���Ϊ������۰���
	public static final String MSG_SYS_PARAM_CUST_CASH_COST="CUST_CASH_COST";//�滻�� Ϊ{�ͻ��ֽ��������}
	
	public static final String MSG_SYS_PARAM_TRADE_AGENT_NAME="TRADE_AGENT_NAME";//�滻�� Ϊ{�����������}
	public static final String MSG_SYS_PARAM_TRIP_DESC_SN="TRIP_DESC_SN";//�滻�� Ϊ{�г̳���}
	public static final String MSG_SYS_PARAM_TRIP_LINE_SN="TRIP_LINE_SN";//�滻�� Ϊ{�г̰�κ�}
	
	public static final String MSG_SYS_PARAM_GET_ON_ADDRESS_DESC="GET_ON_ADDRESS_DESC";//�滻�� Ϊ{�ϳ��ص�λ����Ϣ} �����Ϻ��л�����������120��
	public static final String MSG_SYS_PARAM_CUST_SHORT_NAME="CUST_SHORT_NAME";//�滻�� Ϊ{�ͻ����}
	public static final String MSG_SYS_PARAM_TRIP_DESC="TRIP_DESC";//�滻�� Ϊ{�г̸�Ҫ}
	public static final String MSG_SYS_PARAM_GET_OFF_TIME="GET_OFF_TIME";//�滻�� Ϊ{�³�ʱ��}2015-01-01 18:00
	
	public static final String MSG_SYS_PARAM_TASK_LIST_NAME="TASK_LIST_NAME";//�滻�� Ϊ	{˾��ˢ���б�����}��������/�����У�
	public static final String MSG_SYS_PARAM_CANCLE_DATE_LIST="CANCLE_DATE_LIST";//�滻�� Ϊ{ȡ���ɳ������б�}��03-23,03-24��03-25...��
	
	/* 4-18 ADD END */
	
	public static final String DATA_DIC_SEX_MALE ="SE_0010";  //��
	public static final String DATA_DIC_SEX_FMALE ="SE_0020"; //Ů
	
	public static int AGENT_MAINPAGE_MSG_MAX_COUNT=10;//��ҳ��Ϣ��֪ͨ�������ʾ������
	
	public static final String ASSIGN_FLOW_OP_TYPE_ASSIGN ="AF_0010"; //�����ɳ���˾������������:������
	public static final String ASSIGN_FLOW_OP_TYPE_CANNEL_ASSIGN ="AF_0020"; //�����ɳ���˾�����������ͣ�ȡ����
	public static final String ASSIGN_FLOW_OP_TYPE_CHANGE_ASSIGN ="AF_0030"; //�����ɳ���˾�����������ͣ�������
	
	public static boolean OSS_ISREADY;
	public static String OSS_ACCESS_KEY_ID;
	public static String OSS_ACCESS_KEY_SECRET;
	public static String OSS_BUCKET;
	public static String OSS_ENDPOINT_OUT;
	public static String OSS_ENDPOINT_INNER;
	public static String OSS_IN_OUT_TAG;
	
	public static boolean IS_OCS_SUPPORT=false;//�Ƿ�֧���ⲿ���档
	
	
	public static boolean OTS_ISREADY;
	public static String OTS_ACCESS_KEY_ID;
	public static String OTS_ACCESS_KEY_SECRET;
	public static String OTS_ENDPOINT_OUT;
	public static String OTS_ENDPOINT_INNER;
	public static String OTS_IN_OUT_TAG;	
	public static String OTS_INSTANCE_NAME;	
//	public static String OTS_TABLE_LIST;
	
	
	public static final String SYS_NAME="YOUBUS";//��������cachekey��ǰ׺
	public static final String APP_NAME="AGENT_CENTER";//
	
	//˾���ƶ���������Ϣ����
	public static final String TASK_MSG_PROPERTY_ORDER_TRIP_TYPE="ORDER_TRIP_TYPE";//���������ֵ�-��������
	public static final String TASK_MSG_PROPERTY_ORDER_STATUS="ORDER_STATUS";//���������ֵ�-����״̬
	public static final String TASK_MSG_PROPERTY_ORDER_STATUS_UNBEGIN="ORDER_STATUS_0001";//���������ֵ�-δ��ʼ
	public static final String TASK_MSG_PROPERTY_ORDER_STATUS_RUN="ORDER_STATUS_0002";//���������ֵ�-ִ����
	public static final String TASK_MSG_PROPERTY_ORDER_STATUS_SUB_FINISH="ORDER_STATUS_0003";//���������ֵ�-�������
	public static final String TASK_MSG_PROPERTY_ORDER_STATUS_ALL_FINISH="ORDER_STATUS_0004";//���������ֵ�-�����
	
	public static final String TASK_MSG_PROPERTY_ORDER_CHANGE="ORDER_CHANGE";//���������ֵ�-���״̬
	public static final String TASK_MSG_PROPERTY_ORDER_CHANGE_NORMAL="ORDER_CHANGE_0000";//���������ֵ�-����
	public static final String TASK_MSG_PROPERTY_ORDER_CHANGE_CHANGE="ORDER_CHANGE_0001";//���������ֵ�-���
	public static final String TASK_MSG_PROPERTY_ORDER_CHANGE_CANCLE="ORDER_CHANGE_0002";//���������ֵ�-ȡ��
	
	
	public static final String TASK_MSG_PROPERTY_ORDER_CUST_NAME="ORDER_CUST_NAME";//���������ֵ�-�ͻ����
	public static final String TASK_MSG_PROPERTY_ORDER_TRIP_SUMMRY="ORDER_TRIP_SUMMRY";//���������ֵ�-�г̼���
	public static final String TASK_MSG_PROPERTY_ORDER_P_BEGIN_TIME="ORDER_P_BEGIN_TIME";//���������ֵ�-�г̼ƻ���ʼʱ��
	public static final String TASK_MSG_PROPERTY_ORDER_P_END_TIME="ORDER_P_END_TIME";//���������ֵ�-�г̼ƻ�����ʱ��
	public static final String TASK_MSG_PROPERTY_ORDER_R_BEGIN_TIME="ORDER_R_BEGIN_TIME";//���������ֵ�-�г�ʵ�ʿ�ʼʱ��
	public static final String TASK_MSG_PROPERTY_ORDER_R_END_TIME="ORDER_R_END_TIME";//���������ֵ�-�г�ʵ�ʽ���ʱ��
	public static final String TASK_MSG_PROPERTY_ORDER_TRIP_DISTANCE="ORDER_TRIP_DISTANCE";//���������ֵ�-�г̾��빫��
	public static final String TASK_MSG_PROPERTY_ORDER_TRIP_DURATION="ORDER_TRIP_DURATION";//���������ֵ�-�г�ʱ������
	public static final String TASK_MSG_PROPERTY_ORDER_PAUSE="ORDER_PAUSE";//���������ֵ�-�г���ͣ״̬
	public static final String TASK_MSG_PROPERTY_ORDER_PAUSE_UN_PAUSE="ORDER_PAUSE_0000";//���������ֵ�-�г���ͣ״̬
	public static final String TASK_MSG_PROPERTY_ORDER_PAUSE_PAUSEING="ORDER_PAUSE_0001";//���������ֵ�-�г���ͣ״̬
	
	
	public static final String TASK_MSG_PROPERTY_ORDER_PAUSE_TIME="ORDER_PAUSE_TIME";//���������ֵ�-�г����һ����ͣʱ��
	
	public static final String TASK_MSG_PROPERTY_TASK_MSG_TYPE="TASK_MSG_TYPE";//���������ֵ�-��Ϣ����
	public static final String TASK_MSG_PROPERTY_TASK_MSG_TYPE_NEW="TASK_MSG_TYPE_0001";//���������ֵ�-��Ϣ����-������
	public static final String TASK_MSG_PROPERTY_TASK_MSG_TYPE_RUNNING="TASK_MSG_TYPE_0002";//���������ֵ�-��Ϣ����-ִ����
	public static final String TASK_MSG_PROPERTY_TASK_MSG_TYPE_FINISH="TASK_MSG_TYPE_0003";//���������ֵ�-��Ϣ����-�����
	
	public static final String TASK_MSG_PROPERTY_TASK_MSG_READ="TASK_MSG_READ";//���������ֵ�-��ȡ״̬
	public static final String TASK_MSG_PROPERTY_TASK_MSG_READ_UNREAD="TASK_MSG_READ_0000";//���������ֵ�-��ȡ״̬-δ��
	public static final String TASK_MSG_PROPERTY_TASK_MSG_READ_READ_DONE="TASK_MSG_READ_0001";//���������ֵ�-��ȡ״̬-�Ѷ�
	
	public static final String TASK_MSG_PROPERTY_TASK_MSG_CFM="TASK_MSG_CFM";//���������ֵ�-��Ϣȷ��״̬
	public static final String TASK_MSG_PROPERTY_TASK_MSG_CFM_UNCFM="TASK_MSG_CFM_0000";//���������ֵ�-��Ϣδȷ��״̬
	public static final String TASK_MSG_PROPERTY_TASK_MSG_CFM_CFM_DONE="TASK_MSG_CFM_0001";//���������ֵ�-��Ϣ��ȷ��״̬
	
	public static final String TRIP_BUS_WORKING_STATUS_UN_BEGIN="BW_0000";//daily��ǰ����״̬ δ��ʼ;
	public static final String TRIP_BUS_WORKING_STATUS_BEGINING="BW_0005";//daily��ǰ����״̬�ӿͻ���;
	public static final String TRIP_BUS_WORKING_STATUS_RUNNING="BW_0010";//daily��ǰ����״̬ ������;
	public static final String TRIP_BUS_WORKING_STATUS_PAUSE="BW_0020";//daily��ǰ����״̬ ����ͣ;
	public static final String TRIP_BUS_WORKING_STATUS_FINISH="BW_0030";//daily��ǰ����״̬ �����;
	
	public static final String TRIP_VIA_MOBILE_RECORD_TYPE_AT="RT_0010";//�г̵�;����򿨷�ʽ���Զ�;
	public static final String TRIP_VIA_MOBILE_RECORD_TYPE_MT="RT_0020";//�г̵�;����򿨷�ʽ���ֹ�;
	
	public static final String PUSH_MSG_TYPE_MESSAGE="PMT_0010";//��Ϣ����ƽ̨��Ϣ���ͣ���͸��Ϣ����Ļ����ʾ��TYPE_MESSAGE:͸����Ϣ������ ע�⣺TYPE_MESSAGE������Ϣ Ĭ�����ն��ǲ�չʾ��
	public static final String PUSH_MSG_TYPE_NOTIFICATION="PMT_0020";//��Ϣ����ƽ̨��Ϣ����:��ͨ��Ϣ,��Ļ��ʾ��TYPE_NOTIFICATION:֪ͨ��
	
	public static final String DRIVER_TASK_MSG_TYPE_NEW_TASK="DTM_0010";//������-����
	public static final String DRIVER_TASK_MSG_TYPE_MOD_TASK="DTM_0020";//������-ͬһ�г̵��£�˾��daily���仯����>=1��(��Ե���ȡ�����������ɵ�)
	public static final String DRIVER_TASK_MSG_TYPE_MOD_TRIP="DTM_0030";//�г̱��-�г̱������Կͻ��г̱仯��
	public static final String DRIVER_TASK_MSG_TYPE_CAL_TASK="DTM_0040";//����ȡ��-˾����ȫȡ���ɣ�ͬһ�г̵��£�˾��daily��Ϊ0�����ȫ��ȡ������������˾����
	public static final String DRIVER_TASK_MSG_TYPE_CAL_TRIP="DTM_0050";//�г�ȡ��-�г�ȡ��

	public static final String TRIP_FINANCIAL_CHECK_STATUS_UN_DRIVER_SUBMIT="AM_0000";//˾��δ�ύ  --��˾��ִ�����
	public static final String TRIP_FINANCIAL_CHECK_STATUS_DRIVER_SUBMIT="AM_0010";//˾�����ύ --���������
	public static final String TRIP_FINANCIAL_CHECK_STATUS_DISPATCH_SAVE="AM_0019";//�����ѳ���
	public static final String TRIP_FINANCIAL_CHECK_STATUS_DISPATCH_SUBMIT="AM_0020";//���������--���������
	public static final String TRIP_FINANCIAL_CHECK_STATUS_CASHIER_SAVE="AM_0029";//�����ѳ����
	public static final String TRIP_FINANCIAL_CHECK_STATUS_CASHIER_SUBMIT="AM_0030";//���������
	public static final String TRIP_FINANCIAL_CHECK_STATUS_FINANCE_SUBMIT="AM_0040";//���������--�����
	
//	˾����;��������״̬
	public static final String DRIVE_VIA_RUN_STATUS_NORMAL="DVS_0010";//����
	public static final String DRIVE_VIA_RUN_STATUS_NORMAL_DESC="����";//����
	public static final String DRIVE_VIA_RUN_STATUS_SKIP="DVS_0020";//����
	public static final String DRIVE_VIA_RUN_STATUS_SKIP_DESC="����";//����
	public static final String DRIVE_VIA_RUN_STATUS_DONE="DVS_0030";//�����
	public static final String DRIVE_VIA_RUN_STATUS_DONE_DESC="�����";//�����
	public static final String DRIVE_VIA_RUN_STATUS_LOCK="DVS_0050";//����//��԰೵����
	public static final String DRIVE_VIA_RUN_STATUS_LOCK_DESC="����";//����
	
//  ˾������״̬��DRIVER_TASK_STATUS
	public static final String DRIVER_TASK_STATUS_UN_CONFIRM="DTS_0010";//��ȷ��--
	public static final String DRIVER_TASK_STATUS_UN_BEGIN="DTS_0020";//δ��ʼ  --����
	public static final String DRIVER_TASK_STATUS_BEGINING="DTS_0030";//�ӿͻ���  --����
	public static final String DRIVER_TASK_STATUS_RUNNING="DTS_0040";//ִ����  --����
	public static final String DRIVER_TASK_STATUS_PAUSE="DTS_0050";//��ͣ  --����
	public static final String DRIVER_TASK_STATUS_CONTINUE="DTS_0060";//����  --����
	public static final String DRIVER_TASK_STATUS_SUB_FINISH="DTS_0070";//�������--�ɹ���δȫ�������ѽ���״̬  --����
	public static final String DRIVER_TASK_STATUS_TASK_FINISH="DTS_0080";//�����//��˾������--�ɹ���ȫ�������ѽ���״̬
	public static final String DRIVER_TASK_STATUS_FINACE_SUBMIT="DTS_0090";//�ѽ���//���������--
	public static final String DRIVER_TASK_STATUS_DIS_CHECK_END="DTS_0100";//�����//���������--�����
	public static final String DRIVER_TASK_STATUS_FINACE_END="DTS_0110";//�ѱ���//���������--�����
	public static final String DRIVER_TASK_STATUS_ALL_END="DTS_0120";//�ѽ���--�����
	
	public static final String DRIVER_TASK_STATUS_CONFIRM_END="DTS_0011";//��ȷ��//����״̬--
	public static final String DRIVER_TASK_STATUS_CANCEL="DTS_0190";//������ȡ��--��ȡ��--
	
	
	
	public static final String AGENT_REC_ACC_TYPE_CASH="RAT_0000";//�ֽ�//�ո����������˻�����
	public static final String AGENT_REC_ACC_TYPE_BANK="RAT_0010";//����//�ո����������˻�����
	public static final String AGENT_ACC_REC_PAY_TAG_ALL="ARP_0030";//���տɸ�//�����˻����ո�������
	
//	TRIP_STATUS
	public static final String BUS_TRIP_MAIN_TRIP_STATUS_UNASSIGN="TS_0010";//δ�䳵
	public static final String BUS_TRIP_MAIN_TRIP_STATUS_ASSIGN_END="TS_0011";//���䳵//�������ɳ�
	public static final String BUS_TRIP_MAIN_TRIP_STATUS_RUNNING="TS_0020";//������//�����ֿ�ʼ���������
	public static final String BUS_TRIP_MAIN_TRIP_STATUS_TRIP_FINISH="TS_0030";//�г̽���
	
//	TM_AGENT_TRIP_TASK TRIP_STATUS
	public static final String TRIP_TASK_TRIP_STATUS_TEMP="TOS_0000";//�ݸ�
	public static final String TRIP_TASK_TRIP_STATUS_UNASSIGN="TOS_0020";//�ȴ��ɳ�
	public static final String TRIP_TASK_TRIP_STATUS_ASSIGNING="TOS_0030";//�ɳ���
	public static final String TRIP_TASK_TRIP_STATUS_ASSIGNED="TOS_0040";//���ɳ�
	public static final String TRIP_TASK_TRIP_STATUS_ASSIGN_FINISH="TOS_0050";//�ɳ����
	public static final String TRIP_TASK_TRIP_STATUS_RUNNING="TOS_0060";//ִ����
	public static final String TRIP_TASK_TRIP_STATUS_UNDISPATCH_CHECK="TOS_0070";//���������
	public static final String TRIP_TASK_TRIP_STATUS_UNCASHER_CHECK="TOS_0080";//���������
	public static final String TRIP_TASK_TRIP_STATUS_UNORDER_CHECK="TOS_0090";//���������
	public static final String TRIP_TASK_TRIP_STATUS_ORDER_CHECK_END="TOS_0100";//�ѽ���
	public static final String TRIP_TASK_TRIP_STATUS_ORDER_CANCEL="TOS_0110";//��ȡ��
	
//	TM_SINGLE_BUS_TRIP SBT_TRIP_STATUS,TM_BUS_TRIP_MAIN TRIP_STATUS
	public static final String TRIP_TASK_SBT_TRIP_STATUS_TEMP="SBT_0000";//�ݸ�
	public static final String TRIP_TASK_SBT_TRIP_STATUS_UNASSIGN="SBT_0010";//���ɳ�
	public static final String TRIP_TASK_SBT_TRIP_STATUS_ASSIGNING="SBT_0020";//�����ɳ�
	public static final String TRIP_TASK_SBT_TRIP_STATUS_ASSIGNED="SBT_0030";//���ɳ�
	public static final String TRIP_TASK_SBT_TRIP_STATUS_UNDRIVER_CFM="SBT_0040";//��˾��ȷ��
	public static final String TRIP_TASK_SBT_TRIP_STATUS_ENDRIVER_CFM="SBT_0050";//��ȷ��δ��ʼ
	public static final String TRIP_TASK_SBT_TRIP_STATUS_RUNNING="SBT_0060";//ִ����
	public static final String TRIP_TASK_SBT_TRIP_STATUS_SUB_FINISH="SBT_0070";//���������
	public static final String TRIP_TASK_SBT_TRIP_STATUS_UNDRIVER_CHECK="SBT_0080";//��˾������
	public static final String TRIP_TASK_SBT_TRIP_STATUS_UNDISPATCH_CHECK="SBT_0090";//���������
	public static final String TRIP_TASK_SBT_TRIP_STATUS_UNCASHER_CHECK="SBT_0100";//���������
	public static final String TRIP_TASK_SBT_TRIP_STATUS_CASHER_CHECK_END="SBT_0110";//���������
	public static final String TRIP_TASK_SBT_TRIP_STATUS_SINGLE_CANCEL="SBT_0120";//��ȡ��
	
//	TM_BUS_TRIP_DAILY  BTD_TRIP_STATUS 
	public static final String TRIP_TASK_BTD_TRIP_STATUS_TEMP="BTD_0000";//δȷ��--�ݸ�
	public static final String TRIP_TASK_BTD_TRIP_STATUS_UNASSIGN="BTD_0010";//���ɵ� //����
	public static final String TRIP_TASK_BTD_TRIP_STATUS_ASSIGNED="BTD_0020";//�ѣ�Ԥ���ɳ� //Ԥ��
	public static final String TRIP_TASK_BTD_TRIP_STATUS_UNDRIVER_CFM="BTD_0030";//��˾��ȷ�� //��ȷ��
	public static final String TRIP_TASK_BTD_TRIP_STATUS_ENDRIVER_CFM="BTD_0040";//��ȷ��δ��ʼ //δ��ʼ
	public static final String TRIP_TASK_BTD_TRIP_STATUS_MEET_CUST="BTD_0050";//�ӿͻ���
	public static final String TRIP_TASK_BTD_TRIP_STATUS_RUNNING="BTD_0060";//ִ����
	public static final String TRIP_TASK_BTD_TRIP_STATUS_PAUSE="BTD_0070";//��ͣ
	public static final String TRIP_TASK_BTD_TRIP_STATUS_FINISH="BTD_0080";//�ѽ�����ԭ����ɣ�
	public static final String TRIP_TASK_BTD_TRIP_STATUS_DRIVER_CHECK_END="BTD_0090";//--����ɣ�ԭ�������ύ��
	
	public static final String TRIP_TASK_BTD_TRIP_STATUS_UNDRIVER_CHECK="BTD_0081";//��˾������--
	
	
	
//	COR_0010��������
//	COR_0011��������
//	COR_0050�г̵�����
//	COR_0051�г̵�����
//	TM_AGENT_ORDER_CHANGE CHANGE_REASON
	public static final String ORDER_CHANGE_REASON_TYPE_ORDER_CREATE="COR_0010";//��������
	public static final String ORDER_CHANGE_REASON_TYPE_ORDER_CHANGE_PRICE="COR_0011";//��������
	public static final String ORDER_CHANGE_REASON_TYPE_ORDER_CHANGE_MAIN="COR_0012";//��������Ϣ���
	public static final String ORDER_CHANGE_REASON_TYPE_ORDER_CHANGE_TRIP="COR_0013";//�����г���Ϣ���
	public static final String ORDER_CHANGE_REASON_TYPE_ORDER_CHANGE_CANCEL="COR_0014";//����ȡ�����
	public static final String ORDER_CHANGE_REASON_TYPE_ORDER_CHANGE_MEMBER="COR_0015";//����ҵ��Ա���
	public static final String ORDER_CHANGE_REASON_TYPE_ORDER_CHANGE_OTHER="COR_0019";//����������Ϣ���
	public static final String ORDER_CHANGE_REASON_TYPE_ORDER_AMOUNT_SUBMIT="COR_0016";//�����۸�����
	public static final String ORDER_CHANGE_REASON_TYPE_ORDER_AMOUNT_SUBMIT_CANCLE="COR_0017";//�����۸�ȡ�����ˣ�������
	
	
	public static final String ORDER_CHANGE_REASON_TYPE_SINGLE_CREATE="COR_0050";//�г̵�����
	public static final String ORDER_CHANGE_REASON_TYPE_SINGLE_CHANGE_PRICE="COR_0051";//�г̵�����
	public static final String ORDER_CHANGE_REASON_TYPE_SINGLE_CHANGE_MAIN="COR_0052";//�г̵�����Ϣ���
	public static final String ORDER_CHANGE_REASON_TYPE_SINGLE_CHANGE_TRIP="COR_0053";//�г̵��г���Ϣ���
	public static final String ORDER_CHANGE_REASON_TYPE_SINGLE_CHANGE_ASSIGN="COR_0054";//�г̵�ȷ�ϴ��ɱ��
	public static final String ORDER_CHANGE_REASON_TYPE_SINGLE_CHANGE_CANCEL="COR_0055";//�г̵�ȡ���г̱��
	
	public static final String ORDER_CHANGE_REASON_TYPE_SINGLE_CHANGE_OTHER="COR_0059";//�г̵�������Ϣ���
	
	
	public static final String TRADE_CHANGE_REASON_TYPE_TASK_CREATE="COR_0060";//�������
	public static final String TRADE_CHANGE_REASON_TYPE_PRICE_CHANGE="COR_0061";//������۵���
	
	
	
//	TM_AGENT_ORDER_CHANGE CHANGE_ORDER_STATUS ���ݼ۸���ȷ��״̬	
	public static final String ORDER_CHANGE_CHECK_STATUS_UNCHECK="COS_0010";//δȷ��
	public static final String ORDER_CHANGE_CHECK_STATUS_CHECKED="COS_0020";//��ȷ��
//	 TM_AGENT_ORDER_CHANGE CHANGE_ORDER_TYPE ���ݼ۸�����ˮҵ�񵥾�����
	public static final String ORDER_CHANGE_ORDER_TYPE_ORDER="COT_0010";//������
	public static final String ORDER_CHANGE_ORDER_TYPE_SINGLE="COT_0020";//�г̵���
	public static final String ORDER_CHANGE_ORDER_TYPE_TASK="COT_0030";//˾������ID
//	TM_AGENT_ORDER_AMOUNT  FINAL_PAY_WAY
	public static final String TRIP_TASK_FINAL_PAY_WAY_CASH="FP_0010";//�ֽ�//������������ȸ��ʽ
	public static final String TRIP_TASK_FINAL_PAY_WAY_BACK="FP_0020";//�ؽ�

//	TT_BUS_TRIP_COST_LIST TT_AGENT_ORDER_COST_LIST  CUST_PAY_WAY
	public static final String TRIP_TASK_CUST_PAY_WAY_CASH="CP_0010";//�ֽ�//�ͻ�֧�����ã��ֽ�ؽ�ȷ�ʽ
	public static final String TRIP_TASK_CUST_PAY_WAY_BACK="CP_0020";//�ؽ�
	
	//TT_AGENT_MEMBER_FILE_TASK_LIST TASK_STATUS
	public static final String MEMBER_FILE_TASK_STATUS_UNBEGIN="MFT_0010";//δ��ʼ
	public static final String MEMBER_FILE_TASK_STATUS_RUNNING="MFT_0020";//������
	public static final String MEMBER_FILE_TASK_STATUS_SUCCESS="MFT_0030";//���ɳɹ�
	public static final String MEMBER_FILE_TASK_STATUS_FAILD="MFT_0040";//����ʧ��
	//TT_AGENT_MEMBER_FILE_TASK_LIST TASK_BUSI_TYPE
	public static final String MEMBER_FILE_TASK_BUSTYPE_FINACE="MFB_0010";//���񱨱���
	public static final String MEMBER_FILE_TASK_BUSTYPE_ASSIGN="MFB_0020";//�����ɳ��ƻ�
	
	public static final double SMS_AMOUNT_RATE=0.1d;//��Ϣ����  SMS_AMOUNT=SMS_COUNT x ���ʣ�SMS_AMOUNT_RATE��//Ӧ�����ñ�ȡ
	public static final String SMS_PRIFIX_WORD="���а͡�";//ÿ�����ŵ�ǰ׺
	public static final int SMS_UNIT_WORD_LENGTH=67;//ÿ�����ŵ���������	
	
	
//	TM_YOUBUS_PAY_ORDER
	//public static final String YB_PAY_CHANNEL_TYPE_WX="YPCT_0010";//֧������֧������-΢��
	public static final String YB_PAY_CHANNEL_TYPE_ALI="YPCT_0020";//֧������֧������-֧����
	
	public static final String YB_PAY_ORDER_TYPE_TICKET="CT";//֧������ҵ������ - �ͻ���Ʊ
	public static final String YB_PAY_ORDER_TYPE_CPAY="CP";//֧������ҵ������ - �ͻ�֧��
	
	
	public static final String YB_PAY_ORDER_STATUS_READY="YPS_0010";//֧�����ض���(֧��)״̬ -�ݸ�(���µ�)
	public static final String YB_PAY_ORDER_STATUS_SCAN_CODE="MOS_0020";//֧�����ض���(֧��)״̬ -��ɨ��
	public static final String YB_PAY_ORDER_STATUS_PAYED="MOS_0030";//֧�����ض���(֧��)״̬ -��֧��
	public static final String YB_PAY_ORDER_STATUS_PAY_SUCCESS="MOS_0040";//֧�����ض���(֧��)״̬ -֧���ɹ�
	public static final String YB_PAY_ORDER_STATUS_PAY_FAILD="MOS_0050";//֧�����ض���(֧��)״̬ -֧��ʧ��
	
	public static final String YB_PAY_CALL_STATUS_UNCALL="YPCS_0010";//֧�����ض���֧�����֪ͨ״̬ δ֪ͨ
	public static final String YB_PAY_CALL_STATUS_CALL_SUCCESS="YPCS_0020";//֧�����ض���֧�����֪ͨ״̬ ��֪ͨ
	public static final String YB_PAY_CALL_STATUS_CALL_FAILD="YPCS_0030";//֧�����ض���֧�����֪ͨ״̬ ֪ͨʧ��
	
//	TT_OUT_PAYMENT_MSG_LOG  --REQ_RSP_TYPE
	public static final String PAY_REQ_RSP_TYPE_WX_REQ_PAY="RW_RP_0010";//΢�ŷ���֧����֤����
	public static final String PAY_REQ_RSP_TYPE_WX_RSP_COMMON="RW_PC_0010";//΢�ŷ���ͳһ�µ�Ӧ��
	public static final String PAY_REQ_RSP_TYPE_WX_REQ_PAY_RESULT="RW_RR_0010";//΢�ŷ���֧���������
	
	// ֧������֧������
	public final static String YB_PAY_CHANNEL_TYPE_WX = "YPCT_0010";//΢��
	public final static String YB_PAY_CHANNEL_TYPE_ZFB = "YPCT_0020";//֧����
	
	// ֧������ҵ������
	public final static String YB_PAY_ORDER_TYPE_CT = "CT";//�ͻ���Ʊ
	public final static String YB_PAY_ORDER_TYPE_CP = "CP";//�ͻ�֧��
	
	// ֧�����ض���(֧��)״̬
	public final static String YB_PAY_ORDER_STATUS_ORDER = "YPS_0010";//�ݸ�(���µ�)
	public final static String YB_PAY_ORDER_STATUS_CODE = "YPS_0020";//��ɨ��
	public final static String YB_PAY_ORDER_STATUS_PAY = "YPS_0030";//��֧��
	public final static String YB_PAY_ORDER_STATUS_SUCCESS = "YPS_0040";//֧���ɹ�
	public final static String YB_PAY_ORDER_STATUS_FAIL = "YPS_0050";//֧��ʧ��
	public final static String YB_PAY_ORDER_STATUS_CANCLE = "YPS_0060";//���˿�
	
	// ֧�����ض���֧�����֪ͨ״̬
	public final static String YB_PAY_CALL_STATUS_NOTIFY_NO = "YPCS_0010";//δ֪ͨ
	public final static String YB_PAY_CALL_STATUS_NOTIFY_YES = "YPCS_0020";//��֪ͨ
	public final static String YB_PAY_CALL_STATUS_NOTIFY_FAIL = "YPCS_0030";//֪ͨʧ��
	
	// ΢�Ŷ��˵�У������
	public final static String OUT_PAY_TYPE_JSAPI_CODE = "JSAPI";//���ں�֧��
	public final static String OUT_PAY_TYPE_JSAPI_NAME = "���ں�֧��";//���ں�֧��
	public final static String OUT_PAY_TYPE_MICROPAY_CODE = "MICROPAY";//ˢ��֧��
	public final static String OUT_PAY_TYPE_MICROPAY_NAME = "ˢ��֧��";//ˢ��֧��
	public final static String OUT_PAY_TYPE_NATIVE_CODE = "NATIVE";//ɨ��֧��
	public final static String OUT_PAY_TYPE_NATIVE_NAME = "ɨ��֧��";//ɨ��֧��
	public final static String OUT_PAY_TYPE_APP_CODE = "APP";//APP֧��
	public final static String OUT_PAY_TYPE_APP_NAME = "APP֧��";//APP֧��
	
}
