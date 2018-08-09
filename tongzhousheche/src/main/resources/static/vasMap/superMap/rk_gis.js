var map;
var drawPoint;
var GridPointLayer;
var GridResultLayer;
//图层枚举
var LayerNames = {};

//通州外网地址
var dataurl = "";
//var url = "http://11.33.29.35:8090/iserver/services/map-ugcv5-TongzhouBE/rest/maps/TongzhouBE";
var url = "http://114.251.53.254:8090/iserver/services/map-TZ_MapWorkSpace/rest/maps/TZMap";
var imgurl = "";

var style = {
    strokeColor: "#304DBE",
    strokeWidth: 1,
    pointerEvents: "visiblePainted",
    fillColor: "#304DBE",
    fillOpacity: 0.8
};
//marker 大小
var markerzie = 12;
//标识
var isFlag = false;
var isAllFlag = false;


$(function () {
    //----------------------加载底图----------------------------------
    //创建地图控件
    map = new SuperMap.Map('xmap', { controls: [] });
    map.addControl(new SuperMap.Control.MousePosition({ div: $('.coord_content')[0], emptyString: "0,0" }));
    map.addControl(new SuperMap.Control.ScaleLine({ div: $('.map_scal')[0] }));
    map.addControl(new SuperMap.Control.Navigation({ id: "TNav" }));
    map.addControl(new SuperMap.Control.ZoomBox({ id: "TZoom" }));

    map.events.on({ "zoomend": ZoomChang });
    //统一图层名称
    LayerNames.SL = "通州矢量地图";
    LayerNames.IMG = "通州影像地图";
    LayerNames.CAR = "车辆卡口";
    LayerNames.PERSON = "人脸识别";
    LayerNames.QJSXJ = "全景摄像机";
    LayerNames.WLGQ = "网络固枪";
    LayerNames.WLYT = "网络云台";
    LayerNames.WFGQ = "WIFI固枪";
    LayerNames.SDIGD = "SDI高点";
    LayerNames.SDIYT = "SDI云台";
    LayerNames.CGQ = "超高清";
    LayerNames.ZYKK = "专业卡口";

    //设置比例尺
    var ScalesNum = new Array(
        0.002,
        0.001,
        0.0005,
        0.0002,
        0.0001,
        0.00004,
        0.00002,
        0.00001,
        0.000004
    );
    //设置地图初始化参数
    var opts = {
        maxExtent: new SuperMap.Bounds(-180.0, -85.05, 180.0, 85.05),
        maxResolution: "auto",
        projection: 'EPSG:4326',
        scales: ScalesNum,
        bufferImgCount: 0
    };
    var imgopts = {
        useCanvas: true
    };
    //加载图层
    var layer = new SuperMap.Layer.TiledDynamicRESTLayer(LayerNames.SL, url, { cacheEnabled: true, redirect: true }, opts);
    var imglayer = new SuperMap.Layer.TiledDynamicRESTLayer(LayerNames.IMG, imgurl, { cacheEnabled: true, redirect: true }, imgopts);
    imglayer.isBaseLayer = true;
    layer.events.on({ "layerInitialized": addLayer });
  
    //添加图层
    function addLayer() {
        //将Layer图层加载到Map对象上
        map.addLayer(layer);
        map.addLayer(imglayer);

        //出图，map.setCenter函数显示地图
        map.setCenter(new SuperMap.LonLat(116.708, 39.840), 11, false, false);
    }
    getMakersize();
    function ZoomChang() {
        getMakersize();
        if (isFlag) {
            chanageZoomLayer(LayerNames.CAR, markerzie);
            chanageZoomLayer(LayerNames.PERSON, markerzie);
            chanageZoomLayer(LayerNames.QJSXJ, markerzie);
            chanageZoomLayer(LayerNames.WLGQ, markerzie);
            chanageZoomLayer(LayerNames.WLYT, markerzie);
            chanageZoomLayer(LayerNames.WFGQ, markerzie);
            chanageZoomLayer(LayerNames.SDIGD, markerzie);
            chanageZoomLayer(LayerNames.SDIYT, markerzie);
            chanageZoomLayer(LayerNames.CGQ, markerzie);
            chanageZoomLayer(LayerNames.ZYKK, markerzie);
        }
    }

    //----------------------加载覆盖图层----------------------------------
    //车辆卡口
    var childLayer = new SuperMap.Layer.Markers(LayerNames.CAR);
    map.addLayer(childLayer);
    //人脸识别
    var childLayer = new SuperMap.Layer.Markers(LayerNames.PERSON);
    map.addLayer(childLayer);
    //全景摄像机
    var childLayer = new SuperMap.Layer.Markers(LayerNames.QJSXJ);
    map.addLayer(childLayer);
    //网络固枪
    var childLayer = new SuperMap.Layer.Markers(LayerNames.WLGQ);
    map.addLayer(childLayer);
    //网络云台
    var childLayer = new SuperMap.Layer.Markers(LayerNames.WLYT);
    map.addLayer(childLayer);
    //WIFI固枪
    var childLayer = new SuperMap.Layer.Markers(LayerNames.WFGQ);
    map.addLayer(childLayer);
    //SDI高点
    var childLayer = new SuperMap.Layer.Markers(LayerNames.SDIGD);
    map.addLayer(childLayer);
    //SDI云台
    var childLayer = new SuperMap.Layer.Markers(LayerNames.SDIYT);
    map.addLayer(childLayer);
    //超高清
    var childLayer = new SuperMap.Layer.Markers(LayerNames.CGQ);
    map.addLayer(childLayer);
    //专业卡口
    var childLayer = new SuperMap.Layer.Markers(LayerNames.ZYKK);
    map.addLayer(childLayer);

    //加载车辆开口图层
    //chanageLayer(LayerNames.CAR, markerzie);

    //------------------------------功能按钮-----------------------------
    //打开关闭按钮组
    function openOrClose($child, $mark) {
        var cstatus = $child.css('display');

        if (cstatus == 'none') {
            $child.css('display', 'block');
            $mark.attr('src', 'Scripts/mapResource/theme/images/up_small.png');
        } else {
            $child.css('display', 'none');
            $mark.attr('src', 'Scripts/mapResource/theme/images/down_small.png');
        }
    }
    //按钮hover状态
    function buttonimgHover($this, overpic, outpic, baseurl) {
        if (!baseurl || baseurl != null || baseurl != "") {
            baseurl = 'Scripts/mapResource/theme/images/';
        }
        if (baseurl.lastIndexOf('/') + 1 != baseurl.length) {
            baseurl = baseurl + '/';
        }
        $this.hover(function () {
            $this.find('img').attr('src', baseurl + overpic);
        }, function () {
            $this.find('img').attr('src', baseurl + outpic);
        });
        return $this;
    }
    //车辆卡口
    buttonimgHover($('#car'), 'car24.png', 'car24.png').bind('click', function () { chanageLayer(LayerNames.CAR, markerzie); });
    //人脸识别
    buttonimgHover($('#person'), 'person24.png', 'person24.png').bind('click', function () { chanageLayer(LayerNames.PERSON, markerzie); });
    //网络固枪
    buttonimgHover($('#wlgq'), 'wlgq24.png', 'wlgq24.png').bind('click', function () { chanageLayer(LayerNames.WLGQ, markerzie); });
    //网络云台
    buttonimgHover($('#wlyt'), 'wlyt24.png', 'wlyt24.png').bind('click', function () { chanageLayer(LayerNames.WLYT, markerzie); });
    //WIFI固枪
    buttonimgHover($('#wfgq'), 'wifigq24.png', 'wifigq24.png').bind('click', function () { chanageLayer(LayerNames.WFGQ, markerzie); });
    //SDI高点
    buttonimgHover($('#sdigd'), 'sdigd24.png', 'sdigd24.png').bind('click', function () { chanageLayer(LayerNames.SDIGD, markerzie); });
    //SDI云台
    buttonimgHover($('#sdiyt'), 'sdiyt24.png', 'sdiyt24.png').bind('click', function () { chanageLayer(LayerNames.SDIYT, markerzie); });
    //超高清
    buttonimgHover($('#cgq'), 'cgq24.png', 'cgq24.png').bind('click', function () { chanageLayer(LayerNames.CGQ, markerzie); });
    //全景摄像机
    buttonimgHover($('#qj'), 'qj24.png', 'qj24.png').bind('click', function () { chanageLayer(LayerNames.QJSXJ, markerzie); });
    //专业卡口
    buttonimgHover($('#zykk'), 'monitor.png', 'monitor24.png').bind('click', function () { chanageLayer(LayerNames.ZYKK, markerzie); });

    //全部
    buttonimgHover($('#all'), 'jcy.png', 'jcy_click.png').bind('click', function () { LoadAllLayer(); });

    //------------------------------底图组按钮状态-----------------------------
    //当前底图小图标
    //$('#base_map').hover(base_current_hover_over, base_current_hover_out);
    //切换矢量图
    $('#vectorMap').click(function () {
        map.setMapType(BMAP_NORMAL_MAP);
        $('#currentMap').removeClass('img_map');
        $('#currentMap').addClass('vector_map');
        $('#currentMap span').text("矢量");
    }).hover(base_hover_over, base_hover_out);
    //切换影像图
    $('#imgMap').bind('click', function () {
        map.setMapType(BMAP_HYBRID_MAP);
        $('#currentMap').removeClass('vector_map');
        $('#currentMap').addClass('img_map');
        $('#currentMap span').text("影像");
    }).hover(base_hover_over, base_hover_out);

    function base_hover_over() {
        $(this).addClass('basemap_a_hover');
        $(this).find('span').addClass('basemap_span_hover');
    }

    function base_hover_out() {
        $(this).removeClass('basemap_a_hover');
        $(this).find('span').removeClass('basemap_span_hover');
    }

    function base_current_hover_over() {
        $('#imgMap').parent('div').animate({ 'margin-left': '0px' }, 'normal');
        $('#vectorMap').parent('div').animate({ 'margin-left': '112px' }, 'normal');
    }

    function base_current_hover_out() {
        $('#imgMap').parent('div').animate({ 'margin-left': '-112px' }, 'normal');
        $('#vectorMap').parent('div').animate({ 'margin-left': '0px' }, 'normal');
    }

    //设置hover效果
    $('#layer_selected').mouseenter(function () {
        $('.layer_content').css('display', 'block');
        $('.layer_title img').attr('src', 'Scripts/mapResource/theme/images/up_trigon.png');
    }).mouseleave(function () {
        //延时关闭选项窗口，提高容错性
        cout = setTimeout("$('.layer_content').css('display', 'none');$('.layer_title img').attr('src', 'Scripts/mapResource/theme/images/down_trigon.png');", 400);
    });
    //设置选择值
    $('#layer_selected .layer_content li').click(function () {
        $(this).first().parents('div').find('#layer_selected .layer_title span').text($(this).text());
        $(this).first().parents('div').find('#layer_selected .layer_title span').text($(this).text()).attr('cvalue', '0');
        $(this).first().parents('div').find('#layer_selected .layer_title span').text($(this).text()).attr('ckind', '0');
        $('#search_content').attr("placeholder", "请输入查询内容");
    }).hover(function () {
        $(this).find('>a').css('color', 'white');
        $(this).css({ 'background': '#2EC7E5', 'cursor': 'pointer' });
    }, function () {
        $(this).find('>a').css('color', 'black');
        $(this).css({ 'background': 'white' });
    });
    //解除有子选项菜单的click事件并重新设置hover事件
    $('.search_kind').each(function () {
        $(this).unbind('click').unbind('hover').hover(function () {
            $(this).find('>a').css('color', 'white');
            $(this).css({ 'background': '#2EC7E5', 'cursor': 'pointer' });
            $(this).children('.search_kind_child').css('display', 'block');
        }, function () {
            $(this).find('>a').css('color', 'black');
            $(this).css({ 'background': 'white' });
            $(this).children('.search_kind_child').css('display', 'none');
        });
    });
    //重新设置监测源子项事件
    $('.source_search_child>li').click(function () {
        $(this).first().parents('div').find('#layer_selected .layer_title span').text($(this).text()).attr('ckind', $(this).attr('ckind'));
        $(this).first().parents('div').find('#layer_selected .layer_title span').text($(this).text()).attr('cvalue', '0');
    });
    //展开结果窗体
    $('.result_open').click(function () {
        $(this).animate({ left: $(this).css('left') != '0px' ? '0px' : '250px' }, 100);
        $('.result_container').animate({ width: $(this).css('left') != '0px' ? '0px' : '250px' }, 100);
        $('.map_container').animate({ left: $(this).css('left') != '0px' ? '0px' : '250px' }, 100);
        $(this).find('img').attr('src', $(this).css('left') != '0px' ? 'Scripts/mapResource/theme/images/right_small.png' : 'Scripts/mapResource/theme/images/left_small.png')
    });
});

