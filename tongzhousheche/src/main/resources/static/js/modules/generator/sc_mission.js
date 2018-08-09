

$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + '/generator/sc_mission/find',
        datatype: "json",
        colModel: [			
			{ label: '任务编号', 
				name: 'id',
				index: 'id',
				width: 80, 
				key: true
				},
			{ label: '创建者', name: 'creator', width: 100 },
			{ label: '所有者', name: 'owner', width: 100 },
			{ label: '任务类别', name: 'tasktype', width: 100 },
			{ label: '任务状态', name: 'status', width: 80,
				formatter:function(cellvalue, options, rowObject){
					 if(cellvalue == 0) {
						 cellvalue = '初始化'; 
					 } else if(cellvalue == 1) {
						 cellvalue = '计算中'; 
					 } else {
						 cellvalue = '完成';
					 }
					 return cellvalue;
				}
			},
			{ label: '创建时间', name: 'createtime', width: 160 },
			{ label: '任务内容', name: 'content', width: 400 },
			{ label: '参数', name: 'parameters', width: 400 },
			{ label: '分析',
				name: 'id', 
				width: 100 ,
				formatter:function(cellvalue, options, rowObject){
					 return '<a class="btn btn-primary"  style="background: #337ab7;color:#fff;" onclick="fenxi(\''+cellvalue+'\')">'+'分析'+'</a>';
				}	
			
			},
			{ label: '查看分析结果',
				name: 'rid', 
				width: 100 ,
				formatter:function(cellvalue, options, rowObject){
					 return '<a class="btn btn-primary"  style="background: #337ab7;color:#fff;" onclick="infor1(\''+cellvalue+'\')">'+'分析结果'+'</a>';
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
            cph:"cph", 
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
		q:{
			sel:null,
			time1: null,
			time2: null,
			num:null,
			btcxc:null,
			btcxd:null,
			wscxc:null,
			wscxd:null,
			fxc:null,
			fxd:null,
			btc:null,
			btd:null,
			ycc:null,
			ycd:null
		},
		scMission:{},
		showList: true,
		title:null,
	},
	methods: {
		query: function () {
			var myselect = document.getElementById("sel");
			var index = myselect.selectedIndex;
			console.log(index);
			if(index == -1) {
				alert("请选择要创建的任务类型！");
				return;
			}
			var mission = myselect.options[index].text;
			if(mission == null || mission == "") {
				alert("请选择要创建的任务类型！");
				return;
			}
			var start = $("#time1").val().trim().replace(/\s/g,"");
			var end = $("#time2").val().trim().replace(/\s/g,"");
			var num = $("#num").val().trim().replace(/\s/g,"");
			$("#jqGrid").jqGrid('setGridParam',{
				url: baseURL + 'generator/sc_mission/query',
				postData: {
					'mission' : mission,
					'start' : start,
					'end' : end,
					'num' : num,
					'btcxc' : $("#btcxc").val().trim().replace(/\s/g,""),
					'btcxd' : $("#btcxd").val().trim().replace(/\s/g,""),
					'wscxc' : $("#wscxc").val().trim().replace(/\s/g,""),
					'wscxd' : $("#wscxd").val().trim().replace(/\s/g,""),
					'fxc' : $("#fxc").val().trim().replace(/\s/g,""),
					'fxd' : $("#fxd").val().trim().replace(/\s/g,""),
					'btc' : $("#btc").val().trim().replace(/\s/g,""),
					'btd' : $("#btd").val().trim().replace(/\s/g,""),
					'ycc' : $("#ycc").val().trim().replace(/\s/g,""),
					'ycd' : $("#ycd").val().trim().replace(/\s/g,"")
					
				},
                page:1
            }).trigger("reloadGrid");
			window.location.reload();
		},
		tuisong: function() {
			vm.showList = false;
			vm.title = '推送';
			
		},
		copy: function() {
			var url = '/generator/sc_mission/copy';
			var id = $("#id").val();
			var owner = $("#owner").val();
			$.ajax({
				type : "POST",
				url : baseURL + url,
				contentType : "application/json",
				data : {'id':id,'owner':owner},
				success : function(r) {
			    	if(r.num === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		reload: function () {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
				$("#jqGrid").jqGrid('setGridParam',{
					url: baseURL + 'generator/sc_mission/find',
	                page:page
	            }).trigger("reloadGrid");
		},
		del: function (event) {
			var ids = getSelectedRows();
		
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "generator/sc_mission/delete",
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
		}
	}
	
        
});
	function fenxi(id) {
		var url1 = "generator/sc_mission/update";
		$.ajax({
			url: baseURL + url1,
			type: "POST",
            contentType: "application/json",
		    data: id,
		    success: function(r){
				if(r.code == 0){
					alert('正在运算中,请等待！', function(index){
						$("#jqGrid").trigger("reloadGrid");
					});
				}else{
					alert(r.msg);
				}
			}
		});
		
		var url2 = "generator/sc_mission/fenxi";
		$.ajax({
			url: baseURL + url2,
			type: "POST",
            contentType: "application/json",
		    data: id,
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
	}
	
	function infor1(id){
		
		var searchUrl =encodeURI(baseURL + 'modules/generator/sc_result.html?id='+id);
		
		location.href=searchUrl;

	}

