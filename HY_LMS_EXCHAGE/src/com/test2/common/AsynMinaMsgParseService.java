/**
 * 项目名称:HY_LMS_EXCHAGE
 * 包         名:com.test2.common
 * 文   件  名:AsynMinaMsgParseService.java
 * 创 建日期:2019年4月1日-下午10:14:19
 * Copyright @ 2019-YouBus.com.cn
 */
package com.test2.common;

import java.util.Map;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.hy.exchange.pofactory.TmExMsgPOFactory;
import com.infoservice.framework.Service;
import com.infoservice.framework.exceptions.FrameException;

/**
 * 类名称:AsynMinaMsgParseService
 * 类描述:从mina 表中读取消息，进行处理（发api请求等）
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2019年4月1日 下午10:14:19
 * 修改备注:
 * @version 1.0.0
 */
public class AsynMinaMsgParseService implements Service {
	private static Logger logger=Logger.getLogger(AsynMinaMsgParseService.class);
	/* (non-Javadoc)
	 * @see com.infoservice.framework.Service#destroyService()
	 */
	private static boolean RUN_TAG=false;
	private static ScheduledThreadPoolExecutor  scheduled = new ScheduledThreadPoolExecutor(2);
	
	
	@Override
	public void destroyService() throws FrameException {
		// TODO Auto-generated method stub
		logger.debug("AsynMinaMsgParseService . destroyService ");
		RUN_TAG=false;
		scheduled.shutdown();
		logger.debug("AsynMinaMsgParseService . scheduled.shutdown ");
	}

	/* (non-Javadoc)
	 * @see com.infoservice.framework.Service#getName()
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return AsynMinaMsgParseService.class.getName();
	}

	/* (non-Javadoc)
	 * @see com.infoservice.framework.Service#initialize(java.util.Map)
	 */
	@Override
	public void initialize(Map paraMap) throws FrameException {
		// TODO Auto-generated method stub
		logger.debug("AsynMinaMsgParseService . initialize");
		RUN_TAG=true;
		String PEROID_TIME_LENGTH_STR=(String) paraMap.get("PEROID_TIME_LENGTH");
		long PEROID_TIME_LENGTH=PEROID_TIME_LENGTH_STR!=null?Long.valueOf(PEROID_TIME_LENGTH_STR):60;
		logger.debug("AsynMinaMsgParseService PEROID_TIME_LENGTH ="+PEROID_TIME_LENGTH);
		scheduled.scheduleWithFixedDelay(new Runnable() {
//		scheduled.scheduleAtFixedRate(new Runnable() {  //将api处理线程，由固定频率改为固定延时
		    @Override
		    public void run() {
		    	if(RUN_TAG){
			    	logger.debug("AsynMinaMsgParseService begin thread parse received mina msg");
			    	TmExMsgPOFactory.parseDBMinaMsg();
			    	logger.debug("AsynMinaMsgParseService end thread parse received mina msg");
		    	}

		    }
		}, 30, PEROID_TIME_LENGTH, TimeUnit.SECONDS);

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
