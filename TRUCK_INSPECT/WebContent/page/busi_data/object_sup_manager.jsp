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
        <h2 class="pull-left"><i class="icon-user"></i> 检查项目一级分类管理</h2>

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
        			<label class="col-sm-5 control-label">一级分类名称</label>
         			<div class="col-sm-7"><input type="text" class="form-control" placeholder="一级分类名称" id="qr_input_obj_name"></div>
        		</div>
       			<div class="form-group col-sm-3">
        			<label class="col-sm-5 control-label">一级分类代码</label>
         			<div class="col-sm-7"><input type="text" class="form-control" placeholder="一级分类代码" id="qr_input_obj_code"></div>
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
                  <div class="pull-left">查询结果 &nbsp;&nbsp;&nbsp;<button type="button" class="btn btn-primary btn-xs" href="javascript:void(0);" onclick="addObjSup();" id="bt_add_obj_sup">新增一级分类</button></div>
                  <div class="widget-icons pull-right">
                    <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a> 
                    <a href="#" class="wclose"><i class="icon-remove"></i></a>
                  </div>  
                  <div class="clearfix"></div>
                </div>

                  <div class="widget-content">
                  
                    <table id="objSupList"  class="table table-striped table-bordered table-hover">
                      <thead>
                        <tr>
                          <th width="5%"></th>
                          <th width="10%"></th>
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
		<input  type="hidden" id="opt_confirm_obj_sup_id" value="" >
		<input  type="hidden" id="opt_confirm_obj_sup_code" value="" >
		<input  type="hidden" id="opt_confirm_obj_sup_name" value="" >
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
	
	<!-- Modal add obj sup -->
	<div id="modal_add_obj_sup" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content widget ">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title" id="modal_add_obj_sup_title"></h4>
				</div>
				<div class="alert opt-result-alert"></div>
				<div class="modal-body modal-scrollable" id="modal_add_obj_sup_content">

				 <div class="padd">
                 	 <div class="form-horizontal">
                 	 <input  type="hidden" id="add_input_obj_sup_id" value="" >
                          					<div class="form-group">
<!--                                             <label class="control-label col-md-3" for="add_input_obj_sup_code">一级分类代码</label> -->
<!--                                             <div class="col-md-3"> -->
<!--                                               <input type="text" class="form-control required-input" id="add_input_obj_sup_code" placeholder="一级分类代码"> -->
<!--                                               <span class="text-danger" >*</span> -->
<!--                                             </div> -->
                                            <label class="control-label col-md-3" for="add_input_obj_sup_code">一级分类代码</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_obj_sup_code" placeholder="一级分类代码">
                                              <span class="text-danger">*</span>
                                            </div>
                                            <label class="control-label col-md-3" for="add_input_obj_sup_name">一级分类名称</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_obj_sup_name" placeholder="一级分类名称">
                                              <span class="text-danger">*</span>
                                            </div>
                                          </div>   
                                          <!--  -->
                                          <div class="form-group">
                                            <label class="control-label col-md-3" for="add_input_obj_sup_desc">一级分类说明</label>
                                            <div class="col-md-8">
                                              <input type="text" class="form-control" id="add_input_obj_sup_desc"  placeholder="一级分类说明">
                                            </div>
                                          </div>
                                          <div class="form-group">
                                            <label class="control-label col-md-3" for="add_input_obj_sup_sort">分类排序</label>
                                            <div class="col-md-4">
                                              <input type="number" min="1" value="1" class="form-control" id="add_input_obj_sup_sort"  placeholder="分类排序">
                                            </div>
                                          </div>
                                          <!-- Select box -->
<!--                                           <div class="form-group"> -->
<!--                                             <label class="control-label col-md-3">Drop Down</label> -->
<!--                                             <div class="col-md-9">                                -->
<!--                                                 <select class="form-control"> -->
<!--                                                 <option>&nbsp;</option> -->
<!--                                                 <option>1</option> -->
<!--                                                 <option>2</option> -->
<!--                                                 </select>   -->
<!--                                             </div> -->
<!--                                           </div> -->
										
