<%@ page language="java" contentType="text/html;charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../common/common-soure.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>order list</title>

<script src="<%=path%>/js/jquery.js?v=<%=staticVersion%>"></script>
<!-- jQuery -->
<script src="<%=path%>/js/jquery-mobile/jquery.mobile-1.4.5.js?v=<%=staticVersion%>"></script>
<!-- jQuery mobile -->
<script src="<%=path%>/js/bootstrap.js?v=<%=staticVersion%>"></script>
<!-- Bootstrap -->
<!--
<script src="<%=path%>/js/jquery-mobile/zepto.min.js?v=<%=staticVersion%>"></script>
<script src="<%=path%>/js/jquery-mobile/dropload.js?v=<%=staticVersion%>"></script>
-->
<script src="<%=path%>/js/truck-mobile-common.js?v=<%=staticVersion%>"></script> <!-- mobile common js -->
<script src="<%=path%>/js/busi-js/mobile-order-list.js?v=<%=staticVersion%>"></script> <!-- mobile busi js -->

</head>
<body>
	<!-- main page -->
	<div data-role="page" id="page-order-list">
		<!--<div data-role="content">   -->
		<!-- main-head -->
		<%@ include file="../common/main-common-head.jsp"%>
		<!-- /main-head -->
		<div data-role="main" class="ui-content" style="margin-top: 70px;"
			id="all-check-order-list-content">
			<div data-role="content" id="uncheck-list" style="padding:0;">
				<span class="get-new-span" style="display: none">刷新未检数据... ...</span>
					<ul data-role="listview" data-inset="true" style="z-index:-1">
				      <li data-role="list-divider">鲁F-12345 <span class="ui-li-count">2</span></li>   
				      <li>
				      	<a href="#pageOrderDetail" data-transition="slide" onclick="openDetail('orderid');">   
				        <h2>罐车：车辆检查</h2>
				        <p><b>检查人：管理员 2018-05-18</b></p>
				        <p>车身检查，车头检查。</p>
				        <p class="ui-li-aside order-check-waiting">待检
				        </p></a>
				      </li>
				      <li data-role="list-divider">鲁F-2345 <span class="ui-li-count">10</span></li> 
				      <li>
				      	<a href="#pageOrderDetail" data-transition="slide" onclick="openDetail('orderid');">   
				        <h2>罐车：车辆检查</h2>
				        <p><b>检查人：管理员 2018-05-18</b></p>
				        <p>车身检查，车头检查。</p>
				        <p class="ui-li-aside order-check-waiting">待检
				        </p></a>
				      </li>
				      <li data-role="list-divider">鲁F-12345 <span class="ui-li-count">2</span></li>   
				      <li>
				      	<a href="#pageOrderDetail" data-transition="slide" onclick="openDetail('orderid');">   
				        <h2>罐车：车辆检查</h2>
				        <p><b>检查人：管理员 2018-05-18</b></p>
				        <p>车身检查，车头检查。</p>
				        <p class="ui-li-aside order-check-waiting">待检
				        </p></a>
				      </li>
				      <li data-role="list-divider">鲁F-2345 <span class="ui-li-count">10</span></li> 
				      <li>
				      	<a href="#pageOrderDetail" data-transition="slide" onclick="openDetail('orderid');">   
				        <h2>罐车：车辆检查</h2>
				        <p><b>检查人：管理员 2018-05-18</b></p>
				        <p>车身检查，车头检查。</p>
				        <p class="ui-li-aside order-check-waiting">待检
				        </p></a>
				      </li>
				      <li data-role="list-divider">鲁F-12345 <span class="ui-li-count">2</span></li>   
				      <li>
				      	<a href="#pageOrderDetail" data-transition="slide" onclick="openDetail('orderid');">   
				        <h2>罐车：车辆检查</h2>
				        <p><b>检查人：管理员 2018-05-18</b></p>
				        <p>车身检查，车头检查。</p>
				        <p class="ui-li-aside order-check-waiting">待检
				        </p></a>
				      </li>
				      <li data-role="list-divider">鲁F-2345 <span class="ui-li-count">10</span></li> 
				      <li>
				      	<a href="#pageOrderDetail" data-transition="slide" onclick="openDetail('orderid');">   
				        <h2>罐车：车辆检查</h2>
				        <p><b>检查人：管理员 2018-05-18</b></p>
				        <p>车身检查，车头检查。</p>
				        <p class="ui-li-aside order-check-waiting">待检
				        </p></a>
				      </li>
				      <li data-role="list-divider">鲁F-12345 <span class="ui-li-count">2</span></li>   
				      <li>
				      	<a href="#pageOrderDetail" data-transition="slide" onclick="openDetail('orderid');">   
				        <h2>罐车：车辆检查</h2>
				        <p><b>检查人：管理员 2018-05-18</b></p>
				        <p>车身检查，车头检查。</p>
				        <p class="ui-li-aside order-check-waiting">待检
				        </p></a>
				      </li>
				      <li data-role="list-divider">鲁F-2345 <span class="ui-li-count">10</span></li> 
				      <li>
				      	<a href="#pageOrderDetail" data-transition="slide" onclick="openDetail('orderid');">   
				        <h2>罐车：车辆检查</h2>
				        <p><b>检查人：管理员 2018-05-18</b></p>
				        <p>车身检查，车头检查。</p>
				        <p class="ui-li-aside order-check-waiting">待检
				        </p></a>
				      </li>
				      <li data-role="list-divider">鲁F-12345 <span class="ui-li-count">2</span></li>   
				      <li>
				      	<a href="#pageOrderDetail" data-transition="slide" onclick="openDetail('orderid');">   
				        <h2>罐车：车辆检查</h2>
				        <p><b>检查人：管理员 2018-05-18</b></p>
				        <p>车身检查，车头检查。</p>
				        <p class="ui-li-aside order-check-waiting">待检
				        </p></a>
				      </li>
				      <li data-role="list-divider">鲁F-2345 <span class="ui-li-count">10</span></li> 
				      <li>
				      	<a href="#pageOrderDetail" data-transition="slide" onclick="openDetail('orderid');">   
				        <h2>罐车：车辆检查</h2>
				        <p><b>检查人：管理员 2018-05-18</b></p>
				        <p>车身检查，车头检查。</p>
				        <p class="ui-li-aside order-check-waiting">待检
				        </p></a>
				      </li>
				
				    </ul>
					
				<span class="get-new-page-span" style="display: none">更多未检数据......</span>
				<span class="no-data-span" style="display: none">别扯了，我是有底限的</span>
			</div>
			<div data-role="content" id="checking-list" style="display: none;padding:0;">
				<span class="get-new-span" style="display: none">刷新检中数据... ...</span>
					<ul data-role="listview" data-inset="true" style="z-index:-1">
				      <li data-role="list-divider">鲁F-12345 <span class="ui-li-count">2</span></li>   
				      <li>
				      	<a href="#pageOrderDetail" data-transition="slide" onclick="openDetail('orderid');">   
				        <h2>罐车：车辆检查</h2>
				        <p><b>检查人：管理员 2018-05-18</b></p>
				        <p>车身检查，车头检查。</p>
				        <p class="ui-li-aside order-check-doing">检查中
				        </p></a>
				      </li>
				      <li data-role="list-divider">鲁F-2345 <span class="ui-li-count">10</span></li> 
				      <li>
				      	<a href="#pageOrderDetail" data-transition="slide" onclick="openDetail('orderid');">   
				        <h2>罐车：车辆检查</h2>
				        <p><b>检查人：管理员 2018-05-18</b></p>
				        <p>车身检查，车头检查。</p>
				        <p class="ui-li-aside order-check-doing">检查中
				        </p></a>
				      </li>
				      <li data-role="list-divider">鲁F-12345 <span class="ui-li-count">2</span></li>   
				      <li>
				      	<a href="#pageOrderDetail" data-transition="slide" onclick="openDetail('orderid');">   
				        <h2>罐车：车辆检查</h2>
				        <p><b>检查人：管理员 2018-05-18</b></p>
				        <p>车身检查，车头检查。</p>
				       <p class="ui-li-aside order-check-doing">检查中
				        </p></a>
				      </li>
				      <li data-role="list-divider">鲁F-2345 <span class="ui-li-count">10</span></li> 
				      <li>
				      	<a href="#pageOrderDetail" data-transition="slide" onclick="openDetail('orderid');">   
				        <h2>罐车：车辆检查</h2>
				        <p><b>检查人：管理员 2018-05-18</b></p>
				        <p>车身检查，车头检查。</p>
				        <p class="ui-li-aside order-check-doing">检查中
				        </p></a>
				      </li>
				      <li data-role="list-divider">鲁F-12345 <span class="ui-li-count">2</span></li>   
				      <li>
				      	<a href="#pageOrderDetail" data-transition="slide" onclick="openDetail('orderid');">   
				        <h2>罐车：车辆检查</h2>
				        <p><b>检查人：管理员 2018-05-18</b></p>
				        <p>车身检查，车头检查。</p>
				        <p class="ui-li-aside order-check-doing">检查中
				        </p></a>
				      </li>
				      <li data-role="list-divider">鲁F-2345 <span class="ui-li-count">10</span></li> 
				      <li>
				      	<a href="#pageOrderDetail" data-transition="slide" onclick="openDetail('orderid');">   
				        <h2>罐车：车辆检查</h2>
				        <p><b>检查人：管理员 2018-05-18</b></p>
				        <p>车身检查，车头检查。</p>
				        <p class="ui-li-aside order-check-doing">检查中
				        </p></a>
				      </li>
				      <li data-role="list-divider">鲁F-12345 <span class="ui-li-count">2</span></li>   
				      <li>
				      	<a href="#pageOrderDetail" data-transition="slide" onclick="openDetail('orderid');">   
				        <h2>罐车：车辆检查</h2>
				        <p><b>检查人：管理员 2018-05-18</b></p>
				        <p>车身检查，车头检查。</p>
				        <p class="ui-li-aside order-check-doing">检查中
				        </p></a>
				      </li>
				      <li data-role="list-divider">鲁F-2345 <span class="ui-li-count">10</span></li> 
				      <li>
				      	<a href="#pageOrderDetail" data-transition="slide" onclick="openDetail('orderid');">   
				        <h2>罐车：车辆检查</h2>
				        <p><b>检查人：管理员 2018-05-18</b></p>
				        <p>车身检查，车头检查。</p>
				        <p class="ui-li-aside order-check-doing">检查中
				        </p></a>
				      </li>
				      <li data-role="list-divider">鲁F-12345 <span class="ui-li-count">2</span></li>   
				      <li>
				      	<a href="#pageOrderDetail" data-transition="slide" onclick="openDetail('orderid');">   
				        <h2>罐车：车辆检查</h2>
				        <p><b>检查人：管理员 2018-05-18</b></p>
				        <p>车身检查，车头检查。</p>
				        <p class="ui-li-aside order-check-doing">检查中
				        </p></a>
				      </li>
				      <li data-role="list-divider">鲁F-2345 <span class="ui-li-count">10</span></li> 
				      <li>
				      	<a href="#pageOrderDetail" data-transition="slide" onclick="openDetail('orderid');">   
				        <h2>罐车：车辆检查</h2>
				        <p><b>检查人：管理员 2018-05-18</b></p>
				        <p>车身检查，车头检查。</p>
				        <p class="ui-li-aside order-check-doing">检查中
				        </p></a>
				      </li>
				
				    </ul>
				<span class="get-new-page-span" style="display: none">更多检中数据......</span>
				<span class="no-data-span" style="display: none">别扯了，我是有底限的</span>
			</div>
			<div data-role="content" id="checked-list" style="display: none; padding:0;">
				<span class="get-new-span" style="display: none">刷新已检数据... ...</span>
				    <ul data-role="listview" data-inset="true" style="z-index:-1">
				      <li data-role="list-divider">鲁F-12345 <span class="ui-li-count">2</span></li>   
				      <li><a href="#pageOrderDetailQuery" data-transition="slide" onclick="openDetail('orderid');">   
				        <h2>罐车：车辆检查</h2>
				        <p><b>检查人：管理员 2018-05-18</b></p>
				        <p>车身检查未通过，车头检查通过。</p>
				        <p class="ui-li-aside order-check-not-pass">未通过
				        </p></a>
				<!--    <p class="ui-li-aside">检查结果：通过</p></a> -->
				      </li>
				      <li data-role="list-divider">鲁F-2345 <span class="ui-li-count">10</span></li> 
				      <li><a href="#pageOrderDetailQuery" data-transition="slide" onclick="openDetail('orderid');">
				        <h2>拖车：车辆检查</h2>
				        <p><b>检查人：管理员 2018-05-18</b></p>
				        <p>车身检查通过，车头检查通过。</p>
				        <p class="ui-li-aside order-check-pass">已通过
				        </p></a>
				      </li>
				      <li data-role="list-divider">鲁F-12345 <span class="ui-li-count">2</span></li>   
				      <li><a href="#pageOrderDetailQuery" data-transition="slide" onclick="openDetail('orderid');">   
				        <h2>罐车：车辆检查</h2>
				        <p><b>检查人：管理员 2018-05-18</b></p>
				        <p>车身检查未通过，车头检查通过。</p>
				        <p class="ui-li-aside order-check-not-pass">未通过
				        </p></a>
				<!--    <p class="ui-li-aside">检查结果：通过</p></a> -->
				      </li>
				      <li data-role="list-divider">鲁F-2345 <span class="ui-li-count">10</span></li> 
				      <li><a href="#pageOrderDetailQuery" data-transition="slide" onclick="openDetail('orderid');">
				        <h2>拖车：车辆检查</h2>
				        <p><b>检查人：管理员 2018-05-18</b></p>
				        <p>车身检查通过，车头检查通过。</p>
				        <p class="ui-li-aside order-check-pass">已通过
				        </p></a>
				      </li>
				      <li data-role="list-divider">鲁F-12345 <span class="ui-li-count">2</span></li>   
				      <li><a href="#pageOrderDetailQuery" data-transition="slide" onclick="openDetail('orderid');">   
				        <h2>罐车：车辆检查</h2>
				        <p><b>检查人：管理员 2018-05-18</b></p>
				        <p>车身检查未通过，车头检查通过。</p>
				        <p class="ui-li-aside order-check-not-pass">未通过
				        </p></a>
				<!--    <p class="ui-li-aside">检查结果：通过</p></a> -->
				      </li>
				      <li data-role="list-divider">鲁F-2345 <span class="ui-li-count">10</span></li> 
				      <li><a href="#pageOrderDetailQuery" data-transition="slide" onclick="openDetail('orderid');">
				        <h2>拖车：车辆检查</h2>
				        <p><b>检查人：管理员 2018-05-18</b></p>
				        <p>车身检查通过，车头检查通过。</p>
				        <p class="ui-li-aside order-check-pass">已通过
				        </p></a>
				      </li>
				      <li data-role="list-divider">鲁F-12345 <span class="ui-li-count">2</span></li>   
				      <li><a href="#pageOrderDetailQuery" data-transition="slide" onclick="openDetail('orderid');">   
				        <h2>罐车：车辆检查</h2>
				        <p><b>检查人：管理员 2018-05-18</b></p>
				        <p>车身检查未通过，车头检查通过。</p>
				        <p class="ui-li-aside order-check-not-pass">未通过
				        </p></a>
				<!--    <p class="ui-li-aside">检查结果：通过</p></a> -->
				      </li>
				      <li data-role="list-divider">鲁F-2345 <span class="ui-li-count">10</span></li> 
				      <li><a href="#pageOrderDetailQuery" data-transition="slide" onclick="openDetail('orderid');">   
				        <h2>拖车：车辆检查</h2>
				        <p><b>检查人：管理员 2018-05-18</b></p>
				        <p>车身检查通过，车头检查通过。</p>
				        <p class="ui-li-aside order-check-pass">已通过
				        </p></a>
				      </li>
				      <li data-role="list-divider">鲁F-12345 <span class="ui-li-count">2</span></li>   
				      <li><a href="#pageOrderDetailQuery" data-transition="slide" onclick="openDetail('orderid');">      
				        <h2>罐车：车辆检查</h2>
				        <p><b>检查人：管理员 2018-05-18</b></p>
				        <p>车身检查未通过，车头检查通过。</p>
				        <p class="ui-li-aside order-check-not-pass">未通过
				        </p></a>
				<!--    <p class="ui-li-aside">检查结果：通过</p></a> -->
				      </li>
				      <li data-role="list-divider">鲁F-2345 <span class="ui-li-count">10</span></li> 
				      <li><a href="#pageOrderDetailQuery" data-transition="slide" onclick="openDetail('orderid');">   
				        <h2>拖车：车辆检查</h2>
				        <p><b>检查人：管理员 2018-05-18</b></p>
				        <p>车身检查通过，车头检查通过。</p>
				        <p class="ui-li-aside order-check-pass">已通过
				        </p></a>
				      </li>
				
				    </ul>
				<span class="get-new-page-span" style="display: none">更多已检数据......</span>
				<span class="no-data-span" style="display: none">别扯了，我是有底限的</span>
			</div>

		</div>

		<!-- main-footer -->
		<%@ include file="../common/main-common-footer.jsp"%>
		<!-- /main-footer -->

		<!-- main-panel -->
		<%@ include file="../common/main-panel.jsp"%>
		<!-- /main-panel -->
		<!-- </div> -->
		<!-- end content -->
	</div>
	<!-- end main page -->

	<!-- 检查单详情 可检 -->
	<div data-role="page" id="pageOrderDetail">
		<div data-role="header">
			<h1 id="order-detail-title">检查单详情</h1>
			<a href="#page-order-list"
				class="ui-btn ui-corner-all ui-shadow ui-btn-icon-left ui-icon-back"
				id="page-detail-return-back" data-transition="slide"
				data-direction="reverse">返回</a>
			<!--<a href="#" class="ui-btn ui-corner-all ui-shadow ui-btn-icon-left ui-icon-back" data-rel="back" id="page-detail-return-back" >返回</a> -->
		</div>

		<div data-role="main" class="ui-content">
			<!-- 
   	<p>点击链接返回上一个页面。<b>注意</b>: fade（淡入）效果是默认的</p>
   	<a href="#page-order-list" data-transition="slide" data-direction="reverse" >返回第一个页面</a>
   	 -->
			<!--    	 <div class="ui-field-contain"> -->
			<!--    	 <div class="row"> -->
			<!--
		<label for="fullname">全名:</label>
		<input type="text" name="fullname" id="fullname">
		
		<label for="bday">生日:</label>
		<input type="date" name="bday" id="bday">
		
		<label for="email">E-mail:</label>
		<input type="email" name="email" id="email" placeholder="你的电子邮箱.." class="ui-mini">
	-->
			<table class="col-xs-12">
				<tr>
					<td style="line-height: 50px">
						<div class="order-detail-line">
							<label class="col-xs-4 order-detail-label"
								for="add_input_ins_check_order_no">检查单号</label>
							<div class="col-xs-8">
								<input type="text" class="form-control"
									id="add_input_ins_check_order_no" placeholder="检查单号"
									readonly="readonly">
								<!-- 	           <span class="text-danger">*</span> -->
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td style="line-height: 50px">
						<div class="order-detail-line">
							<label class="col-xs-4 order-detail-label"
								for="add_input_ins_check_order_no">检查人</label>
							<div class="col-xs-8">
								<input type="text" class="form-control"
									id="add_input_ins_check_order_no" placeholder="检查人"
									readonly="readonly">
								<!-- 	           <span class="text-danger">*</span> -->
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td style="line-height: 50px">
						<div class="order-detail-line">
							<label class="col-xs-4 order-detail-label"
								for="add_input_check_target_name">检查对象名称</label>
							<div class="col-xs-8">
								<input type="text" class="form-control"
									id="add_input_check_target_name" placeholder="鲁F-1234"
									readonly="readonly">
								<!-- 	           <span class="text-danger">*</span> -->
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td style="line-height: 50px">
						<div class="order-detail-line">
							<label class="col-xs-4 order-detail-label"
								for="add_input_check_target_name">计划时间</label>
							<div class="col-xs-8">
								<span>05-17 15:00--05-17 18:00</span>
							</div>
						</div>
					</td>
				</tr>
				
				<tr>
				<td style="line-height: 50px">
				<label for="switch"  class="col-xs-11 order-detail-label">检查项目：</label>
				</td>
				</tr>
				<tr><td  class="check-item-top-border"><!-- 检查项目1  -->
				<div class="ui-field-contain" data-order-item-code="ZC-0001">
					<div class="col-xs-8" >车头检查车头检查车头检查车头检查车头检查车头检查车头检查车头检查车头检查</div>
					<div class="col-xs-3" >目测-需拍照</div>

					<div class="col-xs-12" style="float:right;line-height:60px;">
					  
				      <fieldset data-role="controlgroup" data-type="horizontal" style="margin-top: 8px;float:right;">
