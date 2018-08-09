$(function () {
	//window.location=window.location.href + "#rrapp";
    $("#jqGrid").jqGrid({
        url: baseURL + 'generator/scwsjdcjbxx/list',
        datatype: "json",
        colModel: [			
			{ label: '机动车所有人', name: 'syr', index: 'SYR', width: 140 }, 			
			{ label: '车牌号', name: 'cph', index: 'CPH', width: 130 },
			{ label: '身份证明号码', name: 'sfzmhm', index: 'SFZMHM', width: 170 }, 			
			{ label: '初次登记日期', name: 'ccdjrq', index: 'CCDJRQ', width: 170 }, 			
			{ label: '住所详细地址', name: 'zsxxdz', index: 'ZSXXDZ', width: 240 }, 			
			{ label: '车辆型号', name: 'clxh', index: 'CLXH', width: 100 }, 			
			{ label: '车身颜色代码', name: 'csys', index: 'CSYS', width: 100 }, 			
			{ label: 'dataid', name: 'dataid', index: 'DATAID', width: 110, key: true },
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
	var carNum = $("#cph").val();
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
		scWsjdcjbxx: {},
		scQgztryxx:{
			xm:null,
			csrq:$("#qssj").val(),
			sfzh:null,
			hjdqh:null,
			hjdz:null,
			ajbh:null,
			ajlb:null
		}
	},
	methods: {
		query: function () {
			var cph = $("#cph").val().trim().replace(/\s/g,"");
			cph = cph.toUpperCase();
			if(cph == null || cph == "") {
				$("#jqGrid").jqGrid('setGridParam',{ 
					url: baseURL + 'generator/scwsjdcjbxx/list',
	                page:1
	            }).trigger("reloadGrid");
			} else {
				$("#jqGrid").jqGrid('setGridParam',{ 
					url: baseURL + 'generator/scwsjdcjbxx/find',
					postData:{'cph': cph},
					page:1
				}).trigger("reloadGrid");
			}
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.scQgztryxx = {};
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
			var url = vm.scWsjdcjbxx.dataid == null ? "generator/scwsjdcjbxx/save" : "generator/scwsjdcjbxx/update";
			vm.scWsjdcjbxx.ccdjrq=$("#qssj").val();
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.scWsjdcjbxx),
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
				    url: baseURL + "generator/scwsjdcjbxx/delete",
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
				    url: baseURL + "generator/scwsjdcjbxx/delete",
                    contentType: "application/json",
				    data: JSON.stringify(dataids),
				    success: function(r){
					
					}
				});
		
		},
		getInfo: function(dataid){
			$.get(baseURL + "generator/scwsjdcjbxx/info/"+dataid, function(r){
                vm.scWsjdcjbxx = r.scWsjdcjbxx;
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