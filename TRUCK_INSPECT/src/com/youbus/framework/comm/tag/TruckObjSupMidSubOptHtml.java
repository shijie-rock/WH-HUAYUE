/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.youbus.framework.comm.tag
 * ��   ��  ��:TruckObjSupMidSubOptHtml.java
 * �� ������:2018��2��28��-����6:43:26
 * Copyright @ 2018-YouBus.com.cn
 */
package com.youbus.framework.comm.tag;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.log4j.Logger;

import com.infoservice.framework.services.DBService;
import com.infoservice.po.DynaBean;
import com.infoservice.po.POFactory;
import com.truckinspect.busi.entity.po.TmInsCheckEntitySupperPO;
import com.truckinspect.busi.object.pofactory.TmInsCheckObjClassPOFactory;

/**
 * ������:TruckObjSupMidSubOptHtml
 * ������:���ɳ�����Ӧ�ļ����ĿС��option�б�
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2018��2��28�� ����6:43:26
 * �޸ı�ע:
 * @version 1.0.0
 */
public class TruckObjSupMidSubOptHtml extends SimpleTagSupport {
	private static Logger log=Logger.getLogger(CheckEntMidOptHtml.class);
	
	private String entTypeCode;//���������ͣ�������������

	/**
	 * entTypeCode
	 *
	 * @return  the entTypeCode
	 * @since   1.0.0
	*/
	
	public String getEntTypeCode() {
		return entTypeCode;
	}

	/**
	 * @param entTypeCode the entTypeCode to set
	 */
	public void setEntTypeCode(String entTypeCode) {
		this.entTypeCode = entTypeCode;
	}
	
	
	public void doTag()throws JspException, IOException{
		JspWriter out=getJspContext().getOut();
		Connection conn=DBService.getInstance().getConnection();
		if(conn==null){
			log.error("CONNECTION IS NULL");
			return;
		}
//		TM_INS_CHECK_ENTITY_SUPPER
		TmInsCheckEntitySupperPO supPOCon=new TmInsCheckEntitySupperPO();
		supPOCon.setStatus("1");
		supPOCon.setFreezeTag("0");
		supPOCon.setCheckEntTypeCode(entTypeCode);
		
		supPOCon=POFactory.getByPriKey(conn, supPOCon);
		
		if(supPOCon!=null){
	
		List<DynaBean> list=TmInsCheckObjClassPOFactory.queryTruckObjSupMidSubDataBySupCode(conn, supPOCon.getCheckEntCode());
		if(list==null||list.size()<1){
			log.error("SUP_MID_SUB_OBJ IS NULL");
			return ;
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
//			if(needCheckTag){
//				checkTagHtml=" checked='checked' ";
//			}
			//�ݲ��ж� option
			
			if(!funCode.equals(funCodeOld)){
				if(funCodeOld==null){
					ulHtmlStr.append("<li class='active'><a href='javascript:void(0);' onclick='displayActionByRoleCode(this);' data-obj_mid_code='"+funCode+"'>"+funName+"</a></li>");
					checkHtmlStr.append("<div class='action-checkbox-div' id='ACTION_DIV_"+funCode+"'>");
				}else{
					ulHtmlStr.append("<li><a href='javascript:void(0);' onclick='displayActionByRoleCode(this);' data-obj_mid_code='"+funCode+"'>"+funName+"</a></li>");
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
		
		StringBuffer finalHtml=new StringBuffer();
		finalHtml.append("<center><hr class='modal-hr'/></center> ");
		finalHtml.append("\r");
		finalHtml.append("<div class='panel-body' id='add_input_truck_check_sub_obj' style='padding-bottom: 0px;'><p>���������ĿС�����</p></div>");
		finalHtml.append("\r");
		finalHtml.append("<div id='truck_map_obj_option_div'>");
		finalHtml.append(ulHtmlStr);
		finalHtml.append("\r");
		finalHtml.append(checkHtmlStr);
		finalHtml.append("</div>");
		finalHtml.append("\r");
		
		log.debug("html:="+finalHtml.toString());
		out.println(finalHtml.toString());
		}
		try { //close connection
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("conn close error");
		}
		
	}
	
}
