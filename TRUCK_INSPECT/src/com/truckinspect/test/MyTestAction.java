/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.test
 * ��   ��  ��:MyTestAction.java
 * �� ������:2017��5��22��-����11:17:04
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.test;

import java.sql.Connection;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.PageQuery;
import com.youbus.framework.pofactory.PageQueryTabMySql;
import com.youbus.framework.util.POFactoryUtil;

/**
 * ������:MyTestAction
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2017��5��22�� ����11:17:04
 * �޸ı�ע:
 * @version 1.0.0
 */
public class MyTestAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		
		String vehicleLicense=atx.getStringValue("vehicle_license");
		String startDate=atx.getStringValue("startDate");
		String endDate=atx.getStringValue("endDate");
		
		int iDisplayLength = atx.getIntValue("iDisplayLength");//��õ�ǰÿҳ��ʾ����
//		String sEcho =  atx.getStringValue("sEcho","1");//��ǰҳ��
		int iDisplayStart =  atx.getIntValue("iDisplayStart",0);//��ѯ��ʼ��
		
		int currentPageNo=0;
		
		currentPageNo=iDisplayStart/iDisplayLength+1;
		
		Connection conn=atx.getConnection();
		
		List param=new ArrayList();
		PageQuery pageQuery= new PageQueryTabMySql(conn,"SELECT * FROM ts_data_dic " ,param,iDisplayLength);
		List pageList=null;
		try {
			pageList = pageQuery.getResult("PAGE", POFactoryUtil.getCurrentPageNo(atx));
			POFactoryUtil.genJsonResultStr(atx,pageList,pageQuery.getTotalRecords());
		}catch(Exception e){
			e.printStackTrace();
		}
		return 1;
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

//		System.out.println(t);
		String code="3432564576657565756912374861";
		String mobile="1399999999999";
		
		byte[] codeBytes=code.getBytes();
		byte[] mobileBytes=mobile.getBytes();
		
		StringBuffer newMobile=new StringBuffer("");
		
		for(int i=0;i<mobileBytes.length;i++){
			System.out.print("org code["+i+"]:="+codeBytes[i]);
			System.out.println(" org mobile["+i+"]:="+mobileBytes[i]);
			char newMobileCode=(char)(codeBytes[i]+mobileBytes[i]);
			newMobile.append(newMobileCode);
			
			System.out.println("new newMobileCode:="+newMobileCode);
		}
		
		System.out.println("newMobile:="+newMobile);
		
		
		StringBuffer oldMobile=new StringBuffer("");
		byte[] newMobileBytes=newMobile.toString().getBytes();
		
		for(int i=0;i<newMobileBytes.length;i++){
			System.out.print("org code["+i+"]:="+codeBytes[i]);
			System.out.println(" new mobile["+i+"]:="+newMobileBytes[i]);
			char oldMobileCode=(char)(newMobileBytes[i]-codeBytes[i]);
			oldMobile.append(oldMobileCode);
			
			System.out.println("old oldMobile:="+oldMobileCode);
		}
		
		System.out.println("oldMobile:="+oldMobile);
	}
	
    /**
     * �ַ�תASC
     * 
     * @param st
     * @return
     */
    public static int getAsc(String st) {
        byte[] gc = st.getBytes();
        int ascNum = (int) gc[0];
        return ascNum;
    }

    /**
     * ASCת�ַ�
     * 
     * @param backnum
     * @return
     */
    public static char backchar(int backnum) {
        char strChar = (char) backnum;
        return strChar;
    }	
	
//	public static void main(String[] args){
//		String str="asdfsadfsa$$dsfawwe$$fadfsadfsa$$adsf";
//		System.out.print(str.split("[$][$]").length);
//	}

}
