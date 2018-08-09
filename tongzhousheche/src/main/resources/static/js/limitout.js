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

$(function() {
	$.ajax({
		type : "POST",
		url : baseURL + 'generator/sc_xxfx/listXX',
		contentType : "application/json",
		success : function(r) {
			var num = new Array();
			for (var i = 0; i < 5; i++) {
				if(r.list == null || r.list == "") {
					num.push(0);
				} else {
					num.push(r.list[i]);
					//console.log(r.list[i]);
				}
				xianxing(num);
			}
		}
	});
});

function xianxing(num) {
	//console.log(num[0]);
	limitOutOption = {
		title : {
			textStyle : {
				color : '#ccc'
			},
			text : '限行违章统计'
		},
		tooltip : {
			trigger : 'item',
			formatter : "{a} <br/>{b}: {c} ({d}%)"
		},
		legend : {
			show : false,
			orient : 'horizontal',
			x : 'left',
			data : [ '星期一', '星期二', '星期三', '星期四', '星期五' ]
		},
		series : [ {
			name : '限行违章',
			type : 'pie',
			radius : [ '50%', '70%' ],
			avoidLabelOverlap : false,
			label : {
				normal : {
					show : false,
					position : 'center'
				},
				emphasis : {
					show : true,
					textStyle : {
						fontSize : '30',
						fontWeight : 'bold'
					}
				}
			},
			labelLine : {
				normal : {
					show : false
				}
			},
			data : [ {
				value : num[0],
				name : '星期一'
			}, {
				value : num[1],
				name : '星期二'
			}, {
				value : num[2],
				name : '星期三'
			}, {
				value : num[3],
				name : '星期四'
			}, {
				value : num[4],
				name : '星期五'
			} ]
		} ]
	};
	

	if (limitOutOption && typeof limitOutOption === "object") {
		limitOutChart.setOption(limitOutOption, true);
	}

}