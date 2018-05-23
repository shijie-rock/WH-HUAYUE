/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.common.bean
 * 文   件  名:TruckInsCommonCanstant.java
 * 创 建日期:2017年8月16日-上午11:21:42
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.common;

/**
 * 类名称:TruckInsCommonCanstant
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年8月16日 上午11:21:42
 * 修改备注:
 * @version 1.0.0
 */
public class TruckInsCommonCanstant {
	
	public static final String CONTEXT_OPT_TAG="OPT_LOG_BEAN";
	
	public static final String CHECK_ENT_TYPE_TRUCK="CET_0010"; //检查对象类型 车辆
	public static final String CHECK_ENT_TYPE_SERVER="CET_0020";//检查对象类型 服务器
	
	
	
	public static final String OPT_TYPE_LOGIN="OTC_0001";//登录
	public static final String OPT_TYPE_LOGOUT="OTC_0002";//登出
	
	public static final String OPT_TYPE_ROLE_ADD="OTC_0010";//新增
	public static final String OPT_TYPE_ROLE_DEL="OTC_0020";//删除
	public static final String OPT_TYPE_ROLE_MOD="OTC_0030";
	public static final String OPT_TYPE_ROLE_UNV="OTC_0040";
	public static final String OPT_TYPE_ROLE_VAL="OTC_0050";
	
	public static final String OPT_TYPE_INSGROUP_ADD="OTC_0110";//新增
	public static final String OPT_TYPE_INSGROUP_DEL="OTC_0120";//删除
	public static final String OPT_TYPE_INSGROUP_MOD="OTC_0130";//编辑检查组
	public static final String OPT_TYPE_INSGROUP_UNV="OTC_0140";//停用检查组
	public static final String OPT_TYPE_INSGROUP_VAL="OTC_0150";//启用检查组
	
	public static final String OPT_TYPE_MEMBER_ADD="OTC_0210";//新增
	public static final String OPT_TYPE_MEMBER_DEL="OTC_0220";//删除
	public static final String OPT_TYPE_MEMBER_MOD="OTC_0230";//编辑用户
	public static final String OPT_TYPE_MEMBER_UNV="OTC_0240";//停用用户
	public static final String OPT_TYPE_MEMBER_VAL="OTC_0250";//启用用户
	public static final String OPT_TYPE_MEMBER_PAS="OTC_0260";//修改密码
	
	
	public static final String OPT_TYPE_INSPOSITON_ADD="OTC_0310";//新增
	public static final String OPT_TYPE_INSPOSITON_DEL="OTC_0320";//删除
	public static final String OPT_TYPE_INSPOSITON_MOD="OTC_0330";//编辑检查地点
	public static final String OPT_TYPE_INSPOSITON_UNV="OTC_0340";//停用检查地点
	public static final String OPT_TYPE_INSPOSITON_VAL="OTC_0350";//启用检查地点
	
	public static final String OPT_TYPE_OBJSUP_ADD="OTC_0410";//新增一级分类
	public static final String OPT_TYPE_OBJSUP_DEL="OTC_0420";//删除一级分类
	public static final String OPT_TYPE_OBJSUP_MOD="OTC_0430";//编辑一级分类
	public static final String OPT_TYPE_OBJSUP_UNV="OTC_0440";//停用一级分类
	public static final String OPT_TYPE_OBJSUP_VAL="OTC_0450";//启用一级分类
	
	public static final String OPT_TYPE_OBJMID_ADD="OTC_0510";//新增二级分类
	public static final String OPT_TYPE_OBJMID_DEL="OTC_0520";//删除二级分类
	public static final String OPT_TYPE_OBJMID_MOD="OTC_0530";//编辑二级分类
	public static final String OPT_TYPE_OBJMID_UNV="OTC_0540";//停用二级分类
	public static final String OPT_TYPE_OBJMID_VAL="OTC_0550";//启用二级分类
	
	public static final String OPT_TYPE_OBJSUB_ADD="OTC_0610";//新增三级分类
	public static final String OPT_TYPE_OBJSUB_DEL="OTC_0620";//删除三级分类
	public static final String OPT_TYPE_OBJSUB_MOD="OTC_0630";//编辑三级分类
	public static final String OPT_TYPE_OBJSUB_UNV="OTC_0640";//停用三级分类
	public static final String OPT_TYPE_OBJSUB_VAL="OTC_0650";//启用三级分类
	
