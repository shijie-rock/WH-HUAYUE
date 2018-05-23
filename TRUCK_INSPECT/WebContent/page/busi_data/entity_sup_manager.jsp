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
        <h2 class="pull-left"><i class="icon-user"></i> 检查对象大类管理</h2>

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
        			<label class="col-sm-5 control-label">对象大类名称</label>
         			<div class="col-sm-7"><input type="text" class="form-control" placeholder="对象大类名称" id="qr_input_ent_name"></div>
        		</div>
       			<div class="form-group col-sm-3">
        			<label class="col-sm-5 control-label">对象大类代码</label>
         			<div class="col-sm-7"><input type="text" class="form-control" placeholder="对象大类代码" id="qr_input_ent_code"></div>
        		</div>

				<div class="form-group col-sm-3">
					<label class="checkbox-inline"> <input type="checkbox" id="qr_cb_include_stop" value="1"> 包含已停用</label>
				</div>
							<!-- 查询按钮 begin -->
        		 <div class="form-group col-sm-3">
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
                  <div class="pull-left">查询结果 &nbsp;&nbsp;&nbsp;<button type="button" class="btn btn-primary btn-xs" href="javascript:void(0);" onclick="addEntSup();" id="bt_add_ent_sup">新增对象大类</button></div>
                  <div class="widget-icons pull-right">
                    <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a> 
                    <a href="#" class="wclose"><i class="icon-remove"></i></a>
                  </div>  
                  <div class="clearfix"></div>
                </div>

                  <div class="widget-content">
                  
                    <table id="entSupList"  class="table table-striped table-bordered table-hover">
                      <thead>
                        <tr>
                          <th width="5%"></th>
                          <th width="10%"></th>
                          <th width="10%"></th>
                          <th width="15%"></th>
                          <th width="10%"></th>
                          <th width="15%"></th>
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
		<input  type="hidden" id="opt_confirm_ent_sup_id" value="" >
		<input  type="hidden" id="opt_confirm_ent_sup_code" value="" >
		<input  type="hidden" id="opt_confirm_ent_sup_name" value="" >
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
	
	<!-- Modal add ent sup -->
	<div id="modal_add_ent_sup" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content widget ">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title" id="modal_add_ent_sup_title"></h4>
				</div>
				<div class="alert opt-result-alert"></div>
				<div class="modal-body modal-scrollable" id="modal_add_ent_sup_content">

				 <div class="padd">
                 	 <div class="form-horizontal">
                 	 <input  type="hidden" id="add_input_ent_sup_id" value="" >
                          					<div class="form-group">
<!--                                             <label class="control-label col-md-3" for="add_input_ent_sup_code">对象大类代码</label> -->
<!--                                             <div class="col-md-3"> -->
<!--                                               <input type="text" class="form-control required-input" id="add_input_ent_sup_code" placeholder="对象大类代码"> -->
<!--                                               <span class="text-danger" >*</span> -->
<!--                                             </div> -->
                                            <label class="control-label col-md-3" for="add_input_ent_sup_code">对象大类代码</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_ent_sup_code" placeholder="对象大类代码">
                                              <span class="text-danger">*</span>
                                            </div>
                                            <label class="control-label col-md-3" for="add_input_ent_sup_name">对象大类名称</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_ent_sup_name" placeholder="对象大类名称">
                                              <span class="text-danger">*</span>
                                            </div>
                                          </div>   
                                          <!--  -->
                                          <div class="form-group">
                                            <label class="control-label col-md-3" for="add_input_ent_sup_desc">对象大类说明</label>
                                            <div class="col-md-8">
                                              <input type="text" class="form-control" id="add_input_ent_sup_desc"  placeholder="对象大类说明">
                                            </div>
                                          </div>
                                          <div class="form-group">
                                            <label class="control-label col-md-3" for="add_input_ent_sup_sort">对象大类排序</label>
                                            <div class="col-md-4">
                                              <input type="number" min="1" value="1" class="form-control required-input" id="add_input_ent_sup_sort"  placeholder="分类排序">
                                            </div>
                                            
                                           <label class="col-md-3 control-label">对象大类类型</label>
						         			<div class="col-md-4">
						         			<yb:select dataSource="DATA_DIC.CHECK_ENT_TYPE"  selectClass="form-control subWidth"  includeNull="true" selectId="add_input_ent_type"/>
						         			</div>
                                          </div>
								</div>
               		 </div>
				</div>
				<div class="modal-footer">
					<button type="button" id="btn_save_ent_sup" data-opt-type="" class="btn btn-success" data-loading-text="Loading" onclick="saveEntSupConfirm(this);">保&nbsp;&nbsp;存</button>
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
<!-- table收起 -->
<script src="<%=path%>/js/truck-inspect-common.js?v=<%=staticVersion%>"></script> <!-- Custom codes -->
<script src="<%=path%>/js/data-tables/jquery.dataTables.min.js?v=<%=staticVersion%>"	type="text/javascript"></script>
<script src="<%=path%>/js/data-tables/dataTables.bootstrap.min.js?v=<%=staticVersion%>"	type="text/javascript"></script>
<script src="<%=path%>/js/busi-js/query_ent_sup_list.js?v=<%=staticVersion%>" type="text/javascript"></script>

