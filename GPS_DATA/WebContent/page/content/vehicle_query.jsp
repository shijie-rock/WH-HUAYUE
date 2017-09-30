<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../comm/iframeHead.jsp"%>
 <html lang="zh-CN">
  <head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>车辆信息查询</title>

    <!-- Bootstrap core CSS -->
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="../../assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="<%=path%>/css/dashboard.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="../../assets/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>
    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">车辆信息查询</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
<!--             <li><a href="#">菜单1</a></li> -->
<!--             <li><a href="#">菜单2</a></li> -->
<!--             <li><a href="#">菜单3</a></li> -->
            <li><a href="javascript:void(0)" onclick="logout()" >系统登出</a></li>
          </ul>
          <form class="navbar-form navbar-right">
            <input type="text" class="form-control" placeholder="Search...">
          </form>
        </div>
      </div>
    </nav>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
          <ul class="nav nav-sidebar">
            <li class="active"><a href="#">主页 <span class="sr-only">(current)</span></a></li>
            <li><a href="#">Reports</a></li>
            <li><a href="#">Analytics</a></li>
            <li><a href="#">Export</a></li>
          </ul>
<!--           <ul class="nav nav-sidebar"> -->
<!--             <li><a href="">Nav item</a></li> -->
<!--             <li><a href="">Nav item again</a></li> -->
<!--             <li><a href="">One more nav</a></li> -->
<!--             <li><a href="">Another nav item</a></li> -->
<!--             <li><a href="">More navigation</a></li> -->
<!--           </ul> -->
<!--           <ul class="nav nav-sidebar"> -->
<!--             <li><a href="">Nav item again</a></li> -->
<!--             <li><a href="">One more nav</a></li> -->
<!--             <li><a href="">Another nav item</a></li> -->
<!--           </ul> -->
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main" style="padding-right:20px;padding-left:20px;">
<!--           <h1 class="page-header">Dashboard</h1> -->

<!--           <div class="row placeholders"> -->
<!--             <div class="col-xs-6 col-sm-3 placeholder"> -->
<!--               <img src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" width="200" height="200" class="img-responsive" alt="Generic placeholder thumbnail"> -->
<!--               <h4>Label</h4> -->
<!--               <span class="text-muted">Something else</span> -->
<!--             </div> -->
<!--             <div class="col-xs-6 col-sm-3 placeholder"> -->
<!--               <img src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" width="200" height="200" class="img-responsive" alt="Generic placeholder thumbnail"> -->
<!--               <h4>Label</h4> -->
<!--               <span class="text-muted">Something else</span> -->
<!--             </div> -->
<!--             <div class="col-xs-6 col-sm-3 placeholder"> -->
<!--               <img src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" width="200" height="200" class="img-responsive" alt="Generic placeholder thumbnail"> -->
<!--               <h4>Label</h4> -->
<!--               <span class="text-muted">Something else</span> -->
<!--             </div> -->
<!--             <div class="col-xs-6 col-sm-3 placeholder"> -->
<!--               <img src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" width="200" height="200" class="img-responsive" alt="Generic placeholder thumbnail"> -->
<!--               <h4>Label</h4> -->
<!--               <span class="text-muted">Something else</span> -->
<!--             </div> -->
<!--           </div> -->
	<!-- BEGIN EDITABLE TABLE widget-->
	<div class="row-fluid" style="margin-top:45px;overflow-y:auto;">
		<div class="span12">
			<!-- BEGIN EXAMPLE TABLE widget-->
			<div class="widget pink condition-table-bottom" id="head_height">
				<div class="widget-title">
					<h4>
						车辆搜索
<!-- 						<i class="icon-search"></i>&nbsp;车辆搜索 -->
					</h4>
					<span class="tools"> <a href="javascript:;"
						class="icon-chevron-down"></a>
					</span>
				</div>
				<div class="widget-body" style="padding: 10px;">
					<div class="row-fluid">
						<div class="span10">
							<div class="row-fluid">
								<div class="span12" style="margin-left: 0;" id="searchKey">
									<input type="hidden" id="currentiDisplayStart" value="" /> 
									<input type="hidden" id="currentiDisplayLength" value="" />
									<span style=""> 开始时间：</span> <input style="width:120px;height: 30px" id="startDate" onclick="WdatePicker({skin:'twoer',dateFmt:'yy-MM-dd HH:mm'})" value=""/>
									<span style=""> 结束时间：</span> <input style="width:120px;height: 30px" id="endDate" onclick="WdatePicker({skin:'twoer',dateFmt:'yy-MM-dd HH:mm'})" value=""/>
									<span style=""> 车牌：</span> <input style="width:120px;height: 30px" id="vehicle_license" value=""/>
