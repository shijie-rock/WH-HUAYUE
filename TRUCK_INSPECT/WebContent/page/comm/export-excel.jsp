<%@page language="java" pageEncoding="UTF-8"%>
<%@page contentType="application/vnd.ms-excel;charset=gb2312"%>
<%@page import="java.io.*" %>
<%@page import="com.infoservice.framework.util.JspHelper"%>
<%@page import="java.io.File"%>
<%@page import="com.youbus.framework.comm.ExcelExportFactory" %>
<%
	JspHelper helper=new JspHelper(request);  
	Integer mainId=Integer.valueOf(request.getParameter("mainId"));
	System.out.println("mainId:="+request.getParameter("mainId"));
	File file=ExcelExportFactory.exportTravel(mainId);
	System.out.println("filename="+file.getName());
	FileInputStream in=null; 
	OutputStream  output=response.getOutputStream();
	byte b[]=new byte[1024]; 
	try
	{ 
	response.setHeader("content-disposition","attachment; filename="+new String(file.getName().getBytes(),"ISO-8859-1")); 
	response.setContentType("application/vnd.ms-excel"); 
	in=new FileInputStream(file);
	output.flush(); 
	while(in.read(b) != -1) 
	{ 
		output.write(b); 
	} 
	output.flush(); 
	} 
	catch(Exception e) 
	{ 
	System.out.println(e); 
// 	response.sendRedirect("downError.jsp"); 
	} 
	finally
	{ 
	if(in!=null) in.close(); 
	if(output!=null){
		output.close();
	}
	response.flushBuffer();  
	out.clear();  
	out = pageContext.pushBody();  //need 
	} 
%>

