var infowin = null, countcar, phcount = [], point, local, 
map, layer, vectorLayer, markerLayer, drawPoint, centersArray = [], weightsArray = [], n = 0,
//创建一个grid的数据源
gridArrayData = [];
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
	map = new SuperMap.Map("map");
	map.addControl(new SuperMap.Control.Navigation());
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
		drawPoint.deactivate();
		centersArray.push(point);
		n++;
		weightsArray.push(400 + n * 100);
		//调用画圆方法
		getCirle(point);
	}

	// 前后台数据交�
	var vm = new Vue({
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
				$.ajax({
					type : "POST",
					url : baseURL + "importplace/list",
					// url : baseURL + "test/zdqy",
					contentType : "application/json",
					data : JSON.stringify({
						'jingd' : point.x,
						'weid' : point.y,
						'count' : 10000,
						'page' : page,
						'limit' : limit
					}),
					success : function(r) {
						var list = r.page.list;
						countcar = list.length;
						for (var i = 0; i < list.length; i++) {
						
						gridArrayData.push({  
							cph: list[i].cph,  
							num: list[i].num  
                        });      
						// 获取重点区域半径10公里内的徘徊车辆信息 start
						/*cph[i] = list[i].cph;
						phcount[i] = list[i].num;
						console.log("cph[i]="+cph[i]);
						console.log("phcount[i]="+phcount[i]);*/
						//获取重点区域半径10公里内的徘徊车辆信息 end
						}
						openInfoWin();
						//设置jqGrid的数据集
						$("#jqGrid").jqGrid('setGridParam', { data: gridArrayData}).trigger('reloadGrid');
						
					}
				});
			},
			clear : function() {
				closeInfoWin();
				clearElements();
			}
		}
	});

});

function getCirle(point) {
	var sides = 20;
	var origin = point;
	var polygon = SuperMap.Geometry.Polygon.createRegularPolygon(origin, 0.1,
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
	contentHTML += "<div>在此重点区域半径10公里范围内共有徘徊车" + countcar + "辆</div></div>";
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
		} ],
		viewrecords : true,
		height : 385,
		rowNum : 10,
		rowList : [ 10, 30, 50 ],
		rownumbers : true,
		rownumWidth : 25,
		autowidth : true,
		multiselect : true,
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
			// 隐藏grid底部滚动�
			$("#jqGrid").closest(".ui-jqgrid-bdiv").css({
				"overflow-x" : "hidden"
			});
		}
	});
}
