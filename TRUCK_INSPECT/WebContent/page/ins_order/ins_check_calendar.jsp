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
  <link href='<%=path%>/js/calendar/fullcalendar.css' rel='stylesheet' />
<%--   <link href='<%=path%>/js/calendar/fullcalendar.print.css' rel='stylesheet' media='print' /> --%>
</head>

<body class="content-body">


      <!-- Page heading -->
      <div class="page-head">
        <h2 class="pull-left"><i class="glyphicon glyphicon-calendar"></i> 检车任务日历</h2>

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
                <!--   -->

       
       			<div class="form-group col-sm-3">
        			<label class="col-sm-5 control-label" for="qr_input_check_member_name">检车员姓名</label>
         			<div class="col-sm-7"><input type="text" class="form-control" placeholder="检车员姓名" id="qr_input_check_member_name"></div>
        		</div>
       			<div class="form-group col-sm-3">
        			<label class="col-sm-5 control-label" for="qr_input_check_license_name">检车车牌</label>
         			<div class="col-sm-7"><input type="text" class="form-control" placeholder="检车车牌" id="qr_input_check_license_name"></div>
        		</div>
        		<div class="form-group col-sm-3">
					<label class="checkbox-inline"> <input type="checkbox" id="qr_cb_include_stop" value="1"> 包含已停用</label>
				</div>
				<!--
        		 <div class="form-group col-sm-3">
        			<label class="col-sm-5 control-label">检车员编号</label>
         			<div class="col-sm-7"><input type="text" class="form-control" placeholder="检车员编号" id="qr_input_position_code"></div>
        		</div> -->
				<!-- 查询按钮 begin -->
        		 <div class="form-group col-sm-3">
						<button type="button" class="btn btn-success" id="qr_bt_query_member_cal" onclick="calendarInit();">查询</button>
						&nbsp;
						<button type="button" class="btn btn-danger clear" id="qr_bt_clear_member_cal" onclick="btnQueryReset();">清空</button>
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
<script src="<%=path%>/js/jquery-ui.min-1.12.1.js?v=<%=staticVersion%>"></script><!-- 注意与bootstrap的先后顺序，jquery ui 与 bootstrap tips冲突 -->
<script src="<%=path%>/js/bootstrap.js?v=<%=staticVersion%>"></script> <!-- Bootstrap -->
<script src="<%=path%>/js/jquery-ui.min-1.12.1.js?v=<%=staticVersion%>"></script> <!-- Bootstrap -->
<script src='<%=path%>/js/moment.min.js'></script>
<!-- table收起 -->
<script src="<%=path%>/js/truck-inspect-common.js?v=<%=staticVersion%>"></script> <!-- Custom codes -->
<!-- fullcalendar -->
<script src='<%=path%>/js/calendar/fullcalendar.min.js'></script>
<script src='<%=path%>/js/calendar/lang-all.js'></script>

<!-- Script for this page -->
<script type="text/javascript">

