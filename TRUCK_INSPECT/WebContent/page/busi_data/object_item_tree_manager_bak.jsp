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
                  <button type="button" class="btn btn-primary btn-xs" href="javascript:void(0);" onclick="openUp();" id="bt_open_up">展开/收起</button>
                  &nbsp;&nbsp;&nbsp;
                  <button type="button" class="btn btn-primary btn-xs" href="javascript:void(0);" onclick="location.reload();" id="bt_refresh_tree">
                  &nbsp;&nbsp;&nbsp;刷&nbsp;&nbsp;新&nbsp;&nbsp;&nbsp;</button>
                  
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
									
									<span id="Parent_1"><i class="icon-minus"></i> 车</span> <a href="#">增加节点/删除节点</a>
<!-- 									<span id="Parent_1"><i class="glyphicon glyphicon-folder-open"></i> 车</span> <a href="#">增加节点/删除节点</a> -->
										<ul id="tree_content">
										
										<!-- begin -->
										<!-- 
											<li><span><i class="glyphicon glyphicon-minus-sign"></i> 罐车</span> <a href="#">增加节点/删除节点</a>
												<ul>
													<li><span><i class="glyphicon glyphicon-leaf"></i>车体</span> <a href="#">增加节点/删除节点</a>
														<ul>
															<li>
																<span><i class="glyphicon glyphicon-leaf"></i>车体Item</span> <a href="#">增加节点/删除节点</a>
															</li>
														</ul>
													</li>
												</ul>
											</li>
											<li><span><i class="glyphicon glyphicon-minus-sign"></i> LPG</span> <a href="#">增加节点/删除节点</a>
												<ul>
													<li><span><i class="glyphicon glyphicon-leaf"></i>灌体</span> <a href="#">增加节点/删除节点</a></li>
													<li><span><i class="glyphicon glyphicon-minus-sign"></i> 车身</span> <a href="#">增加节点/删除节点</a>
														<ul>
															<li><span><i class="glyphicon glyphicon-minus-sign"></i> 底盘</span> <a href="#">增加节点/删除节点</a>
																<ul>
																	<li><span><i class="glyphicon glyphicon-leaf"></i> 承重梁</span> <a href="#">增加节点/删除节点</a>
																	</li>
																	<li><span><i class="glyphicon glyphicon-leaf"></i> 尾标</span> <a href="#">增加节点/删除节点</a>
																	</li>
																</ul></li>
															<li><span><i class="glyphicon glyphicon-leaf"></i>车头</span> <a href="#">增加节点/删除节点</a></li>
															<li><span><i class="glyphicon glyphicon-leaf"></i>轮胎</span> <a href="#">增加节点/删除节点</a></li>
														</ul>
														</li>
													<li><span><i class="glyphicon glyphicon-leaf"></i> 车顶</span> <a href="#">增加节点/删除节点</a></li>
												</ul>
											</li>
										 -->	
										<!-- end -->
										${OBJ_ITEM_TREE_DATA}
