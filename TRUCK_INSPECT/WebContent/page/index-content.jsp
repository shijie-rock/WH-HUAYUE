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
  
  <!-- HTML5 Support for IE -->
  <!--[if lt IE 9]>
  <script src="js/html5shim.js"></script>
  <![endif]-->

  <!-- Favicon -->
  <link rel="shortcut icon" href="img/favicon/favicon.png">
</head>

<body class="content-body">
<!-- Header starts -->
<!-- Header ends -->

<!-- Main content starts -->

<div class="content">

  	<!-- Sidebar -->
    <!-- Sidebar ends -->

  	  	<!-- Main bar -->
  	<div class="mainbar content-mainbar">
      
	    <!-- Page heading -->
	    <div class="page-head">
	      <h2 class="pull-left"><i class="icon-home"></i> 首页-示例</h2>

        <!-- Breadcrumb -->
        <div class="bread-crumb pull-right">
          <a href="index-content.jsp"><i class="icon-home"></i> 首页</a> 
          <!-- Divider -->
          <span class="divider">/</span> 
          <a href="#" class="bread-current">控制台</a>
        </div>

        <div class="clearfix"></div>

	    </div>
	    <!-- Page heading ends -->



	    <!-- Matter -->

	    <div class="matter">
        <div class="container">

          <!-- Today status. jQuery Sparkline plugin used. -->

          <div class="row">
            <div class="col-md-12"> 
              <!-- List starts -->
              <ul class="today-datas">
                <!-- List #1 -->
                <li>
                  <!-- Graph -->
                  <div><span id="todayspark1" class="spark"></span></div>
                  <!-- Text -->
                  <div class="datas-text">12,000 visitors/day</div>
                </li>
                <li>
                  <div><span id="todayspark2" class="spark"></span></div>
                  <div class="datas-text">30,000 Pageviews</div>
                </li>
                <li>
                  <div><span id="todayspark3" class="spark"></span></div>
                  <div class="datas-text">15.66% Bounce Rate</div>
                </li>
                <li>
                  <div><span id="todayspark4" class="spark"></span></div>
                  <div class="datas-text">$12,000 Revenue/Day</div>
                </li> 
                <li>
                  <div><span id="todayspark5" class="spark"></span></div>
                  <div class="datas-text">15,000000 visitors till date</div>
                </li>                                                                                                              
              </ul> 
            </div>
          </div>

          <!-- Today status ends -->
          
       <div class="row">
            <!-- Task widget -->
            <div class="col-md-6">
              <div class="widget">
                <!-- Widget title -->
                <div class="widget-head">
                  <div class="pull-left">待办事项-示例</div>
                  <div class="widget-icons pull-right">
                    <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a> 
                    <a href="#" class="wclose"><i class="icon-remove"></i></a>
                  </div>  
                  <div class="clearfix"></div>
                </div>
                <div class="widget-content">
                  <!-- Widget content -->
                  <!-- Task list starts -->
                  <ul class="task">

                    <li>
                      <!-- Checkbox -->
                      <span class="uni"><input value="check1" type="checkbox"></span> 
                      <!-- Task -->
                      	有10张检车单待复审...&nbsp;&nbsp;<span class="label label-danger">Important</span>
                      <!-- Delete button -->
                      <a href="#" class="pull-right" onclick="hideLi(this);"><i class="icon-remove"></i></a>
                    </li>

                    <li>
                      <!-- Checkbox -->
                      <span class="uni"><input value="check1" type="checkbox"></span> 
                      <!-- Task -->
                       	有10辆车任务结束待入库...
                      <!-- Delete button -->
                      <a href="#" class="pull-right"  onclick="hideLi(this);"><i class="icon-remove"></i></a>
                    </li>

                    <li>
                      <!-- Checkbox -->
                      <span class="uni"><input value="check1" type="checkbox"></span> 
                      <!-- Task -->
                       	复审单待审核、确认...&nbsp;&nbsp;<span class="label label-warning">Warn</span>
                      <!-- Delete button -->
                      <a href="#" class="pull-right"  onclick="hideLi(this);"><i class="icon-remove"></i></a>
                    </li>

                    <li>
                      <!-- Checkbox -->
                      <span class="uni"><input value="check1" type="checkbox"></span> 
                      <!-- Task -->
                       	张三账户信息需更新...
                      <!-- Delete button -->
                      <a href="#" class="pull-right" onclick="hideLi(this);"><i class="icon-remove"></i></a>
                    </li>

                    <li>
                      <!-- Checkbox -->
                      <span class="uni"><input value="check1" type="checkbox"></span> 
                      <!-- Task -->
                       	有10张质检报表待导出...
                      <!-- Delete button -->
                      <a href="#" class="pull-right"  onclick="hideLi(this);"><i class="icon-remove"></i></a>
                    </li>                                                                                                             
                  </ul>
                  <div class="clearfix"></div>  

                  <div class="widget-foot">
                  </div>

                </div>
              </div>
            </div><!-- Task widget end-->
            <!-- Recent News begin -->
            <div class="col-md-6" style="max-height:314px">
              <div class="widget">
                <!-- Widget title -->
                <div class="widget-head">
                  <div class="pull-left">我的消息-示例</div>
                  <div class="widget-icons pull-right">
                    <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a> 
                    <a href="#" class="wclose"><i class="icon-remove"></i></a>
                  </div>  
                  <div class="clearfix"></div>
                </div>
                <div class="widget-content referrer">
                  <!-- Widget content -->
                  <div class="padd" id="index_content_comm_msg_list_div" style="height:14%">
                  	<div id="index_content_comm_msg_load" style="background-image: url(../img/loading.gif);width: 20px;height: 20px;margin-left: 50%;margin-top: 17%;"></div>
                    <ul class="latest-news" id="index_content_comm_msg_list" style="display:none;">
