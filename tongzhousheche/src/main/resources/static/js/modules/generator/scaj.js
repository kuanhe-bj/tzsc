$(function() {
	$("#jqGrid").jqGrid({
		url : baseURL + 'scaj/query',
		datatype : "json",
		colModel : [ {
			label : '案件编号',
			name : 'ajbh',
			index : "ajbh",
			width : 40,
			key : true
		}, {
			label : '案件名称',
			name : 'ajmc',
			index : "ajmc",
			width : 35
		}, {
			label : '派出所管辖',
			name : 'pcsgxid',
			index : "pcsgxid",
			width : 40
		},{
			label : '警区',
			name : 'jq',
			index : "jq",
			width : 25
		}, {
			label : '发案地点',
			name : 'faddid',
			index : "faddid",
			width : 75
		}, {
			label : '发案开始时间',
			name : 'fakssj',
			index : "fakssj",
			width : 55
		}, {
			label : '车牌号',
			name : 'cph',
			index : "cph",
			width : 40
		},{
			label : '简要案情',
			name : 'jyaq',
			index : "jyaq",
			width : 80
		}],
		viewrecords : true,
		height : 385,
		rowNum : 10,
		rowList : [ 10, 30, 50 ],
		rownumbers : true,
		rownumWidth : 85,
		autowidth : true,
		multiselect : false,
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
		},
		loadComplete: function(xhr) { 
	        var re_records = $("#jqGrid").jqGrid('getGridParam', 'records'); //获取数据总条数
            if(re_records==0){
            	alert("没有符合条件的记录！"); 
            }
	    }  
	});
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			param: null
		}
	},
	methods: {
		query: function () {
			$("#jqGrid").jqGrid('setGridParam',{ 
                postData:{'param': $.trim(vm.q.param)},
                page:1
            }).trigger("reloadGrid");
		},
	    reload: function () {
			var page = $("#jqGrid").jqGrid('getGridParam','page');
				$("#jqGrid").jqGrid('setGridParam',{ 
	                postData:{'param': vm.q.param},
	                page:page
	            }).trigger("reloadGrid");
		}
	}
});
