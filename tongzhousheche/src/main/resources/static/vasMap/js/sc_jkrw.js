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
});
	function getCirle(point) {
		var sides = 20;
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

$(function () {
	//window.location=window.location.href + "#rrapp";
    $("#jqGrid").jqGrid({
        url: baseURL + '/generator/sc_jkrw/find',
        datatype: "json",
        colModel: [			
			{ label: '任务编号', 
				name: 'id',
				index: 'id',
				width: 160, 
				key: true
			},
			{ label: '用户名', name: 'username', width: 100 },
			{ label: '监控类型', name: 'mytype', width: 100,
				formatter:function(cellvalue, options, rowObject){
					if(cellvalue == 1) {
						return cellvalue = '轨迹实时';
					} else if(cellvalue == 2) {
						return cellvalue = '位置实时';
					} else {
						return cellvalue = '重点区域';
					}	
				}
			},
			{ label: '创建时间', name: 'createtime', width: 160 },
			{ label: '参数', name: 'parameter', width: 400 },
			{ label: '是否有效', name: 'id', width: 100,
				formatter:function(cellvalue, options, rowObject){
					var valid = rowObject.valid;
					if(valid == 1) {
						return '<button class="btn btn-default" style="background: #337ab7;color:#fff;" onclick="check2(\'' + cellvalue + '\')">有效</button>';
					} else {
						return '<button class="btn btn-default" style="background: #d9534f;color:#fff;" onclick="check1(\'' + cellvalue + '\')">无效</button>';
					}	
				}
			},
			{ label: '查看分析结果',
				name: 'id', 
				width: 100 ,
				formatter:function(cellvalue, options, rowObject){
					var mytype = rowObject.mytype;
					var valid = rowObject.valid;
					var parameter = rowObject.parameter;
					return '<a class="btn btn-primary"  style="background: #337ab7;color:#fff;" onclick="infor1(\''+cellvalue+'\','+mytype+','+valid+',\''+parameter+'\')">'+'分析结果'+'</a>';
				}	
			
			}
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 85, 
        autowidth:true,
        shrinkToFit:false, 
        multiselect: true,
       
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            cph:"cph", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "" }); 
        }
    });
});


function check1(id) {
	var url = '/generator/sc_jkrw/check1';
	$.ajax({
		type : "POST",
		url : baseURL + url,
		contentType : "application/json",
		data : id,
		success : function(obj) {
			if(obj.num === 0) {
				alert('操作成功', function(index) {
					$("#jqGrid").trigger("reloadGrid");
				});
			} else {
				alert("该类型的任务已有有效任务，请将其更改为无效后再试！");
				return;
			}
		}
	});
}
function check2(id) {
	var url = '/generator/sc_jkrw/check2';
	$.ajax({
		type : "POST",
		url : baseURL + url,
		contentType : "application/json",
		data : id,
		success : function(obj) {
			alert('操作成功', function(index) {
				$("#jqGrid").trigger("reloadGrid");
			});
		}
	});
}

	var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			sel:null,
			time1: null,
			time2: null,
			num:null
		},
		showList: true,
		title:null,
	},
	methods: {
		query: function () {
			var myselect = document.getElementById("sel");
			var mytype = myselect.value;
			if(mytype == null || mytype == null ) {
				alert("请选择监控任务类型！");
				return;
			}
			var cph = $("#cph").val();
			var start = $("#time1").val();
			var end = $("#time2").val();
			var num = sessionStorage.getItem("num");
			var jingdu = sessionStorage.getItem("jingdu");
			var weidu = sessionStorage.getItem("weidu");
			$("#jqGrid").jqGrid('setGridParam',{
				url: baseURL + 'generator/sc_jkrw/query',
				postData: {
					'cph' : cph,
					'mytype' : mytype,
					'start' : start,
					'end' : end,
					'num' : num,
					'jingdu' : jingdu,
					'weidu' : weidu
				},
                page:1
            }).trigger("reloadGrid");
			window.location.reload();
		},
		reload: function () {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
				$("#jqGrid").jqGrid('setGridParam',{
	                page:page
	            }).trigger("reloadGrid");
		},
		del: function (event) {
			var ids = getSelectedRows();
		
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "generator/sc_jkrw/delete",
                    contentType: "application/json",
				    data: JSON.stringify(ids),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		}
	}
	
        
});

	function infor1(id, mytype, valid, parameter){
		//console.log(parameter + "," + valid + "," + mytype);
		if(valid != 1) {
			alert("任务无效，请先修改任务为有效！");
			return;
		}
		$.ajax({
			type: "POST",
		    url: baseURL + "generator/sc_jkrw/fenxi",
            contentType: "application/json",
		    data: id,
		    success: function(r){
		    	cph = r.data;
		    	//console.log(cph);
		    	if(mytype == 1) {
		    		var str = parameter.split("&");
		    		var start = str[0].split("=")[1];
		    		var end = str[1].split("=")[1];
		    		//console.log(start + "," + end);
		    		var searchUrl = baseURL + "./vasMap/views/sc_guijitu.html" +
		    		"?cph=" + cph + "&start=" + start + "&end=" + end;
		    		location.href = encodeURI(searchUrl);
		    	} else if(mytype == 2) {
		    		var searchUrl = baseURL + "./vasMap/views/carPositionControl.html";
		    		location.href = encodeURI(searchUrl);
		    	} else if(mytype == 3) {
		    		var str = parameter.split("&");
		    		var jingdu = str[0].split("=")[1];
		    		var weidu = str[1].split("=")[1];
		    		var r = str[2].split("=")[1];
		    		var searchUrl = baseURL + "./vasMap/views/ss_zdqyjk.html" +
		    		"?jingdu=" + jingdu + "&weidu=" + weidu + "&r=" + r;
		    		location.href = encodeURI(searchUrl);
		    	}
			}
		});
	}

//	function infor(cph){
//		 parent.sayhello("modules/manager/scparkcheck.html",cph);
//
//	}