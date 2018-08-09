/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var dom = document.getElementById("carFromDiv");
var myChart = echarts.init(dom);
var app = {};
caroption = null;

	

var weatherIcons = {
    up: '../tzsc/public_html/data/asset/img/arrow-up.png',
    down: '../tzsc/public_html/data/asset/img/arrow-down.png'
};
function created(){
caroption = {
    textStyle: {
        color: '#ccc'
    }, 
    title: {
        textStyle: {
            color: '#ccc'
        },     
        text: '车辆归属地分布'
    },
    tooltip: {
        trigger: 'item'
    },
    visualMap: {
        min: 0,
        max: 1000,
        left: 'left',
        top: 'bottom',
        text: ['高','低'],
        calculable: true
    },
    toolbox: {
        show: false,
        orient: 'vertical',
        left: 'right',
        top: 'center',
        feature: {
            dataView: {readOnly: false},
            saveAsImage: {}
        }
    },
    animation: true,
    animationDurationUpdate: 1000,
    animationEasingUpdate: 'cubicInOut',
    series: [
        {
            name: '来源',
            type: 'map',
            mapType: 'china',
            roam: true,
            label: {
                normal: {
                    show: true,
                    formatter: function (params) {
                        var icon = params.data.value[1] ? 'up' : 'down';
                        var valueType = params.data.value[1] ? 'valueUp' : 'valueDown';
                        return params.name
                            + '：{' + valueType + '|' + params.value + '} {' + icon + '|}';
                    },
                    position: 'inside',
                    backgroundColor: '#fff',
                    padding: [4, 5],
                    borderRadius: 3,
                    borderWidth: 1,
                    borderColor: 'rgba(0,0,0,0.5)',
                    color: '#777',
                    rich: {
                        valueUp: {
                            color: '#019D2D',
                            fontSize: 14
                        },
                        valueDown: {
                            color: 'red',
                            fontSize: 14
                        },
                        up: {
                            height: 14,
                            align: 'center',
                            backgroundColor: {
                                image: weatherIcons.up
                            }
                        },
                        down: {
                            height: 14,
                            align: 'center',
                            backgroundColor: {
                                image: weatherIcons.down
                            }
                        }
                    }
                },
                emphasis: {
                    show: true
                }
            },
            data: 
            	carFromList
        }
    ]
};

setTimeout(function () {

    myChart.on('mouseup', function (params) {
        if (!down) {
            return;
        }
        down = false;

        var e = params.event;

        var geoCoord = myChart.convertFromPixel('series', [e.offsetX, e.offsetY]);

        myChart.setOption({
            series: [{
                center: geoCoord,
                zoom: 4,
                animationDurationUpdate: 1000,
                animationEasingUpdate: 'cubicInOut'
            }]
        });
    });

    var down;
    myChart.on('mousedown', function () {
        down = true;
    });
    myChart.on('mousemove', function () {
        down = false;
    });
    myChart.on('click', function (params) {
        window.location.href=baseURL + 'modules/generator/sc_dtcldzdans.html?p='+ encodeURIComponent(params.name);
    });
}, 0);
;
if (caroption && typeof caroption === "object") {
    myChart.setOption(caroption, true);
}

}
// 异步加载数据
$.get(baseURL + 'generator/vehiclegs/cargsd').done(function (data) {
    var datalist = data.page.list;
    // 缓存数组长度，进一步提升代码性能
    len = datalist.length,
    result = [];

    for (var i = 0; i < len; i++) {
        result[i] = {'name':datalist[i]['province'],'value':datalist[i]['carnum']};
    };
    // 填入数据
    myChart.setOption({

        series: [{
            // 根据名字对应到相应的系列
            data: result
        }]
    });
});


