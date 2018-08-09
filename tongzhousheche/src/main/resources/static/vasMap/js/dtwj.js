var cph=sessionStorage.getItem("cph");
// 车牌号
sessionStorage.setItem("cph",cph);
var number=sessionStorage.getItem("number");
var pag=sessionStorage.getItem("page");
var name=sessionStorage.getItem("name");
var qhjgsj = sessionStorage.getItem("time");
var jhzyfw = sessionStorage.getItem("fanwei");
var jingdu = sessionStorage.getItem("jindu");
var weidu = sessionStorage.getItem("weidu");
var faddxz = null;
sessionStorage.removeItem("cph");
sessionStorage.removeItem("page");
sessionStorage.removeItem("number");
sessionStorage.removeItem("name");
sessionStorage.removeItem("time");
sessionStorage.removeItem("fanwei");
sessionStorage.removeItem("jindu");
sessionStorage.removeItem("weidu");

// 车辆原点经纬度
if(jingdu==null||jingdu==""||weidu==null||weidu==""){
	alert("当前涉事车辆不存在经纬度请添加经纬度");
}
var time=sessionStorage.getItem("fakssj");
var et=0;
var zf=0;
var wd=0;
var gw=0;
var sa=0;
var cx=0;
var gridArrayData = new Array();

$(function() {
	getgrid();
});
var vm = new Vue({
	el : '#rrapp',
	data : {
		q : {
			//现在时间
			kssj : time,
			qhjgsj : qhjgsj,
			jhzyfw : jhzyfw,
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
		fh:function(){
			sessionStorage.setItem("nu",number);
			sessionStorage.setItem("na",name);
			sessionStorage.setItem("ti",qhjgsj);
			sessionStorage.setItem("jh",jhzyfw);
			sessionStorage.setItem("page",pag);
			window.location.href= baseURL + 'modules/generator/scsmajor.html';
		},
		query : function() {
			clearElements();
			if(vm.q.etcp==true) {
				et = 1;
			} else {
				et = 0;
			}
			if(vm.q.zfyc==true){
				zf = 1;
			} else {
				zf = 0;
			}
			if(vm.q.wdc==true){
				wd = 1;
			} else {
				wd = 0;
			}
			if(vm.q.gwdq==true){
				gw = 1;
			} else {
				gw = 0;
			}
			if(vm.q.sac==true){
				sa = 1;
			} else {
				sa = 0;
			}
			if(vm.q.cxyc==true){
				cx = 1;
			} else {
				cx = 0;
			}
			var kssj = $("#kssj").val();
			var qhjgsj = vm.q.qhjgsj;
			var jhzyfw = vm.q.jhzyfw;
			// 间隔毫秒
			var sjjg = qhjgsj * 60 * 60 * 1000;
			// 输入时间
			var sj = Date.parse(kssj);
			var qm = sj - sjjg;
			var date = new Date(qm);
			var year = date.getFullYear();
			var month = date.getMonth() + 1;
			var day = date.getDate();
			var hour = date.getHours();
			var minute = date.getMinutes();
			var second = date.getSeconds();
			// 前时间
			var qsj = year + "-" + month + "-" + day + " " + hour + ":"
					+ minute + ":" + second;
			//console.log('前时间：'+qsj);
			var hsj = sj + sjjg;
			var date = new Date(hsj);
			var year = date.getFullYear();
			var month = date.getMonth() + 1;
			var day = date.getDate();
			var hour = date.getHours();
			var minute = date.getMinutes();
			var second = date.getSeconds();
			// 后时间
			hsj = year + "-" + month + "-" + day + " " + hour + ":"
					+ minute + ":" + second;
			//console.log('后时间：'+hsj);
			
			var pi = {};
// 地图获取
			pi["count"] =$("#jl").val() ;
			pi["jind"] = jingdu;
			pi["weid"] = weidu;
			pi["qsj"]=qsj;
			pi["hsj"]=hsj;
			pi["et"]=et;
			pi["zf"]=zf;
			pi["wd"]=wd;
			pi["gw"]=gw;
			pi["sa"]=sa;
			pi["cx"]=cx;
			$.ajax({
				url : baseURL + 'generator/dsj/map_allcar',
				dataType : "json",
				type : "post",
				data : pi,
				async : false,
				success : function(r) {
					// 圈内all车牌
					var allcar = new Array();
					for ( var i in r.page) {
						allcar.push(r.page[i]);
						gridArrayData.push({  
							cph: r.page[i].carNumber,  
							num: r.page[i].count  
						});
					}
					if (r.page==null) {
						alert("暂无数据!");
					}
					$("#jqGrid").jqGrid('setGridParam', { data: gridArrayData}).trigger('reloadGrid');
					
					getCircle();
					getLabel(allcar);
				}
			});
			
		},
		clear : function() {
			clearElements();
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
	point = new SuperMap.Geometry.Point(jingdu,weidu); 
	var size = new SuperMap.Size(44, 33), 
	offset = new SuperMap.Pixel(-(size.w / 2), -size.h), 
	icon = new SuperMap.Icon("../../theme/images/marker.png", size, offset);
	marker = new SuperMap.Marker(new SuperMap.LonLat(point.x,point.y));
	
	markerLayer.addMarker(marker);
	
	var sides = 50;
	var origin = point;
	var banjing = vm.q.jhzyfw*0.01;
	var polygon = SuperMap.Geometry.Polygon.createRegularPolygon(origin,banjing,sides);
	var cercleVector = new SuperMap.Feature.Vector(polygon);
	vectorLayer.addFeatures(cercleVector);	
}

function getLabel(allcar) {
	//创建一个矩形标签
	//var carStr = allcar[0].carNumber+""+allcar[0].exitTime;
	for(var i=0; i< allcar.length;i++){
		var geoText = new SuperMap.Geometry.GeoText(allcar[i].jingdu, allcar[i].weidu,allcar[i].carNumber);
		var geotextFeature = new SuperMap.Feature.Vector(geoText);
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
	gridArrayData=[];
	$("#jqGrid").jqGrid('clearGridData');
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
		height : 375,
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
			$("#jqGrid").closest(".ui-jqgrid-bdiv").css({
				"overflow-x" : "hidden"
			});
		}
	});
}


$(window).load(function(){
	vm.query();
});
