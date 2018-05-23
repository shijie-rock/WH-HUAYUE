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
  <link href="<%=path%>/style/truck-tree.css?v=<%=staticVersion%>" rel="stylesheet"  />
</head>

<body class="content-body">


      <!-- Page heading -->
      <div class="page-head">
        <h2 class="pull-left"><i class="icon-user"></i> 检查项目树形图</h2>

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
	    <div class="matter" id="div-matter" > 
        <div class="container">
		
          <!-- Table -->

            <div class="row">

              <div class="col-md-6"><!-- tree div -->

                <div class="widget">

                <div class="widget-head">
                  <div class="pull-left">检查项目树形图 &nbsp;&nbsp;&nbsp;
                  <button type="button" class="btn btn-primary btn-xs tree_btn" href="javascript:void(0);" onclick="openUp();" id="bt_open_up">收起</button>
                  &nbsp;&nbsp;&nbsp;
                  <button type="button" class="btn btn-primary btn-xs tree_btn" href="javascript:void(0);" onclick="location.reload();" id="bt_refresh_tree">刷新</button>
                  &nbsp;&nbsp;&nbsp;
                  <button type="button" class="btn btn-primary btn-xs tree_btn" href="javascript:void(0);" onclick="editDisplay(this);" id="bt_edit_tree" data-display-tag="0">打开编辑</button>
                  
                  </div>
                  <div class="widget-icons pull-right">
                    <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a> 
                    <a href="#" class="wclose"><i class="icon-remove"></i></a>
                  </div>  
                  <div class="clearfix"></div>
                </div>

                  	  <div class="widget-content">
							<div class="tree well">
								<ul>
									<li>
									<span id="Parent_1"><i class="icon-minus"></i>检查项目</span> 
<!-- 									<a href="#">增加节点/删除节点</a> -->
										<ul id="tree_content">
										<!-- begin -->
										<!-- end -->
										${OBJ_ITEM_TREE_DATA}
										</ul>
									</li>
									
<!-- 									<li><span id="Parent_2"><i class="glyphicon glyphicon-folder-open"></i> 服务器</span> <a href="#">增加节点/删除节点</a> -->
<!-- 										<ul> -->
<!-- 											<li><span><i class="glyphicon glyphicon-leaf"></i> 工作站服务器</span> <a href="#">增加节点/删除节点</a></li> -->
<!-- 										</ul> -->
<!-- 									</li> -->
								</ul>
							</div>


							<div class="widget-foot">
                      <div class="clearfix"></div> 
                    </div>
                  </div>
                </div>
              </div>
              
      <div class="col-md-6" style="padding-left: 0px;"><!-- item list -->
              
             <div class="widget">
             	<!-- 查询条件，显示 -->
             	<input  type="hidden" id="tree_query_condition_path_name" value="" ><!--车头>sss>sss  -->
             	<input  type="hidden" id="tree_query_condition_path_code" value="" ><!-- 0001|0002|0003 -->
<!--              	<input  type="hidden" id="tree_query_condition_mid_name" value="" > -->
<!--              	<input  type="hidden" id="tree_query_condition_sub_name" value="" > -->
             	
            	<input  type="hidden" id="tree_query_class_code" value="" ><!-- 检查项目类代码：点击项目一级类，二级类，三级类时赋值，并执行查询 -->
				<input  type="hidden" id="tree_query_class_level_type" value="" ><!--检查项目类级别：（sup，mid，sub） 点击项目一级类，二级类，三级类时赋值，并执行查询 -->
              	<input  type="hidden" id="tree_query_item_deep_level" value="" ><!-- 检查项目层级深度 -->
                <div class="widget-head">
                  <div class="pull-left">查询结果 &nbsp;&nbsp;&nbsp;<button type="button" class="btn btn-primary btn-xs" href="javascript:void(0);" onclick="addObjItem();" id="bt_add_obj_item">新增项目</button></div>
                  <div id="tree_query-condition-summry" class="query-condition-summry"></div>
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
                          <th width="10%"></th>
                          <th width="20%"></th>
                          <th width="20%"></th>
                          <th width="25%"></th>
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
              
   </div><!-- end list -->
            </div>
        </div>
	</div>

	<!-- delete Modal begin-->
	<div id="optModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
		<input  type="hidden" id="opt_confirm_mod_obj_code" value="" >
		<input  type="hidden" id="opt_confirm_mod_obj_up_code" value="" >
		<input  type="hidden" id="opt_confirm_mod_obj_name" value="" >
		<input  type="hidden" id="opt_confirm_opt_type" value="" >
		<input  type="hidden" id="opt_confirm_mod_obj_level" value="" >
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
					<button type="button" class="btn btn-default" data-dismiss="modal" aria-hidden="true"  onclick='saveCloseWin()'>关&nbsp;&nbsp;闭</button>
				</div>
			</div>
		</div>
	</div>
	<!-- delete Modal end -->
	
	<!-- Modal add obj mid -->
	<div id="modal_add_obj_sub" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content widget ">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title" id="modal_add_obj_sub_title"></h4>
				</div>
				<div class="alert opt-result-alert"></div>
				<div class="modal-body modal-scrollable" id="modal_add_obj_sub_content">

				 <div class="padd">
                 	 <div class="form-horizontal">
                 	 <input  type="hidden" id="add_input_obj_sub_id" value="" >
                 	 
                 	 <input  type="hidden" id="query_input_obj_level" value="" >
                 	 <input  type="hidden" id="query_input_obj_upCode" value="" >
                 	 <input  type="hidden" id="query_input_obj_nowCode" value="" >
                 	 
                 	 <input  type="hidden" id="add_input_obj_level" value="" >
                 	 
                 	 <div class="form-group">
			                               <label class="control-label col-md-3" for="add_input_level">选择节点级别</label>
			                                <div class="col-md-4">
			                                <label class="radio-inline"> <input type="radio" data-bind-name="add_input_level" name="add_input_level" id="add_input_same_level" value="SAME_LEVEL" checked> 同级节点</label> 
											<label class="radio-inline"> <input type="radio" data-bind-name="add_input_level" name="add_input_level" id="add_input_inner_level" value="INNER_LEVEL"> 下级节点</label>
			                             	</div>
			                             	
			                             	 <label class="control-label col-md-3" for="add_input_obj_upper_code">上级分类代码</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_obj_upper_code" placeholder="上级分类代码" value="" readonly="readonly">
                                            <span class="text-danger">*</span>
                                             </div>
			                             	
					</div>
                 	 
                          					<div class="form-group">
