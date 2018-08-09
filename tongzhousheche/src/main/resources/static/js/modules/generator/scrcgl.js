$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'generator/scrcgl/list',
        datatype: "json",
        colModel: [			
			{ label: 'cp', name: 'cp', index: 'cp', width: 50, key: true },
			{ label: '车主姓名', name: 'owner', index: 'owner', width: 80 }, 			
			{ label: '车辆品牌', name: 'brand', index: 'brand', width: 80 }, 			
			{ label: '车辆颜色', name: 'color', index: 'color', width: 80 }, 			
			{ label: '', name: 'xm', index: 'xm', width: 80 }, 			
			{ label: '', name: 'sfz', index: 'sfz', width: 80 }, 			
			{ label: '', name: 'sjly', index: 'sjly', width: 80 }			
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
		scRcgl: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.scRcgl = {};
		},
		update: function (event) {
			var cp = getSelectedRow();
			if(cp == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(cp)
		},
		saveOrUpdate: function (event) {
			var url = vm.scRcgl.cp == null ? "generator/scrcgl/save" : "generator/scrcgl/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.scRcgl),
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
			var cps = getSelectedRows();
			if(cps == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "generator/scrcgl/delete",
                    contentType: "application/json",
				    data: JSON.stringify(cps),
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
		getInfo: function(cp){
			$.get(baseURL + "generator/scrcgl/info/"+cp, function(r){
                vm.scRcgl = r.scRcgl;
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