/* Widget close */

var HY_CONSTANT={};

HY_CONSTANT.MAP_AK='XoEid9f6nHRuSxHEULZTvYwEdvK1Uhq1';/*百度地图ak*/


$('.wclose').click(function(e){
  e.preventDefault();
  var $wbox = $(this).parent().parent().parent();
  $wbox.hide(400);
});

//init function
//模态框随滚动条移动
window.parent.onscroll= function(){
	$('div.modal-dialog').css("margin-top",window.parent.getScrollTop()+20+"px");
	console.log('left'+$('div.modal-dialog').position().left);
	console.log('top'+$('div.modal-dialog').position().top);
	console.log('margin-top'+$('div.modal-dialog').css("margin-top"));
	if($('div.tangram-suggestion-main').is(":visible")){//baidu map 随动
//		$('div.tangram-suggestion-main').css("margin-top",window.parent.getScrollTop()+"px");
		$('div.tangram-suggestion-main').css("display","none");//hide suggest //改为隐藏
	}
	
	//滚动时 不显示时间控件
//	$('div.datetimepicker').parent("body").click();
//	$('div.datetimepicker').css("display","none");
	$('div.layui-laydate').css("display","none");
	$('input.layer-date').blur();//失焦，下次再次激活
	$('ul.ui-autocomplete').css("display","none");//自动填充隐藏
//	$('div.layui-laydate').css("margin-top",window.parent.getScrollTop()+20+"px");
	console.log('div margin-top'+$('div.layui-laydate').css("margin-top"));
}

//window.onscroll= function(){
//	$('ul.ui-autocomplete').css("display","none");//自动填充隐藏
//}

$("div").scroll(function() {
	$('ul.ui-autocomplete').css("display","none");//自动填充隐藏
	$('div.layui-laydate').css("display","none");
	$('input.layer-date').blur();//失焦，下次再次激活
	
	//fullcalendar
	$('div.popover.fade.top.in').hide();
});

//模态框展示时，调用
//$('div.modal-dialog').on('show.bs.modal', function () {
	  // 执行一些动作...
//	modal_auto_top(this);
//	alert();
//})


/* Widget minimize */
  $('.wminimize').click(function(e){
    e.preventDefault();
    var $wcontent = $(this).parent().parent().next('.widget-content');
    var isQueryCondition=$wcontent.hasClass("query-condition");/*is query condition div?*/
    var queryConditionSummry = $(this).parent().prev('.query-condition-summry');/* condition summry element*/
    if($wcontent.is(':visible')) 
    {
      $(this).children('i').removeClass('icon-chevron-up');
      $(this).children('i').addClass('icon-chevron-down');
      //up
      if(isQueryCondition==true){
    	  queryConditionSummry.text(query_condition_append()); /*display query condition summry*/
    	  queryConditionSummry.show(400);
      }
    }
    else 
    {
      $(this).children('i').removeClass('icon-chevron-down');
      $(this).children('i').addClass('icon-chevron-up');
      //down
      if(isQueryCondition==true){
    	  //queryConditionSummry.text(''); /*clear query condition summry*/
    	  queryConditionSummry.hide(400,function(){
    		  queryConditionSummry.text(''); 
    	  });
      }
    }            
    $wcontent.toggle(500);
  }); 

  /*child modal auto top and height*/
  function modal_auto_top(obj){
	  $(obj).css("margin-top",window.parent.getScrollTop()+20+"px"); //set modal top 
	  var exporlorHight=$(window.parent.document.body)[0].clientHeight; //set modal hight by user's exprolor
//	  $(obj).css("max-height",(exporlorHight-200)+"px");
	  $(obj).find("div.modal-scrollable").css("max-height",(exporlorHight-300)+"px");
  }
  