<!-- 				      <legend>请选择检查结果：</legend> -->
				        <label for="uncheck" >未检</label>
				        <input type="radio" name="gender" id="uncheck" value="uncheck" onclick="hideItemDesc1(this,'ZC-0001');" checked="checked" >
				        <label for="pass" >通过</label>
				        <input type="radio" name="gender" id="pass" value="pass" onclick="hideItemDesc2(this,'ZC-0001');">
				        <label for="not-pass" >不通过</label>
				        <input type="radio" name="gender" id="not-pass" value="not-pass" onclick="displayItemDesc(this,'ZC-0001');"> 
				      </fieldset>
					</div>
					<div class="col-xs-12" data-order-item-desc="ZC-0001" style="display: none;">
<!-- 						<div class="order-detail-line"> -->
							<div class="col-xs-8" style="height:60px">
								<input type="text" class="form-control" id="add_input_ins_check_order_no" placeholder="问题描述" style="height: 43px;">
							</div>
<!-- 						</div> -->
					<a href="<%=path%>/mobile-page/upload-pic.jsp" class="ui-btn col-xs-4 ui-btn-icon-left ui-icon-camera" target="_top">拍照</a>
					</div>
				 </div>
				</td></tr>
				
				<tr><td class="check-item-top-border"><!-- 检查项目2  -->
				<div class="ui-field-contain" data-order-item-code="ZC-0002">
					<div class="col-xs-8" > 车身检查车身检查车身检查车身检查车身检查车身检查车身检查车身检查车身检查车身检查车身检查车身检查车身检查</div>
					<div class="col-xs-3" >目测-需拍照</div>

					<div class="col-xs-12" style="float:right;line-height:60px;">
					  
				      <fieldset data-role="controlgroup" data-type="horizontal" style="margin-top: 8px;float:right;">
