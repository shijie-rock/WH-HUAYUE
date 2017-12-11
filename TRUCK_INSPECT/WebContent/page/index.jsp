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
</head>

<body class="index-body">

<div class="navbar navbar-fixed-top bs-docs-nav" role="banner">
  
    <div class="conjtainer">
      <!-- Menu button for smallar screens -->
      <div class="navbar-header">
		  <button class="navbar-toggle btn-navbar" type="button" data-toggle="collapse" data-target=".bs-navbar-collapse">
			<span>菜单</span>
		  </button>
		  <!-- Site name for smallar screens -->
		  <a href="index.html" class="navbar-brand hidden-lg">首页</a>
		</div>
      
      

      <!-- Navigation starts -->
      <nav class="collapse navbar-collapse bs-navbar-collapse" role="navigation">         

        <ul class="nav navbar-nav">  

          <!-- Upload to server link. Class "dropdown-big" creates big dropdown -->
          <li class="dropdown dropdown-big">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="label label-success"><i class="glyphicon glyphicon-signal" style="margin-left:1px;"></i></span>&nbsp;业务指标</a>
            <!-- Dropdown -->
            <ul class="dropdown-menu">
              <li>
                <!-- Progress bar -->
                <p>在途车辆</p>
                <!-- Bootstrap progress bar -->
                <div class="progress progress-striped active">
					<div class="progress-bar progress-bar-info"  role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width:70%">
						<span class="sr-only">70%</span>
					</div>
			    </div>
			      <hr />  
                <!-- Progress bar -->
                <p>检车一次通过率</p>
                <!-- Bootstrap progress bar -->
                <div class="progress progress-striped active">
					<div class="progress-bar progress-bar-success"  role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" style="width:90%">
						<span class="sr-only">90%</span>
					</div>
			    </div> 
			      <hr />  
			    <!-- Progress bar -->
                <p>复检返工率</p>
                <!-- Bootstrap progress bar -->
                <div class="progress progress-striped active">
					<div class="progress-bar progress-bar-danger"  role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" style="width:10%">
						<span class="sr-only">10%</span>
					</div>
			    </div> 
                <hr />             
                <!-- Dropdown menu footer -->
                <div class="drop-foot">
                  <a href="#">查看所有</a>
                </div>

              </li>
            </ul>
          </li>

          <!-- Sync to server link -->
          <li class="dropdown dropdown-big">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
            	<span class="label label-danger">
            	<i class="glyphicon glyphicon-envelope"  style="margin-left: 2px;"></i>
            	</span>
            	&nbsp;我的消息&nbsp;<span class="badge pull-right" >3</span></a></a>
            <!-- Dropdown -->
            <ul class="dropdown-menu">
              <li>
                <!-- Using "icon-spin" class to rotate icon. -->
                <p><span class="text-danger"><i class="glyphicon glyphicon-alert"></i></span>&nbsp;2017-12-1紧急通知...</p>
                <hr />
                <p><span class="text-success"><i class="glyphicon glyphicon-star"></i></span>&nbsp;2017-12-1有新检车单生成</p>
                <hr />
                <p><span class="text-warning"><i class="glyphicon glyphicon-star"></i></span>&nbsp;提醒:危险品道路运输许可证到期</p>
                <hr />
                <!-- Dropdown menu footer -->
                <div class="drop-foot">
                  <a href="#">查看所有消息</a>
                </div>

              </li>
            </ul>
          </li>
          
          <!-- Sync to server link my -->
          <li class="dropdown dropdown-big">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
            	<span class="label label-danger">
            	<i class="icon-bell"  style="margin-left: 2px;"></i>
            	</span>
            	&nbsp;待办事项&nbsp;<span class="badge pull-right">2</span></a></a>
            <!-- Dropdown -->
            <ul class="dropdown-menu">
              <li>
                <!-- Using "icon-spin" class to rotate icon. -->
                <p><span class="text-danger"><i class="glyphicon glyphicon-alert"></i></span>&nbsp;2017-12-1有新检车单生成</p>
                <hr />
                <p><span class="text-danger"><i class="glyphicon glyphicon-alert"></i></span>&nbsp;2017-12-1有新返工检车单</p>
                <hr />
                <hr />
                <!-- Dropdown menu footer -->
                <div class="drop-foot">
                  <a href="#">查看所有待办</a>
                </div>

              </li>
            </ul>
          </li>

        </ul>

        <!-- Search form -->
