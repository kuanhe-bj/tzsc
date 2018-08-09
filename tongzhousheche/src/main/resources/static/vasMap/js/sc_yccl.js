var cph,kid = [] ,cishu = [] ,map, layer, heatMapLayer, labelLayer,qssj, jssj, latitude = [], longitude = [], time = [], temp = [],radius=[];
$(function() {
	//window.location=window.location.href + "#rrapp";
	// 前后台数据交互
	var vm = new Vue({
		el : '#rrapp',
		data : {
			riqi : '',
			shijian : '',
			showList : true,
			title : null,
			roleList : {},
			user : {
				status : 1,
				roleIdList : []
			},
			show : false,
			carNum : '',
			time : '',
			addr : '',
			result : ''
		},
		methods : {
			display : function() {
				clearHeatPoints();
				if(!$('#cph').val()){
					alert("请输入车牌号！");
					return;
				}
				var carNum = $('#cph').val().trim().replace(/\s/g,"");
				var atime = $('#startTime').val();
				var etime = $('#endTime').val();
				var num = $("#num").val().trim().replace(/\s/g,"");
				var st=carNum+","+atime+","+etime+","+num;
				$.ajax({
					type : "POST",
					url : baseURL + "generator/sc_yccl/mdd",
					contentType : "application/json",
					data : st,
					success : function(r) {
						if(!r.list || r.list.length<=0){
							alert("没有符合查询条件的记录！");
							return;
						}
						for (var i = 0; i < r.list.length; i++) {
							//获取热力点的经纬度start
							latitude[i] = r.list[i].jingdu;
							longitude[i] = r.list[i].weidu;
							//获取热力点的经纬度end
							cph = r.list[i].carNumber;
							kid[i] = r.list[i].adress; 
							cishu[i] = r.list[i].count;
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
						getLabel();
						vm.yc();
						var result = r.list[1].adress;
						//console.log(result);
						vm.result = result;
					}
				});
			},
			yc : function() {
				var carNum = $('#cph').val().trim().replace(/\s/g,"");
				carNum = carNum.toUpperCase();
				$.ajax({
					type : "POST",
					url : baseURL + "generator/sc_yccl/show",
					contentType : "application/json",
					data : carNum,
					success : function(r) {
						vm.carNum = r.data.carNumber;
						vm.time = r.data.enterTime;
						vm.addr = r.data.adress;
						vm.show = true;
					}
				});
			},
			clear : function() {
				$("#cph").val("");
				$("#startTime").val("");
				$("#endTime").val("");
				$("#num").val("");
				vm.show = false;
				clearHeatPoints();
			}
		}
	});

	url = '${map.url}';
	if (!document.createElement('canvas').getContext) {
		alert('您的浏览器不支持 canvas，请升级');
		return;
	}

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
	//新建一个策略并使用在矢量要素图�vector)上�
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
	layer.events.on({
		"layerInitialized" : addLayer
	});

	function addLayer() {
		map.addLayers([ layer, heatMapLayer ,labelLayer ]);
		map.setCenter(new SuperMap.LonLat(116.708, 39.840), 11, false, false);
	}

	function clearHeatPoints() {
		heatMapLayer.removeAllFeatures();
		labelLayer.removeAllFeatures();
	}
	function getLabel() {
		//创建一个矩形标签
		for(var i=0; i<longitude.length;i++){
			var messegStr = "在" + kid[i] + "," + cishu[i] + "次";
			var geoText = new SuperMap.Geometry.GeoText(latitude[i], longitude[i],messegStr);
			var geotextFeature = new SuperMap.Feature.Vector(geoText);
			labelLayer.addFeatures(geotextFeature);		
		}
		latitude=[], longitude=[];
	}
});
