/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.youbus.framework.comm.tag
 * ��   ��  ��:CheckObjOptHtmlTag.java
 * �� ������:2018��2��24��-����12:11:12
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

import net.sf.ehcache.Element;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.infoservice.framework.services.DBService;
import com.infoservice.po.DynaBean;
import com.infoservice.po.POFactory;
import com.truckinspect.busi.object.po.TmInsCheckObjClassMiddlePO;
import com.truckinspect.busi.object.po.TmInsCheckObjClassSubPO;
import com.truckinspect.busi.object.pofactory.TmInsCheckObjClassPOFactory;

/**
 * ������:CheckObjOptHtmlTag
 * ������:����
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2018��2��24�� ����12:11:12
 * �޸ı�ע:��ѯ������С���Ӧ�ģ������Ŀ���࣬�������ͬ���Ĺ��������Ŀ������
 * @version 1.0.0
 */
public class CheckObjOptHtmlTag extends SimpleTagSupport {
	private static Logger log=Logger.getLogger(CheckObjOptHtmlTag.class);
	
	private String ulId;
	private String entSubCode;//����С�����
	
	/**
	 * entSubCode
	 *
	 * @return  the entSubCode
	 * @since   1.0.0
	 */
	
	public String getEntSubCode() {
		return entSubCode;
	}

	/**
	 * @param entSubCode the entSubCode to set
	 */
	public void setEntSubCode(String entSubCode) {
		this.entSubCode = entSubCode;
	}

	/**
	 * ulId
	 *
	 * @return  the ulId
	 * @since   1.0.0
	*/
	
	public String getUlId() {
		return ulId;
	}

	/**
	 * @param ulId the ulId to set
	 */
	public void setUlId(String ulId) {
		this.ulId = ulId;
	}

	public void doTag() throws JspException, IOException{
		
		JspWriter out=getJspContext().getOut();

		Connection conn=DBService.getInstance().getConnection();
		if(conn==null){
			log.error("CONNECTION IS NULL");
			return;
		}

//		TM_INS_CHECK_OBJ_CLASS_SUB
		//��ѯ����С���Ӧ����ĿС����ϼ��ࣨ����������
		TmInsCheckObjClassSubPO subPOCon=new TmInsCheckObjClassSubPO();
		subPOCon.setStatus("1");
		subPOCon.setCheckEntSubCode(entSubCode);
		subPOCon.setFreezeTag("0");
		subPOCon=POFactory.getByPriKey(conn, subPOCon);
		
		if(subPOCon==null){
			log.error("OBJ_SUB IS NULL");
			return;
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
			return;
		}
		String supCode=midPOCon.getObjClassFCode();//һ����
		
		if(StringUtils.isBlank(midCode)||StringUtils.isBlank(supCode)){
			log.error("MID_CODE OR SUP_CODE IS NULL");
			return;
		}
		
		List<DynaBean> list=TmInsCheckObjClassPOFactory.querySubObjByMidCodeAndSupCode(conn, entSubCode,supCode, midCode);
		
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("conn close error");
		}
		if(list==null||list.size()<1){
			
			log.error("SUB_OBJ IS NULL");
			return;
		}
		
		StringBuffer ulHtmlStr=new StringBuffer("<ul class='nav nav-pills nav-justified' id='"+ulId+"'>");
		StringBuffer checkHtmlStr=new StringBuffer("");
		
		String funCodeOld=null;
		String actionCodeOld="";
		for(DynaBean bean:list){
			String funCode=bean.getString("MID_CODE");
			String funName=bean.getString("MID_NAME");
			String actionCode=bean.getString("SUB_CODE");
			String actionName=bean.getString("SUB_NAME");
			//�ݲ��ж� option
			
			if(!funCode.equals(funCodeOld)){
				if(funCodeOld==null){
					ulHtmlStr.append("<li class='active'><a href='javascript:void(0);' onclick='displayActionByRoleCode(this);' data-ent_mid_code='"+funCode+"'>"+funName+"</a></li>");
					checkHtmlStr.append("<div class='action-checkbox-div' id='ACTION_DIV_"+funCode+"'>");
				}else{
					ulHtmlStr.append("<li><a href='javascript:void(0);' onclick='displayActionByRoleCode(this);' data-role_code='"+funCode+"'>"+funName+"</a></li>");
					checkHtmlStr.append("</div>");
					checkHtmlStr.append("<div class='action-checkbox-div' id='ACTION_DIV_"+funCode+"' style='display:none;'> ");
				}
				
				checkHtmlStr.append("<label class='checkbox-inline'> <input type='checkbox' id='"+funCode+"_CHECK_ALL' value='all'>ȫѡ</label>");
				checkHtmlStr.append("<label class='checkbox-inline'> <input type='checkbox' id='"+actionCode+"_CHECK_BOX' value='"+actionCode+"'>"+actionName+"</label> ");
				
				actionCodeOld=actionCode;
				funCodeOld=funCode;
				
			}else{
				if(!actionCode.equals(actionCodeOld)){
					
					log.debug("actionCode:="+actionCode);
					log.debug("actionCodeOld:="+actionCodeOld);
					log.debug("<label class='checkbox-inline'> <input type='checkbox' id='"+actionCode+"_CHECK_BOX' value='"+actionCode+"'>");
					
					checkHtmlStr.append("<label class='checkbox-inline'> <input type='checkbox' id='"+actionCode+"_CHECK_BOX' value='"+actionCode+"'>"+actionName+"</label> ");
					actionCodeOld=actionCode;
					
				}
			}
		}
		checkHtmlStr.append("</div>");
		ulHtmlStr.append("</ul>");
		
		out.println(ulHtmlStr.toString()+checkHtmlStr.toString());
		
	}

}	

