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
  <link href="../../style/gijgo.min.css" rel="stylesheet" type="text/css" />
  <link href="../../style/truck-tree-view-gijgo.css" rel="stylesheet" type="text/css" />
  <!-- Favicon -->
  <link rel="shortcut icon" href="img/favicon/favicon.png">
</head>

<body class="content-body">


      <!-- Page heading -->
      <div class="page-head">
        <h2 class="pull-left"><i class="icon-user"></i> 检查单管理</h2>

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

                <div class="widget-content query-condition double-size">
                <!-- 第一排查询条件 -->
                <div class="form-group col-sm-3">
        			<label class="col-sm-5 control-label">检查单号</label>
         			<div class="col-sm-7"><input type="text" class="form-control" placeholder="检查单号" id="qr_input_ins_check_order_no"></div>
        		</div>
       			<div class="form-group col-sm-3">
        			<label class="col-sm-5 control-label">检查对象名称</label>
         			<div class="col-sm-7"><input type="text" class="form-control" placeholder="检查对象名称" id="qr_input_check_target_name"></div>
        		</div>
       			<div class="form-group col-sm-3">
        			<label class="col-sm-5 control-label">检查人名称</label>
         			<div class="col-sm-7"><input type="text" class="form-control" placeholder="检查人名称" id="qr_input_ins_check_name"></div>
        		</div>
       			<div class="form-group col-sm-3">
       				<label class="col-sm-5 control-label">检查地点</label>
         			<div class="col-sm-7"><input type="text" class="form-control" placeholder="检查地点" id="qr_input_ins_check_address"></div>
        		</div>

        		<!-- 第二排查询条件 -->
       			<div class="form-group col-sm-3"  style="margin-top:-5px">
        			<label class="col-sm-5 control-label">检查实际开始</label>
         			<div class="col-sm-7"><input type="text" class="form-control" placeholder="检查实际开始" id="qr_input_ins_check_r_begin"></div>
        		</div>
       			<div class="form-group col-sm-3"  style="margin-top:-5px">
        			<label class="col-sm-5 control-label">检查实际结束</label>
         			<div class="col-sm-7"><input type="text" class="form-control" placeholder="检查实际结束" id="qr_input_ins_check_r_end"></div>
        		</div>
        		 <div class="form-group col-sm-3"  style="margin-top:-5px">
        			<label class="col-sm-5 control-label">检查计划开始</label>
         			<div class="col-sm-7"><input type="text" class="form-control" placeholder="检查计划开始" id="qr_input_ins_check_p_begin"></div>
        		</div>
       			<div class="form-group col-sm-3"  style="margin-top:-5px">
        			<label class="col-sm-5 control-label">检查计划结束</label>
         			<div class="col-sm-7"><input type="text" class="form-control" placeholder="检查计划结束" id="qr_input_ins_check_p_end"></div>
        		</div>
        		<!-- 第三排查询条件-->
       			<div class="form-group col-sm-3"  style="margin-top:-5px">
        			<label class="col-sm-5 control-label">检查单状态</label>
         			<div class="col-sm-7">
<!--          		<input type="text" class="form-control" placeholder="检查单状态" id="qr_input_ins_check_status"> -->
         			<yb:select dataSource="DATA_DIC.CHECK_ORDER_STATUS"  selectClass="form-control "  includeNull="true" selectId="qr_input_ins_check_status"/>
         			</div>
        		</div>
       			<div class="form-group col-sm-3"  style="margin-top:-5px">
        			<label class="col-sm-5 control-label">检查结果</label>
         			<div class="col-sm-7">
<!--          		<input type="text" class="form-control" placeholder="检查结果" id="qr_input_ins_check_result"> -->
         			<yb:select dataSource="DATA_DIC.CHECK_ORDER_RESULT"  selectClass="form-control "  includeNull="true" selectId="qr_input_ins_check_result"/>
         			</div>
        		</div>
       			<div class="form-group col-sm-2" style="margin-top:-5px">
        		</div>
				<div class="form-group col-sm-4" style="text-align: right;margin-top:-5px;">
					<label class="checkbox-inline"> <input type="checkbox" id="qr_cb_include_stop" value="1"> 包含已停用</label>
<!-- 				</div> -->
					<!-- 查询按钮 begin -->
<!--         		 <div class="form-group col-sm-3"> -->
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="btn btn-success" id="qr_bt_query_obj" onclick="dataTableInit();">查询</button>
						&nbsp;
						<button type="button" class="btn btn-danger clear" id="qr_bt_clear_obj" onclick="btnQueryReset();">清空</button>
         		 </div>
         		 <!-- 查询按钮 end-->
         		 
			</div>
		</div>
		
		</div>
		
		</div>
		
          <!-- Table -->

            <div class="row">

              <div class="col-md-12">

                <div class="widget">

                <div class="widget-head">
                  <div class="pull-left">查询结果 &nbsp;&nbsp;&nbsp;<button type="button" class="btn btn-primary btn-xs" href="javascript:void(0);" onclick="addCheckOrder();" id="bt_add_ent_mid">新增检查单</button></div>
                  <div class="widget-icons pull-right">
                    <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a> 
                    <a href="#" class="wclose"><i class="icon-remove"></i></a>
                  </div>  
                  <div class="clearfix"></div>
                </div>

                  <div class="widget-content">
                  
                    <table id="checkOrderList"  class="table table-striped table-bordered table-hover">
                      <thead>
                        <tr>
                          <th width="5%"></th>
                          <th width="10%"></th>
                          <th width="10%"></th>
                          <th width="17%"></th>
                          <th width="17%"></th>
                          <th width="10%"></th>
                          <th width="20%"></th>
                          <th width="20%"></th>
                          <th width="10%"></th>
                          <th width="15%"></th>
                        </tr>
                      </thead>
                      <tbody>
                                                                      
                      </tbody>
                    </table>

                    <div class="widget-foot">

