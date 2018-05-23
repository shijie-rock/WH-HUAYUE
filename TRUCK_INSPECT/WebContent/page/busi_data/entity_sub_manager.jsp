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
        <h2 class="pull-left"><i class="icon-user"></i> 对象小类管理</h2>

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
        			<label class="col-sm-5 control-label">对象小类名称</label>
         			<div class="col-sm-7"><input type="text" class="form-control" placeholder="对象小类名称" id="qr_input_ent_name"></div>
        		</div>
       			<div class="form-group col-sm-3">
        			<label class="col-sm-5 control-label">对象小类代码</label>
         			<div class="col-sm-7"><input type="text" class="form-control" placeholder="对象小类代码" id="qr_input_ent_code"></div>
        		</div>
       			<div class="form-group col-sm-3">
        			<label class="col-sm-5 control-label">对象中类代码</label>
         			<div class="col-sm-7"><input type="text" class="form-control" placeholder="对象中类代码" id="qr_input_ent_mid_code"></div>
        		</div>

				<div class="form-group col-sm-3">
					<label class="checkbox-inline"> <input type="checkbox" id="qr_cb_include_stop" value="1"> 包含已停用</label>
<!-- 				</div> -->
							<!-- 查询按钮 begin -->
<!--         		 <div class="form-group col-sm-3"> -->
						&nbsp;	&nbsp;
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
                  <div class="pull-left">查询结果 &nbsp;&nbsp;&nbsp;<button type="button" class="btn btn-primary btn-xs" href="javascript:void(0);" onclick="addEntMid();" id="bt_add_ent_sub">新增对象小类</button></div>
                  <div class="widget-icons pull-right">
                    <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a> 
                    <a href="#" class="wclose"><i class="icon-remove"></i></a>
                  </div>  
                  <div class="clearfix"></div>
                </div>

                  <div class="widget-content">
                  
                    <table id="entSubList"  class="table table-striped table-bordered table-hover">
                      <thead>
                        <tr>
                          <th width="5%"></th>
                          <th width="10%"></th>
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
		<input  type="hidden" id="opt_confirm_ent_sub_id" value="" >
		<input  type="hidden" id="opt_confirm_ent_sub_code" value="" >
		<input  type="hidden" id="opt_confirm_ent_sub_name" value="" >
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
	<div id="modal_add_ent_sub" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content widget ">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title" id="modal_add_ent_sub_title"></h4>
				</div>
				<div class="alert opt-result-alert"></div>
				<div class="modal-body modal-scrollable" id="modal_add_ent_sub_content">

				 <div class="padd">
                 	 <div class="form-horizontal">
                 	 <input  type="hidden" id="add_input_ent_sub_id" value="" >
                          					<div class="form-group">
<!--                                             <label class="control-label col-md-3" for="add_input_ent_sub_code">对象小类代码</label> -->
<!--                                             <div class="col-md-3"> -->
<!--                                               <input type="text" class="form-control required-input" id="add_input_ent_sub_code" placeholder="对象小类代码"> -->
<!--                                               <span class="text-danger" >*</span> -->
<!--                                             </div> -->
                                            <label class="control-label col-md-3" for="add_input_ent_sub_code">对象小类代码</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_ent_sub_code" placeholder="对象小类代码">
                                              <span class="text-danger">*</span>
                                            </div>
                                            <label class="control-label col-md-3" for="add_input_ent_sub_name">对象小类名称</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_ent_sub_name" placeholder="对象小类名称">
                                              <span class="text-danger">*</span>
                                            </div>
                                          </div>   
                                          <!---->
                                          <div class="form-group">
                                            <label class="control-label col-md-3" for="add_input_ent_sub_desc">对象小类说明</label>
                                            <div class="col-md-8">
                                              <input type="text" class="form-control" id="add_input_ent_sub_desc"  placeholder="对象小类说明">
                                            </div>
                                          </div>
                                          <div class="form-group">
                                            <label class="control-label col-md-3" for="add_input_ent_sub_sort">对象小类排序</label>
                                            <div class="col-md-4">
                                              <input type="number" min="1" value="1" class="form-control" id="add_input_ent_sub_sort"  placeholder="分类排序">
                                            </div>
                                             <label class="control-label col-md-3" for="add_input_ent_mid_code">对象中类代码</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_ent_mid_code" placeholder="对象中类代码">
                                            <span class="text-danger">*</span>
                                             </div>
                                          </div>
                                          

								</div>
               		 </div>
				</div>
				<div class="modal-footer">
