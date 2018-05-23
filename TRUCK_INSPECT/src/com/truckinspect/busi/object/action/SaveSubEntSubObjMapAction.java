/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.busi.object.action
 * ��   ��  ��:SaveSubEntSubObjMapAction.java
 * �� ������:2018��2��24��-����11:19:49
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
 * ������:SaveSubEntSubObjMapAction
 * ������:�������������������Ŀ�����Ӧ��ϵ
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2018��2��24�� ����11:19:49
 * �޸ı�ע:
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
		String entSubCode=atx.getStringValue("ENT_SUB_CODE");//������С��
		String subObjStr=atx.getStringValue("SUB_OBJ_STR");//"xxx,xxx",�����ĿС��
		
		//check param
		if(StringUtils.isBlank(entSubCode)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("SAVE_SUB_ENT_SUB_OBJ_MAP_ACTION_ERR_1000", "�༭�����������Ӧ�����Ŀ������Ϊ��", null);
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
		OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_ENTOBJ_VAL, "", "�༭�����������Ӧ�����Ŀ["+entSubCode+"]");
		
		//set return msg
		atx.setStringValue("MSG", "�༭�����������Ӧ�����Ŀ["+entSubCode+"]");
		
		return 1;
	}

}
