/**
 * ��Ŀ����:AGENT_CENTER
 * ��         ��:com.youbus.framework.comm
 * ��   ��  ��:SMSReceiveUtil.java
 * �� ������:2015��7��22��-����2:14:24
 * Copyright @ 2015-YouBus.com.cn
 */
package com.youbus.framework.comm;

import java.sql.Connection;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.infoservice.po.DynaBean;
import com.infoservice.po.POFactory;
import com.youbus.framework.util.DBConUtil;

/**
 * ������:SMSReceiveUtil
 * ������:���Ž��չ�����
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2015��7��22�� ����2:14:24
 * �޸ı�ע:
 * @version 1.0.0
 */
public class SMSReceiveUtil {

	/**
	 * ����һ���µ�ʵ�� SMSReceiveUtil.
	 *
	 */
	private static Logger log=Logger.getLogger(SMSReceiveUtil.class);
	public SMSReceiveUtil() {
		// TODO Auto-generated constructor stub
	}
//	����
//	response return status:=200
//	response result:=<?xml version="1.0" encoding="utf-8"?>
//	<GetReplyResult xmlns="http://121.199.16.178/">
//	<code>2</code>
//	<msg>18221616910,24,2015-07-22 12:5pro3:04,116677315</msg>
//	</GetReplyResult>
//	����
//	response result:=<?xml version="1.0" encoding="utf-8"?>
//	<GetReplyResult xmlns="http://121.199.16.178/">
//	<code>2</code>
//	<msg>18221616910,33,2015-07-22 14:19:50,116677315|@|18221616910,54,2015-07-22 14:19:57,116677315</msg>
//	</GetReplyResult>


