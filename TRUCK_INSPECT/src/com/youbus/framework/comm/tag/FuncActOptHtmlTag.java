/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.youbus.framework.comm.tag
 * ��   ��  ��:FuncActOptHtmlTag.java
 * �� ������:2017��8��17��-����11:43:46
 * Copyright @ 2017-YouBus.com.cn
 */
package com.youbus.framework.comm.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.apache.log4j.Logger;

import com.infoservice.po.DynaBean;
import com.youbus.framework.comm.TruckInsNativeCacheService;

/**
 * ������:FuncActOptHtmlTag
 * ������:ƴ�� function action option html
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2017��8��17�� ����11:43:46
 * �޸ı�ע:
 * @version 1.0.0
 */
public class FuncActOptHtmlTag extends SimpleTagSupport {
	private static Logger log=Logger.getLogger(FuncActOptHtmlTag.class);
	private String ulId;
	
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

		Cache cache=TruckInsNativeCacheService.getCache("FUN_ACT_OPT_CACHE");
		if(cache==null)return;
		
		Element e=cache.get("FUN_ACT_OPT_CACHE_KEY");
		if(e==null)return;
		
		List<DynaBean> list=(List<DynaBean>) e.getValue();
		if(list==null||list.size()<1)return;
		
		StringBuffer ulHtmlStr=new StringBuffer("<ul class='nav nav-pills nav-justified' id='"+ulId+"'>");
		StringBuffer checkHtmlStr=new StringBuffer("");
		
