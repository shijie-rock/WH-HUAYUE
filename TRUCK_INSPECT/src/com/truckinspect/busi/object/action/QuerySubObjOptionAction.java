/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.busi.object.action
 * ��   ��  ��:QuerySubObjOptionAction.java
 * �� ������:2018��2��24��-����5:10:16
 * Copyright @ 2018-YouBus.com.cn
 */
package com.truckinspect.busi.object.action;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.DynaBean;
import com.infoservice.po.POFactory;
import com.truckinspect.busi.object.po.TmInsCheckObjClassMiddlePO;
import com.truckinspect.busi.object.po.TmInsCheckObjClassSubPO;
import com.truckinspect.busi.object.pofactory.TmInsCheckObjClassPOFactory;

/**
 * ������:QuerySubObjOptionAction
 * ������:��ѯ������С���Ӧ�ģ������Ŀ���࣬�������ͬ���Ĺ��������Ŀ�����ࣨ�ο�ԭ��CheckObjOptHtmlTag��
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2018��2��24�� ����5:10:16
 * �޸ı�ע:
 * @version 1.0.0
 */
public class QuerySubObjOptionAction extends ActionImpl {
	private static Logger log = LogManager.getLogger(QuerySubObjOptionAction.class);
	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String entSubCode=atx.getStringValue("ENT_SUB_CODE");//������С�����
		
		if(StringUtils.isBlank(entSubCode)){
			logger.error("PARAM IS NULL");
			atx.setErrorContext("QUERY_SUB_OBJ_OPT_ACTION_ERR_0010", "����Ϊ��", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
//		TM_INS_CHECK_OBJ_CLASS_SUB
		//��ѯ����С���Ӧ����ĿС����ϼ��ࣨ����������
		TmInsCheckObjClassSubPO subPOCon=new TmInsCheckObjClassSubPO();
		subPOCon.setStatus("1");
		subPOCon.setCheckEntSubCode(entSubCode);
		subPOCon.setFreezeTag("0");
		subPOCon=POFactory.getByPriKey(conn, subPOCon);
		
		if(subPOCon==null){
			logger.error("OBJ_SUB IS NULL");
			atx.setErrorContext("QUERY_SUB_OBJ_OPT_ACTION_ERR_0020", "��Ŀ������Ϊ��", null);
			return 0;
		}
		String midCode=subPOCon.getObjClassFCode();//������
		
//		TM_INS_CHECK_OBJ_CLASS_MIDDLE
		//��ѯ�������Ӧ�ĸ�����룬һ�������ڲ�ѯ����������
		TmInsCheckObjClassMiddlePO midPOCon=new TmInsCheckObjClassMiddlePO();
		
		midPOCon.setStatus("1");
		midPOCon.setObjClassCode(midCode);
		midPOCon.setFreezeTag("0");
		midPOCon=POFactory.getByPriKey(conn, midPOCon);
		
		if(midPOCon==null){
			log.error("OBJ_MID IS NULL");
			atx.setErrorContext("QUERY_SUB_OBJ_OPT_ACTION_ERR_0030", "��Ŀ������Ϊ��", null);
			return 0;
		}
		String supCode=midPOCon.getObjClassFCode();//һ����
		
		if(StringUtils.isBlank(midCode)||StringUtils.isBlank(supCode)){
			log.error("MID_CODE OR SUP_CODE IS NULL");
			atx.setErrorContext("QUERY_SUB_OBJ_OPT_ACTION_ERR_0040", "��Ŀ������������Ϊ��", null);
			return 0;
		}
		
		List<DynaBean> list=TmInsCheckObjClassPOFactory.querySubObjByMidCodeAndSupCode(conn,entSubCode, supCode, midCode);
		if(list==null||list.size()<1){
			
			log.error("SUB_OBJ IS NULL");
			atx.setErrorContext("QUERY_SUB_OBJ_OPT_ACTION_ERR_0050", "��Ŀ�������б�Ϊ��", null);
			return 0;
		}
		
		StringBuffer ulHtmlStr=new StringBuffer("<ul class='nav nav-pills nav-justified' id='ent_map_obj_option'>");
		StringBuffer checkHtmlStr=new StringBuffer("");
		
		String funCodeOld=null;
		String actionCodeOld="";
		for(DynaBean bean:list){
			String funCode=bean.getString("MID_CODE");
			String funName=bean.getString("MID_NAME");
			String actionCode=bean.getString("SUB_CODE");
			String actionName=bean.getString("SUB_NAME");
			boolean needCheckTag=StringUtils.isNotBlank(bean.getString("OBJ_CLASS_SUB_CODE"));//�Ƿ�ѡ��
			String checkTagHtml="";
			if(needCheckTag){
				checkTagHtml=" checked='checked' ";
			}
			//�ݲ��ж� option
			
			if(!funCode.equals(funCodeOld)){
				if(funCodeOld==null){
					ulHtmlStr.append("<li class='active'><a href='javascript:void(0);' onclick='displayActionByRoleCode(this);' data-ent_mid_code='"+funCode+"'>"+funName+"</a></li>");
					checkHtmlStr.append("<div class='action-checkbox-div' id='ACTION_DIV_"+funCode+"'>");
				}else{
					ulHtmlStr.append("<li><a href='javascript:void(0);' onclick='displayActionByRoleCode(this);' data-ent_mid_code='"+funCode+"'>"+funName+"</a></li>");
					checkHtmlStr.append("</div>");
					checkHtmlStr.append("<div class='action-checkbox-div' id='ACTION_DIV_"+funCode+"' style='display:none;'> ");
				}
				
				checkHtmlStr.append("<label class='checkbox-inline'> <input type='checkbox' id='"+funCode+"_CHECK_ALL' value='all'>ȫѡ</label>");
				checkHtmlStr.append("<label class='checkbox-inline'> <input type='checkbox' id='"+actionCode+"_CHECK_BOX' value='"+actionCode+"' data-obj_mid_code='"+funCode+"' "+checkTagHtml+">"+actionName+"</label> ");
				
				actionCodeOld=actionCode;
				funCodeOld=funCode;
				
			}else{
				if(!actionCode.equals(actionCodeOld)){
					
					log.debug("actionCode:="+actionCode);
					log.debug("actionCodeOld:="+actionCodeOld);
					
					log.debug("<label class='checkbox-inline'> <input type='checkbox' id='"+actionCode+"_CHECK_BOX' value='"+actionCode+"'>");
					
					checkHtmlStr.append("<label class='checkbox-inline'> <input type='checkbox' id='"+actionCode+"_CHECK_BOX' value='"+actionCode+"' data-obj_mid_code='"+funCode+"' "+checkTagHtml+">"+actionName+"</label> ");
					actionCodeOld=actionCode;
					
				}
			}
		}
		checkHtmlStr.append("</div>");
		ulHtmlStr.append("</ul>");
		
		atx.setStringValue("SUB_OPT_HTML", ulHtmlStr.toString()+checkHtmlStr.toString());
		
		return 1;
	}

}
