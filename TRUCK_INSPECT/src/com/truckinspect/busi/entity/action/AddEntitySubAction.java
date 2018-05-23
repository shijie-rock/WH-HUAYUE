/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.base.action
 * 文   件  名:AddInsPositionAction.java
 * 创 建日期:2017年8月20日-下午5:52:59
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
 * 类名称:AddEntitySubAction
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年8月20日 下午5:52:59
 * 修改备注:
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
		String entMidCode=atx.getStringValue("ENT_MID_CODE");//一级分类代码
		String entSubDesc=atx.getStringValue("ENT_SUB_DESC");
		Integer entSubSort=atx.getIntValue("ENT_SUB_SORT",1000);//排序顺序
		
		//check param
		if(StringUtils.isBlank(entSubName)||StringUtils.isBlank(entSubCode)||StringUtils.isBlank(entSubDesc)||StringUtils.isBlank(entMidCode)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("BUSI_DATA_ENT_SUB_ADD_ACTION_ERR_1000", "新增检查对象小类：参数为空", null);
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
			atx.setErrorContext("BUSI_DATA_ENT_SUB_ADD_ACTION_ERR_2000", "新增检查对象小类：检查对象小类代码["+entSubCode+"]已经存在", null);
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
		//@20180223 自动增加 三级项目类
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
				objSubPOCon.setObjClassFCode(objMidPOCon.getObjClassCode());//设置上级类（二级）代码
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
			objSubPOCon.setRemark("自动增加项目三级分类");
			
			POFactory.insert(conn, objSubPOCon);
			logger.warn(" OBJECT SUB AUTO ADD .");
			msg=",并自动增加项目三级分类";
		}
		
		//build optInfo
		OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_ENTSUB_ADD, "", "新增检查对象小类"+msg);
		
		//set return msg
		atx.setStringValue("MSG", "新增检查对象小类["+entSubCode+"]成功");
		return 1;
	}

}
