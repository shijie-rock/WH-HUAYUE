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
        <h2 class="pull-left"><i class="icon-user"></i> 检查项目明细管理</h2>

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
       			<div class="form-group col-sm-3">
        			<label class="col-sm-5 control-label">检查项目名称</label>
         			<div class="col-sm-7"><input type="text" class="form-control" placeholder="检查项目名称" id="qr_input_obj_item_name"></div>
        		</div>
       			<div class="form-group col-sm-3">
        			<label class="col-sm-5 control-label">检查项目代码</label>
         			<div class="col-sm-7"><input type="text" class="form-control" placeholder="检查项目代码" id="qr_input_obj_item_code"></div>
        		</div>
        		<div class="form-group col-sm-3">
        			<label class="col-sm-5 control-label">一级分类名称</label>
         			<div class="col-sm-7"><input type="text" class="form-control" placeholder="一级分类名称" id="qr_input_obj_sup_name"></div>
        		</div>
       			<div class="form-group col-sm-3">
        			<label class="col-sm-5 control-label">一级分类代码</label>
         			<div class="col-sm-7"><input type="text" class="form-control" placeholder="一级分类代码" id="qr_input_obj_sup_code"></div>
        		</div>
       			<div class="form-group col-sm-3" style="margin-top:-5px">
        			<label class="col-sm-5 control-label">二级分类名称</label>
         			<div class="col-sm-7"><input type="text" class="form-control" placeholder="二级分类名称" id="qr_input_obj_mid_name"></div>
        		</div>
       			<div class="form-group col-sm-3 " style="margin-top:-5px">
        			<label class="col-sm-5 control-label">二级分类代码</label>
         			<div class="col-sm-7"><input type="text" class="form-control" placeholder="二级分类代码" id="qr_input_obj_mid_code"></div>
        		</div>
       			<div class="form-group col-sm-3" style="margin-top:-5px">
        			<label class="col-sm-5 control-label">三级分类名称</label>
         			<div class="col-sm-7"><input type="text" class="form-control" placeholder="三级分类名称" id="qr_input_obj_sub_name"></div>
        		</div>
       			<div class="form-group col-sm-3" style="margin-top:-5px">
        			<label class="col-sm-5 control-label">三级分类代码</label>
         			<div class="col-sm-7"><input type="text" class="form-control" placeholder="三级分类代码" id="qr_input_obj_sub_code"></div>
        		</div>
<!--         	<div class="form-group col-sm-12" style="margin-top:-5px"> -->
<!--         		 <div class="form-group col-sm-8"> </div> -->
        		 <div class="form-group col-sm-3" style="margin-top:-5px">
        			<label class="col-sm-5 control-label">上级项目名称</label>
         			<div class="col-sm-7"><input type="text" class="form-control" placeholder="上级项目名称" id="qr_input_obj_item_f_name"></div>
        			</div>
       				<div class="form-group col-sm-3" style="margin-top:-5px">
        			<label class="col-sm-5 control-label">上级项目代码</label>
         			<div class="col-sm-7"><input type="text" class="form-control" placeholder="上级项目代码" id="qr_input_obj_item_f_code"></div>
        			</div>
       				<div class="form-group col-sm-3" style="margin-top:-5px">
        			</div>
        		
					<div class="form-group col-sm-3" style="text-align: right;margin-top:-5px;">
						<label class="checkbox-inline"> <input type="checkbox" id="qr_cb_include_stop" value="1"> 包含已停用</label>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<!-- 查询按钮 begin -->
							<button type="button" class="btn btn-success" id="qr_bt_query_obj" onclick="dataTableInit();">查询</button>
							&nbsp;
							<button type="button" class="btn btn-danger clear" id="qr_bt_clear_obj" onclick="btnQueryReset();">清空</button>
	         		 </div>
         		 <!-- 查询按钮 end-->
<!--          		 </div> -->
			</div>
		</div>
		
		</div>
		
		</div>
		
          <!-- Table -->

            <div class="row">

              <div class="col-md-12">

                <div class="widget">

                <div class="widget-head">
                  <div class="pull-left">查询结果 &nbsp;&nbsp;&nbsp;<button type="button" class="btn btn-primary btn-xs" href="javascript:void(0);" onclick="addObjItem();" id="bt_add_obj_item">新增检查项目明细</button></div>
                  <div class="widget-icons pull-right">
                    <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a> 
                    <a href="#" class="wclose"><i class="icon-remove"></i></a>
                  </div>  
                  <div class="clearfix"></div>
                </div>

                  <div class="widget-content">
                  
                    <table id="objItemList"  class="table table-striped table-bordered table-hover">
                      <thead>
                        <tr>
                          <th width="4%"></th>
                          <th width="10%"></th>
                          <th width="10%"></th>
                          <th width="10%"></th>
                          <th width="10%"></th>
                          <th width="10%"></th>
                          <th width="10%"></th>
                          <th width="7%"></th>
                          <th width="13%"></th>
                          <th width="5%"></th>
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
		<input  type="hidden" id="opt_confirm_obj_item_id" value="" >
		<input  type="hidden" id="opt_confirm_obj_item_code" value="" >
		<input  type="hidden" id="opt_confirm_obj_item_name" value="" >
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
	
	<!-- Modal add obj item -->
	<div id="modal_add_obj_item" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content widget ">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title" id="modal_add_obj_item_title"></h4>
				</div>
				<div class="alert opt-result-alert"></div>
				<div class="modal-body modal-scrollable" id="modal_add_obj_item_content">

				 <div class="padd">
                 	 <div class="form-horizontal">
                 	 <input  type="hidden" id="add_input_obj_item_id" value="" >
                          					<div class="form-group">
