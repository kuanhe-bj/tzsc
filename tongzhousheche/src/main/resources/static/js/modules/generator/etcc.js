$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'generator/etcc/list',
        datatype: "json",
        colModel: [			
			{ label: 'eId', name: 'eId', index: 'e_id', width: 50, key: true },
			{ label: '', name: 'eJingdu', index: 'e_jingdu', width: 80 }, 			
			{ label: '', name: 'eWeidu', index: 'e_weidu', width: 80 }, 			
			{ label: '', name: 'querytime', index: 'querytime', width: 80 }, 			
			{ label: '', name: 'count', index: 'count', width: 80 }, 			
			{ label: '', name: 'sjly', index: 'sjly', width: 80 }			
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
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
		eTcc: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.eTcc = {};
		},
		update: function (event) {
			var eId = getSelectedRow();
			if(eId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(eId)
		},
		saveOrUpdate: function (event) {
			var url = vm.eTcc.eId == null ? "generator/etcc/save" : "generator/etcc/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.eTcc),
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
			var eIds = getSelectedRows();
			if(eIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "generator/etcc/delete",
                    contentType: "application/json",
				    data: JSON.stringify(eIds),
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
		getInfo: function(eId){
			$.get(baseURL + "generator/etcc/info/"+eId, function(r){
                vm.eTcc = r.eTcc;
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