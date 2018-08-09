(function () {
    var r = new RegExp("(^|(.*?\\/))(include-mapboxgl\.js)(\\?|$)"),
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
        if (!inArray(excludes, 'mapbox-gl')) {
            inputCSS("../../../css/mapbox-gl.css");
            inputScript("../../../js/mapbox-gl.js");
        }
        if (inArray(includes, 'draw')) {
            inputCSS("../../../css/mapbox-gl-draw.css");
            inputScript("../../../js/mapbox-gl-draw.js");
        }
        if (inArray(includes, 'compare')) {
            inputCSS("../../../css/mapbox-gl-compare.css");
            inputScript("../../../js/mapbox-gl-compare.js");
        }
        if (inArray(includes, 'mapv')) {
            inputScript("../../../js/mapv.min.js");
        }
        if (inArray(includes, 'echarts')) {
            inputScript("../../../js/echarts.min.js");
            inputScript("../../../js/EchartsLayer.js");
        }
        if (!inArray(excludes, 'iclient9-mapboxgl')) {
            inputScript("../../dist/iclient9-mapboxgl.min.js");
        }
        if (inArray(includes, 'proj4')) {
            inputScript("../../../js/proj4.js");
        }
        if (inArray(includes, 'echarts-gl')) {
            inputScript("../../../js/echarts-gl.js");
        }
        if (inArray(includes, 'shapefile')) {
            inputScript("../../../js/shapefile.js");
        }
    }

    load();
    window.isLocal = false;
    window.server = document.location.toString().match(/file:\/\//) ? "http://localhost:8090" : 'http://' + document.location.host;
})();