<!--                                             <label class="control-label col-md-3" for="add_input_obj_item_code">一级分类代码</label> -->
<!--                                             <div class="col-md-3"> -->
<!--                                               <input type="text" class="form-control required-input" id="add_input_obj_item_code" placeholder="一级分类代码"> -->
<!--                                               <span class="text-danger" >*</span> -->
<!--                                             </div> -->
                                            <label class="control-label col-md-3" for="add_input_obj_item_code">检查项目代码</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_obj_item_code" placeholder="检查项目代码">
                                              <span class="text-danger">*</span>
                                            </div>
                                            <label class="control-label col-md-3" for="add_input_obj_item_name">检查项目名称</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_obj_item_name" placeholder="检查项目名称">
                                              <span class="text-danger">*</span>
                                            </div>
                                          </div>   
                                          <!--  -->
                                          <div class="form-group">
                                            <label class="control-label col-md-3" for="add_input_obj_item_desc">检查项目说明</label>
                                            <div class="col-md-8">
                                              <input type="text" class="form-control" id="add_input_obj_item_desc"  placeholder="检查项目说明">
                                            </div>
                                          </div>
                                          <div class="form-group">
                                            <label class="control-label col-md-3" for="add_input_obj_item_sort">检查项目排序</label>
                                            <div class="col-md-4">
                                              <input type="number" min="1" value="1" class="form-control required-input" id="add_input_obj_item_sort"  placeholder="分类排序">
                                            </div>
                                            <label class="control-label col-md-3" for="add_input_obj_item_f_code">上级项目代码</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_obj_item_f_code"  placeholder="上级项目代码">
                                            </div>
                                          </div>
                                       <div id="obj-item-check-info-div" class="form-horizontal">
                                       <!-- 检查内容相关 -->
                                       <center><hr class="modal-hr" style="margin-bottom:13px;"/></center>
                                          <div class="form-group">
                                            <label class="control-label col-md-3" for="add_input_obj_item_check_way">检查方式</label>
                                            <div class="col-md-4">
<!--                                               <input type="text" class="form-control required-input" id="add_input_obj_check_way" placeholder="检查方式"> -->
											  <yb:select dataSource="DATA_DIC.OBJ_CHECK_WAY"  selectClass="form-control subWidth"  includeNull="true" selectId="add_input_obj_item_check_way"/>
<!--                                               <span class="text-danger">*</span> -->
                                            </div>
                                            <label class="control-label col-md-3" for="add_input_obj_item_frequency">检查频次</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_obj_item_frequency" placeholder="检查频次" value="1">
<!--                                               <span class="text-danger">*</span> -->
                                            </div>
                                          </div>   
                                          <div class="form-group">
                                            <label class="control-label col-md-3" for="add_input_obj_item_photo">需要拍照</label>
                                            <div class="col-md-4">
			                                <label class="radio-inline"> <input type="radio" data-bind-name="add_input_obj_item_photo" name="add_input_obj_item_photo" id="add_input_obj_item_photo_y" value="1" checked> 拍照</label> 
											<label class="radio-inline"> <input type="radio" data-bind-name="add_input_obj_item_photo" name="add_input_obj_item_photo" id="add_input_obj_item_photo_x" value="0"> 不拍</label>
<!--                                               <span class="text-danger">*</span> -->
                                            </div>
                                            <label class="control-label col-md-3" for="add_input_obj_item_moment">检查阶段</label>
                                            <div class="col-md-4">
                                            <label class="checkbox-inline"> <input type="checkbox" id="add_input_obj_item_moment_before" data-bind-name="add_input_obj_item_moment" value="1">前</label>
                                            <label class="checkbox-inline"> <input type="checkbox" id="add_input_obj_item_moment_middle" data-bind-name="add_input_obj_item_moment"  value="2">中</label>
                                            <label class="checkbox-inline"> <input type="checkbox" id="add_input_obj_item_moment_after" data-bind-name="add_input_obj_item_moment"  value="3">后</label>
<!--                                               <span class="text-danger">*</span> -->
                                            </div>
                                          </div>   
                                      
                                          <div class="form-group">
                                            <label class="control-label col-md-3" for="add_input_obj_item_comliance_desc">合格标准</label>
                                            <div class="col-md-8">
                                              <input type="text" class="form-control" id="add_input_obj_item_comliance_desc"  placeholder="合格标准">
                                            </div>
                                          </div>
                                      
                                       </div>
                                       <!-- 项目所属类别信息 -->
                                       <div id="obj-item-check-class-info-div" class="form-horizontal">
                                       <center><hr class="modal-hr" style="margin-bottom:13px;"/></center>
                                         <div class="form-group">
                                             <label class="control-label col-md-3" for="add_input_obj_sup_code">一级分类代码</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_obj_sup_code" placeholder="一级分类代码">
<!--                                             <span class="text-danger">*</span> -->
                                             </div>
                                             <label class="control-label col-md-3" for="add_input_obj_sup_name">一级分类名称</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_obj_sup_name" placeholder="一级分类名称">
<!--                                             <span class="text-danger">*</span> -->
                                             </div>
                                          </div>
                                         <div class="form-group">
                                             <label class="control-label col-md-3" for="add_input_obj_mid_code">二级分类代码</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_obj_mid_code" placeholder="二级分类代码">
<!--                                             <span class="text-danger">*</span> -->
                                             </div>
                                             <label class="control-label col-md-3" for="add_input_obj_mid_name">二级分类名称</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_obj_mid_name" placeholder="二级分类名称">