//-----------------------根据缩放比例尺确定marker图标大小--------------------------
function getMakersize() {
    var zmNumber = map.getZoom();
    if (zmNumber < 4) {
        if (markerzie == 12) {
            isFlag = false;
        }
        else {
            isFlag = true;
            markerzie = 12;
        }
    }
    else if (zmNumber < 7) {
        if (markerzie == 24) {
            isFlag = false;
        }
        else {
            isFlag = true;
            markerzie = 24;
        }
    }
    else {
        if (markerzie == 32) {
            isFlag = false;
        }
        else {
            isFlag = true;
            markerzie = 32;
        }
    }
}

//------------------------公共------------------------
//关闭窗口
function popclose(controlID) {
    var ps = map.popups;
    for (var i = 0; i < map.popups.length; i++) {
        var cur = map.popups[i];
        if (cur.id == controlID) {
            map.removePopup(cur);
            return;
        }
    }
}

function LoadAllLayer() {
	var isvisl = true;
	if(isAllFlag == true)
	{
		isvisl = false;
		isAllFlag = false;
	}
	else
	{
		isAllFlag = true;
	}
    chanageALLLayer(LayerNames.CAR, markerzie,isvisl);
    chanageALLLayer(LayerNames.PERSON, markerzie,isvisl);
    chanageALLLayer(LayerNames.WLGQ, markerzie,isvisl);
    chanageALLLayer(LayerNames.WLYT, markerzie,isvisl);
    chanageALLLayer(LayerNames.SDIGD, markerzie,isvisl);
    chanageALLLayer(LayerNames.SDIYT, markerzie,isvisl);
    chanageALLLayer(LayerNames.QJSXJ, markerzie,isvisl);
    chanageALLLayer(LayerNames.CGQ, markerzie,isvisl);
    chanageALLLayer(LayerNames.ZYKK, markerzie,isvisl);
    chanageALLLayer(LayerNames.WFGQ, markerzie,isvisl);
}
//图层切换
function chanageLayer(layername, mksize) {
    var clayer = map.getLayersByName(layername)[0];

    if (clayer && clayer != null && ((clayer.features && clayer.features.length > 0) || (clayer.markers && clayer.markers.length > 0))) {
        clayer.setVisibility(clayer.visibility ? false : true);
    }
    else {
        switch (layername) {
            //车辆卡口
            case LayerNames.CAR:
                addCarPointsLayer(mksize);
                break;
            //人脸识别
            case LayerNames.PERSON:
                addPersonPointsLayer(mksize);
                break;
            //网络固枪
            case LayerNames.WLGQ:
                addWLGQPointsLayer(mksize);
                break;
            //网络云台
            case LayerNames.WLYT:
                addWLYTPointsLayer(mksize);
                break;
            //WIFI固枪
            case LayerNames.WFGQ:
                addWFGQPointsLayer(mksize);
                break;
            //SDI高点
            case LayerNames.SDIGD:
                addSDIGDPointsLayer(mksize);
                break;
            //SDI云台
            case LayerNames.SDIYT:
                addSDIYTPointsLayer(mksize);
                break;
            //超高清
            case LayerNames.CGQ:
                addCGQPointsLayer(mksize);
                break;
            //全景摄像机
            case LayerNames.QJSXJ:
                addQJPointsLayer(mksize);
                break;
            //专业卡口
            case LayerNames.ZYKK:
                addZYKKPointsLayer(mksize);
                break;
            //默认车辆卡口    
            default:
                addCarPointsLayer(mksize);
        }
    }
}

