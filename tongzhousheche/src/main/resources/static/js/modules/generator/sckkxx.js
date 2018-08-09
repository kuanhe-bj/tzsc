$(function () {
	//window.location=window.location.href + "#rrapp";
	$("#jqGrid").jqGrid({
		url: baseURL + 'generator/sckkxx/list',
		datatype: "json",
		colModel: [
			{ label: '卡口编号', name: 'id', index: 'ID', width: 180, key: true },
			{ label: '卡口名称', name: 'mc', index: 'MC', width: 180 },
			{ label: '卡口行政区划', name: 'xzqh', index: 'XZQH', width: 100,
				formatter : function(cellvalue, options, rowObject) {
					if(cellvalue == '110112') {
						cellvalue = '北京市通州区';
					}
					return cellvalue;	
				}
			},
			{ label: '卡口详细地址', name: 'xz', index: 'XZ', width: 300 },
			{ label: '卡口类型', name: 'lx', index: 'LX', width: 80,
				formatter : function(cellvalue, options, rowObject) {
					if(cellvalue == '10') {
						cellvalue = '国际';
					} else if(cellvalue == '20') {
						cellvalue = '省际';
					} else if(cellvalue == '30') {
						cellvalue = '市际';
					} else if(cellvalue == '31') {
						cellvalue = '市区';
					} else if(cellvalue == '40') {
						cellvalue = '县际';
					} else if(cellvalue == '41') {
						cellvalue = '县区';
					} else {
						cellvalue = '其他';
					}
					return cellvalue;	
				}
			},
			{ label: '经度', name: 'jd', index: 'JD', width: 100 },
			{ label: '纬度', name: 'wd', index: 'WD', width: 100 }
		],
		viewrecords: true,
		height: 385,
		rowNum: 10,
		rowList : [10,30,50],
		rownumbers: true,
		rownumWidth: 85,
		autowidth:true,
		shrinkToFit:false,
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
			$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "" });
		}
	});
});
var a=null;
var vm = new Vue({
	el:'#rrapp',
	data:{
		xz:null,
		showList: true,
		title: null,
		scKkxx: {}
	},
	methods: {
		query: function () {
			vm.showList = true;
			var id = $("#id").val().trim().replace(/\s/g,"");
			var mc = $("#mc").val().trim().replace(/\s/g,"");
			if((id == null || id == "") && (mc == null || mc == "")) {
				$("#jqGrid").jqGrid('setGridParam',{
					url:  baseURL + 'generator/sckkxx/list',
					page: 1
				}).trigger("reloadGrid");
			}
			$("#jqGrid").jqGrid('setGridParam',{
				url: baseURL + 'generator/sckkxx/cx',
				postData:{
					'id':id,
					'mc':mc
				},
				page:1
			}).trigger("reloadGrid");
			
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.scKkxx = {};
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
			var url = vm.scKkxx.id == null ? "generator/sckkxx/save" : "generator/sckkxx/update";
			$.ajax({
				type: "POST",
				url: baseURL + url,
				contentType: "application/json",
				data: JSON.stringify(vm.scKkxx),
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
					url: baseURL + "generator/sckkxx/delete",
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
			$.get(baseURL + "generator/sckkxx/info/"+id, function(r){
				vm.scKkxx = r.scKkxx;
			});
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
				page:page
			}).trigger("reloadGrid");
		},
		re: function () {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
				url: baseURL + 'generator/sckkxx/cx',
				postData:{
					'xz':a
				},
				page:page
			}).trigger("reloadGrid");
		}
	}
});
function c(){
	a = $("#xz").val().trim().replace(/\s/g,"");
	if(a!=null&&a!=""){
		vm.re();
	}else{
		window.location.href=baseURL + 'modules/generator/sckkxx.html';
	}
}