<!-- 				       <legend>请选择检查结果：</legend> -->
				        <label for="uncheck1" >未检</label>
				        <input type="radio" name="gender1" id="uncheck1" value="uncheck1" onclick="hideItemDesc1(this,'ZC-0002');" checked="checked" >
				        <label for="pass1" >通过</label>
				        <input type="radio" name="gender1" id="pass1" value="pass1" onclick="hideItemDesc2(this,'ZC-0002');">
				        <label for="not-pass1" >不通过</label>
				        <input type="radio" name="gender1" id="not-pass1" value="not-pass1" onclick="displayItemDesc(this,'ZC-0002');"> 
				      </fieldset>
					</div>
					<div class="col-xs-12" data-order-item-desc="ZC-0002" style="display: none;">
<!-- 						<div class="order-detail-line"> -->
							<div class="col-xs-8" style="height:60px">
								<input type="text" class="form-control" id="add_input_ins_check_order_no" placeholder="问题描述" style="height: 43px;">
							</div>
<!-- 						</div> -->
					<a href="<%=path%>/mobile-page/upload-pic.jsp" class="ui-btn col-xs-4 ui-btn-icon-left ui-icon-camera" target="_top">拍照</a>
					</div>
				 </div>
				</td></tr>
				
			</table>

			<!-- 	</div> -->

		</div>

		<!--
	  <div data-role="footer">
	    <h1>底部文本</h1>
	  </div>
    -->
	</div>
	<!-- 检查单详情end -->
	<!-- 检查单详情 只读 -->
	<div data-role="page" id="pageOrderDetailQuery">
		<div data-role="header">
			<h1 id="order-detail-title">查看检查单详情</h1>
			<a href="#page-order-list"
				class="ui-btn ui-corner-all ui-shadow ui-btn-icon-left ui-icon-back"
				id="page-detail-return-back" data-transition="slide"
				data-direction="reverse">返回</a>
			<!--<a href="#" class="ui-btn ui-corner-all ui-shadow ui-btn-icon-left ui-icon-back" data-rel="back" id="page-detail-return-back" >返回</a> -->
		</div>

		<div data-role="main" class="ui-content">
			<table class="col-xs-12">
				<tr>
					<td style="line-height: 50px">
						<div class="order-detail-line">
							<label class="col-xs-4 order-detail-label"
								for="add_input_ins_check_order_no">检查单号</label>
							<div class="col-xs-8">
								<input type="text" class="form-control"
									id="add_input_ins_check_order_no" placeholder="检查单号"
									readonly="readonly">
								<!-- 	           <span class="text-danger">*</span> -->
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td style="line-height: 50px">
						<div class="order-detail-line">
							<label class="col-xs-4 order-detail-label"
								for="add_input_ins_check_order_no">检查人</label>
							<div class="col-xs-8">
								<input type="text" class="form-control"
									id="add_input_ins_check_order_no" placeholder="张三"
									readonly="readonly">
								<!-- 	           <span class="text-danger">*</span> -->
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td style="line-height: 50px">
						<div class="order-detail-line">
							<label class="col-xs-4 order-detail-label"
								for="add_input_check_target_name">检查对象名称</label>
							<div class="col-xs-8">
								<input type="text" class="form-control"
									id="add_input_check_target_name" placeholder="F-12345"
									readonly="readonly">
								<!-- 	           <span class="text-danger">*</span> -->
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td style="line-height: 50px">
						<div class="order-detail-line">
							<label class="col-xs-4 order-detail-label"
								for="add_input_check_target_name">计划时间</label>
							<div class="col-xs-8">
								<span>05-17 15:00--05-17 18:00</span>
							</div>
						</div>
					</td>
				</tr>
				
				<tr>
				<td style="line-height: 50px">
				<label for="switch"  class="col-xs-11 order-detail-label">检查项目：</label>
				</td>
				</tr>
				<tr><td  class="check-item-top-border"><!-- 检查项目1  -->
				<div class="ui-field-contain" data-order-item-code="ZC-0001">
					<div class="col-xs-8" >车头检查车头检查车头检查车头检查车头检查车头检查车头检查车头检查车头检查</div>
					<div class="col-xs-3" >目测-需拍照</div>

					<div class="col-xs-12" style="float:right;line-height:60px;">
				      <fieldset data-role="controlgroup" data-type="horizontal" style="margin-top: 8px;float:right;">
				        <label for="not-pass7" class="order-check-not-pass" >不通过</label>
				        <input type="radio" name="gender" id="not-pass7" value="not-pass7"  checked="checked" readonly="readonly" > 
				      </fieldset>
					</div>
					<div class="col-xs-12" data-order-item-desc="ZC-0001">
					   		 <a href="#myPopup" data-rel="popup" data-position-to="window" data-transition="pop"><span class="glyphicon glyphicon-picture"></span></a>
					   		 |
					   		 <a href="#myPopup" data-rel="popup" data-position-to="window" data-transition="pop"><span class="glyphicon glyphicon-picture"></span></a>
							    <div data-role="popup" id="myPopup" data-overlay-theme="b">
							      <p>故障图片鲁[F-12345]</p> 
							      <a href="#pageone" data-rel="back" class="ui-btn ui-corner-all ui-shadow ui-btn-a ui-icon-delete ui-btn-icon-notext ui-btn-right">Close</a>
							      <img src="<%=path%>/img/temp/timg (1).jpg" alt="Skaret View">
