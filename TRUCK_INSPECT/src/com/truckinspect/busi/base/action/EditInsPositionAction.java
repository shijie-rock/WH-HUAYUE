/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.base.action
 * 文   件  名:EditInsPositionAction.java
 * 创 建日期:2017年8月20日-下午5:53:16
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.busi.base.action;

import java.sql.Connection;

import org.apache.commons.lang.StringUtils;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.POFactory;
import com.truckinspect.busi.base.po.TmInsPositionInfoPO;
import com.truckinspect.common.TruckInsCommonCanstant;
import com.truckinspect.common.util.OptLogUtil;
import com.youbus.framework.comm.YBUtility;

/**
 * 类名称:EditInsPositionAction
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年8月20日 下午5:53:16
 * 修改备注:
 * @version 1.0.0
 */
public class EditInsPositionAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String positionId=atx.getStringValue("POSITION_ID");
		String positionName=atx.getStringValue("POSITION_NAME");
		String positionDesc=atx.getStringValue("POSITION_DESC");
		String positionAddress=atx.getStringValue("POSITION_ADDRESS");
		String positionCoord=atx.getStringValue("POSITION_COORD");
		
		//check param
		if(StringUtils.isBlank(positionId)||StringUtils.isBlank(positionName)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("BASE_DATA_POSITION_EDIT_ACTION_ERR_1000", "编辑检查地点：参数为空", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		
		//check repeat
		TmInsPositionInfoPO positionPOCon=new TmInsPositionInfoPO();
		positionPOCon.setStatus("1");
		positionPOCon.setId(Integer.valueOf(positionId));
		
		TmInsPositionInfoPO positionPOResult=POFactory.getByPriKey(conn, positionPOCon);
		
		if(positionPOResult==null){
			logger.error(" INS_POSITION NOT EXIST .");
			atx.setErrorContext("BASE_DATA_POSITION_EDIT_ACTION_ERR_2000", "编辑检查地点：检查地点不存在", null);
			return 0;
		}
		
		int version=positionPOResult.getVer();
		positionPOCon.setVer(version);

		Integer optMemberId=YBUtility.getMemberId(atx);
//		String optMemberName=YBUtility.getMemberName(atx);
		
		TmInsPositionInfoPO positionPOValue=new TmInsPositionInfoPO();
		
		positionPOValue.setUpdateBy(optMemberId);
		positionPOValue.setUpdateTime(YBUtility.now());
		positionPOValue.setVer(version+1);
		
		positionPOValue.setPositionAddress(positionAddress);
		positionPOValue.setPositionDesc(positionDesc);
		positionPOValue.setPositionName(positionName);
		
		
		if(StringUtils.isNotBlank(positionCoord)&&positionCoord.split(";").length==2){
			positionPOValue.setPositionLatitude(positionCoord.split(";")[1]);
			positionPOValue.setPositionLongitude(positionCoord.split(";")[0]);
		}
		
		int excuteResult=POFactory.update(conn, positionPOCon, positionPOValue);
		
		if(excuteResult<1){
			logger.error(" DATA["+positionId+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER .");
			atx.setErrorContext("BASE_DATA_POSITION_EDIT_ACTION_ERR_8000", "编辑检查地点：数据ID["+positionId+"]已变更，无法完成操作，请稍后重试", null);
			return 0;
		}
		//build optInfo
		OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_INSPOSITON_MOD, "", "编辑检查地点");
		
		//set return msg
		atx.setStringValue("MSG", "编辑检查地点["+positionName+"]成功");
		return 1;
	}
}
