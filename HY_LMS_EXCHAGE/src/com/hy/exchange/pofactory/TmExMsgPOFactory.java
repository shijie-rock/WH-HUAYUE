/**
 * 项目名称:HY_LMS_EXCHAGE
 * 包         名:com.hy.exchange.pofactory
 * 文   件  名:TmExMsgPOFactory.java
 * 创 建日期:2018年10月22日-下午5:16:48
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
import com.hy.exchange.po.TmExMinaBeatLogPO;
import com.hy.exchange.po.TmExNimaMsgPO;
import com.infoservice.framework.services.DBService;
import com.infoservice.po.DynaBean;
import com.infoservice.po.POFactory;
import com.test2.common.HyLmsClientConstant;
import com.test2.dto.BaseRequestParamBean;
import com.test2.dto.BaseResponseBean;
import com.test2.response.parser.HyResponseParserInter;
import com.youbus.framework.comm.remoteclient.ParamBean;
import com.youbus.framework.util.DBConUtil;

/**
 * 类名称:TmExMsgPOFactory
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2018年10月22日 下午5:16:48
 * 修改备注:
 * @version 1.0.0
 */
public class TmExMsgPOFactory extends POFactory {
	private static Logger log = LogManager.getLogger(TmExMsgPOFactory.class);
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
	 * 请求mina消息发送后，记录数据库
	 * 方   法  名:insertReq
	 * 方法描述:
	 * 参         数:@param reqBean
	 * 返   回  值:void
	 * 创   建  人:rock
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
			minaPOCon.setParseStatus("3");//已处理，已发送
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
	 * 插入接收的应答消息
	 * 方   法  名:insertResponse
	 * 方法描述:
	 * 参         数:@param resBean
	 * 参         数:@return
	 * 返   回  值:int
	 * 创   建  人:rock
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
			minaPOCon.setParseStatus("1");//待处理
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
	 * 插入接收的应答消息(需要异步线程发起api请求)
	 * 方   法  名:insertResponse
	 * 方法描述:
	 * 参         数:@param resBean
	 * 参         数:@return
	 * 返   回  值:int
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static int insertNeedParseResponse(BaseResponseBean resBean){
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
			minaPOCon.setParseStatus("2");//处理中
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
	 * 插入api 请求报文，不含应答
	 * 方   法  名:insertApiReqMsg
	 * 方法描述:
	 * 参         数:@param refMinaMsgId
	 * 参         数:@param paramList
	 * 参         数:@return
	 * 返   回  值:int
	 * 创   建  人:rock
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
			apiMsgPOCon.setParseStatus("2");//处理中
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
	 * 方   法  名:updateApiReqMsg
	 * 方法描述:
	 * 参         数:@param refMinaMsgId
	 * 参         数:@param paramList
	 * 参         数:@return
	 * 返   回  值:int
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static int updateApiReqMsg(int apiDBId,String result,String errorCode,String errorMsg,String responseData,String topic){
		
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
				apiMsgPOValue.setParseStatus("3");//处理成功
			}else{
				apiMsgPOValue.setParseStatus("4");//处理失败
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
				
				responsePOCon.setTopic(topic);
				responsePOCon.setParseStatus("1");//待处理
//				responsePOCon.setParseTime(null);
				
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
	
	/**
	 * 仅仅存入rec msg response 表，无rec msg 主表对应。
	 * 用于处理，当mina接收到取消委托单消息时，此表生产一条记录，存入要取消的单号，
	 * 而不再发 http接口去服务端获取整个委托单。
	 * 方   法  名:addRecMsgResponseContent
	 * 方法描述:
	 * 参         数:@param responseData
	 * 参         数:@param topic
	 * 参         数:@return
	 * 返   回  值:int
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static int addRecMsgResponseContent(String responseData,String topic){

		DBService db = DBService.getInstance();
		Connection conn = db.getConnection();
		Object txn = db.getTransaction();
		if (conn == null) {
			log.error("conn is null");
			return 0;
		}
		if (txn == null) {
			log.error("txn is null");
			return 0;
		}
		boolean commit = false;
		int id = 0;
		try {
			TmExApiRecMsgResponsePO responsePOCon = new TmExApiRecMsgResponsePO();
			responsePOCon.setStatus("1");
			responsePOCon.setCreateBy(0);
			responsePOCon.setCreateTime(new Date(System.currentTimeMillis()));
			responsePOCon.setId(POFactory.getIntPriKey(conn, responsePOCon));
			responsePOCon.setResponseData(responseData);

			responsePOCon.setTopic(topic);
			responsePOCon.setParseStatus("1");// 待处理
			// responsePOCon.setParseTime(null);

			POFactory.insert(conn, responsePOCon);
			id=responsePOCon.getId();
			commit = true;
		} catch (Throwable t) {
			t.printStackTrace();
			log.error(t);
		} finally {
			db.closeTransaction(txn, commit);
			db.closeConnection(conn);
		}
		return id;
	}
	
	
	/**
	 * 从数据库中读取TmExNimaMsg 需要处理的数据（parse status=2 or 4）数据，
	 * 方   法  名:parseDBMinaMsg
	 * 方法描述:
	 * 参         数:
	 * 返   回  值:void
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	private static List<DynaBean> queryParseDBMinaMsg(){
		
		DBService db = DBService.getInstance();
		Connection conn = db.getConnection();
		Object txn = db.getTransaction();
		if (conn == null) {
			log.error("conn is null");
			return null;
		}
		if (txn == null) {
			log.error("txn is null");
			return null;
		}
		boolean commit = false;
		
		String sql=" SELECT ID,MSG_ID,TOPIC,PUB_APP_KEY,PUB_TIME,MSG_TYPE,SIGN,"
				+ " REQ_MSG_ID,RESULT,ERR_CODE,ERR_MSG,CONTENT,PARSE_STATUS,PARSE_TIME ,PARSE_COUNT "
				+ " FROM TM_EX_NIMA_MSG "
				+ " WHERE STATUS='1' "
				+ " AND PARSE_STATUS IN ('2','4') "
				+ " AND CONTENT IS NOT NULL "
				+ " AND CONTENT <>'' "
				+ " AND PUB_APP_KEY='99_MessageCenter' "
				+ " AND TOPIC IS NOT NULL "
				+ " AND TOPIC <>'' "
				+ " AND PARSE_COUNT <5"
				+ " AND STATUS='1' "
				+ " ORDER BY ID ASC "
				+ " LIMIT 10 ";
		log.debug("parseDBMinaMsg sql:="+sql);
		try {
			List<DynaBean> list=DBConUtil.getResult(conn, sql, "MINA_MSG_BEAN");
			commit = true;
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e.getMessage());
		} finally {
			db.closeTransaction(txn, commit);
			db.closeConnection(conn);
		}
		
		return null;
	}
	
	/**
	 * schedule 异步处理，需要发api接口的消息
	 * 方   法  名:parseDBMinaMsg
	 * 方法描述:
	 * 参         数:
	 * 返   回  值:void
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static void parseDBMinaMsg(){
		
		List<DynaBean> list=queryParseDBMinaMsg();
		if(list!=null&&list.size()>0){
			for(DynaBean bean:list){
				BaseResponseBean resBean =new BaseResponseBean();
				resBean.setContent(bean.getString("CONTENT"));
				resBean.setErrorCode(bean.getString("ERR_CODE"));
				resBean.setMessageType(bean.getString("MSG_TYPE"));
				resBean.setPublishAppKey(bean.getString("PUB_APP_KEY"));
				resBean.setPublishTime(bean.getString("PUB_TIME"));
				resBean.setRequestMessageId(bean.getString("REQ_MSG_ID"));
				resBean.setResult(bean.getString("RESULT"));
				resBean.setSign(bean.getString("SIGN"));
				resBean.setTopic(bean.getString("TOPIC"));
				resBean.setErrorMessage(bean.getString("ERR_MSG"));
				
//				Object idObj=bean.get("ID");
//				if(idObj instanceof Long)
//				resBean.setId(((Long)idObj).intValue());
//				else{
//					
//				}
				
				int minaMsgId=bean.getInt("ID");
				
				int parseCount=bean.getInt("PARSE_COUNT");
				
				resBean.setId(bean.getString("MSG_ID"));
				
				String msgTopic=resBean.getTopic();
				
				String parserClassName=HyLmsClientConstant.RESPONSE_PARSER_MAP.get(msgTopic);
				logger.debug("parserClassName :="+parserClassName);
				logger.debug("开始处理消息ID["+minaMsgId+"]：="+resBean.toXMLString());
				Class parseClass;
				int parseResult=0;//api 发送处理结果
				try {
					parseClass = Class.forName(parserClassName);
					HyResponseParserInter parser=(HyResponseParserInter)parseClass.newInstance();
					parseResult=parser.parseResponse(resBean);
//				} catch (ClassNotFoundException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//					logger.error(e.getMessage());
//				} catch (InstantiationException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//					logger.error(e.getMessage());
//				} catch (IllegalAccessException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//					logger.error(e.getMessage());
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//					logger.error(e.getMessage());
//				}

				logger.debug((1==parseResult?"[消息处理成功]":"[消息处理失败]")+" 结束处理报文ID["+minaMsgId+"]："+resBean.toXMLString());		
				
				if(1==parseResult){
					logger.debug("消息ID["+minaMsgId+"]处理成功:mina msg更新状态");
					parseSuccess(minaMsgId, parseCount);
					
				}else{
					logger.debug("消息ID["+minaMsgId+"]处理失败:mina msg更新状态");
					parseFaild(minaMsgId, parseCount);
				}
				
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.error(e.getMessage());
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.error(e.getMessage());
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.error(e.getMessage());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.error(e.getMessage());
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.error(e.getMessage());
				}
			}
		}
	}
	
	/**
	 * 处理成功，更新mina表处理状态
	 * 方   法  名:parseSuccess
	 * 方法描述:
	 * 参         数:@param minaMsgId
	 * 参         数:@param parseCount
	 * 返   回  值:void
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	private static void parseSuccess(int minaMsgId,int parseCount){
		TmExNimaMsgPO minaPOCon=new TmExNimaMsgPO();
		minaPOCon.setId(minaMsgId);
		minaPOCon.setStatus("1");
		
		TmExNimaMsgPO minaPOValue=new TmExNimaMsgPO();
		minaPOValue.setUpdateBy(0);
		minaPOValue.setUpdateTime(new Date(System.currentTimeMillis()));
		minaPOValue.setParseStatus("3");
		minaPOValue.setParseTime(new Date(System.currentTimeMillis()));
		minaPOValue.setParseCount(parseCount+1);
		
		DBService db = DBService.getInstance();
		Connection conn = db.getConnection();
		Object txn = db.getTransaction();
		if (conn == null) {
			log.error("conn is null");
			return ;
		}
		if (txn == null) {
			log.error("txn is null");
			return ;
		}
		boolean commit = false;
		POFactory.update(conn, minaPOCon, minaPOValue);
		commit = true;
		db.closeTransaction(txn, commit);
		db.closeConnection(conn);
		
	}
	
	/**
	 * 处理失败，更新mina处理状态
	 * 方   法  名:parseFaild
	 * 方法描述:
	 * 参         数:@param minaMsgId
	 * 参         数:@param parseCount
	 * 返   回  值:void
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	private static void parseFaild(int minaMsgId,int parseCount){
		TmExNimaMsgPO minaPOCon=new TmExNimaMsgPO();
		minaPOCon.setId(minaMsgId);
		minaPOCon.setStatus("1");
		
		TmExNimaMsgPO minaPOValue=new TmExNimaMsgPO();
		minaPOValue.setUpdateBy(0);
		minaPOValue.setUpdateTime(new Date(System.currentTimeMillis()));
		minaPOValue.setParseStatus("4");
		minaPOValue.setParseTime(new Date(System.currentTimeMillis()));
		minaPOValue.setParseCount(parseCount+1);
		
		DBService db = DBService.getInstance();
		Connection conn = db.getConnection();
		Object txn = db.getTransaction();
		if (conn == null) {
			log.error("conn is null");
			return ;
		}
		if (txn == null) {
			log.error("txn is null");
			return ;
		}
		boolean commit = false;
		POFactory.update(conn, minaPOCon, minaPOValue);
		commit = true;
		db.closeTransaction(txn, commit);
		db.closeConnection(conn);
		
	}
	
	
	/**
	 * 记录心跳日志（超时）
	 * 方   法  名:recordMinaBeatLog
	 * 方法描述:
	 * 参         数:@param logContent
	 * 返   回  值:void
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static void recordMinaBeatLog(String logContent){
		
		DBService db = DBService.getInstance();
		Connection conn = db.getConnection();
		Object txn = db.getTransaction();
		if (conn == null) {
			log.error("conn is null");
			return ;
		}
		if (txn == null) {
			log.error("txn is null");
			return ;
		}
		boolean commit = false;
		
		TmExMinaBeatLogPO logPOCon=new TmExMinaBeatLogPO();
		logPOCon.setBeatConnStatus(logContent);
		logPOCon.setCreateBy(0);
		logPOCon.setCreateTime(new Date(System.currentTimeMillis()));
		logPOCon.setId(POFactory.getIntPriKey(conn, logPOCon));
		logPOCon.setRecordTime(new Date(System.currentTimeMillis()));
		logPOCon.setStatus("1");
		
		POFactory.insert(conn, logPOCon);
		commit = true;
		db.closeTransaction(txn, commit);
		db.closeConnection(conn);
		
	}
	
}