<!--                       <li> -->
<!--                         Title and date -->
<!--                         <h6><a href="#">紧急通知 </a> - <span>2017-12-1</span>&nbsp;&nbsp;<span class="label label-danger" style="color:#fff;font-weight: bold;">Important</span></h6> -->
<!--                         <p>安全生产工作紧急通知... ...</p> -->
<!--                       </li> -->

<!--                       <li> -->
<!--                         Title and date -->
<!--                         <h6><a href="#">人事变动通知</a> - <span>2017-12-1</span></h6> -->
<!--                         <p>关于相关人员岗位调动的通知 为了更好的协调工作,提高工作效率。经公司决定现安排以下 人员工作岗... </p> -->
<!--                       </li> -->

<!--                       <li> -->
<!--                         Title and date -->
<!--                         <h6><a href="#">开展安全生产培训的通知</a> - <span>2017-12-1</span></h6> -->
<!--                         <p>2017年1月20日 - 为进一步贯彻落实国务院安委办、省安委办、省国资委、集团公司《关于切实做好春节期间安全生产工作的通知》... </p> -->
<!--                       </li>                                         -->
                    </ul> 
                  </div>
                  <div class="widget-foot">
                  </div>
                </div>
              </div>
            </div><!-- Recent News end-->
          </div>

          <!-- Dashboard Graph starts -->

          <div class="row">
            <div class="col-md-8">

              <!-- Widget -->
              <div class="widget">
                <!-- Widget head -->
                <div class="widget-head">
                  <div class="pull-left">图表-示例</div>
                  <div class="widget-icons pull-right">
                    <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a> 
                    <a href="#" class="wclose"><i class="icon-remove"></i></a>
                  </div>  
                  <div class="clearfix"></div>
                </div>              

                <!-- Widget content -->
                <div class="widget-content">
                  <div class="padd">

                    <!-- Curve chart (Blue color). jQuery Flot plugin used. -->
                    <div id="curve-chart"></div>

                    <hr />
                    <!-- Hover location -->
                    <div id="hoverdata">Mouse hovers at
                    (<span id="x">0</span>, <span id="y">0</span>). <span id="clickdata"></span></div>          

                    <!-- Skil this line. <div class="uni"><input id="enableTooltip" type="checkbox">Enable tooltip</div> -->

                  </div>
                </div>
                <!-- Widget ends -->

              </div>
            </div>

            <div class="col-md-4">

              <div class="widget">

                <div class="widget-head">
                  <div class="pull-left">今天统计-示例</div>
                  <div class="widget-icons pull-right">
                    <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a> 
                    <a href="#" class="wclose"><i class="icon-remove"></i></a>
                  </div>  
                  <div class="clearfix"></div>
                </div>             

                <div class="widget-content">
                  <div class="padd">

                    <!-- Visitors, pageview, bounce rate, etc., Sparklines plugin used -->
                    <ul class="current-status">
                      <li>
                        <span id="status1"></span> <span class="bold">Visits : 2000</span>
                      </li>
                      <li>
                        <span id="status2"></span> <span class="bold">Unique Visitors : 1,345</span>
                      </li>
                      <li>
                        <span id="status3"></span> <span class="bold">Pageviews : 2000</span>
                      </li>
                      <li>
                        <span id="status4"></span> <span class="bold">Pages / Visit : 2000</span>
                      </li>
                      <li>
                        <span id="status5"></span> <span class="bold">Avg. Visit Duration : 2000</span>
                      </li>
                      <li>
                        <span id="status6"></span> <span class="bold">Bounce Rate : 2000</span>
                      </li>   
                      <li>
                        <span id="status7"></span> <span class="bold">% New Visits : 2000</span>
                      </li>                                                                                                            
                    </ul>

                  </div>
                </div>

              </div>

            </div>
          </div>
          <!-- Dashboard graph ends -->

          <!-- Chats, File upload and Recent Comments -->
          <div class="row">
           <!-- Realtime chart starts -->
				<div class="col-md-12">
                <div class="widget">

                <div class="widget-head">
                  <div class="pull-left">在线人数-示例</div>
                  <div class="widget-icons pull-right">
                    <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a> 
                    <a href="#" class="wclose"><i class="icon-remove"></i></a>
                  </div>  
                  <div class="clearfix"></div>
                </div>             

                  <div class="widget-content">
                    <div class="padd">

                      <div id="live-chart"></div>
                      <hr />
                      	采样时间间隔: <input id="updateInterval" type="text" class="span3" value="">

                    </div>
                  </div>
                </div>
			</div>
           <!-- Realtime chart ends -->

          </div>  
          
          <div class="row">
			<div class="col-md-12">    
              <!-- Pie chart starts -->
                <div class="widget">

                <div class="widget-head">
                  <div class="pull-left">饼图-示例</div>
                  <div class="widget-icons pull-right">
                    <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a> 
                    <a href="#" class="wclose"><i class="icon-remove"></i></a>
                  </div>  
                  <div class="clearfix"></div>
                </div>

                  <div class="widget-content">
                    <div class="padd">

                      <div class="row">
                        <div class="col-md-4">
                          <div id="pie-chart"></div>
                        </div>
                        <div class="col-md-4">
                          <div id="pie-chart2"></div>
                        </div>
                        <div class="col-md-4">
                          <div id="pie-chart3"></div>
                        </div>
                      </div>

                    </div>
                  </div>
                </div>
               <!-- Pie chart ends -->
			</div>
          </div>  

        </div>
		  </div>

		<!-- Matter ends -->

    </div>

   <!-- Mainbar ends -->
   <div class="clearfix"></div>

