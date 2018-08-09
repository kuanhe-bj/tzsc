var map, layer, heatMapLayer, qssj, jssj, latitude = [], longitude = [], time = [], temp = [],radius=[];
$(function() {
	url = '${map.url}';
	map = new SuperMap.Map("xmap");
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
})

window.onload = function() {
	var vm = new Vue({
		el : '#rrapp',
		data : {
			q : {
				qssj : getDate(),
				jssj : getNowFormatDate()
			},
			showList : true,
			title : null,
			roleList : {},
			user : {
				status : 1,
				roleIdList : []
			}
		},
		methods : {
			display : function() {
				clearHeatPoints();
				var qishi = $('#qssj').val();
				var jieshu = $('#jssj').val();
				if(qishi == null || qishi == ""){
					qishi = fun_date(-3);
				}
				if(jieshu == null || jieshu == ""){
					jieshu = getNowFormatDate();
				}
				var time1 = Date.parse($("#qssj").val());
				var time2 = Date.parse($("#jssj").val());
				if(time1>time2){
					alert("起始时间不能大于结束时间");
					return;
				}
				$.ajax({
					type : "POST",
					url : baseURL + "etcc/relitu",
					contentType : "application/json",
					data : JSON.stringify({'qssj':qishi,'jssj':jieshu,'sjly':4}),
					success : function(r) {
						if(!r.list || r.list.length<=0){
							alert("没有符合查询条件的记录！");
							return;
						}
						for (var i = 0; i < r.list.length; i++) {
							//获取热力点的经纬度start
							latitude[i] = r.list[i].jingdu;
							longitude[i] = r.list[i].weidu;
							//console.log("latitude[i]="+latitude[i]);
							//获取热力点的经纬度end
							//热力点半径
							radius[i] = r.list[i].count*0.2;
							if (radius[i] < 45) {
								radius[i] = 20;
							}
						}
						// console.info(time);
						var heatPoints = [];
						var num = r.list.length;
						var unit = "px";
						
						//热力点衰减度
						var weight = 9;
						
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
	laydate.render({
		elem : '#qssj',
		type : 'datetime'
	});
	//时间选择器
	laydate.render({
		elem : '#jssj',
		type : 'datetime'
	});
	setInterval("load_home()",1000*60*5);
}
function load_home(){
	//$("#display").click();
	document.getElementById("display").click();
}

function clearHeatPoints() {
	heatMapLayer.removeAllFeatures();
}
//获取系统当前时间
function getNowFormatDate() {
	var date = new Date();
	var seperator1 = "-";
	var seperator2 = ":";
	var month = date.getMonth() + 1;
	var strDate = date.getDate();
	var strMin = date.getMinutes();
	if (month >= 1 && month <= 9) {
		month = "0" + month;
	}
	if (strMin >= 1 && strMin <= 9) {
		strMin = "0" + strMin;
	}
	if (strDate >= 1 && strDate <= 9) {
		strDate = "0" + strDate;
	}
	var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
	+ " " + date.getHours() + seperator2 + strMin
	+ seperator2 + date.getSeconds();
	return currentdate;
} 

function getDate() {
	var date = new Date();
	var seperator1 = "-";
	var seperator2 = ":";
	var month = date.getMonth() + 1;
	var strDate = date.getDate();
	var strMin = date.getMinutes();
	if (month >= 1 && month <= 9) {
		month = "0" + month;
	}
	if (strMin >= 1 && strMin <= 9) {
		strMin = "0" + strMin;
	}
	if (strDate >= 1 && strDate <= 9) {
		strDate = "0" + strDate;
	}
	var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
	+ " " + "00" + seperator2 + "00"
	+ seperator2 + "00";
	return currentdate;
}

//获取当前时间前后指定的时间
function fun_date(aa){
	var date1 = new Date(),
	time1=date1.getFullYear()+"-"+(date1.getMonth()+1)+"-"+date1.getDate();//time1表示当前时间
	var date2 = new Date(date1);
	date2.setDate(date1.getDate()+aa);
	var month = date2.getMonth() + 1;
	var strDate = date2.getDate();
	var strMin = date2.getMinutes();
	if (month >= 1 && month <= 9) {
		month = "0" + month;
	}
	if (strDate >= 1 && strDate <= 9) {
		strDate = "0" + strDate;
	}
	if (strMin >= 1 && strMin <= 9) {
		strMin = "0" + strMin;
	}
	var time2 = date2.getFullYear()+"-"+month+"-"+strDate
	+" "+date2.getHours()+":"+strMin+":"+date2.getSeconds();
	return time2;
}
