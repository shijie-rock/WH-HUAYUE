<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="comm/iframeHead.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>
<style>
body{
background: #fff;
}
.logo{
margin-bottom: 10px;
    width: 507px;
    height: 180px;
    background-repeat: no-repeat;
    background-position: -10px 0;
    background-size: 100% 100%;
    background-image: url(/AUTH_CENTER/img/zybwl-ybflw.png);
    margin:200px auto;
}
</style>
 <link rel="stylesheet" href="<%=path%>/js/jqueryAlert/jquery.alerts.css" />
				<div id="J_videoContainer"  style="position: absolute;width:100%;height:100%; top: 0%; left: 0px; background-image: url(&quot;/AUTH_CENTER/img/img/page-1.jpg&quot;);" >
  
               <div class="main-content">
                <div class="laymid">
                    <div class="logo"  ></div>
                    <div class="video-loading"></div>
                </div>
            </div>
  </div>
<script src="<%=path%>/js/jquery-2.0.0.js"></script>
<script src="<%=path%>/js/jqueryAlert/jquery.alerts.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
$(function(){
	jAlert('<div class=" row-fluid" style="margin: 0px"><div class="span12 " style="text-align: center">${MSG}</div></div>','消息提示',null,function(){
		if (self != top) {  
			window.parent.location.href='<%=path%>/page/index.jsp';
		}else{
			window.location.href='<%=path%>/page/index.jsp';
		}
	}); 
})
// alert("${MSG}");


/**
function alertMSG() {
    $( "#dialog-confirm" ).dialog({
      resizable: false,
      height:140,
      modal: true,
      buttons: {
        "关闭": function() {
          $( this ).dialog( "close" );
        },
        Cancel: function() {
          $( this ).dialog( "close" );
        }
      }
    });
  }
  */
</script>
<body>
<!-- 
<div id="dialog-confirm" title="${MSG}">
  <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>${MSG}</p>
</div>
 -->
</body>
</html>