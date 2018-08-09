$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'generator/scgwrysj/list',
        datatype: "json",
        colModel: [
			{ label: '重点人员编号', name: 'drybh', index: 'DRYBH', width: 100, key: true },
			{ label: '姓名', name: 'xm', index: 'XM', width: 60 },
			{ label: '性别', name: 'xb', index: 'XB', width: 60,
				formatter : function(cellvalue, options, rowObject) {
					if(cellvalue == 1) {
						cellvalue = '男';
					} else {
						cellvalue = '女';
					}
					return cellvalue;	
				}
			},
			{ label: '出生日期', name: 'csrq', index: 'CSRQ', width: 90,
				formatter : function(cellvalue, options, rowObject) {
					if(cellvalue != null) {
						cellvalue = cellvalue.slice(0,cellvalue.indexOf(" "));
					} else {
						cellvalue = "";
					}
					return cellvalue;	
				}
			},
			{ label: '身份证号', name: 'sfzh', index: 'SFZH', width: 160 },
			{ label: '户籍地区划', name: 'hjdqh', index: 'HJDQH', width: 240 },
			{ label: '户籍地详址', name: 'hjdxz', index: 'HJDXZ', width: 240 },
			{ label: '现住地区划', name: 'xzdqh', index: 'XZDQH', width: 240 },
			{ label: '现住地详址', name: 'xzdxz', index: 'XZDXZ', width: 240 }
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

function add(data){
	var url = baseURL + 'generator/sc_gwrysj/save';
	if(data.list == null || data.list == "") {
		alert("没有符合的信息！");
		return;
	} else {
		var data = data.list[0].plate;
		$.ajax({
			url:url,
			data:data,
			type: "POST",
			contentType: "application/json",
			success: function(r) {
				update();
			}
		});
	}
}

function update() {
	var url = baseURL + 'generator/sc_gwrysj/update';
	var data = $("#sfzh").val();
	$.ajax({
		url:url,
		data:data,
		type: "POST",
		contentType: "application/json",
		success: function(r) {
		}
	});
}

function check() {
	var carNum = $("#sfzh").val();
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
		scGwrysj: {}
	},
	methods: {
		query: function () {
			var sfzh = $("#sfzh").val().trim().replace(/\s/g,"");
			if(sfzh == null || sfzh == "") {
				$("#jqGrid").jqGrid('setGridParam',{ 
					url: baseURL + 'generator/scgwrysj/list',
	                page:1
	            }).trigger("reloadGrid");
			} else {
				$("#jqGrid").jqGrid('setGridParam',{ 
					url: baseURL + 'generator/scgwrysj/find',
					postData:{'sfzh': sfzh},
					page:1
				}).trigger("reloadGrid");
			}
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.scGwrysj = {};
		},
		update: function (event) {
			var drybh = getSelectedRow();
			if(drybh == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(drybh)
		},
		check: function() {
			var url = baseURL + 'generator/sc_gwrysj/check';
			var data = $("#sfz").val();
			vm.scGwrysj.csrq = $("#sj").val();
			$.ajax({
				url:url,
				data:data,
				type: "POST",
				contentType: "application/json",
				success: function(r) {
					if(r.list != null) {
						update();
					}
					vm.saveOrUpdate();
				}
			})
		},
		saveOrUpdate: function (event) {
			var url = vm.scGwrysj.drybh == null ? "generator/scgwrysj/save" : "generator/scgwrysj/update";
           	if(vm.scGwrysj.xb ='男'){
                vm.scGwrysj.xb = 1;
			}else {
                vm.scGwrysj.xb = 0;
			}
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.scGwrysj),
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
			var drybhs = getSelectedRows();
			if(drybhs == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "generator/scgwrysj/delete",
                    contentType: "application/json",
				    data: JSON.stringify(drybhs),
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
		getInfo: function(drybh){
			$.get(baseURL + "generator/scgwrysj/info/"+drybh, function(r){
                vm.scGwrysj = r.scGwrysj;
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