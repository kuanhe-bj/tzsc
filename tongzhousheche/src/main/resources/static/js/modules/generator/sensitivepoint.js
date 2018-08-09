$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'generator/sensitivePoint/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '敏感地区类型', name: 'stype', index: 'sType', width: 80, 
				  formatter : function(cellvalue, options, rowObject) {
					  if(cellvalue === 0) {
						  cellvalue = '圆形';
					  } else if(cellvalue === 1) {
						  cellvalue = '方形';
					  } else{
						  cellvalue = '椭圆';
					  }
					  return cellvalue;
				  }
			}, 			
			{ label: '中心点坐标', name: 'center', index: 'center', width: 80 }, 			
			{ label: '长', name: 'x', index: 'x', width: 80 }, 			
			{ label: '宽', name: 'y', index: 'y', width: 80 }, 			
			{ label: '敏感地区级别:0-9', name: 'grade', index: 'grade', width: 80 }			
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
	var center = $("#center").val();
	var reg=/^[-\+]?\d+(\.\d+)? [-\+]?\d+(\.\d+)?$/
	if(!reg.test(center)) {
		alert("请输入正确的中心点坐标！");
	}
}

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		q:{
			jingdu : null,
			weidu : null,
			stype : null
		},
		sensitivepoint: {}
	},
	methods: {
		query: function () {
			var stype;
			if(vm.q.stype == "圆形") {
				stype = 0;
			} else if(vm.q.stype == "方形") {
				stype = 1;
			} else if(vm.q.stype == "椭圆形") {
				stype = 2;
			}
			var jingdu = $("#jingdu").val().trim().replace(/\s/g,"");
			var weidu = $("#weidu").val().trim().replace(/\s/g,"");
			if((stype == null || stype == "") 
			&& (jingdu == null || jingdu == "") 
			&& (weidu == null || weidu == "")) {
				$("#jqGrid").jqGrid('setGridParam',{
					url:baseURL + 'generator/sensitivePoint/list',
					page:1
				}).trigger("reloadGrid");
			} else {
				$("#jqGrid").jqGrid('setGridParam',{
					url:baseURL + 'generator/sensitivePoint/find',
					postData:{
						'jingdu': jingdu,
						'weidu': weidu,
						'stype': stype
					},
					page:1
				}).trigger("reloadGrid");
			}
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.sensitivepoint = {};
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(id);
		},
		saveOrUpdate: function (event) {
			var url = vm.sensitivepoint.id == null ? "generator/sensitivepoint/save" : "generator/sensitivepoint/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.sensitivepoint),
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
				    url: baseURL + "generator/sensitivepoint/delete",
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
			$.get(baseURL + "generator/sensitivepoint/info/"+id, function(r){
                vm.sensitivepoint = r.sensitivepoint;
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