<!--                         <ul class="pagination pull-right"> -->
<!--                           <li><a href="#">Prev</a></li> -->
<!--                           <li><a href="#">1</a></li> -->
<!--                           <li><a href="#">2</a></li> -->
<!--                           <li><a href="#">3</a></li> -->
<!--                           <li><a href="#">4</a></li> -->
<!--                           <li><a href="#">Next</a></li> -->
<!--                         </ul> -->
                      
                      <div class="clearfix"></div> 
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
		<input  type="hidden" id="opt_confirm_check_order_id" value="" >
		<input  type="hidden" id="opt_confirm_check_order_no" value="" >
		<input  type="hidden" id="opt_confirm_check_target_name" value="" >
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
<!-- 				<button type="button" class="btn btn-primary" data-loading-text="Loading" onclick="saveRole(this);">确&nbsp;&nbsp;定</button> -->
					<button type="button" class="btn btn-default" data-dismiss="modal" aria-hidden="true">关&nbsp;&nbsp;闭</button>
				</div>
			</div>
		</div>
	</div>
	<!-- delete Modal end -->
	
	<!-- Modal add ent mid -->
	<div id="modal_add_check_order" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content widget ">
				<div class="modal-header">	
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 style="float:right;margin-right:20px;cursor:pointer;"  onclick="switchOrderItemDiv();" id="modal_add_check_order_switch_title">查看：检查项目列表</h4>
					<h4 class="modal-title" id="modal_add_check_order_title"></h4>
				
				</div>
				<div class="alert opt-result-alert"></div>
				<!-- check order main begin-->
				<div class="modal-body modal-scrollable" id="modal_add_check_order_content">

				 <div class="padd">
                 	 <div class="form-horizontal">
                 	 <input  type="hidden" id="add_input_ent_mid_id" value="" >
                          					<div class="form-group">
<!--                                             <label class="control-label col-md-3" for="add_input_ent_mid_code">检查单代码</label> -->
<!--                                             <div class="col-md-3"> -->
<!--                                               <input type="text" class="form-control required-input" id="add_input_ent_mid_code" placeholder="检查单代码"> -->
<!--                                               <span class="text-danger" >*</span> -->
<!--                                             </div> -->
                                            <label class="control-label col-md-3" for="add_input_ins_check_order_no">检查单号</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_ins_check_order_no" placeholder="检查单号">
                                              <span class="text-danger">*</span>
                                            </div>
                                            <label class="control-label col-md-3" for="add_input_check_target_name">检查对象名称</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_check_target_name" placeholder="检查对象名称">
                                              <span class="text-danger">*</span>
                                            </div>
                                          </div>   
                                          <div class="form-group">
                                            <label class="control-label col-md-3" for="add_input_ins_check_p_begin">检查计划开始</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_ins_check_p_begin" placeholder="检查计划开始">
                                              <span class="text-danger">*</span>
                                            </div>
                                            <label class="control-label col-md-3" for="add_input_ins_check_p_end">检查计划结束</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_ins_check_p_end" placeholder="检查计划结束">
                                              <span class="text-danger">*</span>
                                            </div>
                                          </div>  
                                           <div class="form-group">
                                            <label class="control-label col-md-3" for="add_input_ins_check_r_begin">检查实际开始</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_ins_check_r_begin" placeholder="检查实际开始">
<!--                                               <span class="text-danger">*</span> -->
                                            </div>
                                            <label class="control-label col-md-3" for="add_input_ins_check_r_end">检查实际结束</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_ins_check_r_end" placeholder="检查实际结束">
<!--                                               <span class="text-danger">*</span> -->
                                            </div>
                                          </div>  
                                          
                                           <div class="form-group">
                                            <label class="control-label col-md-3" for="add_input_ins_ins_check_p_name">计划检查人</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_ins_ins_check_p_name" placeholder="计划检查人">
                                              <span class="text-danger">*</span>
                                            </div>
                                            <label class="control-label col-md-3" for="add_input_ins_ins_check_r_name">实际检查人</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_ins_ins_check_r_name" placeholder="实际检查人">
<!--                                               <span class="text-danger">*</span> -->
                                            </div>
                                          </div> 
                                          <!--  --> 
                                          <div class="form-group">
                                            <label class="control-label col-md-3" for="add_input_ins_check_status">检查单状态</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_ins_check_status"  placeholder="检查单状态">
                                            </div>
                                             <label class="control-label col-md-3" for="add_input_ins_check_result">检查结果</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_ins_check_result" placeholder="检查结果">
<!--                                             <span class="text-danger">*</span> -->
                                             </div>
                                          </div>
                                          <!--  -->
                                          <div class="form-group">
                                            <label class="control-label col-md-3" for="add_input_ins_check_address">检查地点</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_ins_check_address" placeholder="检查地点">
                                              <span class="text-danger">*</span>
                                            </div>
                                          </div>
                                          <div class="form-group">
                                            <label class="control-label col-md-3" for="add_input_ins_check_address_desc">检查地点说明</label>
                                            <div class="col-md-8">
                                              <input type="text" class="form-control" id="add_input_ins_check_address_desc"  placeholder="检查地点说明">
                                            </div>
                                          </div>
                                          <div class="form-group">
                                            <label class="control-label col-md-3" for="add_input_ins_check_order_desc">检查单说明</label>
                                            <div class="col-md-8">
                                              <textarea  class="form-control" id="add_input_ins_check_order_desc"  placeholder="检查单说明" rows="2"></textarea>
                                            </div>
                                          </div>

								</div>
               		 </div>
				</div>
				<!-- check order main end -->
				<!-- check obj item begin -->
			   <div class="modal-body modal-scrollable" id="modal_add_check_order_item" style="display:none;">
				 <div class="padd">
                 	 <div class="form-horizontal">
                 	 <input  type="hidden" id="add_input_ent_mid_id" value="" >
                      <div id="treeview-checkable" class=""></div>
					</div>
               		 </div>
				</div>
				<!-- check obj item end -->
				<div class="modal-footer">
<!-- 					<button type="button" class="btn btn-primary">确定</button> --><!-- data-opt-type="" new/edit -->
					<button type="button" id="btn_save_check_order" data-opt-type="" class="btn btn-success" data-loading-text="Loading" onclick="saveEntMidConfirm(this);">保&nbsp;&nbsp;存</button>
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

