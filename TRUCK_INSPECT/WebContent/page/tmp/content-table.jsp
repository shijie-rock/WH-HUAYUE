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
        <h2 class="pull-left"><i class="icon-user"></i> Tables</h2>

        <!-- Breadcrumb -->
        <div class="bread-crumb pull-right">
          <a href="<%=path%>/page/index-content.jsp"><i class="icon-home"></i> 首页</a> 
          <!-- Divider -->
          <span class="divider">/</span> 
          <a href="#" class="bread-current">Dashboard</a>
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
        			<label class="col-sm-5 control-label">姓名</label>
         			<div class="col-sm-7"><input type="text" class="form-control" placeholder="姓名"></div>
        		</div>
        		
       			<div class="form-group col-sm-3">
        			<label class="col-sm-5 control-label">手机</label>
         			<div class="col-sm-7"><input type="text" class="form-control" placeholder="手机"></div>
        		</div>
        		
       			<div class="form-group col-sm-3">
        			<label class="col-sm-5 control-label">手机2</label>
         			<div class="col-sm-7"><select class="form-control">
         				<option value='1' selected="selected">1</option>
         				<option value='2'>2</option>
         			</select>
         			</div>
        		</div>
        		
        		 <div class="form-group col-sm-3">
						<button type="button" class="btn btn-success">查询</button>
						&nbsp;
						<button type="button" class="btn btn-danger">清空</button>
         		 </div>
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
                  
                    <table id="msgMainInfo"  class="table table-striped table-bordered table-hover">
                      <thead>
                        <tr>
                          <th width="5%"></th>
                          <th width="10%"></th>
                          <th width="10%"></th>
                          <th width="20%"></th>
                          <th width="20%"></th>
                          <th width="25%"></th>
                          <th width="10%"></th>
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

	<!-- Modal -->
	<div id="myModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title">Modal title</h4>
				</div>
				<div class="modal-body" id="myModal_content">
					<p>One fine body…</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal" aria-hidden="true">Close</button>
					<button type="button" class="btn btn-primary">Save changes</button>
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
<%-- <script src="<%=path%>/js/jquery-ui-1.9.2.custom.min.js?v=<%=staticVersion%>"></script> <!-- jQuery UI --> --%>
<%-- <script src="<%=path%>/js/fullcalendar.min.js?v=<%=staticVersion%>"></script> <!-- Full Google Calendar - Calendar --> --%>
<%-- <script src="<%=path%>/js/jquery.rateit.min.js?v=<%=staticVersion%>"></script> <!-- RateIt - Star rating --> --%>
<%-- <script src="<%=path%>/js/jquery.prettyPhoto.js?v=<%=staticVersion%>"></script> <!-- prettyPhoto --> --%>

<!-- <!-- jQuery Flot --> 
<%-- <script src="<%=path%>/js/excanvas.min.js?v=<%=staticVersion%>"></script> --%>
<%-- <script src="<%=path%>/js/jquery.flot.js?v=<%=staticVersion%>"></script> --%>
<%-- <script src="<%=path%>/js/jquery.flot.resize.js?v=<%=staticVersion%>"></script> --%>
<%-- <script src="<%=path%>/js/jquery.flot.pie.js?v=<%=staticVersion%>"></script> --%>
<%-- <script src="<%=path%>/js/jquery.flot.stack.js?v=<%=staticVersion%>"></script> --%>

<!-- <!-- jQuery Notification - Noty --> 
<%-- <script src="<%=path%>/js/jquery.noty.js?v=<%=staticVersion%>"></script> <!-- jQuery Notify --> --%>
<%-- <script src="<%=path%>/js/themes/default.js?v=<%=staticVersion%>"></script> <!-- jQuery Notify --> --%>
<%-- <script src="<%=path%>/js/layouts/bottom.js?v=<%=staticVersion%>"></script> <!-- jQuery Notify --> --%>
<%-- <script src="<%=path%>/js/layouts/topRight.js?v=<%=staticVersion%>"></script> <!-- jQuery Notify --> --%>
<%-- <script src="<%=path%>/js/layouts/top.js?v=<%=staticVersion%>"></script> <!-- jQuery Notify --> --%>