	public static final String OPT_TYPE_ENTSUP_ADD="OTC_0710";//新增检查对象大类
	public static final String OPT_TYPE_ENTSUP_DEL="OTC_0720";//删除检查对象大类
	public static final String OPT_TYPE_ENTSUP_MOD="OTC_0730";//编辑检查对象大类
	public static final String OPT_TYPE_ENTSUP_UNV="OTC_0740";//停用检查对象大类
	public static final String OPT_TYPE_ENTSUP_VAL="OTC_0750";//启用检查对象大类
	
	public static final String OPT_TYPE_ENTMID_ADD="OTC_0810";//新增检查对象中类
	public static final String OPT_TYPE_ENTMID_DEL="OTC_0820";//删除检查对象中类
	public static final String OPT_TYPE_ENTMID_MOD="OTC_0830";//编辑检查对象中类
	public static final String OPT_TYPE_ENTMID_UNV="OTC_0840";//停用检查对象中类
	public static final String OPT_TYPE_ENTMID_VAL="OTC_0850";//启用检查对象中类

	
	public static final String OPT_TYPE_ENTSUB_ADD="OTC_0910";//新增检查对象小类
	public static final String OPT_TYPE_ENTSUB_DEL="OTC_0920";//删除检查对象小类
	public static final String OPT_TYPE_ENTSUB_MOD="OTC_0930";//编辑检查对象小类
	public static final String OPT_TYPE_ENTSUB_UNV="OTC_0940";//停用检查对象小类
	public static final String OPT_TYPE_ENTSUB_VAL="OTC_0950";//启用检查对象小类

	public static final String OPT_TYPE_ENTOBJ_VAL="OTC_1000";//编辑检查对象小类对应检查项目小类
	
	public static final String OPT_TYPE_TRUCK_ADD="OTC_1110";//新增车辆
	public static final String OPT_TYPE_TRUCK_DEL="OTC_1120";//删除车辆
	public static final String OPT_TYPE_TRUCK_MOD="OTC_1130";//编辑车辆
	public static final String OPT_TYPE_TRUCK_UNV="OTC_1140";//停用车辆
	public static final String OPT_TYPE_TRUCK_VAL="OTC_1150";//启用车辆
	
	public static final String OPT_TYPE_OBJITEM_ADD="OTC_1210";//新增检查项目
	public static final String OPT_TYPE_OBJITEM_DEL="OTC_1220";//删除检查项目
	public static final String OPT_TYPE_OBJITEM_MOD="OTC_1230";//编辑检查项目
	public static final String OPT_TYPE_OBJITEM_UNV="OTC_1240";//停用检查项目
	public static final String OPT_TYPE_OBJITEM_VAL="OTC_1250";//启用检查项目
	
	//20180507 ADD
	public static final String OPT_TYPE_WEBCHECKORDER_ADD="OTC_1310";//WEB新增检查单
	public static final String OPT_TYPE_WEBCHECKORDER_MOD="OTC_1320";//WEB编辑检查单
	public static final String OPT_TYPE_WEBCHECKORDER_DEL="OTC_1330";//WEB删除检查单
	public static final String OPT_TYPE_WEBCHECKORDER_UNV="OTC_1340";//WEB停用检查单
	public static final String OPT_TYPE_WEBCHECKORDER_VAL="OTC_1350";//WEB启用检查单
	public static final String OPT_TYPE_WEBCHECKORDER_PLB="OTC_1360";//WEB日历更新检查单计划时间
	
	public static final String OPT_TYPE_WEBCHECKORDERITEM_MOD="OTC_1420";//WEB编辑检查单检查明细
	public static final String OPT_TYPE_WEBCHECKORDERITEM_DEL="OTC_1430";//WEB删除检查单检查明细
	public static final String OPT_TYPE_WEBCHECKORDERITEM_UNV="OTC_1440";//WEB停用检查单检查明细
	public static final String OPT_TYPE_WEBCHECKORDERITEM_VAL="OTC_1450";//WEB启用检查单检查明细
	
	public static final String BASE_DATA_CHECK_ORDER_STATUS_UNCHECK="COS_0010";//检查单状态 待检查
	
	public static final String BASE_DATA_CHECK_ORDER_RESULT_UNDONE="COR_0010";//检查单结果 未完成
	
	public static final String BASE_DATA_CHECK_ITEM_STATUS_UNDONE="CIS_0010";//检查项目检查状态 待检查

	
}
