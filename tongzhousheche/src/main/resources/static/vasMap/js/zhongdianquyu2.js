var local, map, layer, vectorLayer, markerLayer, centersArray = [], weightsArray = [],
	n = 0,infowin=null,carNum,start,end,count =[],marker = [],point = [],adress = []; 

$(function() {
	var style = {
			strokeColor : "#304DBE",
			strokeWidth : 1,
			pointerEvents : "visiblePainted",
			fillColor : "#304DBE",
			fillOpacity : 0.4},
		url1 = '${map.url}';
		
		vectorLayer = new SuperMap.Layer.Vector("Vector Layer");
		
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
			map.setCenter(new SuperMap.LonLat(116.64863825477, 39.895331089542), 11, false, false);
		}
		getCircle();
});

function getCircle() {
	//获取圆心点		
	point = new SuperMap.Geometry.Point(116.650876809, 39.908912673); 
	var size = new SuperMap.Size(44, 33), 
	offset = new SuperMap.Pixel(-(size.w / 2), -size.h), 
	icon = new SuperMap.Icon("../../theme/images/marker.png", size, offset);
	marker = new SuperMap.Marker(new SuperMap.LonLat(point.x,point.y));
	
	markerLayer.addMarker(marker);
	
	var sides = 50;
	var origin = point;
	var banjing = 2*0.01;
	//console.info("origin="+origin);
	var polygon = SuperMap.Geometry.Polygon.createRegularPolygon(origin,banjing,sides);
	//console.info("polygon="+polygon);
	var cercleVector = new SuperMap.Feature.Vector(polygon);
	vectorLayer.addFeatures(cercleVector);	
}