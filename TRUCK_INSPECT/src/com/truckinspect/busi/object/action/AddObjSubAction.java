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
import com.truckinspect.busi.object.po.TmInsCheckObjClassSubPO;
import com.truckinspect.common.TruckInsCommonCanstant;
import com.truckinspect.common.util.OptLogUtil;
import com.youbus.framework.comm.YBUtility;

/**
 * ������:AddObjSubAction
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2017��8��20�� ����5:52:59
 * �޸ı�ע:
 * @version 1.0.0
 */
public class AddObjSubAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String objSubName=atx.getStringValue("OBJ_SUB_NAME");
		String objSubCode=atx.getStringValue("OBJ_SUB_CODE");
		String objMidCode=atx.getStringValue("OBJ_MID_CODE");//�����������
		String objSubDesc=atx.getStringValue("OBJ_SUB_DESC");
		Integer objSubSort=atx.getIntValue("OBJ_SUB_SORT",1000);//����˳��
		
		//check param
		if(StringUtils.isBlank(objSubName)||StringUtils.isBlank(objSubCode)||StringUtils.isBlank(objSubDesc)||StringUtils.isBlank(objMidCode)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("BUSI_DATA_OBJ_SUB_ADD_ACTION_ERR_1000", "�����������ࣺ����Ϊ��", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		
		Integer optMemberId=YBUtility.getMemberId(atx);
		String optMemberName=YBUtility.getMemberName(atx);
		
//		TM_INS_CHECK_OBJ_CLASS_SUB
		TmInsCheckObjClassSubPO objSubPOCon=new TmInsCheckObjClassSubPO();
		objSubPOCon.setStatus("1");
		objSubPOCon.setObjClassCode(objSubCode);
		objSubPOCon.setFreezeTag("0");
		
		TmInsCheckObjClassSubPO objSubPOResult=POFactory.getByPriKey(conn, objSubPOCon);
		if(objSubPOResult!=null){
			logger.error(" OBJECT SUB EXIST ALREADY .");
			atx.setErrorContext("BUSI_DATA_OBJ_SUB_ADD_ACTION_ERR_2000", "�����������ࣺ�����������["+objSubCode+"]�Ѿ�����", null);
			return 0;
		}
		objSubPOCon.setObjClassDesc(objSubDesc);
		objSubPOCon.setObjClassLevel(3);
		objSubPOCon.setObjClassName(objSubName);
		objSubPOCon.setSort(objSubSort);
		objSubPOCon.setObjClassFCode(objMidCode);
		
		objSubPOCon.setCreateBy(optMemberId);
		objSubPOCon.setCreateTime(YBUtility.now());
		objSubPOCon.setFreezeTag("0");
		objSubPOCon.setId(POFactory.getIntPriKey(conn, objSubPOCon));
		objSubPOCon.setVer(1);
		
		POFactory.insert(conn, objSubPOCon);
		
		//build optInfo
		OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_OBJSUB_ADD, "", "������������");
		
		//set return msg
		atx.setStringValue("MSG", "������������["+objSubCode+"]�ɹ�");
		return 1;
	}

}
