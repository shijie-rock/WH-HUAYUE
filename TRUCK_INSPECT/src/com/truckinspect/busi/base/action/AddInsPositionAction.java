/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.busi.base.action
 * ��   ��  ��:AddInsPositionAction.java
 * �� ������:2017��8��20��-����5:52:59
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
 * ������:AddInsPositionAction
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2017��8��20�� ����5:52:59
 * �޸ı�ע:
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
			atx.setErrorContext("BASE_DATA_POSITION_ADD_ACTION_ERR_1000", "�������ص㣺����Ϊ��", null);
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
			atx.setErrorContext("BASE_DATA_POSITION_ADD_ACTION_ERR_2000", "�������ص㣺���ص�["+positionName+"]�Ѿ�����", null);
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
		OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_INSPOSITON_ADD, "", "�������ص�");
		
		//set return msg
		atx.setStringValue("MSG", "�������ص�["+positionName+"]�ɹ�");
		return 1;
	}

}