<!--         <form class="navbar-form navbar-left" role="search"> -->
<!-- 			<div class="form-group"> -->
<!-- 				<input type="text" class="form-control" placeholder="Search"> -->
<!-- 			</div> -->
<!-- 		</form> -->
        <!-- Links -->
        <ul class="nav navbar-nav pull-right">
          <li class="dropdown pull-right">            
            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
              <i class="icon-user"></i> 管理员 <b class="caret"></b>              
            </a>
            
            <!-- Dropdown menu -->
            <ul class="dropdown-menu">
              <li><a href="#"><i class="icon-user"></i> 资料</a></li>
              <li><a href="#"><i class="icon-cogs"></i> 设置</a></li>
              <li><a href="#" onclick="logout()"><i class="icon-off"></i> 退出</a></li>
            </ul>
          </li>
          
        </ul>
      </nav>

    </div>
  </div>


<!-- Header starts -->
  <header>
    <div class="container">
      <div class="row">

        <!-- Logo section -->
        <div class="col-md-4">
          <!-- Logo. -->
          <div class="logo">
            <h1><a href="#">WHlog<span class="bold"></span></a></h1>
            <p class="meta">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;后台管理系统</p>
          </div>
          <!-- Logo ends -->
        </div>

        <!-- Button section -->
        <div class="col-md-4"></div>
        <!-- Data section -->

        <div class="col-md-4">
          <div class="header-data">

            <!-- Traffic data -->
            <div class="hdata">
              <div class="mcol-left">
                <!-- Icon with red background -->
                <i class="glyphicon glyphicon-list-alt bred"></i> 
              </div>
              <div class="mcol-right">
                <!-- Number of visitors -->
                <p><a href="#">7000</a> <em>检车单</em></p>
              </div>
              <div class="clearfix"></div>
            </div>

            <!-- Members data -->
            <div class="hdata">
              <div class="mcol-left">
                <!-- Icon with blue background -->
                <i class="icon-user bblue"></i> 
              </div>
              <div class="mcol-right">
                <!-- Number of visitors -->
                <p><a href="#">3000</a> <em>用户</em></p>
              </div>
              <div class="clearfix"></div>
            </div>

            <!-- revenue data -->
            <div class="hdata">
              <div class="mcol-left">
                <!-- Icon with green background -->
                <i class="glyphicon glyphicon-send bgreen"></i> 
              </div>
              <div class="mcol-right">
                <!-- Number of visitors -->
                <p><a href="#">5000</a><em>车辆</em></p>
              </div>
              <div class="clearfix"></div>
            </div>                        

          </div>
        </div>

      </div>
    </div>
  </header>

<!-- Header ends -->

<!-- Main content starts -->
  	<!-- Sidebar -->
    <div class="sidebar" id="index-sidebar">
        <div class="sidebar-dropdown"><a href="#">导航</a></div>

        <!--- Sidebar navigation -->
        <!-- If the main navigation has sub navigation, then add the class "has_sub" to "li" of main navigation. -->
        <ul id="nav">
          <!-- Main menu with font awesome icon -->
          <li><a href="<%=path%>/page/index.jsp" class="open"><i class="icon-home"></i> 首页</a>
            <!-- Sub menu markup 
            <ul>
              <li><a href="#">Submenu #1</a></li>
              <li><a href="#">Submenu #2</a></li>
            </ul>-->
          </li>
          <!-- 
          <li class="has_sub"><a href="#"><i class="icon-list-alt"></i> 插件页面  <span class="pull-right"><i class="icon-chevron-right"></i></span></a>
            <ul>
              <li><a href="<%=path%>/page/index-content.jsp" target="iframepage">首页</a></li>
              <li><a href="<%=path%>/page/tmp/tree-demo.jsp" target="iframepage">树#1</a></li>
              <li><a href="<%=path%>/page/tmp/tree-demo2.jsp" target="iframepage">树#2</a></li>
              <li><a href="<%=path%>/page/tmp/content-table.jsp" target="iframepage">DataTable</a></li>
            </ul>
          </li> 
           --> 
          <li class="has_sub"><a href="#"><i class="icon-user"></i>组织管理<span class="pull-right"><i class="icon-chevron-right"></i></span></a>
            <ul>
              <li><a href="<%=path%>/page/organize/role_manager.jsp" target="iframepage">角色管理</a></li>
              <li><a href="<%=path%>/page/organize/ins_group_manager.jsp" target="iframepage">检查组管理</a></li>
              <li><a href="<%=path%>/page/organize/member_manager.jsp" target="iframepage">用户管理</a></li>
