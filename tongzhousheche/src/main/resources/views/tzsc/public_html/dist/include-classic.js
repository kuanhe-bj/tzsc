(function () {
    var r = new RegExp("(^|(.*?\\/))(include-classic\.js)(\\?|$)"),
        s = document.getElementsByTagName('script'), targetScript;
    for (var i = 0; i < s.length; i++) {
        var src = s[i].getAttribute('src');
        if (src) {
            var m = src.match(r);
            if (m) {
                targetScript = s[i];
                break;
            }
        }
    }

    function inputScript(url) {
        var script = '<script type="text/javascript" src="' + url + '"><' + '/script>';
        document.writeln(script);
    }

    function inputCSS(url) {
        var css = '<link rel="stylesheet" href="' + url + '">';
        document.writeln(css);
    }

    function inArray(arr, item) {
        for (i in arr) {
            if (arr[i] == item) {
                return true;
            }
        }
        return false;
    }

    //加载类库资源文件
    function load() {
        var includes = (targetScript.getAttribute('include') || "").split(",");
        var excludes = (targetScript.getAttribute('exclude') || "").split(",");
        if (!inArray(excludes, 'iclient8c')) {
            inputScript("../../../js/SuperMap.Include.js");
        }
        if (inArray(includes, 'mapv')) {
            inputScript("../../../js/mapv.min.js");
        }
        if (inArray(includes, 'echarts')) {
            inputScript("../../../js/echarts.min.js");
        }
        if (inArray(includes, 'nanoscroller')) {
            inputCSS("../../../css/nanoscroller.css");
            inputScript("../../../js/jquery.nanoscroller.min.js");
        }
        if (inArray(includes, 'infoWindow')) {
            inputCSS("../../../css/infoWindow.css");
            inputScript("../../../js/InfoWindow.js");
        }
        if (inArray(includes, 'heatmapColorCSS')) {
            inputCSS("../../../css/heatmap.css");
        }
        if (!inArray(excludes, 'iclient-classic')) {
            inputScript("../../dist/iclient-classic.min.js");
        }
        if (inArray(includes, 'tianditu')) {
            inputScript("../../../js/Tianditu.js");
        }
        if (inArray(includes, 'echarts-all')) {
            inputScript("../../../js/echarts-all.js");
        }
        if (inArray(includes, 'baidu')) {
            inputScript("../../../js/Baidu.js");
        }
        if (inArray(includes, 'OSMBuildings-SuperMap')) {
            inputScript("../../../js/OSMBuildings-SuperMap.js");
        }
        if (inArray(includes, 'D3WindMap')) {
            inputScript("../../../js/D3WindMap.js");
        }
        if (inArray(includes, 'd3')) {
            inputScript("../../../js/d3.v3.min.js");
        }
        if (inArray(includes, 'three')) {
            inputScript("../../../js/ThreeWebGL.js");
            inputScript("../../../js/ThreeExtras.js");
            inputScript("../../../js/RequestAnimationFrame.js");
            inputScript("../../../js/Detector.js");
            inputScript("../../../js/globe.js");
        }
        if (inArray(includes, 'MapToImg')) {
            inputScript("../../../js/MapToImg.js");
        }
        if (inArray(includes, 'Bar')) {
            inputScript("../../../js/graph/Bar.js");
        }
        if (inArray(includes, 'Bar3D')) {
            inputScript("../../../js/Bar3D.js");
        }
        if (inArray(includes, 'Circle')) {
            inputScript("../../../js/Circle.js");
        }
        if (inArray(includes, 'Line')) {
            inputScript("../../../js/Line.js");
        }
        if (inArray(includes, 'Pie')) {
            inputScript("../../../js/Pie.js");
        }
        if (inArray(includes, 'Point')) {
            inputScript("../../../js/Point.js");
        }
        if (inArray(includes, 'Ring')) {
            inputScript("../../../js/Ring.js");
        }
        if (inArray(includes, 'style')) {
            inputCSS("../../../js/style.css");
        }
        if (inArray(includes, 'sm-doc')) {
            inputCSS("../../../js/sm-doc.css");
        }
        if (inArray(includes, 'LargeFormatPrints')) {
            inputScript("../../../js/LargeFormatPrints.js");
        }
        if (inArray(includes, 'PlottingPanel')) {
            inputScript("../../../js/PlottingPanel.Include.js");
        }
        if (inArray(includes, 'bevInclude')) {
            inputScript("../../../js/bevInclude.js");
        }
        if (inArray(includes, 'DefaultStyleConfiguration')) {
            inputScript("../../../js/DefaultStyleConfiguration.js");
        }
    }

    load();
    window.isLocal = false;
    window.server = document.location.toString().match(/file:\/\//) ? "http://localhost:8090" : 'http://' + document.location.host;
})();
