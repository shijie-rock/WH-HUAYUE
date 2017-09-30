/**
 * 项目名称:GPS_DATA
 * 包         名:com.vehicle.servlet
 * 文   件  名:DataDownloadServlet.java
 * 创 建日期:2017年4月14日-下午3:14:16
 * Copyright @ 2017-YouBus.com.cn
 */
package com.vehicle.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.vehicle.util.ExcelUtil;
import com.vehicle.util.VehicleInfoUtil;
import com.youbus.framework.comm.remoteclient.ParamBean;
import com.youbus.framework.comm.remoteclient.RemoteClientFactory;
import com.youbus.framework.util.DBConUtil;

/**
 * 类名称:DataDownloadServlet
 * 类描述:下载数据
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年4月14日 下午3:14:16
 * 修改备注:
 * @version 1.0.0
 */
public class DataDownloadServlet extends HttpServlet {
	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since 1.0.0
	 */
	
	private static final long serialVersionUID = 9198558503236275339L;
	
	private static Logger logger=Logger.getLogger(DataDownloadServlet.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String vehicleLicense=request.getParameter("vehicle_license");
		String startDate=request.getParameter("startDate");
		String endDate=request.getParameter("endDate");
		String iDisplayLengthStr = request.getParameter("iDisplayLength");//获得当前每页显示条数
		String iDisplayStartStr = request.getParameter("iDisplayStart");//获得当前页起始条数
		String sEcho =  request.getParameter("sEcho");
		String allTag=  request.getParameter("all");//1 全部 
		
		int iDisplayStart=StringUtils.isBlank(iDisplayStartStr)?0:Integer.valueOf(iDisplayStartStr);
		int iDisplayLength=StringUtils.isBlank(iDisplayLengthStr)?1:Integer.valueOf(iDisplayLengthStr);
		
		int currentPageNo=0;
		currentPageNo=iDisplayStart/iDisplayLength+1;//当前页码
		
		//remote method request
		/*
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("in_date", startDate +" - "+ endDate);
		map.put("carnum",vehicleLicense);
		map.put("pageSize",Integer.valueOf(iDisplayLength));
		map.put("pageNo",Integer.valueOf(currentPageNo));
		
		List<ParamBean> params =new ArrayList<ParamBean>();
		params.add(new ParamBean("method","truck.statistic.truckDaily"));
		params.add(new ParamBean("data",JSONObject.fromObject(map).toString()));
		
		String responseStr=RemoteClientFactory.send(params);
		logger.debug("remote str:="+responseStr);
		List list=VehicleInfoUtil.json2Bean(responseStr);
		*/
		List list;
		if("1".equals(allTag))//全部导出
			list=VehicleInfoUtil.queryAllVehicleData(startDate, endDate, vehicleLicense);
		else //导出当前页面
			list=VehicleInfoUtil.queryVehicleDataByCondition(startDate, endDate, vehicleLicense, iDisplayLength, currentPageNo);
		
		//create excel file  bean List->excel
		
		/*busPostion=busPostion.replace("u007C", "");*/
      /*  response.reset();*/
        //指明这是一个下载的respond
		response.setHeader("Connection", "close");
        response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");
		response.setContentType("text/html; charset = UTF-8"); 
       /* response.setContentType("application/x-download");*/
        //这里是提供给用户默认的文件名
        //双重解码、防止乱码
		String filename="车辆导出数据"+DBConUtil.handleFormatDate(new Date(System.currentTimeMillis()))+".xls";
		filename = URLEncoder.encode(filename,"UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.addHeader("Content-Disposition","attachment;filename=" + filename);

        OutputStream stream= (response.getOutputStream());
       /* printwriter.println(busPositionArr.length+"====");*/
        if(list!=null&&list.size()>0){
			ExcelUtil.getExcleWorkbook(list).write(stream);			
		}
        
        //打印流的所有输出内容，必须关闭这个打印流才有效
        stream.close();
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