<!--               <li><a href="gallery.html">相册页面</a></li> -->
            </ul>
          </li> 
          <li class="has_sub"><a href="#"><i class="icon-book"></i>基础数据 <span class="pull-right"><i class="icon-chevron-right"></i></span></a>
            <ul>
              <li><a href="<%=path%>/page/base_data/position_manager.jsp" target="iframepage">检查地点</a></li>
              <li><a href="<%=path%>/page/base_data/truck_manager.jsp" target="iframepage">车辆信息</a></li>
              <li><a href="<%=path%>/page/base_data/driver_manager.jsp" target="iframepage">司机信息</a></li>
            </ul>
          </li>
           <li class="has_sub"><a href="#"><i class="icon-list"></i>项目管理<span class="pull-right"><i class="icon-chevron-right"></i></span></a>
            <ul>
              <li><a href="<%=path%>/page/busi_data/project_class_manager.jsp" target="iframepage">对象大类</a></li>
              <li><a href="<%=path%>/page/busi_data/project_class_sub_manager.jsp" target="iframepage">对象小类</a></li>
              <li><a href="<%=path%>/page/busi_data/project_item_manager.jsp" target="iframepage">检查项目</a></li>
            </ul>
          </li> 
           <li class="has_sub"><a href="#"><i class="glyphicon glyphicon-edit"></i>业务数据<span class="pull-right"><i class="icon-chevron-right"></i></span></a>
            <ul>
              <li><a href="<%=path%>/page/ins_order/ins_order_manager.jsp" target="iframepage">检车单管理</a></li>
              <li><a href="<%=path%>/page/ins_order/ins_order_item_manager.jsp" target="iframepage">检车单明细</a></li>
            </ul>
          </li> 
          <li class="has_sub"><a href="#"><i class="glyphicon glyphicon-send"></i>消息管理<span class="pull-right"><i class="icon-chevron-right"></i></span></a>
            <ul>              
            <li><a href="<%=path%>/page/message/wx_msg_manager.jsp" target="iframepage">微信消息</a></li>
            <li><a href="<%=path%>/page/message/sms_msg_manager.jsp" target="iframepage">短信消息</a></li>
            </ul>
          </li> 
          <li class="has_sub"><a href="#"><i class="icon-align-left"></i>报表管理<span class="pull-right"><i class="icon-chevron-right"></i></span></a>
            <ul>              
            <li><a href="<%=path%>/page/report/ins_check_report.jsp" target="iframepage">检车通过率</a></li>
            <li><a href="<%=path%>/page/report/ins_check_repeat_report.jsp" target="iframepage">检车复检率</a></li>
            </ul>
          </li>
          <li class="has_sub"><a href="#"><i class="icon-eye-open"></i>系统监控<span class="pull-right"><i class="icon-chevron-right"></i></span></a>
            <ul>              
            <li><a href="<%=path%>/page/system/task_manager.jsp" target="iframepage">计划任务</a></li>
            <li><a href="<%=path%>/page/system/sys_log_manager.jsp" target="iframepage">日志查询</a></li>
            </ul>
          </li> 
          <!--                          
          <li><a href="charts.html"><i class="icon-bar-chart"></i>图表</a></li> 
          <li><a href="tables.html"><i class="icon-table"></i>表格</a></li>
          <li><a href="forms.html"><i class="icon-tasks"></i>表单</a></li>
          <li><a href="ui.html"><i class="icon-magic"></i>UI图标</a></li>
          <li><a href="calendar.html"><i class="icon-calendar"></i>日历</a></li>
           -->
        </ul>
    </div>

    <!-- Sidebar ends -->
  	<!-- Main bar -->
  	<div class="mainbar" id="index-content">
            <input type="hidden" id="CurrentBusId" value="">
<!--             <input type="hidden" id="CurrentBusLicense" value=""> -->
<!--             <input type="hidden" id="CurrentDriverId" value=""> -->
<!--             <input type="hidden" id="CurrentDriverName" value=""> -->

				<iframe src="<%=path%>/page/index-content.jsp" id="iframepage" name="iframepage" height="560px" 
					style="background-color: #fff;overflow-x:no;overflow-y:no;border-top:0px solid;min-width:1100px;"
					marginheight="0" marginwidth="10" frameborder="0" scrolling="yes"  width="100%"  onLoad="iFrameHeight(this)"></iframe> 
    </div>
   <!-- Mainbar ends -->
   <div class="clearfix"></div>
