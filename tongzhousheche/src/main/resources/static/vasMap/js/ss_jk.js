var allcar={};
/*ETCP*/

var et=0;
var zf=0;
var wd=0;
var gw=0;
var sa=0;
var cx=0;

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

var data = GetRequest();
var j = data.jingdu;
var w = data.weidu;
var r = data.r;
$(function () {
	$("#jl").val(r);
	$("#jingdu").val(j);
	$("#weidu").val(w);
	plate();
	getCircle();
	getLabel(); 
});

function plate(){ 
	var params={};
	//精度
	params["jingdu"]=$("#jingdu").val();
	//维度
	params["weidu"]=$("#weidu").val();
	//半径
	params["count"]=$("#jl").val();
	params["et"]=et;
	params["zf"]=zf;
	params["wd"]=wd;
	params["gw"]=gw;
	params["sa"]=sa;
	params["cx"]=cx;
$.ajax({
	url : baseURL + 'generator/zfyc/plat',
	dataType : "json",
	data:params,
	type : "post",
	async : false,
	success : function(r) {		

         allcar=r.list;
         //console.log(r.list);
         if(allcar==null||allcar==""){
        	 alert("没有符合当前条件的车辆！");
         }
	}
});
}



var clkbool=false;
var vm = new Vue({
	el : '#rrapp',
	data : {
		q : {
			//现在时间
			//kssj : time,
			//qhjgsj : qhjgsj,
			jhzyfw : null,
			jingdu : null,
			weidu : null,
			etcp:null,
			zfyc:null,
			wdc:null,
			gwdq:null,
			sac:null,
			cxyc:null
		},
		showList : true,
		title : null,
		role : {},

	},
	methods : {
		query : function() {
			var jhzyfw = $("#jl").val();
			var jingdu = $("#jingdujl").val();
			var weidu = $("#weidu").val();
			if(vm.q.etcp) {
				et = 1;
			} else {
				et = 0;
			}
			if(vm.q.zfyc){
				//console.log($("#se").val());
				zf=1;
			} else {
				zf=0;
			}
			if(vm.q.wdc){
				wd = 1;
			} else {
				wd = 0;
			}
			if(vm.q.gwdq){
				gw = 1;
			} else {
				gw = 0;
			}
			if(vm.q.sac==true){
				sa = 1;
			} else {
				sa = 0;
			}
			if(vm.q.cxyc){
				//console.log($("#se").val());
				cx = 1;
			} else {
				cx = 0;
			}
			plate();
			clkbool=true;

			clearElements();
			getCircle();
			getLabel(); 
			setInterval("vm.query()",30000);
		}
	}
});
getMap();
//加载地图
var local, map, layer, vectorLayer, labelLayer ,markerLayer, centersArray = [], weightsArray = [],
n = 0,infowin=null,carNum,count =[],marker = [],point; 
function getMap() {
	url1 = '${map.url}';
	
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
	
	//新建一个策略并使用在矢量要素图层(vector)上。
	var strategy = new SuperMap.Strategy.GeoText();
	strategy.style = {
	  fontColor:"#FF7F00",
	  fontWeight:"bolder",
	  fontSize:"14px",
	  fill: true,
	  fillColor: "#FFFFFF",
	  fillOpacity: 1,
	  stroke: true,
	  strokeColor:"#8B7B8B"
	};		
	var strategies = [strategy];
	labelLayer = new SuperMap.Layer.Vector("Label",{strategies: strategies});
	markerLayer = new SuperMap.Layer.Markers("Markers");
	function addLayer() {
		map.addLayers([ layer, vectorLayer, markerLayer,labelLayer ]);
		map.setCenter(new SuperMap.LonLat(116.708, 39.840), 11, false, false);
		//通过selectFeature控件为标签添加点击事件
		var callbacks = {
			click : function(feature) {
				console.log(feature.geometry.text);
				sessionStorage.setItem("asd",feature.geometry.text);
				window.location.href=baseURL+"vasMap/views/zdqyjk.html";
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
	getCircle();
};
function getCircle() {
	//获取圆心点		
	point = new SuperMap.Geometry.Point($("#jingdu").val(),$("#weidu").val()); 
	var size = new SuperMap.Size(44, 33), 
	offset = new SuperMap.Pixel(-(size.w / 2), -size.h), 
	icon = new SuperMap.Icon("../../theme/images/marker.png", size, offset);
	marker = new SuperMap.Marker(new SuperMap.LonLat(point.x,point.y));
	
	markerLayer.addMarker(marker);
	
	var sides = 50;
	var origin = point;
	var banjing = $("#jl").val()*0.01;
	//console.info("origin="+origin);
	var polygon = SuperMap.Geometry.Polygon.createRegularPolygon(origin,banjing,sides);
	//console.info("polygon="+polygon);
	var cercleVector = new SuperMap.Feature.Vector(polygon);
	vectorLayer.addFeatures(cercleVector);	
}

function getLabel() {
	//创建一个矩形标签
	//var carStr = allcar[0].carNumber+""+allcar[0].exitTime;
	//console.log(allcar.length);
	for(var i=0; i<allcar.length;i++){
		var geoText = new SuperMap.Geometry.GeoText(allcar[i].jingdu, allcar[i].weidu,allcar[i].carNumber);
		var geotextFeature = new SuperMap.Feature.Vector(geoText);
		//console.log(geoText);
		//console.log(geotextFeature);
		labelLayer.addFeatures(geotextFeature);		
	}
}

function clearElements() {
n = 0;
centersArray = [];
weightsArray = [];
markerLayer.clearMarkers();
vectorLayer.removeAllFeatures();
labelLayer.removeAllFeatures();
}

function openInfoWin() {
var j =0;
for(var i=0;i<marker.length;i++){
	if(i==this){
		j = i;
	} 
}
console.log("j="+j);
var contentHTML = "<div style=\'font-size:.8em; opacity: 0.8; overflow-y:hidden;\'>";
contentHTML += "<div>车牌号: "+cph+"</div>";
contentHTML += "<div>落脚次数 : "+count[j]+"</div>";
contentHTML += "<div>经纬度 : "+point[j].x+","+point[j].y+"</div></div>";
var popup = new SuperMap.Popup.FramedCloud("popwin",new SuperMap.LonLat(point[j].x, point[j].y),null,contentHTML,null,true);
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