//图层切换
function chanageZoomLayer(layername, mksize) {
    var clayer = map.getLayersByName(layername)[0];

    if (clayer && clayer != null && ((clayer.features && clayer.features.length > 0) || (clayer.markers && clayer.markers.length > 0))) {
        clayer.clearMarkers();
        if (clayer.visibility == true) {
            switch (layername) {
                //车辆卡口
                case LayerNames.CAR:
                    addCarPointsLayer(mksize);
                    break;
                //人脸识别
                case LayerNames.PERSON:
                    addPersonPointsLayer(mksize);
                    break;
                //网络固枪
                case LayerNames.WLGQ:
                    addWLGQPointsLayer(mksize);
                    break;
                //网络云台
                case LayerNames.WLYT:
                    addWLYTPointsLayer(mksize);
                    break;
                //WIFI固枪
                case LayerNames.WFGQ:
                    addWFGQPointsLayer(mksize);
                    break;
                //SDI高点
                case LayerNames.SDIGD:
                    addSDIGDPointsLayer(mksize);
                    break;
                //SDI云台
                case LayerNames.SDIYT:
                    addSDIYTPointsLayer(mksize);
                    break;
                //超高清
                case LayerNames.CGQ:
                    addCGQPointsLayer(mksize);
                    break;
                //全景摄像机
                case LayerNames.QJSXJ:
                    addQJPointsLayer(mksize);
                    break;
                //专业卡口
                case LayerNames.ZYKK:
                    addZYKKPointsLayer(mksize);
                    break;
                //默认车辆卡口    
                default:
                    addCarPointsLayer(mksize);
            }
        }
    }
}

