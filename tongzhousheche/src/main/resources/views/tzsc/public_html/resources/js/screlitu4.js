var map, layer, heatMapLayer, qssj, jssj, latitude = [], longitude = [], time = [], temp = [],radius=[],refreshtime,tab;
//对url参数进行解析
function GetRequest() {
	var urlstr = decodeURI(decodeURI(location.search)); // 获取url?符后的字符串
	var theRequest = new Object();
	if (urlstr.indexOf("?") != -1) {
		var str = urlstr.substr(1);
		strs = str.split("&");
		for (var i = 0; i < strs.length; i++) {
			theRequest[i] = unescape(strs[i].split("=")[1]);
		}
	}
	return theRequest;
}
var param = GetRequest();
tab = param[1];
//console.log("tab="+tab);
if(tab=='rw'){
	refreshtime = param[0]*1000;
}else{
	refreshtime = 1000*60*5;
}

function getMap() {
	parent.location.href = "screlitu4.html";
}


$(function() {
	if(tab=='rw'){
		$("#fh").show();
	}else{
		$("#fh").hide();
	}

	url = '${map.url}';
	if (!document.createElement('canvas').getContext) {
		alert('您的浏览器不支持 canvas，请升级');
		return;
	}
	map = new SuperMap.Map("map");
	map.addControl(new SuperMap.Control.Navigation());
	//map.addControl(new SuperMap.Control.MousePosition());
	layer = new SuperMap.Layer.TiledDynamicRESTLayer("tongzhou", url, {
		transparent : true,
		cacheEnabled : true
	}, {
		maxResolution : "auto"
	});
	heatMapLayer = new SuperMap.Layer.HeatMapLayer("heatMap", {
		"radius" : 45,
		"featureWeight" : "value"
	});
	layer.events.on({
		"layerInitialized" : addLayer
	});

	function addLayer() {
		map.addLayers([ layer, heatMapLayer ]);
		map.setCenter(new SuperMap.LonLat(116.708, 39.840), 11, false, false);
	}
	// 前后台数据交�
	var vm = new Vue({
		el : '#rrapp',
		data : {
		
		},
		methods : {
			display : function() {
				var myDate = new Date();//获取系统当前时间
				myDate.getFullYear(); //获取完整的年份(4位,1970-????)
				myDate.getMonth()+1; //获取当前月份(0-11,0代表1月)
				myDate.getDate(); //获取当前日(1-31)
				myDate.getHours()-1; //获取当前小时数(0-23)
				myDate.getMinutes(); //获取当前分钟数(0-59)
				myDate.getSeconds(); //获取当前秒数(0-59)
				var riqi = ""+myDate.getFullYear()+"-"+(myDate.getMonth()+1)+"-"+myDate.getDate()+
				" 00:00:00";
				var shijian = ""+myDate.getFullYear()+"-"+(myDate.getMonth()+1)+"-"+myDate.getDate()+
				" 23:59:59";
				clearHeatPoints();
				$.ajax({
					type : "POST",
					url : baseURL + "etcc/relitu2",
					contentType : "application/json",
					data : JSON.stringify({'riqi':riqi,'shijian':shijian,'sjly':4}),
					success : function(r) {
						//console.info($('#riqi').val());
						//console.info(r.list.length);
						for (var i = 0; i < r.list.length; i++) {
							//获取热力点的经纬度start
							latitude[i] = r.list[i].ejingdu;
							longitude[i] = r.list[i].eweidu;
							//获取热力点的经纬度end
							//热力点半径
							radius[i] = r.list[i].count*0.2;
							if (radius[i] < 45) {
								radius[i] = 45;
							}
							//console.info(radius[i]);
						}
						// console.info(time);
						var heatPoints = [];
						var num = r.list.length;
						var unit = "px";
						
						//热力点衰减度
						var weight =  9;
						
						for (var i = 0; i < num; i++) {
							heatMapLayer.radius = radius[i];
							heatPoints[i] = new SuperMap.Feature.Vector(
									new SuperMap.Geometry.Point(latitude[i],
											longitude[i]), {
										"value" : weight
									});
						}
						heatMapLayer.addFeatures(heatPoints);
					}
				});

			},
			clear : function() {
				
				clearHeatPoints();
			}
		}
	});
	vm.display();
	if(tab=='rw'){
		setInterval("shuxin()",refreshtime);
	}
	function clearHeatPoints() {
		heatMapLayer.removeAllFeatures();
	}
});
function shuxin() {
	document.getElementById("btn").click();
}