<!-- <li><span id='2-1' class='data-detail' data-toggle='tooltip' data-placement='top' title='2-1' ><i class='glyphicon glyphicon-minus-sign'></i>[1]2-1</span> <a href='#'>增加节点/删除节点</a><ul><li><span id='2-1|2-1' class='data-detail' data-toggle='tooltip' data-placement='top' title='2-1' ><i class='glyphicon glyphicon-minus-sign'></i>[2]2-1</span> <a href='#'>增加节点/删除节点</a><ul><li><span id='2-1|2-1|1-1-1' class='data-detail' data-toggle='tooltip' data-placement='top' title='1-1-1' ><i class='glyphicon glyphicon-minus-sign'></i>[3]1-1-1</span> <a href='#'>增加节点/删除节点</a><ul></ul></ul></ul></li><li><span id='1-2' class='data-detail' data-toggle='tooltip' data-placement='top' title='1-2' ><i class='glyphicon glyphicon-minus-sign'></i>[1]1-2</span> <a href='#'>增加节点/删除节点</a><ul></ul></li><li><span id='1' class='data-detail' data-toggle='tooltip' data-placement='top' title='1212121' ><i class='glyphicon glyphicon-minus-sign'></i>[1]1212121</span> <a href='#'>增加节点/删除节点</a><ul></ul></li><li><span id='1-1' class='data-detail' data-toggle='tooltip' data-placement='top' title='1-1' ><i class='glyphicon glyphicon-minus-sign'></i>[1]1-1</span> <a href='#'>增加节点/删除节点</a><ul><li><span id='1-1|1-1-A' class='data-detail' data-toggle='tooltip' data-placement='top' title='1-1-A' ><i class='glyphicon glyphicon-minus-sign'></i>[2]1-1-A</span> <a href='#'>增加节点/删除节点</a><ul><li><span id='1-1|1-1-A|1-1-A-1' class='data-detail' data-toggle='tooltip' data-placement='top' title='1-1-A-1' ><i class='glyphicon glyphicon-minus-sign'></i>[3]1-1-A-1</span> <a href='#'>增加节点/删除节点</a><ul></ul><li><span id='1-1|1-1-A|1-1-A-2' class='data-detail' data-toggle='tooltip' data-placement='top' title='1-1-A-2' ><i class='glyphicon glyphicon-minus-sign'></i>[3]1-1-A-2</span> <a href='#'>增加节点/删除节点</a><ul><li><span id='1-1|1-1-A|1-1-A-2|1-1-A-2-item' class='data-detail' data-toggle='tooltip' data-placement='top' title='1-1-A-2-item' ><i class='glyphicon glyphicon-minus-sign'></i>[4]1-1-A-2-item</span> <a href='#'>增加节点/删除节点</a></li></ul></ul><li><span id='1-1|1-1' class='data-detail' data-toggle='tooltip' data-placement='top' title='1-1' ><i class='glyphicon glyphicon-minus-sign'></i>[2]1-1</span> <a href='#'>增加节点/删除节点</a><ul><li><span id='1-1|1-1|1-1-2' class='data-detail' data-toggle='tooltip' data-placement='top' title='1-1-2' ><i class='glyphicon glyphicon-minus-sign'></i>[3]1-1-2</span> <a href='#'>增加节点/删除节点</a><ul></ul></ul></ul></li> -->
										</ul>
										
									</li>
									
									<li><span id="Parent_2"><i class="glyphicon glyphicon-folder-open"></i> 服务器</span> <a href="#">增加节点/删除节点</a>
										<ul>
											<li><span><i class="glyphicon glyphicon-leaf"></i> 工作站服务器</span> <a href="#">增加节点/删除节点</a></li>
										</ul>
									</li>
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
		<input  type="hidden" id="opt_confirm_obj_sub_id" value="" >
		<input  type="hidden" id="opt_confirm_obj_sub_code" value="" >
		<input  type="hidden" id="opt_confirm_obj_sub_name" value="" >
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
                          					<div class="form-group">
<!--                                             <label class="control-label col-md-3" for="add_input_obj_sub_code">三级分类代码</label> -->
<!--                                             <div class="col-md-3"> -->
<!--                                               <input type="text" class="form-control required-input" id="add_input_obj_sub_code" placeholder="三级分类代码"> -->
<!--                                               <span class="text-danger" >*</span> -->
<!--                                             </div> -->
                                            <label class="control-label col-md-3" for="add_input_obj_sub_code">三级分类代码</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_obj_sub_code" placeholder="三级分类代码">
                                              <span class="text-danger">*</span>
                                            </div>
                                            <label class="control-label col-md-3" for="add_input_obj_sub_name">三级分类名称</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_obj_sub_name" placeholder="三级分类名称">
                                              <span class="text-danger">*</span>
                                            </div>
                                          </div>   
                                          <!--  -->
                                          <div class="form-group">
                                            <label class="control-label col-md-3" for="add_input_obj_sub_desc">三级分类说明</label>
                                            <div class="col-md-8">
                                              <input type="text" class="form-control" id="add_input_obj_sub_desc"  placeholder="三级分类说明">
                                            </div>
                                          </div>
                                          <div class="form-group">
                                            <label class="control-label col-md-3" for="add_input_obj_sub_sort">分类排序</label>
                                            <div class="col-md-4">
                                              <input type="number" min="1" value="1" class="form-control" id="add_input_obj_sub_sort"  placeholder="分类排序">
                                            </div>
                                             <label class="control-label col-md-3" for="add_input_obj_mid_code">二级分类代码</label>
                                            <div class="col-md-4">
                                              <input type="text" class="form-control required-input" id="add_input_obj_mid_code" placeholder="二级分类代码">
                                            <span class="text-danger">*</span>
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
										
