/**
 * ��Ŀ����:HY_LMS_EXCHAGE
 * ��         ��:com.hy.exchange.pofactory
 * ��   ��  ��:TmExMsgPOFactory.java
 * �� ������:2018��10��22��-����5:16:48
 * Copyright @ 2018-YouBus.com.cn
 */
package com.hy.exchange.pofactory;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.hy.exchange.po.TmExApiRecMsgPO;
import com.hy.exchange.po.TmExApiRecMsgResponsePO;
import com.hy.exchange.po.TmExNimaMsgPO;
import com.infoservice.framework.services.DBService;
import com.infoservice.po.POFactory;
import com.test2.common.HyLmsClientConstant;
import com.test2.dto.BaseRequestParamBean;
import com.test2.dto.BaseResponseBean;
import com.youbus.framework.comm.remoteclient.ParamBean;

/**
 * ������:TmExMsgPOFactory
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2018��10��22�� ����5:16:48
 * �޸ı�ע:
 * @version 1.0.0
 */
public class TmExMsgPOFactory extends POFactory {
	private static Logger log = LogManager.getLogger(TmExMsgPOFactory.class);
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
	 * ����mina��Ϣ���ͺ󣬼�¼���ݿ�
	 * ��   ��  ��:insertReq
	 * ��������:
	 * ��         ��:@param reqBean
	 * ��   ��  ֵ:void
	 * ��   ��  ��:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static void insertReq(BaseRequestParamBean reqBean){
		if(reqBean==null){
			log.error("reqBean is null");
			return;
		}
		
		DBService db=DBService.getInstance();
		Connection conn=db.getConnection();
		Object txn = db.getTransaction();
		if(conn==null){
			log.error("conn is null");
			return;
		}
		if(txn==null){
			log.error("txn is null");
			return;
		}
		boolean commit=false;
		try{
			TmExNimaMsgPO minaPOCon=new TmExNimaMsgPO();
			minaPOCon.setCreateBy(0);
			minaPOCon.setCreateTime(new Date(System.currentTimeMillis()));
			minaPOCon.setId(POFactory.getIntPriKey(conn, minaPOCon));
			minaPOCon.setMsgId(reqBean.getId());
			minaPOCon.setMsgType(HyLmsClientConstant.MSG_TYPE_REQ);
			minaPOCon.setParseStatus("4");//�Ѵ����ѷ���
			minaPOCon.setPubAppKey(reqBean.getPublishAppKey());
			minaPOCon.setPubTime(reqBean.getPublishTime());
			minaPOCon.setSign(reqBean.getSign());
			minaPOCon.setStatus("1");
			minaPOCon.setTopic(reqBean.getTopic());
			minaPOCon.setContent(reqBean.getContent());
			POFactory.insert(conn, minaPOCon);
			commit=true;
		}catch(Throwable t){
			t.printStackTrace();
			log.error(t);
		}finally{
			db.closeTransaction(txn, commit);
			db.closeConnection(conn);
		}
	}
	