//图层切换
function chanageALLLayer(layername, mksize,isvisibility) {
    var clayer = map.getLayersByName(layername)[0];
	var isvis = false;
	if(isvisibility == false)
	{
		isvis = true;
	}

    if (clayer && clayer != null && ((clayer.features && clayer.features.length > 0) || (clayer.markers && clayer.markers.length > 0))) {
		   if (clayer.visibility == isvis) {
              clayer.setVisibility(isvisibility);
            }
    }
    else {
        switch (layername) {
            //车辆卡口
            case LayerNames.CAR:
                addCarPointsLayer(mksize);
                break;
            //人脸识别
            case LayerNames.PERSON:
                addPersonPointsLayer(mksize);
                break;
            //网络固枪
            case LayerNames.WLGQ:
                addWLGQPointsLayer(mksize);
                break;
            //网络云台
            case LayerNames.WLYT:
                addWLYTPointsLayer(mksize);
                break;
            //WIFI固枪
            case LayerNames.WFGQ:
                addWFGQPointsLayer(mksize);
                break;
            //SDI高点
            case LayerNames.SDIGD:
                addSDIGDPointsLayer(mksize);
                break;
            //SDI云台
            case LayerNames.SDIYT:
                addSDIYTPointsLayer(mksize);
                break;
            //超高清
            case LayerNames.CGQ:
                addCGQPointsLayer(mksize);
                break;
            //全景摄像机
            case LayerNames.QJSXJ:
                addQJPointsLayer(mksize);
                break;
            //专业卡口
            case LayerNames.ZYKK:
                addZYKKPointsLayer(mksize);
                break;
            //默认车辆卡口    
            default:
                addCarPointsLayer(mksize);
        }
    }
}
//鼠标移出标记后自动关闭弹出窗口
function mouseOut() {
    if (this.Name) {
        popclose(this.Name + "_over");
    }
    else if (this.NAME) {
        popclose(this.NAME + "_over");
    }
}
//关闭所有弹出窗
function closeAllpop() {
    while (map.popups.length > 0) {
        map.removePopup(map.popups[0]);
    }
}
//查询失败
function analysFailed(e) {
    alert(e.error.errorMsg);
}

