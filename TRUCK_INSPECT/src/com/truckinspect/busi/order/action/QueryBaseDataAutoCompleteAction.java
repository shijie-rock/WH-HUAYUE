/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.order.action
 * 文   件  名:QueryBaseDataAutoCompleteAction.java
 * 创 建日期:2018年5月3日-上午11:03:42
 * Copyright @ 2018-YouBus.com.cn
 */
package com.truckinspect.busi.order.action;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.DynaBean;
import com.truckinspect.busi.order.pofactory.TmInsCheckOrderPOFactory;

/**
 * 类名称:QueryBaseDataAutoCompleteAction
 * 类描述:模糊输入，查询检查人，检查地点，检查对象信息等
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2018年5月3日 上午11:03:42
 * 修改备注:
 * @version 1.0.0
 */
public class QueryBaseDataAutoCompleteAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String queryType=atx.getStringValue("QUERY_TYPE");//TARGET:检查对象名称如车牌;POSITION:检查地点名称;CHECKER:检查人姓名;
		String name=atx.getStringValue("QUERY_NAME");//名称模糊，或者空格等
		
		if(StringUtils.isBlank(queryType)){
			
			logger.error("根据查询条件模糊查询：查询类型参数为空");
			atx.setErrorContext("BUSI_DATA_QUERY_BASEDATA_AUTO_COMPLETE_ACTION_ERR_1000", "根据查询条件模糊查询：查询类型参数为空", null);
			return 0;
		}
		Connection conn=atx.getConnection();
		List<DynaBean> list=null;
		if(StringUtils.isNotBlank(queryType)){
			if("TARGET".equals(queryType)){
				list=TmInsCheckOrderPOFactory.queryCheckTargetAutoComplete(conn, name);
			}else if("POSITION".equals(queryType)){
				list=TmInsCheckOrderPOFactory.queryCheckPositionAutoComplete(conn, name);
				
			}else if("CHECKER".equals(queryType)){
				list=TmInsCheckOrderPOFactory.queryCheckerInfoAutoComplete(conn, name);
			}else{
				logger.error("根据查询条件模糊查询：不支持查询类型");
				atx.setErrorContext("BUSI_DATA_QUERY_BASEDATA_AUTO_COMPLETE_ACTION_ERR_2000", "根据查询条件模糊查询：不支持查询类型", null);
				return 0;
			}
			if(list!=null){
				atx.setArrayValue("RESULT_LIST", list.toArray());
			}
		}
		return 1;
	}

}
