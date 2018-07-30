<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<!-- 各个主页面的head（不含list） -->
<div data-role="header" style="position: fixed; top: 0px; width: 100%; z-index: 100;"
	data-fullscreen="true">
	<h1>Client Test</h1>
	<a href="#" class="ui-btn ui-corner-all ui-shadow ui-btn-icon-left ui-icon-back" data-rel="back" id="page-return-back" style="display:none;">返回</a>
	<a href="#pushPanel" class="ui-btn ui-corner-all ui-shadow ui-icon-bullets ui-btn-icon-left">设置</a>
		<div data-role="navbar" id="page-order-navcate" style="display:none;">
			<ul>
				<li><a href="#page-order-uncheck"  data-transition="slide" class="ui-btn-active">待检</a></li>
				<li><a href="#page-order-checking" data-transition="slide">检中</a></li>
				<li><a href="#page-order-checkend" data-transition="slide">已检</a></li>
			</ul>
		</div>
</div>