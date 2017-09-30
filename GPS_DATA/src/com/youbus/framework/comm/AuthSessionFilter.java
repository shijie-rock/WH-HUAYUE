/**
 * ��Ŀ����:AGENT_CENTER
 * ��         ��:com.youbus.agentcenter.auth
 * ��   ��  ��:AuthSessionFilter.java
 * �� ������:2015��4��14��-����2:20:33
 * Copyright @ 2015-YouBus.com.cn
 */
package com.youbus.framework.comm;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.infoservice.framework.FrameworkConstant;
import com.youbus.framework.util.DBConUtil;

/**
 * ������:AuthSessionFilter
 * ������:����Session�е�userId
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2015��4��14�� ����2:20:33
 * �޸ı�ע:
 * @version 1.0.0
 */
public class AuthSessionFilter implements Filter {

	/**
	 * ����һ���µ�ʵ�� AuthSessionFilter.
	 *
	 */
	private Logger log=Logger.getLogger(AuthSessionFilter.class);
	
	public AuthSessionFilter() {
	}

	@Override
	public void destroy() {
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse res,FilterChain chain) throws IOException, ServletException {
//		String isRemoteStr = req.getParameter("remote");
//		String reqStr = req.getParameter("req");
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String url=request.getRequestURL().toString();
		String contextPath=request.getContextPath();
		/**
		get:
		request action url:
		URL��=http://192.168.0.133:8088/AGENT_CENTER/service
  		URI��=/AGENT_CENTER/service
  		contextPath��=/AGENT_CENTER
		qurl��=action=DICTIONNARY
		
		request jsp url:
		URL��=http://192.168.0.133:8088/AGENT_CENTER/jsp/businessData/vehicle_driver_calendar.jsp
		URI��=/AGENT_CENTER/jsp/businessData/vehicle_driver_calendar.jsp
		contextPath��=/AGENT_CENTER
		qurl��=null
		 */
//		if(qurl!=null&&qurl.trim().length()>0)url=url+"?"+qurl.trim();
		System.out.println("request url��="+url);
		System.out.println("request url1��="+req.getServletContext().getServerInfo());
		System.out.println("session_id��="+request.getSession().getId());
		String subUrl=request.getServletPath()+"?"+request.getQueryString();//ע��ɹ�����Ҫ��תӦ�ã�����ֻ��Ҫ���� ��Ŀ�����ĺ���� url����
		log.debug("referer_sub url:="+subUrl);
		if (!isEgnore(req)) { 
			//�ų�Զ�̷���reg��Զ��ҳ����ת//�ų���̬�ļ�)
			System.out.println("����Auth-SessionFilter");
			log.debug("����Auth-SessionFilter");
			String referer=request.getHeader("Referer");
			log.debug("request referer :="+referer);
			HttpSession session = request.getSession();
			String sessionId=session.getId();
			
			String userName = (String) session.getAttribute(YBCommonContant.SESSION_USER_NAME); //TM_SYS_MEMBER LOGIN_MEMBER_ID
			
			System.out.println("Auth-SessionFilter:userName["+userName+"] ");
			log.debug("Auth-SessionFilter:userName["+userName+"] ");
			
			if (userName == null || userName.trim().length() < 1) {
				request.setAttribute("MSG", "�Ự���ڣ������µ�¼��");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/page/remote_log_error.jsp");
				dispatcher.forward(request, response);
				System.out.println("Auth-SessionFilter: Session["+sessionId+"] ���ڣ����µ�¼");
				log.debug("Auth-SessionFilter: Session["+sessionId+"] ���ڣ����µ�¼");
				return;
			}
				
			System.out.println("�˳�Auth-SessionFilter");
			log.debug("�˳�Auth-SessionFilter");
			chain.doFilter(req, res);
		} else {
			chain.doFilter(req, res);
		}

	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
	
	private boolean isEgnore(ServletRequest req){
		boolean isEgnore=false;
		String isRemoteStr = req.getParameter("remote");
		String reqStr = req.getParameter("req");
		HttpServletRequest request = (HttpServletRequest) req;
		String url=request.getRequestURL().toString();
		String contextPath=request.getContextPath();
		String qurl=request.getQueryString();
		String method=request.getMethod();
		String actionId=(String) (request.getAttribute(FrameworkConstant.ACTION_ID)==null?request.getParameter(FrameworkConstant.ACTION_ID):request.getAttribute(FrameworkConstant.ACTION_ID));
		System.out.println("actionId :="+actionId);
		
		if(url.endsWith(".js")||url.endsWith(".css")||url.endsWith(".png")||url.endsWith(".jpg")||url.endsWith(".woff")||url.endsWith(".ico")||url.endsWith(".ico")||url.endsWith(".map"))return true;
		if(url.endsWith("js/uploadify/uploadify.swf"))return true;
		//jsp	
		if(url.endsWith("page/index.jsp"))return true;//�����û���¼ҳ��
		if(url.endsWith("jsp/remote_log_error.jsp.jsp"))return true;//���Դ�����תҳ��
		//action
		if("SYS_LOGIN_ACTION".equals(actionId))return true;//�����û���¼action
		if("USER_LOGOUT_ACTION".equals(actionId))return true;//�����û��ǳ�action
		
		System.out.println("URL��="+url);
		System.out.println("sessionId1��="+request.getSession().getId());
		System.out.println("contextPath��="+contextPath);
		System.out.println("method��="+method);
		System.out.println("servlet context��="+req.getServletContext());
		log.debug(isEgnore?"����":"������");
		return isEgnore;
		
	}
/**
 * У�� ��ǰurl �Ƿ�������û��ɲ�����Action list 
 * ��   ��  ��:checkReqAtActionList
 * ��������:
 * ��         ��:@param reqUrl ///AGENT_CENTER/jsp/businessData/vehicle_driver_calendar.jsp or AGENT_CENTER/service?action=AAA
 * ��         ��:@param actionList (/jsp/businessData/vehicle_driver_calendar.jsp or /service?action=AAA)
 * ��         ��:@return
 * ��   ��  ֵ:boolean
 * ��   ��  ��:rock
 * @exception
 * @since  1.0.0
 */
	private  boolean checkReqAtActionList(String reqUrl,List<String> actionList){
		System.out.println("checkReqAtActionList:="+reqUrl);
		for(String actionUrl:actionList){
			if(reqUrl.endsWith(actionUrl))return true;
//			if(actionUrl.equalsIgnoreCase(reqUrl))return true;
		}
//		return false;	
		return true;//����У��
	}
	
	/**
	 * ��requst url ת��Ϊ jsp url ���� action url
	 * http://192.168.0.133:8088/AGENT_CENTER/jsp/businessData/vehicle_driver_calendar.jsp?aaa=aaa =��AGENT_CENTER/jsp/businessData/vehicle_driver_calendar.jsp
	 * http://192.168.0.133:8088/AGENT_CENTER/service?action=AAA&safs=bbb =��AGENT_CENTER/service?action=AAA
	 * ��   ��  ��:parseReqStr
	 * ��������:
	 * ��         ��:@param request
	 * ��         ��:@return
	 * ��   ��  ֵ:String
	 * ��   ��  ��:rock
	 * @exception
	 * @since  1.0.0
	 */
	private String parseReqStr(HttpServletRequest  request){
		Map<String, String[]> params = request.getParameterMap(); 
		String queryString="";
		String method=request.getMethod();
//		String url=request.getRequestURL().toString();
		String uri=request.getRequestURI().toString();
		if(uri.endsWith(".jsp")){
			System.out.println("request:="+method+":"+uri+""+queryString);
			return uri; //jsp ֱ�ӷ���
		}
		for (String key : params.keySet()) {  
			if("action".equalsIgnoreCase(key)){
            String[] values = params.get(key);  
//            for (int i = 0; i < values.length; i++) {  
//                String value = values[i];  
//                queryString += key + "=" + value + "&";  
//            } 
            if(values!=null&&values.length>0)queryString=key + "=" + values[0];
            break;
			}
        }
		if(DBConUtil.stringNotNULL(queryString))queryString="?"+queryString;
		System.out.println("request:="+method+":"+uri+""+queryString);
		return uri+queryString;
//		request:=GET:/AGENT_CENTER/service?action=LOGOUT_ACTION
	}
	
//	/**
//	 * У���û����Ͷ�Ӧ��action�Ƿ�����
//	 * ��   ��  ��:checkMemberTypeActionOPT
//	 * ��������:
//	 * ��         ��:@return
//	 * ��   ��  ֵ:boolean
//	 * ��   ��  ��:rock
//	 * @exception
//	 * @since  1.0.0
//	 */
//	private boolean checkMemberTypeActionOPT(String memberType,String actionId){
//		Cache MEMBER_OPT_MAPPING_ACTION=YoubusNativeCacheService.getCache("MEMBER_OPT_MAPPING_ACTION");
//		if(MEMBER_OPT_MAPPING_ACTION.get(memberType)!=null){
//			Element e=MEMBER_OPT_MAPPING_ACTION.get(memberType);
//			if(e.getValue()!=null){
//				List<String> actionList=(List<String>) e.getValue();
//				for(String action:actionList){
//					if(actionId.equals(action))return true;
//				}
//			}
//		}
//		return false;
//	}
//	
//	/**
//	 * У���û����Ͷ�Ӧ��url�Ƿ��������
//	 * ��   ��  ��:checkMemberTypeURLOPT
//	 * ��������:
//	 * ��         ��:@return
//	 * ��   ��  ֵ:boolean
//	 * ��   ��  ��:rock
//	 * @exception
//	 * @since  1.0.0
//	 */
//	private boolean checkMemberTypeURLOPT(String memberType,String url){
//		Cache MEMBER_OPT_MAPPING_URL=YoubusNativeCacheService.getCache("MEMBER_OPT_MAPPING_URL");
//		if(MEMBER_OPT_MAPPING_URL.get(memberType)!=null){
//			Element e=MEMBER_OPT_MAPPING_URL.get(memberType);
//			if(e.getValue()!=null){
//				List<String> urlList=(List<String>) e.getValue();
//				for(String urlOld:urlList){
//					if(urlOld.indexOf(".")!=-1){//xxx.jsp xxx.js ..
//						if(url.endsWith(urlOld))return true;
//					}else{  //  /sss/ url
//						if(url.indexOf(urlOld)!=-1)return true;
//					}
//					
//				}
//			}
//		}
//		return false;
//	}
	
	public static void main(String[] args){
		String url="http://127.0.0.1:8088/AUTH_CENTER/110/user_center/ECC28FAA7092B05ED51441F9D05B73CC";
		String authLogSessionId=url.substring(url.indexOf("user_center")+12);
		String groupId=url.substring(url.indexOf("AUTH_CENTER")+12,url.indexOf("/user_center"));
		System.out.println(authLogSessionId);
		System.out.println(groupId);
				
	}
}
