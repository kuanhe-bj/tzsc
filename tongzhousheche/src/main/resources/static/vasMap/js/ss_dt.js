//取出要显示的全部车牌号
var allcar={};
function plate(){
	var params={};
	params["jingdu"]=$("#jingdu").val();
	params["weidu"]=$("#weidu").val();
	params["count"]=$("#jl").val();
$.ajax({
	url : baseURL + 'generator/zfyc/plate',
	dataType : "json",
	data:params,
	type : "post",
	async : false,
	//data : "cph=" + cph,
	success : function(r) {		
		//console.log(r);
//		if(r.page!=null&&r.page!=""&&r.page!="undefined"){
//			
//			jingdu = r.page[0].jindu;
//			sessionStorage.setItem("jingdu", jingdu);
//		}
//		if(r.page!=null&&r.page!=""&&r.page!="undefined"){
//			
//			weidu = r.page[0].weidu;
//			sessionStorage.setItem("weidu", weidu);
//		}
		//全部车牌号
         //console.log("allcar:"+r.list);
         allcar=r.list;
         if(allcar==null&&allcar==""){
        	 alert("没有符合当前条件的车辆！");
         }
	}
});
}

//var cph=sessionStorage.getItem("cph");
// 车牌号
//sessionStorage.setItem("cph",cph);
//var qhjgsj = sessionStorage.getItem("time");
//var jhzyfw = $("#jl").val();
//console.log("半径："+jhzyfw);
// 间隔时间

// 范围


//var jingdu = null;
//var weidu = null;
var faddxz = null;
//var allcar = {};
//var allcar=[];
//console.log(cph);
//$.ajax({
//	url : baseURL + 'generator/dsj/find_jwdu',
//	dataType : "json",
//	type : "post",
//	async : false,
//	data : "cph=" + cph,
//	success : function(r) {		
//		//console.log(r);
//		if(r.page!=null&&r.page!=""&&r.page!="undefined"){
//			
//			jingdu = r.page[0].jindu;
//			sessionStorage.setItem("jingdu", jingdu);
//		}
//		if(r.page!=null&&r.page!=""&&r.page!="undefined"){
//			
//			weidu = r.page[0].weidu;
//			sessionStorage.setItem("weidu", weidu);
//		}
//
//
//	}
//});
// 车辆原点经纬度
//console.log(jingdu);
//console.log(weidu);
//if(jingdu==null||jingdu==""||weidu==null||weidu==""){
//	alert("当前涉事车辆不存在经纬度请添加经纬度");
//}
//var date = new Date();
//var year = date.getFullYear();
//var month = date.getMonth() + 1;
//var day = date.getDate();
//var hour = date.getHours();
//var minute = date.getMinutes();
//var second = date.getSeconds();
//var time = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":"
//		+ second;
//var time=sessionStorage.getItem("fakssj");
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
		},
		showList : true,
		title : null,
		role : {},

	},
	methods : {
		fh:function(){
			window.location.href= baseURL + 'modules/manager/zfyc_ss.html';
		},
		query : function() {
			if(vm.q.jhzyfw!=null&&vm.q.jhzyfw!=""&&vm.q.jingdu!=null&&
					vm.q.jingdu!=""&&vm.q.weidu!=null&&vm.q.jingdu!=""){
				
			
			//alert("后台正在拼命计算,计算速度由你实时查询计算的数据量决定,请耐心等待！");
			//var kssj = $("#kssj").val();
			//var qhjgsj = vm.q.qhjgsj;
			var jhzyfw = vm.q.jhzyfw;
			var jingdu=vm.q.jingdu;
			var weidu=vm.q.weidu;
//			var jd = jingdu;
//			var wd = weidu;
			// 间隔毫秒
			//var sjjg = qhjgsj * 60 * 60 * 1000;
			// 输入时间
//			var sj = new Date().getTime(sjjg);
//			var qm = sj - sjjg;
//			var date = new Date(qm);
//			var year = date.getFullYear();
//			var month = date.getMonth() + 1;
//			var day = date.getDate();
//			var hour = date.getHours();
//			var minute = date.getMinutes();
//			var second = date.getSeconds();
//			var qsj = year + "-" + month + "-" + day + " " + hour + ":"
//					+ minute + ":" + second;
//			var qm = sj - sjjg;
//			var date = new Date(qm);
//			var year = date.getFullYear();
//			var month = date.getMonth() + 1;
//			var day = date.getDate();
//			var hour = date.getHours();
//			var minute = date.getMinutes();
//			var second = date.getSeconds();
//			// 前时间
//			var qsj = year + "-" + month + "-" + day + " " + hour + ":"
//					+ minute + ":" + second;
//			var qm = sj - sjjg;
//			//console.log('前时间：'+qsj);
//			var hsj = sj + sjjg;
//			var date = new Date(hsj);
//			var year = date.getFullYear();
//			var month = date.getMonth() + 1;
//			var day = date.getDate();
//			var hour = date.getHours();
//			var minute = date.getMinutes();
//			var second = date.getSeconds();
//			// 后时间
//			var hsj = year + "-" + month + "-" + day + " " + (hour - 1) + ":"
//					+ minute + ":" + second;

//			var pi = {};
// 地图获取
			//pi["count"] =$("#jl").val() ;
//			pi["jind"] = jingdu;
//			pi["weid"] = weidu;
			//pi["qsj"]=qsj;
			//pi["hsj"]=hsj;
			//console.log("1111111111");
//console.log($("#jl").val());
//console.log(jingdu);
//console.log(weidu);
//console.log(qsj);
//console.log(hsj);
			 plate();
//			$.ajax({
//				url : baseURL + 'generator/dsj/map_allcar',
//				dataType : "json",
//				type : "post",
//				data : pi,
//				async : false,
//				success : function(r) {
//					// 圈内all车牌
//					for ( var i in r.page) {
//						allcar[i]=r.page[i];
//						
//					}
//					/* all车 */
//					sessionStorage.setItem("allcar:", allcar);
//				//console.log(allcar);
//					if(allcar==null||allcar==""){
//						//alert("该范围内除点击车辆("+cph+")无其他车辆，您可改变条件搜索...");
//					}
					clearElements();
					getCircle();
					getLabel(); 
//				}
//			});

		}else{
			alert("请输入全部数据！");
		}
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
				window.location.href=baseURL+"modules/generator/sc_sj.html";
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
	point = new SuperMap.Geometry.Point(vm.q.jingdu,vm.q.weidu); 
	var size = new SuperMap.Size(44, 33), 
	offset = new SuperMap.Pixel(-(size.w / 2), -size.h), 
	icon = new SuperMap.Icon("../../theme/images/marker.png", size, offset);
	marker = new SuperMap.Marker(new SuperMap.LonLat(point.x,point.y));
	
	markerLayer.addMarker(marker);
	
	var sides = 50;
	var origin = point;
	var banjing = vm.q.jhzyfw*0.01;
	//console.info("origin="+origin);
	var polygon = SuperMap.Geometry.Polygon.createRegularPolygon(origin,banjing,sides);
	//console.info("polygon="+polygon);
	var cercleVector = new SuperMap.Feature.Vector(polygon);
	vectorLayer.addFeatures(cercleVector);	
}

function getLabel() {
	//创建一个矩形标签
	//var carStr = allcar[0].carNumber+""+allcar[0].exitTime;
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
$(window).load(function(){
	//vm.query();
});