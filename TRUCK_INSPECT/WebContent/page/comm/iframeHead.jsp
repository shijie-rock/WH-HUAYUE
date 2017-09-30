<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%
	String path = request.getContextPath();
// 	String staticVersion="0.0.0.1";
	String staticVersion=new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(System.currentTimeMillis()));
%>
<%@ taglib uri="http://www.whlog.cn/taglib" prefix="yb"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%> 

<meta charset="utf-8" />
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<meta content="" name="description" />

<META HTTP-EQUIV="pragma" CONTENT="no-cache"> 
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate"> 
<META HTTP-EQUIV="expires" CONTENT="0"> 

<link href="<%=path%>/style/bootstrap-datetimepicker.min.css?v=<%=staticVersion%>" rel="stylesheet" />
<link href="<%=path%>/style/bootstrap-switch.css?v=<%=staticVersion%>" rel="stylesheet" />
<link href="<%=path%>/style/bootstrap.css?v=<%=staticVersion%>" rel="stylesheet" />
<link href="<%=path%>/style/font-awesome.css?v=<%=staticVersion%>" rel="stylesheet" />
<link href="<%=path%>/style/fullcalendar.css?v=<%=staticVersion%>" rel="stylesheet" />
<link href="<%=path%>/style/jquery-ui.css?v=<%=staticVersion%>" rel="stylesheet" />
<link href="<%=path%>/style/jquery.cleditor.css?v=<%=staticVersion%>" rel="stylesheet" />
<link href="<%=path%>/style/prettyPhoto.css?v=<%=staticVersion%>" rel="stylesheet" />
<link href="<%=path%>/style/rateit.css?v=<%=staticVersion%>" rel="stylesheet" />
<link href="<%=path%>/style/widgets.css?v=<%=staticVersion%>" rel="stylesheet" id="style_color" /> 
<link href="<%=path%>/style/style.css?v=<%=staticVersion%>" rel="stylesheet" >
<link href="<%=path%>/js/jqueryAlert/jquery.alerts.css?v=<%=staticVersion%>" rel="stylesheet"  />
<link href="<%=path%>/js/data-tables/DT_bootstrap.css?v=<%=staticVersion%>" rel="stylesheet" />

<link href="<%=path%>/style/truck-inspect.css?v=<%=staticVersion%>" rel="stylesheet" /> 

<%-- <link href="<%=path%>/style/bootstrap-datetimepicker.css?v=<%=staticVersion%>" rel="stylesheet" />  --%>

<link href="<%=path%>/favicon.ico" type="image/x-icon" rel=icon />
<link href="<%=path%>/favicon.ico" type="image/x-icon" rel="shortcut icon" />