</div>
<!-- Content ends -->

<!-- Footer starts -->
<!-- Footer ends -->

<!-- Scroll to top -->
<!-- <span class="totop"><a href="#"><i class="icon-chevron-up"></i></a></span>  -->

<!-- JS -->
<script src="<%=path%>/js/jquery.js?v=<%=staticVersion%>"></script> <!-- jQuery -->
<script src="<%=path%>/js/bootstrap.js?v=<%=staticVersion%>"></script> <!-- Bootstrap -->

<script src="<%=path%>/js/truck-inspect-common.js?v=<%=staticVersion%>"></script> <!-- Custom codes -->

<script src="<%=path%>/js/jquery-ui-1.9.2.custom.min.js?v=<%=staticVersion%>"></script> <!-- jQuery UI -->
<script src="<%=path%>/js/fullcalendar.min.js?v=<%=staticVersion%>"></script> <!-- Full Google Calendar - Calendar -->
<script src="<%=path%>/js/jquery.rateit.min.js?v=<%=staticVersion%>"></script> <!-- RateIt - Star rating -->
<script src="<%=path%>/js/jquery.prettyPhoto.js?v=<%=staticVersion%>"></script> <!-- prettyPhoto -->

<!-- jQuery Flot -->
<script src="<%=path%>/js/excanvas.min.js?v=<%=staticVersion%>"></script>
<script src="<%=path%>/js/jquery.flot.js?v=<%=staticVersion%>"></script>
<script src="<%=path%>/js/jquery.flot.resize.js?v=<%=staticVersion%>"></script>
<script src="<%=path%>/js/jquery.flot.pie.js?v=<%=staticVersion%>"></script>
<script src="<%=path%>/js/jquery.flot.stack.js?v=<%=staticVersion%>"></script>

