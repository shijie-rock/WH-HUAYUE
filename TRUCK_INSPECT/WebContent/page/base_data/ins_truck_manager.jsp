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
        <h2 class="pull-left"><i class="icon-user"></i> 车辆管理</h2>

        <!-- Breadcrumb -->
        <div class="bread-crumb pull-right">
          <a href="javascript:void(0);" onclick="packUpDown(this);" ><i class="glyphicon glyphicon-menu-left"></i><span>展开</span></a> 
          |&nbsp;<a href="<%=path%>/page/index-content.jsp"><i class="icon-home"></i>控制台</a> 
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
        			<label class="col-sm-5 control-label">车牌</label>
         			<div class="col-sm-7"><input type="text" class="form-control" placeholder="车牌" id="qr_input_truck_license"></div>
        		</div>
        
       			<div class="form-group col-sm-3">
        			<label class="col-sm-5 control-label">车型</label>
         			<div class="col-sm-7"><input type="text" class="form-control" placeholder="车型" id="qr_input_truck_type"></div>
        		</div>
       			<div class="form-group col-sm-3">
        			<label class="col-sm-5 control-label">车辆状态</label>
         			<div class="col-sm-7"><input type="text" class="form-control" placeholder="车辆状态" id="qr_input_truck_status"></div>
        		</div>

				<div class="form-group col-sm-3">
					<label class="checkbox-inline"> <input type="checkbox" id="qr_cb_include_stop" value="1"> 包含已停用</label>
						&nbsp;	&nbsp;
					<button type="button" class="btn btn-success" id="qr_bt_query_ins_truck" onclick="dataTableInit();">查询</button>
						&nbsp;
					<button type="button" class="btn btn-danger clear" id="qr_bt_clear_ins_truck" onclick="btnQueryReset();">清空</button>
				</div>
				<!-- 查询按钮 begin -->
<!--         		 <div class="form-group col-sm-4"> -->

<!--          		 </div> -->
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
                  <div class="pull-left">查询结果 &nbsp;&nbsp;&nbsp;<button type="button" class="btn btn-primary btn-xs" href="javascript:void(0);" onclick="addMember();" id="bt_add_member">新增车辆</button></div>
                  <div class="widget-icons pull-right">
                    <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a> 
                    <a href="#" class="wclose"><i class="icon-remove"></i></a>
                  </div>  
                  <div class="clearfix"></div>
                </div>

                  <div class="widget-content">
                  
                    <table id="insTruckList"  class="table table-striped table-bordered table-hover">
                      <thead>
                        <tr>
                          <th width="5%"></th>
                          <th width="10%"></th>
                          <th width="10%"></th>
                          <th width="10%"></th>
                          <th width="10%"></th>
                          <th width="10%"></th>
                          <th width="10%"></th>
                          <th width="10%"></th>
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
		<input  type="hidden" id="opt_confirm_ins_truck_id" value="" >
		<input  type="hidden" id="opt_confirm_truck_license" value="" >
		<input  type="hidden" id="opt_confirm_truck_type" value="" >
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
	
	<!-- Modal add member -->
	<div id="modal_add_ins_truck" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content widget ">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title" id="modal_add_ins_truck_title"></h4>
				</div>
				<div class="alert opt-result-alert"></div>
				<div class="modal-body modal-scrollable" id="modal_add_ins_truck_content">

				 <div class="padd">
                 	 <div class="form-horizontal">
                 	 <input  type="hidden" id="add_input_ins_truck_id" value="" >
                          					<div class="form-group">
                                            <label class="control-label col-md-3" for="add_input_truck_license">车辆代码</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_truck_license" placeholder="车辆代码">
                                              <span class="text-danger" >*</span>
                                            </div>
                                            <label class="control-label col-md-3" for="add_input_truck_type">车辆姓名</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_truck_type" placeholder="车辆名称">
                                              <span class="text-danger">*</span>
                                            </div>
                                          </div> 
                                          
                                          <div class="form-group">
                                            <label class="control-label col-md-3" for="add_input_member_password">车辆密码</label>
                                            <div class="col-md-4">
                                              <input type="password" class="form-control required-input" id="add_input_member_password">
                                              <span class="text-danger" >*</span>
                                            </div>
                                            <label class="control-label col-md-3" for="add_input_truck_status">车辆手机</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_truck_status" placeholder="车辆手机">
