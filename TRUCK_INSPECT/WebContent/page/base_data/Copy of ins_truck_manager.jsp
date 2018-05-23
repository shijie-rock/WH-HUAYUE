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
<%--   <link href="<%=path%>/style/layui.css?v=<%=staticVersion%>" rel="stylesheet" /><!-- layui css file --> --%>
  <!-- HTML5 Support for IE -->
  <!--[if lt IE 9]>
  <script src="js/html5shim.js"></script>
  <![endif]-->

  <!-- Favicon -->
  <link rel="shortcut icon" href="img/favicon/favicon.png">
  <style type="text/css">
	.layui-upload-file {
		display: none !important;
		opacity: .01;
		filter: Alpha(opacity = 1);
	}
</style>
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
        			<label class="col-sm-5 control-label">车辆类型</label>
         			<div class="col-sm-7">
<!--          			<input type="text" class="form-control" placeholder="车辆类型" id="qr_input_truck_type"> -->
         			<yb:select dataSource="DATA_DIC.TRUCK_TYPE"  selectClass="form-control subWidth"  includeNull="true" selectId="qr_input_truck_type"/>
         			</div>
        		</div>
       			<div class="form-group col-sm-3">
        			<label class="col-sm-5 control-label">车辆状态</label>
         			<div class="col-sm-7">
<!--          			<input type="text" class="form-control" placeholder="车辆状态" id="qr_input_truck_status"> -->
         			<yb:select dataSource="DATA_DIC.TRUCK_STATUS"  selectClass="form-control subWidth"  includeNull="true" selectId="qr_input_truck_status"/>
         			</div>
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
                  <div class="pull-left">查询结果 &nbsp;&nbsp;&nbsp;<button type="button" class="btn btn-primary btn-xs" href="javascript:void(0);" onclick="addTruck();" id="bt_add_truck">新增车辆</button></div>
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
                          <th width="5%">序号</th>
                          <th width="10%">车牌/车架号</th>
                          <th width="10%">类型/所属/危</th>
                          <th width="10%">品牌/车型</th>
                          <th width="10%">生产/上牌日期</th>
                          <th width="10%">许可开始/截止</th>
                          <th width="10%">长宽高/载重</th>
                          <th width="10%">检测历史</th>
                          <th width="5%">图片</th>
                          <th width="5%">状态</th>
                          <th width="15%">操作</th>
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
                                       <label class="control-label col-md-3" for="add_input_truck_license"></label>
                                        <div class="col-md-4">
<!-- 											<div class="layui-upload"> -->
											 <div class="layui-upload-list">
											    <img class="layui-upload-img" id="truck_img_upload" style="width:158px;height:154px;" > <!-- 上传图片 -->
											    <img class="layui-upload-img" id="truck_img_display" src="" style="width:158px;height:154px; display: none;"> <!-- 显示图片 -->
											  </div>
<!-- 											  <button type="button" class="btn  btn-xs btn-primary" id="test1">上传图片</button> -->
<!-- 											  <button type="button" class="layui-btn" id="test1">上传图片</button> -->
<!-- 											</div> -->
										 </div>
<!-- 										 <div> -->
										    <label class="control-label col-md-6" for="add_input_truck_license">车牌</label>
                                            <div class="col-md-4" style="margin-bottom: 15px">
                                              <input type="text" class="form-control required-input" id="add_input_truck_license" placeholder="车牌">
                                              <span class="text-danger" >*</span>
                                            </div>
                                            
                                            <label class="control-label col-md-3" for="add_input_truck_vin">VIN</label>
                                            <div class="col-md-4" style="margin-bottom: 15px">
                                              <input type="text" class="form-control required-input" id="add_input_truck_vin" placeholder="车辆Vin码">
<!--                                               <span class="text-danger">*</span> -->
                                            </div>
                                            
                                            <label class="control-label col-md-3" for="add_input_truck_type">类型</label>
                                            <div class="col-md-4" style="margin-bottom: 15px">
