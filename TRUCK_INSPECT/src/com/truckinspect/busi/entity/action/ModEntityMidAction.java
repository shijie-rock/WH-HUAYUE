/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.base.action
 * 文   件  名:ModInsPositionAction.java
 * 创 建日期:2017年8月20日-下午5:53:32
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.busi.entity.action;

import java.io.IOException;
import java.net.URI;
import java.sql.Connection;

import org.apache.commons.lang.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.POFactory;
import com.truckinspect.busi.entity.po.TmInsCheckEntityMiddlePO;
import com.truckinspect.busi.entity.po.TmInsCheckEntitySubPO;
import com.truckinspect.common.TruckInsCommonCanstant;
import com.truckinspect.common.util.OptLogUtil;
import com.youbus.framework.comm.YBUtility;
import com.youbus.framework.comm.remoteclient.SSLClient;

/**
 * 类名称:ModEntityMidAction
 * 类描述:停用、启用、删除等操作
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年8月20日 下午5:53:32
 * 修改备注:
 * @version 1.0.0
 */
public class ModEntityMidAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		Integer entMidId=atx.getIntValue("ENT_MID_ID",0);
		
		String optType=atx.getStringValue("OPT_TYPE");//stop;start;delete
		
		//check param
		if(0==entMidId||StringUtils.isBlank(optType)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("BUSI_DATA_ENT_MID_MOD_ACTION_ERR_1000", "处理检查对象中类：参数为空", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		//check exist
//		TM_INS_CHECK_ENTITY_MIDDLE
		TmInsCheckEntityMiddlePO entMidPOCon=new TmInsCheckEntityMiddlePO();
		entMidPOCon.setStatus("1");
		entMidPOCon.setId(entMidId);
		
		TmInsCheckEntityMiddlePO entMidPOResult=POFactory.getByPriKey(conn, entMidPOCon);
		
		if(entMidPOResult==null){
			logger.error(" ENT_MID NOT EXIST .");
			atx.setErrorContext("BUSI_DATA_ENT_MID_MOD_ACTION_ERR_2000", "处理检查对象中类：检查对象中类不存在", null);
			return 0;
		}
		
		int version=entMidPOResult.getVer();
		entMidPOCon.setVer(version);
		
		TmInsCheckEntityMiddlePO entMidPOValue=new TmInsCheckEntityMiddlePO();
		entMidPOValue.setUpdateBy(YBUtility.getMemberId(atx));
		entMidPOValue.setUpdateTime(YBUtility.now());
		entMidPOValue.setVer(version+1);
		
		String optTypeDesc="";
		String optTypeCode="";
		if("stop".equals(optType)){
			if("1".equals(entMidPOResult.getFreezeTag())){
				logger.error(" ENT_MID IS STOPED ALREADY .");
				atx.setErrorContext("BUSI_DATA_ENT_MID_MOD_ACTION_ERR_3001", "处理检查对象中类：检查对象中类已经是停用状态", null);
				return 0;
			}
			
//			TM_INS_CHECK_ENTITY_SUB
			TmInsCheckEntitySubPO entSubPOCon=new TmInsCheckEntitySubPO();
			entSubPOCon.setStatus("1");
			entSubPOCon.setFreezeTag("0");
			entSubPOCon.setCheckEntFCode(entMidPOResult.getCheckEntCode());
			
			if(POFactory.getByPriKey(conn, entSubPOCon)!=null){
				logger.error(" EXIST UN FREEZE CHILD DATA.");
				atx.setErrorContext("BUSI_DATA_ENT_MID_MOD_ACTION_ERR_3020", "处理检查对象中类：存在未停用的检查对象小类", null);
				return 0;
			}
			
			//BUSI
			optTypeDesc="停用";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_ENTMID_UNV;
			entMidPOValue.setFreezeTag("1"); 
			
		}else if("start".equals(optType)){
			
			if("0".equals(entMidPOResult.getFreezeTag())){
				logger.error(" ENT_MID IS START ALREADY .");
				atx.setErrorContext("BUSI_DATA_ENT_MID_MOD_ACTION_ERR_3002", "处理检查对象中类：检查对象中类已经是启用状态", null);
				return 0;
			}
			//检查是否有有效的相同代码的检查对象中类
//			TM_INS_CHECK_ENTITY_MIDDLE
			TmInsCheckEntityMiddlePO entMidPOConTemp=new TmInsCheckEntityMiddlePO();
			entMidPOConTemp.setStatus("1");
			entMidPOConTemp.setFreezeTag("0");
			entMidPOConTemp.setCheckEntCode(entMidPOResult.getCheckEntCode());
			
			TmInsCheckEntityMiddlePO entMidPOResultTemp=POFactory.getByPriKey(conn, entMidPOConTemp);
			
			if(entMidPOResultTemp!=null){
				logger.error(" ENT_MID SAME ENT_CODE EXIST .");
				atx.setErrorContext("BUSI_DATA_ENT_MID_MOD_ACTION_ERR_3003", "处理检查对象中类：存在相同编码检查对象中类", null);
				return 0;
			}
			
			//BUSI
			optTypeDesc="启用";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_ENTMID_VAL;
			entMidPOValue.setFreezeTag("0");
			
		}else if("delete".equals(optType)){
			
//			TM_INS_CHECK_ENTITY_SUB
			TmInsCheckEntitySubPO entSubPOCon=new TmInsCheckEntitySubPO();
			entSubPOCon.setStatus("1");
			entSubPOCon.setCheckEntFCode(entMidPOResult.getCheckEntCode());
			
			if(POFactory.getByPriKey(conn, entSubPOCon)!=null){
				logger.error(" EXIST CHILD DATA.");
				atx.setErrorContext("BUSI_DATA_ENT_MID_MOD_ACTION_ERR_3030", "处理检查对象中类：存在未删除的检查对象小类", null);
				return 0;
			}
			
			//BUSI
			optTypeDesc="删除";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_ENTMID_DEL;
			entMidPOValue.setStatus("0");
			//clear employee  ins_group ，clear ins_group check item
			
		}else{
			
			logger.error(" NOT SUPPORT OPT_TYPE["+optType+"] .");
			atx.setErrorContext("BUSI_DATA_ENT_MID_MOD_ACTION_ERR_3010", "处理检查对象中类：不支持操作类型["+optType+"]", null);
			return 0;
		}
		
		int excuteResult=POFactory.update(conn, entMidPOCon,entMidPOValue);
		
		if(excuteResult<1){
			logger.error(" DATA["+entMidPOResult.getCheckEntCode()+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER .");
			atx.setErrorContext("BUSI_DATA_ENT_MID_MOD_ACTION_ERR_8000", "处理检查对象中类：数据["+entMidPOResult.getCheckEntCode()+"]已变更，无法完成操作，请稍后重试", null);
//			atx.setErrorContext("ORGANIZE_INS_MOD_ACTION_ERR_00040", "DATA["+roleCode+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER", null);
			return 0;
		}
		
		OptLogUtil.bindOptContext(atx,optTypeCode , "", "检查对象中类["+entMidPOResult.getCheckEntCode()+"]"+optTypeDesc);
		
		atx.setStringValue("MSG", "检查对象中类["+entMidPOResult.getCheckEntCode()+"]"+optTypeDesc+"处理成功。");
		return 1;
		
	}
}
