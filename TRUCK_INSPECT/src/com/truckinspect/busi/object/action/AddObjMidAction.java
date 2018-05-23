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
import com.truckinspect.busi.object.po.TmInsCheckObjClassMiddlePO;
import com.truckinspect.common.TruckInsCommonCanstant;
import com.truckinspect.common.util.OptLogUtil;
import com.youbus.framework.comm.YBUtility;

/**
 * ������:AddObjMidAction
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2017��8��20�� ����5:52:59
 * �޸ı�ע:
 * @version 1.0.0
 */
public class AddObjMidAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String objMidName=atx.getStringValue("OBJ_MID_NAME");
		String objMidCode=atx.getStringValue("OBJ_MID_CODE");
		String objSupCode=atx.getStringValue("OBJ_SUP_CODE");//һ���������
		String objMidDesc=atx.getStringValue("OBJ_MID_DESC");
		Integer objMidSort=atx.getIntValue("OBJ_MID_SORT",1000);//����˳��
		
		//check param
		if(StringUtils.isBlank(objMidName)||StringUtils.isBlank(objMidCode)||StringUtils.isBlank(objMidDesc)||StringUtils.isBlank(objSupCode)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("BUSI_DATA_OBJ_MID_ADD_ACTION_ERR_1000", "�����������ࣺ����Ϊ��", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		
		Integer optMemberId=YBUtility.getMemberId(atx);
		String optMemberName=YBUtility.getMemberName(atx);
		
//		TM_INS_CHECK_OBJ_CLASS_MIDDLE
		TmInsCheckObjClassMiddlePO objMidPOCon=new TmInsCheckObjClassMiddlePO();
		objMidPOCon.setStatus("1");
		objMidPOCon.setObjClassCode(objMidCode);
		objMidPOCon.setFreezeTag("0");
		
		TmInsCheckObjClassMiddlePO objMidPOResult=POFactory.getByPriKey(conn, objMidPOCon);
		if(objMidPOResult!=null){
			logger.error(" OBJECT MID EXIST ALREADY .");
			atx.setErrorContext("BUSI_DATA_OBJ_MID_ADD_ACTION_ERR_2000", "�����������ࣺ�����������["+objMidCode+"]�Ѿ�����", null);
			return 0;
		}
		objMidPOCon.setObjClassDesc(objMidDesc);
		objMidPOCon.setObjClassLevel(2);
		objMidPOCon.setObjClassName(objMidName);
		objMidPOCon.setSort(objMidSort);
		objMidPOCon.setObjClassFCode(objSupCode);
		
		objMidPOCon.setCreateBy(optMemberId);
		objMidPOCon.setCreateTime(YBUtility.now());
		objMidPOCon.setFreezeTag("0");
		objMidPOCon.setId(POFactory.getIntPriKey(conn, objMidPOCon));
		objMidPOCon.setVer(1);
		
		POFactory.insert(conn, objMidPOCon);
		
		//build optInfo
		OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_OBJMID_ADD, "", "������������");
		
		//set return msg
		atx.setStringValue("MSG", "������������["+objMidCode+"]�ɹ�");
		return 1;
	}

}