<!--                                             <span class="text-danger">*</span> -->
                                             </div>
                                          </div>
                                         <div class="form-group">
                                             <label class="control-label col-md-3" for="add_input_obj_sub_code">三级分类代码</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_obj_sub_code" placeholder="三级分类代码">
                                            <span class="text-danger">*</span>
                                             </div>
                                             <label class="control-label col-md-3" for="add_input_obj_sub_name">三级分类名称</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_obj_sub_name" placeholder="三级分类名称">
<!--                                             <span class="text-danger">*</span> -->
                                             </div>
                                          </div>
                                       </div>
                                       <!-- 项目创建信息 -->
                                       <div id="obj-item-check-create-info-div" class="form-horizontal">
                                       <center><hr class="modal-hr" style="margin-bottom:13px;"/></center>
                                          <div class="form-group">
                                             <label class="control-label col-md-3" for="add_input_obj_item_create_time">创建时间</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_obj_item_create_time" placeholder="创建时间">
<!--                                             <span class="text-danger">*</span> -->
                                             </div>
                                             <label class="control-label col-md-3" for="add_input_obj_item_create_by">创建人</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_obj_item_create_by" placeholder="创建人">
<!--                                             <span class="text-danger">*</span> -->
                                             </div>
                                          </div>  
                                            <div class="form-group">
                                             <label class="control-label col-md-3" for="add_input_obj_item_update_time">修改时间</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_obj_item_update_time" placeholder="修改时间">
<!--                                             <span class="text-danger">*</span> -->
                                             </div>
                                             <label class="control-label col-md-3" for="add_input_obj_item_update_by">修改人</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_obj_item_update_by" placeholder="修改人">
<!--                                             <span class="text-danger">*</span> -->
                                             </div>
                                          </div>  
                                         </div>
								</div>
               		 </div>
				</div>
				<div class="modal-footer">
<!-- 					<button type="button" class="btn btn-primary">确定</button> --><!-- data-opt-type="" new/edit -->
					<button type="button" id="btn_save_obj_item" data-opt-type="" class="btn btn-success" data-loading-text="Loading" onclick="saveObjItemConfirm(this);">保&nbsp;&nbsp;存</button>
					<button type="button" class="btn btn-default" data-dismiss="modal" aria-hidden="true">关&nbsp;&nbsp;闭</button>

				</div>
			</div>
		</div>
	</div>


	<!-- Modal obj item parse-->
	<div id="modal_obj_item_parse" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content widget ">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title" id="modal_obj_item_parse_title"></h4>
				</div>
				<div class="alert opt-result-alert"></div>
				<div class="modal-body modal-scrollable" id="modal_obj_item_parse_content">
					 <div class="padd">
	                 	 	<div class="form-horizontal" id="modal_obj_item_parse_div_content">
							</div>
               		 </div>
					</div>
				<div class="modal-footer">
<!-- 					<button type="button" id="btn_save_ins_truck" data-opt-type="" class="btn btn-success" data-loading-text="Loading" onclick="saveInsTruckConfirm(this);">保&nbsp;&nbsp;存</button> -->
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
<script src="<%=path%>/js/bootstrap.js?v=<%=staticVersion%>"></script> <!-- Bootstrap -->
<!-- table收起 -->
<script src="<%=path%>/js/truck-inspect-common.js?v=<%=staticVersion%>"></script> <!-- Custom codes -->
<script src="<%=path%>/js/data-tables/jquery.dataTables.min.js?v=<%=staticVersion%>"	type="text/javascript"></script>
<script src="<%=path%>/js/data-tables/dataTables.bootstrap.min.js?v=<%=staticVersion%>"	type="text/javascript"></script>
<script src="<%=path%>/js/busi-js/query_obj_item_list.js?v=<%=staticVersion%>" type="text/javascript"></script>

<!-- Script for this page -->
<script type="text/javascript">
var map=parseData2Map('<yb:dataDic dataDicType="OBJ_CHECK_WAY"/>');
function dicTranse(value){
	return map[value]==null?value:map[value];
}

