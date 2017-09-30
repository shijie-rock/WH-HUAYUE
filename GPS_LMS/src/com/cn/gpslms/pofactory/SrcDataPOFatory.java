/**
 * ��Ŀ����:GPS_LMS
 * ��         ��:com.cn.gpslms.pofactory
 * ��   ��  ��:SrcDataPOFatory.java
 * �� ������:2016��8��23��-����11:15:47
 * Copyright @ 2016-YouBus.com.cn
 */
package com.cn.gpslms.pofactory;

import java.sql.Connection;
import java.util.List;

import com.cn.gpslms.po.EtVehiclePO;
import com.cn.gpslms.po.EtVehicleGpsDataPO;
import com.cn.gpslms.service.SQLCommonService;
import com.cn.gpslms.util.DBConUtil;
import com.infoservice.po.POFactory;

/**
 * ������:SrcDataPOFatory
 * ������:Դ���ݿ�
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2016��8��23�� ����11:15:47
 * �޸ı�ע:
 * @version 1.0.0
 */
public class SrcDataPOFatory extends POFactory {

	/**
	 * ����һ���µ�ʵ�� SrcDataPOFatory.
	 *
	 */
	public SrcDataPOFatory() {
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
	
	public static List<EtVehicleGpsDataPO> querySrcData(Connection srcConn,long baseId){
		String key=getThisClassSimpleName()+"_"+Thread.currentThread().getStackTrace()[1].getMethodName()+"_SQL";
		String sql=SQLCommonService.getSql(key,new String[]{String.valueOf(baseId)});
		try {
			return DBConUtil.getPOResult(srcConn, sql, new EtVehicleGpsDataPO());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return null;
	}
	
	
	/**
	 * ��ȡ����������Ϣ
	 * ��   ��  ��:queryVehicleBaseInfo
	 * ��������:
	 * ��         ��:@param srcConn
	 * ��         ��:@return
	 * ��   ��  ֵ:List<EtVehicle>
	 * ��   ��  ��:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static List<EtVehiclePO> queryVehicleBaseInfo(Connection srcConn){
		
		String key=getThisClassSimpleName()+"_"+Thread.currentThread().getStackTrace()[1].getMethodName()+"_SQL";
		String sql=SQLCommonService.getSql(key,null);
		try {
			return DBConUtil.getPOResult(srcConn, sql, new EtVehiclePO());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return null;
		
	}
	
	private static String getThisClassSimpleName(){
		String className=Thread.currentThread().getStackTrace()[1].getClassName();
		return className.substring(className.lastIndexOf(".")+1);
	}

}
