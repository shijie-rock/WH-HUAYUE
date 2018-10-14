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
        <h2 class="pull-left"><i class="icon-user"></i> 发布公告管理</h2>

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
        			<label class="col-sm-5 control-label">公告内容</label>
         			<div class="col-sm-7"><input type="text" class="form-control" placeholder="公告内容" id="qr_input_comm_msg_content"></div>
        		</div>
        		
        		<div class="form-group col-sm-3">
        			<label class="col-sm-5 control-label">公告级别</label>
        			<div class="col-sm-7">
         				<yb:select dataSource="DATA_DIC.COM_MSG_LEVEL"  selectClass="form-control subWidth"  includeNull="true" selectId="qr_input_comm_msg_level"/>
        			</div>
        		</div>
        		

				<div class="form-group col-sm-3">
					<label class="checkbox-inline"> <input type="checkbox" id="qr_cb_include_stop" value="1"> 包含已停用</label>
				</div>
							<!-- 查询按钮 begin -->
        		 <div class="form-group col-sm-3">
						<button type="button" class="btn btn-success" id="qr_bt_query_comm_msg" onclick="dataTableInit();">查询</button>
						&nbsp;
						<button type="button" class="btn btn-danger clear" id="qr_bt_clear_comm_msg" onclick="btnQueryReset();">清空</button>
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
                  <div class="pull-left">查询结果 &nbsp;&nbsp;&nbsp;<button type="button" class="btn btn-primary btn-xs" href="javascript:void(0);" onclick="addCommMsg();" id="bt_add_comm_msg">新增公告</button></div>
                  <div class="widget-icons pull-right">
                    <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a> 
                    <a href="#" class="wclose"><i class="icon-remove"></i></a>
                  </div>  
                  <div class="clearfix"></div>
                </div>

                  <div class="widget-content">
                  
                    <table id="CommMsgList"  class="table table-striped table-bordered table-hover">
                      <thead>
                        <tr>
                          <th width="5%"></th>
                          <th width="17%"></th>
                          <th width="38%"></th>
                          <th width="15%"></th>
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
		<input  type="hidden" id="opt_confirm_comm_msg_id" value="" >
		<input  type="hidden" id="opt_confirm_comm_msg_code" value="" >
		<input  type="hidden" id="opt_confirm_comm_msg_name" value="" >
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
	
	<!-- Modal add ins group -->
	<div id="modal_add_comm_msg" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content widget ">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title" id="modal_add_comm_msg_title"></h4>
				</div>
				<div class="alert opt-result-alert"></div>
				<div class="modal-body modal-scrollable" id="modal_add_comm_msg_content">

				 <div class="padd">
                 	 <div class="form-horizontal">
                 	 <input  type="hidden" id="add_input_comm_msg_id" value="" >
                          					<div class="form-group">
                                            <label class="control-label col-md-3" for="add_input_comm_msg_name">公告标题</label>
                                            <div class="col-md-5">
                                              <input type="text" class="form-control required-input" id="add_input_comm_msg_name" placeholder="公告标题">
                                              <span class="text-danger">*</span>
                                            </div>
                                          </div>   
                                          <!--  -->
                                          <div class="form-group">
                                            <label class="control-label col-md-3" for="add_input_comm_msg_desc">公告摘要</label>
                                            <div class="col-md-8">
                                              <input type="text" class="form-control required-input" id="add_input_comm_msg_desc"  placeholder="公告摘要">
                                              <span class="text-danger">*</span>
                                            </div>
                                          </div>
                                          <div class="form-group">
                                            <label class="control-label col-md-3" for="add_input_ins_check_order_desc">公告内容</label>
                                            <div class="col-md-8">
                                              <textarea  class="form-control required-input" id="add_input_comm_msg_content"  placeholder="公告内容" rows="2"></textarea>
                                            </div>
                                          </div>
                                          <div class="form-group">
                                            <label class="control-label col-md-3" for="add_input_comm_msg_level_type">公告级别</label>
                                            <div class="col-md-4">
												<yb:select dataSource="DATA_DIC.COM_MSG_LEVEL"  selectClass="form-control subWidth required-input"  selectId="add_input_comm_msg_level_type"/>
                                            </div>
                                            <label class="control-label col-md-3" for="add_input_comm_msg_pub_tag">是否发布</label>
                                            <div class="col-md-4">
				                                <label class="radio-inline"> <input type="radio" name="add_input_comm_msg_pub_tag" id="add_input_comm_msg_pub_tag_false" value="PUB_0010" checked> 否</label> 
												<label class="radio-inline"> <input type="radio" name="add_input_comm_msg_pub_tag" id="add_input_comm_msg_pub_tag_true" value="PUB_0020"> 是</label>
                                            </div>
                                          </div> 
                                          <div id="add_input_comm_msg_pub_info_div" style="display: none;">
	                                          <div class="form-group">
	                                            <label class="control-label col-md-3" for="add_input_comm_msg_pub_member_name">发布人</label>
	                                            <div class="col-md-4">
	                                             	<input type="text" class="form-control required-input" id="add_input_comm_msg_pub_member_name" placeholder="发布员">
	                                            </div>
	                                            <label class="control-label col-md-3" for="add_input_comm_msg_pub_time">发布时间</label>
	                                            	<div class="col-md-4">
	                                            <input type="text" class="form-control required-input" id="add_input_comm_msg_pub_time" placeholder="发布时间">
	                                            </div>
	                                          </div> 
	                                          <div class="form-group">
	                                            <label class="control-label col-md-3" for="add_input_comm_msg_create_member_name">创建人</label>
	                                            <div class="col-md-4">
	                                             	<input type="text" class="form-control required-input" id="add_input_comm_msg_create_member_name" placeholder="创建人">
	                                            </div>
	                                            <label class="control-label col-md-3" for="add_input_comm_msg_create_time">创建时间</label>
	                                            <div class="col-md-4">
	                                            	<input type="text" class="form-control required-input" id="add_input_comm_msg_create_time" placeholder="创建时间">
	                                            </div>
	                                          </div> 
                                          </div>
								</div>
               		 </div>
				</div>
				<div class="modal-footer">
