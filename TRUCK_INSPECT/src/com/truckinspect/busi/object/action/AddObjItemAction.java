/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.busi.object.action
 * ��   ��  ��:AddObjItemAction.java
 * �� ������:2018��3��12��-����6:48:12
 * Copyright @ 2018-YouBus.com.cn
 */
package com.truckinspect.busi.object.action;

import java.sql.Connection;

import org.apache.commons.lang.StringUtils;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.DynaBean;
import com.infoservice.po.POFactory;
import com.truckinspect.busi.object.po.TmInsCheckObjItemPO;
import com.truckinspect.busi.object.pofactory.TmInsCheckObjClassPOFactory;
import com.truckinspect.common.TruckInsCommonCanstant;
import com.truckinspect.common.util.OptLogUtil;
import com.youbus.framework.comm.YBUtility;

/**
 * ������:AddObjItemAction
 * ������:���������Ŀ��ϸ
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2018��3��12�� ����6:48:12
 * �޸ı�ע:
 * @version 1.0.0
 */
public class AddObjItemAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String itemCode=atx.getStringValue("ITEM_CODE");
		String itemName=atx.getStringValue("ITEM_NAME");
		String itemDesc=atx.getStringValue("ITEM_DESC");
		String itemFCode=atx.getStringValue("ITEM_F_CODE");
		
		Integer itemSort=atx.getIntValue("ITEM_SORT",1000);
		
		String itemCheckWay=atx.getStringValue("ITEM_CHECK_WAY");//��鷽ʽ
		Integer itemCheckFrequency=atx.getIntValue("ITEM_CHECK_FREQUENCY",1);//���Ƶ��
		String itemCheckComliance=atx.getStringValue("ITEM_CHECK_COMLIANCE");//����׼
		
		String itemSupCode=atx.getStringValue("ITEM_SUP_CODE");//һ������
		String itemMidCode=atx.getStringValue("ITEM_MID_CODE");//��������
		String itemSubCode=atx.getStringValue("ITEM_SUB_CODE");//��������
		
		String itemPhoto=atx.getStringValue("ITEM_PHOTO");//�Ƿ�����
		String itemCheckMomentStr=atx.getStringValue("ITEM_CHECK_MOMENT_STR");//���ʱ�䣺1��ǰ��2���У�3��
		
		//check param
		if(StringUtils.isBlank(itemCode)||StringUtils.isBlank(itemName)){
			logger.error(" PARAM IS EMTPY 1 .");
			atx.setErrorContext("BUSI_DATA_OBJ_ITEM_ADD_ACTION_ERR_1000", "���������Ŀ�������Ŀ����ͼ����Ŀ���ƶ�����Ϊ��", null);
			return 0;
		}
		if((StringUtils.isBlank(itemSubCode)&&StringUtils.isBlank(itemFCode))){
			logger.error(" PARAM IS EMTPY 2 .");
			atx.setErrorContext("BUSI_DATA_OBJ_ITEM_ADD_ACTION_ERR_1001", "���������Ŀ�������Ŀ�ϼ�����ͼ������������벻��ͬʱΪ��", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		
		Integer optMemberId=YBUtility.getMemberId(atx);
		String optMemberName=YBUtility.getMemberName(atx);
		
//		TM_INS_CHECK_OBJ_ITEM
		TmInsCheckObjItemPO itemPOCon=new TmInsCheckObjItemPO();
		itemPOCon.setFreezeTag("0");
		itemPOCon.setStatus("1");
		itemPOCon.setCheckObjCode(itemCode);
		
		TmInsCheckObjItemPO itemPOResult=POFactory.getByPriKey(conn, itemPOCon);
		if(itemPOResult!=null){
			logger.error(" OBJECT ITEM EXIST ALREADY .");
			atx.setErrorContext("BUSI_DATA_OBJ_ITEM_ADD_ACTION_ERR_2000", "���������Ŀ�������Ŀ����["+itemCode+"]�Ѿ�����", null);
			return 0;
		}
		//itemFCode�Ĳ㼶�ṹ���ȼ�����subcode
		TmInsCheckObjItemPO supItemPOCon=null;
		if(StringUtils.isNotBlank(itemFCode)){//��ѯ���������Ŀ
//			TM_INS_CHECK_OBJ_ITEM
			supItemPOCon=new TmInsCheckObjItemPO();
			supItemPOCon.setStatus("1");
			supItemPOCon.setFreezeTag("0");
			supItemPOCon.setCheckObjCode(itemFCode);
			
			supItemPOCon=POFactory.getByPriKey(conn, supItemPOCon);
			if(supItemPOCon==null){
				logger.error("OBJECT ITEM F CODE IS NOT DATA");
			}else{
				itemSubCode=supItemPOCon.getObjClassCodeSub();
			}
			
		}
		//query subCode-��midCode-��supCode
		DynaBean objClassBean=TmInsCheckObjClassPOFactory.querySubMidSupObjClassBySubCode(conn, itemSubCode);
		if(objClassBean==null){
			logger.error(" OBJECT ITEM SUB CODE IS NULL .");
			atx.setErrorContext("BUSI_DATA_OBJ_ITEM_ADD_ACTION_ERR_3000", "���������Ŀ�������Ŀ�ϼ���["+itemSubCode+"]������", null);
			return 0;
		}
		
		String nodePath=objClassBean.getString("SUP_CODE")+"$$"+objClassBean.getString("MID_CODE")+"$$"+itemSubCode;
		
		if(supItemPOCon!=null){
			nodePath=supItemPOCon.getNodePath()+"$$"+itemFCode;
		}
		
		int nodeLevel=nodePath.split("[$][$]").length-3+1;//ȥ��sup mid sub code�㼶,split $$
		
		itemPOCon.setCheckObjDesc(itemDesc);
		itemPOCon.setCheckObjFCode(itemFCode);
		
		itemPOCon.setCheckObjName(itemName);
		itemPOCon.setComlianceDesc(itemCheckComliance);
		itemPOCon.setNodePath(nodePath);
		itemPOCon.setCheckObjLevel(nodeLevel);
		
		if(StringUtils.isNotBlank(itemCheckMomentStr)){
			itemPOCon.setObjCheckAft(itemCheckMomentStr.indexOf("3")!=-1?"1":"0");
			itemPOCon.setObjCheckBef(itemCheckMomentStr.indexOf("1")!=-1?"1":"0");
			itemPOCon.setObjCheckHaf(itemCheckMomentStr.indexOf("2")!=-1?"1":"0");
		}

		itemPOCon.setObjCheckFrequency(itemCheckFrequency);

		if(StringUtils.isNotBlank(itemPhoto)){
			itemPOCon.setObjCheckPhoto("1".equals(itemPhoto)?1:0);
		}

//		itemPOCon.setObjCheckReceive(objCheckReceive);//�Ƿ�����Ϣ
		itemPOCon.setObjCheckWay(itemCheckWay);
		itemPOCon.setObjClassCodeMid(objClassBean.getString("MID_CODE"));
		itemPOCon.setObjClassCodeSub(itemSubCode);
		itemPOCon.setObjClassCodeSup(objClassBean.getString("SUP_CODE"));
//		itemPOCon.setObjEmergencyLevel(objEmergencyLevel);//���س̶�
		itemPOCon.setSort(itemSort);
		
		itemPOCon.setCreateBy(optMemberId);
		itemPOCon.setCreateTime(YBUtility.now());
		itemPOCon.setFreezeTag("0");
		itemPOCon.setId(POFactory.getIntPriKey(conn, itemPOCon));
		itemPOCon.setVer(1);
		
		POFactory.insert(conn, itemPOCon);
		
		//build optInfo
		OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_OBJITEM_ADD, "", "���������Ŀ");
		
		//set return msg
		atx.setStringValue("MSG", "���������Ŀ["+itemCode+"]�ɹ�");
		return 1;
	}
	

}
