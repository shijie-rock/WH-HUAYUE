/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.base.action
 * 文   件  名:AddInsPositionAction.java
 * 创 建日期:2017年8月20日-下午5:52:59
 * Copyright @ 2017-YouBus.com.cn
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
 * 类名称:AddObjMidAction
 * 类描述:在项目树状图增加项目
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年8月20日 下午5:52:59
 * 修改备注:
 * @version 1.0.0
 */
public class AddObjByTreeAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String objName=atx.getStringValue("OBJ_NAME");//项目名称
		String objCode=atx.getStringValue("OBJ_CODE");//项目代码
		String objUpCode=atx.getStringValue("OBJ_UP_CODE");//上级代码
		String objDesc=atx.getStringValue("OBJ_DESC");//项目说明
		Integer objSort=atx.getIntValue("OBJ_SORT",1000);//排序顺序
		String addLevel=atx.getStringValue("ADD_LEVEL");//要增加的级别，决定在哪个表;
		//1：TM_INS_CHECK_OBJ_CLASS_SUPPER
		//2：TM_INS_CHECK_OBJ_CLASS_MIDDLE
		//3：TM_INS_CHECK_OBJ_CLASS_SUB
		//4：TM_INS_CHECK_OBJ_ITEM
		
		//check param
		if(StringUtils.isBlank(objUpCode)||StringUtils.isBlank(objCode)||StringUtils.isBlank(objName)||StringUtils.isBlank(addLevel)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("BUSI_DATA_OBJ_ADD_BY_TREE_ACTION_ERR_1000", "新增分类：参数为空", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		
		Integer optMemberId=YBUtility.getMemberId(atx);
		String optMemberName=YBUtility.getMemberName(atx);
		if("1".equals(addLevel)){
//			TM_INS_CHECK_OBJ_CLASS_SUPPER
			TmInsCheckObjClassSupperPO objSupPOCon=new TmInsCheckObjClassSupperPO();
			objSupPOCon.setStatus("1");
			objSupPOCon.setObjClassCode(objCode);
			
			TmInsCheckObjClassSupperPO objSupPOResult=POFactory.getByPriKey(conn, objSupPOCon);
			if(objSupPOResult!=null){
				logger.error(" OBJECT SUP EXIST ALREADY .");
				atx.setErrorContext("BUSI_DATA_OBJ_SUP_ADD_ACTION_ERR_2000", "新增一级分类：一级分类代码["+objCode+"]已经存在", null);
				return 0;
			}
			objSupPOCon.setObjClassDesc(objDesc);
			objSupPOCon.setObjClassLevel(1);
			objSupPOCon.setObjClassName(objName);
			objSupPOCon.setSort(objSort);
			
			objSupPOCon.setCreateBy(optMemberId);
			objSupPOCon.setCreateTime(YBUtility.now());
			objSupPOCon.setFreezeTag("0");
			objSupPOCon.setId(POFactory.getIntPriKey(conn, objSupPOCon));
			objSupPOCon.setVer(1);
			
			POFactory.insert(conn, objSupPOCon);
			
			OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_OBJSUP_ADD, "", "新增一级分类");
			//set return msg
			atx.setStringValue("MSG", "新增一级分类["+objCode+"]成功");
			
		}else if("2".equals(addLevel)){
			
//			TM_INS_CHECK_OBJ_CLASS_MIDDLE
			TmInsCheckObjClassMiddlePO objMidPOCon=new TmInsCheckObjClassMiddlePO();
			objMidPOCon.setStatus("1");
			objMidPOCon.setObjClassCode(objCode);
			
			TmInsCheckObjClassMiddlePO objMidPOResult=POFactory.getByPriKey(conn, objMidPOCon);
			if(objMidPOResult!=null){
				logger.error(" OBJECT MID EXIST ALREADY .");
				atx.setErrorContext("BUSI_DATA_OBJ_MID_ADD_ACTION_ERR_2000", "新增二级分类：二级分类代码["+objCode+"]已经存在", null);
				return 0;
			}
			objMidPOCon.setObjClassDesc(objDesc);
			objMidPOCon.setObjClassLevel(2);
			objMidPOCon.setObjClassName(objName);
			objMidPOCon.setSort(objSort);
			objMidPOCon.setObjClassFCode(objUpCode);
			
			objMidPOCon.setCreateBy(optMemberId);
			objMidPOCon.setCreateTime(YBUtility.now());
			objMidPOCon.setFreezeTag("0");
			objMidPOCon.setId(POFactory.getIntPriKey(conn, objMidPOCon));
			objMidPOCon.setVer(1);
			
			POFactory.insert(conn, objMidPOCon);
			
			//build optInfo
			OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_OBJMID_ADD, "", "新增二级分类");
			
			//set return msg
			atx.setStringValue("MSG", "新增二级分类["+objCode+"]成功");
			
		}else if("3".equals(addLevel)){
			
//			TM_INS_CHECK_OBJ_CLASS_SUB
			TmInsCheckObjClassSubPO objSubPOCon=new TmInsCheckObjClassSubPO();
			objSubPOCon.setStatus("1");
			objSubPOCon.setObjClassCode(objCode);
			
			TmInsCheckObjClassSubPO objSubPOResult=POFactory.getByPriKey(conn, objSubPOCon);
			if(objSubPOResult!=null){
				logger.error(" OBJECT SUB EXIST ALREADY .");
				atx.setErrorContext("BUSI_DATA_OBJ_SUB_ADD_ACTION_ERR_2000", "新增三级分类：三级分类代码["+objCode+"]已经存在", null);
				return 0;
			}
			objSubPOCon.setObjClassDesc(objDesc);
			objSubPOCon.setObjClassLevel(3);
			objSubPOCon.setObjClassName(objName);
			objSubPOCon.setSort(objSort);
			objSubPOCon.setObjClassFCode(objUpCode);
			
			objSubPOCon.setCreateBy(optMemberId);
			objSubPOCon.setCreateTime(YBUtility.now());
			objSubPOCon.setFreezeTag("0");
			objSubPOCon.setId(POFactory.getIntPriKey(conn, objSubPOCon));
			objSubPOCon.setVer(1);
			
			POFactory.insert(conn, objSubPOCon);
			
			OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_OBJSUB_ADD, "", "新增三级分类");
			//set return msg
			atx.setStringValue("MSG", "新增三级分类["+objCode+"]成功");
			
		}else if("4".equals(addLevel)){ //4：TM_INS_CHECK_OBJ_ITEM
			atx.setErrorContext("BUSI_DATA_OBJ_ADD_BY_TREE_ACTION_ERR_2000", "新增分类：暂不支持新增项目", null);
			return 0;
			
		}else{
			atx.setErrorContext("BUSI_DATA_OBJ_ADD_BY_TREE_ACTION_ERR_3000", "新增分类：未知操作类型", null);
			return 0;
			
		}
		return 1;
	}

}
