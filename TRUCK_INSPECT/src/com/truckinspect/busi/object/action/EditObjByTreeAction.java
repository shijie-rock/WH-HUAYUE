/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.object.action
 * 文   件  名:EditObjByTreeAction.java
 * 创 建日期:2018年3月6日-下午4:46:50
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
import com.truckinspect.common.TruckInsCommonCanstant;
import com.truckinspect.common.util.OptLogUtil;
import com.youbus.framework.comm.YBUtility;

/**
 * 类名称:EditObjByTreeAction
 * 类描述:树状结构编辑项目分类(不改变层级结构)
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2018年3月6日 下午4:46:50
 * 修改备注:
 * @version 1.0.0
 */
public class EditObjByTreeAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		Integer objId=atx.getIntValue("OBJ_ID",0);
		Integer objSort=atx.getIntValue("OBJ_SORT",1000);
		String objName=atx.getStringValue("OBJ_NAME");
		String objDesc=atx.getStringValue("OBJ_DESC");
		String objLevel=atx.getStringValue("OBJ_LEVEL");
		
		//check param
		if(objId==0||StringUtils.isBlank(objName)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("BUSI_DATA_OBJ_EDIT_BY_TREE_ACTION_ERR_1000", "编辑项目分类：参数为空", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		
		Integer optMemberId=YBUtility.getMemberId(atx);
		String optMemberName=YBUtility.getMemberName(atx);
		
		if("1".equals(objLevel)){
//			TM_INS_CHECK_OBJ_CLASS_SUPPER
			TmInsCheckObjClassSupperPO objSupPOCon=new TmInsCheckObjClassSupperPO();
			objSupPOCon.setStatus("1");
			objSupPOCon.setId(objId);
			
			TmInsCheckObjClassSupperPO objSupPOResult=POFactory.getByPriKey(conn, objSupPOCon);
			if(objSupPOResult==null){
				logger.error(" OBJECT SUP NOT EXIST ALREADY .");
				atx.setErrorContext("BUSI_DATA_OBJ_EDIT_BY_TREE_ACTION_ERR_2000", "编辑项目分类：一级分类ID["+objId+"]不存在", null);
				return 0;
			}
			int version=objSupPOResult.getVer();
			objSupPOCon.setVer(version);
			
			TmInsCheckObjClassSupperPO objSupPOValue=new TmInsCheckObjClassSupperPO();
			
			objSupPOValue.setUpdateBy(optMemberId);
			objSupPOValue.setUpdateTime(YBUtility.now());
			objSupPOValue.setVer(version+1);
			
			objSupPOValue.setSort(objSort);
			objSupPOValue.setObjClassDesc(objDesc);
			objSupPOValue.setObjClassName(objName);
			
			int excuteResult=POFactory.update(conn, objSupPOCon, objSupPOValue);
			
			if(excuteResult<1){
				logger.error(" DATA["+objId+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER .");
				atx.setErrorContext("BUSI_DATA_OBJ_EDIT_BY_TREE_ACTION_ERR_3000", "编辑项目分类：一级分类数据ID["+objId+"]已变更，无法完成操作，请稍后重试", null);
				return 0;
			}
			//build optInfo
			OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_OBJSUP_MOD, "", "编辑项目分类：编辑一级分类");
			//set return msg
			atx.setStringValue("MSG", "编辑项目分类：一级分类ID["+objId+"]成功");
			
		}else if("2".equals(objLevel)){
			
//			TM_INS_CHECK_OBJ_CLASS_MIDDLE
			TmInsCheckObjClassMiddlePO objMidPOCon=new TmInsCheckObjClassMiddlePO();
			objMidPOCon.setStatus("1");
			objMidPOCon.setId(objId);
			
			TmInsCheckObjClassMiddlePO objMidPOResult=POFactory.getByPriKey(conn, objMidPOCon);
			if(objMidPOResult==null){
				logger.error(" OBJECT SUP NOT EXIST ALREADY .");
				atx.setErrorContext("BUSI_DATA_OBJ_EDIT_BY_TREE_ACTION_ERR_4000", "编辑项目分类：二级分类ID["+objId+"]不存在", null);
				return 0;
			}
			int version=objMidPOResult.getVer();
			objMidPOCon.setVer(version);
			
			TmInsCheckObjClassMiddlePO objMidPOValue=new TmInsCheckObjClassMiddlePO();
			
			objMidPOValue.setUpdateBy(optMemberId);
			objMidPOValue.setUpdateTime(YBUtility.now());
			objMidPOValue.setVer(version+1);
			
			objMidPOValue.setSort(objSort);
			objMidPOValue.setObjClassDesc(objDesc);
			objMidPOValue.setObjClassName(objName);
			
			int excuteResult=POFactory.update(conn, objMidPOCon, objMidPOValue);
			
			if(excuteResult<1){
				logger.error(" DATA["+objId+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER .");
				atx.setErrorContext("BUSI_DATA_OBJ_EDIT_BY_TREE_ACTION_ERR_5000", "编辑项目分类：二级分类数据ID["+objId+"]已变更，无法完成操作，请稍后重试", null);
				return 0;
			}
			//build optInfo
			OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_OBJMID_MOD, "", "编辑项目分类：编辑二级分类");
			//set return msg
			atx.setStringValue("MSG", "编辑项目分类：二级分类ID["+objId+"]成功");
			
		}else if("3".equals(objLevel)){
			
//			TM_INS_CHECK_OBJ_CLASS_SUB
			TmInsCheckObjClassSubPO objSubPOCon=new TmInsCheckObjClassSubPO();
			objSubPOCon.setStatus("1");
			objSubPOCon.setId(objId);
			
			TmInsCheckObjClassSubPO objSubPOResult=POFactory.getByPriKey(conn, objSubPOCon);
			if(objSubPOResult==null){
				logger.error(" OBJECT SUP NOT EXIST ALREADY .");
				atx.setErrorContext("BUSI_DATA_OBJ_EDIT_BY_TREE_ACTION_ERR_6000", "编辑项目分类：三级分类ID["+objId+"]不存在", null);
				return 0;
			}
			int version=objSubPOResult.getVer();
			objSubPOCon.setVer(version);
			
			TmInsCheckObjClassSubPO objSubPOValue=new TmInsCheckObjClassSubPO();
			
			objSubPOValue.setUpdateBy(optMemberId);
			objSubPOValue.setUpdateTime(YBUtility.now());
			objSubPOValue.setVer(version+1);
			
			objSubPOValue.setSort(objSort);
			objSubPOValue.setObjClassDesc(objDesc);
			objSubPOValue.setObjClassName(objName);
			
			int excuteResult=POFactory.update(conn, objSubPOCon, objSubPOValue);
			
			if(excuteResult<1){
				logger.error(" DATA["+objId+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER .");
				atx.setErrorContext("BUSI_DATA_OBJ_EDIT_BY_TREE_ACTION_ERR_7000", "编辑项目分类：三级分类数据ID["+objId+"]已变更，无法完成操作，请稍后重试", null);
				return 0;
			}
			//build optInfo
			OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_OBJSUB_MOD, "", "编辑项目分类：编辑三级分类");
			//set return msg
			atx.setStringValue("MSG", "编辑项目分类：三级分类ID["+objId+"]成功");
			
		}else if("4".equals(objLevel)){ //4：TM_INS_CHECK_OBJ_ITEM
			atx.setErrorContext("BUSI_DATA_OBJ_EDIT_BY_TREE_ACTION_ERR_8000", "编辑项目分类：暂不支持编辑项目", null);
			return 0;
			
		}else{
			atx.setErrorContext("BUSI_DATA_OBJ_EDIT_BY_TREE_ACTION_ERR_9000", "编辑项目分类：未知操作类型", null);
			return 0;
			
		}
		
		return 1;
	}

}