<script src="<%=path%>/js/gijgo.min.js?v=<%=staticVersion%>"></script> <!-- jQuery -->

<script src="<%=path%>/js/bootstrap.js?v=<%=staticVersion%>"></script> <!-- Bootstrap -->
<script src="<%=path%>/js/laydate.js?v=<%=staticVersion%>"></script> <!-- laydate.js -->
<!-- table收起 -->
<script src="<%=path%>/js/truck-inspect-common.js?v=<%=staticVersion%>"></script> <!-- Custom codes -->
<script src="<%=path%>/js/data-tables/jquery.dataTables.min.js?v=<%=staticVersion%>"	type="text/javascript"></script>
<script src="<%=path%>/js/data-tables/dataTables.bootstrap.min.js?v=<%=staticVersion%>"	type="text/javascript"></script>
<%-- <script src="<%=path%>/js/bootstrap-treeview.js?v=<%=staticVersion%>"	type="text/javascript"></script> --%>
<script src="<%=path%>/js/moment.min.js?v=<%=staticVersion%>" type="text/javascript"></script>
<script src="<%=path%>/js/busi-js/query_check_order_list.js?v=<%=staticVersion%>" type="text/javascript"></script>
<%-- <script src="<%=path%>/js/busi-js/truck_tree_view.js?v=<%=staticVersion%>" type="text/javascript"></script> --%>

<!-- Script for this page -->
<script type="text/javascript">
	//初始化预查询
	/*
	var initSupCode=getUrlParam("SUP_CODE");//接收从一级页面某个一级分类，跳转过来，直接查询其对应二级代码
	if(initSupCode!=null){
		$("#qr_input_ent_sup_code").val(initSupCode);
		//缺省一级代码，便于批量添加该一级代码对应的检查单
	}
	*/
	
	var map=parseData2Map('<yb:dataDic dataDicType="CHECK_ORDER_STATUS,CHECK_ORDER_RESULT"/>');
	function dicTranse(value){
		return map[value]==null?value:map[value];
	}
	
	/*查询条件清空重置查询*/
	function btnQueryReset(){
		queryBtnRestClear();
		dataTableInit();
	}

	$(function() {
		dataTableInit();
		initTreeView();
	}); 
	
	function dataTableInit() {
		//param
		var targetName = $("#qr_input_check_target_name").val();
		var checkName = $("#qr_input_ins_check_name").val();
		var planBegin = $("#qr_input_ins_check_p_begin").val();
		var planEnd = $("#qr_input_ins_check_p_end").val();
		var rBegin = $("#qr_input_ins_check_r_begin").val();
		var rEnd = $("#qr_input_ins_check_r_end").val();
		var checkStatus = $("#qr_input_ins_check_status").val();
		var checkResult = $("#qr_input_ins_check_result").val();
		var includeStop=(true==$("#qr_cb_include_stop").is(':checked'))?"1":"0";
		
		//格式：列映射关系
		var aoColumns = '[{"mDataProp" : "ID","sTitle" : "序号"}, '
					   + '{"mDataProp" : "CHECK_ORDER_NO","sTitle" : "检查单号"},'
					   + '{"mDataProp" : "CHECK_TARGET_NAME","sTitle" : "对象名称"},'
					   + '{"mDataProp" : "P_BEGIN_TIME","sTitle" : "预计时间/地点"},'
					   + '{"mDataProp" : "R_BEGIN_TIME","sTitle" : "实际时间"},'
					   + '{"mDataProp" : "CHECK_ORDER_STATUS","sTitle" : "检查状态"},'
					   + '{"mDataProp" : "CHECK_ORDER_RESULT","sTitle" : "检查结果"},'
					   + '{"mDataProp" : "ID","sTitle" : "创建信息"},'
					   + '{"mDataProp" : "FREEZE_TAG","sTitle" : "状态"},'
					   + '{"mDataProp" : "ID","sTitle" : "操作"}]';

		var reqData = {
			action : 'BUSI_DATA_CHECK_ORDER_QUERY_LIST_ACTION',
			params : 'params',
			TARGET_NAME:targetName,
			CHECK_OPT_NAME:checkName,
			PLAN_BEGIN_DATE:planBegin,
			PLAN_END_DATE:planEnd,
			CHECK_BEGIN_DATE:rBegin,
			CHECK_END_DATE:rEnd,
			CHECK_RESULT:checkStatus,
			CHECK_STATUS:checkResult,
			INCLUDE_STOP:includeStop
			};
		//example为table定义id ：10：需要加入操作按钮的列；[4,5]：需要转义的列（映射数据字典值）
		initCheckOrderTable('checkOrderList', aoColumns, reqData, '<%=path%>',9,[6]);
		resetIFrameLength();//need ajax async:false,
		} 
	/*title tips- common method,dynamic bind*/
	$(function () { $("[data-toggle='tooltip']").tooltip();});
	
//	查询条件 绑定时间控件
// 	检查计划开始
	laydate.render({
	  elem: '#qr_input_ins_check_p_begin', //指定元素
	  type:'datetime',
	  max:1,
	  position: 'absolute',
	  theme: '#6699CC',
	  done:checkPDate()
	});
// 	检查计划结束
	laydate.render({
	  elem: '#qr_input_ins_check_p_end', //指定元素
// 	  format:'yyyy-MM-dd HH:mm',
	  type:'datetime',
	  max:1,
	  position: 'absolute',
	  theme: '#6699CC',
// 	  theme: '#393D49',
// 	  range: true //双日期展示
	  done: function(value, date, endDate){ //example
			    console.log(value); //得到日期生成的值，如：2017-08-18
			    console.log(date); //得到日期时间对象：{year: 2017, month: 8, date: 18, hours: 0, minutes: 0, seconds: 0}
			    console.log(endDate); //得结束的日期时间对象，开启范围选择（range: true）才会返回。对象成员同上。
		}
	});
	
	function checkPDate(){
		//开始时间不能晚于结束时间
		if($('#qr_input_ins_check_p_begin').val()!=''&&$('#qr_input_ins_check_p_end').val()!=''){
// 			alert($('#qr_input_log_begin_time').val());
// 			alert($('#qr_input_log_end_time').val());
			return $('#qr_input_ins_check_p_begin').val()<$('#qr_input_ins_check_p_end').val();
		}else 
		return true;
	}
