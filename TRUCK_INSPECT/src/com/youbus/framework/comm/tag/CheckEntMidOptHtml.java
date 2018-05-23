/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.youbus.framework.comm.tag
 * ��   ��  ��:CheckEntMidOptHtml.java
 * �� ������:2018��2��25��-����6:20:37
 * Copyright @ 2018-YouBus.com.cn
 */
package com.youbus.framework.comm.tag;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.infoservice.framework.services.DBService;
import com.infoservice.po.DynaBean;
import com.truckinspect.busi.entity.pofactory.TmInsCheckEntityPOFactory;

/**
 * ������:CheckEntMidOptHtml
 * ������:չʾ����������Ӧ�ĸ��������Ӧ��ѡ��磺��ͷ��Σ��Ʒ����ͨ�� ���ɶ�̬ select html ��ǩ
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2018��2��25�� ����6:20:37
 * �޸ı�ע:
 * @version 1.0.0
 */
public class CheckEntMidOptHtml extends SimpleTagSupport {

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
	

	public void doTag() throws JspException, IOException{
		
		JspWriter out=getJspContext().getOut();
		Connection conn=DBService.getInstance().getConnection();
		if(conn==null){
			log.error("CONNECTION IS NULL");
			return;
		}
		
		List<DynaBean> entSubList=TmInsCheckEntityPOFactory.queryInsCheckEntitySubListByEntityTypeCode(conn, entTypeCode);
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("conn close error");
		}
		if(entSubList==null){
			log.error("ENT_SUB_LIST IS NULL");
			return;
		}
		//����
		Map<String,List<DynaBean>> midMap=new LinkedHashMap<String,List<DynaBean>>();
		String midCodeOld="";
		for(DynaBean bean:entSubList){
			String midCode=bean.getString("MID_CODE");
//			String midName=bean.getString("MID_NAME");
//			String subCode=bean.getString("SUB_CODE");
//			String subName=bean.getString("SUB_NAME");
			
			if(midCodeOld.equals(midCode)){
				List<DynaBean> list=midMap.get(midCode);
				list.add(bean);
				midMap.put(midCode, list);
			}else{
				List<DynaBean> list=new ArrayList<DynaBean>();
				list.add(bean);
				midMap.put(midCode, list);
				midCodeOld=midCode;
			}
		}
		
		if(midMap!=null){
			midCodeOld="";
			StringBuffer checkHtmlStr=new StringBuffer("");
			StringBuffer labelHtmlStr=new StringBuffer("");
			checkHtmlStr.append("<center><hr class='modal-hr'/></center> ");
			checkHtmlStr.append("\r");
			checkHtmlStr.append("<div class='panel-body' id='add_input_truck_check_ent_select' style='padding-bottom: 0px;'><p>�������������</p></div>");
			checkHtmlStr.append("\r");
			
			Iterator<String> keyIter=midMap.keySet().iterator();
			int i=0;
			while(keyIter.hasNext()){
				String key=keyIter.next();//midCode
				List<DynaBean> subList=midMap.get(key);
				String midName=subList.get(0).getString("MID_NAME");
				
				labelHtmlStr.append(" <label class='control-label col-md-3' for='add_input_"+key+"'>"+midName+"</label>");
				labelHtmlStr.append("\r");
				labelHtmlStr.append("<div class='col-md-4'>");
				labelHtmlStr.append("\r");
				labelHtmlStr.append("<select id='add_input_"+key+"' name='add_input_"+key+"' class='form-control subWidth' data-belone='truck-ent-mid-type' data-belone-code='"+key+"'>");
				labelHtmlStr.append("\r");
				labelHtmlStr.append("<option class='null' value='' selected>----</option>");
				labelHtmlStr.append("\r");
				for(DynaBean bean:subList){
					labelHtmlStr.append("<option class='null' value='"+bean.getString("SUB_CODE")+"'>"+bean.getString("SUB_NAME")+"</option>");
					labelHtmlStr.append("\r");
				}
				labelHtmlStr.append("</select></div>");
				if(i%2==1){
					checkHtmlStr.append("<div class='form-group'>");//new line
					checkHtmlStr.append("\r");
					checkHtmlStr.append(labelHtmlStr);
					checkHtmlStr.append("\r");
					checkHtmlStr.append("</div>");
					checkHtmlStr.append("\r");
					labelHtmlStr=new StringBuffer("");
				}
				i++;
			}
			if(StringUtils.isNotBlank(labelHtmlStr.toString())){
				checkHtmlStr.append("<div class='form-group'>");//new line
				checkHtmlStr.append("\r");
				checkHtmlStr.append(labelHtmlStr);
				checkHtmlStr.append("\r");
				checkHtmlStr.append("</div>");
				checkHtmlStr.append("\r");
			}
			log.debug("html:="+checkHtmlStr.toString());
			out.println(checkHtmlStr.toString());
		}
		

	}
	
//    <center><hr class="modal-hr"/></center>  
//    <div class="panel-body" id="add_input_truck_check_ent_select" style="padding-bottom: 0px;"><p>�������������</p></div>
//    <div class="form-group">
//    <label class="control-label col-md-3" for="add_input_truck_weight">����</label>
//    <div class="col-md-4">
//      <select id="add_input_truck_belong_type" name="null" class="form-control subWidth" ><option class="null" value="">----</option><option class="null" value="TBT_0010" selected="selected">����</option><option class="null" value="TBT_0020">���</option></select>
//    </div>
//    <label class="control-label col-md-3" for="add_input_truck_color">��ɫ</label>
//    <div class="col-md-4">
//      <select id="add_input_truck_belong_type" name="null" class="form-control subWidth" ><option class="null" value="">----</option><option class="null" value="TBT_0010">����</option><option class="null" value="TBT_0020" selected="selected">���</option></select>
//    </div>
//  </div>
}
