/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.busi.base.action
 * ��   ��  ��:QueryInsTruckCheckObjDetailAction.java
 * �� ������:2018��3��4��-����6:09:34
 * Copyright @ 2018-YouBus.com.cn
 */
package com.truckinspect.busi.base.action;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.DynaBean;
import com.infoservice.po.POFactory;
import com.truckinspect.busi.base.po.TmInsTruckInfoPO;
import com.truckinspect.busi.base.po.TtInsTruckSubObjMapPO;
import com.truckinspect.busi.entity.pofactory.TmInsCheckEntityPOFactory;
import com.truckinspect.busi.object.pofactory.TmInsCheckObjClassPOFactory;

/**
 * ������:QueryInsTruckCheckObjDetailAction
 * ������:��ѯ���������ĿС��
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2018��3��4�� ����6:09:34
 * �޸ı�ע:
 * @version 1.0.0
 */
public class QueryInsTruckCheckObjDetailAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		Integer truckId=atx.getIntValue("TRUCK_ID",0);
		
		//check param
		if(truckId==null||truckId==0){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("BASE_DATA_INS_TRUCK_CHECK_OBJ_DETAIL_ACTION_ERR_1000", "��ѯ���������Ŀ������Ϊ��", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		
//		TM_INS_TRUCK_INFO
		TmInsTruckInfoPO truckPOCon=new TmInsTruckInfoPO(); 
		truckPOCon.setStatus("1");
		truckPOCon.setId(Integer.valueOf(truckId));
		
		TmInsTruckInfoPO truckPOResult=POFactory.getByPriKey(conn, truckPOCon);
		if(truckPOResult==null){
			logger.error("����������");
			atx.setErrorContext("BASE_DATA_INS_TRUCK_CHECK_OBJ_DETAIL_ACTION_ERR_2000", "��ѯ���������Ŀ������������", null);
			return 0;
		}
		
		//TT_INS_TRUCK_SUB_OBJ_MAP
		TtInsTruckSubObjMapPO subMapPOCon=new TtInsTruckSubObjMapPO();
		subMapPOCon.setStatus("1");
		subMapPOCon.setFreezeTag("0");
		subMapPOCon.setTruckId(truckPOResult.getId());
		List<TtInsTruckSubObjMapPO> subList=POFactory.select(conn, subMapPOCon);
		
		if(subList==null||subList.size()<1){
			subList=TmInsCheckEntityPOFactory.querySubObjBySubEnt(conn, truckPOResult.getId());
		}
		//subObj code map
		Map<String,String> subObjMap=new LinkedHashMap<String,String>();
		if(subList!=null&&subList.size()>0){
			for(TtInsTruckSubObjMapPO subObjBean:subList)
				subObjMap.put(subObjBean.getObjClassSubCode(), subObjBean.getObjClassSubCode());
		}
		
		//checkbox html
		
		List<DynaBean> list=TmInsCheckObjClassPOFactory.queryTruckObjSupMidSubDataBySupCode(conn, truckPOResult.getCheckEntCode());
		if(list==null||list.size()<1){
			logger.error("���������޶�Ӧ�����Ŀ");
			atx.setErrorContext("BASE_DATA_INS_TRUCK_CHECK_OBJ_DETAIL_ACTION_ERR_3000", "��ѯ���������Ŀ�����������޶�Ӧ�����Ŀ", null);
			return 0;
		}

		StringBuffer ulHtmlStr=new StringBuffer("<ul class='nav nav-pills nav-justified' id='truck_map_obj_option'>");
		StringBuffer checkHtmlStr=new StringBuffer("");
		
		String funCodeOld=null;
		String actionCodeOld="";
		for(DynaBean bean:list){
			String funCode=bean.getString("MID_CODE");
			String funName=bean.getString("MID_NAME");
			String actionCode=bean.getString("SUB_CODE");
			String actionName=bean.getString("SUB_NAME");
//			boolean needCheckTag=StringUtils.isNotBlank(bean.getString("OBJ_CLASS_SUB_CODE"));//�Ƿ�ѡ��
			String checkTagHtml="";
			if(subObjMap!=null&&subObjMap.containsKey(actionCode)){
				checkTagHtml=" checked='checked' ";
			}
			//�ݲ��ж� option
			
			if(!funCode.equals(funCodeOld)){
				if(funCodeOld==null){
					ulHtmlStr.append("<li class='active'><a href='javascript:void(0);' onclick='displayActionByRoleCode(this);' data-obj_mid_code='"+funCode+"'>"+funName+"</a></li>");
					checkHtmlStr.append("<div class='action-checkbox-div' id='QUERY_ACTION_DIV_"+funCode+"'>");
				}else{
					ulHtmlStr.append("<li><a href='javascript:void(0);' onclick='displayActionByRoleCode(this);' data-obj_mid_code='"+funCode+"'>"+funName+"</a></li>");
					checkHtmlStr.append("</div>");
					checkHtmlStr.append("<div class='action-checkbox-div' id='QUERY_ACTION_DIV_"+funCode+"' style='display:none;'> ");
				}
				
				checkHtmlStr.append("<label class='checkbox-inline'> <input type='checkbox' id='"+funCode+"_CHECK_ALL' value='all'>ȫѡ</label>");
				checkHtmlStr.append("<label class='checkbox-inline'> <input type='checkbox' id='QUERY_"+actionCode+"_CHECK_BOX' value='"+actionCode+"' data-obj_mid_code='"+funCode+"' "+checkTagHtml+">"+actionName+"</label> ");
				
				actionCodeOld=actionCode;
				funCodeOld=funCode;
				
			}else{
				if(!actionCode.equals(actionCodeOld)){
					
					logger.debug("actionCode:="+actionCode);
					logger.debug("actionCodeOld:="+actionCodeOld);
					logger.debug("<label class='checkbox-inline'> <input type='checkbox' id='QUERY_"+actionCode+"_CHECK_BOX' value='"+actionCode+"'>");
					
					checkHtmlStr.append("<label class='checkbox-inline'> <input type='checkbox' id='QUERY_"+actionCode+"_CHECK_BOX' value='"+actionCode+"' data-obj_mid_code='"+funCode+"' "+checkTagHtml+">"+actionName+"</label> ");
					actionCodeOld=actionCode;
					
				}
			}
		}
		checkHtmlStr.append("</div>");
		ulHtmlStr.append("</ul>");
		
		StringBuffer finalHtml=new StringBuffer();
//		finalHtml.append("<center><hr class='modal-hr'/></center> ");
		finalHtml.append("\r");
		finalHtml.append("<div class='panel-body' id='add_input_truck_check_sub_obj' style='padding-bottom: 0px;'><p>���������ĿС�����</p></div>");
		finalHtml.append("\r");
		finalHtml.append("<div id='truck_map_obj_option_div'>");
		finalHtml.append(ulHtmlStr);
		finalHtml.append("\r");
		finalHtml.append(checkHtmlStr);
		finalHtml.append("</div>");
		finalHtml.append("\r");
		
		logger.debug("html:="+finalHtml.toString());
		atx.setStringValue("SUB_LIST_HTML", finalHtml.toString());
		atx.setStringValue("MSG", "����ɹ�");
		
		return 1;
		
	}

}
