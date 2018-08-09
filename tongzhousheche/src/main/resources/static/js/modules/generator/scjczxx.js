$(function () {
	//window.location=window.location.href + "#rrapp";
    $("#jqGrid").jqGrid({
        url: baseURL + 'generator/scjczxx/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'ID', width: 50, key: true },
			{ label: '卡口名称', name: 'mc',width: 80 },
			{ label: '卡口行政区划', name: 'xzqh', index: 'XZQH', width: 80,
				formatter : function(cellvalue, options, rowObject) {
					if(cellvalue == '110112') {
						cellvalue = '北京市通州区';
					}
					return cellvalue;	
				}
			},
			{ label: '卡口详细地址', name: 'xz', index: 'XZ', width: 80 },
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
			}
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 85, 
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
var a=null;
var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		scJczxx: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.scJczxx = {};
		},
		cx:function(){
			c();
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
			var url = vm.scJczxx.id == null ? "generator/scjczxx/save" : "generator/scjczxx/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.scJczxx),
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
				    url: baseURL + "generator/scjczxx/delete",
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
			$.get(baseURL + "generator/scjczxx/info/"+id, function(r){
                vm.scJczxx = r.scJczxx;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		},
		re: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				url: baseURL + 'generator/scjczxx/cx',
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
		window.location.href=baseURL + 'modules/generator/scjczxx.html';
	}
}