//	 window.parent.document.getElementById("test").value; 
  
	//query condition append v1 //not support checkbox
	function query_condition_append_v1(){ //20170720
//		var conditionLabel =$('div.query-condition label').size();
//		var conditionInput =$('div.query-condition input,div.query-condition select').size();
		
		var conditionLabelArr=new Array();
		var conditionInputArr=new Array();
		
		$('div.query-condition label').each(function(index,data){
			conditionLabelArr[index]=$(data).text();
		});
		
		$('div.query-condition input,div.query-condition select').each(function(index,data){
			conditionInputArr[index]=$(data).val();
		});
		
		var queryConditionStr='';
		$.each(conditionInputArr,function(index,data){
			if(data==null||data=='')return true;
			var labelName;
			if(conditionLabelArr.length<index+1){
				labelName='';
			}else{
				labelName=conditionLabelArr[index];
			}
			queryConditionStr+=(labelName+"="+data+"; ");
			
		});
		if(queryConditionStr.length>60)queryConditionStr=queryConditionStr.substring(0,60);
		
		//alert("["+queryConditionStr+"]");
		return "[ "+queryConditionStr+"]";
	}

	//query condition append v2 //add checkbox value //20170725
	function query_condition_append(){
		var conditionLabelArr=[],conditionInputArr=[];
		
		$('div.query-condition input,div.query-condition select').each(function(index,data){ //get input

			var labelName=$(this).parent().parent().children("label").first().text();// get label name
			console.log(labelName);
			console.log($(this).attr("type"));
			console.log($(this).attr("checked"));
			if("checkbox"==$(this).attr("type")){ //check box 
				if($(this).is(':checked')){
//				if("checked"==$(this).attr("checked")){ //error
					conditionInputArr.push('是');
					conditionLabelArr.push(labelName);
				}
			}else{ //other input
				conditionInputArr.push($(data).val());
				conditionLabelArr.push(labelName);
			}
		});
		var queryConditionStr='';
		$.each(conditionInputArr,function(index,data){
			if(data==null||data=='')return true;
			var labelName;
			if(conditionLabelArr.length<index+1){
				labelName='';
			}else{
				labelName=conditionLabelArr[index];
			}
			queryConditionStr+=(labelName+"="+data+"; ");
			
		});
		if(queryConditionStr.length>60)queryConditionStr=queryConditionStr.substring(0,60)+'...';
		
		//alert("["+queryConditionStr+"]");
		if(queryConditionStr!='')
		return "[ "+queryConditionStr+"]";
		else return '';
	}
	
	/*重置按钮，清理输入条件*/
	function queryBtnRestClear(){
		$('div.query-condition input,div.query-condition select').each(function(index,data){ //get input
			if("checkbox"==$(this).attr("type")){ //check box 
				if($(this).is(':checked')){
//				if("checked"==$(this).attr("checked")){ //error
					$(this).removeAttr("checked");
				}
			}else{ //other input
				$(data).val('');
			}
		});
		
	}
	
	/*清理输入*/
	function clearModalContent(modal_id){
// 		  $("#"+obj).find("input.form-control");
// 		  $("#"+obj).find(":text").each(function(){
// 			    $(this).val('');
// 		  });
		  $('#'+modal_id).find(':text').val('');//clear check box
		  $('#'+modal_id).find(":hidden [data-ins-init-need-clear='true']").val('');//clear hide need clear
		  $('#'+modal_id).find("[type='number']").val('1');//clear check box
		  $('#'+modal_id).find('.modal-title').text('');//clear title
		  $('#'+modal_id).find(':checkbox').removeAttr('checked');//clear check box
		  //radio //
		  $('#'+modal_id).find('input:radio').removeAttr('checked');
		  //select
		  $('#'+modal_id).find('select').val('');
		  //textarea
		  $('#'+modal_id).find('textarea').val('');
		  //img
		  $('#'+modal_id).find('img').attr('src','');
	}
	/*模态框显示提示信息-失败或者警告*/
	function returnErrorMsgShow(modal_id,err_msg){
		
		if(err_msg=='' ||err_msg==undefined || err_msg==null){
			return false;
		}
		var obj=$('#'+modal_id).find("div.opt-result-alert");
//		$("div.opt-result-alert").hide();//clear other alert div
//		$("div.opt-result-alert").removeClass("alert-success").removeClass("alert-danger");
//		$("div.opt-result-alert").addClass("alert-danger");
//		$("div.opt-result-alert").text("保存成功！");
//		$("div.opt-result-alert").slideDown(500).delay(3000).slideToggle(500);
		
		$(obj).hide();//clear other alert div
		$(obj).removeClass("alert-success").removeClass("alert-danger");
		$(obj).addClass("alert-danger");
		$(obj).text(err_msg);
		$(obj).slideDown(500).delay(3000).slideToggle(500);
		
	}
	
	function returnSuccessMsgShow(modal_id,err_msg){
		
		if(err_msg=='' ||err_msg==undefined || err_msg==null){
			return false;
		}
		
		var obj=$('#'+modal_id).find("div.opt-result-alert");
		$(obj).hide();//clear other alert div
		$(obj).removeClass("alert-success").removeClass("alert-danger");
		$(obj).addClass("alert-success");
		$(obj).text(err_msg);
		$(obj).slideDown(500).delay(2000).slideToggle(500);
		
	}
	
	/*modal 初始化*/
	function myModalInit(modal_id){
		modal_auto_top($('div.modal-dialog'));// auto top modal
		clearMsgShow(modal_id);// clear msg show
		resetBtn(modal_id);//reset btn
		clearModalContent(modal_id)//clear input
	}
	/*清理提示框*/
	function clearMsgShow(modal_id){
		var obj=$('#'+modal_id).find("div.opt-result-alert");
		$(obj).removeClass("alert-success").removeClass("alert-danger");
		$(obj).text("");
		$(obj).hide();
	}
	/*重置按钮可用*/
	function resetBtn(modal_id){
		$('#'+modal_id).find("button.btn-primary:first").button('reset');
	}
	
	/*modal content readOnly*/
	function readOnlyModalInput(modal_id){
		$('#'+modal_id).find(":text").attr('readonly','');
		$('#'+modal_id).find("[type='number']").attr('readonly','');
		$('#'+modal_id).find(":password").attr('readonly','');
		$('#'+modal_id).find(":radio").attr('disabled','disabled');
		$('#'+modal_id).find(":checkbox").attr('disabled','disabled');
		$('#'+modal_id).find("select").attr('disabled','disabled');
		$('#'+modal_id).find("textarea").attr('disabled','disabled');
	}
	/*modal content active*/
	function activeModalInput(modal_id){
		$('#'+modal_id).find(":text").removeAttr('readonly');
		$('#'+modal_id).find("[type='number']").removeAttr('readonly');
		$('#'+modal_id).find(":checkbox").removeAttr('disabled');
		$('#'+modal_id).find("select").removeAttr('disabled');
		$('#'+modal_id).find("textarea").removeAttr('disabled');
		
		$('#'+modal_id).find(":password").removeAttr('disabled');
		$('#'+modal_id).find(":radio").removeAttr('disabled');
	}
	
