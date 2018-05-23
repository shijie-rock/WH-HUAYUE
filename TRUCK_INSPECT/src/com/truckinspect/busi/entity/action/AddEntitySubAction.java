/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.busi.base.action
 * ��   ��  ��:AddInsPositionAction.java
 * �� ������:2017��8��20��-����5:52:59
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.busi.entity.action;

import java.sql.Connection;

import org.apache.commons.lang.StringUtils;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.POFactory;
import com.truckinspect.busi.entity.po.TmInsCheckEntitySubPO;
import com.truckinspect.busi.object.po.TmInsCheckObjClassMiddlePO;
import com.truckinspect.busi.object.po.TmInsCheckObjClassSubPO;
import com.truckinspect.busi.object.po.TmInsCheckObjClassSupperPO;
import com.truckinspect.common.TruckInsCommonCanstant;
import com.truckinspect.common.util.OptLogUtil;
import com.youbus.framework.comm.YBUtility;

/**
 * ������:AddEntitySubAction
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2017��8��20�� ����5:52:59
 * �޸ı�ע:
 * @version 1.0.0
 */
public class AddEntitySubAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String entSubName=atx.getStringValue("ENT_SUB_NAME");
		String entSubCode=atx.getStringValue("ENT_SUB_CODE");
		String entMidCode=atx.getStringValue("ENT_MID_CODE");//һ���������
		String entSubDesc=atx.getStringValue("ENT_SUB_DESC");
		Integer entSubSort=atx.getIntValue("ENT_SUB_SORT",1000);//����˳��
		
		//check param
		if(StringUtils.isBlank(entSubName)||StringUtils.isBlank(entSubCode)||StringUtils.isBlank(entSubDesc)||StringUtils.isBlank(entMidCode)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("BUSI_DATA_ENT_SUB_ADD_ACTION_ERR_1000", "����������С�ࣺ����Ϊ��", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		
		Integer optMemberId=YBUtility.getMemberId(atx);
		String optMemberName=YBUtility.getMemberName(atx);
		
//		TM_INS_CHECK_ENTITY_SUB
		TmInsCheckEntitySubPO entSubPOCon=new TmInsCheckEntitySubPO();
		entSubPOCon.setStatus("1");
		entSubPOCon.setCheckEntCode(entSubCode);
		entSubPOCon.setFreezeTag("0");
		
		TmInsCheckEntitySubPO entSubPOResult=POFactory.getByPriKey(conn, entSubPOCon);
		if(entSubPOResult!=null){
			logger.error(" ENTITY SUB EXIST ALREADY .");
			atx.setErrorContext("BUSI_DATA_ENT_SUB_ADD_ACTION_ERR_2000", "����������С�ࣺ������С�����["+entSubCode+"]�Ѿ�����", null);
			return 0;
		}
		entSubPOCon.setCheckEntDesc(entSubDesc);
		entSubPOCon.setCheckEntLevel(3);
		entSubPOCon.setCheckEntName(entSubName);
		entSubPOCon.setSort(entSubSort);
		entSubPOCon.setCheckEntFCode(entMidCode);
		
		entSubPOCon.setCreateBy(optMemberId);
		entSubPOCon.setCreateTime(YBUtility.now());
		entSubPOCon.setFreezeTag("0");
		entSubPOCon.setId(POFactory.getIntPriKey(conn, entSubPOCon));
		entSubPOCon.setVer(1);
		
		POFactory.insert(conn, entSubPOCon);
		
		//TM_INS_CHECK_OBJ_CLASS_SUB
		//@20180223 �Զ����� ������Ŀ��
		//entSupCode
		String msg="";
		TmInsCheckObjClassSubPO objSubPOCon=new TmInsCheckObjClassSubPO();
		objSubPOCon.setStatus("1");
		objSubPOCon.setCheckEntSubCode(entSubCode);
		
		TmInsCheckObjClassSubPO objSubPOResult=POFactory.getByPriKey(conn, objSubPOCon);
		if(objSubPOResult!=null){
			logger.error(" OBJECT SUB EXIST ALREADY .NO NEED ADD .");
		}else{
			TmInsCheckObjClassMiddlePO objMidPOCon=new TmInsCheckObjClassMiddlePO();
			objMidPOCon.setStatus("1");
			objMidPOCon.setCheckEntMidCode(entMidCode);
			objMidPOCon=POFactory.getByPriKey(conn, objMidPOCon);
			if(objMidPOCon!=null&&StringUtils.isNotBlank(objMidPOCon.getObjClassCode())){
				objSubPOCon.setObjClassFCode(objMidPOCon.getObjClassCode());//�����ϼ��ࣨ����������
			}
			
			objSubPOCon.setObjClassCode(entSubCode);
			objSubPOCon.setObjClassDesc(entSubDesc);
			objSubPOCon.setObjClassLevel(3);
			objSubPOCon.setObjClassName(entSubName);
			objSubPOCon.setSort(entSubSort);
			
			objSubPOCon.setCreateBy(optMemberId);
			objSubPOCon.setCreateTime(YBUtility.now());
			objSubPOCon.setFreezeTag("0");
			objSubPOCon.setId(POFactory.getIntPriKey(conn, objSubPOCon));
			objSubPOCon.setVer(1);
			objSubPOCon.setRemark("�Զ�������Ŀ��������");
			
			POFactory.insert(conn, objSubPOCon);
			logger.warn(" OBJECT SUB AUTO ADD .");
			msg=",���Զ�������Ŀ��������";
		}
		
		//build optInfo
		OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_ENTSUB_ADD, "", "����������С��"+msg);
		
		//set return msg
		atx.setStringValue("MSG", "����������С��["+entSubCode+"]�ɹ�");
		return 1;
	}

}