<!-- 					<button type="button" class="btn btn-primary">确定</button> --><!-- data-opt-type="" new/edit -->
					<button type="button" id="btn_save_comm_msg" data-opt-type="" class="btn btn-success" data-loading-text="Loading" onclick="saveCommMsgConfirm(this);">保&nbsp;&nbsp;存</button>
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
<script src="<%=path%>/js/busi-js/query_comm_msg_list.js?v=<%=staticVersion%>" type="text/javascript"></script>

<!-- Script for this page -->
<script type="text/javascript">

//data dic
var map=parseData2Map('<yb:dataDic dataDicType="COM_MSG_LEVEL"/>');
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
		var msgContent = $("#qr_input_comm_msg_content").val();
		var msgLevel = $("#qr_input_comm_msg_level").val();
// 		var CommMsgCode = $("#qr_input_comm_msg_code").val();
		var includeStop=(true==$("#qr_cb_include_stop").is(':checked'))?"1":"0";
		
		//格式：列映射关系
		var aoColumns = '[{"mDataProp" : "ID","sTitle" : "序号"}, '
// 					   + '{"mDataProp" : "comm_msg_CODE","sTitle" : "公告代码"},'
					   + '{"mDataProp" : "COMM_MSG_TITLE","sTitle" : "标题摘要"},'
					   + '{"mDataProp" : "COMM_MSG_CONTENT","sTitle" : "公告内容"},'
					   + '{"mDataProp" : "PUBLISH_TAG","sTitle" : "发布状态"},'
					   + '{"mDataProp" : "FREEZE_TAG","sTitle" : "状态"},'
					   + '{"mDataProp" : "ID","sTitle" : "操作"}]';

		var reqData = {
			action : 'MESSAGE_COMM_MSG_QUERY_LIST_ACTION',
			params : 'params',
			COMM_MSG_CONTENT:msgContent,
			COM_MSG_LEVEL:msgLevel,
			INCLUDE_STOP:includeStop
			};
		//example为table定义id ：10：需要加入操作按钮的列；[4,5]：需要转义的列（映射数据字典值）
		initCommMsgTable('CommMsgList', aoColumns, reqData, '<%=path%>',5,[5]);
		resetIFrameLength();//need ajax async:false,
		} 
	/*title tips- common method,dynamic bind*/
	$(function () { $("[data-toggle='tooltip']").tooltip();});
	

	
	function addCommMsg(){
		myModalInit('modal_add_comm_msg');
		activeModalInput('modal_add_comm_msg');
		//check box,select init
		$('#add_input_comm_msg_pub_tag_false').prop('checked','checked');
		$('#add_input_comm_msg_level_type').val('CML_0030');
		
		//pub info hide
		$('#add_input_comm_msg_pub_info_div').hide();
		//btn show
		$('#btn_save_comm_msg').show();
		$('#btn_save_comm_msg').attr('data-opt-type','NEW');
		
		//
		
		$('#modal_add_comm_msg_title').text('新增公告');
		$('#modal_add_comm_msg').modal({backdrop: 'static', keyboard: false});
		
	}
	
	function saveCommMsgConfirm(obj){
		var modalOptType=$(obj).attr('data-opt-type');//new or eidt

		var comm_msg_name=$('#add_input_comm_msg_name').val();//标题
		var comm_msg_desc=$('#add_input_comm_msg_desc').val();//摘要
		var comm_msg_content=$('#add_input_comm_msg_content').val();//内容
		var comm_msg_level=$('#add_input_comm_msg_level_type').val();//级别
		var pub_tag=$("input[name='add_input_comm_msg_pub_tag']:checked").val()=="PUB_0020"?"1":"0";//是否发布
		var comm_msg_id=$('#add_input_comm_msg_id').val();
		
		var reqUrl;
		if('NEW'==modalOptType){
			reqUrl='<%=path%>/AjaxChannel?action=MESSAGE_COMM_MSG_ADD_ACTION';
		}
		else if('EDIT'==modalOptType){
			reqUrl='<%=path%>/AjaxChannel?action=MESSAGE_COMM_MSG_EDIT_ACTION';
		}
		else{
			returnErrorMsgShow('modal_add_comm_msg','未知操作类型，请稍后重试')
			return false;
		}
		
		$(obj).button('loading');
		//ajax begin
		$.ajax({
				type : 'POST',
				url:reqUrl,
				data: {
						   COMM_MSG_TITLE:comm_msg_name,
						   COMM_MSG_SUB_TITLE:comm_msg_desc,
						   COMM_MSG_ID:comm_msg_id,
						   COMM_MSG_CONTENT:comm_msg_content,
						   PUBLISH_TAG:pub_tag,
						   COMM_MSG_LEVEL:comm_msg_level
					   },
				dataType : 'json',
				success : function(json) {
					if(json.SUCCESS=='1'){
							returnSuccessMsgShow('modal_add_comm_msg',json.MSG)
							var fvTable=$("#CommMsgList").dataTable(); //datatable init current
							fvTable.fnDraw(false);
							$(obj).button('reset');
						}else{
// 							alert(json.MSG)	
							returnErrorMsgShow('modal_add_comm_msg',json.MSG)	
							$(obj).button('reset');
						}
					},
				error : function(e) {
					console.log(e);
					}
				});
	}
	
	function detail(comm_msg_id){
		myModalInit('modal_add_comm_msg');
		//input readOnly
		readOnlyModalInput('modal_add_comm_msg');
		//init radio
		$('#add_input_comm_msg_pub_tag_false').prop('checked','checked'); //PUB_0010
		
		//pub info show
		$('#add_input_comm_msg_pub_info_div').show();
		
		//btn hide
		$('#btn_save_comm_msg').hide();
		$('#btn_save_comm_msg').attr('data-opt-type','QUERY');
		
		var reqUrl='<%=path%>/AjaxChannel?action=MESSAGE_COMM_MSG_DETAIL_ACTION';
		//ajax begin
		$.ajax({
				type : 'POST',
				url:reqUrl,
				data: {COMM_MSG_ID:comm_msg_id},
				dataType : 'json',
				success : function(json) {
					if(json.SUCCESS=='1'){
// 							$('#add_input_role_code').val(json.ROLE_BEAN.RoleCode);
							$('#add_input_comm_msg_name').val(json.COMM_MSG_BEAN.COMM_MSG_TITLE);
							$('#add_input_comm_msg_desc').val(json.COMM_MSG_BEAN.COMM_MSG_SUB_TITLE);
							$('#add_input_comm_msg_content').val(json.COMM_MSG_BEAN.COMM_MSG_CONTENT);
							$('#add_input_comm_msg_level_type').val(json.COMM_MSG_BEAN.COM_MSG_LEVEL);
							$('#add_input_comm_msg_id').val(json.COMM_MSG_BEAN.ID);
							//create and publish 
							$('#add_input_comm_msg_pub_member_name').val(json.COMM_MSG_BEAN.PUB_MEM_NAME);
							$('#add_input_comm_msg_pub_time').val(json.COMM_MSG_BEAN.PUB_TIME_STR);
							$('#add_input_comm_msg_create_member_name').val(json.COMM_MSG_BEAN.CREATE_MEM_NAME);
							$('#add_input_comm_msg_create_time').val(json.COMM_MSG_BEAN.CREATE_TIME_STR);
							if('1'==json.COMM_MSG_BEAN.PUBLISH_TAG){
								$('#add_input_comm_msg_pub_tag_true').prop('checked','checked'); //PUB_0020
							}
							
// 							returnSuccessMsgShow('modal_add_comm_msg',json.MSG)
							$('#modal_add_comm_msg_title').text('公告明细['+json.COMM_MSG_BEAN.COMM_MSG_TITLE+']');
						}else{
							returnErrorMsgShow('modal_add_comm_msg',json.MSG)	
						}
					},
				error : function(e) {
					console.log(e);
					}
				});
		//ajax end
// 		$('#modal_add_comm_msg_title').text('公告明细['+$('#add_input_comm_msg_name').val()+']'); //EMPTY
		$('#modal_add_comm_msg').modal({backdrop: 'static', keyboard: false});
		
	}
	
	//open modal
	function editCommMsg(comm_msg_id){
// 		init
		myModalInit('modal_add_comm_msg');
		activeModalInput('modal_add_comm_msg');
		//init radio
		$('#add_input_comm_msg_pub_tag_false').prop('checked','checked'); //PUB_0010
		
		//disable raido
		$('#modal_add_comm_msg').find(":radio").attr('disabled','disabled');
		
		//pub info hide
		$('#add_input_comm_msg_pub_info_div').hide();
		
		//btn show
		$('#btn_save_comm_msg').show();
		$('#btn_save_comm_msg').attr('data-opt-type','EDIT');
		
		var reqUrl='<%=path%>/AjaxChannel?action=MESSAGE_COMM_MSG_DETAIL_ACTION';
		//ajax begin
		$.ajax({
				type : 'POST',
				url:reqUrl,
				data: {COMM_MSG_ID:comm_msg_id},
				dataType : 'json',
				success : function(json) {
					if(json.SUCCESS=='1'){
//							$('#add_input_role_code').val(json.ROLE_BEAN.RoleCode);
						$('#add_input_comm_msg_name').val(json.COMM_MSG_BEAN.COMM_MSG_TITLE);
						$('#add_input_comm_msg_desc').val(json.COMM_MSG_BEAN.COMM_MSG_SUB_TITLE);
						$('#add_input_comm_msg_content').val(json.COMM_MSG_BEAN.COMM_MSG_CONTENT);
						$('#add_input_comm_msg_level_type').val(json.COMM_MSG_BEAN.COM_MSG_LEVEL);
						$('#add_input_comm_msg_id').val(json.COMM_MSG_BEAN.ID);
						//create and publish 
						$('#add_input_comm_msg_pub_member_name').val(json.COMM_MSG_BEAN.PUB_MEM_NAME);
						$('#add_input_comm_msg_pub_time').val(json.COMM_MSG_BEAN.PUB_TIME_STR);
						$('#add_input_comm_msg_create_member_name').val(json.COMM_MSG_BEAN.CREATE_MEM_NAME);
						$('#add_input_comm_msg_create_time').val(json.COMM_MSG_BEAN.CREATE_TIME_STR);
						if('1'==json.COMM_MSG_BEAN.PUBLISH_TAG){
							$('#add_input_comm_msg_pub_tag_true').prop('checked','checked'); //PUB_0020
						}
						
//							returnSuccessMsgShow('modal_add_comm_msg',json.MSG)
						$('#modal_add_comm_msg_title').text('公告编辑['+json.COMM_MSG_BEAN.COMM_MSG_TITLE+']');
						}else{
// 							alert(json.MSG)	
							returnErrorMsgShow('modal_add_role',json.MSG)	
						}
					},
				error : function(e) {
					console.log(e);
					}
				});
		//ajax end
		$('#modal_add_comm_msg').modal({backdrop: 'static', keyboard: false});
	}
	
	/*opt confirm*/
	function optConfirm(opt_type,comm_msg_name,comm_msg_id){
		//auto top and height
// 		modal_auto_top($('div.modal-dialog'));

// 		$('#optModal').find("button.btn-primary:first").button('reset');
// 		clearMsgShow('optModal');
//		init common
		myModalInit('optModal');
		
		var title;
		var contentHtml;
		
		$("#opt_confirm_comm_msg_id").val(comm_msg_id);
		$("#opt_confirm_comm_msg_name").val(comm_msg_name);
		$("#opt_confirm_opt_type").val(opt_type);
		
		if("delete"==opt_type){
			title="删除公告["+comm_msg_name+"]";
			
			contentHtml=
				"<div class='alert alert-danger' role='alert'>"
					+"<span class='glyphicon glyphicon-exclamation-sign' aria-hidden='true'></span> <span class='sr-only'>注意:</span>"
					+"<strong>注意:</strong>公告["+comm_msg_name+"]将彻底删除，无法恢复 !"
				+"</div>";
// 			$('#optModal').find("button.btn-primary:first").click(startRole);

		}else if("stop"==opt_type)	{
			title="停用公告["+comm_msg_name+"]";
			
			contentHtml=
				"<div class='alert alert-warning' role='alert'>"
					+"<span class='glyphicon glyphicon-question-sign' aria-hidden='true'></span> <span class='sr-only'>注意:</span>"
					+"<strong>注意:</strong>公告["+comm_msg_name+"]将停用，可在公告管理恢复。"
				+"</div>";
// 			$('#optModal').find("button.btn-primary:first").click(stopRole);
		}else if("start"==opt_type){
			title="启用公告["+comm_msg_name+"]";
			
			contentHtml=
				"<div class='alert alert-info' role='alert'>"
					+"<strong>注意:</strong><span class='glyphicon glyphicon-info-sign' aria-hidden='true'></span> <span class='sr-only'>注意:</span>"
					+"公告["+comm_msg_name+"]将被启用。"
				+"</div>";
// 			$('#optModal').find("button.btn-primary:first").click(deleteRole);
		}
		else if("pub"==opt_type){
			title="发布公告["+comm_msg_name+"]";
			
			contentHtml=
				"<div class='alert alert-info' role='alert'>"
					+"<strong>注意:</strong><span class='glyphicon glyphicon-info-sign' aria-hidden='true'></span> <span class='sr-only'>注意:</span>"
					+"公告["+comm_msg_name+"]将被发布。"
				+"</div>";
		}
		else if("cal"==opt_type){
			title="取消发布公告["+comm_msg_name+"]";
			
			contentHtml=
				"<div class='alert alert-danger' role='alert'>"
					+"<strong>注意:</strong><span class='glyphicon glyphicon-info-sign' aria-hidden='true'></span> <span class='sr-only'>注意:</span>"
					+"公告["+comm_msg_name+"]将被取消发布。"
				+"</div>";
		}
		$('#optModal').find("h4.modal-title:first").text(title);
		$('#optModal').find("div.modal-body:first").html(contentHtml);
		$('#optModal').modal({backdrop: 'static', keyboard: false});
	}
	
	function optConfirmDone(obj){
		var CommMsgId=$("#opt_confirm_comm_msg_id").val();
		var CommMsgName=$("#opt_confirm_comm_msg_name").val();
		var optType=$("#opt_confirm_opt_type").val();
		
		$(obj).button('loading');
		var reqUrl='<%=path%>/AjaxChannel?action=MESSAGE_COMM_MSG_MOD_ACTION';
		//ajax begin
		$.ajax({
				type : 'POST',
				url:reqUrl,
				data: {COMM_MSG_ID:CommMsgId,OPT_TYPE:optType},
				dataType : 'json',
				success : function(json) {
					if(json.SUCCESS=='1'){
							returnSuccessMsgShow('optModal',json.MSG);//alert msg
// 							$(obj).button('complete');//button 
							var fvTable=$("#CommMsgList").dataTable(); //datatable init current
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

	

</script>

</body>
</html>