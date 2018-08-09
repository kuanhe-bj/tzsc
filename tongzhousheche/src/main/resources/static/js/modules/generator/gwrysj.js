$(function() {
	$("#jqGrid").jqGrid({
		url : baseURL + 'gwrysj/query',
		datatype : "json",
		colModel : [ {
			label : '重点人员编号',
			name : 'drybh',
			index : "drybh",
			width : 40,
			key : true
		}, {
			label : '姓名',
			name : 'xm',
			index : "xm",
			width : 40
		}, {
			label : '性别',
			name : 'xb',
			index : "xb",
			width : 25,formatter: function(value, options, row){
				return value === true ? 
						'<span class="label label-danger">男</span>' : 
						'<span class="label label-success">女</span>';
		}},{
			label : '民族',
			name : 'mz',
			index : "mz",
			width : 25
		}, {
			label : '现住地详址',
			name : 'xzdxz',
			index : "xzdxz",
			width : 75
		}, {
			label : '电话手机',
			name : 'dianhua',
			index : "dianhua",
			width : 60
		}, {
			label : '盗窃',
			name : 'daoqie',
			index : "daoqie",
			width : 25,formatter: function(value, options, row){
				return value === true ? 
						'<span class="label label-danger">是</span>' : 
						'<span class="label label-success">否</span>';
		} }, {
			label : '入室',
			name : 'rushi',
			index : "rushi",
			width : 25,formatter: function(value, options, row){
				return value === true ? 
						'<span class="label label-danger">是</span>' : 
						'<span class="label label-success">否</span>';
		} }, {
			label : '通讯',
			name : 'tongxun',
			index : "tongxun",
			width : 25,formatter: function(value, options, row){
				return value === true ? 
						'<span class="label label-danger">是</span>' : 
						'<span class="label label-success">否</span>';
		} }, {
			label : '扒窃',
			name : 'paqie',
			index : "paqie",
			width : 25,formatter: function(value, options, row){
				return value === true ? 
						'<span class="label label-danger">是</span>' : 
						'<span class="label label-success">否</span>';
		} }],
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
		checked1: '',
		checked2: '',
		checked3: '',
		checked4: ''
	},
	methods: {
		query: function () {
			var param1 = "";
			var param2 = "";
			var param3 = "";
			var param4 = "";
			if(vm.checked1) {
				param1 = "盗窃";
			}
			if(vm.checked2) {
				param2 = "入室";
			}
			if(vm.checked3) {
				param3 = "通讯";
			}
			if(vm.checked4) {
				param4 = "扒窃";
			}
			var param = param1 + param2 + param3 + param4;
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{'param': param},
				page:1
			}).trigger("reloadGrid");
		},
	    reload: function () {
			var page = $("#jqGrid").jqGrid('getGridParam','page');
				$("#jqGrid").jqGrid('setGridParam',{ 
	                page:page
	            }).trigger("reloadGrid");
		}
	}
});
