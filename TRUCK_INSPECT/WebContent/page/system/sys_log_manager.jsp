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
        <h2 class="pull-left"><i class="icon-zoom-in"></i> 操作日志管理</h2>

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

                <div class="widget-content query-condition" style="height:90px;">
                
       			<div class="form-group col-sm-3">
        			<label class="col-sm-5 control-label">操作人</label>
         			<div class="col-sm-7"><input type="text" class="form-control" placeholder="操作人名称" id="qr_input_log_opt_name"></div>
        		</div>
       			<div class="form-group col-sm-3">
        			<label class="col-sm-3 control-label">开始</label>
         			<div class="col-sm-9">
         			<input type="text" class="form-control layer-date" placeholder="开始时间" id="qr_input_log_begin_time"></div>
        		</div>
       			<div class="form-group col-sm-3">
        			<label class="col-sm-3 control-label">截止</label>
         			 <div class="date form_date col-md-9">
							<input type="text" class="form-control layer-date" placeholder="截止时间" id="qr_input_log_end_time"></div>
<!-- 						<input class="form-control required-input layer-date" size="16" type="text" value="" id="qr_input_log_end_time"  placeholder="截止时间"> -->
				</div>
				<div class="form-group col-sm-3">
        			<label class="col-sm-4 control-label">操作内容</label>
         			 <div class="date form_date col-md-8">
							<input type="text" class="form-control" placeholder="操作内容" id="qr_input_log_opt_content"></div>
<!-- 						<input class="form-control required-input layer-date" size="16" type="text" value="" id="qr_input_log_end_time"  placeholder="截止时间"> -->
				</div>
				<div class="row">
				<div class="form-group col-sm-10"></div>
        		 <div class="form-group col-sm-2" style="margin-top:-5px";>
						<button type="button" class="btn btn-success" id="qr_bt_query_sys_logs" onclick="dataTableInit();">查询</button>
						&nbsp;
						<button type="button" class="btn btn-danger clear" id="qr_bt_clear_sys_logs" onclick="btnQueryReset();">清空</button>
         		 </div>
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
                  <div class="pull-left">查询结果</div>
                  <div class="widget-icons pull-right">
                    <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a> 
                    <a href="#" class="wclose"><i class="icon-remove"></i></a>
                  </div>  
                  <div class="clearfix"></div>
                </div>

                  <div class="widget-content">
                  
                    <table id="sysOptLogsList"  class="table table-striped table-bordered table-hover">
                      <thead>
                        <tr>
                          <th width="5%"></th>
                          <th width="10%"></th>
                          <th width="20%"></th>
                          <th width="20%"></th>
                          <th width="25%"></th>
                          <th width="20%"></th>
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
		<input  type="hidden" id="opt_confirm_sys_logs_id" value="" >
		<input  type="hidden" id="opt_confirm_sys_logs_code" value="" >
		<input  type="hidden" id="opt_confirm_log_opt_name" value="" >
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
	<div id="modal_add_sys_logs" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content widget ">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title" id="modal_add_sys_logs_title"></h4>
				</div>
				<div class="alert opt-result-alert"></div>
				<div class="modal-body modal-scrollable" id="modal_add_sys_logs_content">

				 <div class="padd">
                 	 <div class="form-horizontal">
                 	 <input  type="hidden" id="add_input_sys_logs_id" value="" >
                          				<div class="form-group">
                                            <label class="control-label col-md-3" for="add_input_log_opt_name">操作人</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_log_opt_name" placeholder="操作人名称">
                                            </div>
                                            
                                            <label class="control-label col-md-3" for="add_input_log_opt_emp_id">操作人ID</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_log_opt_emp_id" placeholder="操作人ID">
                                            </div>
                                          </div>   
                                          <!--  -->
                                           <div class="form-group">
                                            <label class="control-label col-md-3" for="add_input_log_opt_action_code">操作代码</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_log_opt_action_code" placeholder="操作代码">
                                            </div>
                                            
                                            <label class="control-label col-md-3" for="add_input_log_opt_action_desc">操作说明</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_log_opt_action_desc" placeholder="操作说明">
                                            </div>
                                          </div>  
                                          <!--  -->
                                           <div class="form-group">
                                            <label class="control-label col-md-3" for="add_input_log_opt_content">操作内容</label>
                                            <div class="col-md-8">
                                              <input type="text" class="form-control" id="add_input_log_opt_content" placeholder="操作内容">
                                            </div>
                                          </div>   
                                           
                                          <div class="form-group">
                                            <label class="control-label col-md-3" for="add_input_log_opt_time">操作时间</label>
                                            <div class="col-md-8">
                                              <input type="text" class="form-control" id="add_input_log_opt_time"  placeholder="操作时间">
                                            </div>
                                          </div>
									</div>
               			 </div>
					</div>
				<div class="modal-footer">