<!--                                             <label class="control-label col-md-3" for="add_input_obj_sub_code">三级分类代码</label> -->
<!--                                             <div class="col-md-3"> -->
<!--                                               <input type="text" class="form-control required-input" id="add_input_obj_sub_code" placeholder="三级分类代码"> -->
<!--                                               <span class="text-danger" >*</span> -->
<!--                                             </div> -->
                                            <label class="control-label col-md-3" for="add_input_obj_class_code">项目级别代码</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_obj_class_code" placeholder="项目级别代码">
                                              <span class="text-danger">*</span>
                                            </div>
                                            <label class="control-label col-md-3" for="add_input_obj_class_name">项目级别名称</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_obj_class_name" placeholder="项目级别名称">
                                              <span class="text-danger">*</span>
                                            </div>
                                          </div>   
                                          <!--  -->
                                          <div class="form-group">
                                            <label class="control-label col-md-3" for="add_input_obj_class_desc">项目级别说明</label>
                                            <div class="col-md-8">
                                              <input type="text" class="form-control" id="add_input_obj_class_desc"  placeholder="项目级别说明">
                                            </div>
                                          </div>
                                          <div class="form-group">
                                            <label class="control-label col-md-3" for="add_input_obj_class_sort">分类排序</label>
                                            <div class="col-md-4">
                                              <input type="number" min="1" value="1" class="form-control" id="add_input_obj_class_sort"  placeholder="分类排序">
                                            </div>

                                          </div>
								</div>
               		 </div>
				</div>
				<div class="modal-footer">
<!-- 					<button type="button" class="btn btn-primary">确定</button> --><!-- data-opt-type="" new/edit -->
					<button type="button" id="btn_save_obj_sub" data-opt-type="" class="btn btn-success" data-loading-text="Loading" onclick="saveObjSubConfirm(this);">保&nbsp;&nbsp;存</button>
					<button type="button" class="btn btn-default" data-dismiss="modal" aria-hidden="true" onclick='saveCloseWin()'>关&nbsp;&nbsp;闭</button>

				</div>
			</div>
		</div>
	</div>
<!-- 查看节点,编辑 begin -->
	<div id="modal_add_obj_mid" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content widget ">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title" id="modal_add_obj_mid_title"></h4>
				</div>
				<div class="alert opt-result-alert"></div>
				<div class="modal-body modal-scrollable" id="modal_add_obj_mid_content">

				 <div class="padd">
                 	 <div class="form-horizontal">
                 	 <input  type="hidden" id="add_input_obj_check_id" value="" ><!-- 检查项目class id -->
                 	 <input  type="hidden" id="add_input_obj_check_level" value="" ><!-- 检查项目calss level -->
                          					<div class="form-group">

                                            <label class="control-label col-md-3" for="add_input_obj_check_code">项目分类代码</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_obj_check_code" placeholder="项目分类代码">
                                              <span class="text-danger">*</span>
                                            </div>
                                            <label class="control-label col-md-3" for="add_input_obj_check_name">项目分类名称</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_obj_check_name" placeholder="项目分类名称">
                                              <span class="text-danger">*</span>
                                            </div>
                                          </div>   

                                          <div class="form-group">

                                             <label class="control-label col-md-3" for="add_input_obj_upper_code">上级分类代码</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_obj_upper_code" placeholder="上级分类代码">
<!--                                             <span class="text-danger">*</span> -->
                                             </div>
                                             <label class="control-label col-md-3" for="add_input_obj_upper_name">上级分类名称</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_obj_upper_name" placeholder="上级分类名称">
<!--                                             <span class="text-danger">*</span> -->
                                             </div>
                                          </div>
                                                                                    <!--  -->
                                          <div class="form-group">
                                            <label class="control-label col-md-3" for="add_input_obj_check_desc">项目分类说明</label>
                                            <div class="col-md-8">
                                              <input type="text" class="form-control" id="add_input_obj_check_desc"  placeholder="项目分类说明">
                                            </div>
                                          </div>
                                                                                    <!--  -->
                                          <div class="form-group">
                                            <label class="control-label col-md-3" for="add_input_obj_check_sort">项目分类排序</label>
                                            <div class="col-md-4">
                                              <input type="number" min="1" value="1" class="form-control" id="add_input_obj_check_sort"  placeholder="项目分类排序">
                                            </div>
                                          </div>
                                       <div id="obj-create-info-div" class="form-horizontal">
                                       <center><hr class="modal-hr" style="margin-bottom:13px;"/></center>
                                          <div class="form-group">
                                             <label class="control-label col-md-3" for="add_input_obj_check_create_time">创建时间</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_obj_check_create_time" placeholder="创建时间">
<!--                                             <span class="text-danger">*</span> -->
                                             </div>
                                             <label class="control-label col-md-3" for="add_input_obj_check_create_by">创建人</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_obj_check_create_by" placeholder="创建人">
<!--                                             <span class="text-danger">*</span> -->
                                             </div>
                                          </div>  
                                            <div class="form-group">
                                             <label class="control-label col-md-3" for="add_input_obj_check_update_time">修改时间</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_obj_check_update_time" placeholder="修改时间">
<!--                                             <span class="text-danger">*</span> -->
                                             </div>
                                             <label class="control-label col-md-3" for="add_input_obj_check_update_by">修改人</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_obj_check_update_by" placeholder="修改人">
<!--                                             <span class="text-danger">*</span> -->
                                             </div>
                                          </div>  
                                         </div>
								</div>
               		 </div>
				</div>
				<div class="modal-footer">
<!-- 					<button type="button" class="btn btn-primary">确定</button> --><!-- data-opt-type="" new/edit -->
					<button type="button" id="btn_save_obj_mid" data-opt-type="" class="btn btn-success" data-loading-text="Loading" onclick="saveObjCheckConfirm(this);">保&nbsp;&nbsp;存</button>
					<button type="button" class="btn btn-default" data-dismiss="modal" aria-hidden="true"  onclick='saveCloseWin()'>关&nbsp;&nbsp;闭</button>

				</div>
			</div>
		</div>
	</div>
<!-- 查看节点 end -->

	
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

