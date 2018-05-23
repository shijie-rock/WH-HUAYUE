/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.busi.base.action
 * ��   ��  ��:QueryInsTruckListAction.java
 * �� ������:2017��9��2��-����9:34:29
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.busi.base.action;

import java.sql.Connection;
import java.util.List;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.PageQuery;
import com.truckinspect.busi.base.pofactory.TmInsTruckInfoPOFactory;
import com.youbus.framework.util.POFactoryUtil;

/**
 * ������:QueryInsTruckListAction
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2017��9��2�� ����9:34:29
 * �޸ı�ע:
 * @version 1.0.0
 */
public class QueryInsTruckListAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String truckType=atx.getStringValue("TRUCK_TYPE");
		String truckStatus=atx.getStringValue("TRUCK_STATUS");
		String truckLicense=atx.getStringValue("TRUCK_LICENSE");
		
		String includeStop=atx.getStringValue("INCLUDE_STOP");//1:include freeze; 0: not include
		
		int customPageSize=POFactoryUtil.getPageSize(atx);
		
		List pageList=null;
		try {
			Connection conn=atx.getConnection();
			//��Ҫ�������¼�鵥
			PageQuery pageQuery=TmInsTruckInfoPOFactory.queryInsTruckList(conn,truckType,truckStatus,truckLicense, "1".equals(includeStop), customPageSize);
			pageList = pageQuery.getResult("PAGE", POFactoryUtil.getCurrentPageNo(atx));
			POFactoryUtil.genJsonResultStr(atx,pageList,pageQuery.getTotalRecords());
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("QueryInsTruckListAction error:"+e.getMessage());
			atx.setErrorContext("BASE_DATA_INS_TRUCK_LIST_QUERY_ACTION_ERR_9000", "��ѯ�����б���Ϣ����", e);
			return 0;
		}
		return 1;
	}

}
