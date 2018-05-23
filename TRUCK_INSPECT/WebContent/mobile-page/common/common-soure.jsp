<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
	String path = request.getContextPath();
	String staticVersion=new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(System.currentTimeMillis()));
%>
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<meta content="" name="description" />

<META HTTP-EQUIV="pragma" CONTENT="no-cache"> 
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate"> 
<META HTTP-EQUIV="expires" CONTENT="0"> 
<link href="<%=path%>/style/bootstrap.css?v=<%=staticVersion%>" rel="stylesheet" />
<link href="<%=path%>/style/jqm/themes/default/jquery.mobile-1.4.5.min.css?v=<%=staticVersion%>" rel="stylesheet" />
<link href="<%=path%>/style/truck-inspect.css?v=<%=staticVersion%>" rel="stylesheet" /> 
<link href="<%=path%>/style/jqm/truck-jqm.css?v=<%=staticVersion%>" rel="stylesheet" />