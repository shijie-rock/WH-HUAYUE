/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.object.action
 * 文   件  名:ModObjByTreeAction.java
 * 创 建日期:2018年3月7日-下午9:53:10
 * Copyright @ 2018-YouBus.com.cn
 */
package com.truckinspect.busi.object.action;

import java.sql.Connection;

import org.apache.commons.lang.StringUtils;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.POFactory;
import com.truckinspect.busi.object.po.TmInsCheckObjClassMiddlePO;
import com.truckinspect.busi.object.po.TmInsCheckObjClassSubPO;
import com.truckinspect.busi.object.po.TmInsCheckObjClassSupperPO;
import com.truckinspect.busi.object.po.TmInsCheckObjItemPO;
import com.truckinspect.common.TruckInsCommonCanstant;
import com.truckinspect.common.util.OptLogUtil;
import com.youbus.framework.comm.YBUtility;

/**
 * 类名称:ModObjByTreeAction
 * 类描述:在树形结构里，停用，删除节点
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2018年3月7日 下午9:53:10
 * 修改备注:
 * @version 1.0.0
 */
public class ModObjByTreeAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String objCode=atx.getStringValue("OBJ_CODE");
		String objUpCode=atx.getStringValue("OBJ_UP_CODE");
		String objLevel=atx.getStringValue("OBJ_LEVEL");
		String optType=atx.getStringValue("OPT_TYPE");//stop;start;delete
		
		//check param
		if(StringUtils.isBlank(objCode)||StringUtils.isBlank(optType)||StringUtils.isBlank(objLevel)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("BUSI_DATA_OBJ_CLASS_MOD_BY_TREE_ACTION_ERR_1000", "处理项目分类：参数为空", null);
			return 0;
		}
		Connection conn=atx.getConnection();
		
		Integer optMemberId=YBUtility.getMemberId(atx);
		String optMemberName=YBUtility.getMemberName(atx);
		
		if("1".equals(objLevel)){
//			//TM_INS_CHECK_OBJ_CLASS_SUPPER
			TmInsCheckObjClassSupperPO objSupPOCon=new TmInsCheckObjClassSupperPO();
			objSupPOCon.setStatus("1");
			objSupPOCon.setObjClassCode(objCode);
			
			TmInsCheckObjClassSupperPO objSupPOResult=POFactory.getByPriKey(conn, objSupPOCon);
			
			
			if(objSupPOResult==null){
				logger.error(" OBJ_SUP NOT EXIST .");
				atx.setErrorContext("BUSI_DATA_OBJ_CLASS_MOD_BY_TREE_ACTION_ERR_2000", "处理一级分类：一级分类不存在", null);
				return 0;
			}
			objSupPOCon.setId(objSupPOResult.getId());
			int version=objSupPOResult.getVer();
			objSupPOCon.setVer(version);
			
			TmInsCheckObjClassSupperPO objSupPOValue=new TmInsCheckObjClassSupperPO();
			objSupPOValue.setUpdateBy(optMemberId);
			objSupPOValue.setUpdateTime(YBUtility.now());
			objSupPOValue.setVer(version+1);
			String optTypeDesc="";
			String optTypeCode="";
			if("stop".equals(optType)){
				if("1".equals(objSupPOResult.getFreezeTag())){
					logger.error(" OBJ_SUP IS STOPED ALREADY .");
					atx.setErrorContext("BUSI_DATA_OBJ_CLASS_MOD_BY_TREE_ACTION_ERR_3001", "处理一级分类：一级分类已经是停用状态", null);
					return 0;
				}
//				TM_INS_CHECK_OBJ_CLASS_MIDDLE,如果有子类未停用，则不能停用
				TmInsCheckObjClassMiddlePO midPOCon=new TmInsCheckObjClassMiddlePO();
				midPOCon.setFreezeTag("0");
				midPOCon.setStatus("1");
				midPOCon.setObjClassFCode(objSupPOResult.getObjClassCode());
				
				if(POFactory.getByPriKey(conn, midPOCon)!=null){
					logger.error(" EXIST UN FREEZE CHILD DATA.");
					atx.setErrorContext("BUSI_DATA_OBJ_CLASS_MOD_BY_TREE_ACTION_ERR_3020", "处理一级分类：存在未停用的子类数据", null);
					return 0;
				}
				
				//BUSI
				optTypeDesc="停用";
				optTypeCode=TruckInsCommonCanstant.OPT_TYPE_OBJSUP_UNV;
				objSupPOValue.setFreezeTag("1"); 
				
			}else if("delete".equals(optType)){
//				TM_INS_CHECK_OBJ_CLASS_MIDDLE,如果有子类未停用，则不能停用
				TmInsCheckObjClassMiddlePO midPOCon=new TmInsCheckObjClassMiddlePO();
				midPOCon.setStatus("1");
				midPOCon.setObjClassFCode(objSupPOResult.getObjClassCode());
					
				if(POFactory.getByPriKey(conn, midPOCon)!=null){
					logger.error(" EXIST CHILD DATA.");
					atx.setErrorContext("BUSI_DATA_OBJ_CLASS_MOD_BY_TREE_ACTION_ERR_3030", "处理一级分类：存在未删除的子类数据", null);
					return 0;
				}
					
			//BUSI
			optTypeDesc="删除";
			optTypeCode=TruckInsCommonCanstant.OPT_TYPE_OBJSUP_DEL;
			objSupPOValue.setStatus("0");
			}
			int excuteResult=POFactory.update(conn, objSupPOCon,objSupPOValue);
			
			if(excuteResult<1){
				logger.error(" DATA["+objSupPOResult.getObjClassCode()+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER .");
				atx.setErrorContext("BUSI_DATA_OBJ_CLASS_MOD_BY_TREE_ACTION_ERR_8000", "处理一级分类：数据["+objSupPOResult.getObjClassCode()+"]已变更，无法完成操作，请稍后重试", null);
//				atx.setErrorContext("ORGANIZE_INS_MOD_ACTION_ERR_00040", "DATA["+roleCode+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER", null);
				return 0;
			}
			//clear employee  ins_group ，clear ins_group check item
			OptLogUtil.bindOptContext(atx,optTypeCode , "", "一级分类["+objSupPOResult.getObjClassCode()+"]"+optTypeDesc);
			atx.setStringValue("MSG", "一级分类["+objSupPOResult.getObjClassCode()+"]"+optTypeDesc+"处理成功。");
		
		}else if("2".equals(objLevel)){
//			TM_INS_CHECK_OBJ_CLASS_MIDDLE
			TmInsCheckObjClassMiddlePO objMidPOCon=new TmInsCheckObjClassMiddlePO();
			objMidPOCon.setStatus("1");
			objMidPOCon.setObjClassCode(objCode);
			objMidPOCon.setObjClassFCode(objUpCode);
			
			TmInsCheckObjClassMiddlePO objMidPOResult=POFactory.getByPriKey(conn, objMidPOCon);
			
			if(objMidPOResult==null){
				logger.error(" OBJ_MID NOT EXIST .");
				atx.setErrorContext("BUSI_DATA_OBJ_CLASS_MOD_BY_TREE_ACTION_ERR_4000", "处理二级分类：二级分类不存在", null);
				return 0;
			}
			
			int version=objMidPOResult.getVer();
			objMidPOCon.setVer(version);
			objMidPOCon.setId(objMidPOResult.getId());
			
			TmInsCheckObjClassMiddlePO objMidPOValue=new TmInsCheckObjClassMiddlePO();
			objMidPOValue.setUpdateBy(YBUtility.getMemberId(atx));
			objMidPOValue.setUpdateTime(YBUtility.now());
			objMidPOValue.setVer(version+1);
			
			String optTypeDesc="";
			String optTypeCode="";
			if("stop".equals(optType)){
				if("1".equals(objMidPOResult.getFreezeTag())){
					logger.error(" OBJ_MID IS STOPED ALREADY .");
					atx.setErrorContext("BUSI_DATA_OBJ_CLASS_MOD_BY_TREE_ACTION_ERR_4001", "处理二级分类：二级分类已经是停用状态", null);
					return 0;
				}
				
//				TM_INS_CHECK_OBJ_CLASS_SUB,如果有子类未停用，则不能停用
				TmInsCheckObjClassSubPO midPOCon=new TmInsCheckObjClassSubPO();
				midPOCon.setFreezeTag("0");
				midPOCon.setStatus("1");
				midPOCon.setObjClassFCode(objMidPOResult.getObjClassCode());
				
				if(POFactory.getByPriKey(conn, midPOCon)!=null){
					logger.error(" EXIST UN FREEZE CHILD DATA.");
					atx.setErrorContext("BUSI_DATA_OBJ_CLASS_MOD_BY_TREE_ACTION_ERR_4020", "处理二级分类：存在未停用的子类数据", null);
					return 0;
				}
				
				//BUSI
				optTypeDesc="停用";
				optTypeCode=TruckInsCommonCanstant.OPT_TYPE_OBJMID_UNV;
				objMidPOValue.setFreezeTag("1"); 
				
			}else if("start".equals(optType)){
				
				if("0".equals(objMidPOResult.getFreezeTag())){
					logger.error(" OBJ_MID IS START ALREADY .");
					atx.setErrorContext("BUSI_DATA_OBJ_CLASS_MOD_BY_TREE_ACTION_ERR_4030", "处理二级分类：二级分类已经是启用状态", null);
					return 0;
				}
				//检查是否有有效的相同代码的检查项目二级类
//				TM_INS_CHECK_OBJ_CLASS_MIDDLE
				TmInsCheckObjClassMiddlePO objMidPOConTemp=new TmInsCheckObjClassMiddlePO();
				objMidPOConTemp.setStatus("1");
				objMidPOConTemp.setFreezeTag("0");
				objMidPOConTemp.setObjClassCode(objMidPOResult.getObjClassCode());
				
				TmInsCheckObjClassMiddlePO objMidPOResultTemp=POFactory.getByPriKey(conn, objMidPOConTemp);
				
				if(objMidPOResultTemp!=null){
					logger.error(" OBJ_MID SAME OBJ_CODE EXIST .");
					atx.setErrorContext("BUSI_DATA_OBJ_CLASS_MOD_BY_TREE_ACTION_ERR_4004", "处理二级分类：存在相同编码检查项目二级类", null);
					return 0;
				}
				
				//BUSI
				optTypeDesc="启用";
				optTypeCode=TruckInsCommonCanstant.OPT_TYPE_OBJMID_VAL;
				objMidPOValue.setFreezeTag("0");
				
			}else if("delete".equals(optType)){
				
//				TM_INS_CHECK_OBJ_CLASS_SUB,如果有子类未停用，则不能停用
				TmInsCheckObjClassSubPO midPOCon=new TmInsCheckObjClassSubPO();
				midPOCon.setStatus("1");
				midPOCon.setObjClassFCode(objMidPOResult.getObjClassCode());
				
				if(POFactory.getByPriKey(conn, midPOCon)!=null){
					logger.error(" EXIST CHILD DATA.");
					atx.setErrorContext("BUSI_DATA_OBJ_CLASS_MOD_BY_TREE_ACTION_ERR_4040", "处理二级分类：存在未删除的子类数据", null);
					return 0;
				}
				//BUSI
				optTypeDesc="删除";
				optTypeCode=TruckInsCommonCanstant.OPT_TYPE_OBJMID_DEL;
				objMidPOValue.setStatus("0");
				//clear employee  ins_group ，clear ins_group check item
				
			}else{
				
				logger.error(" NOT SUPPORT OPT_TYPE["+optType+"] .");
				atx.setErrorContext("BUSI_DATA_OBJ_CLASS_MOD_BY_TREE_ACTION_ERR_4050", "处理二级分类：不支持操作类型["+optType+"]", null);
				return 0;
			}
			
			int excuteResult=POFactory.update(conn, objMidPOCon,objMidPOValue);
			
			if(excuteResult<1){
				logger.error(" DATA["+objMidPOResult.getObjClassCode()+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER .");
				atx.setErrorContext("BUSI_DATA_OBJ_CLASS_MOD_BY_TREE_ACTION_ERR_4080", "处理二级分类：数据["+objMidPOResult.getObjClassCode()+"]已变更，无法完成操作，请稍后重试", null);
//				atx.setErrorContext("ORGANIZE_INS_MOD_ACTION_ERR_00040", "DATA["+roleCode+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER", null);
				return 0;
			}
			
			OptLogUtil.bindOptContext(atx,optTypeCode , "", "二级分类["+objMidPOResult.getObjClassCode()+"]"+optTypeDesc);
			
			atx.setStringValue("MSG", "二级分类["+objMidPOResult.getObjClassCode()+"]"+optTypeDesc+"处理成功。");
			
			
		}else if("3".equals(objLevel)){
//			TM_INS_CHECK_OBJ_CLASS_SUB	
			TmInsCheckObjClassSubPO objSubPOCon=new TmInsCheckObjClassSubPO();
			objSubPOCon.setStatus("1");
			objSubPOCon.setObjClassCode(objCode);
			objSubPOCon.setObjClassFCode(objUpCode);
			
			TmInsCheckObjClassSubPO objSubPOResult=POFactory.getByPriKey(conn, objSubPOCon);
			
			if(objSubPOResult==null){
				logger.error(" OBJ_SUB NOT EXIST .");
				atx.setErrorContext("BUSI_DATA_OBJ_CLASS_MOD_BY_TREE_ACTION_ERR_6000", "处理三级分类：三级分类不存在", null);
				return 0;
			}
			objSubPOCon.setId(objSubPOResult.getId());
			int version=objSubPOResult.getVer();
			objSubPOCon.setVer(version);
			
			TmInsCheckObjClassSubPO objSubPOValue=new TmInsCheckObjClassSubPO();
			objSubPOValue.setUpdateBy(YBUtility.getMemberId(atx));
			objSubPOValue.setUpdateTime(YBUtility.now());
			objSubPOValue.setVer(version+1);
			
			String optTypeDesc="";
			String optTypeCode="";
			if("stop".equals(optType)){
				if("1".equals(objSubPOResult.getFreezeTag())){
					logger.error(" OBJ_SUB IS STOPED ALREADY .");
					atx.setErrorContext("BUSI_DATA_OBJ_CLASS_MOD_BY_TREE_ACTION_ERR_6001", "处理三级分类：三级分类已经是停用状态", null);
					return 0;
				}
				//TM_INS_CHECK_OBJ_ITEM
				TmInsCheckObjItemPO itemPOCon=new TmInsCheckObjItemPO();
				itemPOCon.setStatus("1");
				itemPOCon.setFreezeTag("0");
				itemPOCon.setObjClassCodeSub(objSubPOResult.getObjClassCode());
				
				if(POFactory.getByPriKey(conn, itemPOCon)!=null){
					logger.error(" EXIST UN FREEZE CHILD DATA.");
					atx.setErrorContext("BUSI_DATA_OBJ_CLASS_MOD_BY_TREE_ACTION_ERR_6030", "处理三级分类：存在未停用的检查项目", null);
					return 0;
				}
				
				
				//BUSI
				optTypeDesc="停用";
				optTypeCode=TruckInsCommonCanstant.OPT_TYPE_OBJSUB_UNV;
				objSubPOValue.setFreezeTag("1"); 
				
			}else if("start".equals(optType)){
				
				if("0".equals(objSubPOResult.getFreezeTag())){
					logger.error(" OBJ_SUB IS START ALREADY .");
					atx.setErrorContext("BUSI_DATA_OBJ_CLASS_MOD_BY_TREE_ACTION_ERR_6002", "处理三级分类：三级分类已经是启用状态", null);
					return 0;
				}
				//检查是否有有效的相同代码的检查项目三级类
//				TM_INS_CHECK_OBJ_CLASS_SUB
				TmInsCheckObjClassSubPO objSubPOConTemp=new TmInsCheckObjClassSubPO();
				objSubPOConTemp.setStatus("1");
				objSubPOConTemp.setFreezeTag("0");
				objSubPOConTemp.setObjClassCode(objSubPOResult.getObjClassCode());
				
				TmInsCheckObjClassSubPO objSubPOResultTemp=POFactory.getByPriKey(conn, objSubPOConTemp);
				
				if(objSubPOResultTemp!=null){
					logger.error(" OBJ_SUB SAME OBJ_CODE EXIST .");
					atx.setErrorContext("BUSI_DATA_OBJ_CLASS_MOD_BY_TREE_ACTION_ERR_6003", "处理三级分类：存在相同编码检查项目三级类", null);
					return 0;
				}
				
				//BUSI
				optTypeDesc="启用";
				optTypeCode=TruckInsCommonCanstant.OPT_TYPE_OBJSUB_VAL;
				objSubPOValue.setFreezeTag("0");
				
			}else if("delete".equals(optType)){
				
				//TM_INS_CHECK_OBJ_ITEM
				TmInsCheckObjItemPO itemPOCon=new TmInsCheckObjItemPO();
				itemPOCon.setStatus("1");
				itemPOCon.setObjClassCodeSub(objSubPOResult.getObjClassCode());
				
				if(POFactory.getByPriKey(conn, itemPOCon)!=null){
					logger.error(" EXIST CHILD DATA.");
					atx.setErrorContext("BUSI_DATA_OBJ_CLASS_MOD_BY_TREE_ACTION_ERR_6030", "处理三级分类：存在未删除的检查项目", null);
					return 0;
				}
				//BUSI
				optTypeDesc="删除";
				optTypeCode=TruckInsCommonCanstant.OPT_TYPE_OBJSUB_DEL;
				objSubPOValue.setStatus("0");
				//clear employee  ins_group ，clear ins_group check item
				
			}else{
				
				logger.error(" NOT SUPPORT OPT_TYPE["+optType+"] .");
				atx.setErrorContext("BUSI_DATA_OBJ_CLASS_MOD_BY_TREE_ACTION_ERR_6010", "处理三级分类：不支持操作类型["+optType+"]", null);
				return 0;
			}
			
			int excuteResult=POFactory.update(conn, objSubPOCon,objSubPOValue);
			
			if(excuteResult<1){
				logger.error(" DATA["+objSubPOResult.getObjClassCode()+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER .");
				atx.setErrorContext("BUSI_DATA_OBJ_CLASS_MOD_BY_TREE_ACTION_ERR_6080", "处理三级分类：数据["+objSubPOResult.getObjClassCode()+"]已变更，无法完成操作，请稍后重试", null);
//				atx.setErrorContext("ORGANIZE_INS_MOD_ACTION_ERR_00040", "DATA["+roleCode+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER", null);
				return 0;
			}
			
			OptLogUtil.bindOptContext(atx,optTypeCode , "", "三级分类["+objSubPOResult.getObjClassCode()+"]"+optTypeDesc);
			
			atx.setStringValue("MSG", "三级分类["+objSubPOResult.getObjClassCode()+"]"+optTypeDesc+"处理成功。");
		}
		return 1;
	}

}
