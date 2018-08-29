var jList = new Array();
var wList = new Array();
var cList = new Array();
var aList = new Array();
var count;
var jd;
var wd;
var adress;
var url = '';
var carNum = $("#carNum").val();
alert(carNum);
$(function () {
	var carnum = sessionStorage.getItem("carNum");
	var start = sessionStorage.getItem("start");
	var end = sessionStorage.getItem("end");

	if(carnum != null && carnum != "" && carnum != undefined) {
		url += baseURL +"generator/sc_etcptjd/find?carNum=" + carnum + "&start=" + start + "&end=" + end;
		$("#cph").val(carnum);
		$("#startTime").val(start);
		$("#endTime").val(end);
	}
	sessionStorage.removeItem("carNum");
	sessionStorage.removeItem("start");
	sessionStorage.removeItem("end");
	//console.log(url);
    $("#jqGrid").jqGrid({
        url: url,
        datatype: "json",
        colModel: [	
			{ label: '车牌号码', name: 'carNumber', index: 'CAR_NUMBER', width: 80 },
			{ label: '次数', name: 'count', index: 'num', width: 80 },
			{ label: '地址', name: 'adress', index: 'ADRESS', width: 80,
			  formatter : function(cellvalue, options, rowObject) {
				  jd = rowObject.jingdu;
				  wd = rowObject.weidu;
				  count = rowObject.count;
				  adress = rowObject.adress;
				  jList.push(jd);
				  wList.push(wd);
				  cList.push(count);
				  aList.push(adress);
				  return "<span>" + cellvalue + "</span>";
			  }
			}
        ],
		viewrecords: true,
        height: 610,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 85, 
        autowidth:true,
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
        	var re_records = $("#jqGrid").jqGrid('getGridParam', 'records'); //获取数据总条数
    	    if (re_records==0) {
				alert("暂无数据!")
			};
        	//隐藏grid底部滚动
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
        }
    });
});

function get(data) {
	var ids = $('#jqGrid').getDataIDs();
	var len = ids.length;
	for (var i = 0; i < len; i++) {
		var getRow = $('#jqGrid').getRowData(ids[i]);
		var carNum = getRow.carNumber;
		return carNum;
	}
}

function check() {
	var carNum = $("#cph").val();
	if (carNum.length < 10 &&  carNum != null && carNum != "" ) {
		$("#msg").css("color", "green");
		$("#msg").html("输入正确!");
	} else if(carNum == null || carNum == "") {
		$("#msg").css("color", "red");
		$("#msg").html("请输入车牌号");
	} else if(carNum.length >= 10){
		$("#msg").css("color", "red");
		$("#msg").html("输入长度不超10个字符！");
	} 
}

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		scEtcptjd: {}
	},
	methods: {
		find:function() {
			var carNum = $("#cph").val().trim().replace(/\s/g,"");
			var start = $("#startTime").val();
			var end = $("#endTime").val();
			if(carNum == null || carNum == "") {
				alert("请输入要查询的车牌号");
				return;
			}
			carNum.trim().replace(/\s/g,"");
			carNum=carNum.toUpperCase();
			if(start == null || start == ""){
				alert("请输入起始时间！");
				return;
			}
			if(end == null || end == ""){
				alert("请输入结束时间！");
				return;
			}
			url="generator/sc_etcptjd/find";
			$("#jqGrid").jqGrid('setGridParam',{
				url: baseURL + url,
				postData:{'carNum' : carNum, 'start' : start, 'end' : end},
				page:1
			}).trigger("reloadGrid");
		},
		map: function() {
			var cph = $("#cph").val().trim().replace(/\s/g,"");
			var start = $("#startTime").val();
			var end = $("#endTime").val();
			//console.log(cList+ "," + wList + "," + jList);
			//var cph = sessionStorage.getItem("carNum");
			if($("#cph").val() == null || $("#cph").val() == "") {
				alert("请输入要查询的车牌号");
				return;
			}
			location.href = baseURL + "./vasMap/views/luojiaodian.html" +
									  "?wList=" + wList + "&jList=" + jList + 
									  "&cList=" + cList + "&aList=" + aList +
									  "&carNum=" + cph + "&start=" + start + "&end=" + end;
		
		},
		reload: function (event) {
			url="generator/sc_etcptjd/find";
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			
			$("#jqGrid").jqGrid('setGridParam',{
				url: baseURL + url,
				page:page
			}).trigger("reloadGrid");
		}
	}
});