<!-- 停用删除 begin-->
	<!-- delete Modal begin-->
	<div id="optItemModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
					<button type="button" class="btn btn-primary" data-loading-text="Loading" onclick="optItemConfirmDone(this);">确&nbsp;&nbsp;定</button>
<!-- 				<button type="button" class="btn btn-primary" data-loading-text="Loading" onclick="saveRole(this);">确&nbsp;&nbsp;定</button> -->
					<button type="button" class="btn btn-default" data-dismiss="modal" aria-hidden="true">关&nbsp;&nbsp;闭</button>
				</div>
			</div>
		</div>
	</div>
	<!-- delete Modal end -->

<!-- 停用删除 end -->
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
<!-- <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>注意与bootstrap的先后顺序，jquery ui 与 bootstrap tips冲突 -->
<%-- <script src="<%=path%>/js/jquery-ui.min.js?v=<%=staticVersion%>"></script><!-- 注意与bootstrap的先后顺序，jquery ui 与 bootstrap tips冲突 --> --%>
<script src="<%=path%>/js/jquery-ui.min-1.12.1.js?v=<%=staticVersion%>"></script><!-- 注意与bootstrap的先后顺序，jquery ui 与 bootstrap tips冲突 -->
<script src="<%=path%>/js/bootstrap.js?v=<%=staticVersion%>"></script> <!-- Bootstrap -->
<!-- table收起 -->
<script src="<%=path%>/js/truck-inspect-common.js?v=<%=staticVersion%>"></script> <!-- Custom codes -->
<script src="<%=path%>/js/data-tables/jquery.dataTables.min.js?v=<%=staticVersion%>"	type="text/javascript"></script>
<script src="<%=path%>/js/data-tables/dataTables.bootstrap.min.js?v=<%=staticVersion%>"	type="text/javascript"></script>
<script src="<%=path%>/js/busi-js/query_obj_sub_list.js?v=<%=staticVersion%>" type="text/javascript"></script>
<script src="<%=path%>/js/jquery.cookie.js?v=<%=staticVersion%>" type="text/javascript"></script>
<script src="<%=path%>/js/busi-js/query_obj_item_list_tree.js?v=<%=staticVersion%>" type="text/javascript"></script>


<!-- Script for this page -->
<script type="text/javascript">
var map=parseData2Map('<yb:dataDic dataDicType="OBJ_CHECK_WAY"/>');
function dicTranse(value){
	return map[value]==null?value:map[value];
}

var isChange=0;//是否做过新增；0：否，1：是

// $("#div-matter").resize(function() {
//     console.log('div.matter resize:='+$(this).height());
// });

