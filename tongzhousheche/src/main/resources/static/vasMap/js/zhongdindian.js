var infowin = null, countcar, phcount = [], point, local, 
map, layer, vectorLayer, markerLayer, drawPoint, centersArray = [], weightsArray = [], n = 0,
//创建一个grid的数据源
gridArrayData = [],model,vm;
$(function() {
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
	
//	function a(obj){return document.getElementById(obj);} 
//	function show(objid) {a(objid).style.display='inline';} 
//	function hidden(objid) {a(objid).style.display='none';} 

	// 前后台数据交互
	vm = new Vue({
		el : '#toolbar',
		data : {

		},
		methods : {
//			zd:function(){
//				window.location.href= baseURL + "vasMap/views/ss_zdqyjk.html";
//			},
			selectCenters : function() {
				if(!$("#banjing").val() || $("#banjing").val()==""){
					alert("请输入查询范围！");
					return;
				}
				clearElements();
				drawPoint.activate();
			},
			display : function() {
				
				if(!$("#banjing").val() || $("#banjing").val()==""){
					alert("请输入查询范围！");
					return;
				}
				
				if(!point || point == ""){
					alert("请选择地点！");
					return;
				}
				
				var banjing = trim($("#banjing").val());
				sessionStorage.setItem("jingdu", point.x);
				sessionStorage.setItem("weidu", point.y);
				sessionStorage.setItem("num", banjing);
				parent.hidden('map');
				$.ajax({
					type : "POST",
					url : baseURL + "importplace/list",
					// url : baseURL + "test/zdqy",
					contentType : "application/json",
					data : JSON.stringify({
						'jingd' : point.x,
						'weid' : point.y,
						'count' :banjing
					}),
					success : function(r) {
						
						if(!r.page || r.page.list.length<=0){
							alert("没有符合查询条件的记录！");
							return;
						}
						var list = r.page.list;
						countcar = list.length;
						
						point = "";
					}
				});
			},
			clear : function() {
				clearElements();
			}
		}
	});
});

function getCirle(point) {
	var sides = 50;
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
	
	gridArrayData=[];
	$("#jqGrid").jqGrid('clearGridData');
}

function trim(str){
	return str.replace(/\s|\xA0/g,"");   
}

