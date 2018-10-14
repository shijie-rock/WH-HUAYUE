/**
 * 项目名称:AGENT_CENTER
 * 包         名:com.youbus.framework.comm
 * 文   件  名:YoubusThreadService.java
 * 创 建日期:2016年5月11日-上午12:47:58
 * Copyright @ 2016-YouBus.com.cn
 */
package com.youbus.framework.comm;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.infoservice.framework.Service;
import com.infoservice.framework.exceptions.FrameException;
import com.youbus.framework.comm.servicethread.FileExportService;

/**
 * 类名称:YoubusAppCFGLoadService
 * 类描述:应用配置参数读入
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2016年5月11日 上午12:47:58
 * 修改备注:
 * @version 1.0.0
 */
public class YoubusAppCFGLoadService implements Service {
	private static Logger log=Logger.getLogger(YoubusAppCFGLoadService.class);
	/**
	 * 创建一个新的实例 YoubusThreadService.
	 *
	 */
	public static int ReportExportThreadPoolSize=1;/*导出excel 文件服务线程，缺省线程池大小*/
	public static int DailyHtmlThreadPoolSize=1;/*daily生成html线程，缺省线程池大小*/
	public YoubusAppCFGLoadService() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.infoservice.framework.Service#destroyService()
	 */
	@Override
	public void destroyService() throws FrameException {
		// TODO Auto-generated method stub
		FileExportService.getInstance().destroyThreadPool();/*导出excel 文件服务线程，线程池关闭*/
		
		
		
	}

	/* (non-Javadoc)
	 * @see com.infoservice.framework.Service#getName()
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.infoservice.framework.Service#initialize(java.util.Map)
	 */
	@Override
	public void initialize(Map params) throws FrameException {
		// TODO Auto-generated method stub
		log.debug("YoubusAppCFGLoadService init Begin... ...");
		//ReportExportThreadPoolSize
		if(params.containsKey("ReportExportThreadPoolSize"))ReportExportThreadPoolSize=Integer.valueOf((String)params.get("ReportExportThreadPoolSize"));
		if(params.containsKey("DailyHtmlThreadPoolSize"))DailyHtmlThreadPoolSize=Integer.valueOf((String)params.get("DailyHtmlThreadPoolSize"));

		log.debug("YoubusThreadService init End... ...");
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
