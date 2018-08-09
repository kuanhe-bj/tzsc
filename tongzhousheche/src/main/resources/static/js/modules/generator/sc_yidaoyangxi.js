$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'generator/sc_yidaoyangxi/list',
        datatype: "json",
        colModel: [
			{ label: '编号', name: 'drybh', index: 'DRYBH', width: 80, key: true },
			{ label: '姓名', name: 'xm', index: 'XM', width: 80 },
			{ label: '性别', name: 'xb', index: 'XB', width: 50,
				formatter : function(cellvalue, options, rowObject) {
					if(cellvalue == 1) {
						cellvalue = '男';
					} else {
						cellvalue = '女';
					}
					return cellvalue;	
				}
			},
			{ label: '出生日期', name: 'csrq', index: 'CSRQ', width: 100,
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
			{ label: '车牌号', name: 'cp', index: 'CP', width: 100 },
			{ label: '现住地详址', name: 'xzdxz', index: 'XZDXZ', width: 200 },
			{ label: '侵财', name: 'qincai', index: 'QINCAI', width: 60,
				formatter : function(cellvalue, options, rowObject) {
					if(cellvalue) {
						cellvalue = '是';
					} else {
						cellvalue = '否';
					}
					return cellvalue;	
				}
			},
			{ label: '涉毒', name: 'shedu', index: 'SHEDU', width: 60,
				formatter : function(cellvalue, options, rowObject) {
					if(cellvalue) {
						cellvalue = '是';
					} else {
						cellvalue = '否';
					}
					return cellvalue;	
				}
			},
			{ label: '以盗养吸', name: 'yidaoyangxi', index: 'YIDAOYANGXI', width: 80,
				formatter : function(cellvalue, options, rowObject) {
					if(cellvalue) {
						cellvalue = '是';
					} else {
						cellvalue = '否';
					}
					return cellvalue;	
				}
			},
			{ label: '简要案情', name: 'jyaq', index: 'JYAQ', width: 200 }
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 85, 
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


var vm = new Vue({
	el:'#rrapp',
	data:{
		showList : true,
		title : null,
		checked1: '',
		checked2: '',
		checked3: '',
		scAlarminfo:{}
	},
	methods: {
		find: function () {
			vm.showList = true;
			var xm = $("#user").val().trim().replace(/\s/g,"");
			if((xm == null || xm == "") && (vm.checked1 != true) && (vm.checked2 != true) && (vm.checked3 != true)) {
				$("#jqGrid").jqGrid('setGridParam',{ 
					url: baseURL + 'generator/sc_yidaoyangxi/list',
	                page:1
	            }).trigger("reloadGrid");
			}
			$("#jqGrid").jqGrid('setGridParam',{ 
				url: baseURL + 'generator/sc_yidaoyangxi/find',
				postData:{'xm': xm, 'qincai': vm.checked1, 'shedu': vm.checked2, 'yidaoyangxi': vm.checked3},
                page:1
            }).trigger("reloadGrid");
		},
		show : function() {
			vm.showList = false;
			vm.title = "推送";
		},
		alarm : function(plate) {
			var url = baseURL + 'generator/sc_yidaoyangxi/alarm';
			var receiver = $("#receiver").val().trim().replace(/\s/g,"");
			var carnum = plate;
			if(carnum == null || carnum == "") {
				alert("您尚未查询任何车辆信息！");
				return;
			}
			if(receiver == null || receiver == "") {
				alert("请选择目标用户！");
				return;
			}
			$.ajax({
				url : url,
				data : JSON.stringify(vm.scAlarminfo),
				type : "POST",
				contentType : "application/json",
				success : function(r) {
					alert("操作成功！");
					vm.reload();
					$.getJSON(baseURL + "sys/user/info", function(r){
						var user = r.user;
						if(vm.scAlarminfo.receiver == user.username) {
							var audio = document.getElementById('audio'); 
							audio.play();
						}
					});
				}
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