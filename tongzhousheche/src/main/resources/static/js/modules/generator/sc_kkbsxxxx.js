var cph1 = sessionStorage.getItem("cph");
var time = sessionStorage.getItem("time");
var num = sessionStorage.getItem("num");
var start = sessionStorage.getItem("start");
var end = sessionStorage.getItem("end");
var cph2 = sessionStorage.getItem("cph2");

sessionStorage.removeItem("cph");
sessionStorage.removeItem("start");
sessionStorage.removeItem("end");
sessionStorage.removeItem("num");
sessionStorage.removeItem("time");
sessionStorage.removeItem("cph2");

var kkbs = {
	'cph1' : cph1,
	'cph2' : cph2,
	'time' : time,
	'num' : num
};
$(function() {
	$("#jqGrid").jqGrid({
		url : baseURL + 'generator/sc_kkxx/bs',
		datatype : "json",
		postData : {
			'cph1' : cph1,
			'time' : time,
			'num' : num,
			'start' : start,
			'end' : end,
			'cph2' : cph2
		},
		colModel : [ {
			label : '卡口编号',
			name : 'kdid',
			index : 'kdid',
			width : 80
		}, {
			label : '车牌号',
			name : 'cph1',
			index : 'cph',
			width : 80,
		}, {
			label : '通过时间',
			name : 'pssj1',
			index : 'pssj',
			width : 80,
		}, {
			label : '跟随车牌号',
			name : 'cph2',
			index : 'cph',
			width : 80,
		}, {
			label : '跟随时间',
			name : 'pssj2',
			index : 'pssj',
			width : 80,
		} ],
		viewrecords : true,
		height : 385,
		rowNum : 10,
		rowList : [ 10, 30, 50 ],
		rownumbers : true,
		rownumWidth : 25,
		autowidth : true,
		multiselect : true,
		pager : "#jqGridPager",
		jsonReader : {
			root : "page.list",
			page : "page.currPage",
			total : "page.totalPage",
			records : "page.totalCount"
		},
		prmNames : {
			page : "page",
			rows : "limit",
			order : "order"
		},
		gridComplete : function() {
			// 隐藏grid底部滚动条
			$("#jqGrid").closest(".ui-jqgrid-bdiv").css({
				"overflow-x" : "hidden"
			});
		}
	});
});

function backup() {
	sessionStorage.setItem("cph", cph1);
	sessionStorage.setItem("time", time);
	sessionStorage.setItem("num", num);
	sessionStorage.setItem("start", start);
	sessionStorage.setItem("end", end);

	window.history.back(-1);
	 
}

var vm = new Vue({
	el : '#rrapp',
	methods : {
		show : function() {
			var url = baseURL + 'generator/sc_kkxx/ban';
			$.ajax({
				url : url,
				data : JSON.stringify(kkbs),
				type : "POST",
				contentType : "application/json",
				success : function(r) {
				}
			});
			vm.reload();
		},
		reload : function(event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
				postData : {
					'cph1' : cph1,
					'time' : time,
					'num' : num,
					'start' : start,
					'end' : end,
					'cph2' : cph2
				},
				page : page
			}).trigger("reloadGrid");
		}
	}
});

