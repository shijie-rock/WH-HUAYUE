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
  
  <!-- HTML5 Support for IE -->
  <!--[if lt IE 9]>
  <script src="js/html5shim.js"></script>
  <![endif]-->

  <!-- Favicon -->
  <link rel="shortcut icon" href="img/favicon/favicon.png">
</head>

<body class="content-body">


      <!-- Page heading -->
      <div class="page-head">
        <h2 class="pull-left"><i class="glyphicon glyphicon-calendar"></i> 检车员任务日历-示例</h2>

        <!-- Breadcrumb -->
        <div class="bread-crumb pull-right">
          <a href="javascript:void(0);" onclick="packUpDown(this);" ><i class="glyphicon glyphicon-menu-left"></i><span>展开</span></a> 
          |&nbsp;
          <a href="<%=path%>/page/index-content.jsp"><i class="icon-home"></i> 控制台</a> 
          <!-- Divider -->
<!--           <span class="divider">/</span>  -->
<!--           <a href="#" class="bread-current">Dashboard</a> -->
        </div>

        <div class="clearfix"></div>

      </div>
	    <div class="matter">
        <div class="container">
        
           <div class="row">

              <div class="col-md-12">

                <div class="widget">

                <div class="widget-head">
                  <div class="pull-left">查询条件</div>
                  <div class="query-condition-summry"></div>
                  <div class="widget-icons pull-right">
                    <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a> 
                    <a href="#" class="wclose"><i class="icon-remove"></i></a>
                  </div>  
                  <div class="clearfix"></div>
                </div>

                <div class="widget-content query-condition">
                
                <div class="form-group col-sm-3">
        			<label class="col-sm-5 control-label">检车员编号</label>
         			<div class="col-sm-7"><input type="text" class="form-control" placeholder="检车员编号" id="qr_input_position_code"></div>
        		</div>
        
       			<div class="form-group col-sm-3">
        			<label class="col-sm-5 control-label">检车员姓名</label>
         			<div class="col-sm-7"><input type="text" class="form-control" placeholder="检车员姓名" id="qr_input_position_name"></div>
        		</div>
        		


				<div class="form-group col-sm-3">
					<label class="checkbox-inline"> <input type="checkbox" id="qr_cb_include_stop" value="1"> 包含已停用</label>
				</div>
							<!-- 查询按钮 begin -->
        		 <div class="form-group col-sm-3">
						<button type="button" class="btn btn-success" id="qr_bt_query_position" onclick="dataTableInit();">查询</button>
						&nbsp;
						<button type="button" class="btn btn-danger clear" id="qr_bt_clear_position" onclick="btnQueryReset();">清空</button>
         		 </div>
         		 <!-- 查询按钮 end-->
         		 
			</div>
		</div>
		
		</div>
		
		</div>
		
          <!-- Table -->

            <div class="row">

              <div class="col-md-12">

              <!-- Widget -->
              <div class="widget">
                <!-- Widget title -->
                <div class="widget-head">
                  <div class="pull-left">检车员任务日历</div>
                  <div class="widget-icons pull-right">
                    <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a> 
                    <a href="#" class="wclose"><i class="icon-remove"></i></a>
                  </div>  
                  <div class="clearfix"></div>
                </div>
                <div class="widget-content">
                  <!-- Widget content -->
                  <div class="padd">
                    <!-- Below line produces calendar. I am using FullCalendar plugin. -->
                    <div id="calendar"></div>
                  </div>
                </div>
              </div> 

              </div>
            </div>
        </div>
	</div>

	<!-- delete Modal begin-->
	<div id="optModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
		<input  type="hidden" id="opt_confirm_position_id" value="" >
		<input  type="hidden" id="opt_confirm_position_name" value="" >
		<input  type="hidden" id="opt_confirm_opt_type" value="" >
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title">Modal title</h4>
				</div>
				<div class="alert opt-result-alert"></div>
				<div class="modal-body">
					msg...
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-loading-text="Loading" onclick="optConfirmDone(this);">确&nbsp;&nbsp;定</button>
					<button type="button" class="btn btn-default" data-dismiss="modal" aria-hidden="true">关&nbsp;&nbsp;闭</button>
				</div>
			</div>
		</div>
	</div>
	<!-- delete Modal end -->
	
	<!-- Modal add position -->
	<div id="modal_add_position" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content widget ">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title" id="modal_add_position_title"></h4>
				</div>
				<div class="alert opt-result-alert"></div>
				<div class="modal-body modal-scrollable" id="modal_add_position_content">

				 <div class="padd">
                 	 <div class="form-horizontal">
                 	 <input  type="hidden" id="add_input_position_coord" value="" >
                 	 <input  type="hidden" id="add_input_position_id" value="" >
                          					<div class="form-group">
                                            <label class="control-label col-md-3" for="add_input_position_code">检查地点代码</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_position_code" placeholder="检查地点代码">
                                              <span class="text-danger" >*</span>
                                            </div>
                                            <label class="control-label col-md-3" for="add_input_position_name">检查地点名称</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_position_name" placeholder="检查地点名称">
                                              <span class="text-danger">*</span>
                                            </div>
                                          </div>   
                                          <!--  -->
                                          <div class="form-group">
                                            <label class="control-label col-md-3" for="add_input_position_desc">检查地点说明</label>
                                            <div class="col-md-8">
