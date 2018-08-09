var infowin = null, countcar, phcount = [], point, local, 
map, layer, vectorLayer, markerLayer, drawPoint, centersArray = [], weightsArray = [], n = 0,
//创建一个grid的数据源
gridArrayData = [],labelLayer,radius;
/*function SetJssj(datas) {
	var jssj = $('#jssj').val(datas);
}*/
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
	  fontSize:"14px",
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
				window.location.href=baseURL+"modules/generator/sc_dtcldzdans.html?cph="+feature.geometry.text+"&tab="+1;
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
	// 前后台数据交互
	var vm = new Vue({
		el : '#rrapp',
		data : {
			q : {bj:null,num:null},
			person: {zd: false, sa: false},
			checked1: '',
			checked2: '',
			checked3: '',
			checked4: '',
			checked5: ''
		},
		methods : {
			selectCenters : function() {
				clearElements();
				drawPoint.activate();
			},
			display : function() {
				if($('#qssj').val() == null || $('#qssj').val() == ""){
					alert("请选择起始时间");
					return;
				}
				if($('#jssj').val() == null || $('#jssj').val() == ""){
					alert("请选择结束时间");
					return;
				}
				var time1 = Date.parse($("#qssj").val());
				var time2 = Date.parse($("#jssj").val());
				if(time1>time2){
					alert("起始时间不能大于结束时间");
					return;
				}
				if(!point || point == ""){
					alert("请选择地点！");
					return;
				}
				var num = $("#num").val();
				$.ajax({
					type : "POST",
					url : baseURL + "keshihua/query",
					// url : baseURL + "test/zdqy",
					contentType : "application/json",
					data : JSON.stringify({
						'qssj' : $("#qssj").val(),
						'jssj' : $("#jssj").val(),
						'jingd' : point.x,
						'weid' : point.y,
						'count' : trim(radius),
						'zd' : vm.person.zd,
						'sa' : vm.person.sa,
						'num' : num,
						'zfyc': vm.checked1, 
						'csyc': vm.checked2, 
						'jpdp': vm.checked3,
						'yncl': vm.checked4, 
						'xxcl': vm.checked5
					}),
					success : function(r) {
						if(!r.page || r.page.length<=0){
							alert("没有符合查询条件的记录！");
							return;
						}
						var list = r.page;
						for (var i = 0; i < list.length; i++) {
                            var geoText = new SuperMap.Geometry.GeoText(list[i].jingdu, list[i].weidu,list[i].cph);
                            var geotextFeature = new SuperMap.Feature.Vector(geoText);
                            labelLayer.addFeatures(geotextFeature);
    						gridArrayData.push({  
    							cph: list[i].cph,  
    							num: list[i].num  
                            }); 
                        }
						$("#jqGrid").jqGrid('setGridParam', { data: gridArrayData}).trigger('reloadGrid');
						point = "";
					}
				});
			},
			clear : function() {
				clearElements();
			}
		}
	});
    //时间选择器
    laydate.render({
        elem : '#qssj'
    });
    function SetQssj(datas) {
        //console.log("datas= "+datas);
        var qssj = $('#qssj').val(datas);
    }
    laydate.render({
        elem : '#jssj'
    });
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
		//调用画圆方法
		getCirle(point);
	}
});

function getgrid() {
	$("#jqGrid").jqGrid({
		url: '',
		datatype : "local",
		colModel : [ {
			label : '车牌号',
			name : 'cph',
			width : 75
		}, {
			label : '次数',
			name : 'num',
			width : 75
		}],
		viewrecords : true,
		height : 361,
		rowNum : 10,
		rowList : [ 10, 30, 50 ],
		rownumbers : true,
		rownumWidth : 85,
		autowidth : true,
		multiselect : false,
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
			// 隐藏grid底部滚动条
			$("#jqGrid").closest(".ui-jqgrid-bdiv").css({
				"overflow-x" : "hidden"
			});
		}
	});
}

	
function getCirle(point) {
	var sides = 50;
	var origin = point;
	//console.log(point.x);
	var polygon = SuperMap.Geometry.Polygon.createRegularPolygon(origin, radius*0.01,
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
	labelLayer.removeAllFeatures();
	gridArrayData=[];
	$("#jqGrid").jqGrid('clearGridData');
}
function trim(str){
	return str.replace(/\s|\xA0/g,"");   
}