<%-- 							  <img src="<%=path%>/img/temp/timg (1).jpg" style="width:800px;height:400px;" alt="Skaret View"> --%>
							    </div>
					</div>
				 </div>
				</td></tr>
				
				<tr><td class="check-item-top-border"><!-- 检查项目2  -->
				<div class="ui-field-contain" data-order-item-code="ZC-0002">
					<div class="col-xs-8" > 车身检查车身检查车身检查车身检查车身检查车身检查车身检查车身检查车身检查车身检查车身检查车身检查车身检查</div>
					<div class="col-xs-3" >目测-需拍照</div>

					<div class="col-xs-12" style="float:right;line-height:60px;">
				      <fieldset data-role="controlgroup" data-type="horizontal" style="margin-top: 8px;float:right;">
				        <label for="pass7" class="order-check-pass">通过</label>
				        <input type="radio" name="gender1" id="pass7" value="pass7" checked="checked" readonly="readonly">
				      </fieldset>
					</div>

				 </div>
				</td></tr>
				
			</table>
		</div>
	</div>
	
	<!-- 检查单详情 只读 end-->

	<!-- Script for this page -->
	<script type="text/javascript">
		//页面全局变量定义
		var currentListType = "uncheck";//当前检查点列表显示的类型：uncheck 未检，checking 检中，checked 已检
		
		var uncheck_list_page_no = 1;//当前显示未检车数据页码
		var checking_list_page_no = 1;//当前显示检车中数据页码
		var checked_list_page_no = 1;//当前显示已检车数据页码
		
		var uncheckScrollSize = 0;//未检列表滚动条高度
		var checkingScrollSize = 0;//检中列表滚动条高度
		var checkedScrollSize = 0;//已检滚动条高度

		var scrollHeigh = 0;//当前滚动条的高度
		
		//data dic
		var map=parseData2Map('<yb:dataDic dataDicType="COM_MSG_LEVEL,TRUCK_TYPE,CHECK_ORDER_STATUS,CHECK_ORDER_RESULT"/>');
		function dicTranse(value){
			return map[value]==null?value:map[value];
		}
