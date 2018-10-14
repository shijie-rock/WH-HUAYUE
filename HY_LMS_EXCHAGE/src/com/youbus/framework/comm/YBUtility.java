/**
 * 项目名称:AGENT_CENTER
 * 包         名:com.youbus.framework.comm
 * 文   件  名:YBUtility.java
 * 创 建日期:2015年4月10日-下午6:57:06
 * Copyright @ 2015-YouBus.com.cn
 */
package com.youbus.framework.comm;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import net.sf.ehcache.Element;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import sun.misc.BASE64Decoder;

import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.DynaBean;
import com.youbus.paycenter.base.po.TsDataDicPO;

/**
 * 类名称:YBUtility
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2015年4月10日 下午6:57:06
 * 修改备注:
 * @version 1.0.0
 */
public class YBUtility {

	/**
	 * 创建一个新的实例 YBUtility.
	 *
	 */
	public YBUtility() {
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
		System.out.println(getDicDescByCode("ASSIGN_STATUS","AS_0010"));

		//List.add(2015-05-01);
	}
	
	/**
	 * 
	 * 方   法  名:getBillNo
	 * 方法描述:调用存储过程生成单号序列
	 * 参         数:@param conn
	 * 参         数:@param roType 单据类型（2位类似RO）
	 * 参         数:@param sysCode企业（车队，个人用户）编号4位（类似AAA1）
	 * 参         数:@return
	 * 参         数:@throws SQLException
	 * 返   回  值:String  ROAAA11504100002 
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static String getBillNo(Connection conn, String roType,String sysCode)
			throws SQLException {
		CallableStatement stmt = null;
		try {
			String BillNo = "0";
			String errcode;
			stmt = conn.prepareCall(" call p_getbillno(?,?,?,?) ");
			stmt.setString(1, roType);
			stmt.setString(2, sysCode);
			stmt.registerOutParameter(3, Types.VARCHAR);
			stmt.registerOutParameter(4, Types.VARCHAR);
			stmt.execute();
			BillNo = stmt.getString(4);
			errcode = stmt.getString(3);
			if (!"0000000000".equalsIgnoreCase(errcode)) {
				return "";
			}
			return BillNo;
		} catch (SQLException Serr) {
			throw Serr;
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
			}
		}
	}
	
	public static String getRandomString(int length) { //length表示生成字符串的长度
	    String base = "abcdefghijklmnopqrstuvwxyz0123456789";   
	    Random random = new Random();   
	    StringBuffer sb = new StringBuffer();   
	    for (int i = 0; i < length; i++) {   
	        int number = random.nextInt(base.length());   
	        sb.append(base.charAt(number));   
	    }   
	    return sb.toString();   
	 }  
	
	/**
	 * 获取随机的数字，用于处理手机验证码等
	 * 方   法  名:getRandomNumString
	 * 方法描述:
	 * 参         数:@param length
	 * 参         数:@return
	 * 返   回  值:String
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static String getRandomNumString(int length) { //length表示生成字符串的长度
	    String base = "123456789";   
	    Random random = new Random();   
	    StringBuffer sb = new StringBuffer();   
	    for (int i = 0; i < length; i++) {   
	        int number = random.nextInt(base.length());   
	        sb.append(base.charAt(number));   
	    }   
	    return sb.toString();   
	 }  


	// 将 s 进行 BASE64 编码 
	public static String getBASE64(String s) { 
	if (s == null) return null; 
	return (new sun.misc.BASE64Encoder()).encode( s.getBytes() ); 
	} 
	 
	// 将 BASE64 编码的字符串 s 进行解码 
	public static String getFromBASE64(String s) { 
	if (s == null) return null; 
	BASE64Decoder decoder = new BASE64Decoder(); 
	try { 
	byte[] b = decoder.decodeBuffer(s); 
	return new String(b); 
	} catch (Exception e) { 
	return null; 
	} 
	}

	/**
	 * 方   法  名:getDicDescByCode
	 * 方法描述: 根据字典的 typeCode 和 code 字典码描述
	 * 参         数:@param typeCode
	 * 参         数:@param code
	 * 参         数:@return
	 * 返   回  值:String codeDesc
	 * 创   建  人:Ivin
	 * @exception
	 * @since  1.0.0
	*/
	public static String getDicDescByCode(String typeCode,String code){
		if(StringUtils.isBlank(typeCode))
			return StringUtils.EMPTY;
		
		String codeDesc = code;
		Element e1=YoubusNativeCacheService.DATA_DIC_CACHE.get(typeCode);
		List<TsDataDicPO> busGradeS=(List<TsDataDicPO>)e1.getValue();
		for (TsDataDicPO tsDataDicPO : busGradeS) {
			if(StringUtils.trim(code).equals(tsDataDicPO.getCode())  ){
				codeDesc = tsDataDicPO.getCodeDesc();
			}
		}
		return codeDesc;
	}
	
