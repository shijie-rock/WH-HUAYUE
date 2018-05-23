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
                 	 <input  type="hidden" id="add_input_ins_check_order_id" value="" >
                 	 <!-- 用于比较，是否是从下拉列表中选取 -->
                 	 <input  type="hidden" id="add_input_check_target_name_check" value="" data-ins-init-need-clear="true"><!-- modal 初始化时置空， 从下拉列表选中车辆时填入 -->
                 	 <input  type="hidden" id="add_input_check_target_id_check" value=""  data-ins-init-need-clear="true"><!-- modal 初始化时置空， 从下拉列表选中车辆时填入 -->
                 	 <input  type="hidden" id="add_input_ins_check_p_name_check" value=""  data-ins-init-need-clear="true"><!-- modal 初始化时置空， 从下拉列表选中计划检查人时填入 -->
                 	 <input  type="hidden" id="add_input_ins_check_p_id_check" value=""  data-ins-init-need-clear="true"><!-- modal 初始化时置空， 从下拉列表选中计划检查人时填入 -->
                 	 
                 	 <input  type="hidden" id="add_input_ins_check_r_name_check" value=""  data-ins-init-need-clear="true"><!-- modal 初始化时置空， 从下拉列表选中计划检查人时填入 -->
                 	 <input  type="hidden" id="add_input_ins_check_r_id_check" value=""  data-ins-init-need-clear="true"><!-- modal 初始化时置空， 从下拉列表选中计划检查人时填入 -->
                 	
                 	 <input  type="hidden" id="add_input_ins_check_address_check" value=""  data-ins-init-need-clear="true"><!-- modal 初始化时置空， 从下拉列表选中计划检查人时填入 -->
                 	 <input  type="hidden" id="add_input_ins_check_address_code_check" value=""  data-ins-init-need-clear="true"><!-- modal 初始化时置空， 从下拉列表选中计划检查人时填入 -->
                 	
                 	
                 	 
                          					<div class="form-group">

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
                                            <label class="control-label col-md-3" for="add_input_ins_check_p_name">计划检查人</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_ins_check_p_name" placeholder="计划检查人">
                                              <span class="text-danger">*</span>
                                            </div>
                                            <label class="control-label col-md-3" for="add_input_ins_check_r_name">实际检查人</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_ins_check_r_name" placeholder="实际检查人">
<!--                                               <span class="text-danger">*</span> -->
                                            </div>
                                          </div> 
                                           <div class="form-group">
                                            <label class="control-label col-md-3" for="add_input_ins_check_item_count">总检查项数</label>
                                            <div class="col-md-4">
                                              <input  type="number" min="0" value="0"  class="form-control required-input" id="add_input_ins_check_item_count" placeholder="总检查项数">
                                              <span class="text-danger">*</span>
                                            </div>
                                            <label class="control-label col-md-3" for="add_input_ins_check_item_pass_count">已通过项数</label>
                                            <div class="col-md-4">
                                              <input  type="number" min="0" value="0"  class="form-control required-input" id="add_input_ins_check_item_pass_count" placeholder="已通过项数">
<!--                                               <span class="text-danger">*</span> -->
                                            </div>
                                          </div> 
                                          <!--  --> 
                                          <div class="form-group">
                                            <label class="control-label col-md-3" for="add_input_ins_check_status">检查单状态</label>
                                            <div class="col-md-4">
<!--                                               <input type="text" class="form-control required-input" id="add_input_ins_check_status"  placeholder="检查单状态"> -->
                                           <yb:select dataSource="DATA_DIC.CHECK_ORDER_STATUS"  selectClass="form-control subWidth "  includeNull="true" selectId="add_input_ins_check_status"/>
                                            </div>
                                             <label class="control-label col-md-3" for="add_input_ins_check_result">检查结果</label>
                                            <div class="col-md-4">
<!--                                               <input type="text" class="form-control required-input" id="add_input_ins_check_result" placeholder="检查结果"> -->
											  <yb:select dataSource="DATA_DIC.CHECK_ORDER_RESULT"  selectClass="form-control subWidth "  includeNull="true" selectId="add_input_ins_check_result"/>
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
<!--                  	 <input  type="hidden" id="add_input_ins_check_order_id" value="" > -->
                      <div id="treeview-checkable" class=""></div>
					</div>
               		 </div>
				</div>
				<!-- check obj item end -->
				<div class="modal-footer">