// 		alert("1");
		//navicate点击初始化,点击激活navicate
		$(document).on(
				"pagecreate",
				"#page-order-list",
				function() {
					// console.log(1);
					$("#page-order-navcate").on(
							"tap",
							"ul li a",
							function() {
								console.log(3);
								console.log(this);
								$("#page-order-navcate ul li a").removeClass(
										"ui-btn-active nav-checked");
								console.log(this);
								$(this).addClass("nav-checked");
								// 		  $(this).css("color","red");
								//#page-order-uncheck
								//#page-order-checking
								//#page-order-checkend
								var attrHref = $(this).attr("href");
								console.log("attrHref:=" + attrHref);
								$("#all-check-order-list-content div").hide();

								if ("#page-order-uncheck" == attrHref) {//display未检
									$("div #uncheck-list").show();
									currentListType = "uncheck";
									
									if($("#uncheck-list ul").children().length>0){
										scrollTo(uncheckScrollSize);
									}
									else{ //如果没有li，则执行一次查询
										uncheck_list_page_no=1;
										queryCheckOrderList("", uncheck_list_page_no, "1");
									}
									//alert('uncheck-list');

								} else if ("#page-order-checking" == attrHref) {//display检中
									$("div #checking-list").show();
									currentListType = "checking";
// 									scrollTo(checkingScrollSize);
									if($("#checking-list ul").children().length>0){
										scrollTo(checkingScrollSize);
									}
									else{ //如果没有li，则执行一次查询
										checking_list_page_no=1;
										queryCheckOrderList("", checking_list_page_no, "2");
									}
									//         	  alert('checking-list');
								} else {//display已检

									$("div #checked-list").show();
									currentListType = "checkend";
// 									scrollTo(checkedScrollSize);
									if($("#checked-list ul").children().length>0){
										scrollTo(checkedScrollSize);
									}
									else{ //如果没有li，则执行一次查询
										checked_list_page_no=1;
										queryCheckOrderList("", checked_list_page_no, "3");
									}
									//         	  alert('checked-list');
								}
							});
				});

		//头部栏及底部栏按钮显示
