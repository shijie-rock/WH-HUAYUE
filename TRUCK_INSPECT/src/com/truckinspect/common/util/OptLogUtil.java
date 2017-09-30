/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.common.util
 * 文   件  名:OptLogUtil.java
 * 创 建日期:2017年8月16日-上午11:31:47
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.common.util;

import com.infoservice.framework.datacontext.ActionContext;
import com.truckinspect.common.TruckInsCommonCanstant;
import com.truckinspect.common.bean.OptLogDTO;

/**
 * 类名称:OptLogUtil
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年8月16日 上午11:31:47
 * 修改备注:
 * @version 1.0.0
 */
public class OptLogUtil {
	
	public static void bindOptContext(ActionContext atx,String optTypeCode,String optTypeDesc,String optDesc){
		atx.setObjValue(TruckInsCommonCanstant.CONTEXT_OPT_TAG, new OptLogDTO(optTypeCode,optTypeDesc,optDesc));
	}

}
