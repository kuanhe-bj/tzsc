(function () {
    var r = new RegExp("(^|(.*?\\/))(include-leaflet\.js)(\\?|$)"),
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
        if (!inArray(excludes, 'leaflet')) {
            inputCSS("../../css/leaflet-1.2.0.css");
            inputScript("../../js/leaflet-1.2.0.js");
        }
        if (inArray(includes, 'mapv')) {
            inputScript("../../../js/mapv.min.js");
        }
        if (inArray(includes, 'turf')) {
            inputScript("../../../js/turf.min.js");
        }
        if (inArray(includes, 'echarts')) {
            inputScript("../../../js/echarts.min.js");
        }
        if (inArray(includes, 'd3')) {
            inputScript("../../../js/d3.min.js");
        }
        if (inArray(includes, 'd3-hexbin')) {
            inputScript("../../../js/d3-hexbin.v0.2.min.js");
        }
        if (inArray(includes, 'd3Layer')) {
            inputScript("../../../js/leaflet-d3Layer.min.js");
        }
        if (inArray(includes, 'elasticsearch')) {
            inputScript("../../../js/elasticsearch.min.js");
        }
        if (!inArray(excludes, 'iclient9-leaflet')) {
            inputScript("./iclient9-leaflet.min.js");
        }
        if (inArray(includes, 'iclient9-leaflet-css')) {
            inputCSS("./iclient9-leaflet.min.css");
        }
        if (inArray(includes, 'leaflet.heat')) {
            inputScript("../../../js/leaflet-heat.js");
        }
        if (inArray(includes, 'osmbuildings')) {
            inputScript("../../../js/OSMBuildings-Leaflet.js");
        }
        if (inArray(includes, 'leaflet.markercluster')) {
            inputCSS("../../../css/MarkerCluster.Default.css");
            inputCSS("../../../css/MarkerCluster.css");
            inputScript("../../../js/leaflet.markercluster.js");
        }
        if (inArray(includes, 'leaflet-icon-pulse')) {
            inputCSS("../../../css/L.Icon.Pulse.css");
            inputScript("../../../js/L.Icon.Pulse.js");
        }
        if (inArray(includes, 'leaflet.draw')) {
            inputCSS("../../../css/leaflet.draw.css");
            inputScript("../../../js/leaflet.draw.js");
        }
        if (inArray(includes, 'leaflet.pm')) {
            inputCSS("../../../css/leaflet.pm.min.css");
            inputScript("../../../js/leaflet.pm.min.js");
        }
        if (inArray(includes, 'leaflet.miniMap')) {
            inputCSS("../../../css/Control.MiniMap.min.css");
            inputScript("../../../js/Control.MiniMap.min.js");
        }
        if (inArray(includes, 'leaflet.sidebyside')) {
            inputScript("../../../js/leaflet-side-by-side.min.js");
        }
    }

    load();
    window.isLocal = false;
    window.server = document.location.toString().match(/file:\/\//) ? "http://localhost:8090" : 'http://' + document.location.host;
})();
