<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
	<!--
  		设置panal及退出
    -->
	<div data-role="panel" id="pushPanel" data-display="push" data-position="right"  data-theme="b">
   	 <h2>设置</h2>
	    <ul data-role="listview" data-inset="true">
	      <li><a href="#">关于</span></a></li>
	      <li><a href="#">公告<span class="ui-li-count">2</span></a></li>
	      <li><a href="#logoutPopupDialog" data-rel="popup" data-position-to="window">退出</a></li>
	    </ul>
	  <!--  
	  <a href="#pageone" data-rel="close" class="ui-btn ui-btn-inline ui-shadow ui-corner-all ui-btn-a ui-icon-delete ui-btn-icon-left">关闭面板</a>	
		-->  
	</div>
	
	<!-- 登出 -->
	<div data-role="popup" id="logoutPopupDialog" data-overlay-theme="b" data-theme="b">
	  <div data-role="header"><h1>系統退出</h1></div>
	  <div data-role="main" class="ui-content">
	  	<p>确认要退出登录？</p>
			<a href="#pageone" class="ui-btn ui-btn-inline">确认</a>
		    <a href="#pageone" class="ui-btn ui-btn-inline">取消</a>
	  <!--<div data-role="footer"><h1>底部文本..</h1></div>-->
	  </div>
            <!--
            	/popup> main
            -->
	</div>