<!-- jQuery Notification - Noty -->
<script src="<%=path%>/js/jquery.noty.js?v=<%=staticVersion%>"></script> <!-- jQuery Notify -->
<script src="<%=path%>/js/themes/default.js?v=<%=staticVersion%>"></script> <!-- jQuery Notify -->
<script src="<%=path%>/js/layouts/bottom.js?v=<%=staticVersion%>"></script> <!-- jQuery Notify -->
<script src="<%=path%>/js/layouts/topRight.js?v=<%=staticVersion%>"></script> <!-- jQuery Notify -->
<script src="<%=path%>/js/layouts/top.js?v=<%=staticVersion%>"></script> <!-- jQuery Notify -->
<!-- jQuery Notification ends -->

<script src="<%=path%>/js/sparklines.js?v=<%=staticVersion%>"></script> <!-- Sparklines -->
<script src="<%=path%>/js/jquery.cleditor.min.js?v=<%=staticVersion%>"></script> <!-- CLEditor -->
<script src="<%=path%>/js/bootstrap-datetimepicker.min.js?v=<%=staticVersion%>"></script> <!-- Date picker -->
<%-- <script src="<%=path%>/js/jquery.uniform.min.js?v=<%=staticVersion%>"></script> <!-- jQuery Uniform --> --%>
<script src="<%=path%>/js/bootstrap-switch.min.js?v=<%=staticVersion%>"></script> <!-- Bootstrap Toggle -->
<script src="<%=path%>/js/filter.js?v=<%=staticVersion%>"></script> <!-- Filter for support page -->
<script src="<%=path%>/js/custom.js?v=<%=staticVersion%>"></script> <!-- Custom codes -->
<script src="<%=path%>/js/charts.js?v=<%=staticVersion%>"></script> <!-- Charts & Graphs -->

<!-- Script for this page -->
<script type="text/javascript">

//data dic
var map=parseData2Map('<yb:dataDic dataDicType="COM_MSG_LEVEL"/>');
function dicTranse(value){
	return map[value]==null?value:map[value];
}


$(function () {
	
	queryPubCommMsg(3);//查询公告

    /* Bar Chart starts */

    var d1 = [];
    for (var i = 0; i <= 20; i += 1)
        d1.push([i, parseInt(Math.random() * 30)]);

    var d2 = [];
    for (var i = 0; i <= 20; i += 1)
        d2.push([i, parseInt(Math.random() * 30)]);


    var stack = 0, bars = true, lines = false, steps = false;
    
    function plotWithOptions() {
//         $.plot($("#bar-chart"), [ d1, d2 ], {
//             series: {
//                 stack: stack,
//                 lines: { show: lines, fill: true, steps: steps },
//                 bars: { show: bars, barWidth: 0.8 }
//             },
//             grid: {
//                 borderWidth: 0, hoverable: true, color: "#777"
//             },
//             colors: ["#ff6c24", "#ff2424"],
//             bars: {
//                   show: true,
//                   lineWidth: 0,
//                   fill: true,
//                   fillColor: { colors: [ { opacity: 0.9 }, { opacity: 0.8 } ] }
//             }
//         });
    }

    plotWithOptions();
    
    $(".stackControls input").click(function (e) {
        e.preventDefault();
        stack = $(this).val() == "With stacking" ? true : null;
        plotWithOptions();
    });
    $(".graphControls input").click(function (e) {
        e.preventDefault();
        bars = $(this).val().indexOf("Bars") != -1;
        lines = $(this).val().indexOf("Lines") != -1;
        steps = $(this).val().indexOf("steps") != -1;
        plotWithOptions();
    });

    /* Bar chart ends */

});

