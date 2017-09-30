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
        <h2 class="pull-left"><i class="icon-user"></i> 角色管理</h2>

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
        			<label class="col-sm-5 control-label">角色代码</label>
         			<div class="col-sm-7"><input type="text" class="form-control" placeholder="角色代码" id="qr_input_role_code"></div>
        		</div>
        
       			<div class="form-group col-sm-3">
        			<label class="col-sm-5 control-label">角色名称</label>
         			<div class="col-sm-7"><input type="text" class="form-control" placeholder="角色名称" id="qr_input_role_name"></div>
        		</div>
        		


				<div class="form-group col-sm-3">
					<label class="checkbox-inline"> <input type="checkbox" id="qr_cb_include_stop" value="1"> 包含已停用</label>
<!-- 				<label class="checkbox-inline"> <input type="checkbox" id="qr_cb_include_stop" value="1" checked="checked"> 包含已停用</label> -->
				</div>
							<!-- 查询按钮 begin -->
        		 <div class="form-group col-sm-3">
						<button type="button" class="btn btn-success" id="qr_bt_query_role" onclick="dataTableInit();">查询</button>
						&nbsp;
						<button type="button" class="btn btn-danger clear" id="qr_bt_clear_role" onclick="btnQueryReset();">清空</button>
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
                  <div class="pull-left">查询结果 &nbsp;&nbsp;&nbsp;<button type="button" class="btn btn-primary btn-xs" href="javascript:void(0);" onclick="addRole();" id="bt_add_role">新增角色</button></div>