<!-- 								<span style=""> 车牌：</span> <input style="width:120px;height: 30px" id="vehicle_license" value="鲁FV9555"/> -->
									<!-- 
									<span style="font-weight: bold;"> 发送内容：</span> <input style="width:100px" id="sendCont" value=""/>
									<span style="font-weight: bold;"> 信息状态：</span>
										<select id="msgStatus" >
											<option id="nothing" value=""></option>
											<option id="notDeal" value="SS_0010">未发送</option>
											<option id="dealing" value="SS_0020">发送中</option>
											<option id="directDeal" value="SS_0030">已发送</option>
										</select>
									 -->	
								    <button class="btn btn-medium btn-info bold" style="width: 100px;line-height: 30px;padding:1px 1px;" type="button" id="searchButton">检索</button>
								    <button class="btn btn-medium btn-success bold" style="width: 100px;line-height: 30px;padding:1px 1px;" type="button" id="exportButton">导出当前</button>
								    <button class="btn btn-medium btn-danger bold" style="width: 100px;line-height: 30px;padding:1px 1px;" type="button" id="exportAllButton">导出全部</button>
								   <%--  <yb:select dataSource="DATA_DIC.MSG_PROCESS_STATUS" selectId="msgStatus" selectName="msgStatus" /> --%>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="widget pink" id="body_height" style="height:70%;">
				<div class="widget-title">
					<h4>
						<i class="icon-reorder"></i> 信息一览
					</h4>
				</div>
				<div id="allmap" style='display: none;'></div>
				<div class="span12 change-height"></div>
				
				<div class="widget-body">
					<div class="clearfix">
<!-- 						<div class="btn-group"> -->
<!-- 							<button class="btn btn-medium bold leftNewBtn btn-primary" id="addMsgMain" style="width:113px;">导出结果<i class="icon-plus"></i></button> -->
<!-- 						</div> -->
					</div>
					<div role="grid" class="dataTables_wrapper">
						<table id=msgMainInfo class="table table-striped table-hover table-bordered">
							<thead>
								<tr align="left">
									<th style="width:8% !important">序号</th>
									<th style="width:12% !important">车牌号</th>
									<th style="width:12% !important">时长</th>
									<th style="width:12% !important">距离</th>
									<th>类型</th>
									<th style="width:30% !important">状态</th>
									<th style="width:15% !important">操作</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
						</div>
						</div>
					</div>
				</div>
			</div>
        </div>
      </div>
    </div>
    <!-- vehicle detail info -->
   	<div id="roleModal" class="modal fade vehicle_modal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true" >
		<div class="modal-header">
			<div class="row-fluid">
				<div class="col-md-8" style="vertical-align: middle;">
					<h3>
						<span id="vehicle_title" class="info_title">车辆信息</span> 

					</h3>
				</div>
				<div class="col-md-2" style="margin-top:5px;">

					<a id='saveRole' href="javascript:void(0);" onclick="exportSingleData()" class="btn btn-large btn-primary">导出</a>
				</div>
				<div class="col-md-2" style="margin-top:5px;">
					<button id='leaveRole' type="button" class="btn btn-large"
						data-dismiss="modal" aria-hidden="true">关闭</button>
				</div>
			</div>
		</div>
		<div class="modal-body" id="modal-body-action">
			<input type="hidden" id="currentVehicle_license" value="" /> 
			<div class="row-fluid">
				<div class="col-md-2 label_right">车牌</div>			
				<div class="col-md-4 label_value_left"><input class="input-small" type="text" id="carnum"></div>
				<div class="col-md-2 label_right">厢型</div>			
				<div class="col-md-4 label_value_left"><input class="input-small" type="text" id="carriagetype"></div>			
			</div>
			<div class="row-fluid">
				<div class="col-md-2 label_right">驾照类型</div>			
				<div class="col-md-4 label_value_left"><input class="input-small" type="text" id="drivinglicensebrand"></div>
				<div class="col-md-2 label_right">来源机构</div>			
				<div class="col-md-4 label_value_left"><input class="input-small" type="text" id="fromorgcode"></div>			
			</div>
			<div class="row-fluid">
				<div class="col-md-2 label_right">里程</div>			
				<div class="col-md-4 label_value_left"><input class="input-small" type="text" id="gmileage"></div>
				<div class="col-md-2 label_right">运行时长</div>			
				<div class="col-md-4 label_value_left"><input class="input-small" type="text" id="duration"></div>			
			</div>
			<div class="row-fluid">
				<div class="col-md-2 label_right">车长</div>			
				<div class="col-md-4 label_value_left"><input class="input-small" type="text" id="length"></div>
				<div class="col-md-2 label_right">额定体积</div>			
				<div class="col-md-4 label_value_left"><input class="input-small" type="text" id="volume"></div>			
			</div>
			<div class="row-fluid">
				<div class="col-md-2 label_right">适用类型</div>			
				<div class="col-md-4 label_value_left"><input class="input-small" type="text" id="usetype"></div>
				<div class="col-md-2 label_right">车辆状态</div>			
				<div class="col-md-4 label_value_left"><input class="input-small" type="text" id="status"></div>			
			</div>
			<div class="row-fluid">
				<div class="col-md-2 label_right">车辆类型</div>			
				<div class="col-md-4 label_value_left"><input class="input-small" type="text" id="status_code"></div>
				<div class="col-md-2 label_right">设备号</div>			
				<div class="col-md-4 label_value_left"><input class="input-small" type="text" id="gpsno"></div>			
			</div>
			<div class="row-fluid">
				<div class="col-md-2 label_right">备注</div>			
				<div class="col-md-10 label_value_left"><input class="input-xlarge" type="text" id="counttime" style="width:87%;"></div>