//	$('body').on('mouseover',"[data-toggle='tooltip']",function(){
//		$(this).tooltip();
//	});
	
	function parseNull2Empty(value){
		return value==''||value==null?'--':value;
	}
	
	/**传入逗号分割的字符串（每组冒号分割），转为map。如：SEX_0010:男,SEX_0020:女 **/
	function parseData2Map(str){
		var strArray=str.split(',');
		//alert(strArray.length);
		var map = {}; // Map map = new HashMap();
		var dataArray;
		for(var i=0;i<strArray.length;i++)
		{
		dataArray=strArray[i].split(':');
		map[dataArray[0]]=dataArray[1];
		//alert(map);
		}
		return map;
	}
	/*左侧导航栏收起或展开*/
	function packUpDown(obj){
		var indexSidebar=$('#index-sidebar', parent.document);
		var indexContent=$('#index-content', parent.document);
		var displayDiv=$('#div-fix-display-menu', parent.document);
		if(indexSidebar.is(':hidden')){
			indexSidebar.show(50);
//			indexSidebar.animate({'width':'230'},300);
			indexContent.animate({'margin-left':'230'},150);
			$(obj).children('span').text('展开');
			$(obj).children('i').removeClass('glyphicon-menu-right').addClass('glyphicon-menu-left');
			displayDiv.hide(200);
		}else{
			indexSidebar.hide();
//			indexSidebar.animate({'width':'0'},300);
			indexContent.animate({'margin-left':'0'},150);
			$(obj).children('span').text('收起');
			$(obj).children('i').removeClass('glyphicon-menu-left').addClass('glyphicon-menu-right');
			displayDiv.show(200);
		}
	}
	
	//重新设置iframe的高度，在dataTable 返回结果 回调 执行调用。
	function resetIFrameLength(){
		console.log('(document).height()1:='+$("div.page-head").css("height"));
		console.log('(document).height()2:='+$("div.matter").css("height"));
		
		console.log('$(iframepage).height() set before:='+$('#iframepage',window.parent.document).css("height"));
		
		var headHeight=parseInt($("div.page-head").css("height"));
		var tableHeight=parseInt($("div.matter").css("height"));
//		var bufferHeight=50;
		var bufferHeight=100;
		
		var newHight=headHeight+tableHeight+bufferHeight;
		newHight=newHight<700?700:newHight;
		
//		$('#iframepage',window.parent.document).css("height",(headHeight+tableHeight+bufferHeight)+'px');
		$('#iframepage',window.parent.document).css("height",(newHight)+'px');
		
		console.log('$(iframepage).height() set finish:='+$('#iframepage',window.parent.document).css("height"));
		
	}
	   /**
	    * 判断浏览器v2-20171219
	    * @returns {String}
	    */
	   function checkBrowser(){  
		   var ua = navigator.userAgent.toLocaleLowerCase();  
		   var browserType=null;  
		       if (ua.match(/msie/) != null || ua.match(/trident/) != null) {  
		          browserType = "IE";  
		          browserVersion = ua.match(/msie ([\d.]+)/) != null ? ua.match(/msie ([\d.]+)/)[1] : ua.match(/rv:([\d.]+)/)[1];  
		   } else if (ua.match(/firefox/) != null) {  
		          browserType = "FF";  
		   }else if (ua.match(/ubrowser/) != null) {  
		          browserType = "UC";  
		   }else if (ua.match(/opera/) != null) {  
		          browserType = "OPERA";  
		   } else if (ua.match(/bidubrowser/) != null) {  
		          browserType = "BAIDU";    
		   }else if (ua.match(/metasr/) != null) {  
		          browserType = "SOGOU";    
		   }else if (ua.match(/tencenttraveler/) != null || ua.match(/qqbrowse/) != null) {  
		          browserType = "QQ";  
		   }else if (ua.match(/maxthon/) != null) {  
		          browserType = "MAXTHON";  
		   }else if (ua.match(/chrome/) != null) {  
		   var is360 = _mime("type", "application/vnd.chromium.remoting-viewer");  
		   function _mime(option, value) {  
		               var mimeTypes = navigator.mimeTypes;  
		               for (var mt in mimeTypes) {  
		               if (mimeTypes[mt][option] == value) {  
		                      return true;  
		                 }  
		               }  
		               return false;  
		           }  
		   if(is360){                 
		   browserType = '360';    
		                }else{    
		               browserType = "CHROME";    
		                }    
		            
		   }else if (ua.match(/safari/) != null) {  
		          browserType = "SAFARI";  
		   }  
		   return browserType;  
		   } 
	   
	   
	  function recordMainPageBrowserType(pageCode){
		    var url = "http://auth.qa.youbus.com.cn/AUTH_CENTER/jsonP?action=REMOTE_QUERY_ADVLIST_ACTION&FILE_CODE=1213121";
//		    var url = "http://gps.youbus.com.cn/YOUBUS_GPS/jsonP?action=RT_RECORD_PAGE_VISIT_BROWSER_INFO_ACTION";
		    var browserType=checkBrowser();
		    var data = {
		    		BROWSER_TYPE: browserType,//浏览器类型
		            PAGE_CODE: pageCode,//文章CODE
//		            isShowError: "0"
		    };   
		    $.ajax({
		        type: "POST",
		        url: url,
		        async: true,
//		        dataType: "json",
		        data: data,
		        dataType:'jsonp',
		        contentType: "application/jsonp; charset=utf-8",
		        jsonpCallback:'callback',
		        jsonp: "callback",
		        success: function(reuslt) {
		        	 	console.log(reuslt);
		        	 	console.log("result:="+reuslt.AVD_LIST);
		               //callback(reuslt.AVD_LIST);
		        },  
		       error : function() {  
//		               if(data.isShowError!=null && data.isShowError=="0") return;      
//		               if(data.errorCallback!=null) {
//		                   data.errorCallback();       
//		                   return;      
//		               }
//		               alert("网络异常，请稍后尝试！");
		       } 
//		       error: function(XMLHttpRequest, textStatus, errorThrown) {
//		           console.log(XMLHttpRequest);
////		           alert(XMLHttpRequest.status);
////		           alert(XMLHttpRequest.readyState);
////		           alert(textStatus);
//		             }
		    });
	  }
	  function callback(advList){
		  console.log(advList);
//		  var obj = eval('(' + advList + ')');
//		  $.each(obj,function(date){
//			  console.log(this);
//		  });
	  }
	  
	  function jsonPX(){
		var url = "http://auth.qa.youbus.com.cn/AUTH_CENTER/jsonP?action=REMOTE_QUERY_ADVLIST_ACTION&FILE_CODE=1213121";
		var data = {
				FILE_CODE: '1213121'
			};
		  $.ajax({
				type: "POST",
				url: url,
				async: true,//false使用同步的方式,true为异步方式
				data: data,
				dataType:'jsonp',
			    contentType: "application/jsonp; charset=utf-8",
				jsonpCallback:'callback',
				jsonp: "callback",
				success: function(reuslt) {
					console.log("jsonPX:="+reuslt.AVD_LIST);
					var obj = eval('(' + reuslt.AVD_LIST + ')');
					  $.each(obj,function(date){
					  console.log(this);
					  });
				},  
			   	error : function() {  
			   		
			   		console.log('error');  	   		
			   	} 
			});  
	  }
	  
		function getUrlParam(name){  
			//构造一个含有目标参数的正则表达式对象  
			var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");  
			//匹配目标参数  
			var r = window.location.search.substr(1).match(reg);  
			//返回参数值  
			if (r!=null) return unescape(r[2]);  
			return null;  
		} 	
		
		//js 处理字符串超长截断,默认设置10

		//js 处理字符串超长截断
		function wordlimit(cname, wordlength) {
			if(cname!=null&&cname.length>wordlength){
				return cname.substr(0, wordlength) + '...';
			}
			else 
				return cname;
		}
		
		function wordlimitDefault(cname){
			return wordlimit(cname,10);
		}
	  