$(function() {
	calendarInit();
  });
  
  function  calendarInit(){
	    $('div.popover.fade.top.in').hide();
	    var date = new Date();
	    var d = date.getDate();
	    var m = date.getMonth();
	    var y = date.getFullYear();
	    var memberName=$('#qr_input_check_member_name').val();
	    var license=$('#qr_input_check_license_name').val();
	    
	    $('#calendar').fullCalendar('destroy');
	    $('#calendar').fullCalendar({
	      header: {
	        left: 'prev,next',
	        center: 'title'
// 	        right: 'next'
// 	        right: 'month,agendaWeek,agendaDay,next'
	      },
	  	  lang:'zh-cn',
		  weekMode:'liquid', //设置每月显示不定周数
		  handleWindowResize:true,
		  aspectRatio:1.5,
	      editable: true,
	      droppable:false,
	      eventLimit: true, // when too many events in a day, show the popover
	      /*
	      eventDragStart:function(event, jsEvent, ui, view){
	    	  if("COS_0010"!=event.orderStatus){
	    		 alert('只有待检单可以拖动更改时间。'); 
	    		 revertFunc();//恢复原状 
	    		 return false;
	    	  }
	    	  $('div.popover.fade.top.in').hide();
	      },
	      */
		  eventDrop: function(event, delta, revertFunc) {//内部拖动
		    	  if("COS_0010"!=event.orderStatus){
		     		 alert('只有待检单才可以拖动更改时间。'); 
		     		 revertFunc();//恢复原状 
		     		 $('div.popover.fade.top.in').hide();
		     		 return false;
		     	  }
//	          alert(event.title + " was dropped on " + event.start.format());
	            var newBegin= moment(event.start).format('YYYY-MM-DD hh:mm:ss');
	            var newEnd= moment(event.end).format('YYYY-MM-DD hh:mm:ss');
	            var checkOrderId=event.id;
	            if (!confirm('您确认要将任务['+event.title+']拖动到['+newBegin+']?')) {
// 	              alert('newBegin:='+newBegin);
// 	              alert('newEnd:='+newEnd);
	              revertFunc();//恢复原状 
	            }else{
	    			$.ajax({
	    				url: '<%=path%>/AjaxChannel?action=BUSI_DATA_CHECK_ORDER_CALENDAR_UPDATE_PL_TIME_ACTION',
	    				data:{NEW_START:newBegin,NEW_END:newEnd,CHECK_ORDER_ID:checkOrderId},
	    				dataType: 'json',
	    				type:"post",
	    				success: function(data) {
							if(data.SUCCESS==1){
								console.log('msg:='+data.MSG);
							}else{
								revertFunc();//恢复原状 
							}
	    				},		
	    				error: function() {
	    					$('#script-warning').show();
	    					revertFunc();//恢复原状 
	    				}
	    			
	    			});
	            	
	            }
	            $('div.popover.fade.top.in').hide();
	      },
	      eventRender: function(eventObj, $el) {//
	          $el.popover({
	            title: eventObj.popTitle,
	            content:eventObj.popContent,
	            trigger: 'hover',
	            placement: 'top',
	            container: 'body'
	          });
	       },
	      
	      events:function(start, end,timezone,callback){
			var viewStart = moment(start).format('YYYY-MM-DD');
//	 		setBeginDate=new Date(start);
			var viewEnd = moment(end).format('YYYY-MM-DD');
			$.ajax({
			url: '<%=path%>/AjaxChannel?action=BUSI_DATA_CHECK_ORDER_MEMBER_CALENDAR_ACTION',
			data:{START:viewStart,END:viewEnd,MEMBER_NAME:memberName,LICENSE:license},
			dataType: 'json',
			type:"post",
			success: function(data) {
			    var events = [];
				if(data.CALENDAR_TASK_LIST!=null){
					$.each((data.CALENDAR_TASK_LIST),function(i,item){ //指定 -dataType: 'json',
						if(item.PL_MEMBER_NAME!=null)
		                 events.push({
		                	 id:item.ID,
		                	 title:item.PL_MEMBER_NAME.substring(0, 6)+'-'+item.CHECK_TARGET_NAME+'['+getEventChinese(item.CHECK_ORDER_STATUS,item.CHECK_ORDER_RESULT)+']',
		                	 desc:item.CHECK_TARGET_NAME, //自定义
		                	 orderDesc:item.CHECK_ORDER_DESC,
		                	 address:item.POSITION_NAME,
		                	 start:item.P_BEGIN_TIME,// will be parsed//data 类型
		                	 end:item.P_END_TIME,//增加结束时间
		                	 allDay:false,
							 popTitle:item.PL_MEMBER_NAME.substring(0, 6)+'-'+item.CHECK_TARGET_NAME+'['+getEventChinese(item.CHECK_ORDER_STATUS,item.CHECK_ORDER_RESULT)+']',
							 popContent:item.P_BEGIN_TIME+'-'+item.P_END_TIME+':'+parseNull2Empty(item.POSITION_NAME)+'-'+parseNull2Empty(item.CHECK_ORDER_DESC),
		                	 orderStatus:item.CHECK_ORDER_STATUS,
		                	 orderResult:item.CHECK_ORDER_RESULT,
							 color: getEventColor(item.CHECK_ORDER_STATUS,item.CHECK_ORDER_RESULT),
//	 						 color: 'purple',
						     className:"calendarEventClass"
		             });//end push
						
					});//end each
				}
	            callback(events);	
	            //渲染默认司机到各个td
//	          optionButtonCheck();  
	            $('div.popover.fade.top.in').hide();
			},		
			error: function() {
				$('#script-warning').show();
			}
		
		});
		},//
	    });  
	    resetIFrameLength();//need ajax async:false,
  }
  
