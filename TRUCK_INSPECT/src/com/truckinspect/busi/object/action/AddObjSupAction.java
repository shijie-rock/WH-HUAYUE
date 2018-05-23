/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.busi.base.action
 * ��   ��  ��:AddInsPositionAction.java
 * �� ������:2017��8��20��-����5:52:59
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.busi.object.action;

import java.sql.Connection;

import org.apache.commons.lang.StringUtils;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.POFactory;
import com.truckinspect.busi.object.po.TmInsCheckObjClassSupperPO;
import com.truckinspect.common.TruckInsCommonCanstant;
import com.truckinspect.common.util.OptLogUtil;
import com.youbus.framework.comm.YBUtility;

/**
 * ������:AddObjSupAction
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2017��8��20�� ����5:52:59
 * �޸ı�ע:
 * @version 1.0.0
 */
public class AddObjSupAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String objSupName=atx.getStringValue("OBJ_SUP_NAME");
		String objSupCode=atx.getStringValue("OBJ_SUP_CODE");
		String objSupDesc=atx.getStringValue("OBJ_SUP_DESC");
		Integer objSupSort=atx.getIntValue("OBJ_SUP_SORT",1000);//����˳��
		
		//check param
		if(StringUtils.isBlank(objSupName)||StringUtils.isBlank(objSupCode)||StringUtils.isBlank(objSupDesc)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("BUSI_DATA_OBJ_SUP_ADD_ACTION_ERR_1000", "����һ�����ࣺ����Ϊ��", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		
		Integer optMemberId=YBUtility.getMemberId(atx);
		String optMemberName=YBUtility.getMemberName(atx);
		
//		TM_INS_CHECK_OBJ_CLASS_SUPPER
		TmInsCheckObjClassSupperPO objSupPOCon=new TmInsCheckObjClassSupperPO();
		objSupPOCon.setStatus("1");
		objSupPOCon.setObjClassCode(objSupCode);
		objSupPOCon.setFreezeTag("0");
		
		TmInsCheckObjClassSupperPO objSupPOResult=POFactory.getByPriKey(conn, objSupPOCon);
		if(objSupPOResult!=null){
			logger.error(" OBJECT SUP EXIST ALREADY .");
			atx.setErrorContext("BUSI_DATA_OBJ_SUP_ADD_ACTION_ERR_2000", "����һ�����ࣺһ���������["+objSupCode+"]�Ѿ�����", null);
			return 0;
		}
		objSupPOCon.setObjClassDesc(objSupDesc);
		objSupPOCon.setObjClassLevel(1);
		objSupPOCon.setObjClassName(objSupName);
		objSupPOCon.setSort(objSupSort);
		
		objSupPOCon.setCreateBy(optMemberId);
		objSupPOCon.setCreateTime(YBUtility.now());
		objSupPOCon.setFreezeTag("0");
		objSupPOCon.setId(POFactory.getIntPriKey(conn, objSupPOCon));
		objSupPOCon.setVer(1);
		
		POFactory.insert(conn, objSupPOCon);
		
		//build optInfo
		OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_OBJSUP_ADD, "", "����һ������");
		
		//set return msg
		atx.setStringValue("MSG", "����һ������["+objSupCode+"]�ɹ�");
		return 1;
	}

}