		String funCodeOld=null;
		String actionCodeOld="";
		for(DynaBean bean:list){
			String funCode=bean.getString("FUN_CODE");
			String funName=bean.getString("FUN_NAME");
			String actionCode=bean.getString("ACTION_CODE");
			String actionName=bean.getString("ACTION_NAME");
			//�ݲ��ж� option
			
			if(!funCode.equals(funCodeOld)){
				if(funCodeOld==null){
					ulHtmlStr.append("<li class='active'><a href='javascript:void(0);' onclick='displayActionByRoleCode(this);' data-role_code='"+funCode+"'>"+funName+"</a></li>");
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

//<div class="panel-body"><p>��ɫ��Ӧ�˵��б�</p>
//��ǩ���ݿ�ʼ
//	 <ul class="nav nav-pills nav-justified" id="role_action_option">
//	 <li class="active"><a href="javascript:void(0);" onclick="displayActionByRoleCode(this);" data-role_code="ROLE_CODE_0001">Home</a></li>
//	 <li><a href="javascript:void(0);" onclick="displayActionByRoleCode(this);" data-role_code="ROLE_CODE_0002">SVN</a></li>
//	 <li><a href="javascript:void(0);" onclick="displayActionByRoleCode(this);" data-role_code="ROLE_CODE_0003">iOS</a></li>
//	 <li><a href="javascript:void(0);" onclick="displayActionByRoleCode(this);" data-role_code="ROLE_CODE_0004">VB.Net</a></li>
//	 <li><a href="javascript:void(0);" onclick="displayActionByRoleCode(this);" data-role_code="ROLE_CODE_0005">Java</a></li>
//	 <li><a href="javascript:void(0);" onclick="displayActionByRoleCode(this);" data-role_code="ROLE_CODE_0006">PHP</a></li>
//	 <li><a href="javascript:void(0);" onclick="displayActionByRoleCode(this);" data-role_code="ROLE_CODE_0007">PHP2</a></li>
//	</ul>
//	<div class="action-checkbox-div" id="ACTION_DIV_ROLE_CODE_0001">
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox1" value="all"> ȫѡ</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox1" value="option1"> ѡ�� 1</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox2" value="option2"> ѡ�� 2</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox3" value="option3"> ѡ�� 3</label>
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox1" value="option1"> ѡ�� 1</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox2" value="option2"> ѡ�� 2</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox3" value="option3"> ѡ�� 3</label>
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox1" value="option1"> ѡ�� 1</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox2" value="option2"> ѡ�� 2</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox3" value="option3"> ѡ�� 3</label>
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox1" value="option1"> ѡ�� 1</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox2" value="option2"> ѡ�� 2</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox3" value="option3"> ѡ�� 3</label>
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox1" value="option1"> ѡ�� 1</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox2" value="option2"> ѡ�� 2</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox3" value="option3"> ѡ�� 3</label>
//	</div>
//	<div class="action-checkbox-div" id="ACTION_DIV_ROLE_CODE_0002" style="display:none;">
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox1" value="all"> ȫѡ</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox1" value="option1"> ѡ�� 4</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox2" value="option2"> ѡ�� 5</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox3" value="option3"> ѡ�� 6</label>
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox3" value="option3"> ѡ�� 3</label>
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox1" value="option1"> ѡ�� 1</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox2" value="option2"> ѡ�� 2</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox3" value="option3"> ѡ�� 3</label>
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox1" value="option1"> ѡ�� 1</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox2" value="option2"> ѡ�� 2</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox3" value="option3"> ѡ�� 3</label>
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox1" value="option1"> ѡ�� 1</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox2" value="option2"> ѡ�� 2</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox3" value="option3"> ѡ�� 3</label>
//	</div>
//	<div class="action-checkbox-div" id="ACTION_DIV_ROLE_CODE_0003" style="display:none;">
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox1" value="all"> ȫѡ</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox1" value="option1"> ѡ�� 41</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox2" value="option2"> ѡ�� 52</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox3" value="option3"> ѡ�� 63</label>
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox3" value="option3"> ѡ�� 3</label>
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox1" value="option1"> ѡ�� 1</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox2" value="option2"> ѡ�� 2</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox3" value="option3"> ѡ�� 3</label>
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox1" value="option1"> ѡ�� 1</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox2" value="option2"> ѡ�� 2</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox3" value="option3"> ѡ�� 3</label>
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox1" value="option1"> ѡ�� 1</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox2" value="option2"> ѡ�� 2</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox3" value="option3"> ѡ�� 3</label>
//	</div>
//	<div class="action-checkbox-div" id="ACTION_DIV_ROLE_CODE_0004" style="display:none;">
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox1" value="all"> ȫѡ</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox1" value="option1"> ѡ�� 46</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox2" value="option2"> ѡ�� 56</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox3" value="option3"> ѡ�� 66</label>
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox3" value="option3"> ѡ�� 3</label>
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox1" value="option1"> ѡ�� 1</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox2" value="option2"> ѡ�� 2</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox3" value="option3"> ѡ�� 3</label>
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox1" value="option1"> ѡ�� 1</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox2" value="option2"> ѡ�� 2</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox3" value="option3"> ѡ�� 3</label>
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox1" value="option1"> ѡ�� 1</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox2" value="option2"> ѡ�� 2</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox3" value="option3"> ѡ�� 3</label>
//	</div>
//	<div class="action-checkbox-div" id="ACTION_DIV_ROLE_CODE_0005" style="display:none;">
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox1" value="all"> ȫѡ</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox1" value="option1"> ѡ�� 47</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox2" value="option2"> ѡ�� 57</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox3" value="option3"> ѡ�� 67</label>
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox3" value="option3"> ѡ�� 3</label>
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox1" value="option1"> ѡ�� 1</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox2" value="option2"> ѡ�� 2</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox3" value="option3"> ѡ�� 3</label>
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox1" value="option1"> ѡ�� 1</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox2" value="option2"> ѡ�� 2</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox3" value="option3"> ѡ�� 3</label>
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox1" value="option1"> ѡ�� 1</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox2" value="option2"> ѡ�� 2</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox3" value="option3"> ѡ�� 3</label>
//	</div>
//	<div class="action-checkbox-div" id="ACTION_DIV_ROLE_CODE_0006" style="display:none;">
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox1" value="all"> ȫѡ</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox1" value="option1"> ѡ�� 48</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox2" value="option2"> ѡ�� 58</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox3" value="option3"> ѡ�� 68</label>
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox3" value="option3"> ѡ�� 3</label>
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox1" value="option1"> ѡ�� 1</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox2" value="option2"> ѡ�� 2</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox3" value="option3"> ѡ�� 3</label>
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox1" value="option1"> ѡ�� 1</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox2" value="option2"> ѡ�� 2</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox3" value="option3"> ѡ�� 3</label>
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox1" value="option1"> ѡ�� 1</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox2" value="option2"> ѡ�� 2</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox3" value="option3"> ѡ�� 3</label>
//	</div>
//	<div class="action-checkbox-div" id="ACTION_DIV_ROLE_CODE_0007" style="display:none;">
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox1" value="all"> ȫѡ</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox1" value="option1"> ѡ�� 49</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox2" value="option2"> ѡ�� 59</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox3" value="option3"> ѡ�� 69</label>
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox3" value="option3"> ѡ�� 3</label>
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox1" value="option1"> ѡ�� 1</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox2" value="option2"> ѡ�� 2</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox3" value="option3"> ѡ�� 3</label>
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox1" value="option1"> ѡ�� 1</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox2" value="option2"> ѡ�� 2</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox3" value="option3"> ѡ�� 3</label>
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox1" value="option1"> ѡ�� 1</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox2" value="option2"> ѡ�� 2</label> 
//		<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox3" value="option3"> ѡ�� 3</label>
//	</div>
//--��ǩ���ݽ���
//</div>