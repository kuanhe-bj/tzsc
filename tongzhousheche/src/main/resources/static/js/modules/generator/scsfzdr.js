$(function () {
	//window.location=window.location.href + "#rrapp";
    $("#jqGrid").jqGrid({
        url: baseURL + 'generator/scsfzdr/list',
        datatype: "json",
        colModel: [			
			{ label: '姓名', name: 'xm', index: 'XM', width: 80 },
			{ label: '证件号码', name: 'zjhm', index: 'ZJHM', width: 160 },
			{ label: '发生时间', name: 'fssj', index: 'FSSJ', width: 160 },
			{ label: '户籍地详址', name: 'hjdxz', index: 'HJDXZ', width: 300 },
			{ label: 'dataid', name: 'dataid', index: 'DATAID', width: 100, key: true },
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
		showList: true,
		title: null,
		scSfzdr: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.scSfzdr = {};
		},
		update: function (event) {
			var dataid = getSelectedRow();
			if(dataid == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            vm.delll();
            vm.getInfo(dataid)
		},
		saveOrUpdate: function (event) {
			var url = vm.scSfzdr.dataid == null ? "generator/scsfzdr/save" : "generator/scsfzdr/update";
			vm.scSfzdr.fssj=$("#qssj").val();
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.scSfzdr),
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
		cx:function(){
			c();
		},
		del: function (event) {
			var dataids = getSelectedRows();
			if(dataids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "generator/scsfzdr/delete",
                    contentType: "application/json",
				    data: JSON.stringify(dataids),
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
		delll: function (event) {
			var dataids = getSelectedRows();
			if(dataids == null){
				return ;
			}
			
		
				$.ajax({
					type: "POST",
				    url: baseURL + "generator/scsfzdr/delete",
                    contentType: "application/json",
				    data: JSON.stringify(dataids),
				    success: function(r){
						
					}
				});
			
		},
		getInfo: function(dataid){
			$.get(baseURL + "generator/scsfzdr/info/"+dataid, function(r){
                vm.scSfzdr = r.scSfzdr;
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
				 url: baseURL + 'generator/scsfzdr/cx',
				 postData:{
					'zjhm':a
				 },
                page:page
            }).trigger("reloadGrid");
		}
	}
});
function c(){
	a = $("#zjhm").val().trim().replace(/\s/g,"");
	if(a!=null&&a!=""){
		vm.re();
	}else{
		window.location.href=baseURL + 'modules/generator/scsfzdr.html';
	}
}