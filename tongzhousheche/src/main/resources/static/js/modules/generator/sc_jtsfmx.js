$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + '/generator/sc_jtsfmx/list',
        datatype: "json",
        colModel: [			
        	{ label: '编号', name: 'drybh', index: 'DRYBH', width: 70, key: true },
			{ label: '姓名', name: 'xm', index: 'XM', width: 85 },
			{ label: '性别', name: 'xb', index: 'XB', width: 45,
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
			{ label: '车牌号', name: 'cp', index: 'CP', width: 80 },
			{ label: '户籍地区划', name: 'hjdqh', index: 'HJDQH', width: 290 },
			{ label: '户籍地详址', name: 'hjdxz', index: 'HJDXZ', width: 290 },
			{ label: '现住地区划', name: 'xzdqh', index: 'XZDQH', width: 290 },
			{ label: '现住地详址', name: 'xzdxz', index: 'XZDXZ', width: 290 },
			{ label: '电话手机', name: 'dianhua', index: 'DIANHUA', width: 100 },
			{ label: '网络身份', name: 'wangluo', index: 'WANGLUO', width: 160 },
			{ label: '民族', name: 'mz', index: 'MZ', width: 85 },
			{ label: '上访', name: 'shangfang', index: 'SHANGFANG', width: 70,
				formatter : function(cellvalue, options, rowObject) {
					if(cellvalue == true) {
						cellvalue = '是';
					} else {
						cellvalue = "否";
					}
					return cellvalue;	
				}
				},
			{ label: '精神病', name: 'jingshenbing', index: 'JINGSHENBING', width: 80, 
					formatter : function(cellvalue, options, rowObject) {
						if(cellvalue == true) {
							cellvalue = '是';
						} else {
							cellvalue = "否";
						}
						return cellvalue;	
					}	
			},
			{ label: '简要案情', name: 'jyaq', index: 'JYAQ', width: 160 }
			
        ],
		viewrecords: true,
        height:400,
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
            cp:"cp", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "" }); 
        }
    });
});

function get(plate) {
	vm.alarm(plate);
}

	var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			checked1: null,
			checked2: null,
			xm:null
		},
		scAlarminfo:{},
		showList: true,
		title:null,
		roleList:{}
		
	},
	methods: {
		query: function () {
			vm.reload();
		},
		show : function() {
			vm.showList = false;
			vm.title = "推送";
		},
		alarm : function(plate) {
			var url = baseURL + 'generator/sc_jtsfmx/alarm';
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
		reload: function () {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			var checked1 =vm.q.checked1;
			var checked2 =vm.q.checked2;
			var xm = vm.q.xm; 
			if(checked1 != null || checked1 != "" || checked2 != null || checked != ""|| xm != null || xm != ""){
				$("#jqGrid").jqGrid('setGridParam',{ 
					url: baseURL + 'generator/sc_jtsfmx/find',
	                postData:{'shangfang': vm.q.checked1,'jingshenbing': vm.q.checked2,'xm':$("#xm").val().trim().replace(/\s/g,"")},
	                page:1
	            }).trigger("reloadGrid");
			}else{
				$("#jqGrid").jqGrid('setGridParam',{ 
					url: baseURL + 'generator/sc_jtsfmx/list',
					page:page
				}).trigger("reloadGrid");
			}
		}
	}
	
        
});
	