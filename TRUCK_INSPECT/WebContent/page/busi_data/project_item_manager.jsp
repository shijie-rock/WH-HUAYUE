<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../comm/iframeHead.jsp"%>
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
  <link href="<%=path%>/style/truck-tree.css?v=<%=staticVersion%>" rel="stylesheet"  />
  <!-- HTML5 Support for IE -->
  <!--[if lt IE 9]>
  <script src="js/html5shim.js"></script>
  <![endif]-->

  <!-- Favicon -->
  <link rel="shortcut icon" href="img/favicon/favicon.png">
  <style type="text/css">
  a{
  	color:#CCCCCC;
  }
  a:hover{  
	color:#026b9c;
	}
  </style>
</head>

<body class="content-body">
<!-- Header starts -->
<!-- Header ends -->

<!-- Main content starts -->
<div class="tree well">
 <ul>
  <li>
   <span id="Parent_1"><i class="glyphicon glyphicon-folder-open" ></i> 车</span> <a href="#">增加节点/删除节点</a>
   <ul>
    <li>
      <span><i class="glyphicon glyphicon-minus-sign"></i> 罐车</span> <a href="#">增加节点/删除节点</a>
     <ul>
      <li>
        <span><i class="glyphicon glyphicon-leaf"></i> 车体</span> <a href="#">增加节点/删除节点</a>
      </li>
     </ul>
    </li>
    <li>
      <span><i class="glyphicon glyphicon-minus-sign"></i> LPG</span> <a href="#">增加节点/删除节点</a>
     <ul>
      <li>
        <span><i class="glyphicon glyphicon-leaf"></i> 灌体</span> <a href="#">增加节点/删除节点</a>
      </li>
      <li>
        <span><i class="glyphicon glyphicon-minus-sign"></i> 车身</span> <a href="#">增加节点/删除节点</a>
       <ul>
        <li>
          <span><i class="glyphicon glyphicon-minus-sign"></i> 底盘</span> <a href="#">增加节点/删除节点</a>
           <ul>
            <li>
              <span><i class="glyphicon glyphicon-leaf"></i> 承重梁</span> <a href="#">增加节点/删除节点</a>
            </li>
            <li>
              <span><i class="glyphicon glyphicon-leaf"></i> 尾标</span> <a href="#">增加节点/删除节点</a>
            </li>
            </ul>
        </li>
        <li>
          <span><i class="glyphicon glyphicon-leaf"></i> 车头</span> <a href="#">增加节点/删除节点</a>
        </li>
        <li>
          <span><i class="glyphicon glyphicon-leaf"></i> 轮胎</span> <a href="#">增加节点/删除节点</a>
        </li>
       </ul>
      </li>
      <li>
        <span><i class="glyphicon glyphicon-leaf"></i> 车顶</span> <a href="#">增加节点/删除节点</a>
      </li>
     </ul>
    </li>
   </ul>
  </li>
  <li>
   <span id="Parent_2"><i class="glyphicon glyphicon-folder-open" ></i> 服务器</span> <a href="#">增加节点/删除节点</a>
   <ul>
    <li>
      <span><i class="glyphicon glyphicon-leaf"></i> 工作站服务器</span> <a href="#">增加节点/删除节点</a>
      </li>
     </ul>
  </li>
 </ul>
</div>

<!-- Content ends -->

<!-- Footer starts -->
<!-- Footer ends -->

<!-- Scroll to top -->

<!-- JS -->
<script src="<%=path%>/js/jquery.js?v=<%=staticVersion%>"></script> <!-- jQuery -->
<script src="<%=path%>/js/bootstrap.js?v=<%=staticVersion%>"></script> <!-- Bootstrap -->
<%-- <script src="<%=path%>/js/jquery-ui-1.9.2.custom.min.js?v=<%=staticVersion%>"></script> <!-- jQuery UI --> --%>
<%-- <script src="<%=path%>/js/fullcalendar.min.js?v=<%=staticVersion%>"></script> <!-- Full Google Calendar - Calendar --> --%>
<%-- <script src="<%=path%>/js/jquery.rateit.min.js?v=<%=staticVersion%>"></script> <!-- RateIt - Star rating --> --%>
<%-- <script src="<%=path%>/js/jquery.prettyPhoto.js?v=<%=staticVersion%>"></script> <!-- prettyPhoto --> --%>