	/**
	 * ������յ�Ӧ����Ϣ
	 * ��   ��  ��:insertResponse
	 * ��������:
	 * ��         ��:@param resBean
	 * ��         ��:@return
	 * ��   ��  ֵ:int
	 * ��   ��  ��:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static int insertResponse(BaseResponseBean resBean){
		if(resBean==null){
			log.error("resBean is null");
			return 0;
		}
		
		DBService db=DBService.getInstance();
		Connection conn=db.getConnection();
		Object txn = db.getTransaction();
		if(conn==null){
			log.error("conn is null");
			return 0;
		}
		if(txn==null){
			log.error("txn is null");
			return 0;
		}
		boolean commit=false;
		int id=0;
		try{
			TmExNimaMsgPO minaPOCon=new TmExNimaMsgPO();
			minaPOCon.setCreateBy(0);
			minaPOCon.setCreateTime(new Date(System.currentTimeMillis()));
			minaPOCon.setId(POFactory.getIntPriKey(conn, minaPOCon));
			minaPOCon.setMsgId(resBean.getId());
			minaPOCon.setMsgType(HyLmsClientConstant.MSG_TYPE_RSP);
			minaPOCon.setParseStatus("1");//������
			minaPOCon.setPubAppKey(resBean.getPublishAppKey());
			minaPOCon.setPubTime(resBean.getPublishTime());
			minaPOCon.setSign(resBean.getSign());
			minaPOCon.setStatus("1");
			minaPOCon.setTopic(resBean.getTopic());
			minaPOCon.setContent(resBean.getContent());
			minaPOCon.setErrCode(resBean.getErrorCode());
			minaPOCon.setErrMsg(resBean.getErrorMessage());
			minaPOCon.setReqMsgId(resBean.getRequestMessageId());
			minaPOCon.setResult(resBean.getResult());
			
			POFactory.insert(conn, minaPOCon);
			id=minaPOCon.getId();
			commit=true;
		}catch(Throwable t){
			t.printStackTrace();
			log.error(t);
		}finally{
			db.closeTransaction(txn, commit);
			db.closeConnection(conn);
		}
		return id;
	}
	
	/**
	 * ����api �����ģ�����Ӧ��
	 * ��   ��  ��:insertApiReqMsg
	 * ��������:
	 * ��         ��:@param refMinaMsgId
	 * ��         ��:@param paramList
	 * ��         ��:@return
	 * ��   ��  ֵ:int
	 * ��   ��  ��:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static int insertApiReqMsg(String refMinaMsgId,List<ParamBean> paramList){
		
		DBService db=DBService.getInstance();
		Connection conn=db.getConnection();
		Object txn = db.getTransaction();
		if(conn==null){
			log.error("conn is null");
			return 0;
		}
		if(txn==null){
			log.error("txn is null");
			return 0;
		}
		boolean commit=false;
		int id=0;
		try{
//			TM_EX_API_REC_MSG
			TmExApiRecMsgPO apiMsgPOCon=new TmExApiRecMsgPO();
			apiMsgPOCon.setCreateBy(0);
			apiMsgPOCon.setCreateTime(new Date(System.currentTimeMillis()));
			apiMsgPOCon.setId(POFactory.getIntPriKey(conn, apiMsgPOCon));
			apiMsgPOCon.setStatus("1");
			apiMsgPOCon.setParseStatus("2");//������
			if(StringUtils.isNotBlank(refMinaMsgId)){
				apiMsgPOCon.setRefMinaMsgId(refMinaMsgId);
			}
			for(ParamBean bean: paramList){
				String paramName=bean.getParamName();
				String paramValue=bean.getParamValue();
				if("serviceCode".equals(paramName)&&StringUtils.isNotBlank(paramValue)){
					apiMsgPOCon.setServiceCode(paramValue);
				}else if("appKey".equals(paramName)&&StringUtils.isNotBlank(paramValue)){
					apiMsgPOCon.setAppKey(paramValue);
				}else if("requestTime".equals(paramName)&&StringUtils.isNotBlank(paramValue)){
					apiMsgPOCon.setReqTime(paramValue);
				}else if("data".equals(paramName)&&StringUtils.isNotBlank(paramValue)){
					apiMsgPOCon.setReqData(paramValue);
				}else if("sign".equals(paramName)&&StringUtils.isNotBlank(paramValue)){
					apiMsgPOCon.setSign(paramValue);
				}
			}
			POFactory.insert(conn, apiMsgPOCon);
			id=apiMsgPOCon.getId();
			commit=true;
		}catch(Throwable t){
			t.printStackTrace();
			log.error(t);
		}finally{
			db.closeTransaction(txn, commit);
			db.closeConnection(conn);
		}
		return id;
	}
	
	/**
	 * 
	 * ��   ��  ��:updateApiReqMsg
	 * ��������:
	 * ��         ��:@param refMinaMsgId
	 * ��         ��:@param paramList
	 * ��         ��:@return
	 * ��   ��  ֵ:int
	 * ��   ��  ��:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static int updateApiReqMsg(int apiDBId,String result,String errorCode,String errorMsg,String responseData){
		
		DBService db=DBService.getInstance();
		Connection conn=db.getConnection();
		Object txn = db.getTransaction();
		if(conn==null){
			log.error("conn is null");
			return 0;
		}
		if(txn==null){
			log.error("txn is null");
			return 0;
		}
		boolean commit=false;
		int id=0;
		try{
//			TM_EX_API_REC_MSG
			TmExApiRecMsgPO apiMsgPOCon=new TmExApiRecMsgPO();
			apiMsgPOCon.setId(apiDBId);
			apiMsgPOCon.setStatus("1");
			
			TmExApiRecMsgPO apiMsgPOValue=new TmExApiRecMsgPO();
			apiMsgPOValue.setUpdateBy(0);
			apiMsgPOValue.setUpdateTime(new Date(System.currentTimeMillis()));
			if(HyLmsClientConstant.MSG_RESULT_SUCCESS.equals(result)){
				apiMsgPOValue.setParseStatus("3");//����ɹ�
			}else{
				apiMsgPOValue.setParseStatus("4");//����ʧ��
			}
			apiMsgPOValue.setParseTime(new Date(System.currentTimeMillis()));
			if(StringUtils.isNotBlank(errorCode)){
				apiMsgPOValue.setErrCode(errorCode);
			}
			if(StringUtils.isNotBlank(errorMsg)){
				apiMsgPOValue.setErrMsg(errorMsg);
			}
			if(StringUtils.isNotBlank(responseData)){
				TmExApiRecMsgResponsePO responsePOCon=new TmExApiRecMsgResponsePO();
				responsePOCon.setStatus("1");
				responsePOCon.setCreateBy(0);
				responsePOCon.setCreateTime(new Date(System.currentTimeMillis()));
				responsePOCon.setId(POFactory.getIntPriKey(conn, responsePOCon));
				responsePOCon.setResponseData(responseData);
				POFactory.insert(conn, responsePOCon);
			}
			POFactory.update(conn, apiMsgPOCon, apiMsgPOValue);
			id=apiMsgPOCon.getId();
			commit=true;
		}catch(Throwable t){
			t.printStackTrace();
			log.error(t);
		}finally{
			db.closeTransaction(txn, commit);
			db.closeConnection(conn);
		}
		return id;
	}
	
}