<!-- 					<button type="button" class="btn btn-primary">确定</button> --><!-- data-opt-type="" new/edit -->
					<button type="button" id="btn_save_check_order" data-opt-type="" class="btn btn-success" data-loading-text="Loading" onclick="saveCheckOrderConfirm(this);">保&nbsp;&nbsp;存</button>
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
<script src="<%=path%>/js/jquery-ui.min-1.12.1.js?v=<%=staticVersion%>"></script><!-- 注意与bootstrap的先后顺序，jquery ui 与 bootstrap tips冲突 -->
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
	var tree ;//全局map对象
	$(function() {
		dataTableInit();
		initTreeView();

	}); 
	

	function initTreeView(){
		var reqUrl='<%=path%>/AjaxChannel?action=BUSI_DATA_CHECK_ORDER_QUERY_ITEM_TREE_ACTION';
		$.ajax({
			type : 'POST',
			url:reqUrl,
			data: {ENT_TYPE_CODE:'CET_0010'},
			dataType : 'json',
			async : false,
			success : function(json) {
				if(json.SUCCESS=='1'){
					var defaultData=json.TREE_DATA;
					tree = $('#treeview-checkable').tree({
			            primaryKey: 'pk',
			            uiLibrary: 'bootstrap',
			            dataSource: eval(defaultData),
			            checkboxes: true
			        });
					//tree绑定点击事件
					tree.on('checkboxChange',function(e, $node, record, state){
// 						alert('1');
						var allCheckedCount=0;
// 			        	console.log('The new state of record ' + record.text + ' is ' + state);
			        	var result = tree.getCheckedNodes();
// 			        	console.log(result.join());
			        	$.each(result,function(index,item){
			        		var data = tree.getDataById(item);	
			        	  	if(true==data.IS_ITEM_TAG){
			        	  		allCheckedCount++;
// 			        	  		console.log(data.text+":"+data.IS_ITEM_TAG);
			        	  	}
			        	});
			        	console.log("allCheckedCount:="+allCheckedCount);
			        	$('#add_input_ins_check_item_count').val(allCheckedCount);
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
	
	function dataTableInit() {
		//param
		var checkOrderNo = $("#qr_input_ins_check_order_no").val();
		var address = $("#qr_input_ins_check_address").val();
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
			CHECK_ORDER_NO:checkOrderNo,
			ADDRESS:address,
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
	  max:100,
	  position: 'absolute',
	  theme: '#6699CC',
	  done:checkPDate()
	});
// 	检查计划结束
	laydate.render({
	  elem: '#qr_input_ins_check_p_end', //指定元素
// 	  format:'yyyy-MM-dd HH:mm',
	  type:'datetime',
	  max:100,
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
	  max:100,
	  position: 'absolute',
	  theme: '#6699CC',
	  done:checkRDate()
	});
// 	检查实际结束
	laydate.render({
	  elem: '#qr_input_ins_check_r_end', //指定元素
// 	  format:'yyyy-MM-dd HH:mm',
	  type:'datetime',
	  max:100,
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
	
//	modal 绑定时间控件
// 	检查计划开始
	laydate.render({
	  elem: '#add_input_ins_check_p_begin', //指定元素
	  type:'datetime',
	  max:100,
	  position: 'absolute',
	  theme: '#6699CC',
	  done:checkPDateadd()
	});
// 	检查计划结束
	laydate.render({
	  elem: '#add_input_ins_check_p_end', //指定元素
// 	  format:'yyyy-MM-dd HH:mm',
	  type:'datetime',
	  max:100,
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
	
	function checkPDateadd(){
		//开始时间不能晚于结束时间
		if($('#add_input_ins_check_p_begin').val()!=''&&$('#add_input_ins_check_p_end').val()!=''){
// 			alert($('#qr_input_log_begin_time').val());
// 			alert($('#qr_input_log_end_time').val());
			return $('#add_input_ins_check_p_begin').val()<$('#add_input_ins_check_p_end').val();
		}else 
		return true;
	}
// 	检查实际开始
	laydate.render({
	  elem: '#add_input_ins_check_r_begin', //指定元素
	  type:'datetime',
	  max:100,
	  position: 'absolute',
	  theme: '#6699CC',
	  done:checkRDateadd()
	});
// 	检查实际结束
	laydate.render({
	  elem: '#add_input_ins_check_r_end', //指定元素
// 	  format:'yyyy-MM-dd HH:mm',
	  type:'datetime',
	  max:100,
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
	
	function checkRDateadd(){
		//开始时间不能晚于结束时间
		if($('#add_input_ins_check_r_begin').val()!=''&&$('#add_input_ins_check_r_end').val()!=''){
// 			alert($('#qr_input_log_begin_time').val());
// 			alert($('#qr_input_log_end_time').val());
			return $('#add_input_ins_check_r_begin').val()<$('#add_input_ins_check_r_end').val();
		}else 
		return true;
	}
	//end date
	
	//自动填充
	$(function() {//自动填充开始
		 /*查询条件-检查对象名称绑定联想输入 begin*/
// 		 $("#qr_input_obj_sup_name,#qr_input_obj_sup_code").autocomplete({
		 $("#qr_input_check_target_name").autocomplete({
				source: function( request, response ) {
//			 	var userId = $("#birds").val();
					$.ajax({
						type: "POST",
						url: "<%=path%>/AjaxChannel?action=BUSI_DATA_QUERY_BASEDATA_AUTO_COMPLETE_ACTION",
						dataType: "json",
						data: {
							QUERY_NAME:$("#qr_input_check_target_name").val(),
							QUERY_TYPE:"TARGET",
							maxRows: 10
						},
						success: function( data ) {
// 							alert(data.RESULT_LIST);
							if(data.RESULT_LIST!=undefined){
								response( $.map(data.RESULT_LIST, function(bean) {
									return {
										id:bean.ID,
										value: bean.TRUCK_LICENSE //显示的列
									}
								}));
							}else{
								$('#ui-id-1').hide();
// 								alert(1);
							}
						}
					});
				},
				minLength: 0,
				max: 10,
				select: function( event, ui ) {
					$("#qr_input_check_target_name").val(ui.item.value);
// 					var oldSupName=$("#qr_input_obj_sup_name");
// 					var oldSupCode=$("#qr_input_obj_sup_code");
// 					var newSupName=ui.item.value;
// 					var newSupCode=ui.item.supCode;
// 					if(oldSupCode!=newSupCode||oldSupName!=newSupName){
						
// 						$("#qr_input_obj_sup_code").val(ui.item.supCode);

						//clear children
// 						$("#qr_input_obj_mid_name").val('');
// 						$("#qr_input_obj_mid_code").val('');
// 						$("#qr_input_obj_sub_name").val('');
// 						$("#qr_input_obj_sub_code").val('');
// 						$("#qr_input_obj_item_f_name").val('');
// 						$("#qr_input_obj_item_f_code").val('');
// 					}
					
// 					$("#qr_input_obj_sup_id").val(ui.item.supId);
					//setParentBusInfo(ui.item.Id, ui.item.CustName);
				},
				open: function() {},//结果列表弹出时
				close: function() {},//结果列表关闭时
				change: function( event, ui ) {}//当值改变时，ui.item为选中的项
			});
		 /*检查对象名称绑定联想输入 end*/
	
		 /*查询条件-检查人姓名绑定联想输入 begin*/
		 $("#qr_input_ins_check_name").autocomplete({
				source: function( request, response ) {
//			 	var userId = $("#birds").val();
					$.ajax({
						type: "POST",
						url: "<%=path%>/AjaxChannel?action=BUSI_DATA_QUERY_BASEDATA_AUTO_COMPLETE_ACTION",
						dataType: "json",
						data: {
							QUERY_NAME:$("#qr_input_ins_check_name").val(),
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
// 								alert(2);
							}
						}
					});
				},
				minLength: 0,
				max: 10,
				select: function( event, ui ) {
					$("#qr_input_ins_check_name").val(ui.item.value);
				},
				open: function() {},//结果列表弹出时
				close: function() {},//结果列表关闭时
				change: function( event, ui ) {}//当值改变时，ui.item为选中的项
			});
		 /*检查-检查人姓名绑定联想输入 end*/
		 /*查询条件-检查地点绑定联想输入 begin*/
		 $("#qr_input_ins_check_address").autocomplete({
				source: function( request, response ) {
//			 	var userId = $("#birds").val();
					$.ajax({
						type: "POST",
						url: "<%=path%>/AjaxChannel?action=BUSI_DATA_QUERY_BASEDATA_AUTO_COMPLETE_ACTION",
						dataType: "json",
						data: {
							QUERY_NAME:$("#qr_input_ins_check_address").val(),
							QUERY_TYPE:"POSITION",
							maxRows: 10
						},
						success: function( data ) {
							if(data.RESULT_LIST!=undefined){
								response( $.map(data.RESULT_LIST, function(bean) {
									return {
										id:bean.ID,
										value: bean.POSITION_NAME //显示的列
									}
								}));
							}else{
								$('#ui-id-3').hide();
// 								alert(3);
							}
						}
					});
				},
				minLength: 0,
				max: 10,
				select: function( event, ui ) {
					$("#qr_input_ins_check_address").val(ui.item.value);
				},
				open: function() {},//结果列表弹出时
				close: function() {},//结果列表关闭时
				change: function( event, ui ) {}//当值改变时，ui.item为选中的项
			});
		 /*检查-检查地点绑定联想输入 end*/
		 
		 /*新增-检查对象绑定联想输入 begin*/
		 $("#add_input_check_target_name").autocomplete({
				source: function( request, response ) {
//			 	var userId = $("#birds").val();
					$.ajax({
						type: "POST",
						url: "<%=path%>/AjaxChannel?action=BUSI_DATA_QUERY_BASEDATA_AUTO_COMPLETE_ACTION",
						dataType: "json",
						data: {
							QUERY_NAME:$("#add_input_check_target_name").val(),
							QUERY_TYPE:"TARGET",
							maxRows: 10
						},
						success: function( data ) {
							if(data.RESULT_LIST!=undefined){
								response( $.map(data.RESULT_LIST, function(bean) {
									return {
										id:bean.ID,
										value: bean.TRUCK_LICENSE //显示的列
									}
								}));
							}else{
								$('#ui-id-4').hide();
// 								alert(3);
							}
						}
					});
				},
				minLength: 0,
				max: 10,
				select: function( event, ui ) {
					$("#add_input_check_target_name").val(ui.item.value);
					$("#add_input_check_target_name_check").val(ui.item.value);
					$("#add_input_check_target_id_check").val(ui.item.id);
					//取消所有选择
					//在新增的时候，自动刷车辆对应的检查项目，编辑检查单时，不需要
					if($('#add_input_ins_check_order_no').val()==null||$('#add_input_ins_check_order_no').val()==undefined||$('#add_input_ins_check_order_no').val()==''){
						tree.uncheckAll();
						tree.expandAll();
						console.log("truck id:="+ui.item.id);
						queryTruckCheckItemParseTreeData(ui.item.id);
					}
				},
				open: function() {},//结果列表弹出时
				close: function() {},//结果列表关闭时
				change: function( event, ui ) {}//当值改变时，ui.item为选中的项
			});
		 /*新增-检查对象绑定联想输入 end*/
		  
		 /*新增-计划检查人联想输入 begin*/
		 $("#add_input_ins_check_p_name").autocomplete({
				source: function( request, response ) {
//			 	var userId = $("#birds").val();
					$.ajax({
						type: "POST",
						url: "<%=path%>/AjaxChannel?action=BUSI_DATA_QUERY_BASEDATA_AUTO_COMPLETE_ACTION",
						dataType: "json",
						data: {
							QUERY_NAME:$("#add_input_ins_check_p_name").val(),
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
								$('#ui-id-5').hide();
// 								alert(3);
							}
						}
					});
				},
				minLength: 0,
				max: 10,
				select: function( event, ui ) {
					$("#add_input_ins_check_p_name").val(ui.item.value);
					$("#add_input_ins_check_p_name_check").val(ui.item.value);
					$("#add_input_ins_check_p_id_check").val(ui.item.id);
				},
				open: function() {},//结果列表弹出时
				close: function() {},//结果列表关闭时
				change: function( event, ui ) {}//当值改变时，ui.item为选中的项
			});
		 /*新增-计划检查人绑定联想输入 end*/
		 
		 /*新增-实际检查人绑定联想输入 begin*/
		 $("#add_input_ins_check_r_name").autocomplete({
				source: function( request, response ) {
//			 	var userId = $("#birds").val();
					$.ajax({
						type: "POST",
						url: "<%=path%>/AjaxChannel?action=BUSI_DATA_QUERY_BASEDATA_AUTO_COMPLETE_ACTION",
						dataType: "json",
						data: {
							QUERY_NAME:$("#add_input_ins_check_r_name").val(),
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
								$('#ui-id-6').hide();
// 								alert(3);
							}
						}
					});
				},
				minLength: 0,
				max: 10,
				select: function( event, ui ) {
					$("#add_input_ins_check_r_name").val(ui.item.value);
					$("#add_input_ins_check_r_name_check").val(ui.item.value);
					$("#add_input_ins_check_r_id_check").val(ui.item.id);
				},
				open: function() {},//结果列表弹出时
				close: function() {},//结果列表关闭时
				change: function( event, ui ) {}//当值改变时，ui.item为选中的项
			});
		 /*新增-实际检查人绑定联想输入 end*/
		 
		 /*新增-实际检查人绑定联想输入 begin*/
		 $("#add_input_ins_check_address").autocomplete({
				source: function( request, response ) {
//			 	var userId = $("#birds").val();
					$.ajax({
						type: "POST",
						url: "<%=path%>/AjaxChannel?action=BUSI_DATA_QUERY_BASEDATA_AUTO_COMPLETE_ACTION",
						dataType: "json",
						data: {
							QUERY_NAME:$("#add_input_ins_check_address").val(),
							QUERY_TYPE:"POSITION",
							maxRows: 10
						},
						success: function( data ) {
							if(data.RESULT_LIST!=undefined){
								response( $.map(data.RESULT_LIST, function(bean) {
									return {
										id:bean.ID,
										desc:bean.POSITION_DESC,
										code:bean.POSITION_CODE,
										value: bean.POSITION_NAME //显示的列
									}
								}));
							}else{
								$('#ui-id-7').hide();
// 								alert(3);
							}
						}
					});
				},
				minLength: 0,
				max: 10,
				select: function( event, ui ) {
					$("#add_input_ins_check_address").val(ui.item.value);
					$("#add_input_ins_check_address_desc").val(ui.item.desc);
					
					$("#add_input_ins_check_address_check").val(ui.item.value);
					$("#add_input_ins_check_address_code_check").val(ui.item.code);
				},
				open: function() {},//结果列表弹出时
				close: function() {},//结果列表关闭时
				change: function( event, ui ) {}//当值改变时，ui.item为选中的项
			});
		 /*新增-实际检查人绑定联想输入 end*/
});
	//自动填充初始化结束
	
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
		
		$('#add_input_ins_check_item_pass_count').val('');
		$('#add_input_ins_check_item_count').val('0');
		
		$('#add_input_ins_check_order_no').attr('readonly','');//单号只读
		$('#add_input_ins_check_item_count').attr('readonly','');//总检查项数只读
		$('#add_input_ins_check_item_pass_count').attr('readonly','');//已通过项数
		
		displayAddOrdersMain();//默认显示检车单主表div
		//tree 初始化
		tree.destroy();//重新生成树
		initTreeView();
		tree.expandAll();//全部展开
	}	
// 	查看
	function detail(order_no){
		myModalInit('modal_add_check_order');
		//input readOnly
		readOnlyModalInput('modal_add_check_order');
		
		//btn hide
		$('#btn_save_check_order').hide();
		$('#btn_save_check_order').attr('data-opt-type','QUERY');
		
		displayAddOrdersMain();//默认显示检车单主表div

		tree.destroy();//重新生成树
		initTreeView();
// 		tree.uncheckAll();//清空选中
// 		tree.expandAll();//全部展开
		
// 		var ORDER_NO=$("#add_input_check_order_no").val();
		var reqUrl='<%=path%>/AjaxChannel?action=BUSI_DATA_CHECK_ORDER_DETAIL_ACTION';
		//ajax begin
		$.ajax({
				type : 'POST',
				url:reqUrl,
				data: {ORDER_NO:order_no},
				dataType : 'json',
				async : false,
				success : function(json) {
					if(json.SUCCESS=='1'){
// 						var defaultData=json.TREE_DATA;
							//main bean
							if(json.CHECK_ORDER_BEAN!=null){
								var bean=json.CHECK_ORDER_BEAN;
								$('#add_input_ins_check_order_id').val(bean.ID);
								$('#add_input_ins_check_order_no').val(bean.CHECK_ORDER_NO);
								$('#add_input_check_target_name').val(bean.CHECK_TARGET_NAME);
								$('#add_input_ins_check_p_begin').val(bean.P_BEGIN_TIME);
								$('#add_input_ins_check_p_end').val(bean.P_END_TIME);
								$('#add_input_ins_check_r_begin').val(bean.R_BEGIN_TIME);
								$('#add_input_ins_check_r_end').val(bean.R_END_TIME);
								$('#add_input_ins_check_p_name').val(bean.CHECK_PL_MEMBER_NAME);
								$('#add_input_ins_check_r_name').val(bean.CHECK_R_MEMBER_NAME);
								$('#add_input_ins_check_item_count').val(bean.ITEM_COUNT);
								$('#add_input_ins_check_item_pass_count').val(bean.PASS_COUNT);
								$('#add_input_ins_check_status').val(bean.CHECK_ORDER_STATUS);
								$('#add_input_ins_check_result').val(bean.CHECK_ORDER_RESULT);
								$('#add_input_ins_check_address').val(bean.POSITION_NAME);
								$('#add_input_ins_check_address_desc').val(bean.POSITION_ADDRESS);
								$('#add_input_ins_check_order_desc').val(bean.CHECK_ORDER_DESC);
							}
							if(json.ITEM_LIST!=null){
								$.each(json.ITEM_LIST,function(index,item){
									console.log(item);
									var key=(item.CheckObjCode+'$$'+item.CheckObjName);
									var checkedNode=tree.getNodeById(key);
									var checkResult=item.CheckOrderResult;//检查结果
									var faultDesc=item.TroubleDesc;//问题描述
									console.log("key:"+key);
									console.log("checkResult:"+checkResult);
									console.log("checkedNode:"+checkedNode);
									if(checkedNode!=null&&checkedNode!=undefined){
										tree.check(checkedNode);//设置选中
// 										tree.expand(checkedNode,false);//展开当前层级节点
										if(checkResult=='CIR_0020'){
											markCheckFaultItem(key,faultDesc);//标示检查失败项目
										}else if(checkResult=='CIR_0010'){
											markCheckPassItem(key);//标示检查通过项目
										}
									}
								});
								
								//设置根节点只读，查看界面
// 								tree.disableAll();
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
		tree.expandAll();
		tree.disableAll();//设置根节点只读，查看界面

		//ajax end
		$('#modal_add_check_order_title').text('查看检查单明细['+$('#add_input_ins_check_order_no').val()+']'); //EMPTY
// 		$('#modal_add_check_order_title').text('检查单明细['+$('#add_input_check_target_name').val()+']'); //EMPTY
		$('#modal_add_check_order').modal({backdrop: 'static', keyboard: false});
		
	}
	
	//在树状图中标示检查不通过项目
	function markCheckFaultItem(key,faultDesc){
		var div=$('div #treeview-checkable').find('li[data-id="'+key+'"]:first').children('div[data-role="wrapper"]:first');
		div.addClass('alert-danger');
// 		div.addClass('bg-danger');
// 		div.addClass('check-item-fault');
		if(faultDesc!=null&&faultDesc!=''&&faultDesc!=undefined)
		var span=div.children('span[data-role="display"]:first');
		var spanText=span.text();
		span.text(spanText+'   ['+faultDesc.substring(0, 10)+']');
	}
	//在树状图中标示检查通过项目
	function markCheckPassItem(key){
		var div=$('div #treeview-checkable').find('li[data-id="'+key+'"]:first').children('div[data-role="wrapper"]:first');
		div.addClass('alert-success');
// 		div.addClass('bg-success');
// 		div.addClass('check-item-success');
	}
	
	//open modal
	function editCheckOrder(order_no){
// 		init
		myModalInit('modal_add_check_order');
		activeModalInput('modal_add_check_order');
		
		//btn show
		$('#btn_save_check_order').show();
		$('#btn_save_check_order').attr('data-opt-type','EDIT');
		
		$('#add_input_ins_check_order_no').attr('readonly','');
		
		displayAddOrdersMain();//默认显示检车单主表div
		
		tree.destroy();//重新生成树
		initTreeView();
		
		var reqUrl='<%=path%>/AjaxChannel?action=BUSI_DATA_CHECK_ORDER_DETAIL_ACTION';
		//ajax begin
		$.ajax({
				type : 'POST',
				url:reqUrl,
				data: {ORDER_NO:order_no},
				dataType : 'json',
				async : false,
				success : function(json) {
					if(json.SUCCESS=='1'){
// 						var defaultData=json.TREE_DATA;
							//main bean
							if(json.CHECK_ORDER_BEAN!=null){
								var bean=json.CHECK_ORDER_BEAN;
								$('#add_input_ins_check_order_id').val(bean.ID);
								$('#add_input_ins_check_order_no').val(bean.CHECK_ORDER_NO);
								$('#add_input_check_target_name').val(bean.CHECK_TARGET_NAME);
								$('#add_input_ins_check_p_begin').val(bean.P_BEGIN_TIME);
								$('#add_input_ins_check_p_end').val(bean.P_END_TIME);
								$('#add_input_ins_check_r_begin').val(bean.R_BEGIN_TIME);
								$('#add_input_ins_check_r_end').val(bean.R_END_TIME);
								$('#add_input_ins_check_p_name').val(bean.CHECK_PL_MEMBER_NAME);
								$('#add_input_ins_check_r_name').val(bean.CHECK_R_MEMBER_NAME);
								$('#add_input_ins_check_item_count').val(bean.ITEM_COUNT);
								$('#add_input_ins_check_item_pass_count').val(bean.PASS_COUNT);
								$('#add_input_ins_check_status').val(bean.CHECK_ORDER_STATUS);
								$('#add_input_ins_check_result').val(bean.CHECK_ORDER_RESULT);
								$('#add_input_ins_check_address').val(bean.POSITION_NAME);
								$('#add_input_ins_check_address_desc').val(bean.POSITION_ADDRESS);
								$('#add_input_ins_check_order_desc').val(bean.CHECK_ORDER_DESC);
								
								//hide input 
								$('#add_input_check_target_name_check').val(bean.CHECK_TARGET_NAME);
								$('#add_input_check_target_id_check').val(bean.CHECK_TARGET_ID);
								
								$('#add_input_ins_check_p_id_check').val(bean.CHECK_PL_MEMBER_ID);
								$('#add_input_ins_check_p_name_check').val(bean.CHECK_PL_MEMBER_NAME);
								
								$('#add_input_ins_check_r_id_check').val(bean.CHECK_R_MEMBER_ID);
								$('#add_input_ins_check_r_name_check').val(bean.CHECK_R_MEMBER_NAME);
								
								$('#add_input_ins_check_address_check').val(bean.POSITION_NAME);
								$('#add_input_ins_check_address_code_check').val(bean.POSITION_CODE);
								
							}
							if(json.ITEM_LIST!=null){
								$.each(json.ITEM_LIST,function(index,item){
									console.log(item);
									var key=(item.CheckObjCode+'$$'+item.CheckObjName);
									var checkedNode=tree.getNodeById(key);
									var checkResult=item.CheckOrderResult;//检查结果
									var faultDesc=item.TroubleDesc;//问题描述
									console.log("key:"+key);
									console.log("checkResult:"+checkResult);
									console.log("checkedNode:"+checkedNode);
									if(checkedNode!=null&&checkedNode!=undefined){
										tree.check(checkedNode);//设置选中
// 										checkedNode.ORDER_ITEM_ID=item.Id;//check order item id set
// 										tree.updateNode(key, checkedNode);
// 										tree.expand(checkedNode,false);//展开当前层级节点
										if(checkResult=='CIR_0020'){
											markCheckFaultItem(key,faultDesc);//标示检查失败项目
										}else if(checkResult=='CIR_0010'){
											markCheckPassItem(key);//标示检查通过项目
										}
									}
								});
								//设置根节点只读，查看界面
// 								tree.disableAll();
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
		tree.expandAll();
// 		tree.disableAll();//设置根节点只读，查看界面

		//ajax end
		$('#modal_add_check_order_title').text('编辑检查单明细['+$('#add_input_ins_check_order_no').val()+']'); //EMPTY
		$('#modal_add_check_order').modal({backdrop: 'static', keyboard: false});
	}
	
	function saveCheckOrderConfirm(obj){
		var modalOptType=$(obj).attr('data-opt-type');//new or eidt
		//alert('save');
		if(!checkSelectValue()){//检查 车辆及计划检查人，是否是从列表里选择的
			return false;
		}
		//参数表
		var data={};
		data.CHECK_TARGET_ID=$('#add_input_check_target_id_check').val();
		data.CHECK_TARGET_NAME=$('#add_input_check_target_name_check').val();
		if($('#add_input_ins_check_p_name')!=null)
		data.CHECK_PL_MEMBER_ID=$('#add_input_ins_check_p_id_check').val();
		
		if($('#add_input_ins_check_r_name')!=null)
		data.CHECK_R_MEMBER_ID=$('#add_input_ins_check_r_id_check').val();
		
		data.CHECK_PL_BEGIN_TIME=$('#add_input_ins_check_p_begin').val();
		data.CHECK_PL_END_TIME=$('#add_input_ins_check_p_end').val();
		
		data.CHECK_R_BEGIN_TIME=$('#add_input_ins_check_r_begin').val();
		data.CHECK_R_END_TIME=$('#add_input_ins_check_r_end').val();
		
		if($('#add_input_ins_check_address')!=null){
			data.POSITION_CODE=$('#add_input_ins_check_address_code_check').val();
			data.POSITION_NAME=$('#add_input_ins_check_address').val();
			data.POSITION_ADDRESS=$('#add_input_ins_check_address').val();
		}
		
		data.CHECK_ITEM_COUNT=$('#add_input_ins_check_item_count').val();
		data.CHECK_PASS_COUNT=$('#add_input_ins_check_item_pass_count').val();
		
		data.CHECK_ORDER_STATUS=$('#add_input_ins_check_status').val();
		data.CHECK_ORDER_RESULT=$('#add_input_ins_check_result').val();
		data.CHECK_ORDER_DESC=$('#add_input_ins_check_order_desc').val();
		
		var checkNodesResult = tree.getCheckedNodes();//已选择node的key的array
		if(checkNodesResult!=null){ //筛选其中的检查项目，过滤掉检查项目类别节点
			var checkNodesItemArray=[];
        	$.each(checkNodesResult,function(index,item){
        		var data = tree.getDataById(item);	
        	  	if(true==data.IS_ITEM_TAG){
        	  		checkNodesItemArray.push(item);
//	        	  	console.log(data.text+":"+data.IS_ITEM_TAG);
// 	        	  	console.log(data.text+":"+data.ORDER_ITEM_ID);
        	  	}
        	});
	 		data.ITEM_LIST=checkNodesItemArray.join(';;');//,分割 
	 		console.log('checkNodesItemArray:='+data.ITEM_LIST);
	 		//CHE_010101ITEM$$测试检查项目;;CHE_010101ITEM-1$$CHE_010101ITEM-1-NAME;;PTCT-0010$$普通车头-检查;;CZPT-001010$$普通车轴二;;CZPT-0010$$车轴普通-检查
		}
		data.CHECK_ORDER_NO=$('#add_input_ins_check_order_no').val();
		var reqUrl;
// 		alert('modalOptType'+modalOptType);
// 		alert('data.CHECK_ORDER_NO'+data.CHECK_ORDER_NO);
		if('NEW'==modalOptType&&(data.CHECK_ORDER_NO==null||data.CHECK_ORDER_NO=='')){
			reqUrl='<%=path%>/AjaxChannel?action=BUSI_DATA_CHECK_ORDER_ADD_ACTION';
		}
		else if('EDIT'==modalOptType||(data.CHECK_ORDER_NO!=null&&data.CHECK_ORDER_NO!='')){ //单号不为空，则也为编辑
// 			data.CHECK_ORDER_NO=$('#add_input_ins_check_order_no').val();
			reqUrl='<%=path%>/AjaxChannel?action=BUSI_DATA_CHECK_ORDER_EDIT_ACTION';
		}
		else{
			returnErrorMsgShow('modal_add_check_order','未知操作类型，请稍后重试');
			return false;
		}
		
		$(obj).button('loading');
		//ajax begin
		$.ajax({
				type : 'POST',
				url:reqUrl,
				data: data,
				dataType : 'json',
				success : function(json) {
					if(json.SUCCESS=='1'){
							returnSuccessMsgShow('modal_add_check_order',json.MSG)
							if('NEW'==modalOptType){
								$('#add_input_ins_check_order_no').val(json.CHECK_ORDER_NO);
							}
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
	function optConfirm(opt_type,check_order_no,check_order_id){
		//auto top and height
// 		modal_auto_top($('div.modal-dialog'));

// 		$('#optModal').find("button.btn-primary:first").button('reset');
// 		clearMsgShow('optModal');
//		init common
		myModalInit('optModal');
		
		var title;
		var contentHtml;
		
		$("#opt_confirm_check_order_id").val(check_order_id);
		$("#opt_confirm_check_order_no").val(check_order_no);
		$("#opt_confirm_opt_type").val(opt_type);
		
		if("delete"==opt_type){
			title="删除检查单["+check_order_no+"]";
			
			contentHtml=
				"<div class='alert alert-danger' role='alert'>"
					+"<span class='glyphicon glyphicon-exclamation-sign' aria-hidden='true'></span> <span class='sr-only'>注意:</span>"
					+"<strong>注意:</strong>检查单["+check_order_no+"]将彻底删除，无法恢复 !"
				+"</div>";
// 			$('#optModal').find("button.btn-primary:first").click(startRole);

		}else if("stop"==opt_type)	{
			title="停用检查单["+check_order_no+"]";
			
			contentHtml=
				"<div class='alert alert-warning' role='alert'>"
					+"<span class='glyphicon glyphicon-question-sign' aria-hidden='true'></span> <span class='sr-only'>注意:</span>"
					+"<strong>注意:</strong>检查单["+check_order_no+"]将停用，可在检查单管理恢复。"
				+"</div>";
// 			$('#optModal').find("button.btn-primary:first").click(stopRole);
		}else if("start"==opt_type){
			title="启用检查单["+check_order_no+"]";
			
			contentHtml=
				"<div class='alert alert-info' role='alert'>"
					+"<strong>注意:</strong><span class='glyphicon glyphicon-info-sign' aria-hidden='true'></span> <span class='sr-only'>注意:</span>"
					+"检查单["+check_order_no+"]将被启用。"
				+"</div>";
// 			$('#optModal').find("button.btn-primary:first").click(deleteRole);
		}
		$('#optModal').find("h4.modal-title:first").text(title);
		$('#optModal').find("div.modal-body:first").html(contentHtml);
		$('#optModal').modal({backdrop: 'static', keyboard: false});
	}
	
	function optConfirmDone(obj){
		var checkOrderId=$("#opt_confirm_check_order_id").val();
		var checkOrderNo=$("#opt_confirm_check_order_no").val();
		var optType=$("#opt_confirm_opt_type").val();
		
		$(obj).button('loading');
		var reqUrl='<%=path%>/AjaxChannel?action=BUSI_DATA_CHECK_ORDER_MOD_ACTION';
		//ajax begin
		$.ajax({
				type : 'POST',
				url:reqUrl,
				data: {CHECK_ORDER_NO:checkOrderNo,OPT_TYPE:optType},
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
	
	//在执行保存时，检查车辆，及计划检查人，是否是从下拉列表里选择的
	function checkSelectValue(){
		var inputTargetName=$('#add_input_check_target_name').val();
		var checkTargetName=$('#add_input_check_target_name_check').val();
		
		var inputMemberName=$('#add_input_ins_check_p_name').val();
		var checkMemberName=$('#add_input_ins_check_p_name_check').val();
		
		var inputMemberRName=$('#add_input_ins_check_r_name').val();
		var checkMemberRName=$('#add_input_ins_check_r_name_check').val();
		
		var inputAddressName=$('#add_input_ins_check_address').val();
		var checkAddressName=$('#add_input_ins_check_address_check').val();
		
		console.log("inputTargetName:="+inputTargetName);
		console.log("checkTargetName:="+checkTargetName);
		console.log("inputMemberName:="+inputMemberName);
		console.log("checkMemberName:="+checkMemberName);
	
		if(inputTargetName==''||inputTargetName!=checkTargetName){
			returnErrorMsgShow('modal_add_check_order','请从[检查对象名称]下拉列表里选择车牌');
			$("#add_input_check_target_name")[0].focus();
			return false;
		}
		if(inputMemberName!=''&&inputMemberName!=checkMemberName){
			returnErrorMsgShow('modal_add_check_order','请从[计划检查人]下拉列表里选择检查人');
			$("#add_input_ins_check_p_name")[0].focus();
			return false;
		}
		
		if(inputMemberRName!=''&&inputMemberRName!=checkMemberRName){
			returnErrorMsgShow('modal_add_check_order','请从[实际检查人]下拉列表里选择检查人');
			$("#add_input_ins_check_r_name")[0].focus();
			return false;
		}
		
		if(inputAddressName!=''&&inputAddressName!=checkAddressName){
			returnErrorMsgShow('modal_add_check_order','请从[检查地点]下拉列表里选择检查地点');
			$("#add_input_ins_check_address")[0].focus();
			return false;
		}
		return true;
	}
	
	//根据选择的车辆id，查该车对应的检查项目，并渲染到tree上
	function queryTruckCheckItemParseTreeData(truckId){
		var reqUrl='<%=path%>/AjaxChannel?action=BUSI_DATA_QUERY_TURCK_CHECK_OBJ_ITEM_ACTION';
		//ajax begin
		$.ajax({
				type : 'POST',
				url:reqUrl,
				data: {TRUCK_ID:truckId},
				dataType : 'json',
				async : false,
				success : function(json) {
					if(json.SUCCESS=='1'){
// 						var defaultData=json.TREE_DATA;
							//main bean
							if(json.TRUCK_CHECK_ITEM_LIST!=null){
								$.each(json.TRUCK_CHECK_ITEM_LIST,function(index,item){
									console.log(item);
									var key=(item.CHECK_OBJ_CODE+'$$'+item.CHECK_OBJ_NAME);
									var checkedNode=tree.getNodeById(key);
// 									var checkResult=item.CheckOrderResult;//检查结果
// 									var faultDesc=item.TroubleDesc;//问题描述
									console.log("key:"+key);
// 									console.log("checkResult:"+checkResult);
									console.log("checkedNode:"+checkedNode);
									if(checkedNode!=null&&checkedNode!=undefined){
										tree.check(checkedNode);//设置选中
// 										tree.expand(checkedNode,false);//展开当前层级节点
									}
								});
							}
						}else{
							returnErrorMsgShow('modal_add_check_order',json.MSG)	
						}
					},
				error : function(e) {
					console.log(e);
					}
				});
	}
	
	function optCheckOrderItem(checkOrderNo,checkOrderId){
		  window.location.href="ins_check_order_item_manager.jsp?CHECK_ORDER_NO="+checkOrderNo; 
	}
</script>

</body>
</html>