function remove_overlay() {
    map.clearOverlays();
    map.addOverlay(ply);  //添加覆盖物
    //map.centerAndZoom(centrpoint,zoomGrade); 
}


//------------------------车辆卡口------------------------
//车辆卡口
function addCarPointsLayer(mksize) {

    var markers = map.getLayersByName(LayerNames.CAR)[0];
    //标记图层上添加标记
    var size = new SuperMap.Size(mksize, mksize);
    var offset = new SuperMap.Pixel(-(size.w / 2), -(size.h / 2));
    for (var i = 0; i < CarPoints.length; i++) {
        var myIcon = new SuperMap.Icon("Scripts/mapResource/theme/images/car.png", size, offset);
        var marker = new SuperMap.Marker(new SuperMap.LonLat(CarPoints[i][2], CarPoints[i][3]), myIcon);
        marker.Name = CarPoints[i][0];
        marker.addr = CarPoints[i][1];
        marker.events.on({ 'scope': marker, 'mouseover': carover, 'mouseout': mouseOut });
        markers.addMarker(marker);
    }
}
//鼠标悬停
function carover() {
    var lonlat = this.lonlat;
    var contentHtml = '<table> <tr><td align="right">类&nbsp;&nbsp;&nbsp;型：</td><td>' + this.Name + '</td></tr><tr><td align="right">地&nbsp;&nbsp;&nbsp;址：</td><td>' + this.addr + '</td></tr></table>';
    var gaspopup = new SuperMap.Popup.FramedCloud(this.Name + "_over", new SuperMap.LonLat(lonlat.lon, lonlat.lat), new SuperMap.Size(100, 50), contentHtml, null, false);
    map.addPopup(gaspopup);
}

//------------------------网络固枪------------------------
//网络固枪
function addWLGQPointsLayer(mksize) {
    var markers = map.getLayersByName(LayerNames.WLGQ)[0];
    //标记图层上添加标记
    var size = new SuperMap.Size(mksize, mksize);
    var offset = new SuperMap.Pixel(-(size.w / 2), -(size.h / 2));
    for (var i = 0; i < WLGQPoints.length; i++) {
        var myIcon = new SuperMap.Icon("Scripts/mapResource/theme/images/wlgq.png", size, offset);
        var marker = new SuperMap.Marker(new SuperMap.LonLat(WLGQPoints[i][2], WLGQPoints[i][3]), myIcon);
        marker.Name = WLGQPoints[i][0];
        marker.addr = WLGQPoints[i][1];
        marker.events.on({ 'scope': marker, 'mouseover': wlgqover, 'mouseout': mouseOut });
        markers.addMarker(marker);
    }
}

