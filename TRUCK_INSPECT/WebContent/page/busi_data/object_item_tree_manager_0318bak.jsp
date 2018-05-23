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
	    <div class="matter">
        <div class="container">
		
          <!-- Table -->

            <div class="row">

              <div class="col-md-12">

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
			                             	
			                             	 <label class="control-label col-md-3" for="add_input_obj_mid_code">上级分类代码</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_obj_mid_code" placeholder="上级分类代码" value="" readonly="readonly">
                                            <span class="text-danger">*</span>
                                             </div>
			                             	
					</div>
                 	 
                          					<div class="form-group">
<!--                                             <label class="control-label col-md-3" for="add_input_obj_sub_code">三级分类代码</label> -->
<!--                                             <div class="col-md-3"> -->
<!--                                               <input type="text" class="form-control required-input" id="add_input_obj_sub_code" placeholder="三级分类代码"> -->
<!--                                               <span class="text-danger" >*</span> -->
<!--                                             </div> -->
                                            <label class="control-label col-md-3" for="add_input_obj_sub_code">项目级别代码</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_obj_sub_code" placeholder="项目级别代码">
                                              <span class="text-danger">*</span>
                                            </div>
                                            <label class="control-label col-md-3" for="add_input_obj_sub_name">项目级别名称</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_obj_sub_name" placeholder="项目级别名称">
                                              <span class="text-danger">*</span>
                                            </div>
                                          </div>   
                                          <!--  -->
                                          <div class="form-group">
                                            <label class="control-label col-md-3" for="add_input_obj_sub_desc">项目级别说明</label>
                                            <div class="col-md-8">
                                              <input type="text" class="form-control" id="add_input_obj_sub_desc"  placeholder="项目级别说明">
                                            </div>
                                          </div>
                                          <div class="form-group">
                                            <label class="control-label col-md-3" for="add_input_obj_sub_sort">分类排序</label>
                                            <div class="col-md-4">
                                              <input type="number" min="1" value="1" class="form-control" id="add_input_obj_sub_sort"  placeholder="分类排序">
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
                 	 <input  type="hidden" id="add_input_obj_mid_id" value="" ><!-- 检查项目class id -->
                 	 <input  type="hidden" id="add_input_obj_mid_level" value="" ><!-- 检查项目calss level -->
                          					<div class="form-group">

                                            <label class="control-label col-md-3" for="add_input_obj_check_code">项目分类代码</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_obj_check_code" placeholder="项目分类代码">
                                              <span class="text-danger">*</span>
                                            </div>
                                            <label class="control-label col-md-3" for="add_input_obj_mid_name">项目分类名称</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_obj_mid_name" placeholder="项目分类名称">
                                              <span class="text-danger">*</span>
                                            </div>
                                          </div>   

                                          <div class="form-group">

                                             <label class="control-label col-md-3" for="add_input_obj_sup_code">上级分类代码</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_obj_sup_code" placeholder="上级分类代码">
<!--                                             <span class="text-danger">*</span> -->
                                             </div>
                                             <label class="control-label col-md-3" for="add_input_obj_sup_name">上级分类名称</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_obj_sup_name" placeholder="上级分类名称">
<!--                                             <span class="text-danger">*</span> -->
                                             </div>
                                          </div>
                                                                                    <!--  -->
                                          <div class="form-group">
                                            <label class="control-label col-md-3" for="add_input_obj_mid_desc">项目分类说明</label>
                                            <div class="col-md-8">
                                              <input type="text" class="form-control" id="add_input_obj_mid_desc"  placeholder="项目分类说明">
                                            </div>
                                          </div>
                                                                                    <!--  -->
                                          <div class="form-group">
                                            <label class="control-label col-md-3" for="add_input_obj_mid_sort">项目分类排序</label>
                                            <div class="col-md-4">
                                              <input type="number" min="1" value="1" class="form-control" id="add_input_obj_mid_sort"  placeholder="项目分类排序">
                                            </div>
                                          </div>
                                       <div id="obj-create-info-div" class="form-horizontal">
                                       <center><hr class="modal-hr" style="margin-bottom:13px;"/></center>
                                          <div class="form-group">
                                             <label class="control-label col-md-3" for="add_input_obj_mid_create_time">创建时间</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_obj_mid_create_time" placeholder="创建时间">
