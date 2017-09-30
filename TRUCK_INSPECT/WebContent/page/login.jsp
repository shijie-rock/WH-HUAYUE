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
                <i class="icon-lock"></i> 登录 
              </div>

              <div class="widget-content">
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
                        <div class="col-lg-9 col-lg-offset-2">
							<button  class="btn btn-danger"  id="login" onclick="login();">登录</button>
<!-- 						<button type="submit" class="btn btn-danger"  id="login" onclick="login();">登录</button> -->
							<button class="btn btn-default" id="reset" onclick="clearInput();">重置</button>
						</div>
                    <br />
                  </div>
				  
				</div>
                </div>
              
                <div class="widget-foot">
                 	 您还没注册？<a href="#">请注册</a>
                </div>
            </div>  
      </div>
    </div>
  </div> 
</div>
	
		

<!-- JS -->
<script src="<%=path%>/js/jquery.js?v=<%=staticVersion%>"></script>
<script src="<%=path%>/js/bootstrap.js?v=<%=staticVersion%>"></script>
<script src="<%=path%>/js/jqueryAlert/jquery.alerts.js?v=<%=staticVersion%>" type="text/javascript" charset="utf-8"></script>

<script type="text/javascript">
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
	
	var reqUrl='<%=path%>/AjaxChannel?action=SYS_LOGIN_ACTION';
	$.ajax({
			type : 'POST',
			url:reqUrl,
			data: {pass:pass,name:name},
			dataType : 'json',
			success : function(json) {
				if(json.SUCCESS=='1'){
					jAlert(json.MSG, '提示',null,function(){
						location.href='<%=path%>/page/content/vehicle_query.jsp';	
					}); 
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

</script>

</body>
</html>