var infowin = null, countcar, phcount = [], point, local, 
map, layer, vectorLayer, markerLayer, drawPoint, centersArray = [], weightsArray = [], n = 0,
//创建一个grid的数据源
gridArrayData = [],model,vm;
$(function() {
	getgrid();
	var style = {
		strokeColor : "#304DBE",
		strokeWidth : 1,
		pointerEvents : "visiblePainted",
		fillColor : "#304DBE",
		fillOpacity : 0.4
	}, url1 = '${map.url}';

	vectorLayer = new SuperMap.Layer.Vector("Vector Layer");
	drawPoint = new SuperMap.Control.DrawFeature(vectorLayer,
			SuperMap.Handler.Point);
	drawPoint.events.on({
		"featureadded" : drawCompleted
	});
	map = new SuperMap.Map("map", {
		controls : [ // new SuperMap.Control.LayerSwitcher(),
		// new SuperMap.Control.ScaleLine(),
		new SuperMap.Control.Zoom(), new SuperMap.Control.Navigation({
			dragPanOptions : {
				enableKinetic : true
			}
		}), drawPoint ],
		units : "m"
	});
	layer = new SuperMap.Layer.TiledDynamicRESTLayer("tongzhou", url1, {
		transparent : true,
		cacheEnabled : true
	}, {
		maxResolution : "auto"
	});
	layer.events.on({
		"layerInitialized" : addLayer
	});
	vectorLayer = new SuperMap.Layer.Vector("Vector Layer");
	markerLayer = new SuperMap.Layer.Markers("Markers");
	function addLayer() {
		map.addLayers([ layer, vectorLayer, markerLayer ]);
		map.setCenter(new SuperMap.LonLat(116.708, 39.840), 11, false, false);
	}
	function drawCompleted(drawGeometryArgs) {
		point = drawGeometryArgs.feature.geometry, size = new SuperMap.Size(44,
				33), offset = new SuperMap.Pixel(-(size.w / 2), -size.h),
				icon = new SuperMap.Icon("../theme/images/marker.png", size,
						offset);
		markerLayer.addMarker(new SuperMap.Marker(new SuperMap.LonLat(point.x,
				point.y), icon));
		console.log(point);
		drawPoint.deactivate();
		centersArray.push(point);
		n++;
		weightsArray.push(400 + n * 100);
        if(!$("#banjing").val() || $("#banjing").val()==""){
            alert("请输入查询范围！");
            return;
        }
		//调用画圆方法
		getCirle(point);
	}

	// 前后台数据交互
	vm = new Vue({
		el : '#toolbar',
		data : {
		},
		methods : {
			selectCenters : function() {
				clearElements();
				drawPoint.activate();
			},
			display : function() {
				var page = $("#jqGrid").jqGrid('getGridParam','page');
				var limit = $("#jqGrid").jqGrid('getGridParam','rowNum');
				if(!$("#banjing").val() || $("#banjing").val()==""){
					alert("请输入查询范围！");
					return;
				}
				if(!$("#cishu").val() || $("#cishu").val()==""){
					alert("请输入徘徊次数！");
					return;
				}
				if(!point || point == ""){
					alert("请选择地点！");
					return;
				}
				if(!$("#qssj").val() || $("#qssj").val()==""){
					alert("请输入起始时间！");
					return;
				}
				if(!$("#jssj").val() || $("#jssj").val()==""){
					alert("请输入结束时间！");
					return;
				}
				var time1 = Date.parse($("#qssj").val());
				var time2 = Date.parse($("#jssj").val());
				if(time1>time2){
					alert("起始时间不能大于结束时间");
					return;
				}
				var banjing = trim($("#banjing").val());
				var paihuai = $("#cishu").val().trim().replace(/\s/g,"");
				$.ajax({
					type : "POST",
					url : baseURL + "importplace/list",
					contentType : "application/json",
					data : JSON.stringify({
						'jingd' : point.x,
						'weid' : point.y,
						'count' :banjing,
						'page' : page,
						'limit' : limit,
						'paihuai' : paihuai,
						'qssj' : $("#qssj").val(),
						'jssj' : $("#jssj").val()
					}),
					success : function(r) {
						
						if(!r.page || r.page.length<=0){
							alert("没有符合查询条件的记录！");
							return;
						}
						var list = r.page;
						countcar = list.length;
						for (var i = 0; i < list.length; i++) {
						
						gridArrayData.push({  
							cph: list[i].cph,  
							num: list[i].num  
                        });   
						}
						openInfoWin();
						$("#jqGrid").jqGrid('setGridParam', { data: gridArrayData}).trigger('reloadGrid');
						point = "";
					}
				});
			},
			tuisong : function(plate) {
				var date = new Date();
		        var year = date.getFullYear();
		        var month = date.getMonth()+1;
		        var day = date.getDate();
		        var hour = date.getHours();
		        var minute = date.getMinutes();
		        var second = date.getSeconds();
		        var time = year+"-"+month+"-"+day;
				$.ajax({
					type : "POST",
					url : baseURL + "importplace/tuisong",
					contentType : "application/json",
					data : JSON.stringify({
						'carNum' : plate,
						'createUser' : "admin1",
						'createTime' : time,
						'createReason' : "重点区域徘徊车辆"
					}),
					success : function(r) {
						if(r.code == 0){
							alert('操作成功');
						}else{
							alert('操作失败');
						}
						//设置jqGrid的数据集
						//$("#jqGrid").jqGrid('setGridParam', { data: gridArrayData}).trigger('reloadGrid');						
					}
				});
			},
			clear : function() {
				closeInfoWin();
				clearElements();
			}
		}
	});
	laydate.render({
        elem : '#qssj',
        type : 'datetime'
    });
    //时间选择器
    laydate.render({
        elem : '#jssj',
        type : 'datetime'
    });
});

