<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="comm/iframeHead.jsp"%>
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
  <!-- Stylesheets -->
  <!-- HTML5 Support for IE -->
  <!--[if lt IE 9]>
  <script src="js/html5shim.js"></script>
  <![endif]-->
  <!-- Favicon -->
  <link rel="shortcut icon" href="img/favicon/favicon.png">
</head>

<body>

<!-- Form area -->
<div class="admin-form">
  <div class="container">

    <div class="row">
      <div class="col-md-12">
        <!-- Widget starts -->
            <div class="widget worange">
              <!-- Widget head -->
              <div class="widget-head">
                <i class="icon-lock"></i> 登录 华悦检车系统
              </div>

              <div class="widget-content" style="margin-bottom: 13px;">
                <div class="padd">
                  <!-- Login form -->
                  <div class="form-horizontal">
                    <!-- Email -->
                    <div class="form-group">
                      <label class="control-label col-lg-3" for="inputEmail">用户名</label>
                      <div class="col-lg-9">
                        <input type="text" class="form-control" id="inputUserName" placeholder="用户名"  autofocus>
                      </div>
                    </div>
                    <!-- Password -->
                    <div class="form-group">
                      <label class="control-label col-lg-3" for="inputPassword">密码</label>
                      <div class="col-lg-9">
                        <input type="password" class="form-control" id="inputPassword" placeholder="密码">
                      </div>
                    </div>
                    <!-- Remember me checkbox and sign in button -->
<!--                     <div class="form-group"> -->
<!-- 					<div class="col-lg-9 col-lg-offset-3"> -->
<!--                       <div class="checkbox"> -->
<!--                         <label> -->
<!--                           <input type="checkbox"> Remember me -->
<!--                         </label> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 					</div> -->
                        <div class="col-lg-9 col-lg-offset-2" style="width: 84%;">
							<button  class="btn btn-danger"  id="login" onclick="login();">登录</button>
<!-- 						<button type="submit" class="btn btn-danger"  id="login" onclick="login();">登录</button> -->
							<button class="btn btn-default" id="reset" onclick="clearInput();">重置</button>
							
							<button class="btn btn-default" id="reset" onclick="openMobileQRCode();" style="height: 31px;float: right;">
								<i class="glyphicon glyphicon-qrcode" style="top:2px;"></i>&nbsp;移动端扫码
							</button>

						</div>
                    <br />
                  </div>
				  
				</div>
                </div>
              
                <div class="widget-foot">
<!--                  	 您还没注册？<a href="#">请注册</a> -->
                </div>
            </div>  
      </div>
    </div>
  </div> 
</div>


	<!-- 二维码 modal-->
	<div id="optModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
		<input  type="hidden" id="mobile-qrcode-url" value="http://10.10.122.54/TRUCK_INSPECT/mobile-page/order-info/order-list.jsp" >

			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title">请用微信或手机扫描二维码，进入检车页面</h4>
				</div>
				<div class="alert opt-result-alert"></div>
				<div class="modal-body" style="text-align: center;">
					<div id="mobile-qrcode"></div>
					<img id="mobile-qrcode-img"/>
				</div>
				<div class="modal-footer">
				<!-- -->
				
				<button type="button" class="btn btn-info" data-loading-text="Loading" onclick="saveFile();">下载二维码图片</button>
				<button type="button" class="btn btn-default" data-dismiss="modal" aria-hidden="true">关&nbsp;&nbsp;闭</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 二维码 modal end -->

<!--  
<span class="totop" style="display: block;top:0px;bottom:auto;"><a><i class="glyphicon glyphicon-qrcode"></i></a></span>	
-->		

<!-- JS -->
<script src="<%=path%>/js/jquery-1.10.2.min.js?v=<%=staticVersion%>"></script><!-- 注意版本，兼容二维码qrcode -->
<script src="<%=path%>/js/bootstrap.js?v=<%=staticVersion%>"></script>
<script src="<%=path%>/js/jquery.qrcode.min.js?v=<%=staticVersion%>"></script>
<script src="<%=path%>/js/jqueryAlert/jquery.alerts.js?v=<%=staticVersion%>" type="text/javascript" charset="utf-8"></script>

<script type="text/javascript">
var qrcode=$("#mobile-qrcode").qrcode({ 
    render:'canvas',
    width: 200, //宽度 
    height:200, //高度 
    text: $('#mobile-qrcode-url').val() //任意内容 
}).hide(); 
var canvas=qrcode.find('canvas').get(0);  
//如果有循环,此句必不可少 qrcode.find('canvas').remove();
var data = canvas.toDataURL('image/jpg');
$('#mobile-qrcode-img').attr('src',data) ;
//saveFile(data,"移动登录二维码");//本地保存二维码

//转为图片的方法,需要上门的data
// function saveFile(data,filename){
function saveFile(){
	var filename="检车移动端二维码";
    var save_link=document.createElementNS('http://www.w3.org/1999/xhtml', 'a');
    save_link.href=data;
    save_link.download=filename;
    var event=document.createEvent('MouseEvents');
    event.initMouseEvent('click',true,false,window,0,0,0,0,0,false,false,false,false,0,null);
    save_link.dispatchEvent(event);
};

function login(){
	var name=$("#inputUserName").val();
	var pass=$("#inputPassword").val();
	if(name==''){
		$("#inputUserName")[0].focus();
		jAlert('用户名不能为空', '提示');
		return;
	}
	if(pass==''){
		jAlert('密码不能为空', '提示');
		return;
	}
	
	var reqUrl='<%=path%>/AjaxChannel?action=LOGIN_ACTION';
	$.ajax({
			type : 'POST',
			url:reqUrl,
			data: {PASS:pass,NAME:name},
			dataType : 'json',
			success : function(json) {
				if(json.SUCCESS=='1'){
					location.href='<%=path%>/page/index.jsp';	
// 					jAlert(json.MSG, '提示',null,function(){
<%-- 						location.href='<%=path%>/page/index.jsp';	 --%>
// 					}); 
				}else{
					 jAlert(json.MSG, '提示'); 
				}
			},
			error : function(e) {
				console.log(e);
			}
		});
}
function clearInput(){
	$("#inputUserName").val("");
	$("#inputPassword").val("");
}

//点击移动端二维码展示，二维码modal
function openMobileQRCode(){
	$('#optModal').modal({backdrop: 'static', keyboard: false});
// 	$('#optModal').modal({backdrop: 'true', keyboard: false});
}

</script>

</body>
</html>