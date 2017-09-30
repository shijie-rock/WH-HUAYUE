/**
 * ��Ŀ����:AGENT_CENTER
 * ��         ��:com.youbus.framework.comm.tag
 * ��   ��  ��:YouBusButtonCheckTag.java
 * �� ������:2015��4��5��-����1:06:01
 * Copyright @ 2015-YouBus.com.cn
 */
package com.youbus.framework.comm.tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * ������:YouBusButtonCheckTag
 * ������:���ݵ�¼�û�Ȩ�ޣ�session��洢��buttonId List����ɸѡbutton�Ƿ������ʾ��
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2015��4��5�� ����1:06:01
 * �޸ı�ע:
 * @version 1.0.0
<yb:buttonCheck buttonId="1001">
<a id="1000">aaaa</a>
</yb:buttonCheck>
<yb:buttonCheck buttonId="2000">
<button id="2000" value="XXX">aaaa</button>
</yb:buttonCheck>
 */
public class YouBusButtonCheckTag extends SimpleTagSupport {

	private String buttonId;//ȫ��Ψһ
	
	/**
	 * @param buttonId the buttonId to set
	 */
	public void setButtonId(String buttonId) {
		this.buttonId = buttonId;
	}

	/**
	 * ����һ���µ�ʵ�� YouBusButtonCheckTag.
	 *
	 */
	public YouBusButtonCheckTag() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * ��   ��  ��:main
	 * ��������:
	 * ��         ��:@param args
	 * ��   ��  ֵ:void
	 * ��   ��  ��:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public void doTag() throws JspException, IOException{
//		HttpSession session = ((PageContext)this.getJspContext()).getSession();
//		HttpServletRequest request = (HttpServletRequest)((PageContext)this.getJspContext()).getRequest();
		JspContext jspContext=this.getJspContext();
		HttpSession session = ((PageContext)jspContext).getSession();
		
		System.out.println("session id="+session.getId());
		JspWriter out=getJspContext().getOut();
		//session get button id list
		//if id exsists list then out ;else out none
		List<String> buttonIdList=new ArrayList<String>();
		buttonIdList.add("1000");//demo
		buttonIdList.add("2000");
		if(checkButtonIdExists(buttonIdList)){
			//����Ȩ��������ʾ
			System.out.println("in");
			getJspBody().invoke(null);
		}
		//else do nothing
	}
	
	/**
	 * 
	 * ��   ��  ��:checkButtonIdExists
	 * ��������:
	 * ��         ��:@param buttonIdList
	 * ��         ��:@return
	 * ��   ��  ֵ:boolean
	 * ��   ��  ��:rock
	 * @exception
	 * @since  1.0.0
	 */
	private boolean checkButtonIdExists(List<String> buttonIdList){
		for(String bId: buttonIdList){
			if(buttonId.equals(bId))return true;
		}
		return false;
	}

}
