/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var xinjiang = 0;
var gansu = 0;
var shanxi = 0;
var xizang = 0;
var ningxia = 0;

var highRiskDom = document.getElementById("highRiskDiv");
var highRiskChart = echarts.init(highRiskDom);
var highRiskApp = {};
highRiskOption = null;

function create(highRiskList) {
highRiskOption = {
    title: {
        text: '高危地区车牌比例',
        textStyle: {
            color: '#ccc'
        }
    },

    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },

    visualMap: {
        show: false,
        min: 80,
        max: 600,
        inRange: {
            colorLightness: [0.4, 0.6]
        }
    },
    series : [
        {
            name:'来源',
            type:'pie',
            radius : '55%',
            center: ['50%', '50%'],
        data: highRiskList.sort(function (a, b) { return a.value - b.value; }),
            roseType: 'radius',
            label: {
                normal: {
                    textStyle: {
                        color: 'rgba(255, 255, 255, 0.3)'
                    }
                }
            },
            labelLine: {
                normal: {
                    lineStyle: {
                        color: 'rgba(255, 255, 255, 0.3)'
                    },
                    smooth: 0.2,
                    length: 10,
                    length2: 20
                }
            },
            itemStyle: {
                normal: {
                    color: '#c23531',
                    shadowBlur: 200,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            },

            animationType: 'scale',
            animationEasing: 'elasticOut',
            animationDelay: function (idx) {
                return Math.random() * 200;
            }
        }
    ]
};;
if (highRiskOption && typeof highRiskOption === "object") {
    highRiskChart.setOption(highRiskOption, true);
}
}

$(function(){
	$.ajax({
		url:baseURL + "generator/vehiclegs/listhighrisk",
		dataType:"json",
		type:"post",
		success:function(r){
			var highRiskList = r.list;
			create(highRiskList);
		}	
	})
})