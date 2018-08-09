/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var violationDom = document.getElementById("violationDiv");
var violationChart = echarts.init(violationDom);
var violationApp = {};
violationOption = null;
violationOption = {
    tooltip : {
        formatter: "{a} <br/>{b} : {c}%"
    },
    title: {
        textStyle: {
            color: '#ccc',
            fontSize:12
        },  
        text: '违章指数',
        left: 'center',
        bottom:5        
    },
    series: [
        {
            name: '违章指数',
            type: 'gauge',
            axisLine: {            // 坐标轴线
                lineStyle: {       // 属性lineStyle控制线条样式
                    width: 2
                }
            },
            axisTick: {            // 坐标轴小标记
                length: 5,        // 属性length控制线长
                lineStyle: {       // 属性lineStyle控制线条样式
                    color: 'auto'
                }
            },
            splitLine: {           // 分隔线
                length: 8,         // 属性length控制线长
                lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
                    color: 'auto'
                }
            },
            axisLabel: {
                show:false
            },
            detail: {formatter:'{value}%',
                fontSize:14,
            },
            data: [{value: 65, name: ''}]
        }
    ]
};
/*
setInterval(function () {
    violationOption.series[0].data[0].value = (Math.random() * 100).toFixed(0) - 0;
    violationChart.setOption(violationOption, true);
},2000);
;*/
if (violationOption && typeof violationOption === "object") {
    violationChart.setOption(violationOption, true);
}

var accidentDom = document.getElementById("accidentDiv");
var accidentChart = echarts.init(accidentDom);
var accidentApp = {};
accidentOption = null;
accidentOption = {
    tooltip : {
        formatter: "{a} <br/>{b} : {c}%"
    },
    title: {
        textStyle: {
            color: '#ccc',
            fontSize:12,
        },  
        text: '事故指数',
        left: 'center',
        bottom:5
    },
    series: [
        {
            name: '事故指数',
            type: 'gauge',
            axisLine: {            // 坐标轴线
                lineStyle: {       // 属性lineStyle控制线条样式
                    width: 2
                }
            },
            axisTick: {            // 坐标轴小标记
                length: 5,        // 属性length控制线长
                lineStyle: {       // 属性lineStyle控制线条样式
                    color: 'auto'
                }
            },
            splitLine: {           // 分隔线
                length: 8,         // 属性length控制线长
                lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
                    color: 'auto'
                }
            },
            axisLabel: {
                show:false
            },
            detail: {formatter:'{value}%',
                fontSize:14,
            },
            data: [{value: 42, name: ''}]
        }
    ]
};
/*
setInterval(function () {
    accidentOption.series[0].data[0].value = (Math.random() * 100).toFixed(0) - 0;
    accidentChart.setOption(accidentOption, true);
},2000);
;*/
if (accidentOption && typeof accidentOption === "object") {
    accidentChart.setOption(accidentOption, true);
}

//


var trafficDom = document.getElementById("trafficDiv");
var trafficChart = echarts.init(trafficDom);
var trafficApp = {};
trafficOption = null;
trafficOption = {
    tooltip : {
        formatter: "{a} <br/>{b} : {c}%"
    },
    title: {
        textStyle: {
            color: '#ccc',
            fontSize:12,
        },  
        text: '拥堵指数',
        left: 'center',
        bottom:5
    },
    series: [
        {
            name: '拥堵指数',
            type: 'gauge',
            axisLine: {            // 坐标轴线
                lineStyle: {       // 属性lineStyle控制线条样式
                    width: 2
                }
            },
            axisTick: {            // 坐标轴小标记
                length: 5,        // 属性length控制线长
                lineStyle: {       // 属性lineStyle控制线条样式
                    color: 'auto'
                }
            },
            splitLine: {           // 分隔线
                length: 8,         // 属性length控制线长
                lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
                    color: 'auto'
                }
            },
            axisLabel: {
                show:false
            },
            detail: {formatter:'{value}%',
                fontSize:14,
            },
            data: [{value: 82, name: ''}]
        }
    ]
};
/*
setInterval(function () {
    trafficOption.series[0].data[0].value = (Math.random() * 100).toFixed(0) - 0;
    trafficChart.setOption(trafficOption, true);
},2000);
;*/
if (trafficOption && typeof trafficOption === "object") {
    trafficChart.setOption(trafficOption, true);
}


//


var carFlowDom = document.getElementById("carFlowDiv");
var carFlowChart = echarts.init(carFlowDom);
var carFlowApp = {};
carFlowOption = null;
carFlowOption = {
    tooltip : {
        formatter: "{a} <br/>{b} : {c}%"
    },
    title: {
        textStyle: {
            color: '#ccc',
            fontSize:12,
        },  
        text: '大车流指数',
        left: 'center',
        bottom:5
    },
    series: [
        {
            name: '大车流指数',
            type: 'gauge',
            axisLine: {            // 坐标轴线
                lineStyle: {       // 属性lineStyle控制线条样式
                    width: 2
                }
            },
            axisTick: {            // 坐标轴小标记
                length: 5,        // 属性length控制线长
                lineStyle: {       // 属性lineStyle控制线条样式
                    color: 'auto'
                }
            },
            splitLine: {           // 分隔线
                length: 8,         // 属性length控制线长
                lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
                    color: 'auto'
                }
            },
            axisLabel: {
                show:false
            },
            detail: {formatter:'{value}%',
                fontSize:14,
            },
            data: [{value: 73, name: ''}]
        }
    ]
};
/*
setInterval(function () {
    carFlowOption.series[0].data[0].value = (Math.random() * 100).toFixed(0) - 0;
    carFlowChart.setOption(carFlowOption, true);
},2000);
;*/
if (carFlowOption && typeof carFlowOption === "object") {
    carFlowChart.setOption(carFlowOption, true);
}