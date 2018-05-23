/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.object.action
 * 文   件  名:SaveSubEntSubObjMapAction.java
 * 创 建日期:2018年2月24日-下午11:19:49
 * Copyright @ 2018-YouBus.com.cn
 */
package com.truckinspect.busi.object.action;

import java.sql.Connection;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.POFactory;
import com.truckinspect.busi.entity.po.TtInsCheckEntityObjSubMapPO;
import com.truckinspect.common.TruckInsCommonCanstant;
import com.truckinspect.common.util.OptLogUtil;
import com.youbus.framework.comm.YBUtility;

/**
 * 类名称:SaveSubEntSubObjMapAction
 * 类描述:保存检查对象子类与检查项目子类对应关系
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2018年2月24日 下午11:19:49
 * 修改备注:
 * @version 1.0.0
 */
public class SaveSubEntSubObjMapAction extends ActionImpl {
	private static Logger log = LogManager.getLogger(SaveSubEntSubObjMapAction.class);
	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String entSubCode=atx.getStringValue("ENT_SUB_CODE");//检查对象小类
		String subObjStr=atx.getStringValue("SUB_OBJ_STR");//"xxx,xxx",检查项目小类
		
		//check param
		if(StringUtils.isBlank(entSubCode)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("SAVE_SUB_ENT_SUB_OBJ_MAP_ACTION_ERR_1000", "编辑检查对象子类对应检查项目：参数为空", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		
		Integer optMemberId=YBUtility.getMemberId(atx);
//		String optMemberName=YBUtility.getMemberName(atx);
		
//		TT_INS_CHECK_ENTITY_OBJ_SUB_MAP
		TtInsCheckEntityObjSubMapPO mapCon=new TtInsCheckEntityObjSubMapPO();
		mapCon.setStatus("1");
		mapCon.setCheckEntSubCode(entSubCode);
		
		TtInsCheckEntityObjSubMapPO mapValue=new TtInsCheckEntityObjSubMapPO();
		mapValue.setStatus("0");
		mapValue.setUpdateBy(optMemberId);
		mapValue.setUpdateTime(YBUtility.now());
		
		POFactory.update(conn, mapCon, mapValue);
		
		if(StringUtils.isNotBlank(subObjStr)){
			
			mapCon.setCreateBy(optMemberId);
			mapCon.setCreateTime(YBUtility.now());
			mapCon.setFreezeTag("0");
			mapCon.setStatus("1");
			mapCon.setVer(1);
			
			for(String subObjCode:subObjStr.split(",")){
				mapCon.setId(POFactory.getIntPriKey(conn, mapCon));
				mapCon.setObjClassSubCode(subObjCode);
				
				POFactory.insert(conn, mapCon);
			}
		}
		//build optInfo
		OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_ENTOBJ_VAL, "", "编辑检查对象子类对应检查项目["+entSubCode+"]");
		
		//set return msg
		atx.setStringValue("MSG", "编辑检查对象子类对应检查项目["+entSubCode+"]");
		
		return 1;
	}

}