// 		alert("2");
		$(function() {
			// 	$('#page-return-back').show();//返回按钮显示
			$('#page-order-navcate').show();//nav 显示
			//设置按钮激活
			$('#page-footer-btn-group a').removeClass(
					"ui-btn-active ui-state-persist");
			$('#page-footer-btn-order-list').addClass(
					"ui-btn-active ui-state-persist");
		});

// 		alert("3");
		//监听滚动事件
		document.onscroll = function() {

			if (document.documentElement && document.documentElement.scrollTop) {
				scrollHeigh = document.documentElement.scrollTop;
			} else if (document.body) {
				scrollHeigh = document.body.scrollTop;
			}
			console.log("scroll:=" + scrollHeigh);
			// 	scrollHeigh=scrollHeigh-1;

			if (currentListType == "uncheck") {//记录未检列表滚动条高度
				uncheckScrollSize = scrollHeigh;

			} else if (currentListType == "checking") {//记录检中列表滚动条高度
				checkingScrollSize = scrollHeigh;

			} else {//记录已检列表滚动条高度
				checkedScrollSize = scrollHeigh;

			}
		}
		//向上向下滚动到到底顶部与底部
		/*
		$(document).on(
				"scrollstart",
				function() {
					console.log("开始滚动！" + "scrollHeigh:=" + scrollHeigh);
					if (scrollHeigh == 0) {
						console.log("开始滚动 :滚动条已经到达顶部。");
						getNewData();
					} else {
						if ($(document).scrollTop() >= $(document).height()
								- $(window).height()) {
							console.log("开始滚动 :滚动条已经到达底部为"
									+ $(document).scrollTop());
							getLastPageData();
						}
					}
		});
		*/