<!-- <!--  jQuery Notification ends --> 
<%-- <script src="<%=path%>/js/sparklines.js?v=<%=staticVersion%>"></script> <!-- Sparklines --> --%>
<%-- <script src="<%=path%>/js/jquery.cleditor.min.js?v=<%=staticVersion%>"></script> <!-- CLEditor --> --%>
<%-- <script src="<%=path%>/js/bootstrap-datetimepicker.min.js?v=<%=staticVersion%>"></script> <!-- Date picker --> --%>
<%-- <script src="<%=path%>/js/jquery.uniform.min.js?v=<%=staticVersion%>"></script> <!-- jQuery Uniform --> --%>
<%-- <script src="<%=path%>/js/bootstrap-switch.min.js?v=<%=staticVersion%>"></script> <!-- Bootstrap Toggle --> --%>
<%-- <script src="<%=path%>/js/filter.js?v=<%=staticVersion%>"></script> <!-- Filter for support page --> --%>
<%-- <script src="<%=path%>/js/charts.js?v=<%=staticVersion%>"></script> <!-- Charts & Graphs --> --%>
<%-- <script src="<%=path%>/js/custom.js?v=<%=staticVersion%>"></script> <!-- Custom codes --> --%>
<!-- table收起 -->
<script src="<%=path%>/js/truck-inspect-common.js?v=<%=staticVersion%>"></script> <!-- Custom codes -->
<script src="<%=path%>/js/data-tables/jquery.dataTables.min.js?v=<%=staticVersion%>"	type="text/javascript"></script>
<script src="<%=path%>/js/data-tables/dataTables.bootstrap.min.js?v=<%=staticVersion%>"	type="text/javascript"></script>
<script src="<%=path%>/js/busi-js/receive_msg_list.js?v=<%=staticVersion%>" type="text/javascript"></script>

<!-- Script for this page -->
<script type="text/javascript">

//模态框随滚动条移动
//window.parent.onscroll= function(){
//	$('div.modal-dialog').css("margin-top",window.parent.getScrollTop()+"px");
//}

	$(function() {
		dataTableInit();
	});

	function dataTableInit() {
		var startDate = $("#startDate").val();
		var endDate = $("#endDate").val();
		var vehicle_license = $("#vehicle_license").val();
		//格式：列映射关系
		var aoColumns = '[{"mDataProp" : "CODE","sTitle" : "序号"}, '
				+ '{"mDataProp" : "CODE","sTitle" : "代码"},'
				+ '{"mDataProp" : "TYPE_CODE","sTitle" : "类型代码"},'
				+ '{"mDataProp" : "TYPE_DESC","sTitle" : "类型说明"},'
				+ '{"mDataProp" : "CODE_DESC","sTitle" : "代码说明"},'
				+ '{"mDataProp" : "STATUS","sTitle" : "状态"},'
				+ '{"mDataProp" : "CODE","sTitle" : "操作"}]';

		var reqData = {
			action : 'MY_TEST_ACTION',
			params : 'params',
			startDate:startDate,
			endDate:endDate,
			vehicle_license:vehicle_license
			};
		//example为table定义id ：10：需要加入操作按钮的列；[4,5]：需要转义的列（映射数据字典值）
		initSendTable('msgMainInfo', aoColumns, reqData, '<%=path%>',6,[5]);
		//$("#body_height").height($("#msgMainInfo_wrapper").height() +100)
		} 
	
	function detail(){
		modal_auto_top($('div.modal-dialog'));
		$('#myModal').modal({backdrop: 'static', keyboard: false});
// 		$('#myModal').modal('show');
		$('#myModal_content').html('<span>'+window.parent.getScrollTop()+'</span>');
// 		$('div.modal-dialog').css("margin-top",window.parent.getScrollTop()+"px");
// 		$('div.modal-dialog').css("padding-top",window.parent.getScrollTop()+30+"px");
// 		$('#myModal_content').html('<span>'+getScrollTop()+'</span>');
// 		$('#identifier').modal('hide');
	}
	


</script>

</body>
</html>