	/**
	 * 
	 * 方   法  名:getAllFAOHtmlFromFAOBeanList
	 * 方法描述: 从库里查出所有option对应的按钮，二级菜单，一级菜单的信息，包装功能树状结构。
	 * 参         数:@return 参见：TmAgentFunctionPOFactory.queryAgentFAOBeanList() 
	 * DynaBean column：
	 * A.FUN_CODE,A.FUN_NAME,A.FUN_DESC ,B.ACTION_CODE,B.ACTION_NAME,B.ACTION_DESC,B.ACTION_URL,
	   C.OPTION_CODE,C.OPTION_NAME,C.OPTION_DESC,C.OPTION_URL,C.BUTTON_ID,C.FR_ACTION_ID
	 * 返   回  值:String
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static String getAllFAOHtmlFromFAOBeanList(List<DynaBean> list){
		if(CollectionUtils.isEmpty(list)){
			return "";
		}
		
		StringBuffer htmlStr=new StringBuffer("<div class=\"tree well\">");
		String currentFCode="" ,currentACode="",tempFCode="" ,tempACode="";
		for(DynaBean t:list){
			tempFCode=t.getString("FUN_CODE");
			tempACode=t.getString("ACTION_CODE");
			if(tempFCode!=null&&!tempFCode.equals(currentFCode)){//new level 1
				if(currentACode!="")htmlStr.append("</ul></li>");//end last level 1
				if(currentFCode!="")htmlStr.append("</ul></li></ul>");//end last level 1
				htmlStr.append("<ul class=\"treeUl\" id=\""+t.getString("FUN_CODE")+"_MAIN\"><li>"
					         + "<span id=\""+t.getString("FUN_CODE")+"\"><i class=\"icon-folder-open\"></i> "+t.getString("FUN_NAME")+"</span>"
						     + "<label class=\"checkbox supinline\">"
						     + "<input type=\"checkbox\" style=\"margin:0px;\" class=\"supCheckbox\"/>"
						     + "</label>");
				htmlStr.append("<ul>"
						     + " <li class=\"firstLi\" >"
						     + " <span id=\""+t.getString("ACTION_CODE")+"\"><i class=\"icon-minus-sign\"></i> "+t.getString("ACTION_NAME")+"</span>"
						     + " <label class=\"checkbox supinline\">"
						     + " <input type=\"checkbox\" style=\"margin:0px;\" class=\"supCheckbox\"></input></label><lable>"
//						     + " &nbsp;&nbsp;<a class=\"specialCfgA\" id=\"spacial-cfg-"+t.getString("ACTION_CODE")+"\">设置特殊权限</a> "
						     + " </label>");
				htmlStr.append(" <ul><li class=\"sonli firstLi\"> "
						     + " <span id=\""+t.getString("OPTION_CODE")+"\"><i class=\"icon-remove\"></i> "+t.getString("OPTION_NAME")+""
						     + " </span></li>");								
			}else{
				if(tempACode!=null&&tempACode.equals(currentACode)){//old level 2
					htmlStr.append("<li class=\"sonli firstLi\"> "
						     + " <span id=\""+t.getString("OPTION_CODE")+"\"><i class=\"icon-remove\"></i> "+t.getString("OPTION_NAME")+""
						     + " </span></li>");		
				}else if(tempACode!=null&&!tempACode.equals(currentACode)){//new level 2
				htmlStr.append("</ul></li>");
				htmlStr.append(""
						     + " <li class=\"firstLi\" >"
						     + " <span id=\""+t.getString("ACTION_CODE")+"\"><i class=\"icon-minus-sign\"></i> "+t.getString("ACTION_NAME")+"</span>"
						     + " <label class=\"checkbox supinline\">"
						     + " <input type=\"checkbox\" style=\"margin:0px;\" class=\"supCheckbox\"></input></label><lable>"
//						     + " &nbsp;&nbsp;<a class=\"specialCfgA\" id=\"spacial-cfg-"+t.getString("ACTION_CODE")+"\">设置特殊权限</a> "
						     + " </label>");
				htmlStr.append(" <ul><li class=\"sonli firstLi\"> "
						     + " <span id=\""+t.getString("OPTION_CODE")+"\"><i class=\"icon-remove\"></i> "+t.getString("OPTION_NAME")+""
						     + " </span></li>");	
				}
			}
			currentFCode=tempFCode;	
			currentACode=tempACode;
		}
		htmlStr.append("</ul></li></ul></li></ul></div>");
		System.out.println("htmlStr:="+htmlStr);
		return htmlStr.toString();
	}
	
	/**
	 * 方   法  名:getUserFAOHtml
	 * 方法描述:根据用户memberID查找全部一级二次单
	 * 参         数:@param conn
	 * 参         数:@param memberId
	 * 参         数:@return
	 * 返   回  值:String
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
//	public static String getUserFAOHtml(Connection conn,int memberId,int agentId){
//		String sql=" SELECT ID,AGENT_ID, ROLE_ID,ROLE_CODE,OPTION_CODE,OPTION_NAME,OPTION_URL,BUTTON_ID,FR_ACTION_ID,FUN_CODE,FUN_NAME,ACTION_CODE,ACTION_NAME,ACTION_URL,"
//				+ " B.FUN_ICON "
//				 + " FROM TT_AGENT_ROLE_ACTION A ,TM_AGENT_FUNCTION B "
//				 + " WHERE A.FUN_CODE=B.FUN_CODE "
//				 + " AND A.STATUS='1' AND B.STATUS='1' "
//				 + " AND A.AGENT_ID="+agentId +" AND A.MEMBER_ID="+memberId;
//		try {
//			List<DynaBean> list=DBConUtil.getResult(conn, sql, "FAOBEAN");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	/**
	 * 从上下文里获取当前用户MemberId
	 * 方   法  名:getMemberId
	 * 方法描述:
	 * 参         数:@param atx
	 * 参         数:@return
	 * 返   回  值:int
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static int getMemberId(ActionContext atx){
		String memberIdStr=atx.getStringValue(YBCommonContant.SESSION_USER_ID);
		return memberIdStr!=null&&memberIdStr.trim().length()>0?Integer.valueOf(memberIdStr):0;
	}
	
	public static int getAgentEmpId(ActionContext atx){
		String empIdStr=atx.getStringValue(YBCommonContant.SESSION_EMP_ID);
		return empIdStr!=null&&empIdStr.trim().length()>0?Integer.valueOf(empIdStr):0;
	}
	
	public static int getAgentId(ActionContext atx){
		String agentId=atx.getStringValue(YBCommonContant.SESSION_AGENT_ID);
		return agentId!=null&&agentId.trim().length()>0?Integer.valueOf(agentId):0;	
	}
	public static String getAgentNo(ActionContext atx){
		String agentNo=atx.getStringValue(YBCommonContant.SESSION_SYSCODE);
		return agentNo!=null&&agentNo.trim().length()>0?agentNo:"0";	
	}
	

	
	/**
	 * 从会话里获取MemberId
	 * 方   法  名:getMemberId
	 * 方法描述:
	 * 参         数:@param session
	 * 参         数:@return
	 * 返   回  值:int
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static int getMemberId(HttpSession session){
		String memberIdStr=(String) session.getAttribute(YBCommonContant.SESSION_USER_ID);
		return memberIdStr!=null&&memberIdStr.trim().length()>0?Integer.valueOf(memberIdStr):0;
	}
	
	public static int getAgentId(HttpSession session){
		String agentId=(String) session.getAttribute(YBCommonContant.SESSION_AGENT_ID);
		return agentId!=null&&agentId.trim().length()>0?Integer.valueOf(agentId):0;	
	}
	
	/**
	 * 计算两个经纬度点之间距离
	 * 方   法  名:Distance
	 * 方法描述:
	 * 参         数:@param long1 起点经度
	 * 参         数:@param lat1 起点维度
	 * 参         数:@param long2 终点经度
	 * 参         数:@param lat2 终点维度
	 * 参         数:@return 米
	 * 返   回  值:double
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static double Distance(double long1, double lat1, double long2, double lat2) {  
	    double a, b, R;  
	    R = 6378137; // 地球半径  
	    lat1 = lat1 * Math.PI / 180.0;  
	    lat2 = lat2 * Math.PI / 180.0;  
	    a = lat1 - lat2;  
	    b = (long1 - long2) * Math.PI / 180.0;  
	    double d;  
	    double sa2, sb2;  
	    sa2 = Math.sin(a / 2.0);  
	    sb2 = Math.sin(b / 2.0);  
	    d = 2  
	            * R  
	            * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1)  
	                    * Math.cos(lat2) * sb2 * sb2));  
	    return d;  
	}  
	
	/**
	 * 根据两个 经纬度 计算角度
	 * 方   法  名:gps2d
	 * 方法描述:
	 * 参         数:@param lat_a
	 * 参         数:@param lng_a
	 * 参         数:@param lat_b
	 * 参         数:@param lng_b
	 * 参         数:@return
	 * 返   回  值:double
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static double gps2d(double lat_a, double lng_a, double lat_b, double lng_b)
	{
	  double d = 0;
	  lat_a=lat_a*Math.PI/180;
	  lng_a=lng_a*Math.PI/180;
	  lat_b=lat_b*Math.PI/180;
	  lng_b=lng_b*Math.PI/180;
	         
	  d=Math.sin(lat_a)*Math.sin(lat_b)+Math.cos(lat_a)*Math.cos(lat_b)*Math.cos(lng_b-lng_a);
	  d=Math.sqrt(1-d*d);
	  d=Math.cos(lat_b)*Math.sin(lng_b-lng_a)/d;
	  d=Math.asin(d)*180/Math.PI;
	//d = Math.round(d*10000);
	  return d;
	}
	
	/**
	 * 根据车队编号，行程类型，生成全局唯一的班次号
	 * 方   法  名:getSingleTripLineNo
	 * 方法描述:
	 * 参         数:@param conn
	 * 参         数:@param agentNo 车队代号4位
	 * 参         数:@param tripType 行程类型1位
	 * 参         数:@return
	 * 参         数:@throws SQLException
	 * 返   回  值:String
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static String getSingleTripLineNo(Connection conn, String agentNo,String tripType){
		CallableStatement stmt = null;
		try {
			int id = 0;
			stmt = conn.prepareCall(" call P_GET_TRIP_LINE_SN(?,?,?) ");
			stmt.setString(1, agentNo);
			stmt.setString(2, tripType);
			stmt.registerOutParameter(3, Types.INTEGER);
			stmt.execute();
			id = stmt.getInt(3);
			StringBuffer TRIP_LINE_SN=new StringBuffer("");
			String idStr=String.valueOf(id);
			//不足四位前面补0
			for(int i=0;i<4-idStr.length();i++){
				TRIP_LINE_SN.append("0");
			}TRIP_LINE_SN.append(idStr);
			
			return TRIP_LINE_SN.toString();
		} catch (SQLException Serr) {
			Serr.getStackTrace();
			
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
			}
		}return null;
	}
	
	  public static String encodeStr(String plainText){
	        byte[] b=plainText.getBytes();  
	        Base64 base64=new Base64();  
	        b=base64.encode(b);  
	        String s=new String(b);  
	        return s;  
	    } 
	  
	  public static String decodeStr(String encodeStr){  
	        byte[] b=encodeStr.getBytes();  
	        Base64 base64=new Base64();  
	        b=base64.decode(b);  
	        String s=new String(b);  
	        return s;  
	    }
}
