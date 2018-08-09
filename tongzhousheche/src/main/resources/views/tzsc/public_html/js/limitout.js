/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var limitOutDom = document.getElementById("limitOutDiv");
var limitOutChart = echarts.init(limitOutDom);
var limitOutApp = {};
limitOutOption = null;
limitOutApp.title = '环形图';

limitOutOption = {
    title: {
        textStyle: {
            color: '#ccc'
        },  
        text: '限行违章统计'
    },    
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b}: {c} ({d}%)"
    },
    legend: {
        show:false,
        orient: 'horizontal',
        x: 'left',
        data:['星期一','星期二','星期三','星期四','星期五']
    },
    series: [
        {
            name:'限行违章',
            type:'pie',
            radius: ['50%', '70%'],
            avoidLabelOverlap: false,
            label: {
                normal: {
                    show: false,
                    position: 'center'
                },
                emphasis: {
                    show: true,
                    textStyle: {
                        fontSize: '30',
                        fontWeight: 'bold'
                    }
                }
            },
            labelLine: {
                normal: {
                    show: false
                }
            },
            data:[
                {value:335, name:'星期一'},
                {value:310, name:'星期二'},
                {value:234, name:'星期三'},
                {value:135, name:'星期四'},
                {value:1548, name:'星期五'}
            ]
        }
    ]
};
;
if (limitOutOption && typeof limitOutOption === "object") {
    limitOutChart.setOption(limitOutOption, true);
}