<!-- 					<button type="button" class="btn btn-primary">确定</button> --><!-- data-opt-type="" new/edit -->
					<button type="button" id="btn_save_ent_sub" data-opt-type="" class="btn btn-success" data-loading-text="Loading" onclick="saveEntMidConfirm(this);">保&nbsp;&nbsp;存</button>
					<button type="button" class="btn btn-default" data-dismiss="modal" aria-hidden="true">关&nbsp;&nbsp;闭</button>

				</div>
			</div>
		</div>
	</div>

	<!-- Modal add sub obj cfg -->
	<div id="modal_add_ent_map_obj" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content widget ">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title" id="modal_add_ent_map_obj_title"></h4>
				</div>
				<div class="alert opt-result-alert"></div>
				<div class="modal-body modal-scrollable" id="modal_add_ent_map_obj_content">

				 <div class="padd">
                 	 <div class="form-horizontal">
                 	 <input  type="hidden" id="add_input_ent_map_obj_id" value="" >
                          					<div class="form-group">
<!--                                             <label class="control-label col-md-3" for="add_input_ent_sub_code">对象小类代码</label> -->
<!--                                             <div class="col-md-3"> -->
<!--                                               <input type="text" class="form-control required-input" id="add_input_ent_sub_code" placeholder="对象小类代码"> -->
<!--                                               <span class="text-danger" >*</span> -->
<!--                                             </div> -->
                                            <label class="control-label col-md-3" for="add_input_ent_map_obj_code">对象小类代码</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_ent_map_obj_code" placeholder="对象小类代码">
                                              <span class="text-danger">*</span>
                                            </div>
                                            <label class="control-label col-md-3" for="add_input_ent_map_obj_name">对象小类名称</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_ent_map_obj_name" placeholder="对象小类名称">
                                              <span class="text-danger">*</span>
                                            </div>
                                          </div>   
                                          <!-- 
                                          <div class="form-group">
                                            <label class="control-label col-md-3" for="add_input_ent_sub_desc">对象小类说明</label>
                                            <div class="col-md-8">
                                              <input type="text" class="form-control" id="add_input_ent_sub_desc"  placeholder="对象小类说明">
                                            </div>
                                          </div>
                                          <div class="form-group">
                                            <label class="control-label col-md-3" for="add_input_ent_sub_sort">对象小类排序</label>
                                            <div class="col-md-4">
                                              <input type="number" min="1" value="1" class="form-control" id="add_input_ent_sub_sort"  placeholder="分类排序">
                                            </div>
                                             <label class="control-label col-md-3" for="add_input_ent_mid_code">对象中类代码</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_ent_mid_code" placeholder="对象中类代码">
                                            <span class="text-danger">*</span>
                                             </div>
                                          </div>
                                          -->
                                          <div class="form-group"><center><hr class="modal-hr"/></center>
                                            <div class="panel-body" id="ent_map_obj_option_div">
                                            <!-- 
                                            <p>对应检查项目小类列表</p>
											-->
											</div>
								</div>
               		 </div>
				</div>
				</div>
				<div class="modal-footer">
<!-- 					<button type="button" class="btn btn-primary">确定</button> --><!-- data-opt-type="" new/edit -->
					<button type="button" id="btn_save_ent_map_obj" data-opt-type="" class="btn btn-success" data-loading-text="Loading" onclick="saveSubEntMapSubObj(this);">保&nbsp;&nbsp;存</button>
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
<script src="<%=path%>/js/busi-js/query_ent_sub_list.js?v=<%=staticVersion%>" type="text/javascript"></script>

