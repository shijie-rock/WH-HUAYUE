/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.busi.object.action
 * ��   ��  ��:QueryObjTreeBeanListAction.java
 * �� ������:2018��1��31��-����10:39:21
 * Copyright @ 2018-YouBus.com.cn
 */
package com.truckinspect.busi.object.action;

import java.sql.Connection;
import java.util.List;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.DynaBean;
import com.truckinspect.busi.object.pofactory.TmInsCheckObjClassPOFactory;

/**
 * ������:QueryObjTreeBeanListAction
 * ������:��ȡ�����Ŀ��״�ṹ������ǰ̨չʾƴ����״�ṹͼ
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2018��1��31�� ����10:39:21
 * �޸ı�ע:
 * @version 1.0.0
 */
public class QueryObjTreeBeanListAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String includeStop=atx.getStringValue("INCLUDE_STOP");//1:include freeze; 0: not include
		Connection conn=atx.getConnection();
		List<DynaBean> list=TmInsCheckObjClassPOFactory.queryObjectTreeDynaBean(conn, "1".equals(includeStop));
		if(list!=null){
			atx.setArrayValue("OBJECT_LIST", list.toArray());
		}
		TmInsCheckObjClassPOFactory.queryObjectTreeHtml(conn, "1".equals(includeStop));
		return 1;
	}

}
