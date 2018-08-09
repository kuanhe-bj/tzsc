$(function () {
	//window.location=window.location.href + "#rrapp";
    $("#jqGrid").jqGrid({
        url: baseURL + 'generator/scjyz/list',
        datatype: "json",
        colModel: [
			{ label: 'id', name: 'id', index: 'ID', width: 50, key: true },
			{ label: '车牌号', name: 'cph', index: 'CPH', width: 80 },
			{ label: '车辆颜色', name: 'clys', index: 'CLYS', width: 80 },
			{ label: '车辆速度', name: 'sd', index: 'SD', width: 80 },
			{ label: '探头ID', name: 'ttid', index: 'TTID', width: 120 },
			{ label: '拍摄地点', name: 'psdd', index: 'PSDD', width: 390 },
			{ label: '拍摄时间', name: 'pssj', index: 'PSSJ', width: 150 },
			{ label: '入库时间', name: 'rksj', index: 'RKSJ', width: 150 },
			{ label: '图片链接', name: 'tplj', index: 'TPLJ', width: 80 },
			{ label: '视频链接', name: 'splj', index: 'SPLJ', width: 80 },
			{ label: '卡口ID', name: 'kdid', index: 'KDID', width: 80 }
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

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		scJyz: {},
		cph :null
	},
	methods: {
		queryBycph: function () {
			$("#jqGrid").jqGrid('clearGridData');  //清空表格
			//console.log($('#cph').val());
			var cph = $('#cph').val().trim().replace(/\s/g,"");
			cph = cph.toUpperCase();
			if(cph == null || cph == "") {
				$("#jqGrid").jqGrid('setGridParam',{ 
					url: baseURL + 'generator/scjyz/list',
	                page:1
	            }).trigger("reloadGrid");
			}
			var limit = $("#jqGrid").jqGrid('getGridParam','rowNum');
			$.post(baseURL + "generator/scjyz/queryBycph",{
		    	'cph':cph,
		    	'page':1,
		    	'limit':limit},
		    	function(r){
		    		if(r.page == null || r.page ==''){						
						alert("没有符合条件的记录！");
						return;
					}
		    		$("#jqGrid").jqGrid('setGridParam',{  // 重新加载数据
		    		      datatype:'local',
		    		      data : r.page.list,   //  newdata 是符合格式要求的需要重新加载的数据 
		    		      page:1
		    		}).trigger("reloadGrid");
            });
		},
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.scJyz = {};
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
			var url = vm.scJyz.id == null ? "generator/scjyz/save" : "generator/scjyz/update";
			vm.scJyz.pssj = $("#pssj").val();
			vm.scJyz.rksj = $('#rksj').val();
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.scJyz),
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
			console.info(ids);
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "generator/scjyz/delete",
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
			$.get(baseURL + "generator/scjyz/info/"+id, function(r){
                vm.scJyz = r.scJyz;
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