<!-- Script for this page -->
<script type="text/javascript">
	//初始化预查询
	var initMidCode=getUrlParam("MID_CODE");//接收从一级页面某个一级分类，跳转过来，直接查询其对应二级代码
	if(initMidCode!=null){
		$("#qr_input_ent_mid_code").val(initMidCode);
		//缺省一级代码，便于批量添加该一级代码对应的对象小类
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
		var objName = $("#qr_input_ent_name").val();
		var objCode = $("#qr_input_ent_code").val();
		var supCode = $("#qr_input_ent_mid_code").val();
		var includeStop=(true==$("#qr_cb_include_stop").is(':checked'))?"1":"0";
		
		//格式：列映射关系
		var aoColumns = '[{"mDataProp" : "ENT_SUB_ID","sTitle" : "序号"}, '
					   + '{"mDataProp" : "CHECK_ENT_CODE","sTitle" : "对象小类代码"},'
					   + '{"mDataProp" : "CHECK_ENT_F_CODE","sTitle" : "对象中类"},'
					   + '{"mDataProp" : "CHECK_ENT_NAME","sTitle" : "对象小类名称"},'
					   + '{"mDataProp" : "CHECK_ENT_DESC","sTitle" : "对象小类说明"},'
					   + '{"mDataProp" : "ENT_SUB_ID","sTitle" : "创建信息"},'
					   + '{"mDataProp" : "FREEZE_TAG","sTitle" : "状态"},'
					   + '{"mDataProp" : "ENT_SUB_ID","sTitle" : "操作"}]';

		var reqData = {
			action : 'BUSI_DATA_ENT_SUB_QUERY_LIST_ACTION',
			params : 'params',
			ENT_NAME:objName,
			ENT_CODE:objCode,
			MID_CODE:supCode,
			INCLUDE_STOP:includeStop
			};
		//example为table定义id ：10：需要加入操作按钮的列；[4,5]：需要转义的列（映射数据字典值）
		initEntSubTable('entSubList', aoColumns, reqData, '<%=path%>',7,[6]);
		resetIFrameLength();//need ajax async:false,
		} 
	/*title tips- common method,dynamic bind*/
	$(function () { $("[data-toggle='tooltip']").tooltip();});
	
	//opt全选 改为动态绑定
	$('#ent_map_obj_option_div').on('click',":checkbox[value='all']",function(){
		console.log($(this).parent().parent().find(":checkbox").size());
		$(this).parent().parent().find(":checkbox").prop("checked",this.checked); 
	});
	
	function addEntMid(){
		myModalInit('modal_add_ent_sub');
		activeModalInput('modal_add_ent_sub');
		//btn show
		$('#btn_save_ent_sub').show();
		$('#btn_save_ent_sub').attr('data-opt-type','NEW');
		
		if(initMidCode!=null||initMidCode!=undefined){
			$('#add_input_ent_mid_code').val(initMidCode);//自动初始化
		}
		
		$('#modal_add_ent_sub_title').text('新增对象小类');

		$('#modal_add_ent_sub').modal({backdrop: 'static', keyboard: false});
	}
	
	/*对象小类配置项目小类*/
	function subEntMapSubObj(entCode,entId,entName){
		myModalInit('modal_add_ent_map_obj');
		
		actionOptionReset();
		
		activeModalInput('modal_add_ent_map_obj');
		//
		$('#add_input_ent_map_obj_id').val(entId);
		$('#add_input_ent_map_obj_code').val(entCode);
		$('#add_input_ent_map_obj_name').val(entName);
		
		$('#add_input_ent_map_obj_code').attr('readonly','');
		$('#add_input_ent_map_obj_name').attr('readonly','');
		
		//btn show
		$('#btn_save_ent_map_obj').show();
		$('#btn_save_ent_map_obj').attr('data-opt-type','NEW');
		
		$('#modal_add_ent_map_obj_title').text('配置检查对象['+entCode+']对应的检查项目三级类');
		$('#ent_map_obj_option_div').html('<p>对应检查项目小类列表</p>');//初始化
		
		//动态获取sub opt 内容
		//ajax begin
		var reqUrl='<%=path%>/AjaxChannel?action=QUERY_SUB_OBJ_OPT_ACTION';

		$.ajax({
				type : 'POST',
				url:reqUrl,
				data: {ENT_SUB_CODE:entCode},
				dataType : 'json',
				success : function(json) {
					if(json.SUCCESS=='1'){
							returnSuccessMsgShow('modal_add_ent_map_obj',json.MSG)
// 							var fvTable=$("#entSubList").dataTable(); //datatable init current
// 							fvTable.fnDraw(false);
// 							resetIFrameLength();//need ajax async:false,
							
							$('#ent_map_obj_option_div').html('<p>对应检查项目小类列表</p>'+json.SUB_OPT_HTML);

// 							$(obj).button('reset');
						}else{
// 							alert(json.MSG)	
							returnErrorMsgShow('modal_add_ent_map_obj',json.MSG)	
// 							$(obj).button('reset');
						}
					},
				error : function(e) {
					console.log(e);
					}
				});
		
		$('#modal_add_ent_map_obj').modal({backdrop: 'static', keyboard: false});
	}
	//保存检查对象子类对应的检查项目子类
	function saveSubEntMapSubObj(obj){
		//
		var subObjArray=[];
		$('#ent_map_obj_option_div').find(":checked:not([value='all'])").each(function(index,item){
			subObjArray.push($(item).attr('value'));
		});
		var subObjStr='';
		if(subObjArray!=null&&subObjArray.length>0){
			subObjStr=subObjArray.toString();//"xxx,xxx"
		}
// 		alert('TODO');
		reqUrl='<%=path%>/AjaxChannel?action=SAVE_SUB_ENT_SUB_OBJ_MAP_ACTION';//TODO
		var subEntCode=$('#add_input_ent_map_obj_code').val();
		$(obj).button('loading');
		//ajax begin
		$.ajax({
				type : 'POST',
				url:reqUrl,
				data: {ENT_SUB_CODE:subEntCode,SUB_OBJ_STR:subObjStr},
				dataType : 'json',
				success : function(json) {
					if(json.SUCCESS=='1'){
							returnSuccessMsgShow('modal_add_ent_map_obj',json.MSG)
// 							var fvTable=$("#roleList").dataTable(); //datatable init current
// 							fvTable.fnDraw(false);
							$(obj).button('reset');
						}else{
// 							alert(json.MSG)	
							returnErrorMsgShow('modal_add_ent_map_obj',json.MSG)	
							$(obj).button('reset');
						}
					},
				error : function(e) {
					console.log(e);
					}
				});
	}
	
	
	function saveEntMidConfirm(obj){
		var modalOptType=$(obj).attr('data-opt-type');//new or eidt

		var ent_sub_name=$('#add_input_ent_sub_name').val();
		var ent_sub_desc=$('#add_input_ent_sub_desc').val();
		var ent_sub_code=$('#add_input_ent_sub_code').val();
		var ent_sub_id=$('#add_input_ent_sub_id').val();
		var ent_sub_sort=$('#add_input_ent_sub_sort').val();
		
		var ent_mid_code=$('#add_input_ent_mid_code').val();
		
		initMidCode=ent_mid_code;//将上次所需一级代码存为默认代码		
		var reqUrl;
		if('NEW'==modalOptType){
			reqUrl='<%=path%>/AjaxChannel?action=BUSI_DATA_ENT_SUB_ADD_ACTION';
		}
		else if('EDIT'==modalOptType){
			reqUrl='<%=path%>/AjaxChannel?action=BUSI_DATA_ENT_SUB_EDIT_ACTION';
		}
		else{
			returnErrorMsgShow('modal_add_ent_sub','未知操作类型，请稍后重试')
			return false;
		}
		
		$(obj).button('loading');
		//ajax begin
		$.ajax({
				type : 'POST',
				url:reqUrl,
				data: {ENT_SUB_NAME:ent_sub_name,ENT_SUB_DESC:ent_sub_desc,ENT_SUB_ID:ent_sub_id,
					ENT_SUB_CODE:ent_sub_code,ENT_SUB_SORT:ent_sub_sort,ENT_MID_CODE:ent_mid_code},
				dataType : 'json',
				success : function(json) {
					if(json.SUCCESS=='1'){
							returnSuccessMsgShow('modal_add_ent_sub',json.MSG)
							var fvTable=$("#entSubList").dataTable(); //datatable init current
							fvTable.fnDraw(false);
							resetIFrameLength();//need ajax async:false,
							$(obj).button('reset');
						}else{
// 							alert(json.MSG)	
							returnErrorMsgShow('modal_add_ent_sub',json.MSG)	
							$(obj).button('reset');
						}
					},
				error : function(e) {
					console.log(e);
					}
				});
	}
	
	function detail(ent_sub_id){
		myModalInit('modal_add_ent_sub');
		//input readOnly
		readOnlyModalInput('modal_add_ent_sub');
		
		//btn hide
		$('#btn_save_ent_sub').hide();
		$('#btn_save_ent_sub').attr('data-opt-type','QUERY');
		
		var reqUrl='<%=path%>/AjaxChannel?action=BUSI_DATA_ENT_SUB_DETAIL_ACTION';
		//ajax begin
		$.ajax({
				type : 'POST',
				url:reqUrl,
				data: {ENT_SUB_ID:ent_sub_id},
				dataType : 'json',
				success : function(json) {
					if(json.SUCCESS=='1'){
							$('#add_input_ent_sub_code').val(json.ENT_SUB_BEAN.CheckEntCode);
							$('#add_input_ent_sub_name').val(json.ENT_SUB_BEAN.CheckEntName);
							$('#add_input_ent_sub_desc').val(json.ENT_SUB_BEAN.CheckEntDesc);
							$('#add_input_ent_sub_id').val(json.ENT_SUB_BEAN.Id);
							$('#add_input_ent_sub_sort').val(json.ENT_SUB_BEAN.Sort);
							$('#add_input_ent_mid_code').val(json.ENT_SUB_BEAN.CheckEntFCode);
// 							returnSuccessMsgShow('modal_add_ent_sub',json.MSG)
							$('#modal_add_ent_sub_title').text('对象小类明细['+json.ENT_SUB_BEAN.CheckEntCode+']');
						}else{
							returnErrorMsgShow('modal_add_ent_sub',json.MSG)	
						}
					},
				error : function(e) {
					console.log(e);
					}
				});
		//ajax end
// 		$('#modal_add_ent_sub_title').text('对象小类明细['+$('#add_input_ent_sub_name').val()+']'); //EMPTY
		$('#modal_add_ent_sub').modal({backdrop: 'static', keyboard: false});
		
	}
	
	//open modal
	function editEntMid(ent_sub_id){
// 		init
		myModalInit('modal_add_ent_sub');
		activeModalInput('modal_add_ent_sub');
		$('#add_input_ent_sub_code').attr('readonly','');
		
		//btn show
		$('#btn_save_ent_sub').show();
		$('#btn_save_ent_sub').attr('data-opt-type','EDIT');
		
		var reqUrl='<%=path%>/AjaxChannel?action=BUSI_DATA_ENT_SUB_DETAIL_ACTION';
		//ajax begin
		$.ajax({
				type : 'POST',
				url:reqUrl,
				data: {ENT_SUB_ID:ent_sub_id},
				dataType : 'json',
				success : function(json) {
					if(json.SUCCESS=='1'){
						$('#add_input_ent_sub_code').val(json.ENT_SUB_BEAN.CheckEntCode);
						$('#add_input_ent_sub_name').val(json.ENT_SUB_BEAN.CheckEntName);
						$('#add_input_ent_sub_desc').val(json.ENT_SUB_BEAN.CheckEntDesc);
						$('#add_input_ent_sub_id').val(json.ENT_SUB_BEAN.Id);
						$('#add_input_ent_sub_sort').val(json.ENT_SUB_BEAN.Sort);
						$('#add_input_ent_mid_code').val(json.ENT_SUB_BEAN.CheckEntFCode);
						
						$('#modal_add_ent_sub_title').text('对象小类编辑['+json.ENT_SUB_BEAN.CheckEntCode+']');
						
						}else{
// 							alert(json.MSG)	
							returnErrorMsgShow('modal_add_ent_sub',json.MSG)	
						}
					},
				error : function(e) {
					console.log(e);
					}
				});
		//ajax end
		$('#modal_add_ent_sub').modal({backdrop: 'static', keyboard: false});
	}
	
	/*opt confirm*/
	function optConfirm(opt_type,ent_sub_name,ent_sub_id){
		//auto top and height
// 		modal_auto_top($('div.modal-dialog'));

// 		$('#optModal').find("button.btn-primary:first").button('reset');
// 		clearMsgShow('optModal');
//		init common
		myModalInit('optModal');
		
		var title;
		var contentHtml;
		
		$("#opt_confirm_ent_sub_id").val(ent_sub_id);
		$("#opt_confirm_ent_sub_name").val(ent_sub_name);
		$("#opt_confirm_opt_type").val(opt_type);
		
		if("delete"==opt_type){
			title="删除对象小类["+ent_sub_name+"]";
			
			contentHtml=
				"<div class='alert alert-danger' role='alert'>"
					+"<span class='glyphicon glyphicon-exclamation-sign' aria-hidden='true'></span> <span class='sr-only'>注意:</span>"
					+"<strong>注意:</strong>对象小类["+ent_sub_name+"]将彻底删除，无法恢复 !"
				+"</div>";
// 			$('#optModal').find("button.btn-primary:first").click(startRole);

		}else if("stop"==opt_type)	{
			title="停用对象小类["+ent_sub_name+"]";
			
			contentHtml=
				"<div class='alert alert-warning' role='alert'>"
					+"<span class='glyphicon glyphicon-question-sign' aria-hidden='true'></span> <span class='sr-only'>注意:</span>"
					+"<strong>注意:</strong>对象小类["+ent_sub_name+"]将停用，可在对象小类管理恢复。"
				+"</div>";
// 			$('#optModal').find("button.btn-primary:first").click(stopRole);
		}else if("start"==opt_type){
			title="启用对象小类["+ent_sub_name+"]";
			
			contentHtml=
				"<div class='alert alert-info' role='alert'>"
					+"<strong>注意:</strong><span class='glyphicon glyphicon-info-sign' aria-hidden='true'></span> <span class='sr-only'>注意:</span>"
					+"对象小类["+ent_sub_name+"]将被启用。"
				+"</div>";
// 			$('#optModal').find("button.btn-primary:first").click(deleteRole);
		}
		$('#optModal').find("h4.modal-title:first").text(title);
		$('#optModal').find("div.modal-body:first").html(contentHtml);
		$('#optModal').modal({backdrop: 'static', keyboard: false});
	}
	
	function optConfirmDone(obj){
		var entSubId=$("#opt_confirm_ent_sub_id").val();
		var entSubName=$("#opt_confirm_ent_sub_name").val();
		var optType=$("#opt_confirm_opt_type").val();
		
		$(obj).button('loading');
		var reqUrl='<%=path%>/AjaxChannel?action=BUSI_DATA_ENT_SUB_MOD_ACTION';
		//ajax begin
		$.ajax({
				type : 'POST',
				url:reqUrl,
				data: {ENT_SUB_ID:entSubId,OPT_TYPE:optType},
				dataType : 'json',
				success : function(json) {
					if(json.SUCCESS=='1'){
							returnSuccessMsgShow('optModal',json.MSG);//alert msg
// 							$(obj).button('complete');//button 
							var fvTable=$("#entSubList").dataTable(); //datatable init current
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
	//ul li a click
	function displayActionByRoleCode(obj){
		if($(obj).parent().hasClass("active")){
			return false;
		}
		var roleCode=$(obj).attr("data-ent_mid_code");
		console.log(roleCode);
		$(obj).parent().parent().children("li").removeClass("active");
		$(obj).parent().addClass("active");
		//display action list
		$(obj).parent().parent().parent().children("div.action-checkbox-div").hide();
		$(obj).parent().parent().parent().children("#ACTION_DIV_"+roleCode).show();
// 		$(obj).parent().parent().parent().children("div.action-checkbox-div").hide(400);
// 		$(obj).parent().parent().parent().children("#ACTION_DIV_"+roleCode).show(200);
	}
	
	function actionOptionReset(){ //action 复位
		$('#ent_map_obj_option').find('a[data-ent_mid_code]:first').click();
// 		$('#role_action_option').find('a[data-role_code]').first().click();
	}
	


</script>

</body>
</html>