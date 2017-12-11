<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="comm/iframeHead.jsp"%>
 <html lang="zh-CN">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta charset="utf-8">
  <!-- Title and other stuffs -->
  <title>title</title> 
  <meta name="keywords" content="华悦车检系统登录" />
  <meta name="description" content="华悦车检系统登录" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="author" content="">
  
  <!-- HTML5 Support for IE -->
  <!--[if lt IE 9]>
  <script src="js/html5shim.js"></script>
  <![endif]-->

  <!-- Favicon -->
  <link rel="shortcut icon" href="img/favicon/favicon.png">
  <style type="text/css">      
     body{      
        background-image: url(<%=path%>/img/404.png) !important;   
        background-repeat:no-repeat !important;   
        background-size:cover  !important;   
        background-size: 100% 90% !important;  
     }      
 </style>   
</head>

<body class="content-body">
<!-- Header starts -->
<!-- Header ends -->

<!-- Main content starts -->


<!-- Content ends -->

<!-- Footer starts -->
<!-- Footer ends -->

<!-- Scroll to top -->
<!-- <span class="totop"><a href="#"><i class="icon-chevron-up"></i></a></span>  -->

<!-- JS -->
<script src="<%=path%>/js/jquery.js?v=<%=staticVersion%>"></script> <!-- jQuery -->
<script src="<%=path%>/js/bootstrap.js?v=<%=staticVersion%>"></script> <!-- Bootstrap -->
<!-- Script for this page -->
<script type="text/javascript">
$(function(){
	$('#iframepage',window.parent.document).css("height",(500)+'px');
});

</script>

</body>
</html>