<!--                                             <span class="text-danger">*</span> -->
                                             </div>
                                             <label class="control-label col-md-3" for="add_input_obj_mid_create_by">创建人</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_obj_mid_create_by" placeholder="创建人">
<!--                                             <span class="text-danger">*</span> -->
                                             </div>
                                          </div>  
                                            <div class="form-group">
                                             <label class="control-label col-md-3" for="add_input_obj_mid_update_time">修改时间</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_obj_mid_update_time" placeholder="修改时间">
<!--                                             <span class="text-danger">*</span> -->
                                             </div>
                                             <label class="control-label col-md-3" for="add_input_obj_mid_update_by">修改人</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_obj_mid_update_by" placeholder="修改人">
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
<!-- 停用删除 begin-->


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
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script><!-- 注意与bootstrap的先后顺序，jquery ui 与 bootstrap tips冲突 -->
<script src="<%=path%>/js/bootstrap.js?v=<%=staticVersion%>"></script> <!-- Bootstrap -->
<!-- table收起 -->
<script src="<%=path%>/js/truck-inspect-common.js?v=<%=staticVersion%>"></script> <!-- Custom codes -->
<script src="<%=path%>/js/data-tables/jquery.dataTables.min.js?v=<%=staticVersion%>"	type="text/javascript"></script>
<script src="<%=path%>/js/data-tables/dataTables.bootstrap.min.js?v=<%=staticVersion%>"	type="text/javascript"></script>
<script src="<%=path%>/js/busi-js/query_obj_sub_list.js?v=<%=staticVersion%>" type="text/javascript"></script>
<script src="<%=path%>/js/jquery.cookie.js?v=<%=staticVersion%>" type="text/javascript"></script>


<!-- Script for this page -->
<script type="text/javascript">


