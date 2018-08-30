
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
			map.setCenter(new SuperMap.LonLat(116.708, 39.840), 11, false, false);
		}
		//****************************************
		function GetRequest() {
			var urlstr = decodeURI(decodeURI(location.search)); // 获取url?后的字符串
			var theRequest = new Object();
			if (urlstr.indexOf("?") != -1) {
				var str = urlstr.substr(1);
				strs = str.split("&");
				for (var i = 0; i < strs.length; i++) {
					theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
				}
			}
			return theRequest;
		}
		//****************************************
		//获取圆心点
		var data = GetRequest();
		var str1 = data.jList.split(",");
		var str2 = data.wList.split(",");
		carNum = data.carNum;
		start = data.start;
		end = data.end;
		//console.log(carNum);
		count = data.cList.split(",");
		adress = data.aList.split(",");
		for (var i in str1) {
			//console.log(adress[i]);
			point[i] = new SuperMap.Geometry.Point(str1[i],str2[i]), 
			size = new SuperMap.Size(44, 33), 
			offset = new SuperMap.Pixel(-(size.w / 2), -size.h), 
			icon = new SuperMap.Icon("../theme/images/marker.png", size, offset);
			marker[i] = new SuperMap.Marker(new SuperMap.LonLat(point[i].x,point[i].y));
			markerLayer.addMarker(marker[i]);
			marker[i].events.on({
			   "click":openInfoWin,
			   "scope": marker[i]
			});
		
			var sides = 20;
			var origin = point[i];
			var polygon = SuperMap.Geometry.Polygon.createRegularPolygon(origin,0.005,sides);
			var cercleVector = new SuperMap.Feature.Vector(polygon);
			if(count[i]>=20){
				cercleVector.style={
						strokeColor:"#CC0000",
						fillColor:"#FF0000",
						strokeWidth:2 ,
						fillOpacity:0.1
						};
			}else if(count[i]<20&&count[i]>5){
				cercleVector.style={
						strokeColor:"#CC6633",
						fillColor:"#FF6600",
						strokeWidth:2 ,
						fillOpacity:0.1
						};
			}else if(count[i]<=5){
				cercleVector.style={
						strokeColor:"#1E90FF",
						fillColor:"#C6E2FF",
						strokeWidth:2 ,
						fillOpacity:0.1
						};
			}
			vectorLayer.addFeatures(cercleVector);
		}
});

function getCirle(point) {
	var sides = 20;
	var origin = point;
	var polygon = SuperMap.Geometry.Polygon.createRegularPolygon(origin,0.01,sides);
	var cercleVector = new SuperMap.Feature.Vector(polygon);
	vectorLayer.addFeatures(cercleVector);
}
function clearElements() {
	n = 0;
	centersArray = [];
	weightsArray = [];
	markerLayer.clearMarkers();
	vectorLayer.removeAllFeatures();
}

function getInfo() {
	var j =0;
	for(var i=0; i<3; i++){
		if(marker[i]==this){
			j = i;
		}
	}
	var contentHTML = "<div style=\'font-size:.8em; opacity: 0.8; overflow-y:hidden;\'>";
    contentHTML += "<div>车牌号: "+carNum+"</div>";
    contentHTML += "<div>落脚次数 : "+count[j]+"</div>";
    contentHTML += "<div>地址 : "+adress[j]+"</div>";
    contentHTML += "<div>经纬度 : "+point[j].x+","+point[j].y+"</div></div>";
    var popup = new SuperMap.Popup.FramedCloud("popwin",new SuperMap.LonLat(point[j].x, point[j].y),null,contentHTML,null,true);
    infowin = popup;
    infowin.show();
    map.addPopup(popup);
}

function set() {
	sessionStorage.setItem("carNum", carNum);
	sessionStorage.setItem("start", start);
	sessionStorage.setItem("end", end);
}

function openInfoWin() {
	closeInfoWin();
	var j =0;
	for(var i=0;i<marker.length;i++){
		if(marker[i]==this){
			j = i;
		}
	}
	var contentHTML = "<div style=\'font-size:.8em; opacity: 0.8; overflow-y:hidden;\'>";
    contentHTML += "<div>车牌号: "+carNum+"</div>";
    contentHTML += "<div>落脚次数 : "+count[j]+"</div>";
    contentHTML += "<div>地址 : "+adress[j]+"</div>";
    contentHTML += "<div>经纬度 : "+point[j].x+","+point[j].y+"</div></div>";
    var popup = new SuperMap.Popup.FramedCloud("popwin",new SuperMap.LonLat(point[j].x, point[j].y),null,contentHTML,null,true);
    infowin = popup;
    map.addPopup(popup);
}

function closeInfoWin() {// 关闭窗口
	if (infowin) {
		try {
			infowin.hide();// 隐藏窗体对象
			infowin.destroy();// 销毁窗体对象
		} catch (e) {
		}
	}
}