function getCirle(point) {
	var sides = 20;
	if(!point || point == ""){
		alert("请选择地点！");
		return;
	}
	var origin = point;
	var polygon = SuperMap.Geometry.Polygon.createRegularPolygon(origin, trim($("#banjing").val())*0.01,
			sides);
	var lineVector = new SuperMap.Feature.Vector(polygon);
	vectorLayer.addFeatures(lineVector);
}
function clearElements() {
	n = 0;
	centersArray = [];
	weightsArray = [];
	markerLayer.clearMarkers();
	vectorLayer.removeAllFeatures();
	closeInfoWin();
	gridArrayData=[];
	$("#jqGrid").jqGrid('clearGridData');
}
function openInfoWin() {
	closeInfoWin();
	// marker[0].setLabel("起点");
	var contentHTML = "<div style=\'font-size:.8em; opacity: 0.8; overflow-y:hidden;\'>";
	contentHTML += "<div>在此重点区域半径"+trim($("#banjing").val())+"公里范围内共有徘徊车" + countcar + "辆</div></div>";
	var popup = new SuperMap.Popup.FramedCloud("popwin", new SuperMap.LonLat(
			point.x, point.y), null, contentHTML, null, true);
	infowin = popup;
	map.addPopup(popup);
}
function closeInfoWin() {// 关闭窗口
	if (infowin) {
		try {
			infowin.hide();// 隐藏窗体对象
			infowin.destroy();// 消毁窗体对象
		} catch (e) {
		}
	}
}
function getgrid() {
	$("#jqGrid").jqGrid({
		url: '',
		//data: gridArrayData,
		datatype : "local",
		colModel : [ {
			label : '车牌号',
			name : 'cph',
			width : 75
		}, {
			label : '徘徊次数',
			name : 'num',
			width : 75
		} ,{
            label: '操作', name: 'tuisong', index: 'tuisong', width: 60, align: 'center',
            formatter: function (cellvalue, options, rowObject) {
                return '<button class="btn btn-default"  style="background: #337ab7;color:#fff;height:25px;line-height:12px;" onclick= "get(\''+rowObject.cph+'\')">'
				+ '推送' + '</button>';
            }
        }],
		viewrecords : true,
		height : 420,
		rowNum : 10,
		rowList : [ 10, 30, 50 ],
		rownumbers : true,
		rownumWidth : 40,
		autowidth : true,
		multiselect : false,
		pager : "#jqGridPager",
		jsonReader : {
			root : "page.list",
			page : "page.currPage",
			total : "page.totalPage",
			records : "page.totalCount"
		},
		prmNames : {
			page : "page",
			rows : "limit",
			order : "order"
		},
		gridComplete : function() {
			// 隐藏grid底部滚动条
			$("#input_jqGridPager").css("display", "none"); 
			$("#jqGrid").closest(".ui-jqgrid-bdiv").css({
				"overflow-x" : "hidden"
			});
		}
	});
}
function get(plate) {
	var grid_selector = "#jqGrid";  
	model = $(grid_selector).jqGrid('getRowData', plate); 
	console.log("model="+model);
	vm.tuisong(plate);
}


function trim(str){
	return str.replace(/\s|\xA0/g,"");   
}

