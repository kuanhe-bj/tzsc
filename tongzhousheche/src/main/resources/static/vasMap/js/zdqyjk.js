var map, layer,vectorLayer,markerLayer ,animatorLayer,infowin = null,LayerNames = {},latitude = [], longitude = [], 
	time = [], temp = [],marker = [],points=[],vm;
var styleLine = {
		strokeColor : "black",
		strokeWidth : 3,
		fill : false
	}, styleCar1 = {
		externalGraphic : "../theme/images/car_画板 1.png",
		allowRotate : true,
		graphicWidth : 32,
		graphicHeight : 32
	};

var qsj=null;
var hsj=null;
var cph = sessionStorage.getItem("cph");

$(function() {
	// 前后台数据交互
	vm = new Vue({
		el : '#rrapp',
		data : {
			q : {
				cph : cph,
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
			add1: function(){
				vm.display();
		    },
		    add2: function(){
				vm.display();
		    },
			display : function() {
				closeInfoWin();
				//clear();
				var qishi = fun_date(-7);
				var	jieshu = getNowFormatDate();
				//alert("请选择时间！");
				$.ajax({
					type : "POST",
					url : baseURL + "etcp/etcpGJList",
					contentType : "application/json",
					data : JSON.stringify({'qssj':qishi,'jssj':jieshu, 'cph':cph}),
					success : function(r) {
						if(r.page!=null && r.page!=""){
							for (var i = 0; i < r.page.length; i++) {
								cph = r.page[i].carNumber;
								latitude[i] = r.page[i].jingdu;
								longitude[i] = r.page[i].weidu;
								temp[i] = new SuperMap.Geometry.Point(latitude[i], longitude[i]);
								time[i] = r.page[i].enterTime;
								if(i==0){
								    var geoText = new SuperMap.Geometry.GeoText(latitude[i], longitude[i],"起点");
									var geotextFeature = new SuperMap.Feature.Vector(geoText);
									labelLayer.addFeatures(geotextFeature);
								}
								if(i==r.page.length-1){
								    var geoText = new SuperMap.Geometry.GeoText(latitude[i], longitude[i],"终点");
									var geotextFeature = new SuperMap.Feature.Vector(geoText);
									labelLayer.addFeatures(geotextFeature);
								}
								if(i>0 && i<r.page.length-1){
								marker[i] = new SuperMap.Marker(new SuperMap.LonLat(latitude[i], longitude[i]));
								markerLayer.addMarker(marker[i]);
								// marker点击事件
								marker[i].events.on({
									   "click":openInfoWin,
									   "scope": marker[i]
									});
								}
							}
						}else{
							alert("您查询的车辆没有记录");
							return;
						}
						var line = new SuperMap.Geometry.LineString(temp);
						var lineVector = new SuperMap.Feature.Vector(line);
						vectorLayer.addFeatures(lineVector);
						getAnimator();
						function openInfoWin(){
							closeInfoWin();
							if(marker.length==1){
								var contentHTML = "<div style=\'font-size:.8em; opacity: 0.8; overflow-y:hidden;\'>";
								contentHTML += "<div>"+cph+"</div>";
							    contentHTML += "<div>"+time[0]+"</div></div>";
							    var popup = new SuperMap.Popup.FramedCloud("popwin",new SuperMap.LonLat(latitude[0], longitude[0]),null,contentHTML,null,true);
							    infowin = popup;
							    map.addPopup(popup);
							    return;
							}
							var j =0;
							for(var i=0;i<marker.length;i++){
								if(marker[i]==this){
									j = i;
								}
							}
							if(j==0){
								// marker[0].setLabel("起点");
								var contentHTML = "<div style=\'font-size:.8em; opacity: 0.8; overflow-y:hidden;\'>";
							    contentHTML += "<div>  起点<br>"+time[j]+"</div></div>";
							    var popup = new SuperMap.Popup.FramedCloud("popwin",new SuperMap.LonLat(latitude[j], longitude[j]),null,contentHTML,null,true);
							    infowin = popup;
							    map.addPopup(popup);
							}
							if(j==marker.length-1){
								var contentHTML = "<div style=\'font-size:.8em; opacity: 0.8; overflow-y:hidden;\'>";
							    contentHTML += "<div>终点<br>"+time[j]+"</div></div>";
							    var popup = new SuperMap.Popup.FramedCloud("popwin",new SuperMap.LonLat(latitude[j], longitude[j]),null,contentHTML,null,true);
							    infowin = popup;
							    map.addPopup(popup);
							}
							if(j>0 && j<marker.length-1){
								var contentHTML = "<div style=\'font-size:.8em; opacity: 0.8; overflow-y:hidden;\'>";
								contentHTML += "<div>"+cph+"</div>";
							    contentHTML += "<div>"+time[j]+"</div></div>";
							    var popup = new SuperMap.Popup.FramedCloud("popwin",new SuperMap.LonLat(latitude[j], longitude[j]),null,contentHTML,null,true);
							    infowin = popup;
							    map.addPopup(popup);
							}
							// console.info(marker[i]);
							}
						}
					});
				},
				display2 : function() {
					// console.info("cph=1"+$('#cph').val());
					closeInfoWin();
					clear();
					var cph2 = $('#cph').val();
					var qishi = $('#qssj').val();
					var jieshu = $('#jssj').val();
					if(!$('#cph').val()){
						alert("请输入车牌号！");
						return;
					}
					if(!$('#qssj').val() || $('#qssj').val()==""){
						qishi = fun_date(-7);
						
					}
					if(!$('#jssj').val() || $('#jssj').val()==""){
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
						url : baseURL + "generator/scjqcheck/sckkcheck",
						contentType : "application/json",
						data : JSON.stringify({'cph': trim(cph2),'qssj':qishi,'jssj':jieshu}),
						success : function(r) {
							if(r.page!=null && r.page!=""){
								for (var i = 0; i < r.page.length; i++) {
									cph = r.page[i].carNumber;
									latitude[i] = r.page[i].jingdu;
									longitude[i] = r.page[i].weidu;
									temp[i] = new SuperMap.Geometry.Point(latitude[i], longitude[i]);
									time[i] = r.page[i].enterTime;
									if(i==0){
									    var geoText = new SuperMap.Geometry.GeoText(latitude[i], longitude[i],"起点");
										var geotextFeature = new SuperMap.Feature.Vector(geoText);
										labelLayer.addFeatures(geotextFeature);
									}
									if(i==r.page.length-1){
									    var geoText = new SuperMap.Geometry.GeoText(latitude[i], longitude[i],"终点");
										var geotextFeature = new SuperMap.Feature.Vector(geoText);
										labelLayer.addFeatures(geotextFeature);
									}
									if(i>0 && i<r.page.length-1){
									marker[i] = new SuperMap.Marker(new SuperMap.LonLat(latitude[i], longitude[i]));
									markerLayer.addMarker(marker[i]);
									// marker点击事件
									marker[i].events.on({
										   "click":openInfoWin,
										   "scope": marker[i]
										});
									}
								}
							}else{
								alert("您查询的车辆没有记录");
								return;
							}
							var line = new SuperMap.Geometry.LineString(temp);
							var lineVector = new SuperMap.Feature.Vector(line);
							vectorLayer.addFeatures(lineVector);
							getAnimator();
							function openInfoWin(){
								closeInfoWin();
								if(marker.length==1){
									var contentHTML = "<div style=\'font-size:.8em; opacity: 0.8; overflow-y:hidden;\'>";
									contentHTML += "<div>"+cph+"</div>";
								    contentHTML += "<div>"+time[0]+"</div></div>";
								    var popup = new SuperMap.Popup.FramedCloud("popwin",new SuperMap.LonLat(latitude[0], longitude[0]),null,contentHTML,null,true);
								    infowin = popup;
								    map.addPopup(popup);
								    return;
								}
								var j =0;
								for(var i=0;i<marker.length;i++){
									if(marker[i]==this){
										j = i;
									}
								}
								if(j==0){
									// marker[0].setLabel("起点");
									var contentHTML = "<div style=\'font-size:.8em; opacity: 0.8; overflow-y:hidden;\'>";
								    contentHTML += "<div>  起点<br>"+time[j]+"</div></div>";
								    var popup = new SuperMap.Popup.FramedCloud("popwin",new SuperMap.LonLat(latitude[j], longitude[j]),null,contentHTML,null,true);
								    infowin = popup;
								    map.addPopup(popup);
								}
								if(j==marker.length-1){
									var contentHTML = "<div style=\'font-size:.8em; opacity: 0.8; overflow-y:hidden;\'>";
								    contentHTML += "<div>终点<br>"+time[j]+"</div></div>";
								    var popup = new SuperMap.Popup.FramedCloud("popwin",new SuperMap.LonLat(latitude[j], longitude[j]),null,contentHTML,null,true);
								    infowin = popup;
								    map.addPopup(popup);
								}
								if(j>0 && j<marker.length-1){
									var contentHTML = "<div style=\'font-size:.8em; opacity: 0.8; overflow-y:hidden;\'>";
									contentHTML += "<div>"+cph+"</div>";
								    contentHTML += "<div>"+time[j]+"</div></div>";
								    var popup = new SuperMap.Popup.FramedCloud("popwin",new SuperMap.LonLat(latitude[j], longitude[j]),null,contentHTML,null,true);
								    infowin = popup;
								    map.addPopup(popup);
								}
								// console.info(marker[i]);
								}
							}
						});
					},
				clear : function() {
					closeInfoWin();
					clear();
				}
			}
		});
	vm.display();
    laydate.render({
        elem : '#qssj',
        type : 'datetime'
    });
    //时间选择器
    laydate.render({
        elem : '#jssj',
        type : 'datetime'
    });
});
	url = '${map.url}';
	window.onload = function() {
	// 创建地图控件
	map = new SuperMap.Map('xmap');
	map.addControl(new SuperMap.Control.Navigation());
	
	LayerName = "通州矢量地图";
	
	// 创建分块动态REST图层，该图层显示iserver 7C 服务发布的地图
	// 其中“world”为图层名称，url图层的服务地址，{transparent: true}设置到url的可选参数
	layer = new SuperMap.Layer.TiledDynamicRESTLayer(LayerName, url, 
			null, {maxResolution:"auto"});
	layer.events.on({"layerInitialized": addLayer});          
	
	// 增加轨迹图层
	vectorLayer = new SuperMap.Layer.Vector("VectorLayer", {
        renderers : [ "Canvas2" ],
        styleMap : new SuperMap.StyleMap({
			"default" : styleLine
		})
    });    
	
	// 标记图层上添加标记
	var size = new SuperMap.Size(21,25);
	var offset = new SuperMap.Pixel(-(size.w/2), -size.h);
	var icon = new SuperMap.Icon('../theme/images/marker.png',size,offset);
	markerLayer = new SuperMap.Layer.Markers( "Markers" );
	
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
	
	//初始化动态图层start
	animatorLayer = new SuperMap.Layer.AnimatorVector("Car1", {
		styleMap : new SuperMap.StyleMap({
			"default" : styleCar1
		})
	}, {
		//设置速度为每帧播放0.005小时的数据
		speed : 0.003,
		//开始时间为0晨
		startTime : 0,
		//结束时间设置为最后运行结束的汽车结束时间
		endTime : 1
	});
	//初始化动态图层end	
	
	var chepai=parent.cph;
	var qs = parent.sTime;
	var js = parent.eTime;
	
	if (chepai!=null&&chepai!="") {
		var tempcph = chepai.split("-");
		
		document.getElementById('cph').value=tempcph[1];
		document.getElementById('qssj').value=qs;
		document.getElementById('jssj').value=js;
		if(tempcph[0]==1){
			vm.add1();
		}else{
			vm.add2();
		}
	}
	 parent.change();
	}
	function addLayer() {
		// 将Layer图层加载到Map对象
		map.addLayers([layer,vectorLayer,markerLayer ,animatorLayer,labelLayer]);		
		// 出图，map.setCenter函数显示地图
		map.setCenter(new SuperMap.LonLat(116.708, 39.840), 11, false, false);
		
		//通过selectFeature控件为标签添加点击事件
		var callbacks = {
			click : function(feature) {
				var i = -1;
				if(feature.geometry.text=="起点"){
					i=0
				}else{
					i=time.length-1;
				}
				closeInfoWin();
				var contentHTML = "<div style=\'font-size:.8em; opacity: 0.8; overflow-y:hidden;\'>";
				contentHTML += "<div>"+cph+"</div>";
			    contentHTML += "<div>"+time[i]+"</div></div>";
			    var popup = new SuperMap.Popup.FramedCloud("popwin",new SuperMap.LonLat(latitude[i], longitude[i]),null,contentHTML,null,true);
			    infowin = popup;
			    map.addPopup(popup);
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
	function clear() {
		markerLayer.clearMarkers();
		vectorLayer.removeAllFeatures();
		animatorLayer.removeAllFeatures();
		labelLayer.removeAllFeatures();
	}
	function closeInfoWin(){// 关闭窗口
		if(infowin){
			try{
				infowin.hide();// 隐藏窗体对象
				infowin.destroy();// 消毁窗体对象
			}catch(e){}
		}
	}
	//加载动态图
	function getAnimator() {
		stopAnimator();
		var findPathEventArgs = [],findPathEventArgs2 = [];
		//构建节点数据
		for (var i = 0; i < temp.length; i++) {
			//console.log("temp[i]="+temp[i]);
			findPathEventArgs[i] = new SuperMap.Feature.Vector(new SuperMap.Geometry.Point(
					temp[i].x, temp[i].y),
					{
						FEATUREID : "point",//设置要素的id
						TIME : i
					});
		}
		var distance = 0;
		for (var k = 0, len = temp.length ; k < len; k++) {
			if (temp[k + 1]) {
				//获取坐标点
				var nextPoint = temp[k + 1];
				var dx = nextPoint.x - temp[k].x;
				var dy = nextPoint.y - temp[k].y;
				//计算两个坐标点之间的距离
				distance += Math.pow((dx * dx + dy * dy), 0.5);
			} 
		}
		//计算汽车行驶速度parseFloat("1234");
		var speed = animatorLayer.animator.getSpeed();
		var endTime = distance / speed;
		var moves = Math.abs(distance / speed);

		//给animatorLayer图层添加数据	
		animatorLayer.animator.setEndTime(endTime / 10);
		animatorLayer.addFeatures(findPathEventArgs);

		animatorLayer.animator.start();
	}
	

   
//开始播放动画
function startAnimator() {
	animatorLayer.animator.start();
}
//暂停播放动画
function pauseAnimator() {
	animatorLayer.animator.pause();
}
//停止播放动画
function stopAnimator() {
	animatorLayer.animator.stop();
}

//获取系统当前时间
function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 1 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
            + " " + date.getHours() + seperator2 + date.getMinutes()
            + seperator2 + date.getSeconds();
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
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 1 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var time2 = date2.getFullYear()+"-"+month+"-"+strDate
    	+" "+date2.getHours()+":"+date2.getMinutes()+":"+date2.getSeconds();
    return time2;
}
function trim(str){
	return str.replace(/\s|\xA0/g,"");   
}
//$(window).load(function(){
//	alert("请输入时间");
//});
function sj(plate){
	params={};
	params["plate"] = cph;
	//console.log(asd);
	$.ajax({
		url : baseURL + 'generator/zfyc/zdrsj',
		dataType : "json",
		type : "post",
		data : params,
		async : false,
		success : function(r) {
			console.log(r.list);
			if(r.list != null || r.list != "") {
				qsj=r.list[0];
				hsj=r.list[1];
//				console.log(qsj);
//				console.log(hsj);
			}
			//console.log(r);
		}
});
}
$(window).load(function(){
	sj(cph);
	$("#qssj").val(qsj);
	$("#jssj").val(hsj);

});