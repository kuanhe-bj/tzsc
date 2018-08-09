$(function () {
	//window.location=window.location.href + "#rrapp";
    $("#jqGrid").jqGrid({
        url: baseURL + 'generator/scjsrsj/list',
        datatype: "json",
        colModel: [			
			{ label: '姓名', name: 'xm', index: 'XM', width: 80 },
			{ label: '身份证号码', name: 'sfzmhm', index: 'SFZMHM', width: 170 },
			{ label: '出生日期', name: 'csrq', index: 'CSRQ', width: 120 },
			{ label: '登记住所详细地址', name: 'djzsxxdz', index: 'DJZSXXDZ', width: 370 },
			{ label: '登记住所行政区划', name: 'djzsxzqh', index: 'DJZSXZQH', width: 370 },
			{ label: 'dataid', name: 'dataid', index: 'DATAID', width: 170, key: true },
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
		scJsrsj: {},
		sfz : null
	},
	methods: {
		queryBy: function () {
			$("#jqGrid").jqGrid('clearGridData');  //清空表格
			var sfz = $('#sfz').val().trim().replace(/\s/g,"");
			if(sfz == null || sfz == "") {
				$("#jqGrid").jqGrid('setGridParam',{ 
					url: baseURL + 'generator/scjsrsj/list',
	                page:page
	            }).trigger("reloadGrid");
			}
			var limit = $("#jqGrid").jqGrid('getGridParam','rowNum');
			$.post(baseURL + "generator/scjsrsj/queryBy",{
		    	'sfz':$('#sfz').val().trim().replace(/\s/g,""),
		    	'page':1,
		    	'limit':limit},
		    	function(r){
		    		if(r.page.list == null || r.page.list ==''){						
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
			vm.scJsrsj = {};
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
			var url = vm.scJsrsj.dataid == null ? "generator/scjsrsj/save" : "generator/scjsrsj/update";
			vm.scJsrsj.csrq=$("#qssj").val();
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.scJsrsj),
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
				    url: baseURL + "generator/scjsrsj/delete",
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
				    url: baseURL + "generator/scjsrsj/delete",
                    contentType: "application/json",
				    data: JSON.stringify(dataids),
				    success: function(r){
					
					}
				});
			
		},
		getInfo: function(dataid){
			$.get(baseURL + "generator/scjsrsj/info/"+dataid, function(r){
                vm.scJsrsj = r.scJsrsj;
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