$(function () {

	  $('.tree li').addClass('parent_li');//20180319 1，2，3级都增加 parent_li class
// 	  $('.tree li:has(ul)').addClass('parent_li');//20180319 1，2，3级都增加 parent_li class
	  $('.tree li.parent_li > span').on('click', function (e) {
	    var children = $(this).parent('li.parent_li').find(' > ul > li');
	    if (children.is(":visible")) {
// 	      children.hide();
	      children.hide('fast');
// 	      children.hide('fast',function(){resetIFrameLength();});
	      $(this).attr('title', '展开').find(' > i').addClass('icon-plus').removeClass('icon-minus');
	    } else {
// 	      children.show();
	      children.show('fast');
// 	      children.show('fast',function(){resetIFrameLength();});
	      $(this).attr('title', '收起').find(' > i').addClass('icon-minus').removeClass('icon-plus');
	    }
	    e.stopPropagation();
	    console.log('click:'+$(this).attr('data-tree-path-name'));
	    //点击赋值查询条件,写入cookies，并执行查询
	    //
	    var queryConditionTitle='';
	    if($(this).attr('data-tree-path-name')!=null){
	    	queryConditionTitle=$(this).attr('data-tree-path-name');
	    }
		$.cookie('tree_query_data-tree-path-name',queryConditionTitle);//写入cookies
		$("#tree_query_condition_path_name").val(queryConditionTitle);
		
	    var queryConditionPathCode='';
	    if($(this).attr('id')!=null){
	    	queryConditionPathCode=$(this).attr('id');
	    }
		$.cookie('tree_query_data-tree-path-code',queryConditionPathCode);//写入cookies
		$("#tree_query_condition_path_code").val(queryConditionPathCode);
		
		var classCode='';
	    if($(this).attr('data-obj-class-code')!=null){
	    	classCode=$(this).attr('data-obj-class-code');
	    }
		$.cookie('obj_item_query_class_code',classCode);//条件类别代码
		var classLevelType='';
	    if($(this).attr('data-obj-class-level-code')!=null){
	    	classLevelType=$(this).attr('data-obj-class-level-code');
	    }
		$.cookie('obj_item_query_class_level_type',classLevelType);//SUP:1,MID:2,SUB:3
		
		$("#tree_query_class_code").val(classCode);
		$("#tree_query_class_level_type").val(classLevelType);
			//add item modal
			queryConditionDisplay();//默认显示查询条件
		
			dataTableInit();//query table
	    	//滚动条高度

			//需要div Y 隐藏滚动条,延迟执行自适应高度
			lazyResizeDiv();
			
	  });
	  
	  function lazyResizeDiv(){
	    	setTimeout(function(){
	    		//延迟执行-配合 fast
	    		resetIFrameLength();//need ajax async:false,
	    	},300);
	  }
	  
      /*拖动*/
	  $("li.parent_li").draggable({
		  containment:"div.tree well",
		  handle: "span.data-detail",
		  revert: "invalid",
		  delay: 200,
		  opacity: 1,
		  zIndex: 2700,
		  helper: "clone",
		  drag:function(){
		  	$(this).addClass('drag-on-doing');
	  	  },
	  	  stop:function(){
	  		$(this).removeClass('drag-on-doing');  
	  	  }
	  });
      /**/
      $("li span.data-detail").droppable({
          activeClass: "drag-on-doing",
//           hoverClass: "drag-on-doing",
          tolerance     : "pointer",   
//        tolerance     : "touch",   
          over:function(event, ui ){
        	  $(this).addClass('drag-on-scope'); 
        	  console.log('over');
          },
          out:function(event, ui ){
        	  $(this).removeClass('drag-on-scope');
        	  console.log('over');
          },
          drop: function( event, ui ) {
      		$('#optModal').modal({backdrop: 'static', keyboard: false});
//         	  $('#modal_add_obj_sub').modal({backdrop: 'static', keyboard: false});
        	  
        	  $(this).removeClass('drag-on-scope');
//             $( this )
//               .addClass( "ui-state-highlight" )
//               .find( "p" )
//                 .html( "已放置！" ).end();
            ui.draggable.removeClass('drag-on-doing');  
     		$(this).append(ui.draggable.get(0).outerHTML);
     		
     		console.log(event);
     		console.log(ui);
     		console.log(' html:='+ui.draggable.html());
     		console.log(' html id:='+ui.draggable.attr('id'));
            console.log('parent html:='+ui.draggable.get(0).outerHTML);
            ui.draggable.remove();
            console.log('------------------');
            console.log(event.target.id);
                 
          }
        });
        
// 	  $( "li.parent_li" ).draggable({containment:"div.tree well",handle: "span.data-detail",revert: "invalid",delay: 200,opacity: 1,helper: "clone",zIndex: 2700 });
	  
	  //mouse hover //上级选中span闪烁
	  $('.tree li.parent_li > span').hover(function(){
// 		  console.log("in");
		  $(this).parent().find('span').not('.label').addClass('shine_blue');
		},function(){
// 		  console.log("out");
		  $(this).parent().find('span').removeClass('shine_blue');
		});
	  
	  //mouse hover //单个 //$("#main").find(".one").not(".two")
	  /* 无效*/
	  $('.tree li').not('ul').find(' > span').hover(function(){
// 		  console.log("in2");
		  $(this).parent().find('span').not('.label').addClass('shine_red');
		},function(){
// 		  console.log("out2");
		  $(this).parent().find('span').removeClass('shine_red');
		});
	  
	//error
// 	$('li.parent_li').not('> ul').find(' > span').hover(function(){
// 		  console.log("in2");
// 		  $(this).parent().find('span').not('.label').addClass('shine_red');
// 		},function(){
// 		  console.log("out2");
// 		  $(this).parent().find('span').removeClass('shine_red');
// 	});
	
	  $("input[data-bind-name='add_input_level']").on("change",function(){
			//更改新增模态框内容
// 			$('#modal_add_obj_sub_title').text('新增检查项目'+level+'级分类');
// 		  alert($(this).val());
     	var level=$('#query_input_obj_level').val();//模态框初始值-不做改变：当前级别
     	var upCode=$('#query_input_obj_upCode').val();//模态框初始值-不做改变：上级代码
     	var nowCode=$('#query_input_obj_nowCode').val();//模态框初始值-不做改变 :当前级别代码
     	var newLevel=level;
		  
		  if('SAME_LEVEL'==$(this).val()){
			//SAME_LEVEL：同级增加
			$('#add_input_obj_upper_code').val(upCode);
			
			
		  }else{
			//INNER_LEVEL：下级增加
		    newLevel=Number(level)+1;  
		    $('#add_input_obj_upper_code').val(nowCode);
		  }
		  $('#add_input_obj_level').val(newLevel);
		  
     	$('#modal_add_obj_sub_title').text('新增检查项目'+newLevel+'级分类');		
		    });
	});

	function openUp(){
		$('span[id^=Parent_]').click(); //自动点击，收缩
// 		getObjectTree();
		
		$('#bt_open_up').text($('#Parent_1').attr('title'));
	}
	
	/*level:当前级别，upCode:上级代码，nowCode：当前级别代码*/
	function addObjSub(level,upCode,nowCode,levelChina){
		myModalInit('modal_add_obj_sub');
		activeModalInput('modal_add_obj_sub');
		//btn show
		$('#btn_save_obj_sub').show();
		$('#btn_save_obj_sub').attr('data-opt-type','NEW');
		
		$('#add_input_same_level').prop("checked", "checked");
		
// 		if(initMidCode!=null||initMidCode!=undefined){
// 			$('#add_input_obj_upper_code').val(initMidCode);//自动初始化
// 		}
		
		$('#modal_add_obj_sub_title').text('新增检查项目'+level+'级分类');
		
     	$('#query_input_obj_level').val(level);//模态框初始值-不做改变
     	$('#query_input_obj_upCode').val(upCode);//模态框初始值-不做改变
     	$('#query_input_obj_nowCode').val(nowCode);//模态框初始值-不做改变
     	
	    $('#add_input_obj_upper_code').val(upCode);//新增时初始设置为同级增加
	 	$('#add_input_obj_level').val(level);//新增时初始设置为同级增加
	 	
	 	$('#add_input_obj_upper_code').attr('readonly','');//上级代码设置为只读
		
		$('#modal_add_obj_sub').modal({backdrop: 'static', keyboard: false});
	}
	
	/*查询项目类明细*/
	function queryObjSub(level,upCode,nowCode){
		myModalInit('modal_add_obj_mid');
		readOnlyModalInput('modal_add_obj_mid');
		//btn hide
		$('#btn_save_obj_mid').hide();
		$('#btn_save_obj_mid').attr('data-opt-type','QUERY');
		
		//时间戳区域
// 		$('#obj-create-info-div').show();
		$('#modal_add_obj_mid_title').text('查看检查项目'+level+'级分类'+'['+nowCode+']');
		
		$('#modal_add_obj_mid').modal({backdrop: 'static', keyboard: false});
		
		//ajax
		var reqUrl='<%=path%>/AjaxChannel?action=BUSI_DATA_OBJ_QUERY_DEAIL_BY_TREE_ACTION';
		var data={};
		data.LEVEL=level;
		data.UP_CODE=upCode;//上级代码
		data.NOW_CODE=nowCode;//本级代码
		$.ajax({
			type : 'POST',
			url:reqUrl,
			data: data,
			dataType : 'json',
			success : function(json) {
				if(json.SUCCESS=='1'){
						if(json.OBJECT_DETAIL_BEAN!=null){
							$('#add_input_obj_check_id').val(json.OBJECT_DETAIL_BEAN.OBJ_ID);
							$('#add_input_obj_check_level').val(json.OBJECT_DETAIL_BEAN.OBJ_CLASS_LEVEL);
							$('#add_input_obj_check_code').val(json.OBJECT_DETAIL_BEAN.OBJ_CLASS_CODE);
							$('#add_input_obj_check_name').val(json.OBJECT_DETAIL_BEAN.OBJ_CLASS_NAME);
							$('#add_input_obj_upper_code').val(json.OBJECT_DETAIL_BEAN.OBJ_CLASS_F_CODE);
							$('#add_input_obj_upper_name').val(json.OBJECT_DETAIL_BEAN.OBJ_CLASS_F_NAME);
							$('#add_input_obj_check_desc').val(json.OBJECT_DETAIL_BEAN.OBJ_CLASS_DESC);
							$('#add_input_obj_check_sort').val(json.OBJECT_DETAIL_BEAN.SORT);
							$('#add_input_obj_check_create_time').val(json.OBJECT_DETAIL_BEAN.CREATE_TIME_STR);
							$('#add_input_obj_check_update_time').val(json.OBJECT_DETAIL_BEAN.UPDATE_TIME_STR);
							$('#add_input_obj_check_create_by').val(json.OBJECT_DETAIL_BEAN.CREATE_NAME);
							$('#add_input_obj_check_update_by').val(json.OBJECT_DETAIL_BEAN.UPDATE_NAME);
						}
// 						returnSuccessMsgShow('modal_add_obj_sub',json.MSG)
// 						isChange=1;//标示关闭按钮，应该刷新
// 						$(obj).button('reset');
//						location.reload();//刷新树
					}else{
//					    alert(json.MSG)	
						returnErrorMsgShow('modal_add_obj_mid',json.MSG)	
// 						$(obj).button('reset');
					}
				},
			error : function(e) {
				console.log(e);
				}
			});
		
	}
	/*编辑项目明细*/
	function editObjSub(level,upCode,nowCode){
		myModalInit('modal_add_obj_mid');
		readOnlyModalInput('modal_add_obj_mid');
		
		//btn show
		$('#btn_save_obj_mid').show();
		$('#btn_save_obj_mid').attr('data-opt-type','EDIT');
		
		//时间戳区域
// 		$('#obj-create-info-div').hide();
		$('#modal_add_obj_mid_title').text('编辑检查项目'+level+'级分类'+'['+nowCode+']');
		$('#add_input_obj_check_name,#add_input_obj_check_desc,#add_input_obj_check_sort').removeAttr('readonly');
		
		$('#modal_add_obj_mid').modal({backdrop: 'static', keyboard: false});
		
		//ajax
		var reqUrl='<%=path%>/AjaxChannel?action=BUSI_DATA_OBJ_QUERY_DEAIL_BY_TREE_ACTION';
		var data={};
		data.LEVEL=level;
		data.UP_CODE=upCode;//上级代码
		data.NOW_CODE=nowCode;//本级代码
		$.ajax({
			type : 'POST',
			url:reqUrl,
			data: data,
			dataType : 'json',
			success : function(json) {
				if(json.SUCCESS=='1'){
						if(json.OBJECT_DETAIL_BEAN!=null){
							$('#add_input_obj_check_id').val(json.OBJECT_DETAIL_BEAN.OBJ_ID);
							$('#add_input_obj_check_level').val(json.OBJECT_DETAIL_BEAN.OBJ_CLASS_LEVEL);
							$('#add_input_obj_check_code').val(json.OBJECT_DETAIL_BEAN.OBJ_CLASS_CODE);
							$('#add_input_obj_check_name').val(json.OBJECT_DETAIL_BEAN.OBJ_CLASS_NAME);
							$('#add_input_obj_upper_code').val(json.OBJECT_DETAIL_BEAN.OBJ_CLASS_F_CODE);
							$('#add_input_obj_upper_name').val(json.OBJECT_DETAIL_BEAN.OBJ_CLASS_F_NAME);
							$('#add_input_obj_check_desc').val(json.OBJECT_DETAIL_BEAN.OBJ_CLASS_DESC);
							$('#add_input_obj_check_sort').val(json.OBJECT_DETAIL_BEAN.SORT);
							$('#add_input_obj_check_create_time').val(json.OBJECT_DETAIL_BEAN.CREATE_TIME_STR);
							$('#add_input_obj_check_update_time').val(json.OBJECT_DETAIL_BEAN.UPDATE_TIME_STR);
							$('#add_input_obj_check_create_by').val(json.OBJECT_DETAIL_BEAN.CREATE_NAME);
							$('#add_input_obj_check_update_by').val(json.OBJECT_DETAIL_BEAN.UPDATE_NAME);
						}
// 						returnSuccessMsgShow('modal_add_obj_sub',json.MSG)
// 						isChange=1;//标示关闭按钮，应该刷新
// 						$(obj).button('reset');
//						location.reload();//刷新树
					}else{
//					    alert(json.MSG)	
						returnErrorMsgShow('modal_add_obj_mid',json.MSG)	
// 						$(obj).button('reset');
					}
				},
			error : function(e) {
				console.log(e);
				}
			});
		
		
	}
	
	//edit save
	function saveObjCheckConfirm(obj){
		var modalOptType=$(obj).attr('data-opt-type');//new

		var obj_name=$('#add_input_obj_check_name').val();
		var obj_desc=$('#add_input_obj_check_desc').val();
		var obj_id=$('#add_input_obj_check_id').val();
		var obj_sort=$('#add_input_obj_check_sort').val();
		var obj_level=$('#add_input_obj_check_level').val();//级别，对应不同表
		
// 		initMidCode=obj_mid_code;//将上次所需二级代码存为默认代码	
		
		var reqUrl;
		if('EDIT'==modalOptType){
			reqUrl='<%=path%>/AjaxChannel?action=BUSI_DATA_OBJ_EDIT_BY_TREE_ACTION';
		}
		else{
			returnErrorMsgShow('modal_add_obj_mid','未知操作类型，请稍后重试')
			return false;
		}
		
		$(obj).button('loading');
		var data={};
		data.OBJ_NAME=obj_name;
		data.OBJ_ID=obj_id;
		data.OBJ_SORT=obj_sort;
		data.OBJ_DESC=obj_desc;
		data.OBJ_LEVEL=obj_level;
		//ajax begin
		$.ajax({
				type : 'POST',
				url:reqUrl,
				data: data,
				dataType : 'json',
				success : function(json) {
					if(json.SUCCESS=='1'){
							returnSuccessMsgShow('modal_add_obj_mid',json.MSG)
// 							var fvTable=$("#objSubList").dataTable(); //datatable init current
// 							fvTable.fnDraw(false);
							isChange=1;//标示关闭按钮，应该刷新
							$(obj).button('reset');
// 							location.reload();//刷新树
						}else{
// 							alert(json.MSG)	
							returnErrorMsgShow('modal_add_obj_mid',json.MSG)	
							$(obj).button('reset');
						}
					
					},
				error : function(e) {
					console.log(e);
					}
				});
	}
	
	//new save
	function saveObjSubConfirm(obj){
		var modalOptType=$(obj).attr('data-opt-type');//new

		var obj_sub_name=$('#add_input_obj_class_name').val();
		var obj_sub_desc=$('#add_input_obj_class_desc').val();
		var obj_sub_code=$('#add_input_obj_class_code').val();
		var obj_sub_sort=$('#add_input_obj_class_sort').val();
		
		var obj_up_code=$('#add_input_obj_upper_code').val();
		var add_level=$('#add_input_obj_level').val();//级别，对应不同表
		
// 		initMidCode=obj_mid_code;//将上次所需二级代码存为默认代码	
		
		var reqUrl;
		if('NEW'==modalOptType){
			reqUrl='<%=path%>/AjaxChannel?action=BUSI_DATA_OBJ_ADD_BY_TREE_ACTION';
		}
// 		else if('EDIT'==modalOptType){
<%-- 			reqUrl='<%=path%>/AjaxChannel?action=BUSI_DATA_OBJ_SUB_EDIT_ACTION'; --%>
// 		}
		else{
			returnErrorMsgShow('modal_add_obj_sub','未知操作类型，请稍后重试')
			return false;
		}
		
		$(obj).button('loading');
		//ajax begin
		$.ajax({
				type : 'POST',
				url:reqUrl,
				data: {OBJ_NAME:obj_sub_name,OBJ_DESC:obj_sub_desc,ADD_LEVEL:add_level,
					OBJ_CODE:obj_sub_code,OBJ_SORT:obj_sub_sort,OBJ_UP_CODE:obj_up_code},
				dataType : 'json',
				success : function(json) {
					if(json.SUCCESS=='1'){
							returnSuccessMsgShow('modal_add_obj_sub',json.MSG)
// 							var fvTable=$("#objSubList").dataTable(); //datatable init current
// 							fvTable.fnDraw(false);
							isChange=1;//标示关闭按钮，应该刷新
							$(obj).button('reset');
// 							location.reload();//刷新树
						}else{
// 							alert(json.MSG)	
							returnErrorMsgShow('modal_add_obj_sub',json.MSG)	
							$(obj).button('reset');
						}
					
					},
				error : function(e) {
					console.log(e);
					}
				});
	}
	
	function saveCloseWin(){
		if(0!=isChange){
			location.reload();
			isChange=0;
		}
	}
	
	$(function(){
		//从cookie读取，最后一次操作，是显示编辑按钮，还是隐藏编辑按钮
		var displayTag=$.cookie('obj_data-display-tag');//0未显示 1已显示
		if('0'==displayTag){//hide
			$('#bt_edit_tree').attr('data-display-tag','1');
			
		}else{ //show
			$('#bt_edit_tree').attr('data-display-tag','0');
		}
		editDisplay($('#bt_edit_tree')[0],0);
	});
	
	/*显示隐藏编辑按钮*/
	function editDisplay(obj,timeLengh){
		if(timeLengh==null)timeLengh=300;
		var displayTag=$(obj).attr('data-display-tag');//0未显示 1已显示
		if('0'==displayTag){
			$(obj).attr('data-display-tag','1');
			$(obj).text('关闭编辑');
			//XXX show
			$('div.tree.well').find('a.tree_a').show(timeLengh);
// 			console.log($('div.tree.well').find('a.tree_a').length);
		}else{
			$(obj).attr('data-display-tag','0');
			$(obj).text('打开编辑');
			$('div.tree.well').find('a.tree_a').hide(timeLengh);
// 			console.log($('div.tree.well').find('a.tree_a').length);
		}
		//set cookies
		$.cookie('obj_data-display-tag', $(obj).attr('data-display-tag')); 
	}

	$(function(){
		$("li.parent_li").mouseover(function(){
					if($('#bt_edit_tree').attr('data-display-tag')=='0'){
						$(this).children('a.tree_a').show();
					}
				  });
		$("li.parent_li").mouseout(function(){
					if($('#bt_edit_tree').attr('data-display-tag')=='0'){
						$(this).children('a.tree_a').hide();
					}
				  });
	});
	/*
	$(function(){
		$("span.data-detail").mouseover(function(){
				  	$(this).siblings('a.tree_a').show(300);
				  });
		$("span.data-detail").mouseout(function(){
					$(this).siblings('a.tree_a').hide(300);
				  });
	});
	*/
	
	/*opt confirm*/
