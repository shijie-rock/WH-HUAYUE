var initInsTruckTable = function(tableId,aoColumns,reqData,appPath,colIdx,transeDataIndexStr) {
	$("#"+tableId).dataTable({
			"bFilter": false,//去掉搜索框
			"sPaginationType" : "full_numbers",
			"bSort": false, //去掉排序
			"bAutoWidth":false,
			"bJQueryUI":true,
			"bDestroy" : true,
			"bProcessing" : true,
			"sAjaxSource" : appPath+"/AjaxChannel", 
			"fnServerData": function(reqUrl,reqData1, fnCallback,oSettings){// 3个参数的名字可以随便命名,但必须是3个参数,少一个都不行
			 //reqData={action : 'editTab', iDisplayStart:oSettings._iDisplayStart, iDisplayLength:oSettings._iDisplayLength,sEcho:oSettings._sEcho|oSettings.iDraw};
			
				reqData.iDisplayStart=oSettings._iDisplayStart;
				reqData.iDisplayLength=oSettings._iDisplayLength;
				reqData.sEcho=(oSettings._sEcho|oSettings.iDraw);
				$('#currentiDisplayStart').val(oSettings._iDisplayStart);
				$('#currentiDisplayLength').val(oSettings._iDisplayLength);
				
		        $.ajax({
		            url : reqUrl,//这个就是请求地址对应sAjaxSource
		            data :reqData ,  //这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数 
		            type : 'post',
		            dataType : 'json',
		            async : false,
		            success : function(jsonResult) {
		         		fnCallback(jQuery.parseJSON(jsonResult.jsonResult));//把返回的数据传给这个方法就可以了,datatable会自动绑定数据的
		            },
		            error : function(msg) {
		         	   alert("msg:" + msg);
		            }
		        });
				
			}, // 获取数据的处理函数
			"bServerSide" : true,
			"aoColumns":jQuery.parseJSON(aoColumns),	
		    "aoColumnDefs": [ {
		        "aTargets": [colIdx],
		        "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
		        		var buttonHtml;
		        		if('1'==oData.FREEZE_TAG){ //stop
		        			buttonHtml="<button type='button' class='btn btn-success btn-xs' href='javascript:void(0);' onclick='optConfirm(\"start\",\""+ oData.TRUCK_LICENSE+"\",\"" + oData.TRUCK_ID+"\")'>启用</button>"
		        		}
		        		else{
		        			buttonHtml="<button type='button' class='btn btn-warning btn-xs' href='javascript:void(0);' onclick='optConfirm(\"stop\",\""+ oData.TRUCK_LICENSE+"\",\"" + oData.TRUCK_ID+"\")'>停用</button>"
		        		}	
		        		$(nTd).addClass("optd").css("text-align","center").html(
		        		"<button type='button' class='btn btn-primary btn-xs' href='javascript:void(0);' onclick='editTruck(\"" + oData.TRUCK_ID+"\")'>编辑</button>&nbsp;&nbsp;"+buttonHtml
		        		+"&nbsp;&nbsp;<button type='button' class='btn btn-danger btn-xs' href='javascript:void(0);' onclick='optConfirm(\"delete\",\""+ oData.TRUCK_LICENSE+"\",\"" + oData.TRUCK_ID+"\")'>删除</button>"
		        		+"<br/>"
		        		+"<button type='button' class='btn btn-default btn-xs btn-line2large' href='javascript:void(0);' onclick='truckMapSubObj(\""+ oData.TRUCK_LICENSE+"\",\"" + oData.TRUCK_ID+"\",\"" + oData.CHECK_ENT_NAME+"\")'>查看项目</button>"
		        		);
		        }
		      },
		      {
			        "aTargets": [0], //首列序号
			        "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
			        	return $(nTd).html(reqData.iDisplayStart+iRow+1); //dicTranse
			          }
			      },
			      {
			        "aTargets": [1], //
			        "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
			        	return $(nTd).html("<a class='data-detail' data-toggle='tooltip' data-placement='top' title='点击查看详情' href='javascript:void(0)' onclick='detail(\"" + oData.TRUCK_ID+"\")'>"+oData.TRUCK_LICENSE+"</a>"
			        		+"<br/>"+parseNull2Empty(oData.VIN)+""); //dicTranse
			          }
			      },
			      {
			    	  "aTargets": [2], //
			    	  "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
			    		  var dangerHtml=parseNull2Empty(dicTranse(oData.TRUCK_DANGER_LEVEL));
			    		  if("TDL_0020"==oData.TRUCK_DANGER_LEVEL){
			    			  dangerHtml="<font style='color:#c9302c'>"+dangerHtml+"</font>";
			    		  }
			    		  return $(nTd).html(parseNull2Empty(dicTranse(oData.TRUCK_TYPE))
			    				  +"/"+parseNull2Empty(dicTranse(oData.TRUCK_BELONG_TYPE))
			    				  +"<br/>"+dangerHtml); //
			    	  }
			      },
			      {
			    	  "aTargets": [3], //
			    	  "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
			    		  return $(nTd).html(parseNull2Empty(oData.BRAND_NAME)
			    				  +"<br/>"+parseNull2Empty(oData.MODEL_NAME)); //
			    	  }
			      },
			      {
			    	  "aTargets": [4], //
			    	  "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
			    		  return $(nTd).html(parseNull2Empty(oData.TRUCK_MAKE_DATE)
			    				  +"<br/>"+parseNull2Empty(oData.TRUCK_LICENSE_DATE)); //
			    	  }
			      },