<!-- 										<div class="form-group"><center><hr class="modal-hr"/></center> -->
<!-- 											<div class="panel-body" id="role_action_option_div"><p>角色对应菜单列表</p> -->
<%-- 												<yb:funActOptHtml ulId="role_action_option"></yb:funActOptHtml> --%>
<!-- 											</div> -->
<!-- 								         </div>   -->
								</div>
               		 </div>
				</div>
				<div class="modal-footer">
<!-- 					<button type="button" class="btn btn-primary">确定</button> --><!-- data-opt-type="" new/edit -->
					<button type="button" id="btn_save_obj_sup" data-opt-type="" class="btn btn-success" data-loading-text="Loading" onclick="saveObjSupConfirm(this);">保&nbsp;&nbsp;存</button>
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
<script src="<%=path%>/js/busi-js/query_obj_sup_list.js?v=<%=staticVersion%>" type="text/javascript"></script>

<!-- Script for this page -->
<script type="text/javascript">

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
		var objName = $("#qr_input_obj_name").val();
		var objCode = $("#qr_input_obj_code").val();
		var includeStop=(true==$("#qr_cb_include_stop").is(':checked'))?"1":"0";
		
		//格式：列映射关系
		var aoColumns = '[{"mDataProp" : "OBJ_SUP_ID","sTitle" : "序号"}, '
					   + '{"mDataProp" : "OBJ_CLASS_CODE","sTitle" : "一级分类代码"},'
					   + '{"mDataProp" : "OBJ_CLASS_NAME","sTitle" : "一级分类名称"},'
					   + '{"mDataProp" : "OBJ_CLASS_DESC","sTitle" : "一级分类说明"},'
					   + '{"mDataProp" : "OBJ_SUP_ID","sTitle" : "创建信息"},'
					   + '{"mDataProp" : "FREEZE_TAG","sTitle" : "状态"},'
					   + '{"mDataProp" : "OBJ_SUP_ID","sTitle" : "操作"}]';

		var reqData = {
			action : 'BUSI_DATA_OBJ_SUP_QUERY_LIST_ACTION',
			params : 'params',
			OBJ_NAME:objName,
			OBJ_CODE:objCode,
			INCLUDE_STOP:includeStop
			};
		//example为table定义id ：10：需要加入操作按钮的列；[4,5]：需要转义的列（映射数据字典值）
		initObjSupTable('objSupList', aoColumns, reqData, '<%=path%>',6,[6]);
		resetIFrameLength();//need ajax async:false,
		} 
	/*title tips- common method,dynamic bind*/
	$(function () { $("[data-toggle='tooltip']").tooltip();});
	

	
	function addObjSup(){
		myModalInit('modal_add_obj_sup');
		activeModalInput('modal_add_obj_sup');
		//btn show
		$('#btn_save_obj_sup').show();
		$('#btn_save_obj_sup').attr('data-opt-type','NEW');
		
		$('#modal_add_obj_sup_title').text('新增一级分类');
		$('#modal_add_obj_sup').modal({backdrop: 'static', keyboard: false});
		
	}
	
	function saveObjSupConfirm(obj){
		var modalOptType=$(obj).attr('data-opt-type');//new or eidt

		var obj_sup_name=$('#add_input_obj_sup_name').val();
		var obj_sup_desc=$('#add_input_obj_sup_desc').val();
		var obj_sup_code=$('#add_input_obj_sup_code').val();
		var obj_sup_id=$('#add_input_obj_sup_id').val();
		var obj_sup_sort=$('#add_input_obj_sup_sort').val();
		
		var reqUrl;
		if('NEW'==modalOptType){
			reqUrl='<%=path%>/AjaxChannel?action=BUSI_DATA_OBJ_SUP_ADD_ACTION';
		}
		else if('EDIT'==modalOptType){
			reqUrl='<%=path%>/AjaxChannel?action=BUSI_DATA_OBJ_SUP_EDIT_ACTION';
		}
		else{
			returnErrorMsgShow('modal_add_obj_sup','未知操作类型，请稍后重试')
			return false;
		}
		
		$(obj).button('loading');
		//ajax begin
		$.ajax({
				type : 'POST',
				url:reqUrl,
				data: {OBJ_SUP_NAME:obj_sup_name,OBJ_SUP_DESC:obj_sup_desc,OBJ_SUP_ID:obj_sup_id,OBJ_SUP_CODE:obj_sup_code,OBJ_SUP_SORT:obj_sup_sort},
				dataType : 'json',
				success : function(json) {
					if(json.SUCCESS=='1'){
							returnSuccessMsgShow('modal_add_obj_sup',json.MSG)
							var fvTable=$("#objSupList").dataTable(); //datatable init current
							fvTable.fnDraw(false);
							$(obj).button('reset');
						}else{
// 							alert(json.MSG)	
							returnErrorMsgShow('modal_add_obj_sup',json.MSG)	
							$(obj).button('reset');
						}
					},
				error : function(e) {
					console.log(e);
					}
				});
	}
	
	function detail(obj_sup_id){
		myModalInit('modal_add_obj_sup');
		//input readOnly
		readOnlyModalInput('modal_add_obj_sup');
		
		//btn hide
		$('#btn_save_obj_sup').hide();
		$('#btn_save_obj_sup').attr('data-opt-type','QUERY');
		
		var reqUrl='<%=path%>/AjaxChannel?action=BUSI_DATA_OBJ_SUP_DETAIL_ACTION';
		//ajax begin
		$.ajax({
				type : 'POST',
				url:reqUrl,
				data: {OBJ_SUP_ID:obj_sup_id},
				dataType : 'json',
				success : function(json) {
					if(json.SUCCESS=='1'){
							$('#add_input_obj_sup_code').val(json.OBJ_SUP_BEAN.ObjClassCode);
							$('#add_input_obj_sup_name').val(json.OBJ_SUP_BEAN.ObjClassName);
							$('#add_input_obj_sup_desc').val(json.OBJ_SUP_BEAN.ObjClassDesc);
							$('#add_input_obj_sup_id').val(json.OBJ_SUP_BEAN.Id);
							$('#add_input_obj_sup_sort').val(json.OBJ_SUP_BEAN.Sort);
// 							returnSuccessMsgShow('modal_add_obj_sup',json.MSG)
							$('#modal_add_obj_sup_title').text('一级分类明细['+json.OBJ_SUP_BEAN.ObjClassName+']');
						}else{
							returnErrorMsgShow('modal_add_obj_sup',json.MSG)	
						}
					},
				error : function(e) {
					console.log(e);
					}
				});
		//ajax end
// 		$('#modal_add_obj_sup_title').text('一级分类明细['+$('#add_input_obj_sup_name').val()+']'); //EMPTY
		$('#modal_add_obj_sup').modal({backdrop: 'static', keyboard: false});
		
	}
	
	//open modal
	function editObjSup(obj_sup_id){
// 		init
		myModalInit('modal_add_obj_sup');
		activeModalInput('modal_add_obj_sup');
		$('#add_input_obj_sup_code').attr('readonly','');
		
		//btn show
		$('#btn_save_obj_sup').show();
		$('#btn_save_obj_sup').attr('data-opt-type','EDIT');
		
		var reqUrl='<%=path%>/AjaxChannel?action=BUSI_DATA_OBJ_SUP_DETAIL_ACTION';
		//ajax begin
		$.ajax({
				type : 'POST',
				url:reqUrl,
				data: {OBJ_SUP_ID:obj_sup_id},
				dataType : 'json',
				success : function(json) {
					if(json.SUCCESS=='1'){
						$('#add_input_obj_sup_code').val(json.OBJ_SUP_BEAN.ObjClassCode);
						$('#add_input_obj_sup_name').val(json.OBJ_SUP_BEAN.ObjClassName);
						$('#add_input_obj_sup_desc').val(json.OBJ_SUP_BEAN.ObjClassDesc);
						$('#add_input_obj_sup_id').val(json.OBJ_SUP_BEAN.Id);
						$('#add_input_obj_sup_sort').val(json.OBJ_SUP_BEAN.Sort);
						
						$('#modal_add_obj_sup_title').text('一级分类编辑['+json.OBJ_SUP_BEAN.ObjClassName+']');
						
						}else{
// 							alert(json.MSG)	
							returnErrorMsgShow('modal_add_obj_sup',json.MSG)	
						}
					},
				error : function(e) {
					console.log(e);
					}
				});
		//ajax end
		$('#modal_add_obj_sup').modal({backdrop: 'static', keyboard: false});
	}
	
	/*opt confirm*/
	function optConfirm(opt_type,obj_sup_name,obj_sup_id){
		//auto top and height
// 		modal_auto_top($('div.modal-dialog'));

// 		$('#optModal').find("button.btn-primary:first").button('reset');
// 		clearMsgShow('optModal');
//		init common
		myModalInit('optModal');
		
		var title;
		var contentHtml;
		
		$("#opt_confirm_obj_sup_id").val(obj_sup_id);
		$("#opt_confirm_obj_sup_name").val(obj_sup_name);
		$("#opt_confirm_opt_type").val(opt_type);
		
		if("delete"==opt_type){
			title="删除一级分类["+obj_sup_name+"]";
			
			contentHtml=
				"<div class='alert alert-danger' role='alert'>"
					+"<span class='glyphicon glyphicon-exclamation-sign' aria-hidden='true'></span> <span class='sr-only'>注意:</span>"
					+"<strong>注意:</strong>一级分类["+obj_sup_name+"]将彻底删除，无法恢复 !"
				+"</div>";
// 			$('#optModal').find("button.btn-primary:first").click(startRole);

		}else if("stop"==opt_type)	{
			title="停用一级分类["+obj_sup_name+"]";
			
			contentHtml=
				"<div class='alert alert-warning' role='alert'>"
					+"<span class='glyphicon glyphicon-question-sign' aria-hidden='true'></span> <span class='sr-only'>注意:</span>"
					+"<strong>注意:</strong>一级分类["+obj_sup_name+"]将停用，可在一级分类管理恢复。"
				+"</div>";
// 			$('#optModal').find("button.btn-primary:first").click(stopRole);
		}else if("start"==opt_type){
			title="启用一级分类["+obj_sup_name+"]";
			
			contentHtml=
				"<div class='alert alert-info' role='alert'>"
					+"<strong>注意:</strong><span class='glyphicon glyphicon-info-sign' aria-hidden='true'></span> <span class='sr-only'>注意:</span>"
					+"一级分类["+obj_sup_name+"]将被启用。"
				+"</div>";
// 			$('#optModal').find("button.btn-primary:first").click(deleteRole);
		}
		$('#optModal').find("h4.modal-title:first").text(title);
		$('#optModal').find("div.modal-body:first").html(contentHtml);
		$('#optModal').modal({backdrop: 'static', keyboard: false});
	}
	
	function optConfirmDone(obj){
		var objSupId=$("#opt_confirm_obj_sup_id").val();
		var objSupName=$("#opt_confirm_obj_sup_name").val();
		var optType=$("#opt_confirm_opt_type").val();
		
		$(obj).button('loading');
		var reqUrl='<%=path%>/AjaxChannel?action=BUSI_DATA_OBJ_SUP_MOD_ACTION';
		//ajax begin
		$.ajax({
				type : 'POST',
				url:reqUrl,
				data: {OBJ_SUP_ID:objSupId,OPT_TYPE:optType},
				dataType : 'json',
				success : function(json) {
					if(json.SUCCESS=='1'){
							returnSuccessMsgShow('optModal',json.MSG);//alert msg
// 							$(obj).button('complete');//button 
							var fvTable=$("#objSupList").dataTable(); //datatable init current
							fvTable.fnDraw(false);
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
		  window.location.href="object_mid_manager.jsp?SUP_CODE="+supCode; 
	}
	

</script>

</body>
</html>