<!-- 										<div class="form-group"><center><hr class="modal-hr"/></center> -->
<!-- 											<div class="panel-body" id="role_action_option_div"><p>角色对应菜单列表</p> -->
<%-- 												<yb:funActOptHtml ulId="role_action_option"></yb:funActOptHtml> --%>
<!-- 											</div> -->
<!-- 								         </div>   -->
								</div>
               		 </div>
				</div>
				<div class="modal-footer">
<!-- 					<button type="button" class="btn btn-primary">确定</button> --><!-- data-opt-type="" new/edit -->
					<button type="button" id="btn_save_obj_sub" data-opt-type="" class="btn btn-success" data-loading-text="Loading" onclick="saveObjSubConfirm(this);">保&nbsp;&nbsp;存</button>
					<button type="button" class="btn btn-default" data-dismiss="modal" aria-hidden="true" onclick='location.reload();'>关&nbsp;&nbsp;闭</button>

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
<script src="<%=path%>/js/busi-js/query_obj_sub_list.js?v=<%=staticVersion%>" type="text/javascript"></script>

<!-- Script for this page -->
<script type="text/javascript">

$(function () {
	  $('.tree li:has(ul)').addClass('parent_li');
// 	  $('.tree li:has(ul)').addClass('parent_li').find(' > span').attr('title', 'Collapse this branch');
	  $('.tree li.parent_li > span').on('click', function (e) {
	    var children = $(this).parent('li.parent_li').find(' > ul > li');
	    if (children.is(":visible")) {
	      children.hide('fast');
	      $(this).attr('title', 'Expand this branch').find(' > i').addClass('icon-plus').removeClass('icon-minus');
// 	      $(this).attr('title', 'Expand this branch').find(' > i').addClass('icon-plus-sign').removeClass('icon-minus-sign');
	    } else {
	      children.show('fast');
	      $(this).attr('title', 'Collapse this branch').find(' > i').addClass('icon-minus').removeClass('icon-plus');
// 	      $(this).attr('title', 'Collapse this branch').find(' > i').addClass('icon-minus-sign').removeClass('icon-plus-sign');
	    }
	    e.stopPropagation();
	  });
	  
	  
	  //mouse hover //上级选中span闪烁
	  $('.tree li.parent_li > span').hover(function(){
		  console.log("in");
		  $(this).parent().find('span').not('.label').addClass('shine_blue');
//		  $(this).parent().find('span').css("background-color","yellow");
		},function(){
		  console.log("out");
		  $(this).parent().find('span').removeClass('shine_blue');
//		  $(this).parent().find('span').css("background-color","white");
		});
	  
	  //mouse hover //单个 //$("#main").find(".one").not(".two")
	  $('.tree li').not('.parent_li').find(' > span').hover(function(){
		  console.log("in2");
		  $(this).parent().find('span').not('.label').addClass('shine_red');
		},function(){
		  console.log("out2");
		  $(this).parent().find('span').removeClass('shine_red');
		});
	  
	});

	function openUp(){
		$('span[id^=Parent_]').click(); //自动点击，收缩
		getObjectTree();
	}

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
								
								//<li><span><i class="glyphicon glyphicon-leaf"></i>车体</span> <a href="#">增加节点/删除节点</a>
								/*
							 	if(supCodeOld!=supCode){ //new sup
							 		if(treeHtml!=null){
							 			
							 		}
							 		treeHtml+='<li><span><i class="glyphicon glyphicon-minus-sign"></i>[1]'+supName+'</span> <a href="#">增加节点/删除节点</a>';
							 		if(midCode!=null){
							 			treeHtml+='<ul><li><span><i class="glyphicon glyphicon-minus-sign"></i>[2]'+midName+'</span> <a href="#">增加节点/删除节点</a>';
							 			if(subCode!=null){
							 				treeHtml+='<ul><li><span><i class="glyphicon glyphicon-minus-sign"></i>[3]'+subName+'</span> <a href="#">增加节点/删除节点</a>';
							 				if(itemCode!=null){
							 					treeHtml+='<ul><li><span><i class="glyphicon glyphicon-leaf"></i>[4]'+itemName+'</span> <a href="#">增加节点/删除节点</a></li>';
							 				}else{ //item is null
							 					treeHtml+='</li></ul>';
							 				}
							 			}else{//sub is null
							 				treeHtml+='</li></ul>';
							 			}
							 		}else{//mid is null
							 			treeHtml+='</li>';
							 		}
							 		supCodeOld=supCode;
							 	}
							 	else{ //same sup
							 		if(midCodeOld==midCode){//same mid
							 			if(subCodeOld==subCode){//same sub
// 							 				if(itemCodeOld==itemCode){//same item
// 							 				}else{ //deferent item
// 							 				}
							 				treeHtml+='<li><span><i class="glyphicon glyphicon-leaf"></i>[4]'+itemName+'</span> <a href="#">增加节点/删除节点</a></li>';
							 			
							 			  }else{//deferent sub
							 				treeHtml+='</ul><ul><li><span><i class="glyphicon glyphicon-minus-sign"></i>[3]'+subName+'</span> <a href="#">增加节点/删除节点</a>';
								 			if(itemCode!=null){
								 				treeHtml+='<ul><li><span><i class="glyphicon glyphicon-leaf"></i>[4]'+itemName+'</span> <a href="#">增加节点/删除节点</a></li>';
								 			}else{ //item is null
								 				treeHtml+='</ul></li>';
								 			}
								 			subCodeOld=subCode;
							 			}
							 		}else{ //deferent mid
							 			treeHtml+='</ul><ul><li><span><i class="glyphicon glyphicon-minus-sign"></i>[2]'+midName+'</span> <a href="#">增加节点/删除节点</a>';
							 			if(subCode!=null){
							 				treeHtml+='<ul><li><span><i class="glyphicon glyphicon-minus-sign"></i>[3]'+subName+'</span> <a href="#">增加节点/删除节点</a>';
							 				if(itemCode!=null){
							 					treeHtml+='<ul><li><span><i class="glyphicon glyphicon-leaf"></i>[4]'+itemName+'</span> <a href="#">增加节点/删除节点</a></li>';
							 				}else{ //item is null
							 					treeHtml+='</ul></li>';
							 				}
							 			}else{//sub is null
							 				treeHtml+='</ul></li>';
							 			}
							 			midCodeOld=midCode;
							 		}
							 	}
							 	*/
						});
						console.log("treeHtml:="+treeHtml);