<!--              <div class="pull-left">查询结果</div> -->
                  <div class="widget-icons pull-right">
                    <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a> 
                    <a href="#" class="wclose"><i class="icon-remove"></i></a>
                  </div>  
                  <div class="clearfix"></div>
                </div>

                  <div class="widget-content">
                  
                    <table id="roleList"  class="table table-striped table-bordered table-hover">
                      <thead>
                        <tr>
                          <th width="5%"></th>
                          <th width="10%"></th>
                          <th width="10%"></th>
                          <th width="30%"></th>
                          <th width="20%"></th>
                          <th width="25%"></th>
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



                <div class="widget">

                <div class="widget-head">
                  <div class="pull-left">Tables</div>
                  <div class="widget-icons pull-right">
                    <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a> 
                    <a href="#" class="wclose"><i class="icon-remove"></i></a>
                  </div>  
                  <div class="clearfix"></div>
                </div>

                  <div class="widget-content">

                    <table class="table table-striped table-bordered table-hover">
                      <thead>
                        <tr>
                          <th>#</th>
                          <th>Name</th>
                          <th>Location</th>
                          <th>Date</th>
                          <th>Type</th>
                          <th>Status</th>
                          <th>Control</th>
                        </tr>
                      </thead>
                      <tbody>

                        <tr>
                          <td>1</td>
                          <td>Ravi Kumar</td>
                          <td>India</td>
                          <td>23/12/2012</td>
                          <td>Paid</td>
                          <td><span class="label label-success">合&nbsp;&nbsp;&nbsp;&nbsp;格</span></td>
                          <td>

                              <button class="btn btn-xs btn-success"><i class="icon-ok"></i> </button>
                              <button class="btn btn-xs btn-warning"><i class="icon-pencil"></i> </button>
                              <button class="btn btn-xs btn-danger"><i class="icon-remove"></i> </button>
                          
                          </td>
                        </tr>


                        <tr>
                          <td>2</td>
                          <td>Parneethi Chopra</td>
                          <td>USA</td>
                          <td>13/02/2012</td>
                          <td>Free</td>
                          <td><span class="label label-danger">不合格</span></td>
                          <td>

                              <button class="btn btn-xs btn-default"><i class="icon-ok"></i> </button>
                              <button class="btn btn-xs btn-default"><i class="icon-pencil"></i> </button>
                              <button class="btn btn-xs btn-default"><i class="icon-remove"></i> </button>

                          </td>
                        </tr>

                        <tr>
                          <td>3</td>
                          <td>Kumar Ragu</td>
                          <td>Japan</td>
                          <td>12/03/2012</td>
                          <td>Paid</td>
                          <td><span class="label label-success">Active</span></td>
                          <td>

                            <div class="btn-group">
                              <button class="btn btn-xs btn-default"><i class="icon-ok"></i> </button>
                              <button class="btn btn-xs btn-default"><i class="icon-pencil"></i> </button>
                              <button class="btn btn-xs btn-default"><i class="icon-remove"></i> </button>
                            </div>

                          </td>
                        </tr>

                        <tr>
                          <td>4</td>
                          <td>Vishnu Vardan</td>
                          <td>Bangkok</td>
                          <td>03/11/2012</td>
                          <td>Paid</td>
                          <td><span class="label label-success">Active</span></td>
                          <td>

                            <div class="btn-group">
                              <button class="btn btn-xs btn-success"><i class="icon-ok"></i> </button>
                              <button class="btn btn-xs btn-warning"><i class="icon-pencil"></i> </button>
                              <button class="btn btn-xs btn-danger"><i class="icon-remove"></i> </button>
                            </div>

                          </td>
                        </tr>

                        <tr>
                          <td>5</td>
                          <td>Anuksha Sharma</td>
                          <td>Singapore</td>
                          <td>13/32/2012</td>
                          <td>Free</td>
                          <td><span class="label label-danger">Banned</span></td>
                          <td>

                            <div class="btn-group1">
                              <button class="btn btn-xs btn-success"><i class="icon-ok"></i> </button>
                              <button class="btn btn-xs btn-warning"><i class="icon-pencil"></i> </button>
                              <button class="btn btn-xs btn-danger"><i class="icon-remove"></i> </button>
                            </div>

                          </td>
                        </tr>                                                            

                      </tbody>
                    </table>

                    <div class="widget-foot">

                     
                        <ul class="pagination pull-right">
                          <li><a href="#">Prev</a></li>
                          <li><a href="#">1</a></li>
                          <li><a href="#">2</a></li>
                          <li><a href="#">3</a></li>
                          <li><a href="#">4</a></li>
                          <li><a href="#">Next</a></li>
                        </ul>
                     
                      <div class="clearfix"></div> 

                    </div>
                  </div>
                </div>
              </div>
            </div>


            <div class="row">

              <div class="col-md-6">
                <div class="widget">

                <div class="widget-head">
                  <div class="pull-left">Tables</div>
                  <div class="widget-icons pull-right">
                    <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a> 
                    <a href="#" class="wclose"><i class="icon-remove"></i></a>
                  </div>  
                  <div class="clearfix"></div>
                </div>

                  <div class="widget-content">

                    <table class="table table-striped table-bordered table-hover">
                      <thead>
                        <tr>
                          <th>#</th>
                          <th>Name</th>
                          <th>Location</th>
                          <th>Type</th>
                          <th>Status</th>
                        </tr>
                      </thead>
                      <tbody>

                        <tr>
                          <td>1</td>
                          <td>Ravi Kumar</td>
                          <td>India</td>
                          <td>Paid</td>
                          <td><span class="label label-success">合格</span></td>

                        </tr>


                        <tr>
                          <td>2</td>
                          <td>Parneethi Chopra</td>
                          <td>USA</td>
                          <td>Free</td>
                          <td><span class="label label-danger">不合格</span></td>

                        </tr>

                        <tr>
                          <td>3</td>
                          <td>Kumar Ragu</td>
                          <td>Japan</td>
                          <td>Paid</td>
                          <td><span class="label label-success">Active</span></td>

                        </tr>

                        <tr>
                          <td>4</td>
                          <td>Vishnu Vardan</td>
                          <td>Bangkok</td>
                          <td>Paid</td>
                          <td><span class="label label-success">Active</span></td>

                        </tr>

                        <tr>
                          <td>5</td>
                          <td>Anuksha Sharma</td>
                          <td>Singapore</td>
                          <td>Free</td>
                          <td><span class="label label-danger">Banned</span></td>
      
                        </tr>                                                            

                      </tbody>
                    </table>

                    <div class="widget-foot">

                     
                        <ul class="pagination pull-right">
                          <li><a href="#">Prev</a></li>
                          <li><a href="#">1</a></li>
                          <li><a href="#">2</a></li>
                          <li><a href="#">3</a></li>
                          <li><a href="#">4</a></li>
                          <li><a href="#">Next</a></li>
                        </ul>
                     
                      <div class="clearfix"></div> 

                    </div>

                  </div>

                </div>
              </div>

              <div class="col-md-6">

                <div class="widget">

                <div class="widget-head">
                  <div class="pull-left">Tables</div>
                  <div class="widget-icons pull-right">
                    <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a> 
                    <a href="#" class="wclose"><i class="icon-remove"></i></a>
                  </div>  
                  <div class="clearfix"></div>
                </div>

                  <div class="widget-content">

                    <table class="table table-striped table-bordered table-hover">
                      <thead>
                        <tr>
                          <th>#</th>
                          <th>Name</th>
                          <th>Location</th>
                          <th>Date</th>
                          <th>Type</th>
                        </tr>
                      </thead>
                      <tbody>

                      </tbody>
                    </table>

                    <div class="widget-foot">

                      
                        <ul class="pagination pull-right">
                          <li><a href="#">Prev</a></li>
                          <li><a href="#">1</a></li>
                          <li><a href="#">2</a></li>
                          <li><a href="#">3</a></li>
                          <li><a href="#">4</a></li>
                          <li><a href="#">Next</a></li>
                        </ul>
                      
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
		<input  type="hidden" id="opt_confirm_role_code" value="" >
		<input  type="hidden" id="opt_confirm_role_name" value="" >
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
	
	<!-- Modal add role -->
	<div id="modal_add_role" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content widget ">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title" id="modal_add_role_title"></h4>
				</div>
				<div class="alert opt-result-alert"></div>
				<div class="modal-body modal-scrollable" id="modal_add_role_content">

				 <div class="padd">
                 	 <div class="form-horizontal">
                          					<div class="form-group">
                                            <label class="control-label col-md-3" for="add_input_role_code">角色代码</label>
                                            <div class="col-md-3">
                                              <input type="text" class="form-control required-input" id="add_input_role_code" placeholder="角色代码">
                                              <span class="text-danger" >*</span>
                                            </div>
                                            <label class="control-label col-md-3" for="add_input_role_name">角色名称</label>
                                            <div class="col-md-3">
                                              <input type="text" class="form-control required-input" id="add_input_role_name" placeholder="角色名称">
                                              <span class="text-danger">*</span>
                                            </div>
                                          </div>   
                                          <!--  -->
                                          <div class="form-group">
                                            <label class="control-label col-md-3" for="add_input_role_desc">角色说明</label>
                                            <div class="col-md-8">
                                              <input type="text" class="form-control" id="add_input_role_desc" placeholder="角色说明">
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
										
										<div class="form-group"><center><hr class="modal-hr"/></center>
											<div class="panel-body" id="role_action_option_div"><p>角色对应菜单列表</p>
