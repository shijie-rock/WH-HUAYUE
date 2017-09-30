<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../comm/iframeHead.jsp"%>
 <html lang="zh-CN">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta charset="utf-8">
  <!-- Title and other stuffs -->
  <title>title</title> 
  <meta name="keywords" content="华悦车检系统--拖拽测试" />
  <meta name="description" content="华悦车检系统--拖拽测试" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="author" content="">
  <link href="<%=path%>/style/truck-tree.css?v=<%=staticVersion%>" rel="stylesheet"  />
  <!-- HTML5 Support for IE -->
  <!--[if lt IE 9]>
  <script src="js/html5shim.js"></script>
  <![endif]-->

  <!-- Favicon -->
  <link rel="shortcut icon" href="img/favicon/favicon.png">
</head>

<body class="content-body">

<div id="containment-wrapper" 
style="width:487px;height:346px;border: 1px red solid;margin-left:200px;margin-top:200px;
position:relative;background-image: url(<%=path%>/img/car-back.png);
background-repeat:no-repeat;text-align:center;">
  <div id="draggable3" style="width:60px;height:60px;border: 1px blue solid;text-align:center;">
	<div id="point_star"style="background-image:url(<%=path%>/img/star.gif);background-repeat:no-repeat;background-position:0px -16px;width:20px;height:20px;"></div>
    <p id="position_p"></p>
  </div>
</div>

<div id="guzhang" style=" width:160px;height:160px;border: 1px blue solid;text-align:center;background-color:#eee; display:none;position:absolute;z-index:20;">
</div>

<script src="<%=path%>/js/jquery.js?v=<%=staticVersion%>"></script> <!-- jQuery -->
<script src="<%=path%>/js/bootstrap.js?v=<%=staticVersion%>"></script> <!-- Bootstrap -->
<script src="<%=path%>/js/jquery-ui.min.js?v=<%=staticVersion%>"></script> <!-- jQueryUI-12-->
<script type="text/javascript">
	$(function() {
		$("#draggable3").draggable({
			cursor: "move",
			cursorAt: {top:20,left:20},
			containment : "#containment-wrapper",
			scroll : false,
			drag: function() {
				$("#position_p").text(divPosition());
		      },
		});
		$("#point_star").hover(function(){
				var parent_left = parseInt($('#containment-wrapper').css("margin-left"));
				var parent_top = parseInt($('#containment-wrapper').css("margin-top"));
			
				var left = $('#draggable3').position().left;
				var top = $('#draggable3').position().top;
				 $("#guzhang").css({"left":left+parent_left+40+"px","top":top+parent_top-20+"px"});
		   		 $("#guzhang").show(400);
	   		 },function(){
	   			 $("#guzhang").hide(400);
	  		});
	});
	
	function divPosition(){
		var left = $('#draggable3').position().left;
		var top = $('#draggable3').position().top;
		return 'top['+top+']-left['+left+']';
	}
	
</script>
</body>
</html>