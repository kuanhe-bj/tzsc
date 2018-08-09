$(function () {
	//window.location=window.location.href + "#rrapp";
    $("#jqGrid").jqGrid({
        url: baseURL + 'generator/scjxsj/list',
        datatype: "json",
        colModel: [			
			{ label: '车主', name: 'vehiOwner', index: 'VEHI_OWNER', width: 80 },
			{ label: '送修时间', name: 'enterTime', index: 'ENTER_TIME', width: 150 },
			{ label: '车牌号', name: 'licensePlate', index: 'LICENSE_PLATE', width: 100 },
			{ label: '送修人证件号码', name: 'enterCardNo', index: 'ENTER_CARD_NO', width: 180 },
			{ label: '送修人姓名', name: 'enterName', index: 'ENTER_NAME', width: 100 },
			{ label: 'dataid', name: 'dataid', index: 'DATAID', width: 80, key: true },
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
var a=$("#a").val();
var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		scJxsj: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.scJxsj = {};
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
		cx:function(){
			c();
		},
		saveOrUpdate: function (event) {
			var url = vm.scJxsj.dataid == null ? "generator/scjxsj/save" : "generator/scjxsj/update";
			vm.scJxsj.enterTime=$("#qssj").val();
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.scJxsj),
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
			var dataids = getSelectedRows();
			if(dataids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "generator/scjxsj/delete",
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
				    url: baseURL + "generator/scjxsj/delete",
                    contentType: "application/json",
				    data: JSON.stringify(dataids),
				    success: function(r){
						
					}
				});
			
		},
		getInfo: function(dataid){
			$.get(baseURL + "generator/scjxsj/info/"+dataid, function(r){
                vm.scJxsj = r.scJxsj;
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
				 url: baseURL + 'generator/scjxsj/cx',
				 postData:{
					'a':a 
				 },
					 
                page:page
            }).trigger("reloadGrid");
		}
	}
});
function c(){
	a = $("#a").val().trim().replace(/\s/g,"");
	a = a.toUpperCase();
	if(a != null && a != ""){
		vm.re();
	}else{
		window.location.href=baseURL + 'modules/generator/scjxsj.html';
	}
}