<!-- 
										 	 <ul class="nav nav-pills nav-justified" id="role_action_option">
											  <li class="active"><a href="javascript:void(0);" onclick="displayActionByRoleCode(this);" data-role_code="ROLE_CODE_0001">Home</a></li>
											  <li><a href="javascript:void(0);" onclick="displayActionByRoleCode(this);" data-role_code="ROLE_CODE_0002">SVN</a></li>
											  <li><a href="javascript:void(0);" onclick="displayActionByRoleCode(this);" data-role_code="ROLE_CODE_0003">iOS</a></li>
											  <li><a href="javascript:void(0);" onclick="displayActionByRoleCode(this);" data-role_code="ROLE_CODE_0004">VB.Net</a></li>
											  <li><a href="javascript:void(0);" onclick="displayActionByRoleCode(this);" data-role_code="ROLE_CODE_0005">Java</a></li>
											  <li><a href="javascript:void(0);" onclick="displayActionByRoleCode(this);" data-role_code="ROLE_CODE_0006">PHP</a></li>
											  <li><a href="javascript:void(0);" onclick="displayActionByRoleCode(this);" data-role_code="ROLE_CODE_0007">PHP2</a></li>
											</ul>
												<div class="action-checkbox-div" id="ACTION_DIV_ROLE_CODE_0001">
													<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox1" value="all"> 全选</label> 
													<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox1" value="option1"> 选项 1</label> //action code
													<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox2" value="option2"> 选项 2</label> 
													<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox3" value="option3"> 选项 3</label>
													<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox1" value="option1"> 选项 1</label> 
													<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox2" value="option2"> 选项 2</label> 
													<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox3" value="option3"> 选项 3</label>
													<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox1" value="option1"> 选项 1</label> 
													<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox2" value="option2"> 选项 2</label> 
													<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox3" value="option3"> 选项 3</label>
													<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox1" value="option1"> 选项 1</label> 
													<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox2" value="option2"> 选项 2</label> 
													<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox3" value="option3"> 选项 3</label>
													<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox1" value="option1"> 选项 1</label> 
													<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox2" value="option2"> 选项 2</label> 
													<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox3" value="option3"> 选项 3</label>
												</div>
												<div class="action-checkbox-div" id="ACTION_DIV_ROLE_CODE_0002" style="display:none;">
													<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox1" value="all"> 全选</label> 
													<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox1" value="option1"> 选项 4</label> 
													<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox2" value="option2"> 选项 5</label> 
													<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox3" value="option3"> 选项 6</label>
													<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox3" value="option3"> 选项 3</label>
													<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox1" value="option1"> 选项 1</label> 
													<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox2" value="option2"> 选项 2</label> 
													<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox3" value="option3"> 选项 3</label>
													<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox1" value="option1"> 选项 1</label> 
													<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox2" value="option2"> 选项 2</label> 
													<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox3" value="option3"> 选项 3</label>
													<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox1" value="option1"> 选项 1</label> 
													<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox2" value="option2"> 选项 2</label> 
													<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox3" value="option3"> 选项 3</label>
												</div>
												<div class="action-checkbox-div" id="ACTION_DIV_ROLE_CODE_0003" style="display:none;">
													<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox1" value="all"> 全选</label> 
													<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox1" value="option1"> 选项 41</label> 
													<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox2" value="option2"> 选项 52</label> 
													<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox3" value="option3"> 选项 63</label>
													<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox3" value="option3"> 选项 3</label>
													<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox1" value="option1"> 选项 1</label> 
													<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox2" value="option2"> 选项 2</label> 
													<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox3" value="option3"> 选项 3</label>
													<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox1" value="option1"> 选项 1</label> 
													<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox2" value="option2"> 选项 2</label> 
													<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox3" value="option3"> 选项 3</label>
													<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox1" value="option1"> 选项 1</label> 
													<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox2" value="option2"> 选项 2</label> 
													<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox3" value="option3"> 选项 3</label>
												</div>
												-->
												<yb:funActOptHtml ulId="role_action_option"></yb:funActOptHtml>
											</div>
								         </div>  
								</div>
               		 </div>
				</div>
				<div class="modal-footer">
