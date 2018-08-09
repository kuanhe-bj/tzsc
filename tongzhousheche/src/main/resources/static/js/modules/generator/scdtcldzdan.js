$(function () {
	$("#jqGrid").jqGrid({
		url: baseURL + 'generator/scdtcldzdan/list',
		datatype: "json",
		colModel: [
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '车牌号', name: 'plate', index: 'plate', width: 80 },
			{ label: '车主姓名', name: 'owner', index: 'owner', width: 80 },
			{ label: '车辆颜色', name: 'color', index: 'color', width: 80 },
			{ label: '车辆品牌', name: 'brand', index: 'brand', width: 80 },
			{ label: '车辆型号', name: 'model', index: 'model', width: 80 },
			{ label: '出行异常指数', name: 'abnormal', index: 'abnormal', width: 80 },
			{ label: '违章异常指数', name: 'violation', index: 'violation', width: 80 },
			{ label: '昼伏夜出指数', name: 'nightout', index: 'nightOut', width: 80 },
			{ label: '高危地区指数', name: 'highrisk', index: 'highrisk', width: 80 },
			{ label: '事故异常指数', name: 'accident', index: 'accident', width: 80 },
			{ label: '隐匿车辆指数', name: 'hidden', index: 'hidden', width: 80 },
			{ label: '假牌盗牌', name: 'isfake', index: 'isFake', width: 80 },
			{ label: '存疑车辆', name: 'isindoubt', index: 'isInDoubt', width: 80 },
			{ label: '是否涉案', name: 'isinvolved', index: 'isInvolved', width: 80 },
			{ label: '车主是否重点人', name: 'issuspects', index: 'isSuspects', width: 80 },
			{ label: '是否首次入城', name: 'isfirstin', index: 'isFirstIn', width: 80 },
			{ label: '次数异常分析', name: 'times', index: 'times', width: 80 },
			{ label: '限行分析指数', name: 'limits', index: 'limits', width: 80 },
			{ label: '综合异常指数', name: 'summary', index: 'summary', width: 80 },
			{ label: '车辆有进无出', name: 'onlyenter', index: 'onlyEnter', width: 80 },
			{ label: '频繁超速指数', name: 'overspeed', index: 'overSpeed', width: 80 },
			{ label: '连续违章指数', name: 'contviolation', index: 'ContViolation', width: 80 },
			{ label: '异常轨迹指数', name: 'abtravel', index: 'abTravel', width: 80 },
			{ label: '重点区域徘徊指数', name: 'wander', index: 'wander', width: 80 },
			{ label: '电子围栏高危地区分析指数', name: 'efence', index: 'efence', width: 80 },
			{ label: '套牌指数', name: 'multiplate', index: 'multiPlate', width: 80 },
			{ label: '身份证', name: 'sfz', index: 'sfz', width: 80 }
		],
		viewrecords: true,
		height: 385,
		rowNum: 10,
		rowList : [10,30,50],
		rownumbers: true,
		rownumWidth: 55,
		autowidth:true,
		multiselect: true,
		pager: "#jqGridPager",
		jsonReader : {
			root: "page.list",
			page: "page.currPage",
			total: "page.totalPage",
			records: "page.totalCount"
		},
		prmNames : {
			page:"page",
			rows:"limit",
			order: "order"
		},
		gridComplete:function(){
			//隐藏grid底部滚动条
			$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
		}
	});
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		scDtcldzdan: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.scDtcldzdan = {};
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
			vm.title = "修改";

			vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
			var url = vm.scDtcldzdan.id == null ? "generator/scdtcldzdan/save" : "generator/scdtcldzdan/update";
			$.ajax({
				type: "POST",
				url: baseURL + url,
				contentType: "application/json",
				data: JSON.stringify(vm.scDtcldzdan),
				success: function(r){
					if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}

			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
					url: baseURL + "generator/scdtcldzdan/delete",
					contentType: "application/json",
					data: JSON.stringify(ids),
					success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(id){
			$.get(baseURL + "generator/scdtcldzdan/info/"+id, function(r){
				vm.scDtcldzdan = r.scDtcldzdan;
			});
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
				page:page
			}).trigger("reloadGrid");
		}
	}
});