<!--                                          		<input type="text" class="form-control required-input" id="add_input_truck_type" placeholder="车辆类型"> -->
                                              <yb:select dataSource="DATA_DIC.TRUCK_TYPE"  selectClass="form-control subWidth"  includeNull="true" selectId="add_input_truck_type"/>
<!--                                               <span class="text-danger">*</span> -->
                                            </div>
                                            <label class="control-label col-md-3" for="add_input_truck_danger_level">危险类型</label>
                                            <div class="col-md-4" >
<!--                                               <input type="text" class="form-control required-input" id="add_input_truck_danger_level" placeholder="危险类型"> -->
                                              <yb:select dataSource="DATA_DIC.TRUCK_DANGER_LEVEL"  selectClass="form-control subWidth"  includeNull="true" selectId="add_input_truck_danger_level"/>
<!--                                               <span class="text-danger">*</span> -->
                                            </div>
                                            
<!--                                       </div>  -->
										<!--end row -->
                                          </div> 
                          					<div class="form-group">
                                            <label class="control-label col-md-3" for="add_input_truck_driver_name">司机</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_truck_driver_name" placeholder="司机">
                                         	  <input type="hidden" id="add_input_truck_driver_id" value="">
<!--                                               <span class="text-danger" >*</span> -->
                                            </div>
                                            <label class="control-label col-md-3" for="add_input_truck_belong_type">归属类型</label>
                                            <div class="col-md-4">
<!--                                               <input type="text" class="form-control required-input" id="add_input_truck_belong_type" placeholder="归属类型"> -->
                                              <yb:select dataSource="DATA_DIC.TRUCK_BELONG_TYPE"  selectClass="form-control subWidth"  includeNull="true" selectId="add_input_truck_belong_type"/>
<!--                                               <span class="text-danger">*</span> -->
                                            </div>
                                          </div> 
                          					<div class="form-group">
                                            <label class="control-label col-md-3" for="add_input_truck_brand_code">品牌</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_truck_brand_code" placeholder="品牌">
<!--                                               <span class="text-danger" >*</span> -->
                                            </div>
                                            <label class="control-label col-md-3" for="add_input_truck_model_code">车型</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_truck_model_code" placeholder="车型">
<!--                                               <span class="text-danger">*</span> -->
                                            </div>
                                          </div> 
                                          
                                          <div class="form-group">
                                            <label class="control-label col-md-3" for="add_input_truck_make_date">生产日期</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input layer-date" id="add_input_truck_make_date"  placeholder="生产日期">
<!--                                               <span class="text-danger" >*</span> -->
											  <input class="form-control required-input" size="16" type="text" value="" id="add_input_truck_make_date_query" placeholder="生产日期" readonly>
                                            </div>
                                            
                                            <label class="control-label col-md-3" for="add_input_truck_license_date">上牌日期</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input layer-date" id="add_input_truck_license_date"  placeholder="上牌日期">