/**
 * 点击X，隐藏待办Li
 */
function hideLi(obj){
	$(obj).parent('li').hide(500);
}
/* Curve chart starts */

$(function () {
    var sin = [], cos = [];
    for (var i = 0; i < 14; i += 0.5) {
        sin.push([i, Math.sin(i)]);
        cos.push([i, Math.cos(i)]);
    }

    var plot = $.plot($("#curve-chart"),
           [ { data: sin, label: "检车通过率"}, { data: cos, label: "复检通过率" } ], {
               series: {
                   lines: { show: true, fill: true},
                   points: { show: true }
               },
               grid: { hoverable: true, clickable: true, borderWidth:0 },
               yaxis: { min: -1.2, max: 1.2 },
               colors: ["#1eafed", "#1eafed"]
             });

    function showTooltip(x, y, contents) {
        $('<div id="tooltip">' + contents + '</div>').css( {
            position: 'absolute',
            display: 'none',
            top: y + 5,
            left: x + 5,
            border: '1px solid #000',
            padding: '2px 8px',
            color: '#ccc',
            'background-color': '#000',
            opacity: 0.9
        }).appendTo("body").fadeIn(200);
    }

    var previousPoint = null;
    $("#curve-chart").bind("plothover", function (event, pos, item) {
        $("#x").text(pos.x.toFixed(2));
        $("#y").text(pos.y.toFixed(2));

        if ($("#enableTooltip:checked").length > 0) {
            if (item) {
                if (previousPoint != item.dataIndex) {
                    previousPoint = item.dataIndex;
                    
                    $("#tooltip").remove();
                    var x = item.datapoint[0].toFixed(2),
                        y = item.datapoint[1].toFixed(2);
                    
                    showTooltip(item.pageX, item.pageY, 
                                item.series.label + " of " + x + " = " + y);
                }
            }
            else {
                $("#tooltip").remove();
                previousPoint = null;            
            }
        }
    }); 

    $("#curve-chart").bind("plotclick", function (event, pos, item) {
        if (item) {
            $("#clickdata").text("You clicked point " + item.dataIndex + " in " + item.series.label + ".");
            plot.highlight(item.series, item.datapoint);
        }
    });

});

/* Curve chart end/
/* Realtime chart starts */
$(function () {
    // we use an inline data source in the example, usually data would
    // be fetched from a server
    var data = [], totalPoints = 300;
    function getRandomData() {
        if (data.length > 0)
            data = data.slice(1);

        // do a random walk
        while (data.length < totalPoints) {
            var prev = data.length > 0 ? data[data.length - 1] : 50;
            var y = prev + Math.random() * 10 - 5;
            if (y < 10)
                y = 10;
            if (y > 70)
                y = 70;
            data.push(y);
        }

        // zip the generated y values with the x values
        var res = [];
        for (var i = 0; i < data.length; ++i)
            res.push([i, data[i]])
        return res;
    }

    // setup control widget
    var updateInterval = 300;
    $("#updateInterval").val(updateInterval).change(function () {
        var v = $(this).val();
        if (v && !isNaN(+v)) {
            updateInterval = +v;
            if (updateInterval < 1)
                updateInterval = 1;
            if (updateInterval > 2000)
                updateInterval = 2000;
            $(this).val("" + updateInterval);
        }
    });

    // setup plot
    var options = {
        series: { shadowSize: 0 }, // drawing is faster without shadows
        lines: {fill: true},
        grid: {borderWidth:0 },
        yaxis: { min: 0, max: 100 },
        colors: ["#ff2424"]
    };
    var plot = $.plot($("#live-chart"), [ getRandomData() ], options);

    function update() {
        plot.setData([ getRandomData() ]);
        // since the axes don't change, we don't need to call plot.setupGrid()
        plot.draw();
        
        setTimeout(update, updateInterval);
    }

    update();
});

