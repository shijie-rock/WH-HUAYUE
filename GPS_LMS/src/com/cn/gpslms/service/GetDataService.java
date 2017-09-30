/**
 * 项目名称:GPS_LMS
 * 包         名:com.cn.gpslms.service
 * 文   件  名:GetDataService.java
 * 创 建日期:2016年8月23日-下午9:31:16
 * Copyright @ 2016-YouBus.com.cn
 */
package com.cn.gpslms.service;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.cn.gpslms.po.EtVehicleGpsDataPO;
import com.cn.gpslms.po.NewVehicleGpsDataPO;
import com.cn.gpslms.pofactory.SrcDataPOFatory;
import com.cn.gpslms.pofactory.TargetDataPOFactory;
import com.cn.gpslms.service.thread.ChaosuSender;
import com.cn.gpslms.service.thread.PLSender;
import com.cn.gpslms.service.thread.TingcheSender;
import com.infoservice.framework.Service;
import com.infoservice.framework.exceptions.FrameException;
import com.infoservice.framework.services.DBService;
import com.infoservice.po.POFactory;

/**
 * 类名称:GetDataService
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2016年8月23日 下午9:31:16
 * 修改备注:
 * @version 1.0.0
 */
public class GetDataService implements Service {
	
	private static Logger log=Logger.getLogger(GetDataService.class);
	
	static boolean threadRunTag=false;
	int defaultSkipTime=30;//缺省获取数据间隔
	String defaultDataSourceName="dataSourceOra";
	/**
	 * 创建一个新的实例 GetDataService.
	 *
	 */
	public GetDataService() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.infoservice.framework.Service#destroyService()
	 */
	@Override
	public void destroyService() throws FrameException {
		// TODO Auto-generated method stub
		threadRunTag=false;
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
		int skipTime=paramMap.get("SkipTime")!=null?Integer.valueOf((String)paramMap.get("SkipTime")):defaultSkipTime;
		String srcDataSourceName=paramMap.get("DataSourceName")!=null?(String)paramMap.get("DataSourceName"):defaultDataSourceName;
		threadRunTag=true;
		Thread thread=new Thread(new GetDataThread(srcDataSourceName,skipTime));
		thread.start();
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
class GetDataThread implements Runnable{
	private static Logger log=Logger.getLogger(GetDataThread.class);
	private String srcDSName;
	Connection srcConn;
	Connection targetConn;
	int skipTime;
	
	GetDataThread(String srcDSName,int skipTime){
		this.srcDSName=srcDSName;
		this.skipTime=skipTime;
		srcConn=DBService.getInstance().getConnection(srcDSName);
		targetConn=DBService.getInstance().getConnection();
		log.debug("Src Connection [ dataSource name="+srcDSName+" ]:="+srcConn);
		log.debug("tagartConn Connection:="+targetConn);
		log.debug(" GetDataThread INIT [start after 10S] : "+this);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(10000);
			log.debug(" GetDataThread start run : "+this);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("GetDataThread start sleep error "+e.getMessage());
		}//
		log.debug("GetDataThread start"+this);
		while(GetDataService.threadRunTag){
			if(srcConn==null||targetConn==null){
				log.error(" Connection is null:="+(srcConn==null?"srcConn":"targetConn")+" Thread sleep 60s");
				
				try {
					Thread.sleep(60*1000);
					if(srcConn==null)srcConn=DBService.getInstance().getConnection(srcDSName);
					if(targetConn==null)targetConn=DBService.getInstance().getConnection();
					continue;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					log.error("GetDataThread DataSource Reinit sleep error "+e.getMessage());
				}
			}
			try {

				//query mysql max id
				long baseId=TargetDataPOFactory.queryImportDataBaseId(targetConn);
				//query oracle src
				List<EtVehicleGpsDataPO> srcList= SrcDataPOFatory.querySrcData(srcConn, baseId);//取大于mysql表中大于最大id，100条数据
				//insert target
				if(srcList!=null&&srcList.size()>0){
					List<NewVehicleGpsDataPO> targetList=new ArrayList<NewVehicleGpsDataPO>();
					for(EtVehicleGpsDataPO temp:srcList){
						if(temp==null)continue;
						if(temp.getEtvdId()==null){
							log.error(" SRC DATA ETVD_ID IS NULL:"+temp.toXMLString());
							continue;
						}
						log.debug("--GET SRC DATA:"+temp.toXMLString());
						NewVehicleGpsDataPO targetBean=new NewVehicleGpsDataPO();
						try {
							BeanUtils.copyProperties(targetBean, temp);
							
							targetBean.setImportTime(new Date(System.currentTimeMillis()));
							targetBean.setImportBy(-1);
							targetBean.setStatus("1");
							targetBean.setParseStatus("0");
							
							log.debug("--PACK TARGET DATA:"+targetBean.toXMLString());	
							targetConn.setAutoCommit(false);
							POFactory.insert(targetConn, targetBean);
							targetConn.commit();
							log.debug("--TARGET DATA INSERT END [ ETVD_ID=:"+targetBean.getEtvdId()+" ]");
							
							SendBaseGPSDataToLMSService.inputCurrentGpsInfoBySimNo(targetBean);
							
//							AlarmFactory.chaosuJudge(temp);//
							ChaosuSender.getInstance().chaosuJudge(temp);//20161203 改为 thread
							
//							AlarmFactory.tingcheJudge(temp);
							TingcheSender.getInstance().tingcheJudge(temp);//20161203 改为 thread
							
							PLSender.getInstance().PLJudge(temp);
							
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							log.error(" BeanUtils.copyProperties ERROR.");
							log.error(e.getMessage());
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							log.error(" BeanUtils.copyProperties ERROR.");
							log.error(e.getMessage());
						} catch (Throwable t){
							t.printStackTrace();
							log.error(" BeanUtils.copyProperties ERROR.");
							log.error(t.getMessage());
						}
					}
					
				}
				
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				log.error(" Thread error ");
//				log.error(e.getMessage());
			} catch (Throwable t){
				t.printStackTrace();
				log.error(" Thread error");
				log.error(t.getMessage());
			}

			try {
				Thread.sleep(skipTime*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error(" Thread.sleep [" +(skipTime*1000)+ "] ERROR");
				log.error(e.getMessage());
			}
			}
		
		
		//end while

		try {
			closeConn();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(" close conn error");
			log.error(e.getMessage());
		} catch (Throwable t){
			t.printStackTrace();
			log.error(" close conn error");
			log.error(t.getMessage());
		}
	
	}
	
	private void closeConn() throws SQLException{
		if(srcConn!=null)srcConn.close();
		if(targetConn!=null)targetConn.close();
	}
	
}