<!--                                               <span class="text-danger" >*</span> -->
											  <input class="form-control required-input" size="16" type="text" value="" id="add_input_truck_license_date_query" placeholder="上牌日期" readonly>
                                            </div>

                                          </div>  
                                          <!-- 暂不需要
                          				  <div class="form-group">
                                            <label class="control-label col-md-3" for="add_input_truck_cf_begin">许可开始</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_truck_cf_begin" placeholder="许可开始日期">
                                            </div>
                                            
                                            <label class="control-label col-md-3" for="add_input_truck_cf_end">许可截止</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_truck_cf_end" placeholder="许可截止日期">
                                            </div>
                                          </div>   
 										 --> 
 										 
 										  <div class="form-group">
                                            <label class="control-label col-md-3" for="add_input_truck_desc">车辆说明</label>
                                            <div class="col-md-8">
                                              <input type="text" class="form-control" id="add_input_truck_desc"  placeholder="车辆说明">
                                            </div>
                                          </div>
 										<!--  //暂不需要车辆尺寸
                                        <div class="form-group">
                                        <center><hr class="modal-hr"/></center>  
                                        <div class="panel-body" id="add_input_truck_size" style="padding-bottom: 0px;"><p>车辆尺寸</p></div>
                                            <label class="control-label col-md-2" for="add_input_truck_size_length">长</label>
                                            <div class="col-md-2">
                                              <input type="text" class="form-control required-input" id="add_input_truck_size_length" placeholder="长" style="width:84%;"><span class="text-default" >M</span>
                                            </div>
                                            <label class="control-label col-md-2" for="add_input_truck_size_width">宽</label>
                                            <div class="col-md-2">
                                              <input type="text" class="form-control required-input" id="add_input_truck_size_width" placeholder="宽" style="width:84%;"><span class="text-default" >M</span>
                                            </div>
                                            <label class="control-label col-md-2" for="add_input_truck_size_height">高</label>
                                            <div class="col-md-2">
                                              <input type="text" class="form-control required-input" id="add_input_truck_size_height" placeholder="高" style="width:84%;"><span class="text-default" >M</span>
                                            </div>
                                         </div>
                                          <div class="form-group">
                                            <label class="control-label col-md-3" for="add_input_truck_weight">车重</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_truck_weight"  placeholder="车重" style="width:92%;">
                                              <span class="text-default" >T</span>
                                            </div>
                                            <label class="control-label col-md-3" for="add_input_truck_color">颜色</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_truck_color" placeholder="颜色">
                                            </div>
                                        
                                          </div>     
                                          -->  
                                         <yb:CheckEntMidOptHtml entTypeCode="CET_0010"></yb:CheckEntMidOptHtml>
                                         <yb:TruckObjSupMidSubOptHtml entTypeCode="CET_0010"></yb:TruckObjSupMidSubOptHtml>
                                         <!-- 检查对象类型 车辆 -->
                                        <!--  
                                        <center><hr class="modal-hr"/></center>  
                                        <div class="panel-body" id="add_input_truck_check_ent_select" style="padding-bottom: 0px;"><p>车辆检查对象组成</p></div>
                                         <div class="form-group">
                                            <label class="control-label col-md-3" for="add_input_truck_weight">车重</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_truck_weight"  placeholder="车重" style="width:92%;">
                                            </div>
                                            <label class="control-label col-md-3" for="add_input_truck_color">颜色</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_truck_color" placeholder="颜色">
                                            </div>
                                          </div>
                                          
                                          <div class="form-group">
                                            <label class="control-label col-md-3" for="add_input_truck_weight">车重</label>
                                            <div class="col-md-4">
                                              <select id="add_input_truck_belong_type" name="null" class="form-control subWidth" ><option class="null" value="">----</option><option class="null" value="TBT_0010" selected="selected">自有</option><option class="null" value="TBT_0020">外调</option></select>
                                            </div>
                                            <label class="control-label col-md-3" for="add_input_truck_color">颜色</label>
                                            <div class="col-md-4">
                                              <select id="add_input_truck_belong_type" name="null" class="form-control subWidth" ><option class="null" value="">----</option><option class="null" value="TBT_0010">自有</option><option class="null" value="TBT_0020" selected="selected">外调</option></select>
                                            </div>
                                          </div>
                                          -->
<!--                                           <div class="form-group"> -->
<!--                                          		 <label class="control-label col-md-3" >车辆设置</label> -->
<!--                                          		 <div class="col-md-8"> -->
<!-- 													<label class="checkbox-inline col-md-3"> <input type="checkbox" id="add_input_is_login_sys" value="1">操作员</label> -->
<!-- 													<label class="checkbox-inline col-md-3"> <input type="checkbox" id="add_input_is_inspactor" value="1">检查员</label> -->
<!--                                          		 </div> -->
<!--                                           </div> -->
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
	
	<!-- pic modal -->
	