//自动填充
$(function() {//自动填充开始
	 /*查询条件-检查对象名称绑定联想输入 begin*/
	 $("#qr_input_check_license_name").autocomplete({
			source: function( request, response ) {
//		 	var userId = $("#birds").val();
				$.ajax({
					type: "POST",
					url: "<%=path%>/AjaxChannel?action=BUSI_DATA_QUERY_BASEDATA_AUTO_COMPLETE_ACTION",
					dataType: "json",
					data: {
						QUERY_NAME:$("#qr_input_check_license_name").val(),
						QUERY_TYPE:"TARGET",
						maxRows: 10
					},
					success: function( data ) {
//							alert(data.RESULT_LIST);
						if(data.RESULT_LIST!=undefined){
							response( $.map(data.RESULT_LIST, function(bean) {
								return {
									id:bean.ID,
									value: bean.TRUCK_LICENSE //显示的列
								}
							}));
						}else{
							$('#ui-id-1').hide();
//								alert(1);
						}
					}
				});
			},
			minLength: 0,
			max: 10,
			select: function( event, ui ) {
				$("#qr_input_check_license_name").val(ui.item.value);
			},
			open: function() {},//结果列表弹出时
			close: function() {},//结果列表关闭时
			change: function( event, ui ) {}//当值改变时，ui.item为选中的项
		});
	 /*检查对象名称绑定联想输入 end*/

	 /*查询条件-检查人姓名绑定联想输入 begin*/
	 $("#qr_input_check_member_name").autocomplete({
			source: function( request, response ) {
//		 	var userId = $("#birds").val();
				$.ajax({
					type: "POST",
					url: "<%=path%>/AjaxChannel?action=BUSI_DATA_QUERY_BASEDATA_AUTO_COMPLETE_ACTION",
					dataType: "json",
					data: {
						QUERY_NAME:$("#qr_input_check_member_name").val(),
						QUERY_TYPE:"CHECKER",
						maxRows: 10
					},
					success: function( data ) {
						if(data.RESULT_LIST!=undefined){
							response( $.map(data.RESULT_LIST, function(bean) {
								return {
									id:bean.ID,
									value: bean.MEMBER_NAME //显示的列
								}
							}));
						}else{
							$('#ui-id-2').hide();
//							alert(2);
						}
					}
				});
			},
			minLength: 0,
			max: 10,
			select: function( event, ui ) {
				$("#qr_input_check_member_name").val(ui.item.value);
			},
			open: function() {},//结果列表弹出时
			close: function() {},//结果列表关闭时
			change: function( event, ui ) {}//当值改变时，ui.item为选中的项
		});
	 /*检查-检查人姓名绑定联想输入 end*/
	});
	/*title tips- common method,dynamic bind*/
	$(function () { $("[data-toggle='tooltip']").tooltip();});
	
	/*查询条件清空重置查询*/
	function btnQueryReset(){
		queryBtnRestClear();
		dataTableInit();
	}
	
	//根据检查单状态及结果，返回事件颜色
	function getEventColor(orderStatus,orderResult){
//    	 item.CHECK_ORDER_STATUS
//    	 COS_0010:待检查  purple
//    	 COS_0020:检查中 default
//    	 COS_0030..:已检查及之后
//    	 item.CHECK_ORDER_RESULT
//    	 COR_0030:全部合格:green
//    	 other:red
		 if("COS_0010"==orderStatus){ //待检查  purple
			 return "#00bcd4";
// 			 return "purple";
		 }else if("COS_0020"==orderStatus){ //检查中 default
			 return "#36c";
		 }else{ //已检查及之后
			 if("COR_0030"==orderResult){//COR_0030 全部合格:green
				 return "green";
			 }else{//RED
				 return "red";
			 }
		 }
	}
	
	function getEventChinese(orderStatus,orderResult){
//   	 item.CHECK_ORDER_STATUS
//   	 COS_0010:待检查  purple
//   	 COS_0020:检查中 default
//   	 COS_0030..:已检查及之后
//   	 item.CHECK_ORDER_RESULT
//   	 COR_0030:全部合格:green
//   	 other:red
		 if("COS_0010"==orderStatus){ //待检查  purple
			 return "待检";
//			 return "purple";
		 }else if("COS_0020"==orderStatus){ //检查中 default
			 return "检中";
		 }else{ //已检查及之后
			 if("COR_0030"==orderResult){//COR_0030 全部合格:green
				 return "合格";
			 }else{//RED
				 return "不合格";
			 }
		 }
	}
	
</script>

</body>
</html>