<!-- 				<div class="col-md-2 label_right">设备号</div>			 -->
<!-- 				<div class="col-md-4 label_value_left"><input class="input-small" type="text" id="gpsno"></div>			 -->
			</div>

		</div>
		

	</div> 

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="<%=path%>/js/jquery-2.0.0.js"></script>
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
    <script src="<%=path%>/js/bootstrap/js/bootstrap.min.js"></script>
    <!-- Just to make our placeholder images work. Don't actually copy the next line! -->
    <script src="../../assets/js/vendor/holder.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
    
	<script src="<%=path%>/js/chosen-bootstrap/chosen/chosen.jquery.min.js" type="text/javascript" ></script>
	<script src="<%=path%>/js/jquery.nicescroll.js" type="text/javascript"></script>
	<script src="<%=path%>/js/bootstrap/js/bootstrap-multiselect.js" type="text/javascript"></script>
	<script src="<%=path%>/js/data-tables/jquery.dataTables.min.js"	type="text/javascript"></script>
	<script src="<%=path%>/js/data-tables/dataTables.bootstrap.min.js"	type="text/javascript"></script>
<%-- 	<script src="<%=path%>/js/data-tables/jquery.dataTables.js"	type="text/javascript"></script> --%>
<%-- 	<script src="<%=path%>/js/data-tables/DT_bootstrap.js"	type="text/javascript"></script> --%>
	<script src="<%=path%>/js/youbus-common.js" type="text/javascript"></script>
 	<script src="<%=path%>/js/jquery-slimscroll/jquery-ui-1.9.2.custom.min.js" type="text/javascript"></script>
	<script src="<%=path%>/js/busi-js/receive_msg_list.js" type="text/javascript"></script>
	<script src="<%=path%>/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	<script src="<%=path%>/js/common-scripts.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path%>/js/jqueryAlert/jquery.alerts.js" type="text/javascript" charset="utf-8"></script>