<!-- Content ends -->

<!-- Footer starts -->
<footer class="main-footer" >
  <div class="container">
    <div class="row">
      <div class="col-md-12">
            <!-- Copyright info -->
            <p class="copy">Copyright &copy; 2017 | <a href="#">WHlog</a> </p>
      </div>
    </div>
  </div>
</footer> 	

<!-- Footer ends -->

<!-- Scroll to top -->
<span class="totop"><a href="#"><i class="icon-chevron-up"></i></a></span> 

<!-- JS -->
<script src="<%=path%>/js/jquery.js?v=<%=staticVersion%>"></script> <!-- jQuery -->
<script src="<%=path%>/js/bootstrap.js?v=<%=staticVersion%>"></script> <!-- Bootstrap -->
<script src="<%=path%>/js/jquery-ui-1.9.2.custom.min.js?v=<%=staticVersion%>"></script> <!-- jQuery UI -->
<script src="<%=path%>/js/fullcalendar.min.js?v=<%=staticVersion%>"></script> <!-- Full Google Calendar - Calendar -->
<script src="<%=path%>/js/jquery.rateit.min.js?v=<%=staticVersion%>"></script> <!-- RateIt - Star rating -->
<script src="<%=path%>/js/jquery.prettyPhoto.js?v=<%=staticVersion%>"></script> <!-- prettyPhoto -->

<!-- jQuery Flot -->
<script src="<%=path%>/js/excanvas.min.js?v=<%=staticVersion%>"></script>
<script src="<%=path%>/js/jquery.flot.js?v=<%=staticVersion%>"></script>
<script src="<%=path%>/js/jquery.flot.resize.js?v=<%=staticVersion%>"></script>
<script src="<%=path%>/js/jquery.flot.pie.js?v=<%=staticVersion%>"></script>
<script src="<%=path%>/js/jquery.flot.stack.js?v=<%=staticVersion%>"></script>

<!-- jQuery Notification - Noty -->
<script src="<%=path%>/js/jquery.noty.js?v=<%=staticVersion%>"></script> <!-- jQuery Notify -->
<script src="<%=path%>/js/themes/default.js?v=<%=staticVersion%>"></script> <!-- jQuery Notify -->
<script src="<%=path%>/js/layouts/bottom.js?v=<%=staticVersion%>"></script> <!-- jQuery Notify -->
<script src="<%=path%>/js/layouts/topRight.js?v=<%=staticVersion%>"></script> <!-- jQuery Notify -->
<script src="<%=path%>/js/layouts/top.js?v=<%=staticVersion%>"></script> <!-- jQuery Notify -->
<!-- jQuery Notification ends -->

<script src="<%=path%>/js/sparklines.js?v=<%=staticVersion%>"></script> <!-- Sparklines -->
<script src="<%=path%>/js/jquery.cleditor.min.js?v=<%=staticVersion%>"></script> <!-- CLEditor -->
<script src="<%=path%>/js/bootstrap-datetimepicker.min.js?v=<%=staticVersion%>"></script> <!-- Date picker -->
<%-- <script src="<%=path%>/js/jquery.uniform.min.js?v=<%=staticVersion%>"></script> <!-- jQuery Uniform --> --%>
<script src="<%=path%>/js/bootstrap-switch.min.js?v=<%=staticVersion%>"></script> <!-- Bootstrap Toggle -->
<script src="<%=path%>/js/filter.js?v=<%=staticVersion%>"></script> <!-- Filter for support page -->
<script src="<%=path%>/js/custom.js?v=<%=staticVersion%>"></script> <!-- Custom codes -->
<script src="<%=path%>/js/charts.js?v=<%=staticVersion%>"></script> <!-- Charts & Graphs -->

<!-- Script for this page -->
<script type="text/javascript">

$(function(){
	parent.document.all.iframepage.height = document.body.scrollHeight;//设置iframe 的高度，依赖子页面内容高度。
});

function iFrameHeight(obj) {
		if(window.tripPanelFalg==1){
			window.tripPanelFalg=0;
			return;
		}
		var doc;
		if (obj.contentDocument) {
			doc = obj.contentDocument.body;
		} else if (obj.Document) {
			doc = obj.Document.body;
		}
		var h = Math.max(doc.clientHeight, doc.offsetHeight,
				doc.clientHeight,window.screen.availHeight-100);
		obj.style.height = h+"px";
	}
	
