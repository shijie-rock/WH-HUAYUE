/**
 * ��Ŀ����:GPS_LMS
 * ��         ��:com.cn.gpslms.service
 * ��   ��  ��:GetSrcVehicleInfoService.java
 * �� ������:2016��9��11��-����8:51:19
 * Copyright @ 2016-YouBus.com.cn
 */
package com.cn.gpslms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.cn.gpslms.po.EtVehiclePO;
import com.cn.gpslms.po.NewVehiclePO;
import com.cn.gpslms.pofactory.SrcDataPOFatory;
import com.cn.gpslms.pofactory.TargetDataPOFactory;
import com.infoservice.framework.Service;
import com.infoservice.framework.exceptions.FrameException;
import com.infoservice.framework.services.DBService;
import com.infoservice.po.POFactory;

/**
 * ������:GetSrcVehicleInfoService
 * ������:��ʱ��oracle���л�ȡ����������Ϣ�����ƣ��Ƿ�Σ��Ʒ���ȣ�,ͬ����map��
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2016��9��11�� ����8:51:19
 * �޸ı�ע:
 * @version 1.0.0
 */
public class GetSrcVehicleInfoService implements Service {
	private static Logger log=Logger.getLogger(GetSrcVehicleInfoService.class);
	
	private static int SKIP_TIME=60;//���� ��oracle�л�ȡ����������Ϣ
	Connection srcCon=null;
	Connection targetCon=null;
	 
	
	/* (non-Javadoc)
	 * @see com.infoservice.framework.Service#destroyService()
	 */
	@Override
	public void destroyService() throws FrameException {
		// TODO Auto-generated method stub
		try {
			if(srcCon!=null)srcCon.close();
			if(targetCon!=null)targetCon.close();
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
	public void initialize(Map arg0) throws FrameException {
		// TODO Auto-generated method stub
		srcCon=DBService.getInstance().getConnection("dataSourceOra");
		targetCon=DBService.getInstance().getConnection();
		
		synVehicleData(srcCon, targetCon);//ͬ������������Ϣ
    	log.debug("��ʼִ�� �ӱ��ؿ��л�ȡ����������Ϣ-ˢ�½��뻺�� ");
		TargetDataPOFactory.refreshVehicle(targetCon);//ˢ�½���Map
		log.debug("����ִ�� �ӱ��ؿ��л�ȡ����������Ϣ-ˢ�½��뻺��");
		
	    Timer timer = new Timer();
	    timer.schedule(new TimerTask() {
	      public void run() {
	    	log.debug("��ʼִ�� ��oracle���л�ȡ����������Ϣ ");
	        System.out.println("-------��ʼִ�� ��oracle���л�ȡ����������Ϣ --------");
	       
	        synVehicleData(srcCon, targetCon);
			TargetDataPOFactory.refreshVehicle(targetCon);

	        System.out.println("-------����ִ�� ��oracle���л�ȡ����������Ϣ --------");
	        log.debug("����ִ�д�oracle���л�ȡ����������Ϣ ");
	      }
	    }, SKIP_TIME*60*1000, SKIP_TIME*60*1000);
	}
	
	private static void synVehicleData(Connection srcConn,Connection targetConn){
		try{
		List<EtVehiclePO> srcList= SrcDataPOFatory.queryVehicleBaseInfo(srcConn);
		NewVehiclePO newVehiclePO;
		if(srcList!=null&&srcList.size()>0){
		targetConn.setAutoCommit(false);
		newVehiclePO=new NewVehiclePO();
//		newVehiclePO.setlicense();//��ɾ�� ԭlicense
		POFactory.delete(targetConn, newVehiclePO);
		for(EtVehiclePO srcBean:srcList){
			newVehiclePO=new NewVehiclePO();
			BeanUtils.copyProperties(newVehiclePO, srcBean);
//			newVehiclePO.setid//������ id
			POFactory.insert(targetConn, newVehiclePO);
			
			}
		targetConn.commit();
		 }
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
		}
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

}