<!--                                               <span class="text-danger">*</span> -->
                                            </div>
                                          </div>  
                                            
                          				  <div class="form-group">
                                            <label class="control-label col-md-3" for="add_input_member_password_confirm">确认密码</label>
                                            <div class="col-md-4">
                                              <input type="password" class="form-control required-input" id="add_input_member_password_confirm">
                                              <span class="text-danger" >*</span>
                                            </div>
                                            <label class="control-label col-md-3" for="add_input_email">车辆邮箱</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_email" placeholder="车辆邮箱">
<!--                                               <span class="text-danger">*</span> -->
                                            </div>
                                          </div>   
                                          
                                          <!-- Select box -->
                                          <div class="form-group">
                                            <label class="control-label col-md-3">职务</label>
                                            <div class="col-md-4" id="add_input_job">                               
<!--                                                 <select class="form-control" style="width:93%"> -->
<!--                                                 <option>&nbsp;</option> -->
<!--                                                 <option>1</option> -->
<!--                                                 <option>2</option> -->
<!--                                                 </select>   -->
                                            <yb:select dataSource="DATA_DIC.POSITION_TYPE"  selectClass="form-control subWidth"  includeNull="true" selectId="add_input_job_select"/>
                                            </div>

			                               <label class="control-label col-md-3" for="add_input_sex">车辆性别</label>
			                                <div class="col-md-4">
			                                <label class="radio-inline"> <input type="radio" name="add_input_sex" id="add_input_sex_m" value="SEX_0010" checked> 男</label> 
											<label class="radio-inline"> <input type="radio" name="add_input_sex" id="add_input_sex_f" value="SEX_0020"> 女</label>
			                             	</div>
										</div>
										
										 <div class="form-group">
                                            <label class="control-label col-md-3" for="add_input_cert_no">身份证号</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_cert_no" placeholder="身份证号">
                                            </div>
                                            <label class="control-label col-md-3" for="">出生日期</label>
                                           <div class="date form_date col-md-4"  
                                                data-date=""  data-date-format="yyyy-mm-dd" 
                                                data-link-field="add_input_birthday" data-link-format="yyyy-mm-dd">
                                                <span id="testView"></span>
									            <input class="form-control required-input layer-date" size="16" type="text" value="" id="add_input_birthday"  placeholder="出生日期" readonly style="display:none;">
									            <input class="form-control required-input" size="16" type="text" value="" id="add_input_birthday_query" placeholder="出生日期" readonly>
									        </div>
<!-- 													<input type="hidden" id="add_input_birthday" value="" /><br/> -->
                                          </div>  
                                          <!--  -->
                                          <div class="form-group">
                                            <label class="control-label col-md-3" for="add_input_member_desc">车辆说明</label>
                                            <div class="col-md-8">
                                              <input type="text" class="form-control" id="add_input_member_desc"  placeholder="车辆说明">
                                            </div>
                                          </div>
                                          
                                          <div class="form-group">
                                         		 <label class="control-label col-md-3" >车辆设置</label>
                                         		 <div class="col-md-8">
													<label class="checkbox-inline col-md-3"> <input type="checkbox" id="add_input_is_login_sys" value="1">操作员</label>
													<label class="checkbox-inline col-md-3"> <input type="checkbox" id="add_input_is_inspactor" value="1">检查员</label>
<!-- 												<label class="checkbox-inline col-md-3"> <input type="checkbox" id="qr_cb_include_stop" value="1">检查员</label> -->
                                         		 </div>
                                          </div>

										
