$(function () {
	//window.location=window.location.href + "#rrapp";
    $("#jqGrid").jqGrid({
        url: baseURL + 'generator/scjczxx113/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'ID', width: 60, key: true },
			{ label: '车牌号', name: 'cph', index: 'CPH', width: 100 },
			{ label: '车辆颜色', name: 'clys', index: 'CLYS', width: 80 },
			{ label: '车辆速度', name: 'sd', index: 'SD', width: 80 },
			{ label: '探头ID', name: 'ttid', index: 'TTID', width: 120 },
			{ label: '拍摄地点', name: 'psdd', index: 'PSDD', width: 400 },
			{ label: '拍摄时间', name: 'pssj', index: 'PSSJ', width: 150 },
			{ label: '入库时间', name: 'rksj', index: 'RKSJ', width: 150 },
			{ label: '图片链接', name: 'tplj', index: 'TPLJ', width: 150 },
			{ label: '视频链接', name: 'splj', index: 'SPLJ', width: 150 },
			{ label: '卡口ID', name: 'kdid', index: 'KDID', width: 100 }
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

function check() {
	var carNum = $("#carNum").val();
	if (carNum.length < 10 &&  carNum != null && carNum != "" ) {
		$("#msg").css("color", "green");
		$("#msg").html("输入正确！");
	} else if(carNum == null || carNum == "") {
		$("#msg").css("color", "red");
		$("#msg").html("请输入车牌号");
	} else if(carNum.length >= 10){
		$("#msg").css("color", "red");
		$("#msg").html("输入长度不超过10个字符！");
	} 
}

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		scJczxx113: {}
	},
	methods: {
		query: function () {
			var cph = $("#carNum").val().trim().replace(/\s/g,"");
			cph = cph.toUpperCase();
			if(cph == null || cph == "") {
				$("#jqGrid").jqGrid('setGridParam',{ 
					url: baseURL + 'generator/scjczxx113/list',
	                page:1
	            }).trigger("reloadGrid");
			} else {
				$("#jqGrid").jqGrid('setGridParam',{ 
					url: baseURL + 'generator/scjczxx113/find',
					postData:{'cph': cph},
					page:1
				}).trigger("reloadGrid");
			}
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.scJczxx113 = {};
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
			var url = vm.scJczxx113.id == null ? "generator/scjczxx113/save" : "generator/scjczxx113/update";
			vm.scJczxx113.rksj = $("#rksj").val();
			vm.scJczxx113.pssj = $('#pssj').val();
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.scJczxx113),
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
				    url: baseURL + "generator/scjczxx113/delete",
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
			$.get(baseURL + "generator/scjczxx113/info/"+id, function(r){
                vm.scJczxx113 = r.scJczxx113;
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