//鼠标悬停
function wlgqover() {
    var lonlat = this.lonlat;
    var contentHtml = '<table> <tr><td align="right">类&nbsp;&nbsp;&nbsp;型：</td><td>' + this.Name + '</td></tr><tr><td align="right">地&nbsp;&nbsp;&nbsp;址：</td><td>' + this.addr + '</td></tr></table>';
    var gaspopup = new SuperMap.Popup.FramedCloud(this.Name + "_over", new SuperMap.LonLat(lonlat.lon, lonlat.lat), new SuperMap.Size(100, 50), contentHtml, null, false);
    map.addPopup(gaspopup);
}

//------------------------网络云台------------------------
//网络云台
function addWLYTPointsLayer(mksize) {

    var markers = map.getLayersByName(LayerNames.WLYT)[0];
    //标记图层上添加标记
    var size = new SuperMap.Size(mksize, mksize);
    var offset = new SuperMap.Pixel(-(size.w / 2), -(size.h / 2));
    for (var i = 0; i < WLYTPoints.length; i++) {
        var myIcon = new SuperMap.Icon("Scripts/mapResource/theme/images/wlyt.png", size, offset);
        var marker = new SuperMap.Marker(new SuperMap.LonLat(WLYTPoints[i][2], WLYTPoints[i][3]), myIcon);
        marker.Name = WLYTPoints[i][0];
        marker.addr = WLYTPoints[i][1];
        marker.events.on({ 'scope': marker, 'mouseover': wlytover, 'mouseout': mouseOut });
        markers.addMarker(marker);
    }
}

//鼠标悬停
function wlytover() {
    var lonlat = this.lonlat;
    var contentHtml = '<table> <tr><td align="right">类&nbsp;&nbsp;&nbsp;型：</td><td>' + this.Name + '</td></tr><tr><td align="right">地&nbsp;&nbsp;&nbsp;址：</td><td>' + this.addr + '</td></tr></table>';
    var gaspopup = new SuperMap.Popup.FramedCloud(this.Name + "_over", new SuperMap.LonLat(lonlat.lon, lonlat.lat), new SuperMap.Size(100, 50), contentHtml, null, false);
    map.addPopup(gaspopup);
}

//------------------------人脸识别------------------------
//人脸识别
function addPersonPointsLayer(mksize) {

    var markers = map.getLayersByName(LayerNames.PERSON)[0];
    //标记图层上添加标记
    var size = new SuperMap.Size(mksize, mksize);
    var offset = new SuperMap.Pixel(-(size.w / 2), -(size.h / 2));
    for (var i = 0; i < PersonPoints.length; i++) {
        var myIcon = new SuperMap.Icon("Scripts/mapResource/theme/images/person.png", size, offset);
        var marker = new SuperMap.Marker(new SuperMap.LonLat(PersonPoints[i][2], PersonPoints[i][3]), myIcon);
        marker.Name = PersonPoints[i][0];
        marker.addr = PersonPoints[i][1];
        marker.events.on({ 'scope': marker, 'mouseover': persontover, 'mouseout': mouseOut });
        markers.addMarker(marker);
    }
}

//鼠标悬停
function persontover() {
    var lonlat = this.lonlat;
    var contentHtml = '<table> <tr><td align="right">类&nbsp;&nbsp;&nbsp;型：</td><td>' + this.Name + '</td></tr><tr><td align="right">地&nbsp;&nbsp;&nbsp;址：</td><td>' + this.addr + '</td></tr></table>';
    var gaspopup = new SuperMap.Popup.FramedCloud(this.Name + "_over", new SuperMap.LonLat(lonlat.lon, lonlat.lat), new SuperMap.Size(100, 50), contentHtml, null, false);
    map.addPopup(gaspopup);
}
//------------------------WIFI固枪------------------------
//WIFI固枪
function addWFGQPointsLayer(mksize) {

    var markers = map.getLayersByName(LayerNames.WFGQ)[0];
    //标记图层上添加标记
    var size = new SuperMap.Size(mksize, mksize);
    var offset = new SuperMap.Pixel(-(size.w / 2), -(size.h / 2));
    for (var i = 0; i < WFGQPoints.length; i++) {
        var myIcon = new SuperMap.Icon("Scripts/mapResource/theme/images/wifigq.png", size, offset);
        var marker = new SuperMap.Marker(new SuperMap.LonLat(WFGQPoints[i][2], WFGQPoints[i][3]), myIcon);
        marker.Name = WFGQPoints[i][0];
        marker.addr = WFGQPoints[i][1];
        marker.events.on({ 'scope': marker, 'mouseover': wfgqover, 'mouseout': mouseOut });
        markers.addMarker(marker);
    }
}

