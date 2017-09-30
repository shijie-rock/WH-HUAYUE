/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.organize.action
 * 文   件  名:QueryMemberDetailAction.java
 * 创 建日期:2017年9月2日-上午9:35:00
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.busi.organize.action;

import java.sql.Connection;

import org.apache.commons.lang.StringUtils;

import com.info.base.po.TmSysMemberPO;
import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.POFactory;
import com.truckinspect.busi.organize.po.TmInspactGroupPO;

/**
 * 类名称:QueryMemberDetailAction
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年9月2日 上午9:35:00
 * 修改备注:
 * @version 1.0.0
 */
public class QueryMemberDetailAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		String memberId=atx.getStringValue("MEMBER_ID");
		//check param
		if(StringUtils.isBlank(memberId)){
			logger.error(" PARAM MEMBER_ID IS EMTPY .");
			atx.setErrorContext("ORGANIZE_MEMBER_DETAIL_ACTION_ERR_1000", "查询用户明细：参数为空", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		
		TmSysMemberPO memberPOCon=new TmSysMemberPO();
		memberPOCon.setStatus("1");
		memberPOCon.setId(Integer.valueOf(memberId));
		memberPOCon=POFactory.getByPriKey(conn, memberPOCon);
		
		if(memberPOCon!=null){
			
			atx.setObjValue("MEMBER_BEAN", memberPOCon);
		}
		else{
			logger.error("用户不存在");
			atx.setErrorContext("ORGANIZE_MEMBER_DETAIL_ACTION_ERR_2000", "查询用户明细：用户不存在", null);
			return 0;
		}
//		atx.setStringValue("MSG", "处理成功");
		return 1;
	}

}