// 	function optConfirm(opt_type,obj_sub_name,obj_sub_id){
	function optConfirm(opt_type,level,upCode,nowCode){
		//auto top and height
// 		modal_auto_top($('div.modal-dialog'));

// 		$('#optModal').find("button.btn-primary:first").button('reset');
// 		clearMsgShow('optModal');
//		init common
		myModalInit('optModal');
		
		var title;
		var contentHtml;
		
		$("#opt_confirm_mod_obj_code").val(nowCode);
		$("#opt_confirm_mod_obj_up_code").val(upCode);
		$("#opt_confirm_opt_type").val(opt_type);
		$("#opt_confirm_mod_obj_level").val(level);
		
		if("delete"==opt_type){
			title="删除"+level+"级分类["+nowCode+"]";
			
			contentHtml=
				"<div class='alert alert-danger' role='alert'>"
					+"<span class='glyphicon glyphicon-exclamation-sign' aria-hidden='true'></span> <span class='sr-only'>注意:</span>"
					+"<strong>注意:</strong>"+level+"级分类["+nowCode+"]将彻底删除，无法恢复 !"
				+"</div>";
// 			$('#optModal').find("button.btn-primary:first").click(startRole);

		}else if("stop"==opt_type)	{
			title="停用"+level+"级分类["+nowCode+"]";
			
			contentHtml=
				"<div class='alert alert-warning' role='alert'>"
					+"<span class='glyphicon glyphicon-question-sign' aria-hidden='true'></span> <span class='sr-only'>注意:</span>"
					+"<strong>注意:</strong>"+level+"级分类["+nowCode+"]将停用，可在"+level+"级分类管理恢复。"
				+"</div>";
// 			$('#optModal').find("button.btn-primary:first").click(stopRole);
		}else if("start"==opt_type){
			title="启用"+level+"级分类["+nowCode+"]";
			
			contentHtml=
				"<div class='alert alert-info' role='alert'>"
					+"<strong>注意:</strong><span class='glyphicon glyphicon-info-sign' aria-hidden='true'></span> <span class='sr-only'>注意:</span>"
					+""+level+"级分类["+nowCode+"]将被启用。"
				+"</div>";
// 			$('#optModal').find("button.btn-primary:first").click(deleteRole);
		}
		$('#optModal').find("h4.modal-title:first").text(title);
		$('#optModal').find("div.modal-body:first").html(contentHtml);
		$('#optModal').modal({backdrop: 'static', keyboard: false});
	}
	
	function optConfirmDone(obj){
		var objCode=$("#opt_confirm_mod_obj_code").val();
		var objUpCode=$("#opt_confirm_mod_obj_up_code").val();
		var objLevel=$("#opt_confirm_mod_obj_level").val();
// 		var objSubName=$("#opt_confirm_mod_obj_name").val();
		var optType=$("#opt_confirm_opt_type").val();
		
		$(obj).button('loading');
		var reqUrl='<%=path%>/AjaxChannel?action=BUSI_DATA_OBJ_CLASS_MOD_BY_TREE_ACTION';
		//ajax begin
		$.ajax({
				type : 'POST',
				url:reqUrl,
				data: {OBJ_CODE:objCode,OBJ_UP_CODE:objUpCode,OBJ_LEVEL:objLevel,OPT_TYPE:optType},
				dataType : 'json',
				success : function(json) {
					if(json.SUCCESS=='1'){
							returnSuccessMsgShow('optModal',json.MSG);//alert msg
// 							$(obj).button('complete');//button 
// 							var fvTable=$("#objSubList").dataTable(); //datatable init current
// 							fvTable.fnDraw(false);
							isChange=1;//标示关闭按钮，应该刷新
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
	
	//初始化预查询
	/*
	var initMidCode=getUrlParam("MID_CODE"); //接收从二级页面某个二级分类，跳转过来，直接查询其对应三级代码
	if(initMidCode!=null){
		$("#qr_input_obj_mid_code").val(initMidCode);
		//缺省二级代码，便于批量添加该二级代码对应的三级分类
	}
   */

	$(function() {
		//初始化时，从cookie读取，最后一次操作，列表查询条件，点击时，从界面写入cookie，并执行查询
		var conditionPathName=$.cookie('tree_query_data-tree-path-name');//显示查询条件
		if(conditionPathName!=null){
			$("#tree_query_condition_path_name").val(conditionPathName);
		}
		var conditionPathCode=$.cookie('tree_query_data-tree-path-code');//显示查询条件
		if(conditionPathCode!=null){
			$("#tree_query_condition_path_code").val(conditionPathCode);
		}
		var classCode=$.cookie('obj_item_query_class_code');//查询条件类别代码
		var classLevelType=$.cookie('obj_item_query_class_level_type');//SUP,MID,SUB
		if(classCode!=null&&classLevelType!=null){
			$("#tree_query_class_code").val(classCode);
			$("#tree_query_class_level_type").val(classLevelType);
			//add item modal
			queryConditionDisplay();//默认显示查询条件
		}
		dataTableInit();
	}); 
   
   /*在dataTable显示查询列表*/
   function queryConditionDisplay(){
	   var conditionStr='';
	   var conditionPathCodeStr='';
	   var conditionPathName=$("#tree_query_condition_path_name").val();
	   var conditionPathCode=$("#tree_query_condition_path_code").val();

	   if(conditionPathName!=null){
		   conditionStr+=conditionPathName;
	   }
	   if(conditionPathCode!=null){
		   conditionPathCodeStr+=conditionPathCode;
	   }
	   var classNameArr,classCodeArr=[];	
	   classNameArr=conditionStr.split('>');
	   classCodeArr=conditionPathCodeStr.split('|');

	  if(conditionStr!=null&&conditionStr!=''&&classNameArr.length==classCodeArr.length){
		  var queryConditionSummry =$('#tree_query-condition-summry');
// 		  var html="<a href='#' onclick='alert(&quot;aa"+classNameArr+classCodeArr+"&quot;)'>车头</a>";
		  var html='';
		  $.each(classNameArr,function(index,value){
			  if(index!=0){
				  html+='>';
			  }
			  html+="<a href='javascript:void(0);' onclick=queryListByCondition('"+(index+1)+"','"+classCodeArr[index]+"');>"+value+"</a>";
			  });
	 	  //queryConditionSummry.text(conditionStr); /*display query condition summry*/
	 	  queryConditionSummry.html(html);
		  queryConditionSummry.show(400);   
	  	}
  	 }
   /*点击维修项目列表上方，查询条件*/
	function queryListByCondition(objCodeLevel,objCode){
// 		 $("#tree_query_class_code").val(objCode);
// 		 $("#tree_query_class_level_type").val(objCodeLevel);
// 		 dataTableInit();
		 $("span[data-obj-class-code='"+objCode+"'][data-obj-class-level-code='"+objCodeLevel+"']").click();

	}
   
	function dataTableInit() {
		//param
		var classCode = $("#tree_query_class_code").val();//
		var classLevelType = $("#tree_query_class_level_type").val();//SUP:1,MID:2,SUB:3
		
		//格式：列映射关系
		var aoColumns = '[{"mDataProp" : "ID","sTitle" : "序号"}, '
					   + '{"mDataProp" : "CHECK_OBJ_CODE","sTitle" : "项目代码/名称"},'
// 					   + '{"mDataProp" : "SUP_CODE","sTitle" : "一级分类代码/名称"},'
// 					   + '{"mDataProp" : "MID_CODE","sTitle" : "二级分类代码/名称"},'
					   + '{"mDataProp" : "SUB_CODE","sTitle" : "三级分类代码/名称"},'
					   + '{"mDataProp" : "CHECK_OBJ_DESC","sTitle" : "项目说明"},'
// 					   + '{"mDataProp" : "OBJ_CHECK_WAY","sTitle" : "检查方式/频次/标准"},'
// 					   + '{"mDataProp" : "OBJ_CHECK_PHOTO","sTitle" : "拍照/检查阶段"},'
// 					   + '{"mDataProp" : "CREATE_TIME_STR","sTitle" : "创建信息"},'
// 					   + '{"mDataProp" : "FREEZE_TAG","sTitle" : "状态"},'
					   + '{"mDataProp" : "ID","sTitle" : "操作"}]';

		var reqData = {
			action : 'BUSI_DATA_OBJ_ITEM_QUERY_LIST_ACTION',
			params : 'params',
// 			ITEM_NAME:itemName,
// 			ITEM_CODE:itemCode,
// 			SUP_NAME:supName,
// 			SUP_CODE:supCode,
// 			MID_NAME:midName,
// 			MID_CODE:midCode,
// 			SUB_NAME:subName,
// 			SUB_CODE:subCode,
// 			F_NAME:itemFName,
// 			F_CODE:itemFCode,
// 			INCLUDE_STOP:includeStop
			CLASS_CODE:classCode,
			CLASS_LEVEL_TYPE:classLevelType
			};
		//example为table定义id ：10：需要加入操作按钮的列；[4,5]：需要转义的列（映射数据字典值）
		initObjItemTable('objItemList', aoColumns, reqData, '<%=path%>',4,[]);
		resetIFrameLength();//need ajax async:false,
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
		//
		var initQueryClassCode=$("#tree_query_class_code").val();
		var initQueryClassLevel=$("#tree_query_class_level_type").val();
		
		if(initQueryClassCode!=null&&initQueryClassLevel!=null){
			if("1"==initQueryClassLevel){
				$('#add_input_obj_sup_code').val(initQueryClassCode);
			}else if("2"==initQueryClassLevel){
				$('#add_input_obj_mid_code').val(initQueryClassCode);
			}else if("3"==initQueryClassLevel){
				$('#add_input_obj_sub_code').val(initQueryClassCode);
			}else{
				$('#add_input_obj_item_f_code').val(initQueryClassCode);
			}
		}
// 		if(initSupCode!=null){
// 			$('#add_input_obj_sup_code').val(initSupCode);
// 		}
// 		if(initMidCode!=null){
// 			$('#add_input_obj_mid_code').val(initMidCode);
// 		}
// 		if(initSubCode!=null){
// 			$('#add_input_obj_sub_code').val(initSubCode);
// 		}
		
// 		if(initItemSupCode!=null){
// 			$('#add_input_obj_item_f_code').val(initItemFCode);
// 		}
		
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
	function optItemConfirm(opt_type,obj_item_code,obj_item_id){
		//auto top and height
// 		modal_auto_top($('div.modal-dialog'));

// 		$('#optModal').find("button.btn-primary:first").button('reset');
// 		clearMsgShow('optModal');
//		init common
		myModalInit('optItemModal');
		
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
// 			$('#optItemModal').find("button.btn-primary:first").click(startRole);

		}else if("stop"==opt_type)	{
			title="停用检查项目["+obj_item_code+"]";
			
			contentHtml=
				"<div class='alert alert-warning' role='alert'>"
					+"<span class='glyphicon glyphicon-question-sign' aria-hidden='true'></span> <span class='sr-only'>注意:</span>"
					+"<strong>注意:</strong>检查项目["+obj_item_code+"]将停用，可在检查项目管理恢复。"
				+"</div>";
// 			$('#optItemModal').find("button.btn-primary:first").click(stopRole);
		}else if("start"==opt_type){
			title="启用检查项目["+obj_item_code+"]";
			
			contentHtml=
				"<div class='alert alert-info' role='alert'>"
					+"<strong>注意:</strong><span class='glyphicon glyphicon-info-sign' aria-hidden='true'></span> <span class='sr-only'>注意:</span>"
					+"检查项目["+obj_item_code+"]将被启用。"
				+"</div>";
// 			$('#optItemModal').find("button.btn-primary:first").click(deleteRole);
		}
		$('#optItemModal').find("h4.modal-title:first").text(title);
		$('#optItemModal').find("div.modal-body:first").html(contentHtml);
		$('#optItemModal').modal({backdrop: 'static', keyboard: false});
	}
	
	function optItemConfirmDone(obj){
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
							returnSuccessMsgShow('optItemModal',json.MSG);//alert msg
// 							$(obj).button('complete');//button 
							var fvTable=$("#objItemList").dataTable(); //datatable init current
							fvTable.fnDraw(false);
							$(obj).button('reset');
						}else{
							returnErrorMsgShow('optItemModal',json.MSG);
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
	
	function optEntChildItem(objCode,objId,objName){
			var itemLevel=$("#tree_query_item_deep_level").val();
			console.log('itemLevel:='+itemLevel);
			if(itemLevel==null||itemLevel==''){
				$("#tree_query_class_level_type").val('4');
				$("#tree_query_item_deep_level").val('4');
				itemLevel='4';
			}
			 if($("#tree_query_class_code").val()!=objCode){
				$("#tree_query_class_code").val(objCode);
				$("#tree_query_item_deep_level").val((parseInt(itemLevel)+1)+'');
				console.log((parseInt(itemLevel)+1)+'');
				$("#tree_query_class_level_type").val($("#tree_query_item_deep_level").val());
				console.log('tree_query_class_level_type:='+$("#tree_query_class_level_type").val());
				
				 var queryConditionSummry =$('#tree_query-condition-summry');
				 var html=queryConditionSummry.html();
				 if(html==null||html==''){
					  html+="<a href=\"javascript:void(0);\" onclick=\"optEntChildItem('"+objCode+"','"+objId+"','"+objName+"');\">"+objName+"</a>";
				 	  queryConditionSummry.html(html);
					  queryConditionSummry.show(400); 
				 }else{
					 //处理相同code多次点击，重复添加的问题
					 console.log("old html:="+html);
					  var addHtml="<a href=\"javascript:void(0);\" onclick=\"optEntChildItem('"+objCode+"','"+objId+"','"+objName+"');\">"+objName+"</a>";
					  console.log("addHtml:="+addHtml);
					  if(html.indexOf(addHtml)>=0){
				 		//若存在相同的查询条件，则截断
				 		 html=html.substring(0,html.indexOf(addHtml)+addHtml.length);
				 		 console.log("new html:="+html);
				 	  }else{
						  html+=">";
						  html+= addHtml;
				 	  }
				 	  queryConditionSummry.html(html);
				 }
// 				 alert('html:='+ queryConditionSummry.html());
			}
		 dataTableInit();//query table
	}
	
</script>

</body>
</html>