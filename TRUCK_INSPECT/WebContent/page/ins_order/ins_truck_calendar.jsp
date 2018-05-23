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
        <h2 class="pull-left"><i class="glyphicon glyphicon-calendar"></i> 车辆任务任务日历-示例</h2>

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
        			<label class="col-sm-5 control-label">车牌号</label>
         			<div class="col-sm-7"><input type="text" class="form-control" placeholder="车牌号" id="qr_input_position_code"></div>
        		</div>
        
<!--        			<div class="form-group col-sm-3"> -->
<!--         			<label class="col-sm-5 control-label">检车员姓名</label> -->
<!--          			<div class="col-sm-7"><input type="text" class="form-control" placeholder="检车员姓名" id="qr_input_position_name"></div> -->
<!--         		</div> -->
        		


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
                  <div class="pull-left">车辆任务日历</div>
                  <div class="widget-icons pull-right">
                    <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a> 
                    <a href="#" class="wclose"><i class="icon-remove"></i></a>
                  </div>  
                  <div class="clearfix"></div>
                </div>
                <div class="widget-content" id="ins-truck-cal-div">
                  <!-- Widget content -->
                  <div class="padd">
                    <!-- Below line produces calendar. I am using FullCalendar plugin. -->
                    <div id="calendar" class="ins-truck-cal-div"></div>
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
          title: '鲁F-12345宁波',
          start: new Date(y, m, 3)
        },
        {
          title: '鲁F-12345启东',
          start: new Date(y, m, 5)
        },
        {
          title: '鲁F-12345日照',
          start: new Date(y, m, 10)
        },
        {
          title: '鲁F-12345烟台',
          start: new Date(y, m, d-6),
          end: new Date(y, m, d-2)
        },
        {
          id: 999,
          title: '鲁F-12345台州',
          start: new Date(y, m, d-1, 17, 0),
          allDay: false
        },
        {
          id: 999,
          title: '鲁F-12345北仑',
          start: new Date(y, m, d+1, 11, 0),
          allDay: false
        },
        {
          title: '鲁F-12345宁波-烟台往返',
          start: new Date(y, m, d, 8, 30),
          allDay: false
        },
        {
          title: '鲁F-12345浙江',
          start: new Date(y, m, d+1, 12, 0),
          end: new Date(y, m, d+1, 14, 0),
          allDay: false
        },
        {
          title: '鲁F-1234江西',
          start: new Date(y, m, d+3, 19, 0),
          end: new Date(y, m, d+3, 22, 30),
          allDay: false
        },
        {
          title: '鲁F-1234赣州',
          start: new Date(y, m, d+5, 19, 0),
          end: new Date(y, m, d+5, 22, 30),
          allDay: false
        },
        {
          title: '鲁F-12345在途',
          start: new Date(y, m, 30),
          end: new Date(y, m, 31),
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
	
	//baidu map init
// 	var map = new BMap.Map("add_input_position_map_content");
// 	var point = new BMap.Point(116.331398,39.897445);
// 	map.centerAndZoom(point,12);

	$(function(){
// 		alert(1);
// 		map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
// 		alert(2);
// 		mapAutoComplete();
		/*
		$("#modal_add_position_content").scroll(function() {
			if($('div.tangram-suggestion-main').is(":visible")){//baidu map div 滚动隐藏
				$('div.tangram-suggestion-main').css("display","none");
			}
			});
		*/
	});
	//map autocomplete
	function mapAutoComplete(){
		//autocomplete addres
		var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
				{"input" : "add_input_position_desc"
				,"location" : map
			});
		
		ac.addEventListener("onhighlight", function(e) {  //鼠标放在下拉列表上的事件
			var str = "";
				var _value = e.fromitem.value;
				var value = "";
				if (e.fromitem.index > -1) {
					value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
				}    
				str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;
				value = "";
				if (e.toitem.index > -1) {
					_value = e.toitem.value;
					value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
				}    
				str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
				$("#searchResultPanel").innerHTML = str;
			});

			var myValue;
			ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
			var _value = e.item.value;
				myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
				$("#searchResultPanel").innerHTML ="onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue;
				$("#add_input_position_address").text(myValue);
				setPlace();
			});

			function setPlace(){
				map.clearOverlays();    //清除地图上所有覆盖物
				function myFun(){
					var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
					map.centerAndZoom(pp, 15);
					map.addOverlay(new BMap.Marker(pp));    //添加标注
					//alert(point.lng+' '+point.lat);
					$("#add_input_position_coord").val(pp.lng+';'+pp.lat);
// 					$("#add_input_position_coord").val(point.lng+';'+point.lat);
				}
				var local = new BMap.LocalSearch(map, { //智能搜索
				  onSearchComplete: myFun
				});
				local.search(myValue);
			}
	}
	
	function mapMoveTo(pointNew){
		//延迟执行 map move
		setTimeout(function () {
			map.centerAndZoom(pointNew,15);
			var mk = new BMap.Marker(pointNew);
			map.addOverlay(mk);
			map.panTo(pointNew);
		},500);
	}
	
	function currentCoord(){
		var geolocation = new BMap.Geolocation();
		geolocation.getCurrentPosition(function(r){
			if(this.getStatus() == BMAP_STATUS_SUCCESS){
				var mk = new BMap.Marker(r.point);
				map.addOverlay(mk);
				map.panTo(r.point);
				point=r.point;//当前位置
// 				alert('您的位置：'+r.point.lng+','+r.point.lat);
			}
			else {
// 				alert('failed'+this.getStatus());
			}        
		},{enableHighAccuracy: true});
	}

	function detail(position_id,position_name){
		//auto top and height
// 		modal_auto_top($('div.modal-dialog'));
// 		init
		//clearMsgShow('modal_add_position');
		myModalInit('modal_add_position');
		//input readOnly
		readOnlyModalInput('modal_add_position');

		//btn hide
		$('#btn_save_position').hide();
		$('#btn_save_position').attr('data-opt-type','QUERY');
		
		$('#add_input_position_address').text('');//清空
		
		var reqUrl='<%=path%>/AjaxChannel?action=BASE_DATA_POSITION_DETAIL_ACTION';
		//ajax begin
		$.ajax({
				type : 'POST',
				url:reqUrl,
				data: {POSITION_ID:position_id},
				dataType : 'json',
				success : function(json) {
					if(json.SUCCESS=='1'){
							$('#add_input_position_code').val(json.INS_POSITION_BEAN.PositionCode);
							$('#add_input_position_name').val(json.INS_POSITION_BEAN.PositionName);
							$('#add_input_position_id').val(json.INS_POSITION_BEAN.Id);
							$('#add_input_position_address').text(json.INS_POSITION_BEAN.PositionAddress);
							//baidu map hide
							$('#add_input_position_desc').val(json.INS_POSITION_BEAN.PositionDesc);
							
							$('div.tangram-suggestion-main').css("z-index",0);//hide suggest
							
							//map move
							mapMoveTo(new BMap.Point(json.INS_POSITION_BEAN.PositionLongitude,json.INS_POSITION_BEAN.PositionLatitude));
// 							returnSuccessMsgShow('modal_add_position',json.MSG)
						}else{
// 							alert(json.MSG)	
							returnErrorMsgShow('modal_add_position',json.MSG)	
						}
					},
				error : function(e) {
					console.log(e);
					}
				});
		//ajax end
		$('#modal_add_position_title').text('检查地点明细['+position_name+']');
		$('#modal_add_position').modal({backdrop: 'static', keyboard: false});
	}
	
	//open modal
	function editPosition(position_id,position_name){
// 		init
		myModalInit('modal_add_position');
// 		actionOptionReset();
		activeModalInput('modal_add_position');
		
		//btn show
		$('#btn_save_position').show();
		$('#btn_save_position').attr('data-opt-type','EDIT');
		//position_code readyonly
		$('#add_input_position_code').attr('readonly','');
		
		$('#add_input_position_address').text('');//清空
		
		var reqUrl='<%=path%>/AjaxChannel?action=BASE_DATA_POSITION_DETAIL_ACTION';
		//ajax begin
		$.ajax({
				type : 'POST',
				url:reqUrl,
				data: {POSITION_ID:position_id},
				dataType : 'json',
				success : function(json) {
					if(json.SUCCESS=='1'){
							$('#add_input_position_code').val(json.INS_POSITION_BEAN.PositionCode);
							$('#add_input_position_name').val(json.INS_POSITION_BEAN.PositionName);
							$('#add_input_position_id').val(json.INS_POSITION_BEAN.Id);
							$('#add_input_position_address').text(json.INS_POSITION_BEAN.PositionAddress);
							$('#add_input_position_desc').val(json.INS_POSITION_BEAN.PositionDesc);
							//map move
							mapMoveTo(new BMap.Point(json.INS_POSITION_BEAN.PositionLongitude,json.INS_POSITION_BEAN.PositionLatitude));
							
							//tangram-suggestion-main
							$('div.tangram-suggestion-main').css("z-index",0);//hide suggest
							$('#add_input_position_desc').on('input propertychange',function(){
// 								alert('1');
								$('div.tangram-suggestion-main').css("z-index",9999);//hide show
								var divTop=$('div.modal-dialog').css("margin-top");
								var addressTop=$('div.tangram-suggestion-main').css("top");//parse css top
								$('div.tangram-suggestion-main').css("top",(parseInt(divTop)+parseInt(addressTop))+'px');
								console.log('div.tangram-suggestion-main top'+(parseInt(divTop)+parseInt(addressTop)));
// 								alert(divTop);
// 								alert(addressTop);
							});
// 							returnSuccessMsgShow('modal_add_position',json.MSG)
						}else{
// 							alert(json.MSG)	
							returnErrorMsgShow('modal_add_position',json.MSG)	
						}
					},
				error : function(e) {
					console.log(e);
					}
				});
		//ajax end
		$('#modal_add_position_title').text('检查地点编辑['+position_name+']');
		$('#modal_add_position').modal({backdrop: 'static', keyboard: false});
	}

	
	function addPosition(){
		//auto top and height
// 		modal_auto_top($('div.modal-dialog'));
// 		clearModalContent('modal_add_position');
//		common init
// 		clear modal content
		myModalInit('modal_add_position');
// 		actionOptionReset();
		activeModalInput('modal_add_position');
		//btn show
		$('#btn_save_position').show();
		$('#btn_save_position').attr('data-opt-type','NEW');
		
		$('#modal_add_position_title').text('新增检查地点');
		$('#modal_add_position').modal({backdrop: 'static', keyboard: false});
		
		$('#add_input_position_address').text('');//清空
		
		//baidu map autocomplete event
		currentCoord();//定位
// 		mapMoveTo(point);//移动
	}
	
	function savePositionConfirm(obj){
		
// 		$("div.opt-result-alert").hide();//clear other alert div
// 		$("div.opt-result-alert").removeClass("alert-success").removeClass("alert-danger");
//		ajax();
// 		callbak($(obj).button('reset');)
// 		setTimeout(function (){ //callback
// 			$(obj).button('reset'); 
// 			//success
// 			},1000);  
		var modalOptType=$(obj).attr('data-opt-type');//new or eidt

		var position_id=$('#add_input_position_id').val();
		var position_code=$('#add_input_position_code').val();
		var position_name=$('#add_input_position_name').val();
		var position_desc=$('#add_input_position_desc').val();
		var position_address=$('#add_input_position_address').text();
		var position_coord=$('#add_input_position_coord').val();
		
		var data={};

		data.POSITION_NAME=position_name;
		data.POSITION_DESC=position_desc;
		data.POSITION_ADDRESS=position_address;
		data.POSITION_COORD=position_coord;
		
		var reqUrl;
		if('NEW'==modalOptType){
			reqUrl='<%=path%>/AjaxChannel?action=BASE_DATA_POSITION_ADD_ACTION';
			data.POSITION_CODE=position_code;
		}
		else if('EDIT'==modalOptType){
			reqUrl='<%=path%>/AjaxChannel?action=BASE_DATA_POSITION_EDIT_ACTION';
			data.POSITION_ID=position_id;
		}
		else{
			returnErrorMsgShow('modal_add_position','未知操作类型，请稍后重试')
			return false;
		}
		
		$(obj).button('loading');
		//ajax begin
		$.ajax({
				type : 'POST',
				url:reqUrl,
				data:data,
				dataType : 'json',
				success : function(json) {
					if(json.SUCCESS=='1'){
							returnSuccessMsgShow('modal_add_position',json.MSG)
							var fvTable=$("#positionList").dataTable(); //datatable init current
							fvTable.fnDraw(false);
							$(obj).button('reset');
						}else{
// 							alert(json.MSG)	
							returnErrorMsgShow('modal_add_position',json.MSG)	
							$(obj).button('reset');
						}
					},
				error : function(e) {
					console.log(e);
					}
				});
	}
	
	/*opt confirm*/
	function optConfirm(opt_type,position_name,position_id){
		//auto top and height
// 		modal_auto_top($('div.modal-dialog'));
// 		$('#optModal').find("button.btn-primary:first").button('reset');
// 		clearMsgShow('optModal');
//		init common
		myModalInit('optModal');
		
		var title;
		var contentHtml;
		
		$("#opt_confirm_position_id").val(position_id);
		$("#opt_confirm_position_name").val(position_name);
		$("#opt_confirm_opt_type").val(opt_type);
		
		if("delete"==opt_type){
			title="删除检查地点["+position_name+"]";
			
			contentHtml=
				"<div class='alert alert-danger' role='alert'>"
					+"<span class='glyphicon glyphicon-exclamation-sign' aria-hidden='true'></span> <span class='sr-only'>注意:</span>"
					+"<strong>注意:</strong>检查地点["+position_name+"]将彻底删除，无法恢复 !"
				+"</div>";
// 			$('#optModal').find("button.btn-primary:first").click(startRole);

		}else if("stop"==opt_type)	{
			title="停用检查地点["+position_name+"]";
			
			contentHtml=
				"<div class='alert alert-warning' role='alert'>"
					+"<span class='glyphicon glyphicon-question-sign' aria-hidden='true'></span> <span class='sr-only'>注意:</span>"
					+"<strong>注意:</strong>检查地点["+position_name+"]将停用，可在检查地点管理恢复。"
				+"</div>";
// 			$('#optModal').find("button.btn-primary:first").click(stopRole);
		}else if("start"==opt_type){
			title="启用检查地点["+position_name+"]";
			
			contentHtml=
				"<div class='alert alert-info' role='alert'>"
					+"<strong>注意:</strong><span class='glyphicon glyphicon-info-sign' aria-hidden='true'></span> <span class='sr-only'>注意:</span>"
					+"检查地点["+position_name+"]将被启用。"
				+"</div>";
// 			$('#optModal').find("button.btn-primary:first").click(deleteRole);
		}
		$('#optModal').find("h4.modal-title:first").text(title);
		$('#optModal').find("div.modal-body:first").html(contentHtml);
// 		$('#deleteModal_content').html('<span>'+window.parent.getScrollTop()+'</span><br/><span>'+$(window.parent).height()+'</span><br/><span>'+$(window.parent.document.body)[0].clientHeight+'</span>');
		$('#optModal').modal({backdrop: 'static', keyboard: false});
	}
	
	function optConfirmDone(obj){
		var position_id=$("#opt_confirm_position_id").val();
		var position_name=$("#opt_confirm_position_name").val();
		var optType=$("#opt_confirm_opt_type").val();
		
		$(obj).button('loading');
		var reqUrl='<%=path%>/AjaxChannel?action=BASE_DATA_POSITION_MOD_ACTION';
		//ajax begin
		$.ajax({
				type : 'POST',
				url:reqUrl,
				data: {POSITION_ID:position_id,OPT_TYPE:optType},
				dataType : 'json',
				success : function(json) {
					if(json.SUCCESS=='1'){
							returnSuccessMsgShow('optModal',json.MSG);//alert msg
// 							$(obj).button('complete');//button 
							var fvTable=$("#positionList").dataTable(); //datatable init current
							fvTable.fnDraw(false);
							$(obj).button('reset');
						}else{
							returnErrorMsgShow('optModal',json.MSG);
							$(obj).button('reset'); 
						}
					},
				error : function(e) {
					console.log(e);
					}
				});
			//ajax end
	}
</script>

</body>
</html>