<!-- 					<button type="button" class="btn btn-primary">确定</button> --><!-- data-opt-type="" new/edit -->
					<button type="button" id="btn_save_sys_logs" data-opt-type="" class="btn btn-success" data-loading-text="Loading" onclick="saveInsGroupConfirm(this);">保&nbsp;&nbsp;存</button>
					<button type="button" class="btn btn-default" data-dismiss="modal" aria-hidden="true">关&nbsp;&nbsp;闭</button>

				</div>
			</div>
		</div>
	</div>

	<!-- Modal add member -->
	<div id="modal_add_member" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content widget ">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title" id="modal_add_member_title"></h4>
				</div>
				<div class="alert opt-result-alert"></div>
				<div class="modal-body modal-scrollable" id="modal_add_member_content">

				 <div class="padd">
                 	 <div class="form-horizontal">
                 	 <input  type="hidden" id="add_input_member_id" value="" >
                          					<div class="form-group">
                                            <label class="control-label col-md-3" for="add_input_member_code">用户代码</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_member_code" placeholder="用户代码">
                                              <span class="text-danger" >*</span>
                                            </div>
                                            <label class="control-label col-md-3" for="add_input_member_name">用户姓名</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_member_name" placeholder="用户名称">
                                              <span class="text-danger">*</span>
                                            </div>
                                          </div> 
                                          
                                          <div class="form-group">

                                            <label class="control-label col-md-3" for="add_input_mobile">用户手机</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_mobile" placeholder="用户手机">
<!--                                               <span class="text-danger">*</span> -->
                                            </div>
                                            <label class="control-label col-md-3" for="add_input_email">用户邮箱</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_email" placeholder="用户邮箱">