<!--                                          <input  type="hidden" id="add_input_position_desc_here">锚点 -->
                                              <input type="text" class="form-control" id="add_input_position_desc" placeholder="检查地点说明">
                                            </div>
                                          </div>
                                          <div class="form-group">
                                            <label class="control-label col-md-3" for="add_input_position_address">地点地图地址</label>
                                            <div class="col-md-8">
                                              	 <span class="mid-word-text" id="add_input_position_address"></span>
                                            </div>
                                          </div>
										<div class="form-group"><center><hr class="modal-hr"/></center>
											<div class="panel-body" id="add_input_position_map"><p>检查地点地图位置</p>
												<div id="add_input_position_map_content" style="height:20%;border:solid 2px #fff;margin:10 10 0 10;">
											
												</div>
											
											</div>
								         </div>  
								</div>
               		 </div>
				</div>
				<div class="modal-footer">
<!-- 					<button type="button" class="btn btn-primary">确定</button> --><!-- data-opt-type="" new/edit -->
					<button type="button" id="btn_save_position" data-opt-type="" class="btn btn-success" data-loading-text="Loading" onclick="savePositionConfirm(this);">保&nbsp;&nbsp;存</button>
					<button type="button" class="btn btn-default" data-dismiss="modal" aria-hidden="true">关&nbsp;&nbsp;闭</button>

				</div>
			</div>
		</div>
	</div>

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
<script src="<%=path%>/js/jquery-ui.min-1.12.1.js?v=<%=staticVersion%>"></script> <!-- Bootstrap -->
<script src="<%=path%>/js/fullcalendar.min.js?v=<%=staticVersion%>"></script> <!-- Bootstrap -->
<!-- table收起 -->
<script src="<%=path%>/js/truck-inspect-common.js?v=<%=staticVersion%>"></script> <!-- Custom codes -->

<!-- Script for this page -->
<script type="text/javascript">

$(function() {
	  
    var date = new Date();
    var d = date.getDate();
    var m = date.getMonth();
    var y = date.getFullYear();
    
    $('#calendar').fullCalendar({
      header: {
        left: 'prev',
        center: 'title',
        right: 'month,agendaWeek,agendaDay,next'
      },
      editable: true,
      events: [
        {
          title: '鲁F-12345检查任务',
          start: new Date(y, m, 1)
        },
        {
          title: '鲁F-4567检查任务',
          start: new Date(y, m, d-5),
          end: new Date(y, m, d-2)
        },
        {
          id: 999,
          title: '鲁F-1234检查任务',
          start: new Date(y, m, d-3, 16, 0),
          allDay: false
        },
        {
          id: 999,
          title: '鲁F-1234检查任务',
          start: new Date(y, m, d+4, 16, 0),
          allDay: false
        },
        {
          title: '鲁F-1234检查任务',
          start: new Date(y, m, d, 10, 30),
          allDay: false
        },
        {
          title: '鲁F-1234检查任务',
          start: new Date(y, m, d, 12, 0),
          end: new Date(y, m, d, 14, 0),
          allDay: false
        },
        {
          title: '鲁F-1234检查任务',
          start: new Date(y, m, d+1, 19, 0),
          end: new Date(y, m, d+1, 22, 30),
          allDay: false
        },
        {
          title: '鲁F-12345检查任务',
          start: new Date(y, m, 28),
          end: new Date(y, m, 29),
//           url: 'http://google.com/'
        }
      ]
    });
    
  });


	$(function() {
		dataTableInit();
	}); 
	
	function dataTableInit() {
		//param
		var positionName = $("#qr_input_position_name").val();
		var positionCode = $("#qr_input_position_code").val();
		var includeStop=(true==$("#qr_cb_include_stop").is(':checked'))?"1":"0";
		
		//格式：列映射关系
		var aoColumns = '[{"mDataProp" : "POSITION_ID","sTitle" : "序号"}, '
					   + '{"mDataProp" : "POSITION_CODE","sTitle" : "检查地点代码"},'
					   + '{"mDataProp" : "POSITION_NAME","sTitle" : "检查地点名称"},'
					   + '{"mDataProp" : "POSITION_DESC","sTitle" : "检查地点说明"},'
					   + '{"mDataProp" : "POSITION_ADDRESS","sTitle" : "检查地点位置"},'
					   + '{"mDataProp" : "FREEZE_TAG","sTitle" : "检查地点状态"},'
					   + '{"mDataProp" : "POSITION_CODE","sTitle" : "操作"}]';

		var reqData = {
			action : 'BASE_DATA_POSITION_QUERY_LIST_ACTION',
			params : 'params',
			POSITION_NAME:positionName,
			POSITION_CODE:positionCode,
			INCLUDE_STOP:includeStop
			};
		//example为table定义id ：10：需要加入操作按钮的列；[4,5]：需要转义的列（映射数据字典值）
		//initPositionTable('positionList', aoColumns, reqData, '<%=path%>',6,[]); //todo
		resetIFrameLength();//need ajax async:false,
		} 
	/*title tips- common method,dynamic bind*/
	$(function () { $("[data-toggle='tooltip']").tooltip();});
	
	/*查询条件清空重置查询*/
	function btnQueryReset(){
		queryBtnRestClear();
		dataTableInit();
	}
	

</script>

</body>
</html>