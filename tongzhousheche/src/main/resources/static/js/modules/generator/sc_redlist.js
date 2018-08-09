$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'generator/sc_redlist/list',
        datatype: "json",
        colModel: [			
			{ label: '车牌号', name: 'carnum', index: 'carNum', width: 80 },
			{ label: '添加时间', name: 'createtime', index: 'createTime', width: 80 },
			{ label: '添加人', name: 'createuser', index: 'createUser', width: 80 },
			{ label: '添加原因', name: 'createreason', index: 'createReason', width: 80 },
			{ label: '审核人', name: 'audituser', index: 'auditUser', width: 80 },
			{ label: '审核时间', name: 'audittime', index: 'auditTime', width: 80 },
			{ label: '审核意见', name: 'auditopinion', index: 'auditOpinion', width: 80 },
			{
				label : '审核',
				name : 'carnum',
				index : 'carNum',
				width : 80,
				formatter : function(cellvalue, options,
						rowObject) {
					var res = rowObject.auditresults;
					if(res == 1) {
						return '<button class="btn btn-default" style="background: #337ab7;color:#fff;" onclick="check2(\'' + cellvalue + '\')">审核通过</button>';
					} else {
						return '<button class="btn btn-default" style="background: #d9534f;color:#fff;" onclick="check1(\'' + cellvalue + '\')">审核未通过</button>';
					}
				}
			}
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

var c = true;

$(window).load(function() {
	if(c) {
		var url = 'generator/sc_blacklist/color';
		$.ajax({
			type : "POST",
			url : baseURL + url,
			contentType : "application/json",
			success : function(obj) {
				for(var i = 0;i < obj.list.length; i++) {
					var opt = $("<option></option>");
					opt.attr("value",obj.list[i].value);
					opt.text(obj.list[i].color);
					$("#color").append(opt);
					c = false;
				}
			}
		});
	}
})

function check1(cph) {
	var result = window.prompt("请输入审核意见！", "");
	var url = 'generator/sc_redlist/check1';
		$.ajax({
			type : "POST",
			url : baseURL + url,
			contentType : "application/json",
			data : {'cph' : cph, 'result' : result},
			success : function(obj) {
				alert('操作成功', function(index) {
					$("#jqGrid").trigger("reloadGrid");
				});
			}
		});
}

function check2(cph) {
	var url = 'generator/sc_redlist/check2';
	$.ajax({
		type : "POST",
		url : baseURL + url,
		contentType : "application/json",
		data : cph,
		success : function(obj) {
			alert('操作成功', function(index) {
				$("#jqGrid").trigger("reloadGrid");
			});
		}
	});
}

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		selected1: '请选择',
		scRedlist:{},
		sc_redlist:'',
		display:'none'
	},
	methods: {
		color: function() {
			if(c) {
				var url = 'generator/sc_blacklist/color';
				$.ajax({
					type : "POST",
					url : baseURL + url,
					contentType : "application/json",
					success : function(obj) {
						for(var i = 0;i < obj.list.length; i++) {
							var opt = $("<option></option>");
							opt.attr("value",obj.list[i].value);
							opt.text(obj.list[i].color);
							$("#color").append(opt);
							c = false;
						}
					}
				});
			}
		},
		add: function(){
			vm.showList = false;
		},
		check:function() {
			vm.title = "新增";
			var url = "generator/sc_redlist/check";
			var data = this.$refs.sc_redlist.value;
			vm.scRedlist.createtime = $("#createtime").val();
			$.ajax({
				type:"POST",
				url:baseURL + url,
				contentType: "application/json",
				data: {'carnum' : data},
				success:function(obj) {
					if(obj.num == 0 ) {
						vm.same();
					} else {
						alert("没有查找到相关信息！");
						$("#jqGrid").trigger("reloadGrid");
					}
				}
			})
		},
		same : function() {
			var url = 'generator/sc_redlist/same';
			var data = this.$refs.sc_redlist.value;
			$.ajax({
				type : "POST",
				url : baseURL + url,
				contentType : "application/json",
				data : {'carNum' : data},
				success : function(r) {
			    	if(r.num === 0){
			    		vm.save();
					}else{
						alert("该车牌已经存在！");
						vm.reload();
					}
				}
			});
		},
		save:function() {
			var url = 'generator/sc_redlist/save';
			vm.scRedlist.createtime = $("#createtime").val();
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.scRedlist),
			    success: function(r){
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
		find:function() {
			var carNum = $("#carNum").val().trim().replace(/\s/g,"");
			var czxm = $("#czxm").val();
			var clys = $("#clys").val();
			var clxh = $("#clxh").val();
			var clpp = $("#clpp").val();
			if(carNum == null || carNum == "") {
				$("#jqGrid").jqGrid('setGridParam',{ 
					url:baseURL + 'generator/sc_redlist/list',
	                page:1
	            }).trigger("reloadGrid");
			} else {
				carNum=carNum.toUpperCase();
				$("#jqGrid").jqGrid('setGridParam',{ 
					url:baseURL + 'generator/sc_redlist/find',
					postData:{'carNum':carNum,'czxm':czxm,'clys':clys,'clxh':clxh,'clpp':clpp},
					page:1
				}).trigger("reloadGrid");
			}
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "generator/scredlist/delete",
                    contentType: "application/json",
				    data: JSON.stringify(ids),
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
			});
		},
		getInfo: function(id){
			$.get(baseURL + "generator/scredlist/info/"+id, function(r){
                vm.scRedlist = r.scRedlist;
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

function check() {
	var carNum = $("#carNum").val();
	if (carNum.length < 10 &&  carNum != null && carNum !="" ) {
		$("#msg").css("color", "green");
		$("#msg").html("输入正确！");
	} else if(carNum.length >= 10){
		$("#msg").css("color", "red");
		$("#msg").html("输入长度不超过10个字符！");
	} 
}

function IsDate(mystring) {
	var reg = /^(\d{4})-(\d{2})-(\d{2})$/;
	var str = mystring;
	if (str=="") return 0;
	var arr = reg.exec(str);
	if (!reg.test(str) || reg[2]>12 || reg[3]>31) {
		return 1;
	} else {
		return 0;
	}
}

function time() {
	var createtime = $("#createtime").val();
	var start = IsDate(createtime);
	if (start == 1) {
		alert("时间输入有误!正确格式为yyyy-mm-dd");
		return false;
	}
	var audittime = $("#audittime").val();
	var end = IsDate(audittime);
	if (end == 1) {
		alert("时间输入有误!正确格式为yyyy-mm-dd");
		return false;
	}
}