// 						$("#tree_content").html(treeHtml);
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
	
	function addObjSub(){
		myModalInit('modal_add_obj_sub');
		activeModalInput('modal_add_obj_sub');
		//btn show
		$('#btn_save_obj_sub').show();
		$('#btn_save_obj_sub').attr('data-opt-type','NEW');
		
		
		if(initMidCode!=null||initMidCode!=undefined){
			$('#add_input_obj_mid_code').val(initMidCode);//自动初始化
		}
		
		$('#modal_add_obj_sub_title').text('新增三级分类');
		$('#modal_add_obj_sub').modal({backdrop: 'static', keyboard: false});
		
		
		
	}
	
	function saveObjSubConfirm(obj){
		var modalOptType=$(obj).attr('data-opt-type');//new or eidt

		var obj_sub_name=$('#add_input_obj_sub_name').val();
		var obj_sub_desc=$('#add_input_obj_sub_desc').val();
		var obj_sub_code=$('#add_input_obj_sub_code').val();
		var obj_sub_id=$('#add_input_obj_sub_id').val();
		var obj_sub_sort=$('#add_input_obj_sub_sort').val();
		
		var obj_mid_code=$('#add_input_obj_mid_code').val();
		
		initMidCode=obj_mid_code;//将上次所需二级代码存为默认代码	
		
		var reqUrl;
		if('NEW'==modalOptType){
			reqUrl='<%=path%>/AjaxChannel?action=BUSI_DATA_OBJ_SUB_ADD_ACTION';
		}
		else if('EDIT'==modalOptType){
			reqUrl='<%=path%>/AjaxChannel?action=BUSI_DATA_OBJ_SUB_EDIT_ACTION';
		}
		else{
			returnErrorMsgShow('modal_add_obj_sub','未知操作类型，请稍后重试')
			return false;
		}
		
		$(obj).button('loading');
		//ajax begin
		$.ajax({
				type : 'POST',
				url:reqUrl,
				data: {OBJ_SUB_NAME:obj_sub_name,OBJ_SUB_DESC:obj_sub_desc,OBJ_SUB_ID:obj_sub_id,
					OBJ_SUB_CODE:obj_sub_code,OBJ_SUB_SORT:obj_sub_sort,OBJ_MID_CODE:obj_mid_code},
				dataType : 'json',
				success : function(json) {
					if(json.SUCCESS=='1'){
							returnSuccessMsgShow('modal_add_obj_sub',json.MSG)
							var fvTable=$("#objSubList").dataTable(); //datatable init current
							fvTable.fnDraw(false);
							$(obj).button('reset');
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
	function editObjSub(obj_sub_id){
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
	
	/*opt confirm*/
	function optConfirm(opt_type,obj_sub_name,obj_sub_id){
		//auto top and height
// 		modal_auto_top($('div.modal-dialog'));

// 		$('#optModal').find("button.btn-primary:first").button('reset');
// 		clearMsgShow('optModal');
//		init common
		myModalInit('optModal');
		
		var title;
		var contentHtml;
		
		$("#opt_confirm_obj_sub_id").val(obj_sub_id);
		$("#opt_confirm_obj_sub_name").val(obj_sub_name);
		$("#opt_confirm_opt_type").val(opt_type);
		
		if("delete"==opt_type){
			title="删除三级分类["+obj_sub_name+"]";
			
			contentHtml=
				"<div class='alert alert-danger' role='alert'>"
					+"<span class='glyphicon glyphicon-exclamation-sign' aria-hidden='true'></span> <span class='sr-only'>注意:</span>"
					+"<strong>注意:</strong>三级分类["+obj_sub_name+"]将彻底删除，无法恢复 !"
				+"</div>";
// 			$('#optModal').find("button.btn-primary:first").click(startRole);

		}else if("stop"==opt_type)	{
			title="停用三级分类["+obj_sub_name+"]";
			
			contentHtml=
				"<div class='alert alert-warning' role='alert'>"
					+"<span class='glyphicon glyphicon-question-sign' aria-hidden='true'></span> <span class='sr-only'>注意:</span>"
					+"<strong>注意:</strong>三级分类["+obj_sub_name+"]将停用，可在三级分类管理恢复。"
				+"</div>";
// 			$('#optModal').find("button.btn-primary:first").click(stopRole);
		}else if("start"==opt_type){
			title="启用三级分类["+obj_sub_name+"]";
			
			contentHtml=
				"<div class='alert alert-info' role='alert'>"
					+"<strong>注意:</strong><span class='glyphicon glyphicon-info-sign' aria-hidden='true'></span> <span class='sr-only'>注意:</span>"
					+"三级分类["+obj_sub_name+"]将被启用。"
				+"</div>";
// 			$('#optModal').find("button.btn-primary:first").click(deleteRole);
		}
		$('#optModal').find("h4.modal-title:first").text(title);
		$('#optModal').find("div.modal-body:first").html(contentHtml);
		$('#optModal').modal({backdrop: 'static', keyboard: false});
	}
	
	function optConfirmDone(obj){
		var objSubId=$("#opt_confirm_obj_sub_id").val();
		var objSubName=$("#opt_confirm_obj_sub_name").val();
		var optType=$("#opt_confirm_opt_type").val();
		
		$(obj).button('loading');
		var reqUrl='<%=path%>/AjaxChannel?action=BUSI_DATA_OBJ_SUB_MOD_ACTION';
		//ajax begin
		$.ajax({
				type : 'POST',
				url:reqUrl,
				data: {OBJ_SUB_ID:objSubId,OPT_TYPE:optType},
				dataType : 'json',
				success : function(json) {
					if(json.SUCCESS=='1'){
							returnSuccessMsgShow('optModal',json.MSG);//alert msg
// 							$(obj).button('complete');//button 
							var fvTable=$("#objSubList").dataTable(); //datatable init current
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