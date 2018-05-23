/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.busi.base.action
 * ��   ��  ��:EditInsPositionAction.java
 * �� ������:2017��8��20��-����5:53:16
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
 * ������:EditInsPositionAction
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2017��8��20�� ����5:53:16
 * �޸ı�ע:
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
			atx.setErrorContext("BASE_DATA_POSITION_EDIT_ACTION_ERR_1000", "�༭���ص㣺����Ϊ��", null);
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
			atx.setErrorContext("BASE_DATA_POSITION_EDIT_ACTION_ERR_2000", "�༭���ص㣺���ص㲻����", null);
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
			atx.setErrorContext("BASE_DATA_POSITION_EDIT_ACTION_ERR_8000", "�༭���ص㣺����ID["+positionId+"]�ѱ�����޷���ɲ��������Ժ�����", null);
			return 0;
		}
		//build optInfo
		OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_INSPOSITON_MOD, "", "�༭���ص�");
		
		//set return msg
		atx.setStringValue("MSG", "�༭���ص�["+positionName+"]�ɹ�");
		return 1;
	}
}
