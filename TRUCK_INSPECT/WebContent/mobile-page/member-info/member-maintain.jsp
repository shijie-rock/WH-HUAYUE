<%@ page language="java" contentType="text/html;charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../common/common-soure.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>member-maintain</title>
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
</head>
<body>
	<!-- main page -->
	<div data-role="page" id="member-maintain-div">
		<%@ include file="../common/main-common-head.jsp"%>
		<!-- /main-head -->
		<!-- main content begin -->
		<div data-role="main" class="ui-content" style="margin-top: 70px;" id="member-maintain-main-content">
<!-- 		<form method="post" action="demoform.html"> -->
		   <div class="ui-grid-a">
				<div class="ui-block-a" id="member-info-data-div">
					<label for="fullname">姓名:</label>
					<input type="text" name="fullname" id="member_fullname" value="张三" readonly="readonly">
					
					<label for="mobile">手机:</label>
					<input type="text" name="mobile" id="member_mobile" value="13012345678" readonly="readonly">
					
					<label for="bday">出生年月:</label>
					<input type="date" name="bday" id="member_bday" value="1980-01-01" readonly="readonly">
					
	<!-- 			<label for="email">邮箱:</label> -->
	<!-- 			<input type="email" name="email" id="member_email" placeholder="你的电子邮箱.."> -->
				</div>
				<div class="ui-block-b" id="member-info-img-div" style="text-align: center;padding-left:5px;">
					<img alt="" src="<%=path%>/img/driver-temp.png" style="width: 90%; margin-top: 28px;box-shadow: -3px 9px 16px 2px #888888; ">
				</div>
			</div>
			<div>
				<label for="department">所属部门:</label>
				<input type="text" name="department" id="member_department" value="检车一组" readonly="readonly">
			</div>
			
			<!-- modify -->
			<div class="ui-grid-b">
				<div class="ui-block-a">
					<a href="#" class="ui-btn btn_todoing" data-role="button" id="btn_cust_mod" onclick="actModCustInfo();">修改信息</a>
					<a href="#" class="ui-btn btn_dosaving" data-role="button" style="display:none;" id="btn_cust_save_mod" onclick="saveModeCustInfo();">保存</a>
				</div>
				<div class="ui-block-b">
					<a href="#" class="ui-btn" data-role="button" style="display:none;" id="btn_cust_cancel_mod" onclick="cancelModCustInfo();" >取消</a>
				</div>
				<div class="ui-block-c">
					<a href="#mod_cust_mobile_pop" data-role="button" data-rel="popup" data-position-to="window" data-transition="fade"  class="ui-btn btn_todoing"  id="btn_cust_mobile_mod" onclick="initOpenModMobile();">修改手机</a>
				</div>
			</div>
<!-- 		</form> -->

		<!-- pop begin -->

		    <div data-role="popup" id="mod_cust_mobile_pop" data-overlay-theme="b" data-dismissible="false">
		      <div data-role="header">
		        <h2>修改手机</h2>
		      </div>
		
		      <div data-role="main" class="ui-content" >
		     	  <h4>修改用户手机</h4>
		          <label for="member_mobile_new" class="ui-hidden-accessible">新手机号码:</label>
		          <input type="text" name="member_mobile_new" id="member_mobile_new" placeholder="新手机号码">
		          <div class="ui-grid-a">
		          	<div class="ui-block-a" style="margin-top: 3px;">
			           	 <label for="member_check_code" class="ui-hidden-accessible ui-block-a">验证码:</label>
			          	 <input type="text" name="member_check_code" id="member_check_code" placeholder="验证码" >
		          	 </div>
		          	 <div class="ui-block-b"> 
		          	 	<a href="#" class="ui-btn" data-role="button" id="send_member_mobile_check_code" onclick="sendMobileCheckCode();">发送验证码</a>
		          	 	<a href="#" class="ui-btn" data-role="button" style="display:none" id="sencond_count_down_span"></a>
		          	 </div>
		          </div>
				  <div class="ui-grid-a">
				  	<div class="ui-block-a">
				  	 	<a href="#" class="ui-btn btn_dosaving" id="btn_save_cust_mobile" onclick="saveCustMobile();">保存</a>
				  	</div>
				  	<div class="ui-block-b">
				  		<a href="#" class="ui-btn" data-role="button" data-rel="back">返回</a>
				  	</div>
			       </div>
		      </div>
		
