var id = sessionStorage.getItem("kid");
var startTime = sessionStorage.getItem("start");
var endTime = sessionStorage.getItem("end");
var KakouRecordVo = {'kid':id,'start':startTime,'end':endTime};
$(function() {
	vm.show();
	$("#jqGrid").jqGrid({
		url : baseURL + 'generator/sc_kkxx/find',
		datatype : "json",
		postData:{'kid':id,'start':startTime,'end':endTime},
		colModel : [ {
			label : '车牌号',
			name : 'cph',
			index : 'cph',
			width : 80,
			formatter : function(cellvalue, options, rowObject) {
				return "<a href=" + baseURL + "modules/manager/scjdcjbxx.html" + ">" + cellvalue + "</a>";
			}
		}, {
			label : '起始时间',
			name : 'startTime',
			index : 'start',
			width : 80,
		},{
			label : '结束时间',
			name : 'endTime',
			index : 'end',
			width : 80,
		},{
			label : '卡口编号',
			name : 'id',
			index : 'districtNm',
			width : 80,
		}, {
			label : '数据来源',
			name : 'sjly',
			index : 'sjly',
			width : 80,
			editable:true,
			edittype:'select',   
			editoptions: gettypes()
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

function gettypes() {
	return {value:{1:'在用', 2:'空闲', 3:'故障'}}
}


var vm = new Vue({
	el : '#rrapp',
	methods : {
		show : function() {
			var url = baseURL + 'generator/sc_kkxx/list';
			$.ajax({
				url:url,
				data:JSON.stringify(KakouRecordVo),
				type: "POST",
				contentType: "application/json",
				success:function(r) {
				}
			});
			vm.reload();
		},
		reload : function(event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
				postData:{'kid':id,'start':startTime,'end':endTime},
				page : page
			}).trigger("reloadGrid");
		}
	}
});

$(window).load(function() {
	vm.show();
})