$(function(){
	$.ajax({
		url:baseURL + "generator/vehiclegs/list",
		dataType:"json",
		type:"post",
		success:function(r){
			carFromList = r.list;
			created();
		}	
	})
})
/*

var carFromDom = document.getElementById("carFromDiv");
var carFromChart = echarts.init(carFromDom);
var carFromApp = {};
carFromDivOption = null;
var geoCoordMap = {
    '上海': [121.4648,31.2891],
    '东莞': [113.8953,22.901],
    '东营': [118.7073,37.5513],
    '中山': [113.4229,22.478],
    '临汾': [111.4783,36.1615],
    '临沂': [118.3118,35.2936],
    '丹东': [124.541,40.4242],
    '丽水': [119.5642,28.1854],
    '乌鲁木齐': [87.9236,43.5883],
    '佛山': [112.8955,23.1097],
    '保定': [115.0488,39.0948],
    '兰州': [103.5901,36.3043],
    '包头': [110.3467,41.4899],
    '北京': [116.4551,40.2539],
    '北海': [109.314,21.6211],
    '南京': [118.8062,31.9208],
    '南宁': [108.479,23.1152],
    '南昌': [116.0046,28.6633],
    '南通': [121.1023,32.1625],
    '厦门': [118.1689,24.6478],
    '台州': [121.1353,28.6688],
    '合肥': [117.29,32.0581],
    '呼和浩特': [111.4124,40.4901],
    '咸阳': [108.4131,34.8706],
    '哈尔滨': [127.9688,45.368],
    '唐山': [118.4766,39.6826],
    '嘉兴': [120.9155,30.6354],
    '大同': [113.7854,39.8035],
    '大连': [122.2229,39.4409],
    '天津': [117.4219,39.4189],
    '太原': [112.3352,37.9413],
    '威海': [121.9482,37.1393],
    '宁波': [121.5967,29.6466],
    '宝鸡': [107.1826,34.3433],
    '宿迁': [118.5535,33.7775],
    '常州': [119.4543,31.5582],
    '广州': [113.5107,23.2196],
    '廊坊': [116.521,39.0509],
    '延安': [109.1052,36.4252],
    '张家口': [115.1477,40.8527],
    '徐州': [117.5208,34.3268],
    '德州': [116.6858,37.2107],
    '惠州': [114.6204,23.1647],
    '成都': [103.9526,30.7617],
    '扬州': [119.4653,32.8162],
    '承德': [117.5757,41.4075],
    '拉萨': [91.1865,30.1465],
    '无锡': [120.3442,31.5527],
    '日照': [119.2786,35.5023],
    '昆明': [102.9199,25.4663],
    '杭州': [119.5313,29.8773],
    '枣庄': [117.323,34.8926],
    '柳州': [109.3799,24.9774],
    '株洲': [113.5327,27.0319],
    '武汉': [114.3896,30.6628],
    '汕头': [117.1692,23.3405],
    '江门': [112.6318,22.1484],
    '沈阳': [123.1238,42.1216],
    '沧州': [116.8286,38.2104],
    '河源': [114.917,23.9722],
    '泉州': [118.3228,25.1147],
    '泰安': [117.0264,36.0516],
    '泰州': [120.0586,32.5525],
    '济南': [117.1582,36.8701],
    '济宁': [116.8286,35.3375],
    '海口': [110.3893,19.8516],
    '淄博': [118.0371,36.6064],
    '淮安': [118.927,33.4039],
    '深圳': [114.5435,22.5439],
    '清远': [112.9175,24.3292],
    '温州': [120.498,27.8119],
    '渭南': [109.7864,35.0299],
    '湖州': [119.8608,30.7782],
    '湘潭': [112.5439,27.7075],
    '滨州': [117.8174,37.4963],
    '潍坊': [119.0918,36.524],
    '烟台': [120.7397,37.5128],
    '玉溪': [101.9312,23.8898],
    '珠海': [113.7305,22.1155],
    '盐城': [120.2234,33.5577],
    '盘锦': [121.9482,41.0449],
    '石家庄': [114.4995,38.1006],
    '福州': [119.4543,25.9222],
    '秦皇岛': [119.2126,40.0232],
    '绍兴': [120.564,29.7565],
    '聊城': [115.9167,36.4032],
    '肇庆': [112.1265,23.5822],
    '舟山': [122.2559,30.2234],
    '苏州': [120.6519,31.3989],
    '莱芜': [117.6526,36.2714],
    '菏泽': [115.6201,35.2057],
    '营口': [122.4316,40.4297],
    '葫芦岛': [120.1575,40.578],
    '衡水': [115.8838,37.7161],
    '衢州': [118.6853,28.8666],
    '西宁': [101.4038,36.8207],
    '西安': [109.1162,34.2004],
    '贵阳': [106.6992,26.7682],
    '连云港': [119.1248,34.552],
    '邢台': [114.8071,37.2821],
    '邯郸': [114.4775,36.535],
    '郑州': [113.4668,34.6234],
    '鄂尔多斯': [108.9734,39.2487],
    '重庆': [107.7539,30.1904],
    '金华': [120.0037,29.1028],
    '铜川': [109.0393,35.1947],
    '银川': [106.3586,38.1775],
    '镇江': [119.4763,31.9702],
    '长春': [125.8154,44.2584],
    '长沙': [113.0823,28.2568],
    '长治': [112.8625,36.4746],
    '阳泉': [113.4778,38.0951],
    '青岛': [120.4651,36.3373],
    '韶关': [113.7964,24.7028]
};

var BJData = [
    [{name:'北京'}, {name:'上海',value:95}],
    [{name:'北京'}, {name:'广州',value:90}],
    [{name:'北京'}, {name:'大连',value:80}],
    [{name:'北京'}, {name:'南宁',value:70}],
    [{name:'北京'}, {name:'南昌',value:60}],
    [{name:'北京'}, {name:'拉萨',value:50}],
    [{name:'北京'}, {name:'长春',value:40}],
    [{name:'北京'}, {name:'包头',value:30}],
    [{name:'北京'}, {name:'重庆',value:20}],
    [{name:'北京'}, {name:'常州',value:10}]
];



var planePath = 'path://M45.772,21.305h-0.117l-4.89-11.354c-0.338-0.788-1.11-1.294-1.969-1.294h-5.904V5.398c0-1.186-0.959-2.145-2.146-2.145H17.168c-1.185,0-2.144,0.959-2.144,2.145v3.258H9.123c-0.857,0-1.63,0.506-1.968,1.294L2.264,21.305h-0.12C0.96,21.305,0,22.264,0,23.449v3.553v9.403c0,1.188,0.96,2.145,2.144,2.145H2.95v3.971c0,1.186,0.959,2.146,2.144,2.146h5.155c1.184,0,2.145-0.96,2.145-2.146V38.55h23.131v3.971c0,1.186,0.961,2.146,2.146,2.146h5.153c1.188,0,2.145-0.96,2.145-2.146V38.55h0.806c1.188,0,2.145-0.957,2.145-2.145v-9.403v-3.553C47.917,22.264,46.96,21.305,45.772,21.305z M10.535,12.942h26.851l3.603,8.363H6.935L10.535,12.942z M9.292,34.125c-2.319,0-4.199-1.879-4.199-4.198c0-2.317,1.879-4.197,4.199-4.197c2.317,0,4.196,1.88,4.196,4.197C13.488,32.246,11.609,34.125,9.292,34.125z M38.508,34.125c-2.316,0-4.198-1.879-4.198-4.198c0-2.317,1.879-4.197,4.198-4.197c2.317,0,4.196,1.88,4.196,4.197C42.704,32.246,40.825,34.125,38.508,34.125z';

var convertData = function (data) {
    var res = [];
    for (var i = 0; i < data.length; i++) {
        var dataItem = data[i];
        var fromCoord = geoCoordMap[dataItem[1].name];
        var toCoord = geoCoordMap[dataItem[0].name];
        if (fromCoord && toCoord) {
            res.push({
                fromName: dataItem[1].name,
                toName: dataItem[0].name,
                coords: [fromCoord, toCoord]
            });
        }
    }
    return res;
};

var color = ['#a6c84c', '#ffa022', '#46bee9'];
var series = [];
[['北京', BJData]].forEach(function (item, i) {
    series.push({
        name: item[0] + ' Top10',
        type: 'lines',
        zlevel: 1,
        effect: {
            show: true,
            period: 6,
            trailLength: 0.7,
            color: '#fff',
            symbolSize: 3
        },
        lineStyle: {
            normal: {
                color: color[i],
                width: 0,
                curveness: 0.2
            }
        },
        data: convertData(item[1])
    },
    {
        name: item[0] + ' Top10',
        type: 'lines',
        zlevel: 2,
        symbol: ['none', 'arrow'],
        symbolSize: 10,
        effect: {
            show: true,
            period: 6,
            trailLength: 0,
            symbol: planePath,
            symbolSize: 15
        },
        lineStyle: {
            normal: {
                color: color[i],
                width: 1,
                opacity: 0.6,
                curveness: 0.2
            }
        },
        data: convertData(item[1])
    },
    {
        name: item[0] + ' Top10',
        type: 'effectScatter',
        coordinateSystem: 'geo',
        zlevel: 2,
        rippleEffect: {
            brushType: 'stroke'
        },
        label: {
            normal: {
                show: true,
                position: 'right',
                formatter: '{b}'
            }
        },
        symbolSize: function (val) {
            return val[2] / 8;
        },
        itemStyle: {
            normal: {
                color: color[i]
            }
        },
        data: item[1].map(function (dataItem) {
            return {
                name: dataItem[1].name,
                value: geoCoordMap[dataItem[1].name].concat([dataItem[1].value])
            };
        })
    });
});

carFromOption = {
    backgroundColor: '#0f3054',
    title : {
        text: '车辆归属地分布',
        textStyle : {
            color: '#fff'
        }
    },
    tooltip : {
        trigger: 'item'
    },
    legend: {
        orient: 'vertical',
        top: 'bottom',
        left: 'right',
        data:['北京 Top10', '上海 Top10', '广州 Top10'],
        textStyle: {
            color: '#fff'
        },
        selectedMode: 'single'
    },
    geo: {
        map: 'china',
        label: {
            emphasis: {
                show: false
            }
        },
        roam: true,
        itemStyle: {
            normal: {
                areaColor: '#323c48',
                borderColor: '#404a59'
            },
            emphasis: {
                areaColor: '#63a0a7'
            }
        }
    },
    series: series
};;
if (carFromOption && typeof carFromOption === "object") {
    carFromChart.setOption(carFromOption, true);
}*/