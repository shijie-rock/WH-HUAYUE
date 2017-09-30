/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.common.action
 * 文   件  名:OptLogRecordAction.java
 * 创 建日期:2017年8月16日-上午11:28:13
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.common.action;

import java.sql.Connection;

import org.apache.commons.lang.StringUtils;

import com.info.base.po.TbOptLogsPO;
import com.info.base.po.TsDataDicPO;
import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.Framework;
import com.infoservice.framework.conf.ActionInfo;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.POFactory;
import com.truckinspect.common.TruckInsCommonCanstant;
import com.truckinspect.common.bean.OptLogDTO;
import com.youbus.framework.comm.TruckInsNativeCacheUtil;
import com.youbus.framework.comm.YBUtility;

/**
 * 类名称:OptLogRecordAction
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年8月16日 上午11:28:14
 * 修改备注:
 * @version 1.0.0
 */
public class OptLogRecordAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		try{ //log 记录不影响业务功能
			Connection conn=atx.getConnection();
			
			OptLogDTO logBean=(OptLogDTO) atx.getObjValue(TruckInsCommonCanstant.CONTEXT_OPT_TAG);
			Integer optMemberId=YBUtility.getMemberId(atx);
			String optMemberName=YBUtility.getMemberName(atx);
			
			TbOptLogsPO logPOCon=new TbOptLogsPO();
			logPOCon.setStatus("1");
			logPOCon.setId(POFactory.getIntPriKey(conn, logPOCon));
			logPOCon.setCreateBy(optMemberId);
			logPOCon.setCreateTime(YBUtility.now());
			logPOCon.setOptTime(YBUtility.now());
			
			logPOCon.setOptMemberId(optMemberId);
			logPOCon.setOptName(optMemberName);
			
			String actionId=atx.getActionID();
			String actionDesc="";
			if(StringUtils.isNotBlank(actionId)){
				ActionInfo actionInfo= Framework.getActionRepository().getActionDef(actionId);
				if(actionInfo!=null){
					actionDesc=actionInfo.getDecription();
				}
			}
	
			logPOCon.setOptActionCode(actionId);
			logPOCon.setOptActionDesc(actionDesc);
			
	//		DMSDataContext dataCtx=atx.getDmsDataContext();
			String atxContent=atx.toString(); //参数表
			logger.debug(" action context : = "+atxContent);
			//not insert into DB
			
			if(logBean!=null){
				logPOCon.setOptContent(logBean.getOptContent());
				logPOCon.setOptTypeCode(logBean.getOptTypeCode());
	
				String optTypeDesc=logBean.getOptTypeDesc();
				if(StringUtils.isBlank(optTypeDesc)){
					if(StringUtils.isNotBlank(logBean.getOptTypeCode())){
						TsDataDicPO dicPOResult=TruckInsNativeCacheUtil.getCacheDataDic(logBean.getOptTypeCode());
						if(dicPOResult!=null)optTypeDesc=dicPOResult.getCodeDesc();
					}
				}
				logPOCon.setOptTypeDesc(optTypeDesc);
			}
			POFactory.insert(conn, logPOCon);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		catch(Throwable t){
			t.printStackTrace();
			logger.error(t.getMessage());
		}
		return 1;
	}

}