<!-- 										<div class="form-group"><center><hr class="modal-hr"/></center> -->
<!-- 											<div class="panel-body" id="role_action_option_div"><p>角色对应菜单列表</p> -->
<%-- 												<yb:funActOptHtml ulId="role_action_option"></yb:funActOptHtml> --%>
<!-- 											</div> -->
<!-- 								         </div>   -->
										
								</div>
               			 </div>
					</div>
				<div class="modal-footer">
					<button type="button" id="btn_save_ins_truck" data-opt-type="" class="btn btn-success" data-loading-text="Loading" onclick="saveInsTruckConfirm(this);">保&nbsp;&nbsp;存</button>
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
<script src="<%=path%>/js/laydate.js?v=<%=staticVersion%>"></script> <!-- laydate.js -->

<!-- table收起 -->
<script src="<%=path%>/js/truck-inspect-common.js?v=<%=staticVersion%>"></script> <!-- Custom codes -->
<script src="<%=path%>/js/data-tables/jquery.dataTables.min.js?v=<%=staticVersion%>" type="text/javascript"></script>
<script src="<%=path%>/js/data-tables/dataTables.bootstrap.min.js?v=<%=staticVersion%>"	type="text/javascript"></script>
<script src="<%=path%>/js/moment.min.js?v=<%=staticVersion%>" type="text/javascript"></script>

<script src="<%=path%>/js/busi-js/query_ins_truck_list.js?v=<%=staticVersion%>" type="text/javascript"></script>

<!-- Script for this page -->
<script type="text/javascript">
//data dic
var map=parseData2Map('<yb:dataDic dataDicType="POSITION_TYPE,SEX"/>');
function dicTranse(value){
	return map[value]==null?value:map[value];
}
//data dic end
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
		var truck_type = $("#qr_input_truck_type").val();
		var truck_status  = $("#qr_input_truck_status").val();
		var truck_license = $("#qr_input_truck_license").val();
		var includeStop=(true==$("#qr_cb_include_stop").is(':checked'))?"1":"0";
		
		//格式：列映射关系
		var aoColumns = '[{"mDataProp" : "MEMBER_ID","sTitle" : "序号"},'
					   + '{"mDataProp" : "MEMBER_CODE","sTitle" : "代码/姓名"},'
					   + '{"mDataProp" : "MOBILE","sTitle" : "手机/邮箱"},'
					   + '{"mDataProp" : "JOB_TITEL_TYPE","sTitle" : "职位"},'
					   + '{"mDataProp" : "MEMBER_ROLE","sTitle" : "角色"},'
					   + '{"mDataProp" : "IS_INSPACTOR","sTitle" : "操作/检车"},'
					   + '{"mDataProp" : "MEMBER_INS_GROUP","sTitle" : "检查组"},'
					   + '{"mDataProp" : "MEMBER_REMARK","sTitle" : "备注"},'
					   + '{"mDataProp" : "FREEZE_TAG","sTitle" : "状态"},'
					   + '{"mDataProp" : "MEMBER_ID","sTitle" : "操作"}]';

		var reqData = {
			action : 'BASE_DATA_INS_TRUCK_LIST_QUERY_ACTION',
			params : 'params',
			MEMBER_NAME:memberName,
			MOBILE:mobile,
			MEMBER_CODE:memberCode,
			INCLUDE_STOP:includeStop
			};
		//example为table定义id ：10：需要加入操作按钮的列；[4,5]：需要转义的列（映射数据字典值）
		initMemberTable('insTruckList', aoColumns, reqData, '<%=path%>',9,[]);
		resetIFrameLength();//need ajax async:false,
		} 
	/*title tips- common method,dynamic bind*/
	$(function () { $("[data-toggle='tooltip']").tooltip();});
	
// 	$('#add_input_birthday').datetimepicker({
// 		language:'zh-CN',
//         format:'yyyy-mm-dd',
//         weekStart: 1,
//         todayBtn:  1,
// 		autoclose: 1,
// 		todayHighlight: 1,
// 		startView: 2,
// 		minView: 2,
// 		forceParse: 0
//     });

