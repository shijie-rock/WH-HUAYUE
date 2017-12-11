/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.base.action
 * 文   件  名:AddInsPositionAction.java
 * 创 建日期:2017年8月20日-下午5:52:59
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.busi.base.action;

import java.sql.Connection;

import org.apache.commons.lang.StringUtils;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.POFactory;
import com.truckinspect.busi.base.po.TmInsPositionInfoPO;
import com.truckinspect.busi.organize.po.TmInspactGroupPO;
import com.truckinspect.common.TruckInsCommonCanstant;
import com.truckinspect.common.util.OptLogUtil;
import com.youbus.framework.comm.YBUtility;

/**
 * 类名称:AddInsPositionAction
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年8月20日 下午5:52:59
 * 修改备注:
 * @version 1.0.0
 */
public class AddInsPositionAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String positionName=atx.getStringValue("POSITION_NAME");
		String positionCode=atx.getStringValue("POSITION_CODE");
		String positionDesc=atx.getStringValue("POSITION_DESC");
		String positionAddress=atx.getStringValue("POSITION_ADDRESS");//baidu map 
		String positionCoord=atx.getStringValue("POSITION_COORD");
		
		//check param
		if(StringUtils.isBlank(positionName)||StringUtils.isBlank(positionCode)||StringUtils.isBlank(positionDesc)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("BASE_DATA_POSITION_ADD_ACTION_ERR_1000", "新增检查地点：参数为空", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		
		Integer optMemberId=YBUtility.getMemberId(atx);
		String optMemberName=YBUtility.getMemberName(atx);
//		TM_INS_POSITION_INFO
		TmInsPositionInfoPO positionPOCon=new TmInsPositionInfoPO();
		positionPOCon.setStatus("1");
		positionPOCon.setPositionCode(positionCode);
		
		TmInsPositionInfoPO positionPOResult=POFactory.getByPriKey(conn, positionPOCon);
		if(positionPOResult!=null){
			logger.error(" POSITION CODE EXIST ALREADY .");
			atx.setErrorContext("BASE_DATA_POSITION_ADD_ACTION_ERR_2000", "新增检查地点：检查地点["+positionName+"]已经存在", null);
			return 0;
		}
		
		positionPOCon.setCreateBy(optMemberId);
		positionPOCon.setCreateTime(YBUtility.now());
		positionPOCon.setFreezeTag("0");
		positionPOCon.setId(POFactory.getIntPriKey(conn, positionPOCon));
		positionPOCon.setPositionAddress(positionAddress);
		positionPOCon.setPositionDesc(positionDesc);
		positionPOCon.setPositionName(positionName);
		if(StringUtils.isNotBlank(positionCoord)&&positionCoord.split(";").length==2){
			positionPOCon.setPositionLatitude(positionCoord.split(";")[1]);
			positionPOCon.setPositionLongitude(positionCoord.split(";")[0]);
		}
		positionPOCon.setVer(1);
		
		POFactory.insert(conn, positionPOCon);
		
		//build optInfo
		OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_INSPOSITON_ADD, "", "新增检查地点");
		
		//set return msg
		atx.setStringValue("MSG", "新增检查地点["+positionName+"]成功");
		return 1;
	}

}