//鼠标悬停
function wfgqover() {
    var lonlat = this.lonlat;
    var contentHtml = '<table> <tr><td align="right">类&nbsp;&nbsp;&nbsp;型：</td><td>' + this.Name + '</td></tr><tr><td align="right">地&nbsp;&nbsp;&nbsp;址：</td><td>' + this.addr + '</td></tr></table>';
    var gaspopup = new SuperMap.Popup.FramedCloud(this.Name + "_over", new SuperMap.LonLat(lonlat.lon, lonlat.lat), new SuperMap.Size(100, 50), contentHtml, null, false);
    map.addPopup(gaspopup);
}
//------------------------SDI高点------------------------
//SDI高点
function addSDIGDPointsLayer(mksize) {

    var markers = map.getLayersByName(LayerNames.SDIGD)[0];
    //标记图层上添加标记
    var size = new SuperMap.Size(mksize, mksize);
    var offset = new SuperMap.Pixel(-(size.w / 2), -(size.h / 2));
    for (var i = 0; i < SDIGDPoints.length; i++) {
        var myIcon = new SuperMap.Icon("Scripts/mapResource/theme/images/sdigd.png", size, offset);
        var marker = new SuperMap.Marker(new SuperMap.LonLat(SDIGDPoints[i][2], SDIGDPoints[i][3]), myIcon);
        marker.Name = SDIGDPoints[i][0];
        marker.addr = SDIGDPoints[i][1];
        marker.events.on({ 'scope': marker, 'mouseover': sdigdover, 'mouseout': mouseOut });
        markers.addMarker(marker);
    }
}

//鼠标悬停
function sdigdover() {
    var lonlat = this.lonlat;
    var contentHtml = '<table> <tr><td align="right">类&nbsp;&nbsp;&nbsp;型：</td><td>' + this.Name + '</td></tr><tr><td align="right">地&nbsp;&nbsp;&nbsp;址：</td><td>' + this.addr + '</td></tr></table>';
    var gaspopup = new SuperMap.Popup.FramedCloud(this.Name + "_over", new SuperMap.LonLat(lonlat.lon, lonlat.lat), new SuperMap.Size(100, 50), contentHtml, null, false);
    map.addPopup(gaspopup);
}
//------------------------SDI云台------------------------
//SDI云台
function addSDIYTPointsLayer(mksize) {
    var markers = map.getLayersByName(LayerNames.SDIYT)[0];
    //标记图层上添加标记
    var size = new SuperMap.Size(mksize, mksize);
    var offset = new SuperMap.Pixel(-(size.w / 2), -(size.h / 2));
    for (var i = 0; i < SDIYTPoints.length; i++) {
        var myIcon = new SuperMap.Icon("Scripts/mapResource/theme/images/sdiyt.png", size, offset);
        var marker = new SuperMap.Marker(new SuperMap.LonLat(SDIYTPoints[i][2], SDIYTPoints[i][3]), myIcon);
        marker.Name = SDIYTPoints[i][0];
        marker.addr = SDIYTPoints[i][1];
        marker.events.on({ 'scope': marker, 'mouseover': sdiytover, 'mouseout': mouseOut });
        markers.addMarker(marker);
    }
}
//鼠标悬停
function sdiytover() {
    var lonlat = this.lonlat;
    var contentHtml = '<table> <tr><td align="right">类&nbsp;&nbsp;&nbsp;型：</td><td>' + this.Name + '</td></tr><tr><td align="right">地&nbsp;&nbsp;&nbsp;址：</td><td>' + this.addr + '</td></tr></table>';
    var gaspopup = new SuperMap.Popup.FramedCloud(this.Name + "_over", new SuperMap.LonLat(lonlat.lon, lonlat.lat), new SuperMap.Size(100, 50), contentHtml, null, false);
    map.addPopup(gaspopup);
}

