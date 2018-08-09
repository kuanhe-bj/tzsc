$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'generator/scalarminfo/list',
        datatype: "json",
        colModel: [			
			{ label: '车牌号', name: 'carnum', index: 'carnum', width: 50 },
			{ label: '触发时间', name: 'triggertime', index: 'triggerTime', width: 50 },
			{ label: '数据来源', name: 'datasource', index: 'dataSource', width: 80 },
			{ label: '读取状态', name: 'carnum', index: 'carnum', width: 50,
				formatter : function(cellvalue, options, rowObject) {
					var res = rowObject.readstate;
					if(res == 1) {
						return '<button class="btn btn-default" style="background: #337ab7;color:#fff;" onclick="check2(\'' + cellvalue + '\')">已读</button>';
					} else {
						return '<button class="btn btn-default" style="background: #d9534f;color:#fff;" onclick="check1(\'' + cellvalue + '\')">未读</button>';
					}	
				}
			},
			{ label: '推送状态', name: 'pushstate', index: 'pushState', width: 50,
				formatter : function(cellvalue, options, rowObject) {
					if(cellvalue == '0') {
						cellvalue = '未推送';
					} else {
						cellvalue = '已推送';
					}
					return cellvalue;	
				}
			},
			{ label: '接受人', name: 'receiver', index: 'receiver', width: 55 },
			{ label: '报警信息', name: 'message', index: 'message', width: 150 }
        ],
		viewrecords: true,
        height: 512,
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

function check1(cph) {
	var url = 'generator/sc_alarminfo/check1';
	var ids = getSelectedRows();
	if(ids == null){
		return ;
	}
	
	$.ajax({
		type : "POST",
		url : baseURL + url,
		contentType : "application/json",
		data : JSON.stringify(ids),
		success : function(obj) {
			alert('操作成功', function(index) {
				$("#jqGrid").trigger("reloadGrid");
			});
		}
	});
}

function check2(cph) {

}

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		scAlarminfo: {}
	},
	methods: {
		query: function () {
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			var cph = $("#carNum").val().trim().replace(/\s/g,"");
			if(cph == null || cph == "") {
				$("#jqGrid").jqGrid('setGridParam',{ 
					url: baseURL + 'generator/scalarminfo/list',
	                page:1
	            }).trigger("reloadGrid");
			} else {
				cph=cph.toUpperCase();
				$("#jqGrid").jqGrid('setGridParam',{ 
					url: baseURL + 'generator/scalarminfo/find',
					postData:{'cph': cph},
					page:1
				}).trigger("reloadGrid");
			}
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.scAlarminfo = {};
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
			var url = vm.scAlarminfo.id == null ? "generator/scalarminfo/save" : "generator/scalarminfo/update";
			vm.scAlarminfo.triggertime = $('#triggertime').val();
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.scAlarminfo),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
							$.getJSON(baseURL + "sys/user/info", function(r){
								var user = r.user;
								if(vm.scAlarminfo.receiver == user.username) {
									var audio = document.getElementById('audio'); 
									audio.play();
								}
							});
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
				    url: baseURL + "generator/scalarminfo/delete",
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
			$.get(baseURL + "generator/scalarminfo/info/"+id, function(r){
                vm.scAlarminfo = r.scAlarminfo;
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