
$(function() {
	$("#jqGrid").jqGrid({
		url : baseURL + 'generator/sc_blacklist/list',
		datatype : "json",
		colModel : [{
			label : '车牌号',
			name : 'carnum',
			index : 'carNum',
			width : 80
		}, {
			label : '添加时间',
			name : 'createtime',
			index : 'createTime',
			width : 80
		}, {
			label : '添加人',
			name : 'createuser',
			index : 'createUser',
			width : 80
		}, {
			label : '添加原因',
			name : 'createreason',
			index : 'createReason',
			width : 80
		}, {
			label : '审核人',
			name : 'audituser',
			index : 'auditUser',
			width : 80
		}, {
			label : '审核时间',
			name : 'audittime',
			index : 'auditTime',
			width : 80
		},{
			label : '审核意见',
			name : 'auditopinion',
			index : 'auditOpinion',
			width : 80
		}, {
			label : '审核',
			name : 'carnum',
			index : 'carNum',
			width : 80,
			formatter : function(cellvalue, options,
					rowObject) {
				var res = rowObject.auditresults;
				//console.log(res);
				if(res == 1) {
					return '<button class="btn btn-default" style="background: #337ab7;color:#fff;" onclick="check2(\'' + cellvalue + '\')">审核通过</button>';
				} else {
					return '<button class="btn btn-default" style="background: #d9534f;color:#fff;" onclick="check1(\'' + cellvalue + '\')">审核未通过</button>';
				}
			}
		},{
			label : '布控',
			name : 'carnum',
			index : 'carNum',
			width : 80,
			formatter:function(cellvalue, options, rowObject){
				var status = rowObject.status;
				var res = rowObject.auditresults;
				var bk = rowObject.bkStatus;
				var bkid = rowObject.bkid;
				if(bk != 1) {
					return '<a class="btn btn-primary" style="background: #337ab7;color:#fff;width:100px;" onclick="bukong(\''+cellvalue+'\','+res+','+status+')">布控</a>';
				} else {
					return '<a class="btn btn-primary" style="background: #3CB371;color:#fff;width:100px;" onclick="chekong(\'' + cellvalue + '\','+bkid+')">撤控</a>';
				}
			}
		}
		],
		viewrecords : true,
		height : 512,
		rowNum : 10,
		rowList : [ 10, 30, 50 ],
		rownumbers : true,
		rownumWidth : 85,
		autowidth : true,
		multiselect : true,
		pager : "#jqGridPager",
		jsonReader : {
			root : "page.list",
			page : "page.currPage",
			total : "page.totalPage",
			records : "page.totalCount"
		},
		prmNames : {
			page : "page",
			rows : "limit",
			order : "order"
		},
		gridComplete : function() {
			// 隐藏grid底部滚动条
			$("#jqGrid").closest(".ui-jqgrid-bdiv").css({
				"overflow-x" : "hidden"
			});
		}
	});
});

