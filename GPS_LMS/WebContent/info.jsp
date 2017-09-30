<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="common-file/iframeHead.jsp" %>
<title>位置查询</title>
</head>
<link href="<%=path%>/css/common.css" rel="stylesheet" />
<script src="<%=path%>/js/jquery-1.8.3.min.js" type="text/javascript"></script>

<body>


mac选择：
<select id="macquery" onchange="initDate()">

</select>
&nbsp;&nbsp;
日期选择：
<select id="datequery" onchange="initDateTime()">

</select>
&nbsp;&nbsp;
时刻选择：
<select id="datetimequery"  onchange="queryPositionInfo()">

</select>

<div>
<table id="postiontable">
<!-- <tr id="row10">
<td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
</tr>
<tr id="row9">
<td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
</tr>
<tr id="row8">
<td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
</tr>
<tr id="row7">
<td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
</tr>
<tr id="row6">
<td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
</tr>
<tr id="row5">
<td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
</tr>
<tr id="row4">
<td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
</tr>
<tr id="row3">
<td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
</tr>
<tr id="row2">
<td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
</tr>
<tr id="row1">
<td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
</tr> -->

</table>

</div>
<div id="positioninfo"></div>
<br/>
<div id="xy"></div>

</body>



<script type="text/javascript">
$(function(){
	createTable();
	initMac();
});

function createTable(){
	console.log('talbeHtml');
	var tableHtml='';
	for ( var i=100; i>0; i-- ) {   
		//add row
		tableHtml+=('<tr id="row'+i+'">');
		for(var j=0;j<100;j++){
		//add td
			tableHtml+=('<td></td>');	
		}
		tableHtml+=('</tr>');
	}
	console.log(tableHtml);
	$('#postiontable').html(tableHtml);
}


function initDate(){
	var reqUrl='<%=path%>/AjaxChannel?action=QUERY_INFO_ACTION';
	$.ajax({
		type : 'POST',
		url:reqUrl,
		data: {QT:1,MAC:$('#macquery').val()},
		dataType : 'json',
		success : function(json) {
			if(json.MSG!=undefined){
				var dataqueryOP='<option >请选择</option>';
				$(json.MSG).each(function(i,item){
					dataqueryOP+='<option value="'+item.DATE_QUERY+'">'+item.DATE_QUERY+'</option>';
				});
				$('#datequery').html(dataqueryOP);
			}else{
				alert('error');
			}
		},
		error : function(e) {
			console.log(e);
		}
	});
	
}

function initMac(){
	var reqUrl='<%=path%>/AjaxChannel?action=QUERY_INFO_ACTION';
	$.ajax({
		type : 'POST',
		url:reqUrl,
		data: {QT:2},
		dataType : 'json',
		success : function(json) {
			if(json.MSG!=undefined){
				var macqueryOP='<option >请选择</option>';
				$(json.MSG).each(function(i,item){
					//if(item.MAC_QUERY=='20:59:a0:f4:ab:56')
					macqueryOP+='<option value="'+item.MAC_QUERY+'">'+item.MAC_QUERY+'</option>';
				});
				
				$('#macquery').html(macqueryOP);
			}else {
				alert();
			}
		},
		error : function(e) {
			console.log(e);
		}
	});
}

function initDateTime(){
	
	var reqUrl='<%=path%>/AjaxChannel?action=QUERY_INFO_ACTION';
	$.ajax({
		type : 'POST',
		url:reqUrl,
		data: {QT:3,MAC:$('#macquery').val(),DATE:$('#datequery').val()},
		dataType : 'json',
		success : function(json) {
			if(json.MSG!=undefined){
				var datatimequeryOP='<option >请选择</option>';
				$(json.MSG).each(function(i,item){
					datatimequeryOP+='<option value="'+item.SUBTIME+'">'+item.SUBTIME+'</option>';
				});
				$('#datetimequery').html(datatimequeryOP);
			}else{
				alert('无对应时间数据');
			}
		},
		error : function(e) {
			console.log(e);
		}
	});
}

function queryPositionInfo(){
	
	var reqUrl='<%=path%>/AjaxChannel?action=QUERY_INFO_ACTION';
	$.ajax({
		type : 'POST',
		url:reqUrl,
		data: {QT:4,MAC:$('#macquery').val(),DATE:$('#datequery').val(),DATETIME:$('#datetimequery').val()},
		dataType : 'json',
		success : function(json) {
			if(json.MSG!=undefined){
				var positioninfo='';
				var r1,r2,r3;
				$(json.MSG).each(function(i,item){
					positioninfo+=('WIFF_ID:'+item.WIFF_ID+"--RSSI:"+item.RSSI+"<br/>");
					if(item.WIFF_ID=='ff667700')r1=parseInt(item.RSSI);
					if(item.WIFF_ID=='ff667788')r2=parseInt(item.RSSI);
					if(item.WIFF_ID=='ff667799')r3=parseInt(item.RSSI);
				});
				$('#positioninfo').html(positioninfo);
// 				alert(r1+r2+r3);
// 				alert(((r2*r2)-(r1*r1)+10000)/200);
				$('#xy').html('X:='+Math.round(((r2*r2)-(r1*r1)+10000)/200)+"---Y:="+Math.round(((r2*r2)-(r3*r3)+10000)/200));
				drawPosition(Math.round(((r2*r2)-(r1*r1)+10000)/200),Math.round(((r2*r2)-(r3*r3)+10000)/200));
			}else{
				alert('无数据');
			}
		},
		error : function(e) {
			console.log(e);
		}
	});
}

function drawPosition(x,y){
	var trid='row'+y;
	var trObj=$('#'+trid);
// 	$('#'+trid+' td:nth-child('+x+')').addClass('red')
	$("#postiontable td").removeClass('red');
	trObj.children('td').eq(x-1).addClass('red');
	//alert('')
// 	alert($('#'+trid).html());
}
</script>
</html>