var isChange=0;//是否做过新增；0：否，1：是
$(function () {
	  $('.tree li:has(ul)').addClass('parent_li');
	  $('.tree li.parent_li > span').on('click', function (e) {
	    var children = $(this).parent('li.parent_li').find(' > ul > li');
	    if (children.is(":visible")) {
	      children.hide('fast');
	      $(this).attr('title', '展开').find(' > i').addClass('icon-plus').removeClass('icon-minus');
	    } else {
	      children.show('fast');
	      $(this).attr('title', '收起').find(' > i').addClass('icon-minus').removeClass('icon-plus');
	    }
	    e.stopPropagation();
	  });
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
		  console.log("in");
		  $(this).parent().find('span').not('.label').addClass('shine_blue');
		},function(){
		  console.log("out");
		  $(this).parent().find('span').removeClass('shine_blue');
		});
	  
	  //mouse hover //单个 //$("#main").find(".one").not(".two")
	  /* 无效*/
	  $('.tree li').not('ul').find(' > span').hover(function(){
		  console.log("in2");
		  $(this).parent().find('span').not('.label').addClass('shine_red');
		},function(){
		  console.log("out2");
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
			$('#add_input_obj_mid_code').val(upCode);
			
			
		  }else{
			//INNER_LEVEL：下级增加
		    newLevel=Number(level)+1;  
		    $('#add_input_obj_mid_code').val(nowCode);
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
// 			$('#add_input_obj_mid_code').val(initMidCode);//自动初始化
// 		}
		
		$('#modal_add_obj_sub_title').text('新增检查项目'+level+'级分类');
		
     	$('#query_input_obj_level').val(level);//模态框初始值-不做改变
     	$('#query_input_obj_upCode').val(upCode);//模态框初始值-不做改变
     	$('#query_input_obj_nowCode').val(nowCode);//模态框初始值-不做改变
     	
	    $('#add_input_obj_mid_code').val(upCode);//新增时初始设置为同级增加
	 	$('#add_input_obj_level').val(level);//新增时初始设置为同级增加
	 	
	 	$('#add_input_obj_mid_code').attr('readonly','');//上级代码设置为只读
		
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
							$('#add_input_obj_mid_id').val(json.OBJECT_DETAIL_BEAN.OBJ_ID);
							$('#add_input_obj_mid_level').val(json.OBJECT_DETAIL_BEAN.OBJ_CLASS_LEVEL);
							$('#add_input_obj_check_code').val(json.OBJECT_DETAIL_BEAN.OBJ_CLASS_CODE);
							$('#add_input_obj_mid_name').val(json.OBJECT_DETAIL_BEAN.OBJ_CLASS_NAME);
							$('#add_input_obj_sup_code').val(json.OBJECT_DETAIL_BEAN.OBJ_CLASS_F_CODE);
							$('#add_input_obj_sup_name').val(json.OBJECT_DETAIL_BEAN.OBJ_CLASS_F_NAME);
							$('#add_input_obj_mid_desc').val(json.OBJECT_DETAIL_BEAN.OBJ_CLASS_DESC);
							$('#add_input_obj_mid_sort').val(json.OBJECT_DETAIL_BEAN.SORT);
							$('#add_input_obj_mid_create_time').val(json.OBJECT_DETAIL_BEAN.CREATE_TIME_STR);
							$('#add_input_obj_mid_update_time').val(json.OBJECT_DETAIL_BEAN.UPDATE_TIME_STR);
							$('#add_input_obj_mid_create_by').val(json.OBJECT_DETAIL_BEAN.CREATE_NAME);
							$('#add_input_obj_mid_update_by').val(json.OBJECT_DETAIL_BEAN.UPDATE_NAME);
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
		$('#add_input_obj_mid_name,#add_input_obj_mid_desc,#add_input_obj_mid_sort').removeAttr('readonly');
		
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
							$('#add_input_obj_mid_id').val(json.OBJECT_DETAIL_BEAN.OBJ_ID);
							$('#add_input_obj_mid_level').val(json.OBJECT_DETAIL_BEAN.OBJ_CLASS_LEVEL);
							$('#add_input_obj_check_code').val(json.OBJECT_DETAIL_BEAN.OBJ_CLASS_CODE);
							$('#add_input_obj_mid_name').val(json.OBJECT_DETAIL_BEAN.OBJ_CLASS_NAME);
							$('#add_input_obj_sup_code').val(json.OBJECT_DETAIL_BEAN.OBJ_CLASS_F_CODE);
							$('#add_input_obj_sup_name').val(json.OBJECT_DETAIL_BEAN.OBJ_CLASS_F_NAME);
							$('#add_input_obj_mid_desc').val(json.OBJECT_DETAIL_BEAN.OBJ_CLASS_DESC);
							$('#add_input_obj_mid_sort').val(json.OBJECT_DETAIL_BEAN.SORT);
							$('#add_input_obj_mid_create_time').val(json.OBJECT_DETAIL_BEAN.CREATE_TIME_STR);
							$('#add_input_obj_mid_update_time').val(json.OBJECT_DETAIL_BEAN.UPDATE_TIME_STR);
							$('#add_input_obj_mid_create_by').val(json.OBJECT_DETAIL_BEAN.CREATE_NAME);
							$('#add_input_obj_mid_update_by').val(json.OBJECT_DETAIL_BEAN.UPDATE_NAME);
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

		var obj_name=$('#add_input_obj_mid_name').val();
		var obj_desc=$('#add_input_obj_mid_desc').val();
		var obj_id=$('#add_input_obj_mid_id').val();
		var obj_sort=$('#add_input_obj_mid_sort').val();
		var obj_level=$('#add_input_obj_mid_level').val();//级别，对应不同表
		
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

		var obj_sub_name=$('#add_input_obj_sub_name').val();
		var obj_sub_desc=$('#add_input_obj_sub_desc').val();
		var obj_sub_code=$('#add_input_obj_sub_code').val();
		var obj_sub_sort=$('#add_input_obj_sub_sort').val();
		
		var obj_up_code=$('#add_input_obj_mid_code').val();
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
	
	

	/*废弃*/
	function getObjectTree(){
// 		$(obj).button('loading');
		var reqUrl="<%=path%>/AjaxChannel?action=BUSI_DATA_OBJ_QUERY_TREE_ACTION";
		$.ajax({
			type : 'POST',
			url:reqUrl,
			data: {},
			dataType : 'json',
			success : function(json) {
				if(json.SUCCESS=='1'){
// 						returnSuccessMsgShow('modal_add_obj_sub',json.MSG)
// 						var fvTable=$("#objSubList").dataTable(); //datatable init current
// 						fvTable.fnDraw(false);
// 						$(obj).button('reset');
						var treeHtml='';
						
						var supCodeOld;//old 一级
						var midCodeOld;//old 二级
						var subCodeOld;//old 三级
						var itemCodeOld;//old 明细

						if(json.OBJECT_LIST!=null)
							$.each(json.OBJECT_LIST,function(i,item){
								console.log(item.OBJ_SUP_ID);
								console.log(item.OBJ_SUP_NAME);
								console.log(item.OBJ_SUP_CODE);
								
								var supName=item.OBJ_SUP_NAME;
								var midName=item.OBJ_MID_NAME;
								var subName=item.OBJ_SUB_NAME;
								var itemName=item.CHECK_OBJ_NAME;
								
								var supCode=item.OBJ_SUP_CODE;//一级
								var midCode=item.OBJ_MID_CODE;//二级
								var subCode=item.OBJ_SUB_CODE;//三级
								var itemCode=item.CHECK_OBJ_CODE;//明细
								
								if(supCodeOld!=supCode){
									$("#tree_content").append('<li data-li-code="'+supCode+'"><span><i class="glyphicon glyphicon-minus-sign"></i>[1]'+supName+'</span> <a href="#">增加节点/删除节点</a></li>');
									if(midCode!=null){
										$('i[data-li-code="'+supCode+'"]').children("a").append('<ul><li data-li-code="'+supCode+'|'+midCode+'"><span><i class="glyphicon glyphicon-minus-sign"></i>[2]'+midName+'</span> <a href="#">增加节点/删除节点</a></li></ul>');
									   if(subCode!=null){
										   $('i[data-li-code="'+supCode+'|'+midCode+'"]').children("a").append('<ul><li data-li-code="'+supCode+'|'+midCode+'|'+subCode+'"><span><i class="glyphicon glyphicon-minus-sign"></i>[3]'+subName+'</span> <a href="#">增加节点/删除节点</a></li></ul>');  
										   if(itemCode!=null){
											   $('i[data-li-code="'+supCode+'|'+midCode+'|'+subCode+'"]').children("a").append('<ul><li data-li-code="'+supCode+'|'+midCode+'|'+subCode+'|'+itemCode+'"><span><i class="glyphicon glyphicon-minus-leaf"></i>[4]'+itemName+'</span> <a href="#">增加节点/删除节点</a></li></ul>');  
										   }
										   subCodeOld==subCode;
									   }
									   midCodeOld==midCode;
									}
									supCodeOld=supCode;
								}else{//same sup code 
									if(midCode==null){
										midCodeOld==midCode;
// 										continue;
										return true; 
									}
									if(midCodeOld==midCode){//same mid
										if(subCode==null){
											subCodeOld==subCode;
// 											continue;
											return true; 
										}
										if(subCodeOld==subCode){//same sub
											   if(itemCode!=null){
												   $('i[data-li-code="'+supCode+'|'+midCode+'|'+subCode+'"]').children("a").append('<li data-li-code="'+supCode+'|'+midCode+'|'+subCode+'|'+itemCode+'"><span><i class="glyphicon glyphicon-minus-leaf"></i>[4]'+itemName+'</span> <a href="#">增加节点/删除节点</a></li>');  
											   }
										}else{//diffrent sub
											$('i[data-li-code="'+supCode+'|'+midCode+'"]').children("a").append('<ul><li data-li-code="'+supCode+'|'+midCode+'|'+subCode+'"><span><i class="glyphicon glyphicon-minus-sign"></i>[3]'+subName+'</span> <a href="#">增加节点/删除节点</a></li></ul>');  
											   if(itemCode!=null){
												   $('i[data-li-code="'+supCode+'|'+midCode+'|'+subCode+'"]').children("a").append('<ul><li data-li-code="'+supCode+'|'+midCode+'|'+subCode+'|'+itemCode+'"><span><i class="glyphicon glyphicon-minus-leaf"></i>[4]'+itemName+'</span> <a href="#">增加节点/删除节点</a></li></ul>');  
											   }
										  subCodeOld==subCode;
										}
										
									}else{//diffrent mid
										if(midCode!=null){
											$('i[data-li-code="'+supCode+'"]').children("a").append('<ul><li data-li-code="'+supCode+'|'+midCode+'"><span><i class="glyphicon glyphicon-minus-sign"></i>[2]'+midName+'</span> <a href="#">增加节点/删除节点</a></li></ul>');
										   if(subCode!=null){
											   $('i[data-li-code="'+supCode+'|'+midCode+'"]').children("a").append('<ul><li data-li-code="'+supCode+'|'+midCode+'|'+subCode+'"><span><i class="glyphicon glyphicon-minus-sign"></i>[3]'+subName+'</span> <a href="#">增加节点/删除节点</a></li></ul>');  
											   if(itemCode!=null){
												   $('i[data-li-code="'+supCode+'|'+midCode+'|'+subCode+'"]').children("a").append('<ul><li data-li-code="'+supCode+'|'+midCode+'|'+subCode+'|'+itemCode+'"><span><i class="glyphicon glyphicon-minus-leaf"></i>[4]'+itemName+'</span> <a href="#">增加节点/删除节点</a></li></ul>');  
											   }
											   subCodeOld==subCode;
										   }
										   midCodeOld==midCode;
										}
									}
								}
						});
						console.log("treeHtml:="+treeHtml);
						resetIFrameLength();//need ajax async:false,
					}else{
//						alert(json.MSG)	
// 						returnErrorMsgShow('modal_add_obj_sub',json.MSG)	
// 						$(obj).button('reset');
					}
				},
			error : function(e) {
				console.log(e);
				}
			});
	}
	
	//初始化预查询
	var initMidCode=getUrlParam("MID_CODE"); //接收从二级页面某个二级分类，跳转过来，直接查询其对应三级代码
	if(initMidCode!=null){
		$("#qr_input_obj_mid_code").val(initMidCode);
		//缺省二级代码，便于批量添加该二级代码对应的三级分类
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
		var objName = $("#qr_input_obj_name").val();
		var objCode = $("#qr_input_obj_code").val();
		var midCode = $("#qr_input_obj_mid_code").val();
		var includeStop=(true==$("#qr_cb_include_stop").is(':checked'))?"1":"0";
		
		//格式：列映射关系
		var aoColumns = '[{"mDataProp" : "OBJ_SUB_ID","sTitle" : "序号"}, '
					   + '{"mDataProp" : "OBJ_CLASS_CODE","sTitle" : "三级分类代码"},'
					   + '{"mDataProp" : "OBJ_CLASS_F_CODE","sTitle" : "二级分类代码"},'
					   + '{"mDataProp" : "OBJ_CLASS_NAME","sTitle" : "三级分类名称"},'
					   + '{"mDataProp" : "OBJ_CLASS_DESC","sTitle" : "三级分类说明"},'
					   + '{"mDataProp" : "OBJ_SUB_ID","sTitle" : "创建信息"},'
					   + '{"mDataProp" : "FREEZE_TAG","sTitle" : "状态"},'
					   + '{"mDataProp" : "OBJ_SUB_ID","sTitle" : "操作"}]';

		var reqData = {
			action : 'BUSI_DATA_OBJ_SUB_QUERY_LIST_ACTION',
			params : 'params',
			OBJ_NAME:objName,
			OBJ_CODE:objCode,
			MID_CODE:midCode,
			INCLUDE_STOP:includeStop
			};
		//example为table定义id ：10：需要加入操作按钮的列；[4,5]：需要转义的列（映射数据字典值）
		initObjSubTable('objSubList', aoColumns, reqData, '<%=path%>',7,[6]);
		resetIFrameLength();//need ajax async:false,
		} 
	/*title tips- common method,dynamic bind*/
	$(function () { $("[data-toggle='tooltip']").tooltip();});
	

	function detail(obj_sub_id){
		myModalInit('modal_add_obj_sub');
		//input readOnly
		readOnlyModalInput('modal_add_obj_sub');
		
		//btn hide
		$('#btn_save_obj_sub').hide();
		$('#btn_save_obj_sub').attr('data-opt-type','QUERY');
		
		var reqUrl='<%=path%>/AjaxChannel?action=BUSI_DATA_OBJ_SUB_DETAIL_ACTION';
		//ajax begin
		$.ajax({
				type : 'POST',
				url:reqUrl,
				data: {OBJ_SUB_ID:obj_sub_id},
				dataType : 'json',
				success : function(json) {
					if(json.SUCCESS=='1'){
							$('#add_input_obj_sub_code').val(json.OBJ_SUB_BEAN.ObjClassCode);
							$('#add_input_obj_sub_name').val(json.OBJ_SUB_BEAN.ObjClassName);
							$('#add_input_obj_sub_desc').val(json.OBJ_SUB_BEAN.ObjClassDesc);
							$('#add_input_obj_sub_id').val(json.OBJ_SUB_BEAN.Id);
							$('#add_input_obj_sub_sort').val(json.OBJ_SUB_BEAN.Sort);
							$('#add_input_obj_mid_code').val(json.OBJ_SUB_BEAN.ObjClassFCode);
// 							returnSuccessMsgShow('modal_add_obj_sub',json.MSG)
							$('#modal_add_obj_sub_title').text('三级分类明细['+json.OBJ_SUB_BEAN.ObjClassName+']');
						}else{
							returnErrorMsgShow('modal_add_obj_sub',json.MSG)	
						}
					},
				error : function(e) {
					console.log(e);
					}
				});
		//ajax end
// 		$('#modal_add_obj_sub_title').text('三级分类明细['+$('#add_input_obj_sub_name').val()+']'); //EMPTY
		$('#modal_add_obj_sub').modal({backdrop: 'static', keyboard: false});
		
	}
	
	//open modal
	function editObjSub1(obj_sub_id){
// 		init
		myModalInit('modal_add_obj_sub');
		activeModalInput('modal_add_obj_sub');
		$('#add_input_obj_sub_code').attr('readonly','');
		
		//btn show
		$('#btn_save_obj_sub').show();
		$('#btn_save_obj_sub').attr('data-opt-type','EDIT');
		
		var reqUrl='<%=path%>/AjaxChannel?action=BUSI_DATA_OBJ_SUB_DETAIL_ACTION';
		//ajax begin
		$.ajax({
				type : 'POST',
				url:reqUrl,
				data: {OBJ_SUB_ID:obj_sub_id},
				dataType : 'json',
				success : function(json) {
					if(json.SUCCESS=='1'){
						$('#add_input_obj_sub_code').val(json.OBJ_SUB_BEAN.ObjClassCode);
						$('#add_input_obj_sub_name').val(json.OBJ_SUB_BEAN.ObjClassName);
						$('#add_input_obj_sub_desc').val(json.OBJ_SUB_BEAN.ObjClassDesc);
						$('#add_input_obj_sub_id').val(json.OBJ_SUB_BEAN.Id);
						$('#add_input_obj_sub_sort').val(json.OBJ_SUB_BEAN.Sort);
						$('#add_input_obj_mid_code').val(json.OBJ_SUB_BEAN.ObjClassFCode);
						
						$('#modal_add_obj_sub_title').text('三级分类编辑['+json.OBJ_SUB_BEAN.ObjClassName+']');
						
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
		$('#modal_add_obj_sub').modal({backdrop: 'static', keyboard: false});
	}
	


</script>

</body>
</html>