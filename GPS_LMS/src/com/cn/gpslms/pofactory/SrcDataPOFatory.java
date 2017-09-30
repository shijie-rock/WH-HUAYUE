/**
 * 项目名称:GPS_LMS
 * 包         名:com.cn.gpslms.pofactory
 * 文   件  名:SrcDataPOFatory.java
 * 创 建日期:2016年8月23日-下午11:15:47
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
 * 类名称:SrcDataPOFatory
 * 类描述:源数据库
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2016年8月23日 下午11:15:47
 * 修改备注:
 * @version 1.0.0
 */
public class SrcDataPOFatory extends POFactory {

	/**
	 * 创建一个新的实例 SrcDataPOFatory.
	 *
	 */
	public SrcDataPOFatory() {
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
	 * 获取车辆基本信息
	 * 方   法  名:queryVehicleBaseInfo
	 * 方法描述:
	 * 参         数:@param srcConn
	 * 参         数:@return
	 * 返   回  值:List<EtVehicle>
	 * 创   建  人:rock
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
