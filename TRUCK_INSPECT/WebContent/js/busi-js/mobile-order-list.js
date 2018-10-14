/**
 * for mobile /order-list.jsp
 */



	//根据查询类型，检车员 -获取订单列表数据
	function queryCheckOrderList(memberId,page,queryType){
		var uncheck_get_new_span = $("#uncheck-list").children("span.get-new-span:first");
		var checking_get_new_span = $("#checking-list").children("span.get-new-span:first");
		var checked_get_new_span = $("#checked-list").children("span.get-new-span:first");
		
		var uncheck_get_new_page_span = $("#uncheck-list").children("span.get-new-page-span:first");
		var checking_get_new_page_span = $("#checking-list").children("span.get-new-page-span:first");
		var checked_get_new_page_span = $("#checked-list").children("span.get-new-page-span:first");
		
		var uncheck_no_data_span = $("#uncheck-list").children("span.no-data-span:first");
		var checking_no_data_span = $("#checking-list").children("span.no-data-span:first");
		var checked_no_data_span = $("#checked-list").children("span.no-data-span:first");
		
		// 		mobile?
		var reqUrl='<%=path%>/AjaxChannel?action=MOBILE_QUERY_CHECK_ORDER_LIST_ACTION&remote=1';
		//ajax begin
		$.ajax({
				type : 'POST',
				url:reqUrl,
				data: {CHECK_MEMBER_ID:memberId,PAGE:page,QUERY_TYPE:queryType},//1:未检,2：检中,3：已检
				dataType : 'json',
				success : function(json) {
					if(json.SUCCESS=='1'){
						
						uncheck_get_new_span.hide();
						checking_get_new_span.hide();
						checked_get_new_span.hide();
						
						uncheck_get_new_page_span.hide();
						checking_get_new_page_span.hide();
						checked_get_new_page_span.hide();
						
						uncheck_no_data_span.hide();
						checking_no_data_span.hide();
						checked_no_data_span.hide();
						
							if(json.COMM_MSG_LIST!=null){
								var liHtml="";
								
								if('1'==queryType){//1:未检
									
									$.each(json.CHECK_ORDER_LIST,function(index,data){
										console.log(data);
										liHtml+='<li data-role="list-divider">'+json.CHECK_ORDER_LIST.CHECK_TARGET_NAME+'<span class="ui-li-count">'+json.CHECK_ORDER_LIST.CHECK_ITEM_COUNT+'</span></li>'; 
										liHtml+='<li>'; 
										liHtml+='<a href="#pageOrderDetail" data-transition="slide" onclick="openDetail('+json.CHECK_ORDER_LIST.ID+');">';  
										liHtml+='<h2>'+dicTranse(json.CHECK_ORDER_LIST.TRUCK_TYPE)+'&nbsp;'+json.CHECK_ORDER_LIST.POSITION_ADDRESS+'</h2>'; 
										liHtml+='<p><b>检查人：'+json.CHECK_ORDER_LIST.PL_CHECKER_NAME+'&nbsp;'+json.CHECK_ORDER_LIST.P_BEGIN_TIME+'</b></p>'; 
										liHtml+='<p>'+json.CHECK_ORDER_LIST.CHECK_ITEM_SUMMARY+'</p>'; 
										liHtml+='<p class="ui-li-aside order-check-waiting">'+dicTranse(json.CHECK_ORDER_LIST.CHECK_ORDER_STATUS); 
										liHtml+='</p></a>'; 
										liHtml+='</li>'; 
										
									});
									uncheck_list_page_no++;//增加一页
									if(uncheck_list_page_no==1){//拉取全新数据
										$("#uncheck-list ul").html(liHtml);
									}else{
										$("#uncheck-list ul:first-child").append(liHtml);
									}
									
								}else if('2'==queryType){//2：检中
									$.each(json.CHECK_ORDER_LIST,function(index,data){
										console.log(data);
										liHtml+='<li data-role="list-divider">'+json.CHECK_ORDER_LIST.CHECK_TARGET_NAME+'<span class="ui-li-count">'+json.CHECK_ORDER_LIST.CHECK_ITEM_COUNT+'</span></li>'; 
										liHtml+='<li>'; 
										liHtml+='<a href="#pageOrderDetail" data-transition="slide" onclick="openDetail('+json.CHECK_ORDER_LIST.ID+');">';  
										liHtml+='<h2>'+dicTranse(json.CHECK_ORDER_LIST.TRUCK_TYPE)+'&nbsp;'+json.CHECK_ORDER_LIST.POSITION_ADDRESS+'</h2>'; 
										liHtml+='<p><b>检查人：'+json.CHECK_ORDER_LIST.R_CHECKER_NAME+'&nbsp;'+json.CHECK_ORDER_LIST.R_BEGIN_TIME+'</b></p>'; 
										liHtml+='<p>'+json.CHECK_ORDER_LIST.CHECK_ITEM_SUMMARY+'</p>'; 
										liHtml+='<p class="ui-li-aside order-check-waiting">'+dicTranse(json.CHECK_ORDER_LIST.CHECK_ORDER_STATUS); 
										liHtml+='</p></a>'; 
										liHtml+='</li>'; 
										
									});
									checking_list_page_no++;//增加一页
									if(checking_list_page_no==1){//拉取全新数据
										$("#checking-list ul").html(liHtml);
									}else{
										$("#checking-list ul:first-child").append(liHtml);
									}
									
								}else('3'==queryType){//3：已检
									
									$.each(json.CHECK_ORDER_LIST,function(index,data){
										console.log(data);
										liHtml+='<li data-role="list-divider">'+json.CHECK_ORDER_LIST.CHECK_TARGET_NAME+'<span class="ui-li-count">'+json.CHECK_ORDER_LIST.CHECK_ITEM_COUNT+'</span></li>'; 
										liHtml+='<li>'; 
										liHtml+='<a href="#pageOrderDetail" data-transition="slide" onclick="openDetail('+json.CHECK_ORDER_LIST.ID+');">';  
										liHtml+='<h2>'+dicTranse(json.CHECK_ORDER_LIST.TRUCK_TYPE)+'&nbsp;'+json.CHECK_ORDER_LIST.POSITION_ADDRESS+'</h2>'; 
										liHtml+='<p><b>检查人：'+json.CHECK_ORDER_LIST.R_CHECKER_NAME+'&nbsp;'+json.CHECK_ORDER_LIST.R_BEGIN_TIME+'</b></p>'; 
										liHtml+='<p>'+json.CHECK_ORDER_LIST.CHECK_ITEM_SUMMARY+'</p>'; 
										liHtml+='<p class="ui-li-aside order-check-waiting">'+dicTranse(json.CHECK_ORDER_LIST.CHECK_ORDER_RESULT); 
										liHtml+='</p></a>'; 
										liHtml+='</li>'; 
										
									});
									checked_list_page_no++;//增加一页
									if(checked_list_page_no==1){//拉取全新数据
										$("#checked-list ul").html(liHtml);
									}else{
										$("#checked-list ul:first-child").append(liHtml);
									}
									
								}
								
								

								console.log(liHtml);
								$("#index_content_comm_msg_list").html(liHtml);
							}
						}else{
							console.log('无数据');
							if(currentListType=="uncheck"){
								uncheck_no_data_span.hide();
							}else if(currentListType=="checking"){
								checking_no_data_span.hide();
							}else if(currentListType=="checked"){
								checked_no_data_span.hide();
							}
							
						}
					},
				error : function(e) {
					console.log(e);
					}
				});
			//ajax end
	}