package com.youbus.framework.comm;
public class CommonConstant
{
	public static final String USER="userName";
	public static final String VALID_DATE="timestamp";
	public static final String VAR="var";
	public static final String VAR_VALUE_1="1";
	public static final String SIGN="sign";
	public static final String DOMAIN=".gold-tech.com.cn";
	public static final int MAX_AGE=-1;
	public static final String INDUSTRY_ID="industryId";
	public static final String SIGN_PWD="JINDE115";
	public static final String EP_WX_ID="entityWeiXinId";
	public static final String TT_USER="ttuser";
	public static final String RUN_WEBSITE="http://qiwei.gold-tech.com.cn/JDQW/";
	public static final String SAFE_WEBSITE="http://qiwei.gold-tech.com.cn/JDQW_SAFE_CENTER/";
	public static final String USER_EP_WX_ID="userEpWxId";
	public static final String WEIXINUSER_INDUSTRY_ID="weixinUserIndustryId";
	public static final String USER_ENTITY_NAME="userEntityName";
	public static final String ROLE_MENU="MENUS";
	public static final int SYSTEM_USER= 123456;
	public static final int IS_ADMIN=12781001;
	public static final int ROLE_ADMIN=90000001;
	public static final int ROLE_COMMON=10000001;
	public static final String CLENT_IP = "clentIP";
	//超级管理员用户名
	public static final String SUPER_USER_NAME= "9997";
	//超级角色
	public static final String SUPER_ROLE = SUPER_USER_NAME;
	public static final String USR_ANONYOUS="111111";
	public static final String USR_SYSTEM = "999999";
	public static final String ROLE_ANONYOUS = "222222";
	
	
	public static final String DEFAULT_USER="jdqw";
	//更换手机验证码
	public static final int CODE_TYPE_CHANGE_PHONE=4;
	//绑定手机验证码
	public static final int CODE_TYPE_BIND_PHONE=5;
	//激活邮箱验证码
	public static final int CODE_TYPE_ACTIVE_MAIL=1;
	//找回密码邮箱验证码
	public static final int CODE_TYPE_FPWD_MAIL=2;
	//更换邮箱验证码
	public static final int CODE_TYPE_CHANGE_MAIL=3;
	//找回密码手机验证码
	public static final int CODE_TYPE_FPWD_PHONE=6;
	//金的企微自用公众号主键id
	public static  final int DEFAULT_WX_ID = 1000;
	//汽车行业注册后默认的功能
	public static  final int DEFAULT_AUTO_WX_ID = 100001;
	//教育行业注册后默认的功能
	public static  final int DEFAULT_EDU_WX_ID = 100002;
	//科技行业注册后默认的功能
	public static  final int DEFAULT_TECH_WX_ID = 100003;
	//婚庆行业注册后默认的功能
	public static  final int DEFAULT_WEDDING_WX_ID = 100004;
	//投诉内容
	public static final int CONSTANT_COMPLAINT_OLD=10081001;
	public static final int CONSTANT_COMPLAINT_BLUE=10081002;
	public static final int CONSTANT_COMPLAINT_BAD=10081003;
	public static final int CONSTANT_COMPLAINT_ILLEGAL=10081004;
	//内容投诉状态
	public static final int CONSTANT_COMPLAINT_STATUS_NEW=10091001;
	public static final int CONSTANT_COMPLAINT_STATUS_DEALED=10091002;
	public static final int YES = 12781001;
	public static final int NO = 12781002;
	
	public static final int PAGE_SIZE = 5;
	/**
	 * action执行正确返回1
	 */
	public static final int ACTION_SUCCESS_RETURN_CODE = 1;

	/**
	 * 
	 * action执行中出现异常返回0
	 */
	public static final int ACTION_FAILURE_RETURN_CODE = 0;
	
	/**
	 * 抛异常的时的错误码
	 */
	public static final String ACTION_FAILURE_ERROR_CODE = "-1";

}