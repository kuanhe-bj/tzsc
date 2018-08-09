var kdid;
var pssj;
var cph2;
var url = 'generator/sc_kkxx/bansui';
$(function() {
	var cph = sessionStorage.getItem("cph");
	var time = sessionStorage.getItem("time");
	var num = sessionStorage.getItem("num");
	var start = sessionStorage.getItem("start");
	var end = sessionStorage.getItem("end");
	if(cph != null && cph != "" && cph != undefined) {
		url += "?cph=" + cph + "&start=" + start + 
			   "&end=" + end + "&num=" + num + "&time=" + time;
		$("#cph").val(cph);
		$("#startTime").val(start);
		$("#endTime").val(end);
		$("#num").val(num);
		$("#time").val(time);
	}
	sessionStorage.removeItem("cph");
	sessionStorage.removeItem("num");
	sessionStorage.removeItem("time");
	sessionStorage.removeItem("start");
	sessionStorage.removeItem("end");
	
	$("#jqGrid").jqGrid({
						url : baseURL + url,
						datatype : "json",
						postData : {
							'cph' : $("#cph").val(),
							'start' : $("#startTime").val(),
							'end' : $("#endTime").val(),
							'num' : 2,
							'time' : 10
						},
						colModel : [{
									label : '车牌号',
									name : 'carNumber',
									index : 'car_number',
									width : 80,
									formatter:function(cellvalue, options, rowObject){
										 return '<a class="btn btn-primary"  style="background: #337ab7;color:#fff;width:100px;" onclick="infor(\''+cellvalue+'\')">'+cellvalue+'</a>';
									}},
								{
									label : '伴随次数',
									name : 'num',
									index : 'num',
									width : 80,
									formatter : function(cellvalue, options,
											rowObject) {
										return '<a onclick="getNum(\''+rowObject.carNumber+'\')">'
												+ cellvalue + "</a>";
									}
								},
								{
									label : '地图',
									name : 'carNumber',
									index : 'carNumber',
									width : 80,
									formatter : function(cellvalue, options,
											rowObject) {
										return '<button class="btn btn-default"  style="background: #337ab7;color:#fff;" onclick= "map(\''+rowObject.carNumber+'\')">'
												+ '地图' + '</button>';
									}
								},
								{
									label : '警报',
									name : 'districtNmId',
									index : 'districtNmId',
									width : 80,
									formatter : function(cellvalue, options,
											rowObject) {
										if (rowObject.districtNmId == "1") {
											return '<span id="msg"></span><audio id="audio" src="../../images/main/13.wav"></audio><input v-show="show" style="width:36px; height:36px;" type="image" src="../../images/main/HhCu-fynhhay5046228.gif" />';
										}else {
											return "-";
										}
									}}
								],
						viewrecords : true,
						height : 540,
						rowNum : 10,
						rowList : [ 10, 30, 50 ],
						rownumbers : true,
						rownumWidth : 85,
						autowidth : true,
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


//跳转至伴随车辆的详细页面
function getNum(cph) {
	var cph1 = $("#cph").val();
	var num = $("#num").val();
	var time = $("#time").val();
	if(num == null || num == "") {
		num = 2;
	}
	if(time == null || time == "") {
		time = 10;
	}
	var start = $("#startTime").val();
	var end = $("#endTime").val();
	sessionStorage.setItem("cph", cph1);
	sessionStorage.setItem("time", time);
	sessionStorage.setItem("num", num);
	sessionStorage.setItem("start", start);
	sessionStorage.setItem("end", end);
	cph2 = sessionStorage.setItem("cph2", cph);
	location.href = "sc_kkbsxxxx.html";
}

function img() {
	var ids = $('#jqGrid').getDataIDs();
	var len = ids.length;
	if(len != 0) {
		vm.show = true;
	}
}

function check() {
	var carNum = $("#cph").val();
	if (carNum.length < 10 &&  carNum != null && carNum !="" ) {
		$("#msg").css("color", "green");
		$("#msg").html("输入正确！");
	} else if(carNum == null || carNum =="") {
		$("#msg").css("color", "red");
		$("#msg").html("请输入车牌号");
	} else if(carNum.length >= 10){
		$("#msg").css("color", "red");
		$("#msg").html("输入长度不超过10个字符！");
	} 
}


var vm = new Vue({
	el : '#rrapp',
	data : {
		show : false,
		title : null,
		q : {
			cph : null,
			num : null,
			time : null
		}
	},
	methods : {
		find : function() {
			var cph = $("#cph").val();
			if(cph == null || cph == "") {
				alert("请输入要查询的车牌号！");
				return;
			}
			cph=cph.toUpperCase();
			var num = $("#num").val().trim().replace(/\s/g,"");
			var time = $("#time").val().trim().replace(/\s/g,"");
			if(num == null || num == "") {
				num = 2;
			}
			if(time == null || time == "") {
				time = 10;
			}
			var start = $("#startTime").val();
			var end = $("#endTime").val();
			var ru="generator/sc_kkxx/bansui";
			$("#jqGrid").jqGrid('setGridParam', {
				url : baseURL + ru,
				postData : {
					'cph' : cph.trim().replace(/\s/g,""),
					'start' : start,
					'end' : end,
					'num' : $.trim(num),
					'time' : $.trim(time)
				},
				page : 1
			}).trigger("reloadGrid");
			
			setTimeout(function() {
				img();
			}, 1000);
			
		},
		alarm : function() {
			var url = baseURL + 'generator/sc_kkxx/alarm';
			var receiver = $("#receiver").val();
			var carnum = $("#cph").val();
			if(carnum == null || carnum == "") {
				alert("您尚未查询任何信息！");
				return;
			}
			if(receiver == null || receiver == "") {
				alert("请选择目标用户！");
				return;
			}
			var data = {
					'receiver' : receiver,
					'carnum' : carnum
			};
			$.ajax({
				url : url,
				data : JSON.stringify(data),
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
		reload : function(event) {
			var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
				page : page
			}).trigger("reloadGrid");
		}
	}
});


// 时间比较（yyyy-MM-dd）
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
	}
	return true;
}

/**
 * 判断输入框中输入的日期格式为yyyy-mm-dd和正确的日期
 */
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

//跳转至地图
function map(plate) {
	var cph = $("#cph").val();
	var num = $("#num").val();
	var time = $("#time").val();
	var start = $("#startTime").val();
	var end = $("#endTime").val();
	if(num == null || num == "") {
		num = 2;
	}
	if(time == null || time == "") {
		time = 10;
	}
	sessionStorage.setItem("cph", cph);
	sessionStorage.setItem("time", time);
	sessionStorage.setItem("num", num);
	sessionStorage.setItem("start", start);
	sessionStorage.setItem("end", end);
	var url = baseURL + 'generator/sc_kkxx/ban';
	var cph1 = sessionStorage.getItem("cph");
	var time = sessionStorage.getItem("time"); 
	cph2 = plate;
	var data = {
		'cph1' : cph1,
		'cph2' : cph2,
		'time' : time
	};
	$.ajax({
		url : url,
		data : JSON.stringify(data),
		type : "POST",
		contentType : "application/json",
		success : function(r) {
			var jList = new Array();
			var wList = new Array();
			var p1List = new Array();
			var p2List = new Array();
			var c1List = new Array();
			var c2List = new Array();
			var adress = new Array();
			for ( var i in r.data) {
				jList.push(r.data[i].jingdu);
				wList.push(r.data[i].weidu);
				p1List.push(r.data[i].pssj1);
				p2List.push(r.data[i].pssj2);
				c1List.push(r.data[i].cph1);
				c2List.push(r.data[i].cph2);
				adress.push(r.data[i].adress);
			}
			var url = encodeURI(baseURL + "./vasMap/views/scbansui.html"
					+ "?jList=" + jList + "&wList=" + wList + "&p1List="
					+ p1List + "&p2List=" + p2List + "&c1List=" + c1List
					+ "&c2List=" + c2List+ "&adress=" + adress);
			location.href = url;
		}
	});
}

function infor(cph){
   parent.sayhello("modules/manager/scparkcheck.html",cph);
}
