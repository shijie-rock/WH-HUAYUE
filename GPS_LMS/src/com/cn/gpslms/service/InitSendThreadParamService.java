/**
 * 项目名称:GPS_LMS
 * 包         名:com.cn.gpslms.service
 * 文   件  名:InitSendThreadParamService.java
 * 创 建日期:2016年12月3日-下午6:29:56
 * Copyright @ 2016-YouBus.com.cn
 */
package com.cn.gpslms.service;

import java.util.Map;

import org.apache.log4j.Logger;

import com.infoservice.framework.Service;
import com.infoservice.framework.exceptions.FrameException;

/**
 * 类名称:InitSendThreadParamService
 * 类描述:发送线程参数设置
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2016年12月3日 下午6:29:56
 * 修改备注:
 * @version 1.0.0
 */
public class InitSendThreadParamService implements Service {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.Service#destroyService()
	 */
	public static int ChaosuThreadPoolSize=1;/*向lms发送线超速线程报警池的大小*/
	public static int TingcheThreadPoolSize=1;/*向lms发送线停车报警线程池的大小*/
	public static int PLThreadPoolSize=1;/*向lms发送疲劳报警线程池的大小*/
	
	private static Logger log=Logger.getLogger(InitSendThreadParamService.class);
	
	@Override
	public void destroyService() throws FrameException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.infoservice.framework.Service#getName()
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.getClass().getName();
		
	}

	/* (non-Javadoc)
	 * @see com.infoservice.framework.Service#initialize(java.util.Map)
	 */
	@Override
	public void initialize(Map paramMap) throws FrameException {
		// TODO Auto-generated method stub
		log.debug("InitSendThreadParamService init Begin... ...");
		//ReportExportThreadPoolSize
		if(paramMap.containsKey("ChaosuThreadPoolSize"))ChaosuThreadPoolSize=Integer.valueOf((String)paramMap.get("ChaosuThreadPoolSize"));
		if(paramMap.containsKey("TingcheThreadPoolSize"))TingcheThreadPoolSize=Integer.valueOf((String)paramMap.get("TingcheThreadPoolSize"));
		if(paramMap.containsKey("PLThreadPoolSize"))PLThreadPoolSize=Integer.valueOf((String)paramMap.get("PLThreadPoolSize"));

		log.debug(" 初始化 ChaosuThreadPoolSize :="+ChaosuThreadPoolSize);
		log.debug(" 初始化 TingcheThreadPoolSize :="+TingcheThreadPoolSize);
		log.debug(" 初始化 PLThreadPoolSize :="+PLThreadPoolSize);
		
		log.debug("InitSendThreadParamService init End... ...");
		
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
