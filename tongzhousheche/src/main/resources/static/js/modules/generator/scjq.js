$(function () {
	//window.location=window.location.href + "#rrapp";
    $("#jqGrid").jqGrid({
        url: baseURL + 'generator/scjq/list',
        datatype: "json",
        colModel: [			
			{ label: '接处警编号', name: 'jcjbh', index: 'JCJBH', width: 100 },
			{ label: '案(事)件发生时间', name: 'asjfssj', index: 'ASJFSSJ', width: 120 },
			{ label: '案(事)件结束时间', name: 'asjjssj', index: 'ASJJSSJ', width: 120 },
			{ label: '案(事)件发生地点（流水号）', name: 'asjfsddid', index: 'ASJFSDDID', width: 300 },
			{ label: '案(事)件发生地详址', name: 'asjfsxz', index: 'ASJFSXZ', width: 300 },
			{ label: '反映内容', name: 'fynr', index: 'FYNR', width: 240 },
			{ label: '所属派出所（流水号）', name: 'sspcsid', index: 'SSPCSID', width: 150 },
			{ label: '案(事)件类型（流水号）', name: 'asjlxid', index: 'ASJLXID', width: 160 },
			{ label: '车牌号', name: 'cph', index: 'CPH', width: 80 },
			{ label: '报案人名称	', name: 'barmc', index: 'BARMC', width: 100 },
			{ label: '报案单位名称', name: 'badwmc', index: 'BADWMC', width: 270 }
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 85, 
        autowidth:true,
        multiselect: true,
        autowidth:true,
        shrinkToFit:false,
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
		scJq: {}
	},
	methods: {
		query: function () {
			var cph = $("#carNum").val().trim().replace(/\s/g,"");
			cph = cph.toUpperCase();
			if(cph == null || cph == "") {
				$("#jqGrid").jqGrid('setGridParam',{ 
					url: baseURL + 'generator/scjq/list',
	                page:1
	            }).trigger("reloadGrid");
			} else {
				$("#jqGrid").jqGrid('setGridParam',{ 
					url: baseURL + 'generator/scjq/find',
					postData:{'cph': cph},
					page:1
				}).trigger("reloadGrid");
			}
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.scJq = {};
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
			var url = vm.scJq.id == null ? "generator/scjq/save" : "generator/scjq/update";
			vm.scJq.asjfssj = $("#asjfssj").val();
			vm.scJq.asjjssj = $('#asjjssj').val();
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.scJq),
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
				    url: baseURL + "generator/scjq/delete",
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
			$.get(baseURL + "generator/scjq/info/"+id, function(r){
                vm.scJq = r.scJq;
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