//  	$(function() {
//  	  $("#add_input_birthday").datepicker({
//  		 showButtonPanel:true,
//  		 dateFormat: "yy-mm-dd",
//  		 changeMonth: true,
//  		 changeYear: true,
//  		 maxDate: 0 
//  	  });
//   	  $("#add_input_birthday").datepicker("option", "showAnim","slideDown");
//  	 });

		//执行一个laydate实例
		laydate.render({
		  elem: '#add_input_birthday', //指定元素
		  max:0,
// 		  theme: 'molv',
		  theme: '#6699CC'
// 		  theme: '#393D49'
// 		  theme: 'grid'
// 		  position: 'absolute'
// 		  fixed: true
// 		  change: function(value, date){ //监听日期被切换
// 			    lay('#testView').html(value);
// 		  		alert('');
// 			}
		});

	function addMember() {
		myModalInit('modal_add_ins_truck');
		activeModalInput('modal_add_ins_truck');
		
		$('#add_input_birthday_query').hide();
		$('#add_input_birthday').show();
		
		//btn show
		$('#btn_save_ins_truck').show();
		$('#btn_save_ins_truck').attr('data-opt-type', 'NEW');

		$('#modal_add_ins_truck_title').text('新增车辆');
		$('#modal_add_ins_truck').modal({
			backdrop : 'static',
			keyboard : false
		});

	}

	function saveInsTruckConfirm(obj) {
		var modalOptType = $(obj).attr('data-opt-type');//new or eidt

		var truck_type = $('#add_input_truck_type').val();
		var member_desc = $('#add_input_member_desc').val();
		var member_id = $('#add_input_ins_truck_id').val();
		
		var data={};
		data.MEMBER_NAME=truck_type;
		data.MEMBER_DESC=member_desc;
		data.MEMBER_MOBILE=$('#add_input_truck_status').val();
		data.MEMBER_EMAIL=$('#add_input_email').val();
		data.MEMBER_JOB=$('#add_input_job_select').val();
		data.MEMBER_SEX=$('input:radio[name="add_input_sex"]:checked').val();//TODO
// 		data.MEMBER_SEX=$('#add_input_job').val();//TODO
		data.MEMBER_CERT_NO=$('#add_input_cert_no').val();
		data.MEMBER_BIRTH_DAY=$('#add_input_birthday').val();
		data.IS_LOGIN_SYS=($('#add_input_is_login_sys').prop('checked'))?"1":"0";//
		data.IS_INSPACTOR=($('#add_input_is_inspactor').prop('checked'))?"1":"0";//
		data.PASSWORD_CFG=$('#add_input_member_password_confirm').val();
		data.PASSWORD=$('#add_input_member_password').val();
		data.MEMBER_CODE=$('#add_input_truck_license').val();

		var reqUrl;
		if ('NEW' == modalOptType) {
			reqUrl = '<%=path%>/AjaxChannel?action=BASE_DATA_INS_TRUCK_ADD_ACTION';
		}
		else if('EDIT'==modalOptType){
			reqUrl='<%=path%>/AjaxChannel?action=BASE_DATA_INS_TRUCK_EDIT_ACTION';
			data.MEMBER_ID=member_id;
		}
		else{
			returnErrorMsgShow('modal_add_ins_truck','未知操作类型，请稍后重试')
			return false;
		}
		
		$(obj).button('loading');
		//ajax begin
		$.ajax({
				type : 'POST',
				url:reqUrl,
				data: data,
// 				data: {MEMBER_NAME:truck_type,MEMBER_DESC:member_desc,MEMBER_ID:member_id},
				dataType : 'json',
				success : function(json) {
					if(json.SUCCESS=='1'){
							returnSuccessMsgShow('modal_add_ins_truck',json.MSG)
							var fvTable=$("#insTruckList").dataTable(); //datatable init current
							fvTable.fnDraw(false);
							$(obj).button('reset');
						}else{
// 							alert(json.MSG)	
							returnErrorMsgShow('modal_add_ins_truck',json.MSG)	
							$(obj).button('reset');
						}
					},
				error : function(e) {
					console.log(e);
					}
				});
	}
	
	//open query modal
	function detail(member_id){
		myModalInit('modal_add_ins_truck');
		//input readOnly
		readOnlyModalInput('modal_add_ins_truck');
		
		$('#add_input_birthday_query').show();
		$('#add_input_birthday').hide();
		
		//btn hide
		$('#btn_save_ins_truck').hide();
		$('#btn_save_ins_truck').attr('data-opt-type','QUERY');
		
		var reqUrl='<%=path%>/AjaxChannel?action=BASE_DATA_INS_TRUCK_DETAIL_ACTION';
		//ajax begin
		$.ajax({
				type : 'POST',
				url:reqUrl,
				data: {MEMBER_ID:member_id},
				dataType : 'json',
				success : function(json) {
					if(json.SUCCESS=='1'){
							$('#add_input_ins_truck_id').val(json.MEMBER_BEAN.Id);
// 							$('#add_input_role_code').val(json.ROLE_BEAN.RoleCode);
							$('#add_input_truck_type').val(json.MEMBER_BEAN.MemberName);
							$('#add_input_truck_license').val(json.MEMBER_BEAN.MemberCode);
							$('#add_input_truck_status').val(json.MEMBER_BEAN.Mobile);
// 							$('#add_input_member_password').val(json.MEMBER_BEAN.Password);
// 							$('#add_input_member_password_confirm').val(json.MEMBER_BEAN.Password);
							$('#add_input_email').val(json.MEMBER_BEAN.Email);
							$('#add_input_cert_no').val(json.MEMBER_BEAN.CertNo);
							if(json.MEMBER_BEAN.Birthday!=null){
								$('#add_input_birthday').val(moment(json.MEMBER_BEAN.Birthday).format('YYYY-MM-DD'));
								$('#add_input_birthday_query').val(moment(json.MEMBER_BEAN.Birthday).format('YYYY-MM-DD'));
							}
							$('#add_input_member_desc').val(json.MEMBER_BEAN.MemberRemark);
// 							$('#add_input_member_desc').val(json.MEMBER_BEAN.MemberDesc);
// 							$("input:radio[name='add_input_sex']").val(json.MEMBER_BEAN.Sex);
							$("input:radio[name='add_input_sex'][value='"+json.MEMBER_BEAN.Sex+"']").prop("checked", "checked");
							if("1"==json.MEMBER_BEAN.CanLoginSys)$("#add_input_is_login_sys").prop("checked", "checked");
							if("1"==json.MEMBER_BEAN.IsInspactor)$("#add_input_is_inspactor").prop("checked", "checked");
							$('#add_input_job_select').val(json.MEMBER_BEAN.JobTitelType);
// 							returnSuccessMsgShow('modal_add_ins_truck',json.MSG)
							$('#modal_add_ins_truck_title').text('车辆明细['+json.MEMBER_BEAN.MemberName+']');
							
						}else{
							returnErrorMsgShow('modal_add_ins_truck',json.MSG)	
						}
					},
				error : function(e) {
					console.log(e);
					}
				});
		//ajax end
// 		$('#modal_add_ins_truck_title').text('车辆明细['+$('#add_input_truck_type').val()+']'); //EMPTY
		$('#modal_add_ins_truck').modal({backdrop: 'static', keyboard: false});
		
	}
	
	//open edit modal
	function editMember(member_id){
// 		init
		myModalInit('modal_add_ins_truck');
		activeModalInput('modal_add_ins_truck');
		//member code readyonly
		$('#modal_add_ins_truck #add_input_truck_license').attr('readonly','');
		
		//birthday show
		$('#add_input_birthday_query').hide();
		$('#add_input_birthday').show();
		//btn show
		$('#btn_save_ins_truck').show();
		$('#btn_save_ins_truck').attr('data-opt-type','EDIT');
		
		var reqUrl='<%=path%>/AjaxChannel?action=BASE_DATA_INS_TRUCK_DETAIL_ACTION';
		//ajax begin
		$.ajax({
				type : 'POST',
				url:reqUrl,
				data: {MEMBER_ID:member_id},
				dataType : 'json',
				success : function(json) {
					if(json.SUCCESS=='1'){
						$('#add_input_ins_truck_id').val(json.MEMBER_BEAN.Id);
//						$('#add_input_role_code').val(json.ROLE_BEAN.RoleCode);
						$('#add_input_truck_type').val(json.MEMBER_BEAN.MemberName);
						$('#add_input_truck_license').val(json.MEMBER_BEAN.MemberCode);
						$('#add_input_truck_status').val(json.MEMBER_BEAN.Mobile);
//						$('#add_input_member_password').val(json.MEMBER_BEAN.Password);
//						$('#add_input_member_password_confirm').val(json.MEMBER_BEAN.Password);
						$('#add_input_email').val(json.MEMBER_BEAN.Email);
						$('#add_input_cert_no').val(json.MEMBER_BEAN.CertNo);
						if(json.MEMBER_BEAN.Birthday!=null){
							$('#add_input_birthday').val(moment(json.MEMBER_BEAN.Birthday).format('YYYY-MM-DD'));
							$('#add_input_birthday_query').val(moment(json.MEMBER_BEAN.Birthday).format('YYYY-MM-DD'));
						}
						$('#add_input_member_desc').val(json.MEMBER_BEAN.MemberRemark);
// 						$('#add_input_member_desc').val(json.MEMBER_BEAN.MemberDesc);
//						$("input:radio[name='add_input_sex']").val(json.MEMBER_BEAN.Sex);
						$("input:radio[name='add_input_sex'][value='"+json.MEMBER_BEAN.Sex+"']").prop("checked", "checked");
						if("1"==json.MEMBER_BEAN.CanLoginSys)$("#add_input_is_login_sys").prop("checked", "checked");
						if("1"==json.MEMBER_BEAN.IsInspactor)$("#add_input_is_inspactor").prop("checked", "checked");
						$('#add_input_job_select').val(json.MEMBER_BEAN.JobTitelType);
//							returnSuccessMsgShow('modal_add_ins_truck',json.MSG)
							
						$('#modal_add_ins_truck_title').text('车辆编辑['+json.MEMBER_BEAN.MemberName+']');
						
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
		$('#modal_add_ins_truck').modal({backdrop: 'static', keyboard: false});
	}
	
	/*opt confirm*/
	function optConfirm(opt_type,truck_type,member_id){
		//auto top and height
// 		modal_auto_top($('div.modal-dialog'));

// 		$('#optModal').find("button.btn-primary:first").button('reset');
// 		clearMsgShow('optModal');
//		init common
		myModalInit('optModal');
		
		var title;
		var contentHtml;
		
		$("#opt_confirm_ins_truck_id").val(member_id);
		$("#opt_confirm_truck_type").val(truck_type);
		$("#opt_confirm_opt_type").val(opt_type);
		
		if("delete"==opt_type){
			title="删除车辆["+truck_type+"]";
			
			contentHtml=
				"<div class='alert alert-danger' role='alert'>"
					+"<span class='glyphicon glyphicon-exclamation-sign' aria-hidden='true'></span> <span class='sr-only'>注意:</span>"
					+"<strong>注意:</strong>车辆["+truck_type+"]将彻底删除，无法恢复 !"
				+"</div>";
// 			$('#optModal').find("button.btn-primary:first").click(startRole);

		}else if("stop"==opt_type)	{
			title="停用车辆["+truck_type+"]";
			
			contentHtml=
				"<div class='alert alert-warning' role='alert'>"
					+"<span class='glyphicon glyphicon-question-sign' aria-hidden='true'></span> <span class='sr-only'>注意:</span>"
					+"<strong>注意:</strong>车辆["+truck_type+"]将停用，可在车辆管理恢复。"
				+"</div>";
// 			$('#optModal').find("button.btn-primary:first").click(stopRole);
		}else if("start"==opt_type){
			title="启用车辆["+truck_type+"]";
			
			contentHtml=
				"<div class='alert alert-info' role='alert'>"
					+"<strong>注意:</strong><span class='glyphicon glyphicon-info-sign' aria-hidden='true'></span> <span class='sr-only'>注意:</span>"
					+"车辆["+truck_type+"]将被启用。"
				+"</div>";
// 			$('#optModal').find("button.btn-primary:first").click(deleteRole);
		}
		$('#optModal').find("h4.modal-title:first").text(title);
		$('#optModal').find("div.modal-body:first").html(contentHtml);
		$('#optModal').modal({backdrop: 'static', keyboard: false});
	}
	
	function optConfirmDone(obj){
		var memberId=$("#opt_confirm_ins_truck_id").val();
		var memberName=$("#opt_confirm_truck_type").val();
		var optType=$("#opt_confirm_opt_type").val();
		
		$(obj).button('loading');
		var reqUrl='<%=path%>/AjaxChannel?action=BASE_DATA_INS_TRUCK_MOD_ACTION';
		//ajax begin
		$.ajax({
				type : 'POST',
				url:reqUrl,
				data: {MEMBER_ID:memberId,OPT_TYPE:optType},
				dataType : 'json',
				success : function(json) {
					if(json.SUCCESS=='1'){
							returnSuccessMsgShow('optModal',json.MSG);//alert msg
// 							$(obj).button('complete');//button 
							var fvTable=$("#insTruckList").dataTable(); //datatable init current
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
	}
	
	function optModPassword(memberName,memberId){
		myModalInit('modal_mod_password');
		activeModalInput('modal_mod_password');
		$('#add_input_mod_password_member_id').val(memberId);
		$('#btn_mod_password').show();
		$('#btn_mod_password').attr('data-opt-type','MOD_PASSWORD');
		$('#modal_mod_password_title').text('车辆['+memberName+']修改密码');
		
		$('#modal_mod_password').modal({backdrop: 'static', keyboard: false});
	}
	
	//returnErrorMsgShow('modal_add_ins_truck',json.MSG)	
	
	function savePassword(obj){
		var password=$('#add_input_mod_password').val();
		var passwordCfg=$('#add_input_mod_password_confirm').val();
		if(password==null||password==''){
			returnErrorMsgShow('modal_mod_password','密码不能为空');
			return;
		}
		if(passwordCfg==null||passwordCfg==''){
			returnErrorMsgShow('modal_mod_password','确认密码不能为空');
			return;
		}
		if(password!=passwordCfg){
			returnErrorMsgShow('modal_mod_password','新密码与确认密码不一致');
			return;
		}
		$(obj).button('loading');
		var reqUrl='<%=path%>/AjaxChannel?action=BASE_DATA_INS_TRUCK_MOD_PASSWORD_ACTION';
		
		var memberId=$('#add_input_mod_password_member_id').val();
		
		//ajax begin
		$.ajax({
				type : 'POST',
				url:reqUrl,
				data: {MEMBER_ID:memberId,PASSWORD:password,PASSWORD_CFG:passwordCfg},
				dataType : 'json',
				success : function(json) {
					if(json.SUCCESS=='1'){
							returnSuccessMsgShow('modal_mod_password',json.MSG);//alert msg
							var fvTable=$("#insTruckList").dataTable(); //datatable init current
// 							fvTable.fnDraw(false);
							$(obj).button('reset');
						}else{
							returnErrorMsgShow('modal_mod_password',json.MSG);
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