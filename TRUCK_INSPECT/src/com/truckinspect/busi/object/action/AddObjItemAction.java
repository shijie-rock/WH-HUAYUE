/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.object.action
 * 文   件  名:AddObjItemAction.java
 * 创 建日期:2018年3月12日-下午6:48:12
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
 * 类名称:AddObjItemAction
 * 类描述:新增检查项目明细
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2018年3月12日 下午6:48:12
 * 修改备注:
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
		
		String itemCheckWay=atx.getStringValue("ITEM_CHECK_WAY");//检查方式
		Integer itemCheckFrequency=atx.getIntValue("ITEM_CHECK_FREQUENCY",1);//检查频率
		String itemCheckComliance=atx.getStringValue("ITEM_CHECK_COMLIANCE");//检查标准
		
		String itemSupCode=atx.getStringValue("ITEM_SUP_CODE");//一级代码
		String itemMidCode=atx.getStringValue("ITEM_MID_CODE");//二级代码
		String itemSubCode=atx.getStringValue("ITEM_SUB_CODE");//三级代码
		
		String itemPhoto=atx.getStringValue("ITEM_PHOTO");//是否拍照
		String itemCheckMomentStr=atx.getStringValue("ITEM_CHECK_MOMENT_STR");//检查时间：1：前；2：中；3后
		
		//check param
		if(StringUtils.isBlank(itemCode)||StringUtils.isBlank(itemName)){
			logger.error(" PARAM IS EMTPY 1 .");
			atx.setErrorContext("BUSI_DATA_OBJ_ITEM_ADD_ACTION_ERR_1000", "新增检查项目：检查项目代码和检查项目名称都不能为空", null);
			return 0;
		}
		if((StringUtils.isBlank(itemSubCode)&&StringUtils.isBlank(itemFCode))){
			logger.error(" PARAM IS EMTPY 2 .");
			atx.setErrorContext("BUSI_DATA_OBJ_ITEM_ADD_ACTION_ERR_1001", "新增检查项目：检查项目上级代码和检查类别三级代码不能同时为空", null);
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
			atx.setErrorContext("BUSI_DATA_OBJ_ITEM_ADD_ACTION_ERR_2000", "新增检查项目：检查项目代码["+itemCode+"]已经存在", null);
			return 0;
		}
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
		//query subCode-》midCode-》supCode
		DynaBean objClassBean=TmInsCheckObjClassPOFactory.querySubMidSupObjClassBySubCode(conn, itemSubCode);
		if(objClassBean==null){
			logger.error(" OBJECT ITEM SUB CODE IS NULL .");
			atx.setErrorContext("BUSI_DATA_OBJ_ITEM_ADD_ACTION_ERR_3000", "新增检查项目：检查项目上级类["+itemSubCode+"]无数据", null);
			return 0;
		}
		
		String nodePath=objClassBean.getString("SUP_CODE")+"$$"+objClassBean.getString("MID_CODE")+"$$"+itemSubCode;
		
		if(supItemPOCon!=null){
			nodePath=supItemPOCon.getNodePath()+"$$"+itemFCode;
		}
		
		int nodeLevel=nodePath.split("[$][$]").length-3+1;//去掉sup mid sub code层级,split $$
		
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

//		itemPOCon.setObjCheckReceive(objCheckReceive);//是否发送消息
		itemPOCon.setObjCheckWay(itemCheckWay);
		itemPOCon.setObjClassCodeMid(objClassBean.getString("MID_CODE"));
		itemPOCon.setObjClassCodeSub(itemSubCode);
		itemPOCon.setObjClassCodeSup(objClassBean.getString("SUP_CODE"));
//		itemPOCon.setObjEmergencyLevel(objEmergencyLevel);//严重程度
		itemPOCon.setSort(itemSort);
		
		itemPOCon.setCreateBy(optMemberId);
		itemPOCon.setCreateTime(YBUtility.now());
		itemPOCon.setFreezeTag("0");
		itemPOCon.setId(POFactory.getIntPriKey(conn, itemPOCon));
		itemPOCon.setVer(1);
		
		POFactory.insert(conn, itemPOCon);
		
		//build optInfo
		OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_OBJITEM_ADD, "", "新增检查项目");
		
		//set return msg
		atx.setStringValue("MSG", "新增检查项目["+itemCode+"]成功");
		return 1;
	}
	

}