<!-- jQuery Flot -->
<%-- <script src="<%=path%>/js/excanvas.min.js?v=<%=staticVersion%>"></script> --%>
<%-- <script src="<%=path%>/js/jquery.flot.js?v=<%=staticVersion%>"></script> --%>
<%-- <script src="<%=path%>/js/jquery.flot.resize.js?v=<%=staticVersion%>"></script> --%>
<%-- <script src="<%=path%>/js/jquery.flot.pie.js?v=<%=staticVersion%>"></script> --%>
<%-- <script src="<%=path%>/js/jquery.flot.stack.js?v=<%=staticVersion%>"></script> --%>

<!-- jQuery Notification - Noty -->
<%-- <script src="<%=path%>/js/jquery.noty.js?v=<%=staticVersion%>"></script> <!-- jQuery Notify --> --%>
<%-- <script src="<%=path%>/js/themes/default.js?v=<%=staticVersion%>"></script> <!-- jQuery Notify --> --%>
<%-- <script src="<%=path%>/js/layouts/bottom.js?v=<%=staticVersion%>"></script> <!-- jQuery Notify --> --%>
<%-- <script src="<%=path%>/js/layouts/topRight.js?v=<%=staticVersion%>"></script> <!-- jQuery Notify --> --%>
<%-- <script src="<%=path%>/js/layouts/top.js?v=<%=staticVersion%>"></script> <!-- jQuery Notify --> --%>
 <!-- jQuery Notification ends -->

<%-- <script src="<%=path%>/js/sparklines.js?v=<%=staticVersion%>"></script> <!-- Sparklines --> --%>
<%-- <script src="<%=path%>/js/jquery.cleditor.min.js?v=<%=staticVersion%>"></script> <!-- CLEditor --> --%>
<%-- <script src="<%=path%>/js/bootstrap-datetimepicker.min.js?v=<%=staticVersion%>"></script> <!-- Date picker --> --%>
<%-- <script src="<%=path%>/js/jquery.uniform.min.js?v=<%=staticVersion%>"></script> <!-- jQuery Uniform --> --%>
<%-- <script src="<%=path%>/js/bootstrap-switch.min.js?v=<%=staticVersion%>"></script> <!-- Bootstrap Toggle --> --%>
<%-- <script src="<%=path%>/js/filter.js?v=<%=staticVersion%>"></script> <!-- Filter for support page --> --%>
<%-- <script src="<%=path%>/js/custom.js?v=<%=staticVersion%>"></script> <!-- Custom codes --> --%>
<%-- <script src="<%=path%>/js/charts.js?v=<%=staticVersion%>"></script> <!-- Charts & Graphs --> --%>

<!-- Script for this page -->
<script type="text/javascript">
$(function () {
	  $('.tree li:has(ul)').addClass('parent_li').find(' > span').attr('title', 'Collapse this branch');
	  $('.tree li.parent_li > span').on('click', function (e) {
	    var children = $(this).parent('li.parent_li').find(' > ul > li');
	    if (children.is(":visible")) {
	      children.hide('fast');
	      $(this).find(' > i').addClass('icon-plus-sign').removeClass('icon-minus-sign');
// 	      $(this).attr('title', 'Expand this branch').find(' > i').addClass('icon-plus-sign').removeClass('icon-minus-sign');
	    } else {
	      children.show('fast');
	      $(this).find(' > i').addClass('icon-minus-sign').removeClass('icon-plus-sign');
// 	      $(this).attr('title', 'Collapse this branch').find(' > i').addClass('icon-minus-sign').removeClass('icon-plus-sign');
	    }
	    e.stopPropagation();
	  });
	  
	  
	  //mouse hover //上级选中span闪烁
	  $('.tree li.parent_li > span').hover(function(){
		  console.log("in");
		  $(this).parent().find('span').addClass('shine_blue');
// 		  $(this).parent().find('span').css("background-color","yellow");
		},function(){
		  console.log("out");
		  $(this).parent().find('span').removeClass('shine_blue');
// 		  $(this).parent().find('span').css("background-color","white");
		});
	  
	  //mouse hover //单个 //$("#main").find(".one").not(".two")
	  $('.tree li').not('.parent_li').find(' > span').hover(function(){
		  console.log("in2");
		  $(this).parent().find('span').addClass('shine_red');
		},function(){
		  console.log("out2");
		  $(this).parent().find('span').removeClass('shine_red');
		});
	  
	});

$(function(){
	$('span[id^=Parent_]').click(); //自动点击，收缩
});	
</script>
</body>
</html>