// 	检查实际开始
	laydate.render({
	  elem: '#qr_input_ins_check_r_begin', //指定元素
	  type:'datetime',
	  max:1,
	  position: 'absolute',
	  theme: '#6699CC',
	  done:checkRDate()
	});
// 	检查实际结束
	laydate.render({
	  elem: '#qr_input_ins_check_r_end', //指定元素
// 	  format:'yyyy-MM-dd HH:mm',
	  type:'datetime',
	  max:1,
	  position: 'absolute',
	  theme: '#6699CC',
// 	  theme: '#393D49',
// 	  range: true //双日期展示
	  done: function(value, date, endDate){ //example
			    console.log(value); //得到日期生成的值，如：2017-08-18
			    console.log(date); //得到日期时间对象：{year: 2017, month: 8, date: 18, hours: 0, minutes: 0, seconds: 0}
			    console.log(endDate); //得结束的日期时间对象，开启范围选择（range: true）才会返回。对象成员同上。
		}
	});
	
	function checkRDate(){
		//开始时间不能晚于结束时间
		if($('#qr_input_ins_check_r_begin').val()!=''&&$('#qr_input_ins_check_r_end').val()!=''){
// 			alert($('#qr_input_log_begin_time').val());
// 			alert($('#qr_input_log_end_time').val());
			return $('#qr_input_ins_check_r_begin').val()<$('#qr_input_ins_check_r_end').val();
		}else 
		return true;
	}
	
	//end date

	var tree ;//全局map对象
	function initTreeView(){
		var reqUrl='<%=path%>/AjaxChannel?action=BUSI_DATA_CHECK_ORDER_QUERY_ITEM_TREE_ACTION';
		$.ajax({
			type : 'POST',
			url:reqUrl,
			data: {ENT_TYPE_CODE:'CET_0010'},
			dataType : 'json',
			success : function(json) {
				if(json.SUCCESS=='1'){
					var defaultData=json.TREE_DATA;
					tree= $('#treeview-checkable').tree({
			            primaryKey: 'pk',
			            uiLibrary: 'bootstrap',
			            dataSource: eval(defaultData),
			            checkboxes: true
			        });
				}else{
//					alert(json.MSG)	
				returnErrorMsgShow('modal_add_check_order',json.MSG)	
			}
		},
		error : function(e) {
			console.log(e);
			}
		});
		
	}
	//展开新增
	function addCheckOrder(){
		myModalInit('modal_add_check_order');
		activeModalInput('modal_add_check_order');
		//btn show
		$('#btn_save_check_order').show();
		$('#btn_save_check_order').attr('data-opt-type','NEW');
		
// 		if(initSupCode!=null||initSupCode!=undefined){
// 			$('#add_input_ent_sup_code').val(initSupCode);//自动初始化
// 		}
		
		$('#modal_add_check_order_title').text('新增检查单');
		$('#modal_add_check_order').modal({backdrop: 'static', keyboard: false});
		
		displayAddOrdersMain();//默认显示检车单主表div
		//tree 初始化
		tree.uncheckAll();//清空选中
		tree.enableAll();//可用
		tree.expandAll();//全部展开
		
		var reqUrl='<%=path%>/AjaxChannel?action=BUSI_DATA_CHECK_ORDER_QUERY_ITEM_TREE_ACTION';
		//ajax begin
		$.ajax({
				type : 'POST',
				url:reqUrl,
				data: {ENT_TYPE_CODE:'CET_0010',ORDER_NO:'1'},
				dataType : 'json',
				success : function(json) {
					if(json.SUCCESS=='1'){
						var defaultData=json.TREE_DATA;
						/* 原 tree view 实现
						$('#treeview-checkable').treeview({
		                    data: eval(defaultData),
		                    levels: 5,
		                    showIcon: false,
		                    showCheckbox: true,
		                    showTags: true,
		                    onNodeChecked: function(event, node) {
// 		                      $('#checkable-output').prepend('<p>' + node.text + ' was checked</p>');
// 		                    	truck_onNodeChecked(event, node);
		                    },
		                    onNodeUnchecked: function (event, node) {
// 		                      $('#checkable-output').prepend('<p>' + node.text + ' was unchecked</p>');
// 		                    	truck_onNodeUnchecked(event, node);
		                    }
		                  });
						*/
						/*
		                var tree = $('#treeview-checkable').tree({
		                    primaryKey: 'pk',
		                    uiLibrary: 'bootstrap',
		                    dataSource: eval(defaultData),
		                    checkboxes: true
		                });
						*/
// 						 tree.on('checkboxChange', function (e, $node, record, state) {
// 					         console.log('recode text :'+record.text+' '+state);
// 					         console.log('tree.getCheckedNodes(); :'+ tree.getCheckedNodes().join());
// 					     });
						//
						/* 原 tree view 实现
						if(json.ITEM_LIST!=null){
							$.each(json.ITEM_LIST,function(index,item){
								//console.log(item);
// 								$(':checkbox[id="'+item.ObjClassSubCode+'_CHECK_BOX"]').prop("checked", "checked");
								var nodeName=item.CheckObjName;
								var nodes=$('#treeview-checkable').treeview('search', [nodeName, {
									  ignoreCase: false,     // case insensitive
									  exactMatch: false,    // like or equals
									  revealResults: true,  // reveal matching nodes
									}]);
// 								var nodes={text: nodeName}
									
									$('#treeview-checkable').treeview('checkNode', [ nodes, { silent: $('#chk-check-silent').is(':checked') }]);
									$('#treeview-checkable').treeview('clearSearch');

							})
						}
						*/ /*
							if(json.ITEM_LIST!=null){
								$.each(json.ITEM_LIST,function(index,item){
									console.log(item);
									var key=(item.CheckObjCode+'$$'+item.CheckObjName);
									var checkedNode=tree.getNodeById(key);
									console.log("key:"+key);
									console.log("checkedNode:"+checkedNode);
									if(checkedNode!=null&&checkedNode!=undefined){
										tree.check(checkedNode);//设置选中
// 										tree.expand(checkedNode,false);//展开当前层级节点
									}
								});
								tree.expandAll();
								//设置根节点只读，查看界面
								tree.disableAll();
							}*/
						
						}else{
//								alert(json.MSG)	
							returnErrorMsgShow('modal_add_check_order',json.MSG)	
						}
					},
				error : function(e) {
					console.log(e);
					}
				});
		//ajax end
	}
	