// 		alert("4");
		//向下滑动，直到最顶部，刷新列表
		function getNewData() {
			if (currentListType == "uncheck") {//获取最新未检列表
				var uncheck_get_new_span = $("#uncheck-list").children(
						"span.get-new-span:first");
				uncheck_get_new_span.show();
				console.log("uncheck_get_new_span:=" + uncheck_get_new_span);
				// 		ajax for new Data
				console.log("begin uncheck ajax");
// 				sleep(1000);
				uncheck_list_page_no=1;
				queryCheckOrderList("", uncheck_list_page_no, "1");
// 				queryCheckOrderList(memberId, uncheck_list_page_no, "1");

				console.log("end uncheck ajax");
				// 		success
				// 		uncheck_get_new_span.hide();

			} else if (currentListType == "checking") {//取最新检中列表
				var checking_get_new_span = $("#checking-list").children(
						"span.get-new-span:first");
				checking_get_new_span.show();
				// 		ajax for new Data
				console.log("begin checking ajax");
// 				sleep(1000);
				checking_list_page_no=1;
				queryCheckOrderList("", checking_list_page_no, "2");
				console.log("end checking ajax");
				// 		success
				// 		uncheck_get_new_span.hide();

			} else {//取最新已检列表

				var checked_get_new_span = $("#checked-list").children(
						"span.get-new-span:first");
				checked_get_new_span.show();
				// 		ajax for new Data

				console.log("begin checkend ajax");
// 				sleep(1000);
				checked_list_page_no=1;
				queryCheckOrderList("", checked_list_page_no, "3");
				
				console.log("end checkend ajax");
				// 		success
				// 		uncheck_get_new_span.hide();
			}
		}

// 		alert("5");
		function loading(text) {
			$.mobile.loading('show', {
				text : text + '加载中...', //加载器中显示的文字
				textVisible : true, //是否显示文字
				theme : 'a', //加载器主题样式a-e
				textonly : false, //是否只显示文字
				html : "" //要显示的html内容，如图片等
			});
		}
// 		alert("6");
		function hideLoader() {
			//隐藏加载器
			$.mobile.loading('hide');
		}

		//向上滑动，直到最底部，拉取分页新数据
