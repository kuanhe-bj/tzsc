var map, layer, heatMapLayer, kkbh = [], qssj, jssj, latitude = [], longitude = [], time = [], temp = [];
$(function() {
	// 前后台数据交�
	var vm = new Vue({
		el : '#rrapp',
		data : {
			q : {
				etcp : null,
				qssj : null,
				jssj : null
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
				$.ajax({
					type : "POST",
					url : baseURL + "guijiback/guiji",
					contentType : "application/json",
					data : JSON.stringify(vm.q.etcp),
					success : function(r) {
						
							
							//获取热力点的经纬度start
							latitude[i] = sessionStorage.getItem("jingdu");
							longitude[i] =sessionStorage.getItem("weidu");
							//获取热力点的经纬度end
							
							console.info(latitude[i]);
							console.info(longitude[i]);
						
						// console.info(time);
						var heatPoints = [];
						var num = r.list.length;
						var unit = "px";
						//热力点半径
						var radius = sessionStorage.getItem("count");
						if (radius < 45) {
							radius = 20;
						}
						//热力点衰减度
						var weight = Math.random() * 9;
						
						heatMapLayer.radius = radius
						for (var i = 0; i < num; i++) {
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
        elem : '#qssj'
        ,type: 'datetime'
    });
    //时间选择器
    laydate.render({
        elem: '#jssj'
        ,type: 'datetime'
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
	layer.events.on({
		"layerInitialized" : addLayer
	});

	function addLayer() {
		map.addLayers([ layer, heatMapLayer ]);
		map.setCenter(new SuperMap.LonLat(116.708, 39.840), 11, false, false);
	}

	function clearHeatPoints() {
		heatMapLayer.removeAllFeatures();
	}
});
