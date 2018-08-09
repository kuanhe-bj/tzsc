var c = "";
var url = 'generator/zfyc/page';
var cph = sessionStorage.getItem("cph");
var pag = sessionStorage.getItem("pag");
sessionStorage.removeItem("pag");
$(function() {
	if(cph){
		$("#plate").val(cph);
		url += "?plate=" + cph;
		sessionStorage.removeItem("cph");
	}
	if (pag != null && pag != "" && pag != "null") {
		
	}else {
		pag = 1;
	}
	
	$("#jqGrid") 
			.jqGrid(
					{
						url : baseURL + url,
						datatype : "json",
						page : pag,
						colModel : [
								{
									label : '车牌号',
									name : 'plate',
									index : 'plate',
									width : 50,
									key : true,
									formatter : function(cellvalue, options, rowObject) {
										return '<a class="btn btn-default"  style="background: #337ab7;color:#fff;width:100px;" onclick="infor(\''
												+ cellvalue
												+ '\')">'
												+ cellvalue + '</a>';
									}
								}, {
									label : '车主姓名',
									name : 'owner',
									index : 'owner',
									width : 80
								}, {
									label : '车辆颜色',
									name : 'color',
									index : 'color',
									width : 80
								}, {
									label : '车辆型号',
									name : 'model',
									index : 'model',
									width : 80
								}, {
									label : '昼伏夜出指数',
									name : 'nightout',
									index : 'nightout',
									width : 80
								} ],
						viewrecords : true,
						height : 512,
						rowNum : 10,
						rowList : [ 10, 30, 50 ],
						rownumbers : true,
						rownumWidth : 55,
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

var filePath=null;
var text=null;
var vm = new Vue({
	el : '#rrapp',
	data : {
		q : {
			plate : null,
			dr:null
		},
		DynamicVehicleEctronicFileVo : {},
		showList : true,
		title : null,
		roleList : {}
	},
	methods : {
		query : function() {
          //实现查询功能
			if(vm.q.plate!=null&&vm.q.plate!=""){
				var carNum = $("#carNum").val().trim().replace(/\s/g,"");
				carNum = carNum.toUpperCase();
				$("#jqGrid").jqGrid('setGridParam',{
					url: baseURL + 'generator/zfyc/list',
					postData:{'plate': carNum},
					page:1
				}).trigger("reloadGrid");
			}else{
				window.location.href=baseURL + '/modules/manager/sc_zfyc.html';
			
			}
		},
		reloa : function() {
            //自动加载列表
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
				url : baseURL + 'generator/zfyc/list',
				page : page
			}).trigger("reloadGrid");
		},
		ss:function(){
			window.location.href= baseURL + 'modules/manager/zfyc_ss.html';
		},
		rel : function() {
            //查询按钮方法
			var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			var carNum = $("#carNum").val();
			qkg(vm.q.plate);
			if(carNum == null || carNum == "") {
				$("#jqGrid").jqGrid('setGridParam',{
					url: baseURL + 'generator/zfyc/page',
	                page:page
	            }).trigger("reloadGrid");
			} else {
				$("#jqGrid").jqGrid('setGridParam',{
					url: baseURL + 'generator/zfyc/page',
					 postData:{'plate': c},
					page:1
				}).trigger("reloadGrid");
			}
			
			c="";
		}
	}

});

function infor(cph) {
   //跳页面传递车牌数据
	var t1 = getBeforeDate(7);
	var t2 = getBeforeDate(0);
	var pag = $("#jqGrid").jqGrid('getGridParam','page');
	sessionStorage.setItem("cph", cph);
	sessionStorage.setItem("carn",$("#carNum").val());
	sessionStorage.setItem("pag", pag);
	sessionStorage.setItem("js", t2);
	sessionStorage.setItem("qs", t1);
	window.location.href = baseURL + 'vasMap/views/ziguijitu.html';
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

//输入去空格
function qkg(a){
	 var b=a.trim().split(" ");
	 for(var i=0;i<b.length;i++){
		 if(b[i]!=null){
			 c=c+b[i];
		 }
			
	}
}