<!--                                               <span class="text-danger">*</span> -->
                                            </div>
                                          </div>  
                                          
                                          <!-- Select box -->
                                          <div class="form-group">
                                            <label class="control-label col-md-3">职务</label>
                                            <div class="col-md-4" id="add_input_job">                               
                                            <yb:select dataSource="DATA_DIC.POSITION_TYPE"  selectClass="form-control subWidth"  includeNull="true" selectId="add_input_job_select"/>
                                            </div>

			                               <label class="control-label col-md-3" for="add_input_sex">用户性别</label>
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
                                            <label class="control-label col-md-3" for="add_input_member_desc">用户说明</label>
                                            <div class="col-md-8">
                                              <input type="text" class="form-control" id="add_input_member_desc"  placeholder="用户说明">
                                            </div>
                                          </div>
                                           <div class="form-group">
                                            <label class="control-label col-md-3" for="add_input_member_login_time">上次登录时间</label>
                                            <div class="col-md-8">
                                              <input type="text" class="form-control" id="add_input_member_login_time">
                                            </div>
                                          </div>  
                                          <div class="form-group">
                                         		 <label class="control-label col-md-3" >用户设置</label>
                                         		 <div class="col-md-8">
													<label class="checkbox-inline col-md-3"> <input type="checkbox" id="add_input_is_login_sys" value="1">操作员</label>
													<label class="checkbox-inline col-md-3"> <input type="checkbox" id="add_input_is_inspactor" value="1">检查员</label>
                                         		 </div>
                                          </div>
 
								</div>
               			 </div>
					</div>
				<div class="modal-footer">
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
<script src="<%=path%>/js/data-tables/jquery.dataTables.min.js?v=<%=staticVersion%>"	type="text/javascript"></script>
<script src="<%=path%>/js/data-tables/dataTables.bootstrap.min.js?v=<%=staticVersion%>"	type="text/javascript"></script>
<script src="<%=path%>/js/moment.min.js?v=<%=staticVersion%>" type="text/javascript"></script>
<script src="<%=path%>/js/busi-js/query_sys_logs_list.js?v=<%=staticVersion%>" type="text/javascript"></script>

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
// 		alert(checkDate());
		if(!checkDate()){
			alert('开始时间不能晚于截止时间。');
			return;
		}
		//param
		var opt_name = $("#qr_input_log_opt_name").val();
		var opt_content = $("#qr_input_log_opt_content").val();
		var begin_time = $("#qr_input_log_begin_time").val();
		var end_time = $("#qr_input_log_end_time").val();
		//格式：列映射关系
		var aoColumns = '[{"mDataProp" : "ID","sTitle" : "序号"}, '
					   + '{"mDataProp" : "OPT_NAME","sTitle" : "操作人"},'
					   + '{"mDataProp" : "OPT_ACTION_CODE","sTitle" : "操作代码"},'
					   + '{"mDataProp" : "OPT_ACTION_DESC","sTitle" : "操作说明"},'
					   + '{"mDataProp" : "OPT_CONTENT","sTitle" : "操作内容"},'
					   + '{"mDataProp" : "OPT_TIME","sTitle" : "操作时间"}]';

		var reqData = {
			action : 'SYS_OPT_LOGS_QUERY_LIST_ACTION',
			params : 'params',
			OPT_NAME:opt_name,
			OPT_CONTENT:opt_content,
			BEGIN_TIME:begin_time,
			END_TIME:end_time
			};
		//example为table定义id ：10：需要加入操作按钮的列；[4,5]：需要转义的列（映射数据字典值）
		initSysOptLogsTable('sysOptLogsList', aoColumns, reqData, '<%=path%>','',[5]);
		resetIFrameLength();//need ajax async:false,
		} 
	/*title tips- common method,dynamic bind*/
	$(function () { $("[data-toggle='tooltip']").tooltip();});
	
//	开始
	laydate.render({
	  elem: '#qr_input_log_begin_time', //指定元素
	  type:'datetime',
	  max:1,
	  position: 'absolute',
	  theme: '#6699CC',
	  done:checkDate()
	});
