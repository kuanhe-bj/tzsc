
var map, markerLayer,vectorLayer, layer, infowin = null, LayerNames = {}, latitude = [], 
	longitude = [], cph1 , cph2 , time1 = [], time2 = [], points = [], marker = [],
	animatorLayer,animatorLayer2,data;
var styleLine = {
		strokeColor : "black",
		strokeWidth : 3,
		fill : false
	}, styleCar1 = {
		externalGraphic : "../theme/images/闪烁.gif",
		allowRotate : true,
		graphicWidth : 32,
		graphicHeight : 32
	}, styleCar2 = {
		externalGraphic : "../theme/images/闪烁1.gif",
		allowRotate : true,
		graphicWidth : 32,
		graphicHeight : 32
	},
	url = '${map.url}';
window.onload = function() {
	data = GetRequest();
	
	document.getElementById('cph11').innerHTML=data.c1List.split(",")[0];
	document.getElementById('cph22').innerHTML=data.c2List.split(",")[0];
	// 创建地图控件
	map = new SuperMap.Map('xmap');
	
	map.addControl(new SuperMap.Control.Navigation());
	// map.addControl(new SuperMap.Control.ZoomBox());

	// 创建分块动态REST图层，该图层显示iserver 7C 服务发布的地�
	// 其中“world”为图层名称，url图层的服务地址，{transparent: true}设置到url的可选参数
	layer = new SuperMap.Layer.TiledDynamicRESTLayer("tongzhou", url, null, {
		maxResolution : "auto"
	});
	layer.events.on({
		"layerInitialized" : addLayer
	});
	// 增加轨迹
	vectorLayer = new SuperMap.Layer.Vector("VectorLayer",{
		styleMap : new SuperMap.StyleMap({
			"default" : styleLine
		})
	});

	// 标记图层上添加标记
	var size = new SuperMap.Size(21, 25);
	var offset = new SuperMap.Pixel(-(size.w / 2), -size.h);
	var icon = new SuperMap.Icon('../theme/images/marker.png', size, offset);
	markerLayer = new SuperMap.Layer.Markers("Markers");
	//初始化动态图层start
	animatorLayer = new SuperMap.Layer.AnimatorVector("Car1", {
		styleMap : new SuperMap.StyleMap({
			"default" : styleCar1
		})
	}, {
		//设置速度为每帧播放0.005小时的数据
		speed : 0.003,
		//开始时间为0晨
		startTime : 0.15,
		//结束时间设置为最后运行结束的汽车结束时间
		endTime : 1
	});
	animatorLayer2 = new SuperMap.Layer.AnimatorVector("Car2", {
		styleMap : new SuperMap.StyleMap({
			"default" : styleCar2
		})
	}, {
		speed : 0.003,
		startTime : 0,
		endTime : 1
	});
	//初始化动态图层end	
}
function addLayer() {
	// 将Layer图层加载到Map对象�
	map.addLayers([ layer, vectorLayer, animatorLayer, animatorLayer2,markerLayer ]);

	// 出图，map.setCenter函数显示地图
	map.setCenter(new SuperMap.LonLat(116.708, 39.840), 11, false, false);
	guiji();
}
// 对url参数进行解析
function GetRequest() {
	var urlstr = decodeURI(decodeURI(location.search)); // 获取url"符后的字
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

// 调用此方法发绘制轨迹
function guiji() {
	clear();
	//var data = GetRequest();
	var str1 = data.wList.split(",");
	var str2 = data.jList.split(",");
	var str3 = data.c1List.split(",");
	var str4 = data.c2List.split(",");
	var str5 = data.p1List.split(",");
	var str6 = data.p2List.split(",");
	for (var i in str1) {
		longitude[i] = str1[i];
		latitude[i] = str2[i];
		cph1 = str3[i];
		cph2 = str4[i];
		time1[i] = str5[i];
		time2[i] = str6[i];
		points[i] = new SuperMap.Geometry.Point(latitude[i], longitude[i]);
		
		marker[i] = new SuperMap.Marker(new SuperMap.LonLat(latitude[i],
				longitude[i]));
		markerLayer.addMarker(marker[i]);
	
		marker[i].events.on({
			   "click":openInfoWin,
			   "scope": marker[i]
			});
	}

	var line = new SuperMap.Geometry.LineString(points);
	var lineVector = new SuperMap.Feature.Vector(line);
	vectorLayer.addFeatures(lineVector);
	getAnimator();
	
	function openInfoWin() {
		closeInfoWin();
		var j=-1;
		for (var i = 0; i < marker.length; i++) {
			if(marker[i]==this){
				j=i;
			}
		}
		var contentHTML = "<div style=\'font-size:.8em; opacity: 0.8; overflow-y:hidden;\'>";
		contentHTML += "<div>" + cph1+":" + time1[j]+ "</div>";
		contentHTML += "<div>" + cph2+":" + time2[j]+ "</div></div>";
		var popup = new SuperMap.Popup.FramedCloud("popwin",
				new SuperMap.LonLat(latitude[j], longitude[j]), null,
				contentHTML, null, true);
		infowin = popup;
		map.addPopup(popup);
		
	}
}
//加载动态图
function getAnimator() {
	stopAnimator();
	var findPathEventArgs = [],findPathEventArgs2 = [];
	//构建节点数据
	for (var i = 0; i < points.length; i++) {
		findPathEventArgs[i] = new SuperMap.Feature.Vector(new SuperMap.Geometry.Point(
				points[i].x, points[i].y),
				{
					FEATUREID : "point",//设置要素的id
					TIME : i
				});
		findPathEventArgs2[i] = new SuperMap.Feature.Vector(new SuperMap.Geometry.Point(
				points[i].x, points[i].y),
				{
					FEATUREID : "point",//设置要素的id
					TIME : i
				});
	}
	var distance = 0;
	for (var k = 0, len = points.length ; k < len; k++) {
		if (points[k + 1]) {
			//获取坐标点
			var nextPoint = points[k + 1];
			var dx = nextPoint.x - points[k].x;
			var dy = nextPoint.y - points[k].y;
			//计算两个坐标点之间的距离
			distance += Math.pow((dx * dx + dy * dy), 0.5);

			//car.stop = false;
		} else {
			//car.stop = true;
		}
	}
	//计算汽车行驶速度parseFloat("1234");
	var speed = animatorLayer.animator.getSpeed();
	var endTime = distance / speed;
	var moves = Math.abs(distance / speed);

	//给animatorLayer图层添加数据	
	animatorLayer.animator.setEndTime(endTime );
	animatorLayer2.animator.setEndTime(endTime );
	animatorLayer.addFeatures(findPathEventArgs);
	animatorLayer2.addFeatures(findPathEventArgs2);

	animatorLayer.animator.start();
	animatorLayer2.animator.start();
}
function clear() {
	markerLayer.clearMarkers();
	vectorLayer.removeAllFeatures();
	animatorLayer.removeAllFeatures();
	animatorLayer2.removeAllFeatures();
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
//开始播放动画
function startAnimator() {
	animatorLayer.animator.start();
	animatorLayer2.animator.start();
}
//暂停播放动画
function pauseAnimator() {
	animatorLayer.animator.pause();
}
//停止播放动画
function stopAnimator() {
	animatorLayer.animator.stop();
	animatorLayer2.animator.stop();
}