//初始化预查询
var initSupCode=getUrlParam("SUP_CODE"); //接收从三级页面某个一级类，跳转过来，直接查询其对应项目列表
var initMidCode=getUrlParam("MID_CODE"); //接收从三级页面某个二级分类，跳转过来，直接查询其对应项目列表
var initSubCode=getUrlParam("SUB_CODE"); //接收从三级页面某个三级分类，跳转过来，直接查询其对应项目列表
var initItemFCode=getUrlParam("ITEM_F_CODE"); //接收从项目页面某个项目代码，跳转过来，直接查询其对应项目列表
if(initSupCode!=null){
	$("#qr_input_obj_sup_code").val(initSupCode);
	//缺省一级代码
}
if(initMidCode!=null){
	$("#qr_input_obj_mid_code").val(initMidCode);
	//缺省二级代码
}
if(initSubCode!=null){
	$("#qr_input_obj_sub_code").val(initSubCode);
	//缺省三级代码
}
if(initItemFCode!=null){
	$("#qr_input_obj_item_f_code").val(initItemFCode);
	//缺省上级检查项目代码
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
		var itemName = $("#qr_input_obj_item_name").val();
		var itemCode = $("#qr_input_obj_item_code").val();
		var supName = $("#qr_input_obj_sup_name").val();
		var supCode = $("#qr_input_obj_sup_code").val();
		var midName = $("#qr_input_obj_mid_name").val();
		var midCode = $("#qr_input_obj_mid_code").val();
		var subName = $("#qr_input_obj_sub_name").val();
		var subCode = $("#qr_input_obj_sub_code").val();
		
		var itemFName = $("#qr_input_obj_item_f_name").val();
		var itemFCode = $("#qr_input_obj_item_f_code").val();
		
		var includeStop=(true==$("#qr_cb_include_stop").is(':checked'))?"1":"0";
		
		//格式：列映射关系
		var aoColumns = '[{"mDataProp" : "ID","sTitle" : "序号"}, '
					   + '{"mDataProp" : "CHECK_OBJ_CODE","sTitle" : "项目代码/名称"},'
					   + '{"mDataProp" : "SUP_CODE","sTitle" : "一级分类代码/名称"},'
					   + '{"mDataProp" : "MID_CODE","sTitle" : "二级分类代码/名称"},'
					   + '{"mDataProp" : "SUB_CODE","sTitle" : "三级分类代码/名称"},'
					   + '{"mDataProp" : "CHECK_OBJ_DESC","sTitle" : "项目说明"},'
					   + '{"mDataProp" : "OBJ_CHECK_WAY","sTitle" : "检查方式/频次/标准"},'
					   + '{"mDataProp" : "OBJ_CHECK_PHOTO","sTitle" : "拍照/检查阶段"},'
					   + '{"mDataProp" : "CREATE_TIME_STR","sTitle" : "创建信息"},'
					   + '{"mDataProp" : "FREEZE_TAG","sTitle" : "状态"},'
					   + '{"mDataProp" : "ID","sTitle" : "操作"}]';

		var reqData = {
			action : 'BUSI_DATA_OBJ_ITEM_QUERY_LIST_ACTION',
			params : 'params',
			ITEM_NAME:itemName,
			ITEM_CODE:itemCode,
			SUP_NAME:supName,
			SUP_CODE:supCode,
			MID_NAME:midName,
			MID_CODE:midCode,
			SUB_NAME:subName,
			SUB_CODE:subCode,
			F_NAME:itemFName,
			F_CODE:itemFCode,
			INCLUDE_STOP:includeStop
			};
		//example为table定义id ：10：需要加入操作按钮的列；[4,5]：需要转义的列（映射数据字典值）
		initObjItemTable('objItemList', aoColumns, reqData, '<%=path%>',10,[6]);
		resetIFrameLength();//need ajax async:false,
		} 
	/*title tips- common method,dynamic bind*/
	$(function () { $("[data-toggle='tooltip']").tooltip();});
	
	function addObjItem(){
		myModalInit('modal_add_obj_item');
		activeModalInput('modal_add_obj_item');
		//btn show
		$('#btn_save_obj_item').show();
		$('#btn_save_obj_item').attr('data-opt-type','NEW');
		// init
		$('#add_input_obj_item_frequency').val('1');
// 		$("#add_input_obj_item_photo_x").removeAttr('checked');
		$('#add_input_obj_item_photo_y').prop('checked',true);
// 		$('#add_input_obj_item_photo_y').attr('checked','checked');//error

		$('#add_input_obj_item_moment_before').prop('checked','checked');
		$('#add_input_obj_item_moment_middle').prop('checked','checked');
		$('#add_input_obj_item_moment_after').prop('checked','checked');
		
		$('#modal_add_obj_item_title').text('新增检查项目');
		$('#modal_add_obj_item').modal({backdrop: 'static', keyboard: false});
		
		//init 
		if(initSupCode!=null){
			$('#add_input_obj_sup_code').val(initSupCode);
		}
		if(initMidCode!=null){
			$('#add_input_obj_mid_code').val(initMidCode);
		}
		if(initSubCode!=null){
			$('#add_input_obj_sub_code').val(initSubCode);
		}
		
		if(initItemSupCode!=null){
			$('#add_input_obj_item_f_code').val(initItemFCode);
		}
		
		$('#obj-item-check-create-info-div').hide();//隐藏创建信息
		
	}
	
	function saveObjItemConfirm(obj){
		var modalOptType=$(obj).attr('data-opt-type');//new or eidt
		var data={};
		data.ITEM_CODE=$('#add_input_obj_item_code').val();
		data.ITEM_NAME=$('#add_input_obj_item_name').val();
		data.ITEM_DESC=$('#add_input_obj_item_desc').val();
		data.ITEM_SORT=$('#add_input_obj_item_sort').val();
		data.ITEM_F_CODE=$('#add_input_obj_item_f_code').val();/*上级项目代码*/
		data.ITEM_CHECK_WAY=$('#add_input_obj_item_check_way').val();
		data.ITEM_CHECK_FREQUENCY=$('#add_input_obj_item_frequency').val();
		data.ITEM_CHECK_COMLIANCE=$('#add_input_obj_item_comliance_desc').val();//合格标准
		
		data.ITEM_SUP_CODE=$('#add_input_obj_sup_code').val();
		data.ITEM_MID_CODE=$('#add_input_obj_mid_code').val();
		data.ITEM_SUB_CODE=$('#add_input_obj_sub_code').val();
		
		var photoTag=$("input[name='add_input_obj_item_photo']:checked").val(); 
// 		alert(photoTag);
		data.ITEM_PHOTO=photoTag;
		
		var checkMomentArray=[];//检查阶段
		$('#obj-item-check-info-div').find("input[data-bind-name='add_input_obj_item_moment']:checked").each(function(index,item){
			checkMomentArray.push($(item).attr('value'));
		});
		var checkMomentStr='';
		if(checkMomentArray!=null&&checkMomentArray.length>0){
			checkMomentStr=checkMomentArray.toString();//"xxx,xxx"
		}
		//检查阶段
		console.log(checkMomentStr);
		data.ITEM_CHECK_MOMENT_STR=checkMomentStr;
		
		var reqUrl;
		if('NEW'==modalOptType){
			reqUrl='<%=path%>/AjaxChannel?action=BUSI_DATA_OBJ_ITEM_ADD_ACTION';
		}
		else if('EDIT'==modalOptType){
			data.ITEM_ID=$('#add_input_obj_item_id').val();
			reqUrl='<%=path%>/AjaxChannel?action=BUSI_DATA_OBJ_ITEM_EDIT_ACTION';
		}
		else{
			returnErrorMsgShow('modal_add_obj_item','未知操作类型，请稍后重试')
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
							returnSuccessMsgShow('modal_add_obj_item',json.MSG)
							var fvTable=$("#objItemList").dataTable(); //datatable init current
							fvTable.fnDraw(false);
							$(obj).button('reset');
						}else{
// 							alert(json.MSG)	
							returnErrorMsgShow('modal_add_obj_item',json.MSG)	
							$(obj).button('reset');
						}
					},
				error : function(e) {
					console.log(e);
					}
				});
	}
	
	function detail(obj_item_id){
		myModalInit('modal_add_obj_item');
		//input readOnly
		readOnlyModalInput('modal_add_obj_item');
		
		//btn hide
		$('#btn_save_obj_item').hide();
		$('#btn_save_obj_item').attr('data-opt-type','QUERY');
		
		$('#obj-item-check-create-info-div').show();//显示创建信息
		
		var reqUrl='<%=path%>/AjaxChannel?action=BUSI_DATA_OBJ_ITEM_DETAIL_ACTION';
		//ajax begin
		$.ajax({
				type : 'POST',
				url:reqUrl,
				data: {OBJ_ITEM_ID:obj_item_id},
				dataType : 'json',
				success : function(json) {
					if(json.SUCCESS=='1'){
						$('#add_input_obj_item_id').val(json.OBJ_ITEM_BEAN.ID);
						$('#add_input_obj_item_code').val(json.OBJ_ITEM_BEAN.CHECK_OBJ_CODE);
						$('#add_input_obj_item_name').val(json.OBJ_ITEM_BEAN.CHECK_OBJ_NAME);
						$('#add_input_obj_item_desc').val(json.OBJ_ITEM_BEAN.CHECK_OBJ_DESC);
						$('#add_input_obj_item_sort').val(json.OBJ_ITEM_BEAN.SORT);
						$('#add_input_obj_item_f_code').val(json.OBJ_ITEM_BEAN.CHECK_OBJ_F_CODE);/*上级项目代码*/
						$('#add_input_obj_item_check_way').val(json.OBJ_ITEM_BEAN.OBJ_CHECK_WAY);
						$('#add_input_obj_item_frequency').val(json.OBJ_ITEM_BEAN.OBJ_CHECK_FREQUENCY);
						$('#add_input_obj_item_comliance_desc').val(json.OBJ_ITEM_BEAN.COMLIANCE_DESC);//合格标准
						
						$('#add_input_obj_sup_code').val(json.OBJ_ITEM_BEAN.OBJ_CLASS_CODE_SUP);
						$('#add_input_obj_mid_code').val(json.OBJ_ITEM_BEAN.OBJ_CLASS_CODE_MID);
						$('#add_input_obj_sub_code').val(json.OBJ_ITEM_BEAN.OBJ_CLASS_CODE_SUB);
						
						$('#add_input_obj_sup_name').val(json.OBJ_ITEM_BEAN.SUP_NAME);
						$('#add_input_obj_mid_name').val(json.OBJ_ITEM_BEAN.MID_NAME);
						$('#add_input_obj_sub_name').val(json.OBJ_ITEM_BEAN.SUB_NAME);
						
						$('#add_input_obj_item_create_time').val(json.OBJ_ITEM_BEAN.CREATE_TIME_STR);
						$('#add_input_obj_item_update_time').val(json.OBJ_ITEM_BEAN.UPDATE_TIME_STR);
						$('#add_input_obj_item_create_by').val(json.OBJ_ITEM_BEAN.CREATE_NAME);
						$('#add_input_obj_item_update_by').val(json.OBJ_ITEM_BEAN.UPDATE_NAME);
						
						if(json.OBJ_ITEM_BEAN.OBJ_CHECK_PHOTO>=1){
							$('#add_input_obj_item_photo_y').prop('checked',true);
						}else{
							$('#add_input_obj_item_photo_x').prop('checked',true);
						}
						if('1'==json.OBJ_ITEM_BEAN.OBJ_CHECK_BEF){
							$('#add_input_obj_item_moment_before').prop('checked','checked');
						}
						if('1'==json.OBJ_ITEM_BEAN.OBJ_CHECK_HAF){
							$('#add_input_obj_item_moment_middle').prop('checked','checked');
						}
						if('1'==json.OBJ_ITEM_BEAN.OBJ_CHECK_AFT){
							$('#add_input_obj_item_moment_after').prop('checked','checked');
						}
						
						$('#modal_add_obj_item_title').text('检查项目明细['+json.OBJ_ITEM_BEAN.CHECK_OBJ_CODE+']');
						
					}else{
							returnErrorMsgShow('modal_add_obj_item',json.MSG);
						}
					},
				error : function(e) {
					console.log(e);
					}
				});
		//ajax end
// 		$('#modal_add_obj_item_title').text('检查项目明细['+$('#add_input_obj_item_name').val()+']'); //EMPTY
		$('#modal_add_obj_item').modal({backdrop: 'static', keyboard: false});
		
	}
	
	//open modal
	function editObjItem(obj_item_id){
// 		init
		myModalInit('modal_add_obj_item');
		activeModalInput('modal_add_obj_item');
		$('#add_input_obj_item_code').attr('readonly','');
		$('#obj-item-check-create-info-div').show();//显示创建信息
		readOnlyModalInput('obj-item-check-create-info-div');
		
		//btn show
		$('#btn_save_obj_item').show();
		$('#btn_save_obj_item').attr('data-opt-type','EDIT');
		
		var reqUrl='<%=path%>/AjaxChannel?action=BUSI_DATA_OBJ_ITEM_DETAIL_ACTION';
		//ajax begin
		$.ajax({
				type : 'POST',
				url:reqUrl,
				data: {OBJ_ITEM_ID:obj_item_id},
				dataType : 'json',
				success : function(json) {
					if(json.SUCCESS=='1'){
						$('#add_input_obj_item_id').val(json.OBJ_ITEM_BEAN.ID);
						$('#add_input_obj_item_code').val(json.OBJ_ITEM_BEAN.CHECK_OBJ_CODE);
						$('#add_input_obj_item_name').val(json.OBJ_ITEM_BEAN.CHECK_OBJ_NAME);
						$('#add_input_obj_item_desc').val(json.OBJ_ITEM_BEAN.CHECK_OBJ_DESC);
						$('#add_input_obj_item_sort').val(json.OBJ_ITEM_BEAN.SORT);
						$('#add_input_obj_item_f_code').val(json.OBJ_ITEM_BEAN.CHECK_OBJ_F_CODE);/*上级项目代码*/
						$('#add_input_obj_item_check_way').val(json.OBJ_ITEM_BEAN.OBJ_CHECK_WAY);
						$('#add_input_obj_item_frequency').val(json.OBJ_ITEM_BEAN.OBJ_CHECK_FREQUENCY);
						$('#add_input_obj_item_comliance_desc').val(json.OBJ_ITEM_BEAN.COMLIANCE_DESC);//合格标准
						
						$('#add_input_obj_sup_code').val(json.OBJ_ITEM_BEAN.OBJ_CLASS_CODE_SUP);
						$('#add_input_obj_mid_code').val(json.OBJ_ITEM_BEAN.OBJ_CLASS_CODE_MID);
						$('#add_input_obj_sub_code').val(json.OBJ_ITEM_BEAN.OBJ_CLASS_CODE_SUB);
						
						$('#add_input_obj_sup_name').val(json.OBJ_ITEM_BEAN.SUP_NAME);
						$('#add_input_obj_mid_name').val(json.OBJ_ITEM_BEAN.MID_NAME);
						$('#add_input_obj_sub_name').val(json.OBJ_ITEM_BEAN.SUB_NAME);
						
						$('#add_input_obj_item_create_time').val(json.OBJ_ITEM_BEAN.CREATE_TIME_STR);
						$('#add_input_obj_item_update_time').val(json.OBJ_ITEM_BEAN.UPDATE_TIME_STR);
						$('#add_input_obj_item_create_by').val(json.OBJ_ITEM_BEAN.CREATE_NAME);
						$('#add_input_obj_item_update_by').val(json.OBJ_ITEM_BEAN.UPDATE_NAME);
						
						if(json.OBJ_ITEM_BEAN.OBJ_CHECK_PHOTO>=1){
							$('#add_input_obj_item_photo_y').prop('checked',true);
						}else{
							$('#add_input_obj_item_photo_x').prop('checked',true);
						}
						if('1'==json.OBJ_ITEM_BEAN.OBJ_CHECK_BEF){
							$('#add_input_obj_item_moment_before').prop('checked','checked');
						}
						if('1'==json.OBJ_ITEM_BEAN.OBJ_CHECK_HAF){
							$('#add_input_obj_item_moment_middle').prop('checked','checked');
						}
						if('1'==json.OBJ_ITEM_BEAN.OBJ_CHECK_AFT){
							$('#add_input_obj_item_moment_after').prop('checked','checked');
						}
						
						$('#modal_add_obj_item_title').text('检查项目编辑['+json.OBJ_ITEM_BEAN.CHECK_OBJ_CODE+']');
						
						}else{
// 							alert(json.MSG)	
							returnErrorMsgShow('modal_add_obj_item',json.MSG);	
						}
					},
				error : function(e) {
					console.log(e);
					}
				});
		//ajax end
		$('#modal_add_obj_item').modal({backdrop: 'static', keyboard: false});
	}
	
	/*opt confirm*/
	function optConfirm(opt_type,obj_item_code,obj_item_id){
		//auto top and height
// 		modal_auto_top($('div.modal-dialog'));

// 		$('#optModal').find("button.btn-primary:first").button('reset');
// 		clearMsgShow('optModal');
//		init common
		myModalInit('optModal');
		
		var title;
		var contentHtml;
		
		$("#opt_confirm_obj_item_id").val(obj_item_id);
		$("#opt_confirm_obj_item_code").val(obj_item_code);
		$("#opt_confirm_opt_type").val(opt_type);
		
		if("delete"==opt_type){
			title="删除检查项目["+obj_item_code+"]";
			
			contentHtml=
				"<div class='alert alert-danger' role='alert'>"
					+"<span class='glyphicon glyphicon-exclamation-sign' aria-hidden='true'></span> <span class='sr-only'>注意:</span>"
					+"<strong>注意:</strong>检查项目["+obj_item_code+"]将彻底删除，无法恢复 !"
				+"</div>";
// 			$('#optModal').find("button.btn-primary:first").click(startRole);

		}else if("stop"==opt_type)	{
			title="停用检查项目["+obj_item_code+"]";
			
			contentHtml=
				"<div class='alert alert-warning' role='alert'>"
					+"<span class='glyphicon glyphicon-question-sign' aria-hidden='true'></span> <span class='sr-only'>注意:</span>"
					+"<strong>注意:</strong>检查项目["+obj_item_code+"]将停用，可在检查项目管理恢复。"
				+"</div>";
// 			$('#optModal').find("button.btn-primary:first").click(stopRole);
		}else if("start"==opt_type){
			title="启用检查项目["+obj_item_code+"]";
			
			contentHtml=
				"<div class='alert alert-info' role='alert'>"
					+"<strong>注意:</strong><span class='glyphicon glyphicon-info-sign' aria-hidden='true'></span> <span class='sr-only'>注意:</span>"
					+"检查项目["+obj_item_code+"]将被启用。"
				+"</div>";
// 			$('#optModal').find("button.btn-primary:first").click(deleteRole);
		}
		$('#optModal').find("h4.modal-title:first").text(title);
		$('#optModal').find("div.modal-body:first").html(contentHtml);
		$('#optModal').modal({backdrop: 'static', keyboard: false});
	}
	
	function optConfirmDone(obj){
		var objItemId=$("#opt_confirm_obj_item_id").val();
		var objItemCode=$("#opt_confirm_obj_item_code").val();
		var optType=$("#opt_confirm_opt_type").val();
		
		$(obj).button('loading');
		var reqUrl='<%=path%>/AjaxChannel?action=BUSI_DATA_OBJ_ITEM_MOD_ACTION';
		//ajax begin
		$.ajax({
				type : 'POST',
				url:reqUrl,
				data: {OBJ_ITEM_ID:objItemId,OPT_TYPE:optType},
				dataType : 'json',
				success : function(json) {
					if(json.SUCCESS=='1'){
							returnSuccessMsgShow('optModal',json.MSG);//alert msg
// 							$(obj).button('complete');//button 
							var fvTable=$("#objItemList").dataTable(); //datatable init current
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
	
	//整改设置
	function parseObjCfg(itemCode,itemId){
		$('#modal_obj_item_parse_title').text('检查项目['+itemCode+']检查项目整改设置');
		$('#modal_obj_item_parse #modal_obj_item_parse_div_content').html('开发中...');
<%-- 		var reqUrl='<%=path%>/AjaxChannel?action=BASE_DATA_INS_TRUCK_CHECK_OBJ_DETAIL_ACTION'; --%>
		//ajax begin
		/**
		$.ajax({
				type : 'POST',
				url:reqUrl,
				data: {TRUCK_ID:truckId},
				dataType : 'json',
				success : function(json) {
					if(json.SUCCESS=='1'){
						$('#modal_sub_ins_truck #modal_sub_ins_truck_div_content').html(json.SUB_LIST_HTML);
						readOnlyModalInput('modal_sub_ins_truck');	
						}else{
							returnErrorMsgShow('modal_sub_ins_truck',json.MSG)	
						}
					},
				error : function(e) {
					console.log(e);
					}
				});
		*/
		//ajax end
// 		$('#modal_add_ins_truck_title').text('车辆明细['+$('#add_input_truck_type').val()+']'); //EMPTY
		returnErrorMsgShow('modal_obj_item_parse','开发中...')	
		$('#modal_obj_item_parse').modal({backdrop: 'static', keyboard: false});
	}
	
	function optEntChildItem(itemFCode,itemId){
		  window.location.href="object_item_manager.jsp?ITEM_F_CODE="+itemFCode; 
	}
//自动填充
	$(function() {
// 		 alert('auto');//大类代码、名称绑定联想输入
		 /*大类代码、名称绑定联想输入 begin*/
// 		 $("#qr_input_obj_sup_name,#qr_input_obj_sup_code").autocomplete({
		 $("#qr_input_obj_sup_name").autocomplete({
				source: function( request, response ) {
//			 	var userId = $("#birds").val();
					$.ajax({
						type: "POST",
						url: "<%=path%>/AjaxChannel?action=QUERY_OBJ_CLASS_CODE_BY_CONDITIION_ACTION",
						dataType: "json",
						data: {
							SUP_NAME:$("#qr_input_obj_sup_name").val(),
// 							SUP_CODE:$("#qr_input_obj_sup_code").val(),
							QUERY_TYPE:"SUP",
							maxRows: 10
						},
						success: function( data ) {
							if(data.OBJ_CLASS_DATA!=undefined){
								response( $.map(data.OBJ_CLASS_DATA, function(bean) {
									return {
										supCode:bean.SUP_CODE,
										supId:bean.SUP_ID,
										value: bean.SUP_NAME //显示的列
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
					var oldSupName=$("#qr_input_obj_sup_name");
					var oldSupCode=$("#qr_input_obj_sup_code");
					var newSupName=ui.item.value;
					var newSupCode=ui.item.supCode;
					if(oldSupCode!=newSupCode||oldSupName!=newSupName){
						$("#qr_input_obj_sup_name").val(ui.item.value);
						$("#qr_input_obj_sup_code").val(ui.item.supCode);
						//clear children
						$("#qr_input_obj_mid_name").val('');
						$("#qr_input_obj_mid_code").val('');
						$("#qr_input_obj_sub_name").val('');
						$("#qr_input_obj_sub_code").val('');
						$("#qr_input_obj_item_f_name").val('');
						$("#qr_input_obj_item_f_code").val('');
					}
					
// 					$("#qr_input_obj_sup_id").val(ui.item.supId);
					//setParentBusInfo(ui.item.Id, ui.item.CustName);
				},
				open: function() {},//结果列表弹出时
				close: function() {},//结果列表关闭时
				change: function( event, ui ) {}//当值改变时，ui.item为选中的项
			});
		 /*大类代码、名称绑定联想输入 end*/
		 
		 /*中类代码、名称绑定联想输入 begin*/
// 		 $("#qr_input_obj_mid_name,#qr_input_obj_mid_code").autocomplete({
		 $("#qr_input_obj_mid_name").autocomplete({
				source: function( request, response ) {
//			 	var userId = $("#birds").val();
					$.ajax({
						type: "POST",
						url: "<%=path%>/AjaxChannel?action=QUERY_OBJ_CLASS_CODE_BY_CONDITIION_ACTION",
						dataType: "json",
						data: {
							MID_NAME:$("#qr_input_obj_mid_name").val(),
// 							MID_CODE:$("#qr_input_obj_mid_code").val(),
							SUP_NAME:$("#qr_input_obj_sup_name").val(),
							SUP_CODE:$("#qr_input_obj_sup_code").val(),
							QUERY_TYPE:"MID",
							maxRows: 10
						},
						success: function( data ) {
							if(data.OBJ_CLASS_DATA!=undefined){
								response( $.map(data.OBJ_CLASS_DATA, function(bean) {
									return {
										midCode:bean.MID_CODE,
										midId:bean.MID_ID,
										value: bean.MID_NAME, //显示的列
										
										supCode:bean.SUP_CODE,
										supName:bean.SUP_NAME
									}
								}));
							}else{
								$('#ui-id-2').hide();
// 								alert(1);
							}
						}
					});
				},
				minLength: 0,
				max: 10,
				select: function( event, ui ) {
					var oldMidName=$("#qr_input_obj_mid_name");
					var oldMidCode=$("#qr_input_obj_mid_code");
					var newMidName=ui.item.value;
					var newMidCode=ui.item.midCode;
					if(oldMidCode!=newMidCode||oldMidName!=newMidName){
						$("#qr_input_obj_mid_name").val(ui.item.value);
						$("#qr_input_obj_mid_code").val(ui.item.midCode);
						
						$("#qr_input_obj_sup_name").val(ui.item.supName);
						$("#qr_input_obj_sup_code").val(ui.item.supCode);
						//clear children
						$("#qr_input_obj_sub_name").val('');
						$("#qr_input_obj_sub_code").val('');
						$("#qr_input_obj_item_f_name").val('');
						$("#qr_input_obj_item_f_code").val('');
					}
					
// 					$("#qr_input_obj_sup_id").val(ui.item.supId);
					//setParentBusInfo(ui.item.Id, ui.item.CustName);
				},
				open: function() {},//结果列表弹出时
				close: function() {},//结果列表关闭时
				change: function( event, ui ) {}//当值改变时，ui.item为选中的项
			});
		 /*中类代码、名称绑定联想输入 end*/
		 
		 /*小类代码、名称绑定联想输入 begin*/
// 		 $("#qr_input_obj_sub_name,#qr_input_obj_sub_code").autocomplete({
		 $("#qr_input_obj_sub_name").autocomplete({
				source: function( request, response ) {
//			 	var userId = $("#birds").val();
					$.ajax({
						type: "POST",
						url: "<%=path%>/AjaxChannel?action=QUERY_OBJ_CLASS_CODE_BY_CONDITIION_ACTION",
						dataType: "json",
						data: {
							SUB_NAME:$("#qr_input_obj_sub_name").val(),
// 							SUB_CODE:$("#qr_input_obj_sub_code").val(),
							MID_NAME:$("#qr_input_obj_mid_name").val(),
							MID_CODE:$("#qr_input_obj_mid_code").val(),
							SUP_NAME:$("#qr_input_obj_sup_name").val(),
							SUP_CODE:$("#qr_input_obj_sup_code").val(),
							QUERY_TYPE:"SUB",
							maxRows: 10
						},
						success: function( data ) {
							if(data.OBJ_CLASS_DATA!=undefined){
								response( $.map(data.OBJ_CLASS_DATA, function(bean) {
									return {
										subCode:bean.SUB_CODE,
										subId:bean.SUB_ID,
										value: bean.SUB_NAME, //显示的列
										
										supCode:bean.SUP_CODE,
										supName:bean.SUP_NAME,
										midCode:bean.MID_CODE,
										midName:bean.MID_NAME
									}
								}));
							}else{
								$('#ui-id-3').hide();
// 								alert(1);
							}
						}
					});
				},
				minLength: 0,
				max: 10,
				select: function( event, ui ) {
					var oldSubName=$("#qr_input_obj_sub_name");
					var oldSubCode=$("#qr_input_obj_sub_code");
					var newSubName=ui.item.value;
					var newSubCode=ui.item.subCode;
					if(oldSubCode!=newSubCode||oldSubName!=newSubName){
						
						$("#qr_input_obj_sub_name").val(ui.item.value);
						$("#qr_input_obj_sub_code").val(ui.item.subCode);
						
						$("#qr_input_obj_mid_name").val(ui.item.midName);
						$("#qr_input_obj_mid_code").val(ui.item.midCode);
						
						$("#qr_input_obj_sup_name").val(ui.item.supName);
						$("#qr_input_obj_sup_code").val(ui.item.supCode);

						//clear children
						$("#qr_input_obj_item_f_name").val('');
						$("#qr_input_obj_item_f_code").val('');
					}
					
// 					$("#qr_input_obj_sup_id").val(ui.item.supId);
					//setParentBusInfo(ui.item.Id, ui.item.CustName);
				},
				open: function() {},//结果列表弹出时
				close: function() {},//结果列表关闭时
				change: function( event, ui ) {}//当值改变时，ui.item为选中的项
			});
		 /*小类代码、名称绑定联想输入 end*/
		 
	});

</script>

</body>
</html>