<!-- 		      <div data-role="footer"> -->
<!-- 		        <h1>底部文本</h1> -->
<!-- 		      </div> -->
		    </div>

		<!-- pop end -->
		</div> <!-- main content end -->
		<!-- main-footer -->
		<%@ include file="../common/main-common-footer.jsp"%>
		<!-- /main-footer -->

		<!-- main-panel -->
		<%@ include file="../common/main-panel.jsp"%>
		<!-- /main-panel -->
		
	</div><!-- end content -->

	
<script type="text/javascript">

//头部栏及底部栏按钮显示
$(function() {
	// 	$('#page-return-back').show();//返回按钮显示
	$('#page-order-navcate').hide();//nav 显示
	//设置按钮激活
	$('#page-footer-btn-group a').removeClass(
			"ui-btn-active ui-state-persist");
	$('#page-footer-btn-my').addClass(
			"ui-btn-active ui-state-persist");
	
	
	//click
// 	$("#btn_cust_mod").on("click",function(){

// 	});
	
});

/*激活更改界面*/
function actModCustInfo(){
	$("#btn_cust_mod").hide();
	$("#btn_cust_save_mod").show();
	$("#btn_cust_cancel_mod").show();
	
	$("#member_fullname").removeAttr("readonly");
	$("#member_bday").removeAttr("readonly");
}

/*执行保存动作*/
function saveModeCustInfo(){
	
// 	$ajax();
	alert("save cust success!");
	cancelModCustInfo();
	
}

/*取消激活保存*/
function cancelModCustInfo(){
	$("#btn_cust_mod").show();
	$("#btn_cust_save_mod").hide();
	$("#btn_cust_cancel_mod").hide();
	
	$("#member_fullname").attr("readonly","readonly");
	$("#member_bday").attr("readonly","readonly");
}

/*打开修改手机界面，初始化*/
var timer;//定时器
function initOpenModMobile(){
	console.log("initOpenModMobile begin");
	$("#send_member_mobile_check_code").show();
// 	$("#sencond_count_down_span").text("重新发送"+beginTimeSecond+"秒");
	$("#sencond_count_down_span").hide();
	//清空输入项
	$("#member_mobile_new").val();
	$("#member_check_code").val();
	//清除定时器
	if(timer!=null){
		window.clearInterval(timer);
	}
	console.log("initOpenModMobile end");
}

/*发送验证码*/
function sendMobileCheckCode(){
// 	校验新的手机号格式
// 	member_mobile_new
// 	ajax send
// 	发成功之后，返回success。
// 	发送按钮只读（disable），按钮同时显示倒计时30秒。
	$("#send_member_mobile_check_code").hide();
	$("#sencond_count_down_span").show();
	var beginTimeSecond=30;
	$("#sencond_count_down_span").text("重新发送"+beginTimeSecond+"秒");
	timer=setInterval(function(){
		if(beginTimeSecond>1){
			beginTimeSecond--;
			$("#sencond_count_down_span").text("重新发送"+beginTimeSecond+"秒");
		}else{
			//初始化按钮
			beginTimeSecond=30;
			$("#send_member_mobile_check_code").show();
			$("#sencond_count_down_span").text("重新发送"+beginTimeSecond+"秒");
			$("#sencond_count_down_span").hide();
			if(timer!=null){
				window.clearInterval(timer);
			}
		}
	},1000);
}


/*确认保存手机号码*/
function saveCustMobile(){
	
// 	$ajax();
// 	后台校验验证码，手机号码
	alert("save mobile success!");
	$("#member_mobile").val('');
	$("#mod_cust_mobile_pop").popup("close");
}




</script>	
</body>

</html>