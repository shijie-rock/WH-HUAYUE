/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.object.action
 * 文   件  名:EditObjItemAction.java
 * 创 建日期:2018年3月12日-下午6:48:32
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
 * 类名称:EditObjItemAction
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2018年3月12日 下午6:48:32
 * 修改备注:
 * @version 1.0.0
 */
public class EditObjItemAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		Integer itemId=atx.getIntValue("ITEM_ID",0);
		
//		String itemCode=atx.getStringValue("ITEM_CODE");
		String itemName=atx.getStringValue("ITEM_NAME");
		String itemDesc=atx.getStringValue("ITEM_DESC");
		String itemFCode=atx.getStringValue("ITEM_F_CODE");
		
		Integer itemSort=atx.getIntValue("ITEM_SORT",1000);
		
		String itemCheckWay=atx.getStringValue("ITEM_CHECK_WAY");//检查方式
		Integer itemCheckFrequency=atx.getIntValue("ITEM_CHECK_FREQUENCY",1);//检查频率
		String itemCheckComliance=atx.getStringValue("ITEM_CHECK_COMLIANCE");//检查标准
		
		String itemSupCode=atx.getStringValue("ITEM_SUP_CODE");//一级代码
		String itemMidCode=atx.getStringValue("ITEM_MID_CODE");//二级代码
		String itemSubCode=atx.getStringValue("ITEM_SUB_CODE");//三级代码
		
		String itemPhoto=atx.getStringValue("ITEM_PHOTO");//是否拍照
		String itemCheckMomentStr=atx.getStringValue("ITEM_CHECK_MOMENT_STR");//检查时间：1：前；2：中；3后
		
		//check param
		if(itemId==null||itemId==0||StringUtils.isBlank(itemName)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("BUSI_DATA_OBJ_ITEM_EDIT_ACTION_ERR_1000", "编辑检查项目：参数为空", null);
			return 0;
		}
		Connection conn=atx.getConnection();
		
		//check repeat
//		TM_INS_CHECK_OBJ_ITEM
		TmInsCheckObjItemPO itemPOCon=new TmInsCheckObjItemPO();
		itemPOCon.setStatus("1");
		itemPOCon.setId(itemId);
		
		TmInsCheckObjItemPO itemPOResult=POFactory.getByPriKey(conn, itemPOCon);
		
		if(itemPOResult==null){
			logger.error(" OBJ_MID NOT EXIST .");
			atx.setErrorContext("BUSI_DATA_OBJ_ITEM_EDIT_ACTION_ERR_2000", "编辑检查项目：检查项目不存在", null);
			return 0;
		}
		
		int version=itemPOResult.getVer();
		itemPOCon.setVer(version);

		Integer optMemberId=YBUtility.getMemberId(atx);
//		String optMemberName=YBUtility.getMemberName(atx);
		
		TmInsCheckObjItemPO itemPOValue=new TmInsCheckObjItemPO();
		
		itemPOValue.setUpdateBy(optMemberId);
		itemPOValue.setUpdateTime(YBUtility.now());
		itemPOValue.setVer(version+1);
		
		//itemFCode的层级结构优先级高于subcode
		TmInsCheckObjItemPO supItemPOCon=null;
		if(StringUtils.isNotBlank(itemFCode)){//查询父级检查项目
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
		
		DynaBean objClassBean=TmInsCheckObjClassPOFactory.querySubMidSupObjClassBySubCode(conn, itemSubCode);
		if(objClassBean==null){
			logger.error(" OBJECT ITEM SUB CODE IS NULL .");
			atx.setErrorContext("BUSI_DATA_OBJ_ITEM_EDIT_ACTION_ERR_3000", "编辑检查项目：检查项目上级类["+itemSupCode+"]无数据", null);
			return 0;
		}
		
		String nodePath=objClassBean.getString("SUP_CODE")+"$$"+objClassBean.getString("MID_CODE")+"$$"+itemSubCode;
		if(supItemPOCon!=null){
			nodePath=supItemPOCon.getNodePath()+"$$"+itemFCode;
		}
		
		int nodeLevel=nodePath.split("[$][$]").length-3+1;//去掉sup mid sub code层级,split $$
		
		itemPOValue.setCheckObjDesc(itemDesc);
		itemPOValue.setCheckObjFCode(itemFCode);
		
		itemPOValue.setCheckObjName(itemName);
		itemPOValue.setComlianceDesc(itemCheckComliance);
		itemPOValue.setNodePath(nodePath);
		itemPOValue.setCheckObjLevel(nodeLevel);
		
		if(StringUtils.isNotBlank(itemCheckMomentStr)){
			itemPOValue.setObjCheckAft(itemCheckMomentStr.indexOf("3")!=-1?"1":"0");
			itemPOValue.setObjCheckBef(itemCheckMomentStr.indexOf("1")!=-1?"1":"0");
			itemPOValue.setObjCheckHaf(itemCheckMomentStr.indexOf("2")!=-1?"1":"0");
		}

		itemPOValue.setObjCheckFrequency(itemCheckFrequency);

		if(StringUtils.isNotBlank(itemPhoto)){
			itemPOValue.setObjCheckPhoto("1".equals(itemPhoto)?1:0);
		}

//		itemPOValue.setObjCheckReceive(objCheckReceive);//是否发送消息
		itemPOValue.setObjCheckWay(itemCheckWay);
		itemPOValue.setObjClassCodeMid(objClassBean.getString("MID_CODE"));
		itemPOValue.setObjClassCodeSub(itemSubCode);
		itemPOValue.setObjClassCodeSup(objClassBean.getString("SUP_CODE"));
//		itemPOValue.setObjEmergencyLevel(objEmergencyLevel);//严重程度
		itemPOValue.setSort(itemSort);
		
		int excuteResult=POFactory.update(conn, itemPOCon, itemPOValue);
		
		if(excuteResult<1){
			logger.error(" DATA["+itemId+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER .");
			atx.setErrorContext("BUSI_DATA_OBJ_ITEM_EDIT_ACTION_ERR_8000", "编辑检查项目：数据ID["+itemId+"]已变更，无法完成操作，请稍后重试", null);
			return 0;
		}
		//build optInfo
		OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_OBJITEM_MOD, "", "编辑检查项目");
		
		//set return msg
		atx.setStringValue("MSG", "编辑检查项目["+itemPOResult.getCheckObjCode()+"]成功");
		return 1;
	}
}