// 	查看
	function detail(order_id){
		myModalInit('modal_add_check_order');
		//input readOnly
		readOnlyModalInput('modal_add_check_order');
		
		//btn hide
		$('#btn_save_check_order').hide();
		$('#btn_save_check_order').attr('data-opt-type','QUERY');
		
		displayAddOrdersMain();//默认显示检车单主表div
		
		tree.uncheckAll();//清空选中
		tree.expandAll();//全部展开
		
		var reqUrl='<%=path%>/AjaxChannel?action=BUSI_DATA_CHECK_ORDER_QUERY_ITEM_TREE_ACTION';
		//ajax begin
		$.ajax({
				type : 'POST',
				url:reqUrl,
				data: {ORDER_NO:'1'},
				dataType : 'json',
				success : function(json) {
					if(json.SUCCESS=='1'){
// 						var defaultData=json.TREE_DATA;
							if(json.ITEM_LIST!=null){
								$.each(json.ITEM_LIST,function(index,item){
									console.log(item);
									var key=(item.CheckObjCode+'$$'+item.CheckObjName);
									var checkedNode=tree.getNodeById(key);
									console.log("key:"+key);
									console.log("checkedNode:"+checkedNode);
									if(checkedNode!=null&&checkedNode!=undefined){
										tree.check(checkedNode);//设置选中
// 										tree.expand(checkedNode,false);//展开当前层级节点
									}
								});
								tree.expandAll();
								//设置根节点只读，查看界面
								tree.disableAll();
							}
						
						}else{
//								alert(json.MSG)	
							returnErrorMsgShow('modal_add_check_order',json.MSG)	
						}
					},
				error : function(e) {
					console.log(e);
					}
				});

		//ajax end
// 		$('#modal_add_check_order_title').text('检查单明细['+$('#add_input_check_target_name').val()+']'); //EMPTY
		$('#modal_add_check_order').modal({backdrop: 'static', keyboard: false});
		
	}
	
	//open modal
	function editEntMid(order_id){
// 		init
		myModalInit('modal_add_check_order');
		activeModalInput('modal_add_check_order');
		$('#add_input_ent_mid_code').attr('readonly','');
		
		//btn show
		$('#btn_save_check_order').show();
		$('#btn_save_check_order').attr('data-opt-type','EDIT');
		
		displayAddOrdersMain();//默认显示检车单主表div
		
		tree.uncheckAll();//清空选中
		tree.enableAll();//可用
		tree.expandAll();//全部展开
		
		var reqUrl='<%=path%>/AjaxChannel?action=BUSI_DATA_CHECK_ORDER_QUERY_ITEM_TREE_ACTION';
		//ajax begin
		$.ajax({
				type : 'POST',
				url:reqUrl,
				data: {ORDER_NO:'1'},
				dataType : 'json',
				success : function(json) {
					if(json.SUCCESS=='1'){
// 						var defaultData=json.TREE_DATA;
							if(json.ITEM_LIST!=null){
								$.each(json.ITEM_LIST,function(index,item){
									console.log(item);
									var key=(item.CheckObjCode+'$$'+item.CheckObjName);
									var checkedNode=tree.getNodeById(key);
									console.log("key:"+key);
									console.log("checkedNode:"+checkedNode);
									if(checkedNode!=null&&checkedNode!=undefined){
										tree.check(checkedNode);//设置选中
// 										tree.expand(checkedNode,false);//展开当前层级节点
									}
								});
// 								tree.expandAll();
								//设置根节点只读，查看界面
								//tree.disableAll();
							}
						
						}else{
//								alert(json.MSG)	
							returnErrorMsgShow('modal_add_check_order',json.MSG)	
						}
					},
				error : function(e) {
					console.log(e);
					}
				});
		//ajax end
		$('#modal_add_check_order').modal({backdrop: 'static', keyboard: false});
	}
	
	function saveEntMidConfirm(obj){
		var modalOptType=$(obj).attr('data-opt-type');//new or eidt

		var ent_mid_name=$('#add_input_check_target_name').val();
		var ent_mid_desc=$('#add_input_ent_mid_desc').val();
		var ent_mid_code=$('#add_input_ent_mid_code').val();
		var ent_mid_id=$('#add_input_ent_mid_id').val();
		var ent_mid_sort=$('#add_input_ent_mid_sort').val();
		
		var ent_sup_code=$('#add_input_ent_sup_code').val();
		
		initSupCode=ent_sup_code;//将上次所需一级代码存为默认代码		
		var reqUrl;
		if('NEW'==modalOptType){
			reqUrl='<%=path%>/AjaxChannel?action=BUSI_DATA_ENT_MID_ADD_ACTION';
		}
		else if('EDIT'==modalOptType){
			reqUrl='<%=path%>/AjaxChannel?action=BUSI_DATA_ENT_MID_EDIT_ACTION';
		}
		else{
			returnErrorMsgShow('modal_add_check_order','未知操作类型，请稍后重试')
			return false;
		}
		
		$(obj).button('loading');
		//ajax begin
		$.ajax({
				type : 'POST',
				url:reqUrl,
				data: {ENT_MID_NAME:ent_mid_name,ENT_MID_DESC:ent_mid_desc,ENT_MID_ID:ent_mid_id,
					ENT_MID_CODE:ent_mid_code,ENT_MID_SORT:ent_mid_sort,ENT_SUP_CODE:ent_sup_code},
				dataType : 'json',
				success : function(json) {
					if(json.SUCCESS=='1'){
							returnSuccessMsgShow('modal_add_check_order',json.MSG)
							var fvTable=$("#checkOrderList").dataTable(); //datatable init current
							fvTable.fnDraw(false);
							resetIFrameLength();//need ajax async:false,
							$(obj).button('reset');
						}else{
// 							alert(json.MSG)	
							returnErrorMsgShow('modal_add_check_order',json.MSG)	
							$(obj).button('reset');
						}
					},
				error : function(e) {
					console.log(e);
					}
				});
	}

	

	
	/*opt confirm*/
	function optConfirm(opt_type,ent_mid_name,ent_mid_id){
		//auto top and height
// 		modal_auto_top($('div.modal-dialog'));

// 		$('#optModal').find("button.btn-primary:first").button('reset');
// 		clearMsgShow('optModal');
//		init common
		myModalInit('optModal');
		
		var title;
		var contentHtml;
		
		$("#opt_confirm_check_order_id").val(ent_mid_id);
		$("#opt_confirm_check_target_name").val(ent_mid_name);
		$("#opt_confirm_opt_type").val(opt_type);
		
		if("delete"==opt_type){
			title="删除检查单["+ent_mid_name+"]";
			
			contentHtml=
				"<div class='alert alert-danger' role='alert'>"
					+"<span class='glyphicon glyphicon-exclamation-sign' aria-hidden='true'></span> <span class='sr-only'>注意:</span>"
					+"<strong>注意:</strong>检查单["+ent_mid_name+"]将彻底删除，无法恢复 !"
				+"</div>";
// 			$('#optModal').find("button.btn-primary:first").click(startRole);

		}else if("stop"==opt_type)	{
			title="停用检查单["+ent_mid_name+"]";
			
			contentHtml=
				"<div class='alert alert-warning' role='alert'>"
					+"<span class='glyphicon glyphicon-question-sign' aria-hidden='true'></span> <span class='sr-only'>注意:</span>"
					+"<strong>注意:</strong>检查单["+ent_mid_name+"]将停用，可在检查单管理恢复。"
				+"</div>";
// 			$('#optModal').find("button.btn-primary:first").click(stopRole);
		}else if("start"==opt_type){
			title="启用检查单["+ent_mid_name+"]";
			
			contentHtml=
				"<div class='alert alert-info' role='alert'>"
					+"<strong>注意:</strong><span class='glyphicon glyphicon-info-sign' aria-hidden='true'></span> <span class='sr-only'>注意:</span>"
					+"检查单["+ent_mid_name+"]将被启用。"
				+"</div>";
// 			$('#optModal').find("button.btn-primary:first").click(deleteRole);
		}
		$('#optModal').find("h4.modal-title:first").text(title);
		$('#optModal').find("div.modal-body:first").html(contentHtml);
		$('#optModal').modal({backdrop: 'static', keyboard: false});
	}
	
	function optConfirmDone(obj){
		var entMidId=$("#opt_confirm_check_order_id").val();
		var entMidName=$("#opt_confirm_check_target_name").val();
		var optType=$("#opt_confirm_opt_type").val();
		
		$(obj).button('loading');
		var reqUrl='<%=path%>/AjaxChannel?action=BUSI_DATA_ENT_MID_MOD_ACTION';
		//ajax begin
		$.ajax({
				type : 'POST',
				url:reqUrl,
				data: {ENT_MID_ID:entMidId,OPT_TYPE:optType},
				dataType : 'json',
				success : function(json) {
					if(json.SUCCESS=='1'){
							returnSuccessMsgShow('optModal',json.MSG);//alert msg
// 							$(obj).button('complete');//button 
							var fvTable=$("#checkOrderList").dataTable(); //datatable init current
							fvTable.fnDraw(false);
							resetIFrameLength();//need ajax async:false,
							$(obj).button('reset');
						}else{
							returnErrorMsgShow('optModal',json.MSG);
// 							$(obj).button('complete');//button 
							//var fvTable=$("#roleList").dataTable();
							//fvTable.fnDraw(false);
							$(obj).button('reset'); 
						}
					},
				error : function(e) {
					console.log(e);
					}
				});
			//ajax end
		//
	}
	
	function optEnterSub(midCode,midId){
		  window.location.href="entity_sub_manager.jsp?MID_CODE="+midCode; 
	}
	
	function switchOrderItemDiv(){
// 		alert('in');
		var content=$('#modal_add_check_order_content');
		var item=$('#modal_add_check_order_item');
		var swithTitle=$('#modal_add_check_order_switch_title');
		if(content.is(':hidden')){
// 			alert(1);
			item.hide();
			content.fadeIn('fast');
			swithTitle.text('查看：检查项目列表');
// 			content.animate({left:'250px'}).fadeIn("fast");
		}else{
// 			alert(2);
			content.hide();
			item.fadeIn('fast');
			swithTitle.text('查看：检车单主内容');
		}
	}
	
	//新增检车单时-初始化检车单主表和tree的div
	function displayAddOrdersMain(){
		var content=$('#modal_add_check_order_content');
		var item=$('#modal_add_check_order_item');
		var swithTitle=$('#modal_add_check_order_switch_title');
			item.hide();
			content.fadeIn();
			swithTitle.text('查看：检查项目列表');
	}
	
	/*
	 var defaultData =
	 */
		 /*[
	                    {
	                      text: 'Parent 1',
	                      href: '#parent1',
	                      tags: ['4'],
	                      nodes: [
	                        {
	                          text: 'Child 1',
	                          href: '#child1',
	                          tags: ['2'],
	                          nodes: [
	                            {
	                              text: 'Grandchild 1',
	                              href: '#grandchild1',
	                              tags: ['0']
	                            },
	                            {
	                              text: 'Grandchild 2',
	                              href: '#grandchild2',
	                              tags: ['0']
	                            }
	                          ]
	                        },
	                        {
	                          text: 'Child 2',
	                          href: '#child2',
	                          tags: ['0']
	                        }
	                      ]
	                    },
	                    {
	                      text: 'Parent 2',
	                      href: '#parent2',
	                      tags: ['0']
	                    },
	                    {
	                      text: 'Parent 3',
	                      href: '#parent3',
	                       tags: ['0']
	                    },
	                    {
	                      text: 'Parent 4',
	                      href: '#parent4',
	                      tags: ['0']
	                    },
	                    {
	                      text: 'Parent 5',
	                      href: '#parent5'  ,
	                      tags: ['0']
	                    }
	                  ];
	 */
