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
import com.truckinspect.busi.entity.po.TmInsCheckEntityMiddlePO;
import com.truckinspect.busi.object.po.TmInsCheckObjClassMiddlePO;
import com.truckinspect.busi.object.po.TmInsCheckObjClassSupperPO;
import com.truckinspect.common.TruckInsCommonCanstant;
import com.truckinspect.common.util.OptLogUtil;
import com.youbus.framework.comm.YBUtility;

/**
 * ������:AddEntityMidAction
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2017��8��20�� ����5:52:59
 * �޸ı�ע:
 * @version 1.0.0
 */
public class AddEntityMidAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String entMidName=atx.getStringValue("ENT_MID_NAME");
		String entMidCode=atx.getStringValue("ENT_MID_CODE");
		String entSupCode=atx.getStringValue("ENT_SUP_CODE");//һ���������
		String entMidDesc=atx.getStringValue("ENT_MID_DESC");
		Integer entMidSort=atx.getIntValue("ENT_MID_SORT",1000);//����˳��
		
		//check param
		if(StringUtils.isBlank(entMidName)||StringUtils.isBlank(entMidCode)||StringUtils.isBlank(entMidDesc)||StringUtils.isBlank(entSupCode)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("BUSI_DATA_ENT_MID_ADD_ACTION_ERR_1000", "�������������ࣺ����Ϊ��", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		
		Integer optMemberId=YBUtility.getMemberId(atx);
		String optMemberName=YBUtility.getMemberName(atx);
		
//		TM_INS_CHECK_ENTITY_MIDDLE
		TmInsCheckEntityMiddlePO entMidPOCon=new TmInsCheckEntityMiddlePO();
		entMidPOCon.setStatus("1");
		entMidPOCon.setCheckEntCode(entMidCode);
		entMidPOCon.setFreezeTag("0");
		
		TmInsCheckEntityMiddlePO entMidPOResult=POFactory.getByPriKey(conn, entMidPOCon);
		if(entMidPOResult!=null){
			logger.error(" ENTITY MID EXIST ALREADY .");
			atx.setErrorContext("BUSI_DATA_ENT_MID_ADD_ACTION_ERR_2000", "�������������ࣺ�������������["+entMidCode+"]�Ѿ�����", null);
			return 0;
		}
		entMidPOCon.setCheckEntDesc(entMidDesc);
		entMidPOCon.setCheckEntLevel(2);
		entMidPOCon.setCheckEntName(entMidName);
		entMidPOCon.setSort(entMidSort);
		entMidPOCon.setCheckEntFCode(entSupCode);
		
		entMidPOCon.setCreateBy(optMemberId);
		entMidPOCon.setCreateTime(YBUtility.now());
		entMidPOCon.setFreezeTag("0");
		entMidPOCon.setId(POFactory.getIntPriKey(conn, entMidPOCon));
		entMidPOCon.setVer(1);
		
		POFactory.insert(conn, entMidPOCon);
		//TM_INS_CHECK_OBJ_CLASS_MIDDLE
		//@20180223 �Զ����� ������Ŀ��
		//entSupCode
		String msg="";
		TmInsCheckObjClassMiddlePO objMidPOCon=new TmInsCheckObjClassMiddlePO();
		objMidPOCon.setStatus("1");
		objMidPOCon.setCheckEntMidCode(entMidCode);
		
		TmInsCheckObjClassMiddlePO objMidPOResult=POFactory.getByPriKey(conn, objMidPOCon);
		if(objMidPOResult!=null){
			logger.warn(" OBJECT MID EXIST ALREADY .NO NEED ADD.");
		}else{
			//entSupCode ����ent sup code ��ѯ obj sup code
			TmInsCheckObjClassSupperPO objSupPOCon=new TmInsCheckObjClassSupperPO();
			objSupPOCon.setStatus("1");
			objSupPOCon.setCheckEntSupCode(entSupCode);
			objSupPOCon=POFactory.getByPriKey(conn, objSupPOCon);
			if(objSupPOCon!=null&&StringUtils.isNotBlank(objSupPOCon.getObjClassCode())){
				objMidPOCon.setObjClassFCode(objSupPOCon.getObjClassCode());//�����ϼ���(һ��)����
			}
			
			objMidPOCon.setObjClassCode(entMidCode);//δ���� �����ظ����� TODO
			objMidPOCon.setObjClassDesc(entMidDesc);
			objMidPOCon.setObjClassLevel(2);
			objMidPOCon.setObjClassName(entMidName);
			objMidPOCon.setSort(entMidSort);
			
			objMidPOCon.setCreateBy(optMemberId);
			objMidPOCon.setCreateTime(YBUtility.now());
			objMidPOCon.setFreezeTag("0");
			objMidPOCon.setId(POFactory.getIntPriKey(conn, objMidPOCon));
			objMidPOCon.setVer(1);
			objMidPOCon.setRemark("�Զ�������Ŀ��������");
			
			POFactory.insert(conn, objMidPOCon);
			logger.warn(" OBJECT MID AUTO ADD .");
			msg=",���Զ�������Ŀ��������";
		}
		
		//build optInfo
		OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_ENTMID_ADD, "", "��������������"+msg);
		
		//set return msg
		atx.setStringValue("MSG", "��������������["+entMidCode+"]�ɹ�");
		return 1;
	}

}
