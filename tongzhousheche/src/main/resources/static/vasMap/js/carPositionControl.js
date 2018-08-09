var infowin = null, countcar, phcount = [], point, local, 
map, layer, vectorLayer, markerLayer, drawPoint, centersArray = [], weightsArray = [], n = 0,
//创建一个grid的数据源
gridArrayData = [],labelLayer,radius;
/*function SetJssj(datas) {
	var jssj = $('#jssj').val(datas);
}*/
//setInterval("rel()",10000);  
$(function() {
	
	//window.location=window.location.href + "#rrapp";
	//getgrid();
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
	//新建一个策略并使用在矢量要素图层(vector)上。
	var strategy = new SuperMap.Strategy.GeoText();
	strategy.style = {
	  fontColor:"#FF7F00",
	  fontWeight:"bolder",
	  fontSize:"10px",
	  fill: true,
	  fillColor: "#FFFFFF",
	  fillOpacity: 1,
	  stroke: true,
	  strokeColor:"#8B7B8B"
	};		
	var strategies = [strategy];
	labelLayer = new SuperMap.Layer.Vector("Label",{strategies: strategies});
	function addLayer() {
		map.addLayers([ layer, vectorLayer, markerLayer ,labelLayer]);
		map.setCenter(new SuperMap.LonLat(116.708, 39.840), 11, false, false);
		//通过selectFeature控件为标签添加点击事件
		var callbacks = {
			click : function(feature) {
				var ss=feature.geometry.text.split("-");
				var carNum=ss[0];
				parent.sayhello("vasMap/views/scguijitu.html","2-"+carNum,"","");
			}
		};

		//实例化 selectFeature 控件
		var selectFeature = new SuperMap.Control.SelectFeature(labelLayer, {
			callbacks : callbacks
		});
		//map上添加控件
		map.addControl(selectFeature);
		//激活控件
		selectFeature.activate();
	}
	rtp();
    
	function drawCompleted(drawGeometryArgs) {
		radius = trim($("#bj").val());
		if(radius == null || radius == ""){
			radius = 5;
		}
		point = drawGeometryArgs.feature.geometry, 
		size = new SuperMap.Size(44,33), 
		offset = new SuperMap.Pixel(-(size.w / 2), -size.h),
		icon = new SuperMap.Icon("../theme/images/marker.png", size,offset);
		markerLayer.addMarker(new SuperMap.Marker(new SuperMap.LonLat(point.x,
				point.y), icon));
		drawPoint.deactivate();
		centersArray.push(point);
		n++;
		weightsArray.push(400 + n * 100);
		
	}
});
	
// 前后台数据交互
function rtp() {
	
	$.ajax({
		type : "POST",
		url : baseURL + "etcp/rtp",
		// url : baseURL + "test/zdqy",
		contentType : "application/json",
		data : {},
		success : function(r) {
			if(!r.page || r.page.length<=0){
				alert("没有符合查询条件的记录！");
				return;
			}
			var list = r.page;
			for (var i = 0; i < list.length; i++) {
               var geoText = new SuperMap.Geometry.GeoText(list[i].jingdu, list[i].weidu,list[i].carNumber+"-"+list[i].enterTime +"\n"+"出现在"+list[i].adress);
               var geotextFeature = new SuperMap.Feature.Vector(geoText);
               labelLayer.addFeatures(geotextFeature);
           }
		
		}
	});
}
function rel() {
	location.reload();
}