// 	 '';
//	 [{text: '车辆大类1',href: '#CHE_01',tags: ['5'],nodes:[{text: '车头',href: '#CHE_0101',tags: ['3'],nodes:[{text: '危险品车头',href: '#CHE_010101',tags: ['0']},{text: '普通车头',href: '#CHE_010102',tags: ['0']},{text: '车头（公共）',href: '#CHE_010109',tags: ['0']},]},{text: '挂车',href: '#CHE_0102',tags: ['2'],nodes:[{text: '普通挂车',href: '#CHE_010201',tags: ['0']},{text: '危险品挂车',href: '#CHE_0102_01',tags: ['0']},]},{text: '罐车',href: '#CHE_0103',tags: ['1'],nodes:[{text: '压力罐车',href: '#CHE_010301',tags: ['0']},]},{text: '车尾',href: '#CHE_0105',tags: ['2'],nodes:[{text: '车尾普通（公共）',href: '#CHE_010501',tags: ['0']},{text: '车尾2（公共）',href: '#CHE_010502',tags: ['0']},]},{text: '车轴1',href: '#CHE_0108',tags: ['1'],nodes:[{text: '车轴普通',href: '#CHE_010801',tags: ['0']},]},]},{text: '服务器',href: '#MAC_01',tags: ['2'],nodes:[{text: '普通服务器',href: '#MAC_0101',tags: ['0']},{text: 'UPS',href: '#MAC_0102',tags: ['0']},]}];
// 	 [{text: '车辆大类1',href: '#CHE_01',tags: ['5'],nodes:[{text: '车头',href: '#CHE_0101',tags: ['3'],nodes:[{text: '危险品车头',href: '#CHE_010101',tags: ['0']},{text: '普通车头',href: '#CHE_010102',tags: ['0']},{text: '车头（公共）',href: '#CHE_010109',tags: ['0']},]},{text: '挂车',href: '#CHE_0102',tags: ['2'],nodes:[{text: '普通挂车',href: '#CHE_010201',tags: ['0']},{text: '危险品挂车',href: '#CHE_0102_01',tags: ['0']},]},{text: '罐车',href: '#CHE_0103',tags: ['1'],nodes:[{text: '压力罐车',href: '#CHE_010301',tags: ['0']},]},{text: '车尾',href: '#CHE_0105',tags: ['2'],nodes:[{text: '车尾普通（公共）',href: '#CHE_010501',tags: ['0']},{text: '车尾2（公共）',href: '#CHE_010502',tags: ['0']},]},{text: '车轴1',href: '#CHE_0108',tags: ['1'],nodes:[{text: '车轴普通',href: '#CHE_010801',tags: ['0']},]},]},];
// 	 [{text: '车辆大类1',href: '#CHE_01',tags: ['5'],nodes:[{text: '车头',href: '#CHE_0101',tags: ['3'],nodes:[{text: '危险品车头',href: '#CHE_010101',tags: ['1'],nodes:[{text: '测试检查项目',href: '#CHE_010101ITEM',tags: ['0']},]},{text: '普通车头',href: '#CHE_010102',tags: ['1'],nodes:[{text: '普通车头-检查',href: '#PTCT-0010',tags: ['0']},]},{text: '车头（公共）',href: '#CHE_010109',tags: ['0']},]},{text: '挂车',href: '#CHE_0102',tags: ['2'],nodes:[{text: '普通挂车',href: '#CHE_010201',tags: ['0']},{text: '危险品挂车',href: '#CHE_0102_01',tags: ['1'],nodes:[{text: '外观检查',href: '#CHE_0102_01_ITEM_01',tags: ['0']},]},]},{text: '罐车',href: '#CHE_0103',tags: ['1'],nodes:[{text: '压力罐车',href: '#CHE_010301',tags: ['0']},]},{text: '车尾',href: '#CHE_0105',tags: ['2'],nodes:[{text: '车尾普通（公共）',href: '#CHE_010501',tags: ['0']},{text: '车尾2（公共）',href: '#CHE_010502',tags: ['0']},]},{text: '车轴1',href: '#CHE_0108',tags: ['1'],nodes:[{text: '车轴普通',href: '#CHE_010801',tags: ['2'],nodes:[{text: '车轴普通-检查',href: '#CZPT-0010',tags: ['0']},{text: '普通车轴二',href: '#CZPT-001010',tags: ['0']},]},]},]},];
// 	 [{text: '车辆大类1',href: '#CHE_01',tags: ['5'],nodes:[{text: '车头',href: '#CHE_0101',tags: ['3'],nodes:[{text: '危险品车头',href: '#CHE_010101',tags: ['1'],nodes:[{text: '测试检查项目',href: '#CHE_010101ITEM',tags: ['1'],nodes:[{text: 'CHE_010101ITEM-1-NAME',href: '#CHE_010101ITEM-1',tags: ['0']},]},]},{text: '普通车头',href: '#CHE_010102',tags: ['1'],nodes:[{text: '普通车头-检查',href: '#PTCT-0010',tags: ['0']},]},{text: '车头（公共）',href: '#CHE_010109',tags: ['0']},]},{text: '挂车',href: '#CHE_0102',tags: ['2'],nodes:[{text: '普通挂车',href: '#CHE_010201',tags: ['0']},{text: '危险品挂车',href: '#CHE_0102_01',tags: ['1'],nodes:[{text: '外观检查',href: '#CHE_0102_01_ITEM_01',tags: ['1'],nodes:[{text: '车兜平整',href: '#CHE_0102_01_01_01',tags: ['0']},]},]},]},{text: '罐车',href: '#CHE_0103',tags: ['1'],nodes:[{text: '压力罐车',href: '#CHE_010301',tags: ['0']},]},{text: '车尾',href: '#CHE_0105',tags: ['2'],nodes:[{text: '车尾普通（公共）',href: '#CHE_010501',tags: ['0']},{text: '车尾2（公共）',href: '#CHE_010502',tags: ['0']},]},{text: '车轴1',href: '#CHE_0108',tags: ['1'],nodes:[{text: '车轴普通',href: '#CHE_010801',tags: ['2'],nodes:[{text: '普通车轴二',href: '#CZPT-001010',tags: ['0']},{text: '车轴普通-检查',href: '#CZPT-0010',tags: ['0']},]},]},]},];
/*
[{text: '车辆大类1',href: '#CHE_01',tags: ['5'],backColor:'#444241' ,color:'#FFFFFF' ,nodes:[{text: '车头',href: '#CHE_0101',tags: ['3'],backColor:'#444241' ,color:'#FFFFFF' ,nodes:[{text: '危险品车头',href: '#CHE_010101',tags: ['1'],nodes:[{text: '测试检查项目',href: '#CHE_010101ITEM',tags: ['1'],nodes:[{text: 'CHE_010101ITEM-1-NAME',href: '#CHE_010101ITEM-1',tags: ['0']},]},]},{text: '普通车头',href: '#CHE_010102',tags: ['1'],nodes:[{text: '普通车头-检查',href: '#PTCT-0010',tags: ['0']},]},{text: '车头（公共）',href: '#CHE_010109',tags: ['0']},]},{text: '挂车',href: '#CHE_0102',tags: ['2'],backColor:'#444241' ,color:'#FFFFFF' ,nodes:[{text: '普通挂车',href: '#CHE_010201',tags: ['0']},{text: '危险品挂车',href: '#CHE_0102_01',tags: ['1'],nodes:[{text: '外观检查',href: '#CHE_0102_01_ITEM_01',tags: ['1'],nodes:[{text: '车兜平整',href: '#CHE_0102_01_01_01',tags: ['0']},]},]},]},{text: '罐车',href: '#CHE_0103',tags: ['1'],backColor:'#444241' ,color:'#FFFFFF' ,nodes:[{text: '压力罐车',href: '#CHE_010301',tags: ['0']},]},{text: '车尾',href: '#CHE_0105',tags: ['2'],backColor:'#444241' ,color:'#FFFFFF' ,nodes:[{text: '车尾普通（公共）',href: '#CHE_010501',tags: ['0']},{text: '车尾2（公共）',href: '#CHE_010502',tags: ['0']},]},{text: '车轴1',href: '#CHE_0108',tags: ['1'],backColor:'#444241' ,color:'#FFFFFF' ,nodes:[{text: '车轴普通',href: '#CHE_010801',tags: ['2'],nodes:[{text: '普通车轴二',href: '#CZPT-001010',tags: ['0']},{text: '车轴普通-检查',href: '#CZPT-0010',tags: ['0']},]},]},]},];
[{text: '车辆大类1',href: '#CHE_01',tags: ['5'],backColor:'#444241' ,color:'#FFFFFF' ,nodes:[{text: '车头',href: '#CHE_0101',tags: ['3'],backColor:'#444241' ,color:'#FFFFFF' ,nodes:[{text: '危险品车头',href: '#CHE_010101',tags: ['1'],nodes:[{text: '测试检查项目',href: '#CHE_010101ITEM',tags: ['1'],nodes:[{text: 'CHE_010101ITEM-1-NAME',href: '#CHE_010101ITEM-1',tags: ['0']},]},]},{text: '普通车头',href: '#CHE_010102',tags: ['1'],nodes:[{text: '普通车头-检查',href: '#PTCT-0010',tags: ['0']},]},{text: '车头（公共）',href: '#CHE_010109',tags: ['0']},]},{text: '挂车',href: '#CHE_0102',tags: ['2'],backColor:'#444241' ,color:'#FFFFFF' ,nodes:[{text: '普通挂车',href: '#CHE_010201',tags: ['0']},{text: '危险品挂车',href: '#CHE_0102_01',tags: ['1'],nodes:[{text: '外观检查',href: '#CHE_0102_01_ITEM_01',tags: ['1'],nodes:[{text: '车兜平整',href: '#CHE_0102_01_01_01',tags: ['0']},]},]},]},{text: '罐车',href: '#CHE_0103',tags: ['1'],backColor:'#444241' ,color:'#FFFFFF' ,nodes:[{text: '压力罐车',href: '#CHE_010301',tags: ['0']},]},{text: '车尾',href: '#CHE_0105',tags: ['2'],backColor:'#444241' ,color:'#FFFFFF' ,nodes:[{text: '车尾普通（公共）',href: '#CHE_010501',tags: ['0']},{text: '车尾2（公共）',href: '#CHE_010502',tags: ['0']},]},{text: '车轴1',href: '#CHE_0108',tags: ['1'],backColor:'#444241' ,color:'#FFFFFF' ,nodes:[{text: '车轴普通',href: '#CHE_010801',tags: ['2'],nodes:[{text: '普通车轴二',href: '#CZPT-001010',tags: ['0']},{text: '车轴普通-检查',href: '#CZPT-0010',tags: ['0']},]},]},]},]"
var $checkableTree = $('#treeview-checkable').treeview({
	                    data: defaultData,
	                    levels: 5,
	                    showIcon: false,
	                    showCheckbox: true,
	                    showTags: true,
	                    onNodeChecked: function(event, node) {
	                      $('#checkable-output').prepend('<p>' + node.text + ' was checked</p>');
	                    },
	                    onNodeUnchecked: function (event, node) {
	                      $('#checkable-output').prepend('<p>' + node.text + ' was unchecked</p>');
	                    }
	                  });
*/	                  
</script>

</body>
</html>