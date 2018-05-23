/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.busi.object.action
 * ��   ��  ��:QueryObjItemListAction.java
 * �� ������:2018��3��8��-����6:30:54
 * Copyright @ 2018-YouBus.com.cn
 */
package com.truckinspect.busi.object.action;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.PageQuery;
import com.truckinspect.busi.object.pofactory.TmInsCheckObjItemPOFactory;
import com.youbus.framework.util.POFactoryUtil;

/**
 * ������:QueryObjItemListAction
 * ������:��������ѯ�����Ŀ�б�
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2018��3��8�� ����6:30:54
 * �޸ı�ע:
 * @version 1.0.0
 */
public class QueryObjItemListAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String itemName=atx.getStringValue("ITEM_NAME");
		String itemCode=atx.getStringValue("ITEM_CODE");
		
		String supName=atx.getStringValue("SUP_NAME");
		String supCode=atx.getStringValue("SUP_CODE");
		
		String midName=atx.getStringValue("MID_NAME");
		String midCode=atx.getStringValue("MID_CODE");
		
		String subName=atx.getStringValue("SUB_NAME");
		String subCode=atx.getStringValue("SUB_CODE");
		
		String itemFName=atx.getStringValue("F_NAME");//�ϼ���Ŀ����
		String itemFCode=atx.getStringValue("F_CODE");//�ϼ���Ŀ����
		
		String includeStop=atx.getStringValue("INCLUDE_STOP");//1:include freeze; 0: not include
		//�¼Ӳ�����������tree ҳ�棬��ѯ
		String classCode=atx.getStringValue("CLASS_CODE");//�����
		String classLevel=atx.getStringValue("CLASS_LEVEL_TYPE");//1 SUP,2 MID,3 SUB
		
		int customPageSize=POFactoryUtil.getPageSize(atx);
		
		List pageList=null;
		try {
			Connection conn=atx.getConnection();
			PageQuery pageQuery=null;
			if(StringUtils.isNotBlank(classLevel)&&StringUtils.isNotBlank(classCode)){
//				��item tree ҳ���ѯ
				
				if("1".equals(classLevel)){
					//sup
					logger.debug(" QUERY OBJ ITEM BY SUP ��");
					pageQuery=TmInsCheckObjItemPOFactory.queryInsCheckItemList(conn, classCode, null, null, null, null, null, null, null,null,null, false, customPageSize);
				}else if("2".equals(classLevel)){
					//mid
					logger.debug(" QUERY OBJ ITEM BY MID ��");
					pageQuery=TmInsCheckObjItemPOFactory.queryInsCheckItemList(conn, null, null, classCode, null, null, null, null, null,null,null, false, customPageSize);
					
				}else if("3".equals(classLevel)){
					//sub
					logger.debug(" QUERY OBJ ITEM BY SUB ��");
					pageQuery=TmInsCheckObjItemPOFactory.queryInsCheckItemList(conn, null, null, null, null, classCode, null, null, null,null,null, false, customPageSize);
					
				}else{
					//��ѯ item F code
					//TODO
//					QUERY ITEM F CODE = CLASS CODE
					/**
					 * 
					 */
					logger.debug(" QUERY OBJ ITEM BY ITEM UPPER ��");
					pageQuery=TmInsCheckObjItemPOFactory.queryInsCheckItemList(conn, null, null, null, null, null, null, null, null,null,classCode, false, customPageSize);
				}
			}else{
				//��item manger ҳ�棬��ѯ
				logger.debug(" QUERY OBJ ITEM BY CONDITION ��");
				pageQuery=TmInsCheckObjItemPOFactory.queryInsCheckItemList(conn, supCode, supName, midCode, midName, subCode, subName, itemCode, itemName,itemFName,itemFCode, "1".equals(includeStop), customPageSize);
			}
			if(pageQuery!=null){
				pageList = pageQuery.getResult("PAGE", POFactoryUtil.getCurrentPageNo(atx));
				POFactoryUtil.genJsonResultStr(atx,pageList,pageQuery.getTotalRecords());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("QueryObjItemListAction error:"+e.getMessage());
			atx.setErrorContext("BUSI_DATA_OBJ_ITEM_QUERY_LIST_ACTION_ERR_9000", "��ѯ�����Ŀ�б���Ϣ����", e);
			return 0;
		}
		
		return 1;
	}

}