<div id="modal_ins_truck_img" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
				<div class="modal-content widget ">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title" id="modal_add_ins_truck_img_title"></h4>
				</div>
				<div class="alert opt-result-alert"></div>
				<div class="modal-body modal-scrollable modal-truck-img" id="modal_add_ins_truck_img_content">
			<div id="myCarousel" class="carousel slide" >
				<!-- 轮播（Carousel）指标 -->
				<ol class="carousel-indicators">
					<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
					<li data-target="#myCarousel" data-slide-to="1"></li>
					<li data-target="#myCarousel" data-slide-to="2"></li>
					<li data-target="#myCarousel" data-slide-to="3"></li>
					<li data-target="#myCarousel" data-slide-to="4"></li>
				</ol>
				<!-- 轮播（Carousel）项目 -->
				<div class="carousel-inner">
					<div class="item active">
						<img src="<%=path%>/img/temp/timg.jpg" alt="First slide" class="auto-size-img">
						<div class="carousel-caption">鲁F-12345</div>
					</div>
					<div class="item">
						<img src="<%=path%>/img/temp/timg (1).jpg" alt="Second slide" class="auto-size-img">
						<div class="carousel-caption">鲁F-12345</div>
					</div>
					<div class="item">
						<img src="<%=path%>/img/temp/timg (2).jpg" alt="Third slide" class="auto-size-img">
						<div class="carousel-caption">鲁F-12345</div>
					</div>
					<div class="item">
						<img src="<%=path%>/img/temp/timg (3).jpg" alt="Third slide" class="auto-size-img">
						<div class="carousel-caption">鲁F-12345</div>
					</div>
					<div class="item">
						<img src="<%=path%>/img/temp/timg (4).jpg" alt="Third slide" class="auto-size-img">
						<div class="carousel-caption">鲁F-12345</div>
					</div>
				</div>
				<!-- 轮播（Carousel）导航 -->
				<a class="carousel-control left" 
				    href="#myCarousel" data-slide="prev"><span class="glyphicon glyphicon-chevron-left" style="font-size:20px"></span></a> 
				<a class="carousel-control right"
					href="#myCarousel" data-slide="next"><span class="glyphicon glyphicon-chevron-right" style="font-size:20px"></span></a>
			</div>
		 </div>
<!-- 		 <div class="modal-footer"> -->
<!-- 				<button type="button" class="btn btn-default" data-dismiss="modal" aria-hidden="true">关&nbsp;&nbsp;闭</button> -->
<!-- 		 </div> -->
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
<script src="<%=path%>/js/layui/layui.js?v=<%=staticVersion%>"></script> <!-- layui core.js -->
<%-- <script src="<%=path%>/js/layui/upload.js?v=<%=staticVersion%>"></script> <!-- layui upload.js --> --%>

<!-- table收起 -->
<script src="<%=path%>/js/truck-inspect-common.js?v=<%=staticVersion%>"></script> <!-- Custom codes -->
<script src="<%=path%>/js/data-tables/jquery.dataTables.min.js?v=<%=staticVersion%>" type="text/javascript"></script>
<script src="<%=path%>/js/data-tables/dataTables.bootstrap.min.js?v=<%=staticVersion%>"	type="text/javascript"></script>
<script src="<%=path%>/js/moment.min.js?v=<%=staticVersion%>" type="text/javascript"></script>

<script src="<%=path%>/js/busi-js/query_ins_truck_list.js?v=<%=staticVersion%>" type="text/javascript"></script>

<!-- Script for this page -->
<script type="text/javascript">
//data dic
var map=parseData2Map('<yb:dataDic dataDicType="TRUCK_TYPE,TRUCK_BELONG_TYPE,TRUCK_DANGER_LEVEL,TRUCK_STATUS"/>');
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
// 		recordMainPageBrowserType("site_root");
		jsonPX();
