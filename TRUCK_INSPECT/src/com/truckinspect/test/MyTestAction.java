/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.test
 * 文   件  名:MyTestAction.java
 * 创 建日期:2017年5月22日-下午11:17:04
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.test;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.PageQuery;
import com.youbus.framework.pofactory.PageQueryTabMySql;
import com.youbus.framework.util.POFactoryUtil;

/**
 * 类名称:MyTestAction
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年5月22日 下午11:17:04
 * 修改备注:
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
		
		int iDisplayLength = atx.getIntValue("iDisplayLength");//获得当前每页显示条数
//		String sEcho =  atx.getStringValue("sEcho","1");//当前页码
		int iDisplayStart =  atx.getIntValue("iDisplayStart",0);//查询起始数
		
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

}