//			      {
//			    	  "aTargets": [4], //
//			    	  "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
//			    		  return $(nTd).html(parseNull2Empty(moment(oData.MAKE_DATE).format("YYYY-MM-DD"))
//			    				  +"<br/>"+parseNull2Empty(moment(oData.LICENSE_DATE).format("YYYY-MM-DD"))); //
//			    	  }
//			      },
			      {
			    	  "aTargets": [5], //
			    	  "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
			    		  return $(nTd).html(parseNull2Empty(oData.TRUCK_CONFIRM_BEGIN_DATE)
			    				  +"<br/>"+parseNull2Empty(oData.TRUCK_CONFIRM_EXP_DATE)); //
			    	  }
			      },
			      {
			    	  "aTargets": [6], //
			    	  "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
			    		  return $(nTd).html(parseNull2Empty(oData.TRUCK_LENGTH)
			    				  +"*"+parseNull2Empty(oData.TRUCK_WIDTH)
			    				  +"*"+parseNull2Empty(oData.TRUCK_HEIGHT)+" m"
			    				  +"<br/>"+parseNull2Empty(oData.TRUCK_WEIGHT))+" t"; //
			    	  }
			      },
			      {
				        "aTargets": [8], //
				        "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
				        	if(oData.IMG_COUNT!=0){
				        		return $(nTd).html("<a class='data-detail' data-toggle='tooltip' data-placement='top' title='点击查看图片' href='javascript:void(0)' onclick='detailImg(\"" + oData.TRUCK_LICENSE+"\",\"" + oData.TRUCK_ID+"\")'>"+oData.IMG_COUNT+"</a>");
				        	}else{
				        		$(nTd).html("--");
				        	}
				       
				        }
				  },
			      {
			        "aTargets": [9], //
			        "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
			        	var statusHtml;
			        	if('1'==oData.FREEZE_TAG){
			        		statusHtml="<span class='label label-danger'>无效</span>"
//			        		statusHtml="<font style='color:#7F7F7F'></font>"
			        	}else{
			        		statusHtml="<span class='label label-success'>有效</span>"
			        	}
			        	return $(nTd).html(statusHtml); //dicTranse
			          }
			      },
		      ],
		      
			"oLanguage" : {
				"sProcessing" : "正在加载中......",
				"sLengthMenu" : "每页显示 _MENU_ 条记录",
				"sZeroRecords" : "没有数据！",
				"sEmptyTable" : "当前搜索条件无记录！",
				"sInfo" : "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
				"sInfoEmpty" : "显示 0 到 0 条记录",
				"sInfoFiltered" : "数据表中共为 _MAX_ 条记录",
				//"sSearch" : "搜索",
				"oPaginate" : {
					"sFirst" : "首页",
					"sPrevious" : "上一页",
					"sNext" : "下一页",
					"sLast" : "末页"
				}
			}
		});
	}
