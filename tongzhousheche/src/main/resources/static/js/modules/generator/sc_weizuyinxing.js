var jList = new Array();
var wList = new Array();
var cph=sessionStorage.getItem("cph");
var ss=null;
var ph="/generator/sc_weizuyinxing/find" ;
var pag=sessionStorage.getItem("pag");
     sessionStorage.removeItem("pag");
$(function () {
	if(cph){
		$("#cph").val(cph);
		ph+="?plate="+cph;
		sessionStorage.removeItem("cph");
	}
	if (pag!=null&&pag!=""&&pag!="null") {
		
	}else {
		pag=1;
	}
    $("#jqGrid").jqGrid({
        url: baseURL + ph,
        page:pag,
        datatype: "json",
        colModel: [
			{ label: '编号', name: 'drybh', index: 'DRYBH', width: 80, key: true },
			{ label: '姓名', name: 'xm', index: 'XM', width: 70 },
			{ label: '性别', name: 'xb', index: 'XB', width: 50,
				formatter : function(cellvalue, options, rowObject) {
					if(cellvalue) {
						cellvalue = '男';
					}else {
						cellvalue = '女';
					}
					return cellvalue;	
				}
			},
			{ label: '车牌号', name: 'cp', index: 'CP', width: 90 },
			{ label: '身份证号', name: 'sfzh', index: 'SFZH', width: 160 },
			{ label: '民族', name: 'mz', index: 'MZ', width: 80 },
			{ label: '现住地详址', name: 'xzdxz', index: 'XZDXZ', width: 260 },
			{ label: '维族重点人', name: 'weizhu', index: 'WEIZHU', width: 90, 
				formatter : function(cellvalue, options, rowObject) {
					if(cellvalue) {
						cellvalue = '是';
					}else {
						cellvalue = '否';
					}
					return cellvalue;	
				}
			},
			{ label: '查关系人', name: 'qincai', index: 'QINCAI', width: 100,
				formatter : function(cellvalue, options, rowObject) {
					return '<button class="btn btn-default"  style="background: #337ab7;color:#fff;" onclick= "find(\''+rowObject.sfzh+'\')">'
							+ '查关系人' + '</button>';	
				}
			},
			{ label: '查轨迹', name: 'shedu', index: 'SHEDU', width: 100, 
				formatter : function(cellvalue, options, rowObject) {
					return '<button class="btn btn-default"  style="background: #337ab7;color:#fff;" onclick= "map(\''+rowObject.cp+'\')">'
							+ '查轨迹' + '</button>';	
				}
			},
			{ label: '设为维族重点人', name: 'jyaq', index: 'JYAQ', width: 150,
				formatter : function(cellvalue, options, rowObject) {
					return '<button class="btn btn-default"  style="background: #337ab7;color:#fff;" onclick= "save(\''+rowObject.cp+'\')">'
							+ '设为维族重点人' + '</button>';	
				}
			}
        ],
		viewrecords: true,
        height: 530,
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

function find(sfzh) {
	location.href = "sc_rcgls.html?sfzh=" + sfzh;
}

function guiji(sfzh) {
	var url = baseURL + 'generator/sc_gwrysj/check';
	var data = sfzh;
	$.ajax({
		type : "POST",
		url : url,
		contentType : "application/json",
		data : data,
		success : function(r) {
			var plate = '';
			console.log(r.list);
			if(r.list.length != 0) {
				plate = r.list[0].plate;
				
			} 
			map(plate);
		}
	});
}

function getNowFormatDate() {
	var date = new Date();
	var seperator1 = "-";
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var strDate = date.getDate();
	if (month >= 1 && month <= 9) {
		month = "0" + month;
	}
	if (strDate >= 0 && strDate <= 9) {
		strDate = "0" + strDate;
	}
	var currentdate = year + seperator1 + month + seperator1 + strDate;
	return currentdate;
}

function get30DaysBefore(date) {
	var date = date || new Date(), timestamp, newDate;
	if (!(date instanceof Date)) {
		date = new Date(date.replace(/-/g, '-'));
	}
	timestamp = date.getTime();
	newDate = new Date(timestamp - 30 * 24 * 3600 * 1000);
	var month = newDate.getMonth() + 1;
	if (month >= 1 && month <= 9) {
		month = "0" + month;
	}
	var date = newDate.getDate();
	if (date >= 0 && date <= 9) {
		date = "0" + date;
	}
	return [ [ newDate.getFullYear(), month, date ].join('-') ].join(' ');
}

var endTime = getNowFormatDate();
var startTime = get30DaysBefore(endTime);

function map(cph) {
	var t1 = getBeforeDate(7);
	var t2 = getBeforeDate(0);
	var pag = $("#jqGrid").jqGrid('getGridParam','page');
	sessionStorage.setItem("cph", cph);
	sessionStorage.setItem("carn",$("#cph").val());
	sessionStorage.setItem("pag", pag);
	sessionStorage.setItem("js", t2);
	sessionStorage.setItem("qs", t1);
	window.location.href=baseURL +'vasMap/views/ziguijitu.html';
}

function getBeforeDate(n) {
	var n = n;
	var d = new Date();
	var year = d.getFullYear();
	var mon = d.getMonth() + 1;
	var day = d.getDate();
	if (day <= n) {
		if (mon > 1) {
			mon = mon - 1;
		} else {
			year = year - 1;
			mon = 12;
		}
	}
	d.setDate(d.getDate() - n);
	year = d.getFullYear();
	mon = d.getMonth() + 1;
	day = d.getDate();
	var hour = d.getHours();
	var min = d.getMinutes();
	var sen = d.getSeconds();
	s = year + "-" + (mon < 10 ? ('0' + mon) : mon) + "-"
			+ (day < 10 ? ('0' + day) : day) + " "
			+ (hour < 10 ? ('0' + hour) : hour) + ":"
			+ (min < 10 ? ('0' + min) : min) + ":"
			+ (sen < 10 ? ('0' + sen) : sen);
	return s;
}

function save(plate) {
	var url = baseURL + 'generator/sc_weizuyinxing/update';
	var data = plate;
	$.ajax({
		type : "POST",
		url : url,
		contentType : "application/json",
		data : data,
		success : function(r) {
			alert("操作成功！");
			vm.reload();
		}
	});
}

var vm = new Vue({
	el:'#rrapp',
	data:{
	},
	methods: {
		find: function() {
			var cp = $("#cph").val().trim().replace(/\s/g,"");
			cp = cp.toUpperCase();
			if(cp == null || cp == "") {
				$("#jqGrid").jqGrid('setGridParam',{ 
					url: baseURL + 'generator/sc_weizuyinxing/find',
	                page:1
	            }).trigger("reloadGrid");
			} else {
				$("#jqGrid").jqGrid('setGridParam',{ 
					url: baseURL + 'generator/sc_weizuyinxing/query',
					postData: {'cp' : cp},
					page:1
				}).trigger("reloadGrid");
			}
		},
		reload: function (event) {
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
				url: baseURL + 'generator/sc_weizuyinxing/find',
                page:page
            }).trigger("reloadGrid");
		}
	}
});