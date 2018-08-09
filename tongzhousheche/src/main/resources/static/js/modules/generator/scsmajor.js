var fanwei = 1;
var time = 1;
var cph;
var c = "";
var fakssj;
var nu = sessionStorage.getItem("nu");
var na = sessionStorage.getItem("na");
var ti = sessionStorage.getItem("ti");
var jh = sessionStorage.getItem("jh");
var pag = sessionStorage.getItem("page");
sessionStorage.removeItem("nu");
sessionStorage.removeItem("na");
sessionStorage.removeItem("ti");
sessionStorage.removeItem("jh");
sessionStorage.removeItem("page");
var a = 0;
var vm = new Vue(
		{
			el : '#rrapp',
			data : {
				q : {
					number : null,
					name : null,
					qssj : null,
					jssj : null,
					ladw : null,
					time : null,
					fanwei :null
				},
				showList : true,
				title : null,
				role : {}
			},
			methods : {
				query : function() {
					vm.reload();
				},
				// 清空数据
				qk : function() {
					vm.q.number = null;
					vm.q.name = null;
					vm.q.qssj = null;
					vm.q.jssj = null;
					vm.q.ladw = null;
					vm.q.time = null;
					vm.q.fanwei = null;

				},
				reload : function() {
					vm.showList = true;
					var number=vm.q.number;
					if (number!=null) {
						number = number.replace(/ /g,'');
					};
					var name=vm.q.name;
					if (name!=null) {
						name = name.replace(/ /g,'');
					};
					ph="generator/dsj/list";
					var page = $("#jqGrid").jqGrid('getGridParam', 'page');
					$("#jqGrid").jqGrid('setGridParam', {
						url: baseURL + ph,
						postData : {
							'number' : number,
							'name' : name
						},
						page:page
					}).trigger("reloadGrid");

				},
			}
		});
var ph=baseURL +"generator/dsj/list";
$(function() {
	   if (nu!=null&&nu!=""&&nu!="null") {
			ph=ph+"?number="+nu;
	   }else if (na!=null&&na!=""&&na!="null") {
			ph=ph+"?name="+na;
	   }
	
	$("#jqGrid").jqGrid({
			url : ph,
            datatype : "json",
			colModel : [
								{
									label : '案件编号',
									name : 'ajbh',
									index : "ajbh",
									width : 100,
									key : true
								},
								{
									label : '车牌号',
									name : 'cph',
									index : 'cph',
									width : 80,
								},
								{
									label : '案件名称',
									name : 'ajmc',
									index : "ajmc",
									width : 120
								},
								{
									label : '发案开始时间',
									name : 'fakssj',
									index : "fakssj",
									width : 150
								},
								{
									label : '立案单位',
									name : 'pcsgxid',
									index : "pcsgxid",
									width : 240
								},
								{label : '发案地点',name : 'faddid',index : "faddid",width : 260,
									formatter : function(cellvalue, options,rowObject) {
										 return '<a  style="color:blue;" onclick="address(\''+rowObject.cph+'\','+'\''+rowObject.fakssj+'\','+'\''+rowObject.jindu+'\','+'\''+rowObject.weidu+'\')">'+ cellvalue+'</a>';
									}},
								{
									label : '发案地点详址',
									name : 'faddxz',
									index : "faddxz",
									width : 260,
									formatter : function(cellvalue, options,rowObject) {
										 return '<a  style="color:blue;" onclick="address(\''+rowObject.cph+'\','+'\''+rowObject.fakssj+'\','+'\''+rowObject.jindu+'\','+'\''+rowObject.weidu+'\')">'+ cellvalue+'</a>';
	
									}
								}, {
									label : '简要案情',
									name : 'jyaq',
									index : "jyaq",
									width : 150
								} ],
						sortable : true,
						sortorder : 'asc',
						viewrecords : true,
						height : 620,
						rowNum : 10,
						rowList : [ 10, 30, 50 ],
						rownumbers : true,
						rownumWidth : 85,
						autowidth : true,
						shrinkToFit:false,  
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
								"overflow-x" : ""
							});
						}
					});
});

var jingdu = null;
var weidu = null;
var faddxz = null;
var allcar = {};
var a1 = null;
var b1 = null;
function qkg(a) {
	var b = a.trim().split(" ");
	for (var i = 0; i < b.length; i++) {
		if (b[i] != null) {
			c = c + b[i];
		}

	}
}

//跳转至地图
function address(cph, stime,ji,wei) {
	time = vm.q.time;
	fanwei = vm.q.fanwei;
	if (vm.q.time == null||vm.q.time =="") {
		
		time = 1;
	}
	if (vm.q.fanwei == null||vm.q.fanwei =="") {
		fanwei = 1;
	}
	var pag = $("#jqGrid").jqGrid('getGridParam', 'page');
	sessionStorage.setItem("weidu", wei);
	sessionStorage.setItem("page", pag);
	sessionStorage.setItem("jindu", ji);
	sessionStorage.setItem("fakssj", stime);
    sessionStorage.setItem("cph", cph);
    sessionStorage.setItem("number",vm.q.number);
	sessionStorage.setItem("name",vm.q.name);
	sessionStorage.setItem("time", time);
	sessionStorage.setItem("fanwei", fanwei);
	window.location.href=''+baseURL +'vasMap/views/dtwj.html';
}
