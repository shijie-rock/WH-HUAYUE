<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../comm/iframeHead.jsp"%>
 <html lang="zh-CN">
<head>
 <title>车辆检查登录</title>
 <link href="<%=path%>/css/vehicle-signin.css" rel="stylesheet">
</head>
  <body>

    <div class="container">

      <div class="form-signin">
        <h3 class="form-signin-heading">请登录-车辆信息查询</h3>
<!--         <label for="inputEmail" class="sr-only">Email address</label> -->
        <input type="text" id="inputEmail" class="form-control" placeholder="用户名" required autofocus>
<!--         <label for="inputPassword" class="sr-only">Password</label> -->
        <input type="password" id="inputPassword" class="form-control" placeholder="密码" required style="margin-top:10px;">
        <div class="checkbox">
          <label>
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" id="login" onclick="login();">登录</button>
      </div>
    </div> <!-- /container -->
</body>
<script src="<%=path%>/js/jquery-2.0.0.js"></script>
<script src="<%=path%>/js/jqueryAlert/jquery.alerts.js" type="text/javascript" charset="utf-8"></script>
<%-- <script src="<%=path%>/js/bootstrap/js/bootstrap.min.js"></script> --%>
<script type="text/javascript">
	function login(){
		var name=$("#inputEmail").val();
		var pass=$("#inputPassword").val();
		if(name==''){
			$("#inputEmail")[0].focus();
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
</script>
</html>