// 		alert("7");
		function getLastPageData() {
			if (currentListType == "uncheck") {//获取下一页未检列表
				var uncheck_get_new_page_span = $("#uncheck-list").children(
						"span.get-new-page-span:first");
				uncheck_get_new_page_span.show();
				// 		ajax for new Data
				// 		append data
				console.log("begin uncheck ajax");
// 				sleep(1000);
				queryCheckOrderList("", uncheck_list_page_no, "1");

				console.log("end uncheck ajax");

			} else if (currentListType == "checking") {//获取下一页检中列表
				var checking_get_new_span = $("#checking-list").children(
						"span.get-new-page-span:first");
				checking_get_new_span.show();
				// 		ajax for new Data
				// 		append data
				console.log("begin checking ajax");
// 				sleep(1000);
				queryCheckOrderList("", checking_list_page_no, "2");
				console.log("end checking ajax");
				// 		success
				// 		uncheck_get_new_span.hide();

			} else {//获取下一页已检列表
				var checked_get_new_span = $("#checked-list").children(
						"span.get-new-page-span:first");
				checked_get_new_span.show();
				// 		ajax for new Data
				// 		append data
				console.log("begin checkend ajax");
// 				sleep(1000);
				queryCheckOrderList("", checked_list_page_no, "3");
				console.log("end checkend ajax");
				// 		success
				// 		uncheck_get_new_span.hide();

			}
		}

// 		alert("8");
		function sleep(n) { //n表示的毫秒数
			var start = new Date().getTime();
			while (true)
				if (new Date().getTime() - start > n)
					break;
		}

		//检查单详情
// 		alert("9");
		function openDetail(orderId) {
			$("#order-detail-title").text("单据详情[" + orderId + "]");
			loading("单据详情[" + orderId + "]");
			//ajax 

		}
		
		
		//不通过展示
// 		alert("10");
		function displayItemDesc(obj,order_item_id){
// 			alert("displayItemDesc");
			var itemDescDiv=findItemDesc(obj,order_item_id);
			console.log(itemDescDiv);
			if(itemDescDiv.is(':hidden')){
				itemDescDiv.show(500);
			}
			$(obj).parent().parent().find("label").removeClass("order-check-not-pass order-check-pass");
			$(obj).prev().addClass("order-check-not-pass");//label
		}
		
// 	      未检点击
// 		alert("11");
		function hideItemDesc1(obj,order_item_id){
// 			alert("hideItemDesc");
			var itemDescDiv=findItemDesc(obj,order_item_id);
			console.log(itemDescDiv);
			if(itemDescDiv.is(':visible')){
				itemDescDiv.hide(300);
			}
			//remove label class ui-btn ui-corner-all ui-btn-inherit ui-first-child 
			$(obj).parent().parent().find("label").removeClass("order-check-not-pass order-check-pass");
// 			$(obj).prev().addClass("ui-radio-off");//label
		}
		
		//通过展示
// 		alert("12");
		function hideItemDesc2(obj,order_item_id){
// 			alert("hideItemDesc");
			var itemDescDiv=findItemDesc(obj,order_item_id);
			console.log(itemDescDiv);
			if(itemDescDiv.is(':visible')){
				itemDescDiv.hide(300);
			}
			$(obj).parent().parent().find("label").removeClass("order-check-not-pass order-check-pass");
			$(obj).prev().addClass("order-check-pass");//label
		}
// 		alert("13");
		function findItemDesc(obj,order_item_id){
// 			return $(obj).parent().parent(); data-order-item-desc
			return $("div[data-order-item-desc='"+order_item_id+"']").first();
		}
		
// 		alert("14");
		$("body").on("touchstart", function(e) {
		    // 判断默认行为是否可以被禁用 --浏览器有效，手机端无效，需要注释掉
		    /*
		    if (e.cancelable) {
		        // 判断默认行为是否已经被禁用
		        if (!e.defaultPrevented) {
		            e.preventDefault();
		        }
		    }   
		    */
		    startX = e.originalEvent.changedTouches[0].pageX,
		    startY = e.originalEvent.changedTouches[0].pageY;
		});
// 		alert("15");
		$("body").on("touchmove", function(e) {         
		    // 判断默认行为是否可以被禁用 --浏览器有效，手机端无效，需要注释掉
		    /*
		    if (e.cancelable) {
		        // 判断默认行为是否已经被禁用
		        if (!e.defaultPrevented) {
		            e.preventDefault();
		        }
		    }   
		    */
		    moveEndX = e.originalEvent.changedTouches[0].pageX,
		    moveEndY = e.originalEvent.changedTouches[0].pageY,
		    X = moveEndX - startX,
		    Y = moveEndY - startY;
		    //左滑
		    if ( X > 20 ) {
		        //alert('左滑');                
		    }
		    //右滑
		    else if ( X < -20 ) {
		        //alert('右滑');    
		    }
		    //下滑
		    else if ( Y >= 20) {
		        //alert('下滑');
		       if (scrollHeigh == 0) {
				console.log("向下滑动 :滚动条已经到达顶部。");
				getNewData();
		       }
		    }
		    //上滑
		    else if ( Y <= -20 ) {
		        //alert('上滑');  
		      if ($(document).scrollTop() >= $(document).height()- $(window).height()) {
				console.log("向上滑动 :滚动条已经到达底部为"+ $(document).scrollTop());
					getLastPageData();
				}
		    }
		    //单击
// 		    else{
// 		        alert('单击');    
// 		    }
		});
		
		
		
	</script>

</body>
</html>