//scroll top hight for content frame//
function getScrollTop() {
    if ('pageYOffset' in window) {
        return window.pageYOffset;
    } else if (document.compatMode === "BackCompat") {
        return document.body.scrollTop;
    } else {
        return document.documentElement.scrollTop;
    }
}

$(function () {

    /* Bar Chart starts */

    var d1 = [];
    for (var i = 0; i <= 20; i += 1)
        d1.push([i, parseInt(Math.random() * 30)]);

    var d2 = [];
    for (var i = 0; i <= 20; i += 1)
        d2.push([i, parseInt(Math.random() * 30)]);


    var stack = 0, bars = true, lines = false, steps = false;
    
    function plotWithOptions() {
        $.plot($("#bar-chart"), [ d1, d2 ], {
            series: {
                stack: stack,
                lines: { show: lines, fill: true, steps: steps },
                bars: { show: bars, barWidth: 0.8 }
            },
            grid: {
                borderWidth: 0, hoverable: true, color: "#777"
            },
            colors: ["#ff6c24", "#ff2424"],
            bars: {
                  show: true,
                  lineWidth: 0,
                  fill: true,
                  fillColor: { colors: [ { opacity: 0.9 }, { opacity: 0.8 } ] }
            }
        });
    }

    plotWithOptions();
    
    $(".stackControls input").click(function (e) {
        e.preventDefault();
        stack = $(this).val() == "With stacking" ? true : null;
        plotWithOptions();
    });
    $(".graphControls input").click(function (e) {
        e.preventDefault();
        bars = $(this).val().indexOf("Bars") != -1;
        lines = $(this).val().indexOf("Lines") != -1;
        steps = $(this).val().indexOf("steps") != -1;
        plotWithOptions();
    });

    /* Bar chart ends */

});


/* Curve chart starts */

$(function () {
    var sin = [], cos = [];
    for (var i = 0; i < 14; i += 0.5) {
        sin.push([i, Math.sin(i)]);
        cos.push([i, Math.cos(i)]);
    }

    var plot = $.plot($("#curve-chart"),
           [ { data: sin, label: "sin(x)"}, { data: cos, label: "cos(x)" } ], {
               series: {
                   lines: { show: true, fill: true},
                   points: { show: true }
               },
               grid: { hoverable: true, clickable: true, borderWidth:0 },
               yaxis: { min: -1.2, max: 1.2 },
               colors: ["#1eafed", "#1eafed"]
             });

    function showTooltip(x, y, contents) {
        $('<div id="tooltip">' + contents + '</div>').css( {
            position: 'absolute',
            display: 'none',
            top: y + 5,
            left: x + 5,
            border: '1px solid #000',
            padding: '2px 8px',
            color: '#ccc',
            'background-color': '#000',
            opacity: 0.9
        }).appendTo("body").fadeIn(200);
    }

    var previousPoint = null;
    $("#curve-chart").bind("plothover", function (event, pos, item) {
        $("#x").text(pos.x.toFixed(2));
        $("#y").text(pos.y.toFixed(2));

        if ($("#enableTooltip:checked").length > 0) {
            if (item) {
                if (previousPoint != item.dataIndex) {
                    previousPoint = item.dataIndex;
                    
                    $("#tooltip").remove();
                    var x = item.datapoint[0].toFixed(2),
                        y = item.datapoint[1].toFixed(2);
                    
                    showTooltip(item.pageX, item.pageY, 
                                item.series.label + " of " + x + " = " + y);
                }
            }
            else {
                $("#tooltip").remove();
                previousPoint = null;            
            }
        }
    }); 

    $("#curve-chart").bind("plotclick", function (event, pos, item) {
        if (item) {
            $("#clickdata").text("You clicked point " + item.dataIndex + " in " + item.series.label + ".");
            plot.highlight(item.series, item.datapoint);
        }
    });

});

function logout(){
	if(confirm('是否要退出系统?')){
	var reqUrl='<%=path%>/AjaxChannel?action=LOGOUT_ACTION';
	$.ajax({
		type : 'POST',
		url:reqUrl,
		data: {},
		dataType : 'json',
		success : function(json) {
			if(json.SUCCESS=='1'){
				location.href='<%=path%>/page/login.jsp';	
//					jAlert(json.MSG, '提示',null,function(){
<%-- 						location.href='<%=path%>/page/index.jsp';	 --%>
//					}); 
			}else{
				 jAlert(json.MSG, '提示'); 
			}
		},
		error : function(e) {
			console.log(e);
		}
	});
  }
}

/* Curve chart ends */
</script>

</body>
</html>