// 	结束
	laydate.render({
	  elem: '#qr_input_log_end_time', //指定元素
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
	
	function checkDate(){
		//开始时间不能晚于结束时间
		if($('#qr_input_log_begin_time').val()!=''&&$('#qr_input_log_end_time').val()!=''){
// 			alert($('#qr_input_log_begin_time').val());
// 			alert($('#qr_input_log_end_time').val());
			return $('#qr_input_log_begin_time').val()<$('#qr_input_log_end_time').val();
		}else 
		return true;
	}
	
	function detail(sys_logs_id){
		myModalInit('modal_add_sys_logs');
		//input readOnly
		readOnlyModalInput('modal_add_sys_logs');
		
		//btn hide
		$('#btn_save_sys_logs').hide();
		$('#btn_save_sys_logs').attr('data-opt-type','QUERY');
		
		var reqUrl='<%=path%>/AjaxChannel?action=SYS_OPT_LOGS_QUERY_DETAIL_ACTION';
		//ajax begin
		$.ajax({
				type : 'POST',
				url:reqUrl,
				data: {LOGS_ID:sys_logs_id},
				dataType : 'json',
				success : function(json) {
					if(json.SUCCESS=='1'){
							$('#add_input_log_opt_emp_id').val(json.OPT_LOGS_BEAN.OptMemberId);
							$('#add_input_log_opt_name').val(json.OPT_LOGS_BEAN.OptName);
							$('#add_input_log_opt_action_code').val(json.OPT_LOGS_BEAN.OptActionCode);
							$('#add_input_log_opt_action_desc').val(json.OPT_LOGS_BEAN.OptActionDesc);
							$('#add_input_sys_logs_id').val(json.OPT_LOGS_BEAN.Id);
							$('#add_input_log_opt_content').val(json.OPT_LOGS_BEAN.OptContent);
							$('#add_input_log_opt_time').val(json.OPT_LOGS_BEAN.OptTime);
// 							returnSuccessMsgShow('modal_add_sys_logs',json.MSG)
							$('#modal_add_sys_logs_title').text('日志明细]');
						}else{
							returnErrorMsgShow('modal_add_sys_logs',json.MSG)	
						}
					},
				error : function(e) {
					console.log(e);
					}
				});
		//ajax end
		$('#modal_add_sys_logs').modal({backdrop: 'static', keyboard: false});
		
	}
	//open query modal
	function detailEmp(member_id){
		myModalInit('modal_add_member');
		//input readOnly
		readOnlyModalInput('modal_add_member');
		
		$('#add_input_birthday_query').show();
		$('#add_input_birthday').hide();
		
		//btn hide
		$('#btn_save_member').hide();
		$('#btn_save_member').attr('data-opt-type','QUERY');
		
		var reqUrl='<%=path%>/AjaxChannel?action=ORGANIZE_MEMBER_DETAIL_ACTION';
		//ajax begin
		$.ajax({
				type : 'POST',
				url:reqUrl,
				data: {MEMBER_ID:member_id},
				dataType : 'json',
				success : function(json) {
					if(json.SUCCESS=='1'){
							$('#add_input_member_id').val(json.MEMBER_BEAN.Id);
// 							$('#add_input_role_code').val(json.ROLE_BEAN.RoleCode);
							$('#add_input_member_name').val(json.MEMBER_BEAN.MemberName);
							$('#add_input_member_code').val(json.MEMBER_BEAN.MemberCode);
							$('#add_input_mobile').val(json.MEMBER_BEAN.Mobile);
// 							$('#add_input_member_password').val(json.MEMBER_BEAN.Password);
// 							$('#add_input_member_password_confirm').val(json.MEMBER_BEAN.Password);
							$('#add_input_email').val(json.MEMBER_BEAN.Email);
							$('#add_input_cert_no').val(json.MEMBER_BEAN.CertNo);
							$('#add_input_member_login_time').val(json.MEMBER_BEAN.LastLoginTime);
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
// 							returnSuccessMsgShow('modal_add_member',json.MSG)
							$('#modal_add_member_title').text('用户明细['+json.MEMBER_BEAN.MemberName+']');
							
							
						}else{
							returnErrorMsgShow('modal_add_member',json.MSG)	
						}
					},
				error : function(e) {
					console.log(e);
					}
				});
		//ajax end
// 		$('#modal_add_member_title').text('用户明细['+$('#add_input_member_name').val()+']'); //EMPTY
		$('#modal_add_member').modal({backdrop: 'static', keyboard: false});
		
	}	
	//open modal
	function editInsGroup(sys_logs_id){
// 		init
		myModalInit('modal_add_sys_logs');
		activeModalInput('modal_add_sys_logs');
		
		//btn show
		$('#btn_save_sys_logs').show();
		$('#btn_save_sys_logs').attr('data-opt-type','EDIT');
		
		var reqUrl='<%=path%>/AjaxChannel?action=ORGANIZE_INS_GROUP_DETAIL_ACTION';
		//ajax begin
		$.ajax({
				type : 'POST',
				url:reqUrl,
				data: {INS_GROUP_ID:sys_logs_id},
				dataType : 'json',
				success : function(json) {
					if(json.SUCCESS=='1'){
						$('#add_input_log_opt_name').val(json.INS_GROUP_BEAN.InsGroupName);
						$('#add_input_sys_logs_desc').val(json.INS_GROUP_BEAN.InsGroupDesc);
						$('#add_input_sys_logs_id').val(json.INS_GROUP_BEAN.Id);
						$('#modal_add_sys_logs_title').text('检查组编辑['+json.INS_GROUP_BEAN.InsGroupName+']');
						
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
		$('#modal_add_sys_logs').modal({backdrop: 'static', keyboard: false});
	}
	
	/*opt confirm*/
	function optConfirm(opt_type,log_opt_name,sys_logs_id){
		//auto top and height
// 		modal_auto_top($('div.modal-dialog'));

// 		$('#optModal').find("button.btn-primary:first").button('reset');
// 		clearMsgShow('optModal');
//		init common
		myModalInit('optModal');
		
		var title;
		var contentHtml;
		
		$("#opt_confirm_sys_logs_id").val(sys_logs_id);
		$("#opt_confirm_log_opt_name").val(log_opt_name);
		$("#opt_confirm_opt_type").val(opt_type);
		
		if("delete"==opt_type){
			title="删除检查组["+log_opt_name+"]";
			
			contentHtml=
				"<div class='alert alert-danger' role='alert'>"
					+"<span class='glyphicon glyphicon-exclamation-sign' aria-hidden='true'></span> <span class='sr-only'>注意:</span>"
					+"<strong>注意:</strong>检查组["+log_opt_name+"]将彻底删除，无法恢复 !"
				+"</div>";
// 			$('#optModal').find("button.btn-primary:first").click(startRole);

		}else if("stop"==opt_type)	{
			title="停用检查组["+log_opt_name+"]";
			
			contentHtml=
				"<div class='alert alert-warning' role='alert'>"
					+"<span class='glyphicon glyphicon-question-sign' aria-hidden='true'></span> <span class='sr-only'>注意:</span>"
					+"<strong>注意:</strong>检查组["+log_opt_name+"]将停用，可在检查组管理恢复。"
				+"</div>";
// 			$('#optModal').find("button.btn-primary:first").click(stopRole);
		}else if("start"==opt_type){
			title="启用检查组["+log_opt_name+"]";
			
			contentHtml=
				"<div class='alert alert-info' role='alert'>"
					+"<strong>注意:</strong><span class='glyphicon glyphicon-info-sign' aria-hidden='true'></span> <span class='sr-only'>注意:</span>"
					+"检查组["+log_opt_name+"]将被启用。"
				+"</div>";
// 			$('#optModal').find("button.btn-primary:first").click(deleteRole);
		}
		$('#optModal').find("h4.modal-title:first").text(title);
		$('#optModal').find("div.modal-body:first").html(contentHtml);
		$('#optModal').modal({backdrop: 'static', keyboard: false});
	}
	
	function optConfirmDone(obj){
		var insGroupId=$("#opt_confirm_sys_logs_id").val();
		var insGroupName=$("#opt_confirm_log_opt_name").val();
		var optType=$("#opt_confirm_opt_type").val();
		
		$(obj).button('loading');
		var reqUrl='<%=path%>/AjaxChannel?action=ORGANIZE_INS_GROUP_MOD_ACTION';
		//ajax begin
		$.ajax({
				type : 'POST',
				url:reqUrl,
				data: {INS_GROUP_ID:insGroupId,OPT_TYPE:optType},
				dataType : 'json',
				success : function(json) {
					if(json.SUCCESS=='1'){
							returnSuccessMsgShow('optModal',json.MSG);//alert msg
// 							$(obj).button('complete');//button 
							var fvTable=$("#sysOptLogsList").dataTable(); //datatable init current
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