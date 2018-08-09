var c = "";

$(function() {
	$("#jqGrid").jqGrid({
		url : baseURL + 'generator/zfyc/ss_list',
		datatype : "json",
		colModel : [ {
			label : '车牌号',
			name : 'plate',
			index : 'plate',
			width : 50,
			key : true,
		}, {
			label : '出现次数',
			name : 'count',
			index : 'count',
			width : 80
		}, {
			label : '昼伏指数',
			name : 'num',
			index : 'num',
			width : 80
		} ],
		viewrecords : true,
		height : 385,
		rowNum : 10,
		rowList : [ 10, 30, 50 ],
		rownumbers : true,
		rownumWidth : 55,
		autowidth : true,
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

var filePath = null;
var text = null;
var all = [];
var vm = new Vue({
	el : '#rrapp',
	data : {
		q : {
			plate : null,
			dr : null,
			fxc : null,
			fxd : null,
			btc : null,
			btd : null,
			ycc : null,
			ycd : null,
			btcxc : null,
			btcxd : null,
			wscxc : null,
			wscxd : null
		},
		DynamicVehicleEctronicFileVo : {},
		showList : true,
		title : null,
		roleList : {}
	},
	methods : {
		query : function() {
			// 实现查询功能
			if ($("#fxc").val() != null && $("#fxc").val() != ""
					&& $("#fxd").val() && $("#fxd").val() != ""
					&& $("#btc").val() != null && $("#btc").val() != ""
					&& $("#btd").val() != null && $("#btd").val() != ""
					&& $("#ycc").val() != null && $("#ycc").val() != ""
					&& $("#ycd").val() != null && $("#ycd").val() != ""
					&& vm.q.btcxc != null && vm.q.btcxc != ""
					&& vm.q.btcxd != null && vm.q.btcxd != ""
					&& vm.q.wscxc != null && vm.q.wscxc != ""
					&& vm.q.wscxd != null && vm.q.wscxd != "") {
				ss_();
				vm.rel();
				alert("后台正在拼命计算");
			} else {
				alert("请输入全部数据！");

			}
		},
		dt : function() {
			window.location.href = baseURL + 'vasMap/views/ss_dt.html';
		},
		qk : function() {
			del();
			vm.rel();
		},
		ss : function() {
			window.location.href = baseURL + 'modules/manager/zfyc_ss.html';
		},
		rel : function() {
			// 查询按钮方法
			var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
				
			}).trigger("reloadGrid");
			c = "";
		}
	}

});
function infor(cph) {
	// 跳页面传递车牌数据
	sessionStorage.setItem("cph", cph);
	window.location.href = baseURL + "vasMap/views/zdqyjk.html";
}

// 输入去空格
function qkg(a) {
	var b = a.trim().split(" ");
	for (var i = 0; i < b.length; i++) {
		if (b[i] != null) {
			c = c + b[i];
		}

	}
}
// SS查询ETCP全部条件结果
function ss_() {
	var params = {};
	params["fxc"] = $("#fxc").val();
	params["fxd"] = $("#fxd").val();
	params["btc"] = $("#btc").val();
	params["btd"] = $("#btd").val();
	params["ycc"] = $("#ycc").val();
	params["ycd"] = $("#ycd").val();
	params["btcxc"] = vm.q.btcxc;
	params["btcxd"] = vm.q.btcxd;
	params["wscxc"] = vm.q.wscxc;
	params["wscxd"] = vm.q.wscxd;

	$.ajax({
		url : baseURL + 'generator/zfyc/ss_c',
		data : params,
		dataType : "json",
		type : "post",
		async : "false",
		success : function(r) {
		}
	});
}

function del() {
	var params = {};
	$.ajax({
		url : baseURL + 'generator/zfyc/delete',
		data : params,
		dataType : "json",
		type : "post",
		success : function(r) {
			alert("数据正在清空,已清空后台计算完成数据！如后台未计算完请待稍后后台计算完成再清空！");
		}
	});
}