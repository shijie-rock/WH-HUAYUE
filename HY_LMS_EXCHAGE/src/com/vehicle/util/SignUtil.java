/**
 * 项目名称:GPS_DATA
 * 包         名:com.vehicle.util
 * 文   件  名:SignUtil.java
 * 创 建日期:2017年4月10日-下午11:17:34
 * Copyright @ 2017-YouBus.com.cn
 */
package com.vehicle.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.vehicle.common.CommonConstant;
import com.youbus.framework.comm.remoteclient.ParamBean;

/**
 * 类名称:SignUtil
 * 类描述:签名算法
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年4月10日 下午11:17:34
 * 修改备注:
 * @version 1.0.0
 */
public class SignUtil {
	private static Logger log = LogManager.getLogger(SignUtil.class);
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
	
//	md5（密钥+参数按字母升序排列+密钥）后的大写字母
	
	public static String getSign(List<ParamBean> paramList){
		if(paramList==null||paramList.size()<1)return null;
		Map<String,String> paramMap = new TreeMap<String,String>();
		for(ParamBean pBean:paramList){
			paramMap.put(pBean.getParamName(), pBean.getParamValue());
		}
		Set keySet=paramMap.keySet();
		Iterator<String> iter=keySet.iterator();
		StringBuffer sbf=new StringBuffer();
		sbf.append(CommonConstant.SERCRET);
		while(iter.hasNext()){
			String key=iter.next();
			String value=paramMap.get(key);
			sbf.append(key+value);
		}
		sbf.append(CommonConstant.SERCRET);
		log.debug("src sign :="+sbf.toString());
		String paramMD5=DigestUtils.md5Hex(sbf.toString());
		return paramMD5.toUpperCase();
	}
	
	

}