<script>
$(function(){
		dataTableInit();
	});

	function dataTableInit() {
		var startDate=$("#startDate").val();
		var endDate=$("#endDate").val();
		var vehicle_license=$("#vehicle_license").val();
// 		var sendCont=$("#sendCont").val();
// 		var msgStatus=$("#msgStatus").val();

	 //格式：列映射关系
		 var aoColumns ='[{"mDataProp" : "id","sTitle" : "序号"}, '
			+ '{"mDataProp" : "carnum","sTitle" : "车牌号"},'	
	 		+ '{"mDataProp" : "duration","sTitle" : "时长"},'	 		
		 	+ '{"mDataProp" : "gmileage","sTitle" : "距离"},'
		 	+ '{"mDataProp" : "status_code","sTitle" : "类型"},'
			+ '{"mDataProp" : "emptydriving","sTitle" : "状态"},'
			+ '{"mDataProp" : "carnum","sTitle" : "操作"}]';

		var reqData={action : 'QUERY_VEHICLE_MSG_ACTION',params : 'params'};
		//example为table定义id ：10：需要加入操作按钮的列；[4,5]：需要转义的列（映射数据字典值）
		initSendTable('msgMainInfo',aoColumns,reqData,'<%=path%>',6,[5],startDate,endDate,vehicle_license);
		
// 		$("#body_height").height($("#msgMainInfo_wrapper").height() +100)
	} 
	$("#searchButton").click(function(){
		dataTableInit();
	});
	$("#exportButton").click(function(){
		exportData();
	});
	$("#exportAllButton").click(function(){
		exportAllData();
	});
	
	function loadVehicleDetailInfo(vehicle_license){
// 	 	$("#roleModal").css("top",window.parent.window.getModalTop());
		$("#vehicle_title").text('车辆信息：'+vehicle_license);
		$("div.label_value_left > input").attr("disabled","disabled");  
		$("div.label_value_left > input").val("");  
		
		var startDate=$("#startDate").val();
		var endDate=$("#endDate").val();
		
		var reqUrl='<%=path%>/AjaxChannel?action=QUERY_SINGLE_VEHICLE_MSG_ACTION';
		$.ajax({
				type : 'POST',
				url:reqUrl,
				data: {vehicle_license:vehicle_license,startDate:startDate,endDate:endDate
// 					   name:$("#empModal #name").val(),
					   },
				dataType : 'json',
				success : function(json) {
					if(json.SUCCESS=='1'){
// 						jAlert("success", '提示'); 
						$("#modal-body-action #carnum").val(json.VEHICLE_INFO.Carnum);
						$("#modal-body-action #carriagetype").val(json.VEHICLE_INFO.Carriagetype);
						$("#modal-body-action #drivinglicensebrand").val(json.VEHICLE_INFO.Drivinglicensebrand);
						$("#modal-body-action #fromorgcode").val(json.VEHICLE_INFO.Fromorgcode);
						$("#modal-body-action #gmileage").val(json.VEHICLE_INFO.Gmileage);
						$("#modal-body-action #duration").val(json.VEHICLE_INFO.Duration);
						$("#modal-body-action #length").val(json.VEHICLE_INFO.Length);
						$("#modal-body-action #volume").val(json.VEHICLE_INFO.Volume);
						$("#modal-body-action #usetype").val(json.VEHICLE_INFO.Usetype);
						$("#modal-body-action #status").val(json.VEHICLE_INFO.Status);
						$("#modal-body-action #status_code").val(json.VEHICLE_INFO.Status_code);
						$("#modal-body-action #gpsno").val(json.VEHICLE_INFO.Gpsno);
						$("#modal-body-action #counttime").val("最近上报时间:"+json.VEHICLE_INFO.Counttime);
						$("#modal-body-action #currentVehicle_license").val(json.VEHICLE_INFO.Carnum);
						
					}else{
// 						$("#empModal #text-html").html(json.MSG);
						 jAlert(json.MSG, '提示'); 
					}
				},
				error : function(e) {
					console.log(e);
				}
			});


	 	$('#roleModal').modal({
	 		backdrop : 'static',
	 		keyboard : false
	 	});	
	}

	function logout(){
		jConfirm('系统退出？', '提示',null,function(r){
				if(r){
// 					alert('退出');
					var reqUrl='<%=path%>/AjaxChannel?action=SYS_LOGOUT_ACTION';
					$.ajax({
							type : 'POST',
							url:reqUrl,
							dataType : 'json',
							success : function(json) {
								location.href='<%=path%>/page/index.jsp';	
							},
							error : function(e) {
								console.log(e);
								location.href='<%=path%>/page/index.jsp';	
							}
						});
				}
		});
	}
	
	function exportData(license){
// 		alert(license);
		var startDate=$("#startDate").val();
		var endDate=$("#endDate").val();
		var vehicle_license=$("#vehicle_license").val();
		if(license!=''&&typeof(license)!="undefined")vehicle_license=license;
		
		var iDisplayStart=$('#currentiDisplayStart').val();
		var iDisplayLength=$('#currentiDisplayLength').val();
		
	    var serverUrl ="<%=path%>/DownloadExcelChannel?vehicle_license="+vehicle_license+"&startDate="+startDate+"&endDate="+endDate+"&iDisplayStart="+iDisplayStart+"&iDisplayLength="+iDisplayLength;
	    window.open(serverUrl); 
	}
	
	function exportAllData(){
// 		alert(license); //全部导出
		var startDate=$("#startDate").val();
		var endDate=$("#endDate").val();
		var vehicle_license=$("#vehicle_license").val();
		
	    var serverUrl ="<%=path%>/DownloadExcelChannel?vehicle_license="+vehicle_license+"&startDate="+startDate+"&endDate="+endDate+"&all=1";
	    window.open(serverUrl); 
	}
	
	function exportSingleData(){
		exportData($("#currentVehicle_license").val());
	}
	
	</script> 
  </body>
</html>
