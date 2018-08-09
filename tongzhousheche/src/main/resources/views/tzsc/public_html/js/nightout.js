/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// 基于准备好的dom，初始化echarts实例
var nightOutChart = echarts.init(document.getElementById('nightOutDiv'));

// 指定图表的配置项和数据
var nightOutOption = {
    
    textStyle: {
        color: '#ccc'
    },    
    title: {
        textStyle: {
            color: '#ccc'
        },  
        text: '昼伏夜出车辆时段统计'
    },
    tooltip: {},
    legend: {
        data:['数量']
    },
    xAxis: {
        axisLine: {
          lineStyle: {
            color: 'rgba(255, 255, 255, 0.6)' //坐标轴线颜色
          }
        },
        data: ["21点","22点","23点","0点","1点","2点","3点","4点","5点"]
    },
    yAxis: {
        axisLine: {
          lineStyle: {
            color: 'rgba(255, 255, 255, 0.6)' //坐标轴线颜色
          }
        },        
    },
    series: [{
        name: '出行车辆数',
        type: 'line',
        data: [1820, 1232, 901, 734, 590, 630, 320,880,1320],
    }]
};

// 使用刚指定的配置项和数据显示图表。
nightOutChart.setOption(nightOutOption);
