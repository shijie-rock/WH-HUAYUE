/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.common.util
 * ��   ��  ��:OptLogUtil.java
 * �� ������:2017��8��16��-����11:31:47
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.common.util;

import com.infoservice.framework.datacontext.ActionContext;
import com.truckinspect.common.TruckInsCommonCanstant;
import com.truckinspect.common.bean.OptLogDTO;

/**
 * ������:OptLogUtil
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2017��8��16�� ����11:31:47
 * �޸ı�ע:
 * @version 1.0.0
 */
public class OptLogUtil {
	
	public static void bindOptContext(ActionContext atx,String optTypeCode,String optTypeDesc,String optDesc){
		atx.setObjValue(TruckInsCommonCanstant.CONTEXT_OPT_TAG, new OptLogDTO(optTypeCode,optTypeDesc,optDesc));
	}

}