<!-- Script for this page -->
<script type="text/javascript">
//data dic
var map=parseData2Map('<yb:dataDic dataDicType="CHECK_ENT_TYPE,TRUCK_STATUS"/>');
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
	}); 
	
	function dataTableInit() {
		//param
		var entName = $("#qr_input_ent_name").val();
		var entCode = $("#qr_input_ent_code").val();
		var includeStop=(true==$("#qr_cb_include_stop").is(':checked'))?"1":"0";
		
		//格式：列映射关系
		var aoColumns = '[{"mDataProp" : "ENT_SUP_ID","sTitle" : "序号"}, '
					   + '{"mDataProp" : "CHECK_ENT_CODE","sTitle" : "对象大类代码"},'
					   + '{"mDataProp" : "CHECK_ENT_NAME","sTitle" : "对象大类名称"},'
					   + '{"mDataProp" : "CHECK_ENT_DESC","sTitle" : "对象大类说明"},'
					   + '{"mDataProp" : "CHECK_ENT_TYPE_CODE","sTitle" : "对象类型"},'
					   + '{"mDataProp" : "ENT_SUP_ID","sTitle" : "创建信息"},'
					   + '{"mDataProp" : "FREEZE_TAG","sTitle" : "状态"},'
					   + '{"mDataProp" : "ENT_SUP_ID","sTitle" : "操作"}]';

		var reqData = {
			action : 'BUSI_DATA_ENT_SUP_QUERY_LIST_ACTION',
			params : 'params',
			ENT_NAME:entName,
			ENT_CODE:entCode,
			INCLUDE_STOP:includeStop
			};
		//example为table定义id ：10：需要加入操作按钮的列；[4,5]：需要转义的列（映射数据字典值）
		initEntSupTable('entSupList', aoColumns, reqData, '<%=path%>',7,[5]);
		resetIFrameLength();//need ajax async:false,
		} 
	/*title tips- common method,dynamic bind*/
	$(function () { $("[data-toggle='tooltip']").tooltip();});
	

	
	function addEntSup(){
		myModalInit('modal_add_ent_sup');
		activeModalInput('modal_add_ent_sup');
		//btn show
		$('#btn_save_ent_sup').show();
		$('#btn_save_ent_sup').attr('data-opt-type','NEW');
		
		$('#modal_add_ent_sup_title').text('新增对象大类');
		$('#modal_add_ent_sup').modal({backdrop: 'static', keyboard: false});
		
	}
	
	function saveEntSupConfirm(obj){
		var modalOptType=$(obj).attr('data-opt-type');//new or eidt

		var ent_sup_name=$('#add_input_ent_sup_name').val();
		var ent_sup_desc=$('#add_input_ent_sup_desc').val();
		var ent_sup_code=$('#add_input_ent_sup_code').val();
		var ent_sup_id=$('#add_input_ent_sup_id').val();
		var ent_sup_sort=$('#add_input_ent_sup_sort').val();
		var ent_type=$('#add_input_ent_type').val();
		
		var reqUrl;
		if('NEW'==modalOptType){
			reqUrl='<%=path%>/AjaxChannel?action=BUSI_DATA_ENT_SUP_ADD_ACTION';
		}
		else if('EDIT'==modalOptType){
			reqUrl='<%=path%>/AjaxChannel?action=BUSI_DATA_ENT_SUP_EDIT_ACTION';
		}
		else{
			returnErrorMsgShow('modal_add_ent_sup','未知操作类型，请稍后重试')
			return false;
		}
		
		$(obj).button('loading');
		//ajax begin
		$.ajax({
				type : 'POST',
				url:reqUrl,
				data: {ENT_SUP_NAME:ent_sup_name,ENT_SUP_DESC:ent_sup_desc,ENT_SUP_ID:ent_sup_id,ENT_SUP_CODE:ent_sup_code,ENT_SUP_SORT:ent_sup_sort,ENT_TYPE:ent_type},
				dataType : 'json',
				success : function(json) {
					if(json.SUCCESS=='1'){
							returnSuccessMsgShow('modal_add_ent_sup',json.MSG)
							var fvTable=$("#entSupList").dataTable(); //datatable init current
							fvTable.fnDraw(false);
							resetIFrameLength();//need ajax async:false,
							$(obj).button('reset');
						}else{
// 							alert(json.MSG)	
							returnErrorMsgShow('modal_add_ent_sup',json.MSG)	
							$(obj).button('reset');
						}
					},
				error : function(e) {
					console.log(e);
					}
				});
	}
	
	function detail(ent_sup_id){
		myModalInit('modal_add_ent_sup');
		//input readOnly
		readOnlyModalInput('modal_add_ent_sup');
		
		//btn hide
		$('#btn_save_ent_sup').hide();
		$('#btn_save_ent_sup').attr('data-opt-type','QUERY');
		
		var reqUrl='<%=path%>/AjaxChannel?action=BUSI_DATA_ENT_SUP_DETAIL_ACTION';
		//ajax begin
		$.ajax({
				type : 'POST',
				url:reqUrl,
				data: {ENT_SUP_ID:ent_sup_id},
				dataType : 'json',
				success : function(json) {
					if(json.SUCCESS=='1'){
							$('#add_input_ent_sup_code').val(json.ENT_SUP_BEAN.CheckEntCode);
							$('#add_input_ent_sup_name').val(json.ENT_SUP_BEAN.CheckEntName);
							$('#add_input_ent_sup_desc').val(json.ENT_SUP_BEAN.CheckEntDesc);
							$('#add_input_ent_sup_id').val(json.ENT_SUP_BEAN.Id);
							$('#add_input_ent_sup_sort').val(json.ENT_SUP_BEAN.Sort);
							$('#add_input_ent_type').val(json.ENT_SUP_BEAN.CheckEntTypeCode);
// 							returnSuccessMsgShow('modal_add_ent_sup',json.MSG)
							$('#modal_add_ent_sup_title').text('对象大类明细['+json.ENT_SUP_BEAN.CheckEntCode+']');
						}else{
							returnErrorMsgShow('modal_add_ent_sup',json.MSG)	
						}
					},
				error : function(e) {
					console.log(e);
					}
				});
		//ajax end
// 		$('#modal_add_ent_sup_title').text('对象大类明细['+$('#add_input_ent_sup_name').val()+']'); //EMPTY
		$('#modal_add_ent_sup').modal({backdrop: 'static', keyboard: false});
		
	}
	
	//open modal
	function editEntSup(ent_sup_id){
// 		init
		myModalInit('modal_add_ent_sup');
		activeModalInput('modal_add_ent_sup');
		$('#add_input_ent_sup_code').attr('readonly','');
		
		//btn show
		$('#btn_save_ent_sup').show();
		$('#btn_save_ent_sup').attr('data-opt-type','EDIT');
		
		var reqUrl='<%=path%>/AjaxChannel?action=BUSI_DATA_ENT_SUP_DETAIL_ACTION';
		//ajax begin
		$.ajax({
				type : 'POST',
				url:reqUrl,
				data: {ENT_SUP_ID:ent_sup_id},
				dataType : 'json',
				success : function(json) {
					if(json.SUCCESS=='1'){
						$('#add_input_ent_sup_code').val(json.ENT_SUP_BEAN.CheckEntCode);
						$('#add_input_ent_sup_name').val(json.ENT_SUP_BEAN.CheckEntName);
						$('#add_input_ent_sup_desc').val(json.ENT_SUP_BEAN.CheckEntDesc);
						$('#add_input_ent_sup_id').val(json.ENT_SUP_BEAN.Id);
						$('#add_input_ent_sup_sort').val(json.ENT_SUP_BEAN.Sort);
						$('#add_input_ent_type').val(json.ENT_SUP_BEAN.CheckEntTypeCode);
						$('#modal_add_ent_sup_title').text('对象大类编辑['+json.ENT_SUP_BEAN.CheckEntCode+']');
						
						}else{
// 							alert(json.MSG)	
							returnErrorMsgShow('modal_add_ent_sup',json.MSG)	
						}
					},
				error : function(e) {
					console.log(e);
					}
				});
		//ajax end
		$('#modal_add_ent_sup').modal({backdrop: 'static', keyboard: false});
	}
	
	/*opt confirm*/
	function optConfirm(opt_type,ent_sup_name,ent_sup_id){
		//auto top and height
// 		modal_auto_top($('div.modal-dialog'));

// 		$('#optModal').find("button.btn-primary:first").button('reset');
// 		clearMsgShow('optModal');
//		init common
		myModalInit('optModal');
		
		var title;
		var contentHtml;
		
		$("#opt_confirm_ent_sup_id").val(ent_sup_id);
		$("#opt_confirm_ent_sup_name").val(ent_sup_name);
		$("#opt_confirm_opt_type").val(opt_type);
		
		if("delete"==opt_type){
			title="删除对象大类["+ent_sup_name+"]";
			
			contentHtml=
				"<div class='alert alert-danger' role='alert'>"
					+"<span class='glyphicon glyphicon-exclamation-sign' aria-hidden='true'></span> <span class='sr-only'>注意:</span>"
					+"<strong>注意:</strong>对象大类["+ent_sup_name+"]将彻底删除，无法恢复 !"
				+"</div>";
// 			$('#optModal').find("button.btn-primary:first").click(startRole);

		}else if("stop"==opt_type)	{
			title="停用对象大类["+ent_sup_name+"]";
			
			contentHtml=
				"<div class='alert alert-warning' role='alert'>"
					+"<span class='glyphicon glyphicon-question-sign' aria-hidden='true'></span> <span class='sr-only'>注意:</span>"
					+"<strong>注意:</strong>对象大类["+ent_sup_name+"]将停用，可在对象大类管理恢复。"
				+"</div>";
// 			$('#optModal').find("button.btn-primary:first").click(stopRole);
		}else if("start"==opt_type){
			title="启用对象大类["+ent_sup_name+"]";
			
			contentHtml=
				"<div class='alert alert-info' role='alert'>"
					+"<strong>注意:</strong><span class='glyphicon glyphicon-info-sign' aria-hidden='true'></span> <span class='sr-only'>注意:</span>"
					+"对象大类["+ent_sup_name+"]将被启用。"
				+"</div>";
// 			$('#optModal').find("button.btn-primary:first").click(deleteRole);
		}
		$('#optModal').find("h4.modal-title:first").text(title);
		$('#optModal').find("div.modal-body:first").html(contentHtml);
		$('#optModal').modal({backdrop: 'static', keyboard: false});
	}
	
	function optConfirmDone(obj){
		var objSupId=$("#opt_confirm_ent_sup_id").val();
		var objSupName=$("#opt_confirm_ent_sup_name").val();
		var optType=$("#opt_confirm_opt_type").val();
		
		$(obj).button('loading');
		var reqUrl='<%=path%>/AjaxChannel?action=BUSI_DATA_ENT_SUP_MOD_ACTION';
		//ajax begin
		$.ajax({
				type : 'POST',
				url:reqUrl,
				data: {ENT_SUP_ID:objSupId,OPT_TYPE:optType},
				dataType : 'json',
				success : function(json) {
					if(json.SUCCESS=='1'){
							returnSuccessMsgShow('optModal',json.MSG);//alert msg
// 							$(obj).button('complete');//button 
							var fvTable=$("#entSupList").dataTable(); //datatable init current
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
	
	function optEnterMid(supCode,supId){
		  window.location.href="entity_mid_manager.jsp?SUP_CODE="+supCode; 
	}
	

</script>

</body>
</html>