/* Realtime charts ends */

/* Pie chart starts */

$(function () {

    var data = [];
    var series = Math.floor(Math.random()*10)+1;
    for( var i = 0; i<series; i++)
    {
        data[i] = { label: "指标:"+(i+1), data: Math.floor(Math.random()*100)+1 }
    }

    $.plot($("#pie-chart"), data,
    {
        series: {
            pie: {
                show: true,
                radius: 1,
                label: {
                    show: true,
                    radius: 3/4,
                    formatter: function(label, series){
                        return '<div style="font-size:8pt;text-align:center;padding:2px;color:white;">'+label+'<br/>'+Math.round(series.percent)+'%</div>';
                    },
                    background: { opacity: 0 }
                }
            }
        },
        grid: {hoverable: true},
        legend: {
            show: false
        }
    }); 

    $.plot($("#pie-chart2"), data,
    {
        series: {
            pie: {
                show: true
            }
        },
        grid: {hoverable: true}
    });


    $.plot($("#pie-chart3"), data,
    {
        series: {
            pie: {
                show: true
            }
        },
        grid: {hoverable: true},
        legend: {
            show: false
        }
    });   

/* Pie chart ends */

});

function queryPubCommMsg(dataCount){
	var reqUrl='<%=path%>/AjaxChannel?action=MESSAGE_COMM_MSG_QUERY_MAIN_PAGE_ACTION';
	//ajax begin
	$.ajax({
			type : 'POST',
			url:reqUrl,
			data: {DATA_COUNT:dataCount},
			dataType : 'json',
			success : function(json) {
				if(json.SUCCESS=='1'){
					$("#index_content_comm_msg_load").hide();
					$("#index_content_comm_msg_list").show();
						if(json.COMM_MSG_LIST!=null){
							var liHtml="";
							
							$.each(json.COMM_MSG_LIST,function(index,data){
								
								console.log(data);
								//一般
								var commLevelHtml='<span class="label label-default" style="color:#fff;font-weight: bold;">'+dicTranse("CML_0030")+'</span>';
								if(data.COM_MSG_LEVEL=='CML_0010'){//紧急
									commLevelHtml='<span class="label label-danger" style="color:#fff;font-weight: bold;">'+dicTranse(data.COM_MSG_LEVEL)+'</span>';
								}else if(data.COM_MSG_LEVEL=='CML_0020'){//严重
									 commLevelHtml='<span class="label label-warn" style="color:#fff;font-weight: bold;">'+dicTranse(data.COM_MSG_LEVEL)+'</span>';
								}
								
								liHtml+='<li>';
								liHtml+='<h6><a href="#">'+data.COMM_MSG_TITLE+' </a> - <span>'+data.PUB_TIME_STR+'</span>&nbsp;&nbsp;'
								+commLevelHtml+
								'</h6>';
								liHtml+='<p>'+data.COMM_MSG_SUB_TITLE+'</p>';
								liHtml+='</li>';
							});
							console.log(liHtml);
							$("#index_content_comm_msg_list").html(liHtml);
						}
					
//                     <li>
//                     <!-- Title and date -->
//                     <h6><a href="#">紧急通知 </a> - <span>2017-12-1</span>&nbsp;&nbsp;<span class="label label-danger" style="color:#fff;font-weight: bold;">Important</span></h6>
//                     <p>安全生产工作紧急通知... ...</p>
//                   </li>
					
// 						returnSuccessMsgShow('optModal',json.MSG);//alert msg
// 						var fvTable=$("#CommMsgList").dataTable(); //datatable init current
// 						fvTable.fnDraw(false);
// 						$(obj).button('reset');
					}else{
						console.log('无数据');
// 						returnErrorMsgShow('optModal',json.MSG);
// 						$(obj).button('reset'); 
					}
				},
			error : function(e) {
				console.log(e);
				}
			});
		//ajax end
	//
}

</script>

</body>
</html>