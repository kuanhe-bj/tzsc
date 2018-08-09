$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'generator/scviw/list',
        datatype: "json",
        colModel: [			
			{ label: '车牌号', name: 'carnum', index: 'carNum', width: 80 },
			{ label: '触发时间', name: 'triggertime', index: 'triggerTime', width: 80 },
			{ label: '触发原因', name: 'triggerreason', index: 'triggerReason', width: 80 },
			{ label: '读取状态', name: 'readstate', index: 'readState', width: 80 },
			{ label: '推送状态', name: 'pushstate', index: 'pushState', width: 80,
				formatter : function(cellvalue, options, rowObject) {
					if(cellvalue == '0') {
						cellvalue = '未推送';
					} else {
						cellvalue = '已推送';
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
		scViw: {}
	},
	methods: {
		query: function () {
			var cph = $("#carNum").val().trim().replace(/\s/g,"");
			if(cph == null || cph == "") {
				$("#jqGrid").jqGrid('setGridParam',{ 
					url: baseURL + 'generator/scviw/list',
	                page:1
	            }).trigger("reloadGrid");
			} else {
				cph=cph.toUpperCase();
				$("#jqGrid").jqGrid('setGridParam',{ 
					url: baseURL + 'generator/scviw/find',
					postData:{'cph': cph},
					page:1
				}).trigger("reloadGrid");
			}
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.scViw = {};
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
			var url = vm.scViw.id == null ? "generator/scviw/save" : "generator/scviw/update";
			vm.scViw.triggertime = $("#triggertime").val();
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.scViw),
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
				    url: baseURL + "generator/scviw/delete",
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
			$.get(baseURL + "generator/scviw/info/"+id, function(r){
                vm.scViw = r.scViw;
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