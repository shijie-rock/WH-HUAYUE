<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%
	String path = request.getContextPath();
// 	String staticVersion="0.0.0.1";
	String staticVersion=new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(System.currentTimeMillis()));
%>
<html lang="zh-CN">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta charset="utf-8">
  <!-- Title and other stuffs -->
  <meta name="keywords" content="华悦接口测试页面" />
  <meta name="description" content="华悦接口测试页面" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="author" content="">
<link href="<%=path%>/style/bootstrap.min.css?v=<%=staticVersion%>" rel="stylesheet" />
<script src="<%=path%>/js/jquery.js?v=<%=staticVersion%>"></script> <!-- jQuery -->
<script src="<%=path%>/js/bootstrap.min.js?v=<%=staticVersion%>"></script> <!-- Bootstrap -->
<title>HY-LMS API 接口测试页面</title>
</head>
<body>
<div id="main-div" style="border: 1px solid #fff; text-align: center;padding: 20px;">
<h3>
    <span class="glyphicon glyphicon-retweet"></span>  HY-LMS API 接口测试
</h3>
<form role="form" style="text-align: left;">
  <div class="form-group">
    <label for="json_msg">请求消息</label>
    <input type="text" class="form-control" id="json_msg" placeholder="请输入请求json报文">
  </div>
  <div id="button_group" >
	  <button type="button" class="btn btn-success" onclick="sendJson();">查询</button>
	  <button type="button" class="btn btn-danger" onclick="clearData();"style="margin-left: 10px;">清空</button>
  </div>
  <br/>
  <br/>
  <div class="form-group">
    <label for="response_msg">返回消息</label>
	<textarea class="form-control" rows="10" id="response_msg" readonly="readonly" placeholder="返回应答报文"></textarea>
  </div>
  </form>
</div>

<script type="text/javascript">
	function clearData(){
		$('#json_msg').val('');
		$('#response_msg').val('');
	}
	
	function sendJson(){
		
		var reqUrl='<%=path%>/AjaxChannel?action=SEND_JSON_TEST_ACTION&remote=1';
		$.ajax({
			type : 'POST',
			url:reqUrl,
			data: {JSON_MSG:$('#json_msg').val()},
			dataType : 'json',
			success : function(json) {
				if(json.SUCCESS=='1'){
					
					$('#response_msg').val(json.MSG);
					
				}else{
					
				}
			},
			error : function(e) {
				console.log(e);
			}
		});
	}

</script>

</body>
</html>