<!-- 					<button type="button" class="btn btn-primary">确定</button> --><!-- data-opt-type="" new/edit -->
					<button type="button" id="btn_save_role" data-opt-type="" class="btn btn-success" data-loading-text="Loading" onclick="saveRoleConfirm(this);">保&nbsp;&nbsp;存</button>
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
<script src="<%=path%>/js/busi-js/query_role_list.js?v=<%=staticVersion%>" type="text/javascript"></script>

<!-- Script for this page -->
<script type="text/javascript">

	$(function() {
		dataTableInit();
	}); 
	
	function dataTableInit() {
		//param
		var roleName = $("#qr_input_role_name").val();
		var roleCode = $("#qr_input_role_code").val();
		var includeStop=(true==$("#qr_cb_include_stop").is(':checked'))?"1":"0";
		
		//格式：列映射关系
		var aoColumns = '[{"mDataProp" : "ROLE_CODE","sTitle" : "序号"}, '
					   + '{"mDataProp" : "ROLE_CODE","sTitle" : "角色代码"},'
					   + '{"mDataProp" : "ROLE_NAME","sTitle" : "角色名称"},'
					   + '{"mDataProp" : "ROLE_DESC","sTitle" : "角色说明"},'
// 					   + '{"mDataProp" : "CODE_DESC","sTitle" : "代码说明"},'
					   + '{"mDataProp" : "FREEZE_TAG","sTitle" : "角色状态"},'
					   + '{"mDataProp" : "ROLE_CODE","sTitle" : "操作"}]';

		var reqData = {
			action : 'ORGANIZE_ROLE_QUERY_ACTION',
			params : 'params',
			ROLE_NAME:roleName,
			ROLE_CODE:roleCode,
			INCLUDE_STOP:includeStop
			};
		//example为table定义id ：10：需要加入操作按钮的列；[4,5]：需要转义的列（映射数据字典值）
		initRoleTable('roleList', aoColumns, reqData, '<%=path%>',5,[5]);
		} 
	/*title tips- common method,dynamic bind*/
	$(function () { $("[data-toggle='tooltip']").tooltip();});
	
	function actionOptionReset(){ //action 复位
		$('#role_action_option').find('a[data-role_code]:first').click();
// 		$('#role_action_option').find('a[data-role_code]').first().click();
	}
	
	function detail(role_code){
		//auto top and height
// 		modal_auto_top($('div.modal-dialog'));
// 		init
		//clearMsgShow('modal_add_role');
		myModalInit('modal_add_role');
		
// 		$('#myModal').modal({backdrop: 'static', keyboard: false});
// 		$('#myModal_content').html('<span>'+window.parent.getScrollTop()+'</span><br/><span>'+$(window.parent).height()+'</span><br/><span>'+$(window.parent.document.body)[0].clientHeight+'</span>');
// 		clearModalContent('modal_add_role');
		actionOptionReset();
		
		//input readOnly
		readOnlyModalInput('modal_add_role');
		
		//btn hide
		$('#btn_save_role').hide();
		$('#btn_save_role').attr('data-opt-type','QUERY');
		
		var reqUrl='<%=path%>/AjaxChannel?action=ORGANIZE_ROLE_DETAIL_ACTION';
		//ajax begin
		$.ajax({
				type : 'POST',
				url:reqUrl,
				data: {ROLE_CODE:role_code},
				dataType : 'json',
				success : function(json) {
					if(json.SUCCESS=='1'){
							$('#add_input_role_code').val(json.ROLE_BEAN.RoleCode);
							$('#add_input_role_name').val(json.ROLE_BEAN.RoleName);
							$('#add_input_role_desc').val(json.ROLE_BEAN.RoleDesc);
							
							if(json.ACTION_LIST!=null)
								$.each(json.ACTION_LIST,function(i,item){
									console.log(item.OptionCode);
									console.log(item.ActionCode);
									//check box :checked by action code
									//role_action_option_div
									var actionCode=item.ActionCode;
									$("#role_action_option_div").find(":checkbox[value='"+actionCode+"']").prop("checked",true);
								});
// 							returnSuccessMsgShow('modal_add_role',json.MSG)
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
		$('#modal_add_role_title').text('角色明细['+role_code+']');
		$('#modal_add_role').modal({backdrop: 'static', keyboard: false});
		
	}
	
	//open modal
	function editRole(role_code){
// 		init
		myModalInit('modal_add_role');
		actionOptionReset();
		activeModalInput('modal_add_role');
		
		//btn show
		$('#btn_save_role').show();
		$('#btn_save_role').attr('data-opt-type','EDIT');
		//role_code readyonly
		$('#add_input_role_code').attr('readonly','');
		
		var reqUrl='<%=path%>/AjaxChannel?action=ORGANIZE_ROLE_DETAIL_ACTION';
		//ajax begin
		$.ajax({
				type : 'POST',
				url:reqUrl,
				data: {ROLE_CODE:role_code},
				dataType : 'json',
				success : function(json) {
					if(json.SUCCESS=='1'){
							$('#add_input_role_code').val(json.ROLE_BEAN.RoleCode);
							$('#add_input_role_name').val(json.ROLE_BEAN.RoleName);
							$('#add_input_role_desc').val(json.ROLE_BEAN.RoleDesc);
							
							if(json.ACTION_LIST!=null)
								$.each(json.ACTION_LIST,function(i,item){
									console.log(item.OptionCode);
									console.log(item.ActionCode);
									//check box :checked by action code
									//role_action_option_div
									var actionCode=item.ActionCode;
									$("#role_action_option_div").find(":checkbox[value='"+actionCode+"']").prop("checked",true);
								});
// 							returnSuccessMsgShow('modal_add_role',json.MSG)
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
		$('#modal_add_role_title').text('角色编辑['+role_code+']');
		$('#modal_add_role').modal({backdrop: 'static', keyboard: false});
		
	}

	
	function addRole(){
		//auto top and height
// 		modal_auto_top($('div.modal-dialog'));
// 		clearModalContent('modal_add_role');
//		common init
// 		clear modal content
		myModalInit('modal_add_role');
		actionOptionReset();
		activeModalInput('modal_add_role');
		//btn show
		$('#btn_save_role').show();
		$('#btn_save_role').attr('data-opt-type','NEW');
		
		$('#modal_add_role_title').text('新增角色');
		$('#modal_add_role').modal({backdrop: 'static', keyboard: false});
		
	}
	//ul li a click
	function displayActionByRoleCode(obj){
		
		if($(obj).parent().hasClass("active")){
			return false;
		}
		var roleCode=$(obj).attr("data-role_code");
		console.log(roleCode);
		$(obj).parent().parent().children("li").removeClass("active");
		$(obj).parent().addClass("active");
		//display action list
		$(obj).parent().parent().parent().children("div.action-checkbox-div").hide();
		$(obj).parent().parent().parent().children("#ACTION_DIV_"+roleCode).show();
// 		$(obj).parent().parent().parent().children("div.action-checkbox-div").hide(400);
// 		$(obj).parent().parent().parent().children("#ACTION_DIV_"+roleCode).show(200);
	}

	/*
	function saveRole(obj){
		$(obj).button('loading');
		
		$("div.opt-result-alert").hide();//clear other alert div
		$("div.opt-result-alert").removeClass("alert-success").removeClass("alert-danger");
//		ajax();
// 		callbak($(obj).button('reset');)
		setTimeout(function (){ //callback
			$(obj).button('reset'); 
			//success
// 			$("div.opt-result-alert").addClass("alert-success"); 
			//faild
			$("div.opt-result-alert").addClass("alert-danger");
			
			$("div.opt-result-alert").text("保存成功！");
			
// 			$("div.opt-result-alert").fadeIn(function(){
// 				$("div.opt-result-alert").fadeToggle(3000);
// 			});
// 			$("div.opt-result-alert").fadeIn().delay(3000).fadeToggle();

			$("div.opt-result-alert").slideDown(500).delay(3000).slideToggle(500);
			
			},1000);  
	}
	*/
	
	function saveRoleConfirm(obj){
		
// 		$("div.opt-result-alert").hide();//clear other alert div
// 		$("div.opt-result-alert").removeClass("alert-success").removeClass("alert-danger");
//		ajax();
// 		callbak($(obj).button('reset');)
// 		setTimeout(function (){ //callback
// 			$(obj).button('reset'); 
// 			//success
// 			},1000);  
		var modalOptType=$(obj).attr('data-opt-type');//new or eidt

		var role_code=$('#add_input_role_code').val();
		var role_name=$('#add_input_role_name').val();
		var role_desc=$('#add_input_role_desc').val();
		
		//	$(":checked")
		var roleActionArray=[];
		$('#role_action_option_div').find(":checked:not([value='all'])").each(function(index,item){
			roleActionArray.push($(item).attr('value'));
		});
		var roleActionStr='';
		if(roleActionArray!=null&&roleActionArray.length>0){
			roleActionStr=roleActionArray.toString();//"xxx,xxx"
		}
		var reqUrl;
		if('NEW'==modalOptType){
			reqUrl='<%=path%>/AjaxChannel?action=ORGANIZE_ROLE_ADD_ACTION';
		}
		else if('EDIT'==modalOptType){
			reqUrl='<%=path%>/AjaxChannel?action=ORGANIZE_ROLE_EDIT_ACTION';
		}
		else{
			returnErrorMsgShow('modal_add_role','未知操作类型，请稍后重试')
			return false;
		}
		
		$(obj).button('loading');
		//ajax begin
		$.ajax({
				type : 'POST',
				url:reqUrl,
				data: {ROLE_CODE:role_code,ROLE_NAME:role_name,ROLE_DESC:role_desc,ROLE_ACTION_STR:roleActionStr},
				dataType : 'json',
				success : function(json) {
					if(json.SUCCESS=='1'){
							returnSuccessMsgShow('modal_add_role',json.MSG)
							var fvTable=$("#roleList").dataTable(); //datatable init current
							fvTable.fnDraw(false);
							$(obj).button('reset');
						}else{
// 							alert(json.MSG)	
							returnErrorMsgShow('modal_add_role',json.MSG)	
							$(obj).button('reset');
						}
					},
				error : function(e) {
					console.log(e);
					}
				});
	}
	
	/*opt confirm*/
	function optConfirm(opt_type,role_name,role_code){
		//auto top and height
// 		modal_auto_top($('div.modal-dialog'));

// 		$('#optModal').find("button.btn-primary:first").button('reset');
// 		clearMsgShow('optModal');
//		init common
		myModalInit('optModal');
		
		var title;
		var contentHtml;
		
		$("#opt_confirm_role_code").val(role_code);
		$("#opt_confirm_role_name").val(role_name);
		$("#opt_confirm_opt_type").val(opt_type);
		
		if("delete"==opt_type){
			title="删除角色["+role_name+"]";
			
			contentHtml=
				"<div class='alert alert-danger' role='alert'>"
					+"<span class='glyphicon glyphicon-exclamation-sign' aria-hidden='true'></span> <span class='sr-only'>注意:</span>"
					+"<strong>注意:</strong>角色["+role_name+"]将彻底删除，无法恢复 !"
				+"</div>";
// 			$('#optModal').find("button.btn-primary:first").click(startRole);

		}else if("stop"==opt_type)	{
			title="停用角色["+role_name+"]";
			
			contentHtml=
				"<div class='alert alert-warning' role='alert'>"
					+"<span class='glyphicon glyphicon-question-sign' aria-hidden='true'></span> <span class='sr-only'>注意:</span>"
					+"<strong>注意:</strong>角色["+role_name+"]将停用，可在角色管理恢复。"
				+"</div>";
// 			$('#optModal').find("button.btn-primary:first").click(stopRole);
		}else if("start"==opt_type){
			title="启用角色["+role_name+"]";
			
			contentHtml=
				"<div class='alert alert-info' role='alert'>"
					+"<strong>注意:</strong><span class='glyphicon glyphicon-info-sign' aria-hidden='true'></span> <span class='sr-only'>注意:</span>"
					+"角色["+role_name+"]将被启用。"
				+"</div>";
// 			$('#optModal').find("button.btn-primary:first").click(deleteRole);
		}
		$('#optModal').find("h4.modal-title:first").text(title);
		$('#optModal').find("div.modal-body:first").html(contentHtml);
// 		$('#deleteModal_content').html('<span>'+window.parent.getScrollTop()+'</span><br/><span>'+$(window.parent).height()+'</span><br/><span>'+$(window.parent.document.body)[0].clientHeight+'</span>');
		$('#optModal').modal({backdrop: 'static', keyboard: false});
	}
	
	function optConfirmDone(obj){
		var roleCode=$("#opt_confirm_role_code").val();
		var roleName=$("#opt_confirm_role_name").val();
		var optType=$("#opt_confirm_opt_type").val();
		
		$(obj).button('loading');
		var reqUrl='<%=path%>/AjaxChannel?action=ORGANIZE_ROLE_MOD_ACTION';
		//ajax begin
		$.ajax({
				type : 'POST',
				url:reqUrl,
				data: {ROLE_CODE:roleCode,OPT_TYPE:optType},
				dataType : 'json',
				success : function(json) {
					if(json.SUCCESS=='1'){
							returnSuccessMsgShow('optModal',json.MSG);//alert msg
// 							$(obj).button('complete');//button 
							var fvTable=$("#roleList").dataTable(); //datatable init current
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
	/*查询条件清空重置查询*/
	function btnQueryReset(){
		queryBtnRestClear();
		dataTableInit();
	}
	
// 	action 全选 check box
	$(function(){
		$(":checkbox[value='all']").click(function() {
			console.log($(this).parent().parent().find(":checkbox").size());
// 			alert($(this).parent().parent().find("[type='checkbox']").size());
			$(this).parent().parent().find(":checkbox").prop("checked",this.checked); 
// 			$(this).parent().parent().find(":checkbox").attr("checked",this.checked); 
//             $('input[name="subBox"]').attr("checked",this.checked); 
        });
	});

</script>

</body>
</html>