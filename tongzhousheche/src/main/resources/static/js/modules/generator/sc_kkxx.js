$(function() {
	$("#jqGrid")
			.jqGrid(
					{
						url : baseURL + 'generator/scetcptjd/list',
						datatype : "json",
						colModel : [
								{
									label : '卡口编号',
									name : 'id',
									index : 'districtNm',
									width : 80,
									formatter : function(cellvalue, options,
											rowObject) {
										return "<a href='sc_kkxxxx.html'>" + cellvalue + "</a>";
									}
								}, {
									label : '卡口名称',
									name : 'mc',
									index : 'MC',
									width : 80
								}, {
									label : '通过车辆数',
									name : 'number',
									index : 'number',
									width : 80
								} ],
						viewrecords : true,
						height : 385,
						rowNum : 10,
						rowList : [ 10, 30, 50 ],
						rownumbers : true,
						rownumWidth : 25,
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


var date = new Date();
var year = date.getFullYear();
var month = date.getMonth() + 1;
var day = date.getDate();
var time = year + "-" + month + "-" + day;
var date = year + "-" + month + "-" + (day - 7);
if(month < 10) {
	time = year + "-0" + month + "-" + day;
	date = year + "-0" + month + "-" + (day - 7);
}

var vm = new Vue({
	el : '#rrapp',
	data : {
		showList : true,
		title : null,
		q : {
			kid : null,
			startTime : date,
			endTime : time
		}
	},
	methods : {
		find : function() {
			var id = $("#kid").val();
			sessionStorage.setItem("kid", id);
			var start = $("#startTime").val();
			sessionStorage.setItem("start", start);
			var end = $("#endTime").val();
			sessionStorage.setItem("end", end);
			
			var start = IsDate(vm.q.startTime);
			if (start == 1) {
				alert("时间输入有误!正确格式为yyyy-mm-dd");
				return false;
			}
			var end = IsDate(vm.q.endTime);
			if (end == 1) {
				alert("时间输入有误!正确格式为yyyy-mm-dd");
				return false;
			}
			if ((vm.q.startTime!=null&& vm.q.startTime!="")&&(vm.q.endTime!=null&& vm.q.endTime!="")) {
				compareDate(vm.q.startTime,vm.q.endTime);	
			}
			vm.reload();
		},
		map : function() {
			vm.reload();
		},
		reload : function(event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
				url : baseURL + 'generator/sc_kkxx/find',
				postData : {
					'kid' : vm.q.kid,
					'start' : vm.q.startTime,
					'end' : vm.q.endTime
				},
				page : page
			}).trigger("reloadGrid");
		}
	}
});

//时间比较（yyyy-MM-dd）
function compareDate(startDate, endDate) {
  var arrStart = startDate.split("-");
  var startTime = new Date(arrStart[0], arrStart[1], arrStart[2]);
  var startTimes = startTime.getTime();
  var arrEnd = endDate.split("-");
  var endTime = new Date(arrEnd[0], arrEnd[1], arrEnd[2]);
  var endTimes = endTime.getTime();
  if (endTimes < startTimes) {
      alert("结束时间不能小于开始时间");
      return false;
  }else if ((endTimes-startTimes)>(90*24*60*60*1000)) {
	  alert("时间段最长不超过90天");
	  return false;
  }
  return true;
}

/**
	判断输入框中输入的日期格式为yyyy-mm-dd和正确的日期
*/
function IsDate(mystring) {
	var reg = /^(\d{4})-(\d{2})-(\d{2})$/;
	var str = mystring;
	var arr = reg.exec(str);
	if (!reg.test(str)&&RegExp.$2<=12&&RegExp.$3<=31){
		return 1;
	}
	return 0;
}