	/**
	 * ��ⱨ��
	 * ��   ��  ��:parseSmsResponse
	 * ��������:
	 * ��         ��:@param xml
	 * ��         ��:@return
	 * ��   ��  ֵ:List<DynaBean>
	 * ��   ��  ��:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static List<DynaBean> parseSmsResponse(String xml){
		Document doc;
		try {
			doc = DocumentHelper.parseText(xml);
			Element root = doc.getRootElement();
			String returnCode=root.element("code").getText();
			System.out.println("returnCode:="+returnCode);
			if("2".equals(returnCode)){
				List<DynaBean> list=new ArrayList<DynaBean>();
				String msgContent=root.element("msg").getText();
				System.out.println("msgContent:="+msgContent);
				String splitTag="\\|@\\|";
				if(DBConUtil.stringNotNULL(msgContent)){
				String[] msgList=msgContent.split(splitTag);
					if(msgList!=null&&msgList.length>0){
						for(String msgInfo:msgList){
							System.out.println("msgInfo:="+msgInfo);//
							DynaBean bean=parseOneMsgInfo(msgInfo);
							if(bean!=null)list.add(bean);
							System.out.println(bean);
						}
					}
					
				}
				return list;
			}
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	/**
	 * ��������Ϣת��Ϊdynabean
	 * ��   ��  ��:parseOneMsgInfo
	 * ��������:
	 * ��         ��:@param msgInfo
	 * ��         ��:@return
	 * ��   ��  ֵ:DynaBean
	 * ��   ��  ��:rock
	 * @exception
	 * @since  1.0.0
	 */
	private static DynaBean parseOneMsgInfo(String msgInfo){
//		18221616910,33,2015-07-22 14:19:50,116677315
		if(DBConUtil.stringNotNULL(msgInfo)){
			String[] msg=msgInfo.split(",");
			if(msg!=null&&msg.length>0){
				if(msg.length==4){
					DynaBean bean=new DynaBean("MSG");
					bean.add("MOBILE_NO", msg[0]);
					bean.add("SMS_CONTENT", msg[1]);
					try {
						bean.add("SMS_SEND_TIME", DBConUtil.string2Time(msg[2]));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					bean.add("FLOW_ID", msg[3]);
					return bean;
				}else{
					log.debug("msgInfo:=["+msgInfo+"]��Ϣ���ȸ�ʽ����������ʧ�ܡ� ");	
				}
			}
		}
		return null;
	}
	
	/**���ݻظ����ݼ����룬ʱ�䣬�Զ����� TM_AGENT_MSG_ITEM ��Ϣ����״̬,��Ϊ�ظ�ʱ�����ڷ���ʱ��7���ڣ������Դ���
	 * 			String mobile=temp.getMobileNo();
				Date sendDate=temp.getSmsSendTime();
				Date receiveDate=temp.getCreateTime();
				String content=temp.getSmsContent();
	 * ��   ��  ��:parseSms2Busi
	 * ��������: sendDate �Ƕ���ƽ̨�Ľ��ջظ���ʱ�䣬�뱾����ʱ��ͬ��������,��Ϊ�ظ�ʱ�����ڷ���ʱ��7���ڣ������Դ���
	 * ��         ��:@param conn
	 * ��         ��:@return
	 * ��   ��  ֵ:int
	 * ��   ��  ��:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static DynaBean parseSms2Busi(Connection conn,String mobile,String content,Date createDate,int smsItemId){
		//TM_AGENT_MSG_ITEM  TT_AGENT_MSG_CC_LIST�����������У��ѷ��ͳɹ��ģ�������send Date ͬһ��� ��ȡ�����Ƕ��ŵ� �����ߣ�Ŀ�ĵء�
//		��Ϊ�ظ�ʱ�����ڷ���ʱ��7���ڣ������Դ���
//		String subString=null;
//		if(createDate!=null){
//			DBConUtil.handleTimestamp(createDate);
//		}
		Date now=new Date(System.currentTimeMillis());
		if(!DBConUtil.stringNotNULL(content))return null;
		if(!DBConUtil.stringNotNULL(content.trim()))return null;
		
		String sql=" SELECT B.MSG_MAIN_ID ,B.AGENT_ID ,B.MSG_ITEM_ID,A.MSG_REP_CODE,"
				 + " B.SEND_CHANNEL,B.MSG_TYPE,B.MSG_SEND_STATUS,B.RECEIVE_CHANNEL_NO,"
				 + " B.RECEIVER_EMP_ID,B.RECEIVER_NAME,B.MSG_SEND_TIME , '0' AS TARGET_TAG ,A.SINGLE_BUS_ID  "
				 + " FROM TM_AGENT_MSG_ITEM A ,TT_AGENT_MSG_CC_LIST B "
				 + " WHERE A.ID=B.MSG_ITEM_ID "
				 + " AND A.AGENT_ID=B.AGENT_ID "
				 + " AND A.MSG_MAIN_ID=B.MSG_MAIN_ID "
				 + " AND B.STATUS='1' "
				 + " AND B.MSG_SEND_STATUS='"+YBCommonContant.MSG_SEND_STATUS_SEND_END+"' "
				 + " AND B.SEND_CHANNEL='"+YBCommonContant.MSG_MSG_SEND_CHANNEL_SMS+"' "
				 + " AND B.MSG_TYPE='"+YBCommonContant.MSG_MSG_TYPE_INFO+"' "
				 + " AND date_format(B.MSG_SEND_TIME,'%Y-%m-%d %H:%i:%s') < '"+DBConUtil.handleFormatDate(createDate)+"' " //С�ڽ���ʱ��
				 + " AND date_format(DATE_ADD(B.MSG_SEND_TIME,INTERVAL 7 DAY),'%Y-%m-%d') >= '"+DBConUtil.handleTimestamp2(createDate) +"' " //����
//				 + " AND date_format(B.MSG_SEND_TIME,'%Y-%m-%d') = '"+DBConUtil.handleTimestamp2(createDate) +"' " //����
				 + " AND B.RECEIVE_CHANNEL_NO='"+mobile+"' "
				 + " AND A.MSG_REP_CODE='"+content+"' "
				 + " UNION ALL "
				 + " SELECT A.MSG_MAIN_ID ,A.AGENT_ID ,A.ID AS MSG_ITEM_ID,A.MSG_REP_CODE,"
				 + " A.SEND_CHANNEL,A.MSG_TYPE,A.MSG_SEND_STATUS,A.RECEIVE_CHANNEL_NO,"
				 + " A.RECEIVER_EMP_ID,A.RECEIVER_NAME,A.MSG_SEND_TIME, '1' AS TARGET_TAG ,A.SINGLE_BUS_ID   "
				 + " FROM TM_AGENT_MSG_ITEM A "
				 + " WHERE A.STATUS='1' "
				 + " AND A.MSG_SEND_STATUS='"+YBCommonContant.MSG_SEND_STATUS_SEND_END+"' "
				 + " AND A.SEND_CHANNEL='"+YBCommonContant.MSG_MSG_SEND_CHANNEL_SMS+"' "
				 + " AND A.MSG_TYPE='"+YBCommonContant.MSG_MSG_TYPE_INFO+"' "
				 + " AND date_format(A.MSG_SEND_TIME,'%Y-%m-%d %H:%i:%s') < '"+DBConUtil.handleFormatDate(createDate)+"' " //С�ڽ���ʱ��
				 + " AND date_format(DATE_ADD(A.MSG_SEND_TIME,INTERVAL 7 DAY),'%Y-%m-%d') >= '"+DBConUtil.handleTimestamp2(createDate) +"' " //����
//				 + " AND date_format(A.MSG_SEND_TIME,'%Y-%m-%d') = '"+DBConUtil.handleTimestamp2(createDate) +"' " //����
				 + " AND A.RECEIVE_CHANNEL_NO='"+mobile+"' "
				 + " AND A.MSG_REP_CODE='"+content+"' ";
		try {
			List<DynaBean> list=DBConUtil.getResult(conn, sql, "MSG_INFO");
			if(DBConUtil.listNotNULL(list)){
				DynaBean bean=new DynaBean("MSG_INFO");
				for(DynaBean temp:list){
//				TM_AGENT_MSG_REP	
				int msgItemId=temp.getInt("MSG_ITEM_ID");
				int msgMainId=temp.getInt("MSG_MAIN_ID");
				int singleId=temp.getInt("SINGLE_BUS_ID");
				int agentId=temp.getInt("AGENT_ID");
				int empId=temp.getInt("RECEIVER_EMP_ID");
				String empName=temp.getString("RECEIVER_NAME");
				String isTarget=temp.getString("TARGET_TAG");//1��ֱ�ӽ����ߴ���0�������ߴ���
				bean.add("MSG_ITEM_ID", msgItemId);
				bean.add("MSG_MAIN_ID", msgMainId);
				
				TmAgentMsgRepPO amrPO=new TmAgentMsgRepPO();
				amrPO.setAgentId(agentId);
				amrPO.setMsgItemId(msgItemId);
				amrPO.setMsgMainId(msgMainId);
				amrPO.setMsgSendTime(temp.getDate("MSG_SEND_TIME"));
				amrPO.setSendChannel(temp.getString("SEND_CHANNEL")); //���� 
				amrPO.setSendChannelNo(temp.getString("RECEIVE_CHANNEL_NO"));
				amrPO.setSenderEmpId(empId); //��������˿ͣ��ͻ���ϵ�ˣ���û�� emp id
				amrPO.setSenderName(empName);
				amrPO.setMsgRepCode(temp.getString("MSG_REP_CODE"));
				amrPO.setMsgContent(content);
				amrPO.setSmsItemId(smsItemId);
				amrPO.setStatus("1");
				amrPO.setCreateBy(YBCommonContant.SYS_DEFAULT_MEMBER_ID);
				amrPO.setCreateTime(now);	
				amrPO.setId(POFactory.getIntPriKey(conn, amrPO));
				POFactory.insert(conn, amrPO);
				
				TmBusTripDailyPO btdPOCon=new TmBusTripDailyPO();
				btdPOCon.setStatus("1");
				btdPOCon.setAgentId(agentId);
				btdPOCon.setSingleBusTripId(singleId);
				btdPOCon.setDriverId(empId);
				
				TmBusTripDailyPO btdPOValue=new TmBusTripDailyPO();
				btdPOValue.setUpdateBy(YBCommonContant.SYS_DEFAULT_MEMBER_ID);
				btdPOValue.setUpdateTime(now);
				btdPOValue.setTaskConfirmEmpId(empId);
				btdPOValue.setTaskConfirmTime(now);
				
				POFactory.update(conn, btdPOCon, btdPOValue);
				

				//��������Ϣ
				TmAgentMsgItemPO amiPO=new TmAgentMsgItemPO();
				amiPO.setStatus("1");
				amiPO.setId(msgItemId);
				amiPO.setMsgMainId(msgMainId);
				amiPO.setAgentId(agentId);
				
				TmAgentMsgItemPO amiValue=new TmAgentMsgItemPO(); //LOCK_EMP_ID Ӧ������������ ������ǰ��¼������������������������Ϣ�� �Ĵ�������
				amiValue.setMsgProcesserEmpId(empId);
				amiValue.setMsgProcesserName(empName);
				amiValue.setMsgProcessTime(now);
				amiValue.setUpdateBy(YBCommonContant.SYS_DEFAULT_MEMBER_ID);
				amiValue.setUpdateTime(now);
				if("1".equals(isTarget)){
					amiValue.setMsgProcessStatus(YBCommonContant.MSG_PROCESS_STATUS_RECEIVE_DONE);//�����Ƿ�ǰ�û�Ϊֱ�ӻ���CC�û�
					amiValue.setMsgProcessResult("OWNER PROCESS SUCCESS");
				}
				else {
					amiValue.setMsgProcessStatus(YBCommonContant.MSG_PROCESS_STATUS_CC_DONE);//CC�û�
					amiValue.setMsgProcessResult("OTHER PROCESS SUCCESS");
				}
				POFactory.update(conn, amiPO, amiValue);
				
				//TM_AGENT_RECEIVE_MSG_LIST
				if(empId!=0){//����ϵͳֱ�ӽ�����
				TmAgentReceiveMsgListPO rmListPOCon=new TmAgentReceiveMsgListPO();
				rmListPOCon.setAgentId(agentId);
				rmListPOCon.setReceiverEmpId(empId);
				rmListPOCon.setMsgMainId(msgMainId);
				rmListPOCon.setMsgItemId(msgItemId);
				rmListPOCon.setStatus("1");
				
				TmAgentReceiveMsgListPO rmListPOValue=new TmAgentReceiveMsgListPO();
				rmListPOValue.setUpdateBy(YBCommonContant.SYS_DEFAULT_MEMBER_ID); //���Żظ���create �� update by Ϊsystem
				rmListPOValue.setUpdateTime(new Date(System.currentTimeMillis()));
				rmListPOValue.setMsgProcessStatus(amiValue.getMsgProcessStatus());//���ﲻ�����Ƿ�ǰ�û�Ϊֱ�ӻ���CC�û�
//				rmListPOValue.setMsgProcessStatus(YBCommonContant.MSG_PROCESS_STATUS_RECEIVE_DONE);//���ﲻ�����Ƿ�ǰ�û�Ϊֱ�ӻ���CC�û�
				POFactory.update(conn, rmListPOCon,rmListPOValue);//
				}
				//��������receive list
				TmAgentReceiveMsgListPO rmListPOCon=new TmAgentReceiveMsgListPO();
				rmListPOCon.setAgentId(agentId);
//				rmListPOCon.setId(receiveListId);
				rmListPOCon.setMsgMainId(msgMainId);
				rmListPOCon.setMsgItemId(msgItemId);
				rmListPOCon.setStatus("1");
				
				List<TmAgentReceiveMsgListPO> otherReceiveList=POFactory.select(conn, rmListPOCon);
				TmAgentReceiveMsgListPO rmListPOValue=new TmAgentReceiveMsgListPO();
				rmListPOValue.setUpdateBy(YBCommonContant.SYS_DEFAULT_MEMBER_ID); //���Żظ���create �� update by Ϊsystem
				rmListPOValue.setUpdateTime(new Date(System.currentTimeMillis()));
				rmListPOValue.setMsgProcessStatus(amiValue.getMsgProcessStatus());
//				rmListPOValue.setMsgProcessStatus(YBCommonContant.MSG_PROCESS_STATUS_CC_DONE);
				
				for(TmAgentReceiveMsgListPO arm:otherReceiveList){
					int receiveId=arm.getReceiverEmpId();
					if(empId!=0&&receiveId==empId)continue;//������ǰ��Ϣ
					rmListPOCon.setId(arm.getId());
					POFactory.update(conn, rmListPOCon, rmListPOValue);
				}
				AgentMsgProcesserFactory.recordMsgProcessLog(conn, agentId, msgMainId, msgItemId, YBCommonContant.SYS_DEFAULT_MEMBER_ID);//��¼���Ŵ�����CC��ֱ�ӽ����߻ظ���
				
				}
				return bean;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
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
		String result="<?xml version=\"1.0\" encoding=\"utf-8\"?>"
		             +"<GetReplyResult xmlns=\"http://121.199.16.178/\">"
		             +"<code>2</code>"
		             +"<msg>18221616910,33,2015-07-22 14:19:50,116677315|@|18221616910,54,2015-07-22 14:19:57,116677315|@|18221616910,54,2015-07-22 14:19:57,116677315|@|18221616910,54,2015-07-22 14:19:57,116677315</msg>"
		             +"</GetReplyResult>";
		parseSmsResponse(result);
	}

}