//------------------------超高清------------------------
//超高清
function addCGQPointsLayer(mksize) {

    var markers = map.getLayersByName(LayerNames.CGQ)[0];
    //标记图层上添加标记
    var size = new SuperMap.Size(mksize, mksize);
    var offset = new SuperMap.Pixel(-(size.w / 2), -(size.h / 2));
    for (var i = 0; i < CGQPoints.length; i++) {
        var myIcon = new SuperMap.Icon("Scripts/mapResource/theme/images/cgq.png", size, offset);
        var marker = new SuperMap.Marker(new SuperMap.LonLat(CGQPoints[i][2], CGQPoints[i][3]), myIcon);
        marker.Name = CGQPoints[i][0];
        marker.addr = CGQPoints[i][1];
        marker.events.on({ 'scope': marker, 'mouseover': cgqover, 'mouseout': mouseOut });
        markers.addMarker(marker);
    }
}

//鼠标悬停
function cgqover() {
    var lonlat = this.lonlat;
    var contentHtml = '<table> <tr><td align="right">类&nbsp;&nbsp;&nbsp;型：</td><td>' + this.Name + '</td></tr><tr><td align="right">地&nbsp;&nbsp;&nbsp;址：</td><td>' + this.addr + '</td></tr></table>';
    var gaspopup = new SuperMap.Popup.FramedCloud(this.Name + "_over", new SuperMap.LonLat(lonlat.lon, lonlat.lat), new SuperMap.Size(100, 50), contentHtml, null, false);
    map.addPopup(gaspopup);
}

//------------------------全景摄像机------------------------
//全景摄像机
function addQJPointsLayer(mksize) {
    var markers = map.getLayersByName(LayerNames.QJSXJ)[0];
    //标记图层上添加标记
    var size = new SuperMap.Size(mksize, mksize);
    var offset = new SuperMap.Pixel(-(size.w / 2), -(size.h / 2));
    for (var i = 0; i < QJPoint.length; i++) {
        var myIcon = new SuperMap.Icon("Scripts/mapResource/theme/images/qj.png", size, offset);
        var marker = new SuperMap.Marker(new SuperMap.LonLat(QJPoint[i][2], QJPoint[i][3]), myIcon);
        marker.Name = QJPoint[i][0];
        marker.addr = QJPoint[i][1];
        marker.events.on({ 'scope': marker, 'mouseover': qjover, 'mouseout': mouseOut });
        markers.addMarker(marker);
    }
}

//鼠标悬停
function qjover() {
    var lonlat = this.lonlat;
    var contentHtml = '<table> <tr><td align="right">类&nbsp;&nbsp;&nbsp;型：</td><td>' + this.Name + '</td></tr><tr><td align="right">地&nbsp;&nbsp;&nbsp;址：</td><td>' + this.addr + '</td></tr></table>';
    var gaspopup = new SuperMap.Popup.FramedCloud(this.Name + "_over", new SuperMap.LonLat(lonlat.lon, lonlat.lat), new SuperMap.Size(100, 50), contentHtml, null, false);
    map.addPopup(gaspopup);
}

//------------------------专业卡口------------------------
//专业卡口
function addZYKKPointsLayer(mksize) {

    var markers = map.getLayersByName(LayerNames.ZYKK)[0];
    //标记图层上添加标记
    var size = new SuperMap.Size(mksize, mksize);
    var offset = new SuperMap.Pixel(-(size.w / 2), -(size.h / 2));
    for (var i = 0; i < ZYKKPoints.length; i++) {
        var myIcon = new SuperMap.Icon("Scripts/mapResource/theme/images/monitor.png", size, offset);
        var marker = new SuperMap.Marker(new SuperMap.LonLat(ZYKKPoints[i][2], ZYKKPoints[i][3]), myIcon);
        marker.Name = ZYKKPoints[i][0];
        marker.addr = ZYKKPoints[i][1];
        marker.events.on({ 'scope': marker, 'mouseover': zykkover, 'mouseout': mouseOut });
        markers.addMarker(marker);
    }
}

//鼠标悬停
function zykkover() {
    var lonlat = this.lonlat;
    var contentHtml = '<table> <tr><td align="right">类&nbsp;&nbsp;&nbsp;型：</td><td>' + this.Name + '</td></tr><tr><td align="right">地&nbsp;&nbsp;&nbsp;址：</td><td>' + this.addr + '</td></tr></table>';
    var gaspopup = new SuperMap.Popup.FramedCloud(this.Name + "_over", new SuperMap.LonLat(lonlat.lon, lonlat.lat), new SuperMap.Size(100, 50), contentHtml, null, false);
    map.addPopup(gaspopup);
}