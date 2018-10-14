/**
 * 项目名称:AGENT_CENTER
 * 包         名:com.youbus.framework.comm.tag
 * 文   件  名:YouBusButtonCheckTag.java
 * 创 建日期:2015年4月5日-下午1:06:01
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
 * 类名称:YouBusButtonCheckTag
 * 类描述:根据登录用户权限（session里存储的buttonId List），筛选button是否可以显示。
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2015年4月5日 下午1:06:01
 * 修改备注:
 * @version 1.0.0
<yb:buttonCheck buttonId="1001">
<a id="1000">aaaa</a>
</yb:buttonCheck>
<yb:buttonCheck buttonId="2000">
<button id="2000" value="XXX">aaaa</button>
</yb:buttonCheck>
 */
public class YouBusButtonCheckTag extends SimpleTagSupport {

	private String buttonId;//全局唯一
	
	/**
	 * @param buttonId the buttonId to set
	 */
	public void setButtonId(String buttonId) {
		this.buttonId = buttonId;
	}

	/**
	 * 创建一个新的实例 YouBusButtonCheckTag.
	 *
	 */
	public YouBusButtonCheckTag() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 方   法  名:main
	 * 方法描述:
	 * 参         数:@param args
	 * 返   回  值:void
	 * 创   建  人:rock
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
			//有授权，可以显示
			System.out.println("in");
			getJspBody().invoke(null);
		}
		//else do nothing
	}
	
	/**
	 * 
	 * 方   法  名:checkButtonIdExists
	 * 方法描述:
	 * 参         数:@param buttonIdList
	 * 参         数:@return
	 * 返   回  值:boolean
	 * 创   建  人:rock
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