// 		console.log(checkBrowser());
		dataTableInit();
	}); 
	
	function dataTableInit() {
		//param
		var truck_type = $("#qr_input_truck_type").val();
		var truck_status  = $("#qr_input_truck_status").val();
		var truck_license = $("#qr_input_truck_license").val();
		var includeStop=(true==$("#qr_cb_include_stop").is(':checked'))?"1":"0";
		
		//格式：列映射关系
		var aoColumns = '[{"mDataProp" : "TRUCK_ID","sTitle" : "序号"},'
					   + '{"mDataProp" : "TRUCK_LICENSE","sTitle" : "车牌/车架号"},'
					   + '{"mDataProp" : "TRUCK_TYPE","sTitle" : "类型/所属/危"},'
					   + '{"mDataProp" : "BRAND_CODE","sTitle" : "品牌/车型"},'
					   + '{"mDataProp" : "MAKE_DATE","sTitle" : "生产/上牌日期"},'
					   + '{"mDataProp" : "CONFIRM_BEGIN_DATE","sTitle" : "许可开始/截止"},'
					   + '{"mDataProp" : "TRUCK_HEIGHT","sTitle" : "长宽高/载重"},'
					   + '{"mDataProp" : "TRUCK_DESC","sTitle" : "检测历史"},'
					   + '{"mDataProp" : "IMG_COUNT","sTitle" : "图片"},'
					   + '{"mDataProp" : "FREEZE_TAG","sTitle" : "状态"},'
					   + '{"mDataProp" : "TRUCK_ID","sTitle" : "操作"}]';

		var reqData = {
			action : 'BASE_DATA_INS_TRUCK_LIST_QUERY_ACTION',
			params : 'params',
			TRUCK_TYPE:truck_type,
			TRUCK_STATUS:truck_status,
			TRUCK_LICENSE:truck_license,
			INCLUDE_STOP:includeStop
			};
		//example为table定义id ：10：需要加入操作按钮的列；[4,5]：需要转义的列（映射数据字典值）
		initInsTruckTable('insTruckList', aoColumns, reqData, '<%=path%>',10,[]);
		resetIFrameLength();//need ajax async:false,
		} 
	/*title tips- common method,dynamic bind*/
	$(function () { $("[data-toggle='tooltip']").tooltip();});
	
		//执行一个laydate实例 生产日期
		laydate.render({
		  elem: '#add_input_truck_make_date', //指定元素
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
		//上牌日期
		laydate.render({
		  elem: '#add_input_truck_license_date', //指定元素
		  max:0,
		  theme: '#6699CC'
		});

	function addTruck() {
		myModalInit('modal_add_ins_truck');
		activeModalInput('modal_add_ins_truck');
		
		$('#add_input_truck_license_date_query').hide();
		$('#add_input_truck_license_date').show();
		
		$('#add_input_truck_make_date_query').hide();
		$('#add_input_truck_make_date').show();
		
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

		var truckType = $('#add_input_truck_type').val();
		var truckImgData = $('#truck_img_upload').attr('src');//图片
		var truckId = $('#add_input_ins_truck_id').val();
		
		var data={};
		
		data.TRUCK_TYPE=truck_type;
		data.TRUCK_IMG_DATA=truckImgData;
		data.TRUCK_IMG_FILE_NAME=$('#truck_img_upload').attr('data-upload-filename');//图片名称
		
		data.TRUCK_LICENSE=$('#add_input_truck_license').val();
		data.TRUCK_VIN=$('#add_input_truck_vin').val();
		data.TRUCK_TYPE=$('#add_input_truck_type').val();//TODO
		data.TRUCK_D_LEVEL=$('#add_input_truck_danger_level').val();//TODO
		data.TRUCK_DRIVER_NAME=$('#add_input_truck_driver_name').val();
		data.TRUCK_BELONG_TYPE=$('#add_input_truck_belong_type').val();
		data.TRUCK_BRAND=$('#add_input_truck_brand_code').val();
		data.TRUCK_MODEL=$('#add_input_truck_model_code').val();
		data.TRUCK_MAKE_DATE=$('#add_input_truck_make_date').val();
		data.TRUCK_LICENSE_DATE=$('#add_input_truck_license_date').val();
		data.TRUCK_DESC=$('#add_input_truck_desc').val();
		
		data.TRUCK_LENGTH=$('#add_input_truck_size_length').val();
		data.TRUCK_WIDTH=$('#add_input_truck_size_width').val();
		data.TRUCK_HEIGHT=$('#add_input_truck_size_height').val();
		data.TRUCK_WEIGHT=$('#add_input_truck_weight').val();
		data.TRUCK_COLOR=$('#add_input_truck_color').val();
		
// 		data.IS_INSPACTOR=($('#add_input_is_inspactor').prop('checked'))?"1":"0";//

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
		
		$('#add_input_truck_license_date_query').show();
		$('#add_input_truck_license_date').hide();
		
		$('#add_input_truck_make_date_query').show();
		$('#add_input_truck_make_date').hide();
		
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
	
	function detailImg(truckId){
		$('#modal_ins_truck_img').modal({backdrop: 'static', keyboard: false});
		$('#myCarousel').carousel({
		    interval: 3000
		});
	
	}
	
	/*图片固定长宽比*/
	function imgResize(){
		var fWidth=$("div.modal-dialog").width();
		var fHeight=$("#modal_add_ins_truck_img_content").height();
		console.log(fWidth+"X"+fHeight);
		
		var iWidth=$("img").width();
		var iHeight=$("img").height();
		console.log(iWidth+"X"+iHeight);
		
// 		$("div.carousel-inner img").
// 		alert($('#myCarousel').css("height"));
// 		$('div.truck-img-slide').css("line-height","300px");
//  	600X334
//  	552X307
	}
	
	//truck img upload
	layui.use('upload', function(){
		  var $ = layui.jquery
		  ,upload = layui.upload;
		  
		  //普通图片上传
		  var uploadInst = upload.render({
		    elem: '#truck_img_upload'//选择文件
		    ,url: 'null' //不做上传
// 		    ,url: '/upload/'
		    ,accept:'file'
			,auto: false //选择文件后不自动上传 //与 choose配合使用
		    ,choose: function(obj){ //auto: false 时生效
			      obj.preview(function(index, file, result){
			            console.log(index); //得到文件索引
			            console.log(file.type); //得到文件类型
			            console.log(file.name); //得到文件类型
			            console.log(file); //得到文件对象
			            console.log(result); //得到文件base64编码，比如图片
			            if(file.type.indexOf("image")==-1){
			            	returnErrorMsgShow('modal_add_ins_truck','非图片文件格式，不能上传！');
			            	return;
			            }else{
			                $('#truck_img_upload').attr('src', result); //图片（base64）
			                $('#truck_img_upload').attr('data-upload-filename', file.name);//图片文件名称 
			            }
			      });
		    }
// 		    ,bindAction: '#btn_save_ins_truck'//不做上传按钮绑定，modal保存时，以base64字符串与其他参数一起提交action；或者作为绑定时，先上传成功，返回文件保存后地址信息，id等，再连同其他参数，再次提交后台action；
		    ,before: function(obj){
		      //预读本地文件示例，不支持ie8
		      obj.preview(function(index, file, result){
		            console.log(index); //得到文件索引
		            console.log(file); //得到文件对象
		            console.log(file.type); //得到文件类型
		            console.log(result); //得到文件base64编码，比如图片
		            if(file.type.indexOf("image")==-1){
		            	alert('file type is error');
		            	return false;
		            }
		        $('#truck_img_upload').attr('src', result); //图片链接（base64）
		      });
		    }
		    ,done: function(res){
		      //如果上传失败
		      if(res.code > 0){
// 		        return layer.msg('上传失败');
		        returnErrorMsgShow('modal_add_ins_truck','上传失败');
		        return;
		      }else{
		    	 returnSuccessMsgShow('modal_add_ins_truck','上传成功');//alert msg
		    	 
		      }
		      //上传成功
		    }
		    ,error: function(){
		      //演示失败状态，并实现重传
// 		      var demoText = $('#demoText');
// 		      demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
// 		      demoText.find('.demo-reload').on('click', function(){
// 		        uploadInst.upload();
// 		      });
		    	 returnErrorMsgShow('modal_add_ins_truck','上传失败');
		    }
		  });
	});
	
	//ul li a click
	function displayActionByRoleCode(obj){
		if($(obj).parent().hasClass("active")){
			return false;
		}
		var roleCode=$(obj).attr("data-obj_mid_code");
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
		$('#truck_map_obj_option').find('a[data-obj_mid_code]:first').click();
// 		$('#role_action_option').find('a[data-role_code]').first().click();
	}

</script>

</body>
</html>