function check1(cph) {
	var result = window.prompt("请输入审核意见！", "");
	var url = 'generator/sc_blacklist/check1';
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
	var url = 'generator/sc_blacklist/check2';
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


function bukong(plateNoType,res,status) {
	if(status == 1) {
		alert("该条信息无效,布控失败！");
		return ;
	} else if(res == 2) {
		alert("该条未通过审核,布控失败！");
		return ;
	} else if(status == 0 && res == 1) {
		var url = 'generator/sc_blacklist/disp';
		$.ajax({
			type : "POST",
			url : baseURL + url,
			contentType : "application/json",
			data : plateNoType,
			success : function(r) {
				if (r.res=="1") {
					alert('操作成功', function(index) {
						$("#jqGrid").trigger("reloadGrid");
					});
				}else {
					alert('布控失败！！');
				}
				
			}
		});
	}
}

function chekong(plate,bkid) {
	var plateNoType=plate+"="+bkid;
	$.ajax({
		type : "POST",
		url : baseURL + 'generator/sc_blacklist/chedisp',
		contentType : "application/json",
		data : plateNoType,
		success : function(r) {
			if (r.res=="1") {
				alert('撤销成功', function(index) {
					$("#jqGrid").trigger("reloadGrid");
				});
			}else {
				alert('撤销失败！！');
			}
			
		}
	});
}

var c = true;
var b = true;

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

var vm = new Vue({
	el : '#rrapp',
	data : {
		showList : true,
		title : null,
		selected1: '请选择',
		selected2: '请选择',
		sc_blacklist : "",
		scBlacklist : {}
	},
	methods : {
		color: function() {
			if(c) {
				var url = 'generator/sc_blacklist/color';
				$.ajax({
					type : "POST",
					url : baseURL + url,
					contentType : "application/json",
					success : function(obj) {
						for(var i = 0;i < obj.list.length; i++) {
							//console.log(obj.list[i].color);
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
		brand: function() {
			if(b) {
				var url = 'generator/sc_blacklist/brand';
				$.ajax({
					type : "POST",
					url : baseURL + url,
					contentType : "application/json",
					success : function(obj) {
						for(var i = 0;i < obj.list.length; i++) {
							var opt = $("<option></option>");
							opt.attr("value",obj.list[i].value);
							opt.text(obj.list[i].contact);
							$("#brand").append(opt);
							b = false;
						}
					}
				});
			}
		},
		add : function() {
			vm.showList = false;
		},
		check : function() {
			vm.showList = false;
			vm.title = "新增";
			var url = "generator/sc_blacklist/check";
			var data = this.$refs.sc_blacklist.value;
			vm.scBlacklist.createtime = $("#createtime").val();
			$.ajax({
				type : "POST",
				url : baseURL + url,
				contentType : "application/json",
				data : {'carNum' : data},
				success : function(obj) {
					if (obj.num == 0) {
						vm.same();
					} else {
						alert("没有查找到相关信息！");
						$("#jqGrid").trigger("reloadGrid");
					}
				}
			})
		},
		same : function() {
			var url = 'generator/sc_blacklist/same';
			var data = this.$refs.sc_blacklist.value;
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
		save : function() {
			var url = 'generator/sc_blacklist/save';
			$.ajax({
				type : "POST",
				url : baseURL + url,
				contentType : "application/json",
				data : JSON.stringify(vm.scBlacklist),
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
		find : function() {
			var carNum = $("#carNum").val().trim().replace(/\s/g,"");
			var czxm = $("#czxm").val();
			var clys = $("#clys").val();
			var clxh = $("#clxh").val();
			var clpp = $("#clpp").val();
			if(carNum == null || carNum == "") {
				$("#jqGrid").jqGrid('setGridParam', {
					url : baseURL + 'generator/sc_blacklist/list',
					page : 1
				}).trigger("reloadGrid");
			} else {
				carNum = carNum.toUpperCase();
				$("#jqGrid").jqGrid('setGridParam', {
					url : baseURL + 'generator/sc_blacklist/find',
					postData : {
						'carNum' : carNum,
						'czxm' : czxm,
						'clys' : clys,
						'clxh' : clxh,
						'clpp' : clpp
					},
					page : 1
				}).trigger("reloadGrid");
			}
		},
		del : function(event) {
			var ids = getSelectedRows();
			if (ids == null) {
				return;
			}

			confirm('确定要删除选中的记录？', function() {
				$.ajax({
					type : "POST",
					url : baseURL + "generator/scblacklist/delete",
					contentType : "application/json",
					data : JSON.stringify(ids),
					success : function(r) {
						if (r.code === 0) {
							alert('操作成功', function(index) {
								$("#jqGrid").trigger("reloadGrid");
							});
						} else {
							alert(r.msg);
						}
					}
				});
			});
		},
		reload : function(event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
				page : page
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

//判断日期格式
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